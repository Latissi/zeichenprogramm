/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeichenprogramm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import zeichenprogramm.model.Figur;
import zeichenprogramm.model.GrafikModel;

/**
 *
 * @author le
 */
public class GrafikView extends JComponent implements Printable
{
  private final static Dimension EINS = new Dimension(1, 1);
  private final static Logger lg = Logger.getLogger("mvcGrafik");
  private Rectangle2D.Float pixel;
  private Line2D.Float line;
  private GrafikModel model;
  private JPopupMenu popup;
  private JMenuItem druckenItem;
  private JMenuItem speichernItem;

  public GrafikView()
  {
    pixel = new Rectangle2D.Float();
    line = new Line2D.Float();
    this.setBackground(Color.WHITE);
    popup = new JPopupMenu();
    druckenItem = new JMenuItem("Drucken");
    popup.add(druckenItem);
    speichernItem = new JMenuItem("Speichern");
    popup.add(speichernItem);
  }
  
  public JMenuItem getMenuItemDrucken(){
      return druckenItem;
  }
  
  public JMenuItem getMenuItemSpeichern(){
      return speichernItem;
  }

  public void setModel(GrafikModel model)
  {
    this.model = model;
  }

  public void drawPoint(Point p)
  {
    Graphics2D g2 = (Graphics2D) this.getGraphics();
    pixel.setFrame(p, EINS);
    g2.draw(pixel);
    /*int letzterIndexFiguren = model.getFiguren().size() - 1;
    int letzterIndexPoint = model.getFiguren().get(letzterIndexFiguren)
                            .getPunkte().size() - 1;
    line.setLine(p, model.getFiguren().get(letzterIndexFiguren)
                .getPunkte().get(letzterIndexPoint));
    g2.draw(line);*/
    g2.dispose(); // VERY, VERY WICHTIG
  }

  @Override
  public void paintComponent(Graphics g)
  {
    if (model == null)
    {
      lg.severe("keine Referenz auf Model vorhanden");
      return;
    }
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    for (int i=0; i<model.getFiguren().size(); i++)
    {
      for (int j=0; j<(model.getFiguren().get(i).getPunkte().size())-1;j++)
      {
      pixel.setFrame(model.getFiguren().get(i).getPunkte().get(j), EINS);
      g2.draw(pixel);
      
      line.setLine(model.getFiguren().get(i).getPunkte().get(j),
                   model.getFiguren().get(i).getPunkte().get(j+1));
      g2.draw(line);
      }
    }
  }
  
  public void doPrint()
  {
    HashPrintRequestAttributeSet printSet = 
     new HashPrintRequestAttributeSet();
    printSet.add(DialogTypeSelection.NATIVE);
    PrinterJob pj = PrinterJob.getPrinterJob();
    pj.setPrintable(this);
    // Druckdialog
    if (pj.printDialog(printSet))
    {
      try
      {
        pj.print(printSet);
      }
      catch (Exception ex)
      {
        JOptionPane.showMessageDialog(this, ex.toString());
      }
    }
  }

  @Override
  public int print(Graphics gp, PageFormat pf, int pageIndex) throws PrinterException
  {
    Graphics2D g2p = (Graphics2D) gp;
    if (pageIndex == 0)
    {
      g2p.translate(0.1*pf.getWidth(), 0.1 * pf.getHeight());
      System.out.println(pf.getImageableX());
      //Hat mit vorherhigen Einstellungen (getImageableX) falsch skaliert!
      g2p.scale(pf.getWidth()/(1.2* this.getWidth()), 
                pf.getHeight()/(1.2*this.getHeight()));
      System.out.println(pf.getWidth()/(1.2*this.getWidth()));
      super.print(g2p);
      return Printable.PAGE_EXISTS;
    }
    else
    {
      return Printable.NO_SUCH_PAGE;
    }
  }

}
