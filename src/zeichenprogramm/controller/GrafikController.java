/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zeichenprogramm.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import zeichenprogramm.model.GrafikModel;
import zeichenprogramm.view.GrafikView;

/**
 *
 * @author le
 */
public class GrafikController implements MouseMotionListener, MouseListener
{
  private GrafikView view;
  private GrafikModel model;
  
  public GrafikController(GrafikView view, GrafikModel model)
  {
    this.view = view;
    this.model = model;
  }

  public void registerEvents()
  {
    view.addMouseMotionListener(this);
    view.addMouseListener(this);
  }
  
  @Override
  public void mouseDragged(MouseEvent evt)
  {
    Point p = evt.getPoint();
    
    view.drawPoint(p);
    model.addPoint(p);
  }

  @Override
  public void mouseMoved(MouseEvent e)
  {
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
  }

  @Override
  public void mouseReleased(MouseEvent evt)
  {
    if (evt.getButton() == MouseEvent.BUTTON3)
    {
      view.doPrint();
    }
    model.addFigur();
    view.repaint();
  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
  }

  @Override
  public void mouseExited(MouseEvent e)
  {
  }
}
