package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ChamadoController;
import model.dto.TelaInicialDTO;

import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class TelaInicial extends JFrame {
	private JTable tblChamados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {
		setTitle("Augusto & MauricioTi Sistemas Assistencia Tecnica");

		JMenuBar jMenuBar = new JMenuBar();

		JMenuItem name = new JMenuItem();
		setJMenuBar(jMenuBar);

		JMenu mnNewMenu = new JMenu("Novo");
		jMenuBar.add(mnNewMenu);

		JMenuItem mntmNovoChamado = new JMenuItem("Criar novo chamado");
		mntmNovoChamado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GerenciadorCliente gerenciadorClienteFrame = new GerenciadorCliente();
				gerenciadorClienteFrame.setVisible(true);
			}
		});
		JMenuItem mntmNovoTecnico = new JMenuItem("Criar novo Tecnico");
		mntmNovoTecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GerenciadordeTecnico gerenciadordeTecnicoFrame = new GerenciadordeTecnico();

				JScrollPane scroll = new JScrollPane(gerenciadordeTecnicoFrame.getContentPane());
				gerenciadordeTecnicoFrame.setContentPane(scroll);

				gerenciadordeTecnicoFrame.setVisible(true);
			}
		});
		JMenuItem mntmNovoServico = new JMenuItem("Criar novo Servico");
		mntmNovoServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciadorServico GerenciadorServicoFrame = new GerenciadorServico();

				JScrollPane scroll = new JScrollPane(GerenciadorServicoFrame.getContentPane());
				GerenciadorServicoFrame.setContentPane(scroll);

				GerenciadorServicoFrame.setVisible(true);
			}
		});

		JMenuItem mntmNovoProduto = new JMenuItem("Criar novo Produto");
		mntmNovoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciadorProduto gerenciadorProdutoFrame = new GerenciadorProduto();

				JScrollPane scroll = new JScrollPane(gerenciadorProdutoFrame.getContentPane());
				gerenciadorProdutoFrame.setContentPane(scroll);
				gerenciadorProdutoFrame.setVisible(true);
			}
		});

		JMenuItem mntmNovoCliente = new JMenuItem("Gerenciamento de clientes");
		mntmNovoCliente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				GerenciadorCliente gerenciadorClienteFrame = new GerenciadorCliente();

				JScrollPane scroll = new JScrollPane(gerenciadorClienteFrame.getContentPane());
				gerenciadorClienteFrame.setContentPane(scroll);

				gerenciadorClienteFrame.setVisible(true);
			}
		});

		mnNewMenu.add(mntmNovoChamado);
		mnNewMenu.add(mntmNovoTecnico);
		mnNewMenu.add(mntmNovoServico);
		mnNewMenu.add(mntmNovoProduto);
		mnNewMenu.add(mntmNovoCliente);
		
		JMenuItem mntmListachamado = new JMenuItem("ListaChamado");
		mntmListachamado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ListaChamado listachamadoFrame = new ListaChamado();

				JScrollPane scroll = new JScrollPane(listachamadoFrame.getContentPane());
				listachamadoFrame.setContentPane(scroll);

				listachamadoFrame.setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmListachamado);
		
		JMenu menuPessoas = new JMenu("Gerenciamento de pessoas");
		menuPessoas.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));
		menuPessoas.setIcon(new ImageIcon("C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-macho-de-configurações-de-admin-64.png"));
		jMenuBar.add(menuPessoas);
		
		JMenuItem mntmGerenciamentoDeTecnico = new JMenuItem("Gerenciamento de tecnicos");
		mntmGerenciamentoDeTecnico.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmGerenciamentoDeTecnico.setIcon(new ImageIcon("C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-administrador-masculino-64.png"));
		mntmGerenciamentoDeTecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciadordeTecnico gerenciadordeTecnicoFrame = new GerenciadordeTecnico();

				JScrollPane scroll = new JScrollPane(gerenciadordeTecnicoFrame.getContentPane());
				gerenciadordeTecnicoFrame.setContentPane(scroll);

				gerenciadordeTecnicoFrame.setVisible(true);
			}
		});
		menuPessoas.add(mntmGerenciamentoDeTecnico);

		JMenuItem mntmGerenciamentoDeClientes = new JMenuItem("Gerenciamento de Clientes");
		mntmGerenciamentoDeClientes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmGerenciamentoDeClientes.setIcon(new ImageIcon("C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-usuário-64.png"));
		mntmGerenciamentoDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GerenciadorCliente gerenciadorClienteFrame = new GerenciadorCliente();

				JScrollPane scroll = new JScrollPane(gerenciadorClienteFrame.getContentPane());
				gerenciadorClienteFrame.setContentPane(scroll);

				gerenciadorClienteFrame.setVisible(true);
			}
		});
		menuPessoas.add(mntmGerenciamentoDeClientes);
		
		JMenu mnGerenciamentoDeProdutos = new JMenu("Gerenciamento de Produtos e Serviços");
		mnGerenciamentoDeProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		mnGerenciamentoDeProdutos.setIcon(new ImageIcon("C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-placa-mãe-64.png"));
		jMenuBar.add(mnGerenciamentoDeProdutos);
		
		JMenuItem mntmGerenciamentoDeProdutos = new JMenuItem("Gerenciamento de produtos");
		mntmGerenciamentoDeProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciadorProduto gerenciadorProdutoFrame = new GerenciadorProduto();

				JScrollPane scroll = new JScrollPane(gerenciadorProdutoFrame.getContentPane());
				gerenciadorProdutoFrame.setContentPane(scroll);
				gerenciadorProdutoFrame.setVisible(true);
			}
		});
		mntmGerenciamentoDeProdutos.setIcon(new ImageIcon("C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-monitor-64.png"));
		mnGerenciamentoDeProdutos.add(mntmGerenciamentoDeProdutos);
		
		JMenuItem mntmGerenciamentoDeServios = new JMenuItem("Gerenciamento de serviços");
		mntmGerenciamentoDeServios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GerenciadorServico GerenciadorServicoFrame = new GerenciadorServico();

				JScrollPane scroll = new JScrollPane(GerenciadorServicoFrame.getContentPane());
				GerenciadorServicoFrame.setContentPane(scroll);

				GerenciadorServicoFrame.setVisible(true);
			}
		});
		mntmGerenciamentoDeServios.setIcon(new ImageIcon("C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-ferramentas-administrativas-64.png"));
		mnGerenciamentoDeProdutos.add(mntmGerenciamentoDeServios);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 310);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		tblChamados = new JTable();
		tblChamados.setEnabled(false);
		tblChamados.setFillsViewportHeight(true);
		tblChamados.setModel(
				new DefaultTableModel(new String[][] { { "Chamados", "Clientes", "Serviço", "Produtos", "Tecnico" }, },
						new String[] { "Chamados", "Clientes", "Serviço", "Produtos", "Tecnico" }));

		getContentPane().add(tblChamados);

		ChamadoController chamadoController = new ChamadoController();

		atualizarTabelaChamados(chamadoController.ConsultaChamados());

	}

	protected void consultarProdutos() {

		// List<Produto> produtos = controlador.listarProdutos(seletor);

		// atualizarTabelaProdutos(produtos);
		ChamadoController chamadoController = new ChamadoController();
		List<TelaInicialDTO> v_telaInicial = chamadoController.ConsultaChamados();
		atualizarTabelaChamados(v_telaInicial);
	}

	protected void atualizarTabelaChamados(List<TelaInicialDTO> v_telaInicial) {
		// atualiza o atributo produtosConsultados

		// Limpa a tabela
		tblChamados.setModel(
				new DefaultTableModel(new String[][] { { "Chamados", "Clientes", "Serviço", "Produtos", "Tecnico" }, },
						new String[] { "Chamados", "Clientes", "Serviço", "Produtos", "Tecnico" }));

		DefaultTableModel modelo = (DefaultTableModel) tblChamados.getModel();

		for (TelaInicialDTO v : v_telaInicial) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeçalho da tabela

			String[] novaLinha = new String[] { v.getIdChamado() + "", v.getNome_cliente(), v.getNome_servico(),
					v.getModelo(), v.getNome_Tecnico() };
			modelo.addRow(novaLinha);
		}

	}
}
