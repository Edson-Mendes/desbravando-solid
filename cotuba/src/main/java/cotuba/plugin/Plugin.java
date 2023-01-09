package cotuba.plugin;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.util.ServiceLoader;

public interface Plugin {

    /**
     * Método que poderá ser utilizado para customizações a
     * serem aplicadas após a renderização do HTML dos
     * capítulos.
     * @param html String que representa o html renderizado de cada capítulo
     * @return html modificado.
     */
    String aposRenderizacao(String html);

    /**
     * Método para customizações a serem feitas depois da
     * geração do ebook.
     * @param ebook
     */
    void aposGeracao(Ebook ebook);

    static void renderizou(Capitulo capitulo) throws InterruptedException {
        ServiceLoader.load(Plugin.class)
            .forEach(plugin -> {
                String html = capitulo.getConteudoHTML();
                String htmlModificado = plugin.aposRenderizacao(html);
                capitulo.setConteudoHTML(htmlModificado);
            });
      System.out.println("Aplicou estilizacao!");
    }

    static void gerou(Ebook ebook) {
      ServiceLoader.load(Plugin.class)
          .forEach(plugin -> plugin.aposGeracao(ebook));
      System.out.println("Modificacao apos gerar ebook");
    }

}
