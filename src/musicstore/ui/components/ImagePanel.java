/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstore.ui.components;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author jablo
 */
public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {                
    }

    public void SetImagePath(String path)
    {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double scale;
        if(image.getHeight() > image.getWidth())
            scale = (double)this.getHeight()/image.getHeight();
        else
            scale = (double)this.getWidth()/image.getWidth();
        g.drawImage(image, 0, 0, (int)(scale*image.getWidth()), (int)(scale*image.getHeight()), null); // see javadoc for more info on the parameters

        
    }

}
