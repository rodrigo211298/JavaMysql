/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;




public class JavaApplication2 {

    
    public static void main(String[] args) {
   
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String _url = "jdbc:mysql://localhost/tienda?user=root&password=mysqladmin";
            Connection connect = DriverManager.getConnection(_url);
            Statement statement = connect.createStatement();
            String _query = "SELECT * FROM producto";
            ResultSet resultSet = statement.executeQuery(_query);
            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                String descProducto = resultSet.getString("desc_producto");
                int precio = resultSet.getInt("precio");
                System.out.println("Id: " + idProducto);
                System.out.println("Desc: " + descProducto);
                System.out.println("Precio: " + precio);
                System.out.println("=============================");
            }
        } catch (Exception e) {
            //
        }

      
    }
    
}
