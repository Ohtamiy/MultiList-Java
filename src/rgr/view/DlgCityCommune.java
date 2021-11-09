package rgr.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import rgr.model.CityCommune;

// ���� ������ ̳�����������, ���� ��������� ��������� �����
public class DlgCityCommune extends Dlg {
	// ������� ����
	private JTextField textFieldCity;
	private JTextField textFieldChief;
	
	// �����������, ���� ������ ������� ������������ ��'���
	public DlgCityCommune(Object data) {
		// ����������� ����������
		this();
		// ��������� ������� ��� �� ���� �������� ������������
		CityCommune cc = (CityCommune) data;
		// ���������� ���� ������
		textField.setText(cc.toString());
		textFieldCity.setText(cc.getCity());
		textFieldChief.setText(cc.getChief());
	}

	// ����������� ������ ��� ������������ ��������� �����������
	public DlgCityCommune() {
		setBounds(300,155,300,155);
		setTitle("̳�����������");
		textField.setColumns(27);
		
		JLabel lblNewLabel = new JLabel("̳���");
		contentPanel.add(lblNewLabel);
		
		textFieldCity = new JTextField();
		contentPanel.add(textFieldCity);
		textFieldCity.setColumns(27);
		
		JLabel lblNewLabel_1 = new JLabel("���������");
		contentPanel.add(lblNewLabel_1);
		
		textFieldChief = new JTextField();
		contentPanel.add(textFieldChief);
		textFieldChief.setColumns(25);
	}

	// ����� ��� ��������� ��'����
	@Override
	public Object createObject() throws Exception {
		// ���� ����������� ����������
		if(!ok) return null;
		// �������� ��� � ����
		String name = textField.getText();
		String city = textFieldCity.getText();
		String chief = textFieldChief.getText();
		// ��������� ��������� ��'���
		return new CityCommune(name, city, chief);
	}
}
