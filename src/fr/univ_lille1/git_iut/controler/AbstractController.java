package fr.univ_lille1.git_iut.controler;

import fr.univ_lille1.git_iut.model.FigureModel;
import fr.univ_lille1.git_iut.observer.Gui3D;

/**
 * Classe abstraite du(des) controleur(s)
 *  
 * @author plouvinm
 *
 */
public abstract class AbstractController {
	protected FigureModel model;
	protected Gui3D gui;

    /**
 * Constructeur de la classe 
 * instancie les variables
 * 	
 * @param model le modele a observer
 * @param gui
 */
	public AbstractController(FigureModel model,Gui3D gui) {
		this.model=model;
		this.gui=gui;
	}
}
