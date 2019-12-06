package es.uva.inf.tds.pr2;

import java.time.LocalDate;
import java.util.ArrayList;
import org.easymock.Mock;
import static org.easymock.EasyMock.*;

/**
 * Clase que representa un boletín de noticias. <br>
 * Puede crearse vacío o a partir de un conjunto de noticias, permitiendo operar
 * con las noticias,<br>
 * realizar comparaciones y consultas, y obtener subconjutnos en base a ciertos
 * criterios.<br>
 * 
 * @author pamarti
 * @author migusan
 *
 */
public class Boletin {

	private ArrayList<Noticia> listaNoticias;

	/**
	 * Creación de un boletín de noticias a partir de un conjunto inicial de
	 * noticia.
	 * 
	 * @param al {@code ArrayList} compuesto por {@code Noticias} que representa el
	 *           conjunto inicial de noticias.
	 * @throws {@code IllegalArgumentException} si la lista introducida por
	 *         parámetro es {@code null}.
	 */
	public Boletin(ArrayList<Noticia> al) {
		if (al == null) {
			throw new IllegalArgumentException();
		}
		listaNoticias = al;
	}

	/**
	 * Creación de un boletín de noticias vacío.
	 */
	public Boletin() {
		listaNoticias = new ArrayList<>();
	}

	/**
	 * Devuelve las noticias que componen el boletín.
	 * 
	 * @return {@code ArrayList} que representa el conjunto de noticias del boletín.
	 */
	public ArrayList<Noticia> getNoticias() {
		return listaNoticias;
	}

	/**
	 * Agrega una noticia al boletín.
	 * 
	 * @param n {@code Noticia} a agregar al boletín.
	 * @throws {@code IllegalArgumentException} en caso de que la noticia a
	 *         introducir sea {@code null}.
	 */
	public void addNoticia(Noticia n) {
		if (n == null) {
			throw new IllegalArgumentException();
		}
		listaNoticias.add(n);
	}

	/**
	 * Devuelve el número de noticias existentes en el boletín.
	 * 
	 * @return Número entero que representa la cantidad de noticias del boletín.
	 */
	public int getNumberOfNoticias() {
		return listaNoticias.size();
	}

	/**
	 * Devuelve la fecha de la noticia publicada más recientemente. La fecha puede
	 * coincidir con varias noticias.
	 * 
	 * @return {@code LocalDate} que representa la fecha de la noticia publicada más
	 *         recientemente.
	 */
	public LocalDate getMostRecentDate() {
		// mejorable
		LocalDate fechaReciente = LocalDate.of(0, 1, 1);

		for (int i = 0; i < listaNoticias.size(); i++) {
			if (listaNoticias.get(i).getFechaPublicacion().compareTo(fechaReciente) > 0)
				fechaReciente = listaNoticias.get(i).getFechaPublicacion();
		}
		return fechaReciente;
	}

	/**
	 * Devuelve la fecha de la noticia más antigua publicada. La fecha puede
	 * coincidir con varias noticias.
	 * 
	 * @return {@code LocalDate} que representa la fecha de la noticia más antigua
	 *         publicada.
	 */
	public LocalDate getOldestDate() {
		// mejorable
		LocalDate fechaAntigua = LocalDate.of(9999, 1, 1);

		for (int i = 0; i < listaNoticias.size(); i++) {
			if (listaNoticias.get(i).getFechaPublicacion().compareTo(fechaAntigua) < 0)
				fechaAntigua = listaNoticias.get(i).getFechaPublicacion();
		}
		return fechaAntigua;
	}

	/**
	 * Obtiene todas las noticias del boletín ordenadas cronológicamente de más
	 * antigua a más reciente. En caso de coincidir en la fecha, el orden será <br>
	 * tomado por orden de llegada al boletín.
	 * 
	 * @return {@code ArrayList} que representa la lista de noticias del boletín
	 *         ordenadas cronológicamente.
	 */
	public ArrayList<Noticia> getChronologicalOrder() {
		// mejorable
		ArrayList<Noticia> resultado = new ArrayList<>();
		ArrayList<LocalDate> listaPorFechas = new ArrayList<>();
		ArrayList<Integer> valores = new ArrayList<>();

		for (int j = 0; j < listaNoticias.size(); j++) {
			listaPorFechas.add(listaNoticias.get(j).getFechaPublicacion());
		}

		while (listaPorFechas.size() > 0) {
			int i = listaPorFechas.indexOf(getOldestDate());
			valores.add(i);
			listaPorFechas.remove(i);
		}

		for (int valor : valores) {
			resultado.add(listaNoticias.get(valor));
		}
		return resultado;
	}

