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
import daos.Dao_Aluno;
import entidades.Aluno;

@SuppressWarnings("serial")
public class Tela_Diretor_Aluno_Consulta extends JFrame {

	private JTextField consultaTexto;
	private Dao_Aluno dao_aluno = new Dao_Aluno();
	private Utilitario utilitario = new Utilitario();
	
	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	}
	
	
	public JScrollPane getLista(List<Aluno> listAluno) {	
		int TAM = listAluno.size();
		String[][]  lista = new String [TAM][5];
		Aluno aluno = null;
		if(listAluno.isEmpty()) {
			
		}else {
			for(int i=0; i<TAM;i++) {
				aluno = listAluno.get(i);
				lista[i][0]=aluno.getNome();
				lista[i][1]= ""+aluno.getMatricula();
				lista[i][2]= ""+aluno.getNascimento();				
				lista[i][3]= ""+aluno.getCpf();
				lista[i][4]= ""+aluno.getEmail();
			}
		}
		
		
		String [] dados = {"NOME","MATRICULA","NASCIMENTO","CPF","EMAIL"};
		JTable tabela = new JTable (lista,dados);
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(300);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
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
		opcaoBusca.setModel(new DefaultComboBoxModel(new String[] {"NOME", "CPF", "MATRICULA"}));
		panel.add(opcaoBusca);
		
		consultaTexto = new JTextField();
		consultaTexto.setBounds(10, 34, 265, 25);
		panel.add(consultaTexto);
		consultaTexto.setColumns(10);
		
		
		JButton buscarAluno = new JButton("BUSCAR ALUNO");
		buscarAluno.setBounds(516, 34, 190, 25);
		buscarAluno.addActionListener(new ActionListener() {
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
						List<Aluno> listAluno= dao_aluno.getAlunoNome(valorConsulta);	
		      			panel.add(getLista(listAluno));
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
							List<Aluno> listAluno= dao_aluno.getAlunoCpf(valorConsulta);	
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
							List<Aluno> listAluno= new ArrayList<Aluno>();
							if(utilitario.valideMatricula(valorConsulta)){
								Aluno aluno = dao_aluno.getAlunoMatricula(Long.parseLong(valorConsulta));
								if(aluno==null) {
									panel.add(getLista(listAluno));
								}else {
									listAluno.add(aluno);
									panel.add(getLista(listAluno));
								}	
				      		
								repaint();
							}else {
								alert("MATRICULA INVALIDA");
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
		panel.add(buscarAluno);
		
		return panel;
	}	

}
