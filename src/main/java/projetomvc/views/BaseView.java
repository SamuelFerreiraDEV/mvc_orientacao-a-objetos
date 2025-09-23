package projetomvc.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import projetomvc.controllers.interfaces.Controller;
import projetomvc.models.entities.Author;
import projetomvc.models.entities.Book;
import projetomvc.views.interfaces.EntityView;
import projetomvc.views.interfaces.ViewNavigator;

public abstract class BaseView<T> extends javax.swing.JFrame implements EntityView<T> {
	protected JTextField[] textFields;
    protected Integer selectedEntityId = null;
	protected final ViewNavigator navigator;
    private final Map<Class<?>, Controller<?>> controllers = new HashMap<>();

    protected javax.swing.JList<String> entitiesList;
    protected javax.swing.JButton buttonDelete;
    protected javax.swing.JButton buttonEdit;
    protected javax.swing.JButton buttonMainScreen;
    protected javax.swing.JButton buttonNew;
    protected javax.swing.JButton buttonSave;
    protected javax.swing.JButton buttonSearch;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JLabel labelHeader;
    protected javax.swing.JPanel panelButtons;
    protected javax.swing.JPanel panelInput;
    protected javax.swing.JTextArea actionResultArea;

    public BaseView(JTextField[] textFields, ViewNavigator navigator, Controller<Author> authorController, Controller<Book> bookController) {
		this.textFields = textFields;
		this.navigator = navigator;
        this.controllers.put(Author.class, authorController);
        this.controllers.put(Book.class, bookController);
	}

    public void buttonMainScreenActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        this.navigator.showMainView();
    }

    public void buttonNewActionPerformed(java.awt.event.ActionEvent evt) {
        this.clearTextFields();
        this.entitiesList.clearSelection();
        this.selectedEntityId = null;
    }

    public void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {
        this.displayEntities(true, true);
    }

    public void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.selectedEntityId != null) {
            T entity = this.getController(this.getEntityClass()).show(this.selectedEntityId);
            boolean deleted = this.getController(this.getEntityClass()).delete(this.selectedEntityId);
            this.displayActionResultText("delete", deleted, entity);
            this.displayEntities(false, true);
            this.selectedEntityId = null;
            this.entitiesList.clearSelection();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Selecione um item da lista para remover.", 
                "Remoção", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        if(this.fieldsValid()) {
            T entity = buildEntityFromInputs();
            boolean persisted = false;
            String action = null;

            if (this.selectedEntityId != null) {
                persisted = this.getController(this.getEntityClass()).update(this.selectedEntityId, entity);
                action = "update";
                this.selectedEntityId = null;
                this.entitiesList.clearSelection();
            } else {
                persisted = this.getController(this.getEntityClass()).create(entity);
                action = "save";
            }

            this.clearTextFields();
            this.displayActionResultText(action, persisted, entity);
            this.displayEntities(false, true);
        }
    }

    private void entitiesListValueChanged(javax.swing.event.ListSelectionEvent evt) {
        String value = this.entitiesList.getSelectedValue();
        if (value != null && !value.trim().isEmpty()) {
            this.selectedEntityId = Integer.parseInt(value.split(":")[0]);
            this.buttonEdit.setEnabled(true);
            this.buttonDelete.setEnabled(true);
        } else {
            this.buttonEdit.setEnabled(false);
            this.buttonDelete.setEnabled(false);
            this.selectedEntityId = null;
        }
    }

    protected void displayEntities(boolean updateActionResultArea, boolean updateEntitiesList) {
        List<T> entities = this.searchEntities();
        if (updateActionResultArea) {
            this.setEntitiesResult(entities);
        }
        if (updateEntitiesList) {
            this.updateEntitiesList(entities);
        }
    }

    protected List<T> searchEntities() {
        HashMap<String, String> params = this.buildParams();
        List<T> entities = this.getController(this.getEntityClass()).index(params);

        if (entities != null && !entities.isEmpty()) {
            return entities;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    protected <E> Controller<E> getController(Class<E> clazz) {
        Controller<E> controller = (Controller<E>) controllers.get(clazz);
        if (controller == null) {
            throw new IllegalArgumentException("Controller não encontrado para classe " + clazz.getName());
        }
        return controller;
    }

	protected void clearTextFields() {
        for(JTextField field : textFields) {
            field.setText("");
        }
    }

	protected boolean fieldsValid() {
        if (this.anyFieldEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Validação", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

	private boolean anyFieldEmpty() {
        for(JTextField f : textFields) {
            if (f == null || f.getText() == null || f.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    protected void initComponents() {
        labelHeader = new javax.swing.JLabel();
        panelButtons = new javax.swing.JPanel();
        buttonMainScreen = new javax.swing.JButton();
        buttonNew = new javax.swing.JButton();
        buttonSearch = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        panelInput = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        actionResultArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        entitiesList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelHeader.setFont(new java.awt.Font("Fira Code", 1, 24));
        labelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonMainScreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exit.png")));
        buttonMainScreen.setText("Voltar");
        buttonMainScreen.setMaximumSize(new java.awt.Dimension(103, 42));
        buttonMainScreen.setMinimumSize(new java.awt.Dimension(103, 42));
        buttonMainScreen.setPreferredSize(new java.awt.Dimension(103, 42));
        buttonMainScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMainScreenActionPerformed(evt);
            }
        });
        panelButtons.add(buttonMainScreen);

        buttonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novo_32x32.png")));
        buttonNew.setText("Novo");
        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewActionPerformed(evt);
            }
        });
        panelButtons.add(buttonNew);

        buttonSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png")));
        buttonSearch.setText("Buscar");
        buttonSearch.setMaximumSize(new java.awt.Dimension(108, 42));
        buttonSearch.setMinimumSize(new java.awt.Dimension(108, 42));
        buttonSearch.setPreferredSize(new java.awt.Dimension(108, 42));
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });
        panelButtons.add(buttonSearch);

        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit3_32x32.png")));
        buttonEdit.setText("Editar");
        buttonEdit.setEnabled(false);
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });
        panelButtons.add(buttonEdit);

        buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/del_32x32.png")));
        buttonDelete.setText("Excluir");
        buttonDelete.setEnabled(false);
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });
        panelButtons.add(buttonDelete);

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save_32x32.png")));
        buttonSave.setText("Salvar");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        panelButtons.add(buttonSave);

        panelInput.setBackground(new java.awt.Color(204, 204, 204));

        actionResultArea.setBackground(new java.awt.Color(255, 255, 255));
        actionResultArea.setColumns(20);
        actionResultArea.setRows(5);
        jScrollPane1.setViewportView(actionResultArea);

        entitiesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                entitiesListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(entitiesList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelHeader, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panelInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }
}
