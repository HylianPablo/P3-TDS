package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class NoticiaTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testCrearNoticia() {
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		fail("Not yet implemented");
		assertNotNull(n);
		assertEquals(titular, n.getTitular());
		assertEquals(fechaPublicacion, n.getFechaPublicacion());
		//assertEquals(n.getFuente());
	//	assertEquals(n.getUrl());
	//	assertEquals(n.getCategoria());
	}

	@Test
	public void testCrearNoticiaError() {
		String titular = null;
		LocalDate fechaPublicacion = null;
		String fuente = null;
		EnumCategoria categoria = null;
		String url = null;
		
		/*assertsThrows(IllegalArgumentException.class, ()-> {Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria)});
		
		assertNotNull(n.getTitular());
		assertNotNull(n.getFechaPublicacion());
		assertNotNull(n.getFuente());
		assertNotNull(n.getUrl());
		assertNotNull(n.getCategoria());*/
	}
}
