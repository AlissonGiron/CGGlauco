/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto163011;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

/**
 *
 * @author alissongiron
 */
public class ChairManager {
    
    GL2 gl;
    
    public ChairManager(GL2 gl)
    {
        this.gl = gl;
    }
    
    public void DrawChair() {
        gl.glColor3f(1, 1, 1);
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("cadeira_encosto"));

        gl.glPushMatrix();
        drawLeg();
        gl.glTranslated(5, 0, 0);
        drawLeg();
        gl.glTranslated(0, 0, 5);
        drawLeg();
        gl.glTranslated(-5, 0, 0);
        drawLeg();
        gl.glPopMatrix();

        for (int j = 0; j < 6; j++) {
            gl.glPushMatrix();
            gl.glTranslatef(2.5f, 0.2f, 0 + j);
            gl.glScalef(5, 0.2f, 0.1f);
            DrawHelper.drawBox(gl, 1f);
            gl.glPopMatrix();
        }

        gl.glPushMatrix();
        gl.glTranslatef(0, 0.2f, 2.5f);
        gl.glScalef(0.1f, 0.2f, 5f);
        DrawHelper.drawBox(gl, 1f);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(5f, 0.2f, 2.5f);
        gl.glScalef(0.1f, 0.2f, 5f);
        DrawHelper.drawBox(gl, 1f);
        gl.glPopMatrix();

        // ferro que segura o encosto
        gl.glPushMatrix();
        gl.glTranslated(0, 6, 5);
        drawLeg();
        gl.glTranslated(0, 0, -5);
        drawLeg();
        gl.glPopMatrix();

        // ferro que prende a mesa na cadeira
        gl.glPushMatrix();
        gl.glTranslatef(1f, 6f, 5f);
        gl.glScalef(2, 0.2f, 0.3f);
        DrawHelper.drawBox(gl, 1f);
        gl.glPopMatrix();

        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("texture_chair"));

        // encosto da cadeira
        gl.glPushMatrix();
        gl.glTranslatef(0, 8f, 2.5f);
        gl.glScalef(0.5f, 5f, 6);
        DrawHelper.drawBox(gl, 1f);
        gl.glPopMatrix();

        // mesa
        gl.glPushMatrix();
        gl.glTranslatef(4f, 6f, 4f);
        gl.glScalef(6, 0.3f, 4);
        DrawHelper.drawBox(gl, 1f);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(2.5f, 3f, 2.5f);
        gl.glScalef(5.5f, 0.3f, 5.5f);
        DrawHelper.drawBox(gl, 1f);
        gl.glPopMatrix();

        gl.glDisable(GL.GL_TEXTURE_2D);
    }

    private void drawLeg() {
        gl.glPushMatrix();
        gl.glScalef(1, 20, 1);
        DrawHelper.drawBox(gl, 0.3f);
        gl.glPopMatrix();
    }
}
