package es.uva.inf.tds.pr3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr3.EnumCategoria;
import es.uva.inf.tds.pr3.Noticia;

public class NoticiaSequenceTest {

	@Tag("Sequence")
	@Test
	public void secuenciaNormal() {
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.NACIONAL;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		LocalDate fecha2 = LocalDate.of(2019, 11, 12);
		Noticia n2 = new Noticia(titular, fecha2, "elmundo.es", url, categoria);

		boolean b = n.isSimilar(n2);
		assertTrue(b);
		String s = n.comparaFechaNoticia(n2);
		assertEquals("anterior", s);

		assertNotNull(n);
		assertEquals(titular, n.getTitular());
		assertEquals(fechaPublicacion, n.getFechaPublicacion());
		assertEquals(fuente, n.getFuente());
		assertEquals(categoria, n.getCategoria());
		assertEquals(url, n.getUrl());
	}
}
