package view;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import controller.TecnicoController;
<<<<<<< HEAD
import model.seletor.Seletor;
=======
import model.dto.TelaInicialDTO;
>>>>>>> 2c59e7f6850601f32135fc81382bdc05250db515
import model.vo.TecnicoVO;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.Color;

public class GerenciadordeTecnico extends JFrame {
	private static final int TAMANHO_PAGINA = 0;

	private JPanel contentPane;
	private JTextField textFieldNomeTecnico;
	private JTextField textFieldTelefone;
	private JTable tableDadosDoTecnico;
	private JTextField textFieldPesquisa;
	private int paginaAtual = 1;
	private JLabel lblPaginaAtual;

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
		setBounds(100, 100, 589, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[150px][][10px][96px][][10px][][48.00][26.00px]",
				"[14px][23px][14px][21.00px][][][][44.00px][-11.00][][-26.00px][83.00px][50.00px]"));

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblNome, "cell 0 0,alignx left,aligny center");

		textFieldNomeTecnico = new JTextField();
		contentPane.add(textFieldNomeTecnico, "cell 0 1 4 1,growx,aligny center");
		textFieldNomeTecnico.setColumns(10);
		JButton btnProvisotio = new JButton("Criar");
		btnProvisotio.setFont(new Font("Arial", Font.PLAIN, 13));
		btnProvisotio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent btnInserir) {
				String retornoInserirTecnico = inserirTecnico(textFieldNomeTecnico.getText(),
						textFieldTelefone.getText());
				TecnicoController tecnicoController = new TecnicoController();
				atualizarTabelaTecnico(tecnicoController.consultaTecnicosController());
				JOptionPane.showMessageDialog(null, retornoInserirTecnico);

			}
		});
		contentPane.add(btnProvisotio, "cell 4 1,alignx left,aligny top");

		JLabel labelTelefone = new JLabel("Telefone:");
		labelTelefone.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(labelTelefone, "cell 0 2,alignx left,aligny center");

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		contentPane.add(textFieldTelefone, "cell 0 3,growx,aligny top");

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
		contentPane.add(btnExcluir, "cell 7 3,alignx left,growy");

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, updateTecnico());
				TecnicoController tecnicoController = new TecnicoController();
				atualizarTabelaTecnico(tecnicoController.consultaTecnicosController());

			}
		});
		button.setIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\modify.png"));
		contentPane.add(button, "cell 8 3");

		JLabel lblPesquisa = new JLabel("Pesquisa:");
		lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblPesquisa, "cell 0 4,alignx left,aligny center");

		final JComboBox comboBoxPesquisa = new JComboBox();
		comboBoxPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Nome", "Id" }));
		comboBoxPesquisa.setSelectedIndex(0);
		contentPane.add(comboBoxPesquisa, "cell 3 6,grow");

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setText("");
		textFieldPesquisa.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String consultaValor = textFieldPesquisa.getText().trim();

				System.out.println(consultaValor);
				String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

				pesquisaTecnicos(consultaValor, comboBoxSelecionado);
			}
		});
		textFieldPesquisa.setColumns(10);
		contentPane.add(textFieldPesquisa, "cell 0 5,growx,aligny center");

		tableDadosDoTecnico = new JTable();
		tableDadosDoTecnico.setCellSelectionEnabled(true);
		tableDadosDoTecnico.setFillsViewportHeight(true);
		tableDadosDoTecnico.setColumnSelectionAllowed(true);
		tableDadosDoTecnico.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		tableDadosDoTecnico.setModel(new DefaultTableModel(new String[][] { { "Codigo", "Nome", "Telefone" }, },
				new String[] { "Codigo", "Nome", "Telefone" }) {
			Class[] columnTypes = new Class[] { Object.class, String.class, String.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true, false };

			public boolean isCellEditable(int row, int column) {

				if (row == 0) {

					return false;

				}
				return columnEditables[column];

			}
		});
		contentPane.add(tableDadosDoTecnico, "cell 0 9 7 3,grow");

		JButton btnAnterior = new JButton("< Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				consultarProdutos();
			}
		});
		contentPane.add(btnAnterior, "cell 0 12");

		JButton btnProximo = new JButton("Proximo >");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				consultarProdutos();
			}
		});

		contentPane.add(lblPaginaAtual, "cell 1 12");
		contentPane.add(btnProximo, "cell 4 12");
		TecnicoController tecnicoController = new TecnicoController();
		atualizarTabelaTecnico(tecnicoController.consultaTecnicosController());
	}

	protected void consultarProdutos() {
		lblPaginaAtual.setText(paginaAtual + "");

		TecnicoController tecnicoController = new TecnicoController();
		Seletor seletor = new Seletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(TAMANHO_PAGINA);
		List<TecnicoVO> tecnicoVO = tecnicoController.consultaTecnicosController(seletor);
		atualizarTabelaTecnico(tecnicoVO);
	}

	protected String excluirCedula() {
		// TODO Auto-generated method stub
		TecnicoController tecnicoController = new TecnicoController();
		String retorno = tecnicoController
				.excluirController(tableDadosDoTecnico.getValueAt(tableDadosDoTecnico.getSelectedRow(), 0).toString());
		atualizarTabelaTecnico(tecnicoController.consultaTecnicosController());

		return retorno;
	}

	protected String updateTecnico() {

		TecnicoController tecnicoController = new TecnicoController();
		// tableDadosDoTecnico.getSelectedRow(), 0).toString();

		TecnicoVO tecnicoVO = new TecnicoVO();
		tecnicoVO.setIdtecnico(
				Integer.parseInt(tableDadosDoTecnico.getValueAt(tableDadosDoTecnico.getSelectedRow(), 0).toString()));
		tecnicoVO.setNome(tableDadosDoTecnico.getValueAt(tableDadosDoTecnico.getSelectedRow(), 1).toString());
		tecnicoVO.setTelefone(tableDadosDoTecnico.getValueAt(tableDadosDoTecnico.getSelectedRow(), 2).toString());

		return tecnicoController.updateController(tecnicoVO);

	}

	/*
	 * Metodo de Inserir Tecnico na tabela tecnico
	 * 
	 * @param inserir dado na coluna nome
	 * 
	 * @param inserir dado na coluna telefone
	 * 
	 * 
	 */
	protected String inserirTecnico(String nome, String telefone) {

		TecnicoController tecnicoController = new TecnicoController();

		return tecnicoController.inserirTecnicoController(nome, telefone);
	}

	/*
	 * Metodo de pesquisa utiliza o like para procurar dados no banco
	 * 
	 * @param consulta é o que o usuario quer buscar no banco
	 * 
	 * @param comboBoxPesquisa é o qual a coluna que o mesmo irá pesquisas(nome ou
	 * id)
	 * 
	 * 
	 */
	protected void pesquisaTecnicos(String consulta, String comboBoxPesquisa) {

		// List<Produto> produtos = controlador.listarProdutos(seletor);

		// atualizarTabelaProdutos(produtos);
		TecnicoController tecnicoController = new TecnicoController();

		if (consulta.equals("")) {
			List<TecnicoVO> tecnicoVO = tecnicoController.consultaTecnicosController();
			atualizarTabelaTecnico(tecnicoVO);
		} else {

			List<TecnicoVO> tecnicoVO = tecnicoController.consultaTecnicosController(consulta, comboBoxPesquisa);
			atualizarTabelaTecnico(tecnicoVO);
		}

	}

	protected void atualizarTabelaTecnico(List<TecnicoVO> tecnicoVO) {
		// atualiza o atributo produtosConsultados

		// Limpa a tabela

		tableDadosDoTecnico.setModel(new DefaultTableModel(new String[][] { { "Codigo", "Nome", "Telefone" }, },
				new String[] { "Codigo", "Nome", "Telefone" }) {
			Class[] columnTypes = new Class[] { Object.class, String.class, String.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true, false };

			public boolean isCellEditable(int row, int column) {

				if (row == 0) {

					return false;

				}
				return columnEditables[column];

			}
		});

		DefaultTableModel modelo = (DefaultTableModel) tableDadosDoTecnico.getModel();
		// btnExcluir.setIcon(new
		// ImageIcon("C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\iconesicons8-ms-excel-48.png"));
		for (TecnicoVO tecnico : tecnicoVO) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeçalho da tabela

			// Object[] novaLinha = new Object[] { tecnico.getIdtecnico() + "",
			// tecnico.getNome(), tecnico.getTelefone(),btnExcluir.getIcon()};
			String[] novaLinha = new String[] { tecnico.getIdtecnico() + "", tecnico.getNome(), tecnico.getTelefone() };
			modelo.addRow(novaLinha);

		}

	}
}
