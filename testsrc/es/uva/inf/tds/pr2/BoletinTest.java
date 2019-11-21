package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoletinTest {
	
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

	@Test
	public void createEmptyBoletin() {
		Boletin b = new Boletin();
		assertNotNull(b);
		fail("Until GREEN Phase");
	}
	
	@BeforeEach
	public void setUp() throws Exception{
		
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
	}
	
	@Test
	public void createNotEmptyBoletin() {
		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		
		Boletin b = new Boletin(al);
		assertNotNull(b);
		assertEquals(al,b.getNoticias());
	}
	
	@Test
	public void addNoticia() {
		Boletin b = new Boletin();
		b.addNoticia(n);
		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		assertEquals(al,b.getNoticias());
	}
	
	@Test
	public void addNoticiaRepetida() {
		Boletin b = new Boletin();
		b.addNoticia(n);
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(n);});
	}
	
	@Test
	public void numeroNoticias() {
		Boletin b = new Boletin();
		b.addNoticia(n);
		assertSame(1,b.getNumberOfNoticias());
	}
	
	
	
	@Test
	public void getFechaMasReciente() {
		Boletin b = new Boletin();
		b.addNoticia(n);
		b.addNoticia(n2);
		
		assertEquals(fechaPublicacion, b.getMostRecentDate());
	}
	
	@Test
	public void getFechaMasAntigua() {
		Boletin b = new Boletin();
		b.addNoticia(n);
		b.addNoticia(n2);
		
		assertEquals(fechaPublicacion2, b.getOldestDate());
	}
	
	@Test
	public void listaCronologica() {
		Boletin b = new Boletin();
		b.addNoticia(n);
		b.addNoticia(n2);
		
		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n2);
		al.add(n);
		
		assertEquals(al,b.getChronologicalOrder());
	}
	
	@Test
	public void listaCronologicaMismaFecha() {
		Boletin b = new Boletin();
		b.addNoticia(n2);
		b.addNoticia(n);
		
		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n2);
		al.add(n);
		
		assertEquals(al,b.getChronologicalOrder());
	}
	
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
	}
	
	@Test
	public void listaCategorias() {
		Boletin b = new Boletin();
		
		String titular3 = "Hola3";
		LocalDate fechaPublicacion3 = LocalDate.of(2019, 11, 16);
		String fuente3 = "Adios3";
		EnumCategoria categoria3 = EnumCategoria.sociedad;
		String url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular3;
		Noticia n3 = new Noticia(titular3, fechaPublicacion3, fuente3, url3, categoria3);
		
		String titular4 = "Hola4";
		LocalDate fechaPublicacion4 = LocalDate.of(2019, 11, 17);
		String fuente4 = "Adios4";
		EnumCategoria categoria4 = EnumCategoria.economia;
		String url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		Noticia n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		String titular5 = "Hola5";
		LocalDate fechaPublicacion5 = LocalDate.of(2019, 11, 18);
		String fuente5 = "Adios5";
		EnumCategoria categoria5 = EnumCategoria.deporte;
		String url5 = "https://www." + fuente5 + '/' + categoria5 + '/' + titular5;
		Noticia n5 = new Noticia(titular5, fechaPublicacion5, fuente5, url5, categoria5);
	
		String titular6 = "Hola6";
		LocalDate fechaPublicacion6 = LocalDate.of(2019, 11, 19);
		String fuente6 = "Adios6";
		EnumCategoria categoria6 = EnumCategoria.cultura;
		String url6 = "https://www." + fuente6 + '/' + categoria6 + '/' + titular6;
		Noticia n6 = new Noticia(titular6, fechaPublicacion6, fuente6, url6, categoria6);
	
		String titular7 = "Hola7";
		LocalDate fechaPublicacion7 = LocalDate.of(2019, 11, 15);
		String fuente7 = "Adios7";
		EnumCategoria categoria7 = EnumCategoria.sociedad;
		String url7 = "https://www." + fuente7 + '/' + categoria7 + '/' + titular7;
		Noticia n7 = new Noticia(titular7, fechaPublicacion7, fuente7, url7, categoria7);
		
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
		
		assertEquals(al,b.getNewsByCategory());
	}
	
	@Test
	public void noticiasSimilares() {
		Boletin b = new Boletin();
		
		String titular3 = "Hola";
		LocalDate fechaPublicacion3 = LocalDate.of(2019, 11, 16);
		String fuente3 = "Adios3";
		EnumCategoria categoria3 = EnumCategoria.sociedad;
		String url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular3;
		Noticia n3 = new Noticia(titular3, fechaPublicacion3, fuente3, url3, categoria3);
		
		String titular4 = "Hola";
		LocalDate fechaPublicacion4 = LocalDate.of(2019, 11, 17);
		String fuente4 = "Adios4";
		EnumCategoria categoria4 = EnumCategoria.internacional;
		String url4 = "https://www." + fuente4 + '/' + categoria4 + '/' + titular4;
		Noticia n4 = new Noticia(titular4, fechaPublicacion4, fuente4, url4, categoria4);

		String titular5 = "Hola";
		LocalDate fechaPublicacion5 = LocalDate.of(2019, 11, 18);
		String fuente5 = "Adios5";
		EnumCategoria categoria5 = EnumCategoria.internacional;
		String url5 = "https://www." + fuente5 + '/' + categoria5 + '/' + titular5;
		Noticia n5 = new Noticia(titular5, fechaPublicacion5, fuente5, url5, categoria5);
	
		String titular6 = "Hola";
		LocalDate fechaPublicacion6 = LocalDate.of(2019, 11, 14);
		String fuente6 = "Adios6";
		EnumCategoria categoria6 = EnumCategoria.internacional;
		String url6 = "https://www." + fuente6 + '/' + categoria6 + '/' + titular6;
		Noticia n6 = new Noticia(titular6, fechaPublicacion6, fuente6, url6, categoria6);
	
		String titular7 = "Hola7";
		LocalDate fechaPublicacion7 = LocalDate.of(2019, 11, 15);
		String fuente7 = "Adios7";
		EnumCategoria categoria7 = EnumCategoria.sociedad;
		String url7 = "https://www." + fuente7 + '/' + categoria7 + '/' + titular7;
		Noticia n7 = new Noticia(titular7, fechaPublicacion7, fuente7, url7, categoria7);
	
		b.addNoticia(n);
		b.addNoticia(n2);
		b.addNoticia(n3);
		b.addNoticia(n4);
		b.addNoticia(n5);
		b.addNoticia(n6);
		b.addNoticia(n7);
		
		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n4);
		al.add(n6);
		
		assertEquals(al,b.getSimilarNews(n2));
	}
	
	@AfterEach
	public void tearDown() {
		n=null;
		n2=null;
		
	}

}
