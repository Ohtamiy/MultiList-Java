package rgr.model;

import rgr.view.Dlg;
import rgr.view.DlgCityCommune;
import rgr.view.DlgHouseExpContor;

// Клас Міськкомунхоз, успадковує абстрактний клас
public class CityCommune extends AnyData {
	// інформаційні поля
	String city;
	String chief;
	
	// конструктор класу; взаємодіє зі спільним полем
	public CityCommune(String name, String city, String chief){
		this.name = name;
		this.city = city;
		this.chief = chief;
	}
	
	// гетери
	public String getCity() { return city; }
	public String getChief() { return chief; }

	// метод виведення діалогу
	@Override
	public Dlg showDialog(boolean b) {
		// оголошуємо діалог, батьком якого є поточний
		DlgCityCommune d = new DlgCityCommune(this);
		// дозволяємо чи забороняємо змінювати дані
		d.setEditable(b);
		// показуємо діалог
		d.setVisible(true);
		// повертаємо оголошений діалог
		return d;
	}
	
	// метод виведення діалогу "дитини"
	@Override
	public Dlg showSonDialog() {
		// оголошуємо діалог
		DlgHouseExpContor d = new DlgHouseExpContor();
		// показуємо діалог
		d.setVisible(true);
		// повертаємо оголошений діалог
		return d;
	}
}
