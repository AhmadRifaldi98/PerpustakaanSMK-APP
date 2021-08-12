/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaansmk.app;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Ahmad
 */
public class tambah_buku extends javax.swing.JFrame {
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    /**
     * Creates new form tambah_buku
     */
    private Statement stm;
    public tambah_buku() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setResizable(false);
        getregis();
    }
    
    String s_noregis,s_judul,s_pengarang,s_penerbit,s_kotaterbit,s_isbn13,s_isbn10,s_thnterbit,s_harga,s_jumlah,s_ringkasan,s_rak;
    Integer no_regisakhir,no_awal,no_akhir,maxregis,regisawal,k,loop;
    String combain,nomor,jml_regis;
    
    
    public void get(){
        s_noregis       = no_regist.getText();
        s_judul         = jdl_buku.getText();
        s_pengarang     = pengarang.getSelectedItem().toString();
        s_penerbit      = penerbit.getSelectedItem().toString();
        s_kotaterbit    = kota_terbit.getSelectedItem().toString();
        s_isbn13        = isbn_13.getText();
        s_isbn10        = isbn_10.getText();
        s_thnterbit     = thn_terbit.getText();
        s_harga         = harga_buku.getText();
        s_jumlah        = jumlah_buku.getText();
        s_rak           = rak_buku.getSelectedItem().toString();
        s_ringkasan     = ringkasan_buku.getText();
        jml_regis       = jumlah_regist.getText();
    }
    
    public void getregis(){
        conn = koneksi.ConnectDb();
        String sql = "SELECT MAX(no_regis) FROM `tb_statusbuku`";
        try {
            stm=conn.createStatement();
            rs=stm.executeQuery(sql);
            while(rs.next())
            {
                maxregis = rs.getInt(1);
                regisawal = maxregis + 1;
//              merubah integer to string
                combain = regisawal + "";          
                no_regist.setText(combain);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void count(){
        no_awal = 0;
        no_akhir = 0;
        s_noregis       = no_regist.getText();
        s_jumlah        = jumlah_buku.getText();
        no_awal = Integer.parseInt(s_noregis);
        no_akhir = Integer.parseInt(s_jumlah);
//        JOptionPane.showMessageDialog(null, "i = "+i, "Error",JOptionPane.ERROR_MESSAGE);
        if (no_akhir <= 1) {
//            JOptionPane.showMessageDialog(null, "j = "+j, "Error",JOptionPane.ERROR_MESSAGE);
            jumlah_regist.setText(no_awal.toString());
        } else if (no_akhir > 1) {
            no_akhir = no_awal + no_akhir - 1;
            jumlah_regist.setText(no_awal.toString()+" - "+ no_akhir.toString());
        } 

    }    
    
    public void clear(){
//        s_noregis       = no_regist.getText();
        getregis();
        jdl_buku.setText("");
        pengarang.setSelectedIndex(0);
        penerbit.setSelectedIndex(0);
        kota_terbit.setSelectedIndex(0);
        isbn_13.setText("");
        isbn_10.setText("");
        thn_terbit.setText("");
        harga_buku.setText("");
        jumlah_buku.setText("");
        rak_buku.setSelectedIndex(0);
        ringkasan_buku.setText("");
        jumlah_regist.setText("");
    }
    
    public void addall(){
        adddatabuku();
        addstatusbuku();
    }
    
    public void addstatusbuku(){
        get();
//      INSERT INTO `tb_buku` (`no_regis`, `isbn_13`, `isbn_10`, `judul`, `pengarang`, `penerbit`, `thn_terbit`, `harga`, `jumlah`, `lokasi_rak`, `ringkasan`, `foto`, `tanggal`, `kondisi`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,NOW(),'Baik');  
        String sql ="INSERT INTO `tb_statusbuku` (`no_regis`, `isbn_13`, `isbn_10`, `judul`, `pengarang`, `penerbit`, `thn_terbit`, `harga`, `lokasi_rak`, `ringkasan`, `foto`, `tanggal`, `kondisi`) VALUES (?,?,?,?,?,?,?,?,?,?,'',NOW(),'Baik')";
        no_awal = 0;
        no_akhir = 0;
        s_noregis       = no_regist.getText();
        s_jumlah        = jumlah_buku.getText();
        no_awal = Integer.parseInt(s_noregis);
        no_akhir = Integer.parseInt(s_jumlah);
        if (no_akhir <= 1) {
            no_akhir = no_awal;
        } else if (no_akhir > 1) {
            no_akhir = no_awal + no_akhir - 1;
        } 
        for (int i = no_awal; i <= no_akhir; i++) {
            try {
//                JOptionPane.showMessageDialog(null, i, "Error",JOptionPane.ERROR_MESSAGE);
                conn = koneksi.ConnectDb();
                pst = conn.prepareStatement(sql);
                if (i < 10) {
                    nomor = "0000"+i;
                } else if (i < 100){
                    nomor = "000"+i;
                } else if (i < 1000){
                    nomor = "00"+i;
                } else if (i < 10000){
                    nomor = "0"+i;
                }
                pst.setString(1,  nomor + "");
                pst.setString(2,  s_isbn13);
                pst.setString(3,  s_isbn10);
                pst.setString(4,  s_judul);
                pst.setString(5,  s_pengarang);
                pst.setString(6,  s_penerbit);
                pst.setString(7,  s_thnterbit);
                pst.setString(8,  s_harga);
                pst.setString(9, s_rak);
                pst.setString(10, s_ringkasan);
                pst.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan..", "Berhasil",JOptionPane.INFORMATION_MESSAGE);
                no_regist.setText(i+"");
                

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan..", "Berhasil",JOptionPane.INFORMATION_MESSAGE);
        clear();
    }
    
    public void adddatabuku(){
        get();
        String sql ="INSERT INTO `tb_databuku` (`no_regis`,`isbn_13`, `isbn_10`, `judul`, `pengarang`, `penerbit`, `thn_terbit`, `harga`,`jumlah`, `lokasi_rak`, `ringkasan`, `foto`, `tanggal`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,NOW())";
            try {
                conn = koneksi.ConnectDb();
                pst = conn.prepareStatement(sql);
                pst.setString(1,  jml_regis);
                pst.setString(2,  s_isbn13);
                pst.setString(3,  s_isbn10);
                pst.setString(4,  s_judul);
                pst.setString(5,  s_pengarang);
                pst.setString(6,  s_penerbit);
                pst.setString(7,  s_thnterbit);
                pst.setString(8,  s_harga);
                pst.setString(9,  s_jumlah);
                pst.setString(10, s_rak);
                pst.setString(11, s_ringkasan);
                pst.setString(12, "");
                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan..", "Berhasil",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void update(){
                conn = koneksi.ConnectDb();
        get();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jdl_buku = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pengarang = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        no_regist = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        kota_terbit = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        thn_terbit = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        isbn_13 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        isbn_10 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jumlah_buku = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rak_buku = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        ringkasan_buku = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        foto_buku = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        harga_buku = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        penerbit = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jumlah_regist = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("Judul Buku");

        jdl_buku.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Pengarang");

        pengarang.setEditable(true);
        pengarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));

        jPanel2.setBackground(new java.awt.Color(30, 108, 199));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tambah Buku");

        no_regist.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        no_regist.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NO REGIST");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 439, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(no_regist, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(no_regist, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Penerbit");

        kota_terbit.setEditable(true);
        kota_terbit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Tahun Terbit");

        thn_terbit.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        thn_terbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thn_terbitActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setText("Kode ISBN - 13");

        isbn_13.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Kode ISBN - 10");

        isbn_10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setText("Jumlah Buku");

        jumlah_buku.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jumlah_buku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlah_bukuKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel10.setText("Lokasi Rak");

        rak_buku.setEditable(true);
        rak_buku.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));

        ringkasan_buku.setColumns(20);
        ringkasan_buku.setRows(5);
        jScrollPane1.setViewportView(ringkasan_buku);

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel11.setText("Ringkasan Buku");

        javax.swing.GroupLayout foto_bukuLayout = new javax.swing.GroupLayout(foto_buku);
        foto_buku.setLayout(foto_bukuLayout);
        foto_bukuLayout.setHorizontalGroup(
            foto_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
        );
        foto_bukuLayout.setVerticalGroup(
            foto_bukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel12.setText("Foto Buku");

        jButton1.setText("Explore");

        jButton2.setText("Hapus Gambar");

        harga_buku.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel13.setText("Harga Buku");

        jButton3.setText("Simpan");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel14.setText("Kota Terbit");

        penerbit.setEditable(true);
        penerbit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih --" }));

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel15.setText("No Regist");

        jumlah_regist.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jumlah_regist.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(187, 187, 187)
                        .addComponent(jLabel4)
                        .addGap(126, 126, 126)
                        .addComponent(jLabel5)
                        .addGap(142, 142, 142)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jdl_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(pengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(penerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(kota_terbit, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(foto_buku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2)
                                    .addComponent(jLabel9)
                                    .addComponent(jumlah_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(jumlah_regist, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(rak_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(94, 94, 94)
                                .addComponent(jLabel8)
                                .addGap(98, 98, 98)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(isbn_13, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(isbn_10, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(thn_terbit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(harga_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel14)))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdl_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(penerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kota_terbit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8))
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel13)))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(thn_terbit, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(isbn_10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(isbn_13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(harga_buku))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(6, 6, 6)
                                        .addComponent(jButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)
                                        .addGap(6, 6, 6)
                                        .addComponent(jumlah_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15)
                                        .addGap(6, 6, 6)
                                        .addComponent(jumlah_regist, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(19, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(foto_buku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rak_buku, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addContainerGap())))
        );

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(878, 565));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void thn_terbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thn_terbitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thn_terbitActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        addall();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jumlah_bukuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah_bukuKeyReleased
        s_jumlah        = jumlah_buku.getText();
        if (s_jumlah.equals("")||s_jumlah.equals(null)) {            
            jumlah_regist.setText("");
        } else {
            count();
        }
    }//GEN-LAST:event_jumlah_bukuKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(tambah_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(tambah_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(tambah_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(tambah_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tambah_buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel foto_buku;
    private javax.swing.JTextField harga_buku;
    private javax.swing.JTextField isbn_10;
    private javax.swing.JTextField isbn_13;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jdl_buku;
    private javax.swing.JTextField jumlah_buku;
    private javax.swing.JTextField jumlah_regist;
    private javax.swing.JComboBox<String> kota_terbit;
    private javax.swing.JTextField no_regist;
    private javax.swing.JComboBox<String> penerbit;
    private javax.swing.JComboBox<String> pengarang;
    private javax.swing.JComboBox<String> rak_buku;
    private javax.swing.JTextArea ringkasan_buku;
    private javax.swing.JTextField thn_terbit;
    // End of variables declaration//GEN-END:variables
}
