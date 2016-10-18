/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Point;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author sala308b
 */
public class Utils {
    
    private static boolean isAuth = false;
    
    public static void  Autenticado(boolean b){
        isAuth = b;
    }
    
    public static boolean isAuth(){
        return isAuth;
    }
   
    public static Point centralizarFormInterno(JInternalFrame filho, JDesktopPane pai){
        int width, height;
        width = (pai.getWidth() - filho.getWidth()) / 2;
        height = (pai.getHeight() - filho.getHeight()) / 2;
      
        return new Point(width,height);
    }
}
