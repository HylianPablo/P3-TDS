package es.uva.inf.tds.pr2;

import java.time.LocalDate;

public interface INoticia {
	
	public abstract LocalDate getFechaPublicacion();

	public abstract boolean isSimilar(INoticia iNoticia);

	public abstract EnumCategoria getCategoria();

}
