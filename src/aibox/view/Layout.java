/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aibox.view;

import aibox.utils.Koneksi;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author romad
 */
public class Layout extends javax.swing.JFrame {

    Koneksi koneksi = new Koneksi();

    private DefaultTableModel produkTableModel, pelangganTableModel, transaksiTableModel;
    private String TransaksiID, TransaksiProdukID;
    private ArrayList<Transaksi> TransaksiList = new ArrayList<>();

    private void ProdukPrefixID() {
        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM produk ORDER BY id DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoProduk = r.getString("id").substring(2);
                String PR = "" + (Integer.parseInt(NoProduk) + 1);
                String Nol = "";

                if (PR.length() == 1) {
                    Nol = "000";
                } else if (PR.length() == 2) {
                    Nol = "00";
                } else if (PR.length() == 3) {
                    Nol = "0";
                } else if (PR.length() == 4) {
                    Nol = "";
                }

                ProdukInputID.setText("PR" + Nol + PR);

            } else {
                ProdukInputID.setText("PR0001");
            }
            ProdukInputID.setEnabled(false);
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("ProdukPrefixID error");
        }
    }

    public void ProdukClear() {
        ProdukInputNama.setText("");
        ProdukInputHarga.setText("");
        ProdukInputStok.setText("");
    }

    public void ProdukLoadData() {
        produkTableModel.getDataVector().removeAllElements();
        produkTableModel.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM produk";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[5];
                o[0] = r.getString("id");
                o[1] = r.getString("nama");
                o[2] = r.getString("jenis");
                o[3] = r.getString("harga");
                o[4] = r.getString("stok");

                produkTableModel.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }

    private void PelangganPrefixID() {
        try {
            // Generate random alphanumeric string
            String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
            String numbers = "0123456789";
            String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            int length = 6;

            for(int i = 0; i < length; i++) {
              int index = random.nextInt(alphaNumeric.length());
              char randomChar = alphaNumeric.charAt(index);
              sb.append(randomChar);
            }

            String randomString = sb.toString();
            PelangganInputID.setText(randomString);
            PelangganInputID.setEnabled(false);
        } catch (Exception e) {
            System.out.println("PelangganPrefixID error");
        }
    }

    public void PelangganClear() {
        PelangganInputNama.setText("");
        PelangganInputEmail.setText("");
        PelangganInputTelepon.setText("");
        PelangganInputAlamat.setText("");
        PelangganInputCatatan.setText("");
    }

    public void PelangganLoadData() {
        pelangganTableModel.getDataVector().removeAllElements();
        pelangganTableModel.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM pelanggan";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[3];
                o[0] = r.getString("id");
                o[1] = r.getString("nama");
                o[2] = r.getString("email");

                pelangganTableModel.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }
    
    private void TransaksiPrefixID() {
        try {
            // Generate random alphanumeric string
            String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
            String numbers = "0123456789";
            String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            int length = 10;

            for(int i = 0; i < length; i++) {
              int index = random.nextInt(alphaNumeric.length());
              char randomChar = alphaNumeric.charAt(index);
              sb.append(randomChar);
            }

            String randomString = sb.toString();
            TransaksiID = randomString;
        } catch (Exception e) {
            System.out.println("TransaksiPrefixID error");
        }
    }
    
    private void TransaksiProdukPrefixID() {
        try {
            // Generate random alphanumeric string
            String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
            String numbers = "0123456789";
            String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            int length = 10;

            for(int i = 0; i < length; i++) {
              int index = random.nextInt(alphaNumeric.length());
              char randomChar = alphaNumeric.charAt(index);
              sb.append(randomChar);
            }

            String randomString = sb.toString();
            TransaksiProdukID = randomString;
        } catch (Exception e) {
            System.out.println("TransaksiPrefixID error");
        }
    }
    
    public void TransaksiLoadData() {
        TransaksiInputNamaProduk.removeAllItems();
        TransaksiInputNamaProduk.setSelectedIndex(-1);
        TransaksiInputNamaPelanggan.removeAllItems();
        TransaksiInputNamaPelanggan.setSelectedIndex(-1);
        
        try {
            Connection c = Koneksi.getKoneksi();
            Statement produkS = c.createStatement();
            Statement pelangganS = c.createStatement();

            String produkSql = "SELECT * FROM produk ORDER BY nama";
            ResultSet produkR = produkS.executeQuery(produkSql);
            String pelangganSql = "SELECT * FROM pelanggan ORDER BY nama";
            ResultSet pelangganR = pelangganS.executeQuery(pelangganSql);

            while (produkR.next()) {
                if (produkR.getInt("stok") > 0) {
                    String nama = produkR.getString("nama");
                    TransaksiInputNamaProduk.addItem(nama);
                }
            }
            while (pelangganR.next()) {
                String nama = pelangganR.getString("nama");
                TransaksiInputNamaPelanggan.addItem(nama);
            }
            produkR.close();
            produkS.close();
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }
    
    public void TransaksiKalkulasiRefresh() {
        int TotalPembayaran = 0;
        transaksiTableModel.getDataVector().removeAllElements();
        transaksiTableModel.fireTableDataChanged();
        for (Transaksi transaksi: TransaksiList) {
            Object[] o = new Object[3];
            o[0] = transaksi.NamaProduk;
            o[1] = transaksi.Jumlah;
            o[2] = transaksi.TotalHarga;
            transaksiTableModel.addRow(o);
            TotalPembayaran += transaksi.TotalHarga;
        }
        
        TransaksiInputTotalPembayaran.setText(Integer.toString(TotalPembayaran));
    }

    public Layout() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        // Init Produk
        produkTableModel = new DefaultTableModel();
        ProdukDatatable.setModel(produkTableModel);
        produkTableModel.addColumn("ID");
        produkTableModel.addColumn("Nama");
        produkTableModel.addColumn("Jenis");
        produkTableModel.addColumn("Harga");
        produkTableModel.addColumn("Stok");
        ProdukButtonSimpan.setEnabled(true);
        ProdukButtonEdit.setEnabled(false);
        ProdukButtonBatal.setEnabled(false);
        ProdukButtonHapus.setEnabled(false);
        ProdukLoadData();
        ProdukPrefixID();
        
        // Init Pelanggan
        pelangganTableModel = new DefaultTableModel();
        PelangganDatatable.setModel(pelangganTableModel);
        pelangganTableModel.addColumn("ID");
        pelangganTableModel.addColumn("Nama");
        pelangganTableModel.addColumn("Email");
        PelangganButtonSimpan.setEnabled(true);
        PelangganButtonEdit.setEnabled(false);
        PelangganButtonBatal.setEnabled(false);
        PelangganButtonHapus.setEnabled(false);
        PelangganLoadData();
        PelangganPrefixID();
        
        // Init Transaksi
        transaksiTableModel = new DefaultTableModel();
        TransaksiDatatable.setModel(transaksiTableModel);
        transaksiTableModel.addColumn("Nama Produk");
        transaksiTableModel.addColumn("Jumlah");
        transaksiTableModel.addColumn("Total Harga");
        TransaksiInputTotalPembayaran.setEnabled(false);
        TransaksiInputTotalPembayaran.setText("0");
        TransaksiInputKembali.setEnabled(false);
        TransaksiInputKembali.setText("0");
        TransaksiInputBayar.setText("0");
        TransaksiLoadData();
        TransaksiPrefixID();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Body = new javax.swing.JPanel();
        Sidebar = new javax.swing.JPanel();
        LogoText = new javax.swing.JLabel();
        LogoImg = new javax.swing.JLabel();
        MenuProduk = new javax.swing.JLabel();
        MenuPelanggan = new javax.swing.JLabel();
        MenuTransaksi = new javax.swing.JLabel();
        MenuProdukSeparatorBottom = new javax.swing.JSeparator();
        MenuProdukSeparatorTop = new javax.swing.JSeparator();
        MenuTransaksiSeparatorBottom = new javax.swing.JSeparator();
        MenuPelangganSeparatorBottom1 = new javax.swing.JSeparator();
        Content = new javax.swing.JTabbedPane();
        ManajemenProduk = new javax.swing.JPanel();
        ProdukPanelHeader = new javax.swing.JPanel();
        ProdukPanelHeaderLabel = new javax.swing.JLabel();
        ProdukLabelID = new javax.swing.JLabel();
        ProdukInputID = new javax.swing.JTextField();
        ProdukLabelNama = new javax.swing.JLabel();
        ProdukInputNama = new javax.swing.JTextField();
        ProdukLabelJenis = new javax.swing.JLabel();
        ProdukInputJenis = new javax.swing.JComboBox();
        ProdukLabelHarga = new javax.swing.JLabel();
        ProdukInputHarga = new javax.swing.JTextField();
        ProdukLabelPencarian = new javax.swing.JLabel();
        ProdukInputPencarian = new javax.swing.JTextField();
        ProdukDatatableOverflow = new javax.swing.JScrollPane();
        ProdukDatatable = new javax.swing.JTable();
        ProdukButtonSimpan = new javax.swing.JButton();
        ProdukButtonEdit = new javax.swing.JButton();
        ProdukButtonHapus = new javax.swing.JButton();
        ProdukButtonBatal = new javax.swing.JButton();
        ProdukLabelStok = new javax.swing.JLabel();
        ProdukInputStok = new javax.swing.JTextField();
        ManajemenPelanggan = new javax.swing.JPanel();
        PelangganPanelHeader = new javax.swing.JPanel();
        PelangganPanelHeaderLabel = new javax.swing.JLabel();
        PelangganLabelID = new javax.swing.JLabel();
        PelangganInputID = new javax.swing.JTextField();
        PelangganLabelNama = new javax.swing.JLabel();
        PelangganInputNama = new javax.swing.JTextField();
        PelangganLabelEmail = new javax.swing.JLabel();
        PelangganInputEmail = new javax.swing.JTextField();
        PelangganInputTelepon = new javax.swing.JTextField();
        PelangganLabelTelepon = new javax.swing.JLabel();
        PelangganLabelAlamat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PelangganInputAlamat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        PelangganInputCatatan = new javax.swing.JTextArea();
        PelangganLabelAlamat1 = new javax.swing.JLabel();
        PelangganLabelPencarian = new javax.swing.JLabel();
        PelangganInputPencarian = new javax.swing.JTextField();
        PelangganDatatableOverflow = new javax.swing.JScrollPane();
        PelangganDatatable = new javax.swing.JTable();
        PelangganButtonSimpan = new javax.swing.JButton();
        PelangganButtonEdit = new javax.swing.JButton();
        PelangganButtonHapus = new javax.swing.JButton();
        PelangganButtonBatal = new javax.swing.JButton();
        Transaksi = new javax.swing.JPanel();
        TransaksiPanelHeader = new javax.swing.JPanel();
        TransaksiPanelHeaderLabel = new javax.swing.JLabel();
        TransaksiLabelNamaProduk = new javax.swing.JLabel();
        TransaksiInputNamaProduk = new javax.swing.JComboBox<>();
        TransaksiLabelJumlah = new javax.swing.JLabel();
        TransaksiInputJumlah = new javax.swing.JTextField();
        TransaksiButtonTambahProduk = new javax.swing.JButton();
        TransaksiButtonHapusProduk = new javax.swing.JButton();
        TransaksiDatatableOverflow = new javax.swing.JScrollPane();
        TransaksiDatatable = new javax.swing.JTable();
        TransaksiLabelTotalPembayaran = new javax.swing.JLabel();
        TransaksiInputTotalPembayaran = new javax.swing.JTextField();
        TransaksiLabelBayar = new javax.swing.JLabel();
        TransaksiInputBayar = new javax.swing.JTextField();
        TransaksiLabelKembali = new javax.swing.JLabel();
        TransaksiInputKembali = new javax.swing.JTextField();
        TransaksiLabelNamaPelanggan = new javax.swing.JLabel();
        TransaksiInputNamaPelanggan = new javax.swing.JComboBox<>();
        TransaksiButtonKonfirmasiTransaksi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TransaksiPrintArea = new javax.swing.JTextArea();
        TransaksiButtonPrintStrukPembayaran = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Body.setBackground(new java.awt.Color(204, 204, 204));

        Sidebar.setBackground(new java.awt.Color(0, 0, 0));
        Sidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogoText.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        LogoText.setForeground(new java.awt.Color(255, 255, 255));
        LogoText.setText("AIBOX");
        Sidebar.add(LogoText, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 82, 91, -1));

        LogoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/aibox-logo-white.png"))); // NOI18N
        LogoImg.setText("jLabel2");
        Sidebar.add(LogoImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 29, 52, 41));

        MenuProduk.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        MenuProduk.setForeground(new java.awt.Color(255, 255, 255));
        MenuProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-product.png"))); // NOI18N
        MenuProduk.setText("  PRODUK");
        MenuProduk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuProdukMouseClicked(evt);
            }
        });
        Sidebar.add(MenuProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 192, 209, 34));

        MenuPelanggan.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        MenuPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        MenuPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-pelanggan.png"))); // NOI18N
        MenuPelanggan.setText("  PELANGGAN");
        MenuPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuPelangganMouseClicked(evt);
            }
        });
        Sidebar.add(MenuPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 209, 34));

        MenuTransaksi.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        MenuTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        MenuTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-transaksi.png"))); // NOI18N
        MenuTransaksi.setText("  TRANSAKSI");
        MenuTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuTransaksiMouseClicked(evt);
            }
        });
        Sidebar.add(MenuTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 209, 34));
        Sidebar.add(MenuProdukSeparatorBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 238, 234, 10));
        Sidebar.add(MenuProdukSeparatorTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 177, 234, 9));
        Sidebar.add(MenuTransaksiSeparatorBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 234, 10));
        Sidebar.add(MenuPelangganSeparatorBottom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 228, 10));

        Content.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        Content.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        ManajemenProduk.setBackground(new java.awt.Color(241, 241, 241));
        ManajemenProduk.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        ProdukPanelHeader.setBackground(new java.awt.Color(0, 0, 0));

        ProdukPanelHeaderLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        ProdukPanelHeaderLabel.setForeground(new java.awt.Color(255, 255, 255));
        ProdukPanelHeaderLabel.setText("MANAJEMEN PRODUK");

        javax.swing.GroupLayout ProdukPanelHeaderLayout = new javax.swing.GroupLayout(ProdukPanelHeader);
        ProdukPanelHeader.setLayout(ProdukPanelHeaderLayout);
        ProdukPanelHeaderLayout.setHorizontalGroup(
            ProdukPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdukPanelHeaderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(ProdukPanelHeaderLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProdukPanelHeaderLayout.setVerticalGroup(
            ProdukPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProdukPanelHeaderLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(ProdukPanelHeaderLabel)
                .addGap(19, 19, 19))
        );

        ProdukLabelID.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukLabelID.setText("ID *");

        ProdukInputID.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        ProdukLabelNama.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukLabelNama.setText("Nama *");

        ProdukInputNama.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        ProdukLabelJenis.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukLabelJenis.setText("Kategori *");

        ProdukInputJenis.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukInputJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "iPhone", "iPad", "Macbook", "Lainnya" }));

        ProdukLabelHarga.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukLabelHarga.setText("Harga *");

        ProdukInputHarga.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        ProdukLabelPencarian.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukLabelPencarian.setText("Pencarian");

        ProdukInputPencarian.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukInputPencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ProdukInputPencarianKeyTyped(evt);
            }
        });

        ProdukDatatable.setAutoCreateRowSorter(true);
        ProdukDatatable.setBackground(new java.awt.Color(0, 0, 0));
        ProdukDatatable.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukDatatable.setForeground(new java.awt.Color(255, 255, 255));
        ProdukDatatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Kategori", "Harga", "Stok"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProdukDatatable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProdukDatatable.setRowHeight(30);
        ProdukDatatable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukDatatableMouseClicked(evt);
            }
        });
        ProdukDatatableOverflow.setViewportView(ProdukDatatable);

        ProdukButtonSimpan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukButtonSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-simpan.png"))); // NOI18N
        ProdukButtonSimpan.setText(" Simpan");
        ProdukButtonSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProdukButtonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProdukButtonSimpanActionPerformed(evt);
            }
        });

        ProdukButtonEdit.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukButtonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-edit.png"))); // NOI18N
        ProdukButtonEdit.setText(" Edit");
        ProdukButtonEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProdukButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProdukButtonEditActionPerformed(evt);
            }
        });

        ProdukButtonHapus.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukButtonHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-hapus.png"))); // NOI18N
        ProdukButtonHapus.setText(" Hapus");
        ProdukButtonHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProdukButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProdukButtonHapusActionPerformed(evt);
            }
        });

        ProdukButtonBatal.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukButtonBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-batal.png"))); // NOI18N
        ProdukButtonBatal.setText(" Batal");
        ProdukButtonBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProdukButtonBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProdukButtonBatalActionPerformed(evt);
            }
        });

        ProdukLabelStok.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProdukLabelStok.setText("Stok *");

        ProdukInputStok.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        javax.swing.GroupLayout ManajemenProdukLayout = new javax.swing.GroupLayout(ManajemenProduk);
        ManajemenProduk.setLayout(ManajemenProdukLayout);
        ManajemenProdukLayout.setHorizontalGroup(
            ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProdukPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ManajemenProdukLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ManajemenProdukLayout.createSequentialGroup()
                        .addComponent(ProdukButtonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ProdukButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ManajemenProdukLayout.createSequentialGroup()
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProdukLabelNama)
                            .addComponent(ProdukLabelID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProdukInputNama, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(ProdukInputID)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ManajemenProdukLayout.createSequentialGroup()
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProdukLabelHarga)
                            .addComponent(ProdukLabelJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProdukInputHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(ProdukInputJenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ManajemenProdukLayout.createSequentialGroup()
                        .addComponent(ProdukLabelStok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ProdukInputStok, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ManajemenProdukLayout.createSequentialGroup()
                        .addComponent(ProdukDatatableOverflow, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(ManajemenProdukLayout.createSequentialGroup()
                        .addComponent(ProdukLabelPencarian)
                        .addGap(12, 12, 12)
                        .addComponent(ProdukInputPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ProdukButtonBatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ProdukButtonHapus)
                        .addContainerGap())))
        );
        ManajemenProdukLayout.setVerticalGroup(
            ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManajemenProdukLayout.createSequentialGroup()
                .addComponent(ProdukPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ManajemenProdukLayout.createSequentialGroup()
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ProdukInputPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ProdukButtonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ProdukButtonBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ProdukLabelPencarian))
                        .addGap(18, 18, 18)
                        .addComponent(ProdukDatatableOverflow, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
                    .addGroup(ManajemenProdukLayout.createSequentialGroup()
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProdukInputID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ProdukLabelID))
                        .addGap(10, 10, 10)
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProdukInputNama, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ProdukLabelNama))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProdukInputJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ProdukLabelJenis))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProdukInputHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ProdukLabelHarga))
                        .addGap(18, 18, 18)
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProdukInputStok, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ProdukLabelStok))
                        .addGap(37, 37, 37)
                        .addGroup(ManajemenProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProdukButtonSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(ProdukButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        Content.addTab("PRODUK", ManajemenProduk);

        ManajemenPelanggan.setBackground(new java.awt.Color(241, 241, 241));
        ManajemenPelanggan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        PelangganPanelHeader.setBackground(new java.awt.Color(0, 0, 0));

        PelangganPanelHeaderLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        PelangganPanelHeaderLabel.setForeground(new java.awt.Color(255, 255, 255));
        PelangganPanelHeaderLabel.setText("DATA PELANGGAN");

        javax.swing.GroupLayout PelangganPanelHeaderLayout = new javax.swing.GroupLayout(PelangganPanelHeader);
        PelangganPanelHeader.setLayout(PelangganPanelHeaderLayout);
        PelangganPanelHeaderLayout.setHorizontalGroup(
            PelangganPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PelangganPanelHeaderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(PelangganPanelHeaderLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PelangganPanelHeaderLayout.setVerticalGroup(
            PelangganPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PelangganPanelHeaderLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(PelangganPanelHeaderLabel)
                .addGap(19, 19, 19))
        );

        PelangganLabelID.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganLabelID.setText("ID *");

        PelangganInputID.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        PelangganLabelNama.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganLabelNama.setText("Nama *");

        PelangganInputNama.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        PelangganLabelEmail.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganLabelEmail.setText("Email");

        PelangganInputEmail.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        PelangganInputTelepon.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        PelangganLabelTelepon.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganLabelTelepon.setText("Telepon");

        PelangganLabelAlamat.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganLabelAlamat.setText("Alamat");

        PelangganInputAlamat.setColumns(20);
        PelangganInputAlamat.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganInputAlamat.setRows(5);
        jScrollPane1.setViewportView(PelangganInputAlamat);

        PelangganInputCatatan.setColumns(20);
        PelangganInputCatatan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganInputCatatan.setRows(5);
        jScrollPane2.setViewportView(PelangganInputCatatan);

        PelangganLabelAlamat1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganLabelAlamat1.setText("Catatan");

        PelangganLabelPencarian.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganLabelPencarian.setText("Pencarian");

        PelangganInputPencarian.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganInputPencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PelangganInputPencarianKeyTyped(evt);
            }
        });

        PelangganDatatable.setAutoCreateRowSorter(true);
        PelangganDatatable.setBackground(new java.awt.Color(0, 0, 0));
        PelangganDatatable.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganDatatable.setForeground(new java.awt.Color(255, 255, 255));
        PelangganDatatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nama", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PelangganDatatable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PelangganDatatable.setRowHeight(30);
        PelangganDatatable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PelangganDatatableMouseClicked(evt);
            }
        });
        PelangganDatatableOverflow.setViewportView(PelangganDatatable);

        PelangganButtonSimpan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganButtonSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-simpan.png"))); // NOI18N
        PelangganButtonSimpan.setText(" Simpan");
        PelangganButtonSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PelangganButtonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PelangganButtonSimpanActionPerformed(evt);
            }
        });

        PelangganButtonEdit.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganButtonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-edit.png"))); // NOI18N
        PelangganButtonEdit.setText(" Edit");
        PelangganButtonEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PelangganButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PelangganButtonEditActionPerformed(evt);
            }
        });

        PelangganButtonHapus.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganButtonHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-hapus.png"))); // NOI18N
        PelangganButtonHapus.setText(" Hapus");
        PelangganButtonHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PelangganButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PelangganButtonHapusActionPerformed(evt);
            }
        });

        PelangganButtonBatal.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PelangganButtonBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-batal.png"))); // NOI18N
        PelangganButtonBatal.setText(" Batal");
        PelangganButtonBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PelangganButtonBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PelangganButtonBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ManajemenPelangganLayout = new javax.swing.GroupLayout(ManajemenPelanggan);
        ManajemenPelanggan.setLayout(ManajemenPelangganLayout);
        ManajemenPelangganLayout.setHorizontalGroup(
            ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PelangganPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ManajemenPelangganLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ManajemenPelangganLayout.createSequentialGroup()
                        .addComponent(PelangganButtonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PelangganButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ManajemenPelangganLayout.createSequentialGroup()
                        .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PelangganLabelTelepon)
                            .addComponent(PelangganLabelAlamat)
                            .addComponent(PelangganLabelAlamat1)
                            .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(PelangganLabelNama, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                .addComponent(PelangganLabelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PelangganLabelID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(PelangganInputTelepon)
                            .addComponent(PelangganInputEmail)
                            .addComponent(PelangganInputNama)
                            .addComponent(PelangganInputID))))
                .addGap(18, 18, 18)
                .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ManajemenPelangganLayout.createSequentialGroup()
                        .addComponent(PelangganLabelPencarian)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PelangganInputPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(PelangganButtonBatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PelangganButtonHapus))
                    .addComponent(PelangganDatatableOverflow, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        ManajemenPelangganLayout.setVerticalGroup(
            ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManajemenPelangganLayout.createSequentialGroup()
                .addComponent(PelangganPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ManajemenPelangganLayout.createSequentialGroup()
                        .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(PelangganInputPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PelangganButtonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PelangganButtonBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PelangganLabelPencarian))
                        .addGap(18, 18, 18)
                        .addComponent(PelangganDatatableOverflow, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(ManajemenPelangganLayout.createSequentialGroup()
                        .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ManajemenPelangganLayout.createSequentialGroup()
                                .addComponent(PelangganLabelID)
                                .addGap(24, 24, 24)
                                .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PelangganInputNama, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PelangganLabelNama)))
                            .addComponent(PelangganInputID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PelangganInputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PelangganLabelEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PelangganInputTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PelangganLabelTelepon))
                        .addGap(18, 18, 18)
                        .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PelangganLabelAlamat)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PelangganLabelAlamat1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(ManajemenPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PelangganButtonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PelangganButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))))
        );

        Content.addTab("PELANGGAN", ManajemenPelanggan);

        Transaksi.setBackground(new java.awt.Color(241, 241, 241));
        Transaksi.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        TransaksiPanelHeader.setBackground(new java.awt.Color(0, 0, 0));

        TransaksiPanelHeaderLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        TransaksiPanelHeaderLabel.setForeground(new java.awt.Color(255, 255, 255));
        TransaksiPanelHeaderLabel.setText("TRANSAKSI");

        javax.swing.GroupLayout TransaksiPanelHeaderLayout = new javax.swing.GroupLayout(TransaksiPanelHeader);
        TransaksiPanelHeader.setLayout(TransaksiPanelHeaderLayout);
        TransaksiPanelHeaderLayout.setHorizontalGroup(
            TransaksiPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiPanelHeaderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(TransaksiPanelHeaderLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TransaksiPanelHeaderLayout.setVerticalGroup(
            TransaksiPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiPanelHeaderLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(TransaksiPanelHeaderLabel)
                .addGap(19, 19, 19))
        );

        TransaksiLabelNamaProduk.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiLabelNamaProduk.setText("Nama Produk *");

        TransaksiInputNamaProduk.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        TransaksiLabelJumlah.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiLabelJumlah.setText("Jumlah *");

        TransaksiInputJumlah.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        TransaksiButtonTambahProduk.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiButtonTambahProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-simpan.png"))); // NOI18N
        TransaksiButtonTambahProduk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TransaksiButtonTambahProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransaksiButtonTambahProdukActionPerformed(evt);
            }
        });

        TransaksiButtonHapusProduk.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiButtonHapusProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-hapus.png"))); // NOI18N
        TransaksiButtonHapusProduk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TransaksiButtonHapusProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransaksiButtonHapusProdukActionPerformed(evt);
            }
        });

        TransaksiDatatable.setAutoCreateRowSorter(true);
        TransaksiDatatable.setBackground(new java.awt.Color(0, 0, 0));
        TransaksiDatatable.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiDatatable.setForeground(new java.awt.Color(255, 255, 255));
        TransaksiDatatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama Produk", "Jumlah", "Total Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TransaksiDatatable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TransaksiDatatable.setRowHeight(30);
        TransaksiDatatableOverflow.setViewportView(TransaksiDatatable);

        TransaksiLabelTotalPembayaran.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiLabelTotalPembayaran.setText("Total Pembayaran");

        TransaksiInputTotalPembayaran.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        TransaksiLabelBayar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiLabelBayar.setText("Bayar");

        TransaksiInputBayar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiInputBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TransaksiInputBayarKeyReleased(evt);
            }
        });

        TransaksiLabelKembali.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiLabelKembali.setText("Kembali");

        TransaksiInputKembali.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        TransaksiLabelNamaPelanggan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiLabelNamaPelanggan.setText("Nama Pelanggan");

        TransaksiInputNamaPelanggan.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        TransaksiButtonKonfirmasiTransaksi.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiButtonKonfirmasiTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-simpan.png"))); // NOI18N
        TransaksiButtonKonfirmasiTransaksi.setText(" Konfirmasi Transaksi");
        TransaksiButtonKonfirmasiTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TransaksiButtonKonfirmasiTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransaksiButtonKonfirmasiTransaksiActionPerformed(evt);
            }
        });

        TransaksiPrintArea.setColumns(20);
        TransaksiPrintArea.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiPrintArea.setRows(5);
        jScrollPane3.setViewportView(TransaksiPrintArea);

        TransaksiButtonPrintStrukPembayaran.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        TransaksiButtonPrintStrukPembayaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aibox/img/ic-simpan.png"))); // NOI18N
        TransaksiButtonPrintStrukPembayaran.setText(" Print Struk Pembayaran");
        TransaksiButtonPrintStrukPembayaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TransaksiButtonPrintStrukPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransaksiButtonPrintStrukPembayaranActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TransaksiLayout = new javax.swing.GroupLayout(Transaksi);
        Transaksi.setLayout(TransaksiLayout);
        TransaksiLayout.setHorizontalGroup(
            TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TransaksiPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TransaksiLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TransaksiDatatableOverflow)
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addComponent(TransaksiInputNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TransaksiLabelJumlah)
                            .addGroup(TransaksiLayout.createSequentialGroup()
                                .addComponent(TransaksiInputJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TransaksiButtonTambahProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TransaksiButtonHapusProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TransaksiLabelKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TransaksiInputKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TransaksiInputBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(TransaksiButtonKonfirmasiTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TransaksiLabelBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TransaksiLabelNamaProduk))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TransaksiInputTotalPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TransaksiLabelTotalPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TransaksiLabelNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TransaksiInputNamaPelanggan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TransaksiButtonPrintStrukPembayaran))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        TransaksiLayout.setVerticalGroup(
            TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiLayout.createSequentialGroup()
                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TransaksiLabelNamaPelanggan)
                        .addGap(38, 38, 38)
                        .addComponent(TransaksiLabelBayar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(TransaksiLayout.createSequentialGroup()
                                .addComponent(TransaksiInputBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TransaksiLabelKembali)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TransaksiInputKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TransaksiButtonKonfirmasiTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(TransaksiLayout.createSequentialGroup()
                        .addComponent(TransaksiPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(TransaksiLayout.createSequentialGroup()
                                .addComponent(TransaksiLabelNamaProduk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TransaksiInputNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TransaksiLayout.createSequentialGroup()
                                .addComponent(TransaksiLabelJumlah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TransaksiButtonTambahProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TransaksiInputJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(TransaksiButtonHapusProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TransaksiButtonPrintStrukPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransaksiLayout.createSequentialGroup()
                                .addComponent(TransaksiDatatableOverflow, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TransaksiLabelTotalPembayaran)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TransaksiInputTotalPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TransaksiInputNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
        );

        Content.addTab("TRANSAKSI", Transaksi);

        javax.swing.GroupLayout BodyLayout = new javax.swing.GroupLayout(Body);
        Body.setLayout(BodyLayout);
        BodyLayout.setHorizontalGroup(
            BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BodyLayout.createSequentialGroup()
                .addComponent(Sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Content, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        BodyLayout.setVerticalGroup(
            BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );

        Content.getAccessibleContext().setAccessibleName("Content");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProdukInputPencarianKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProdukInputPencarianKeyTyped
        // TODO add your handling code here:
        DefaultTableModel tabel = new DefaultTableModel();

        tabel.addColumn("ID");
        tabel.addColumn("Nama");
        tabel.addColumn("Jenis");
        tabel.addColumn("Harga");
        tabel.addColumn("Stok");

        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "Select * FROM produk WHERE nama LIKE '%" + ProdukInputPencarian.getText() + "%'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                tabel.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                });
            }
            ProdukDatatable.setModel(tabel);
            ProdukLoadData();
        } catch (Exception e) {
            System.out.println("Cari data error");
        } finally {}
    }//GEN-LAST:event_ProdukInputPencarianKeyTyped

    private void ProdukDatatableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukDatatableMouseClicked
        // TODO add your handling code here:
        ProdukButtonSimpan.setEnabled(false);
        ProdukButtonEdit.setEnabled(true);
        ProdukButtonBatal.setEnabled(true);
        ProdukButtonHapus.setEnabled(true);
        int i = ProdukDatatable.getSelectedRow();
        if (i == -1) {
            return;
        }

        String id = (String) produkTableModel.getValueAt(i, 0);
        ProdukInputID.setText(id);
        String nama = (String) produkTableModel.getValueAt(i, 1);
        ProdukInputNama.setText(nama);
        String jenis = (String) produkTableModel.getValueAt(i, 2);
        ProdukInputJenis.setSelectedItem(jenis);
        String harga = (String) produkTableModel.getValueAt(i, 3);
        ProdukInputHarga.setText(harga);
        String stok = (String) produkTableModel.getValueAt(i, 4);
        ProdukInputStok.setText(stok);
    }//GEN-LAST:event_ProdukDatatableMouseClicked

    private void ProdukButtonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProdukButtonSimpanActionPerformed
        // TODO add your handling code here:
        String id = ProdukInputID.getText();
        String nama = ProdukInputNama.getText();
        String jenis = (String) ProdukInputJenis.getSelectedItem();
        String harga = ProdukInputHarga.getText();
        String stok = ProdukInputStok.getText();

        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "INSERT INTO produk VALUES (?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id);
            p.setString(2, nama);
            p.setString(3, jenis);
            p.setString(4, harga);
            p.setString(5, stok);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan");
        } finally {
            ProdukLoadData();
            ProdukPrefixID();
            ProdukClear();
        }
    }//GEN-LAST:event_ProdukButtonSimpanActionPerformed

    private void ProdukButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProdukButtonEditActionPerformed
        // TODO add your handling code here:
        int i = ProdukDatatable.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) produkTableModel.getValueAt(i, 0);
        String nama = ProdukInputNama.getText();
        String jenis = (String) ProdukInputJenis.getSelectedItem();
        String harga = ProdukInputHarga.getText();
        String stok = ProdukInputStok.getText();

        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "UPDATE produk SET nama = ?, jenis = ?, harga = ?, stok = ? WHERE id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, nama);
            p.setString(2, jenis);
            p.setString(3, harga);
            p.setString(4, stok);
            p.setString(5, id);

            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
            ProdukButtonSimpan.setEnabled(true);
            ProdukClear();
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan");
        } finally {
            ProdukLoadData();
            ProdukPrefixID();
        }
    }//GEN-LAST:event_ProdukButtonEditActionPerformed

    private void ProdukButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProdukButtonHapusActionPerformed
        // TODO add your handling code here:
        int i = ProdukDatatable.getSelectedRow();
        if (i == -1) {
            return;
        }

        String id = (String) produkTableModel.getValueAt(i, 0);

        int question = JOptionPane.showConfirmDialog(null, "Apakah kamu yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (question == JOptionPane.OK_OPTION) {
            try {
                Connection c = Koneksi.getKoneksi();
                String sql = "DELETE FROM produk WHERE id = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, id);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                ProdukClear();
                ProdukLoadData();
                ProdukButtonSimpan.setEnabled(true);
                ProdukPrefixID();
            } catch (SQLException e) {
                System.out.println("Terjadi kesalahan");
            } finally {
                ProdukLoadData();
                ProdukPrefixID();
                ProdukClear();
            }
        }
        if (question == JOptionPane.CANCEL_OPTION) {}
    }//GEN-LAST:event_ProdukButtonHapusActionPerformed

    private void ProdukButtonBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProdukButtonBatalActionPerformed
        // TODO add your handling code here:
        ProdukClear();
        ProdukLoadData();
        ProdukButtonSimpan.setEnabled(true);
        ProdukPrefixID();
    }//GEN-LAST:event_ProdukButtonBatalActionPerformed

    private void PelangganInputPencarianKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PelangganInputPencarianKeyTyped
       // TODO add your handling code here:
        DefaultTableModel tabel = new DefaultTableModel();

        tabel.addColumn("ID");
        tabel.addColumn("Nama");
        tabel.addColumn("Email");

        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "Select * FROM pelanggan WHERE nama LIKE '%" + PelangganInputPencarian.getText() + "%'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                tabel.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
                });
            }
            PelangganDatatable.setModel(tabel);
            PelangganLoadData();
        } catch (Exception e) {
            System.out.println("Cari data error");
        } finally {}
    }//GEN-LAST:event_PelangganInputPencarianKeyTyped

    private void PelangganDatatableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PelangganDatatableMouseClicked
        // TODO add your handling code here:
        PelangganButtonSimpan.setEnabled(false);
        PelangganButtonEdit.setEnabled(true);
        PelangganButtonBatal.setEnabled(true);
        PelangganButtonHapus.setEnabled(true);
        int i = PelangganDatatable.getSelectedRow();
        if (i == -1) {
            return;
        }

        String id = (String) pelangganTableModel.getValueAt(i, 0);
        PelangganInputID.setText(id);
        String nama = (String) pelangganTableModel.getValueAt(i, 1);
        PelangganInputNama.setText(nama);
        String email = (String) pelangganTableModel.getValueAt(i, 2);
        PelangganInputEmail.setText(email);
        
        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "Select * FROM pelanggan WHERE id = '"+ id + "'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            
            while (rs.next()) {
                PelangganInputTelepon.setText(rs.getString(4));
                PelangganInputAlamat.setText(rs.getString(5));
                PelangganInputCatatan.setText(rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {}
    }//GEN-LAST:event_PelangganDatatableMouseClicked

    private void PelangganButtonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PelangganButtonSimpanActionPerformed
        // TODO add your handling code here:
        String id = (String) PelangganInputID.getText();
        String nama = PelangganInputNama.getText();
        String email = PelangganInputEmail.getText();
        String telepon = PelangganInputTelepon.getText();
        String alamat = PelangganInputAlamat.getText();
        String catatan = PelangganInputCatatan.getText();

        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "INSERT INTO pelanggan VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id);
            p.setString(2, nama);
            p.setString(3, email);
            p.setString(4, telepon);
            p.setString(5, alamat);
            p.setString(6, catatan);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan");
        } finally {
            PelangganLoadData();
            PelangganPrefixID();
            PelangganClear();
        }
    }//GEN-LAST:event_PelangganButtonSimpanActionPerformed

    private void PelangganButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PelangganButtonEditActionPerformed
        // TODO add your handling code here:
        int i = PelangganDatatable.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) pelangganTableModel.getValueAt(i, 0);
        String nama = PelangganInputNama.getText();
        String email = (String) PelangganInputEmail.getText();
        String telepon = PelangganInputTelepon.getText();
        String alamat = PelangganInputAlamat.getText();
        String catatan = PelangganInputCatatan.getText();

        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "UPDATE pelanggan SET nama = ?, email = ?, telepon = ?, alamat = ?, catatan = ? WHERE id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, nama);
            p.setString(2, email);
            p.setString(3, telepon);
            p.setString(4, alamat);
            p.setString(5, catatan);
            p.setString(6, id);

            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
            PelangganButtonSimpan.setEnabled(true);
            PelangganClear();
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan");
        } finally {
            PelangganLoadData();
            PelangganPrefixID();
        }
    }//GEN-LAST:event_PelangganButtonEditActionPerformed

    private void PelangganButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PelangganButtonHapusActionPerformed
        // TODO add your handling code here:
        int i = PelangganDatatable.getSelectedRow();
        if (i == -1) {
            return;
        }

        String id = (String) pelangganTableModel.getValueAt(i, 0);

        int question = JOptionPane.showConfirmDialog(null, "Apakah kamu yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (question == JOptionPane.OK_OPTION) {
            try {
                Connection c = Koneksi.getKoneksi();
                String sql = "DELETE FROM pelanggan WHERE id = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, id);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                PelangganClear();
                PelangganLoadData();
                PelangganButtonSimpan.setEnabled(true);
                PelangganPrefixID();
            } catch (SQLException e) {
                System.out.println("Terjadi kesalahan");
            } finally {
                PelangganLoadData();
                PelangganPrefixID();
                PelangganClear();
            }
        }
        if (question == JOptionPane.CANCEL_OPTION) {}
    }//GEN-LAST:event_PelangganButtonHapusActionPerformed

    private void PelangganButtonBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PelangganButtonBatalActionPerformed
        // TODO add your handling code here:
        PelangganClear();
        PelangganLoadData();
        PelangganButtonSimpan.setEnabled(true);
        PelangganPrefixID();
    }//GEN-LAST:event_PelangganButtonBatalActionPerformed

    private void MenuProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuProdukMouseClicked
        // TODO add your handling code here:
        Content.setSelectedIndex(0);
        ProdukLoadData();
        ProdukPrefixID();
    }//GEN-LAST:event_MenuProdukMouseClicked

    private void MenuPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuPelangganMouseClicked
        // TODO add your handling code here:
        Content.setSelectedIndex(1);
        PelangganLoadData();
        PelangganPrefixID();
    }//GEN-LAST:event_MenuPelangganMouseClicked

    private void TransaksiButtonTambahProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransaksiButtonTambahProdukActionPerformed
        // TODO add your handling code here:
        String NamaProduk = (String) TransaksiInputNamaProduk.getSelectedItem();
        String Jumlah = TransaksiInputJumlah.getText();
        String IdProduk = "";
        int TotalHarga = 0;
      
        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT id, harga FROM produk WHERE nama='" + NamaProduk + "'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                IdProduk = r.getString("id");
                TotalHarga = Integer.parseInt(Jumlah) * Integer.parseInt(r.getString("harga"));
                TransaksiList.add(new Transaksi(IdProduk, NamaProduk, Jumlah, TotalHarga));
                TransaksiKalkulasiRefresh();
            }
            r.close();
            s.close();
            TransaksiInputJumlah.setText("");
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }//GEN-LAST:event_TransaksiButtonTambahProdukActionPerformed

    private void MenuTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuTransaksiMouseClicked
        // TODO add your handling code here:
        Content.setSelectedIndex(2);
        TransaksiLoadData();
        TransaksiPrefixID();
    }//GEN-LAST:event_MenuTransaksiMouseClicked

    private void TransaksiButtonHapusProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransaksiButtonHapusProdukActionPerformed
        // TODO add your handling code here:
        int i = TransaksiDatatable.getSelectedRow();
        TransaksiList.remove(i);
        TransaksiKalkulasiRefresh();
    }//GEN-LAST:event_TransaksiButtonHapusProdukActionPerformed

    private void TransaksiInputBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TransaksiInputBayarKeyReleased
        // TODO add your handling code here:
        String TotalPembayaran = TransaksiInputTotalPembayaran.getText();
        String Bayar = TransaksiInputBayar.getText();
        
        
        if (Bayar != null && !Bayar.isEmpty() && TotalPembayaran != null && !TotalPembayaran.isEmpty() && Integer.parseInt(Bayar) >= Integer.parseInt(TotalPembayaran)) {
            int Kembali = Integer.parseInt(Bayar) - Integer.parseInt(TotalPembayaran);
            TransaksiInputKembali.setText(String.valueOf(Kembali));
        }
        else {
            TransaksiInputKembali.setText("0");
        }
    }//GEN-LAST:event_TransaksiInputBayarKeyReleased

    private void TransaksiButtonKonfirmasiTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransaksiButtonKonfirmasiTransaksiActionPerformed
        // TODO add your handling code here:
        String IdPelanggan;
        String NamaPelanggan = (String) TransaksiInputNamaPelanggan.getSelectedItem();
        String TotalPembayaran = TransaksiInputTotalPembayaran.getText();
        String Bayar = TransaksiInputBayar.getText();
        String Kembali = TransaksiInputKembali.getText();
        
        LocalDateTime CurrentDateObj = LocalDateTime.now(); 
        DateTimeFormatter FormatDateObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        String CurrentDateTimeFormatted = CurrentDateObj.format(FormatDateObj);
        
        if (Integer.parseInt(Bayar) >= Integer.parseInt(TotalPembayaran)) {
            try {
                Connection c = Koneksi.getKoneksi();
                Statement s = c.createStatement();

                String sql = "SELECT id FROM pelanggan WHERE nama='" + NamaPelanggan + "'";
                ResultSet r = s.executeQuery(sql);

                while (r.next()) {
                    IdPelanggan = r.getString("id");
                    
                    String transaksiSql = "INSERT INTO transaksi VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement transaksiP = c.prepareStatement(transaksiSql);
                    transaksiP.setString(1, TransaksiID);
                    transaksiP.setString(2, IdPelanggan);
                    transaksiP.setString(3, TotalPembayaran);
                    transaksiP.setString(4, Bayar);
                    transaksiP.setString(5, Kembali);
                    transaksiP.setString(6, CurrentDateTimeFormatted);
                    transaksiP.executeUpdate();
                    for (Transaksi transaksi: TransaksiList) {
                        TransaksiProdukPrefixID();
                        String transaksiProdukSql = "INSERT INTO transaksi_produk VALUES (?, ?, ?)";
                        PreparedStatement transaksiProdukP = c.prepareStatement(transaksiProdukSql);
                        transaksiProdukP.setString(1, TransaksiProdukID);
                        transaksiProdukP.setString(2, TransaksiID);
                        transaksiProdukP.setString(3, transaksi.IdProduk);
                        transaksiProdukP.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(null, "Transaksi berhasil.");
                }
                r.close();
                s.close();
                TransaksiPrintArea.append("============================================="+ "\n" +
                "AIBOX STORE\n" +
                "=============================================\n\n" +
                "ID Transaksi \t\t: " + TransaksiID + "\n" +
                "Tanggal \t\t: " + CurrentDateTimeFormatted + "\n" +
                "Nama Pelanggan \t: " + NamaPelanggan + "\n\n" +
                "=============================================" + "\n\n");
                for (Transaksi transaksi: TransaksiList) {
                    TransaksiPrintArea.append(transaksi.NamaProduk + " (x" + transaksi.Jumlah + ")\t" + transaksi.TotalHarga + "\n");
                }
                        
                TransaksiPrintArea.append("\n=============================================" + "\n\n"+
                "Total Pembayaran \t: " + TotalPembayaran + "\n" +
                "Bayar \t\t: " + Bayar + "\n" +
                "Kembali \t\t: " + Kembali + "\n\n" +
                "============================================="
                );      
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Uang yang Anda masukkan kurang! Silahkan cek kembali.");
        }
    }//GEN-LAST:event_TransaksiButtonKonfirmasiTransaksiActionPerformed

    private void TransaksiButtonPrintStrukPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransaksiButtonPrintStrukPembayaranActionPerformed
        // TODO add your handling code here:
        try {
            boolean printed = TransaksiPrintArea.print();
            if (printed) {
                JOptionPane.showMessageDialog(null, "Struk pembayaran berhasil di print!");
            }
            else {
                JOptionPane.showMessageDialog(null, "Struk pembayaran gagal di print!");
            }
        }
        catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_TransaksiButtonPrintStrukPembayaranActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Layout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private javax.swing.JTabbedPane Content;
    private javax.swing.JLabel LogoImg;
    private javax.swing.JLabel LogoText;
    private javax.swing.JPanel ManajemenPelanggan;
    private javax.swing.JPanel ManajemenProduk;
    private javax.swing.JLabel MenuPelanggan;
    private javax.swing.JSeparator MenuPelangganSeparatorBottom1;
    private javax.swing.JLabel MenuProduk;
    private javax.swing.JSeparator MenuProdukSeparatorBottom;
    private javax.swing.JSeparator MenuProdukSeparatorTop;
    private javax.swing.JLabel MenuTransaksi;
    private javax.swing.JSeparator MenuTransaksiSeparatorBottom;
    private javax.swing.JButton PelangganButtonBatal;
    private javax.swing.JButton PelangganButtonEdit;
    private javax.swing.JButton PelangganButtonHapus;
    private javax.swing.JButton PelangganButtonSimpan;
    private javax.swing.JTable PelangganDatatable;
    private javax.swing.JScrollPane PelangganDatatableOverflow;
    private javax.swing.JTextArea PelangganInputAlamat;
    private javax.swing.JTextArea PelangganInputCatatan;
    private javax.swing.JTextField PelangganInputEmail;
    private javax.swing.JTextField PelangganInputID;
    private javax.swing.JTextField PelangganInputNama;
    private javax.swing.JTextField PelangganInputPencarian;
    private javax.swing.JTextField PelangganInputTelepon;
    private javax.swing.JLabel PelangganLabelAlamat;
    private javax.swing.JLabel PelangganLabelAlamat1;
    private javax.swing.JLabel PelangganLabelEmail;
    private javax.swing.JLabel PelangganLabelID;
    private javax.swing.JLabel PelangganLabelNama;
    private javax.swing.JLabel PelangganLabelPencarian;
    private javax.swing.JLabel PelangganLabelTelepon;
    private javax.swing.JPanel PelangganPanelHeader;
    private javax.swing.JLabel PelangganPanelHeaderLabel;
    private javax.swing.JButton ProdukButtonBatal;
    private javax.swing.JButton ProdukButtonEdit;
    private javax.swing.JButton ProdukButtonHapus;
    private javax.swing.JButton ProdukButtonSimpan;
    private javax.swing.JTable ProdukDatatable;
    private javax.swing.JScrollPane ProdukDatatableOverflow;
    private javax.swing.JTextField ProdukInputHarga;
    private javax.swing.JTextField ProdukInputID;
    private javax.swing.JComboBox ProdukInputJenis;
    private javax.swing.JTextField ProdukInputNama;
    private javax.swing.JTextField ProdukInputPencarian;
    private javax.swing.JTextField ProdukInputStok;
    private javax.swing.JLabel ProdukLabelHarga;
    private javax.swing.JLabel ProdukLabelID;
    private javax.swing.JLabel ProdukLabelJenis;
    private javax.swing.JLabel ProdukLabelNama;
    private javax.swing.JLabel ProdukLabelPencarian;
    private javax.swing.JLabel ProdukLabelStok;
    private javax.swing.JPanel ProdukPanelHeader;
    private javax.swing.JLabel ProdukPanelHeaderLabel;
    private javax.swing.JPanel Sidebar;
    private javax.swing.JPanel Transaksi;
    private javax.swing.JButton TransaksiButtonHapusProduk;
    private javax.swing.JButton TransaksiButtonKonfirmasiTransaksi;
    private javax.swing.JButton TransaksiButtonPrintStrukPembayaran;
    private javax.swing.JButton TransaksiButtonTambahProduk;
    private javax.swing.JTable TransaksiDatatable;
    private javax.swing.JScrollPane TransaksiDatatableOverflow;
    private javax.swing.JTextField TransaksiInputBayar;
    private javax.swing.JTextField TransaksiInputJumlah;
    private javax.swing.JTextField TransaksiInputKembali;
    private javax.swing.JComboBox<String> TransaksiInputNamaPelanggan;
    private javax.swing.JComboBox<String> TransaksiInputNamaProduk;
    private javax.swing.JTextField TransaksiInputTotalPembayaran;
    private javax.swing.JLabel TransaksiLabelBayar;
    private javax.swing.JLabel TransaksiLabelJumlah;
    private javax.swing.JLabel TransaksiLabelKembali;
    private javax.swing.JLabel TransaksiLabelNamaPelanggan;
    private javax.swing.JLabel TransaksiLabelNamaProduk;
    private javax.swing.JLabel TransaksiLabelTotalPembayaran;
    private javax.swing.JPanel TransaksiPanelHeader;
    private javax.swing.JLabel TransaksiPanelHeaderLabel;
    private javax.swing.JTextArea TransaksiPrintArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}

class Transaksi {
    public String IdProduk, NamaProduk, Jumlah;
    public int TotalHarga;
    
    Transaksi(String IdProduk, String NamaProduk, String Jumlah, int TotalHarga)
    {
        this.IdProduk = IdProduk;
        this.NamaProduk = NamaProduk;
        this.Jumlah = Jumlah;
        this.TotalHarga = TotalHarga;
    }
}