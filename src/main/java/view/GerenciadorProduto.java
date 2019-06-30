package view;

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

import model.seletor.Seletor;
import model.vo.ProdutoVO;
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
	private int paginaAtual = 1;
	private JLabel lblPaginaAtual = new JLabel("1");
	private String[] LimitePagina = { "10", "20", "50", "100", "1000" };
	private Seletor seletor = new Seletor();
	private JLabel labelTotalPaginas = new JLabel("\\");
	private JButton button;
	private JButton btnExcluir;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 725, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[95px][69.00px][71.00][59.00][78.00px][43px][86.00px][grow]",
				"[14px][29px][14px][][24.00px][55.00][51.00px][14px][29px][226px][][23px]"));

		textFieldMarca = new JTextField();
		contentPane.add(textFieldMarca, "cell 0 1 2 1,grow");
		textFieldMarca.setColumns(10);

		JLabel lblValorCusto = new JLabel("Valor Custo:");
		lblValorCusto.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblValorCusto, "cell 0 2,growx,aligny center");

		JLabel lblValorVenda = new JLabel("Valor Venda:");
		lblValorVenda.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblValorVenda, "cell 2 2,alignx left,aligny center");

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblQuantidade, "cell 4 2,alignx left,aligny center");

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblModelo, "cell 6 2,alignx left,aligny center");

		textFieldValorCusto = new JTextField();
		textFieldValorCusto.setColumns(10);
		contentPane.add(textFieldValorCusto, "cell 0 3,growx,aligny top");

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblMarca, "cell 0 0,alignx left,aligny center");

		textFieldValorVenda = new JTextField();
		textFieldValorVenda.setColumns(10);
		contentPane.add(textFieldValorVenda, "cell 2 3,growx,aligny top");

		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setColumns(10);
		contentPane.add(textFieldQuantidade, "cell 4 3,grow");

		textFieldModelo = new JTextField();
		textFieldModelo.setColumns(10);
		contentPane.add(textFieldModelo, "cell 6 3,grow");

		textFieldDescricao = new JTextField();
		contentPane.add(textFieldDescricao, "cell 0 5 4 1,grow");
		textFieldDescricao.setColumns(10);
		
				btnSalvar = new JButton("Salvar");
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent btnInserir) {

						String retornoInserirTecnico = inserirProduto();
						ProdutoController produtoController = new ProdutoController();
						
						JOptionPane.showMessageDialog(null, retornoInserirTecnico);
						pesquisaProdutos(textFieldPesquisa.getText().trim(), comboBoxPesquisa.getSelectedItem().toString().trim(), seletor);
					}
				});
				contentPane.add(btnSalvar, "cell 5 5,alignx left,aligny center");

		lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblPesquisa, "cell 0 6,alignx left,aligny bottom");

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			

				pesquisaProdutos(textFieldPesquisa.getText().trim(), comboBoxPesquisa.getSelectedItem().toString().trim(), seletor);

			}
		});

		JButton buttonAtualizar = new JButton("");
		buttonAtualizar.setIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\modify.png"));
		buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, updateLinha());

				seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
				seletor.setPagina(paginaAtual);
				ProdutoController produtoController = new ProdutoController();

				int result = (int) Math.ceil((float) produtoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				
				pesquisaProdutos(textFieldPesquisa.getText(),comboBoxLimitePagina.getSelectedItem().toString(), seletor);
				
				

			}
		});
		contentPane.add(buttonAtualizar, "cell 5 6,alignx center,aligny center");

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoController produtoController = new ProdutoController();
				
				JOptionPane.showMessageDialog(null, excluirCedula());
				
				int result = (int) Math.ceil((float) produtoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				
				
				pesquisaProdutos(textFieldPesquisa.getText(),comboBoxLimitePagina.getSelectedItem().toString(), seletor);
			}
		});
		btnExcluir.setIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-fechar-janela-48.png"));
		contentPane.add(btnExcluir, "cell 6 6");
		textFieldPesquisa.setColumns(10);
		contentPane.add(textFieldPesquisa, "cell 0 7 2 1,grow");

		comboBoxPesquisa = new JComboBox();
		comboBoxPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Codigo", "Modelo", "Marca" }));
		comboBoxPesquisa.setSelectedIndex(0);
		contentPane.add(comboBoxPesquisa, "cell 3 7,grow");

		JLabel lblDescricao = new JLabel("Descricao:");
		contentPane.add(lblDescricao, "cell 0 4,alignx center,aligny top");

		JButton btnAnterior = new JButton("< Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoController produtoController = new ProdutoController();

				int result = (int) Math.ceil((float) produtoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				
				if (paginaAtual > 1) {

					paginaAtual--;

					lblPaginaAtual.setText(paginaAtual + "");

					pesquisaProdutos(textFieldPesquisa.getText(),comboBoxLimitePagina.getSelectedItem().toString(), seletor);
				}
			}
		});
		JButton btnProximo = new JButton("Proximo >");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ProdutoController produtoController = new ProdutoController();

				int result = (int) Math
						.ceil((float) produtoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				if (paginaAtual < result) {
					paginaAtual++;
					lblPaginaAtual.setText(paginaAtual + "");
					pesquisaProdutos(textFieldPesquisa.getText(), comboBoxLimitePagina.getSelectedItem().toString(),seletor);
				}
			}
		});

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

		contentPane.add(tableProduto, "cell 0 9 8 2,grow");
		contentPane.add(btnAnterior, "cell 0 11 2 1,alignx center,aligny top");

		contentPane.add(lblPaginaAtual, "flowx,cell 2 11,alignx left,aligny center");
		comboBoxLimitePagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoController produtoController = new ProdutoController();

				seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
				seletor.setPagina(paginaAtual);
 
				int result = (int) Math.ceil((float) produtoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);

				pesquisaProdutos(textFieldPesquisa.getText(),comboBoxLimitePagina.getSelectedItem().toString(), seletor);
				
			}
		});

		comboBoxLimitePagina.setModel(new DefaultComboBoxModel(LimitePagina));

		ProdutoController produtoController = new ProdutoController();

		seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
		seletor.setPagina(paginaAtual);

		pesquisaProdutos(textFieldPesquisa.getText(), comboBoxLimitePagina.getSelectedItem().toString(), seletor);
		int result = (int) Math.ceil((float) produtoController.countLinhasTotalController() / seletor.getLimite());

		labelTotalPaginas.setText("\\" + result);

		contentPane.add(btnProximo, "cell 3 11,alignx left,aligny top");

		contentPane.add(labelTotalPaginas, "cell 2 11,growx,aligny center");

		contentPane.add(comboBoxLimitePagina, "cell 4 11,aligny baseline");

	}

	protected String updateLinha() {
		ProdutoController produtoController = new ProdutoController();
		// tableDadosDoTecnico.getSelectedRow(), 0).toString();

		ProdutoVO produtoVO = new ProdutoVO();
		produtoVO.setIdproduto(Integer.parseInt(tableProduto.getValueAt(tableProduto.getSelectedRow(), 0).toString()));
		produtoVO.setMarca(tableProduto.getValueAt(tableProduto.getSelectedRow(), 1).toString());
		produtoVO.setModelo(tableProduto.getValueAt(tableProduto.getSelectedRow(), 2).toString());
		produtoVO.setQuantidade(Integer.parseInt(tableProduto.getValueAt(tableProduto.getSelectedRow(), 3).toString()));
		produtoVO
				.setValor_custo(Float.parseFloat(tableProduto.getValueAt(tableProduto.getSelectedRow(), 4).toString()));
		produtoVO
				.setValor_venda(Float.parseFloat(tableProduto.getValueAt(tableProduto.getSelectedRow(), 5).toString()));
		produtoVO.setObservacao(tableProduto.getValueAt(tableProduto.getSelectedRow(), 6).toString());
		return produtoController.updateController(produtoVO);
	}

	protected void pesquisaProdutos(String consulta, String comboBoxPesquisa, Seletor seletor) {
		ProdutoController produtoController = new ProdutoController();
		seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
		seletor.setPagina(paginaAtual);
		

		if (consulta.equals("")) {
			List<ProdutoVO> produtoVO = produtoController.consultaProdutoController(seletor);
			atualizarTabelaProduto(produtoVO);
		} else {

			List<ProdutoVO> produtoVO = produtoController.consultaProdutoController(consulta, comboBoxPesquisa,
					seletor);
			atualizarTabelaProduto(produtoVO);
		}

	}

	protected String inserirProduto() {

		ProdutoController produtoController = new ProdutoController();
		ProdutoVO produtoVO = new ProdutoVO();
		produtoVO.setMarca(textFieldMarca.getText());
		produtoVO.setModelo(textFieldModelo.getText());
		produtoVO.setObservacao(textFieldDescricao.getText());
		produtoVO.setQuantidade(Integer.parseInt(textFieldQuantidade.getText()));
		produtoVO.setValor_custo(Float.parseFloat(textFieldValorCusto.getText()));
		produtoVO.setValor_venda(Float.parseFloat(textFieldValorVenda.getText()));
		return produtoController.inserirProdutoController(produtoVO);
	}

	protected String excluirCedula() {
		// TODO Auto-generated method stub
		ProdutoController produtoController = new ProdutoController();
		ProdutoVO produtoVO = new ProdutoVO();
		produtoVO.setIdproduto(Integer.parseInt(tableProduto.getValueAt(tableProduto.getSelectedRow(), 0).toString()));
		String retorno = produtoController.excluirController(produtoVO);
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
		for (ProdutoVO produto : produtoVO) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeçalho da tabela

			String[] novaLinha = new String[] { produto.getIdproduto() + "", produto.getMarca(), produto.getModelo(),
					produto.getQuantidade() + "", produto.getValor_custo() + "", produto.getValor_venda() + "",
					produto.getObservacao() };
			modelo.addRow(novaLinha);

		}

	}
}
