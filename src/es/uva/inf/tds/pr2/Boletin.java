package es.uva.inf.tds.pr2;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase que representa un boletín de noticias. COMPLETAR
 * @author pamarti
 * @author migusan
 *
 */
public class Boletin {

	/**
	 * Creación de un boletín de noticias a partir de un conjunto inicial de noticia.
	 * @param al {@code ArrayList} compuesto por {@code Noticias} que representa el conjunto inicial de noticias.
	 * @throws {@code IllegalArgumentException} si la lista introducida por parámetro es {@code null}.
	 */
	public Boletin(ArrayList<Noticia> al) {
		// TODO Crear constructor con noticias. Utilizar un atributo privado ArrayList
	}

	/**
	 * Creación de un boletín de noticias vacío.
	 */
	public Boletin() {
		// TODO Crear constructor vacío
	}

	/**
	 * Devuelve las noticias que componen el boletín.
	 * @return {@code ArrayList} que representa el conjunto de noticias del boletín.
	 */
	public ArrayList<Noticia> getNoticias() {
		// TODO Devolver las noticias del boletín
		return null;
	}

	/**
	 * Agrega una noticia al boletín.
	 * @param n {@code Noticia} a agregar al boletín.
	 * @throws {@code IllegalArgumentException} en caso de que la noticia a introducir sea {@code null}.
	 */
	public void addNoticia(Noticia n) {
		// TODO Agregar noticia al ArrayList

	}

	/**
	 * Devuelve el número de noticias existentes en el boletín.
	 * @return Número entero que representa la cantidad de noticias del boletín.
	 */
	public int getNumberOfNoticias() {
		// TODO Devolver size() del arrayList
		return 0;
	}

	/**
	 * Devuelve la fecha de la noticia publicada más recientemente.
	 * @return {@code LocalDate} que representa la fecha de la noticia publicada más recientemente.
	 */
	public LocalDate getMostRecentDate() {
		// TODO Recorrer las noticias con un centinela, devolver el índice que indique el centinela
		return null;
	}

	/**
	 * Devuelve la fecha de la noticia más antigua publicada.
	 * @return {@code LocalDate} que representa la fecha de la noticia más antigua publicada.
	 */
	public LocalDate getOldestDate() {
		// TODO Recorrer las noticias con un centinela, devolver el índice que indique el centinelaAuto-generated method stub
		return null;
	}

	/**
	 * Obtiene todas las noticias del boletín ordenadas cronológicamente (de anterior a posterior).
	 * @return {@code ArrayList} que representa la lista de noticias del boletín ordenadas cronológicamente.
	 */
	public ArrayList<Noticia> getChronologicalOrder() {
		// TODO Recorre las noticias y crea un nuevo ArrayList ordenado por fecha
		return null;
	}

	/**
	 * Obtiene todas las noticias del boletín ordenadas por categoría.
	 * @return {@code ArrayList} que representa la lista de noticias del boletín ordenadas por categoría.
	 */
	public ArrayList<Noticia> getNewsByCategory() {
		// TODO Recorre las noticias y crea un nuevo ArrayList ordenado por categorías. Por cada iteración elimina o descarta los ya añadidos.
		return null;
	}

	/**
	 * Obtiene una lista de noticias similares a la noticia introducida por parámetro.
	 * @param n {@code  Noticia} con una categoría determinada a partir de la cual se obtendrán el resto de noticias de esa categoría.
	 * @return {@code ArrayList} que representa la lista de noticias con igual categoría a la categoría de la noticia dada.
	 * @throws {@code IllegalArgumentException} en caso de que la noticia introducida sea {@code null}.
	 */
	public ArrayList<Noticia> getSimilarNews(Noticia n) {
		// TODO Recorre las noticias y crea un nuevo ArrayList con las noticias similares. Lanzar antes de nada una excepción en caso de ser Noticia null.
		return null;
	}

