package es.uva.inf.tds.pr2;

import java.time.LocalDate;
import java.util.ArrayList;

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

	private ArrayList<INoticia> listaNoticias;

	/**
	 * Creación de un boletín de noticias a partir de un conjunto inicial de
	 * noticia.
	 * 
	 * @param al {@code ArrayList} compuesto por {@code Noticias} que representa el
	 *           conjunto inicial de noticias.
	 * @throws {@code IllegalArgumentException} si la lista introducida por
	 *         parámetro es {@code null}.
	 */
	public Boletin(ArrayList<INoticia> al) {
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
	public ArrayList<INoticia> getNoticias() {
		return listaNoticias;
	}

	/**
	 * Agrega una noticia al boletín.
	 * 
	 * @param in {@code Noticia} a agregar al boletín.
	 * @throws {@code IllegalArgumentException} en caso de que la noticia a
	 *         introducir sea {@code null}.
	 * @throws {@code IllegalArgumentException} en caso de que la noticia ya exista.
	 */
	public void addNoticia(INoticia in) {
		if (in == null) {
			throw new IllegalArgumentException();
		}
		if (listaNoticias.contains(in))
			throw new IllegalArgumentException();
		listaNoticias.add(in);
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
	public ArrayList<INoticia> getChronologicalOrder() { // REVISAR
		ArrayList<INoticia> resultado = new ArrayList<>();
		ArrayList<INoticia> copia = (ArrayList<INoticia>) listaNoticias.clone();

		while (!listaNoticias.isEmpty()) {
			LocalDate fechaAntigua = getOldestDate();
			for (int i = 0; i < listaNoticias.size(); i++) {
				if (listaNoticias.get(i).getFechaPublicacion().compareTo(fechaAntigua) == 0) {
					resultado.add(listaNoticias.get(i));
					listaNoticias.remove(i);
				}
			}
		}
		listaNoticias = copia;

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
	public ArrayList<INoticia> getNewsByCategory() {
		ArrayList<INoticia> cronologico = getChronologicalOrder();
		ArrayList<INoticia> resultado = new ArrayList<>();
		for (int i = 0; i < cronologico.size(); i++) {
			if (cronologico.get(i).getCategoria().equals(EnumCategoria.nacional)) {
				resultado.add(cronologico.get(i));
				cronologico.remove(i);
			}
		}

		for (int i = 0; i < cronologico.size(); i++) {
			if (cronologico.get(i).getCategoria().equals(EnumCategoria.internacional)) {
				resultado.add(cronologico.get(i));
				cronologico.remove(i);
			}
		}

		for (int i = 0; i < cronologico.size(); i++) {
			if (cronologico.get(i).getCategoria().equals(EnumCategoria.sociedad)) {
				resultado.add(cronologico.get(i));
				cronologico.remove(i);
			}
		}

		for (int i = 0; i < cronologico.size(); i++) {
			if (cronologico.get(i).getCategoria().equals(EnumCategoria.economia)) {
				resultado.add(cronologico.get(i));
				cronologico.remove(i);
			}
		}

		for (int i = 0; i < cronologico.size(); i++) {
			if (cronologico.get(i).getCategoria().equals(EnumCategoria.deporte)) {
				resultado.add(cronologico.get(i));
				cronologico.remove(i);
			}
		}

		for (INoticia noticia : cronologico) {
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
	public ArrayList<INoticia> getSimilarNews(INoticia n) {
		if (n == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<INoticia> resultado = new ArrayList<>();

		for (INoticia noticia : listaNoticias) {
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
		ArrayList<INoticia> resultado = new ArrayList<>();

		for (INoticia noticia : listaNoticias) {
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
	 *         En caso de no tener noticias, devuelve un boletín vacío.
	 * @throws {@code IllegalArgumentException} en caso de que alguna de las fechas
	 *         introducidas por parámetro sea {@code null}.
	 * @throws {@code IllegalArgumentException} en caso de que la fecha inicial del
	 *         intervalo sea mayor que la final
	 */
	public Boletin getSubconjuntoIntervalo(LocalDate inicioIntervalo, LocalDate finalIntervalo) {
		if (inicioIntervalo == null || finalIntervalo == null) {
			throw new IllegalArgumentException();
		}

		if (inicioIntervalo.compareTo(finalIntervalo) > 0) {
			throw new IllegalArgumentException();
		}

		ArrayList<INoticia> resultado = new ArrayList<>();

		for (INoticia noticia : listaNoticias) {
			if (noticia.getFechaPublicacion().compareTo(inicioIntervalo) >= 0
					&& noticia.getFechaPublicacion().compareTo(finalIntervalo) <= 0) {
				resultado.add(noticia);
			}
		}

		if (resultado.isEmpty()) {
			return new Boletin();
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
		ArrayList<INoticia> resultado = new ArrayList<>();

		for (INoticia noticia : listaNoticias) {
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
		ArrayList<INoticia> resultado = new ArrayList<>();

		for (INoticia noticia : listaNoticias) {
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

		ArrayList<INoticia> resultado = new ArrayList<>();

		for (INoticia noticia : listaNoticias) {
			if (noticia.getCategoria().equals(categoriaBuscada)
					&& noticia.getFechaPublicacion().compareTo(inicioIntervalo) >= 0
					&& noticia.getFechaPublicacion().compareTo(finalIntervalo) <= 0) {
				resultado.add(noticia);
			}
		}

		return new Boletin(resultado);
	}

	/**
	 * Obtiene el porcentaje de similitud entre dos boletines, se comprueba la
	 * similitud entre el objeto actual y el pasado por parámetro. Dos noticias son
	 * similares si comparten titular y categoría y su fecha no se diferencia en más
	 * de dos días.
	 * 
	 * @param boletinComparar Objeto boletín a comparar con el objeto actual,
	 *                        cuantas noticias similares tiene.
	 * @return porcentaje en punto flotante
	 * @throws {@code IllegalArgumentException} Si el boletin a comparar es nulo
	 */
	public double getGradoSimilitud(Boletin boletinComparar) {
		if (boletinComparar == null) {
			throw new IllegalArgumentException();
		}
		int noticiasSimilares = 0;
		ArrayList<INoticia> copia = boletinComparar.getNoticias();

		for (int i = 0; i < listaNoticias.size(); i++) {
			for (int j = 0; j < boletinComparar.getNumberOfNoticias(); j++) {
				if (listaNoticias.get(i).isSimilar(copia.get(j)))
					noticiasSimilares++;
			}
		}

		return noticiasSimilares * 100 / listaNoticias.size();
	}

}