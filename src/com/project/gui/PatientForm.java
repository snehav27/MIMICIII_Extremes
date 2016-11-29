package com.project.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.project.dao.ExtremeOfPatientDAO;
import com.project.dao.PDFGenerator;

public class PatientForm extends JApplet implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JLabel jPatientId = new JLabel("Enter the Patient ID");
	private JTextField jtfPatientId = new JTextField();

	private JButton jbtValues = new JButton("Show High Extremes");
	private JButton jbtValuesLow = new JButton("Show Low Extremes");
	private JRadioButton history = new JRadioButton("Generate patient's history");
	JRadioButton extremes = new JRadioButton("Generate extreme for this patient");
	public PatientForm() {}

	public JPanel getContent() throws SQLException {
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(0, 2));
		p1.add(jPatientId);
		p1.add(jtfPatientId);

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 1));

		extremes.setSelected(true);
		extremes.addActionListener(this);

		history.addActionListener(this);

		ButtonGroup bG = new ButtonGroup();
		bG.add(extremes);
		bG.add(history);
		p.add(extremes);
		p.add(history);
		p.add(p1, BorderLayout.BEFORE_FIRST_LINE);
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.setBorder(new TitledBorder(
				"Mimic iii CMIS Interface:"));
		p2.add(p, BorderLayout.BEFORE_FIRST_LINE);
		p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
		p2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		p2.add(Box.createVerticalStrut(10));
		p2.add(jbtValues);
		p2.add(jbtValuesLow);
		p2.add(Box.createVerticalStrut(10));

		jbtValues.addActionListener(this);
		jbtValuesLow.addActionListener(this);
		JPanel p3 = new JPanel();
		p3.add(p1);
		p3.add(p);
		p3.add(p2);
		return p3;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtValues)
			try {
				getPatientDetails("desc");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		else if (e.getSource() == jbtValuesLow)
			try {
				getPatientDetails("asc");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}

	public void getPatientDetails(String order) throws NumberFormatException, SQLException {

		String patient_Id = jtfPatientId.getText();
		ArrayList<ArrayList<String>> result = null;
		if((patient_Id != null && !("").equals(patient_Id)) && ((jtfPatientId.getText()
				.trim().matches("[0-9]+")))) {
			ExtremeOfPatientDAO dao = new ExtremeOfPatientDAO();
			if(extremes.isSelected()) {
				result = dao.getExtremeOfPatient(order, Integer.parseInt(patient_Id));
				PDFGenerator.generate(result);
			}
			else {
				dao.getPatientDetails(order, Integer.parseInt(patient_Id));
			}
		} else  {
			JOptionPane.showMessageDialog(null,
					"Please enter a valid patient ID", null, 1);
		}	
	}
}
