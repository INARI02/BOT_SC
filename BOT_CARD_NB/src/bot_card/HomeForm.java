/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bot_card;

import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author N ~ N
 */
public class HomeForm extends javax.swing.JFrame {

    private final ConnectJavaCard card = new ConnectJavaCard();
    private DBConnection db;
    /**
     * Creates new form HomeForm
     */
    public HomeForm() {
       initComponents();
       jpnINFO.setVisible(true);
       jpnPIN.setVisible(false);
       jpnSoDu.setVisible(false);
       jpnHISTORY.setVisible(false);
       jpnNapTien.setVisible(false);
       jpnCHECKOUT.setVisible(false);
       
        // Test ID field
       jTextField12.setEnabled(false);
       /*
       jTextField5.setEnabled(false);
       jTextField6.setEnabled(false);
       jTextField7.setEnabled(false);
       jTextField8.setEnabled(false);
       jTextField12.setEnabled(false);
       */
       
       ResponseAPDU responseCheck = card.sendRequest(
               new CommandAPDU(0x00,config.BOTAPPLET.INS_CHECK,0x00,0x00)
       );
       
        if (responseCheck.getSW() == 0x9000) {
            // Field 12 - id
            jTextField12.setText(getDataString(config.BOTAPPLET.P1_OUT_ID));
            card.strID = jTextField12.getText();
            // Field 6 - name
            jTextField6.setText(getDataString(config.BOTAPPLET.P1_OUT_NAME));
            // Field 7 - dob
            jTextField7.setText(getDataString(config.BOTAPPLET.P1_OUT_DOB));
            // Field 8 - address
            jTextField8.setText(getDataString(config.BOTAPPLET.P1_OUT_ADDRESS));
            // Field 5 - Number plate
            jTextField5.setText(getDataString(config.BOTAPPLET.P1_OUT_NUMBER_PLATE));
//                String data = getData();
//                System.out.println("Get Data: " + data);
//
//                // Split the data using the "|" separator
//                String[] fields = data.split(",");
//                if (fields.length == 5) {
//                    jTextField12.setText(fields[0]);
//                    jTextField6.setText(fields[1]);
//                    jTextField7.setText(fields[2]);
//                    jTextField8.setText(fields[3]);
//                    jTextField5.setText(fields[4]);
             // Image
            try {
                card.image = card.fetchImageData();
                System.out.println(Arrays.toString(card.image));
                displayImage(card.image);
            } catch (Exception ex) {
                ex.printStackTrace();
        }
        System.out.println("Chua set data -> card");
    }
        db = new DBConnection();
        
}
    
