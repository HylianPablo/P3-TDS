package es.uva.inf.tds.pr2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

	private String titular;
	private LocalDate fechaPublicacion;
	private String fuente;
	private String url;
	private EnumCategoria categoria;

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
	 * @throws {@code IllegalArgumentException} en caso de que el titular sea {@code null}.   
	 * @throws {@code IllegalArgumentException} en caso de que la fecha de publicación sea {@code null}. 
	 * @throws {@code IllegalArgumentException} en caso de que la fuente sea {@code null}. 
	 * @throws {@code IllegalArgumentException} en caso de que la url sea {@code null}. 
	 * @throws {@code IllegalArgumentException} en caso de que la categoría sea {@code null}.             
	 */
	public Noticia(String titular, LocalDate fechaPublicacion, String fuente, String url, EnumCategoria categoria) {
		if(titular==null) {
			throw new IllegalArgumentException();
		}
		if(fechaPublicacion==null) {
			throw new IllegalArgumentException();
		}
		if(fuente==null) {
			throw new IllegalArgumentException();
		}
		if(url==null) {
			throw new IllegalArgumentException();
		}
		if(categoria==null) {
			throw new IllegalArgumentException();
		}
		String[] countWords=titular.split(" ");
		if(countWords.length>12 || titular.equals(""))
			throw new IllegalArgumentException();
		this.titular = titular;
		this.fechaPublicacion = fechaPublicacion;
		this.fuente = fuente;
		this.url = url;
		this.categoria = categoria;
	}

	/**
	 * Devuelve el titular de la noticia.
	 * 
	 * @return Cadena de caracteres que representa el titular de la noticia.
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Devuelve la fecha de publicación de la noticia.
	 * 
	 * @return {@code Localdate} que representa la fecha de publicación de la
	 *         noticia.
	 */
	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * Devuelve la fuente de la noticia.
	 * 
	 * @return Cadena de caracteres que representa la fuente de la noticia.
	 */
	public String getFuente() {
		return fuente;
	}

	/**
	 * Devuelve la URL de la noticia.
	 * 
	 * @return Cadena de caracteres que representa la URL de la noticia.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Devuelve la categoría a la que pertenece la noticia.
	 * 
	 * @return Tipo enumerado que representa la categoría de la noticia.
	 */
	public EnumCategoria getCategoria() {
		return categoria;
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
		if (n == null) {
			throw new IllegalArgumentException();
		}
		if (fechaPublicacion.compareTo(n.getFechaPublicacion()) < 0) {
			return "posterior";
		} else if (fechaPublicacion.compareTo(n.getFechaPublicacion()) > 0) {
			return "anterior";
		}
		return "igual";
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
		if (n == null) {
			throw new IllegalArgumentException();
		}
		if (Math.abs(fechaPublicacion.until(n.getFechaPublicacion(),ChronoUnit.DAYS)) > 2)
			return false;

		if (!titular.equals(n.getTitular()))
			return false;

		return (categoria.equals(n.getCategoria()));
		
	}

}
