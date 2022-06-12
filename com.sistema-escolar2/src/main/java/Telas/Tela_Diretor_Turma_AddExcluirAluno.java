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
import javax.swing.border.EmptyBorder;

import Utilitarios.Utilitario;
import daos.Dao_Aluno;
import daos.Dao_Turma;
import entidades.Aluno;
import entidades.Turma;

@SuppressWarnings("serial")
public class Tela_Diretor_Turma_AddExcluirAluno extends JFrame {
	private static String taoma = "Tahoma";
	private JPanel contentPane;
	private static Utilitario utilitario = new Utilitario();
	protected static final Throwable ErroInternoTratavel = null;
	private static Dao_Turma dao_turma = new Dao_Turma();
	private static Dao_Aluno dao_aluno = new Dao_Aluno();
	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	}
	
	public JPanel getPanel() {
		setBounds(100, 100, 776, 596);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 78, 744, 472);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextField textAluno = new JTextField();
		textAluno.setBounds(353, 110, 116, 27);
		panel.add(textAluno);
		
		JLabel lbAluno = new JLabel("CODIGO ALUNO:");
		lbAluno.setFont(new Font(taoma, Font.PLAIN, 14));
		lbAluno.setBounds(211, 110, 150, 27);
		panel.add(lbAluno);
		
		JLabel lblCodigoTurma = new JLabel("CODIGO TURMA:");
		lblCodigoTurma.setFont(new Font(taoma, Font.PLAIN, 14));
		lblCodigoTurma.setBounds(211, 165, 120, 27);
		panel.add(lblCodigoTurma);
		
		JTextField textTurma = new JTextField();
		textTurma.setBounds(353, 165, 116, 27);
		panel.add(textTurma);
		
		JButton btnExcluir = new JButton("EXCLUIR ALUNO DA TURMA");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
		try {
				String codigoTurma = null;
				String codigoAluno = null;
				
				codigoAluno = textAluno.getText();
				codigoTurma = textTurma.getText();
				
				if(!utilitario.valideMatricula(codigoAluno)) {throw new  Exception("ERRO MATRICULA DO ALUNO INVALIDA!",ErroInternoTratavel);}
				if(!utilitario.valideCodigo(codigoTurma)) {throw new  Exception("ERRO CODIGO DA TURMA INVALIDO!",ErroInternoTratavel);}
				
				Turma turma = dao_turma.getTurmaCodigo(Long.parseLong(codigoTurma));
				Aluno aluno = dao_aluno.getAlunoMatricula(Long.parseLong(codigoAluno));
				
				if(aluno == null) {throw new  Exception("ALUNO NÃO ENCONTRADO!",ErroInternoTratavel);}	
				if(turma== null) {throw new  Exception("TURMA NÃO ENCONTRADA!",ErroInternoTratavel);}
				if(!turma.contemAluno(Long.parseLong(codigoAluno))) {throw new  Exception("ALUNO NÃO ESTÁ NA TURMA!",ErroInternoTratavel);}	
				
		
					
					Object[] options = { "Sim", "Não" }; 
			        int op =  JOptionPane.showOptionDialog(null, "ALUNO: "+aluno.getNome()+"\nMATRICULA: "+
					aluno.getMatricula()+"\nTURMA: "+turma.getId()+"\nDESEJA REMOVER ESSE ALUNO DESSA TURMA? \n \n", "ALTERAR ALUNO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				        if(op==0) {
				        	if(turma.removAluno(aluno.getMatricula())) {
				            	sucess("ALUNO EXCLUIDO COM SUCESSO!");		
				            }else {
				            	alert("ERRO! O ALUNO NÃO FOI EXCLUIDO");
				            }
				        }
			}catch (Exception erro) {
				if(erro.getCause() == ErroInternoTratavel) {
					alert(""+erro.getMessage());
				}else {
					alert("ERRO NO SISTEMA!");
				}
			}
			}
		});
		btnExcluir.setBounds(120, 269, 220, 23);
		panel.add(btnExcluir);
		
		JButton btnAdicionar = new JButton("ADICIONAR ALUNO NA TURMA");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			try {
				String codigoTurma = null;
				String codigoAluno = null;
				
				codigoAluno = textAluno.getText();
				codigoTurma = textTurma.getText();
				if(!utilitario.valideCodigo(codigoTurma)) {throw new  Exception("ERRO CODIGO DA TURMA INVALIDO!",ErroInternoTratavel);}
				if(!utilitario.valideMatricula(codigoAluno)) {throw new  Exception("ERRO MATRICULA DO ALUNO INVALIDO!",ErroInternoTratavel);}
				Turma turma = dao_turma.getTurmaCodigo(Long.parseLong(codigoTurma));
				Aluno aluno = dao_aluno.getAlunoMatricula(Long.parseLong(codigoAluno));
				
				if(turma== null) {throw new  Exception("TURMA NÃO ENCONTRADA!",ErroInternoTratavel);}
				if(aluno == null) {throw new  Exception("ALUNO NÃO ENCONTRADO!",ErroInternoTratavel);}
			
				Object[] options = { "Sim", "Não" }; 
		        int op =  JOptionPane.showOptionDialog(null, "ALUNO: "+aluno.getNome()+"\nMATRICULA: "+
				aluno.getMatricula()+"\nTURMA: "+turma.getId()+"\nDESEJA ADICIONAR ESSE ALUNO NESSA TURMA? \n \n", "ALTERAR ALUNO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			        if(op==0) {
			        	if(turma.addAluno(aluno)) {
				        	sucess("ALUNO CADASTRADO COM SUCESSO!");		
			            }else {
			            	alert("ERRO! O ALUNO NÃO FOI AIDICIONADO");
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
		btnAdicionar.setBounds(358, 269, 220, 23);
		panel.add(btnAdicionar);
		return panel;
	}
}
