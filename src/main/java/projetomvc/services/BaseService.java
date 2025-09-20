package projetomvc.services;

import projetomvc.models.dao.interfaces.DAO;

public abstract class BaseService<T> {
	protected final DAO<T> dao;

	public BaseService(DAO<T> dao) {
		this.dao = dao;
	}
}
