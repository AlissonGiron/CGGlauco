/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto163011;

import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alissongiron
 */
public class TextureHelper {
    public static HashMap<String, Texture> map = new HashMap<>();
    
    public static void init() {
        try {
            map.put("cadeira_encosto", TextureIO.newTexture(new File("./images/encosto-texture.png"), true));
            map.put("texture_chair", TextureIO.newTexture(new File("./images/texture-chair2.jpeg"), true));
            map.put("stone_texture", TextureIO.newTexture(new File("./images/stone-texture.jpg"), true));
            map.put("wood_texture", TextureIO.newTexture(new File("./images/wood-texture.png"), true));
            map.put("floor_texture", TextureIO.newTexture(new File("./images/floor-texture.jpg"), true));
            map.put("glauco_rosto", TextureIO.newTexture(new File("./images/glauco-texture.jpg"), true));
            map.put("verde", TextureIO.newTexture(new File("./images/verde.jpeg"), true));
        } catch (IOException | GLException ex) {
            Logger.getLogger(TextureHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int GetTexture(String AKey) {
        return map.get(AKey).getTextureObject();
    }
}
