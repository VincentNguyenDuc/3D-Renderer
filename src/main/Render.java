package main;

import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.*;
import java.util.List;
import java.awt.geom.*;

public class Render {
    Render(){}

    public static JPanel renderPanel(JSlider headingSlider, JSlider pitchSlider) {
        /*
         * rendered the objects into a JPanel
         */
        return new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.translate(getWidth() / 2, getHeight() / 2);
                g2.setColor(Color.WHITE);

                // transform
                Matrix3 transform = transformCalculator(headingSlider, pitchSlider);

                // rendering will happen here
                List<Triangle> triangles = Triangle.generateTriangle();

                for (Triangle t : triangles) {
                    Vertex v1 = transform.transform(t.v1);
                    Vertex v2 = transform.transform(t.v2);
                    Vertex v3 = transform.transform(t.v3);
                    Path2D path = new Path2D.Double();
                    path.moveTo(v1.x, v1.y);
                    path.lineTo(v2.x, v2.y);
                    path.lineTo(v3.x, v3.y);
                    path.closePath();
                    g2.draw(path);
                }
            }
        };
    }

    private static Matrix3 transformCalculator(JSlider headingSlider, JSlider pitchSlider) {
        /*
         * Calculate the transform matrix from heading slider and pitch slider
         */
        double heading = Math.toRadians(headingSlider.getValue());
        Matrix3 headingTransform = new Matrix3(new double[] {
                Math.cos(heading), 0, -Math.sin(heading),
                0, 1, 0,
                Math.sin(heading), 0, Math.cos(heading)
        });
        double pitch = Math.toRadians(pitchSlider.getValue());
        Matrix3 pitchTransform = new Matrix3(new double[] {
                1, 0, 0,
                0, Math.cos(pitch), Math.sin(pitch),
                0, -Math.sin(pitch), Math.cos(pitch)
        });
        return headingTransform.multiply(pitchTransform);

    }


}
