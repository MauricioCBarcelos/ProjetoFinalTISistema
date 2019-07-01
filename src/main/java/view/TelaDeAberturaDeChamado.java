package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ChamadoController;
import controller.ControladoraCadastroCliente;
import controller.NovoChamadoController;
import controller.ProdutoController;
import controller.ServicoController;
import controller.TecnicoController;
import model.vo.ChamadoVO;
import model.vo.ClienteVO;
import model.vo.ProdutoVO;
import model.vo.ServicoVO;
import model.vo.TecnicoVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaDeAberturaDeChamado extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldOberservacao;
	private JTextField textFieldproblemaRelatado;
	private JLabel lblDescriaoDoProblema;
	private JComboBox comboBoxProduto;
	private Object[] listaProduto;
	private Object[] listaTecnico;
	private Object[] listaServico;
	private Object[] listaCliente;
	private JComboBox comboBoxTecnico;
	private JComboBox comboBoxServico;
	private JButton btnCriarChamado;
	private JComboBox comboBoxCliente;
	private JLabel lblListaServico;
	private JLabel lblProduto;
	private JLabel lblListaDeTecnicos;
	private JLabel lblListaDeClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeAberturaDeChamado frame = new TelaDeAberturaDeChamado();
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
	public TelaDeAberturaDeChamado() {
		atualiza();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 586, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[182.00px][113.00,grow][385.00,grow]", "[14px][20px][14px][31.00px][20px][][][20px][20px][][]"));
		
		JLabel lblTituloDoChamado = new JLabel("Titulo do chamado:");
		contentPane.add(lblTituloDoChamado, "cell 0 0,alignx left,aligny top");
		
		textFieldOberservacao = new JTextField();
		contentPane.add(textFieldOberservacao, "cell 0 1,alignx left,aligny top");
		textFieldOberservacao.setColumns(10);
		
		btnCriarChamado = new JButton("Criar Chamado");
		btnCriarChamado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, inserir());
				
			
			}
		});
		contentPane.add(btnCriarChamado, "cell 2 1");
		
		textFieldproblemaRelatado = new JTextField();
		textFieldproblemaRelatado.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldproblemaRelatado.setColumns(10);
		contentPane.add(textFieldproblemaRelatado, "cell 0 3 3 1,grow");
		
		lblDescriaoDoProblema = new JLabel("Descriçao do problema:");
		contentPane.add(lblDescriaoDoProblema, "cell 0 2,alignx left,aligny top");
		
		lblListaServico = new JLabel("Lista de serviços:");
		contentPane.add(lblListaServico, "cell 0 5");
		
		comboBoxServico = new JComboBox();
		comboBoxServico.setModel(new DefaultComboBoxModel(listaServico));
		contentPane.add(comboBoxServico, "cell 0 6,alignx left,aligny top");
		
		lblProduto = new JLabel("Lista de Produtos:");
		contentPane.add(lblProduto, "cell 0 7,alignx left");
		
		lblListaDeClientes = new JLabel("Lista de clientes:");
		contentPane.add(lblListaDeClientes, "cell 1 7");
		
		comboBoxProduto = new JComboBox();
		
		comboBoxProduto.setModel(new DefaultComboBoxModel(listaProduto));
		contentPane.add(comboBoxProduto, "cell 0 8,alignx left,aligny top");
		
		comboBoxCliente = new JComboBox();
		comboBoxCliente.setModel(new DefaultComboBoxModel(listaCliente));
		contentPane.add(comboBoxCliente, "cell 1 8,growx");
		
		lblListaDeTecnicos = new JLabel("Lista de tecnicos:");
		contentPane.add(lblListaDeTecnicos, "cell 0 9");
		
		comboBoxTecnico = new JComboBox();
		comboBoxTecnico.setModel(new DefaultComboBoxModel(listaTecnico));
		contentPane.add(comboBoxTecnico, "cell 0 10,alignx left,aligny top");
	}

	protected String inserir() {
		ProdutoVO produtoVO = new ProdutoVO();
		TecnicoVO tecnicoVO = new TecnicoVO();
		ServicoVO servicoVO = new ServicoVO();
		ClienteVO clienteVO = new ClienteVO();
		ChamadoVO chamadoVO = new ChamadoVO();
		
		produtoVO = (ProdutoVO) comboBoxProduto.getSelectedItem();
		tecnicoVO = (TecnicoVO) comboBoxTecnico.getSelectedItem();
		servicoVO = (ServicoVO) comboBoxServico.getSelectedItem();
		clienteVO = (ClienteVO) comboBoxCliente.getSelectedItem();
		
		chamadoVO.setIdtecnico(tecnicoVO);
		chamadoVO.setProblema_relatado(textFieldproblemaRelatado.getText());
		chamadoVO.setIdservico(servicoVO);
		chamadoVO.setObservacao(textFieldOberservacao.getText());
		chamadoVO.setIdcliente(clienteVO);
		
		NovoChamadoController novoChamadoController = new NovoChamadoController();
		
		ChamadoController chamadoController = new ChamadoController();
		TelaInicial telaInicial = new TelaInicial();
		telaInicial.atualizarTabelaChamados(chamadoController.ConsultaChamados());
		return novoChamadoController.inserirController(chamadoVO,produtoVO);
	
	}

	private void atualiza() {
		// TODO Auto-generated method stub
		ProdutoController produtoController = new ProdutoController();
		ServicoController servicoController = new ServicoController();
		TecnicoController tecnicoController = new TecnicoController(); 
		ControladoraCadastroCliente controladoraCadastroCliente = new ControladoraCadastroCliente();
		List<ProdutoVO> produtoVO = produtoController.consultaProdutoController();
		List<TecnicoVO> tecnicoVO = tecnicoController.consultaProdutoController();
		List<ServicoVO> servicoVO = servicoController.consultaProdutoController();
		List<ClienteVO> clienteVO = controladoraCadastroCliente.consultaProdutoController();
		
		listaProduto = new Object[produtoController.countLinhasTotalController()]; 
		listaTecnico= new Object[tecnicoController.countLinhasTotalController()];
		listaServico = new Object[servicoController.countLinhasTotalController()];
		listaCliente = new Object[controladoraCadastroCliente.countLinhasTotalController()];
		int i = 0;
		for (ProdutoVO produtoVO2 : produtoVO) {
			
			listaProduto[i] = produtoVO2;
			i++;
          
		}
		
		i = 0;
		for (TecnicoVO tecnicoVO2 : tecnicoVO) {
			
			listaTecnico[i] = tecnicoVO2;
			i++;
          
		}
		
		i = 0;
		for (ServicoVO servicoVO2 : servicoVO) {
			
			listaServico[i] = servicoVO2;
			i++;
          
		}
		i = 0;
		for (ClienteVO clienteVO2 : clienteVO) {
			
			listaCliente[i] = clienteVO2;
			i++;
          
		}
		
		
	}
}
