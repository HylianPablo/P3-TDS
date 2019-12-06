package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr2.Boletin;
import es.uva.inf.tds.pr2.EnumCategoria;
import es.uva.inf.tds.pr2.Noticia;

public class BoletinSequence {

	@Tag("Sequence")
	@Test
	public void secuenciaNormal() {
		Boletin boletin1 = new Boletin();
		assertNotNull(boletin1);

		LocalDate fechaConcretaMal = LocalDate.of(2019, 5, 1);
		LocalDate fechaConcreta = LocalDate.of(2019, 11, 26);
		LocalDate fechaAnterior = LocalDate.of(2018, 1, 1);
		LocalDate fechaAnterior2 = LocalDate.of(2018, 12, 27);
		LocalDate fechaPosterior = LocalDate.of(2020, 1, 1);
		
		ArrayList<Noticia> vacia = new ArrayList<>();
		
		assertEquals(vacia, boletin1.getChronologicalOrder());
		assertNull(boletin1.getMostRecentDate());
		assertEquals(boletin1, boletin1.getSubconjuntoIntervalo(fechaAnterior, fechaPosterior));
		assertEquals(boletin1, boletin1.getSubconjuntoFecha(fechaConcreta));
		assertEquals(boletin1, boletin1.getSubconjuntoCategoriaIntervalo(EnumCategoria.nacional, fechaAnterior, fechaAnterior));
		assertEquals(boletin1, boletin1.getSubconjuntoCategoriaFecha(EnumCategoria.nacional, fechaConcreta));
		assertEquals(boletin1, boletin1.getSubconjuntoCategoria(EnumCategoria.nacional));
		assertNull(boletin1.getOldestDate());
		assertEquals(0, boletin1.getNumberOfNoticias());
		assertEquals(vacia, boletin1.getNoticias());
		assertEquals(vacia, boletin1.getNewsByCategory());
	

		String titular = "Pruebas!";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 28);
		String fuente = "fakeNews";
		EnumCategoria categoria = EnumCategoria.cultura;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia noticia1 = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		boletin1.addNoticia(noticia1);

		String titular2 = "Titular cuyo numero de palabras se encuentra justo en el limite superior";
		EnumCategoria categoria2 = EnumCategoria.nacional;
		String url2 = "https://www." + fuente + '/' + categoria2 + '/' + titular2;

		Noticia noticia2 = new Noticia(titular2, fechaPublicacion, fuente, url2, categoria2);

		LocalDate fechaPublicacion3 = LocalDate.of(2019, 11, 28);
		String fuente3 = "cicloRGB";
		String url3 = "https://www." + fuente3 + '/' + categoria + '/' + titular;
		Noticia noticia3 = new Noticia(titular, fechaPublicacion3, fuente3, url3, categoria);

		LocalDate fechaPublicacion4 = LocalDate.of(2019, 12, 28);
		EnumCategoria categoria4 = EnumCategoria.internacional;
		String url4 = "https://www." + fuente + '/' + categoria4 + '/' + titular;
		Noticia noticia4 = new Noticia(titular, fechaPublicacion4, fuente, url4, categoria4);

		LocalDate fechaPublicacion5 = LocalDate.of(2018, 12, 28);
		EnumCategoria categoria5 = EnumCategoria.sociedad;
		String url5 = "https://www." + fuente + '/' + categoria5 + '/' + titular;
		Noticia noticia5 = new Noticia(titular, fechaPublicacion5, fuente, url5, categoria5);

		EnumCategoria categoria6 = EnumCategoria.economia;
		String url6 = "https://www." + fuente + '/' + categoria6 + '/' + titular;
		Noticia noticia6 = new Noticia(titular, fechaPublicacion, fuente, url6, categoria6);

		EnumCategoria categoria7 = EnumCategoria.deporte;
		String url7 = "https://www." + fuente + '/' + categoria7 + '/' + titular;
		Noticia noticia7 = new Noticia(titular, fechaPublicacion, fuente, url7, categoria7);

