package cotuba.plugin;

import cotuba.domain.Ebook;

import java.util.ServiceLoader;

public interface AoFinalizarGeracao {

  void aposGeracao(Ebook ebook);

  static void gerou(Ebook ebook) {
    ServiceLoader.load(AoFinalizarGeracao.class)
        .forEach(plugin -> plugin.aposGeracao(ebook));
    System.out.println("Modificacao apos gerar ebook");
  }

}
