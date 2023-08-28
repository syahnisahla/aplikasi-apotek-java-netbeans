/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class FormPelanggan extends javax.swing.JFrame {

    /**
     * Creates new form FormPelanggan
     */
    Connection koneksi;
    
    public FormPelanggan() {
        initComponents();
        koneksi = DataConnection.getKoneksi("localhost", "3306", "root", "", "aplikasi_penjualan");
        showData();
        idPelanggan();
        ubahButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }
    DefaultTableModel dtm;
    public void showData(){
        String [] kolom = {"ID Pelanggan", "Nama Pelanggan", "Jenis Kelamin", "Alamat"};
        
        dtm = new DefaultTableModel(null, kolom);
        String search = textCari.getText();
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM pelanggan WHERE Nama_Pelanggan LIKE '%"+search+"%'";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()){
                String idPelanggan = rs.getString("ID_Pelanggan");
                String namaPelanggan = rs.getString("Nama_Pelanggan");
                String jenisKelamin = rs.getString("Jenis_Kelamin");
                String alamat = rs.getString("Alamat");
                
                dtm.addRow(new String[]{idPelanggan, namaPelanggan, jenisKelamin, alamat});
                
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Gagal Get Data Karena:" + ex);
        }
        tabelPelanggan.setModel(dtm);
    }
    
    private void idPelanggan(){
        try {
            String sql = "select * from pelanggan order by ID_Pelanggan desc";
            Statement stmt = koneksi.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String idPelanggan = rs.getString("ID_Pelanggan").substring(1);
                String AN = "" + (Integer.parseInt(idPelanggan) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "112";}
                else if(AN.length()==2)
                {Nol = "11";}
                else if(AN.length()==3)
                {Nol = "1";}
                else if(AN.length()==4)
                {Nol = "";}

               idPelangganTF.setText("P" + Nol + AN);
            } else {
               idPelangganTF.setText("P1120");
            }

           }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           }
    }
    private void clear(){
        pelangganTF.setText("");
        alamatTF.setText("");
        
    }
   
    private void enabled(){
        pelangganTF.enable(true);
        alamatTF.enable(true);
    }
   
    private void desabled(){
        pelangganTF.enable(false);
        alamatTF.enable(false);

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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pelangganTF = new javax.swing.JTextField();
        jenisKelamin = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        alamatTF = new javax.swing.JTextField();
        tambahButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        ubahButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        perempuanRB = new javax.swing.JRadioButton();
        lakiRB = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        idPelangganTF = new javax.swing.JTextField();
        keluarButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        textCari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPelanggan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama Pelanggan");

        jenisKelamin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jenisKelamin.setForeground(new java.awt.Color(255, 255, 255));
        jenisKelamin.setText("Jenis Kelamin");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Alamat");

        tambahButton.setBackground(new java.awt.Color(0, 0, 51));
        tambahButton.setForeground(new java.awt.Color(255, 255, 255));
        tambahButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon add.png"))); // NOI18N
        tambahButton.setText("Tambah");
        tambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(0, 0, 51));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon delete.png"))); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        ubahButton.setBackground(new java.awt.Color(0, 0, 51));
        ubahButton.setForeground(new java.awt.Color(255, 255, 255));
        ubahButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon edit.png"))); // NOI18N
        ubahButton.setText("Ubah");
        ubahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahButtonActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(0, 0, 51));
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon cancel.png"))); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        perempuanRB.setForeground(new java.awt.Color(255, 255, 255));
        perempuanRB.setText("Perempuan");

        lakiRB.setForeground(new java.awt.Color(255, 255, 255));
        lakiRB.setText("Laki-laki");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID Pelanggan");

        idPelangganTF.setEditable(false);

        keluarButton.setBackground(new java.awt.Color(0, 0, 51));
        keluarButton.setForeground(new java.awt.Color(255, 255, 255));
        keluarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon keluar.png"))); // NOI18N
        keluarButton.setText("Keluar");
        keluarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cari Pelanggan");

        textCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textCariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(541, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idPelangganTF, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(pelangganTF, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jenisKelamin)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(perempuanRB, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lakiRB, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(alamatTF, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(110, 370, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tambahButton, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubahButton, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(keluarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textCari, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tambahButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ubahButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton)
                        .addGap(11, 11, 11)
                        .addComponent(keluarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textCari, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(idPelangganTF, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addComponent(pelangganTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jenisKelamin)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(perempuanRB)
                            .addComponent(lakiRB))
                        .addGap(11, 11, 11)
                        .addComponent(jLabel4)
                        .addGap(11, 11, 11)
                        .addComponent(alamatTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 59, 623, 400));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/check-form_116472.png"))); // NOI18N
        jLabel1.setText("FORM PELANGGAN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 623, 60));

        tabelPelanggan.setBackground(new java.awt.Color(0, 0, 51));
        tabelPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        tabelPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pelanggan", "Nama Pelanggan", "Jenis Kelamin", "Alamat"
            }
        ));
        tabelPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPelanggan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 623, 140));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
        String idPelanggan = idPelangganTF.getText();
        String namaPelanggan = pelangganTF.getText();
        String alamat = alamatTF.getText();
        String jenisKelamin = null;
        if(perempuanRB.isSelected()){
            jenisKelamin = "Perempuan";
        } else if (lakiRB.isSelected()) {
            jenisKelamin = "Laki-laki";
        }
        try{
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate("insert into pelanggan values('"+idPelanggan+"', '"+namaPelanggan+"', '"+jenisKelamin+"', '"+alamat+"');");
            stmt.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
            showData();
            clear();
            idPelanggan();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal ditambahkan, karena: " + ex);
        }
        perempuanRB.setSelected(false);
        lakiRB.setSelected(false);

    }//GEN-LAST:event_tambahButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String idPelanggan = idPelangganTF.getText();
        try{
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate("delete from pelanggan where ID_Pelanggan = ('"+idPelanggan+"');");
            clear();
            showData();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Gagal Hapus Data, karena: " + ex);
        }
        perempuanRB.setSelected(false);
        lakiRB.setSelected(false);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void ubahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahButtonActionPerformed
        String idPelanggan = idPelangganTF.getText();
        String namaPelanggan = pelangganTF.getText();
        String alamat = alamatTF.getText();
        String jenisKelamin = null;
        if(perempuanRB.isSelected()){
            jenisKelamin = "Perempuan";
        } else if (lakiRB.isSelected()){
            jenisKelamin = "Laki-laki";
        }
        try{
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate("UPDATE pelanggan SET ID_Pelanggan = '"+idPelanggan+"', Nama_Pelanggan='"+namaPelanggan+"', Jenis_Kelamin='"+jenisKelamin+"', Alamat= '"+alamat+"' WHERE ID_Pelanggan='"+idPelanggan+"';");
            stmt.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            clear();
            showData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal Ubah Data. Karena: " +ex);
        }
        perempuanRB.setSelected(false);
        lakiRB.setSelected(false);
    }//GEN-LAST:event_ubahButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    clear();
        tambahButton.setEnabled(true);
        ubahButton.setEnabled(false);
        deleteButton.setEnabled(false);
        idPelangganTF.setEditable(true);
        idPelangganTF.enable(true);
        perempuanRB.setSelected(false);
        lakiRB.setSelected(false);
        idPelanggan();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void tabelPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPelangganMouseClicked
    int row = tabelPelanggan.getSelectedRow();
        idPelangganTF.enable(false);
        idPelangganTF.setText((String)tabelPelanggan.getValueAt(row, 0));
        pelangganTF.setText((String)tabelPelanggan.getValueAt(row, 1));
        String gender = (String)tabelPelanggan.getValueAt(row, 2);
        if ("Laki-laki".equals(gender)) {
            lakiRB.setSelected(true);
        } else {
            perempuanRB.setSelected(true);
        }
        alamatTF.setText((String)tabelPelanggan.getValueAt(row, 3));
        tambahButton.setEnabled(false);
        ubahButton.setEnabled(true);
        deleteButton.setEnabled(true);        
    }//GEN-LAST:event_tabelPelangganMouseClicked

    private void keluarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarButtonActionPerformed
    new HomePegawai().setVisible(true);
    this.dispose(); // TODO add your handling code here:
    }//GEN-LAST:event_keluarButtonActionPerformed

    private void textCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCariKeyReleased
        // TODO add your handling code here:
        showData();
    }//GEN-LAST:event_textCariKeyReleased

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamatTF;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField idPelangganTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jenisKelamin;
    private javax.swing.JButton keluarButton;
    private javax.swing.JRadioButton lakiRB;
    private javax.swing.JTextField pelangganTF;
    private javax.swing.JRadioButton perempuanRB;
    private javax.swing.JTable tabelPelanggan;
    private javax.swing.JButton tambahButton;
    private javax.swing.JTextField textCari;
    private javax.swing.JButton ubahButton;
    // End of variables declaration//GEN-END:variables
}
