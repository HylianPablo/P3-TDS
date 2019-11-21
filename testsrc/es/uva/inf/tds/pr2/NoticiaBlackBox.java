package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoticiaBlackBox {
	
	private String titular;
	private LocalDate fechaPublicacion;
	private String fuente;
	private EnumCategoria categoria;
	private String url;
	private Noticia n;
	
	@BeforeEach
	public void setUp() {
		titular = "Hola";
		fechaPublicacion = LocalDate.of(2019, 11, 14);
		fuente = "Adios";
		categoria = EnumCategoria.nacional;
		url = "https://www." + fuente + '/' + categoria + '/' + titular;

		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
	}

	@Test
	public void testCeroPalabras() {
		String titular="";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;
		
		assertThrows(IllegalArgumentException.class, () -> {@SuppressWarnings("unused")
		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);});
	}
	
	@Test
	public void noticiaPosterior() {
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 15);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		
		assertEquals("posterior",n.comparaFechaNoticia(n2));
	}
	
	@Test
	public void noticiaAnterior() {
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 13);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		
		assertEquals("anterior",n.comparaFechaNoticia(n2));
	}
	
	@Test
	public void noticiaNoSimilarCategoria() {
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 16);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.internacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
	
		assertFalse(n.isSimilar(n2));
		fail("Until GREEN Phase");
	}
	
	@Test
	public void noticiaNoSimilarFecha() {		
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 17);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
	
		assertFalse(n.isSimilar(n2));
		fail("Until GREEN Phase");
	}

}
