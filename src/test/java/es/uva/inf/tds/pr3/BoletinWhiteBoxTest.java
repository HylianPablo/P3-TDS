package es.uva.inf.tds.pr3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import es.uva.inf.tds.pr3.Boletin;
import es.uva.inf.tds.pr3.EnumCategoria;
import es.uva.inf.tds.pr3.INoticia;
import es.uva.inf.tds.pr3.Noticia;

class BoletinWhiteBoxTest {

	@Tag("WhiteBox")
	@Tag("Negative")
	@Test
	public void boletinArrayNulo() {
		ArrayList<INoticia> nulo = null;
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Boletin boletinFallo = new Boletin(nulo);
		});
	}

	@Tag("WhiteBox")
	@Tag("Negative")
	@Test
	public void subconjuntoIntervaloInverso() {
		Boletin boletin = new Boletin();
		LocalDate inicioIntervalo = LocalDate.of(2018, 1, 1);
		LocalDate finalIntervalo = LocalDate.of(2019, 12, 31);

		assertThrows(IllegalArgumentException.class, () -> {
			boletin.getSubconjuntoIntervalo(finalIntervalo, inicioIntervalo);
		});
	}

	@Tag("WhiteBox")
	@Tag("Negative")
	@Test
	public void subconjuntoIntervaloCategoriaMal() {
		String titular = "Hola";
		LocalDate fechaPublicacion = LocalDate.of(2018, 11, 20);
		String fuente = "Adios";
		EnumCategoria categoria = EnumCategoria.NACIONAL;
		String url = "https://www." + fuente + '/' + categoria + '/' + titular;
		Noticia n = new Noticia(titular, fechaPublicacion, fuente, url, categoria);

		Boletin b = new Boletin();

		LocalDate inicioIntervalo = LocalDate.of(2019, 1, 1);
		LocalDate finalIntervalo = LocalDate.of(2019, 12, 31);
		EnumCategoria c = EnumCategoria.NACIONAL;

		Boletin b2 = new Boletin();
		b2.addNoticia(n);

		assertArrayEquals(b.getNoticias().toArray(),
				b2.getSubconjuntoCategoriaIntervalo(c, inicioIntervalo, finalIntervalo).getNoticias().toArray());

	}

}
