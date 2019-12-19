package es.uva.inf.tds.pr2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BoletinWhiteBoxTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Tag("WhiteBox")
	@Tag("Negative")
	@Test
	public void boletinArrayNulo() {
		ArrayList<INoticia> nulo = null;
		assertThrows(IllegalArgumentException.class, () -> {
			Boletin boletinFallo = new Boletin(nulo);
		});
	}

	@Tag("WhiteBox")
	@Tag("Negative")
	@Test
	public void subconjuntoIntervaloIncorrecto() {
		Boletin boletin = new Boletin();
		LocalDate inicioIntervalo = LocalDate.of(2018, 1, 1);
		LocalDate finalIntervalo = LocalDate.of(2019, 12, 31);

		assertThrows(IllegalArgumentException.class, () -> {
			boletin.getSubconjuntoIntervalo(finalIntervalo, inicioIntervalo);
		});
	}

}
