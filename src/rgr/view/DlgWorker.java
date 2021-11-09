package rgr.view;

import javax.swing.JLabel;
import javax.swing.JSpinner;

import rgr.model.Worker;

import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SpinnerNumberModel;

//клас діалогу Робітник, який успадковує загальний діалог
public class DlgWorker extends Dlg {
	private JSpinner spinnerAge;
	private JCheckBox chckbxNewCheckBox;
	private Component horizontalStrut;
	
	public DlgWorker(Object data) {
		this();
		Worker w = (Worker) data;
		textField.setText(w.toString());
		spinnerAge.setValue(w.getAge());
		chckbxNewCheckBox.setSelected(w.isBestWorker());
	}

	public DlgWorker() {
		setBounds(277,137,277,137);
		textField.setColumns(25);
		setTitle("Робітник");
		
		nameLabel.setText("ПІБ");
		
		JLabel lblNewLabel = new JLabel("Вік");
		contentPanel.add(lblNewLabel);
		
		spinnerAge = new JSpinner();
		spinnerAge.setModel(new SpinnerNumberModel(20, 16, 65, 1));
		contentPanel.add(spinnerAge);
		
		horizontalStrut = Box.createHorizontalStrut(50);
		contentPanel.add(horizontalStrut);
		
		chckbxNewCheckBox = new JCheckBox("Найкращий");
		contentPanel.add(chckbxNewCheckBox);
	}

	@Override
	public Object createObject() throws Exception {
		if(!ok) return null;
		String name = textField.getText();
		int age = Integer.parseInt(spinnerAge.getValue().toString());
		boolean bestWorker = chckbxNewCheckBox.isSelected();
		return new Worker(name, age, bestWorker);
	}

}
