package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.ManagerBD;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CustoTotal extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldIdEquip;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public CustoTotal() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 296, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblIdDoEquipamento = new JLabel("Id do Equipamento");
		
		txtFieldIdEquip = new JTextField();
		txtFieldIdEquip.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[] resultados = new double[2];
				try {
					resultados = ManagerBD.getInstance().getValorTotalDeReservaByEquipamentoId(Integer.parseInt(txtFieldIdEquip.getText()));
				} catch (NumberFormatException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "Numero de ID inválido");
				}
				if(resultados[0] == -1){
					JOptionPane.showMessageDialog(null, "Equipamento não localizado");
				}
				else{
					String textoResultado = "Numero de dias reservados: "+resultados[1]+ " dias | Custo total: R$ "+resultados[0];
					JOptionPane.showMessageDialog(null, textoResultado);
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(lblIdDoEquipamento)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFieldIdEquip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(226, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(171, Short.MAX_VALUE)
					.addComponent(btnBuscar)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdDoEquipamento)
						.addComponent(txtFieldIdEquip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnBuscar)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
