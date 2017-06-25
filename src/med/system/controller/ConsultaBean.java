package med.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import med.system.dao.ConsultaDAO;
import med.system.dao.DoutorDAO;
import med.system.entity.Consulta;
import med.system.entity.Doutor;

@ManagedBean
public class ConsultaBean {
    
    private Consulta consulta = new Consulta();
    private ConsultaDAO consultaDao = new ConsultaDAO();
    
    public void exclui(Consulta consulta) {
        this.consultaDao.remove(consulta);
    }
    
    public List<Consulta> getConsultas() {
        return this.consultaDao.listaTodos();
    }
    
    public void salva() throws IOException {
        if (isFilledFields(this.consulta)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERRO!", "Informe os dados corretamente."));
        } else {
            this.consultaDao.salva(this.consulta);
            this.consulta = new Consulta();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Paciente cadastrado com sucesso!"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../main.xhtml");
        }
    }
    
    public List<SelectItem> getDoutores() {
       DoutorDAO doutorDao = new DoutorDAO();
       List<Doutor> listaDoutores = doutorDao.listaTodos();
       List<SelectItem> itens = new ArrayList<SelectItem>(listaDoutores.size());
       listaDoutores.forEach(doutor -> {
           itens.add(new SelectItem(doutor.getId(), doutor.getNome()));
       });
       return itens;
    }
    
    private boolean isFilledFields(Consulta consulta) {
        return consulta.getTitulo().isEmpty() || consulta.getDataConsulta() == null;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    
}