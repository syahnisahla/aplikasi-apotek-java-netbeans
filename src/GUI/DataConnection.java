/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
class DataConnection {
    private static Connection koneksi;  
 public static Connection getkoneksi(){  
    if(koneksi==null){  
      try {  
        String url=new String();  
        String user=new String();  
        String password=new String();  
        url="jdbc:mysql://localhost:3306/aplikasi_penjualan";  
        user="root";  
        password="";  
          DriverManager.registerDriver(new com.mysql.jdbc.Driver());  
        koneksi=DriverManager.getConnection(url,user,password);  
      }catch (SQLException t){  
        System.out.println("Eror membuat koneksi");  
      }  
     }  
    return koneksi;  
  }   
    public static Connection getKoneksi(String host, String port, String username, String password, String db) {
        String konString= "jdbc:mysql://" + host + ":" + port + "/" + db;
        Connection koneksi= null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi= DriverManager.getConnection(konString, username, password);
            System.out.println("KoneksiBerhasil");
        } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "KoneksiDatabase Error");
        koneksi= null;
        }
    return koneksi;
    }

    static Connection getConnection(String url, String user, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static Connection getKoneksi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}