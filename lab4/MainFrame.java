import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainFrame implements ItemListener {
    public static final JFrame frame = new JFrame ("Cardinal Spline");
    public static JPanel panel = new JPanel();
    public static JLabel menuLabel = new JLabel("Point");
    public static String[] menuButtons = { "X0", "X1", "X2", "X3", "X4", "X5"};
    public static JComboBox menuList = new JComboBox(menuButtons);
    public static JSlider XSlider = new JSlider(-100, 100);
    public static JSlider YSlider = new JSlider(-100, 100);
    public static JLabel XLabel = new JLabel("X");
    public static JLabel YLabel = new JLabel("Y");

    public static JLabel weightLabel = new JLabel("Weight");
    public static JSlider weightSlider = new JSlider(-500, 500);
    static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    public static int CURRENT_POINT;

    public static float X_NUMBER;
    public static float Y_NUMBER;
    public static float WEIGHT = -2.5f;


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == menuList){
            CURRENT_POINT = menuList.getSelectedIndex();

//            if (!(CURRENT_POINT == 0 || CURRENT_POINT == 1 || CURRENT_POINT == 2)) {
//                CURRENT_POINT++;
//            }

            float X = MainEventListener.points.get(CURRENT_POINT).getX();
            X_NUMBER = X;
            XSlider.setValue((int) (X * 100));

            float Y = MainEventListener.points.get(CURRENT_POINT).getY();
            Y_NUMBER = Y;
            YSlider.setValue((int) (Y * 100));
        }

    }

    public static void main(String[] args) {
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        MainEventListener.points.add(new Point(-0.866f, -0.27f, true)); // A - control
        MainEventListener.points.add(new Point(-0.68f, 0.52f, true)); // B - control
        MainEventListener.points.add(new Point(-0.086f, -0.406f, true)); // C - control
        MainEventListener.points.add(new Point(0.235f, 0.26f, true)); // D - control
        MainEventListener.points.add(new Point(0.544f, 0.716f, true)); // E - control
        MainEventListener.points.add(new Point(0.915f, 0.46f, true)); // F - control

        MainEventListener mainEventListener = new MainEventListener();
        glcanvas.addGLEventListener(mainEventListener);

        MainFrame mainFrame = new MainFrame();
        glcanvas.setSize(500, 500);

        frame.add(panel, BorderLayout.EAST);
        frame.getContentPane().add(glcanvas, BorderLayout.CENTER);

        menuList.addItemListener(mainFrame);
        menuList.setSelectedIndex(0);

        menuList.setMaximumSize(new Dimension(200,30));

        XSlider.addChangeListener(e -> {
            X_NUMBER = (float) ((JSlider)e.getSource()).getValue() / 100;
            MainEventListener.points.get(CURRENT_POINT).setX(X_NUMBER);
            glcanvas.display();
        });

        YSlider.addChangeListener(e -> {
            Y_NUMBER = (float) ((JSlider)e.getSource()).getValue() / 100;
            MainEventListener.points.get(CURRENT_POINT).setY(Y_NUMBER);
            glcanvas.display();
        });

        weightSlider.addChangeListener(e -> {
            WEIGHT = (float) ((JSlider)e.getSource()).getValue() / 100;
            glcanvas.display();
        });

        panel.add(menuLabel);
        panel.add(menuList);
        panel.add(XLabel);
        panel.add(XSlider);
        panel.add(YLabel);
        panel.add(YSlider);
        panel.add(weightLabel);
        panel.add(weightSlider);

        frame.setVisible(true);
    }
}