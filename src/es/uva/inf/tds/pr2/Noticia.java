package es.uva.inf.tds.pr2;

import java.time.LocalDate;

/**
 * Clase que representa una noticia. COMPLETAR
 * @author pamarti migusan
 *
 */
public class Noticia {
	
	/**
	 * Creación de una noticia a partir de un titular, una fecha de publicación, una fuente, una categoría y una URL.
	 * @param titular Cadena de caracteres que representa el titular de la noticia.
	 * @param fechaPublicacion {@code LocalDate} que representa la fecha de publicación de la noticia.
	 * @param fuente Cadena de caracteres que representa la fuente de la noticia.
	 * @param url Cadena de caracteres que representa la URL de la noticia.
	 * @param categoria Tipo enumerado que representa la categoría de la noticia.
	 */
	public Noticia(String titular, LocalDate fechaPublicacion, String fuente, String url, EnumCategoria categoria) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Devuelve el titular de la noticia.
	 * @return Cadena de caracteres que representa el titular de la noticia.
	 */
	public String getTitular() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Devuelve la fecha de publicación de la noticia.
	 * @return {@code Localdate} que representa la fecha de publicación de la noticia.
	 */
	public LocalDate getFechaPublicacion() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Devuelve la fuente de la noticia.
	 * @return Cadena de caracteres que representa la fuente de la noticia.
	 */
	public String getFuente() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Devuelve la URL de la noticia.
	 * @return Cadena de caracteres que representa la URL de la noticia.
	 */
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Devuelve la categoría a la que pertenece la noticia.
	 * @return Tipo enumerado que representa la categoría de la noticia.
	 */
	public EnumCategoria getCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Compara las fechas de publicación entre la noticia recibida por parámetro y el objeto.
	 * @param n Noticia a comparar la fecha.
	 * @return Cadena de caracteres que representa el resultado de la comparación y varía entre {@code "anterior"} si la fecha por parámetro es menor, {@code "igual"} si es igual y {@code "posterior"} si es mayor.
	 */
	public String comparaFechaNoticia(Noticia n) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Comprueba si la noticia recibida por parámetro y el objeto son similares. Dos noticias son similares si comparten titular y categoría y su fecha no se diferencia en más de dos días.
	 * @param n Noticia a comprobar si es similar.
	 * @return {@code True} en caso de ser similares y {@code false} en caso contrario.
	 */
	public boolean isSimilar(Noticia n) {
		// TODO Auto-generated method stub
		return false;
	}

}
