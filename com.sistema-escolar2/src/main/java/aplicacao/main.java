package aplicacao;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder.In;

import DAOs.Dao_Aluno;
import DAOs.Dao_Disciplina;
import DAOs.Dao_Professor;
import DAOs.Dao_Turma;
import Telas.Tela_Aluno;
import entidades.Aluno;
import entidades.Chamada;
import entidades.Disciplina;
import entidades.Professor;
import entidades.Turma;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class main extends Application{
	
	
	static void  n() {
			System.out.println("LA");
	}
	
	
	public void cadastrarAluno(Stage primaryStage) {
		
		primaryStage.close();
        Button enviar = new Button("CADASTRAR ALUNO");
        Button voltar = new Button("VOLTAR");
	    TextField nome  = new  TextField("NOME");
	    TextField cpf  = new  TextField("cpf");
	    TextField email  = new  TextField("email");
	    
	    HBox data = new HBox();
	    TextField dia = new TextField("DIA");
	    TextField mes = new TextField("MES");
	    TextField ano = new TextField("ANO");
	    dia.setMaxWidth(40);
	    mes.setMaxWidth(40);
	    ano.setMaxWidth(40);
	    
	    data.getChildren().addAll(dia,mes,ano);
	    data.setAlignment(Pos.CENTER);
	    
	    nome.setMaxWidth(200);
	    cpf.setMaxWidth(200);
	    email.setMaxWidth(200);
	    enviar.setMaxWidth(200);
	   
	    voltar.setOnAction( n -> {
			 star(primaryStage); 
		});
	    
	    enviar.setOnAction( n -> {
	    	String name = nome.getText();
	    	String cpfUser = cpf.getText();
	    	String emailUser = email.getText();
	    	Dao_Aluno da = new Dao_Aluno();
	    	Date date = new Date(Integer.parseInt(ano.getText()),Integer.parseInt(mes.getText()),Integer.parseInt(dia.getText()));
	    	Aluno aluno = new Aluno(name,date,cpfUser,emailUser);
	    	Long id = da.saveAluno(aluno);
	    	Alert alerta = new Alert(AlertType.INFORMATION);
	    	alerta.setContentText("ALUNO CADASTRADO COM SUCESSO!\n MATRICULA: "+id);
	    	alerta.show();
	    	nome.clear();
	    	cpf.clear();
	    	email.clear();
	    	dia.clear();
	    	mes.clear();
	    	ano.clear();
		});
	    
	    
        VBox box = new VBox();
	    box.setAlignment(Pos.CENTER);
	    box.setSpacing(20);
		box.getChildren().addAll(nome,data,cpf,email,enviar,voltar);
		
		Scene cenaCadastroAluno = new Scene(box);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.setScene(cenaCadastroAluno);
	    primaryStage.show();
	}
	
	public void verAluno(Stage primaryStage) {
			
		 primaryStage.close();
		  TextField matricula = new TextField("MATRICULA DO ALUNO");
		  matricula.setPromptText("MATRICULA DO ALUNO");
		  Button consultar = new Button("Consultar Aluno");
		  Button voltar = new Button("Voltar");
		    voltar.setOnAction( n -> {
			 	star(primaryStage);
		    });
		    consultar.setOnAction( n -> {
			 	
		    	Dao_Aluno da = new Dao_Aluno();
		    	Aluno aluno = da.getAlunoMatricula(Long.parseLong(matricula.getText()));
		    	Alert alerta = new Alert(AlertType.INFORMATION);
		    	if(aluno != null) {
		    		alerta.setContentText(aluno.toString());
		    	}else {
		    		alerta.setContentText("ALUNO N�O ENCONTRADO!");
		    	}
		    
		    	alerta.show();
		    	
		    });
		  
		    consultar.setMaxWidth(200);
		    voltar.setMaxWidth(200);
		    matricula.setMaxWidth(200);
		    HBox boxh = new HBox();
		    boxh.setAlignment(Pos.CENTER);
		    boxh.setSpacing(40);
		    boxh.getChildren().addAll(voltar,consultar);
			VBox box = new VBox();
		    box.setAlignment(Pos.CENTER);
		    box.setSpacing(20);
			box.getChildren().addAll(matricula,boxh);
			Scene cenaUnica = new Scene(box);
			primaryStage.setWidth(800);
			primaryStage.setHeight(600);
			primaryStage.setScene(cenaUnica);
		    primaryStage.show();	
		    
		
	}
	
	public void star(Stage primaryStage) {
		  primaryStage.close();
		  Button a = new Button("CADASTRAR ALUNO");
		  Button b = new Button("VER ALUNOS!");
		    a.setOnAction( n -> {
			 cadastrarAluno(primaryStage); 
		    });
		    b.setOnAction( n -> {
				 verAluno(primaryStage);
			    });
		    
			HBox box = new HBox();
		    box.setAlignment(Pos.CENTER);
		    box.setSpacing(20);
			box.getChildren().add(a);
			box.getChildren().add(b);
			Scene cenaUnica = new Scene(box);
			primaryStage.setWidth(800);
			primaryStage.setHeight(600);
			primaryStage.setScene(cenaUnica);
		    primaryStage.show();	
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			
			Tela_Aluno tela_aluno = new Tela_Aluno();
			tela_aluno.star(primaryStage);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
			
		launch(args);
		
	}

	

}
