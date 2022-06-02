package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Utilitarios.Utilitario;
import daos.Dao_Professor;
import entidades.Professor;

@SuppressWarnings("serial")
public class Tela_Diretor_Professor_Consulta extends JFrame {

	private JTextField consultaTexto;
	private Dao_Professor dao_professor = new Dao_Professor();
	private Utilitario utilitario = new Utilitario();
	
	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	}
	
	
	public JScrollPane getLista(List<Professor> listProfessor) {	
		int TAM = listProfessor.size();
		String[][]  lista = new String [TAM][5];
		Professor professor = null;
		for(int i=0; i<TAM;i++) {
			professor = listProfessor.get(i);
			lista[i][0]=""+professor.getCodigo();
			lista[i][1]=professor.getNome();
			lista[i][2]= ""+professor.getNascimento();				
			lista[i][3]= ""+professor.getCpf();
			lista[i][4]= ""+professor.getEmail();
		}
		
		String [] dados = {"CODIGO","NOME","NASCIMENTO","CPF","EMAIL"};
		JTable tabela = new JTable (lista,dados);
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(250);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(300);
		tabela.setEnabled(false);
		JScrollPane scroll_table = new JScrollPane(tabela);           // add table to scroll panel
		scroll_table.setBounds(5, 100, 700, 350);
		scroll_table.setVisible(true);
		
		return scroll_table;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanel getPanel() {
		
		
		final JPanel panel = new JPanel();
		panel.setLayout(null);
		
		final JComboBox opcaoBusca = new JComboBox();
		opcaoBusca.setBounds(328, 34, 149, 25);
		opcaoBusca.setModel(new DefaultComboBoxModel(new String[] {"NOME", "CPF", "CODIGO"}));
		panel.add(opcaoBusca);
		
		consultaTexto = new JTextField();
		consultaTexto.setBounds(10, 34, 265, 25);
		panel.add(consultaTexto);
		consultaTexto.setColumns(10);
		
		
		JButton buscarProfessor = new JButton("BUSCAR PROFESSOR");
		buscarProfessor.setBounds(516, 34, 190, 25);
		buscarProfessor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
			
			
				try {
					
				Integer opcao = opcaoBusca.getSelectedIndex();	
				String valorConsulta = consultaTexto.getText();
				
				switch (opcao) {
				
				case 0:
					try {
						panel.remove(3);
					}catch (Exception e2) {
					}
				
					try {
						List<Professor> listProfessor= dao_professor.getProfessorNome(valorConsulta);	
		      			panel.add(getLista(listProfessor));
						repaint();
					}catch (Exception e2) {
						alert("ERRO NO SISTEMA"+e2.getMessage());
					}
				break;
				
				case 1:
					try {
						panel.remove(3);
					}catch (Exception e2) {
					}
				
					try {
						if(utilitario.validarCpf(valorConsulta)) {
							List<Professor> listAluno= dao_professor.getProfessorCpf(valorConsulta);	
			      			panel.add(getLista(listAluno));
							repaint();
						}else {
							alert("CPF INVALIDO");
						}
					}catch (Exception e2) {
						alert("ERRO NO SISTEMA"+e2.getMessage());
					}
					
				break;
					
				case 2:
					try {
						panel.remove(3);
					}catch (Exception e2) {
					}
				
					try {
							List<Professor> listProfessor= new ArrayList<Professor>();
							if(utilitario.valideMatricula(valorConsulta)){
								Professor professor = dao_professor.getProfessorMatricula(Long.parseLong(valorConsulta));
								if(professor==null) {
									panel.add(getLista(listProfessor));
								}else {
									listProfessor.add(professor);
									panel.add(getLista(listProfessor));
								}	
				      		
								repaint();
							}else {
								alert("CODIGO INVALIDA");
								repaint();
							}
							
					}catch (Exception e2) {
						alert("ERRO NO SISTEMA"+e2.getMessage());
					}
				break;
				

				default:
					break;
				}
				
				}catch (Exception e2) {
						alert("ERRO NO SISTEMA!"+e2.getMessage());
						System.exit(-1);
				}
			}
		});
		panel.add(buscarProfessor);
		
		return panel;
	}	

}
