package med.system.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.bean.ManagedBean;

import med.system.dao.UsuarioDAO;
import med.system.entity.Usuario;
import med.system.persistence.JpaUtil;

@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	JpaUtil em;
	
	public void salva() {
		this.usuarioDao.salva(this.usuario);
		this.usuario = new Usuario();
	}
	
	public void exclui(Usuario usuario) {
		this.usuarioDao.remove(usuario);
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarioDao.listaTodos();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario isUsuarioReadyToLogin(String email, String senha) {
		try {
			email = email.toLowerCase().trim();
			List retorno = em.getEntityManager()
					.createNamedQuery(Usuario.FIND_BY_EMAIL_SENHA)
					.setParameter("email", email)
					.setParameter("senha", convertStringToMd5(senha))
					.getResultList();

			if (retorno.size() == 1) {
				Usuario userFound = (Usuario) retorno.get(0);
				return userFound;
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try { 
			mDigest = MessageDigest.getInstance("MD5");
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
