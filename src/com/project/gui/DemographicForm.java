package com.project.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.project.dao.Demographics;
import com.project.dao.ExtremeEventsDAO;
import com.project.dao.ExtremeWithAgeDAO;
import com.project.dao.PDFGenerator;

public class DemographicForm extends JApplet implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel jAge = new JLabel("Enter the age");

	private JLabel jMartialStatus = new JLabel("Enter the marital status");

	private JLabel jReligionStatus = new JLabel("Enter the religion status");

	private JLabel jEthinicity = new JLabel("Enter the ethinicity");

	private JTextField jtfAge = new JTextField();

	private JComboBox<String> jtfMartialStatus  = new JComboBox<String>();

	private JComboBox<String> jtfReligionStatus  = new JComboBox<String>();

	private JComboBox<String> jtfEthinicity  = new JComboBox<String>();

	private JButton jbtValues = new JButton("Show High Extremes");
	private JButton jbtValuesLow = new JButton("Show Low Extremes");

	public DemographicForm(){}

	public JPanel getContent() throws SQLException {
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(0, 2));
		p1.add(jAge);
		p1.add(jtfAge);
		getMaritalStatus();
		p1.add(jMartialStatus);
		p1.add(jtfMartialStatus);
		getReligion();
		p1.add(jReligionStatus);
		p1.add(jtfReligionStatus);
		getEthinicity();
		p1.add(jEthinicity);
		p1.add(jtfEthinicity);
		//frame.add(p1);
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.setBorder(new TitledBorder(
				"Mimic iii CMIS Interface:"));
		p2.add(p1, BorderLayout.BEFORE_FIRST_LINE);
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
		p3.add(p2);
		return p3;
	}

	private void getMaritalStatus() throws SQLException {
		ComboBoxModel<String> sessionUserComboModel = new DefaultComboBoxModel<String>(new Demographics().getMaritalStatus());
		jtfMartialStatus.setModel(sessionUserComboModel);
	}

	private void getEthinicity() throws SQLException {
		ComboBoxModel<String> sessionUserComboModel = new DefaultComboBoxModel<String>(new Demographics().getEthinicity());

		jtfEthinicity.setModel(sessionUserComboModel);

	}

	private void getReligion() throws SQLException {
		ComboBoxModel<String> sessionUserComboModel = new DefaultComboBoxModel<String>(new Demographics().getReligionStatus());
		jtfReligionStatus.setModel(sessionUserComboModel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtValues)
			try {
				getExtremeValues("desc");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		else if (e.getSource() == jbtValuesLow)
			try {
				getExtremeValues("asc");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}

	private void getExtremeValues(String order) throws SQLException {
		LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
		if (jtfMartialStatus.getSelectedItem()!=null) {
			parameters.put("marital_status", jtfMartialStatus.getSelectedItem().toString());
		}
		if (jtfEthinicity.getSelectedItem()!=null && !("").equals(jtfEthinicity.getSelectedItem())) {
			parameters.put("ethinicity", jtfEthinicity.getSelectedItem().toString());
		}
		if (jtfReligionStatus.getSelectedItem()!=null) {
			parameters.put("religion", jtfReligionStatus.getSelectedItem().toString());
		}
		for(Map.Entry<String, String> param: parameters.entrySet()) {
			System.out.println(param.getKey() + " " + param.getValue());
		}
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		if(jtfAge.getText() != null && !("").equals(jtfAge.getText())) {
			if(jtfAge.getText().trim().matches("[0-9]+")) {
				ExtremeWithAgeDAO dao = new ExtremeWithAgeDAO();
				result = dao.getExtremesWithAge(parameters, order, Integer.parseInt(jtfAge.getText()));
				PDFGenerator.generate(result);
			}
			else {
				JOptionPane.showMessageDialog(null,
						"Please enter a valid age", null, 1);
			}
		} else  {
			ExtremeEventsDAO dao = new ExtremeEventsDAO();
			result = dao.getDescendingExtremes(parameters, order);
			PDFGenerator.generate(result);
		}
		
	}
}
