package med.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import med.system.dao.UsuarioDAO;
import med.system.entity.Usuario;
import med.system.persistence.SessionContext;

@ManagedBean(name = "usuarioBean")
public class UsuarioBean {

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDao = new UsuarioDAO();
    
    public Usuario getUser() {
        return (Usuario) SessionContext.getInstance().getUsuarioLogado();
    }
    
    public void salva() throws IOException {
        if (isFilledFields(this.usuario)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERRO!", "Informe os dados corretamente."));
        } else {
            this.usuarioDao.salva(this.usuario);
            this.usuario = new Usuario();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Paciente cadastrado com sucesso!"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    }
    
    private boolean isFilledFields(Usuario user) {
        return user.getNomeUsuario().trim().isEmpty() || user.getSenha().trim().isEmpty() || 
                user.getNome().trim().isEmpty() || user.getIdade() == 0 || user.getGenero().trim().isEmpty();
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
            SessionContext.getInstance().setAttribute("usuarioBean", usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("main.xhtml");
        }
    }
    
    public void doLogout() throws IOException {
        SessionContext.getInstance().encerrarSessao();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    public void goRegisterPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("register.xhtml");
    }
    
    public List<SelectItem> getPatient() {
        List<Usuario> listaUsers = this.usuarioDao.listaTodos();
        List<SelectItem> itens = new ArrayList<SelectItem>(1);
        for (Usuario u : listaUsers) {
            itens.add(new SelectItem(u.getId(), u.getNome()));
        }
        return itens;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
