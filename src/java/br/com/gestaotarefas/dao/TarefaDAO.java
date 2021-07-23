/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.dao;

import br.com.gestaotarefas.model.Filtro;
import br.com.gestaotarefas.model.Tarefa;
import br.com.lumi.persistence.dao.JPADAOImpl;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Weslley
 */
public class TarefaDAO extends JPADAOImpl<Tarefa, Integer> implements ITarefaDAO, Serializable {

    @Override
    public String getNamedQueryBuscarTodos() {
        return "Tarefa.findAll";
    }

    @Override
    public List<Tarefa> buscarPorFiltro(Filtro filtro) {
        String sql = "SELECT t FROM Tarefa t WHERE (titulo like '%" + filtro.getTituloDesc() + "%' OR descricao like '%" + filtro.getTituloDesc() + "%')";
        if (filtro.getNumero() != null && filtro.getNumero() > 0) {
            sql += " AND idTarefa = " + filtro.getNumero();
        }
        List<Tarefa> tarefas = null;

        if (filtro.getResponsavel() != null) {
            sql += " AND idResponsavel = " + filtro.getResponsavel().getIdResponsavel();
        }
        sql += filtro.getSituacao().equals("P") ? " AND concluido = false" : " AND concluido = true";

        Query query = getEntityManager().createQuery(sql);
        tarefas = query.getResultList();
        return tarefas;

    }

}
