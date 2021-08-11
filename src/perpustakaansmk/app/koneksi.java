/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaansmk.app;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Ahmad
 */
public class koneksi {
    Connection conn;

    public static Connection ConnectDb(){
        try {
//            String url ="jdbc:mysql://sql.smkkesletris.sch.id/u5394077_perpustakaan1";
//            String user="u5394077_koperasi";
//            String pass="Koperasiletris123";
            String url ="jdbc:mysql://localhost:3306/perpustakaan";
            String user="root";
            String pass="root";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =DriverManager.getConnection(url,user,pass);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
}
