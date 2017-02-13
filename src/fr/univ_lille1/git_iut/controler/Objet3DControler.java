package fr.univ_lille1.git_iut.controler;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javafx.scene.input.MouseButton;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.univ_lille1.git_iut.model.FigureModel;
import fr.univ_lille1.git_iut.observer.Gui3D;
import fr.univ_lille1.git_iut.observer.Objet3D;

/**
 * Controleur notifie le modele lors d'un appui ou d'une modification des
 * boutons ou des sliders
 * 
 * @author plouvinm
 *
 */
public class Objet3DControler extends AbstractController implements
		ActionListener, ChangeListener, MouseListener, MouseMotionListener {
	Point lastPosition = new Point(0, 0);

	public Objet3DControler(FigureModel model, Gui3D gui) {
		super(model, gui);
	}

	/**
	 * permet de lancer le zoom ou le dezoom selon le bouton qui a ete presse
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gui.btnZoomP) {
			model.zoom(1.25);
		}
		if (e.getSource() == gui.btnZoomM) {
			model.zoom(0.8);
		}
		if (e.getSource() == gui.btnFaces) {
			if (model.getOption().equals("-f -s")
					|| model.getOption().equals("-s -f")) {
				model.setOption("-s");
			} else if (model.getOption().equals("-s")) {
				model.setOption("-f -s");
			} else if (model.getOption().equals("")) {
				model.setOption("-f");
			} else {
				model.setOption("");
			}
		}
		if (e.getSource() == gui.btnSegments) {
			if (model.getOption().equals("-f -s")
					|| model.getOption().equals("-s -f")) {
				model.setOption("-f");
			} else if (model.getOption().equals("-f")) {
				model.setOption("-f -s");
			} else if (model.getOption().equals("")) {
				model.setOption("-s");
			} else {
				model.setOption("");
			}
		}
		if (e.getSource() == gui.btnCentrer) {
			model.centre();
		}
		if (e.getSource() == gui.btnShadow) {
			model.switchValueOmbre();
		}
	}

	/**
	 * permet de lancer la rotation ou la translation lors d'un appui sur les
	 * boutons
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == gui.slideRotationX) {
			double value = (double) ((JSlider) e.getSource()).getValue();
			model.rotation(value - gui.oldRotation, 1);
			gui.oldRotation = value;
		}
		if (e.getSource() == gui.slideRotationY) {
			double value = (double) ((JSlider) e.getSource()).getValue();
			model.rotation(value - gui.oldRotation, 2);
			gui.oldRotation = value;
		}
		if (e.getSource() == gui.slideRotationZ) {
			double value = (double) ((JSlider) e.getSource()).getValue();
			model.rotation(value - gui.oldRotation, 3);
			gui.oldRotation = value;
		}
		if (e.getSource() == gui.sliTranslationX) {
			double transX = (double) ((JSlider) e.getSource()).getValue() / 100;
			model.translation((transX - gui.oldTransX) * 500, 0);
			gui.oldTransX = transX;
		}
		if (e.getSource() == gui.sliTranslationY) {
			double transY = (double) ((JSlider) e.getSource()).getValue() / 100;
			model.translation(0, (transY - gui.oldTransY) * 500);
			gui.oldTransY = transY;
		}
	}

	/**
	 * detecte la fermeture ou la reduction de la fenetre
	 */
	public void mouseClicked(MouseEvent e) {
		// Detecte fermeture et reduction de fenetre
		if (e.getX() > gui.o.getWidth() - 30 && e.getY() < 29)
			System.exit(JFrame.DISPOSE_ON_CLOSE);
		else if (e.getX() > gui.o.getWidth() - 60 && e.getY() < 29)
			gui.setExtendedState(JFrame.HIDE_ON_CLOSE);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			Point p = e.getPoint();
			model.rotation(p.getX() - lastPosition.getX(), 2);
			model.rotation(p.getY() - lastPosition.getY(), 1);
			lastPosition = p;
		} else if (SwingUtilities.isRightMouseButton(e)) {
			Point p = e.getPoint();
			model.translation(p.getX() - lastPosition.getX(), p.getY()
					- lastPosition.getY());
			lastPosition = p;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		lastPosition = e.getPoint();
	}
}
