package testes.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

import br.com.project.report.util.DateUtils;

public class TesteData {

	@Test
	public void testeData() {
		
		try {
			
			assertEquals("27012021", DateUtils.getDateAtualReportName());
			
			assertEquals("'2021-01-27'", DateUtils.formatDateStringSQL(Calendar.getInstance().getTime()));
			
			assertEquals("2021-01-27", DateUtils.formatDateStringSQLSimple(Calendar.getInstance().getTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
