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
import daos.Dao_Disciplina;
import entidades.Disciplina;

@SuppressWarnings("serial")
public class Tela_Diretor_Disciplina_Consulta extends JFrame {

	private JTextField consultaTexto;
	private Utilitario utilitario = new Utilitario();
	
	public void alert(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem);
	
	}
	public void sucess(String mensagem) {
		JOptionPane.showInternalMessageDialog(null, mensagem);
	
	}
	
	
	public JScrollPane getLista(List<Disciplina> listDisciplinas) {	
		int TAM = listDisciplinas.size();
		String[][]  lista = new String [TAM][3];
		Disciplina disciplina = null;
		for(int i=0; i<TAM;i++) {
			disciplina = listDisciplinas.get(i);
			lista[i][0]=""+disciplina.getCodigo();
			lista[i][1]=disciplina.getNome();
			lista[i][2]=disciplina.getDescricao();
		}
		
		String [] dados = {"CODIGO","NOME","DESCRIÇÃO"};
		JTable tabela = new JTable (lista,dados);
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(500);
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
		opcaoBusca.setModel(new DefaultComboBoxModel(new String[] {"NOME", "CODIGO"}));
		panel.add(opcaoBusca);
		
		consultaTexto = new JTextField();
		consultaTexto.setBounds(10, 34, 265, 25);
		panel.add(consultaTexto);
		consultaTexto.setColumns(10);
		
		
		JButton buscarDisciplina = new JButton("BUSCAR DISCIPLINA");
		buscarDisciplina.setBounds(516, 34, 190, 25);
		buscarDisciplina.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
				try {
				
				Integer opcao = opcaoBusca.getSelectedIndex();	
				String valorConsulta = consultaTexto.getText();
				Dao_Disciplina dao_disciplina = new Dao_Disciplina();
				
				try {
					panel.remove(3); /// só excuta se existir o elemento;
				}catch (Exception ec) {
					
				}
				
				if(opcao == 0) {
				
					

					List<Disciplina> listDisciplinas= dao_disciplina.getDisciplinaNome(valorConsulta);
					panel.add(getLista(listDisciplinas));
					repaint();
				}else if(opcao ==1){
					
					if(utilitario.valideCodigo(valorConsulta)) {
					Disciplina disciplina = dao_disciplina.getDisciplinaCodigo(Long.parseLong(valorConsulta));
		      		if(disciplina == null) {
		      			alert("DISCIPLINA NÃO ENCONTRADA!");
		      		}else {
		      			sucess(disciplina.toString());
		      			repaint();
		      		}
					repaint();
					}else {
						alert("ERRO: CODIGO INVALIDO!");
					}
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
