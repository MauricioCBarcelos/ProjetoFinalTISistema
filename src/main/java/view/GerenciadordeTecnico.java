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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import controller.ChamadoController;
import controller.TecnicoController;
import model.dto.telaInicialDTO;
import model.vo.TecnicoVO;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GerenciadordeTecnico extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNomeTecnico;
	private JTextField textFieldTelefone;
	private JTable tableDadosDoTecnico;
	private JTextField textFieldPesquisa;

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
		setBounds(100, 100, 428, 396);
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

		TecnicoController tecnicoController = new TecnicoController();
		atualizarTabelaTecnico(tecnicoController.consultaTecnicosController());
		JButton btnProvisotio = new JButton("Criar");
		btnProvisotio.setFont(new Font("Arial", Font.PLAIN, 13));
		btnProvisotio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent btnInserir) {
				inserirTecnico(textFieldNomeTecnico.getText(), textFieldTelefone.getText());

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
		textFieldPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent tecla) {

				String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString();
				pesquisaTecnicos(textFieldPesquisa.getText(), comboBoxSelecionado);

			}
		});
		textFieldPesquisa.setColumns(10);
		textFieldPesquisa.setBounds(10, 148, 150, 20);
		contentPane.add(textFieldPesquisa);

		JLabel lblPesquisa = new JLabel("Pesquisa:");
		lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPesquisa.setBounds(10, 123, 60, 14);
		contentPane.add(lblPesquisa);
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
	
	return tecnicoController.inserirTecnicoController(nome,telefone);
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
		List<TecnicoVO> tecnicoVO = tecnicoController.consultaTecnicosController(consulta, comboBoxPesquisa);
		atualizarTabelaTecnico(tecnicoVO);
	}

	protected void atualizarTabelaTecnico(List<TecnicoVO> tecnicoVO) {
		// atualiza o atributo produtosConsultados

		// Limpa a tabela
		tableDadosDoTecnico.setModel(new DefaultTableModel(new String[][] { { "Codigo", "Nome", "Telefone" }, },
				new String[] { "Codigo", "Nome", "Telefone" }));

		DefaultTableModel modelo = (DefaultTableModel) tableDadosDoTecnico.getModel();

		for (TecnicoVO tecnico : tecnicoVO) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeçalho da tabela

			String[] novaLinha = new String[] { tecnico.getIdtecnico() + "", tecnico.getNome(), tecnico.getTelefone() };
			modelo.addRow(novaLinha);
		}

	}

}
