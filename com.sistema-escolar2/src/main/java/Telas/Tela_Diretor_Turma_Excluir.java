package Telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Utilitarios.Utilitario;
import daos.Dao_Turma;
import entidades.Turma;

public class Tela_Diretor_Turma_Excluir{

	private JPanel contentPane;
	private JTextField textField;

	protected static final Throwable ErroInternoTratavel = null;

	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	}
	private Utilitario utilitario = new Utilitario();
	
	public JPanel getPanel() {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 82, 730, 468);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					
					String codigoTurma = textField.getText();
					Dao_Turma dao_turma = new Dao_Turma();
					
					if(!utilitario.valideCodigo(codigoTurma))  {throw new Exception("ERRO: CODIGO INVALIDO!",ErroInternoTratavel);}
					Turma turma = dao_turma.getTurmaCodigo(Long.parseLong(codigoTurma));
					if(turma == null){throw new Exception("ERRO: TURMA NÃO ENCONTRADA!",ErroInternoTratavel);}
					Object[] options = { "Sim", "Não" }; 
				    int op =  JOptionPane.showOptionDialog(null, turma.toString()+" \n DESEJA EXCLUIR ESSA TURMA? \n \n", "EXCLUIR TURMA", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				    
				    if(op == 0) {
				    	if(dao_turma.deleteTurma(turma.getId())) {
				    		sucess("TURMA EXCLUIDA COM SUCESSO!");
				    	}else {
				    		throw new Exception("ERRO NO SISTEMA, TURMA NÃO EXCLUIDA!",ErroInternoTratavel);
				    	}
				    }
					
					
				} catch (Exception erro) {
						
					if(erro.getCause() == ErroInternoTratavel) {
						alert(erro.getMessage());
					}else {
						alert("ERRO NO SISTEMA!");
					}
				}
				
				
				
			}
		});
		textField.setBounds(324, 115, 118, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("EXCLUIR TURMA");
		btnNewButton.setBounds(252, 178, 140, 30);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("CODIGO DA TURMA:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(183, 111, 149, 30);
		panel.add(lblNewLabel);
		return panel;
	}
}
