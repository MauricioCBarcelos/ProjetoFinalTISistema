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

import controller.ListaChamadoController;
import controller.TecnicoController;
import model.dto.ListaChamadoDTO;
import model.vo.TecnicoVO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "codigo", "nome", "telefone" }));
		comboBox.setBounds(183, 26, 141, 20);
		contentPane.add(comboBox);
		tableListaChamado = new JTable();
		tableListaChamado.setFillsViewportHeight(true);
		tableListaChamado.setBounds(0, 57, 679, 350);
		tableListaChamado.setModel(new DefaultTableModel(
				new Object[][] { { "C\u00F3digo", "Cliente", "Telefone", "Status", "modelo", "Valor Total" }, },
				new String[] { "C\u00F3digo", "Cliente", "Telefone", "Status", "modelo", "Valor Total" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableListaChamado.getColumnModel().getColumn(0).setResizable(false);
		tableListaChamado.getColumnModel().getColumn(1).setResizable(false);
		tableListaChamado.getColumnModel().getColumn(2).setResizable(false);
		tableListaChamado.getColumnModel().getColumn(3).setResizable(false);
		tableListaChamado.getColumnModel().getColumn(4).setResizable(false);
		tableListaChamado.getColumnModel().getColumn(5).setResizable(false);
		contentPane.add(tableListaChamado);

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				String consultaValor = textField.getText().trim();

				System.out.println(consultaValor);
				String comboBoxSelecionado = comboBox.getSelectedItem().toString().trim();

				pesquisaListaChamado(consultaValor, comboBoxSelecionado);

			}
		});
		ListaChamadoController listachamado = new ListaChamadoController();
		atualizarTabelaListarChamado(listachamado.ConsultaChamados());
	}

	protected void pesquisaListaChamado(String consulta, String comboBoxPesquisa) {

		// List<Produto> produtos = controlador.listarProdutos(seletor);

		// atualizarTabelaProdutos(produtos);
		ListaChamadoController listachamado = new ListaChamadoController();

		if (consulta.equals("")) {
			List<ListaChamadoDTO> listaChamadoDTO = listachamado.ConsultaChamados();
			atualizarTabelaListarChamado(listaChamadoDTO);
		} else {

			List<ListaChamadoDTO> listaChamadoDTO = listachamado.ConsultaChamados(consulta, comboBoxPesquisa);
			atualizarTabelaListarChamado(listaChamadoDTO);
		}

	}

	protected void atualizarTabelaListarChamado(List<ListaChamadoDTO> listaChamadoDTO) {
		// atualiza o atributo produtosConsultados

		// Limpa a tabela

		tableListaChamado.setModel(new DefaultTableModel(
				new Object[][] { { "C\u00F3digo", "Cliente", "Telefone", "Status", "modelo", "Valor Total" }, },
				new String[] { "C\u00F3digo", "Cliente", "Telefone", "Status", "modelo", "Valor Total" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		DefaultTableModel modelo = (DefaultTableModel) tableListaChamado.getModel();
		// btnExcluir.setIcon(new
		// ImageIcon("C:\\Users\\MCB_home.000\\git\\ProjetoFinalTISistema\\src\\main\\java\\iconesicons8-ms-excel-48.png"));
		for (ListaChamadoDTO listaChamado : listaChamadoDTO) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeçalho da tabela

			// Object[] novaLinha = new Object[] { tecnico.getIdtecnico() + "",
			// tecnico.getNome(), tecnico.getTelefone(),btnExcluir.getIcon()};
			String[] novaLinha = new String[] { listaChamado.getIdchamado() + "", listaChamado.getNome(),
					listaChamado.getTelefone(), listaChamado.getStatus() + "", listaChamado.getModelo(),
					"" + listaChamado.getTotal() + "" };
			modelo.addRow(novaLinha);

		}

	}

}
