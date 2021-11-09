package rgr.model;

import rgr.view.Dlg;
import rgr.view.DlgCityCommune;
import rgr.view.DlgHouseExpContor;

// ���� ̳�����������, ��������� ����������� ����
public class CityCommune extends AnyData {
	// ����������� ����
	String city;
	String chief;
	
	// ����������� �����; �����䳺 � ������� �����
	public CityCommune(String name, String city, String chief){
		this.name = name;
		this.city = city;
		this.chief = chief;
	}
	
	// ������
	public String getCity() { return city; }
	public String getChief() { return chief; }

	// ����� ��������� ������
	@Override
	public Dlg showDialog(boolean b) {
		// ��������� �����, ������� ����� � ��������
		DlgCityCommune d = new DlgCityCommune(this);
		// ���������� �� ����������� �������� ���
		d.setEditable(b);
		// �������� �����
		d.setVisible(true);
		// ��������� ���������� �����
		return d;
	}
	
	// ����� ��������� ������ "������"
	@Override
	public Dlg showSonDialog() {
		// ��������� �����
		DlgHouseExpContor d = new DlgHouseExpContor();
		// �������� �����
		d.setVisible(true);
		// ��������� ���������� �����
		return d;
	}
}
