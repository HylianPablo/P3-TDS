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

public class BoletinTDDFixtureVarious {

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

	private String titular4;
	private LocalDate fechaPublicacion4;
	private String fuente4;
	private EnumCategoria categoria4;
	private String url4;
	private Noticia n4;

	private String titular5;
	private LocalDate fechaPublicacion5;
	private String fuente5;
	private EnumCategoria categoria5;
	private String url5;
	private Noticia n5;

	private String titular6;
	private LocalDate fechaPublicacion6;
	private String fuente6;
	private EnumCategoria categoria6;
	private String url6;
	private Noticia n6;

	private String titular7;
	private LocalDate fechaPublicacion7;
	private String fuente7;
	private EnumCategoria categoria7;
	private String url7;
	private Noticia n7;

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

		titular4 = "Hola4";
		fechaPublicacion4 = LocalDate.of(2019, 11, 17);
		fuente4 = "Adios4";
		categoria4 = EnumCategoria.economia;
		url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		titular5 = "Hola5";
		fechaPublicacion5 = LocalDate.of(2019, 11, 18);
		fuente5 = "Adios5";
		categoria5 = EnumCategoria.deporte;
		url5 = "https://www." + fuente5 + '/' + categoria5 + '/' + titular5;
		n5 = new Noticia(titular5, fechaPublicacion5, fuente5, url5, categoria5);

		titular6 = "Hola6";
		fechaPublicacion6 = LocalDate.of(2019, 11, 19);
		fuente6 = "Adios6";
		categoria6 = EnumCategoria.cultura;
		url6 = "https://www." + fuente6 + '/' + categoria6 + '/' + titular6;
		n6 = new Noticia(titular6, fechaPublicacion6, fuente6, url6, categoria6);

		titular7 = "Hola7";
		fechaPublicacion7 = LocalDate.of(2019, 11, 15);
		fuente7 = "Adios7";
		categoria7 = EnumCategoria.sociedad;
		url7 = "https://www." + fuente7 + '/' + categoria7 + '/' + titular7;
		n7 = new Noticia(titular7, fechaPublicacion7, fuente7, url7, categoria7);
		
