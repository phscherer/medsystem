package med.system.controller;

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
	
	
	public String salva() {
		this.usuarioDao.salva(this.usuario);
		this.usuario = new Usuario();
		FacesMessage msg = new FacesMessage("Paciente cadastrado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return "index.xhtml";
	}
	
	public void exclui(Usuario usuario) {
		this.usuarioDao.remove(usuario);
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarioDao.listaTodos();
	}
	
	public String envia() {
		usuario = usuarioDao.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
							"Erro no Login!"));
			return null;
		} else {
			return "/main";
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
