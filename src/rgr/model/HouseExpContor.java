package rgr.model;

import rgr.view.Dlg;
import rgr.view.DlgHouseExpContor;
import rgr.view.DlgProfession;

// ���� ���, ��������� ����������� ����
public class HouseExpContor extends AnyData {
	int number;
	int rating;
	
	// ����������� �������� ����������� �������� �����
	public HouseExpContor(String name, int number, int rating) throws Exception {
		if(rating < 1 || rating > 5) // ��������
			throw new Exception("Incorrect rating");
		this.name = name;
		this.number = number;
		this.rating = rating;
	}
	
	public int getNumber() { return number; }
	public int getRating() { return rating; }

	@Override
	public Dlg showDialog(boolean b) {
		DlgHouseExpContor d = new DlgHouseExpContor(this);
		d.setEditable(b);
		d.setVisible(true);
		return d;
	}
	
	@Override
	public Dlg showSonDialog() {
		DlgProfession d = new DlgProfession();
		d.setVisible(true);
		return d;
	}
}
