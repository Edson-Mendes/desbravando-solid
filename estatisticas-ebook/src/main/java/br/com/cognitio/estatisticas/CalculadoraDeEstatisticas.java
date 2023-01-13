package br.com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.Map;

public class CalculadoraDeEstatisticas implements Plugin {

  @Override
  public String aposRenderizacao(String html) {
    return html;
  }

  @Override
  public void aposGeracao(Ebook ebook) {
    var contagemDePalavras = new ContagemDePalavras();

    for (Capitulo capitulo : ebook.getCapitulos()) {
      String html = capitulo.getConteudoHTML();

      Document doc = Jsoup.parse(html);

      String textoDoCapitulo = doc.body().text();
      String textoDoCapituloSemPontuacao =
          textoDoCapitulo.replaceAll("\\p{Punct}", " ");

      String decomposta = Normalizer.normalize(textoDoCapituloSemPontuacao, Normalizer.Form.NFD);
      String textoDoCapituloSemAcentos = decomposta.replaceAll("[^\\p{ASCII}]", "");

      String[] palavras = textoDoCapituloSemAcentos.split("\\s+");

      for (String palavra : palavras) {
        String emMaiusculas = palavra.toUpperCase();
        contagemDePalavras.adicionaPalavra(emMaiusculas);
      }

      for (Map.Entry<String, Integer> contagem : contagemDePalavras.entrySet()) {
        String palavra = contagem.getKey();

        Integer ocorrencias = contagem.getValue();

        System.out.println(palavra + ": " + ocorrencias);
      }

    }
    try {
      Thread.sleep(20000);
    } catch (Exception ex) {
      System.out.println("Something went wrong!");
    }
  }

}
