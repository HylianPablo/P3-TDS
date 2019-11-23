package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoletinTestFirstFixtureSimple {

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

	@Test
	public void createEmptyBoletin() {
		Boletin b = new Boletin();
		assertNotNull(b);
		fail("Until GREEN Phase");
	}

	@BeforeEach
	public void setUp() throws Exception {

		titular = "Hola";
		fechaPublicacion = LocalDate.of(2019, 11, 14);
		fuente = "Adios";
		categoria = EnumCategoria.nacional;
		url = "https://www." + fuente + '/' + categoria + '/' + titular;
		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		titular2 = "Hola2";
		fechaPublicacion2 = LocalDate.of(2019, 12, 14);
		fuente2 = "Adios2";
		categoria2 = EnumCategoria.nacional;
		url2 = "https://www." + fuente2 + '/' + categoria2 + '/' + titular2;
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		
		b=new Boletin();
	}

	@Test
	public void createNotEmptyBoletin() {
		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);

		b = new Boletin(al);
		assertNotNull(b);
		assertEquals(al, b.getNoticias());
	}

	@Test
	public void addNoticia() {
		b.addNoticia(n);
		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		assertEquals(al, b.getNoticias());
	}

	@Test
	public void addNoticiaNula() {
		Noticia n2=null;
		assertThrows(IllegalArgumentException.class, () -> {b.addNoticia(n2);;});
	}

	@Test
	public void numeroNoticias() {
		b.addNoticia(n);
		assertSame(1, b.getNumberOfNoticias());
	}

	@Test
	public void getFechaMasReciente() {
		b.addNoticia(n);
		b.addNoticia(n2);

		assertEquals(fechaPublicacion, b.getMostRecentDate());
	}

	@Test
	public void getFechaMasAntigua() {
		b.addNoticia(n);
		b.addNoticia(n2);

		assertEquals(fechaPublicacion2, b.getOldestDate());
	}

	@Test
	public void listaCronologica() {
		b.addNoticia(n);
		b.addNoticia(n2);

		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n2);
		al.add(n);

		assertEquals(al, b.getChronologicalOrder());
	}

	@AfterEach
	public void tearDown() {
		n = null;
		n2 = null;
		b=null;
	}

}