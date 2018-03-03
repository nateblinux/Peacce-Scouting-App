package com.peacce.team3461.escouting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
/**
 * @author Nathan Benham Operation Peacce Robotics Team #3461
 * Class to write csv file from csv data in qr code
 */
public class WriteCsv {
	
	public static void writeLine(String csvText) throws IOException {
		Writer output;
		output = new BufferedWriter(new FileWriter("csvFile.csv", true)); 
		output.append(csvText + "\n");
		output.close();
	}

}
