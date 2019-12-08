package es.uva.inf.tds.pr2;

import java.time.LocalDate;

/**
 * Clase que representa una noticia. Una noticia está compuesta por un titular,
 * una fecha de publicación <br>
 * una fuente, una categoría y una url que hace referencia a su contenido. Puede
 * compararse con otras <br>
 * noticias en base a sus propiedades anteriormente mencionadas.
 * 
 * @author pamarti
 * @author migusan
 *
 */
public class Noticia implements INoticia {

	/**
	 * Creación de una noticia a partir de un titular, una fecha de publicación, una
	 * fuente, una categoría y una URL.
	 * 
	 * @param titular          Cadena de caracteres que representa el titular de la
	 *                         noticia.
	 * @param fechaPublicacion {@code LocalDate} que representa la fecha de
	 *                         publicación de la noticia.
	 * @param fuente           Cadena de caracteres que representa la fuente de la
	 *                         noticia.
	 * @param url              Cadena de caracteres que representa la URL de la
	 *                         noticia.
	 * @param categoria        Tipo enumerado que representa la categoría de la
	 *                         noticia.
	 * @throws {@code IllegalArgumentException} en caso de que el titular tenga más
	 *                de 12 palabras o no tenga ninguna.
	 */
	public Noticia(String titular, LocalDate fechaPublicacion, String fuente, String url, EnumCategoria categoria) {
		// TODO Asignar parámetros a atributos.
	}

	/**
	 * Devuelve el titular de la noticia.
	 * 
	 * @return Cadena de caracteres que representa el titular de la noticia.
	 */
	public String getTitular() {
		// TODO Devolver atributo titular.
		return null;
	}

	/**
	 * Devuelve la fecha de publicación de la noticia.
	 * 
	 * @return {@code Localdate} que representa la fecha de publicación de la
	 *         noticia.
	 */
	public LocalDate getFechaPublicacion() {
		// TODO Devolver atributo fecha de publicación.
		return null;
	}

	/**
	 * Devuelve la fuente de la noticia.
	 * 
	 * @return Cadena de caracteres que representa la fuente de la noticia.
	 */
	public String getFuente() {
		// TODO Devolver atributo fuente.
		return null;
	}

	/**
	 * Devuelve la URL de la noticia.
	 * 
	 * @return Cadena de caracteres que representa la URL de la noticia.
	 */
	public String getUrl() {
		// TODO Devolver atributo url.
		return null;
	}

	/**
	 * Devuelve la categoría a la que pertenece la noticia.
	 * 
	 * @return Tipo enumerado que representa la categoría de la noticia.
	 */
	public EnumCategoria getCategoria() {
		// TODO Devolver atributo categoria.
		return null;
	}

	/**
	 * Compara las fechas de publicación entre la noticia recibida por parámetro y
	 * el objeto.
	 * 
	 * @param n Noticia a comparar la fecha.
	 * @return Cadena de caracteres que representa el resultado de la comparación y
	 *         varía entre {@code "anterior"} si la fecha por parámetro es menor,
	 *         {@code "igual"} si es igual y {@code "posterior"} si es mayor.
	 * @throws {@code IllegalArgumentException} en caso de que la noticia recibida
	 *                por parámetro sea {@code null}.
	 */
	public String comparaFechaNoticia(Noticia n) {
		// TODO comparar ambos campos de fecha de las noticias con métodos de LocalDate.
		return null;
	}

	/**
	 * Comprueba si la noticia recibida por parámetro y el objeto son similares. Dos
	 * noticias son similares si comparten titular y categoría y su fecha no se
	 * diferencia en más de dos días.
	 * 
	 * @param n Noticia a comprobar si es similar.
	 * @return {@code True} en caso de ser similares y {@code false} en caso
	 *         contrario.
	 * @throws {@code IllegalArgumentException} en caso de que la noticia recibida
	 *                por parámetro sea {@code null}.
	 */
	public boolean isSimilar(INoticia n) {
		// TODO Comprobar los campos requeridos. Devolver false si uno de los campos
		// falla. En otro caso devolver true.
		return false;
	}

}
