package Telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Utilitarios.Utilitario;
import daos.Dao_Disciplina;
import entidades.Disciplina;

@SuppressWarnings("serial")
public class Tela_Diretor_Disciplina_Excluir extends JFrame {

	private JTextField consultaTexto;
	private Utilitario utilitario = new Utilitario();
	
	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	
	}
	
	public JPanel getPanel() {
		
		final JPanel panel = new JPanel();
		panel.setLayout(null);
		
		
		
		JLabel lbltext = new JLabel("CODIGO DA DISCIPLINA:");
		lbltext.setBounds(40, 40, 170, 19);
		lbltext.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lbltext);
		
		
		consultaTexto = new JTextField();
		consultaTexto.setBounds(280, 40, 100, 25);
		panel.add(consultaTexto);
		consultaTexto.setColumns(10);
		
		
		JButton buscarDisciplina = new JButton("EXCLUIR DISCIPLINA");
		buscarDisciplina.setBounds(516, 34, 190, 25);
		buscarDisciplina.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
				try {
					String valorConsulta = consultaTexto.getText();
					Dao_Disciplina dao_disciplina = new Dao_Disciplina();
					if(utilitario.valideCodigo(valorConsulta)) {
					Disciplina disciplina = dao_disciplina.getDisciplinaCodigo(Long.parseLong(valorConsulta));
		      		if(disciplina == null) {
		      			alert("DISCIPLINA NÃO ENCONTRADA!");
		      		}else {
		      			
		      			Object[] options = { "Sim", "Não" }; 
				        int op =  JOptionPane.showOptionDialog(null, disciplina.toString()+" \n DESEJA EXCLUIR ESSA DISCIPLINA? \n \n", "EXCLUIR DISCIPLINA", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				        if(op==0) {
				        	if(dao_disciplina.deleteDisciplina(disciplina.getCodigo())){
				        		
				        	}else {
				        		alert("ERRO NO SISTEMA!: DISCIPLINA NÃO EXCLUIDA.");
				        	}
				        	
				        }
		      			repaint();
		      		}
					repaint();
					}else {
						alert("ERRO: CODIGO INVALIDO!");
					}	

				}catch (Exception e2) {
						alert("ERRO NO SISTEMA!"+e2.getMessage());
				}
			}
		});
		panel.add(buscarDisciplina);
		
		return panel;
	}	

}
