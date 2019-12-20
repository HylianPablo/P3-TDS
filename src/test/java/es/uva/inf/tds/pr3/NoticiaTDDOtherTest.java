package es.uva.inf.tds.pr3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr3.EnumCategoria;
import es.uva.inf.tds.pr3.Noticia;

public class NoticiaTDDOtherTest {

	@Tag("Negative")
	@Tag("TDD")
	@Test
	public void testCrearNoticiaErrorTitularNull() {
		String titular = null;
		LocalDate fechaPublicacion = LocalDate.of(2019, 1, 1);
		String fuente = "a";
		EnumCategoria categoria = EnumCategoria.NACIONAL;
		String url = "b";
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		});
	}

	@Tag("Negative")
	@Tag("TDD")
	@Test
	public void testCrearNoticiaErrorFechaNull() {
		String titular = "c";
		LocalDate fechaPublicacion = null;
		String fuente = "a";
		EnumCategoria categoria = EnumCategoria.NACIONAL;
		String url = "b";
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		});
	}

	@Tag("Negative")
	@Tag("TDD")
	@Test
	public void testCrearNoticiaErrorFuenteNull() {
		String titular = "c";
		LocalDate fechaPublicacion = LocalDate.of(2019, 1, 1);
		String fuente = null;
		EnumCategoria categoria = EnumCategoria.NACIONAL;
		String url = "b";
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		});
	}

	@Tag("Negative")
	@Tag("TDD")
	@Test
	public void testCrearNoticiaErrorCategoriaNull() {
		String titular = "c";
		LocalDate fechaPublicacion = LocalDate.of(2019, 1, 1);
		String fuente = "a";
		EnumCategoria categoria = null;
		String url = "b";
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		});
	}

	@Tag("Negative")
	@Tag("TDD")
	@Test
	public void testCrearNoticiaErrorURLNull() {
		String titular = "c";
		LocalDate fechaPublicacion = LocalDate.of(2019, 1, 1);
		String fuente = "a";
		EnumCategoria categoria = EnumCategoria.NACIONAL;
		String url = null;
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		});
	}

	@Tag("Positive")
	@Tag("TDD")
	@Test
	public void testTrecePalabras() {
		String titular = "Uno dos tres cuatro cinco seis siete ocho nueve diez once doce trece";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.NACIONAL;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		});
	}

}
