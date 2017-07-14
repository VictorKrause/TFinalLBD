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

public class RelatorioReservas extends JFrame {

	private JPanel contentPane;
	private JTable tableFuncionarios;

	/**
	 * Launch the application.
	 */
	
	public RelatorioReservas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 912, 520);
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
				{null, null, null, null},
			},
			new String[] {
				"Funcionario", "Equipamento", "Data Inicial", "Data Final"
			}
		));
		tableFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(206);
		tableFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(321);
		scrollPane.setViewportView(tableFuncionarios);
		contentPane.setLayout(gl_contentPane);
		
		addRowToTable();
	}
	
	private void addRowToTable(){
		ArrayList<String> resultado = new ArrayList<>();
		try {
			resultado = ManagerBD.getInstance().getRelatorioDeReservasFuturas();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Falha ao iniciar Banco");
		}
		DefaultTableModel model = (DefaultTableModel) tableFuncionarios.getModel();
		Object rowData[] = new Object[4];
		
		for(int i = 0; i<resultado.size(); i+=4){
			rowData[0] = resultado.get(i);
			rowData[1] = resultado.get(i+1);
			rowData[2] = resultado.get(i+2);
			rowData[3] = resultado.get(i+3);
			
			model.addRow(rowData);
		}
			
			
		}	
}
