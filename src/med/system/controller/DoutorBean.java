package med.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import med.system.dao.DoutorDAO;
import med.system.entity.Doutor;

@ManagedBean(name = "doutorBean")
public class DoutorBean {
    
    private Doutor doutor = new Doutor();
    private DoutorDAO doutorDao = new DoutorDAO();
    
    public void exclui(Doutor doutor) {
        this.doutorDao.remove(doutor);
    }
    
    public List<Doutor> getDoutores() {
        return this.doutorDao.listaTodos();
    }
    
    public List<SelectItem> getDoutoresSelectedItem() {
        List<Doutor> listaDoutores = this.doutorDao.listaTodos();
        List<SelectItem> itens = new ArrayList<SelectItem>(listaDoutores.size());
        for (Doutor i : listaDoutores) {
            itens.add(new SelectItem(i.getId(), i.getNome()));
        }
        return itens;
    }
    
    public void salva() throws IOException {
        if (isFilledFields(this.doutor)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERRO!", "Informe os dados corretamente."));
        } else {
            this.doutorDao.salva(this.doutor);
            this.doutor = new Doutor();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Doutor registrado com sucesso!"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../main.xhtml");
        }
    }
    
    private boolean isFilledFields(Doutor doutor) {
        return doutor.getNome().isEmpty() || doutor.getFaixaIdade().isEmpty();
    }

    public Doutor getDoutor() {
        return doutor;
    }

    public void setDoutor(Doutor doutor) {
        this.doutor = doutor;
    }
}
