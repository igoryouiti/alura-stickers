import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e bucar os top 250 filmes
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // String url = "https://api.nasa.gov/planetary/apod?api_key=tQXVDmyOoTKgdUx8MkxhvKWiNCu1dK76meNIL5CC&start_date=2022-09-04&end_date=2022-09-07";
        String url = "http://localhost:8080/linguagens";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados

        //ExtratorDeConteudo extrator = new ExtratoDeConteudoDaNasa();
        ExtratorDeConteudo extrator = new ExtratoDeConteudoDoIMDB();

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for(int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();


            geradora.cria(inputStream, conteudo.getTitulo() + ".png");

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }


    }
}
