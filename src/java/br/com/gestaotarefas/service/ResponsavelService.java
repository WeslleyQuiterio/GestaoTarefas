/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.service;

import br.com.gestaotarefas.dao.IResponsavelDAO;
import br.com.gestaotarefas.dao.ResponsavelDAO;
import br.com.gestaotarefas.model.Responsavel;
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
public class ResponsavelService implements Serializable{

    private IResponsavelDAO responsavelDAO = new ResponsavelDAO();

    public List<Responsavel> buscarTodos() throws BaseException {
        EntityManager em = null;

        try {
            em = EntityManagerUtil.getInstance().getEntityManager();

            responsavelDAO.setEntityManager(em);
            return responsavelDAO.buscarTodos();
        } catch (Exception ex) {
            throw new BaseException(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public void salvar(Responsavel responsavel) throws BaseException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = EntityManagerUtil.getInstance().getEntityManager();
            responsavelDAO.setEntityManager(em);
            tx = em.getTransaction();
            tx.begin();
            if (responsavel.getIdResponsavel() == null || responsavel.getIdResponsavel() == 0) {
                responsavelDAO.incluir(responsavel);
            } else {
                responsavelDAO.alterar(responsavel);
            }

            responsavelDAO.flush();
            responsavelDAO.clear();
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
    
    

    public void excluir(Responsavel responsavel) throws BaseException {
        EntityManager em = null;
        EntityTransaction tx = null;
        Responsavel respDel = null;

        try {
            em = EntityManagerUtil.getInstance().getEntityManager();
            responsavelDAO.setEntityManager(em);
            tx = em.getTransaction();
            tx.begin();
            if (responsavel.getIdResponsavel() != null && responsavel.getIdResponsavel() > 0) {
                respDel = responsavelDAO.buscarPorId(responsavel.getIdResponsavel());
                responsavelDAO.excluir(respDel);
            }

            responsavelDAO.flush();
            responsavelDAO.clear();
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
