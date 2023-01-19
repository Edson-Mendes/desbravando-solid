package br.com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.AoFinalizarGeracao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.Map;

public class CalculadoraDeEstatisticas implements AoFinalizarGeracao {

  @Override
  public void aposGeracao(Ebook ebook) {
    var contagemDePalavras = new ContagemDePalavras();

    for (Capitulo capitulo : ebook.capitulos()) {
      String html = capitulo.conteudoHTML();

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

      for (ContagemDePalavras.Contagem contagem : contagemDePalavras) {
        System.out.println(contagem.palavra() + ": " + contagem.contagem());
      }

    }

    try {
      Thread.sleep(2000);
    } catch (Exception ex) {
      System.out.println("Something went wrong!");
    }
  }

}
