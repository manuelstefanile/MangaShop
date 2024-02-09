package Dao;

import java.sql.Connection;


import java.sql.PreparedStatement;

import java.sql.SQLException;

import Model.ConnectionPool;

import Beans.UtenteBean;;

public class UtenteDao extends GeneralDao {

	
    private static final String TABLE_NAME = "Utente";

    
    private static Connection connection;

   
    public UtenteDao() {
    }
    

    public<T> void deleteByEmail(String email) throws SQLException {

    	  PreparedStatement preparedStatement = null;

          String deleteSQL =  "DELETE * FROM " + TABLE_NAME + "where email = ?";

          try {
              connection = ConnectionPool.getConnection();
              preparedStatement = connection.prepareStatement(deleteSQL);
              preparedStatement.setString(1, email);

              preparedStatement.executeUpdate();
              connection.commit();
          } finally {
              try {
                  if (preparedStatement != null) {
                      preparedStatement.close();
                  }
              } finally {
                  if (connection != null) {
                      connection.close();
                  }
              }
          }
      }
    
   

	

}
