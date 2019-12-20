package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr2.EnumCategoria;
import es.uva.inf.tds.pr2.Noticia;

public class NoticiaBlackBoxTest {

	private String titular;
	private LocalDate fechaPublicacion;
	private String fuente;
	private EnumCategoria categoria;
	private String url;
	private Noticia n;

	private String titular2;
	private LocalDate fechaPublicacion2;
	private String fuente2;
	private EnumCategoria categoria2;
	private String url2;
	private Noticia n2;

	@BeforeEach
	public void setUp() {
		titular = "Hola";
		fechaPublicacion = LocalDate.of(2019, 11, 14);
		fuente = "Adios";
		categoria = EnumCategoria.NACIONAL;
		url = "https://www." + fuente + '/' + categoria + '/' + titular;

		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		titular2 = "Hola";
		fechaPublicacion2 = LocalDate.of(2019, 11, 15);
		fuente2 = "Adios";
		categoria2 = EnumCategoria.NACIONAL;
		url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Test
	public void testCeroPalabras() {
		titular = "";
		url = "https://www." + fuente + '/' + categoria + '/' + titular;

		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Positive")
	@Test
	public void noticiaPosterior() {
		assertEquals("posterior", n.comparaFechaNoticia(n2));
	}

	@Tag("Positive")
	@Tag("BlackBoxTestFirst")
	@Test
	public void noticiaAnterior() {
		fechaPublicacion2 = LocalDate.of(2019, 11, 13);
		url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		assertEquals("anterior", n.comparaFechaNoticia(n2));
	}

	@Tag("Positive")
	@Tag("BlackBoxTestFirst")
	@Test
	public void noticiaNoSimilarCategoria() {
		fechaPublicacion2 = LocalDate.of(2019, 11, 16);
		categoria2 = EnumCategoria.INTERNACIONAL;
		url2 = "https://www." + fuente + '/' + categoria + '/' + titular;
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		assertFalse(n.isSimilar(n2));
	}

	@Tag("Positive")
	@Tag("BlackBoxTestFirst")
	@Test
	public void noticiaNoSimilarFecha() {
		fechaPublicacion2 = LocalDate.of(2019, 11, 17);
		url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		assertFalse(n.isSimilar(n2));
	}

	@Tag("Positive")
	@Tag("BlackBoxTestFirst")
	@Test
	public void noticiaNoSimilar() {
		String titular2 = "Adios";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 16);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.NACIONAL;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		assertFalse(n.isSimilar(n2));
	}

	@Tag("Positive")
	@Tag("BlackBoxTestFirst")
	@Test
	public void noticiaSimilarFecha2DiasAtras() {
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 12);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.NACIONAL;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		assertTrue(n.isSimilar(n2));
	}

}
