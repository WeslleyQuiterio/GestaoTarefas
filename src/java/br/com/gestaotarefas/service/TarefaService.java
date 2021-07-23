/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.service;

import br.com.gestaotarefas.dao.IResponsavelDAO;
import br.com.gestaotarefas.dao.ITarefaDAO;
import br.com.gestaotarefas.dao.ResponsavelDAO;
import br.com.gestaotarefas.dao.TarefaDAO;
import br.com.gestaotarefas.model.Filtro;
import br.com.gestaotarefas.model.Responsavel;
import br.com.gestaotarefas.model.Tarefa;
import br.com.gestaotarefas.util.EntityManagerUtil;
import br.com.lumi.exception.BaseException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Weslley
 */
public class TarefaService implements Serializable{

    private ITarefaDAO tarefaDAO = new TarefaDAO();

    public List<Tarefa> buscarTodos() throws BaseException {
        EntityManager em = null;

        try {
            em = EntityManagerUtil.getInstance().getEntityManager();

            tarefaDAO.setEntityManager(em);
            return tarefaDAO.buscarTodos();
        } catch (Exception ex) {
            throw new BaseException(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
    
    public List<Tarefa> buscarPorFiltro(Filtro filtro)throws BaseException {
        EntityManager em = null;

        try {
            em = EntityManagerUtil.getInstance().getEntityManager();

            tarefaDAO.setEntityManager(em);
            return tarefaDAO.buscarPorFiltro(filtro);
        } catch (Exception ex) {
            throw new BaseException(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void salvar(Tarefa tarefa) throws BaseException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = EntityManagerUtil.getInstance().getEntityManager();
            tarefaDAO.setEntityManager(em);
            tx = em.getTransaction();
            tx.begin();
            if (tarefa.getIdTarefa() == null || tarefa.getIdTarefa() == 0) {
                tarefaDAO.incluir(tarefa);
            } else {
                tarefaDAO.alterar(tarefa);
            }

            tarefaDAO.flush();
            tarefaDAO.clear();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new BaseException(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    

    public void excluir(Tarefa tarefa) throws BaseException {
        EntityManager em = null;
        EntityTransaction tx = null;
        Tarefa tarefaDel = null;

        try {
            em = EntityManagerUtil.getInstance().getEntityManager();
            tarefaDAO.setEntityManager(em);
            tx = em.getTransaction();
            tx.begin();
            if (tarefa.getIdTarefa() != null && tarefa.getIdTarefa() > 0) {
                tarefaDel = tarefaDAO.buscarPorId(tarefa.getIdTarefa());
                tarefaDAO.excluir(tarefaDel);
            }

            tarefaDAO.flush();
            tarefaDAO.clear();
            tx.commit();

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new BaseException(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
