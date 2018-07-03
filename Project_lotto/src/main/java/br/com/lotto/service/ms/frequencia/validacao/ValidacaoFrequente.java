package br.com.lotto.service.ms.frequencia.validacao;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lotto.dto.ConfiguracoesDTO;
import br.com.lotto.dto.FrequenciaDTO;
import br.com.lotto.dto.PalpiteDTO;
import br.com.lotto.dto.RespostaValidacao;
import br.com.lotto.service.Validacao;
import br.com.lotto.service.ms.frequencia.FrequenciaService;

/**
 * Classe de validação de frequencia de numeros sorteados.Esta validacao a
 * configuração passa como referencia para determinar se o palpite é valido
 * 
 * @author Juliano
 *
 */
@Service
public class ValidacaoFrequente implements Validacao {

	@Autowired
	private FrequenciaService frequenciaService;

	private Collection<FrequenciaDTO> frequenciaList;

	/**
	 * Realiza a validacao de um jogo, onde deve-se passar a quantidade numeros
	 * mais frequentes sorteados. Na mega sena
	 * 
	 * @param qtd
	 * @param jogos
	 * @return
	 */
	@Override
	public RespostaValidacao validar(ConfiguracoesDTO config, PalpiteDTO palpiteDTO) {

		this.frequenciaList = this.frequenciaService.buscarFrequencias();

		AtomicBoolean valido = new AtomicBoolean(false);

		this.frequenciaList.stream().map(fr -> fr.getNumero()).forEach(fre -> {

			palpiteDTO.getNumeroCollection().stream().forEach(jo -> {
				if (jo.getIdNumero().equals(fre) && !valido.get()) {
					valido.set(true);
				}
			});

		});

		return new RespostaValidacao(this.toString(), valido.get());
	}

	@Override
	public String toString() {
		return "validação de frequência ";
	}

}
