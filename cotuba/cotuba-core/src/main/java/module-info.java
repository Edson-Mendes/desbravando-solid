 module cotuba.core {
  exports cotuba.application;
  exports cotuba.domain;
  exports cotuba.plugin;

  uses cotuba.plugin.GeradorEbook;
  uses cotuba.plugin.AoRenderizarHTML;
  uses cotuba.plugin.AoFinalizarGeracao;

  requires org.commonmark;
 }