package projetomvc.views.interfaces;

public interface IViewNavigator<T, K> {
	void showMainView();
	void showBooksView();
	void showAuthorsView();
	void setAuthorsView(IEntityView<T> authorsView);
	void setBooksView(IEntityView<K> booksView);
	void setVisible(boolean visible);
}
