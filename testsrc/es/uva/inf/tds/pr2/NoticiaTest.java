package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class NoticiaTest {

	@Test
	public void testCrearNoticia() {
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		assertNotNull(n);
		assertEquals(titular, n.getTitular());
		assertEquals(fechaPublicacion, n.getFechaPublicacion());
		assertEquals(fuente,n.getFuente());
		assertEquals(categoria,n.getCategoria());
		assertEquals(url,n.getUrl());
	}

	@Test
	public void testCrearNoticiaError() {
		String titular = null;
		LocalDate fechaPublicacion = null;
		String fuente = null;
		EnumCategoria categoria = null;
		String url = null;
		assertThrows(IllegalArgumentException.class, () -> {@SuppressWarnings("unused")
		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);});
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
	public void testCatorcePalabras() {
		String titular="Uno dos tres cuatro cinco seis siete ocho nueve diez once doce trece catorce";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;
		
		assertThrows(IllegalArgumentException.class, () -> {@SuppressWarnings("unused")
		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);});
	}
	
	@Test
	public void noticiaAnterior() {
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 13);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		
		assertEquals("anterior",n.comparaFechaNoticia(n2));
	}
	
	@Test
	public void noticiaIgual() {
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 14);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		
		assertEquals("igual",n.comparaFechaNoticia(n2));
	}
	
	@Test
	public void noticiaPosterior() {
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 15);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		
		assertEquals("posterior",n.comparaFechaNoticia(n2));
	}
	
	@Test
	public void noticiaSimilar() {
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		String titular2 = "Hola";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 16);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
	
		assertTrue(n.isSimilar(n2));
	}
	
	@Test
	public void noticiaNoSimilarTitular() {
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		String titular2 = "Adios";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 11, 16);
		String fuente2 = "Adios";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
	
		assertFalse(n.isSimilar(n2));
		fail("Until GREEN Phase");
	}
	
	@Test
	public void noticiaNoSimilarCategoria() {
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
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
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
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
