package rgr.model;

import rgr.view.Dlg;
import rgr.view.DlgProfession;
import rgr.view.DlgWorker;

//Клас Професія, успадковує абстрактний клас
public class Profession extends AnyData {
	int salary;
	int hoursPerDay;
	
	public Profession(String name, int salary, int hoursPerDay) throws Exception {
		if(hoursPerDay < 4 || hoursPerDay > 16)
			throw new Exception("Incorrect hours per day");
		this.name = name;
		this.salary = salary;
		this.hoursPerDay = hoursPerDay;
	}
	
	public int getSalary() { return salary; }
	public int getHoursPerDay() { return hoursPerDay; }

	@Override
	public Dlg showDialog(boolean b) {
		DlgProfession d = new DlgProfession(this);
		d.setEditable(b);
		d.setVisible(true);
		return d;
	}
	
	@Override
	public Dlg showSonDialog() {
		DlgWorker d = new DlgWorker();
		d.setVisible(true);
		return d;
	}
}
