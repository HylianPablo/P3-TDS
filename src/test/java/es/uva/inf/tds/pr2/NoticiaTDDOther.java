package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr2.EnumCategoria;
import es.uva.inf.tds.pr2.Noticia;

public class NoticiaTDDOther {

	@Tag("Negative")
	@Tag("TDD")
	@Test
	public void testCrearNoticiaError() {
		String titular = null;
		LocalDate fechaPublicacion = null;
		String fuente = null;
		EnumCategoria categoria = null;
		String url = null;
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		});
	}

	@Tag("Positive")
	@Tag("TDD")
	@Test
	public void testCatorcePalabras() {
		String titular = "Uno dos tres cuatro cinco seis siete ocho nueve diez once doce trece catorce";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		});
	}

}
