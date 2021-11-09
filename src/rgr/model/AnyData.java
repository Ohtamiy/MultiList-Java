package rgr.model;

import java.io.Serializable;

import rgr.view.Dlg;

// ����������� ����, ���� � ����������� ��� �����
// ���� ����������������� �� ��� ��� ��'���� ����� ����������
public abstract class AnyData implements Serializable {
	// ������ ���� � ����������
	protected String name;
	
	// ��������� �����
	public abstract Dlg showDialog(boolean b);
	public abstract Dlg showSonDialog();

	// ����� ���������� ���� �����
	@Override
	public String toString() { return name; }
}