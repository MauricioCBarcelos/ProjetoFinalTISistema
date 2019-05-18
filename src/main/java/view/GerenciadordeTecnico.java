package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GerenciadordeTecnico extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNomeTecnico;
	private JTextField textFieldTelefone;
	private JTable tableDadosDoTecnico;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadordeTecnico frame = new GerenciadordeTecnico();
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
	public GerenciadordeTecnico() {
		setTitle("Gerenciador de Técnico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);

		textFieldNomeTecnico = new JTextField();
		textFieldNomeTecnico.setBounds(10, 36, 256, 20);
		contentPane.add(textFieldNomeTecnico);
		textFieldNomeTecnico.setColumns(10);

		JLabel labelTelefone = new JLabel("Telefone:");
		labelTelefone.setFont(new Font("Arial", Font.PLAIN, 13));
		labelTelefone.setBounds(10, 67, 60, 14);
		contentPane.add(labelTelefone);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(10, 92, 150, 20);
		contentPane.add(textFieldTelefone);

		tableDadosDoTecnico = new JTable();
		tableDadosDoTecnico.setModel(new DefaultTableModel(new Object[][] { { "Codigo", "Nome", "Telefone" }, },
				new String[] { "Codigo", "Nome", "Telefone" }));
		tableDadosDoTecnico.getColumnModel().getColumn(0).setResizable(false);
		tableDadosDoTecnico.getColumnModel().getColumn(0).setPreferredWidth(48);
		tableDadosDoTecnico.getColumnModel().getColumn(0).setMinWidth(14);
		tableDadosDoTecnico.getColumnModel().getColumn(0).setMaxWidth(48);
		tableDadosDoTecnico.getColumnModel().getColumn(1).setPreferredWidth(350);
		tableDadosDoTecnico.getColumnModel().getColumn(1).setMinWidth(152);
		tableDadosDoTecnico.getColumnModel().getColumn(1).setMaxWidth(350);
		tableDadosDoTecnico.getColumnModel().getColumn(2).setResizable(false);
		tableDadosDoTecnico.getColumnModel().getColumn(2).setPreferredWidth(96);
		tableDadosDoTecnico.getColumnModel().getColumn(2).setMaxWidth(96);
		tableDadosDoTecnico.setBounds(10, 173, 403, 174);
		contentPane.add(tableDadosDoTecnico);

		JButton btnProvisotio = new JButton("Provisorio");
		btnProvisotio.setFont(new Font("Arial", Font.PLAIN, 13));
		btnProvisotio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnProvisotio.setBounds(322, 35, 91, 23);
		contentPane.add(btnProvisotio);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Nome", "Id" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(170, 147, 91, 22);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 148, 150, 20);
		contentPane.add(textField);
		
		JLabel lblPesquisa = new JLabel("Pesquisa:");
		lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPesquisa.setBounds(10, 123, 60, 14);
		contentPane.add(lblPesquisa);
	}
}
