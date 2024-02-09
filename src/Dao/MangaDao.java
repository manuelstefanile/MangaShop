package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Model.ConnectionPool;
import Beans.AmministratoreBean;
import Beans.MangaBean;


public class MangaDao extends GeneralDao {

	
    private static final String TABLE_NAME = "Manga";

    
    private static Connection connection;

   
    public MangaDao() {
    	
    }
    /*ritorna i primi tot manga in base alla categoria */
    
    public List<MangaBean> RetriveMangaLimit(int limite ,int idCategoria) {
        String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE categoria= "+ idCategoria + " LIMIT " + limite ;
        ArrayList<MangaBean> list = new ArrayList<>();
        
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                MangaBean t = new MangaBean();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setAutore(rs.getInt("autore"));
                t.setCategoria(rs.getInt("categoria"));
                t.setData_rilascio(rs.getDate("data_rilascio"));
                t.setDescrizione(rs.getString("descrizione"));
                t.setDisponibilita(rs.getBoolean("disponibilita"));
                t.setEditore(rs.getInt("editore"));
                t.setImmagini_manga(rs.getInt("immagini_manga"));
                t.setPrezzo(rs.getFloat("prezzo"));
                t.setQuantita(rs.getInt("quantita"));
                t.setRilegatura(rs.getString("rilegatura"));
                
                
                connection.commit();
                list.add(t);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }
    
    public List<MangaBean> RetriveMangParola(String parola) {
        String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE nome LIKE '"+ parola+ "%' OR nome LIKE '%" + parola+ "%' ";
        ArrayList<MangaBean> list = new ArrayList<>();
        System.out.println(selectSQL);
        
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                MangaBean t = new MangaBean();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setAutore(rs.getInt("autore"));
                t.setCategoria(rs.getInt("categoria"));
                t.setData_rilascio(rs.getDate("data_rilascio"));
                t.setDescrizione(rs.getString("descrizione"));
                t.setDisponibilita(rs.getBoolean("disponibilita"));
                t.setEditore(rs.getInt("editore"));
                t.setImmagini_manga(rs.getInt("immagini_manga"));
                t.setPrezzo(rs.getFloat("prezzo"));
                t.setQuantita(rs.getInt("quantita"));
                t.setRilegatura(rs.getString("rilegatura"));
                
                
                connection.commit();
                list.add(t);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }
    

    public List<MangaBean> RetriveMangaNovitaSettimana() {
        String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE datediff(now(),data_rilascio)<=7";
        
        		
        ArrayList<MangaBean> list = new ArrayList<>();
        
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                MangaBean t = new MangaBean();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setAutore(rs.getInt("autore"));
                t.setCategoria(rs.getInt("categoria"));
                t.setData_rilascio(rs.getDate("data_rilascio"));
                t.setDescrizione(rs.getString("descrizione"));
                t.setDisponibilita(rs.getBoolean("disponibilita"));
                t.setEditore(rs.getInt("editore"));
                t.setImmagini_manga(rs.getInt("immagini_manga"));
                t.setPrezzo(rs.getFloat("prezzo"));
                t.setQuantita(rs.getInt("quantita"));
                t.setRilegatura(rs.getString("rilegatura"));
                
                
                connection.commit();
                list.add(t);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }
    
   
    
  
   

}
