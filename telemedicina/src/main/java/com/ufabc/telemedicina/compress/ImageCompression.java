package com.ufabc.telemedicina.compress;

import com.ufabc.telemedicina.domains.ImageStatus;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.*;

@Component
public class ImageCompression {

    public ImageStatus comprimirImagem() throws IOException {

        ImageStatus imageStatus = new ImageStatus();

        File input = new File("D:\\Projetos\\projeto-telemedicina-aed2\\telemedicina\\src\\main\\java\\com\\ufabc\\telemedicina\\raio_x_original.jpg");
        BufferedImage image = ImageIO.read(input);

        File compressedImageFile = new File("raio_x_comprimido.jpg");
        OutputStream os = new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.4f);  // Change the quality value you prefer
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();




        imageStatus.setTamAntigo(input.length() / 1024 + " Kbs");
        imageStatus.setTamNovo(compressedImageFile.length() / 1024 + " Kbs");

        Float tamanhoAntigo = Float.parseFloat(String.valueOf(input.length()/ 1024));
        Float tamanhoNovo = Float.parseFloat(String.valueOf(compressedImageFile.length()/ 1024));

        String compressao = ((tamanhoNovo/tamanhoAntigo) * 100L) + " %";

        imageStatus.setReducao(compressao);
        return imageStatus;
    }
}



