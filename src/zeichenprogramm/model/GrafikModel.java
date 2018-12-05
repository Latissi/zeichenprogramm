/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zeichenprogramm.model;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author le
 */
public class GrafikModel
{
  private ArrayList<Figur> figuren;
  
  public GrafikModel()
  {
    figuren = new ArrayList<>();
    this.addFigur();
  }
  
  public void addPoint(Point p)
  {
    figuren.get(figuren.size()-1).addPunkte(p);
  }
  
  public void addFigur()
  {
    figuren.add(new Figur());
  }
 

  public List<Figur> getFiguren()
  {
    return Collections.unmodifiableList(figuren);
  }
  
  public void speicherePunkte(String dateiname) throws FileNotFoundException, IOException
  {
    FileOutputStream fos = new FileOutputStream(dateiname);
    BufferedOutputStream buffout = new BufferedOutputStream(fos);
    ObjectOutputStream oos = new ObjectOutputStream(buffout);
    oos.writeObject(figuren);
    oos.flush();
    oos.close();
  }
  
  public void ladePunkte(String dateiname) throws FileNotFoundException, IOException, ClassNotFoundException
  {
    FileInputStream fis = new FileInputStream(dateiname);
    BufferedInputStream buffin = new BufferedInputStream(fis);
    ObjectInputStream ois = new ObjectInputStream(buffin);
    Object obj = ois.readObject();
//     if (obj instanceof ArrayList)
//     {
//       figuren = (ArrayList<Point>) obj;
//     }
//     else
//     {
//       Fehler ....
//     }
    figuren =  (ArrayList<Figur>) ois.readObject();
    ois.close();
  }
  
}