		boletin1.addNoticia(noticia2);
		boletin1.addNoticia(noticia3);
		boletin1.addNoticia(noticia4);
		boletin1.addNoticia(noticia5);
		boletin1.addNoticia(noticia6);
		boletin1.addNoticia(noticia7);

		ArrayList<Noticia> lista = new ArrayList<>();
		lista.add(noticia1);
		lista.add(noticia2);
		lista.add(noticia3);
		lista.add(noticia4);
		lista.add(noticia5);
		lista.add(noticia6);
		lista.add(noticia7);

		assertArrayEquals(lista.toArray(), boletin1.getNoticias().toArray());
		assertEquals(7, boletin1.getNumberOfNoticias());
		
		Boletin test4 = boletin1.getSubconjuntoIntervalo(fechaAnterior, fechaAnterior2);
		assertEquals(0, test4.getNumberOfNoticias());
		assertEquals(boletin1, boletin1.getSubconjuntoIntervalo(fechaAnterior, fechaPosterior));

		Boletin boletin6 = new Boletin();
		boletin6.addNoticia(noticia1);
		boletin6.addNoticia(noticia3);
		assertEquals(boletin6,
				boletin1.getSubconjuntoCategoriaIntervalo(EnumCategoria.cultura, fechaPublicacion, fechaPublicacion3));
		Boletin test6 = boletin1.getSubconjuntoCategoriaIntervalo(EnumCategoria.cultura, fechaAnterior, fechaAnterior2);
		assertEquals(0, test6.getNumberOfNoticias());

		assertEquals(fechaPublicacion4, boletin1.getMostRecentDate());
		assertEquals(fechaPublicacion5, boletin1.getOldestDate());
		
		Boletin boletin4 = new Boletin();
		boletin4.addNoticia(noticia1);
		boletin4.addNoticia(noticia3);
		assertEquals(boletin4, boletin1.getSubconjuntoCategoria(EnumCategoria.cultura));

		ArrayList<Noticia> listaOrdenada = new ArrayList<>();
		listaOrdenada.add(noticia5);
		listaOrdenada.add(noticia1);
		listaOrdenada.add(noticia2);
		listaOrdenada.add(noticia6);
		listaOrdenada.add(noticia7);
		listaOrdenada.add(noticia3);
		listaOrdenada.add(noticia4);

		assertArrayEquals(listaOrdenada.toArray(), boletin1.getChronologicalOrder().toArray());

		ArrayList<Noticia> listaCategorias = new ArrayList<>();
		listaCategorias.add(noticia2);
		listaCategorias.add(noticia4);
		listaCategorias.add(noticia5);
		listaCategorias.add(noticia6);
		listaCategorias.add(noticia7);
		listaCategorias.add(noticia1);
		listaCategorias.add(noticia3);

		assertArrayEquals(listaCategorias.toArray(), boletin1.getNewsByCategory().toArray());

		ArrayList<Noticia> listaSimilares = new ArrayList<>();
		listaSimilares.add(noticia1);
		listaSimilares.add(noticia3);

		assertArrayEquals(listaSimilares.toArray(), boletin1.getSimilarNews(noticia1).toArray());

		Boletin test3 = boletin1.getSubconjuntoFecha(fechaConcretaMal);
		assertEquals(0, test3.getNumberOfNoticias());
		Boletin boletin3 = new Boletin();
		boletin3.addNoticia(noticia3);
		assertEquals(boletin3, boletin1.getSubconjuntoFecha(fechaConcreta));

		Boletin boletin5 = new Boletin();
		boletin5.addNoticia(noticia1);
		assertEquals(boletin5, boletin1.getSubconjuntoCategoriaFecha(EnumCategoria.cultura, fechaPublicacion));
		Boletin test5 = boletin1.getSubconjuntoCategoriaFecha(EnumCategoria.cultura, fechaPublicacion4);
		assertEquals(0, test5.getNumberOfNoticias());
		
	}
}