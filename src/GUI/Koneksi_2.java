/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
class Koneksi {
    public static Connection getConnection() throws SQLException {
            Connection koneksi = null;
            String driver = "com.mysql.jbdc.Driver";
            String url = "jdbc:mysql://localhost:3360/aplikasi_penjualan";
            String user = "root";
            String password = "";
            if (koneksi == null){
                try{
                    Class.forName(driver);
                    koneksi = DriverManager.getConnection(url, user, password);
                }catch (ClassNotFoundException error){
                    System.exit(0);
                }
            }
            return koneksi;
        }
}
