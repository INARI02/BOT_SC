/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author Admin
 */
public class BOTAPPLET {
     public static final byte[] AID_APPLET = {
        (byte)0x11, (byte)0x22, (byte)0x33, (byte)0x44, (byte)0x55, (byte)0x00, (byte)0x00
    };
    
    
    public final static byte CLA =(byte)0xA0;
    
    public final static byte INS_SETUP = (byte) 0x2A;
	//INS-PIN
    public final static byte INS_CREATE_PIN = (byte) 0x40;
    public final static byte INS_VERIFY_PIN = (byte) 0x42;
    public final static byte INS_CHANGE_PIN = (byte) 0x44;
    public final static byte INS_UNBLOCK_PIN = (byte) 0x46;
    
    public final static byte INS_CHANGE_INFORMATION = (byte) 0x52;
    public final static byte INS_CREATE_INFORMATION = (byte)0x50;
    
    public final static byte INS_SET_DATA = (byte)0x01;
    public final static byte INS_GET_DATA = (byte)0x02;
    public final static byte P1_OUT_ID = (byte)0x05;
    public final static byte P1_OUT_NAME = (byte)0x01;
    public final static byte P1_OUT_DOB = (byte)0x02;
    public final static byte P1_OUT_ADDRESS = (byte)0x03;
    public final static byte P1_OUT_NUMBER_PLATE= (byte)0x04;
    
    // INS - p1 - TRANSACTION
    public final static byte INS_TRANSACTION = (byte) 0x1C;
    public final static byte P1_THANH_TOAN = (byte) 0x01;
    public final static byte P1_NAP_TIEN = (byte) 0x02;
    public final static byte P1_SO_DU = (byte) 0x03;
    
    public final static byte INS_PIN_CHECK = (byte) 0x1A;
    public final static byte INS_VERIFY_CARD  = (byte) 0x1B;
       /*
    public final static byte INS_CREATE_IMAGE = (byte)0x53;
    public final static byte INS_OUT_IMAGE = (byte)0x55;
    public final static byte INS_GET_IMAGE_SIZE = (byte) 0x54;
    */
    
    // INS - Image - using Extended APDU
    public final static byte INS_SEND_IMAGE = 0x10;	// App -> Card
    public final static byte INS_GET_IMAGE = 0x11; 	// Card -> App
    public final static byte INS_CHECK = 0x12; 	// Kiem tra da set up chua
}