	/**
	 * Obtiene todas las noticias del boletín ordenadas por categoría. El orden
	 * viene determinado por: nacional, internacional, sociedad, economía, deporte y
	 * cultura. <br>
	 * Por cada categoría, el orden viene determinado por orden cronológico de más
	 * antigua a más reciente. En caso de coincidir en la fecha, el orden será <br>
	 * tomado por orden de llegada al boletín.
	 * 
	 * @return {@code ArrayList} que representa la lista de noticias del boletín
	 *         ordenadas por categoría.
	 */
	public ArrayList<Noticia> getNewsByCategory() {
		ArrayList<Noticia> cronologico = getChronologicalOrder();
		ArrayList<Noticia> resultado = new ArrayList<>();

		for (Noticia noticia : cronologico) {
			if (noticia.getCategoria().equals(EnumCategoria.nacional)) {
				resultado.add(noticia);
				cronologico.remove(noticia);
			}
		}

		for (Noticia noticia : cronologico) {
			if (noticia.getCategoria().equals(EnumCategoria.internacional)) {
				resultado.add(noticia);
				cronologico.remove(noticia);
			}
		}

		for (Noticia noticia : cronologico) {
			if (noticia.getCategoria().equals(EnumCategoria.sociedad)) {
				resultado.add(noticia);
				cronologico.remove(noticia);
			}
		}

		for (Noticia noticia : cronologico) {
			if (noticia.getCategoria().equals(EnumCategoria.economia)) {
				resultado.add(noticia);
				cronologico.remove(noticia);
			}
		}

		for (Noticia noticia : cronologico) {
			if (noticia.getCategoria().equals(EnumCategoria.deporte)) {
				resultado.add(noticia);
				cronologico.remove(noticia);
			}
		}

		for (Noticia noticia : cronologico) {
			resultado.add(noticia);
		}

		return resultado;
	}

