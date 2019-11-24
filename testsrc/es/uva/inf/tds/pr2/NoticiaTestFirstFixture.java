package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoticiaTestFirstFixture {

	private String titular;
	private LocalDate fechaPublicacion;
	private String fuente;
	private EnumCategoria categoria;
	private String url;
	private Noticia n;

	@BeforeEach
	public void setUp() throws Exception {
		titular = "Hola";
		fechaPublicacion = LocalDate.of(2019, 11, 14);
		fuente = "Adios";
		categoria = EnumCategoria.nacional;
		url = "https://www." + fuente + '/' + categoria + '/' + titular;

		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
	}

	@Category(TDD.class)
	@Test
	public void testCrearNoticia() {
		assertNotNull(n);
		assertEquals(titular, n.getTitular());
		assertEquals(fechaPublicacion, n.getFechaPublicacion());
		assertEquals(fuente, n.getFuente());
		assertEquals(categoria, n.getCategoria());
		assertEquals(url, n.getUrl());
	}

	@Category(BlackBoxTestFirst.class)
	@Test
	public void comparaFechaNull() {
		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		Noticia n2 = null;

		assertThrows(IllegalArgumentException.class, () -> {
			n.comparaFechaNoticia(n2);
		});
	}

	@Category(TDD.class)
	@Test
	public void noticiaIgual() {
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 14);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		assertEquals("igual", n.comparaFechaNoticia(n2));
	}

	@Category(TDD.class)
	@Test
	public void noticiaSimilar() {
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 16);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		assertTrue(n.isSimilar(n2));
	}

	@Category(BlackBoxTestFirst.class)
	@Test
	public void noticiaNoSimilar() { // Titular
		String titular2 = "Adios";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 16);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		assertFalse(n.isSimilar(n2));
		fail("Until GREEN Phase");
	}

	@Category(BlackBoxTestFirst.class)
	@Test
	public void noticiaNulaSimilar() {
		Noticia n2 = null;

		assertThrows(IllegalArgumentException.class, () -> {
			n.isSimilar(n2);
		});
	}

	@AfterEach
	public void tearDown() {
		n = null;
	}

}
