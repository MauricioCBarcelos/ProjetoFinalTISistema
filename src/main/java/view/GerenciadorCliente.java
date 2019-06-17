package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class GerenciadorCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textnome;
	private JTextField textcpf;
	private JTextField texttelefone2;
	private JTextField texttelefone1;
	private JTextField textpesquisar;
	private JComboBox comboBox;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadorCliente frame = new GerenciadorCliente();
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
	public GerenciadorCliente() {
		setTitle("Gerenciador De Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 435, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		textnome = new JTextField();
		textnome.setBounds(10, 30, 172, 20);
		contentPane.add(textnome);
		textnome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(209, 11, 46, 14);
		contentPane.add(lblCpf);
		
		textcpf = new JTextField();
		textcpf.setBounds(209, 30, 154, 20);
		contentPane.add(textcpf);
		textcpf.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone 1");
		lblTelefone.setBounds(10, 61, 77, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblTelefone2 = new JLabel("Telefone 2");
		lblTelefone2.setBounds(115, 61, 56, 14);
		contentPane.add(lblTelefone2);
		
		texttelefone2 = new JTextField();
		texttelefone2.setBounds(115, 86, 95, 20);
		contentPane.add(texttelefone2);
		texttelefone2.setColumns(10);
		
		texttelefone1 = new JTextField();
		texttelefone1.setColumns(10);
		texttelefone1.setBounds(10, 86, 95, 20);
		contentPane.add(texttelefone1);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(10, 118, 95, 14);
		contentPane.add(lblPesquisar);
		
		textpesquisar = new JTextField();
		textpesquisar.setBounds(10, 143, 217, 20);
		contentPane.add(textpesquisar);
		textpesquisar.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"codigo", "cpf", "nome", "telefone"}));
		comboBox.setBounds(237, 143, 142, 20);
		contentPane.add(comboBox);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"nome"
			}
		));
		table.setBounds(10, 185, 373, 213);
		contentPane.add(table);
	}
}
