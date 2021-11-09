package rgr.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import rgr.model.AnyData;
import rgr.model.CityCommune;
import rgr.model.HouseExpContor;
import rgr.model.Profession;
import rgr.model.Worker;

import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

// ���� ��������� ����, ��������� JFrame
public class MainGui extends JFrame {
	// ���� ������� ����
	private JPanel contentPane;
	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainGui frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					// ������������ �������� ������
					tree.setModel(getStartModel());
					// ���������� ����� 
					for(int i = 0; i < tree.getRowCount(); i++)
						tree.expandRow(i);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});		
		setTitle("\u0420\u0413\u0420 \u041C\u0456\u0441\u044C\u043A\u043A\u043E\u043C\u0443\u043D\u0445\u043E\u0437");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{291, 0, 0};
		gbl_contentPane.rowHeights = new int[]{186, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onMouseClicked(e);
			}
		});
		scrollPane.setViewportView(tree);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnAdd = new JButton("������");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onAdd();
			}
		});
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("��������");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onRemove();
			}
		});
		panel.add(btnRemove);
		
		JButton btnEdit = new JButton("����������");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onEdit();
			}
		});
		panel.add(btnEdit);
		
		JButton btnStore = new JButton("��������");
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onStore();
			}
		});
		panel.add(btnStore);
		
		JButton btnRestore = new JButton("³�������");
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onRestore();
			}
		});
		panel.add(btnRestore);
		
		JButton btnCalculate = new JButton("��������");
		panel.add(btnCalculate);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(btnCalculate, popupMenu);
		
		JMenuItem btnNumberOf = new JMenuItem("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043F\u0440\u0430\u0446\u0456\u0432\u043D\u0438\u043A\u0456\u0432 \u043C\u0456\u0441\u044F\u0446\u044F");
		btnNumberOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onNumber();
			}
		});
		popupMenu.add(btnNumberOf);
		
		JMenuItem btnAverage = new JMenuItem("\u0421\u0435\u0440\u0435\u0434\u043D\u044F \u0437\u0430\u0440\u043E\u0431\u0456\u0442\u043D\u044F \u043F\u043B\u0430\u0442\u0430 \u0436\u0435\u043A\u0443");
		btnAverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onAverage();
			}
		});
		popupMenu.add(btnAverage);
	}

	// ����� ��������� �������� ��������� ����� ����
	protected void onAverage() {
		// �������� ������� �����
		DefaultMutableTreeNode hec = getSelectedNode();
		if(hec == null) return;
		int average = 0, number = 0;
		// �������� ���� �������� �����
		Enumeration<TreeNode> enm = hec.postorderEnumeration();
		// ���� � ������� ��������
		while(enm.hasMoreElements()) {
			// �������� ��������� �������
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) enm.nextElement();
			// �������� ����������
			Object data = currentNode.getUserObject();
			// ���� ������ �� ������� ����� �������
			if(!(data instanceof Profession)) 
				continue;
			// ������ ������� �������� �����
			average += ((Profession) data).getSalary();
			number++;
		}
		// �������� ������� �������� �����
		average /= number;
		System.out.println("������� �/� �� ���� -> " + hec + " ������� " + average);
	}

	// ����� ���������� ����������� �������� �����
	protected void onNumber() {
		// �������� ������� �����
		DefaultMutableTreeNode node = getSelectedNode();
		if(node == null) return;
		int number = 0, maxAge = 0;
		// �������� ����� ��� �������� ����������
		DefaultMutableTreeNode oldest = null;
		// �������� ���� �������� �����
		Enumeration<TreeNode> enm = node.postorderEnumeration();
		// ���� � ������� ��������
		while(enm.hasMoreElements()) {
			// �������� ��������� �������
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) enm.nextElement();
			// �������� ����������
			Object data = currentNode.getUserObject();
			// ���� ������ �� ������� ����� �������
			if(!(data instanceof Worker)) 
				continue;
			// ���� �� ������� �����
			if(((Worker) data).isBestWorker()) {
				// + ������� �����
				number++;
				// �������� ���� ��
				int age = ((Worker) data).getAge();
				// ���� �� ����� ����������
				if(age > maxAge) {
					maxAge = age;
					oldest = currentNode;
				}
			}
		}
		if(oldest == null) {
			System.out.println("���� �����");
			return;
		}
		System.out.println("ʳ������ �������� ����� -> " + number + ", �� ���������� �: " + oldest);
		selectNode(oldest);
		((AnyData) oldest.getUserObject()).showDialog(false);
	}

	// ����� ����������
	protected void onRestore() {
		// ����� ������ �����
		FileDialog fileDialog = new FileDialog(this);
		// ����� ������������
		fileDialog.setMode(FileDialog.LOAD);
		// �������� �����
		fileDialog.setVisible(true);
		String dr = fileDialog.getDirectory();
		String fn = fileDialog.getFile();
		if(dr == null || fn == null) return;
		// ��������� � ����
		String fName = dr + fn;
		try {
			// ��������� ��'���, ���� ���� ������������� ��� �� ������ ���� � ��'���
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fName));
			// ������� ������
			TreeModel model = (TreeModel) in.readObject();
			// ������������ ������ ������
			tree.setModel(model);
			// ��������� 
			in.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(tree, "������� ������������ ������", "������������", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ���������� �����
		expandAll();
	}

	// ����� ���������� 
	protected void onStore() {
		// ���� ������ ������
		if(tree.getModel() == null) return;
		// ��������� ����� �������� �����
		JFileChooser fileChooser = new JFileChooser("���������� ����� ������");
		fileChooser.showSaveDialog(this);
		try {
			// ������ ����
			File f = fileChooser.getSelectedFile();
			// �������� ����
			String fName = f.getAbsolutePath();
			// ��������� ��'���, ���� ���� ���������� ��� � ����
			FileOutputStream fileStream = new FileOutputStream(fName);
			// ��������� ��'���, ���� ���� ������������� ��� � ���� ����� 
			ObjectOutputStream out = new ObjectOutputStream(fileStream);
			// �������� ������ ������
			out.writeObject(tree.getModel());
			// ��������� ����
			out.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(tree, "������� �������� �����", "���������� ������ � ����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ������������ ������ ������
		tree.setModel(((new JTree()).getModel()));
	}

	// ����� �����������
	protected void onEdit() {
		// �������� ������� �����
		DefaultMutableTreeNode node = getSelectedNode();
		if(node == null) return;
		// �������� ���������� ������� �����
		AnyData data = (AnyData) node.getUserObject();
		// �������� ����� ��������� �����
		Dlg dlg = data.showDialog(true);
		Object obj;
		try {
			// �������� ��������� ��'���
			obj = dlg.createObject();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(tree, e.getMessage());
			return;
		}
		((JDialog) dlg).dispose();
		if(obj == null) return;
		// ��������� ���������� �����
		node.setUserObject(obj);
		// ��������� ������
		tree.updateUI();
	}

	// ����� ���������
	protected void onRemove() {
		// �������� ������� �����
		DefaultMutableTreeNode node = getSelectedNode();
		if(node == null) return;
		// ���������
		node.removeFromParent();
		// ��������� ��������
		tree.setSelectionPath(null);
		// ��������� ������
		tree.updateUI();
	}

	// ����� ��������� �����
	protected void onAdd() {
		// �������� ������� �����
		DefaultMutableTreeNode parent = getSelectedNode();
		// ���� �� �������
		if(parent == null) return;
		// �������� ���������� ������� �����
		AnyData parentData = (AnyData) parent.getUserObject();
		// �������� ����� ������ ��������� �����
		Dlg dlg = parentData.showSonDialog();
		// ���� �� ������� ������� �����
		if(dlg == null) return;
		// ��������� ��'���
		Object obj;
		try {
			// �������� ��������� ��'���
			obj = dlg.createObject();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(tree, e.getMessage());
			return;
		}
		((JDialog) dlg).dispose();
		if(obj == null) return;
		// ��������� ����� ����� � ������
		DefaultMutableTreeNode newSon = new DefaultMutableTreeNode(obj);
		// ������ ������ �� �������� �����
		parent.add(newSon);
		// ��������� ������
		tree.updateUI();
		// ���������� �� �����
		expandAll();
	}

	// ��������� ��������� "Expose components"
	public JTree getTree() { return tree; }
	
	// ����� ��� ��������� ����� ������ � ������ ����� ��� ������ setModel()
	protected TreeModel getStartModel() throws Exception {
		// ��������� ��'���� ��� ����� ������
		CityCommune cc = new CityCommune("��������� ������������ ������������", "�������", "������ ��������� ����������");
		HouseExpContor h = new HouseExpContor("�������������� 7", 1, 3);
		Profession p = new Profession("���������", 6000, 8);
		Worker w = new Worker("������ �.�.", 29, false);
		// ��������� ����� ������
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(cc);
		DefaultMutableTreeNode hNod = new DefaultMutableTreeNode(h);
		DefaultMutableTreeNode pNod = new DefaultMutableTreeNode(p);
		DefaultMutableTreeNode wNod = new DefaultMutableTreeNode(w);
		// ��'������ �����
		root.add(hNod); hNod.add(pNod); pNod.add(wNod);
		// ��������� �������� ������
		return (new JTree(root)).getModel();
	}
	
	// ����� ��������� �������� �����
	private DefaultMutableTreeNode getSelectedNode() {
		// �������� ������� ������� ����
		Object selectNode = tree.getLastSelectedPathComponent();
		// ���� �� �������
		if(selectNode == null) return null;
		// ��������� ������� �����
		return (DefaultMutableTreeNode) selectNode;
	}
	
	// ����� ����������� ��� �����
	private void expandAll() {
		// ������� ������������
		for(int i = 0; i < tree.getRowCount(); i++)
			// ����������
			tree.expandRow(i);
	}
	
	// ����� �������� �����
	private void selectNode(DefaultMutableTreeNode node) {
		int n = 0; 
		// �������� ����� ����� ������ 
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
		// �������� ������ ���� ������
		Enumeration<TreeNode> enm = root.children();
		// ���� ������ �� ������� ��������
		while(enm.hasMoreElements()) {
			// �������� ��������� �������
			DefaultMutableTreeNode nod = (DefaultMutableTreeNode) enm.nextElement();
			// ���� ������������ ������� == ������
			if(nod == node) {
				// ������������ ������� �����
				tree.setSelectionRow(n);
				return;
			}
			// ����� ++
			n++;
		}
	}
	
	// ����� ��� ����������� ������ ��� ��������� ����������� �� �������� ��� ����
	private void onMouseClicked(MouseEvent e) {
		// ���������� ������� ���������� 
		if(e.getClickCount() != 3 || e.getButton() != MouseEvent.BUTTON3) return;
		// �������� ������� �����
		DefaultMutableTreeNode node = getSelectedNode();
		// ���� �� �������
		if(node == null) return;
		// �������� ����� ���
		AnyData data = (AnyData) node.getUserObject();
		// �������� ����� ��� ��������� ����������� 
		Dlg dlg = data.showDialog(false);
		((JDialog) dlg).dispose();
	}
	
	// ����� ������������ ����
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				showMenu(e);
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
