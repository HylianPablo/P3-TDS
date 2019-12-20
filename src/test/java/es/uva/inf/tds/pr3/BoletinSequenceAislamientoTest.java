package es.uva.inf.tds.pr3;

import static org.junit.jupiter.api.Assertions.*;

import static org.easymock.EasyMock.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr3.Boletin;
import es.uva.inf.tds.pr3.EnumCategoria;
import es.uva.inf.tds.pr3.INoticia;
import es.uva.inf.tds.pr3.Noticia;

public class BoletinSequenceAislamientoTest {

	@Tag("Sequence")
	@Tag("Isolation")
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

		
		LocalDate fechaPublicacion = LocalDate.of(2019, 11, 28);
		EnumCategoria categoria = EnumCategoria.CULTURA;

		INoticia in = createMock(INoticia.class);

		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in.getCategoria()).andReturn(categoria).anyTimes();
		expect(in.isSimilar(in)).andReturn(true).anyTimes();
		replay(in);

		boletin1.addNoticia(in);

		LocalDate fechaPublicacion2 = LocalDate.of(2019, 12, 28);
		EnumCategoria categoria2 = EnumCategoria.NACIONAL;

		INoticia in2 = createMock(INoticia.class);

		LocalDate fechaPublicacion3 = LocalDate.of(2019, 11, 27);
		EnumCategoria categoria3 = EnumCategoria.CULTURA;

		INoticia in3 = createMock(INoticia.class);

		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).anyTimes();
		expect(in3.getFechaPublicacion()).andReturn(fechaPublicacion3).anyTimes();
		expect(in2.getCategoria()).andReturn(categoria2).anyTimes();
		expect(in3.getCategoria()).andReturn(categoria3).anyTimes();
		expect(in2.isSimilar(in)).andReturn(false).anyTimes();
		expect(in3.isSimilar(in)).andReturn(true).anyTimes();
		replay(in2);
		replay(in3);

		boletin1.addNoticia(in2);
		boletin1.addNoticia(in3);

		ArrayList<INoticia> lista = new ArrayList<>();
		lista.add(in);
		lista.add(in2);
		lista.add(in3);

		assertArrayEquals(lista.toArray(), boletin1.getNoticias().toArray());
		assertEquals(3, boletin1.getNumberOfNoticias());
		verify(in);
		verify(in2);
		verify(in3);

		Boletin test4 = boletin1.getSubconjuntoIntervalo(fechaAnterior, fechaAnterior2);
		assertEquals(0, test4.getNumberOfNoticias());
		assertArrayEquals(boletin1.getNoticias().toArray(),
				boletin1.getSubconjuntoIntervalo(fechaAnterior, fechaPosterior).getNoticias().toArray());
		verify(in);
		verify(in2);
		verify(in3);

		Boletin boletin6 = new Boletin();
		boletin6.addNoticia(in);
		boletin6.addNoticia(in3);
		assertArrayEquals(boletin6.getNoticias().toArray(),
				boletin1.getSubconjuntoCategoriaIntervalo(EnumCategoria.CULTURA, fechaPublicacion3, fechaPublicacion)
						.getNoticias().toArray());
		Boletin test6 = boletin1.getSubconjuntoCategoriaIntervalo(EnumCategoria.CULTURA, fechaAnterior, fechaAnterior2);
		assertEquals(0, test6.getNumberOfNoticias());
		verify(in);
		verify(in3);

		assertEquals(fechaPublicacion2, boletin1.getMostRecentDate());
		assertEquals(fechaPublicacion3, boletin1.getOldestDate());
		verify(in2);
		verify(in3);

		Boletin boletin4 = new Boletin();
		boletin4.addNoticia(in);
		boletin4.addNoticia(in3);
		assertArrayEquals(boletin4.getNoticias().toArray(),
				boletin1.getSubconjuntoCategoria(EnumCategoria.CULTURA).getNoticias().toArray());
		verify(in);
		verify(in3);

		ArrayList<INoticia> listaOrdenada = new ArrayList<>();
		listaOrdenada.add(in3);
		listaOrdenada.add(in);
		listaOrdenada.add(in2);

		assertArrayEquals(listaOrdenada.toArray(), boletin1.getChronologicalOrder().toArray());
		verify(in);
		verify(in2);
		verify(in3);

		ArrayList<INoticia> listaCategorias = new ArrayList<>();
		listaCategorias.add(in2);
		listaCategorias.add(in3);
		listaCategorias.add(in);

		assertArrayEquals(listaCategorias.toArray(), boletin1.getNewsByCategory().toArray());

		ArrayList<INoticia> listaSimilares = new ArrayList<>();

		listaSimilares.add(in3);

		Boletin boletin11 = new Boletin();
		boletin11.addNoticia(in2);
		boletin11.addNoticia(in3);
		assertArrayEquals(listaSimilares.toArray(), boletin11.getSimilarNews(in).toArray());
		verify(in2);
		verify(in3);

		Boletin test3 = boletin1.getSubconjuntoFecha(fechaConcretaMal);
		assertEquals(0, test3.getNumberOfNoticias());
		Boletin boletin3 = new Boletin();
		boletin3.addNoticia(in3);
		assertArrayEquals(boletin3.getNoticias().toArray(),
				boletin1.getSubconjuntoFecha(fechaPublicacion3).getNoticias().toArray());
		verify(in3);

		Boletin boletin5 = new Boletin();
		boletin5.addNoticia(in);
		assertArrayEquals(boletin5.getNoticias().toArray(),
				boletin1.getSubconjuntoCategoriaFecha(EnumCategoria.CULTURA, fechaPublicacion).getNoticias().toArray());
		Boletin test5 = boletin1.getSubconjuntoCategoriaFecha(EnumCategoria.CULTURA, fechaPublicacion3);
		assertEquals(1, test5.getNumberOfNoticias());

	}
}