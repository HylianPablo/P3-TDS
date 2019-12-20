package es.uva.inf.tds.pr3;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
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
import es.uva.inf.tds.pr3.EnumCategoria;
import es.uva.inf.tds.pr3.INoticia;

public class BoletinTDDFixtureVariousAislamientoTest {

	private LocalDate fechaPublicacion;
	private EnumCategoria categoria;
	@Mock
	private INoticia in;

	private LocalDate fechaPublicacion2;
	private EnumCategoria categoria2;
	@Mock
	private INoticia in2;

	private LocalDate fechaPublicacion3;
	private EnumCategoria categoria3;
	@Mock
	private INoticia in3;

	@BeforeEach
	public void setUpCategorias() {
		fechaPublicacion = LocalDate.of(2019, 11, 14);
		categoria = EnumCategoria.NACIONAL;

		fechaPublicacion2 = LocalDate.of(2019, 11, 15);
		categoria2 = EnumCategoria.INTERNACIONAL;

		fechaPublicacion3 = LocalDate.of(2019, 11, 16);
		categoria3 = EnumCategoria.SOCIEDAD;

		in = createMock(INoticia.class);
		in2 = createMock(INoticia.class);
		in3 = createMock(INoticia.class);
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void listaCategorias() {
		Boletin b = new Boletin();

		expect(in.getCategoria()).andReturn(categoria).anyTimes();
		expect(in2.getCategoria()).andReturn(categoria2).anyTimes();
		expect(in3.getCategoria()).andReturn(categoria3).anyTimes();
		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).anyTimes();
		expect(in3.getFechaPublicacion()).andReturn(fechaPublicacion3).anyTimes();
		replay(in);
		replay(in2);
		replay(in3);

		b.addNoticia(in);
		b.addNoticia(in3);
		b.addNoticia(in2);

		ArrayList<INoticia> al = new ArrayList<>();
		al.add(in);
		al.add(in2);
		al.add(in3);

		assertArrayEquals(al.toArray(), b.getNewsByCategory().toArray());
		verify(in);
		verify(in2);
		verify(in3);
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void noticiasSimilares() {
		Boletin b = new Boletin();

		b.addNoticia(in);
		b.addNoticia(in2);
		b.addNoticia(in3);

		expect(in2.isSimilar(in)).andReturn(true).anyTimes();
		expect(in3.isSimilar(in)).andReturn(false).anyTimes();
		replay(in2);
		replay(in3);

		ArrayList<INoticia> al = new ArrayList<>();
		al.add(in2);

		assertArrayEquals(al.toArray(), b.getSimilarNews(in).toArray());
		verify(in2);
	}

	@Tag("Negative")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void noticiasSimilaresANula() {
		Boletin b = new Boletin();
		in2 = null;
		assertThrows(IllegalArgumentException.class, () -> {
			b.getSimilarNews(in2);
		});
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void subconjuntoFecha() {
		Boletin todas = new Boletin();
		LocalDate fechaBuscada = LocalDate.of(2019, 10, 21);

		fechaPublicacion = fechaBuscada;
		fechaPublicacion2 = LocalDate.of(2019, 10, 22);

		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).times(1);
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).times(1);
		replay(in);
		replay(in2);

		todas.addNoticia(in);
		todas.addNoticia(in2);

		Boletin validas = new Boletin();
		validas.addNoticia(in);

		assertArrayEquals(validas.getNoticias().toArray(),
				todas.getSubconjuntoFecha(fechaBuscada).getNoticias().toArray());
		verify(in);
		verify(in2);
	}

