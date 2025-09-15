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
