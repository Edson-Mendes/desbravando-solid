package br.com.cognitio.estatisticas;

import java.util.TreeMap;

public class ContagemDePalavras extends TreeMap<String, Integer> {

  void adicionaPalavra(String palavra) {
    Integer contagem = get(palavra);

    if(contagem != null) {
      contagem++;
    } else {
      contagem = 1;
    }

    put(palavra, contagem);
  }

}
