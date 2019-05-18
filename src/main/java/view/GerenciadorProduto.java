package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

public class GerenciadorProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldQuantidade;
	private JTextField textFieldValorCusto;
	private JTextField textFieldValorVenda;
	private JTextField textFieldPesquisa;
	private JComboBox comboBoxDadosCliente;
	private JLabel lblPesquisa;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadorProduto frame = new GerenciadorProduto();
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
	public GerenciadorProduto() {
		setTitle("Gerenciador Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setModel(new DefaultComboBoxModel(new String[] {"Categoria"}));
		comboBoxCategoria.setToolTipText("");
		comboBoxCategoria.setBounds(10, 11, 241, 29);
		contentPane.add(comboBoxCategoria);

		textFieldMarca = new JTextField();
		textFieldMarca.setBounds(10, 76, 179, 29);
		contentPane.add(textFieldMarca);
		textFieldMarca.setColumns(10);

		textFieldModelo = new JTextField();
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(199, 76, 179, 29);
		contentPane.add(textFieldModelo);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setColumns(10);
		textFieldQuantidade.setBounds(10, 158, 95, 29);
		contentPane.add(textFieldQuantidade);

		textFieldValorCusto = new JTextField();
		textFieldValorCusto.setColumns(10);
		textFieldValorCusto.setBounds(115, 158, 95, 29);
		contentPane.add(textFieldValorCusto);

		textFieldValorVenda = new JTextField();
		textFieldValorVenda.setColumns(10);
		textFieldValorVenda.setBounds(220, 158, 95, 29);
		contentPane.add(textFieldValorVenda);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMarca.setBounds(10, 51, 46, 14);
		contentPane.add(lblMarca);

		JLabel lblNewLabel = new JLabel("Modelo");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(199, 51, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Arial", Font.PLAIN, 13));
		lblQuantidade.setBounds(10, 133, 71, 14);
		contentPane.add(lblQuantidade);

		JLabel lblValorCusto = new JLabel("Valor Custo");
		lblValorCusto.setFont(new Font("Arial", Font.PLAIN, 13));
		lblValorCusto.setBounds(115, 133, 71, 14);
		contentPane.add(lblValorCusto);

		JLabel lblVa = new JLabel("Valor Venda");
		lblVa.setFont(new Font("Arial", Font.PLAIN, 13));
		lblVa.setBounds(220, 133, 71, 14);
		contentPane.add(lblVa);

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setColumns(10);
		textFieldPesquisa.setBounds(10, 213, 179, 29);
		contentPane.add(textFieldPesquisa);

		comboBoxDadosCliente = new JComboBox();
		comboBoxDadosCliente.setModel(new DefaultComboBoxModel(new String[] {"Código", "Cpf", "Nome", "Telefone"}));
		comboBoxDadosCliente.setSelectedIndex(0);
		comboBoxDadosCliente.setBounds(360, 213, 241, 29);
		contentPane.add(comboBoxDadosCliente);

		lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPesquisa.setBounds(10, 198, 54, 14);
		contentPane.add(lblPesquisa);


		table = new JTable();
		table.setBounds(10, 253, 578, 226);
		table.setModel(new DefaultTableModel(
				new String[][] { { "Código", "Marca", "Modelo", "QTD", "Valor Custo", "Valor Venda", "Categoria",
						"Descrição" }, },
				new String[] { "Código", "Marca", "Modelo", "QTD", "Valor Custo", "Valor Venda", "Categoria",
						"Descrição" }));
		contentPane.add(table);
	}
}
