package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;

public class NoticiaTestFirstOther {

	@Category(BlackBoxTestFirst.class)
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

	@Category(BlackBoxTestFirst.class)
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
