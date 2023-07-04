import com.jogamp.opengl.GL2;

enum DFactor {
    GL_ZERO("GL_ZERO",GL2.GL_ZERO),
    GL_ONE("GL_ONE", GL2.GL_ONE),
    GL_SRC_COLOR("GL_SRC_COLOR", GL2.GL_SRC_COLOR),
    GL_ONE_MINUS_SRC_COLOR("GL_ONE_MINUS_SRC_COLOR", GL2.GL_ONE_MINUS_SRC_COLOR),
    GL_SRC_ALPHA("GL_SRC_ALPHA", GL2.GL_SRC_ALPHA),
    GL_ONE_MINUS_SRC_ALPHA("GL_ONE_MINUS_SRC_ALPHA", GL2.GL_ONE_MINUS_SRC_ALPHA),
    GL_DST_ALPHA("GL_DST_ALPHA", GL2.GL_DST_ALPHA),
    GL_ONE_MINUS_DST_ALPHA("GL_ONE_MINUS_DST_ALPHA", GL2.GL_ONE_MINUS_DST_ALPHA);

    public final String name;
    public final int number;
    DFactor(String type, int number){
        this.name = type;
        this.number = number;
    }

    String getName(){
        return this.name;
    }

    int getNumber(){
        return this.number;
    }

}
