/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zeichenprogramm.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author nobody
 */
public class Figur
{
  private ArrayList<Point> punkte; 
  
  public Figur()
  {
    punkte = new ArrayList<>();
  }
  
  public void addPunkte(Point p)
  {
    punkte.add(p);
  }
  
  public List<Point> getPunkte()
  {
    return Collections.unmodifiableList(punkte);
  }
}