	/**
	 * Obtiene una lista de noticias similares a la noticia introducida por
	 * parámetro. Dos noticias son similares si comparten titular y categoría y su
	 * fecha no se diferencia en más de dos días.
	 * 
	 * @param n {@code  Noticia} con una categoría determinada a partir de la cual
	 *          se obtendrán el resto de noticias de esa categoría.
	 * @return {@code ArrayList} que representa la lista de noticias con igual
	 *         categoría a la categoría de la noticia dada.
	 * @throws {@code IllegalArgumentException} en caso de que la noticia
	 *         introducida sea {@code null}.
	 */
	public ArrayList<Noticia> getSimilarNews(Noticia n) {
		if (n == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<Noticia> resultado = new ArrayList<>();

		for (Noticia noticia : listaNoticias) {
			if (noticia.isSimilar(n)) {
				resultado.add(noticia);
			}
		}

		return resultado;
	}

	/**
	 * Obtiene un boletín de noticias que contiene únicamente las noticias con fecha
	 * igual a la introducida por parámetro.
	 * 
	 * @param fechaBuscada {@code LocalDate} que representa la fecha a buscar entre
	 *                     las noticias del boletín.
	 * @return Sub-boletín compuesto únicamente por las noticias que tienen la fecha
	 *         introducida.
	 * @throws {@code IllegalArgumentException} en caso de que la fecha introducida
	 *         sea {@code null}.
	 */
	public Boletin getSubconjuntoFecha(LocalDate fechaBuscada) {
		if (fechaBuscada == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<Noticia> resultado = new ArrayList<>();

		for (Noticia noticia : listaNoticias) {
			if (noticia.getFechaPublicacion().compareTo(fechaBuscada) == 0) {
				resultado.add(noticia);
			}
		}
		return new Boletin(resultado);
	}

	/**
	 * Obtiene un boletín de noticias que contiene únicamente las noticias con fecha
	 * entre las dos fechas introducidas por parámetros. Los extremos del intervalo
	 * también están incluidos.
	 * 
	 * @param inicioIntervalo {@code LocalDate} que representa la fecha que hace de
	 *                        extremo inferior del intervalo admitido.
	 * @param finalIntervalo  {@code LocalDate} que representa la fecha que hace de
	 *                        extremo superior del intervalo admitido.
	 * @return Sub-boletín compuesto únicamente por las noticias con fecha en el
	 *         intervalo compuesto por las dos fechas introducidas por parámetros.
	 * @throws {@code IllegalArgumentException} en caso de que alguna de las fechas
	 *         introducidas por parámetro sea {@code null}.
	 */
	public Boletin getSubconjuntoIntervalo(LocalDate inicioIntervalo, LocalDate finalIntervalo) {
		if (inicioIntervalo == null || finalIntervalo == null) {
			throw new IllegalArgumentException();
		}

		ArrayList<Noticia> resultado = new ArrayList<>();

		for (Noticia noticia : listaNoticias) {
			if (noticia.getFechaPublicacion().compareTo(inicioIntervalo) >= 0
					&& noticia.getFechaPublicacion().compareTo(finalIntervalo) <= 0) {
				resultado.add(noticia);
			}
		}

		return new Boletin(resultado);
	}

	/**
	 * Obtiene un boletín de noticias que contiene únicamente las noticias con la
	 * misma categoría a la introducida por parámetro.
	 * 
	 * @param categoriaBuscada {@code EnumCategoria} que representa la categoría a
	 *                         buscar.
	 * @return Sub-boletín compuesto únicamente por las noticas con categoría igual
	 *         a la recibida por parámetro.
	 * @throws {@code IllegalArgumentException} en caso de que la categoria
	 *         introducida por parámetro sea {@code null}.
	 */
	public Boletin getSubconjuntoCategoria(EnumCategoria categoriaBuscada) {
		if (categoriaBuscada == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<Noticia> resultado = new ArrayList<>();

		for (Noticia noticia : listaNoticias) {
			if (noticia.getCategoria().equals(categoriaBuscada)) {
				resultado.add(noticia);
			}
		}
		return new Boletin(resultado);
	}

	/**
	 * Obtiene un boletín de noticias que contiene únicamente las noticias con la
	 * misma categoría y misma fecha a las introducidas por parámetro.
	 * 
	 * @param categoriaBuscada {@code EnumCategoria} que representa la categoría a
	 *                         buscar.
	 * @param fechaConcreta    {@code LocalDate} que representa la fecha a buscar
	 *                         entre las noticias del boletín.
	 * @return Sub-boletín compuesto únicamente por las noticias con fecha y
	 *         categoría iguales a las introducidas por parámetro.
	 * @throws {@code IllegalArgumentException} en caso de que la categoría o la
	 *         fecha introducidas por parámetro sean {@code null}.
	 */
	public Boletin getSubconjuntoCategoriaFecha(EnumCategoria categoriaBuscada, LocalDate fechaConcreta) {
		if (fechaConcreta == null || categoriaBuscada == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<Noticia> resultado = new ArrayList<>();

		for (Noticia noticia : listaNoticias) {
			if (noticia.getFechaPublicacion().compareTo(fechaConcreta) == 0
					&& noticia.getCategoria().equals(categoriaBuscada)) {
				resultado.add(noticia);
			}
		}
		return new Boletin(resultado);
	}

	/**
	 * Obtiene un boletín de noticias que contiene exclusivamente las noticias con
	 * la misma categoría introducida por parámetro y con fecha entre las dos fechas
	 * introducidas por parámetro. <br>
	 * Los extremos del intervalo también están incluidos.
	 * 
	 * @param categoriaBuscada {@code EnumCategoria} que representa la categoría a
	 *                         buscar.
	 * @param inicioIntervalo  {@code LocalDate} que representa la fecha que hace de
	 *                         extremo inferior del intervalo admitido.
	 * @param finalIntervalo   {@code LocalDate} que representa la fecha que hace de
	 *                         extremo superior del intervalo admitido.
	 * @return Sub-boletín compuesto únicamente por las noticias con categoría igual
	 *         a la introducida por parámetro y fecha contenida entre las dos fechas
	 *         introducidas por parámetro.
	 * @throws {@code IllegalArgumentException} en caso de que la categoría o alguna
	 *         de las fechas introducidas por parámetro sean {@code null}.
	 */
	public Boletin getSubconjuntoCategoriaIntervalo(EnumCategoria categoriaBuscada, LocalDate inicioIntervalo,
			LocalDate finalIntervalo) {
		if (categoriaBuscada == null || inicioIntervalo == null || finalIntervalo == null) {
			throw new IllegalArgumentException();
		}

		ArrayList<Noticia> resultado = new ArrayList<>();

		for (Noticia noticia : listaNoticias) {
			if (noticia.getCategoria().equals(categoriaBuscada)
					&& noticia.getFechaPublicacion().compareTo(inicioIntervalo) >= 0
					&& noticia.getFechaPublicacion().compareTo(finalIntervalo) <= 0) {
				resultado.add(noticia);
			}
		}

		return new Boletin(resultado);
	}

}
