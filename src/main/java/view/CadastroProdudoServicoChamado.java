package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CadastroProdudoServicoChamado extends JFrame {
	private JTextField textFieldProblemaCostado;
	private JTextField textFieldObservacao;
	private JTextField textFieldPesquisaProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProdudoServicoChamado frame = new CadastroProdudoServicoChamado();
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
	public CadastroProdudoServicoChamado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 655);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabelProblemaCostado = new JLabel("Problema costatado:");
		lblNewLabelProblemaCostado.setBounds(10, 11, 106, 14);
		getContentPane().add(lblNewLabelProblemaCostado);
		
		textFieldProblemaCostado = new JTextField();
		textFieldProblemaCostado.setBounds(10, 33, 458, 56);
		getContentPane().add(textFieldProblemaCostado);
		textFieldProblemaCostado.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Aberto", "Fechado"}));
		comboBox.setBounds(493, 33, 79, 22);
		getContentPane().add(comboBox);
		
		JLabel lblObservao = new JLabel("Observação:");
		lblObservao.setBounds(10, 119, 62, 14);
		getContentPane().add(lblObservao);
		
		textFieldObservacao = new JTextField();
		textFieldObservacao.setBounds(10, 138, 458, 22);
		getContentPane().add(textFieldObservacao);
		textFieldObservacao.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Categoria", "Nome", "ID"}));
		comboBox_1.setBounds(359, 226, 148, 22);
		getContentPane().add(comboBox_1);
		
		textFieldPesquisaProduto = new JTextField();
		textFieldPesquisaProduto.setBounds(10, 226, 316, 22);
		getContentPane().add(textFieldPesquisaProduto);
		textFieldPesquisaProduto.setColumns(10);
		
		JLabel lblPesquisaProduto = new JLabel("Pesquisa produto:");
		lblPesquisaProduto.setBounds(10, 201, 88, 14);
		getContentPane().add(lblPesquisaProduto);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 298, 231, 27);
		getContentPane().add(comboBox_2);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(10, 273, 46, 14);
		getContentPane().add(lblProduto);
	}
}
