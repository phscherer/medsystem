package med.system.test;

import java.io.IOException;
import java.util.Date;
import med.system.dao.ConsultaDAO;
import med.system.entity.Consulta;
import org.junit.Test;
import junit.framework.TestCase;

public class ConsultaTest extends TestCase {
    
    @Test
    public void testAtualizaConsulta() throws IOException {
        Consulta consulta = new Consulta();
        consulta.setTitulo("Consulta 01");
        consulta.setDataConsulta(new Date());
        consulta.setObservacoes("Estou apenas testando a inserção de consultas.");
        
        ConsultaDAO consultaDao = new ConsultaDAO();
        consultaDao.salva(consulta);
        
        Consulta newConsulta = consultaDao.buscaPorld(Long.parseLong("1"));
        assertNotNull(newConsulta);
        assertEquals(newConsulta.getTitulo(), consulta.getTitulo());
        assertEquals(newConsulta.getObservacoes(), consulta.getObservacoes());
        assertEquals(newConsulta.getDataConsulta(), consulta.getDataConsulta());
    }
    
}
