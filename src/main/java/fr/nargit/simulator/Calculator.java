package fr.nargit.simulator;

import fr.nargit.simulator.core.Level;
import fr.nargit.simulator.core.Simulable;

/**
 * (c) Swissquote 20-juin-2018
 *
 * @author Tigran
 */
public interface Calculator {

	@Simulable(display = Level.ALL, identifier = "calcul#add")
	int add(int a, int b);
}
