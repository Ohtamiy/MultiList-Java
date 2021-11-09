package rgr.view;

import rgr.model.Profession;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

//клас діалогу Професія, який успадковує загальний діалог
public class DlgProfession extends Dlg {
	private JSpinner spinnerSalary;
	private JSpinner spinnerHours;
	
	public DlgProfession(Object data) {
		this();
		Profession p = (Profession) data;
		spinnerSalary.setValue(p.getSalary());
		textField.setText(p.toString());
		spinnerHours.setValue(p.getHoursPerDay());
	}
	
	public DlgProfession() {
		setBounds(222,158,222,158);
		setTitle("Професія");
		textField.setColumns(15);
		
		JLabel lblNewLabel = new JLabel("Заробітня плата");
		contentPanel.add(lblNewLabel);
		
		spinnerSalary = new JSpinner();
		spinnerSalary.setModel(new SpinnerNumberModel(4500, 4500, 10000, 100));
		contentPanel.add(spinnerSalary);
		
		JLabel lblNewLabel_1 = new JLabel("Робочий день(години)");
		contentPanel.add(lblNewLabel_1);
		
		spinnerHours = new JSpinner();
		spinnerHours.setModel(new SpinnerNumberModel(8, 4, 16, 1));
		contentPanel.add(spinnerHours);
	}

	@Override
	public Object createObject() throws Exception {
		if(!ok) return null;
		String name = textField.getText();
		int salary = Integer.parseInt(spinnerSalary.getValue().toString());
		int hours = Integer.parseInt(spinnerHours.getValue().toString());
		return new Profession(name, salary, hours);
	}
}
