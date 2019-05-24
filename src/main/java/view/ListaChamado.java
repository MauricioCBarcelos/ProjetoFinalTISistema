package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ListaChamado extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaChamado frame = new ListaChamado();
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
	public ListaChamado() {
		setTitle("Lista De Chamado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPesquisar = new JLabel("Pesquisar ");
		lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPesquisar.setBounds(10, 11, 70, 14);
		contentPane.add(lblPesquisar);

		textField = new JTextField();
		textField.setBounds(10, 26, 163, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "codigo", "cpf", "nome", "telefone" }));
		comboBox.setBounds(183, 26, 141, 20);
		contentPane.add(comboBox);

		table = new JTable();
		table.setBounds(0, 57, 594, 280);
		table.setModel(new DefaultTableModel(
				new String[][] {
						{ "Código", "Cliente", "Telefone", "Status", "Categoria Equipamento", "Valor Total", }, },
				new String[] { "Código", "Cliente", "Telefone", "Status", "Categoria Equipamento", "Valor Total", }));
		contentPane.add(table);

	}
}
