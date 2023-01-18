package cotuba.domain;

import cotuba.plugin.CapituloSoParaLeitura;

public final class Capitulo implements CapituloSoParaLeitura {

  private final String titulo;
  private final String conteudoHTML;

  public Capitulo(String titulo, String conteudoHTML) {
    this.titulo = titulo;
    this.conteudoHTML = conteudoHTML;
  }

  @Override
  public String getTitulo() {
    return titulo;
  }

  @Override
  public String getConteudoHTML() {
    return conteudoHTML;
  }

}
