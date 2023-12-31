/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Syahni Sahla Fatimah
 */
public final class FormDataObat extends javax.swing.JFrame {

    /**
     * Creates new form FormDataObat
     */
    Connection koneksi;
    
    public FormDataObat() {
        initComponents();
        koneksi = DataConnection.getKoneksi("localhost", "3306", "root", "", "aplikasi_penjualan");
        showData();
        idObat();
        ubahButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }
    
    DefaultTableModel dtm;
    public void showData(){
        String [] kolom = {"ID Obat", "Nama Obat", "Jenis Obat", "Harga", "Stok"};
        
        dtm = new DefaultTableModel(null, kolom);
        
        String search = searchTF.getText();
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM obat WHERE Nama_Obat LIKE '%"+search+"%'";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()){
                String idobat = rs.getString("ID_Obat");
                String namaobat = rs.getString("Nama_Obat");
                String jenisobat = rs.getString("Jenis_Obat");
                String harga = rs.getString("Harga");
                String stok = rs.getString("Stok");
                
                dtm.addRow(new String[]{idobat, namaobat, jenisobat, harga, stok});
                
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Gagal Get Data Karena:" + ex);
        }
        obatTable.setModel(dtm);
    }
    
    private void idObat(){
        try {
            String sql = "select * from obat order by ID_Obat desc";
            Statement stmt = koneksi.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String idObat = rs.getString("ID_Obat").substring(1);
                String AN = "" + (Integer.parseInt(idObat) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "000";}
                else if(AN.length()==2)
                {Nol = "00";}
                else if(AN.length()==3)
                {Nol = "0";}
                else if(AN.length()==4)
                {Nol = "";}

               idobatTF.setText("K" + Nol + AN);
            } else {
               idobatTF.setText("K0001");
            }

           }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           }
    }
    private void clear(){
        idobatTF.setText("");
        namaobatTF.setText("");
        hargaTF.setText("");
        stokTF.setText("");
        jenisobatCB.setSelectedIndex(0);
    }
   
    private void enabled(){
        idobatTF.enable(true);
        namaobatTF.enable(true);
        hargaTF.enable(true);
        jenisobatCB.enable(true);
        stokTF.requestFocus();
    }
   
    private void desabled(){
        idobatTF.enable(false);
        namaobatTF.enable(false);
        hargaTF.enable(false);
        stokTF.enable(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        idobatTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        namaobatTF = new javax.swing.JTextField();
        jenisobatCB = new javax.swing.JComboBox<>();
        hargaTF = new javax.swing.JTextField();
        stokTF = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        ubahButton = new javax.swing.JButton();
        tambahButton = new javax.swing.JButton();
        keluarButton = new javax.swing.JButton();
        searchTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        obatTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(590, 610));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Obat");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 59, -1));

        idobatTF.setEditable(false);
        idobatTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idobatTFActionPerformed(evt);
            }
        });
        jPanel3.add(idobatTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 270, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Obat");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jenis Obat");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Harga");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Stok");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));
        jPanel3.add(namaobatTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 270, 30));

        jenisobatCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Jenis Obat --", "Kapsul", "Tablet", "Syrup", "Salep", "Cream", "Powder", "Liquid" }));
        jPanel3.add(jenisobatCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 270, 30));
        jPanel3.add(hargaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 270, 30));
        jPanel3.add(stokTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 270, 30));

        cancelButton.setBackground(new java.awt.Color(0, 0, 51));
        cancelButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon cancel.png"))); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel3.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 220, 30));

        deleteButton.setBackground(new java.awt.Color(0, 0, 51));
        deleteButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon delete.png"))); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel3.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 220, 30));

        ubahButton.setBackground(new java.awt.Color(0, 0, 51));
        ubahButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        ubahButton.setForeground(new java.awt.Color(255, 255, 255));
        ubahButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon edit.png"))); // NOI18N
        ubahButton.setText("Ubah");
        ubahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahButtonActionPerformed(evt);
            }
        });
        jPanel3.add(ubahButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 220, 30));

        tambahButton.setBackground(new java.awt.Color(0, 0, 51));
        tambahButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tambahButton.setForeground(new java.awt.Color(255, 255, 255));
        tambahButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon add.png"))); // NOI18N
        tambahButton.setText("Tambah");
        tambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonActionPerformed(evt);
            }
        });
        jPanel3.add(tambahButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 220, 30));

        keluarButton.setBackground(new java.awt.Color(0, 0, 51));
        keluarButton.setForeground(new java.awt.Color(255, 255, 255));
        keluarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon keluar.png"))); // NOI18N
        keluarButton.setText("Keluar");
        keluarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarButtonActionPerformed(evt);
            }
        });
        jPanel3.add(keluarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 220, 30));

        searchTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTFKeyReleased(evt);
            }
        });
        jPanel3.add(searchTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 210, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cari Obat");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, -1, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 610, 400));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pills_pill_bottle_medicine_medical_healthcare_icon_141993 (1).png"))); // NOI18N
        jLabel1.setText("FORM DATA OBAT");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 90));

        obatTable.setBackground(new java.awt.Color(0, 0, 51));
        obatTable.setForeground(new java.awt.Color(255, 255, 255));
        obatTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Obat", "Nama Obat", "Jenis Obat", "Harga", "Stok"
            }
        ));
        obatTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                obatTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(obatTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
        String idObat = idobatTF.getText();
        String namaObat = namaobatTF.getText();
        Object jenisObat = jenisobatCB.getSelectedItem();
        String harga = hargaTF.getText();
        String stok = stokTF.getText();

        try {
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate("insert into obat values ('"+idObat+"','"+namaObat+"','"+jenisObat+"','"+harga+"','"+stok+"');");
            stmt.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
            showData();
            clear();
            idObat();
        }catch (SQLException ex){   
            JOptionPane.showMessageDialog(null, "Gagal Tambah Data. Karena :" + ex);
        }
    }//GEN-LAST:event_tambahButtonActionPerformed

    private void ubahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahButtonActionPerformed
        String idObat = idobatTF.getText();
        String namaObat = namaobatTF.getText();
        Object jenisObat = jenisobatCB.getSelectedItem();
        String harga = hargaTF.getText();
        String stok = stokTF.getText();

        try {
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate("UPDATE obat SET ID_Obat = '"+idObat+"', Nama_Obat='"+namaObat+"', Jenis_Obat='"+jenisObat+"',harga= '"+harga+"',stok='"+stok+"' WHERE ID_Obat='"+idObat+"';");
            stmt.close();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            clear();
            showData();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan, Karena" + ex);
        }
    }//GEN-LAST:event_ubahButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String idobat = idobatTF.getText();
        try {
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate("delete from obat where ID_Obat = ('"+idobat+"');");
            clear();
            showData();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus, Karena" + ex);
        }
        tambahButton.setEnabled(true);
        ubahButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        clear();
        idObat();
        tambahButton.setEnabled(true);
        ubahButton.setEnabled(false);
        deleteButton.setEnabled(false);
        idobatTF.setEditable(true);
        idobatTF.enable(true);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void keluarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarButtonActionPerformed
    new HomePegawai().setVisible(true); 
    this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_keluarButtonActionPerformed

    private void obatTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_obatTableMouseClicked
            int row = obatTable.getSelectedRow();
        idobatTF.setEditable(false);
        idobatTF.enable(false);
        idobatTF.setText((String)obatTable.getValueAt(row, 0));
        namaobatTF.setText((String)obatTable.getValueAt(row, 1));
        jenisobatCB.setSelectedItem((String)obatTable.getValueAt(row, 2));
        hargaTF.setText((String)obatTable.getValueAt(row, 3));
        stokTF.setText((String)obatTable.getValueAt(row, 4));
        tambahButton.setEnabled(false);
        ubahButton.setEnabled(true);
        deleteButton.setEnabled(true);
    }//GEN-LAST:event_obatTableMouseClicked

    private void searchTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTFKeyReleased
        // TODO add your handling code here:
        showData();
    }//GEN-LAST:event_searchTFKeyReleased

    private void idobatTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idobatTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idobatTFActionPerformed

    

     
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDataObat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField hargaTF;
    private javax.swing.JTextField idobatTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jenisobatCB;
    private javax.swing.JButton keluarButton;
    private javax.swing.JTextField namaobatTF;
    private javax.swing.JTable obatTable;
    private javax.swing.JTextField searchTF;
    private javax.swing.JTextField stokTF;
    private javax.swing.JButton tambahButton;
    private javax.swing.JButton ubahButton;
    // End of variables declaration//GEN-END:variables

  
    }
