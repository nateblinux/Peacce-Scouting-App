package com.peacce.team3461.escouting;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;
import com.peacce.team3461.escouting.qrdecoder.QR;
/**
 * @author Nathan Benham Operation Peacce Robotics Team #3461
 * Main class where most of the work is done for peacce scouting
 * To take the image from the webcam it uses a separate python script. 
 * The python script utilizes openCV for the images. 
 */
public class Main extends JFrame implements ActionListener{
	JFrame main;
	JButton writeData;
	static boolean initcsv = false;
	public Main() {
		main = new JFrame();
		main.setSize(100, 100);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color background = new Color(200, 200, 200);
		main.setBackground(background);
		main.setForeground(Color.black);
		main.setTitle("Peacce Scout Console");
		writeData = new JButton("Decode a QR code");
		writeData.addActionListener(this);
		main.add(writeData);
		main.pack();
		main.setVisible(true);
	}


	public static void main(String[] args) throws IOException, InterruptedException {
		if(initcsv == false)
			init();
		
		new Main();
	}
	
	public static void init() {
		try {
			WriteCsv.writeLine("Team No, Match No, Cross Base Line, Auto Switch, Auto Scale, "
					+ "Auto Vault, Alliance Switch, "
					+ "Opponent Switch, Scale, Vault, "
					+ "Climb, Park, Levitate, Notes");
			System.out.println("[*] CSV file initalized");
		} catch (IOException e) {
			System.out.println("[*] **ERROR** initializing csv file" );
			e.printStackTrace();
		}
		initcsv = true;
	}
	
	public static void takeImg() throws IOException, InterruptedException {
		System.out.println("[*]starting webcam script");
		String[] command = {
		        "/bin/bash",
		        "-c",
		        "python webcam.py"
		    };
		System.out.println("[*]reached exec on webcam script \n");
		Process proc = Runtime.getRuntime().exec(command);
		System.out.println("[*] Press space to take photo");      
		proc.waitFor();	   
		System.out.println("[*] finished webcam script");
	}
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("[*]recived action");
		if(ae.getSource() == writeData) {
			System.out.println("[*]starting program");
			try {
				takeImg();
			} catch (Exception e1) {
				System.out.println("[*] **ERROR** Taking image");
				e1.printStackTrace();
			}
			System.out.println("[*]reached point qr decode");
			String qrResult = QR.decodeQr("qr.jpg");
			try {
				WriteCsv.writeLine(qrResult);
				System.out.println("[*]qr data recived: " + qrResult);
			} catch (IOException e) {
				System.out.println("[*] **ERROR** decoding qr");
				e.printStackTrace(); 
				
			}
		}
		System.out.println("[*]Completed Requested action - Waiting \n\n\n");
	}

}
