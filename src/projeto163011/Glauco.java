package projeto163011;


import com.jogamp.opengl.*;

/**
 * @author alissongiron
 */
public class Glauco {
    
    private final GL2 gl;
    private int rot = 0;
    private int x = 0;
    private int dir = 1;
    
    public Glauco(GL2 gl) {
        this.gl = gl;
    }
    
    public void drawGlauco()
    {
        gl.glTranslated(20, 0, 0);   
        gl.glEnable(GL.GL_TEXTURE_2D);

        rot--;
        x += dir;
        
        if(x > 70)
        {
            dir = -1;
        }
        else if(x < 0)
        {
            dir = 1;
        }
        
        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("verde"));
        
        // perna direita
        gl.glTranslated(0, 0, -x);
        
        gl.glPushMatrix();

            gl.glTranslated(0, 6, 0);
            gl.glRotated(rot*10, 1, 0, 0);
            gl.glTranslated(0, -6, 0);

            gl.glPushMatrix();
            gl.glTranslatef(0, -2f, 0);
            gl.glScaled(1, 0.5, 2);
            DrawHelper.drawBox(gl, 1);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(0, 1f, 1f);
            gl.glScalef(0.5f, 6, 0.5f);
            DrawHelper.drawBox(gl, 1);
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glTranslatef(0, 5f, 1f);
            gl.glScalef(0.8f, 4, 0.8f);
            DrawHelper.drawBox(gl, 1);
            gl.glPopMatrix();


        gl.glPopMatrix();
            gl.glTranslated(-2, 0, 0);

        // perna esquerda
        
        gl.glPushMatrix();
            gl.glTranslated(0, 6, 0);
            gl.glRotated(-rot*10, 1, 0, 0);
            gl.glTranslated(0, -6, 0);

            gl.glPushMatrix();
            gl.glTranslatef(0, -2f, 0);
            gl.glScaled(1, 0.5, 2);
            DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
       
        gl.glPushMatrix();
        gl.glTranslatef(0, 1f, 1f);
        gl.glScalef(0.5f, 6, 0.5f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslatef(0, 5f, 1f);
        gl.glScalef(0.8f, 4, 0.8f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
        
        gl.glPopMatrix();
        
        gl.glTranslated(1, 0, 0);        
        
        gl.glPushMatrix();
        gl.glTranslatef(0, 7f, 1f);
        gl.glScalef(3f, 3, 2f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslatef(0, 10f, 1f);
        gl.glScalef(3.5f, 5, 3f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
                
        // braço direito
        
        gl.glPushMatrix();
        gl.glTranslatef(2.5f, 9f, 1f);
        gl.glScalef(0.7f, 6, 0.7f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
    
        gl.glPushMatrix();
        gl.glTranslatef(2.5f, 9f, 1f);
        gl.glScalef(1.1f, 1, 1.1f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslatef(2.5f, 6f, 1f);
        gl.glScalef(1.2f, 1, 1.2f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
        
        gl.glTranslated(-5, 0, 0);
        
        // braço direito
        
        gl.glPushMatrix();
        gl.glTranslatef(2.5f, 9f, 1f);
        gl.glScalef(0.7f, 6, 0.7f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
    
        gl.glPushMatrix();
        gl.glTranslatef(2.5f, 9f, 1f);
        gl.glScalef(1.1f, 1, 1.1f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslatef(2.5f, 6f, 1f);
        gl.glScalef(1.2f, 1, 1.2f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
        
        // cabeça
        
        gl.glPushMatrix();
        gl.glTranslatef(3, 14.5f, 1f);
        gl.glScalef(1f, 4, 3f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslatef(7, 14.5f, 1f);
        gl.glScalef(1f, 4, 3f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslatef(5, 16f, 1f);
        gl.glScalef(4f, 2, 3f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslatef(5, 14f, 3f);
        gl.glScalef(4f, 4, 1f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
    
        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("glauco_rosto"));
        
        gl.glPushMatrix();
        gl.glTranslatef(5, 14.5f, 1f);
        gl.glScalef(4f, 4, 2f);
        DrawHelper.drawBox(gl, 1);
        gl.glPopMatrix();
    
        gl.glDisable(GL.GL_TEXTURE_2D);
    }    
}
