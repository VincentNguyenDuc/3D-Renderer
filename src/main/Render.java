package main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import java.awt.geom.*;

public class Render {

    Render(){}

    public static JPanel renderPanel() {
        return new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.translate(getWidth() / 2, getHeight() / 2);
                g2.setColor(Color.WHITE);

                // Triangles rendering
                renderTriangles(g2);
            }
        };
    }

    private static void renderTriangles(Graphics2D g2) {

        List<Triangle> triangles = Triangle.generateTriangle();

        for (Triangle t : triangles) {
            Path2D path = new Path2D.Double();
            path.moveTo(t.v1.x, t.v1.y);
            path.lineTo(t.v2.x, t.v2.y);
            path.lineTo(t.v3.x, t.v3.y);
            path.closePath();
            g2.draw(path);
        }

    }
}
