package es.uva.inf.tds.pr3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr3.Boletin;
import es.uva.inf.tds.pr3.EnumCategoria;
import es.uva.inf.tds.pr3.INoticia;
import es.uva.inf.tds.pr3.Noticia;

public class BoletinTDDFixtureSimpleAislamientoNoTest {

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
	private Boletin b;

	@Tag("Positive")
	@Tag("TDD")
	@Test
	public void createEmptyBoletin() {
		Boletin b = new Boletin();
		assertNotNull(b);
	}

	@BeforeEach
	public void setUp() throws Exception {

		titular = "Hola";
		fechaPublicacion = LocalDate.of(2019, 11, 14);
		fuente = "Adios";
		categoria = EnumCategoria.NACIONAL;
		url = "https://www." + fuente + '/' + categoria + '/' + titular;
		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		titular2 = "Hola2";
		fechaPublicacion2 = LocalDate.of(2019, 12, 14);
		fuente2 = "Adios2";
		categoria2 = EnumCategoria.NACIONAL;
		url2 = "https://www." + fuente2 + '/' + categoria2 + '/' + titular2;
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		b = new Boletin();
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Test
	public void createNotEmptyBoletin() {
		ArrayList<INoticia> al = new ArrayList<>();
		al.add(n);

		b = new Boletin(al);
		assertNotNull(b);
		assertArrayEquals(al.toArray(), b.getNoticias().toArray());
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Test
	public void addNoticia() {
		b.addNoticia(n);
		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		assertArrayEquals(al.toArray(), b.getNoticias().toArray());
	}

	@Tag("Negative")
	@Tag("TDD")
	@Test
	public void addNoticiaNula() {
		Noticia n2 = null;
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(n2);
			;
		});
	}

	@Tag("Positive")
	@Tag("TDD")
	@Test
	public void numeroNoticias() {
		b.addNoticia(n);
		assertSame(1, b.getNumberOfNoticias());
	}

	@Tag("Positive")
	@Tag("TDD")
	@Test
	public void getFechaMasReciente() {
		b.addNoticia(n);
		b.addNoticia(n2);

		assertEquals(fechaPublicacion2, b.getMostRecentDate());
	}

	@Tag("Positive")
	@Tag("TDD")
	@Test
	public void getFechaMasAntigua() {
		b.addNoticia(n);
		b.addNoticia(n2);

		assertEquals(fechaPublicacion, b.getOldestDate());
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Test
	public void listaCronologica() {
		b.addNoticia(n2);
		b.addNoticia(n);

		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		al.add(n2);

		assertArrayEquals(al.toArray(), b.getChronologicalOrder().toArray());
	}

	@AfterEach
	public void tearDown() {
		n = null;
		n2 = null;
		b = null;
	}
}
