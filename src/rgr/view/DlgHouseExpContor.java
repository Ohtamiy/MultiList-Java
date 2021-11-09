package rgr.view;

import javax.swing.JLabel;
import javax.swing.JSpinner;

import rgr.model.HouseExpContor;

import java.awt.Component;
import javax.swing.Box;

//клас діалогу Жек, який успадковує загальний діалог
public class DlgHouseExpContor extends Dlg {
	private JSpinner spinnerNumber;
	private JSpinner spinnerRating;
	
	public DlgHouseExpContor(Object data) {
		this();
		HouseExpContor h = (HouseExpContor) data;
		textField.setText(h.toString());
		spinnerNumber.setValue(h.getNumber());
		spinnerRating.setValue(h.getRating());
	}

	public DlgHouseExpContor() {
		setBounds(286,135,286,136);
		setTitle("Жек");
		textField.setColumns(25);
		
		nameLabel.setText("Вулиця");
		
		JLabel lblNewLabel = new JLabel("Номер");
		contentPanel.add(lblNewLabel);
		
		spinnerNumber = new JSpinner();
		contentPanel.add(spinnerNumber);
		
		Component horizontalStrut = Box.createHorizontalStrut(40);
		contentPanel.add(horizontalStrut);
		
		JLabel lblNewLabel_1 = new JLabel("Рейтинг");
		contentPanel.add(lblNewLabel_1);
		
		spinnerRating = new JSpinner();
		contentPanel.add(spinnerRating);
	}

	@Override
	public Object createObject() throws Exception {
		if(!ok) return null;
		String name = textField.getText();
		int number = Integer.parseInt(spinnerNumber.getValue().toString());
		int rating = Integer.parseInt(spinnerRating.getValue().toString());
		return new HouseExpContor(name, number, rating);
	}
}
