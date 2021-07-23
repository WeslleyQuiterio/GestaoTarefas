/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Weslley
 */
@Entity
@Table(schema = "gestao_tarefas")
@NamedQueries({
    @NamedQuery(name = "Tarefa.findAll", query = "SELECT t FROM Tarefa t WHERE t.concluido = false")
    , @NamedQuery(name = "Tarefa.findByIdTarefa", query = "SELECT t FROM Tarefa t WHERE t.idTarefa = :idTarefa")
    , @NamedQuery(name = "Tarefa.findByDescricao", query = "SELECT t FROM Tarefa t WHERE t.descricao = :descricao")
    , @NamedQuery(name = "Tarefa.findByPrioridade", query = "SELECT t FROM Tarefa t WHERE t.prioridade = :prioridade")
    , @NamedQuery(name = "Tarefa.findByDeadline", query = "SELECT t FROM Tarefa t WHERE t.deadline = :deadline")})
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idTarefa;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String titulo;
    @Basic(optional = false)
    @Column(nullable = false)
    private Character prioridade;
    @Basic(optional = false)
    @Column(nullable = false)
    private Boolean concluido;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    @JoinColumn(name = "idResponsavel", referencedColumnName = "idResponsavel", nullable = false)
    @ManyToOne(optional = false)
    private Responsavel responsavel;
    
    
    @Transient
    private String strPrioridade;
    @Transient
    private String lookPrioridade;
    

    public Tarefa() {
    }

    public Tarefa(Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public Tarefa(Integer idTarefa, String descricao, String titulo, Character prioridade, Boolean situacao, Date deadline, Responsavel responsavel) {
        this.idTarefa = idTarefa;
        this.descricao = descricao;
        this.titulo = titulo;
        this.prioridade = prioridade;
        this.concluido = situacao;
        this.deadline = deadline;
        this.responsavel = responsavel;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Character getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Character prioridade) {
        this.prioridade = prioridade;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarefa != null ? idTarefa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarefa)) {
            return false;
        }
        Tarefa other = (Tarefa) object;
        if ((this.idTarefa == null && other.idTarefa != null) || (this.idTarefa != null && !this.idTarefa.equals(other.idTarefa))) {
            return false;
        }
        return true;
    }

    public String getStrPrioridade() {
        if (this.prioridade.equals('1')){
            strPrioridade = "Alta";
        }else if (this.prioridade.equals('2')){
            strPrioridade = "Média";
        }else{
            strPrioridade = "Baixa";
        }
        
        return strPrioridade;
    }

    public String getLookPrioridade() {
        
        if (this.prioridade.equals('1')){
            lookPrioridade = "danger";
        }else if (this.prioridade.equals('2')){
            lookPrioridade = "warning";
        }else{
            lookPrioridade = "info";
        }
        
        
        return lookPrioridade;
    }
    
    

    @Override
    public String toString() {
        return "br.com.gestaotarefas.model.Tarefa[ idTarefa=" + idTarefa + " ]";
    }

}
