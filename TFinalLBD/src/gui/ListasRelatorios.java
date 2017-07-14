package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListasRelatorios extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ListasRelatorios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 167);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblListas = new JLabel("Listas");
		
		JLabel lblRelatorios = new JLabel("Relatorios");
		
		JButton btnListarFuncionarios = new JButton("Listar Funcionarios");
		btnListarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaAllFuncionarios frame = new ListaAllFuncionarios();
				frame.setVisible(true);
			}
		});
		
		JButton btnListarFuncionariosSem = new JButton("Listar funcionarios sem reserva");
		btnListarFuncionariosSem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaRelatoriosSemReserva frame = new ListaRelatoriosSemReserva();
				frame.setVisible(true);
			}
		});
		
		JButton btnReservasFuturas = new JButton("Reservas Futuras");
		btnReservasFuturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioReservas frame = new RelatorioReservas();
				frame.setVisible(true);
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(btnListarFuncionarios))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(80)
							.addComponent(lblListas)))
					.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblRelatorios)
							.addGap(79))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnReservasFuturas)
							.addGap(45))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnListarFuncionariosSem)
					.addContainerGap(243, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblListas)
						.addComponent(lblRelatorios))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnListarFuncionarios)
						.addComponent(btnReservasFuturas))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnListarFuncionariosSem)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
