/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.view;

import br.com.gestaotarefas.model.Responsavel;
import br.com.gestaotarefas.service.ResponsavelService;
import br.com.lumi.exception.BaseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Weslley
 */
@ManagedBean
@SessionScoped
public class ResponsavelBean {

    private Responsavel responsavel;
    private List<Responsavel> responsaveis;
    private ResponsavelService service;

    public ResponsavelBean() {
        responsavel = new Responsavel();
        responsaveis = new ArrayList<>();
        service = new ResponsavelService();
        refresh();
    }

    private void refresh() {
        try {
            responsaveis = service.buscarTodos();
        } catch (BaseException ex) {
            MensagensView.getInstance().exibirErro("Erro ao buscar Responsáveis", "Detalhe: " + ex.getMessage());
        }
    }

    /*
    Getters and Setters
     */
    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public List<Responsavel> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }

}
