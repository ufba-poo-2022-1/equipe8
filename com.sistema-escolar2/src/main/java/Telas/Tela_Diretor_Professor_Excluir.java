package Telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Utilitarios.Utilitario;
import daos.Dao_Professor;
import entidades.Professor;

public class Tela_Diretor_Professor_Excluir  {

	private Utilitario utilitario = new Utilitario();
	
	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	}
	
	public JPanel getPanel() {
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(26, 78, 728, 472);
		panel_1.setLayout(null);
		
		
		final JTextField campoBusca = new  JTextField();
		campoBusca.setBounds(285, 121, 158, 30);
		campoBusca.setColumns(10);
		panel_1.add(campoBusca);
	
		
		JLabel lblNewLabel = new JLabel("CODIGO:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(184, 119, 91, 30);
		panel_1.add(lblNewLabel);
		
		
		
		JButton excluir = new JButton("EXCLUIR");
		 excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long matricula = null;

				try {
				
					if(utilitario.valideMatricula(campoBusca.getText())) {
						matricula = Long.parseLong(campoBusca.getText());
						Dao_Professor dao_professor = new Dao_Professor();
						Professor  professor = dao_professor.getProfessorMatricula(matricula);
						if(professor ==null) {
							alert("PROFESSOR NÃO ENCONTRADO!");
						}else {
		                Object[] options = { "Sim", "Não" }; 
		            int op =  JOptionPane.showOptionDialog(null, professor.toString()+" \n DESEJA EXCLUIR ESSE PROFESSOR? \n \n", "EXCLUIR ALUNO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		            	if(op==0) {
		            
		            		if(dao_professor.deleteProfessor(matricula)) {
		            			sucess("PROFESSOR EXCLUIDO COM SUCESSO!");
		            		}else {
		            			alert("ERRO! O PROFESSOR NÃO FOI EXCLUIDO");
		            		}
		            	}
		               
						}
					}else {
						alert("CODIGO INVALIDA!");
					}
					
					
					
					
				}catch (Exception error) {
					alert("ERRO NO SISTEMA"+error.getMessage());
					
				}
				
			}
		});
		
		excluir.setBounds(232, 197, 200, 30);
		panel_1.add(excluir);
		
		
		 
	
		
		return panel_1;
	}
	
}