	@Tag("Negative")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void subconjuntoFechaNula() {
		Boletin b = new Boletin();
		fechaPublicacion = null;
		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoFecha(fechaPublicacion);
		});
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void subconjuntoIntervalo() {
		Boletin todas = new Boletin();

		LocalDate inicioIntervalo = LocalDate.of(2019, 1, 1);
		LocalDate finalIntervalo = LocalDate.of(2019, 12, 31);

		fechaPublicacion = LocalDate.of(2019, 1, 1);
		fechaPublicacion2 = LocalDate.of(2018, 1, 1);
		fechaPublicacion3 = LocalDate.of(2019, 12, 31);

		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).anyTimes();
		expect(in3.getFechaPublicacion()).andReturn(fechaPublicacion3).anyTimes();
		replay(in);
		replay(in2);
		replay(in3);

		todas.addNoticia(in);
		todas.addNoticia(in2);
		todas.addNoticia(in3);

		Boletin validas = new Boletin();
		validas.addNoticia(in);
		validas.addNoticia(in3);

		assertArrayEquals(validas.getNoticias().toArray(),
				todas.getSubconjuntoIntervalo(inicioIntervalo, finalIntervalo).getNoticias().toArray());
		verify(in);
		verify(in2);
		verify(in3);
	}

	@Tag("Negative")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void subconjuntoIntervalosNull() {
		LocalDate inicioIntervalo = null;
		LocalDate finalIntervalo = null;
		Boletin b = new Boletin();

		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoIntervalo(inicioIntervalo, finalIntervalo);
		});
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoria() {
		Boletin todas = new Boletin();

		EnumCategoria categoriaBuscada = EnumCategoria.NACIONAL;

		expect(in.getCategoria()).andReturn(categoria).anyTimes();
		expect(in2.getCategoria()).andReturn(categoria2).anyTimes();
		replay(in);
		replay(in2);

		todas.addNoticia(in);
		todas.addNoticia(in2);

		Boletin validas = new Boletin();
		validas.addNoticia(in);

		assertArrayEquals(validas.getNoticias().toArray(),
				todas.getSubconjuntoCategoria(categoriaBuscada).getNoticias().toArray());
		verify(in);
		verify(in2);
	}

	@Tag("Negative")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaNull() {
		categoria3 = null;
		Boletin b = new Boletin();
		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoCategoria(categoria3);
		});
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaFecha() {
		Boletin todas = new Boletin();

		LocalDate fechaConcreta = LocalDate.of(2019, 11, 17);

		EnumCategoria categoriaBuscada = EnumCategoria.NACIONAL;

		fechaPublicacion = LocalDate.of(2019, 11, 17);
		fechaPublicacion2 = LocalDate.of(2019, 1, 1);

		expect(in.getCategoria()).andReturn(categoria).anyTimes();
		expect(in2.getCategoria()).andReturn(categoria2).anyTimes();
		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).anyTimes();

		replay(in);
		replay(in2);

		todas.addNoticia(in);
		todas.addNoticia(in2);

		Boletin validas = new Boletin();
		validas.addNoticia(in);

		assertArrayEquals(validas.getNoticias().toArray(),
				todas.getSubconjuntoCategoriaFecha(categoriaBuscada, fechaConcreta).getNoticias().toArray());
		verify(in);
		verify(in2);
	}

	@Tag("Negative")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaFechaNulls() {
		fechaPublicacion = null;
		categoria = null;
		Boletin b = new Boletin();
		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoCategoriaFecha(categoria, fechaPublicacion);
		});

	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaIntervalo() {
		Boletin todas = new Boletin();

		LocalDate inicioIntervalo = LocalDate.of(2019, 1, 1);
		LocalDate finalIntervalo = LocalDate.of(2019, 12, 31);

		EnumCategoria categoriaBuscada = EnumCategoria.NACIONAL;

		fechaPublicacion = LocalDate.of(2019, 1, 1);
		fechaPublicacion2 = LocalDate.of(2018, 1, 1);
		fechaPublicacion3 = LocalDate.of(2019, 12, 31);

		expect(in.getCategoria()).andReturn(categoria).anyTimes();
		expect(in2.getCategoria()).andReturn(categoria2).anyTimes();
		expect(in3.getCategoria()).andReturn(categoria).anyTimes();
		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).anyTimes();
		expect(in3.getFechaPublicacion()).andReturn(fechaPublicacion3).anyTimes();

		replay(in);
		replay(in2);
		replay(in3);

		todas.addNoticia(in);
		todas.addNoticia(in2);
		todas.addNoticia(in3);

		Boletin validas = new Boletin();
		validas.addNoticia(in);
		validas.addNoticia(in3);

		assertArrayEquals(validas.getNoticias().toArray(),
				todas.getSubconjuntoCategoriaIntervalo(categoriaBuscada, inicioIntervalo, finalIntervalo).getNoticias()
						.toArray());
	}

	@Tag("Negative")
	@Tag("TDD")
	@Tag("Isolation")
	@Test
	public void subconjuntoCategoriaIntervaloNulls() {
		LocalDate inicioIntervalo = null;
		LocalDate finalIntervalo = null;
		categoria = null;
		Boletin b = new Boletin();

		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoCategoriaIntervalo(categoria, inicioIntervalo, finalIntervalo);
		});
	}

	@AfterEach
	public void tearDown() {
		in = null;
		in2 = null;
		in3 = null;
	}

}
