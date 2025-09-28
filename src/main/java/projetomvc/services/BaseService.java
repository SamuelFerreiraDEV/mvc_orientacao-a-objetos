package projetomvc.services;

import projetomvc.models.dao.interfaces.IDAO;

public abstract class BaseService<T> {
	protected final IDAO<T> dao;

	public BaseService(IDAO<T> dao) {
		this.dao = dao;
	}
}
