package cotuba.cli;

import cotuba.application.Cotuba;
import cotuba.cli.LeitorOpcoesCLI;

import java.nio.file.Path;

public class Main {

  public static void main(String[] args) {
    Path diretorioDosMD;
    String formato;
    Path arquivoDeSaida;
    boolean modoVerboso = false;

    try {
      var opcoes = new LeitorOpcoesCLI(args);

      diretorioDosMD = opcoes.getDiretorioDosMD();
      formato = opcoes.getFormato();
      arquivoDeSaida = opcoes.getArquivoDeSaida();
      modoVerboso = opcoes.isModoVerboso();

      var cotuba = new Cotuba();
      cotuba.executa(formato, diretorioDosMD, arquivoDeSaida);

      System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      if (modoVerboso) {
        ex.printStackTrace();
      }

      System.exit(1);
    }
  }

}
