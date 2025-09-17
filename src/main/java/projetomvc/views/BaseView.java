package projetomvc.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import projetomvc.controllers.Controller;
import projetomvc.models.entities.Author;
import projetomvc.models.entities.Book;
import projetomvc.views.interfaces.ViewNavigator;

public abstract class BaseView<T> extends javax.swing.JFrame {
	protected JTextField[] textFields;
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

    protected abstract HashMap<String, String> buildParams();
    protected abstract void setEntitiesResult(List<T> entities);
    protected abstract void updateEntitiesList(List<T> entities);
    protected abstract Class<T> getEntityClass();
    protected abstract T buildEntityFromInputs();
    protected abstract void displayActionResultText(String action, boolean success, T entity);

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
            JOptionPane.showMessageDialog(this, "Não encontrado.", "Busca", JOptionPane.INFORMATION_MESSAGE);
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
}
