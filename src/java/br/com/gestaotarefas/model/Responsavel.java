/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Weslley
 */
@Entity
@Table(schema = "gestao_tarefas")
@NamedQueries({
    @NamedQuery(name = "Responsavel.findAll", query = "SELECT r FROM Responsavel r")
    , @NamedQuery(name = "Responsavel.findByIdResponsavel", query = "SELECT r FROM Responsavel r WHERE r.idResponsavel = :idResponsavel")
    , @NamedQuery(name = "Responsavel.findByNome", query = "SELECT r FROM Responsavel r WHERE r.nome = :nome")})
public class Responsavel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idResponsavel", nullable = false)
    private Integer idResponsavel;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String nome;   

    public Responsavel() {
    }

    public Responsavel(Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public Responsavel(Integer idResponsavel, String nome) {
        this.idResponsavel = idResponsavel;
        this.nome = nome;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResponsavel != null ? idResponsavel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsavel)) {
            return false;
        }
        Responsavel other = (Responsavel) object;
        if ((this.idResponsavel == null && other.idResponsavel != null) || (this.idResponsavel != null && !this.idResponsavel.equals(other.idResponsavel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.gestaotarefas.model.Responsavel[ idResponsavel=" + idResponsavel + " ]";
    }
    
}
