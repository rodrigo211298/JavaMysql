/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;




public class JavaApplication2 {

    
    public static void main(String[] args) {
   
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String _url = "jdbc:mysql://localhost/tienda?user=root&password=mysqladmin";
            Connection connect = DriverManager.getConnection(_url);
            Statement statement = connect.createStatement();
            String _query = "SELECT * FROM producto";
            ResultSet resultSet = statement.executeQuery(_query);
            String format="|%1$-3d|%2$-17s|%3$-5d\n";
            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                String descProducto = resultSet.getString("desc_producto");
                int precio = resultSet.getInt("precio");
                System.out.println("Id: " + idProducto);
                System.out.println("Desc: " + descProducto);
                System.out.println("Precio: " + precio);
                System.out.println("=============================");
            }
            Scanner scan = new Scanner(System.in);
            System.out.println("¿Que deseas hacer: INSERTAR/ BORRAR/ CONSULTAR / ACTUALIZAR");
            String accion = scan.nextLine();
            if(accion.equals("INSERTAR")){
                scan = new Scanner(System.in);
                System.out.println("Ingresar el id_producto");
                String idprod = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa el edsc_producto");
                String descprod = scan.nextLine();
                
                scan= new Scanner(System.in);
                System.out.println("Ingresa el precio");
                String precio=scan.nextLine();
                _query = "INSERT INTO producto VALUES(?,?,?)";
                PreparedStatement ps = connect.prepareStatement(_query);
                ps.setInt(1,Integer.parseInt(idprod));
                ps.setString(2,descprod);
                ps.setInt(3,Integer.parseInt(precio));
                ps.executeUpdate();
                                
                
                
            }
            else if (accion.equals("BORRAR")){
                        scan=new Scanner(System.in);
                        System.out.println("Ingresa el id_producto");
                        String idprod=scan.nextLine();
                _query = "DELETE FROM producto WHERE id_producto = ?";
                PreparedStatement ps = connect.prepareStatement(_query);
                ps.setInt(1, Integer.parseInt(idprod));
                ps.executeUpdate();
           }
            else if (accion.equals("ACTUALIZAR")) {
                scan = new Scanner(System.in);
                System.out.println("Ingresa el id_producto");
                String idprod = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa el desc_producto");
                String descprod = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa el precio");
                String precio = scan.nextLine();
                
                _query = "UPDATE producto SET desc_producto = ?, precio = ? WHERE id_producto = ?";
                PreparedStatement ps = connect.prepareStatement(_query);
                ps.setString(1, descprod);
                ps.setInt(2, Integer.parseInt(precio));
                ps.setInt(3, Integer.parseInt(idprod));
                ps.executeUpdate();
            }
            resultSet.close();
            statement.close();
            connect.close();

           
       
        } catch (Exception e) {
            System.err.println(e);
        }

      
    }
    
}
