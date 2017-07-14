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

public class ListaAllFuncionarios extends JFrame {

	private JPanel contentPane;
	private JTable tableFuncionarios;

	/**
	 * Launch the application.
	 */
	
	public ListaAllFuncionarios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 409, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		tableFuncionarios = new JTable();
		tableFuncionarios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome Completo"
			}
		));
		tableFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(354);
		scrollPane.setViewportView(tableFuncionarios);
		contentPane.setLayout(gl_contentPane);
		addRowToTable();
		
	}
	
	private void addRowToTable(){
		ArrayList<String> nomeFuncionarios = new ArrayList<>();
		try {
			nomeFuncionarios = ManagerBD.getInstance().getAllFuncionarios();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, nomeFuncionarios);
		}
		DefaultTableModel model = (DefaultTableModel) tableFuncionarios.getModel();
		Object rowData[] = new Object[1];
		for(String nome : nomeFuncionarios){
			rowData[0] = nome;
			model.addRow(rowData);
		}
	}
}
