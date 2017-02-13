package fr.univ_lille1.git_iut.main;

import java.io.IOException;

import fr.univ_lille1.git_iut.controler.Objet3DControler;
import fr.univ_lille1.git_iut.model.FigureModel;
import fr.univ_lille1.git_iut.observer.Gui3D;

public class Main {
	/**
	 * Main, a excuter pour lancer le programme
	 * 
	 * @author plouvinm
	 *
	 */
	public static void main(String[] args) {

		FigureModel model = null;
		try {
			model = new FigureModel(/* args[0] */"data/shark.ply");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// initialisation mvc
		Gui3D vue = new Gui3D(model);
		Objet3DControler controler = new Objet3DControler(model, vue);

		// initialisation des listener pour mvc
		vue.o.addMouseListener(controler);
		vue.btnZoomM.addActionListener(controler);
		vue.btnZoomP.addActionListener(controler);
		vue.btnFaces.addActionListener(controler);
		vue.btnSegments.addActionListener(controler);
		vue.btnShadow.addActionListener(controler);
		vue.btnCentrer.addActionListener(controler);
		vue.slideRotationX.addChangeListener(controler);
		vue.slideRotationY.addChangeListener(controler);
		vue.slideRotationZ.addChangeListener(controler);
		vue.sliTranslationX.addChangeListener(controler);
		vue.sliTranslationY.addChangeListener(controler);
		vue.o.addMouseMotionListener(controler);
	}
}
