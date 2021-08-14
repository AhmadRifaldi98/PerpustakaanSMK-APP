/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaansmk.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Ahmad
 */
public class pnl_databuku extends javax.swing.JPanel {
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    /**
     * Creates new form pnl_dashboard
     */
    public pnl_databuku() {
        initComponents();
        tablebuku();
        tablestatusbuku();
        tablepenerbit();
    }

private DefaultTableModel tabmode;



public void tablebuku(){
    Object[] Baris = {"No. Regist","Judul Buku","Lokasi Rak","Pengarang","Penerbit","Tahun Terbit","ISBN-13","ISBN-10","Jumlah Buku", "Harga Buku", "Ringkasan","Tanggal"};
//                                      a,l,d,j,e,f,g,b,c,i,h,k,m
        tabmode = new DefaultTableModel(null, Baris);
        tb_buku.setModel(tabmode);
        String sql = "SELECT * FROM `tb_databuku`";
        try{
            conn=koneksi.ConnectDb();
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("no_regis");
                String b = hasil.getString("isbn_13");
                String c = hasil.getString("isbn_10");
                String d = hasil.getString("judul");
                String e = hasil.getString("pengarang");
                String f = hasil.getString("penerbit");
                String g = hasil.getString("thn_terbit");
                String h = hasil.getString("harga");
                String i = hasil.getString("jumlah");
                String j = hasil.getString("lokasi_rak");
                String k = hasil.getString("ringkasan");
                String l = hasil.getString("foto");
                String m = hasil.getString("tanggal");
                String[] data ={a,d,j,e,f,g,b,c,i,h,k,m};
                tabmode.addRow(data);
                Thread.sleep(50);
            }
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(null, e);
        }
}

public void tablestatusbuku(){
    Object[] Baris = {"No. Regist","Judul Buku","Lokasi Rak","Pengarang","Penerbit","Tahun Terbit","ISBN-13","ISBN-10", "Harga Buku","Tanggal","Kondisi"};
//                                      a,l,d,j,e,f,g,b,c,i,h,k,m
        tabmode = new DefaultTableModel(null, Baris);
        tb_statusbuku.setModel(tabmode);
        String sql = "SELECT * FROM `tb_statusbuku`";
        try{
            conn=koneksi.ConnectDb();
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("no_regis");
                String b = hasil.getString("isbn_13");
                String c = hasil.getString("isbn_10");
                String d = hasil.getString("judul");
                String e = hasil.getString("pengarang");
                String f = hasil.getString("penerbit");
                String g = hasil.getString("thn_terbit");
                String h = hasil.getString("harga");
                String j = hasil.getString("lokasi_rak");
                String k = hasil.getString("ringkasan");
                String l = hasil.getString("foto");
                String m = hasil.getString("tanggal");
                String n = hasil.getString("kondisi");
                String[] data ={a,d,j,e,f,g,b,c,h,m,n};
                tabmode.addRow(data);
                Thread.sleep(50);
            }
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(null, e);
        }
}

public void tablepenerbit(){
    Object[] Baris = {"ID","Kode Penerbit", "Nama Penerbit"};
        tabmode = new DefaultTableModel(null, Baris);
        tb_penerbit.setModel(tabmode);
        String sql = "SELECT * FROM `tb_penerbit`";
        try{
            conn=koneksi.ConnectDb();
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id");
                String b = hasil.getString("kode");
                String c = hasil.getString("penerbit");
                String[] data ={a,b,c};
                tabmode.addRow(data);
                Thread.sleep(50);
            }
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(null, e);
        }
}

