/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto163011;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author alissongiron
 */
public class Main implements GLEventListener{
    GL2 gl;
    GLU glu = new GLU();
    
    Glauco glauco;
    SceneManager scene;

    // rotacao da camera
    private int look_x;
    
    private double x;
    private double z = -4;

    float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
    float mat_shininess[] = {100.0f};
    float shininess = 100.0f;

    float light_ambient[] = {1.0f, 1.0f, 1.0f, 1.0f};
    float light_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
    float light_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
    float light_position[] = {1.0f, 1.0f, 1.0f, 0.0f};

    public static void main(String args[]) { new Main(); }

    public Main() {
        GLJPanel canvas = new GLJPanel();
        canvas.addGLEventListener(this);

        look_x = 0;

        JFrame frame = new JFrame("Projeto 163011");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        frame.setFocusable(true);

        FPSAnimator fps = new FPSAnimator(canvas, 60);
        fps.start();

        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent me) { look_x = me.getX(); }
            
            @Override
            public void mouseDragged(MouseEvent me) { }
        });

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent key) { doActions(key); }
            
            @Override
            public void keyTyped(KeyEvent key) { }

            @Override
            public void keyReleased(KeyEvent key) { }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { 
                new Thread(() -> System.exit(0)).start();
            }
        });
    }

    @Override
    public void init(GLAutoDrawable glAuto) {
        configureGL(glAuto);

        TextureHelper.init();
        scene = new SceneManager(gl);
        glauco = new Glauco(gl);
    }

    @Override
    public void display(GLAutoDrawable glAuto) {
        configureGL(glAuto);
        
        MovePlayer();
        scene.drawScene();
        glauco.drawGlauco();
    }
    
    private void MovePlayer() 
    {
        gl.glRotatef(look_x, 0, 1, 0);
        gl.glTranslated(x, -10, -z);
    }

    private void configureGL(GLAutoDrawable glAuto)
    {
        gl = glAuto.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);

        gl.glLoadIdentity();
        
        //Define o sistema de colora��o
        gl.glShadeModel(GL2.GL_SMOOTH); // Troque para GL.GL_FLAT e veja a diferenca

        //Define as propriedades do Material
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, mat_shininess, 0);

        //Define uma fonte de LUZ em uma posicao
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position, 0);

        //Define a luz ambiente
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, light_ambient, 0);

        //Define a luz difusa
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_DIFFUSE, light_diffuse, 0);

        //Define a lua especula
        gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_SPECULAR, light_specular, 0);

        //Habilita iluminacao
        gl.glEnable(GL2.GL_LIGHTING);

        //Habilita ilumina�ao
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_LIGHT2);
        gl.glEnable(GL2.GL_LIGHT3);

        //Habilita o teste de profundidade
        gl.glEnable(GL.GL_DEPTH_TEST);

        //Define a posicao de uma fonte de luz
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position, 0);
        // Define a concentra��o do brilho
        gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SHININESS, mat_shininess, 0);
    }
    
    @Override
    public void reshape(GLAutoDrawable gLAutoDrawable, int x, int y, int w, int h) {
        gl = gLAutoDrawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50, (float) w / h, 1, 1000);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslated(0, 0, -5);
    }
    
    private void doActions(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_A:
                x += -Math.sin(Math.toRadians(look_x - 90));
                z += -Math.cos(Math.toRadians(look_x - 90));
                break;
            case KeyEvent.VK_D:
                x += -Math.sin(Math.toRadians(look_x + 90));
                z += -Math.cos(Math.toRadians(look_x + 90));
                break;
            case KeyEvent.VK_W:
                x += -Math.sin(Math.toRadians(look_x));
                z += -Math.cos(Math.toRadians(look_x));
                break;
            case KeyEvent.VK_S:
                x += Math.sin(Math.toRadians(look_x));
                z += Math.cos(Math.toRadians(look_x));
                break;
            default:
                break;
        }
    }

    @Override
    public void dispose(GLAutoDrawable glad) {}
}
