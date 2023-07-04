import org.lwjgl.opengl.GL;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;

public class Main {

    public static int createShaderProgram() {

        String vertexShaderCode = "#version 330 core\n"
                + "layout (location = 0) in vec3 aPos;\n"
                + "layout (location = 1) in vec2 aTexCoord;\n"
                + "out vec2 TexCoord;\n"
                + "void main()\n"
                + "{\n"
                + "    gl_Position = vec4(aPos, 1.0);\n"
                + "    TexCoord = aTexCoord;\n"
                + "}\n";

        String fragmentShaderCode = "#version 330 core\n" +
                " uniform sampler2D texture;\n" +
                "uniform vec2 resolution;\n" +
                "uniform vec2 mouse;\n" +
                "\n" +
                "void main() {\n" +
                "  vec2 uv = gl_FragCoord.xy / resolution.xy;\n" +
                "  vec2 center = mouse / resolution.xy;\n" +
                "\n" +
                "  float radius = 0.2;\n" +
                "  float strength = 0.5;\n" +
                "\n" +
                "  vec4 color = texture2D(texture, uv);\n" +
                "\n" +
                "  float distance = length(uv - center);\n" +
                "  if (distance < radius) {\n" +
                "    float delta = smoothstep(radius, radius * (1.0 - strength), distance);\n" +
                "    gl_FragColor = mix(color, texture2D(texture, center + (uv - center) * (1.0 + delta)), delta);\n" +
                "  } else {\n" +
                "    gl_FragColor = color;\n" +
                "  }\n" +
                "}";

        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexShaderCode);
        glCompileShader(vertexShader);

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentShaderCode);
        glCompileShader(fragmentShader);

        int shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);

        return shaderProgram;
    }

    public static void main(String[] args) throws IOException {

//        InputStream in = new FileInputStream("C:\\Users\\Ксения\\Desktop\\photo_2023-01-15_14-20-00.jpg");
        InputStream in = new FileInputStream("C:\\Users\\Ксения\\Desktop\\1.png");


        BufferedImage image = ImageIO.read(in);
        System.out.println(image.getWidth() + " " + image.getHeight());

        glfwInit();
        long window = glfwCreateWindow(1000, 1000, "Magnify Effect", NULL, NULL);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();


        int[] texture = new int[1];
        glGenTextures(texture);

        glBindTexture(GL_TEXTURE_2D, texture[0]);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);


        ByteBuffer buffer = ByteBuffer.allocateDirect(image.getWidth() * image.getHeight() * 4);
        for (int y = image.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
                buffer.put((byte) (pixel & 0xFF)); // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component
            }
        }
        buffer.flip();

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0,
                GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        int shaderProgram = createShaderProgram();
        glUseProgram(shaderProgram);
        int resolutionLocation = glGetUniformLocation(shaderProgram, "resolution");
        glUniform2f(resolutionLocation, 1000, 1000);

        while (!glfwWindowShouldClose(window)) {

            int mouseLocation = glGetUniformLocation(shaderProgram, "mouse");
            float mouseX = 400;
            float mouseY = 500;
            glUniform2f(mouseLocation, mouseX, mouseY);

            glEnable(GL_TEXTURE_2D);
            glBegin(GL_TRIANGLES);
            glBindTexture(GL_TEXTURE_2D, texture[0]);

            glTexCoord2f(0.0f, 0.0f);
            glVertex3f(-1.0f, -1.0f, 0.0f);
            glTexCoord2f(1.0f, 0.0f);
            glVertex3f(1.0f, -1.0f, 0.0f);
            glTexCoord2f(1.0f, 1.0f);
            glVertex3f(1.0f, 1.0f, 0.0f);
            glTexCoord2f(1.0f, 1.0f);
            glVertex3f(1.0f, 1.0f, 0.0f);
            glTexCoord2f(0.0f, 1.0f);
            glVertex3f(-1.0f, 1.0f, 0.0f);
            glTexCoord2f(0.0f, 0.0f);
            glVertex3f(-1.0f, -1.0f, 0.0f);

            glEnd();

            glfwPollEvents();
            glDisable(GL_TEXTURE_2D);
            glfwSwapBuffers(window);
        }

    }
}