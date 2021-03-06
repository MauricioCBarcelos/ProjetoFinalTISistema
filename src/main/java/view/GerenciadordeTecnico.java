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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.TecnicoController;
import model.seletor.Seletor;
import model.vo.TecnicoVO;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class GerenciadordeTecnico extends JFrame {
	private JTextField textFieldCpf;
	private JPanel contentPane;
	private JTextField textFieldNomeTecnico;
	private JTextField textFieldTelefone;
	private JTable tableDadosDoTecnico;
	private JTextField textFieldPesquisa;
	private JComboBox comboBoxPesquisa = new JComboBox();
	private JComboBox comboBoxLimitePagina = new JComboBox();
	private int paginaAtual = 1;
	private JLabel lblPaginaAtual = new JLabel("1");
	private String[] limitePagina = { "10", "20", "50", "100", "1000" };
	private JLabel labelTotalPaginas = new JLabel("\\");
	private Seletor seletor = new Seletor();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadordeTecnico frame = new GerenciadordeTecnico();
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

	public GerenciadordeTecnico() {

		setTitle("Gerenciador de Tecncio\r\n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 589, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[150px,grow][][][107.00px][77.00,grow][48.00][26.00px]", "[14px][23px][14px][32.00px][][][][][][-11.00][][-26.00px][83.00px,top][50.00px,center]"));

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblNome, "cell 0 0,alignx left,aligny center");

		textFieldNomeTecnico = new JTextField();
		contentPane.add(textFieldNomeTecnico, "cell 0 1 4 1,growx,aligny center");
		textFieldNomeTecnico.setColumns(10);

		JLabel labelTelefone = new JLabel("Telefone:");
		labelTelefone.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(labelTelefone, "cell 0 2,alignx left,aligny center");

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		contentPane.add(textFieldTelefone, "cell 0 3,growx,aligny top");

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.setText("");
		textFieldPesquisa.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {

				String consultaValor = textFieldPesquisa.getText().trim();

				String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

				pesquisaTecnicos(consultaValor, comboBoxSelecionado, seletor);
			}
		});
		JButton btnCriar = new JButton("Criar");
		btnCriar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent btnInserir) {
				TecnicoController tecnicoController = new TecnicoController();
	

				String retornoInserirTecnico = inserirTecnico();
			

				atualizarTabelaTecnico(tecnicoController.consultaTecnicosController(seletor));
				JOptionPane.showMessageDialog(null, retornoInserirTecnico);

				int result = (int) Math
						.ceil((float) tecnicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);

			}
		});
		contentPane.add(btnCriar, "cell 3 3,alignx left,aligny top");

		JButton buttonAtualizar = new JButton("");
		buttonAtualizar.setIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\modify.png"));
		buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, updateTecnico());
				TecnicoController tecnicoController = new TecnicoController();
				atualizarTabelaTecnico(tecnicoController.consultaTecnicosController(seletor));

				int result = (int) Math
						.ceil((float) tecnicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);

			}
		});

		JButton btnExcluir = new JButton();
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent excluir) {

				JOptionPane.showMessageDialog(null, excluirCedula());

				TecnicoController tecnicoController = new TecnicoController();
				int result = (int) Math
						.ceil((float) tecnicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
			}
		});
		
		JLabel lblCpf = new JLabel("CPF:");
		contentPane.add(lblCpf, "cell 0 4");
		
		textFieldCpf = new JTextField();
		contentPane.add(textFieldCpf, "cell 0 5,growx");
		textFieldCpf.setColumns(10);
		btnExcluir.setToolTipText("Excluir linha selecionada");
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-fechar-janela-48.png"));
		btnExcluir.setSelectedIcon(new ImageIcon(
				"C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-fechar-janela-48.png"));
		contentPane.add(btnExcluir, "flowx,cell 5 6,alignx left,growy");

		contentPane.add(buttonAtualizar, "cell 5 6");

		JLabel lblPesquisa = new JLabel("Pesquisa:");
		lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblPesquisa, "cell 0 7,alignx left,aligny center");
		textFieldPesquisa.setColumns(10);
		contentPane.add(textFieldPesquisa, "cell 0 8,growx,aligny center");

		comboBoxPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Nome", "Id" }));
		comboBoxPesquisa.setSelectedIndex(0);
		contentPane.add(comboBoxPesquisa, "cell 3 8,grow");

		tableDadosDoTecnico = new JTable();
		tableDadosDoTecnico.setCellSelectionEnabled(true);
		tableDadosDoTecnico.setFillsViewportHeight(true);
		tableDadosDoTecnico.setColumnSelectionAllowed(true);
		tableDadosDoTecnico.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		tableDadosDoTecnico.setModel(new DefaultTableModel(new String[][] { { "Codigo", "Nome", "Telefone", "CPF" }, },
				new String[] { "Codigo", "Nome", "Telefone", "CPF" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true, true };

			public boolean isCellEditable(int row, int column) {

				if (row == 0) {

					return false;

				}
				return columnEditables[column];

			}
		});
		contentPane.add(tableDadosDoTecnico, "cell 0 10 6 3,grow");

		JButton btnAnterior = new JButton("< Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TecnicoController tecnicoController = new TecnicoController();
				int result = (int) Math
						.ceil((float) tecnicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				if (paginaAtual > 1) {

					paginaAtual--;
					String consultaValor = textFieldPesquisa.getText().trim();

					String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

					lblPaginaAtual.setText(paginaAtual + "");
					seletor.setPagina(paginaAtual);

					pesquisaTecnicos(consultaValor, comboBoxSelecionado, seletor);

				}

			}
		});
		contentPane.add(btnAnterior, "cell 0 13,alignx center,aligny center");

		JButton btnProximo = new JButton("Proximo >");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TecnicoController tecnicoController = new TecnicoController();
				int result = (int) Math
						.ceil((float) tecnicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				if (paginaAtual < result) {
					paginaAtual++;
					String consultaValor = textFieldPesquisa.getText().trim();

					String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

					lblPaginaAtual.setText(paginaAtual + "");

					pesquisaTecnicos(consultaValor, comboBoxSelecionado, seletor);

				}

			}
		});

		contentPane.add(lblPaginaAtual, "cell 1 13");

		contentPane.add(btnProximo, "cell 3 13,alignx right,aligny center");
		comboBoxLimitePagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoController tecnicoController = new TecnicoController();

				seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
				seletor.setPagina(paginaAtual);


				int result = (int) Math
						.ceil((float) tecnicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);

				pesquisaTecnicos(textFieldPesquisa.getText(),
						comboBoxLimitePagina.getSelectedItem().toString(), seletor);
			}
		});

		comboBoxLimitePagina.setModel(new DefaultComboBoxModel(limitePagina));
		contentPane.add(comboBoxLimitePagina, "cell 4 13,alignx center,aligny center");

		TecnicoController tecnicoController = new TecnicoController();

		seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
		seletor.setPagina(paginaAtual);

		pesquisaTecnicos(textFieldPesquisa.getText(), comboBoxLimitePagina.getSelectedItem().toString(), seletor);

		int result = (int) Math.ceil((float) tecnicoController.countLinhasTotalController() / seletor.getLimite());
		labelTotalPaginas.setText("\\" + result);
		contentPane.add(labelTotalPaginas, "cell 2 13");
	}

	protected String excluirCedula() {
		// TODO Auto-generated method stub
		// y
		TecnicoController tecnicoController = new TecnicoController();
		TecnicoVO tecnicoVO = new TecnicoVO();
		tecnicoVO.setIdtecnico(
				Integer.parseInt(tableDadosDoTecnico.getValueAt(tableDadosDoTecnico.getSelectedRow(), 0).toString()));
		String retorno = tecnicoController.excluirController(tecnicoVO);
		seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
		atualizarTabelaTecnico(tecnicoController.consultaTecnicosController(seletor));

		return retorno;
	}

	protected String updateTecnico() {

		TecnicoController tecnicoController = new TecnicoController();
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
	protected String inserirTecnico() {

		TecnicoController tecnicoController = new TecnicoController();
		TecnicoVO tecnicoVO = new TecnicoVO();
		tecnicoVO.setNome(textFieldNomeTecnico.getText());
		tecnicoVO.setTelefone(textFieldTelefone.getText());
		tecnicoVO.setCpf(textFieldCpf.getText());

		return tecnicoController.inserirTecnicoController(tecnicoVO);
	}

	/*
	 * Metodo de pesquisa utiliza o like para procurar dados no banco
	 * 
	 * @param consulta Ã© o que o usuario quer buscar no banco
	 * 
	 * @param comboBoxPesquisa Ã© o qual a coluna que o mesmo irÃ¡ pesquisas(nome ou
	 * id)
	 * 
	 * 
	 */

	protected void pesquisaTecnicos(String consulta, String comboBoxPesquisa, Seletor selector) {

		TecnicoController tecnicoController = new TecnicoController();
		selector.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
		selector.setPagina(paginaAtual);

		if (consulta.equals("")) {

			List<TecnicoVO> tecnicoVO = tecnicoController.consultaTecnicosController(seletor);
			atualizarTabelaTecnico(tecnicoVO);

		} else {
			List<TecnicoVO> tecnicoVO = tecnicoController.consultaTecnicosController(consulta, comboBoxPesquisa,
					selector);
			atualizarTabelaTecnico(tecnicoVO);
		}

	}

	/*
	 * 
	 * 
	 * Metado para montar as infomacao na tela
	 */
	protected void atualizarTabelaTecnico(List<TecnicoVO> tecnicoVO) {

		tableDadosDoTecnico.setModel(new DefaultTableModel(new String[][] { { "Codigo", "Nome", "Telefone", "CPF" }, },
				new String[] { "Codigo", "Nome", "Telefone", "CPF" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true, true };

			public boolean isCellEditable(int row, int column) {

				if (row == 0) {

					return false;

				}
				return columnEditables[column];

			}
		});

		DefaultTableModel modelo = (DefaultTableModel) tableDadosDoTecnico.getModel();

		for (TecnicoVO tecnico : tecnicoVO) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeÃ§alho da tabela

			// Object[] novaLinha = new Object[] { tecnico.getIdtecnico() + "",
			// tecnico.getNome(), tecnico.getTelefone(),btnExcluir.getIcon()};
			String[] novaLinha = new String[] { tecnico.getIdtecnico() + "", tecnico.getNome(), tecnico.getTelefone(),tecnico.toStringCPF()};
			modelo.addRow(novaLinha);

		}

	}
}
