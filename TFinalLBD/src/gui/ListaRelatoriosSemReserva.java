package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bd.ManagerBD;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListaRelatoriosSemReserva extends JFrame {

	private JPanel contentPane;
	private JTable tableFuncionarios;

	/**
	 * Launch the application.
	 */
	
	public ListaRelatoriosSemReserva() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		tableFuncionarios = new JTable();
		tableFuncionarios.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Login", "Nome Completo"
			}
		));
		tableFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(92);
		tableFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(187);
		scrollPane.setViewportView(tableFuncionarios);
		contentPane.setLayout(gl_contentPane);
		
		addRowToTable();
	}
	
	private void addRowToTable(){
		ArrayList<String> nomeELoginFuncionarios = new ArrayList<>();
		try {
			nomeELoginFuncionarios = ManagerBD.getInstance().getAllFuncionariosSemReserva();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no banco");
		}
		DefaultTableModel model = (DefaultTableModel) tableFuncionarios.getModel();
		Object rowData[] = new Object[1];
		for(int i = 0; i <nomeELoginFuncionarios.size(); i+=2){
			rowData[0] = nomeELoginFuncionarios.get(i);
			rowData[1] = nomeELoginFuncionarios.get(i+1);
			
			model.addRow(rowData);
		}
	}
}
