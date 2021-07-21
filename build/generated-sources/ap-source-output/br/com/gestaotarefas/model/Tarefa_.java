package br.com.gestaotarefas.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tarefa.class)
public abstract class Tarefa_ {

	public static volatile SingularAttribute<Tarefa, Character> prioridade;
	public static volatile SingularAttribute<Tarefa, Integer> idTarefa;
	public static volatile SingularAttribute<Tarefa, Date> deadline;
	public static volatile SingularAttribute<Tarefa, Responsavel> idResponsavel;
	public static volatile SingularAttribute<Tarefa, String> descricao;

}

