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

// клас головного вікна, успадковує JFrame
public class MainGui extends JFrame {
	// деякі допоміжні поля
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
					// встановлюємо отриману модель
					tree.setModel(getStartModel());
					// розкриваємо вузли 
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
		
		JButton btnAdd = new JButton("Додати");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onAdd();
			}
		});
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Видалити");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onRemove();
			}
		});
		panel.add(btnRemove);
		
		JButton btnEdit = new JButton("Редагувати");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onEdit();
			}
		});
		panel.add(btnEdit);
		
		JButton btnStore = new JButton("Зберегти");
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onStore();
			}
		});
		panel.add(btnStore);
		
		JButton btnRestore = new JButton("Відновити");
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onRestore();
			}
		});
		panel.add(btnRestore);
		
		JButton btnCalculate = new JButton("Операції");
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

	// метод підрахунку середньої заробітньої плати жеку
	protected void onAverage() {
		// отримуємо обраний вузел
		DefaultMutableTreeNode hec = getSelectedNode();
		if(hec == null) return;
		int average = 0, number = 0;
		// отримуємо дітей обраного вузла
		Enumeration<TreeNode> enm = hec.postorderEnumeration();
		// поки є наступні елементи
		while(enm.hasMoreElements()) {
			// отримуємо наступний елемент
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) enm.nextElement();
			// отрмиуємо інформацію
			Object data = currentNode.getUserObject();
			// якщо дитина не відповідає класу Професія
			if(!(data instanceof Profession)) 
				continue;
			// сумуємо середню заробітню плату
			average += ((Profession) data).getSalary();
			number++;
		}
		// отримуємо середню заробітню плату
		average /= number;
		System.out.println("Середня з/п на жеку -> " + hec + " дорівнює " + average);
	}

	// метод визначення найстаршого робітника місяця
	protected void onNumber() {
		// отримуємо обраний вузел
		DefaultMutableTreeNode node = getSelectedNode();
		if(node == null) return;
		int number = 0, maxAge = 0;
		// допоміжна змінна для фіксації настаршого
		DefaultMutableTreeNode oldest = null;
		// отримуємо дітей обраного вузла
		Enumeration<TreeNode> enm = node.postorderEnumeration();
		// поки є наступні елементи
		while(enm.hasMoreElements()) {
			// отримуємо наступний елемент
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) enm.nextElement();
			// отрмиуємо інформацію
			Object data = currentNode.getUserObject();
			// якщо дитина не відповідає класу Робітник
			if(!(data instanceof Worker)) 
				continue;
			// якщо це робітник місяця
			if(((Worker) data).isBestWorker()) {
				// + робітник місяця
				number++;
				// отримуємо його вік
				int age = ((Worker) data).getAge();
				// якщо він зараз найстарший
				if(age > maxAge) {
					maxAge = age;
					oldest = currentNode;
				}
			}
		}
		if(oldest == null) {
			System.out.println("Немає таких");
			return;
		}
		System.out.println("Кількість робітників місяця -> " + number + ", де найстаршим є: " + oldest);
		selectNode(oldest);
		((AnyData) oldest.getUserObject()).showDialog(false);
	}

	// метод відновлення
	protected void onRestore() {
		// діалог вибору файлу
		FileDialog fileDialog = new FileDialog(this);
		// метод завантаження
		fileDialog.setMode(FileDialog.LOAD);
		// показуємо діалог
		fileDialog.setVisible(true);
		String dr = fileDialog.getDirectory();
		String fn = fileDialog.getFile();
		if(dr == null || fn == null) return;
		// директорія і файл
		String fName = dr + fn;
		try {
			// оголошуємо об'єкт, який буде перетворювати дані із потоку байт в об'єкт
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fName));
			// зчитуємо модель
			TreeModel model = (TreeModel) in.readObject();
			// встановлюємо модель дереву
			tree.setModel(model);
			// закриваємо 
			in.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(tree, "Помилка десеріалізації дерева", "Десеріалізація", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// розкриваємо вузли
		expandAll();
	}

	// метод збереження 
	protected void onStore() {
		// якщо дерево порожнє
		if(tree.getModel() == null) return;
		// відкриваємо діалог обирання файлу
		JFileChooser fileChooser = new JFileChooser("Серіалізація моделі дерева");
		fileChooser.showSaveDialog(this);
		try {
			// обрали файл
			File f = fileChooser.getSelectedFile();
			// отрмиали шлях
			String fName = f.getAbsolutePath();
			// оголошуємо об'єкт, який буде переносити дані у файл
			FileOutputStream fileStream = new FileOutputStream(fName);
			// оголошуємо об'єкт, який буде перетворювати дані у потік байтів 
			ObjectOutputStream out = new ObjectOutputStream(fileStream);
			// записуємо модель дерева
			out.writeObject(tree.getModel());
			// закриваємо потік
			out.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(tree, "Помилка відкриття файлу", "Збереження дерева у файл", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// встановлюємо модель дереву
		tree.setModel(((new JTree()).getModel()));
	}

	// метод редагування
	protected void onEdit() {
		// отримуємо обраний вузел
		DefaultMutableTreeNode node = getSelectedNode();
		if(node == null) return;
		// отримуємо інформацію обраних даних
		AnyData data = (AnyData) node.getUserObject();
		// отримуємо діалог поточного вузла
		Dlg dlg = data.showDialog(true);
		Object obj;
		try {
			// отримуємо створений об'єкт
			obj = dlg.createObject();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(tree, e.getMessage());
			return;
		}
		((JDialog) dlg).dispose();
		if(obj == null) return;
		// оновлюємо інформацію вузла
		node.setUserObject(obj);
		// оновлюємо дерево
		tree.updateUI();
	}

	// метод видалення
	protected void onRemove() {
		// отримуємо обраний вузел
		DefaultMutableTreeNode node = getSelectedNode();
		if(node == null) return;
		// видаляємо
		node.removeFromParent();
		// прибираємо виділення
		tree.setSelectionPath(null);
		// оновлюємо дерево
		tree.updateUI();
	}

	// метод додавання вузлів
	protected void onAdd() {
		// отримуємо обраний вузел
		DefaultMutableTreeNode parent = getSelectedNode();
		// якщо не обраний
		if(parent == null) return;
		// отримуємо інформацію обраних даних
		AnyData parentData = (AnyData) parent.getUserObject();
		// отримуємо діалог дитини поточного вузла
		Dlg dlg = parentData.showSonDialog();
		// якщо не можливо відкрити діалог
		if(dlg == null) return;
		// оголошуємо об'єкт
		Object obj;
		try {
			// отримуємо створений об'єкт
			obj = dlg.createObject();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(tree, e.getMessage());
			return;
		}
		((JDialog) dlg).dispose();
		if(obj == null) return;
		// створюємо новий вузел з даними
		DefaultMutableTreeNode newSon = new DefaultMutableTreeNode(obj);
		// додаємо дитину до обраного вузла
		parent.add(newSon);
		// оновлюємо дерево
		tree.updateUI();
		// розкриваємо усі вузли
		expandAll();
	}

	// результат виконання "Expose components"
	public JTree getTree() { return tree; }
	
	// метод для створення вузлів дерева у вигляді моделі для методу setModel()
	protected TreeModel getStartModel() throws Exception {
		// створення об'єктів для вузлів дерева
		CityCommune cc = new CityCommune("Управління комунального господарства", "Житомир", "Марцун Олександр Васильович");
		HouseExpContor h = new HouseExpContor("Чернишевського 7", 1, 3);
		Profession p = new Profession("Бухгалтер", 6000, 8);
		Worker w = new Worker("Іванов В.В.", 29, false);
		// створюємо вузли дерева
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(cc);
		DefaultMutableTreeNode hNod = new DefaultMutableTreeNode(h);
		DefaultMutableTreeNode pNod = new DefaultMutableTreeNode(p);
		DefaultMutableTreeNode wNod = new DefaultMutableTreeNode(w);
		// об'єднуємо вузли
		root.add(hNod); hNod.add(pNod); pNod.add(wNod);
		// повертаємо створену модель
		return (new JTree(root)).getModel();
	}
	
	// метод отримання обраного вузла
	private DefaultMutableTreeNode getSelectedNode() {
		// отримуємо останній обраний шлях
		Object selectNode = tree.getLastSelectedPathComponent();
		// якщо не обраний
		if(selectNode == null) return null;
		// повертаємо обраний вузел
		return (DefaultMutableTreeNode) selectNode;
	}
	
	// метод розгорнення усіх вузлів
	private void expandAll() {
		// циклічно переглядаемо
		for(int i = 0; i < tree.getRowCount(); i++)
			// розгортаємо
			tree.expandRow(i);
	}
	
	// метод виділення вузла
	private void selectNode(DefaultMutableTreeNode node) {
		int n = 0; 
		// отримуємо корінь моделі дерева 
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
		// отримуємо список дітей кореня
		Enumeration<TreeNode> enm = root.children();
		// поки список має наступні елементи
		while(enm.hasMoreElements()) {
			// отримуємо наступний елемент
			DefaultMutableTreeNode nod = (DefaultMutableTreeNode) enm.nextElement();
			// якщо переглядаємий елемент == обрано
			if(nod == node) {
				// встановлюємо обраний рядок
				tree.setSelectionRow(n);
				return;
			}
			// рядок ++
			n++;
		}
	}
	
	// метод для відображення діалогу без можливості редагування на потрійний клік миші
	private void onMouseClicked(MouseEvent e) {
		// перевіряємо потрійне натискання 
		if(e.getClickCount() != 3 || e.getButton() != MouseEvent.BUTTON3) return;
		// отримуємо обраний вузол
		DefaultMutableTreeNode node = getSelectedNode();
		// якщо не обраний
		if(node == null) return;
		// отримуємо обрані дані
		AnyData data = (AnyData) node.getUserObject();
		// виводимо діалог без можливості редагування 
		Dlg dlg = data.showDialog(false);
		((JDialog) dlg).dispose();
	}
	
	// метод вспливаючого меню
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
