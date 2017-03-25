import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import utils.WebService;
import vue.session.FSession;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;


import javax.swing.ImageIcon;

public class Main {

	public static void main(String[] args) {
		try {
			WebService webService = new WebService();
			webService.mettreAJourBaseFilm();
			
			NimRODTheme nt = new NimRODTheme();
			nt.setPrimary1(Color.decode("#5454C0"));
			nt.setPrimary2(Color.decode("#5E5ECA"));
			nt.setPrimary3(Color.decode("#6868D4"));
			nt.setSecondary1(Color.decode("#191E27"));
			nt.setSecondary2(Color.decode("#232831"));
			nt.setSecondary3(Color.decode("#2D323B"));
			nt.setWhite(Color.decode("#494854"));
			nt.setBlack(Color.decode("#FFFFFF"));

			NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
			NimRODLF.setCurrentTheme(nt);
			UIManager.setLookAndFeel(NimRODLF);
			
			//1. Create the frame.
			JFrame frame = new JFrame("CineGo");
			
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setResizable(false);
			frame.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	
			//2. Optional: What happens when the frame closes?
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.getContentPane().setLayout(new BorderLayout());
			
			JLabel header = new JLabel(new ImageIcon("./images/logo_cinego.png"));
			frame.getContentPane().add(BorderLayout.NORTH, header);
			
			//3. Add page
			FSession page = new FSession(frame);
			page.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			frame.getContentPane().add(BorderLayout.CENTER, page);		
	
			
			//5. Show it.
			frame.setVisible(true);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}
}
