package rgr.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import rgr.model.CityCommune;

// клас діалогу Міськкомунхоз, який успадковує загальний діалог
public class DlgCityCommune extends Dlg {
	// допоміжні поля
	private JTextField textFieldCity;
	private JTextField textFieldChief;
	
	// конструктор, який приймає обраний користувачем об'єкт
	public DlgCityCommune(Object data) {
		// конструктор суперкласу
		this();
		// приводимо отримані дані до типу обраного користувачем
		CityCommune cc = (CityCommune) data;
		// заповнюємо поля діалогу
		textField.setText(cc.toString());
		textFieldCity.setText(cc.getCity());
		textFieldChief.setText(cc.getChief());
	}

	// конструктор діалогу для встановлення графічних настроювань
	public DlgCityCommune() {
		setBounds(300,155,300,155);
		setTitle("Міськкомунхоз");
		textField.setColumns(27);
		
		JLabel lblNewLabel = new JLabel("Місто");
		contentPanel.add(lblNewLabel);
		
		textFieldCity = new JTextField();
		contentPanel.add(textFieldCity);
		textFieldCity.setColumns(27);
		
		JLabel lblNewLabel_1 = new JLabel("Начальник");
		contentPanel.add(lblNewLabel_1);
		
		textFieldChief = new JTextField();
		contentPanel.add(textFieldChief);
		textFieldChief.setColumns(25);
	}

	// метод для створення об'єкту
	@Override
	public Object createObject() throws Exception {
		// якщо редагування заборонено
		if(!ok) return null;
		// отримуємо дані з полів
		String name = textField.getText();
		String city = textFieldCity.getText();
		String chief = textFieldChief.getText();
		// повертаємо створений об'єкт
		return new CityCommune(name, city, chief);
	}
}
