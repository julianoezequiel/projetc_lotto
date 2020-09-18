package br.com.ot.service.ms.frequ.validacao;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ot.dto.ConfiguracoesDTO;
import br.com.ot.dto.FrequenciaDTO;
import br.com.ot.dto.Ppt;
import br.com.ot.dto.RespostaValidacao;
import br.com.ot.service.Validacao;
import br.com.ot.service.ms.JGDerivadoValidacao;
import br.com.ot.service.ms.frequ.FrequenciaService;

/**
 * Classe de validação de frequencia de numeros sorteados.Esta validacao a
 * configuração passa como referencia para determinar se o pptEntity é valido
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
	public RespostaValidacao validar(ConfiguracoesDTO config, Ppt ppt,List<JGDerivadoValidacao> list) {

		this.frequenciaList = this.frequenciaService.buscarFrequencias();

		AtomicBoolean valido = new AtomicBoolean(false);

		this.frequenciaList.stream().map(fr -> fr.getNumero()).forEach(fre -> {

			ppt.getNumeroCollection().stream().forEach(jo -> {
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
