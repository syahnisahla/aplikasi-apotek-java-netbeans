/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author User
 */
public class Penjualan extends javax.swing.JFrame {
private DefaultTableModel model;
    /**
     * Creates new form Penjualan
     */

    boolean stockAvailable = true;
    int quantityBuy = 0;
    String idObatGlobal;

    public Penjualan() {
        initComponents();
        comboPelanggan();
        comboId();
        autoKey();
        
        textFaktur.disable();
        
        model =new DefaultTableModel();
        tabelDetail.setModel(model);
        model.addColumn("ID Obat");
        model.addColumn("Nama Obat");
        model.addColumn("Harga");
        model.addColumn("Qty");
        model.addColumn("Sub Total");
        model.addColumn("Jual Time");
        
        //START fungsi tidak menampilkan column ID barang(0) dan jual time (5)
        tabelDetail.getColumnModel().getColumn(5).setMinWidth(0);
        tabelDetail.getColumnModel().getColumn(5).setMaxWidth(0);
        tabelDetail.getColumnModel().getColumn(5).setWidth(0);
        
        tabelDetail.getColumnModel().getColumn(0).setMinWidth(0);
        tabelDetail.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelDetail.getColumnModel().getColumn(0).setWidth(0);
        
        loadData();
        Date date = new Date();
        cmbTanggal.setDate(date);
    }