	/**
	 * Obtiene un boletín de noticias que contiene únicamente las noticias ccon fecha igual a la introducida por parámetro.
	 * @param fechaBuscada {@code LocalDate} que representa la fecha a buscar entre las noticias del boletín.
	 * @return Sub-boletín compuesto únicamente por las noticias que tienen la fecha introducida.
	 * @throws {@code IllegalArgumentException} en caso de que la fecha introducida sea {@code null}.
	 */
	public Boletin getSubconjuntoFecha(LocalDate fechaBuscada) {
		// TODO Comprobar que la fecha introducida no es nula. Crear un nuevo boletín y agregar los coincidentes.
		return null;
	}

	/**
	 * Obtiene un boletín de noticias que contiene únicamente las noticias con fecha entre las dos fechas introducidas por parámetros.
	 * @param inicioIntervalo {@code LocalDate} que representa la fecha que hace de extremo inferior del intervalo admitido.
	 * @param finalIntervalo {@code LocalDate} que representa la fecha que hace de extremo superior del intervalo admitido.
	 * @return Sub-boletín compuesto únicamente por las noticias con fecha en el intervalo compuesto por las dos fechas introducidas por parámetros.
	 * @throws {@code IllegalArgumentException} en caso de que alguna de las fechas introducidas por parámetro sea {@code null}.
	 */
	public Boletin getSubconjuntoIntervalo(LocalDate inicioIntervalo, LocalDate finalIntervalo) {
		// TODO Comprobar que la fechas introducida no son nulas, por separado. Crear un nuevo boletín y agregar los coincidentes.
		return null;
	}

	/**
	 * Obtiene un boletín de noticias que contiene únicamente las noticias con la misma categoría a la introducida por parámetro.
	 * @param categoriaBuscada {@code EnumCategoria} que representa la categoría a buscar.
	 * @return Sub-boletín compuesto únicamente por las noticas con categoría igual a la recibida por parámetro.
	 * @throws {@code IllegalArgumentException} en caso de que la enumeración introducida por parámetro sea {@code null}.
	 */
	public Boletin getSubconjuntoCategoria(EnumCategoria categoriaBuscada) {
		// TODO Comprobar que la categoría introducida no es nula. Crear un nuevo boletín y agregar los coincidentes.
		return null;
	}

	/**
	 * Obtiene un boletín de noticias que contiene únicamente las noticias con la misma categoría y misma fecha a las introducidas por parámetro.
	 * @param categoriaBuscada {@code EnumCategoria} que representa la categoría a buscar.
	 * @param fechaConcreta {@code LocalDate} que representa la fecha a buscar entre las noticias del boletín.
	 * @return Sub-boletín compuesto únicamente por las noticias con fecha y categoría iguales a las introducidas por parámetro.
	 * @throws {@code IllegalArgumentException} en caso de que la enumeración o la fecha introducidas por parámetro sean {@code null}.
	 */
	public Boletin getSubconjuntoCategoriaFecha(EnumCategoria categoriaBuscada, LocalDate fechaConcreta) {
		// TODO Comprobar que la fecha o la categoría introducidas no son nulas, por separado. Crear un nuevo boletín y agregar los coincidentes.
		return null;
	}

	/**
	 * Obtiene un boletín de noticias que contiene exclusivamente las noticias con la misma categoría introducida por parámetro y con fecha entre las dos fechas introducidas por parámetro.
	 * @param categoriaBuscada {@code EnumCategoria} que representa la categoría a buscar.
	 * @param inicioIntervalo {@code LocalDate} que representa la fecha que hace de extremo inferior del intervalo admitido.
	 * @param finalIntervalo {@code LocalDate} que representa la fecha que hace de extremo superior del intervalo admitido.
	 * @return Sub-boletín compuesto únicamente por las noticias con categoría igual a la introducida por parámetro y fecha contenida entre las dos fechas introducidas por parámetro.
	 * @throws {@code IllegalArgumentException} en caso de la enumeración o alguna de las fechas introducidas por parámetro sean {@code null}.
	 */
	public Boletin getSubconjuntoCategoriaIntervalo(EnumCategoria categoriaBuscada, LocalDate inicioIntervalo,
			LocalDate finalIntervalo) {
		// TODO Comprobar que las fechas o la categoría introducidas no son nulas, por separado. Crear un nuevo boletín y agregar los coincidentes.
		return null;
	}

}
