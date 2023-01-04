package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class Cotuba {

  private final RenderizadorMDParaHTML renderizador;
  private final GeradorPDF geradorPDF;
  private final GeradorEPUB geradorEPUB;

  public Cotuba(RenderizadorMDParaHTML renderizador, GeradorPDF geradorPDF, GeradorEPUB geradorEPUB) {
    this.renderizador = renderizador;
    this.geradorPDF = geradorPDF;
    this.geradorEPUB = geradorEPUB;
  }

  public void executa(ParametrosCotuba parametros) {
    String formato = parametros.getFormato();
    Path diretorioDosMD = parametros.getDiretorioDosMD();
    Path arquivoDeSaida = parametros.getArquivoDeSaida();

    List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

    Ebook ebook = new Ebook();
    ebook.setFormato(formato);
    ebook.setArquivoDeSaida(arquivoDeSaida);
    ebook.setCapitulos(capitulos);

    if ("pdf".equals(formato)) {
      geradorPDF.gera(ebook);
    } else if ("epub".equals(formato)) {
      geradorEPUB.gera(ebook);
    } else {
      throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
    }
  }

}
