import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {


    public void cria(InputStream inputStream, String nomeDoArquivo) throws Exception{

        // leitura de imagem

        // InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transarência e com tamanho novo

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copia a imagem original para nova imagem (em memória)

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar fonte

        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        // escrever uma frase na nova imagem

        graphics.drawString("MASSA!", 100, novaAltura - 100);

        // escrever a nova imagem em um arquivo

        ImageIO.write(novaImagem, "png", new File("saida/" + nomeDoArquivo.replace(":", " -")));


    }

}
