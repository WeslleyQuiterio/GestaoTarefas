/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.dao;

import br.com.gestaotarefas.model.Responsavel;
import br.com.lumi.persistence.dao.JPADAOImpl;
import java.io.Serializable;

/**
 *
 * @author Weslley
 */
public class ResponsavelDAO extends JPADAOImpl<Responsavel, Integer> implements IResponsavelDAO, Serializable{

    @Override
    public String getNamedQueryBuscarTodos() {
        return "Responsavel.findAll";
    }
    
}
