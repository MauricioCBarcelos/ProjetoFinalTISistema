package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GerenciadorCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
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
		setTitle("Gerenciamento De Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);

		textField = new JTextField();
		textField.setBounds(10, 31, 189, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(209, 11, 46, 14);
		contentPane.add(lblCpf);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(209, 31, 115, 20);
		contentPane.add(textField_1);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 61, 46, 14);
		contentPane.add(lblTelefone);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 79, 96, 20);
		contentPane.add(textField_2);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(117, 62, 46, 14);
		contentPane.add(lblCelular);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(116, 79, 96, 20);
		contentPane.add(textField_3);

		JButton btnNewButton = new JButton("Salvar\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(219, 62, 89, 39);
		contentPane.add(btnNewButton);

		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setBounds(10, 110, 46, 14);
		contentPane.add(lblPesquisar);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 123, 189, 20);
		contentPane.add(textField_4);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "codigo", "nome", "telefone", "celular", "cpf" }));
		comboBox.setBounds(209, 123, 102, 20);
		contentPane.add(comboBox);

		table = new JTable();
		table.setBounds(10, 154, 322, 176);
		table.setModel(new DefaultTableModel(new String[][] { { "Código", "Nome", "Telefone", "Celular", "CPF" }, },
				new String[] { "Código", "Nome", "Telefone", "Celular", "CPF" }));
		contentPane.add(table);

		JButton btnNewButton_2 = new JButton("Excluir\r\n");
		btnNewButton_2.setBounds(342, 276, 89, 29);
		contentPane.add(btnNewButton_2);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAlterar.setBounds(342, 208, 89, 29);
		contentPane.add(btnAlterar);
	}
}