    public HomeForm(JButton btnCanPIN, JButton btnHuy, JButton btnNapThem1, JButton btnNapTien, JButton btnThoat, JButton btnUP, JButton btnup1, JButton btnupPIN, JLabel jLabel1, JLabel jLabel10, JLabel jLabel12, JLabel jLabel13, JLabel jLabel14, JLabel jLabel15, JLabel jLabel16, JLabel jLabel17, JLabel jLabel18, JLabel jLabel19, JLabel jLabel2, JLabel jLabel20, JLabel jLabel21, JLabel jLabel22, JLabel jLabel23, JLabel jLabel24, JLabel jLabel25, JLabel jLabel3, JLabel jLabel4, JLabel jLabel8, JPanel jPanel1, JPanel jPanel2, JPanel jPanel3, JScrollPane jScrollPane1, JTable jTable1, JTextField jTextField10, JTextField jTextField2, JTextField jTextField3, JTextField jTextField4, JTextField jTextField5, JTextField jTextField6, JTextField jTextField7, JTextField jTextField8, JLabel jlbCancel, JLabel jlbConnect, JLabel jlbHis, JLabel jlbINFO, JLabel jlbNap, JLabel jlbPIN, JLabel jlbSoDu, JPanel jpnConnect, JPanel jpnHISTORY, JPanel jpnINFO, JPanel jpnNapTien, JPanel jpnPIN, JPanel jpnSoDu, JPanel jpnbank1, JPanel jpnhis, JPanel jpnif2, JPanel jpnpin, JPanel jpnsoDu2, JLabel juploadpt1) throws HeadlessException {
        this.btnCanPIN = btnCanPIN;
        this.btnHuy = btnHuy;
        
        this.btnNapThem1 = btnNapThem1;
        this.btnNapTien = btnNapTien;
        this.btnThoat = btnThoat;
        this.btnUP = btnUP;
        this.btnup1 = btnup1;
        this.btnupPIN = btnupPIN;
        this.jLabel1 = jLabel1;
        this.jLabel10 = jLabel10;
        this.jLabel12 = jLabel12;
        this.jLabel13 = jLabel13;
        this.jLabel14 = jLabel14;
        this.jLabel15 = jLabel15;
        this.jLabel16 = jLabel16;
        this.jLabel17 = jLabel17;
        this.jLabel18 = jLabel18;
        this.jLabel19 = jLabel19;
        this.jLabel2 = jLabel2;
        this.jLabel20 = jLabel20;
        this.jLabel21 = jLabel21;
        this.jLabel22 = jLabel22;
        this.jLabel23 = jLabel23;
        this.jLabel24 = jLabel24;
        this.jLabel25 = jLabel25;
        this.jLabel3 = jLabel3;
        this.jLabel4 = jLabel4;
        this.jLabel8 = jLabel8;
        this.jPanel1 = jPanel1;
        this.jPanel2 = jPanel2;
        this.jPanel3 = jPanel3;
        this.jScrollPane1 = jScrollPane1;
        this.jTableHistory = jTable1;
        this.jTextField10 = jTextField10;
        this.jTextField2 = jTextField2;
        this.jTextField3 = jTextField3;
        this.jTextField4 = jTextField4;
        this.jTextField5 = jTextField5;
        this.jTextField6 = jTextField6;
        this.jTextField7 = jTextField7;
        this.jTextField8 = jTextField8;
        
        this.jlbCancel = jlbCancel;
        this.jlbConnect = jlbConnect;
        this.jlbHis = jlbHis;
        this.jlbINFO = jlbINFO;
        this.jlbNap = jlbNap;
        this.jlbPIN = jlbPIN;
        this.jlbSoDu = jlbSoDu;
        this.jpnConnect = jpnConnect;
        this.jpnHISTORY = jpnHISTORY;
        this.jpnINFO = jpnINFO;
        this.jpnNapTien = jpnNapTien;
        this.jpnPIN = jpnPIN;
        this.jpnSoDu = jpnSoDu;
        this.jpnbank1 = jpnbank1;
        this.jpnhis = jpnhis;
        this.jpnif2 = jpnif2;
        this.jpnpin = jpnpin;
        this.jpnsoDu2 = jpnsoDu2;
        this.juploadpt1 = juploadpt1;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jpnpin = new javax.swing.JPanel();
        jlbPIN = new javax.swing.JLabel();
        jpnhis = new javax.swing.JPanel();
        jlbHis = new javax.swing.JLabel();
        jpnif2 = new javax.swing.JPanel();
        jlbINFO = new javax.swing.JLabel();
        jpnConnect = new javax.swing.JPanel();
        jlbConnect = new javax.swing.JLabel();
        jpnsoDu2 = new javax.swing.JPanel();
        jlbSoDu = new javax.swing.JLabel();
        jpnbank1 = new javax.swing.JPanel();
        jlbNap = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpnCheckOut = new javax.swing.JPanel();
        jlbCheckOut = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlbCancel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jpnINFO = new javax.swing.JPanel();
        juploadpt1 = new javax.swing.JLabel();
        btnup1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        btnUP = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jpnPIN = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        btnCanPIN = new javax.swing.JButton();
        btnupPIN = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jpnCHECKOUT = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        btnThoat1 = new javax.swing.JButton();
        btnCheckOut = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jpnSoDu = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();
        btnNapThem1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jlbMone = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jpnNapTien = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        btnHuy = new javax.swing.JButton();
        btnNapTien = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jpnHISTORY = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistory = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dashboard.png"))); // NOI18N
        jLabel1.setText("Dashboard");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cog.png"))); // NOI18N
        jLabel4.setText("Tùy Chọn");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/atm-card.png"))); // NOI18N
        jLabel8.setText("Banking");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jpnpin.setBackground(new java.awt.Color(102, 102, 102));
        jpnpin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbPIN.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlbPIN.setForeground(new java.awt.Color(255, 255, 255));
        jlbPIN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/security.png"))); // NOI18N
        jlbPIN.setText("Mã PIN");
        jlbPIN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbPINMouseClicked(evt);
            }
        });
        jpnpin.add(jlbPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jPanel1.add(jpnpin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 240, 40));

        jpnhis.setBackground(new java.awt.Color(102, 102, 102));
        jpnhis.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbHis.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlbHis.setForeground(new java.awt.Color(255, 255, 255));
        jlbHis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.png"))); // NOI18N
        jlbHis.setText("Lịch sử giao dịch");
        jlbHis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbHisMouseClicked(evt);
            }
        });
        jpnhis.add(jlbHis, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        jPanel1.add(jpnhis, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 240, 40));

        jpnif2.setBackground(new java.awt.Color(102, 102, 102));
        jpnif2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbINFO.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlbINFO.setForeground(new java.awt.Color(255, 255, 255));
        jlbINFO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/teamwork.png"))); // NOI18N
        jlbINFO.setText("Thông Tin");
        jlbINFO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbINFOMouseClicked(evt);
            }
        });
        jpnif2.add(jlbINFO, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        jPanel1.add(jpnif2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 240, 40));

        jpnConnect.setBackground(new java.awt.Color(102, 102, 102));
        jpnConnect.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbConnect.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlbConnect.setForeground(new java.awt.Color(255, 255, 255));
        jlbConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/disruption.png"))); // NOI18N
        jlbConnect.setText("Ngắt kết nối");
        jlbConnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbConnectMouseClicked(evt);
            }
        });
        jpnConnect.add(jlbConnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        jPanel1.add(jpnConnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 240, 40));

        jpnsoDu2.setBackground(new java.awt.Color(102, 102, 102));
        jpnsoDu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnsoDu2MouseClicked(evt);
            }
        });
        jpnsoDu2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbSoDu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlbSoDu.setForeground(new java.awt.Color(255, 255, 255));
        jlbSoDu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/balance.png"))); // NOI18N
        jlbSoDu.setText("Số Dư");
        jlbSoDu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbSoDuMouseClicked(evt);
            }
        });
        jpnsoDu2.add(jlbSoDu, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        jPanel1.add(jpnsoDu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 240, 40));

        jpnbank1.setBackground(new java.awt.Color(102, 102, 102));
        jpnbank1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbNap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlbNap.setForeground(new java.awt.Color(255, 255, 255));
        jlbNap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/mobile-banking.png"))); // NOI18N
        jlbNap.setText("Nạp Tiền");
        jlbNap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbNapMouseClicked(evt);
            }
        });
        jpnbank1.add(jlbNap, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        jPanel1.add(jpnbank1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 240, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jpnCheckOut.setBackground(new java.awt.Color(102, 102, 102));
        jpnCheckOut.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbCheckOut.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlbCheckOut.setForeground(new java.awt.Color(255, 255, 255));
        jlbCheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check-out.png"))); // NOI18N
        jlbCheckOut.setText("Thanh Toán");
        jlbCheckOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbCheckOutMouseClicked(evt);
            }
        });
        jpnCheckOut.add(jlbCheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        jPanel1.add(jpnCheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 240, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 650));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("USER HOME");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 19, 183, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Xin Chào, User");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        jlbCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        jlbCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbCancelMouseClicked(evt);
            }
        });
        jPanel2.add(jlbCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 770, 70));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnINFO.setBackground(new java.awt.Color(255, 255, 255));
        jpnINFO.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        juploadpt1.setBackground(new java.awt.Color(204, 204, 204));
        juploadpt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        juploadpt1.setOpaque(true);
        jpnINFO.add(juploadpt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 170, 200));

        btnup1.setBackground(new java.awt.Color(51, 204, 255));
        btnup1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnup1.setText("Upload Photo");
        btnup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnup1ActionPerformed(evt);
            }
        });
        jpnINFO.add(btnup1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, -1, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel17.setText("THÔNG TIN CÁ NHÂN");
        jpnINFO.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("Biển số xe : ");
        jpnINFO.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jpnINFO.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 320, 40));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("Họ Tên : ");
        jpnINFO.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setText("Ngày sinh :");
        jpnINFO.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setText("Quê Quán :");
        jpnINFO.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jTextField6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jpnINFO.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 320, 40));

        jTextField7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jpnINFO.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 320, 40));

        jTextField8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jpnINFO.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 320, 40));

        btnUP.setBackground(new java.awt.Color(255, 102, 102));
        btnUP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUP.setText("Cập Nhật");
        btnUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUPActionPerformed(evt);
            }
        });
        jpnINFO.add(btnUP, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 150, 40));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setText("ID :");
        jpnINFO.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jTextField12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jpnINFO.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 320, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/winter.png"))); // NOI18N
        jpnINFO.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 110, 110));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/snowball.png"))); // NOI18N
        jpnINFO.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, -1, -1));

        jPanel3.add(jpnINFO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 540));
        jpnINFO.getAccessibleContext().setAccessibleDescription("");

        jpnPIN.setBackground(new java.awt.Color(255, 255, 255));
        jpnPIN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setText("THAY ĐỔI MÃ PIN");
        jpnPIN.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Xác nhận mã pin mới:");
        jpnPIN.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Nhập mã pin mới:");
        jpnPIN.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jpnPIN.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 320, 40));
        jpnPIN.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 320, 40));

        btnCanPIN.setBackground(new java.awt.Color(255, 102, 102));
        btnCanPIN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCanPIN.setText("Hủy Bỏ");
        btnCanPIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanPINActionPerformed(evt);
            }
        });
        jpnPIN.add(btnCanPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, 120, 40));

        btnupPIN.setBackground(new java.awt.Color(255, 102, 102));
        btnupPIN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnupPIN.setText("Cập Nhật");
        btnupPIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupPINActionPerformed(evt);
            }
        });
        jpnPIN.add(btnupPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, 120, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Mã pin cũ :");
        jpnPIN.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 100, -1));
        jpnPIN.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 320, 40));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data-protection.png"))); // NOI18N
        jpnPIN.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        jPanel4.setBackground(new java.awt.Color(153, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpnPIN.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 740, 80));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/folder.png"))); // NOI18N
        jpnPIN.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, -1, -1));

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/key.png"))); // NOI18N
        jPanel9.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jpnPIN.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 80));

        jPanel3.add(jpnPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 540));

        jpnCHECKOUT.setBackground(new java.awt.Color(255, 255, 255));
        jpnCHECKOUT.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel26.setText("THANH TOÁN");
        jpnCHECKOUT.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setText("Nhập số tiền cần thanh toán :");
        jpnCHECKOUT.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 280, 60));

        jTextField11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jpnCHECKOUT.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 370, 70));

        btnThoat1.setBackground(new java.awt.Color(255, 102, 102));
        btnThoat1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThoat1.setText("Thoát");
        btnThoat1.setActionCommand("Xác Nhận");
        btnThoat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoat1ActionPerformed(evt);
            }
        });
        jpnCHECKOUT.add(btnThoat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 410, 120, 50));

        btnCheckOut.setBackground(new java.awt.Color(255, 102, 102));
        btnCheckOut.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCheckOut.setText("Thanh Toán");
        btnCheckOut.setActionCommand("Xác Nhận");
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });
        jpnCHECKOUT.add(btnCheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 140, 50));

        jPanel5.setBackground(new java.awt.Color(102, 204, 255));
        jpnCHECKOUT.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 110));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/self-checkout.png"))); // NOI18N
        jpnCHECKOUT.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, -1, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/credit-card.png"))); // NOI18N
        jLabel34.setToolTipText("");
        jpnCHECKOUT.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jPanel3.add(jpnCHECKOUT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 540));

        jpnSoDu.setBackground(new java.awt.Color(255, 255, 255));
        jpnSoDu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel13.setText("SỐ DƯ");
        jpnSoDu.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel22.setText("Số Dư hiện tại : ");
        jpnSoDu.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 190, 60));

        btnThoat.setBackground(new java.awt.Color(255, 102, 102));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.setActionCommand("Xác Nhận");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jpnSoDu.add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 130, 50));

        btnNapThem1.setBackground(new java.awt.Color(255, 102, 102));
        btnNapThem1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNapThem1.setText("Nạp Thêm");
        btnNapThem1.setActionCommand("Xác Nhận");
        btnNapThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNapThem1ActionPerformed(evt);
            }
        });
        jpnSoDu.add(btnNapThem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 130, 50));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/balance-scale.png"))); // NOI18N
        jLabel11.setToolTipText("");
        jpnSoDu.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, -1, -1));

        jPanel6.setBackground(new java.awt.Color(153, 153, 255));
        jpnSoDu.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 740, 100));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finance.png"))); // NOI18N
        jpnSoDu.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jlbMone.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jlbMone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnSoDu.add(jlbMone, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 250, 60));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel36.setText("VNĐ");
        jpnSoDu.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, -1, -1));

        jPanel3.add(jpnSoDu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 540));

        jpnNapTien.setBackground(new java.awt.Color(255, 255, 255));
        jpnNapTien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel24.setText("NẠP TIỀN");
        jpnNapTien.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel25.setText("Nhập số tiền cần nạp :");
        jpnNapTien.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 230, 50));

        jTextField10.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jpnNapTien.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 230, 60));

        btnHuy.setBackground(new java.awt.Color(255, 102, 102));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setActionCommand("Xác Nhận");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jpnNapTien.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, 120, 50));

        btnNapTien.setBackground(new java.awt.Color(255, 102, 102));
        btnNapTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNapTien.setText("Nạp Tiền");
        btnNapTien.setActionCommand("Xác Nhận");
        btnNapTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNapTienActionPerformed(evt);
            }
        });
        jpnNapTien.add(btnNapTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 120, 50));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nprien.png"))); // NOI18N
        jpnNapTien.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        jPanel7.setBackground(new java.awt.Color(102, 204, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpnNapTien.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 540));

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jpnNapTien.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 650, 60));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salary.png"))); // NOI18N
        jLabel29.setToolTipText("");
        jpnNapTien.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, -1, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel37.setText(".000 VNĐ");
        jpnNapTien.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));

        jPanel3.add(jpnNapTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 540));

        jpnHISTORY.setBackground(new java.awt.Color(255, 255, 255));
        jpnHISTORY.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel23.setText("LỊCH SỬ GIAO DỊCH");
        jpnHISTORY.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jTableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã GD", "Số Tiền", "Ngày GD"
            }
        ));
        jScrollPane1.setViewportView(jTableHistory);

        jpnHISTORY.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 700, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/file.png"))); // NOI18N
        jpnHISTORY.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        jPanel3.add(jpnHISTORY, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 540));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 760, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private String getDataString(byte p1) {
        
        String kq = "";
        try {
            ResponseAPDU respond = card.sendRequest(
                    new CommandAPDU(0x00,config.BOTAPPLET.INS_GET_DATA,p1,0x00)
            );

            String result = Integer.toHexString(respond.getSW());
            if(result.equals("9000")) {              
                kq = new String(respond.getData(),StandardCharsets.UTF_8);
                System.out.println("OUTDATA::::::::::::"+kq);
                //kq = hexToString(respond.getData());
            } else {
                System.out.println("Error command APDU");
            }
        } catch(Exception e) {
                
        }
        return kq;
    }
