import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e bucar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados que interessam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados

        var geradora = new GeradoraDeFigurinhas();

        for (Map<String, String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String nomeFilme = filme.get("title");
            InputStream inputStream = new URL(urlImage).openStream();


            geradora.cria(inputStream, nomeFilme + ".png");

            System.out.println(nomeFilme);

            System.out.println();
        }

    }
}
