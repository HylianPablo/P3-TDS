package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BoletinBlackBoxVariousAislamientoNoTest {

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
	@Tag("BlackBoxTestFirst")
	@Test
	public void listaCategoriasEDC() {
		fechaPublicacion = LocalDate.of(2019, 6, 15);
		fechaPublicacion2 = LocalDate.of(2018, 1, 1);
		
		Boletin b = new Boletin();
		
		b.addNoticia(n3);
		b.addNoticia(n);
		b.addNoticia(n2);

		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		al.add(n2);
		al.add(n3);

		assertArrayEquals(al.toArray(), b.getNewsByCategory().toArray());
	}
	
	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("BlackBoxTestFirst")
	@Test
	public void listaCategoriasIguales() {
		fechaPublicacion = LocalDate.of(2019, 6, 15);
		fechaPublicacion2 = LocalDate.of(2018, 1, 1);
		
		categoria=EnumCategoria.INTERNACIONAL;
		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		
		Boletin b = new Boletin();
		
		b.addNoticia(n);
		b.addNoticia(n2);

		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n2);
		al.add(n);

		assertArrayEquals(al.toArray(), b.getNewsByCategory().toArray());
	}
	
	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("BlackBoxTestFirst")
	@Test
	public void noticiasSimilaresMismaCat() {
		Boletin b = new Boletin();
		
		titular="Hola2";
		categoria = EnumCategoria.INTERNACIONAL;
		n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		b.addNoticia(n);
		b.addNoticia(n3);

		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);

		assertArrayEquals(al.toArray(), b.getSimilarNews(n2).toArray());
	}

	@AfterEach
	public void tearDown() {
		n=null;
		n2=null;
		n3=null;
	}

}
