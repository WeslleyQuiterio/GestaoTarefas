/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.model;

/**
 *
 * @author Weslley
 */
public class Filtro {

    private Integer numero;
    private String tituloDesc;
    private Responsavel responsavel;
    private String situacao;

    public Filtro(Integer numero, String tituloDesc, Responsavel responsavel, String situacao) {
        this.numero = numero;
        this.tituloDesc = tituloDesc;
        this.responsavel = responsavel;
        this.situacao = situacao;
    }

    public Filtro() {
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTituloDesc() {
        return tituloDesc;
    }

    public void setTituloDesc(String tituloDesc) {
        this.tituloDesc = tituloDesc;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "Filtro{" + "numero=" + numero + ", tituloDesc=" + tituloDesc + ", responsavel=" + responsavel + ", situacao=" + situacao + '}';
    }

}
