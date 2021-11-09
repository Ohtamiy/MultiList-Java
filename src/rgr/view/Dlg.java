package rgr.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// ����������� ���� ������, ���� ��������� JDialog � ���� ����������� ��� ��� �������
public abstract class Dlg extends JDialog {
	// ����, �� ������ �������� ��� ��� ������� ������
	protected final JPanel contentPanel = new JPanel();
	protected JTextField textField;
	protected JLabel nameLabel;
	protected JButton okButton;
	protected JButton cancelButton;
	// ��������� �������� ��� ��������� ����������, ��� ������ ���� ���������
	protected boolean ok;
	// ����������� ����� ��� ��������� ��'���� �������� ����
	public abstract Object createObject() throws Exception;
	
	/**
	 * Create the dialog.
	 */
	public Dlg() {
		setModal(true);
		setBounds(100, 100, 261, 124);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			nameLabel = new JLabel("�����");
			contentPanel.add(nameLabel);
		}
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// ��������� "OK"
						ok = true;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// ��������� "Cancel"
						ok = false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	// ����� ��� ������������ ��������� �����������
	public void setEditable(boolean b ) {
		// ������ "OK" ���� ������������, ���� ����������� �������
		okButton.setVisible(b);
		// ���� ����������� ������, �������� ������ "Cancel"
		if(b) cancelButton.setText("Cancel");
		// � ������ �������
		else cancelButton.setText("Exit");
		// ������� ������ ������ �� ����������
		for(Component c: contentPanel.getComponents())
			c.setEnabled(b);
	}

	// ��� ����������� ������� "Expose components" �������� ��� ������
	protected JButton getOkButton() { return okButton; }
	protected JButton getCancelButton() { return cancelButton; }
	protected JTextField getTextField() { return textField; }
	protected JLabel getNameLabel() { return nameLabel; }
}
