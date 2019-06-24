package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProdutoController;
import controller.TecnicoController;
import model.seletor.Seletor;
import model.vo.ProdutoVO;
import model.vo.TecnicoVO;
import net.miginfocom.swing.MigLayout;

public class GerenciadorProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldQuantidade;
	private JTextField textFieldValorCusto;
	private JTextField textFieldValorVenda;
	private JTextField textFieldPesquisa;
	private JComboBox comboBoxPesquisa;
	private JLabel lblPesquisa;
	private JTable tableProduto;
	private JTextField textFieldDescricao;
	private JButton btnSalvar;
	private JComboBox comboBoxLimitePagina = new JComboBox();
	private int totalLinhas = 0;
	private int paginaAtual = 1;
	private JLabel lblPaginaAtual = new JLabel("1");
	private String[] LimitePagina = { "10", "20", "50", "100", "1000" };
	private Seletor seletor = new Seletor();
	private JLabel labelTotalPaginas = new JLabel("\\");
	private JButton btnExcluir;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadorProduto frame = new GerenciadorProduto();
					JScrollPane scroll = new JScrollPane(frame.getContentPane());
					frame.setContentPane(scroll);
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
		setBounds(100, 100, 612, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new MigLayout("", "[95px][71px][1px][][15.00px][78.00px][13px][25px][12px][43px][239px,grow]", "[14px][29px][14px][42px][42px][14px][29px][226px][23px]"));

		textFieldMarca = new JTextField();
		contentPane.add(textFieldMarca, "cell 0 1 2 1,grow");
		textFieldMarca.setColumns(10);

		textFieldModelo = new JTextField();
		textFieldModelo.setColumns(10);
		contentPane.add(textFieldModelo, "cell 10 3,growx,aligny top");

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setColumns(10);
		contentPane.add(textFieldQuantidade, "cell 0 3,growx,aligny top");

		textFieldValorCusto = new JTextField();
		textFieldValorCusto.setColumns(10);
		contentPane.add(textFieldValorCusto, "cell 1 3 5 1,growx,aligny top");

		textFieldValorVenda = new JTextField();
		textFieldValorVenda.setColumns(10);
		contentPane.add(textFieldValorVenda, "cell 6 3 4 1,growx,aligny top");

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblMarca, "cell 0 0,alignx left,aligny center");

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblModelo, "cell 10 2,alignx left,aligny center");

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblQuantidade, "cell 0 2,alignx left,aligny center");

		JLabel lblValorCusto = new JLabel("Valor Custo");
		lblValorCusto.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblValorCusto, "cell 1 2,growx,aligny center");

		JLabel lblValorVenda = new JLabel("Valor Venda");
		lblValorVenda.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblValorVenda, "cell 6 2 4 1,alignx left,aligny center");

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String consultaValor = textFieldPesquisa.getText().trim();

				System.out.println(consultaValor);
				String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

				pesquisaProdutos(consultaValor, comboBoxSelecionado, seletor);

			}
		});

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent btnInserir) {
				int quantidade = Integer.parseInt(textFieldQuantidade.getText().trim());
				float valorCusto = Float.parseFloat(textFieldValorCusto.getText().trim());
				float valorVenda = Float.parseFloat(textFieldValorVenda.getText().trim());
				String retornoInserirTecnico = inserirProduto(textFieldMarca.getText(), quantidade, valorCusto,
						valorVenda, textFieldModelo.getText(), textFieldDescricao.getText());
				ProdutoController produtoController = new ProdutoController();
				atualizarTabelaProduto(produtoController.consultaProdutoController(seletor));
				JOptionPane.showMessageDialog(null, retornoInserirTecnico);
			}
		});
		contentPane.add(btnSalvar, "cell 9 4,alignx left,aligny center");

		JButton btnExcluir = new JButton();
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent excluir) {

				JOptionPane.showMessageDialog(null, excluirCedula());
			}
		});
		btnExcluir.setToolTipText("Excluir linha selecionada");
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-fechar-janela-48.png"));
		btnExcluir.setSelectedIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-fechar-janela-48.png"));
		contentPane.add(btnExcluir, "flowx,cell 5 4,alignx left,growy");
		contentPane.add(btnExcluir, "cell 9 4");
		textFieldPesquisa.setColumns(10);
		contentPane.add(textFieldPesquisa, "cell 0 6 5 1,grow");

		comboBoxPesquisa = new JComboBox();
		comboBoxPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Codigo", "Modelo", "Marca" }));
		comboBoxPesquisa.setSelectedIndex(0);
		contentPane.add(comboBoxPesquisa, "cell 5 6 6 1,grow");

		lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblPesquisa, "cell 0 5,alignx left,aligny center");

		tableProduto = new JTable();
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
		atualizarTabelaProduto(produtoController.consultaProdutoController(seletor));

		contentPane.add(tableProduto, "cell 0 7 11 1,grow");

		textFieldDescricao = new JTextField();
		contentPane.add(textFieldDescricao, "cell 0 4 8 1,grow");
		textFieldDescricao.setColumns(10);

		JLabel lblDescricao = new JLabel("Descricao:");
		contentPane.add(lblDescricao, "cell 0 3,alignx center,aligny bottom");

		JButton btnProximo = new JButton("Proximo >");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paginaAtual <= totalLinhas - 1) {
					paginaAtual++;

					String consultaValor = textFieldPesquisa.getText().trim();

					String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

					lblPaginaAtual.setText(paginaAtual + "");
					System.out.println("" + totalLinhas);

					pesquisaProdutos(consultaValor, comboBoxSelecionado, seletor);
					seletor.setTotalLinhas(
							totalLinhas % Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
					labelTotalPaginas.setText("\\" + seletor.getTotalLinhas());

				}

			}
		});

		JButton btnAnterior = new JButton("< Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {

					paginaAtual--;
					String consultaValor = textFieldPesquisa.getText().trim();

					String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

					lblPaginaAtual.setText(paginaAtual + "");
					labelTotalPaginas.setText(
							"\\" + totalLinhas % Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
					seletor.setPagina(paginaAtual);

					pesquisaProdutos(consultaValor, comboBoxSelecionado, seletor);

				}

			}
		});
		contentPane.add(btnAnterior, "cell 0 8 2 1,alignx center,aligny top");
		
				contentPane.add(lblPaginaAtual, "cell 3 8,alignx left,aligny center");
		contentPane.add(btnProximo, "cell 9 8,alignx left,aligny top");

		comboBoxLimitePagina.setModel(new DefaultComboBoxModel(LimitePagina));

		contentPane.add(comboBoxLimitePagina, "cell 10 8,aligny baseline");

		contentPane.add(labelTotalPaginas, "cell 5 8,growx,aligny center");

		JButton buttonAtualizar = new JButton("");
		buttonAtualizar.setIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\modify.png"));
		buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, updateLinha());

				seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
				seletor.setPagina(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()) * paginaAtual);
				ProdutoController produtoController = new ProdutoController();
				atualizarTabelaProduto(produtoController.consultaProdutoController(seletor));

			}
		});
		contentPane.add(buttonAtualizar, "cell 10 4");

	}

	protected Object updateLinha() {
		ProdutoController produtoController = new ProdutoController();
		// tableDadosDoTecnico.getSelectedRow(), 0).toString();

		ProdutoVO produtoVO = new ProdutoVO();
		produtoVO.setIdproduto(Integer.parseInt(tableProduto.getValueAt(tableProduto.getSelectedRow(), 0).toString()));
		produtoVO.setMarca(tableProduto.getValueAt(tableProduto.getSelectedRow(), 1).toString());
		produtoVO.setModelo(tableProduto.getValueAt(tableProduto.getSelectedRow(), 2).toString());
		produtoVO.setQuantidade(Integer.parseInt(tableProduto.getValueAt(tableProduto.getSelectedRow(), 3).toString()));
		produtoVO.setValor_custo(Float.parseFloat(tableProduto.getValueAt(tableProduto.getSelectedRow(), 4).toString()));
		produtoVO.setValor_venda(Float.parseFloat(tableProduto.getValueAt(tableProduto.getSelectedRow(), 5).toString()));
		produtoVO.setObservacao(tableProduto.getValueAt(tableProduto.getSelectedRow(), 6).toString());
		return produtoController.updateController(produtoVO);
	}

	protected void pesquisaProdutos(String consulta, String comboBoxPesquisa, Seletor seletor) {

		// List<Produto> produtos = controlador.listarProdutos(seletor);

		// atualizarTabelaProdutos(produtos);
		ProdutoController produtoController = new ProdutoController();

		if (consulta.equals("")) {
			List<ProdutoVO> produtoVO = produtoController.consultaProdutoController(seletor);
			atualizarTabelaProduto(produtoVO);
		} else {

			List<ProdutoVO> produtoVO = produtoController.consultaProdutoController(consulta, comboBoxPesquisa,
					seletor);
			atualizarTabelaProduto(produtoVO);
		}

	}

	protected String inserirProduto(String marca, int quantidade, float valorCusto, float valorVenda, String modelo,
			String observacao) {

		ProdutoController produtoController = new ProdutoController();

		return produtoController.inserirProdutoController(marca, quantidade, valorCusto, valorVenda, modelo,
				observacao);
	}

	protected String excluirCedula() {
		// TODO Auto-generated method stub
		ProdutoController produtoController = new ProdutoController();
		String retorno = produtoController
				.excluirController(tableProduto.getValueAt(tableProduto.getSelectedRow(), 0).toString());
		seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
		atualizarTabelaProduto(produtoController.consultaProdutoController(seletor));

		return retorno;
	}

	protected void atualizarTabelaProduto(List<ProdutoVO> produtoVO) {
		// atualiza o atributo produtosConsultados

		// Limpa a tabela

		tableProduto.setModel(new DefaultTableModel(
				new String[][] {
						{ "Codigo", "Marca", "Modelo", "QTD", "Valor Custo", "Valor Venda", "Descri\u00E7\u00E3o" }, },
				new String[] { "Codigo", "Marca", "Modelo", "QTD", "Valor Custo", "Valor Venda",
						"Descri\u00E7\u00E3o" }) {
			/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true, true, true, true, true };

			public boolean isCellEditable(int row, int column) {
				if (row == 0) {

					return false;

				}
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

		totalLinhas = modelo.getRowCount() + 2;

	}
}
