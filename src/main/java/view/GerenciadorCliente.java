package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControladoraCadastroCliente;
import model.vo.ClienteVO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GerenciadorCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txttnome;
	private JTextField txtcpf;
	private JTextField txtTelefone;

	private JTextField textpesquisar;
	private JComboBox comboBox;

	private JTable table;
	private ArrayList<ClienteVO> listClientes;
	protected ClienteVO cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciadorCliente frame = new GerenciadorCliente();
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
	public GerenciadorCliente() {
		setTitle("Gerenciador De Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 435, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNome = new JLabel("Nome:");

		txttnome = new JTextField();
		txttnome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");

		txtcpf = new JTextField();
		txtcpf.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone 1");

		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");

		textpesquisar = new JTextField();
		textpesquisar.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "codigo", "cpf", "nome", "telefone" }));

		table = new JTable();
		table.setModel(new DefaultTableModel(new String[][] { { "Nome", "Telefone", "CPF" }, },
				new String[] { "Nome", "Telefone", "CPF" }) {

		});

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		// Listener (ouvinte) de ação de mouse sobre o botão "Salvar"
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				ControladoraCadastroCliente controller = new ControladoraCadastroCliente();
				String mensagemRetorno = controller.salvar(txttnome.getText(), txtcpf.getText(), txtTelefone.getText());

				JOptionPane.showMessageDialog(null, mensagemRetorno);

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup()
				.addGroup(gl_contentPane.createSequentialGroup().addGap(5).addGroup(gl_contentPane.createParallelGroup()
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(153)
								.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(txttnome, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addComponent(txtcpf, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addGap(21)
								.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPesquisar, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(textpesquisar, GroupLayout.PREFERRED_SIZE, 217,
										GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup()
				.addGroup(gl_contentPane.createSequentialGroup().addGap(6)
						.addGroup(gl_contentPane.createParallelGroup().addComponent(lblNome).addComponent(lblCpf))
						.addGap(5)
						.addGroup(gl_contentPane.createParallelGroup()
								.addComponent(txttnome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtcpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(11).addComponent(lblTelefone).addGap(10)
						.addGroup(gl_contentPane.createParallelGroup()
								.addGroup(gl_contentPane.createSequentialGroup().addGap(1).addComponent(txtTelefone,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSalvar))
						.addGap(10).addComponent(lblPesquisar).addGap(11)
						.addGroup(gl_contentPane.createParallelGroup()
								.addComponent(textpesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(22).addComponent(table, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)));
		contentPane.setLayout(gl_contentPane);
	}
}
