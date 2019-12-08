package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.Mock;
import static org.easymock.EasyMock.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr2.Boletin;
import es.uva.inf.tds.pr2.EnumCategoria;
import es.uva.inf.tds.pr2.Noticia;



public class BoletinTDDFixtureSimple {

	private String titular;
	private LocalDate fechaPublicacion;
	private String fuente;
	private EnumCategoria categoria;
	private String url;
	private INoticia in;
	private Noticia n;

	private String titular2;
	private LocalDate fechaPublicacion2;
	private String fuente2;
	private EnumCategoria categoria2;
	private String url2;
	@Mock
	private INoticia in2;
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
		categoria = EnumCategoria.nacional;
		url = "https://www." + fuente + '/' + categoria + '/' + titular;
		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		

		titular2 = "Hola2";
		fechaPublicacion2 = LocalDate.of(2019, 12, 14);
		fuente2 = "Adios2";
		categoria2 = EnumCategoria.nacional;
		url2 = "https://www." + fuente2 + '/' + categoria2 + '/' + titular2;
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		
		in= createMock(INoticia.class);
		in2 = createMock(INoticia.class);

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
		assertSame(1,b.getNumberOfNoticias());
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
		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).times(2);
		replay(in);
		b.addNoticia(in);
		assertEquals(fechaPublicacion, b.getMostRecentDate());
		verify(in);
	}

	@Tag("Positive")
	@Tag("TDD")
	@Test
	public void getFechaMasAntigua() {
		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).times(2);
		replay(in);
		b.addNoticia(in);
		assertEquals(fechaPublicacion, b.getOldestDate());
		verify(in);
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Test
	public void listaCronologica() {
		
		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).atLeastOnce();
		replay(in);
		b.addNoticia(in);
		
		
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).atLeastOnce();
		replay(in2);
		b.addNoticia(in2);
		
		


		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n2);
		al.add(n);

		assertArrayEquals(al.toArray(), b.getChronologicalOrder().toArray());
		
		verify(in);
		verify(in2);
	}

	@AfterEach
	public void tearDown() {
		n = null;
		n2 = null;
		b = null;
	}
}