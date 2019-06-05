package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.TecnicoController;
import controller.ProdutoController;
import model.vo.ProdutoVO;
import model.vo.TecnicoVO;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JTable tableProduto;
	private JTextField textFieldDescricao;
	private JButton btnSalvar;

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

		textFieldMarca = new JTextField();
		textFieldMarca.setBounds(-3, 25, 179, 29);
		contentPane.add(textFieldMarca);
		textFieldMarca.setColumns(10);

		textFieldModelo = new JTextField();
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(312, 93, 179, 29);
		contentPane.add(textFieldModelo);

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setColumns(10);
		textFieldQuantidade.setBounds(-3, 93, 95, 29);
		contentPane.add(textFieldQuantidade);

		textFieldValorCusto = new JTextField();
		textFieldValorCusto.setColumns(10);
		textFieldValorCusto.setBounds(102, 93, 95, 29);
		contentPane.add(textFieldValorCusto);

		textFieldValorVenda = new JTextField();
		textFieldValorVenda.setColumns(10);
		textFieldValorVenda.setBounds(207, 93, 95, 29);
		contentPane.add(textFieldValorVenda);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMarca.setBounds(4, 0, 56, 14);
		contentPane.add(lblMarca);

		JLabel lblNewLabel = new JLabel("Modelo");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(312, 68, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Arial", Font.PLAIN, 13));
		lblQuantidade.setBounds(4, 68, 71, 14);
		contentPane.add(lblQuantidade);

		JLabel lblValorCusto = new JLabel("Valor Custo");
		lblValorCusto.setFont(new Font("Arial", Font.PLAIN, 13));
		lblValorCusto.setBounds(102, 68, 71, 14);
		contentPane.add(lblValorCusto);

		JLabel lblVa = new JLabel("Valor Venda");
		lblVa.setFont(new Font("Arial", Font.PLAIN, 13));
		lblVa.setBounds(207, 68, 71, 14);
		contentPane.add(lblVa);

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String consultaValor = textFieldPesquisa.getText().trim();

				System.out.println(consultaValor);
				String comboBoxSelecionado = comboBoxDadosCliente.getSelectedItem().toString().trim();

				pesquisaProdutos(consultaValor, comboBoxSelecionado);

			}
		});
		textFieldPesquisa.setColumns(10);
		textFieldPesquisa.setBounds(10, 213, 179, 29);
		contentPane.add(textFieldPesquisa);

		comboBoxDadosCliente = new JComboBox();
		comboBoxDadosCliente.setModel(new DefaultComboBoxModel(new String[] {"Codigo", "Modelo", "Marca"}));
		comboBoxDadosCliente.setSelectedIndex(0);
		comboBoxDadosCliente.setBounds(205, 213, 241, 29);
		contentPane.add(comboBoxDadosCliente);

		lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPesquisa.setBounds(10, 198, 54, 14);
		contentPane.add(lblPesquisa);

		tableProduto = new JTable();
		tableProduto.setBounds(10, 253, 578, 226);
		tableProduto.setModel(new DefaultTableModel(
				new String[][] {
						{ "Codigo", "Marca", "Modelo", "QTD", "Valor Custo", "Valor Venda", "Descri\u00E7\u00E3o" }, },
				new String[] { "Codigo", "Marca", "Modelo", "QTD", "Valor Custo", "Valor Venda",
						"Descri\u00E7\u00E3o" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true, true, true, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		ProdutoController produtoController = new ProdutoController();
		atualizarTabelaProduto(produtoController.consultaProdutoController());

		contentPane.add(tableProduto);

		textFieldDescricao = new JTextField();
		textFieldDescricao.setBounds(10, 145, 247, 42);
		contentPane.add(textFieldDescricao);
		textFieldDescricao.setColumns(10);

		JLabel lblDescricao = new JLabel("Descricao:");
		lblDescricao.setBounds(10, 121, 71, 14);
		contentPane.add(lblDescricao);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent btnInserir) {
				int quantidade = Integer.parseInt(textFieldQuantidade.getText().trim());
				float valorCusto = Float.parseFloat(textFieldValorCusto.getText().trim());
				float valorVenda = Float.parseFloat(textFieldValorVenda.getText().trim());
				String retornoInserirTecnico = inserirProduto(textFieldMarca.getText(), quantidade, valorCusto,
						valorVenda, textFieldModelo.getText(), textFieldDescricao.getText());
				ProdutoController produtoController = new ProdutoController();
				atualizarTabelaProduto(produtoController.consultaProdutoController());
				JOptionPane.showMessageDialog(null, retornoInserirTecnico);
			}
		});
		btnSalvar.setBounds(402, 25, 89, 29);
		contentPane.add(btnSalvar);

	}

	protected void pesquisaProdutos(String consulta, String comboBoxPesquisa) {

		// List<Produto> produtos = controlador.listarProdutos(seletor);

		// atualizarTabelaProdutos(produtos);
		TecnicoController tecnicoController = new TecnicoController();

		if (consulta.equals("")) {
			List<ProdutoVO> produtoVO = ProdutoController.consultaProdutoController();
			atualizarTabelaProduto(produtoVO);
		} else {

			List<ProdutoVO> produtoVO = ProdutoController.consultaProdutoController(consulta, comboBoxPesquisa);
			atualizarTabelaProduto(produtoVO);
		}

	}

	protected String inserirProduto(String marca, int quantidade, float valorCusto, float valorVenda, String modelo,
			String observacao) {

		ProdutoController produtoController = new ProdutoController();

		return produtoController.inserirProdutoController(marca, quantidade, valorCusto, valorVenda, modelo,
				observacao);
	}

	protected void atualizarTabelaProduto(List<ProdutoVO> produtoVO) {
		// atualiza o atributo produtosConsultados

		// Limpa a tabela

		tableProduto.setModel(new DefaultTableModel(
				new String[][] {
						{ "Codigo", "Marca", "Modelo", "QTD", "Valor Custo", "Valor Venda", "Descri\u00E7\u00E3o" }, },
				new String[] { "Codigo", "Marca", "Modelo", "QTD", "Valor Custo", "Valor Venda",
						"Descri\u00E7\u00E3o" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true, true, true, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		DefaultTableModel modelo = (DefaultTableModel) tableProduto.getModel();
		// btnExcluir.setIcon(new
		// ImageIcon("C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\iconesicons8-ms-excel-48.png"));
		for (ProdutoVO produto : produtoVO) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeçalho da tabela

			// Object[] novaLinha = new Object[] { tecnico.getIdtecnico() + "",
			// tecnico.getNome(), tecnico.getTelefone(),btnExcluir.getIcon()};
			String[] novaLinha = new String[] { produto.getIdproduto() + "", produto.getMarca(), produto.getModelo(),
					produto.getQuantidade() + "", produto.getValor_custo() + "", produto.getValor_venda() + "",
					produto.getObservacao() };
			modelo.addRow(novaLinha);

		}

	}

}
