package med.system.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import med.system.entity.Usuario;
import med.system.persistence.JpaUtil;

public class UsuarioDAO extends JpaDaoBase<Usuario> {
    EntityManager em = JpaUtil.getEntityManager();
    
    public Usuario getUsuario(String nomeUsuario, String senha) {
        try {
            Usuario usuario = (Usuario) em
                    .createQuery("SELECT u from Usuario u where u.nomeUsuario = :name and u.senha = :senha")
                    .setParameter("name", nomeUsuario)
                    .setParameter("senha", senha).getSingleResult();
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }
}
