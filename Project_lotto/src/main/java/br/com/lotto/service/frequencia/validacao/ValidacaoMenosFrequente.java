package br.com.lotto.service.frequencia.validacao;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dto.Configuracoes;
import br.com.lotto.dto.FrequenciaDTO;
import br.com.lotto.dto.Jogos;
import br.com.lotto.dto.RespostaValidacao;
import br.com.lotto.service.Validacao;
import br.com.lotto.service.frequencia.FrequenciaService;
import br.com.lotto.service.frequencia.analise.AnaliseFrequencia;

/**
 * Classe de validação dos numeros frequente sorteados.Esta validacao utiliza
 * a analize de frequencia para determinar se um jogo(palpite) contem x dos
 * mumereros menos sorteados
 * 
 * @author Juliano
 *
 */
@Service
public class ValidacaoMenosFrequente implements Validacao {

	@Autowired
	private FrequenciaService frequenciaService;

	private Collection<FrequenciaDTO> menosFrequenteList;

	/**
	 * Realiza a validacao de um jogo, onde deve-se passar a quantidade numeros
	 * menos frequentes sorteados. Na mega sena
	 * 
	 * @param qtd
	 * @param jogos
	 * @return
	 */
	@Override
	public RespostaValidacao validar(Configuracoes config, Jogos jogos) {

		this.menosFrequenteList = this.frequenciaService.getMenosFrequente(config);

		AtomicBoolean valido = new AtomicBoolean(false);

		this.menosFrequenteList.stream().map(fr -> fr.getNumero()).forEach(fre -> {

			jogos.getNumeros().stream().forEach(jo -> {
				if (jo.getNumero().equals(fre) && !valido.get()) {
					valido.set(true);
				}
			});

		});

		return new RespostaValidacao(this.toString(), valido.get());
	}

	@Override
	public String toString() {
		return "Menos frequente";
	}

}