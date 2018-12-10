package projeto163011;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alissongiron
 */
public class SceneManager {
    
    private GL2 gl;
    private ChairManager chairManager;
    private float rotjanela = 0.0f;
    private float rot = 0.0f;
    
    public SceneManager(GL2 gl)
    {
        this.gl = gl;
        chairManager = new ChairManager(gl);
    }
    
    public void drawScene() {
        gl.glTranslated(30, 0, -40);

        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("stone_texture"));

        DesenhaParedes(-50, 94, -0.5, 64, -0.25, 30);

        gl.glTranslated(0, 2, 5);
        gl.glRotatef(180, 0, 1, 0);
        gl.glScalef(0.7f, 0.7f, 0.7f);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                gl.glPushMatrix();
                gl.glTranslated(-20 * i, 0, -20 * j);
                chairManager.DrawChair();
                gl.glPopMatrix();
            }
        }
    }

    private void DesenhaParedes(double xinicial, double xfinal, double zinicial, double zfinal, double chao, double altura) {
        double tamX = 0, tamZ = 0;

        gl.glPushMatrix(); // chao
        tamX = (xfinal - xinicial) / 20;
        tamZ = (zfinal - zinicial) / 10;
        gl.glTranslated(-56, 0, tamZ / 2);

        for (int j = 0; j < 10; j++) {
            gl.glPushMatrix();

            for (int i = 0; i < 20; i++) {
                gl.glTranslated(tamX, 0, 0);
                gl.glPushMatrix();
                gl.glScaled(tamX, 1, tamZ);
                DrawHelper.drawBox(gl, 1f);
                gl.glPopMatrix();
            }

            gl.glPopMatrix();

            gl.glTranslated(0, 0, tamZ);
        }
        
        gl.glPopMatrix();

        gl.glPushMatrix(); // teto
        tamX = (xfinal - xinicial) / 20;
        tamZ = (zfinal - zinicial) / 10;
        gl.glTranslated(-56, altura, tamZ / 2);

        for (int j = 0; j < 10; j++) {
            gl.glPushMatrix();

            for (int i = 0; i < 20; i++) {
                gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("wood_texture"));
                gl.glTranslated(tamX, 0, 0);

                if ((i > 0 && i < 19 && j > 0 && j < 9)
                        && (j == 2 || j == 7) && i != 9 && i != 10) // lampadas
                {

                    gl.glPushMatrix();
                    gl.glScaled(tamX, 1, tamZ);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("cadeira_encosto"));

                    gl.glPushMatrix();
                    gl.glTranslated(0, -5, 0);

                    gl.glScaled(tamX, 1, tamZ / 2);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    if (i == 7 || i == 12 || i == 2 || i == 17) {
                        gl.glPushMatrix();
                        gl.glTranslated(0, -2, 0);

                        gl.glScaled(1, 5, 1);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();
                    }

                    continue;
                }

                gl.glPushMatrix();
                gl.glScaled(tamX, 1, tamZ);
                DrawHelper.drawBox(gl, 1f);
                gl.glPopMatrix();
            }

            gl.glPopMatrix();

            gl.glTranslated(0, 0, tamZ);
        }

        gl.glPopMatrix();

        gl.glPushMatrix(); // parede da frente
        tamX = (zfinal - zinicial) / 10;
        gl.glTranslated(-53, 4 - altura / 4, -tamX / 2);

        for (int j = 0; j < 4; j++) {

            gl.glTranslated(0, altura / 4, 0);

            gl.glPushMatrix();

            for (int i = 0; i < 10; i++) {

                gl.glTranslated(0, 0, tamX);

                if ((j == 2 || j == 1) && i > 0 && i < 9) { // lousa
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("stone_texture"));
                    if (j == 1) {
                        gl.glPushMatrix();
                        gl.glRotated(90, 0, 1, 0);

                        gl.glTranslated(0, -altura / 8, 0);
                        gl.glScaled(tamX + 2, 4, 3);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();
                    }

                    if (j == 2) {
                        gl.glPushMatrix();
                        gl.glRotated(90, 0, 1, 0);

                        gl.glTranslated(0, altura / 8, 0);
                        gl.glScaled(tamX, 1, 3);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();
                    }

                    if (i == 8) {
                        gl.glPushMatrix();
                        gl.glRotated(90, 0, 1, 0);

                        gl.glTranslated(-tamX / 2, 0, 0);
                        gl.glScaled(1, altura / 4, 3);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();
                    }

                    if (i == 1) {
                        gl.glPushMatrix();
                        gl.glRotated(90, 0, 1, 0);

                        gl.glTranslated(tamX / 2, 0, 0);
                        gl.glScaled(1, altura / 4, 3);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();
                    }

                    gl.glDisable(GL.GL_TEXTURE_2D);
                    gl.glPushMatrix();
                    gl.glRotated(90, 0, 1, 0);

                    gl.glScaled(tamX, altura / 4, 1);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();
                    gl.glEnable(GL.GL_TEXTURE_2D);

                    continue;
                }

                if (i % 2 == 0) {
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("stone_texture"));
                } else {
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("wood_texture"));
                }

                gl.glPushMatrix();
                gl.glScaled(1, altura / 4, tamX);
                DrawHelper.drawBox(gl, 1f);
                gl.glPopMatrix();
            }
            gl.glPopMatrix();
        }
        gl.glPopMatrix();

        gl.glPushMatrix(); // parede de tras
        tamX = (zfinal - zinicial) / 10;
        gl.glTranslated(90, 4, -tamX / 2);

        for (int j = 0; j < 4; j++) {

            gl.glPushMatrix();

            for (int i = 0; i < 10; i++) {

                if (i % 2 == 0) {
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("stone_texture"));
                } else {
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("wood_texture"));
                }

                gl.glTranslated(0, 0, tamX);
                gl.glPushMatrix();
                gl.glScaled(1, altura / 4, tamX);
                DrawHelper.drawBox(gl, 1f);
                gl.glPopMatrix();
            }
            gl.glPopMatrix();
            gl.glTranslated(0, altura / 4, 0);
        }
        gl.glPopMatrix();

        gl.glPushMatrix(); // parede direita
        tamX = (xfinal - xinicial) / 10;
        gl.glTranslated(-60, 4 - altura / 4, zfinal);

        for (int j = 0; j < 4; j++) {

            gl.glTranslated(0, altura / 4, 0);

            gl.glPushMatrix();

            for (int i = 0; i < 10; i++) {

                gl.glTranslated(tamX, 0, 0);

                if ((j == 1 || j == 2) && i > 0 && i < 9 && i != 5 && i != 4) { // janelas
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("wood_texture"));

                    gl.glPushMatrix();
                    gl.glTranslated(0, -altura / 8, 0);
                    gl.glScaled(tamX, 1, 3);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glPushMatrix();
                    gl.glTranslated(0, altura / 8, 0);
                    gl.glScaled(tamX, 1, 3);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glPushMatrix();
                    gl.glTranslated(-tamX / 2, 0, 0);
                    gl.glScaled(1, altura / 4, 3);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glPushMatrix();
                    gl.glTranslated(tamX / 2, 0, 0);
                    gl.glScaled(1, altura / 4, 3);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glEnable(GL.GL_BLEND);
                    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("cadeira_encosto"));
                    gl.glColor4d(1.0, 1.0, 1.0, 0.3);

                    gl.glPushMatrix();

                    gl.glRotated(rotjanela -= 0.1, 1, 0, 0);

                    gl.glScaled(tamX, altura / 4, 0.2);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glDisable(GL.GL_BLEND);

                    continue;
                }

                if (i % 2 == 0) {
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("stone_texture"));
                } else {
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("wood_texture"));
                }

                gl.glPushMatrix();
                gl.glScaled(tamX, altura / 4, 1);
                DrawHelper.drawBox(gl, 1f);
                gl.glPopMatrix();
            }
            gl.glPopMatrix();
        }
        gl.glPopMatrix();

        // parede esquerda
        gl.glPushMatrix();
        tamX = (xfinal - xinicial) / 10;
        gl.glTranslated(-60, 4 - altura / 4, 0);

        for (int j = 0; j < 4; j++) {

            gl.glTranslated(0, altura / 4, 0);

            gl.glPushMatrix();

            for (int i = 0; i < 10; i++) {

                gl.glTranslated(tamX, 0, 0);

                if (i > 2 && j == 3) { // janela topo
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("wood_texture"));

                    gl.glPushMatrix();
                    gl.glTranslated(0, -altura / 8, 0);
                    gl.glScaled(tamX, 1, 3);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glPushMatrix();
                    gl.glTranslated(0, altura / 8, 0);
                    gl.glScaled(tamX, 1, 3);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glPushMatrix();
                    gl.glTranslated(-tamX / 2, 0, 0);
                    gl.glScaled(1, altura / 4, 3);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glPushMatrix();
                    gl.glTranslated(tamX / 2, 0, 0);
                    gl.glScaled(1, altura / 4, 3);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glEnable(GL.GL_BLEND);
                    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("cadeira_encosto"));
                    gl.glColor4d(1.0, 1.0, 1.0, 0.3);

                    gl.glPushMatrix();
                    gl.glRotated(rotjanela -= 0.1, 1, 0, 0);

                    gl.glScaled(tamX, altura / 4, 0.2);
                    DrawHelper.drawBox(gl, 1f);
                    gl.glPopMatrix();

                    gl.glDisable(GL.GL_BLEND);

                    continue;
                }

                if (i == 1 && j < 2) { // porta
                    if (j == 0) {
                        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("wood_texture"));

                        gl.glPushMatrix();
                        gl.glTranslated(0, -altura / 8, 0);
                        gl.glScaled(tamX, 1, 3);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();

                        gl.glPushMatrix();
                        gl.glTranslated(0, 3 * (altura / 8), 0);
                        gl.glScaled(tamX, 1, 3);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();

                        gl.glPushMatrix();
                        gl.glTranslated(-tamX / 2, altura / 8, 0);
                        gl.glScaled(1, altura / 2, 3);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();

                        gl.glPushMatrix();
                        gl.glTranslated(tamX / 2, altura / 8, 0);
                        gl.glScaled(1, (altura / 2), 3);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();

                        gl.glPushMatrix();
                        gl.glTranslated(-6, 0, 0);
                        gl.glRotated(rot, 0, 1, 0);

                        gl.glTranslated(6, altura / 8, 0);
                        gl.glScaled(tamX, altura / 2, 1);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();

                        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("cadeira_encosto"));

                        gl.glPushMatrix();

                        gl.glTranslated(-6, 0, 0);
                        gl.glRotated(rot, 0, 1, 0);

                        gl.glTranslated(tamX / 2 + 4, altura / 8, 0);
                        gl.glScaled(2, 0.5, 2);
                        DrawHelper.drawBox(gl, 1f);
                        gl.glPopMatrix();

                        rot--;
                    }

                    continue;
                }

                if (i % 2 == 0) {
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("stone_texture"));
                } else {
                    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureHelper.GetTexture("wood_texture"));
                }

                gl.glPushMatrix();
                gl.glScaled(tamX, altura / 4, 1);
                DrawHelper.drawBox(gl, 1f);
                gl.glPopMatrix();
            }
            gl.glPopMatrix();
        }
        gl.glPopMatrix();
    }
}
