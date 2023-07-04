import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.stream.Stream;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;

public class MainFrame extends JFrame implements GLEventListener, ItemListener {
    public static int CURRENT_PRIMITIVE = GL2.GL_QUAD_STRIP;
    public static int CURRENT_ALPHA = GL2.GL_ALWAYS;
    public static float TRANSPARENCY_NUMBER = 0.5f;
    public static int SCISSOR_X_NUMBER = 0;
    public static int SCISSOR_Y_NUMBER = 0;
    public static int SFACTOR = GL2.GL_ONE;
    public static int DFACTOR = GL2.GL_ONE;

    public static String[] menuButtons = { "GL_POINTS", "GL_LINES",
            "GL_LINE_LOOP", "GL_LINE_STRIP", "GL_TRIANGLES", "GL_TRIANGLE_STRIP", "GL_TRIANGLE_FAN", "GL_QUADS",
            "GL_QUAD_STRIP", "GL_POLYGON"};

    public static String[] transparencyButtons = {"GL_NEVER", "GL_LESS", "GL_EQUAL", "GL_LEQUAL",
            "GL_GREATER", "GL_NOTEQUAL", "GL_GEQUAL", "GL_ALWAYS"};


    public static SFactor[] sfactor = SFactor.values();
    public static DFactor[] dfactor = DFactor.values();

    public static final JFrame frame = new JFrame ("Primitives");
    public static JComboBox menuList = new JComboBox(menuButtons);
    public static JComboBox transparencyList = new JComboBox(transparencyButtons);
    public static JComboBox sfactorList;
    public static JComboBox dfactorList;
    public static JSlider transparencySlider = new JSlider(0, 10, 5);
    public static JSlider scissorXSlider = new JSlider(0, 800, 0);
    public static JSlider scissorYSlider = new JSlider(0, 600, 0);
    public static JLabel menuLabel = new JLabel("Примитив");
    public static JLabel transparencyLabel = new JLabel("Тест прозрачности");
    public static JLabel scissorLabel = new JLabel("Тест отсечения");
    public static JLabel blendLabel = new JLabel("Тест смешения цветов");
    public static JLabel XLabel = new JLabel("X");
    public static JLabel YLabel = new JLabel("Y");
    public static JLabel sfactorLabel = new JLabel("sfactor");
    public static JLabel dfactorLabel = new JLabel("dfactor");
    public static JPanel panel = new JPanel();

    static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    static int width;
    static int height;

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glViewport(0, 0, 600, 500);

        gl.glViewport(glcanvas.getWidth() - 549, glcanvas.getHeight() - 463, 600, 500);

        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        System.out.println(glcanvas.getX() + " " + glcanvas.getY());
        System.out.println(glcanvas.getWidth() + " " + glcanvas.getHeight());

        gl.glEnable(GL2.GL_ALPHA_TEST);
        gl.glEnable(GL2.GL_BLEND);
        gl.glEnable(GL2.GL_SCISSOR_TEST);

        gl.glScissor(SCISSOR_X_NUMBER, SCISSOR_Y_NUMBER, 800 - SCISSOR_X_NUMBER, 600 - SCISSOR_Y_NUMBER);
        gl.glAlphaFunc(CURRENT_ALPHA, TRANSPARENCY_NUMBER);
        gl.glBlendFunc(SFACTOR, DFACTOR);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glBegin(CURRENT_PRIMITIVE);
        gl.glColor4d(0.191,0.184,0.998, 0.8);
        gl.glVertex2d(-0.2,  0.4);
        gl.glVertex2d( 0.6,  0.4);
        gl.glColor4d(0.129,0.686,0.768, 0.4);
        gl.glVertex2d( 1, 0.0);
        gl.glVertex2d( 0.6, -0.4);
        gl.glVertex2d(-0.2, -0.4);
        gl.glColor4d(0.905,0.705,0.980, 0.2);
        gl.glVertex2d( -0.6, 0.0);
        gl.glEnd();

