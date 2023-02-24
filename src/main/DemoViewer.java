package main;

import javax.swing.*;
import java.awt.*;


public class DemoViewer {

    DemoViewer(){}

    public static void viewDemo() {

        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        // slider to control horizontal rotation
        JSlider headingSlider = new JSlider(0, 360, 180);
        pane.add(headingSlider, BorderLayout.SOUTH);

        // slider to control vertical rotation
        JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pitchSlider, BorderLayout.EAST);

        // panel to display render results
        pane.add(Render.renderPanel(), BorderLayout.CENTER);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }

}