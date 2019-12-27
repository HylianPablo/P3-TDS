package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BoletinTDDFixtureVariousAislamientoNoTest {

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

	private String titular3;
	private LocalDate fechaPublicacion3;
	private String fuente3;
	private EnumCategoria categoria3;
	private String url3;
	private Noticia n3;

	@BeforeEach
	public void setUpCategorias() {
		titular = "Hola";
		fechaPublicacion = LocalDate.of(2019, 11, 14);
		fuente = "Adios";
		categoria = EnumCategoria.NACIONAL;
		url = "https://www." + fuente + '/' + categoria + '/' + titular;
		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		titular2 = "Hola2";
		fechaPublicacion2 = LocalDate.of(2019, 11, 15);
		fuente2 = "Adios2";
		categoria2 = EnumCategoria.INTERNACIONAL;
		url2 = "https://www." + fuente2 + '/' + categoria2 + '/' + titular2;
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		titular3 = "Hola3";
		fechaPublicacion3 = LocalDate.of(2019, 11, 16);
		fuente3 = "Adios3";
		categoria3 = EnumCategoria.SOCIEDAD;
		url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular3;
		n3 = new Noticia(titular3, fechaPublicacion3, fuente3, url3, categoria3);
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Test
	public void listaCategorias() {
		Boletin b = new Boletin();

		b.addNoticia(n);
		b.addNoticia(n3);
		b.addNoticia(n2);


		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		al.add(n2);
		al.add(n3);

		assertArrayEquals(al.toArray(), b.getNewsByCategory().toArray());
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("TDD")
	@Test
	public void noticiasSimilares() {
		Boletin b = new Boletin();

		n2 = new Noticia(titular, fechaPublicacion2, fuente2, url2, categoria);
		b.addNoticia(n2);
		b.addNoticia(n3);


		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n2);

		assertArrayEquals(al.toArray(), b.getSimilarNews(n).toArray());
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
		fechaPublicacion=fechaBuscada;
		fechaPublicacion2=LocalDate.of(2019, 10, 22);
		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		todas.addNoticia(n);
		todas.addNoticia(n2);

		Boletin validas = new Boletin();
		validas.addNoticia(n);

		assertArrayEquals(validas.getNoticias().toArray(), todas.getSubconjuntoFecha(fechaBuscada).getNoticias().toArray());
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
		
		fechaPublicacion = LocalDate.of(2019, 1, 1);
		fechaPublicacion2 = LocalDate.of(2018, 1, 1);
		fechaPublicacion3 = LocalDate.of(2019, 12, 31);

		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		n3 = new Noticia(titular3, fechaPublicacion3, fuente3, url3, categoria3);

		todas.addNoticia(n);
		todas.addNoticia(n2);
		todas.addNoticia(n3);

		Boletin validas = new Boletin();
		validas.addNoticia(n);
		validas.addNoticia(n3);

		assertArrayEquals(validas.getNoticias().toArray(), todas.getSubconjuntoIntervalo(inicioIntervalo, finalIntervalo).getNoticias().toArray());
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

		EnumCategoria categoriaBuscada = EnumCategoria.NACIONAL;
		todas.addNoticia(n);
		todas.addNoticia(n2);

		Boletin validas = new Boletin();
		validas.addNoticia(n);

		assertArrayEquals(validas.getNoticias().toArray(), todas.getSubconjuntoCategoria(categoriaBuscada).getNoticias().toArray());
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
	@Tag("TDD")
	@Test
	public void subconjuntoCategoriaFecha() {
		Boletin todas = new Boletin();

		LocalDate fechaConcreta = LocalDate.of(2019, 11, 17);

		EnumCategoria categoriaBuscada = EnumCategoria.NACIONAL;

		fechaPublicacion = LocalDate.of(2019, 11, 17);
		fechaPublicacion2 = LocalDate.of(2019, 1, 1);
		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		todas.addNoticia(n);
		todas.addNoticia(n2);

		Boletin validas = new Boletin();
		validas.addNoticia(n);

		assertArrayEquals(validas.getNoticias().toArray(), todas.getSubconjuntoCategoriaFecha(categoriaBuscada, fechaConcreta).getNoticias().toArray());
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

		EnumCategoria categoriaBuscada = EnumCategoria.NACIONAL;

		fechaPublicacion = LocalDate.of(2019, 1, 1);
		fechaPublicacion2 = LocalDate.of(2018, 1, 1);
		fechaPublicacion3 = LocalDate.of(2019, 12, 31);
		url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular3;
		n3 = new Noticia(titular3, fechaPublicacion3, fuente3, url3, categoria);

		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);

		todas.addNoticia(n);
		todas.addNoticia(n2);
		todas.addNoticia(n3);

		Boletin validas = new Boletin();
		validas.addNoticia(n);
		validas.addNoticia(n3);

		assertArrayEquals(validas.getNoticias().toArray(),
				todas.getSubconjuntoCategoriaIntervalo(categoriaBuscada, inicioIntervalo, finalIntervalo).getNoticias().toArray());
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