//    public void tablebuku(){
//    conn=koneksi.ConnectDb();
////        id	judul_buku	pengarang	penerbit	tahun_terbit	kode_ISBN_13	kode_ISBN_10	jumlah	harga	lokasi_rak	foto_cover	ringkasan	tanggal
//    Object[] Baris = {"ID","Cover Buku","Judul Buku","Lokasi Rak","Pengarang","Penerbit","Tahun Terbit","ISBN-13","ISBN-10","Jumlah Buku", "Harga Buku",  "Ringkasan","Tanggal"};
//    tabmode = new DefaultTableModel(null, Baris);
//    tb_buku.setModel(tabmode);
//    String sql = "select * from `tb_buku`";
//        TableColumnModel columnModel = tb_buku.getColumnModel();
//        columnModel.getColumn(0).setPreferredWidth(30);
//        columnModel.getColumn(0).setMaxWidth(170);
////        columnModel.getColumn(1).setPreferredWidth(150);
////        columnModel.getColumn(1).setMaxWidth(150);
////        columnModel.getColumn(2).setPreferredWidth(40);
////        columnModel.getColumn(2).setMaxWidth(40);  
//    try{
//            java.sql.Statement stat = conn.createStatement();
//            ResultSet hasil = stat.executeQuery(sql);
//            while(hasil.next()){
//                String a = hasil.getString("id");
//                String b = hasil.getString("foto_cover");
//                String c = hasil.getString("judul_buku");
//                String d = hasil.getString("lokasi_rak");
////                String e = hasil.getString("pengarang");
////                String f = hasil.getString("penerbit");
//                String g = hasil.getString("tahun_terbit");
//                String h = hasil.getString("kode_ISBN_13");
//                String i = hasil.getString("kode_ISBN_10");
//                String j = hasil.getString("jumlah");
////                String k = hasil.getString("harga");
//                String l = hasil.getString("ringkasan");
////                String m = hasil.getString("tanggal");
//                
//                String[] data ={a,b,c,d,g,h,i,j,l};
//                tabmode.addRow(data);
//            }
//        } catch (Exception e) { 
//            
//    }
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        daftar_buku = new javax.swing.JPanel();
        tambah = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_buku = new javax.swing.JTable();
        status_buku = new javax.swing.JPanel();
        tambah1 = new javax.swing.JButton();
        ubah1 = new javax.swing.JButton();
        hapus1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_statusbuku = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        status_buku1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_penerbit = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(914, 600));
        setMinimumSize(new java.awt.Dimension(914, 620));
        setPreferredSize(new java.awt.Dimension(914, 620));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        Background.setBackground(new java.awt.Color(255, 255, 255));

        daftar_buku.setBackground(new java.awt.Color(255, 255, 255));

        tambah.setBackground(new java.awt.Color(42, 157, 143));
        tambah.setForeground(new java.awt.Color(255, 255, 255));
        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        ubah.setBackground(new java.awt.Color(252, 163, 17));
        ubah.setForeground(new java.awt.Color(255, 255, 255));
        ubah.setText("Ubah");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        hapus.setBackground(new java.awt.Color(230, 57, 70));
        hapus.setForeground(new java.awt.Color(255, 255, 255));
        hapus.setText("Hapus");

        tb_buku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_buku);

        javax.swing.GroupLayout daftar_bukuLayout = new javax.swing.GroupLayout(daftar_buku);
        daftar_buku.setLayout(daftar_bukuLayout);
        daftar_bukuLayout.setHorizontalGroup(
            daftar_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daftar_bukuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(637, 637, 637))
            .addGroup(daftar_bukuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        daftar_bukuLayout.setVerticalGroup(
            daftar_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daftar_bukuLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(daftar_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Daftar Buku", daftar_buku);

        status_buku.setBackground(new java.awt.Color(255, 255, 255));

        tambah1.setBackground(new java.awt.Color(42, 157, 143));
        tambah1.setForeground(new java.awt.Color(255, 255, 255));
        tambah1.setText("Tambah");
        tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah1ActionPerformed(evt);
            }
        });

        ubah1.setBackground(new java.awt.Color(252, 163, 17));
        ubah1.setForeground(new java.awt.Color(255, 255, 255));
        ubah1.setText("Ubah");
        ubah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubah1ActionPerformed(evt);
            }
        });

        hapus1.setBackground(new java.awt.Color(230, 57, 70));
        hapus1.setForeground(new java.awt.Color(255, 255, 255));
        hapus1.setText("Hapus");

        tb_statusbuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tb_statusbuku);

        javax.swing.GroupLayout status_bukuLayout = new javax.swing.GroupLayout(status_buku);
        status_buku.setLayout(status_bukuLayout);
        status_bukuLayout.setHorizontalGroup(
            status_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status_bukuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ubah1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(637, 637, 637))
            .addGroup(status_bukuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        status_bukuLayout.setVerticalGroup(
            status_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status_bukuLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(status_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubah1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Status Buku", status_buku);

        status_buku1.setBackground(new java.awt.Color(255, 255, 255));

        tb_penerbit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tb_penerbit);

        javax.swing.GroupLayout status_buku1Layout = new javax.swing.GroupLayout(status_buku1);
        status_buku1.setLayout(status_buku1Layout);
        status_buku1Layout.setHorizontalGroup(
            status_buku1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status_buku1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        status_buku1Layout.setVerticalGroup(
            status_buku1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status_buku1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(status_buku1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(status_buku1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Penerbit", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Pengarang", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Kota", jPanel1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Rak Buku", jPanel4);

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        add(Background);
    }// </editor-fold>//GEN-END:initComponents

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubahActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        new tambah_buku().setVisible(true);
//        tambah_buku tambahbuku = new tambah_buku();
    }//GEN-LAST:event_tambahActionPerformed

    private void tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tambah1ActionPerformed

    private void ubah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubah1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubah1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JPanel daftar_buku;
    private javax.swing.JButton hapus;
    private javax.swing.JButton hapus1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel status_buku;
    private javax.swing.JPanel status_buku1;
    private javax.swing.JButton tambah;
    private javax.swing.JButton tambah1;
    public javax.swing.JTable tb_buku;
    public javax.swing.JTable tb_penerbit;
    public javax.swing.JTable tb_statusbuku;
    private javax.swing.JButton ubah;
    private javax.swing.JButton ubah1;
    // End of variables declaration//GEN-END:variables
}
