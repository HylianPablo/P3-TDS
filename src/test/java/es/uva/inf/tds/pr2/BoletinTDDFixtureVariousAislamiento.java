package es.uva.inf.tds.pr2;

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

import es.uva.inf.tds.pr2.Boletin;
import es.uva.inf.tds.pr2.EnumCategoria;
import es.uva.inf.tds.pr2.Noticia;

public class BoletinTDDFixtureVariousAislamiento {

	private String titular;
	private LocalDate fechaPublicacion;
	private String fuente;
	private EnumCategoria categoria;
	private String url;
	private Noticia n;
	private INoticia in;

	private String titular2;
	private LocalDate fechaPublicacion2;
	private String fuente2;
	private EnumCategoria categoria2;
	private String url2;
	private Noticia n2;
	private INoticia in2;

	private String titular3;
	private LocalDate fechaPublicacion3;
	private String fuente3;
	private EnumCategoria categoria3;
	private String url3;
	private Noticia n3;
	private INoticia in3;

	@BeforeEach
	public void setUpCategorias() {
		titular = "Hola";
		fechaPublicacion = LocalDate.of(2019, 11, 14);
		fuente = "Adios";
		categoria = EnumCategoria.nacional;
		url = "https://www." + fuente + '/' + categoria + '/' + titular;
		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		titular2 = "Hola2";
		fechaPublicacion2 = LocalDate.of(2019, 11, 15);
		fuente2 = "Adios2";
		categoria2 = EnumCategoria.internacional;
		url2 = "https://www." + fuente2 + '/' + categoria2 + '/' + titular2;
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		titular3 = "Hola3";
		fechaPublicacion3 = LocalDate.of(2019, 11, 16);
		fuente3 = "Adios3";
		categoria3 = EnumCategoria.sociedad;
		url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular3;
		n3 = new Noticia(titular3, fechaPublicacion3, fuente3, url3, categoria3);

		in = createMock(INoticia.class);
		in2 = createMock(INoticia.class);
		in3 = createMock(INoticia.class);
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
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
	@Test
	public void noticiasSimilares() {
		Boletin b = new Boletin();

		n2 = new Noticia(titular, fechaPublicacion2, fuente2, url2, categoria);
		n3 = new Noticia(titular, LocalDate.of(2100, 1, 1), fuente3, url3, categoria);

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
	@Test
	public void noticiasSimilaresANula() {
		Boletin b = new Boletin();
		n2 = null;
		assertThrows(IllegalArgumentException.class, () -> {
			b.getSimilarNews(n2);
		});
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
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
	@Test
	public void subconjuntoCategoria() {
		Boletin todas = new Boletin();

		EnumCategoria categoriaBuscada = EnumCategoria.nacional;

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
	@Test
	public void subconjuntoCategoriaFecha() {
		Boletin todas = new Boletin();

		LocalDate fechaConcreta = LocalDate.of(2019, 11, 17);

		EnumCategoria categoriaBuscada = EnumCategoria.nacional;

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
	@Test
	public void subconjuntoCategoriaIntervalo() {
		Boletin todas = new Boletin();

		LocalDate inicioIntervalo = LocalDate.of(2019, 1, 1);
		LocalDate finalIntervalo = LocalDate.of(2019, 12, 31);

		EnumCategoria categoriaBuscada = EnumCategoria.nacional;

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
		n = null;
		n2 = null;
		n3 = null;
	}

}
