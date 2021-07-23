/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.dao;

import br.com.gestaotarefas.model.Filtro;
import br.com.gestaotarefas.model.Tarefa;
import br.com.lumi.persistence.dao.GenericDAO;
import java.util.List;

/**
 *
 * @author Weslley
 */
public interface ITarefaDAO extends GenericDAO<Tarefa, Integer>{
    
    /**
     *
     * @param filtro
     * @return
     */
    public List<Tarefa> buscarPorFiltro(Filtro filtro);
}
