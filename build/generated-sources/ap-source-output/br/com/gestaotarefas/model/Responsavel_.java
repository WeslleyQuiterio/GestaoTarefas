package br.com.gestaotarefas.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Responsavel.class)
public abstract class Responsavel_ {

	public static volatile ListAttribute<Responsavel, Tarefa> tarefaList;
	public static volatile SingularAttribute<Responsavel, String> nome;
	public static volatile SingularAttribute<Responsavel, Integer> idResponsavel;

}

