package med.system.controller;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import med.system.dao.ConsultaDAO;
import med.system.entity.Consulta;

@ViewScoped
@ManagedBean(name = "consultaBean")
public class ConsultaBean {
    
    private Consulta consulta = new Consulta();
    private ConsultaDAO consultaDao = new ConsultaDAO();
    
    public void exclui(Consulta consulta) throws IOException {
        this.consultaDao.remove(consulta);
        goListPage();
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

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Consulta agendada com sucesso!"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../main.xhtml");
        }
    }
    
    public void update(Consulta consulta) throws IOException {
        this.setConsulta(consulta);
        goUpdatePage();
    }
    
    private boolean isFilledFields(Consulta consulta) {
        return consulta.getTitulo().isEmpty() || consulta.getDataConsulta() == null;
    }
    
    public void goUpdatePage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("update.xhtml");
    }
    
    public void goListPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("list-per-patient.xhtml");
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    
}