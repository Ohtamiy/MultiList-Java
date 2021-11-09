package rgr.model;

import rgr.view.Dlg;
import rgr.view.DlgWorker;

//Клас Робітник, успадковує абстрактний клас
public class Worker extends AnyData {
	int age;
	boolean bestWorker;
	
	public Worker(String name, int age, boolean bestWorker) throws Exception {
		if(age < 16 || age > 60)
			throw new Exception("Incorrect age");
		this.name = name;
		this.age = age;
		this.bestWorker = bestWorker;
	}
	
	public int getAge() { return age; }
	public boolean isBestWorker() { return bestWorker; }

	@Override
	public Dlg showDialog(boolean b) {
		DlgWorker d = new DlgWorker(this);
		d.setEditable(b);
		d.setVisible(true);
		return d;
	}
	
	@Override
	public Dlg showSonDialog() {
		return null;
	}
}
