package projetomvc.views.interfaces;

public interface ViewNavigator<T, K> {
	void showMainView();
	void showBooksView();
	void showAuthorsView();
	void setAuthorsView(EntityView<T> authorsView);
	void setBooksView(EntityView<K> booksView);
	void setVisible(boolean visible);
}
