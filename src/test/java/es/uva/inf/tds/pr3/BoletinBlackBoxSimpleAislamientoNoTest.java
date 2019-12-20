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
import es.uva.inf.tds.pr3.Noticia;

public class BoletinBlackBoxSimpleAislamientoNoTest {

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

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Test
	public void addNoticiaRepetida() {
		b.addNoticia(n);
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(n);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Positive")
	@Test
	public void testNumeroNoticiasBoletinVacio() {
		assertEquals(0, b.getNumberOfNoticias());
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Positive")
	@Test
	public void testListaCronologicaMismaFecha() {
		b.addNoticia(n);
		b.addNoticia(n2);

		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		al.add(n2);

		assertArrayEquals(al.toArray(), b.getChronologicalOrder().toArray());
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
	@Test
	public void subconjuntoIntervaloInicialNull() {
		LocalDate inicioIntervalo = null;
		LocalDate finalIntervalo = LocalDate.of(2019, 12, 13);

		assertThrows(IllegalArgumentException.class, () -> {
			b.getSubconjuntoIntervalo(inicioIntervalo, finalIntervalo);
		});
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Positive")
	@Test
	public void subconjuntoIntervaloIguales() {
		LocalDate int1 = LocalDate.of(2019, 11, 15);
		LocalDate int2 = LocalDate.of(2019, 12, 14);

		Boletin b2 = new Boletin();
		b2.addNoticia(n2);

		b.addNoticia(n);
		b.addNoticia(n2);

		assertArrayEquals(b2.getNoticias().toArray(), b.getSubconjuntoIntervalo(int1, int2).getNoticias().toArray());
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Positive")
	@Test
	public void subconjuntoCategoriaSinFecha() {
		b.addNoticia(n);
		b.addNoticia(n2);

		Boletin b2 = new Boletin();
		EnumCategoria c = EnumCategoria.SOCIEDAD;

		assertArrayEquals(b2.getNoticias().toArray(),
				b.getSubconjuntoCategoriaFecha(c, fechaPublicacion).getNoticias().toArray());
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Positive")
	@Test
	public void subconjuntoCategoriaIntervaloMismaFecha() {
		LocalDate int1 = LocalDate.of(2019, 11, 14);
		LocalDate int2 = LocalDate.of(2019, 11, 14);
		EnumCategoria c = EnumCategoria.NACIONAL;

		Boletin b2 = new Boletin();
		b2.addNoticia(n);

		b.addNoticia(n);
		b.addNoticia(n2);

		assertArrayEquals(b2.getNoticias().toArray(),
				b.getSubconjuntoCategoriaIntervalo(c, int1, int2).getNoticias().toArray());
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Positive")
	@Test
	public void subconjuntoCategoriaSinIntervalo() {
		LocalDate int1 = LocalDate.of(2010, 11, 14);
		LocalDate int2 = LocalDate.of(2012, 11, 14);
		EnumCategoria c = EnumCategoria.NACIONAL;

		Boletin b2 = new Boletin();

		b.addNoticia(n);
		b.addNoticia(n2);

		assertArrayEquals(b2.getNoticias().toArray(),
				b.getSubconjuntoCategoriaIntervalo(c, int1, int2).getNoticias().toArray());
	}

	@Tag("BlackBoxTestFirst")
	@Tag("Negative")
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
		n = null;
		n2 = null;
		b = null;
	}

}
