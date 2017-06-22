package med.system.entity;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.envers.NotAudited;

@Entity
public class Doutor implements Bean {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id", nullable=false, unique=true)
    private Long id;
    
    @Column(name="nome", nullable=false, unique=false)
    private String nome;
    
    @Column(name="faixa_idade", nullable=false, unique=false)
    private String faixaIdade;

    @NotAudited
    @OneToMany(mappedBy="doutor", cascade = CascadeType.ALL)
    private List<Consulta> consultasRelacionadas;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFaixaIdade() {
        return faixaIdade;
    }

    public void setFaixaIdade(String faixaIdade) {
        this.faixaIdade = faixaIdade;
    }

}
