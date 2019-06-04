package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class RelatorioChamado extends JPanel {

	/**
	 * Create the panel.
	 */
	final private String chamadoN = "Chamado N°";
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	public RelatorioChamado() {
		setLayout(null);

		JLabel lblNumChamado = new JLabel(chamadoN);
		lblNumChamado.setBounds(0, 11, 63, 14);
		add(lblNumChamado);

		table = new JTable();
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				new String[][] { { "ID", "Cliente", "CPF", "Celular", "Telefone", "Data Chamado", "Status" }, },
				new String[] { "ID", "Cliente", "CPF", "Celular", "Telefone", "Data Chamado", "Status" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(197);
		table.getColumnModel().getColumn(2).setPreferredWidth(153);
		table.getColumnModel().getColumn(3).setPreferredWidth(111);
		table.getColumnModel().getColumn(4).setPreferredWidth(104);
		table.getColumnModel().getColumn(5).setPreferredWidth(122);
		table.setBounds(0, 46, 523, 58);
		add(table);

		JLabel lblProblemaRelatado = new JLabel("Problema Relatado:");
		lblProblemaRelatado.setBounds(0, 117, 105, 22);
		add(lblProblemaRelatado);

		JTextPane txtpnRgfgsgsggs = new JTextPane();
		txtpnRgfgsgsggs.setEditable(false);
		txtpnRgfgsgsggs.setBounds(0, 137, 450, 29);
		add(txtpnRgfgsgsggs);

		JLabel lblProblemaCostatado = new JLabel("Problema Costatado:");
		lblProblemaCostatado.setBounds(0, 177, 105, 14);
		add(lblProblemaCostatado);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(0, 194, 450, 29);
		add(textPane);

		JLabel lblObservao = new JLabel("Observação:");
		lblObservao.setBounds(0, 234, 63, 14);
		add(lblObservao);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(0, 248, 450, 29);
		add(textPane_1);

		table_1 = new JTable();
		table_1.setBorder(null);
		table_1.setModel(
				new DefaultTableModel(new String[][] { { "Categoria", "Equipamento", "Marca", "Modelo", "Obs" } },
						new String[] { "Categoria", "Equipamento", "Marca", "Modelo", "Obs" }) {
					Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class,
							String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		table_1.setBounds(0, 302, 513, 58);
		add(table_1);

		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(new String[][] {

				{ "Servi\u00E7o", "Valor unit\u00E1rio", "Valor de Servi\u00E7o", "Valor de produto", "Total" } },
				new String[] { "Servi\u00E7o", "Valor unit\u00E1rio", "Valor de Servi\u00E7o", "Valor de produto",
						"Total" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_2.getColumnModel().getColumn(0).setPreferredWidth(146);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(92);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(112);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(126);
		table_2.setBounds(0, 388, 523, 80);
		add(table_2);
		
		JButton btnExcel = new JButton("");
		btnExcel.setIcon(new ImageIcon("C:\\Users\\mauricio.barcelos@flexcontact.com.br\\git\\ProjetoFinalTISistema\\src\\main\\java\\icones\\icons8-ms-excel-48.png"));
		btnExcel.setBounds(533, 388, 63, 48);
		add(btnExcel);

	}
}
