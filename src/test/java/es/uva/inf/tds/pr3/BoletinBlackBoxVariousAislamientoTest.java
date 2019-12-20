package es.uva.inf.tds.pr3;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.Mock;
import static org.easymock.EasyMock.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr3.Boletin;
import es.uva.inf.tds.pr3.EnumCategoria;
import es.uva.inf.tds.pr3.INoticia;

public class BoletinBlackBoxVariousAislamientoTest {

	private LocalDate fechaPublicacion;
	@Mock
	private INoticia in;

	private LocalDate fechaPublicacion2;
	@Mock
	private INoticia in2;

	private INoticia in3;

	@BeforeEach
	public void setUpCategorias() {
		fechaPublicacion = LocalDate.of(2019, 11, 14);

		fechaPublicacion2 = LocalDate.of(2019, 11, 15);

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
		expect(in.getCategoria()).andReturn(EnumCategoria.ECONOMIA).anyTimes();
		expect(in2.getCategoria()).andReturn(EnumCategoria.DEPORTE).anyTimes();
		expect(in3.getCategoria()).andReturn(EnumCategoria.CULTURA).anyTimes();
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
		expect(in.getCategoria()).andReturn(EnumCategoria.ECONOMIA).anyTimes();
		expect(in2.getCategoria()).andReturn(EnumCategoria.ECONOMIA).anyTimes();
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
