package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
		setTitle("AugustoTi Sistemas Assistencia Tecnica");

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
		tblChamados.setModel( 
				new DefaultTableModel(new String[][] { { "Chamados", "Clientes", "Serviço", "Produtos", "Tecnico" }, },
						new String[] { "Chamados", "Clientes", "Serviço", "Produtos", "Tecnico" }));

		getContentPane().add(tblChamados);
	}
}
