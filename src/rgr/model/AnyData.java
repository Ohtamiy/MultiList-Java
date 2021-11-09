package rgr.model;

import java.io.Serializable;

import rgr.view.Dlg;

// Асбтрактний клас, який є батьківським для інших
// буде використовуватися як тип для об'єктів класів спадкоємців
public abstract class AnyData implements Serializable {
	// спільне поле у спадкоємців
	protected String name;
	
	// абстрактні класи
	public abstract Dlg showDialog(boolean b);
	public abstract Dlg showSonDialog();

	// метод повернення поля класу
	@Override
	public String toString() { return name; }
}