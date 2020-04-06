package org.andengine.util.texturepack;

import android.content.res.AssetManager;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.util.StreamUtils;
import org.andengine.util.texturepack.exception.TexturePackParseException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class TexturePackLoader {
    private final AssetManager mAssetManager;
    private final TextureManager mTextureManager;

    public TexturePackLoader(AssetManager pAssetManager, TextureManager pTextureManager) {
        this.mAssetManager = pAssetManager;
        this.mTextureManager = pTextureManager;
    }

    public TexturePack loadFromAsset(String pAssetPath, String pAssetBasePath) throws TexturePackParseException {
        try {
            return load(this.mAssetManager.open(pAssetPath), pAssetBasePath);
        } catch (IOException e) {
            throw new TexturePackParseException("Could not load " + getClass().getSimpleName() + " data from asset: " + pAssetPath, e);
        }
    }

    public TexturePack load(InputStream pInputStream, String pAssetBasePath) throws TexturePackParseException {
        try {
            XMLReader xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            TexturePackParser texturePackParser = new TexturePackParser(this.mAssetManager, pAssetBasePath, this.mTextureManager);
            xr.setContentHandler(texturePackParser);
            xr.parse(new InputSource(new BufferedInputStream(pInputStream)));
            TexturePack texturePack = texturePackParser.getTexturePack();
            StreamUtils.close(pInputStream);
            return texturePack;
        } catch (SAXException e) {
            throw new TexturePackParseException((Exception) e);
        } catch (ParserConfigurationException e2) {
            StreamUtils.close(pInputStream);
            return null;
        } catch (IOException e3) {
            throw new TexturePackParseException((Exception) e3);
        } catch (Throwable th) {
            StreamUtils.close(pInputStream);
            throw th;
        }
    }
}
