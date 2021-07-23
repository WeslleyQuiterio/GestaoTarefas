/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.view;

import br.com.gestaotarefas.model.Filtro;
import br.com.gestaotarefas.model.Responsavel;
import br.com.gestaotarefas.model.Tarefa;
import br.com.gestaotarefas.service.ResponsavelService;
import br.com.gestaotarefas.service.TarefaService;
import br.com.lumi.exception.BaseException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Weslley
 */
@ManagedBean
@SessionScoped
public class TarefaBean {

    private Responsavel responsavel;
    private Tarefa tarefa;
    private List<Responsavel> responsaveis;
    private List<Tarefa> tarefas;
    private ResponsavelService respService;
    private TarefaService tarefaService;
    private Filtro filtro;

    public TarefaBean() {
        responsavel = new Responsavel();
        filtro = new Filtro();
        responsaveis = new ArrayList<>();
        respService = new ResponsavelService();
        tarefaService = new TarefaService();
        refresh();
    }

    public void filtrar() {
        try {
            tarefas = tarefaService.buscarPorFiltro(filtro);
            responsaveis = respService.buscarTodos();
        } catch (BaseException ex) {
            MensagensView.getInstance().exibirErro("Erro ao filtrar dados", "Detalhe: " + ex.getMessage());
        }
    }

    public final void refresh() {
        try {
            tarefas = tarefaService.buscarTodos();
            responsaveis = respService.buscarTodos();
        } catch (BaseException ex) {
            MensagensView.getInstance().exibirErro("Erro ao buscar Dados novos", "Detalhe: " + ex.getMessage());
        }
    }

    public String addOrEdit(Tarefa tarefa) {
        if (tarefa == null) {
            this.tarefa = new Tarefa();
            this.tarefa.setConcluido(false);
        } else {
            this.tarefa = tarefa;
        }
        return "/app/tarefa/tarefa.xhtml?faces-redirect=true";
    }
    
    public void concluirTarefa(Tarefa tarefa){
        this.tarefa = tarefa;
        this.tarefa.setConcluido(true);
        this.salvar();
    }

    public String salvar() {
        try {
            tarefaService.salvar(this.tarefa);
            refresh();
            return "/app/tarefa/tarefas.xhtml?faces-redirect=true";
        } catch (BaseException ex) {
            MensagensView.getInstance().exibirErro("Erro ao salvar responsável", "Detalhe: " + ex.getMessage());
        }

        return "";
    }

    public void excluir(Tarefa tarefa) {
        if (tarefa != null) {
            try {
                tarefaService.excluir(tarefa);
                refresh();
            } catch (BaseException ex) {
                MensagensView.getInstance().exibirErro("Erro ao excluir tarefa", "Detalhe: " + ex.getMessage());
            }
        }
    }

    /*
    Getters and Setters
     */
    public Filtro getFiltro() {
        return filtro;
    }

    public void setFiltro(Filtro filtro) {
        this.filtro = filtro;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public List<Responsavel> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

}
