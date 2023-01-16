package cotuba.domain;

import cotuba.plugin.EbookSoParaLeitura;

import java.nio.file.Path;
import java.util.List;

public class Ebook implements EbookSoParaLeitura {

  private FormatoEbook formato;
  private Path arquivoDeSaida;
  private List<Capitulo> capitulos;

  @Override
  public FormatoEbook getFormato() {
    return formato;
  }

  public boolean isUltimoCapitulo(Capitulo capitulo) {
    return this.capitulos.get(this.capitulos.size() - 1).equals(capitulo);
  }

  public void setFormato(FormatoEbook formato) {
    this.formato = formato;
  }

  @Override
  public Path getArquivoDeSaida() {
    return arquivoDeSaida;
  }

  public void setArquivoDeSaida(Path arquivoDeSaida) {
    this.arquivoDeSaida = arquivoDeSaida;
  }

  @Override
  public List<Capitulo> getCapitulos() {
    return capitulos;
  }

  public void setCapitulos(List<Capitulo> capitulos) {
    this.capitulos = capitulos;
  }
}
