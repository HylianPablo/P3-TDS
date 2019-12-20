package es.uva.inf.tds.pr3;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.Mock;
import static org.easymock.EasyMock.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr3.Boletin;
import es.uva.inf.tds.pr3.INoticia;
import es.uva.inf.tds.pr3.Noticia;

public class BoletinTDDFixtureSimpleAislamientoTest {

	private LocalDate fechaPublicacion;
	private INoticia in;

	private LocalDate fechaPublicacion2;
	@Mock
	private INoticia in2;
	private Boletin b;

	@Tag("Positive")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void createEmptyBoletin() {
		Boletin b = new Boletin();
		assertNotNull(b);
	}

	@BeforeEach
	public void setUp() throws Exception {

		fechaPublicacion = LocalDate.of(2019, 11, 14);

		fechaPublicacion2 = LocalDate.of(2019, 12, 14);

		in = createMock(INoticia.class);
		in2 = createMock(INoticia.class);

		b = new Boletin();
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void createNotEmptyBoletin() {
		ArrayList<INoticia> al = new ArrayList<>();
		al.add(in);

		b = new Boletin(al);
		assertNotNull(b);
		assertArrayEquals(al.toArray(), b.getNoticias().toArray());
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void addNoticia() {
		b.addNoticia(in);
		ArrayList<INoticia> al = new ArrayList<>();
		al.add(in);
		assertArrayEquals(al.toArray(), b.getNoticias().toArray());
		assertSame(1, b.getNumberOfNoticias());
	}

	@Tag("Negative")
	@Tag("TDD")
	@Tag("Isolation")
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
	@Tag("Isolation")
	@Test
	public void numeroNoticias() {
		b.addNoticia(in);
		assertSame(1, b.getNumberOfNoticias());
	}

	@Tag("Positive")
	@Tag("TDD")
	@Test
	public void getFechaMasReciente() {
		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).times(2);
		replay(in);
		b.addNoticia(in);
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).times(2);
		replay(in2);
		b.addNoticia(in2);
		assertEquals(fechaPublicacion2, b.getMostRecentDate());
		verify(in);
		verify(in2);
	}

	@Tag("Positive")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void getFechaMasAntigua() {
		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).times(2);
		replay(in);
		b.addNoticia(in);
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).times(1);
		replay(in2);
		b.addNoticia(in2);
		assertEquals(fechaPublicacion, b.getOldestDate());
		verify(in);
		verify(in2);
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void listaCronologica() {

		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).atLeastOnce();
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).atLeastOnce();
		replay(in);
		replay(in2);

		b.addNoticia(in2);
		b.addNoticia(in);

		ArrayList<INoticia> al = new ArrayList<>();
		al.add(in);
		al.add(in2);

		assertArrayEquals(al.toArray(), b.getChronologicalOrder().toArray());

		verify(in);
		verify(in2);
	}

	@Tag("Positive")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void gradoSimilitud() {

		expect(in.isSimilar(in)).andReturn(true).anyTimes();
		replay(in);
		b.addNoticia(in);

		expect(in2.isSimilar(in)).andReturn(false).atLeastOnce();
		replay(in2);
		b.addNoticia(in2);

		ArrayList<INoticia> al = new ArrayList<>();
		al.add(in2);
		al.add(in);

		Boletin b2 = new Boletin();
		b2.addNoticia(in);

		assertEquals(50.0, b.getGradoSimilitud(b2));
		verify(in);
		verify(in2);
	}

	@Tag("Negative")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void gradoSimilitudNulo() {
		Boletin b2 = null;

		assertThrows(IllegalArgumentException.class, () -> {
			b.getGradoSimilitud(b2);
		});
	}

	@AfterEach
	public void tearDown() {
		in = null;
		in2 = null;
		b = null;
	}
}