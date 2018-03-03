package com.peacce.team3461.escouting.qrdecoder;

import java.io.FileInputStream;
import javax.imageio.ImageIO;
 
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
/**
 *  @author Nathan Benham Operation Peacce Robotics Team #3461
 *	Class containing method to  decode qr code from an image
 */
 
public class QR {
	
	public static String decodeQr(String img) {
		 
		Result result = null;
		BinaryBitmap binaryBitmap;
 
		try{
 
			binaryBitmap = new BinaryBitmap(new HybridBinarizer(new 
					BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(img)))));
			result = new MultiFormatReader().decode(binaryBitmap);
			return result.getText();
 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "\nfailed to decode QR";
	}
}