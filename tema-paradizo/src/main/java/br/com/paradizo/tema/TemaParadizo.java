package br.com.paradizo.tema;

import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TemaParadizo implements Plugin {

  @Override
  public String aposRenderizacao(String html) {
    return aplicaTema(html);
  }

  @Override
  public void aposGeracao(Ebook ebook) {

  }

  private String aplicaTema(String html) {
    System.out.println("Entrou em TemaParadizo#aplicaTema");
    Document document = Jsoup.parse(html);

    String css = cssDoTema();

    document.select("head")
        .append("<style>" + css + "</style>");

    System.out.println("Saiu de TemaParadizo#aplicaTema");
    return document.html();
  }

  private String cssDoTema() {
    String tema = FileUtils.getResourceContents("/tema.css");
    System.out.println("Aqui o tema:");
    System.out.println(tema);
    return tema;
  }
}
