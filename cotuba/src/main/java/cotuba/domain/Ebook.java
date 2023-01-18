package cotuba.domain;

import cotuba.plugin.EbookSoParaLeitura;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public final class Ebook implements EbookSoParaLeitura {

  private final FormatoEbook formato;
  private final Path arquivoDeSaida;
  private final List<Capitulo> capitulos;

  public Ebook(FormatoEbook formato, Path arquivoDeSaida, List<Capitulo> capitulos) {
    this.formato = formato;
    this.arquivoDeSaida = arquivoDeSaida;
    this.capitulos = Collections.unmodifiableList(capitulos);
  }

  @Override
  public FormatoEbook getFormato() {
    return formato;
  }

  public boolean isUltimoCapitulo(Capitulo capitulo) {
    return this.capitulos.get(this.capitulos.size() - 1).equals(capitulo);
  }

  @Override
  public Path getArquivoDeSaida() {
    return arquivoDeSaida;
  }

  @Override
  public List<Capitulo> getCapitulos() {
    return capitulos;
  }

}