//    private String getData() {
//    try {
//        // Tạo và gửi lệnh APDU cho việc lấy dữ liệu
//        CommandAPDU getDataCommand = new CommandAPDU(0x00, config.BOTAPPLET.INS_GET_DATA, 0x00, 0x00);
//        ResponseAPDU response = card.sendRequest(getDataCommand);
//        
//        // Kiểm tra mã trạng thái và trả về dữ liệu
//        if (response.getSW() == 0x9000) {
//            // Dữ liệu được trả về dưới dạng chuỗi byte, chuyển thành String
//            byte[] data = response.getData();
//            return new String(data, StandardCharsets.UTF_8); // Trả về dữ liệu dưới dạng String
//        } else {
//            return "Lỗi khi lấy dữ liệu";
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//        return "Lỗi kết nối";
//    }
//}
    
        private void displayImage(byte[] imageBytes) throws Exception {
        // Chuyển đổi byte array thành BufferedImage
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        BufferedImage image = ImageIO.read(bis);

        // Hiển thị ảnh trong JFrame
        // juploadpt1.setIcon(new ImageIcon(image));
        
        ImageIcon imageIcon = new ImageIcon(imageBytes );
        
        // Kich thuoc o juploadpt1
        int width = juploadpt1.getWidth();
        int height = juploadpt1.getHeight();
        
        //Scale image
        Image scaledImage;
        scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        juploadpt1.setIcon(new ImageIcon(scaledImage));

    }
    
    private byte[] selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "bmp"));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                return Files.readAllBytes(file.toPath()); // Đọc ảnh thành byte array
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    private void jlbINFOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbINFOMouseClicked
       jpnINFO.setVisible(true);
       jpnPIN.setVisible(false);
       jpnSoDu.setVisible(false);
       jpnHISTORY.setVisible(false);
       jpnNapTien.setVisible(false);
       jpnCHECKOUT.setVisible(false);
    }//GEN-LAST:event_jlbINFOMouseClicked

    private void jlbPINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbPINMouseClicked
       jpnINFO.setVisible(false);
       jpnPIN.setVisible(true);
       jpnSoDu.setVisible(false);
       jpnHISTORY.setVisible(false);
       jpnNapTien.setVisible(false);
       jpnCHECKOUT.setVisible(false);
    }//GEN-LAST:event_jlbPINMouseClicked

    private void jlbSoDuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbSoDuMouseClicked
       jpnINFO.setVisible(false);
       jpnPIN.setVisible(false);
       jpnSoDu.setVisible(true);
       
       jpnHISTORY.setVisible(false);
       jpnNapTien.setVisible(false);
       jpnCHECKOUT.setVisible(false);
       
       String balanceString = String.valueOf(card.getBalance());
        jlbMone.setText(balanceString);
    }//GEN-LAST:event_jlbSoDuMouseClicked

    private void jlbHisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbHisMouseClicked
       jpnINFO.setVisible(false);
       jpnPIN.setVisible(false);
       jpnSoDu.setVisible(false);
       jpnHISTORY.setVisible(true);
       jpnNapTien.setVisible(false);
       jpnCHECKOUT.setVisible(false);
       
        loadTransactionDataToTable(jTableHistory);
       
    }//GEN-LAST:event_jlbHisMouseClicked

    
    private void loadTransactionDataToTable(JTable table) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            List<Map<String, Object>> transactions = DBConnection.loadTransactions(card.strID);
            if (transactions.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có data giao dịch");
                return;
            }

            int index = 1;
            for (Map<String, Object> transaction : transactions) {
                model.addRow(new Object[]{
                    index++,
                    transaction.get("transaction_id"),
                    decimalFormat.format((Double) transaction.get("amount")),
                    dateFormat.format((Date) transaction.get("transaction_date")),
                    
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lá»—i khi táº£i dá»¯ liá»‡u: " + e.getMessage());
        }
    }
    private void jlbNapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbNapMouseClicked
       jpnINFO.setVisible(false);
       jpnPIN.setVisible(false);
       jpnSoDu.setVisible(false);
       jpnHISTORY.setVisible(false);
       jpnNapTien.setVisible(true);
       jpnCHECKOUT.setVisible(false);
    }//GEN-LAST:event_jlbNapMouseClicked

    private void jlbConnectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbConnectMouseClicked
        // TODO add your handling code here:
        ConnectJavaCard disConnect = new ConnectJavaCard();
        disConnect.disconnectCard();
    }//GEN-LAST:event_jlbConnectMouseClicked

    private void btnupPINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupPINActionPerformed
        // TODO add your handling code here:
        card.strID = jTextField12.getText();
        card.strName = jTextField6.getText();
        card.strAddress = jTextField8.getText();
        card.strDate = jTextField7.getText();
        card.strNumberPlate = jTextField5.getText();
        ConnectJavaCard connect = new ConnectJavaCard();
        String oldPin = jTextField2.getText();
        String newPin = jTextField4.getText();
        String cofirmPin = jTextField3.getText();
        
        if(newPin.equals(cofirmPin) && !newPin.equals(oldPin)){
            
            // Previous code
            if(card.ChangePIN(oldPin, newPin)){
                connect.getPin(newPin);
                // Get string data
                String dataSend = String.join(",",card.strID, card.strName, card.strDate, card.strAddress, card.strNumberPlate);
                card.data = dataSend.getBytes(StandardCharsets.UTF_8);
        
                // Send request
                ResponseAPDU respond;
                respond = card.sendRequest(
                    new CommandAPDU(0x00,config.BOTAPPLET.INS_SET_DATA,0x00,0x00,card.data)
                );
                System.out.println("PININCHANGE:::::::::::"+newPin);
                System.out.println("Đổi mã PIN thành công!");
                new loginForm().setVisible(true);
                this.dispose();
            }
            else{
                System.out.println("Đổi mã PIN không thành công!");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Kiểm tra mã PIN");
        }
    }//GEN-LAST:event_btnupPINActionPerformed

    private void btnCanPINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanPINActionPerformed
        // TODO add your handling code here:
        jpnINFO.setVisible(true);
       jpnPIN.setVisible(false);
       jpnSoDu.setVisible(false);
       jpnHISTORY.setVisible(false);
       jpnNapTien.setVisible(false);
       jpnCHECKOUT.setVisible(false);
    }//GEN-LAST:event_btnCanPINActionPerformed

   
    // Update button
    private void btnUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUPActionPerformed
        // TODO add your handling code here:
        // Send commandAPDU to Card _ INS_SET
        int age; 
        
        card.strID = jTextField12.getText();
        card.strName = jTextField6.getText();
        card.strAddress = jTextField8.getText();
        card.strDate = jTextField7.getText();
        card.strNumberPlate = jTextField5.getText();
        
        if (!isValidName(card.strName)) {
            // Notify null
            if (card.strName == null || card.strName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Trống trường tên");
                return;
            }
            if (card.strName.length() < 2) {
                JOptionPane.showMessageDialog(this, "Tên quá ngắn (< 2 kí tự)");
            } else if (card.strName.length() > 50) {
                JOptionPane.showMessageDialog(this, "Tên quá dài (> 50 kí tự)");
            } else {
                JOptionPane.showMessageDialog(this, "Trong tên có số");
            }
            return;
        }
        
        if (!isValidAddress(card.strAddress)) {
            // Check address null
            if (card.strAddress == null || card.strAddress.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Trống trường địa chỉ");
                return;
            }

            if (card.strAddress.length() < 2) {
                JOptionPane.showMessageDialog(this, "Địa chỉ quá ngắn (< 2 kí tự)");
            } else {
                JOptionPane.showMessageDialog(this, "Địa chỉ quá dài (> 50 kí tự)");
            }
            return;
        }
        
        if (!isValidDob(card.strDate)) {
            return;
        }
        
        if (!isValidNumberPlate(card.strNumberPlate)) {

            if (card.strNumberPlate.isEmpty()) {
                return;
            }

            JOptionPane.showMessageDialog(this, "Sai định dạng biển số: Eg: 30A-888.88");
            return;
        }

        String id = card.strID;                     // ID người dùng
        String name = card.strName;                 // Tên
        String address = card.strAddress;           // Địa chỉ
        String dob = card.strDate;                  // Ngày sinh
        String licensePlate = card.strNumberPlate;  // Biển số xe
        System.out.println("start ");
        
        try {
            System.out.println("Start updating");
            // Gửi dữ liệu lên DB
            boolean success = DBConnection.updateUserInfo(id, name, address, dob, licensePlate);

            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công vào cơ sở dữ liệu!");
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi cập nhật khi lưu dữ liệu vào cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
    //        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            System.out.println("error db");
        }
        System.out.println("send done");
        // Get string data
        String dataSend = String.join(",",card.strID, card.strName, card.strDate, card.strAddress, card.strNumberPlate);
        card.data = dataSend.getBytes(StandardCharsets.UTF_8);
        
        // Send request
        ResponseAPDU respond;
        respond = card.sendRequest(
                new CommandAPDU(0x00,config.BOTAPPLET.INS_SET_DATA,0x00,0x00,card.data)
        );
        System.out.println(respond.toString());
        String result = Integer.toHexString(respond.getSW());
        if(result.equals("9000")) {
            System.out.println("Send data to card success");
            
        } else {
            System.out.println("Error command APDU");
        }
        
    }//GEN-LAST:event_btnUPActionPerformed
    
    // Button select image
    private void btnup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnup1ActionPerformed
        // TODO add your handling code here:
        byte[] originalImageBytes  = selectImage();
        if (originalImageBytes  != null) {
            JOptionPane.showMessageDialog(this, "Image selected successfully!");
            
            try {
                card.sendImage(originalImageBytes);
            } catch (CardException ex) {
                ex.printStackTrace();
            }
        }
        
        ImageIcon imageIcon = new ImageIcon(originalImageBytes );
        
        // Kich thuoc o juploadpt1
        int width = juploadpt1.getWidth();
        int height = juploadpt1.getHeight();
        
        //Scale image
        Image scaledImage;
        scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        juploadpt1.setIcon(new ImageIcon(scaledImage));
        
        
    }//GEN-LAST:event_btnup1ActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        jpnINFO.setVisible(true);
        jpnPIN.setVisible(false);
        jpnCHECKOUT.setVisible(false);
        jpnSoDu.setVisible(false);
        jpnHISTORY.setVisible(false);
        jpnNapTien.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnNapThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNapThem1ActionPerformed
        // TODO add your handling code here:
       jpnINFO.setVisible(false);
       jpnPIN.setVisible(false);
       jpnSoDu.setVisible(false);
       jpnHISTORY.setVisible(false);
       jpnNapTien.setVisible(true);
       jpnCHECKOUT.setVisible(false);
    }//GEN-LAST:event_btnNapThem1ActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        jpnINFO.setVisible(true);
        jpnPIN.setVisible(false);
        jpnCHECKOUT.setVisible(false);
        jpnSoDu.setVisible(false);
        jpnHISTORY.setVisible(false);
        jpnNapTien.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnNapTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNapTienActionPerformed
        // TODO add your handling code here:
        String amountString = jTextField10.getText();
        int amount = Integer.parseInt(amountString);
        
        double amountDouble = Double.parseDouble(amountString);
        
        
        // Confirm form
        Confirmform confirmform = new Confirmform(
                new PinConfirmCallback() {
            @Override
            public void onPinCorrect() {
                System.out.println("Verify done");
                
                // Process payment
                card.napTien(amount);
                
                String balanceString = String.valueOf(card.getBalance());
                jlbMone.setText(balanceString);
                
                try {
                    db.addTransaction(card.strID,amountDouble);
                } catch (SQLException ex) {
                    Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                moveToSoDuPanel();
                
                
            }
                }
        );
        confirmform.setVisible(true);
        
    }//GEN-LAST:event_btnNapTienActionPerformed

    private void jlbCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCancelMouseClicked
        // TODO add your han nedxw4  dling code here:
        this.dispose();
    }//GEN-LAST:event_jlbCancelMouseClicked
    
    // Field name
    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void btnThoat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoat1ActionPerformed
        jpnINFO.setVisible(true);
        jpnPIN.setVisible(false);
        jpnCHECKOUT.setVisible(false);
        jpnSoDu.setVisible(false);
        jpnHISTORY.setVisible(false);
        jpnNapTien.setVisible(false); // TODO add your handling code here:
    }//GEN-LAST:event_btnThoat1ActionPerformed

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        // TODO add your handling code here:
        String amount = jTextField11.getText();
        
        if ((Integer.parseInt(amount) % 1000) != 0) {
            JOptionPane.showMessageDialog(this, "Nhập mệnh giá là bội của 1.000");
            return;
        }
        
        int amountInt = Integer.parseInt(amount) / 1000 ;
        double amountDouble = (double) amountInt;
        // Confirm form
        Confirmform confirmform = new Confirmform(
                new PinConfirmCallback() {
            @Override
            public void onPinCorrect() {
                System.out.println("Verify done");
                
                // Process payment
                card.pay(amountInt);
                
                try {
                    db.addTransaction(card.strID,-amountDouble);
                } catch (SQLException ex) {
                    Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String balanceString = String.valueOf(card.getBalance());
                jlbMone.setText(balanceString);
                moveToSoDuPanel();
                
                
            }
                }
        );
        confirmform.setVisible(true);
        
        
    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void moveToSoDuPanel() {
       
       JOptionPane.showMessageDialog(this, "Giao dịch thành công");
       
       jpnINFO.setVisible(false);
       jpnPIN.setVisible(false);
       jpnSoDu.setVisible(true);
       jpnHISTORY.setVisible(false);
       jpnNapTien.setVisible(false);
       jpnCHECKOUT.setVisible(false);
       jTextField10.setText("");
       jTextField11.setText("");
    }
    private void jlbCheckOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCheckOutMouseClicked
        // Set layout
        jpnINFO.setVisible(false);
        jpnPIN.setVisible(false);
        jpnCHECKOUT.setVisible(true);
        jpnSoDu.setVisible(false);
        jpnHISTORY.setVisible(false);
        jpnNapTien.setVisible(false);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jlbCheckOutMouseClicked

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jpnsoDu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnsoDu2MouseClicked
        // TODO add your handling code here:
        // So du panel
        jpnINFO.setVisible(false);
        jpnPIN.setVisible(false);
        jpnSoDu.setVisible(true);

        jpnHISTORY.setVisible(false);
        jpnNapTien.setVisible(false);
        jpnCHECKOUT.setVisible(false);
       
        String balanceString = String.valueOf(card.getBalance());
        jlbMone.setText(balanceString);
       
    }//GEN-LAST:event_jpnsoDu2MouseClicked
    
        // Validate name
    public boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false; // Check null
        }
        String regex = "^[\\p{L}\\s]{2,50}$"; // Only alphabet, leng = 2 -> 50
        return name.matches(regex);
    }

    // Validate dob
    public boolean isValidDob(String dob) {

        if (dob == null || dob.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Trống trường ngày sinh");
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Check dob is exsit (eg: 30/02/2002)
        dateFormat.setLenient(false);

        try {
            Date dateOfBirth = dateFormat.parse(dob);
            Date currentDate = new Date();

            // Kiem tra ngay sinh co lon hon hien tai
            // Eg: DoB = 30/12/2024 (Error)
            if (dateOfBirth.after(currentDate)) {
                JOptionPane.showMessageDialog(this, "Bạn chưa được sinh ra.");
                return false;
            }

            // Kiem tra tuoi co phu hop khong
            long ageInMillis = currentDate.getTime() - dateOfBirth.getTime();
            int age = (int) (ageInMillis / (1000L * 60 * 60 * 24 * 365));

            if (age < 18) {
                JOptionPane.showMessageDialog(this, "Bạn chưa đủ tuổi.");
                return false;
            } else if (age > 90) {
                JOptionPane.showMessageDialog(this, "Bạn quá tuổi");
                return false;
            } else {
                return true;
            }
            //return ((age >= 18) && (age <=90));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không tồn tại");
            return false;
        }
    }

    // Validate address
    public boolean isValidAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return false; // 
        }
        // Length = 2: eg: HN, HP, BG
        return address.length() >= 2 && address.length() <= 50; // 2 <= Length <= 59
    }

    // Validate number plate
    public boolean isValidNumberPlate(String numberPlate) {
        if (numberPlate == null || numberPlate.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Trống trường biển số xe");
            return false; // 
        }
        //
        // format "29F-888.88"
        String regex = "^[0-9]{2}[A-Z]-[0-9]{3}\\.[0-9]{2}$";
        return numberPlate.matches(regex);
    }
    
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
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new HomeForm().setVisible(true);
        });
    }
    
    
    private List<byte[]> splitByteArray(byte[] data, int chunkSize) {
        List<byte[]> chunks = new ArrayList<>();
        int start = 0;
        while (start < data.length) {
            int end = Math.min(data.length, start + chunkSize);
            chunks.add(Arrays.copyOfRange(data, start, end));
            start += chunkSize;
        }
        return chunks;
    }
    
    private byte[] imageToByteArray(Image image, String format) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(
            image.getWidth(null),
            image.getHeight(null),
            BufferedImage.TYPE_INT_ARGB
        );

        // Vẽ ảnh vào BufferedImage
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        // Chuyển BufferedImage thành byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, baos);
        return baos.toByteArray();
    }
    
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X ", b));
        }
        return hexString.toString().trim();
    }


    public static String hexToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append((char) b); // Chuyển đổi byte sang ký tự
        }
        return sb.toString();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCanPIN;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnNapThem1;
    private javax.swing.JButton btnNapTien;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThoat1;
    private javax.swing.JButton btnUP;
    private javax.swing.JButton btnup1;
    private javax.swing.JButton btnupPIN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHistory;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel jlbCancel;
    private javax.swing.JLabel jlbCheckOut;
    private javax.swing.JLabel jlbConnect;
    private javax.swing.JLabel jlbHis;
    private javax.swing.JLabel jlbINFO;
    private javax.swing.JLabel jlbMone;
    private javax.swing.JLabel jlbNap;
    private javax.swing.JLabel jlbPIN;
    private javax.swing.JLabel jlbSoDu;
    private javax.swing.JPanel jpnCHECKOUT;
    private javax.swing.JPanel jpnCheckOut;
    private javax.swing.JPanel jpnConnect;
    private javax.swing.JPanel jpnHISTORY;
    private javax.swing.JPanel jpnINFO;
    private javax.swing.JPanel jpnNapTien;
    private javax.swing.JPanel jpnPIN;
    private javax.swing.JPanel jpnSoDu;
    private javax.swing.JPanel jpnbank1;
    private javax.swing.JPanel jpnhis;
    private javax.swing.JPanel jpnif2;
    private javax.swing.JPanel jpnpin;
    private javax.swing.JPanel jpnsoDu2;
    private javax.swing.JLabel juploadpt1;
    // End of variables declaration//GEN-END:variables
}
