package main;

import IuEscritorio.IUGraficaMenu;
import logica.DatosPrueba;

public class Inicio {

     public static void main(String[] args) throws Exception {
        DatosPrueba.cargar();
        IUGraficaMenu menu = new IUGraficaMenu();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
      } 
}
