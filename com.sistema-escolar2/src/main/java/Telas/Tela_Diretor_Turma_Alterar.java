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
import javax.swing.SwingConstants;

import Utilitarios.Utilitario;
import daos.Dao_Disciplina;
import daos.Dao_Professor;
import daos.Dao_Turma;
import entidades.Disciplina;
import entidades.Professor;
import entidades.Turma;


@SuppressWarnings("serial")
public class Tela_Diretor_Turma_Alterar extends JFrame {

	
	protected static final Throwable ErroInternoTratavel = null;

	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	}
	private Utilitario utilitario = new Utilitario();
	
	public JPanel getPanel() {
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 87, 744, 463);
		panel.setLayout(null);
		
		
		
		JTextField textFieldTurma =  new JTextField();
		textFieldTurma.setBounds(355, 63, 121, 25);
		panel.add(textFieldTurma);
		textFieldTurma.setColumns(10);
		
		
		JLabel lblNewLabel2 = new JLabel(" CODIGO DA TURMA");
		lblNewLabel2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel2.setBounds(185, 66, 164, 25);
		panel.add(lblNewLabel2);
		
		
		JTextField textField =  new JTextField();
		textField.setBounds(355, 113, 121, 25);
		panel.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel(" CODIGO DA NOVA DISCIPLINA:");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(115, 107, 230, 25);
		panel.add(lblNewLabel);
		
		JLabel lblCodigoDoProfessor = new JLabel("CODIGO DO NOVO PROFESSOR:");
		lblCodigoDoProfessor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCodigoDoProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoDoProfessor.setBounds(110, 170, 220, 25);
		panel.add(lblCodigoDoProfessor);
		
		JTextField textField_1 =  new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(355, 176, 121, 25);
		panel.add(textField_1);
		
		JButton btnNewButton = new JButton("ALTERAR TURMA");
		btnNewButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				try {
					
					String codigoDisciplina = textField.getText();
					String codigoProfessor = textField_1.getText();
					String codigoTurma = textFieldTurma.getText();
					
					if(!utilitario.valideCodigo(codigoProfessor)) {throw new Exception("\"ERRO: CODIGO DO PROFESSOR INVALIDO!\"",ErroInternoTratavel);}
					if(!utilitario.valideCodigo(codigoDisciplina)) {throw new Exception("\"ERRO: CODIGO DA DISCIPLINA INVALIDO!",ErroInternoTratavel);}
					if(!utilitario.valideCodigo(codigoTurma)) {throw new Exception("\"ERRO: CODIGO DA TURMA INVALIDO!",ErroInternoTratavel);}
					
					Dao_Disciplina dao_disciplina = new Dao_Disciplina();
					Dao_Professor dao_professor = new Dao_Professor();
					Dao_Turma  dao_turma = new Dao_Turma();
				
					Professor professor = dao_professor.getProfessorMatricula(Long.parseLong(codigoProfessor));
					Disciplina disciplina = dao_disciplina.getDisciplinaCodigo(Long.parseLong(codigoDisciplina));	
					
					if(professor==null) {throw new Exception("ERRO: PROFESSOR NÃO ENCONTRADO!",ErroInternoTratavel);}
					if(disciplina==null){throw new Exception("ERRO: DISCIPLINA NÃO ENCONTRADA!",ErroInternoTratavel);}
					Turma turma = dao_turma.getTurmaCodigo(Long.parseLong(codigoTurma));
					if(turma == null){throw new Exception("ERRO: TURMA NÃO ENCONTRADA!",ErroInternoTratavel);}
					turma.setDisciplina(disciplina);
					turma.setProfessor(professor);
						
					Object[] options = { "Sim", "Não" }; 
				    int op =  JOptionPane.showOptionDialog(null, turma.toString()+" \n DESEJA ATUALIZAR ESSA TURMA? \n \n", "ATUALIZAR TURMA", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				    if(op==0) {
				       
				        if(dao_turma.updateTurma(turma)) {
				            	sucess("TURMA ATUALIZADA COM SUCESSO!");
				          }
				    }
				  
				        
				}catch(Exception erro ) {
					
					if(erro.getCause() == ErroInternoTratavel) {
						alert(erro.getMessage());
					}else {
						alert("ERRO NO SERVIDOR!");
					}
				} 
				
				
			}
		});
		btnNewButton.setBounds(279, 258, 200, 31);
		panel.add(btnNewButton);
		return panel;
		
	}
}
