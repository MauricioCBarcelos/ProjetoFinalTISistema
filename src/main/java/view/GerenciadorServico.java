package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ServicoController;
import controller.TecnicoController;
import model.seletor.Seletor;
import model.vo.ServicoVO;
import model.vo.TecnicoVO;
import net.miginfocom.swing.MigLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GerenciadorServico extends JFrame {
	private JTextField textFieldNome;
	private JTextField textFieldValor;
	private JTextField textFieldPesquisa;
	private JLabel labelPesquisa;
	private JTable tableDadosServico;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton buttonAtualizar;
	private JButton btnExcluir;
	private JComboBox comboBoxLimitePagina;
	private int paginaAtual = 1;
	private JComboBox comboBoxPesquisa = new JComboBox();
	private JLabel lblPaginaAtual = new JLabel("1");
	private String[] limitePagina = { "10", "20", "50", "100", "1000" };
	private JLabel labelTotalPaginas = new JLabel("\\");
	private Seletor seletor = new Seletor();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadorServico frame = new GerenciadorServico();
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
	public GerenciadorServico() {
		setTitle("Gerenciador Serviço");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 513, 396);
		getContentPane().setLayout(new MigLayout("", "[][123.00px][127px][][14px][89px,grow]", "[14px][23px][14px][22px][155.00][28.00px]"));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
		getContentPane().add(lblNome, "cell 1 0,alignx left,aligny center");
		
		textFieldNome = new JTextField();
		getContentPane().add(textFieldNome, "cell 1 1,growx,aligny center");
		textFieldNome.setColumns(10);
		
		JLabel labelValor = new JLabel("Valor:");
		labelValor.setFont(new Font("Arial", Font.PLAIN, 13));
		getContentPane().add(labelValor, "cell 2 0,alignx left,aligny center");
		
		textFieldValor = new JTextField();
		textFieldValor.setColumns(10);
		getContentPane().add(textFieldValor, "cell 2 1,growx,aligny center");
		
		JButton btnCriar = new JButton("Inserir");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ServicoController servicoController = new ServicoController();
				String retornoInserirTecnico = inserir();
				

				atualizarTabela(servicoController.consultaController(seletor));
				JOptionPane.showMessageDialog(null, retornoInserirTecnico);

				int result = (int) Math.ceil((float) servicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);

			}
		});
		getContentPane().add(btnCriar, "cell 3 1,growx,aligny top");
		
		buttonAtualizar = new JButton("Atualizar");
		buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				JOptionPane.showMessageDialog(null, update());
				ServicoController servicoController = new ServicoController();
				atualizarTabela(servicoController.consultaController(seletor));

				int result = (int) Math
						.ceil((float) servicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
			}
		});
		getContentPane().add(buttonAtualizar, "cell 4 2");
		
		textFieldPesquisa = new JTextField();
		textFieldPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String consultaValor = textFieldPesquisa.getText().trim();

				String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

				pesquisa(consultaValor, comboBoxSelecionado, seletor);
			}
		});
		textFieldPesquisa.setColumns(10);
		getContentPane().add(textFieldPesquisa, "cell 1 3,growx,aligny center");
		
		labelPesquisa = new JLabel("Pesquisa:");
		labelPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		getContentPane().add(labelPesquisa, "cell 1 2,alignx left,aligny center");
		
		
		comboBoxPesquisa.setModel(new DefaultComboBoxModel(new String[] { "Nome", "Id" }));
		comboBoxPesquisa.setSelectedIndex(0);
		getContentPane().add(comboBoxPesquisa, "cell 2 3,grow");
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, excluirCedula());

			ServicoController servicoController = new ServicoController();
				int result = (int) Math
						.ceil((float) servicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				String consultaValor = textFieldPesquisa.getText().trim();

				String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();
				pesquisa(consultaValor, comboBoxSelecionado, seletor);
			}
		});
		getContentPane().add(btnExcluir, "cell 4 3");
		
		tableDadosServico = new JTable();
		tableDadosServico.setModel(new DefaultTableModel(new String[][] { { "Codigo", "Nome", "Valor"}, },
				new String[] { "Codigo", "Nome", "Valor" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true};

			public boolean isCellEditable(int row, int column) {

				if (row == 0) {

					return false;

				}
				return columnEditables[column];

			}
		});
		//tableDadosDoTecnico.setBounds(10, 207, 366, 120);
		getContentPane().add(tableDadosServico, "cell 1 4 5 1,grow");
		
		btnAnterior = new JButton("< Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ServicoController servicoController = new ServicoController();
				int result = (int) Math
						.ceil((float) servicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				if (paginaAtual > 1) {

					paginaAtual--;
					String consultaValor = textFieldPesquisa.getText().trim();

					String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

					lblPaginaAtual.setText(paginaAtual + "");
					seletor.setPagina(paginaAtual);

					pesquisa(consultaValor, comboBoxSelecionado, seletor);

				}
			}
		});
		getContentPane().add(btnAnterior, "flowx,cell 1 5");
		
		btnProximo = new JButton("Proximo >");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				ServicoController servicoController = new ServicoController();
				int result = (int) Math
						.ceil((float) servicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);
				if (paginaAtual < result) {
					paginaAtual++;
					String consultaValor = textFieldPesquisa.getText().trim();

					String comboBoxSelecionado = comboBoxPesquisa.getSelectedItem().toString().trim();

					lblPaginaAtual.setText(paginaAtual + "");

					pesquisa(consultaValor, comboBoxSelecionado, seletor);

				}
			}
		});
		getContentPane().add(btnProximo, "flowx,cell 2 5,alignx left");
		
		lblPaginaAtual = new JLabel("1");
		getContentPane().add(lblPaginaAtual, "cell 1 5");
		
		labelTotalPaginas = new JLabel("\\0");
		getContentPane().add(labelTotalPaginas, "cell 1 5");
		
		comboBoxLimitePagina = new JComboBox();
		comboBoxLimitePagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ServicoController servicoController = new ServicoController();

				seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
				seletor.setPagina(paginaAtual);


				int result = (int) Math.ceil((float) servicoController.countLinhasTotalController() / seletor.getLimite());
				labelTotalPaginas.setText("\\" + result);

				pesquisa(textFieldPesquisa.getText(),comboBoxLimitePagina.getSelectedItem().toString(), seletor);
			}
		});
		comboBoxLimitePagina.setModel(new DefaultComboBoxModel(limitePagina));
		
		getContentPane().add(comboBoxLimitePagina, "cell 3 5");
		ServicoController servicoController = new ServicoController();
		seletor.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
		seletor.setPagina(paginaAtual);

		pesquisa(textFieldPesquisa.getText(), comboBoxLimitePagina.getSelectedItem().toString(), seletor);

		int result = (int) Math.ceil((float) servicoController.countLinhasTotalController() / seletor.getLimite());
		labelTotalPaginas.setText("\\" + result);
		
		
	}
	private String excluirCedula() {
		// TODO Auto-generated method stub
		// y

		ServicoController servicoController = new ServicoController();
		ServicoVO servicoVO = new ServicoVO();
		servicoVO.setIdservico(Integer.parseInt(tableDadosServico.getValueAt(tableDadosServico.getSelectedRow(), 0).toString()));
		String retorno = servicoController.excluirController(servicoVO);
		return retorno;
	}

	private String update() {

		ServicoController servicoController = new ServicoController();
		ServicoVO servicoVO = new ServicoVO();
		servicoVO.setIdservico(Integer.parseInt(tableDadosServico.getValueAt(tableDadosServico.getSelectedRow(), 0).toString()));
		servicoVO.setNome(tableDadosServico.getValueAt(tableDadosServico.getSelectedRow(), 1).toString());
		servicoVO.setValor(Float.parseFloat(tableDadosServico.getValueAt(tableDadosServico.getSelectedRow(), 2).toString()));
		
		return servicoController.updateController(servicoVO);

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
	private String inserir() {

		ServicoController servicoController = new ServicoController();
		ServicoVO servicoVO = new ServicoVO();
		servicoVO.setNome(textFieldNome.getText());
		servicoVO.setValor(Float.parseFloat(textFieldValor.getText()));
		return servicoController.inserirController(servicoVO);
	}

	/*
	 * Metodo de pesquisa utiliza o like para procurar dados no banco
	 * 
	 * @param consulta Ã© o que o usuario quer buscar no banco
	 * 
	 * @param comboBoxPesquisa Ã© o qual a coluna que o mesmo irÃ¡ pesquisas(nome ou
	 * id)
	 * 
	 * 
	 */

	private void pesquisa(String consulta, String comboBoxPesquisa, Seletor selector) {

		ServicoController servicoController = new ServicoController();
		selector.setLimite(Integer.parseInt(comboBoxLimitePagina.getSelectedItem().toString()));
		selector.setPagina(paginaAtual);

		if (consulta.equals("")) {

			List<ServicoVO> servicosVO = servicoController.consultaController(seletor);
			atualizarTabela(servicosVO);

		} else {
			List<ServicoVO> servicosVO = servicoController.consultaController(consulta, comboBoxPesquisa,selector);
			atualizarTabela(servicosVO);
		}

	}

	/*
	 * 
	 * 
	 * Metado para montar as infomacao na tela
	 */
	private void atualizarTabela(List<ServicoVO> servicosVO) {

		tableDadosServico.setModel(new DefaultTableModel(new String[][] { { "Codigo", "Nome", "Valor"}, },
				new String[] { "Codigo", "Nome", "Valor" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true};

			public boolean isCellEditable(int row, int column) {

				if (row == 0) {

					return false;

				}
				return columnEditables[column];

			}
		});

		DefaultTableModel modelo = (DefaultTableModel) tableDadosServico.getModel();

		for (ServicoVO servico : servicosVO) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeÃ§alho da tabela

			String[] novaLinha = new String[] {servico.getIdservico()+"",servico.getNome(),servico.getValor()+""};
			modelo.addRow(novaLinha);

		}

	}
}
