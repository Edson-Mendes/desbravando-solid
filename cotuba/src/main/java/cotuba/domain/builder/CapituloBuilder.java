package cotuba.domain.builder;

import cotuba.domain.Capitulo;

public class CapituloBuilder {

  private String titulo;
  private String conteudoHTML;

  public CapituloBuilder titulo(String titulo) {
    this.titulo = titulo;
    return this;
  }

  public CapituloBuilder conteudoHTML(String conteudoHTML) {
    this.conteudoHTML = conteudoHTML;
    return this;
  }

  public Capitulo constroi() {
    return new Capitulo(titulo, conteudoHTML);
  }

}
