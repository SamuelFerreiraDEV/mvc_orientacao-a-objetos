package projetomvc.views;

import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import projetomvc.controllers.interfaces.IController;
import projetomvc.models.entities.Author;
import projetomvc.models.entities.Book;
import projetomvc.views.interfaces.IViewNavigator;

/**
 *
 * @author samuel
 */
public class BooksView extends BaseView<Author, Book, Book> {

    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelAuthor;
    private javax.swing.JLabel labelPublishedYear;
    private javax.swing.JTextField fieldTitle;
    private javax.swing.JTextField fieldPublishedYear;
    private javax.swing.JComboBox<String> comboBoxAuthors;

    public BooksView(IViewNavigator<Author, Book> navigator, IController<Author> authorController, IController<Book> bookController) {
        super(new JTextField[] {}, navigator, authorController, bookController);
        initComponents();
        super.textFields = new JTextField[] { this.fieldTitle, this.fieldPublishedYear };
        this.loadAuthors();
        super.displayEntities(true, true);
    }

    public void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {
        if (super.selectedEntityId != null) {
            Book book = super.getController(Book.class).show(super.selectedEntityId);

            this.fieldTitle.setText(book.getTitle());
            this.comboBoxAuthors.setSelectedIndex(0);
            this.fieldPublishedYear.setText(book.getPublishedYear().toString());
        }
        else {
            JOptionPane.showMessageDialog(this, 
                "Selecione um livro da lista para editar.", 
                "Edição", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public HashMap<String, String> buildParams() {
        HashMap<String, String> params = new HashMap<>();
        int authorId = this.getAuthorIdByName(this.comboBoxAuthors.getSelectedItem().toString());
        
        params.put("title", this.fieldTitle.getText());
        params.put("authorId", String.valueOf(authorId));
        params.put("publishedYear", String.valueOf(this.fieldPublishedYear.getText()));

        return params;
    }

    public void setEntitiesResult(List<Book> entities) {
        if (entities != null && !entities.isEmpty()) {
            this.actionResultArea.setText("Livros encontrados:\n");
            for (Book entity : entities) {
                this.actionResultArea.append("Título: " + entity.getTitle() + ", Autor: " + super.getController(Author.class).show(entity.getAuthorId()).getName() + ", Ano: " + entity.getPublishedYear() + "\n");
            }
        } else {
            this.actionResultArea.setText("Nenhum livro encontrado.");

        }
    }

    public void updateEntitiesList(List<Book> entities) {
        if (entities != null && !entities.isEmpty()) {
            this.entitiesList.setListData(entities.stream().map(entity -> entity.getId() + ": " + entity.getTitle()).toArray(String[]::new));
        } else {
            this.entitiesList.setListData(new String[0]);
        }
    }

    public Book buildEntityFromInputs() {
        Book book = new Book();
        int authorId = this.getAuthorIdByName(this.comboBoxAuthors.getSelectedItem().toString());

        book.setTitle(this.fieldTitle.getText());
        book.setAuthorId(authorId);
        book.setPublishedYear(Integer.parseInt(this.fieldPublishedYear.getText()));

        return book;
    }

    private void loadAuthors() {
        this.comboBoxAuthors.removeAllItems();
        this.comboBoxAuthors.addItem("Selecione um autor");
        List<Author> authors = super.getController(Author.class).index(new HashMap<>());

        for(Author author : authors) {
            this.comboBoxAuthors.addItem(author.getName());
        }
    }

    private int getAuthorIdByName(String name) {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);

        List<Author> author = super.getController(Author.class).index(params);
        if (author != null && !author.isEmpty()) {
            return author.get(0).getId();
        }
        return -1;
    }

    public void displayActionResultText(String action, boolean success, Book book) {
        String result = "";
        switch (action) {
            case "save":
                result = success ? "Livro salvo: " + book.getTitle() : "Erro ao salvar livro.";
                break;
            case "update":
                result = success ? "Livro editado: " + book.getTitle() : "Erro ao editar livro.";
                break;
            case "delete":
                result = success ? "Livro removido: " + book.getTitle() : "Erro ao remover livro.";
                break;
        }
        this.actionResultArea.setText(result);
        
    }

    @Override
    protected void clearTextFields() {
        super.clearTextFields();
        this.comboBoxAuthors.setSelectedIndex(0);
    }

    private boolean isYearValid() {
        String year = this.fieldPublishedYear.getText();
        try {
            int y = Integer.parseInt(year);
            return y > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    protected boolean fieldsValid() {
        if (!super.fieldsValid()) {
            return false;
        }
        
        if (!this.isYearValid()) {
            JOptionPane.showMessageDialog(this, "Ano inválido.", "Validação", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(visible) {
            super.clearTextFields();
            this.loadAuthors();
            super.displayEntities(true, true);
        }
    }

    public Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        labelTitle = new javax.swing.JLabel();
        labelAuthor = new javax.swing.JLabel();
        labelPublishedYear = new javax.swing.JLabel();
        fieldTitle = new javax.swing.JTextField();
        fieldPublishedYear = new javax.swing.JTextField();
        comboBoxAuthors = new javax.swing.JComboBox<>();

        labelHeader.setText("Gerenciador de Livros");

        labelTitle.setBackground(new java.awt.Color(0, 0, 0));
        labelTitle.setForeground(new java.awt.Color(0, 0, 0));
        labelTitle.setText("Título:");

        fieldTitle.setBackground(new java.awt.Color(255, 255, 255));
        fieldTitle.setForeground(new java.awt.Color(0, 0, 0));

        labelAuthor.setForeground(new java.awt.Color(0, 0, 0));
        labelAuthor.setText("Autor:");

        labelPublishedYear.setForeground(new java.awt.Color(0, 0, 0));
        labelPublishedYear.setText("Ano de publicação:");

        fieldPublishedYear.setBackground(new java.awt.Color(255, 255, 255));
        fieldPublishedYear.setForeground(new java.awt.Color(0, 0, 0));

        comboBoxAuthors.setModel(new javax.swing.DefaultComboBoxModel<>());

        javax.swing.GroupLayout panelInputLayout = new javax.swing.GroupLayout(super.panelInput);
        super.panelInput.setLayout(panelInputLayout);
        panelInputLayout.setHorizontalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(labelTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(labelAuthor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxAuthors, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPublishedYear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPublishedYear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
        );
        panelInputLayout.setVerticalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelPublishedYear)
                        .addComponent(fieldPublishedYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTitle)
                        .addComponent(fieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAuthor)
                    .addComponent(comboBoxAuthors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
}
