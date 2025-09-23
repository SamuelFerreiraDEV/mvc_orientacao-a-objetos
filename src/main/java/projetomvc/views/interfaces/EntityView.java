package projetomvc.views.interfaces;

import java.util.HashMap;
import java.util.List;

public interface EntityView<T> {
	void buttonMainScreenActionPerformed(java.awt.event.ActionEvent evt);
	void buttonNewActionPerformed(java.awt.event.ActionEvent evt);
	void buttonSearchActionPerformed(java.awt.event.ActionEvent evt);
	void buttonEditActionPerformed(java.awt.event.ActionEvent evt);
	void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt);
	void buttonSaveActionPerformed(java.awt.event.ActionEvent evt);
	HashMap<String, String> buildParams();
	void setEntitiesResult(List<T> entities);
	void updateEntitiesList(List<T> entities);
	Class<T> getEntityClass();
	T buildEntityFromInputs();
	void displayActionResultText(String action, boolean success, T entity);
	void setVisible(boolean visible);
}
