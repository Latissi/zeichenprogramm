/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zeichenprogramm;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.WindowConstants;
import zeichenprogramm.controller.GrafikController;
import zeichenprogramm.model.GrafikModel;
import zeichenprogramm.view.GrafikView;

/**
 * Builder Class
 * @author le
 */
public class Start
{
  public Start()
  {
    JFrame frm = new JFrame("Zeichenprogramm");
    frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    GrafikView view = new GrafikView();
    GrafikModel model = new GrafikModel();
    view.setModel(model);
    GrafikController controller = new GrafikController(view, model);
    controller.registerEvents();
    frm.setContentPane(view);
    frm.setSize(800, 600);
    frm.setVisible(true);

  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) 
  {
    new Start();
  }
}
