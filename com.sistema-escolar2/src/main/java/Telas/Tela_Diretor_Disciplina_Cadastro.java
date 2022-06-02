package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import daos.Dao_Disciplina;
import entidades.Disciplina;

@SuppressWarnings("serial")
public class Tela_Diretor_Disciplina_Cadastro extends JFrame {

		
		public void alert(String mensagem) {
			JOptionPane.showMessageDialog(null,mensagem);
		
		}
		public void sucess(String mensagem) {
			JOptionPane.showInternalMessageDialog(null, mensagem);
		}

		public JPanel getPanel() {
			
			JPanel panel = new JPanel();
			
			JTextField textFieldNome = new JTextField();
			textFieldNome.setBounds(240, 80, 203, 24);
			panel.add(textFieldNome);
			
			JLabel lblNewLabel = new JLabel("NOME DA DISCIPLINA:");
			lblNewLabel.setBounds(50, 81, 220, 19);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel.add(lblNewLabel);
			panel.setLayout(null);
		
			
			JLabel lblDescricao = new JLabel("DESCRIÇÃO DA DSICIPLINA:");
			lblDescricao.setBounds(50, 157, 251, 17);
			lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel.add(lblDescricao);
			
			MaskFormatter mask = null;
			try {
				mask = new MaskFormatter("********************************************************************************");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			JTextField textArea = new JFormattedTextField(mask);
			textArea.setBounds(50, 180, 600, 50);
			textArea.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			panel.add(textArea);
			
			JButton btnNewButton = new JButton("CADASTRAR DISCIPLINA");
			btnNewButton.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent e) {  
				
			    List<String> msgErro = new ArrayList<String>();	
			    String descricao;
			     String nome;
			    try {
			    	descricao = textArea.getText();
			    	nome = textFieldNome.getText();
					if(nome.isEmpty() || nome.length()<2) {msgErro.add("ERRO: NOME INVALIDO!");}
				} catch (Exception e2) {
					msgErro.add("ERRO: DIGITE UMA DESCRIÇÃO COM ATÉ 40 CARACTERES");	
				}
				
				
				
				
				if(msgErro.isEmpty()) {
					
			    	try {
						Dao_Disciplina dao_disciplina = new Dao_Disciplina();
						Disciplina disciplina = new Disciplina(textFieldNome.getText(), textArea.getText());
						Long matricula = dao_disciplina.saveDisciplina(disciplina);
						sucess("DISCIPLINA CADASTRADA COM SUCESSO! \nCODIGO: "+matricula);
					} catch (Exception e2) {
						alert("ERRO NO SISTEMA: "+e2.getMessage());
					}
			    		
			    	}else {
			    	   String erro ="";	
			    		for (int i=0; i< msgErro.size(); i++) {
			    			erro = erro+"\n"+msgErro.get(i);
			    		}
			    		alert(erro);
			    		msgErro.clear();
			    	}

				}	
				});
		
			btnNewButton.setBounds(220, 400, 240, 23);
			panel.add(btnNewButton);
			
			return panel;

		}

}
