package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;

public class RelatorioChamado extends JPanel {

	/**
	 * Create the panel.
	 */
	final private String chamadoN = "Chamado N°";
	private JTable table;

	public RelatorioChamado() {
		setLayout(null);

		JLabel lblNumChamado = new JLabel(chamadoN);
		lblNumChamado.setBounds(0, 11, 63, 14);
		add(lblNumChamado);

		table = new JTable();
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "Cliente", "CPF", "Celular", "Telefone", "Data Chamado", "Status"},
			},
			new String[] {
				"ID", "Cliente", "CPF", "Celular", "Telefone", "Data Chamado", "Status"
			}
		));
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
		txtpnRgfgsgsggs.setBounds(0, 137, 332, 29);
		add(txtpnRgfgsgsggs);
		
		JLabel lblProblemaCostatado = new JLabel("Problema Costatado:");
		lblProblemaCostatado.setBounds(0, 177, 105, 14);
		add(lblProblemaCostatado);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(0, 194, 332, 29);
		add(textPane);
		
		JLabel lblObservao = new JLabel("Observação:");
		lblObservao.setBounds(0, 234, 63, 14);
		add(lblObservao);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(0, 248, 332, 29);
		add(textPane_1);

	}
}
