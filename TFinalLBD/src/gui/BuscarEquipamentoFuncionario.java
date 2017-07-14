package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.ManagerBD;
import jdk.nashorn.internal.scripts.JO;
import model.Equipamento;
import model.Funcionario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarEquipamentoFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public BuscarEquipamentoFuncionario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JComboBox comboBox = new JComboBox();

		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Funcionario", "Equipamento"}));
		
		JLabel lblCampoDeBusca = new JLabel("Nome");
		
		comboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String comboBoxString = comboBox.getSelectedItem().toString();
				if(comboBoxString.equals("Funcionario")){
					lblCampoDeBusca.setText("Nome");
				}
				else
					lblCampoDeBusca.setText("Descricao");
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = comboBox.getSelectedItem().toString();
				
				if(selectedItem.equals("Funcionario")){
					try {
						Funcionario fun = (Funcionario) ManagerBD.getInstance().getFuncionarioOuEquipamento(textField.getText(), 1);
						if(fun!=null)
							JOptionPane.showMessageDialog(null, fun.toString());
						else
							JOptionPane.showMessageDialog(null, "Funcionario não encontrado");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Falha no banco");
					}	
				}
				else{
					try {
						Equipamento equip = (Equipamento) ManagerBD.getInstance().getFuncionarioOuEquipamento(textField.getText(), 2);
						if(equip!=null)
							JOptionPane.showMessageDialog(null, equip.toString());
						else
							JOptionPane.showMessageDialog(null, "Equipamento não encontrado");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Falha no banco");
					}
				}
				
			}
		});
			
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(130)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnBuscar)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCampoDeBusca)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)))
							.addGap(30)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCampoDeBusca)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(btnBuscar))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
