package View;
/*
 * CS5004
 * Dean Beebe
 * Final Project
 *
 * This file contains the view for the main page of the tee time reservation system.
 */
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.graphics.*;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SpringLayout;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;

import javax.swing.JTable;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;



public class MainPage extends JFrame {
	private JTextField txt_AvailableTeeTimes;
	private JTable table_TeeSheet;
	
	public MainPage() {
		getContentPane().setBackground(SystemColor.window);
		getContentPane().setLayout(null);
		
		JLabel lbl_Title = new JLabel("Welcome to the Roux Golf Club!");
		lbl_Title.setBounds(305, 5, 289, 22);
		lbl_Title.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 18));
		getContentPane().add(lbl_Title);
		
		JPanel panel_CalendarBorder = new JPanel();
		panel_CalendarBorder.setBackground(SystemColor.window);
		panel_CalendarBorder.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Select a Date:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_CalendarBorder.setBounds(0, 72, 482, 342);
		getContentPane().add(panel_CalendarBorder);
		panel_CalendarBorder.setLayout(null);
		
		CalendarPanel calendarPanel = new CalendarPanel();
		calendarPanel.setBounds(6, 18, 470, 318);
		panel_CalendarBorder.add(calendarPanel);
		GridBagLayout gridBagLayout = (GridBagLayout) calendarPanel.getLayout();
		gridBagLayout.columnWidths = new int[] {20, 100, 20, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 5, 138, 0, 0, 0};
		calendarPanel.getNextYearButton().setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		calendarPanel.getNextMonthButton().setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		calendarPanel.getPreviousMonthButton().setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		calendarPanel.getPreviousYearButton().setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		
		JPanel panel_TeeSheet = new JPanel();
		panel_TeeSheet.setBounds(494, 72, 500, 342);
		getContentPane().add(panel_TeeSheet);
		panel_TeeSheet.setLayout(null);
		panel_TeeSheet.setVisible(false);
		
		
		JButton btn_DateSelect = new JButton("View Tee Times");
		btn_DateSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String dateSelected = String.valueOf(calendarPanel.getSelectedDate());
				
				TimeLoader load = new TimeLoader(dateSelected, "times.txt");
				
			}
		});
		btn_DateSelect.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_btn_DateSelect = new GridBagConstraints();
		gbc_btn_DateSelect.gridwidth = 2;
		gbc_btn_DateSelect.anchor = GridBagConstraints.EAST;
		gbc_btn_DateSelect.insets = new Insets(0, 0, 0, 5);
		gbc_btn_DateSelect.gridx = 1;
		gbc_btn_DateSelect.gridy = 6;
		calendarPanel.add(btn_DateSelect, gbc_btn_DateSelect);
		
		
		
		txt_AvailableTeeTimes = new JTextField();
		txt_AvailableTeeTimes.setBackground(SystemColor.window);
		txt_AvailableTeeTimes.setBounds(6, 6, 158, 26);
		txt_AvailableTeeTimes.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		txt_AvailableTeeTimes.setText("Available Tee Times :");
		panel_TeeSheet.add(txt_AvailableTeeTimes);
		txt_AvailableTeeTimes.setColumns(10);
		
		table_TeeSheet = new JTable();
		table_TeeSheet.setBounds(6, 32, 488, 304);
		panel_TeeSheet.add(table_TeeSheet);
		
		
		
		
	}
}
