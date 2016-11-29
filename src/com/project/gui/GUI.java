package com.project.gui;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class GUI extends JApplet {

	private static final long serialVersionUID = 1L;


	private PatientForm patientForm;
	private DemographicForm DemographicForm;
	public void init() {
		try {
			JFrame frame = new JFrame("MIMICIII Extremes Data Info");
			JTabbedPane tabby = new JTabbedPane();
			patientForm = new PatientForm();
			DemographicForm = new DemographicForm();
			tabby.addTab("Demographics", DemographicForm.getContent());
			tabby.addTab("Patient", patientForm.getContent());

			frame.getContentPane().add(tabby);

			frame.setSize(900, 500);
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			frame.setVisible(true);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.init();
	}

}
