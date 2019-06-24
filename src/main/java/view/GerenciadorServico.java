package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GerenciadorServico extends JFrame {
	private JTextField textFieldNome;
	private JTextField textFieldValor;
	private JTextField textFieldPesquisa;
	private JLabel labelPesquisa;
	private JTable tableDadosDoTecnico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadorServico frame = new GerenciadorServico();
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
	public GerenciadorServico() {
		setTitle("Gerenciador Serviço");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 396);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 23, 46, 14);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
		getContentPane().add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 47, 162, 20);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel labelValor = new JLabel("Valor:");
		labelValor.setFont(new Font("Arial", Font.PLAIN, 13));
		labelValor.setBounds(196, 23, 46, 14);
		getContentPane().add(labelValor);
		
		textFieldValor = new JTextField();
		textFieldValor.setColumns(10);
		textFieldValor.setBounds(196, 47, 127, 20);
		getContentPane().add(textFieldValor);
		
		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);
		textFieldPesquisa.setBounds(10, 125, 186, 20);
		getContentPane().add(textFieldPesquisa);
		
		labelPesquisa = new JLabel("Pesquisa:");
		labelPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		labelPesquisa.setBounds(10, 101, 63, 14);
		getContentPane().add(labelPesquisa);
		
		JComboBox comboBoxPesquisa = new JComboBox();
		comboBoxPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Nome", "Id" }));
		comboBoxPesquisa.setSelectedIndex(0);
		comboBoxPesquisa.setBounds(232, 124, 91, 22);
		getContentPane().add(comboBoxPesquisa);
		
		tableDadosDoTecnico = new JTable();
		tableDadosDoTecnico.setModel(new DefaultTableModel(new Object[][] { { "Codigo", "Nome", "Valor" }, },
				new String[] { "Codigo", "Nome", "Valor" }));
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
		//tableDadosDoTecnico.setBounds(10, 207, 366, 120);
		getContentPane().add(tableDadosDoTecnico);
		
		JButton btnNewButton = new JButton("Inserir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(337, 46, 89, 23);
		getContentPane().add(btnNewButton);
		
		
	}
}
