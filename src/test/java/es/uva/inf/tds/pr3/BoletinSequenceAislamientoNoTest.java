package es.uva.inf.tds.pr3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr3.Boletin;
import es.uva.inf.tds.pr3.EnumCategoria;
import es.uva.inf.tds.pr3.Noticia;

public class BoletinSequenceAislamientoNoTest {

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

		LocalDate fechaNulaMenor = LocalDate.of(0, 1, 1);
		LocalDate fechaNulaMayor = LocalDate.of(9999, 1, 1);

		ArrayList<Noticia> vacia = new ArrayList<>();

		assertEquals(vacia, boletin1.getChronologicalOrder());
		assertEquals(fechaNulaMenor, boletin1.getMostRecentDate());
		assertArrayEquals(boletin1.getNoticias().toArray(),
				boletin1.getSubconjuntoIntervalo(fechaAnterior, fechaPosterior).getNoticias().toArray());
		assertArrayEquals(boletin1.getNoticias().toArray(),
                boletin1.getSubconjuntoFecha(fechaConcreta).getNoticias().toArray());
		assertArrayEquals(boletin1.getNoticias().toArray(),
				boletin1.getSubconjuntoCategoriaIntervalo(EnumCategoria.NACIONAL, fechaAnterior, fechaAnterior)
						.getNoticias().toArray());
		assertArrayEquals(boletin1.getNoticias().toArray(),
				boletin1.getSubconjuntoCategoriaFecha(EnumCategoria.NACIONAL, fechaConcreta).getNoticias().toArray());
		assertArrayEquals(boletin1.getNoticias().toArray(),
				boletin1.getSubconjuntoCategoria(EnumCategoria.NACIONAL).getNoticias().toArray());
		assertEquals(fechaNulaMayor, boletin1.getOldestDate());
		assertEquals(0, boletin1.getNumberOfNoticias());
		assertEquals(vacia, boletin1.getNoticias());
		assertEquals(vacia, boletin1.getNewsByCategory());

		String titular = "Pruebas!";
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 28);
		String fuente = "fakeNews";
		EnumCategoria categoria = EnumCategoria.CULTURA;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;

		Noticia noticia1 = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		boletin1.addNoticia(noticia1);

		String titular2 = "Titular cuyo numero de palabras se encuentra justo en el limite superior";
		LocalDate fechaPublicacion2 = LocalDate.of(2019, 12, 28);
		EnumCategoria categoria2 = EnumCategoria.NACIONAL;
		String url2 = "https://www." + fuente + '/' + categoria2 + '/' + titular2;

		Noticia noticia2 = new Noticia(titular2, fechaPublicacion2, fuente, url2, categoria2);

		LocalDate fechaPublicacion3 = LocalDate.of(2019, 11, 27);
		String fuente3 = "cicloRGB";
		EnumCategoria categoria3 = EnumCategoria.CULTURA;
		String url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular;
		Noticia noticia3 = new Noticia(titular, fechaPublicacion3, fuente3, url3, categoria3);

		boletin1.addNoticia(noticia2);
		boletin1.addNoticia(noticia3);

		ArrayList<Noticia> lista = new ArrayList<>();
		lista.add(noticia1);
		lista.add(noticia2);
		lista.add(noticia3);

		assertArrayEquals(lista.toArray(), boletin1.getNoticias().toArray());
		assertEquals(3, boletin1.getNumberOfNoticias());

		Boletin test4 = boletin1.getSubconjuntoIntervalo(fechaAnterior, fechaAnterior2);
		assertEquals(0, test4.getNumberOfNoticias());
		assertArrayEquals(boletin1.getNoticias().toArray(), boletin1.getSubconjuntoIntervalo(fechaAnterior, fechaPosterior).getNoticias().toArray());

		Boletin boletin6 = new Boletin();
		boletin6.addNoticia(noticia1);
		boletin6.addNoticia(noticia3);
		assertArrayEquals(boletin6.getNoticias().toArray(),
				boletin1.getSubconjuntoCategoriaIntervalo(EnumCategoria.CULTURA, fechaPublicacion3, fechaPublicacion).getNoticias().toArray());
		Boletin test6 = boletin1.getSubconjuntoCategoriaIntervalo(EnumCategoria.CULTURA, fechaAnterior, fechaAnterior2);
		assertEquals(0, test6.getNumberOfNoticias());

		assertEquals(fechaPublicacion2, boletin1.getMostRecentDate());
		assertEquals(fechaPublicacion3, boletin1.getOldestDate());

		Boletin boletin4 = new Boletin();
		boletin4.addNoticia(noticia1);
		boletin4.addNoticia(noticia3);
		assertArrayEquals(boletin4.getNoticias().toArray(), boletin1.getSubconjuntoCategoria(EnumCategoria.CULTURA).getNoticias().toArray());

		ArrayList<Noticia> listaOrdenada = new ArrayList<>();
		listaOrdenada.add(noticia3);
		listaOrdenada.add(noticia1);
		listaOrdenada.add(noticia2);

		assertArrayEquals(listaOrdenada.toArray(), boletin1.getChronologicalOrder().toArray());

		ArrayList<Noticia> listaCategorias = new ArrayList<>();
		listaCategorias.add(noticia2);
		listaCategorias.add(noticia3);
		listaCategorias.add(noticia1);

		assertArrayEquals(listaCategorias.toArray(), boletin1.getNewsByCategory().toArray());

		ArrayList<Noticia> listaSimilares = new ArrayList<>();
		listaSimilares.add(noticia1);
		listaSimilares.add(noticia3);

		assertArrayEquals(listaSimilares.toArray(), boletin1.getSimilarNews(noticia1).toArray());

		Boletin test3 = boletin1.getSubconjuntoFecha(fechaConcretaMal);
		assertEquals(0, test3.getNumberOfNoticias());
		Boletin boletin3 = new Boletin();
		boletin3.addNoticia(noticia3);
		assertArrayEquals(boletin3.getNoticias().toArray(), boletin1.getSubconjuntoFecha(fechaPublicacion3).getNoticias().toArray());

		Boletin boletin5 = new Boletin();
		boletin5.addNoticia(noticia1);
		assertArrayEquals(boletin5.getNoticias().toArray(), boletin1.getSubconjuntoCategoriaFecha(EnumCategoria.CULTURA, fechaPublicacion).getNoticias().toArray());
		Boletin test5 = boletin1.getSubconjuntoCategoriaFecha(EnumCategoria.CULTURA, fechaPublicacion3);
		assertEquals(1, test5.getNumberOfNoticias());

	}
}
