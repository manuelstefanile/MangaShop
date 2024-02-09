package Dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Beans.AmministratoreBean;
import Beans.General;
import Beans.UtenteBean;
import Model.ConnectionPool;

public class GeneralDao implements GeneralDaoInterface {
	
    private static Connection connection;

	/*prendo il .class dell'oggetto
	 * prendo con fields le variabili della classe
	 * creo la stringa sql grande tanto quanto sono il numero di var
	 * se== al tipo String allora il prepareStatement è setString
	 * poi prendiamo il metodo getQualcosa e lo invochiamo*/
    
	@Override
	public <T extends General> Integer insert(T oggetto) throws SQLException {
		Integer idgenerato=null;
		Class<?> classe=oggetto.getClass();
		java.lang.reflect.Field[] fields = classe.getDeclaredFields();
		
        PreparedStatement preparedStatement = null;
        int i=1;
        if(oggetto instanceof UtenteBean || oggetto instanceof AmministratoreBean) {
        	i=0;
        }
        String insertSQL = "Insert Into " + oggetto.getNomeTabella() +" (" ;
        for(;i<fields.length;i++) {
        	insertSQL+=fields[i].getName();
        	if(fields.length-i>1) {
        		insertSQL+=",";
        	}
        	else {
        		insertSQL+=")";
        	}
        }
        int l=1;
        if(oggetto instanceof UtenteBean || oggetto instanceof AmministratoreBean) {
        	l=0;
        }
        insertSQL+=" Values (";
        for(;l<fields.length;l++) {
        	insertSQL+="?";
        	if(fields.length-l>1) {
        		insertSQL+=",";
        	}
        	else {
        		insertSQL+=")";
        	}
        }
        
        System.out.println(insertSQL);
        
        try {

            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            
            int p=1;
            if(oggetto instanceof UtenteBean || oggetto instanceof AmministratoreBean) {
            	p=0;
            }
            for(int f=0;p<fields.length;p++,f++) {
            	
            	String fieldsMaiuscolo=fields[p].getName().substring(0,1).toUpperCase() + fields[p].getName().substring(1);
        		java.lang.reflect.Method metodoGet = classe.getMethod("get"+fieldsMaiuscolo);
        		
        		
            	if(fields[p].getType() ==  String.class) {
            		if (metodoGet.invoke(oggetto)!=null) {
            			preparedStatement.setString(f+1, (String) metodoGet.invoke(oggetto));
            		}else {
            			preparedStatement.setNull(f+1,java.sql.Types.VARCHAR);
            		}
           		 	
           		 	
            	}else if(fields[p].getType() ==  int.class) {
          		 	preparedStatement.setInt(f+1, (int) metodoGet.invoke(oggetto));
               
            	}else if(fields[p].getType() ==  Integer.class) {
            		/*usato per immagini_manga*/
					if (metodoGet.invoke(oggetto)!=null)
						 preparedStatement.setInt(f+1, (int) metodoGet.invoke(oggetto));
					else
						 preparedStatement.setNull(f+1,java.sql.Types.INTEGER);
          		 	
            	}    
            	else if(fields[p].getType() ==  byte[].class) {
            		
          		 	preparedStatement.setBytes(f+1, (byte[]) metodoGet.invoke(oggetto));
               
            	}else if(fields[p].getType() ==  float.class) {
          		 	preparedStatement.setFloat(f+1, (float) metodoGet.invoke(oggetto));
               
            	}else if(fields[p].getType() ==  boolean.class) {
          		 	preparedStatement.setBoolean(f+1, (boolean) metodoGet.invoke(oggetto));
               
            	}else if(fields[p].getType() ==  Date.class) {
          		 	preparedStatement.setDate(f+1, (Date) metodoGet.invoke(oggetto));
               
            	}
            	
           
            	
            }
           
       
            	
            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                idgenerato = generatedKeys.getInt(1);
                
            }
            
            connection.commit();

        } catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
       return idgenerato; 
    	
    }




	@Override
	public <T extends General> Object update(T oggetto) throws SQLException {
		int i;
		Class<?> classe=oggetto.getClass();
		java.lang.reflect.Field[] fields = classe.getDeclaredFields();
        PreparedStatement preparedStatement = null;
        String updateSQL = "UPDATE " + oggetto.getNomeTabella() + " SET ";
        for( i=0; i<fields.length;i++) {
        	updateSQL+=fields[i].getName()+"= ?";
        	if(fields.length-i>1) {
        		updateSQL+=",";
        	}
        	else {
        		updateSQL+="";
        	}
        	
        }
        updateSQL+=" where " + fields[0].getName() +"= ?";
        
        
        String fieldId=fields[0].getName().substring(0,1).toUpperCase() + fields[0].getName().substring(1);
 		java.lang.reflect.Method metodoEmail=null;
		try {
			metodoEmail = classe.getMethod("get"+fieldId);
		} catch (NoSuchMethodException | SecurityException e1) {

			e1.printStackTrace();
		}
        
        
        
        try {
        	 connection = ConnectionPool.getConnection();
             preparedStatement = connection.prepareStatement(updateSQL);
             
             for( i=0; i<fields.length;i++) {
          		String fieldsMaiuscolo=fields[i].getName().substring(0,1).toUpperCase() + fields[i].getName().substring(1);
          		java.lang.reflect.Method metodoGet = classe.getMethod("get"+fieldsMaiuscolo);

             	if(fields[i].getType() ==  String.class) {

            		 preparedStatement.setString(i+1, (String) metodoGet.invoke(oggetto));
            
             	}else if(fields[i].getType() ==  int.class) {
          		 	preparedStatement.setInt(i+1, (int) metodoGet.invoke(oggetto));
               
            	}else if(fields[i].getType() ==  Integer.class) {
          		 	preparedStatement.setInt(i+1, (int) metodoGet.invoke(oggetto));
          		 	
          		 	if (metodoGet.invoke(oggetto)!=null)
						 preparedStatement.setInt(i+1, (int) metodoGet.invoke(oggetto));
					else
						 preparedStatement.setNull(i+1,java.sql.Types.INTEGER);
                    
            	}else if(fields[i].getType() ==   byte[].class) {
      		 	preparedStatement.setBytes(i+1, (byte[]) metodoGet.invoke(oggetto));
           
        	}else if(fields[i].getType() ==  float.class) {
      		 	preparedStatement.setFloat(i+1, (float) metodoGet.invoke(oggetto));
           
        	}else if(fields[i].getType() ==  boolean.class) {
      		 	preparedStatement.setBoolean(i+1, (boolean) metodoGet.invoke(oggetto));
           
        	}else if(fields[i].getType() ==  Date.class) {
      		 	preparedStatement.setDate(i+1, (Date) metodoGet.invoke(oggetto));
           
        	}
       
             	
             }
             //endFor
 
             /**/
             if(fields[0].getType() ==  String.class) {
                 preparedStatement.setString(i+1, (String) metodoEmail.invoke(oggetto));
             }
             else {
            	 preparedStatement.setInt(i+1, (int) metodoEmail.invoke(oggetto));
             }
             

             
             preparedStatement.executeUpdate();
             connection.commit();
        } catch (NoSuchMethodException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (SecurityException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IllegalAccessException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IllegalArgumentException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (InvocationTargetException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
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
        
  		try {
  			String fieldsMaiuscolo=fields[0].getName().substring(0,1).toUpperCase() + fields[0].getName().substring(1);
  	  		java.lang.reflect.Method metodoGet = classe.getMethod("get"+fieldsMaiuscolo);
			return metodoGet.invoke(oggetto);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return null;
     	
     }



	@Override
	public <T extends General> void delete(Object id, T oggetto) throws SQLException {
		Class<?> classe=oggetto.getClass();
		java.lang.reflect.Field[] fields = classe.getDeclaredFields();
        PreparedStatement preparedStatement = null;
  
        String fieldId=fields[0].getName().substring(0,1).toUpperCase() + fields[0].getName().substring(1);
 		java.lang.reflect.Method metodoid=null;
		try {
			metodoid = classe.getMethod("get"+fieldId);
		} catch (NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
		
		
	      String deleteSQL =  "DELETE FROM " + oggetto.getNomeTabella() + " where ";
	      if(id instanceof String) {
	    	  deleteSQL+="email = ?";
	      }else {
	    	  deleteSQL+="id = ?";
	      }

	      try {
              connection = ConnectionPool.getConnection();
              preparedStatement = connection.prepareStatement(deleteSQL);
              
              if(fields[0].getType() == String.class) {
    		 	preparedStatement.setString(1, (String) id);
              } else {
            	  preparedStatement.setInt(1, (int) id);
              }
              
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



/* oggetto=istanza della classe che devo cercare*/
	@Override
	public <T extends General> List<T> retriveAll(T oggetto) throws SQLException {
		
		Class<?> classe=oggetto.getClass();
		java.lang.reflect.Field[] fields = classe.getDeclaredFields();
		String retriveAllSQL = "SELECT * FROM "+ oggetto.getNomeTabella();
		 
		
		ArrayList<T> list = new ArrayList<>();
      	
		   try {
	            connection = ConnectionPool.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(retriveAllSQL);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	            	
	                T newItem = (T) classe.newInstance();

	                for(int i=0; i<fields.length;i++) {
		                
	                	String fieldsMaiuscolo=fields[i].getName().substring(0,1).toUpperCase() + fields[i].getName().substring(1);
	                	//per il parametro del setter
	                	Class<?> fieldType = fields[i].getType();
	                   
	                	java.lang.reflect.Method metodoGet = classe.getMethod("get"+fieldsMaiuscolo);
	                	java.lang.reflect.Method metodoSet = classe.getMethod("set"+fieldsMaiuscolo,fieldType);
	                	
	                	if(fields[i].getType() ==  String.class) {
	                		
	                		metodoSet.invoke(newItem, rs.getString(fields[i].getName()));
	                	}else if(fields[i].getType() ==  int.class) {
	                		metodoSet.invoke(newItem, rs.getInt(fields[i].getName()));
	                   
	                	}else if(fields[i].getType() ==  Integer.class) {
	                		metodoSet.invoke(newItem, rs.getInt(fields[i].getName()));
	                   
	                	}else if(fields[i].getType() ==   byte[].class) {
	                		metodoSet.invoke(newItem, rs.getBytes(fields[i].getName()));
	                   
	                	}else if(fields[i].getType() ==  float.class) {
	                		metodoSet.invoke(newItem, rs.getFloat(fields[i].getName()));
	                   
	                	}else if(fields[i].getType() ==  boolean.class) {
	                		metodoSet.invoke(newItem, rs.getBoolean(fields[i].getName()));
	                   
	                	}else if(fields[i].getType() ==  Date.class) {
	                		metodoSet.invoke(newItem, rs.getDate(fields[i].getName()));
	                   
	                	}

	            }
	               
	                connection.commit();
	              
	                list.add(newItem);
		
	            }
		   }catch (Exception e) {
			// TODO: handle exception
		}
		   
		   return list;
	}
	
	
public <T extends General> T retriveById(T oggetto,Object id) throws SQLException {
		
		Class<?> classe=oggetto.getClass();
		java.lang.reflect.Field[] fields = classe.getDeclaredFields();
		String retriveByIdSQL = "SELECT * FROM "+ oggetto.getNomeTabella() + " WHERE ";
		retriveByIdSQL+=fields[0].getName() + " =" + id;
		
		T oggettoReturn=null;
		
		   try {
	            connection = ConnectionPool.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(retriveByIdSQL);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	            	
	                oggettoReturn= (T) classe.newInstance();

	                for(int i=0; i<fields.length;i++) {
		                
	                	String fieldsMaiuscolo=fields[i].getName().substring(0,1).toUpperCase() + fields[i].getName().substring(1);
	                	//per il parametro del setter
	                	Class<?> fieldType = fields[i].getType();
	                   
	                	java.lang.reflect.Method metodoGet = classe.getMethod("get"+fieldsMaiuscolo);
	                	java.lang.reflect.Method metodoSet = classe.getMethod("set"+fieldsMaiuscolo,fieldType);
	                	
	                	if(fields[i].getType() ==  String.class) {
	                		
	                		metodoSet.invoke(oggettoReturn, rs.getString(fields[i].getName()));
	                	}else if(fields[i].getType() ==  int.class) {
	                		metodoSet.invoke(oggettoReturn, rs.getInt(fields[i].getName()));
	                   
	                	}else if(fields[i].getType() ==  Integer.class) {
	                		metodoSet.invoke(oggettoReturn, rs.getInt(fields[i].getName()));
	                   
	                	}
	                	else if(fields[i].getType() ==   byte[].class) {
	                		metodoSet.invoke(oggettoReturn, rs.getBytes(fields[i].getName()));
	                   
	                	}else if(fields[i].getType() ==  float.class) {
	                		metodoSet.invoke(oggettoReturn, rs.getFloat(fields[i].getName()));
	                   
	                	}else if(fields[i].getType() ==  boolean.class) {
	                		metodoSet.invoke(oggettoReturn, rs.getBoolean(fields[i].getName()));
	                   
	                	}else if(fields[i].getType() ==  Date.class) {
	                		metodoSet.invoke(oggettoReturn, rs.getDate(fields[i].getName()));
	                   
	                	}
	                	
	            }
	               
	                connection.commit();
	                
	              
	                
		
	            }
		   }catch (Exception e) {
			// TODO: handle exception
		}
		   
		   
		return oggettoReturn;
	}

//ritorna una lista in base al campo e al valore del campo da ricervare nella tabella
public <T extends General> List<T> retriveByCampo(T oggetto,Object campo,Object id) {
	
	Class<?> classe=oggetto.getClass();
	java.lang.reflect.Field[] fields = classe.getDeclaredFields();
	String retriveByCampo = "SELECT * FROM "+ oggetto.getNomeTabella() + " WHERE ";
	for(java.lang.reflect.Field field : fields) {
	    if (field.getName().equals(campo)) {
	    	retriveByCampo+=field.getName() + "=\"" + id +"\""; 
	    	}
	    }
	
	ArrayList<T> oggettoReturn = new ArrayList<>();
	
	
	   try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(retriveByCampo);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	
                T item =(T) classe.newInstance(); 

                for(int i=0; i<fields.length;i++) {
	                
                	String fieldsMaiuscolo=fields[i].getName().substring(0,1).toUpperCase() + fields[i].getName().substring(1);
                	//per il parametro del setter
                	Class<?> fieldType = fields[i].getType();
                   
                	java.lang.reflect.Method metodoGet = classe.getMethod("get"+fieldsMaiuscolo);
                	java.lang.reflect.Method metodoSet = classe.getMethod("set"+fieldsMaiuscolo,fieldType);
                	
                	if(fields[i].getType() ==  String.class) {
                		
                		metodoSet.invoke(item, rs.getString(fields[i].getName()));
                	}else if(fields[i].getType() ==  int.class) {
                		metodoSet.invoke(item, rs.getInt(fields[i].getName()));
                   
                	}else if(fields[i].getType() ==  Integer.class) {
                		metodoSet.invoke(item, rs.getInt(fields[i].getName()));
                   
                	}else if(fields[i].getType() == byte[].class) {
                		metodoSet.invoke(item, rs.getBytes(fields[i].getName()));
                   
                	}else if(fields[i].getType() ==  float.class) {
                		metodoSet.invoke(item, rs.getFloat(fields[i].getName()));
                   
                	}else if(fields[i].getType() ==  boolean.class) {
                		metodoSet.invoke(item, rs.getBoolean(fields[i].getName()));
                   
                	}else if(fields[i].getType() ==  Date.class) {
                		metodoSet.invoke(item, rs.getDate(fields[i].getName()));
                   
                	}

            }
               
                connection.commit();
              oggettoReturn.add(item);
                
	
            }
	   }catch (Exception e) {
		// TODO: handle exception
	}
	   
	   
	return oggettoReturn;
}
}


