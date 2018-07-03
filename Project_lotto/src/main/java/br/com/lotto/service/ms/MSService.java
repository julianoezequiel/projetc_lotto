/**
 *
 */
package br.com.lotto.service.ms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.lotto.dao.MegaSenaRepository;
import br.com.lotto.dto.AtrasoDTO;
import br.com.lotto.dto.ConfiguracoesDTO;
import br.com.lotto.dto.FrequenciaDTO;
import br.com.lotto.dto.Jogos;
import br.com.lotto.dto.MegaSenaDTO;
import br.com.lotto.dto.MegaSenaResultadoSimples;
import br.com.lotto.dto.RespostaValidacao;
import br.com.lotto.entity.Megasena;
import br.com.lotto.service.ServiceException;
import br.com.lotto.service.ms.atraso.analise.AnaliseAtraso;
import br.com.lotto.service.ms.frequencia.FrequenciaService;
import br.com.lotto.service.ms.frequencia.analise.AnaliseFrequencia;
import br.com.lotto.service.ms.frequencia.validacao.ValidacaoFrequente;

/**
 * @author Juliano
 *
 */
@Service
public class MSService {

    @Autowired
    private MegaSenaRepository megaSenaRepository;
    @Autowired
    private FrequenciaService frequenciaService;
    @Autowired
    private AnaliseAtraso atrazoAnalizeservice;
    @Autowired
    private AnaliseFrequencia analiseFrequencia;

    /**
     * Lista todos os concursos
     *
     * @return
     */
    public Collection<MegaSenaDTO> buscartodos() {
        Collection<MegaSenaDTO> list = new ArrayList<>();
        this.megaSenaRepository.findAll().stream().forEach(s -> {
            list.add(s.toMegaSenaDTO());
        });
        return list;
    }

    private String sorteados;

    /**
     * Lista todos os concursos resultados Simples
     *
     * @return
     */
    public Collection<MegaSenaResultadoSimples> buscartodosSimples() {
        Collection<MegaSenaResultadoSimples> list = new ArrayList<>();
        this.megaSenaRepository.findAll().stream().forEach(s -> {
            sorteados = "";
            s.getMegasenanumeroCollection().stream().forEach(n -> {
                sorteados = sorteados + n.getNumero().getDescricao() + " - ";
            });
            sorteados = sorteados.substring(0, sorteados.lastIndexOf(" -"));
            list.add(new MegaSenaResultadoSimples(s.getConcurso(), sorteados));
        });
        return list;
    }

    /**
     * Lista todos os concursos
     *
     * @return
     */
    public Collection<Jogos> buscartodosConcursos() {
        List<Jogos> list = new ArrayList<>();

        this.megaSenaRepository.findAll().stream().forEach(s -> {
            list.add(new Jogos(s.getConcurso(), s.getMegasenanumeroCollection()));
        });
        return list;
    }

    public Collection<Jogos> buscarMenorQueConcursos(Integer id) {
        List<Jogos> list = new ArrayList<>();

        this.megaSenaRepository.buscarMenorQue(id).stream().forEach(s -> {
            list.add(new Jogos(s.getConcurso(), s.getMegasenanumeroCollection()));
        });
        return list;
    }

    /**
     * Busca o concurso pelo id do concuso
     *
     * @param id
     * @return
     */
    public MegaSenaDTO buscarPorId(Integer id) throws ServiceException {
        Megasena megasena = this.megaSenaRepository.findOne(id);
        if (megasena != null) {
            return megasena.toMegaSenaDTO();
        } else {
            throw new ServiceException(HttpStatus.NO_CONTENT, "Concurso não encontrado");
        }
    }

    /**
     * Calcula quantas vezes cada dezena ja foi sorteada
     *
     * @return
     */
    public Collection<FrequenciaDTO> buscarFrequencias() {
        return frequenciaService.buscarFrequencias();
    }

    /**
     * Calcula os atrazos das dezenas noc concursos realizados
     *
     * @return
     */
    public Collection<AtrasoDTO> buscarAtrazos() {
        return this.atrazoAnalizeservice.buscarAtrasos();
    }

    private HashMap<Long, ConfiguracoesDTO> melhorConfig = new HashMap<>();
    private int totalTentativas = 1000;
    private int passo = 0;
    private Long max = 0l;

    public HashMap<Long, ConfiguracoesDTO> iniciarAnalise() {
        max = 0l;
        totalTentativas = 1000;
        passo = 0;
        melhorConfig = new HashMap<>();

        return analisar();
    }

    private ConfiguracoesDTO config = new ConfiguracoesDTO();
    
    public  HashMap<Long, ConfiguracoesDTO> analisarFrequencia(){
    	HashMap<Object, Object> params = new HashMap<>();
    	params.put("Inicio", 1);
    	params.put("numFilter", 0);
        analiseFrequencia.init(params);
        return melhorConfig;
    }
    
    public  HashMap<Long, ConfiguracoesDTO> analisarAtraso(){
    	HashMap<Object, Object> params = new HashMap<>();
    	params.put("Inicio", 1);
    	params.put("numFilter", 0);
        analiseFrequencia.init(params);
        return melhorConfig;
    }
    
    public HashMap<Long, ConfiguracoesDTO> analisar() {
    	
    	HashMap<Object, Object> params = new HashMap<>();
    	params.put("Inicio", 1);
    	params.put("numFilter", 0);
        analiseFrequencia.init(params);

        // long totalJogos = this.megaSenaRepository.count();
        //
        // while (totalJogos > 0) {
        // config = getConfigDefault();
        // Collection<Jogos> jogos =
        // this.buscarMenorQueConcursos(config.getMaisFrequente());
        // Collection<RespostaValidacao> validacoes = new ArrayList<>();
        //
        // jogos.stream().forEach(jogo -> {
        // System.out.println("Jogo : " + jogo.toString());
        // validacoes.add(this.maisFrequenteValidacaoService.validar(config,
        // jogo));
        // validacoes.add(this.menosFrequenteValidacaoService.validar(config,
        // jogo));
        // });
        //
        // long subTotal = validacoes.stream().filter(f ->
        // f.getAprovado().equals(true)).count();
        //
        // if (subTotal > max) {
        //
        // max = subTotal;
        // melhorConfig.put(max, config.newIntance());
        // System.out.println(melhorConfig.values().toArray());
        // }
        //
        // totalJogos--;
        // System.out.println("Total de jogos : " + totalJogos);
        // }
        //
        // if (config.getMaisFrequente() != 60 || config.getMenosFrequente() !=
        // 60) {
        // passo++;
        // System.out.println("Teste : " + passo + " validação : " +
        // config.toString());
        // analizar();
        // }
        return melhorConfig;

    }

    ConfiguracoesDTO configuracoes = new ConfiguracoesDTO();

    public ConfiguracoesDTO getConfigDefault() {

        // configuracoes.setMaisAtrazado(getnumRand());
        this.configuracoes.setMaisFrequente(
                this.configuracoes.getMaisFrequente() <= 60 && this.configuracoes.getMenosFrequente() == 60
                ? this.configuracoes.getMaisFrequente() + 1 : this.configuracoes.getMaisFrequente());
        // configuracoes.setMenosAtrazado(getnumRand());
        this.configuracoes.setMenosFrequente(
                this.configuracoes.getMenosFrequente() < 60 ? this.configuracoes.getMenosFrequente() + 1 : 1);
        return configuracoes;
    }

    Random rand = new Random();

    private int getnumRand() {

        return ThreadLocalRandom.current().nextInt(1, 60);
    }

}
