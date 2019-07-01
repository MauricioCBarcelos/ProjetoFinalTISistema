package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.ProdutoController;
import model.vo.ProdutoVO;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CadastroProdudoServicoChamado extends JFrame {
	private JTextField textFieldProblemaCostado;
	private JTextField textFieldObservacao;
	private JTextField textFieldPesquisaProduto;
	private ProdutoVO[] produtosVO;


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
		setBounds(100, 100, 613, 392);
		getContentPane().setLayout(new MigLayout("", "[316px][33px][109px,grow][25px][79px]", "[14px][56px][14px][52px][14px][22px][14px][27px]"));
		
		JLabel lblNewLabelProblemaCostado = new JLabel("Problema costatado:");
		getContentPane().add(lblNewLabelProblemaCostado, "cell 0 0,alignx left,aligny top");
		
		textFieldProblemaCostado = new JTextField();
		getContentPane().add(textFieldProblemaCostado, "cell 0 1 3 1,grow");
		textFieldProblemaCostado.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Aberto", "Fechado"}));
		getContentPane().add(comboBox, "cell 4 1,growx,aligny top");
		
		JLabel lblObservao = new JLabel("Observação:");
		getContentPane().add(lblObservao, "cell 0 2,alignx left,aligny top");
		
		textFieldObservacao = new JTextField();
		getContentPane().add(textFieldObservacao, "cell 0 3 3 1,grow");
		textFieldObservacao.setColumns(10);
		
		textFieldPesquisaProduto = new JTextField();
		getContentPane().add(textFieldPesquisaProduto, "cell 0 5,grow");
		textFieldPesquisaProduto.setColumns(10);
		
		JLabel lblPesquisaProduto = new JLabel("Pesquisa produto:");
		getContentPane().add(lblPesquisaProduto, "cell 0 4,alignx left,aligny top");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Categoria", "Nome", "ID"}));
		getContentPane().add(comboBox_1, "cell 3 5,grow");
		
		JComboBox comboBoxProduto = new JComboBox();
		ProdutoController produtoController = new ProdutoController();
		for (int i = 0; i < produtoController.consultaProdutoController().size(); i++) {
			produtosVO[i].setMarca(produtoController.consultaProdutoController().get(i).getMarca());
			
		}
		
		comboBoxProduto.setModel(new DefaultComboBoxModel());
		getContentPane().add(comboBoxProduto, "cell 0 7,gr");
		
		JLabel lblProduto = new JLabel("Produto:");
		getContentPane().add(lblProduto, "cell 0 6,alignx left,aligny top");
		
		
	}
}
