package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import bd.ManagerBD;
import model.Equipamento;
import model.Funcionario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FazerReserva extends JFrame {

	private JPanel contentPane;

	public FazerReserva() throws ParseException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnReservar = new JButton("Reservar");

		JComboBox<Equipamento> comboBoxEquip = new JComboBox<Equipamento>();
		iniciaComboEquip(comboBoxEquip);

		JLabel lblEquipamento = new JLabel("Equipamento");

		JComboBox<Funcionario> comboBoxFunc = new JComboBox<Funcionario>();
		iniciaComboFunc(comboBoxFunc);

		JLabel lblFuncionario = new JLabel("Funcionario");

		JFormattedTextField txtFieldDataInicial = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtFieldDataInicial.setToolTipText("DD/MM/AAAA");

		JLabel lblDataInicial = new JLabel("Data Inicial");

		JFormattedTextField txtFieldDataFim = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtFieldDataFim.setToolTipText("DD/MM/AAAA");

		JLabel lblDataFinal = new JLabel("Data Final");

		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ManagerBD.getInstance().addReserva((Equipamento)comboBoxEquip.getSelectedItem(), 
							(Funcionario)comboBoxFunc.getSelectedItem(), txtFieldDataInicial.getText(), txtFieldDataFim.getText());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Falha ao iniciar o banco");
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(173)
										.addComponent(btnReservar))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addContainerGap()
										.addComponent(comboBoxEquip, 0, 414, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(186)
										.addComponent(lblEquipamento))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGap(70)
														.addComponent(lblFuncionario))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addContainerGap()
														.addComponent(comboBoxFunc, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGap(38)
														.addComponent(txtFieldDataInicial, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
														.addComponent(txtFieldDataFim, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGap(60)
														.addComponent(lblDataInicial)
														.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
														.addComponent(lblDataFinal)
														.addGap(24)))))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFuncionario)
								.addComponent(lblDataInicial)
								.addComponent(lblDataFinal))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxFunc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldDataFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldDataInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblEquipamento)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBoxEquip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnReservar)
						.addContainerGap())
				);
		contentPane.setLayout(gl_contentPane);
	}

	private void iniciaComboFunc(JComboBox<Funcionario> comboBox) {
		ArrayList<Funcionario> funcionarios;

		try {
			funcionarios = ManagerBD.getInstance().getAllFuncionarios();
			for (Funcionario func: funcionarios) {
				comboBox.addItem(func);
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro ao inicializar banco, verifique");
			e1.printStackTrace();
		}

	}

	private void iniciaComboEquip(JComboBox<Equipamento> comboBox) {
		ArrayList<Equipamento> equips;

		try {
			equips = ManagerBD.getInstance().getAllEquips();
			for (Equipamento equip: equips) {
				comboBox.addItem(equip);
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro ao inicializar banco, verifique");
			e1.printStackTrace();
		}

	}

}
