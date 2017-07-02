package med.system.controller;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@SessionScoped
@ManagedBean
public class LocaleBean {

	private static Map<String, Object> countries;
	private String locale;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public static Map<String, Object> getCountries() {
		return countries;
	}
	
	public Map<String, Object> getCountriesMap() {
		return countries;
	}
	
	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("English", Locale.US);
		countries.put("Portugues", new Locale("pt", "BR"));
	}
	
	public void countryLocaleCodeChanged(ValueChangeEvent e) {
		String newLocaleValue = e.getNewValue().toString();
 		for (Map.Entry<String, Object> entry : countries.entrySet()) {
			if (entry.getValue().toString().equals(newLocaleValue)) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
			}
		}
	}
}