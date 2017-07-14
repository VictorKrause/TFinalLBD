package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class App extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
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
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnReservarItem = new JButton("Reservar Equipamento");
		btnReservarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FazerReserva frame = null;
				try {
					frame = new FazerReserva();
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				frame.setVisible(true);
			}
		});
		
		
		JButton btnListasRelatorios = new JButton("Listas/Relatorios");
		btnListasRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListasRelatorios janela = new ListasRelatorios();
				janela.setVisible(true);
			}
		});
		
		JButton btnBuscarEquipOuFunc = new JButton("Buscar equipamentos/funcionarios");
		
		btnBuscarEquipOuFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BuscarEquipamentoFuncionario frame = new BuscarEquipamentoFuncionario();
				frame.setVisible(true);
			}
		});
		
		JButton btnCustoTotal = new JButton("Visualizar custo total de uma reserva");
		btnCustoTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CustoTotal frame = new CustoTotal();
				frame.setVisible(true);
				
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBuscarEquipOuFunc, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnReservarItem, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnListasRelatorios, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
						.addComponent(btnCustoTotal, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReservarItem, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnListasRelatorios, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscarEquipOuFunc, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCustoTotal, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
