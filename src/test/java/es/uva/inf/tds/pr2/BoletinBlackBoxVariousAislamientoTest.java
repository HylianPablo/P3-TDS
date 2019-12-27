package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.Mock;
import static org.easymock.EasyMock.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr2.Boletin;
import es.uva.inf.tds.pr2.EnumCategoria;
import es.uva.inf.tds.pr2.Noticia;

public class BoletinBlackBoxVariousAislamientoTest {

	private String titular;
	private LocalDate fechaPublicacion;
	private String fuente;
	private EnumCategoria categoria;
	private String url;
	private INoticia in;

	private String titular2;
	private LocalDate fechaPublicacion2;
	private String fuente2;
	private EnumCategoria categoria2;
	private String url2;
	private INoticia in2;

	private String titular3;
	private LocalDate fechaPublicacion3;
	private String fuente3;
	private EnumCategoria categoria3;
	private String url3;
	private INoticia in3;

	@BeforeEach
	public void setUpCategorias() {
		titular = "Hola";
		fechaPublicacion = LocalDate.of(2019, 11, 14);
		fuente = "Adios";
		categoria = EnumCategoria.nacional;
		url = "https://www." + fuente + '/' + categoria + '/' + titular;

		titular2 = "Hola2";
		fechaPublicacion2 = LocalDate.of(2019, 11, 15);
		fuente2 = "Adios2";
		categoria2 = EnumCategoria.internacional;
		url2 = "https://www." + fuente2 + '/' + categoria2 + '/' + titular2;

		titular3 = "Hola3";
		fechaPublicacion3 = LocalDate.of(2019, 11, 16);
		fuente3 = "Adios3";
		categoria3 = EnumCategoria.sociedad;
		url3 = "https://www." + fuente3 + '/' + categoria3 + '/' + titular3;

		in = createMock(INoticia.class);
		in2 = createMock(INoticia.class);
		in3 = createMock(INoticia.class);
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void noticiasSimilaresMismaCat() {
		Boletin b = new Boletin();

		expect(in.isSimilar(in2)).andReturn(true).anyTimes();
		expect(in3.isSimilar(in2)).andReturn(false).anyTimes();
		replay(in);
		replay(in3);

		b.addNoticia(in);
		b.addNoticia(in3);

		ArrayList<INoticia> al = new ArrayList<>();
		al.add(in);

		assertArrayEquals(al.toArray(), b.getSimilarNews(in2).toArray());
		verify(in);
		verify(in3);
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void listaCategoriasEDC() {
		fechaPublicacion = LocalDate.of(2019, 6, 15);
		fechaPublicacion2 = LocalDate.of(2018, 1, 1);

		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in3.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in.getCategoria()).andReturn(EnumCategoria.economia).anyTimes();
		expect(in2.getCategoria()).andReturn(EnumCategoria.deporte).anyTimes();
		expect(in3.getCategoria()).andReturn(EnumCategoria.cultura).anyTimes();
		replay(in);
		replay(in2);
		replay(in3);

		ArrayList<INoticia> todas = new ArrayList<>();

		todas.add(in);
		todas.add(in2);
		todas.add(in3);

		Boletin validas = new Boletin();
		validas.addNoticia(in3);
		validas.addNoticia(in);
		validas.addNoticia(in2);

		assertArrayEquals(todas.toArray(), validas.getNewsByCategory().toArray());
		verify(in);
		verify(in2);
		verify(in3);
	}

	@Tag("Positive")
	@Tag("ArrayEquals")
	@Tag("BlackBoxTestFirst")
	@Tag("Isolation")
	@Test
	public void listaCategoriasIguales() {
		fechaPublicacion = LocalDate.of(2019, 6, 15);
		fechaPublicacion2 = LocalDate.of(2018, 1, 1);

		expect(in.getFechaPublicacion()).andReturn(fechaPublicacion).anyTimes();
		expect(in2.getFechaPublicacion()).andReturn(fechaPublicacion2).anyTimes();
		expect(in.getCategoria()).andReturn(EnumCategoria.economia).anyTimes();
		expect(in2.getCategoria()).andReturn(EnumCategoria.economia).anyTimes();
		replay(in);
		replay(in2);

		ArrayList<INoticia> todas = new ArrayList<>();

		todas.add(in2);
		todas.add(in);

		Boletin validas = new Boletin();
		validas.addNoticia(in);
		validas.addNoticia(in2);

		assertArrayEquals(todas.toArray(), validas.getNewsByCategory().toArray());
		verify(in);
		verify(in2);
	}

}
