package projetomvc.views;

import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import projetomvc.controllers.interfaces.Controller;
import projetomvc.models.entities.Author;
import projetomvc.models.entities.Book;
import projetomvc.views.interfaces.ViewNavigator;

/**
 *
 * @author samuel
 */
public class AuthorsView extends BaseView<Author> {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AuthorsView.class.getName());

    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelHometown;
    private javax.swing.JLabel labelBirthDate;
    private javax.swing.JTextField fieldName;
    private javax.swing.JTextField fieldHometown;
    private javax.swing.JTextField fieldBirthDate;

    public AuthorsView(ViewNavigator navigator, Controller<Author> authorController, Controller<Book> bookController) {
        super(new JTextField[] {}, navigator, authorController, bookController);
        initComponents();
        super.textFields = new JTextField[] { this.fieldName, this.fieldBirthDate, this.fieldHometown };
        super.displayEntities(true, true);
    }

    @Override
    public void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {
        if (super.selectedEntityId != null) {
            Author author = super.getController(Author.class).show(super.selectedEntityId);

            this.fieldName.setText(author.getName());
            this.fieldHometown.setText(author.getHometown());
            this.fieldBirthDate.setText(author.getBirthDate().toString());
        }
        else {
            JOptionPane.showMessageDialog(this,
                "Selecione um autor da lista para editar.",
                "Edição",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public Author buildEntityFromInputs() {
        Author author = new Author();

        author.setName(this.fieldName.getText());
        author.setHometown(this.fieldHometown.getText());
        author.setBirthDate(Integer.parseInt(this.fieldBirthDate.getText()));

        return author;
    }

    @Override
    public void displayActionResultText(String action, boolean success, Author author) {
        String result = "";
        switch (action) {
            case "save":
                result = success ? "Autor salvo: " + author.getName() : "Erro ao salvar autor.";
                break;
            case "update":
                result = success ? "Autor editado: " + author.getName() : "Erro ao editar autor.";
                break;
            case "delete":
                result = success ? "Autor removido: " + author.getName() : "Erro ao remover autor.";
                break;
        }
        this.actionResultArea.setText(result);
    }

    @Override
    public HashMap<String, String> buildParams() {
        HashMap<String, String> params = new HashMap<>();
        
        params.put("name", this.fieldName.getText());
        params.put("hometown", this.fieldHometown.getText());
        params.put("birthDate", this.fieldBirthDate.getText());

        return params;
    }

    @Override
    public void setEntitiesResult(List<Author> entities) {
        if (entities != null && !entities.isEmpty()) {
            this.actionResultArea.setText("Autores encontrados:\n");
            for (Author entity : entities) {
                this.actionResultArea.append("Nome: " + entity.getName() + ", Cidade Natal: " + entity.getHometown() + ", Data de nascimento: " + entity.getBirthDate() + "\n");
            }
        } else {
            this.actionResultArea.setText("Nenhum autor encontrado.");

        }
    }

    public void updateEntitiesList(List<Author> entities) {
        if (entities != null && !entities.isEmpty()) {
            this.entitiesList.setListData(entities.stream().map(entity -> entity.getId() + ": " + entity.getName()).toArray(String[]::new));
        } else {
            this.entitiesList.setListData(new String[0]);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(visible) {
            super.clearTextFields();
            super.displayEntities(true, true);
        }
    }

    @Override
    public Class<Author> getEntityClass() {
        return Author.class;
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        labelName = new javax.swing.JLabel();
        labelHometown = new javax.swing.JLabel();
        labelBirthDate = new javax.swing.JLabel();
        fieldName = new javax.swing.JTextField();
        fieldHometown = new javax.swing.JTextField();
        fieldBirthDate = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelHeader.setText("Gerenciador de Autores");

        labelName.setBackground(new java.awt.Color(0, 0, 0));
        labelName.setForeground(new java.awt.Color(0, 0, 0));
        labelName.setText("Nome:");

        fieldName.setBackground(new java.awt.Color(255, 255, 255));
        fieldName.setForeground(new java.awt.Color(0, 0, 0));

        labelHometown.setForeground(new java.awt.Color(0, 0, 0));
        labelHometown.setText("Cidade natal:");
        
        fieldHometown.setBackground(new java.awt.Color(255, 255, 255));
        fieldHometown.setForeground(new java.awt.Color(0, 0, 0));
        
        labelBirthDate.setForeground(new java.awt.Color(0, 0, 0));
        labelBirthDate.setText("Data de nascimento:");

        fieldBirthDate.setBackground(new java.awt.Color(255, 255, 255));
        fieldBirthDate.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelInputLayout = new javax.swing.GroupLayout(super.panelInput);
        super.panelInput.setLayout(panelInputLayout);
        panelInputLayout.setHorizontalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(labelName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(labelBirthDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHometown)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldHometown)
                .addContainerGap())
        );
        panelInputLayout.setVerticalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelHometown)
                        .addComponent(fieldHometown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelName)
                        .addComponent(fieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBirthDate)
                    .addComponent(fieldBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
}
