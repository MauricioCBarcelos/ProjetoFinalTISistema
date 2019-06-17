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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ChamadoController;
import model.dto.TelaInicialDTO;

import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JMenu;

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

		JMenuItem mntmNewMenuItem = new JMenuItem("Criar novo chamado");
		mnNewMenu.add(mntmNewMenuItem);

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
