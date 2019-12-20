package es.uva.inf.tds.pr3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.easymock.Mock;
import static org.easymock.EasyMock.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr3.Boletin;
import es.uva.inf.tds.pr3.EnumCategoria;
import es.uva.inf.tds.pr3.INoticia;

public class BoletinBlackBoxSimpleAislamientoTest {

	private LocalDate fechaPublicacion;
	private EnumCategoria categoria;
	@Mock
	private INoticia in;

	private LocalDate fechaPublicacion2;
	private EnumCategoria categoria2;
	@Mock
	private INoticia in2;

	private Boletin b;

	@BeforeEach
	public void setUp() throws Exception {

		fechaPublicacion = LocalDate.of(2019, 11, 14);
		categoria = EnumCategoria.NACIONAL;

		fechaPublicacion2 = LocalDate.of(2019, 12, 14);
		categoria2 = EnumCategoria.NACIONAL;

		in = createMock(INoticia.class);
		in2 = createMock(INoticia.class);

		b = new Boletin();
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Tag("Isolation")
	@Test
	public void addNoticiaRepetida() {
		b.addNoticia(in);
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(in);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Positive")
	@Tag("Isolation")
	@Test
	public void numeroNoticiasBoletinVacio() {
		assertEquals(0, b.getNumberOfNoticias());
	}

	@Tag("BlackBoxTestFirst")
	@Tag("ArrayEquals")
	@Tag("Positive")
	@Tag("Isolation")
	@Test
	public void listaCronologicaMismaFecha() {
		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).atLeastOnce();
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion).atLeastOnce();
		replay(in);
		replay(in2);

		b.addNoticia(in);
		b.addNoticia(in2);

		ArrayList<INoticia> al = new ArrayList<>();
		al.add(in);
		al.add(in2);

		assertArrayEquals(al.toArray(), b.getChronologicalOrder().toArray());
		verify(in);
		verify(in2);
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Tag("Isolation")
	@Test
	public void subconjuntoIntervaloInicialNull() {
		LocalDate inicioIntervalo = null;
		LocalDate finalIntervalo = LocalDate.of(2019, 12, 13);

		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoIntervalo(inicioIntervalo, finalIntervalo);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("ArrayEquals")
	@Tag("Positive")
	@Tag("Isolation")
	@Test
	public void subconjuntoIntervaloIguales() {
		LocalDate int1 = LocalDate.of(2019, 11, 15);
		LocalDate int2 = LocalDate.of(2019, 12, 14);

		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).atLeastOnce();
		replay(in);
		b.addNoticia(in);

		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).atLeastOnce();
		replay(in2);
		b.addNoticia(in2);

		Boletin b2 = new Boletin();
		b2.addNoticia(in2);

		assertArrayEquals(b2.getNoticias().toArray(), b.getSubconjuntoIntervalo(int1, int2).getNoticias().toArray());

		verify(in);
		verify(in2);
	}

	@Tag("BlackBoxTestFirst")
	@Tag("ArrayEquals")
	@Tag("Positive")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaSinFecha() {

		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in.getCategoria()).andReturn(categoria).anyTimes();
		replay(in);
		b.addNoticia(in);

		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).anyTimes();
		expect(in2.getCategoria()).andReturn(categoria2).anyTimes();
		replay(in2);
		b.addNoticia(in2);

		Boletin b2 = new Boletin();
		EnumCategoria c = EnumCategoria.SOCIEDAD;
		assertArrayEquals(b2.getNoticias().toArray(),
				b.getSubconjuntoCategoriaFecha(c, fechaPublicacion).getNoticias().toArray());

		verify(in);
		verify(in2);
	}

	@Tag("BlackBoxTestFirst")
	@Tag("ArrayEquals")
	@Tag("Positive")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaIntervaloMismaFecha() {
		LocalDate int1 = LocalDate.of(2019, 11, 14);
		LocalDate int2 = LocalDate.of(2019, 11, 14);
		EnumCategoria c = EnumCategoria.NACIONAL;

		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).atLeastOnce();
		expect(in.getCategoria()).andReturn(categoria).atLeastOnce();
		replay(in);
		b.addNoticia(in);

		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).atLeastOnce();
		expect(in2.getCategoria()).andReturn(categoria2).atLeastOnce();
		replay(in2);
		b.addNoticia(in2);

		Boletin b2 = new Boletin();
		b2.addNoticia(in);

		assertArrayEquals(b2.getNoticias().toArray(),
				b.getSubconjuntoCategoriaIntervalo(c, int1, int2).getNoticias().toArray());

		verify(in);
		verify(in2);
	}

	@Tag("BlackBoxTestFirst")
	@Tag("ArrayEquals")
	@Tag("Positive")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaSinIntervalo() {
		LocalDate int1 = LocalDate.of(2010, 11, 14);
		LocalDate int2 = LocalDate.of(2012, 11, 14);
		EnumCategoria c = EnumCategoria.NACIONAL;

		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in.getCategoria()).andReturn(categoria).anyTimes();
		replay(in);
		b.addNoticia(in);

		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).anyTimes();
		expect(in2.getCategoria()).andReturn(categoria2).anyTimes();
		replay(in2);
		b.addNoticia(in2);

		Boletin b2 = new Boletin();

		assertArrayEquals(b2.getNoticias().toArray(),
				b.getSubconjuntoCategoriaIntervalo(c, int1, int2).getNoticias().toArray());

		verify(in);
		verify(in2);

	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Tag("Isolation")
	@Test
	public void subconjuntoIntervaloFinalNull() {
		LocalDate inicioIntervalo = LocalDate.of(2000, 1, 1);
		LocalDate finalIntervalo = null;

		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoIntervalo(inicioIntervalo, finalIntervalo);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaNullFecha() {
		fechaPublicacion = null;
		categoria = EnumCategoria.SOCIEDAD;
		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoCategoriaFecha(categoria, fechaPublicacion);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaFechaNull() {
		fechaPublicacion = LocalDate.of(2000, 1, 1);
		categoria = null;
		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoCategoriaFecha(categoria, fechaPublicacion);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaNullIntervalo() {
		LocalDate inicioIntervalo = LocalDate.of(2000, 1, 1);
		LocalDate finalIntervalo = LocalDate.of(2000, 1, 2);
		categoria = null;

		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoCategoriaIntervalo(categoria, inicioIntervalo, finalIntervalo);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaIntervaloNull1() {
		LocalDate inicioIntervalo = null;
		LocalDate finalIntervalo = LocalDate.of(2000, 1, 2);
		categoria = EnumCategoria.CULTURA;
		;

		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoCategoriaIntervalo(categoria, inicioIntervalo, finalIntervalo);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaIntervaloNull2() {
		LocalDate inicioIntervalo = LocalDate.of(2000, 1, 1);
		LocalDate finalIntervalo = null;
		categoria = EnumCategoria.CULTURA;
		;

		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoCategoriaIntervalo(categoria, inicioIntervalo, finalIntervalo);
		});
	}

	@AfterEach
	public void tearDown() {
		in = null;
		in2 = null;
		b = null;
	}

}
