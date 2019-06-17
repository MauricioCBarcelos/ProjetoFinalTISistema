package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.dto.ListaChamadoDTO;

public class ListaChamado extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable tableListaChamado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaChamado frame = new ListaChamado();
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
	public ListaChamado() {
		setTitle("Lista De Chamado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPesquisar = new JLabel("Pesquisar ");
		lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPesquisar.setBounds(10, 11, 70, 14);
		contentPane.add(lblPesquisar);

		textField = new JTextField();
		textField.setBounds(10, 26, 163, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "codigo", "cpf", "nome", "telefone" }));
		comboBox.setBounds(183, 26, 141, 20);
		contentPane.add(comboBox);

		tableListaChamado = new JTable();
		tableListaChamado.setBounds(0, 57, 679, 350);
		tableListaChamado.setModel(new DefaultTableModel(
				new String[][] { { "Código", "Cliente", "Telefone", "Status", "modelo", "Valor Total", }, },
				new String[] { "Código", "Cliente", "Telefone", "Status", "odelo", "Valor Total", }));
		contentPane.add(tableListaChamado);

	}

	protected void atualizarTabelaProduto(List<ListaChamadoDTO> listaChamadosDTO) {
		// atualiza o atributo produtosConsultados

		// Limpa a tabela

		tableListaChamado.setModel(new DefaultTableModel(
				new String[][] {
						{ "Código", "Cliente", "Telefone", "Status", "Categoria Equipamento", "Valor Total", }, },
				new String[] { "Código", "Cliente", "Telefone", "Status", "Categoria Equipamento", "Valor Total", }));

		DefaultTableModel modelo = (DefaultTableModel) tableListaChamado.getModel();
		// btnExcluir.setIcon(new
		// ImageIcon("C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\iconesicons8-ms-excel-48.png"));
		for (ListaChamadoDTO listachamado : listaChamadosDTO) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do listachamado
			// na ORDEM do cabeçalho da tabela

			// Object[] novaLinha = new Object[] { tecnico.getIdlistachamado() + "",
			// tecnico.getNome(), tecnico.getTelefone(),btnExcluir.getIcon()};
			String[] novaLinha = new String[] {
					listachamado.getIdchamado() + "" + listachamado.getNome() + "" + listachamado.getTelefone() };
			modelo.addRow(novaLinha);

		}

	}

}