    public void Batal() {
        int stok, qty, total;
        stok = Integer.parseInt(textStok.getText());
        qty = Integer.parseInt(textQty.getText());
        total = stok+qty;
    
        Object idObat = this.cmbIdObat.getSelectedItem();
        try{
            Connection c = DataConnection.getkoneksi();  
            String sql = "UPDATE obat set Stok WHERE ID_Obat";  
            PreparedStatement p =(PreparedStatement)c.prepareStatement(sql);  
                p.setInt(1,total);
                p.setObject(2,idObat);//yang kode atau id letakkan di nomor terakhir  
                p.executeUpdate();  
                p.close(); 
                //cekStok();
        }catch(SQLException e){  
            System.out.println("Terjadi Kesalahan");  
        }finally{   
            //JOptionPane.showMessageDialog(this,"Stock barang telah di update Diubah");
        }
        
         //Proses mengahapus data dari tabel detail
     try {
        Connection c= DataConnection.getkoneksi();
        String sql="DELETE From jual WHERE ID_Penjualan='"+this.textFaktur.getText()+"' AND Time ='"+this.textDateTime.getText()+"'";
        PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);
        p.executeUpdate();
        p.close();
    }catch(SQLException e){
        System.out.println("Terjadi Kesalahan");
    }finally{
        loadData();
        JOptionPane.showMessageDialog(this,"Sukses Hapus Data...");
    }  
    }
    
    public void cariKode(){
        int i=tabelDetail.getSelectedRow();  
        if(i==-1)  
        { return; }  
        String ID =(String)model.getValueAt(i, 0); 
        cmbIdObat.setSelectedItem(ID);
    }
    
    public void showData(){
        try {
        Connection c = DataConnection.getkoneksi();
        String sql="Select * from jual, obat WHERE jual.ID_Obat = obat.ID_Obat AND jual.ID_Obat='"+this.textNama.getText()+"'"; 
        Statement st = DataConnection.getkoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
        this.textQty.setText(rs.getString("Qty"));
        this.cmbIdObat.setSelectedItem(rs.getString("Nama_Obat"));
        this.textJual.setText(rs.getString("Harga"));
        this.textSubTotal.setText(rs.getString("Sub_Total"));
        this.textDateTime.setText(rs.getString("Time"));
        }
        rs.close(); st.close();}
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Simpan Karena :" +e);
        }
    }
    
    public void showSisa(){
        try {
        Connection c = DataConnection.getkoneksi();
        String sql="Select * from  obat WHERE ID_Obat ='"+this.textNama.getText()+"'"; 
        Statement st = DataConnection.getkoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
        this.textStok.setText(rs.getString("Stok"));      
        }
        rs.close(); st.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateStok(){
        int stok, qty, total;
        stok = Integer.parseInt(textStok.getText());
        qty = Integer.parseInt(textQty.getText());
        total = stok-qty;
    
        String idObat = this.textNama.getText();
        try{
            Connection c = DataConnection.getkoneksi();  
            String sql ="UPDATE obat set Stok WHERE ID_Obat";  
            PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
                p.setInt(1,total);
                p.setString(2,idObat);//yang kode atau id letakkan di nomor terakhir  
                p.executeUpdate();  
                p.close();
                cekStok();
        }catch(SQLException e){  
            System.out.println("Terjadi Kesalahan");  
        }finally{   
       //JOptionPane.showMessageDialog(this,"Stock barang telah di update Diubah");  
        }
    }
    
    public final void loadData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{  
            Connection c= DataConnection.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from jual, obat WHERE jual.ID_Obat = obat.ID_Obat AND jual.ID_Penjualan='"+this.textFaktur.getText()+"'";
            ResultSet r=s.executeQuery(sql);
            while(r.next()){
                Object[]o=new Object[6];
                o[0]=r.getString("ID_Obat");
                o[1]=r.getString("Nama_Obat");
                o[2]=r.getString("Harga");
                o[3]=r.getString("Qty");
                o[4]=r.getString("Sub_Total");
                o[5]=r.getString("Stok");
                model.addRow(o);
            }  
            r.close();  
            s.close();
            //showData();  
        }catch(SQLException e){  
            JOptionPane.showMessageDialog(null, "Gagal" + e); 
   }



   //menjumlahkan isi colom ke 4 dalam tabel
   int total = 0;
   for (int i =0; i< tabelDetail.getRowCount(); i++){
       int amount = Integer.parseInt((String)tabelDetail.getValueAt(i, 4));
       total += amount;
   }
   textTotal.setText(""+total);
    }
    
    public void autoSum(){
        int a, b, c;
        a = Integer.parseInt(textJual.getText());
        b = Integer.parseInt(textQty.getText());
        c = a*b;
        textSubTotal.setText(""+c);
    }
    
    public void hitungKembali(){
        int d, e, f;
        d = Integer.parseInt(textTotal.getText());
        e = Integer.parseInt(textBayar.getText());
        f = e-d;
        textKembalian.setText(""+f);
    }
    
    public void autoKey(){
        try {  
            java.util.Date tgl = new java.util.Date();  
            java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyMMdd");  
            java.text.SimpleDateFormat tanggal = new java.text.SimpleDateFormat("yyyyMMdd");  
            Connection c=DataConnection.getkoneksi();  
            String sql = "select max(ID_Penjualan) from penjualan WHERE Tanggal ="+tanggal.format(tgl);   
            Statement st = DataConnection.getkoneksi().createStatement();  
            ResultSet rs = st.executeQuery(sql);  
            while(rs.next()){  
            Long a =rs.getLong(1); //mengambil nilai tertinggi  
                if(a == 0){  
                    this.textFaktur.setText(kal.format(tgl)+"0000"+(a+1));  
                }else{  
                    this.textFaktur.setText(""+(a+1));  
                }  
            }  
            rs.close(); st.close();}  
        catch (Exception e) {  
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan" + e);  
        }  
    }
    
    public void selesai(){
        String idPenjualan =this.textFaktur.getText();  
        Object pelanggan =this.cmbPelanggan.getSelectedItem();
        String idObat = this.textNama.getText();
        String harga = this.textJual.getText();
        String qty = this.textQty.getText();
        String total = this.textTotal.getText();
        String bayar =this.textBayar.getText();
        String kembali =this.textKembalian.getText();
   
   //Date date = new Date();
   //JdateJual.setDate(date);
        
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
   Date tanggal = new Date(); 
   tanggal = cmbTanggal.getDate(); 
   String jual_tgl = dateFormat.format(tanggal);

        
        
   
   try{  
     Connection c=DataConnection.getkoneksi();  
     String sql="INSERT INTO penjualan(ID_Penjualan, Tanggal, Pelanggan, ID_Obat, Harga, Qty, Total, Bayar, Kembali) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  
     PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
     p.setString(1,idPenjualan);
     p.setString(2,jual_tgl);
     p.setObject(3,pelanggan);
     p.setString(4,idObat);
     p.setString(5,harga);
     p.setString(6,qty);
     p.setString(7,total);
     p.setString(8,bayar);
     p.setString(9,kembali);
     p.executeUpdate();
     p.close();
     JOptionPane.showMessageDialog(null, "Silahkan Cetak Struk");
   }catch(SQLException e){ 
       JOptionPane.showMessageDialog(null, "Gagal Simpan" + e);
  }
   
  autoKey();
  loadData();
    }
    
    public void tambahDetail(){
        Date HariSekarang = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
      
        String idPenjualan =this.textFaktur.getText();  
        String idObat =this.textNama.getText();  
        String harga=this.textJual.getText();  
        String qty=this.textQty.getText();
        quantityBuy = Integer.parseInt(qty);
        idObatGlobal = idObat;
        String subTotal =this.textSubTotal.getText();
        String DateTime = ft.format(HariSekarang);
        cekStok();
        if (stockAvailable) {
            try{  
                Connection c=DataConnection.getkoneksi();  
                String sql="Insert into jual (ID_Penjualan, ID_Obat, Harga, Qty, Sub_Total, Time) values (?,?,?,?,?,?)";  
                PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
                p.setString(1,idPenjualan);
                p.setObject(2,idObat);
                p.setString(3,harga);
                p.setString(4,qty);
                p.setString(5,subTotal);
                p.setString(6,DateTime);
                p.executeUpdate();
                p.close();
            }catch(SQLException e){ 
                JOptionPane.showMessageDialog(null, "Gagal Tambah Data" + e); 
            }finally{  
   //loadData();
       //JOptionPane.showMessageDialog(this,"Data Telah Tersimpan");  
            }
        } else {
            JOptionPane.showMessageDialog(null, "Stock Kurang"); 
        }
    }
    
    public void cariId(){
        try {
        Connection c=DataConnection.getkoneksi();
        String sql = "select * from obat where Nama_Obat='"+this.cmbIdObat.getSelectedItem()+"'"; 
        Statement st = DataConnection.getkoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){
        this.textNama.setText(rs.getString("ID_Obat"));
        this.textJual.setText(rs.getString("Harga"));
        this.textStok.setText(rs.getString("Stok"));
        }
        rs.close(); st.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void comboId(){
       try {
        Connection c=DataConnection.getkoneksi();
        Statement st = c.createStatement();
        String sql_tc = "select ID_Obat, Nama_Obat from obat order by Nama_Obat asc";
        ResultSet rs = st.executeQuery(sql_tc);

        while(rs.next()){
            String nama = rs.getString("Nama_Obat");
            cmbIdObat.addItem(nama);
        }
        rs.close(); st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal"+ e);
        }
    }
    
    public void cariNama(){
        try {
        Connection c=DataConnection.getkoneksi();
        String sql_t = "select ID_Pelanggan from pelanggan where Nama_Pelanggan='"+cmbPelanggan.getSelectedItem()+"'"; 
        Statement st = DataConnection.getkoneksi().createStatement();
        ResultSet rs = st.executeQuery(sql_t);                              // yang anda ingin kan
        
        while(rs.next()){
        this.textPelanggan.setText(rs.getString("ID_Pelanggan")); 
        }
        rs.close(); st.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }     
    }
    
    public void bersihkan(){
        cmbIdObat.setSelectedIndex(0);
        textNama.setText("");
        textJual.setText("");
        textQty.setText("");
        textBayar.setText("");
        textSubTotal.setText("");
    }
    
    public void comboPelanggan(){
        try {
        Connection c=DataConnection.getkoneksi();
        Statement st = c.createStatement();
        String sql_tc = "select ID_Pelanggan, Nama_Pelanggan from pelanggan order by ID_Pelanggan asc";
        ResultSet rs = st.executeQuery(sql_tc);

        while(rs.next()){
            String nama = rs.getString("Nama_Pelanggan");
            cmbPelanggan.addItem(nama);
        }
        rs.close(); st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
        
    private int cekStok(){
        int stok = 0;
        try{
            Connection c = DataConnection.getkoneksi();
            Statement st = c.createStatement();
            String queryStock = "SELECT Stok FROM obat WHERE ID_Obat='"+idObatGlobal+"'";
            ResultSet rs = st.executeQuery(queryStock);
            
            while (rs.next()){
                stok = Integer.parseInt(rs.getString(1));
            }
        }catch (Exception ex){
           JOptionPane.showMessageDialog(null, "Gagal Cek Stock, Karena:" + ex);
        }
        if (stok < quantityBuy) {
            stockAvailable = false;
        }
        return stok;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textFaktur = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbTanggal = new org.freixas.jcalendar.JCalendarCombo();
        jLabel4 = new javax.swing.JLabel();
        cmbPelanggan = new javax.swing.JComboBox<>();
        textTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textNama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textJual = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        textQty = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        textSubTotal = new javax.swing.JTextField();
        buttonAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelDetail = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        buttonBatal = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        textBayar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        textKembalian = new javax.swing.JTextField();
        textStok = new javax.swing.JTextField();
        textPelanggan = new javax.swing.JTextField();
        textDateTime = new javax.swing.JTextField();
        cmbIdObat = new javax.swing.JComboBox<>();
        buttonStruk = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setBackground(new java.awt.Color(0, 0, 51));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Sales-by-payment-method_25410.png"))); // NOI18N
        jLabel1.setText("FORM TRANSAKSI PENJUALAN");

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No Faktur");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tanggal");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pelanggan");

        cmbPelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Pelanggan--" }));
        cmbPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPelangganMouseClicked(evt);
            }
        });
        cmbPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPelangganActionPerformed(evt);
            }
        });

        textTotal.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total Pembelian (Rp)");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nama Obat");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID Obat");

        textNama.setText("press Enter");
        textNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textNamaKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Harga Jual");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Qty");

        textQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textQtyKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sub Total");

        textSubTotal.setText("press Enter");
        textSubTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textSubTotalKeyPressed(evt);
            }
        });

        buttonAdd.setBackground(new java.awt.Color(0, 0, 51));
        buttonAdd.setForeground(new java.awt.Color(255, 255, 255));
        buttonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon add.png"))); // NOI18N
        buttonAdd.setText("ADD");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        tabelDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelDetail);

        buttonBatal.setBackground(new java.awt.Color(0, 0, 51));
        buttonBatal.setForeground(new java.awt.Color(255, 255, 255));
        buttonBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon cancel.png"))); // NOI18N
        buttonBatal.setText("Batal");
        buttonBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBatalActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Bayar");

        textBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBayarKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Kembalian");

        textKembalian.setText("press Enter");
        textKembalian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textKembalianKeyPressed(evt);
            }
        });

        cmbIdObat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Obat--" }));

        buttonStruk.setBackground(new java.awt.Color(0, 0, 51));
        buttonStruk.setForeground(new java.awt.Color(255, 255, 255));
        buttonStruk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon cetak.png"))); // NOI18N
        buttonStruk.setText("Struk");
        buttonStruk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStrukActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(0, 0, 51));
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon save.png"))); // NOI18N
        saveButton.setText("Simpan");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cmbIdObat, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(textJual, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(textQty, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 91, Short.MAX_VALUE))
                    .addComponent(textSubTotal))
                .addGap(18, 18, 18)
                .addComponent(buttonAdd)
                .addGap(39, 39, 39))
            .addComponent(jScrollPane1)
            .addComponent(jSeparator2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textBayar)
                    .addComponent(textKembalian, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonStruk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textStok)
                    .addComponent(textPelanggan)
                    .addComponent(textDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonBatal)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbIdObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonBatal)
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(textBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(textKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonStruk, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(textPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        // TODO add your handling code here:
            tambahDetail();
            loadData();
            //cekStok();
    }//GEN-LAST:event_buttonAddActionPerformed

    private void cmbPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPelangganActionPerformed
        // TODO add your handling code here:
        cariNama();
    }//GEN-LAST:event_cmbPelangganActionPerformed

    private void textQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textQtyKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_textQtyKeyPressed

    private void tabelDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDetailMouseClicked
        // TODO add your handling code here:
        this.cariKode();
        this.showData();
        this.showSisa();
    }//GEN-LAST:event_tabelDetailMouseClicked

    private void textBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBayarKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_textBayarKeyPressed

    private void cmbPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPelangganMouseClicked
        // TODO add your handling code here:
        cariNama();
    }//GEN-LAST:event_cmbPelangganMouseClicked

    private void buttonBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBatalActionPerformed
        // TODO add your handling code here:
        Batal();
        bersihkan();
    }//GEN-LAST:event_buttonBatalActionPerformed

    private void textNamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNamaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {     
            cariId();
        }
    }//GEN-LAST:event_textNamaKeyPressed

    private void textSubTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textSubTotalKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  
        autoSum();
        }
    }//GEN-LAST:event_textSubTotalKeyPressed

    private void buttonStrukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStrukActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/aplikasi_penjualan", "root", ""); 
            String fileName = "D:\\Hasil UTS yang benar\\ALGO2-TIFRP20C-SyahniSahlaFatimah-UTS\\src\\GUI\\reportPenjualan.jrxml";
            String template = "D:\\Hasil UTS yang benar\\ALGO2-TIFRP20C-SyahniSahlaFatimah-UTS\\src\\GUI\\reportPenjualan.jasper";
            JasperCompileManager.compileReport(fileName);
            Map param = new HashMap();
            JasperFillManager.fillReport(template, param, con);
            JasperPrint jp = JasperFillManager.fillReport(template, param, con);
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Report Gagal, Karena " + ex);
        }
    }//GEN-LAST:event_buttonStrukActionPerformed

    private void textKembalianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textKembalianKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  
        hitungKembali();
        }
    }//GEN-LAST:event_textKembalianKeyPressed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        selesai();
        //cekStok();
    }//GEN-LAST:event_saveButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonBatal;
    private javax.swing.JButton buttonStruk;
    private javax.swing.JComboBox<String> cmbIdObat;
    private javax.swing.JComboBox<String> cmbPelanggan;
    private org.freixas.jcalendar.JCalendarCombo cmbTanggal;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable tabelDetail;
    private javax.swing.JTextField textBayar;
    private javax.swing.JTextField textDateTime;
    private javax.swing.JTextField textFaktur;
    private javax.swing.JTextField textJual;
    private javax.swing.JTextField textKembalian;
    private javax.swing.JTextField textNama;
    private javax.swing.JTextField textPelanggan;
    private javax.swing.JTextField textQty;
    private javax.swing.JTextField textStok;
    private javax.swing.JTextField textSubTotal;
    private javax.swing.JTextField textTotal;
    // End of variables declaration//GEN-END:variables
}
