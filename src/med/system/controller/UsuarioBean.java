package med.system.controller;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import med.system.dao.UsuarioDAO;
import med.system.entity.Usuario;

@ManagedBean
public class UsuarioBean {

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDao = new UsuarioDAO();


    public void salva() throws IOException {
        if (this.usuario.getNomeUsuario().trim().isEmpty() || this.usuario.getSenha().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Informe os dados corretamente."));
        } else {
            this.usuarioDao.salva(this.usuario);
            this.usuario = new Usuario();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Paciente cadastrado com sucesso!"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    }

    public void exclui(Usuario usuario) {
        this.usuarioDao.remove(usuario);
    }

    public List<Usuario> getUsuarios() {
        return this.usuarioDao.listaTodos();
    }

    public void envia() throws IOException {
        usuario = usuarioDao.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
        if (usuario == null) {
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro no Login!"));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("main.xhtml");
        }
    }

    public void goRegisterPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("register.xhtml");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
