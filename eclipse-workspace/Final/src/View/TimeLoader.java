package View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TimeLoader {
	
	private String csvTimes;
	private String date;
	
	public TimeLoader(String date, String csvTimes) {
		this.csvTimes = csvTimes;
		this.date = date;
		
		loadTimesToDB(readTimes());
	}
	
	private ArrayList<String> readTimes() {
		
		ArrayList<String> times = new ArrayList<String>();
		
		try {
			FileReader fileReader = new FileReader("times.txt");
			BufferedReader buff = new BufferedReader(fileReader);
			
			while (buff.read() != -1) {
				times.add(buff.readLine());
			}
			buff.close();
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return times;
	}
	
	private void loadTimesToDB(ArrayList<String> times) {
		
		try {
			
			Connection connector;
			connector = DriverManager.getConnection("jdbc:mysql://localhost:3306/applications?allowPublicKeyRetrieval=true&useSSL=false", "root", "Patriots2020!");
		
			int count = 0;
		
			while (!times.isEmpty()) {
				String time = times.get(count);
				String query = "INSERT INTO TeeSheet (`Date`, `Times Available`) "
						+ "VALUES (?, ?);";
				
				PreparedStatement ps = connector.prepareStatement(query);
				ps.setString(1, this.date);
				ps.setString(2, time);
				ps.executeUpdate();
				times.remove(count);
				count++;
		}
		
		connector.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
