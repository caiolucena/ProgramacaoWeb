 package br.uepb.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
/**
 * Essa classe é responsável por estabelcer uma conexão com o banco de dados
 * @author EquipeACL
 */
public class Conexao {
    public static Connection con;
    private static String user = "root";
    private static String pass = "root";
    
    private static final Logger logger = LogManager.getLogger(Conexao.class);
    
    /**
     * Método utilizado para criar uma conexao com o banco de dados
     * @return con, objeto do tipo Connection
     * @throws Exception
     */
    public static Connection iniciarConexao() throws Exception{
    	try {
            Class.forName("com.mysql.jdbc.Driver");
            con =  (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/biblioteca",user,pass);
        } catch (ClassNotFoundException ex1){
        	ex1 = new ClassNotFoundException();
        	logger.error("Conexao falhou!",ex1);
        	throw ex1;
        } catch(SQLException ex2) {
        	ex2 = new SQLException();
        	logger.error("Conexao falhou!",ex2);
        	throw ex2;
        }
        return con;
    }
    
}