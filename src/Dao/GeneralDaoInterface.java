package Dao;

import java.sql.SQLException;
import java.util.List;

import Beans.General;

public interface GeneralDaoInterface {

	public<T extends General> Integer insert(T oggetto)  throws SQLException;
	public<T extends General>  void delete(Object id, T classe)  throws SQLException;
	public<T extends General> Object update(T oggetto) throws SQLException;
	public<T extends General> List<T> retriveAll(T oggetto) throws SQLException;
	public<T extends General> T retriveById(T oggetto,Object id) throws SQLException;
	public<T extends General> List<T> retriveByCampo(T oggetto,Object campo,Object id);
}