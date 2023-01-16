package br.com.cognitio.estatisticas;

import cotuba.plugin.AoFinalizarGeracao;
import cotuba.plugin.CapituloSoParaLeitura;
import cotuba.plugin.EbookSoParaLeitura;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.Map;

public class CalculadoraDeEstatisticas implements AoFinalizarGeracao {

  @Override
  public void aposGeracao(EbookSoParaLeitura ebook) {
    var contagemDePalavras = new ContagemDePalavras();

    for (CapituloSoParaLeitura capitulo : ebook.getCapitulos()) {
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
      Thread.sleep(2000);
    } catch (Exception ex) {
      System.out.println("Something went wrong!");
    }
  }

}
