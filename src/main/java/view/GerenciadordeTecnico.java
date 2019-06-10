package view;

import java.awt.BorderLayout;
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

import controller.ChamadoController;
import controller.TecnicoController;
import model.dto.TelaInicialDTO;
import model.vo.TecnicoVO;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.Color;

public class GerenciadordeTecnico extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNomeTecnico;
	private JTextField textFieldTelefone;
	private JTable tableDadosDoTecnico;
	private JTextField textFieldPesquisa;
	// private JButton btnExcluir = new JButton("Excluir");

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
		setBounds(100, 100, 655, 396);
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
		tableDadosDoTecnico.setFillsViewportHeight(true);
		tableDadosDoTecnico.setCellSelectionEnabled(true);
		tableDadosDoTecnico.setColumnSelectionAllowed(true);
		tableDadosDoTecnico.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		tableDadosDoTecnico.setModel(new DefaultTableModel(new String[][] { { "Codigo", "Nome", "Telefone" }, },
				new String[] { "Codigo", "Nome", "Telefone", "Excluir" }) {
			Class[] columnTypes = new Class[] { Object.class, String.class, String.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
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
		tableDadosDoTecnico.getColumnModel().getColumn(3).setResizable(false);
		tableDadosDoTecnico.setBounds(10, 180, 403, 174);
		contentPane.add(tableDadosDoTecnico);
		TecnicoController tecnicoController = new TecnicoController();
		atualizarTabelaTecnico(tecnicoController.consultaTecnicosController());
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
		btnProvisotio.setBounds(276, 34, 91, 23);
		contentPane.add(btnProvisotio);

		final JComboBox comboBoxPesquisa = new JComboBox();
		comboBoxPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Nome", "Id" }));
		comboBoxPesquisa.setSelectedIndex(0);
		comboBoxPesquisa.setBounds(170, 147, 91, 22);
		contentPane.add(comboBoxPesquisa);

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
		textFieldPesquisa.setBounds(10, 148, 150, 20);
		contentPane.add(textFieldPesquisa);

		JLabel lblPesquisa = new JLabel("Pesquisa:");
		lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPesquisa.setBounds(10, 123, 60, 14);
		contentPane.add(lblPesquisa);

		JButton btnExcluir = new JButton();
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent excluir) {

				TecnicoController tecnicoController = new TecnicoController();
				String retorno = tecnicoController.excluirController(
						tableDadosDoTecnico.getValueAt(tableDadosDoTecnico.getSelectedRow(), 0).toString());
				atualizarTabelaTecnico(tecnicoController.consultaTecnicosController());
				JOptionPane.showMessageDialog(null, retorno);
			}
		});
		btnExcluir.setToolTipText("Excluir linha selecionada");
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-fechar-janela-48.png"));
		btnExcluir.setSelectedIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-fechar-janela-48.png"));
		btnExcluir.setBounds(303, 131, 31, 38);
		contentPane.add(btnExcluir);
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
