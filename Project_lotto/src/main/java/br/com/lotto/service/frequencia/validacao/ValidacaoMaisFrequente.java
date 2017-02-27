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
 * Classe de validação de frequencia de numeros sorteados.Esta validacao utiliza
 * a analize de frequencia para determinar se um jogo(palpite) contem x dos
 * mumereros mais ou menos sorteados
 * 
 * @author Juliano
 *
 */
@Service
public class ValidacaoMaisFrequente implements Validacao {

	@Autowired
	private FrequenciaService frequenciaService;

	private Collection<FrequenciaDTO> topFrequencia;

	/**
	 * Realiza a validacao de um jogo, onde deve-se passar a quantidade numeros
	 * mais frequentes sorteados. Na mega sena
	 * 
	 * @param qtd
	 * @param jogos
	 * @return
	 */
	@Override
	public RespostaValidacao validar(Configuracoes config, Jogos jogos) {

		this.topFrequencia = this.frequenciaService.getMaisFrequente(config);

		AtomicBoolean valido = new AtomicBoolean(false);

		this.topFrequencia.stream().map(fr -> fr.getNumero()).forEach(fre -> {

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
		return "Mais frequente";
	}

}