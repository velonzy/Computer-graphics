import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;

public class MainFrame extends JFrame implements GLEventListener, ItemListener {

    public static float color = 0.0f;

    public static final JFrame frame = new JFrame ("Фрактал");
    public static JPanel panel = new JPanel();
    static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    static float Radius = 1.0f;
    static float ScalingX = 1.0f;
    static float ScalingY = 1.0f;
    static int depth = 1;
    static int step = 500;

    ArrayList<Circle> circlesList = new ArrayList<>();


    public static void drawCircle(GL2 gl, float radius, float oriX, float oriY){
        for (int i = 0; i <= step; i++) {
            float angle = 2 * 3.1415f * i / step;
            float x = (float) (Math.cos(angle) * radius);
            float y = (float) (Math.sin(angle) * radius);
//            gl.glColor4d(0.388 - (color / 2),1 - color,0.941 - color, 1);
            gl.glColor4d(0.4196f, 1, 1 - color, 1);
            gl.glVertex2d(oriX + x, oriY + y);
        }
        
//        color += 0.005;
    }

    public void drawCircles(GL2 gl){
        drawCircle(gl, 0.666f * Radius, 0.0f * ScalingX, (-0.333f + 1.0f) * ScalingY - 1.0f);

        //внешние круги
        drawCircle(gl, 0.333f * Radius, 0.0f * ScalingX, (0.666f + 1.0f) * ScalingY - 1.0f);

        drawCircle(gl, 0.2792f * Radius, 0.57f * ScalingX, (0.43f  + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.2792f * Radius, -0.57f * ScalingX, (0.43f  + 1.0f) * ScalingY - 1.0f);

        drawCircle(gl, 0.1975f * Radius, 0.8f * ScalingX, (0.01f  + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.1975f * Radius, -0.8f * ScalingX, (0.01f + 1.0f) * ScalingY - 1.0f);

        drawCircle(gl, 0.134f * Radius, 0.8f * ScalingX, (-0.32f + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.134f * Radius, -0.8f * ScalingX, (-0.32f + 1.0f) * ScalingY - 1.0f);

        drawCircle(gl, 0.0894f * Radius, 0.73f * ScalingX, (-0.54f + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.0894f * Radius, -0.73f * ScalingX, (-0.54f + 1.0f) * ScalingY - 1.0f);

        //круги между
        drawCircle(gl, 0.1049f * Radius, 0.42f * ScalingX, (0.79f + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.1049f * Radius, -0.42f * ScalingX, (0.79f + 1.0f) * ScalingY - 1.0f);

        drawCircle(gl, 0.0707f * Radius, 0.887f * ScalingX, (0.272f + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.0707f * Radius, -0.887f * ScalingX, (0.272f + 1.0f) * ScalingY - 1.0f);

        drawCircle(gl, 0.0458f * Radius, 0.93f * ScalingX, (-0.199f + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.0458f * Radius, -0.93f * ScalingX, (-0.199f + 1.0f) * ScalingY - 1.0f);

        drawCircle(gl, 0.0316f * Radius, 0.839f * ScalingX, (-0.482f + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.0316f * Radius, -0.839f * ScalingX, (-0.482f + 1.0f) * ScalingY - 1.0f);


        //круги на касании трех
        drawCircle(gl, 0.0591f * Radius, 0.24f * ScalingX, (0.353f + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.0591f * Radius, -0.24f * ScalingX, (0.353f + 1.0f) * ScalingY - 1.0f);

        drawCircle(gl, 0.0479f * Radius, 0.57f * ScalingX, (0.1f + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.0479f * Radius, -0.57f * ScalingX, (0.1f + 1.0f) * ScalingY - 1.0f);

        drawCircle(gl, 0.0331f * Radius, 0.688f * ScalingX, (-0.193f + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.0331f * Radius, -0.688f * ScalingX, (-0.193f + 1.0f) * ScalingY - 1.0f);

        drawCircle(gl, 0.0245f * Radius, 0.685f * ScalingX, (-0.434f + 1.0f) * ScalingY - 1.0f);
        drawCircle(gl, 0.0245f * Radius, -0.685f * ScalingX, (-0.434f + 1.0f) * ScalingY - 1.0f);

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glViewport(0, 0 , 500, 500);

        gl.glLoadIdentity();

        gl.glBegin(GL2.GL_POINTS);

        drawCircle(gl, Radius, 0.0f, 0.0f);
        for (int i = 0; i < depth; i++){
            drawCircles(gl);
            Radius = Radius * 2 / 3;
            ScalingX = ScalingX * 2 / 3;
            ScalingY = ScalingY * 2 / 3;
            step -= 50;
            color += 0.085;
        }

        gl.glEnd();

    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int i, int i1, int i2, int i3) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(i, i1, i2, i3);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    public static void main(String[] args) {
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        MainFrame mainFrame = new MainFrame();
        glcanvas.addGLEventListener(mainFrame);
        glcanvas.setSize(500, 500);

        frame.add(panel, BorderLayout.WEST);
        frame.getContentPane().add(glcanvas, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}