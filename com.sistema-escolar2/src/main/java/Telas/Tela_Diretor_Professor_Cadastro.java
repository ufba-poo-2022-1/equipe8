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
import daos.Dao_Professor;
import entidades.Professor;

public class Tela_Diretor_Professor_Cadastro {

	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private Utilitario utilitario = new Utilitario();
	
	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	}

	public JPanel getPanel() {
		
		JPanel panel_1 = new JPanel();
		
		textFieldNome = new JFormattedTextField();
		textFieldNome.setBounds(243, 80, 203, 24);
		panel_1.add(textFieldNome);
		
		JLabel lblNewLabel = new JLabel("NOME COMPLETO:");
		lblNewLabel.setBounds(89, 81, 130, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel);
		
		JLabel lblDataDeNascimento = new JLabel("DATA DE NASCIMENTO:");
		lblDataDeNascimento.setBounds(49, 126, 171, 19);
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblDataDeNascimento);
		
		
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
		MaskFormatter maskCpf = null;
		try {
			maskCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		panel_1.setLayout(null);
		
		
		final JTextField textFieldDataDia = new JFormattedTextField(datadia);
		textFieldDataDia.setBounds(263, 125, 30, 25);
		textFieldDataDia.setToolTipText("DIA");
		panel_1.add(textFieldDataDia);
		
		final JTextField textFieldDataMes = new JFormattedTextField(dataMes);
		textFieldDataMes.setBounds(312, 125, 30, 25);
		textFieldDataMes.setToolTipText("MES");
		panel_1.add(textFieldDataMes);
		
		final JTextField textFieldDataAno = new JFormattedTextField(dataAno);
		textFieldDataAno.setBounds(365, 125, 61, 25);
		textFieldDataAno.setToolTipText("ANO");
		panel_1.add(textFieldDataAno);
		
		final JTextField textFieldCpf = new JFormattedTextField(maskCpf);
		textFieldCpf.setBounds(263, 161, 163, 25);
		textFieldCpf.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCpf.setColumns(10);
		panel_1.add(textFieldCpf);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(188, 167, 32, 17);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblCpf);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(249, 205, 197, 25);
		textFieldEmail.setColumns(10);
		panel_1.add(textFieldEmail);
		
		JLabel lblEmail = new JLabel("EMAIL:");
		lblEmail.setBounds(168, 207, 51, 17);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblEmail);
		
		JButton btnNewButton = new JButton("CADASTRAR ALUNO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        List<String> msgErro = new ArrayList<String>();
		    	String nameUser = textFieldNome.getText();
		    	String cpfUser = textFieldCpf.getText();
		    	cpfUser = cpfUser.replace(".", "");
		    	cpfUser = cpfUser.replace(".", "");
		    	cpfUser = cpfUser.replace("-", "");
		    	String emailUser = textFieldEmail.getText();	
				String data = utilitario.gerarData(textFieldDataDia.getText(),textFieldDataMes.getText(), textFieldDataAno.getText());
				
				if(!utilitario.ValideDate(data) || data == null) { msgErro.add("ERRO: DATA INVALIDA");}
		    	if(!utilitario.validarCpf(cpfUser)) {msgErro.add("ERRO: CPF INVALIDO");}
		    	if(!utilitario.valideNome(nameUser)) {msgErro.add("ERRO: NOME INVALIDO");}
		    	if(!utilitario.validarEmail(emailUser)) {msgErro.add("ERRO: EMAIL INVALIDO");}
		    	
		    	if(msgErro.isEmpty()) {
		    		Dao_Professor da = new Dao_Professor();
			    	Professor professor = new Professor(nameUser,data,cpfUser,emailUser);
			    	Long id = da.saveProfessor(professor);
			    	sucess("PROFESSOR CADASTRADO COM SUCESSO!\n MATRICULA: "+id);
			    	textFieldNome.setText("");
			    	textFieldCpf.setText("");
			    	textFieldEmail.setText("");
			    	textFieldDataAno.setText("");
			    	textFieldDataMes.setText("");
			    	textFieldDataDia.setText("");
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
	
		btnNewButton.setBounds(263, 273, 164, 23);
		panel_1.add(btnNewButton);
		
		return panel_1;

	}
}