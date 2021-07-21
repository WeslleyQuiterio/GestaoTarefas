/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.dao;

import br.com.gestaotarefas.model.Tarefa;
import br.com.lumi.persistence.dao.JPADAOImpl;
import java.io.Serializable;

/**
 *
 * @author Weslley
 */
public class TarefaDAO extends JPADAOImpl<Tarefa, Integer> implements ITarefaDAO, Serializable{

    @Override
    public String getNamedQueryBuscarTodos() {
       return "Tarefa.findAll"; 
    }
    
}