		in=createMock(INoticia.class);
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Test
	public void listaCategorias() {
		Boletin b = new Boletin();

		b.addNoticia(n);
		b.addNoticia(n2);
		b.addNoticia(n3);
		b.addNoticia(n4);
		b.addNoticia(n5);
		b.addNoticia(n6);
		b.addNoticia(n7);

		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		al.add(n2);
		al.add(n7);
		al.add(n3);
		al.add(n4);
		al.add(n5);
		al.add(n6);

		assertArrayEquals(al.toArray(), b.getNewsByCategory().toArray());
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Test
	public void noticiasSimilares() {
		Boletin b = new Boletin();

		categoria4 = EnumCategoria.internacional;
		url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		categoria5 = EnumCategoria.internacional;
		url5 = "https://www." + fuente5 + '/' + categoria5 + '/' + titular5;
		n5 = new Noticia(titular5, fechaPublicacion5, fuente5, url5, categoria5);

		fechaPublicacion6 = LocalDate.of(2019, 11, 19);
		categoria6 = EnumCategoria.internacional;
		url6 = "https://www." + fuente6 + '/' + categoria6 + '/' + titular6;
		n6 = new Noticia(titular6, fechaPublicacion6, fuente6, url6, categoria6);

		b.addNoticia(n);
		b.addNoticia(n2);
		b.addNoticia(n3);
		b.addNoticia(n4);
		b.addNoticia(n5);
		b.addNoticia(n6);
		b.addNoticia(n7);

		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n4);

		assertArrayEquals(al.toArray(), b.getSimilarNews(n2).toArray());
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
	@Tag("TDD")
	@Test
	public void subconjuntoFecha() {
		Boletin todas = new Boletin();
		LocalDate fechaBuscada = LocalDate.of(2019, 10, 21);
		
		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).times(2);
		replay(in);
		todas.addNoticia(in);
		assertEquals(fechaPublicacion, todas.getMostRecentDate());
		verify(in);

		titular3 = "En fecha";
		fechaPublicacion3 = LocalDate.of(2019, 10, 21);
		categoria3 = EnumCategoria.internacional;
		url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular3;
		n3 = new Noticia(titular3, fechaPublicacion3, fuente3, url3, categoria3);

		titular4 = "Tambien";
		fechaPublicacion4 = LocalDate.of(2019, 10, 21);
		categoria4 = EnumCategoria.internacional;
		url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		titular5 = "No en fecha";
		categoria5 = EnumCategoria.internacional;
		url5 = "https://www." + fuente5 + '/' + categoria5 + '/' + titular5;
		n5 = new Noticia(titular5, fechaPublicacion5, fuente5, url5, categoria5);

		todas.addNoticia(n3);
		todas.addNoticia(n4);
		todas.addNoticia(n5);

		Boletin validas = new Boletin();
		validas.addNoticia(n3);
		validas.addNoticia(n4);

		assertEquals(validas, todas.getSubconjuntoFecha(fechaBuscada));
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
	@Tag("TDD")
	@Test
	public void subconjuntoIntervalo() {
		Boletin todas = new Boletin();

		LocalDate inicioIntervalo = LocalDate.of(2019, 1, 1);
		LocalDate finalIntervalo = LocalDate.of(2019, 12, 31);

		titular3 = "Anterior al intervalo";
		fechaPublicacion3 = LocalDate.of(2018, 12, 31);
		categoria3 = EnumCategoria.internacional;
		url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular3;
		n3 = new Noticia(titular3, fechaPublicacion3, fuente3, url3, categoria3);

		titular4 = "Fecha en el intervalo";
		fechaPublicacion4 = LocalDate.of(2019, 1, 1);
		categoria4 = EnumCategoria.internacional;
		url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		titular5 = "Fecha en el intervalo 2";
		fechaPublicacion5 = LocalDate.of(2019, 11, 21);
		categoria5 = EnumCategoria.internacional;
		url5 = "https://www." + fuente5 + '/' + categoria5 + '/' + titular5;
		n5 = new Noticia(titular5, fechaPublicacion5, fuente5, url5, categoria5);

		titular6 = "No en fecha";
		fechaPublicacion6 = LocalDate.of(2020, 1, 1);
		categoria6 = EnumCategoria.internacional;
		url6 = "https://www." + fuente6 + '/' + categoria6 + '/' + titular6;
		n6 = new Noticia(titular6, fechaPublicacion6, fuente6, url6, categoria6);

		todas.addNoticia(n3);
		todas.addNoticia(n4);
		todas.addNoticia(n5);
		todas.addNoticia(n6);

		Boletin validas = new Boletin();
		validas.addNoticia(n4);
		validas.addNoticia(n5);

		assertEquals(validas, todas.getSubconjuntoIntervalo(inicioIntervalo, finalIntervalo));
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
	@Tag("TDD")
	@Test
	public void subconjuntoCategoria() {
		Boletin todas = new Boletin();

		EnumCategoria categoriaBuscada = EnumCategoria.sociedad;

		categoria4 = EnumCategoria.sociedad;
		url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		todas.addNoticia(n3);
		todas.addNoticia(n4);
		todas.addNoticia(n5);

		Boletin validas = new Boletin();
		validas.addNoticia(n3);
		validas.addNoticia(n4);

		assertEquals(validas, todas.getSubconjuntoCategoria(categoriaBuscada));
	}

	@Tag("Negative")
	@Tag("TDD")
	@Test
	public void subconjuntoCategoriaNull() {
		categoria4 = null;
		Boletin b = new Boletin();
		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoCategoria(categoria4);
		});
	}

	@Tag("Positive")
	@Tag("TDD")
	@Test
	public void subconjuntoCategoriaFecha() {
		Boletin todas = new Boletin();

		LocalDate fechaConcreta = LocalDate.of(2019, 11, 17);

		EnumCategoria categoriaBuscada = EnumCategoria.sociedad;

		fechaPublicacion3 = LocalDate.of(2019, 11, 17);
		categoria3 = EnumCategoria.sociedad;
		url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular3;
		n3 = new Noticia(titular3, fechaPublicacion3, fuente3, url3, categoria3);

		categoria4 = EnumCategoria.sociedad;
		url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		todas.addNoticia(n3);
		todas.addNoticia(n4);
		todas.addNoticia(n5);

		Boletin validas = new Boletin();
		validas.addNoticia(n3);
		validas.addNoticia(n4);

		assertEquals(validas, todas.getSubconjuntoCategoriaFecha(categoriaBuscada, fechaConcreta));
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
	@Tag("TDD")
	@Test
	public void subconjuntoCategoriaIntervalo() {
		Boletin todas = new Boletin();

		LocalDate inicioIntervalo = LocalDate.of(2019, 1, 1);
		LocalDate finalIntervalo = LocalDate.of(2019, 12, 31);

		EnumCategoria categoriaBuscada = EnumCategoria.sociedad;

		fechaPublicacion3 = LocalDate.of(2019, 1, 1);
		url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular3;
		n3 = new Noticia(titular3, fechaPublicacion3, fuente3, url3, categoria3);

		categoria4 = EnumCategoria.sociedad;
		url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		todas.addNoticia(n3);
		todas.addNoticia(n4);
		todas.addNoticia(n5);

		Boletin validas = new Boletin();
		validas.addNoticia(n3);
		validas.addNoticia(n4);

		assertEquals(validas,
				todas.getSubconjuntoCategoriaIntervalo(categoriaBuscada, inicioIntervalo, finalIntervalo));
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
		n4 = null;
		n5 = null;
		n6 = null;
		n7 = null;
	}

}
