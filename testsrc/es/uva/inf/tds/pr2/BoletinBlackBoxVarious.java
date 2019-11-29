package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BoletinBlackBoxVarious {

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
		categoria6 = EnumCategoria.sociedad;
		url6 = "https://www." + fuente6 + '/' + categoria6 + '/' + titular6;
		n6 = new Noticia(titular6, fechaPublicacion6, fuente6, url6, categoria6);

		titular7 = "Hola7";
		fechaPublicacion7 = LocalDate.of(2019, 11, 19);
		fuente7 = "Adios7";
		categoria7 = EnumCategoria.sociedad;
		url7 = "https://www." + fuente7 + '/' + categoria7 + '/' + titular7;
		n7 = new Noticia(titular7, fechaPublicacion7, fuente7, url7, categoria7);
	}
	
	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("BlackBoxTestFirstTestFirst")
	@Test
	public void listaCategoriasMismaCatMismaFecha() {
		Boletin b = new Boletin();
		
		b.addNoticia(n);
		b.addNoticia(n2);
		b.addNoticia(n3);
		b.addNoticia(n4);
		b.addNoticia(n5);
		b.addNoticia(n7);
		b.addNoticia(n6);

		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		al.add(n2);
		al.add(n3);
		al.add(n4);
		al.add(n5);
		al.add(n7);
		al.add(n6);

		assertArrayEquals(al.toArray(), b.getNewsByCategory().toArray());
	}
	
	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("BlackBoxTestFirst")
	@Test
	public void noticiasSimilaresMismaCat() {
		Boletin b = new Boletin();

		fechaPublicacion4 = LocalDate.of(2019, 11, 16);
		categoria4 = EnumCategoria.internacional;
		url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		categoria5 = EnumCategoria.internacional;
		url5 = "https://www." + fuente5 + '/' + categoria5 + '/' + titular5;
		n5 = new Noticia(titular5, fechaPublicacion5, fuente5, url5, categoria5);

		fechaPublicacion6 = LocalDate.of(2019, 11, 13);
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
		al.add(n6);
		al.add(n4);
		

		assertArrayEquals(al.toArray(), b.getSimilarNews(n2).toArray());
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("BlackBoxTestFirst")
	@Test
	public void noticiasSimilaresMismaFecha() {
		Boletin b = new Boletin();

		fechaPublicacion4 = LocalDate.of(2019, 11, 13);
		categoria4 = EnumCategoria.internacional;
		url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		categoria5 = EnumCategoria.internacional;
		url5 = "https://www." + fuente5 + '/' + categoria5 + '/' + titular5;
		n5 = new Noticia(titular5, fechaPublicacion5, fuente5, url5, categoria5);

		fechaPublicacion6 = LocalDate.of(2019, 11, 13);
		categoria6 = EnumCategoria.internacional;
		url6 = "https://www." + fuente6 + '/' + categoria6 + '/' + titular6;
		n6 = new Noticia(titular6, fechaPublicacion6, fuente6, url6, categoria6);

		b.addNoticia(n);
		b.addNoticia(n2);
		b.addNoticia(n3);
		b.addNoticia(n5);
		b.addNoticia(n6);
		b.addNoticia(n7);
		b.addNoticia(n4);

		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n6);
		al.add(n4);
		
		assertArrayEquals(al.toArray(), b.getSimilarNews(n2).toArray());
	}

}
