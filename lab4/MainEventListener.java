import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import java.util.ArrayList;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;

public class MainEventListener implements GLEventListener {

    static ArrayList<Point> points = new ArrayList<>();
    int step = 20;
    public void drawControlPoints(GL2 gl){

        for (int i = 0; i < points.size(); i++) {

            gl.glColor3d(1.0f,0f,0.365f);
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2d(points.get(i).getX() - 0.01, points.get(i).getY() + 0.01);
            gl.glVertex2d(points.get(i).getX() - 0.01, points.get(i).getY() - 0.01);
            gl.glVertex2d(points.get(i).getX() + 0.01, points.get(i).getY() + 0.01);
            gl.glVertex2d(points.get(i).getX() + 0.01, points.get(i).getY() - 0.01);
            gl.glEnd();



//            if (points.get(i).getControl()) {
//                for (int j = 0; j <= step; j++) {
//                    float angle = 2 * 3.1415f * j / step;
//                    float x = (float) (Math.cos(angle) * 0.009);
//                    float y = (float) (Math.sin(angle) * 0.009);
//
//                    gl.glColor4d(0.4196f, 1, 1, 1);
//                    gl.glVertex2d(points.get(i).getX() + x, points.get(i).getY() + y);
//                }
//            }
        }
    }

    public void drawPrimitiveLine(GL2 gl) {
        gl.glBegin(GL2.GL_LINE_STRIP);

        gl.glColor3d(0.2f,0.812f,1.0f);
//        gl.glVertex2d(points.get(1).getX(), points.get(1).getY());
//        gl.glVertex2d(points.get(2).getX(), points.get(2).getY());

//        System.out.println(points.get(1).getX() + " " + points.get(1).getY());
        for (int i = 1; i < 5; i++) {
            gl.glVertex2d(points.get(i).getX(), points.get(i).getY());
            System.out.println(points.get(i).getX() + " " + points.get(i).getY());
            System.out.println("  ");
        }

        gl.glEnd();
    }

    public void drawLine(GL2 gl) {

        gl.glBegin(GL2.GL_POINTS);

        float XF, YF;
        float s = (1 - MainFrame.WEIGHT) / 2;
        System.out.println("s = " + s);


        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 1000; i++) {
                float t = (float) i / 1000;


            XF = ((-s) * (float) Math.pow(t, 3) + 2 * s * (float) Math.pow(t, 2) - s * t) * points.get(j + 0).getX()
                    + ((2 - s) * (float) Math.pow(t, 3) + (s - 3) * (float) Math.pow(t, 2) + 1) * points.get(j + 1).getX()
                    + ((s - 2) * (float) Math.pow(t, 3) + (3 - 2 * s) * (float) Math.pow(t, 2) + s * t) * points.get(j + 2).getX()
                    + (s * (float) Math.pow(t, 3) - s * (float) Math.pow(t, 2)) * points.get(j + 3).getX();

            YF = (- s * (float) Math.pow(t, 3) + 2 * s * (float) Math.pow(t, 2) - s * t) * points.get(j + 0).getY()
                    + ((2 - s) * (float) Math.pow(t, 3) + (s - 3) * (float) Math.pow(t, 2) + 1) * points.get(j + 1).getY()
                    + ((s - 2) * (float) Math.pow(t, 3) + (3 - 2 * s) * (float) Math.pow(t, 2) + s * t) * points.get(j + 2).getY()
                    + (s * (float) Math.pow(t, 3) - s * (float) Math.pow(t, 2)) * points.get(j + 3).getY();

//                XF = 0.5f * ((2 * points.get(j + 1).getX())
//                        + (-points.get(j + 0).getX() + points.get(j + 2).getX()) * t
//                        + (2 * points.get(j + 0).getX() - 5 * points.get(j + 1).getX() + 4 * points.get(j + 2).getX() - points.get(j + 3).getX()) * (float) Math.pow(t, 2)
//                        + (-points.get(j + 0).getX() + 3 * points.get(j + 1).getX() - 3 * points.get(j + 2).getX() + points.get(j + 3).getX()) * (float) Math.pow(t, 3)
//                );
//
//                YF = 0.5f * ((2 * points.get(j + 1).getY())
//                        + (-points.get(j + 0).getY() + points.get(j + 2).getY()) * t
//                        + (2 * points.get(j + 0).getY() - 5 * points.get(j + 1).getY() + 4 * points.get(j + 2).getY() - points.get(j + 3).getY()) * (float) Math.pow(t, 2)
//                        + (-points.get(j + 0).getY() + 3 * points.get(j + 1).getY() - 3 * points.get(j + 2).getY() + points.get(j + 3).getY()) * (float) Math.pow(t, 3)
//                );

                gl.glColor4d(0.749f, 0, 1.0f, 1);
                gl.glVertex2d(XF, YF);
            }
        }

        gl.glEnd();


//        for (int i = 0; i < 1000; i++) {
//            float v = (float) i / 1000;
//            XF = (float) Math.pow((1 - v), 4) * points.get(0).getX() +
//                    4 * (float) Math.pow((1 - v), 3) * v * points.get(1).getX() +
//                    6 * (float) Math.pow((1 - v), 2) * (float) Math.pow(v, 2) * points.get(2).getX() +
//                    4 * (1 - v) * (float) Math.pow(v, 3) * points.get(4).getX() +
//                    (float) Math.pow(v, 4) * points.get(5).getX();
//
//            YF = (float) Math.pow((1 - v), 4) * points.get(0).getY() +
//                    4 * (float) Math.pow((1 - v), 3) * v * points.get(1).getY() +
//                    6 * (float) Math.pow((1 - v), 2) * (float) Math.pow(v, 2) * points.get(2).getY() +
//                    4 * (1 - v) * (float) Math.pow(v, 3) * points.get(4).getY() +
//                    (float) Math.pow(v, 4) * points.get(5).getY();
//
//            gl.glColor4d(1f, 0, 0.349f, 1);
//            gl.glVertex2d(XF, YF);
//        }
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {

        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glViewport(0, 0 , 500, 500);

        gl.glLoadIdentity();

        drawControlPoints(gl);
        drawLine(gl);
        drawPrimitiveLine(gl);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
