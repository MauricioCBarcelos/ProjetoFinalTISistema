package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControladoraCadastroCliente;
import controller.TecnicoController;
import model.seletor.Seletor;
import model.vo.ClienteVO;
import model.vo.TecnicoVO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GerenciadorCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txttnome;
	private JTextField txtcpf;
	private JTextField txtTelefone;

	private JTextField textFieldPesquisa;
	private JComboBox comboBoxPesquisa;

	private JTable table;
	private ArrayList<ClienteVO> listClientes;
	protected ClienteVO cliente;

	private Seletor seletor = new Seletor();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadorCliente frame = new GerenciadorCliente();
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
	public GerenciadorCliente() {
		setTitle("Gerenciador De Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 435, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNome = new JLabel("Nome:");

		txttnome = new JTextField();
		txttnome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");

		txtcpf = new JTextField();
		txtcpf.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone ");

		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");

		textFieldPesquisa = new JTextField();
		textFieldPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				String consultaValor = textFieldPesquisa.getText().trim();

				String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

				pesquisaCliente(consultaValor, comboBoxSelecionado, seletor);

			}
		});
		textFieldPesquisa.setColumns(10);

		comboBoxPesquisa = new JComboBox();
		comboBoxPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Nome", "Telefone", "CPF" }));

		table = new JTable();
		table.setModel(new DefaultTableModel(new String[][] { { "Nome", "Telefone", "CPF" }, },
				new String[] { "Nome", "Telefone", "CPF" }) {

		});

		JButton btnSalvar = new JButton("Salvar");
		// Listener (ouvinte) de ação de mouse sobre o botão "Salvar"
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				ControladoraCadastroCliente controller = new ControladoraCadastroCliente();
				ClienteVO clienteVO = new ClienteVO();
				clienteVO.setNome(txttnome.getText());
				clienteVO.setCpf(txtcpf.getText());
				clienteVO.setTelefone(txtTelefone.getText());
				String mensagemRetorno = controller.salvar(clienteVO);

				JOptionPane.showMessageDialog(null, mensagemRetorno);
				ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
				seletor.setLimite(10);
				seletor.setPagina(1);
				atualizarTabelaCliente(controladoraCadastroCliente.consultaClientesController(seletor));
			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null, excluirCedula());
			}

		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(153)
							.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txttnome, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(txtcpf, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPesquisar, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(comboBoxPesquisa, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(lblCpf))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txttnome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtcpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(lblTelefone)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnSalvar)
							.addComponent(btnExcluir))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addComponent(lblPesquisar)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);

		ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
		seletor.setLimite(10);
		seletor.setPagina(1);

		atualizarTabelaCliente(controladoraCadastroCliente.consultaClientesController(seletor));
	}

	protected String excluirCedula() {
		// TODO Auto-generated method stub
		ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
		String retorno = controladoraCadastroCliente
				.excluirController(table.getValueAt(table.getSelectedRow(), 2).toString());
		atualizarTabelaCliente(controladoraCadastroCliente.consultaClientesController(seletor));

		return retorno;
	}

	protected void pesquisaCliente(String consulta, String comboBoxPesquisa, Seletor selector) {

		ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();

		if (consulta.equals("")) {

			List<ClienteVO> clienteVO = controladoraCadastroCliente.consultaClientesController(seletor);
			atualizarTabelaCliente(clienteVO);
		} else {
			List<ClienteVO> clienteVO = controladoraCadastroCliente.consultaClientesController(consulta,
					comboBoxPesquisa, selector);
			atualizarTabelaCliente(clienteVO);
		}

	}

	/*
	 * 
	 * 
	 * Metado para montar as infomacao na tela
	 */
	protected void atualizarTabelaCliente(List<ClienteVO> clientesVO) {

		table.setModel(new DefaultTableModel(new String[][] { { "Nome", "Telefone", "cpf" }, },
				new String[] { "Nome", "Telefone", "cpf" }) {
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

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();

		for (ClienteVO clienteVO : clientesVO) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeÃ§alho da tabela
			String[] novaLinha = new String[] { clienteVO.getNome(), clienteVO.getTelefone(), clienteVO.getCpf() };
			modelo.addRow(novaLinha);

		}
		// totalpaginas = (modelo.getRowCount() + 1) % seletor.getLimite();
	}
}