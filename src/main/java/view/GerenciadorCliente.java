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
import controller.ProdutoController;
import controller.TecnicoController;
import model.seletor.Seletor;
import model.vo.ClienteVO;
import model.vo.TecnicoVO;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class GerenciadorCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txttnome;
	private JTextField txtcpf;
	private JTextField txtTelefone;

	private JTextField textFieldPesquisa;
	private JComboBox comboBoxPesquisa;

	private JTable tableClientes;
	protected ClienteVO cliente;

	private Seletor seletor = new Seletor();
	private JButton btnAnterior;
	private JButton btnProximo;
	private JLabel lblPaginaAtual;
	private JLabel labelTotalPaginas;

	private String[] limitePagina = { "10", "20", "50", "100", "1000" };
	private JComboBox comboBoxLimitePagina;
    private int paginaAtual = 1;
    private JLabel labelEmail;
    private JTextField textFieldEmail;
    private JButton buttonAtualizar;
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
		setBounds(100, 100, 533, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNome = new JLabel("Nome:");

		txttnome = new JTextField();
		txttnome.setColumns(10);

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

		tableClientes = new JTable();
		tableClientes.setModel(new DefaultTableModel(new String[][] { { "Nome", "Telefone", "CPF","Email" }, },
				new String[] { "Nome", "Telefone", "CPF","Email" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, true, true, true };

			public boolean isCellEditable(int row, int column) {

				if (row == 0) {

					return false;

				}
				return columnEditables[column];

			}
		});
		contentPane.setLayout(new MigLayout("", "[110px,grow][6px][56px][35.00px][18.00px][157.00,grow][146px,grow]", "[14px][20px][14px][23px][][][][][14px][20px][213px,grow][]"));
		contentPane.add(lblNome, "cell 0 0,alignx left,aligny top");
		
				JLabel lblCpf = new JLabel("CPF:");
				contentPane.add(lblCpf, "cell 3 0,alignx left,aligny top");
		contentPane.add(txttnome, "cell 0 1 3 1,growx,aligny top");
		
				txtcpf = new JTextField();
				txtcpf.setColumns(10);
				contentPane.add(txtcpf, "cell 3 1,growx,aligny top");
		contentPane.add(lblTelefone, "cell 0 2,alignx left,aligny top");
		contentPane.add(txtTelefone, "cell 0 3,growx,aligny center");
		
				JButton btnSalvar = new JButton("Salvar");
				// Listener (ouvinte) de ação de mouse sobre o botão "Salvar"
				btnSalvar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						JOptionPane.showMessageDialog(null, inserir());
						ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();

						int result = (int) Math.ceil((float) controladoraCadastroCliente.countLinhasTotalController() / seletor.getLimite());
						labelTotalPaginas.setText("\\" + result);
						pesquisaCliente(textFieldPesquisa.getText().trim(),comboBoxPesquisa.getSelectedItem().toString().trim(), seletor);
					}
				});
				contentPane.add(btnSalvar, "cell 5 3,alignx left,aligny top");
		
		labelEmail = new JLabel("Email:");
		contentPane.add(labelEmail, "cell 0 5");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		contentPane.add(textFieldEmail, "cell 0 6 4 1,growx");
		contentPane.add(lblPesquisar, "cell 0 8,growx,aligny top");
		
				JButton btnExcluir = new JButton("Excluir");
				btnExcluir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						JOptionPane.showMessageDialog(null, excluirCedula());

						ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
						int result = (int) Math
								.ceil((float) controladoraCadastroCliente.countLinhasTotalController() / seletor.getLimite());
						labelTotalPaginas.setText("\\" + result);
						pesquisaCliente(textFieldPesquisa.getText().trim(), comboBoxPesquisa.getSelectedItem().toString().trim(), seletor);

					}

				});
				
				buttonAtualizar = new JButton("Atualizar");
				buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, update());
				ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();

				int result = (int) Math.ceil((float) controladoraCadastroCliente.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				pesquisaCliente(textFieldPesquisa.getText().trim(),
						comboBoxPesquisa.getSelectedItem().toString().trim(), seletor);
			}
		});
				contentPane.add(buttonAtualizar, "flowx,cell 5 8");
				contentPane.add(btnExcluir, "cell 5 8,alignx right,aligny top");
		contentPane.add(textFieldPesquisa, "cell 0 9 3 1,growx,aligny top");
		
				comboBoxPesquisa = new JComboBox();
				comboBoxPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Nome", "Telefone", "CPF" }));
				contentPane.add(comboBoxPesquisa, "cell 5 9,growx,aligny top");
		contentPane.add(tableClientes, "cell 0 10 7 1,grow");
		
		btnAnterior = new JButton("< Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
				int result = (int) Math.ceil((float) controladoraCadastroCliente.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				if (paginaAtual > 1) {
					paginaAtual--;
					String consultaValor = textFieldPesquisa.getText().trim();

					String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

					lblPaginaAtual.setText(paginaAtual + "");

					pesquisaCliente(consultaValor, comboBoxSelecionado, seletor);

				}

			}
		});
		contentPane.add(btnAnterior, "cell 0 11");
		
		btnProximo = new JButton("Proximo >");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
				int result = (int) Math.ceil((float) controladoraCadastroCliente.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				if (paginaAtual < result) {
					paginaAtual++;
					String consultaValor = textFieldPesquisa.getText().trim();

					String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

					lblPaginaAtual.setText(paginaAtual + "");

					pesquisaCliente(consultaValor, comboBoxSelecionado, seletor);

				}

			}
		});
		
		lblPaginaAtual = new JLabel("1");
		contentPane.add(lblPaginaAtual, "flowx,cell 2 11,alignx left");
		contentPane.add(btnProximo, "cell 3 11");
		
		labelTotalPaginas = new JLabel("\\");
		
		
		comboBoxLimitePagina = new JComboBox();
		comboBoxLimitePagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();

				seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
				seletor.setPagina(paginaAtual);


				int result = (int) Math
						.ceil((float) controladoraCadastroCliente.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);

				pesquisaCliente(textFieldPesquisa.getText(),
						comboBoxLimitePagina.getSelectedItem().toString(), seletor);
			}
		});
		
		comboBoxLimitePagina.setModel(new DefaultComboBoxModel(limitePagina));
		contentPane.add(comboBoxLimitePagina, "cell 5 11,alignx center,aligny center");

		ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
		seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
		seletor.setPagina(paginaAtual);
		int result = (int) Math
				.ceil((float) controladoraCadastroCliente.countLinhasTotalController() / seletor.getLimite());
		labelTotalPaginas.setText("\\" + result);
		
		pesquisaCliente(textFieldPesquisa.getText(), comboBoxLimitePagina.getSelectedItem().toString(), seletor);
		contentPane.add(labelTotalPaginas, "cell 2 11");
	}

	protected String excluirCedula() {
		// TODO Auto-generated method stub
		ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
		String retorno = controladoraCadastroCliente
				.excluirController(tableClientes.getValueAt(tableClientes.getSelectedRow(), 2).toString());
		atualizarTabelaCliente(controladoraCadastroCliente.consultaClientesController(seletor));

		return retorno;
	}

	protected void pesquisaCliente(String consulta, String comboBoxPesquisa, Seletor selector) {

		ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
		selector.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
		selector.setPagina(paginaAtual);
		
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

		tableClientes.setModel(new DefaultTableModel(new String[][] { { "Nome", "Telefone", "CPF","Email" }, },
				new String[] { "Nome", "Telefone", "CPF","Email" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, true, true, true };

			public boolean isCellEditable(int row, int column) {

				if (row == 0) {

					return false;

				}
				return columnEditables[column];

			}
		});

		DefaultTableModel modelo = (DefaultTableModel) tableClientes.getModel();

		for (ClienteVO clienteVO : clientesVO) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeÃ§alho da tabela
			String[] novaLinha = new String[] { clienteVO.getNome(), clienteVO.getTelefone(), clienteVO.getCpf(),clienteVO.getEmail() };
			modelo.addRow(novaLinha);

		}
	}
	
	protected String update() {

		ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setNome(tableClientes.getValueAt(tableClientes.getSelectedRow(), 0).toString());
		clienteVO.setTelefone(tableClientes.getValueAt(tableClientes.getSelectedRow(), 1).toString());
		clienteVO.setCpf(tableClientes.getValueAt(tableClientes.getSelectedRow(), 2).toString());
		clienteVO.setEmail(tableClientes.getValueAt(tableClientes.getSelectedRow(), 3).toString());
		
		return controladoraCadastroCliente.updateController(clienteVO);

	}
	
	protected String inserir() {
		
		ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setNome(txttnome.getText());
		clienteVO.setCpf(txtcpf.getText());
		clienteVO.setTelefone(txtTelefone.getText());
		clienteVO.setEmail(textFieldEmail.getText());
		

		return controladoraCadastroCliente.salvar(clienteVO);
	}
}