        gl.glDisable(GL2.GL_ALPHA_TEST);
        gl.glDisable(GL2.GL_SCISSOR_TEST);
        gl.glDisable(GL2.GL_BLEND);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int width, int height) {

//        GL2 gl = glAutoDrawable.getGL().getGL2();
//        gl.glViewport(i, i1, i2, i3);
////        width = i2;
////        height = i3;

//        GL2 gl = glAutoDrawable.getGL().getGL2();
//        gl.glViewport(0, 0, width, height);

//        int w = width / 2;
//        int h = height / 2;
//        gl.glOrtho(-1 * w, w, -1 * h, h, -1000, 1000);
//        gl.glMatrixMode(gl.GL_MODELVIEW);
//        gl.glLoadIdentity();
//        int d = Math.min(w, h);

    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable arg0) {
        // method body
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == menuList){
            CURRENT_PRIMITIVE = menuList.getSelectedIndex();
        }

        if (e.getSource() == transparencyList){
            CURRENT_ALPHA = transparencyList.getSelectedIndex() + 512;
        }

        if (e.getSource() == sfactorList){
            Stream.of(sfactor).forEach(entity -> {
                if (entity.getName() == sfactorList.getSelectedItem().toString()){
                    SFACTOR = entity.getNumber();
                }
            });
        }

        if (e.getSource() == dfactorList){
            Stream.of(dfactor).forEach(entity -> {
                if (entity.getName() == dfactorList.getSelectedItem().toString()){
                    DFACTOR = entity.getNumber();
                }
            });
        }

        glcanvas.display();
    }

    public static void main(String[] args) {
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        MainFrame mainFrame = new MainFrame();
        glcanvas.addGLEventListener(mainFrame);
        glcanvas.setSize(500, 500);

        ArrayList<String> sfactorButtons = new ArrayList<>();
        Stream.of(sfactor).forEach(entity -> sfactorButtons.add(entity.getName()));
        String[] stringSF = sfactorButtons.toArray(new String[9]);
        sfactorList = new JComboBox(stringSF);

        ArrayList<String> dfactorButtons = new ArrayList<>();
        Stream.of(dfactor).forEach(entity -> dfactorButtons.add(entity.getName()));
        String[] stringDF = dfactorButtons.toArray(new String[8]);
        dfactorList = new JComboBox(stringDF);

        menuList.addItemListener(mainFrame);
        menuList.setSelectedIndex(0);

        transparencyList.addItemListener(mainFrame);
        transparencyList.setSelectedIndex(0);

        sfactorList.addItemListener(mainFrame);
        sfactorList.setSelectedIndex(0);

        dfactorList.addItemListener(mainFrame);
        dfactorList.setSelectedIndex(0);

        transparencySlider.addChangeListener(e -> {
            TRANSPARENCY_NUMBER = (((JSlider)e.getSource()).getValue()* 1.0f) / 10;
            glcanvas.display();
        });

        scissorXSlider.addChangeListener(e -> {
            SCISSOR_X_NUMBER = ((JSlider)e.getSource()).getValue();
            System.out.println(SCISSOR_X_NUMBER);
            glcanvas.display();
        });

        scissorYSlider.addChangeListener(e -> {
            SCISSOR_Y_NUMBER = ((JSlider)e.getSource()).getValue();
            System.out.println(SCISSOR_Y_NUMBER);
            glcanvas.display();
        });

        menuList.setMaximumSize(new Dimension(200,30));
        transparencyList.setMaximumSize(new Dimension(200,30));
        menuLabel.setMaximumSize(new Dimension(200,30));
        transparencyLabel.setMaximumSize(new Dimension(200,30));
        dfactorList.setMaximumSize(new Dimension(200,30));
        sfactorList.setMaximumSize(new Dimension(200,30));

        panel.add(menuLabel);
        panel.add(menuList);
        panel.add(transparencyLabel);
        panel.add(transparencyList);
        panel.add(transparencySlider);
        panel.add(scissorLabel);
        panel.add(XLabel);
        panel.add(scissorXSlider);
        panel.add(YLabel);
        panel.add(scissorYSlider);
        panel.add(blendLabel);
        panel.add(sfactorLabel);
        panel.add(sfactorList);
        panel.add(dfactorLabel);
        panel.add(dfactorList);

        frame.add(panel, BorderLayout.WEST);
        frame.getContentPane().add(glcanvas, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
