/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exemplo1.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConectaMysql {
    public static void main(String[] args) throws SQLException{
        Connection conexao = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3307/agenda",
            "root", "123");
            
        } catch (ClassNotFoundException e){
            System.out.println("Class não encontrada. Erro: " + e.getMessage());
        } catch (SQLException e){
            System.out.println("Ocorreu um erro no SQL. Erro " + e.getMessage());
        } finally{
            try{
                if(conexao != null){
                    conexao.close();
                }
                
            } catch (SQLException e) {
            System.out.println("Erro ao fechar coneção. Erro: " +e.getMessage());
        }
        }
    }
    
}
