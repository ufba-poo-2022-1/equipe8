package Telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import Utilitarios.Utilitario;
import daos.Dao_Aluno;
import entidades.Aluno;

public class Tela_Diretor_Aluno_Alterar {

	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private Utilitario utilitario = new Utilitario();
	private List<JTextField> lista = new ArrayList<JTextField>();
	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	}

	public JPanel getPanel() {
		
		JPanel panel_1 = new JPanel();
		
		JTextField text = new JFormattedTextField();
		text.setBounds(240, 40, 203, 24);
		panel_1.add(text);
		
		JLabel lbltext = new JLabel("MATRICULA DO ALUNO:");
		lbltext.setBounds(40, 40, 170, 19);
		lbltext.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lbltext);
		
		JButton btntext= new JButton("BUSCAR ALUNO");
		btntext.setBounds(463, 40, 164, 23);
		panel_1.add(btntext);
		
		textFieldNome = new JFormattedTextField();
	    textFieldNome.setBounds(243, 110, 203, 24);
	    lista.add(textFieldNome);
		panel_1.add(textFieldNome);
		
		JLabel lblNewLabel = new JLabel("NOME COMPLETO:");
		lblNewLabel.setBounds(89, 110, 130, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		panel_1.add(lblNewLabel);
		
		JLabel lblDataDeNascimento = new JLabel("DATA DE NASCIMENTO:");
		lblDataDeNascimento.setBounds(49, 140, 171, 19);
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblDataDeNascimento);
		
		
		MaskFormatter maskCpf = null;
		try {
			maskCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		panel_1.setLayout(null);
		MaskFormatter datadia = null;
		try {
			datadia = new MaskFormatter("##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MaskFormatter dataMes = null;
		try {
			dataMes = new MaskFormatter("##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MaskFormatter dataAno = null;
		try {
			dataAno = new MaskFormatter("####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		final JTextField textFieldDataDia2 = new JFormattedTextField(datadia);
		textFieldDataDia2.setBounds(263, 145, 30, 25);
		textFieldDataDia2.setToolTipText("DIA");
		lista.add(textFieldDataDia2);
		panel_1.add(textFieldDataDia2);
		
		 
		final JTextField textFieldDataMes2 = new JFormattedTextField(dataMes);
		textFieldDataMes2.setBounds(312, 145, 30, 25);
		textFieldDataMes2.setToolTipText("MES");
		panel_1.add(textFieldDataMes2);
		lista.add(textFieldDataMes2);
		

		final JTextField textFieldDataAno2 = new JFormattedTextField(dataAno);
		textFieldDataAno2.setBounds(365, 145, 61, 25);
		textFieldDataAno2.setToolTipText("ANO");
		panel_1.add(textFieldDataAno2);
		lista.add(textFieldDataAno2);
		
		final JTextField textFieldCpf = new JFormattedTextField(maskCpf);
		textFieldCpf.setBounds(263, 181, 163, 25);
		textFieldCpf.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCpf.setColumns(10);
		panel_1.add(textFieldCpf);
		lista.add(textFieldCpf);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(188, 187, 32, 17);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblCpf);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(249, 225, 197, 25);
		textFieldEmail.setColumns(10);
		panel_1.add(textFieldEmail);
		lista.add(textFieldEmail);
		
		JLabel lblEmail = new JLabel("EMAIL:");
		lblEmail.setBounds(168, 227, 51, 17);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblEmail);
		
		JButton btnNewButton = new JButton("ALTERAR ALUNO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		      
				
				List<String> msgErro = new ArrayList<String>();
		    	String nameUser = textFieldNome.getText();
		    	String cpfUser = textFieldCpf.getText();
		    	cpfUser = cpfUser.replace(".", "");
		    	cpfUser = cpfUser.replace(".", "");
		    	cpfUser = cpfUser.replace("-", "");
		    	String emailUser = textFieldEmail.getText();	
				String data = utilitario.gerarData(textFieldDataDia2.getText(),textFieldDataMes2.getText(), textFieldDataAno2.getText());
				
				if(!utilitario.ValideDate(data) || data == null) { msgErro.add("ERRO: DATA INVALIDA");}
		    	if(!utilitario.validarCpf(cpfUser)) {msgErro.add("ERRO: CPF INVALIDO");}
		    	if(!utilitario.valideNome(nameUser)) {msgErro.add("ERRO: NOME INVALIDO");}
		    	if(!utilitario.validarEmail(emailUser)) {msgErro.add("ERRO: EMAIL INVALIDO");}
		    	if(data.contains(" ")) { msgErro.add("ERRO: DATA INVALIDA PRENCHA TODOS OS CAMPOS NO FOMATO EX: 01/02/2020");}
		    	
		    	if(msgErro.isEmpty()) {
		    		Dao_Aluno da = new Dao_Aluno();
			    	Aluno aluno =  da.getAlunoMatricula(Long.parseLong(text.getText()));
			    	if(aluno == null) {
			    		alert("ALUNO NÃO ENCONTRADO!");
			    	}else {
			    		
			    	aluno.setNome(nameUser);
			    	aluno.setCpf(cpfUser);
			    	aluno.setEmail(emailUser);
			    	aluno.setNascimento(data);
			    	Object[] options = { "Sim", "Não" }; 
			        int op =  JOptionPane.showOptionDialog(null, aluno.toString()+" \n DESEJA ALTERAR ESSE ALUNO? \n \n", "ALTERAR ALUNO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				        if(op==0) {
				        	if(da.updateAluno(aluno)) {
		
				            	sucess("ALUNO ALTERADO COM SUCESSO!");
				            			
				            }else {
				            	alert("ERRO! O ALUNO NÃO FOI ALTERADO");
				            }
				        			 
				        }
			    	}
			        textFieldNome.setText("");
					 textFieldCpf.setText("");
					 textFieldEmail.setText("");
					 textFieldDataAno2.setText("");
					 textFieldDataMes2.setText("");
					 textFieldDataDia2.setText("");
					 
						
			       
		    	}else {
		    		
		    	   String erro ="";
		    			
		    		for (int i=0; i< msgErro.size(); i++) {
		    			erro = erro+"\n"+msgErro.get(i);
		    		}
		    		alert(erro);
		    		msgErro.clear();
		    	}
		    	 textFieldNome.setText("");
				 textFieldCpf.setText("");
				 textFieldEmail.setText("");
				 textFieldDataAno2.setText("");
				 textFieldDataMes2.setText("");
				 textFieldDataDia2.setText("");
				 text.setEditable(true);
				 lista.forEach(jfiled ->{
						jfiled.setEditable(false);
					});
					btnNewButton.setEnabled(false);
				text.setText("");
			}
				
			});
		lista.forEach(jfiled ->{
			jfiled.setEditable(false);
		});
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(263, 293, 164, 23);
		panel_1.add(btnNewButton);
	
		btntext.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				Long matricula = null;

				try {
				
					if(utilitario.valideMatricula(text.getText())) {
						matricula = Long.parseLong(text.getText());
						Dao_Aluno dao_aluno = new Dao_Aluno();
						Aluno aluno = dao_aluno.getAlunoMatricula(matricula);
						if(aluno ==null) {
							alert("ALUNO NÃO ENCONTRADO!");
							 textFieldNome.setText("");
							 textFieldCpf.setText("");
							 textFieldEmail.setText("");
							 textFieldDataAno2.setText("");
							 textFieldDataMes2.setText("");
							 textFieldDataDia2.setText("");
							 text.setEditable(true);
							 lista.forEach(jfiled ->{
									jfiled.setEditable(false);
								});
								btnNewButton.setEnabled(false);
							text.setText("");
						}else {
							
							lista.forEach(jfiled ->{
								jfiled.setEditable(true);
							});
							btnNewButton.setEnabled(true);
							textFieldNome.setText(aluno.getNome());
							textFieldCpf.setText(aluno.getCpf());
							textFieldDataAno2.setText(aluno.getNascimento().substring(6));
							textFieldDataMes2.setText(aluno.getNascimento().substring(3,5));
							textFieldDataDia2.setText(aluno.getNascimento().substring(0,2));
							textFieldEmail.setText(aluno.getEmail());
							text.setEditable(false);
						}
					}else {
						alert("MATRICULA INVALIDA!");
					}
					
					
					
					
				}catch (Exception error) {
					alert("ERRO NO SISTEMA"+error.getMessage());
					
				}
				
			}
		});
		
		return panel_1;

	}
}
