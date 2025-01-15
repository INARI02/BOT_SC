/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bot_card;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ConnectJavaCard {

    // Properties
    byte[] data;
    byte[] image;
    public String strID;
    public String strName;
    public String strDate;
    public String strAddress;
    public String strNumberPlate;
    public String message;

    // Khai bao doi tuong ket noi applet
    TerminalFactory factory;
    List<CardTerminal> terminals;
    CardTerminal terminal;
    Card card;
    CardChannel channel;

    public String connectapplet() {
        String kq = "";
        try {
            // Display the list of terminals
            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();
            System.out.println("Terminals: " + terminals);

            // Use the first terminal
            terminal = terminals.get(0);

            // Connect wit hthe card
            card = terminal.connect("*");
            System.out.println("card: " + card);
            channel = card.getBasicChannel();

            // Send Select Applet command
            byte[] aid = {(byte) 0x11, 0x22, 0x33, 0x44, 0x55, 0x01};
            ResponseAPDU answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x04, 0x00, aid));
            kq = answer.toString();
            data = answer.getData();
            System.out.println("answer::::: " + answer.toString());
            return kq;
        } catch (CardException e) {
            System.out.println("Error::::: " + e.toString());
        }
        return kq;
    }

    public boolean disconnectCard() {
        try {
            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();
            System.out.println("Terminals: " + terminals);

            // Use the first terminal
            terminal = terminals.get(0);

            // Connect wit hthe card
            card = terminal.connect("*");
            card.disconnect(true);
            return true;
        } catch (CardException e) {
            System.out.println("ERROR::::::::" + e);
        }
        return false;
    }

    public void setUp() {

        try {
            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0xB0, config.BOTAPPLET.INS_SETUP, 0x00, 0x00));

        } catch (CardException ex) {
            System.out.println("ERROR::::::::" + ex);
        }

    }

    public boolean createPIN(String pin) {

        byte[] pinbyte = pin.getBytes();
        byte len = (byte) pinbyte.length;

        byte[] send = new byte[len + 1];
        send[0] = len;
        for (int i = 1; i < send.length; i++) {
            send[i] = pinbyte[i - 1];
        }
        try {

            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0xA0, config.BOTAPPLET.INS_CREATE_PIN, 0x00, 0x03, send));

            message = answer.toString();
            switch (((message.split("="))[1]).toUpperCase()) {
                case config.STATUS.SW_NO_ERROR:
                    return true;
                case config.STATUS.SW_INVALID_PARAMETER:
                    JOptionPane.showMessageDialog(null, "Mã PIN không hợp lệ");
                    return false;
                case config.STATUS.SW_WRONG_LENGTH:
                    JOptionPane.showMessageDialog(null, "Độ dài mã PIN không chính xác");
                    return false;
                default:
                    return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean verifyPin(String pin) {
        connectapplet();
        byte[] pinbyte = pin.getBytes();
        try {

            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0xA0, config.BOTAPPLET.INS_VERIFY_PIN, 0x00, 0x00, pinbyte));
            message = Integer.toHexString(answer.getSW());
            switch (message.toUpperCase()) {
                case config.STATUS.SW_NO_ERROR:
                    return true;
                case config.STATUS.SW_AUTH_FAILED:
                    JOptionPane.showMessageDialog(null, "Bạn đã nhập sai mã PIN");
                    return false;
                case config.STATUS.SW_IDENTITY_BLOCKED:
                    JOptionPane.showMessageDialog(null, "Bạn đã nhập sai quá 3 lần !!!Thẻ đã bị khoá");
                    return false;
                case config.STATUS.SW_INVALID_PARAMETER:
                    JOptionPane.showMessageDialog(null, "Độ dài PIN chưa hợp lệ");
                    return false;
                default:
                    return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public void getPin(String pin) {
        System.out.println("getpin");
        connectapplet();
        byte[] pinbyte = pin.getBytes();
        try {

            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0xA0, 0x70, 0x00, 0x00, pinbyte));
            System.out.println("getpin done");
        } catch (Exception ex) {
            System.out.println("ERROR::::::::" + ex);
        }
    }

    public boolean ChangePIN(String oldPin, String newPin) {
        connectapplet();
        byte[] oldPinByte = oldPin.getBytes();
        byte oldPinLen = (byte) oldPinByte.length;

        byte[] newPinByte = newPin.getBytes();
        byte newPinLen = (byte) newPinByte.length;

        byte[] send = new byte[newPinLen + oldPinLen + 2];
        int offSet = 0;
        send[offSet] = oldPinLen;
        offSet += 1;
        System.arraycopy(oldPinByte, 0, send, offSet, oldPinLen);
        offSet += oldPinLen;
        send[offSet] = newPinLen;
        offSet += 1;
        System.arraycopy(newPinByte, 0, send, offSet, newPinLen);
        try {

            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU answer = channel.transmit(new CommandAPDU(0xA0, config.BOTAPPLET.INS_CHANGE_PIN, 0x00, 0x00, send));

            message = answer.toString();
            switch (((message.split("="))[1]).toUpperCase()) {
                case config.STATUS.SW_NO_ERROR:
                    JOptionPane.showMessageDialog(null, "Cập nhật PIN thành công!");
                    return true;
                case config.STATUS.SW_AUTH_FAILED:
                    JOptionPane.showMessageDialog(null, "Bạn đã nhập sai PIN");
                    return false;
                case config.STATUS.SW_IDENTITY_BLOCKED:
                    JOptionPane.showMessageDialog(null, "Bạn đã nhập sai quá 3 lần !!!Thẻ đã bị khoá");
                    return false;
                default:
                    return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean UnblockPin(byte[] aid) {
        try {

            factory = TerminalFactory.getDefault();
            terminals = factory.terminals().list();

            terminal = terminals.get(0);

            card = terminal.connect("*");

            channel = card.getBasicChannel();

            ResponseAPDU selectBlockcard = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x00, 0x00, aid));

            String check = Integer.toHexString(selectBlockcard.getSW());

            if (check.equals(config.STATUS.SW_NO_ERROR)) {
                CardChannel channel2 = card.getBasicChannel();

                ResponseAPDU unblockCard = channel2.transmit(new CommandAPDU(config.BOTAPPLET.CLA, config.BOTAPPLET.INS_UNBLOCK_PIN, 0x00, 0x00));
                message = unblockCard.toString();
                switch (((message.split("="))[1]).toUpperCase()) {
                    case config.STATUS.SW_NO_ERROR:
                        JOptionPane.showMessageDialog(null, "Mở khoá thẻ thành công");
                        return true;
                    case config.STATUS.SW_OPERATION_NOT_ALLOWED:
                        JOptionPane.showMessageDialog(null, "Thẻ không bị khoá vui lòng kiểm tra lại!");
                        return false;
                    default:
                        return false;
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    
    public boolean checkPin(String pinChecked) {
        
        connectapplet();
        
        try {
            byte[] pinBytes = pinChecked.getBytes();
            
            ResponseAPDU getPinResponseAPDU = channel.transmit(
                    new CommandAPDU(0x00,config.BOTAPPLET.INS_PIN_CHECK,0x00,0x00,pinBytes)
            );
            
            if (getPinResponseAPDU.getSW() != 0x9000) {
                System.out.println("Err command APDU");
                return false;
            }

            return true;
            
        } catch (CardException ex) {
            Logger.getLogger(ConnectJavaCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public ResponseAPDU sendRequest(CommandAPDU commandAPDU) {
        ResponseAPDU respond;
        String kq = connectapplet();
        if (!kq.contains("SW=9000")) {
            System.out.println("Connection error");
        } else {
            System.out.println("Connect to card");
            setUp();
            System.out.println("Set up done!");

            try {
                factory = TerminalFactory.getDefault();
                terminals = factory.terminals().list();
                terminal = terminals.get(0);
                card = terminal.connect("*");
                channel = card.getBasicChannel();

                respond = channel.transmit(commandAPDU);
                return respond;
            } catch (CardException e) {

            }

        }
        return null;
    }

    public void sendImage(byte[] image) throws CardException {
        connectapplet();
        setUp();
        int chunk_size = 243;
        int chunk_blocks = (image.length + chunk_size - 1) / chunk_size;
        for (int index = 0; index < chunk_blocks; index++) {
            int offset = index * chunk_size;
            int length = (chunk_size < (image.length - offset)) ? chunk_size : (image.length - offset);

            byte[] chunks = Arrays.copyOfRange(image, offset, offset + length);

            CommandAPDU commandAPDU = new CommandAPDU(
                    0x00,
                    config.BOTAPPLET.INS_SEND_IMAGE,
                    index,
                    0x00,
                    chunks
            );

            System.out.println("Sending chunk " + (index + 1) + "/" + chunk_blocks);
            ResponseAPDU response = channel.transmit(commandAPDU);

            if (response.getSW() != 0x9000) {
                throw new CardException("Error sending chunk: " + (index + 1));
            }
        }
    }

    public byte[] fetchImageData() throws Exception {
        connectapplet();
        setUp();
        // Bộ đệm để lưu toàn bộ dữ liệu ảnh
        ByteArrayOutputStream imageData = new ByteArrayOutputStream();
        int chunkSize;
        byte[] buffer;

        while (true) {
            // Gửi lệnh yêu cầu chunk
            CommandAPDU command = new CommandAPDU(0x00, config.BOTAPPLET.INS_GET_IMAGE, 0x00, 0x00);
            ResponseAPDU response = channel.transmit(command);

            // Lấy dữ liệu chunk từ phản hồi
            buffer = response.getData();
            chunkSize = buffer.length;

            if (chunkSize == 0) {
                break;
            }

            // Ghi chunk vào bộ đệm
            imageData.write(buffer, 0, chunkSize);

            // Kiểm tra trạng thái hoàn tất
            if (response.getSW() == 0x9000) {
                continue; // Toàn bộ dữ liệu đã được nhận
            }
        }

        // Ngắt kết nối thẻ
        card.disconnect(false);

        return imageData.toByteArray();
    }

    public String getPublicKey() throws CardException, NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            // Tạo lệnh APDU với CLA=0x00 thay vì 0x80 để phù hợp với applet
            CommandAPDU command = new CommandAPDU(0x00, config.RSAAPPLET.INS_GET_PUBKEY, 0x00, 0x00, 256);
            ResponseAPDU response = channel.transmit(command);

            if (response.getSW() != 0x9000) {
                throw new CardException("Failed to get public key. SW: 0x"
                        + String.format("%04X", response.getSW()));
            }

            byte[] publicKeyData = response.getData();

            // Với RSA 1024 bit, modulus là 128 bytes
            if (publicKeyData.length < 128) {
                throw new CardException("Invalid public key data length: " + publicKeyData.length);
            }

            byte[] modulus = Arrays.copyOfRange(publicKeyData, 0, 128);
            byte[] exponent = Arrays.copyOfRange(publicKeyData, 128, publicKeyData.length);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(
                    new BigInteger(1, modulus),
                    new BigInteger(1, exponent)
            );

            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return Base64.getEncoder().encodeToString(publicKey.getEncoded());

        } catch (CardException e) {
            throw new CardException("Smart card communication error: " + e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw e; // Rethrow these specific exceptions
        }
    }
    
    public boolean verifyCard(String datacheck)  {
        
        connectapplet();
        
        try {
            // Convert public key frome base 64 -> PublicKey
            String pubKeyBase64 = getPublicKey();

            // String -> Byte[]
            byte[] publicKeyBytes = Base64.getDecoder().decode(pubKeyBase64);

            // Byte[] -> PublicKey
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
        
        
            CommandAPDU verifyCommandAPDU = new CommandAPDU(0x00,config.BOTAPPLET.INS_VERIFY_CARD,0x00,0x00,datacheck.getBytes());
            ResponseAPDU verifyResponseAPDU = sendRequest(verifyCommandAPDU);
            
            if (verifyResponseAPDU.getSW() != 0x9000) {
                System.out.println("Error command verify");
            }
            
            byte[] dataVerify = verifyResponseAPDU.getData();
            
            Signature rsaSignature = Signature.getInstance("SHA1withRSA");
            rsaSignature.initVerify(publicKey);
            rsaSignature.update(datacheck.getBytes());
            boolean isVerify = rsaSignature.verify(dataVerify);
            
            if (isVerify) {
                System.out.println("Verifi card success");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Verifi faile in bla bla");
            System.out.println(e.toString());
        }
        
        return false;
    }
    
    // Pay amout
    public boolean pay(int amount) {
        
        connectapplet();
        
        byte highByte = (byte) (amount >> 8);
        byte lowByte = (byte) (amount & 0xFF);
        
        byte[] amountBytes = { highByte, lowByte };
        
        
        try {
            ResponseAPDU payResponseAPDU = sendRequest(
                    new CommandAPDU(0x00, config.BOTAPPLET.INS_TRANSACTION, config.BOTAPPLET.P1_THANH_TOAN,0x00,amountBytes)
            );
            
            if (payResponseAPDU.getSW() != 0x9000) {
                System.out.println("Error at pay method");
                return false;
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("err in pay() method");
        }
        
        return false;
    }
    
    public int getBalance() {
        connectapplet();
        byte[] balanceBytes;
        try {
            ResponseAPDU getBalanceResponseAPDU = sendRequest(
                    new CommandAPDU(0x00, config.BOTAPPLET.INS_TRANSACTION,config.BOTAPPLET.P1_SO_DU,0x00)
            );
            
            if (getBalanceResponseAPDU.getSW() != 0x9000) {
                System.out.println("get balance fail");
            }
            
            balanceBytes = getBalanceResponseAPDU.getData();
            byte high = balanceBytes[0];
            byte low = balanceBytes[1];
            int balance = ((high & 0xFF) << 8) | (low & 0xFF);
            
            return (balance * 1000 );
            
        } catch (Exception e) {
        }
        return 0;
    }
    
    public boolean napTien(int amount) {
        connectapplet();
        
        byte highByte = (byte) (amount >> 8);
        byte lowByte = (byte) (amount & 0xFF);
        
        byte[] amountBytes = { highByte, lowByte };
        
                try {
            ResponseAPDU payResponseAPDU = sendRequest(
                    new CommandAPDU(0x00, config.BOTAPPLET.INS_TRANSACTION, config.BOTAPPLET.P1_NAP_TIEN,0x00,amountBytes)
            );
            
            if (payResponseAPDU.getSW() != 0x9000) {
                System.out.println("Error at napTien method");
                return false;
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("Error at napTien() method");
        }
                
        return false;
    }
  }
