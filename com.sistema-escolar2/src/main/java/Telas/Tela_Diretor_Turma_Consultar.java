package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import daos.Dao_Turma;
import entidades.Turma;

@SuppressWarnings("serial")
public class Tela_Diretor_Turma_Consultar extends JFrame {


	private JTextField consultaTexto;
	private Utilitario utilitario = new Utilitario();
	private Dao_Turma dao_turma = new Dao_Turma();
	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	
	}

	
	
	public JScrollPane getLista(List<Turma> listTurmas) {	
		int TAM = listTurmas.size();
		String[][]  lista = new String [TAM][4];
		Turma turma = null;
		for(int i=0; i<TAM;i++) {
			turma = listTurmas.get(i);
			String disciplina = " - ";
			String professor = " - ";
			String qtAlunos = ""+turma.getListAluno().size();
			if(turma.getDisciplina()!= null && turma.getDisciplina().getNome()!=null){
				disciplina = turma.getDisciplina().getNome();
			}
			
			if(turma.getProfessor()!= null && turma.getProfessor().getNome()!=null) {
				professor =turma.getProfessor().getNome();
			}
			lista[i][0]=""+turma.getId();
			lista[i][1]=  disciplina;
			lista[i][2]= professor;
			lista[i][3] = qtAlunos;
		}
		
		String [] dados = {"CODIGO","DISCIPLINA","PROFESSOR","Nº ALUNOS"};
		JTable tabela = new JTable (lista,dados);
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(250);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(250);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
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
		opcaoBusca.setModel(new DefaultComboBoxModel(new String[] {"TODAS","PROFESSOR", "CODIGO","DISCIPLINA","ALUNO"}));
		panel.add(opcaoBusca);
		
		consultaTexto = new JTextField();
		consultaTexto.setBounds(10, 34, 265, 25);
		panel.add(consultaTexto);
		consultaTexto.setColumns(10);
		
		
		JButton buscarDisciplina = new JButton("BUSCAR TURMA");
		buscarDisciplina.setBounds(516, 34, 190, 25);
		buscarDisciplina.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
				try {
				Integer opcao = opcaoBusca.getSelectedIndex();	
				
				String valorConsulta = consultaTexto.getText();
				try {
					panel.remove(3); /// só excuta se existir o elemento;
				}catch (Exception ec) {
					
				}
				switch (opcao) {
				
				case 0:
		      			panel.add(getLista(dao_turma.getAll()));
		    			repaint();		
				break;
				
				case 1:
					if(utilitario.valideCodigo(valorConsulta)) {
		      			List<Turma> listTurmas = dao_turma.getTurmaProfessor(Long.parseLong(valorConsulta));
		      			panel.add(getLista(listTurmas));
		    			repaint();		
					}else {
						alert("ERRO: CODIGO DO PROFESSOR INVALIDO!");
					}
				break;
					
				case 2:
					
					if(utilitario.valideCodigo(valorConsulta)) {
					Turma turma = dao_turma.getTurmaCodigo(Long.parseLong(valorConsulta));
		      		if(turma == null) {
		      			alert("TURMA NÃO ENCONTRADA!");
		      		}else {
		      			sucess(turma.toString());
		      			repaint();
		      		}
					repaint();
					}else {
						alert("ERRO: CODIGO INVALIDO!");
					}
					
				break;
				
				case 3:
					if(utilitario.valideCodigo(valorConsulta)) {
		      			List<Turma> listTurmas =  dao_turma.getTurmaDisciplina(Long.parseLong(valorConsulta));
		      			panel.add(getLista(listTurmas));
		    			repaint();		
					}else {
						alert("ERRO: CODIGO DA DISCIPLINA INVALIDA!");
					}
					
				break;	
				
				case 4:
					if(utilitario.valideMatricula(valorConsulta)) {
		      			List<Turma> listTurmas =  dao_turma.getTurmaAlnuo(Long.parseLong(valorConsulta));
		      			panel.add(getLista(listTurmas));
		    			repaint();		
					}else {
						alert("ERRO: MATRICULA DO ALUNO INVALIDA!");
					}
					
				break;	
				
				default:
				
					break;
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
