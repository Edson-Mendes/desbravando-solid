package cotuba.html;

import cotuba.domain.FormatoEbook;
import cotuba.plugin.GeradorEbook;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;

public class GeradorHTML implements GeradorEbook {

  @Override
  public void gera(Ebook ebook) {
    Path arquivoDeSaida = ebook.arquivoDeSaida();
    try {
      Path diretorioDoHTML =
          Files.createDirectory(arquivoDeSaida);
      int i = 1;
      for (Capitulo capitulo : ebook.capitulos()) {
        String nomeDoArquivoHTMLDoCapitulo = obtemNomeDoArquivoHTMLDoCapitulo(i, capitulo);
        Path arquivoHTMLDoCapitulo = diretorioDoHTML.resolve(nomeDoArquivoHTMLDoCapitulo);
        String html = """
            <!DOCTYPE html>
            <html lang="pt-BR">
              <head>
                <meta charset="UTF-8">
                <title>%s</title>
              </head>
              <body>
                %s
              </body>
            </html>
            """.formatted(capitulo.titulo(), capitulo.conteudoHTML());

        Files.writeString(arquivoHTMLDoCapitulo, html,
            StandardCharsets.UTF_8);
        i++;
      }
    } catch (IOException ex) {
      throw new IllegalStateException("Erro ao criar HTML: "
          + arquivoDeSaida.toAbsolutePath(), ex);
    }
  }

  @Override
  public FormatoEbook formato() {
    return FormatoEbook.HTML;
  }

  private String obtemNomeDoArquivoHTMLDoCapitulo(int i, Capitulo capitulo) {
    return i + "-"
        + removeAcentos(capitulo.titulo().toLowerCase())
        .replaceAll("[^\\w]", "")
        + ".html";
  }

  // Será que remover acentos é responsabilidade do GeradorHTML?
  private String removeAcentos(String texto) {
    return Normalizer.normalize(texto, Normalizer.Form.NFD)
        .replaceAll("[^\\p{ASCII}]", "");
  }

}
