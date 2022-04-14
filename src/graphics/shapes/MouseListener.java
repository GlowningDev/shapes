package graphics.shapes;
import graphics.shapes.attributes.ColorAttributes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MouseListener {
        private JLabel label;
        public MouseListener() {
            label.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt,Graphics g, ColorAttributes ca) {
                    g.setColor(Color.black);
                }
                public void mouseExited(MouseEvent evt, Graphics g, ColorAttributes ca) {
                    g.setColor(ca.filledColor);
                }
            });
        }
}
