package com.moizesjr.passeio_api.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.moizesjr.passeio_api.model.Categoria;
import com.moizesjr.passeio_api.model.Lugar;
import com.moizesjr.passeio_api.repository.CategoriaRepository;
import com.moizesjr.passeio_api.repository.LugarRepository;

@Configuration
public class DadosIniciais {

  @Bean
  public CommandLineRunner carregarDados(LugarRepository lugarRepo, CategoriaRepository categoriaRepo) {
    return args -> {
      // Se o banco estiver vazio, cria dados de teste
      if (lugarRepo.count() == 0) {

        // 1. Criar Categorias
        Categoria cat1 = new Categoria();
        cat1.setNome("Praias");
        cat1.setDescricao("Praias excelentes para relaxar e aproveitar o sol.");
        categoriaRepo.save(cat1);

        Categoria cat2 = new Categoria();
        cat2.setNome("Histórico");
        cat2.setDescricao("Lugares históricos com muita cultura e tradição.");
        categoriaRepo.save(cat2);

        Categoria cat3 = new Categoria();
        cat3.setNome("Restaurantes");
        cat3.setDescricao("Os melhores restaurantes da região.");
        categoriaRepo.save(cat3);

        // 2. Criar Lugares
        Lugar lugar1 = new Lugar();
        lugar1.setNome("Porto de Galinhas");
        lugar1.setCategoria("Praia");
        lugar1.setLocalizacao("Ipojuca - PE");
        lugar1.setAvaliacao(4.8);
        lugar1
            .setUrlFoto(
                "https://www.dicasportodegalinhas.com.br/wp-content/uploads/2023/07/PiscNat-AHPG_vlubambo-7563-1980x1450.jpg");

        Lugar lugar2 = new Lugar();
        lugar2.setNome("Marco Zero");
        lugar2.setCategoria("Histórico");
        lugar2.setLocalizacao("Recife - PE");
        lugar2.setAvaliacao(4.5);
        lugar2.setUrlFoto(
            "https://www.quintoandar.com.br/guias/wp-content/uploads/2022/02/Praca-do-Maraco-Zero-Rosa-dos-Ventos-Abre.jpg");

        Lugar lugar3 = new Lugar();
        lugar3.setNome("Restaurante Leite");
        lugar3.setCategoria("Restaurante");
        lugar3.setLocalizacao("Recife - PE");
        lugar3.setAvaliacao(4.7);
        lugar3.setUrlFoto("https://media-cdn.tripadvisor.com/media/photo-s/09/55/54/cb/leite.jpg");

        lugarRepo.saveAll(Arrays.asList(lugar1, lugar2));

        System.out.println("--- BANCO DE DADOS CARREGADO COM SUCESSO ---");
      }
    };
  }
}