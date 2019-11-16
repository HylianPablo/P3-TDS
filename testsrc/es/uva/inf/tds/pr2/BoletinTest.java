package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BoletinTest {

	@Test
	public void createEmptyBoletin() {
		Boletin b = new Boletin();
		assertNotNull(b);
		fail("Until GREEN Phase");
	}
	
	@Test
	public void createNotEmptyBoletin() {
		ArrayList<Noticia> al = new ArrayList<>();
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		al.add(n);
		
		Boletin b = new Boletin(al);
		assertNotNull(b);
		assertEquals(al,b.getNoticias());
	}
	
	@Test
	public void addNoticia() {
		Boletin b = new Boletin();
		
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		b.addNoticia(n);
		
		ArrayList<Noticia> al = new ArrayList<>();
		al.add(n);
		
		assertEquals(al,b.getNoticias());
	}
	
	@Test
	public void addNoticiaRepetida() {
		Boletin b = new Boletin();
		
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		b.addNoticia(n);
		assertThrows(IllegalArgumentException.class, () -> {
			b.addNoticia(n);});

	}
	
	@Test
	public void numeroNoticias() {
		Boletin b = new Boletin();
		
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		b.addNoticia(n);
		assertSame(1,b.getNumberOfNoticias());
	}
	
	@Test
	public void getFechaMasReciente() {
		Boletin b = new Boletin();
		
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		String titular2 = "Hola2";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 12, 14);
		String fuente2 = "Adios2";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		
		b.addNoticia(n);
		b.addNoticia(n2);
		
		assertEquals(fechaPublicacion, b.getMostRecentDate());
	}
	
	@Test
	public void getFechaMasAntigua() {
		Boletin b = new Boletin();
		
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 14);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.nacional;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);
		
		String titular2 = "Hola2";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 12, 14);
		String fuente2 = "Adios2";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia n2 = new Noticia(titular2, fechaPublicacion2, fuente2, url2, categoria2);
		
		b.addNoticia(n);
		b.addNoticia(n2);
		
		assertEquals(fechaPublicacion2, b.getOldestDate());
	}

}
