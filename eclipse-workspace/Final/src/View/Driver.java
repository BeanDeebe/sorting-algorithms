package View;

import javax.swing.JFrame;

public class Driver {
	
	public static void main(String[] args) {
		UserLogin user = new UserLogin();

		user.setTitle("The Roux Golf Club");
		user.setSize(400, 350);
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		user.setVisible(true);
		
		
	}

}
