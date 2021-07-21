/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestaotarefas.view;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


class MensagensView implements Serializable {

    private MensagensView() {
    }

    public static MensagensView getInstance() {
        return MensagensViewHolder.INSTANCE;
    }

    private static class MensagensViewHolder {

        private static final MensagensView INSTANCE = new MensagensView();
    }

    public void exibirMensagem(String titulo, String detalhe) {

        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, detalhe);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);

    }

    public void exibirErro(String titulo, String detalhe) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalhe);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);

    }

}
