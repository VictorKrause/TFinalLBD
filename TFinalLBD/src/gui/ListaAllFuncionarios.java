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
import model.Funcionario;

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
		setBounds(100, 100, 897, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tableFuncionarios = new JTable();
		tableFuncionarios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Login", "Senha", "Data nascimento", "Data admissao", "Sexo", "Nome Completo", "Endereco", "Salario Mensal (R$)"
			}
		));
		tableFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(95);
		tableFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(103);
		tableFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(142);
		tableFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableFuncionarios.getColumnModel().getColumn(5).setPreferredWidth(181);
		tableFuncionarios.getColumnModel().getColumn(7).setPreferredWidth(110);
		scrollPane.setViewportView(tableFuncionarios);
		contentPane.setLayout(gl_contentPane);
		addRowToTable();
		
	}
	
	private void addRowToTable(){
		ArrayList<Funcionario> funcionarios = new ArrayList<>();
		try {
			funcionarios = ManagerBD.getInstance().getAllFuncionarios();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Falha no Banco");
		}
		DefaultTableModel model = (DefaultTableModel) tableFuncionarios.getModel();
		Object rowData[] = new Object[8];
		for(Funcionario fun : funcionarios){
			rowData[0] = fun.getLogin();
			rowData[1] = fun.getSenha();
			rowData[2] = fun.getDataNascimento();
			rowData[3] = fun.getDataAdmissao();
			rowData[4] = fun.getSexo();
			rowData[5] = fun.getNomeCompleto();
			rowData[6] = fun.getEndereco();
			rowData[7] = fun.getSalarioMensal();
			
			model.addRow(rowData);
		}
	}
}
