package com.github.uniliva.commonsutils.utils;

import static java.lang.String.format;
import static java.text.Normalizer.normalize;
import static java.util.Locale.getDefault;
import static java.util.ResourceBundle.getBundle;
import static org.springframework.util.Assert.isTrue;

import java.text.MessageFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class StringUtils {

	public static final String BRANCO = "";
	public static final String VIRGULA = ",";
	public static final String ASPAS_SIMPLES = "'";

	private enum DirecaoPreenchimento {
		DIREITA("%1$-"), ESQUERDA("%1$");

		private final String regra;

		private DirecaoPreenchimento(final String regra) {
			this.regra = regra;
		}
	}

	/**
	 * Recupera os textos presentes no arquivo messages.properties da API
	 *
	 * @param chaveMensagem
	 * @param params
	 * @return
	 */
	public static String getMensagem(final String chaveMensagem, final Object... params) {
		final ResourceBundle bundle = getBundle("messages", getDefault());

		return recuperarTexto(bundle, chaveMensagem, params);
	}

	public static String getNomeProjeto() {
		final ResourceBundle bundle = getBundle("application", getDefault());

		return recuperarTexto(bundle, "spring.application.name", null);
	}

	private static String recuperarTexto(final ResourceBundle bundle, final String chaveMensagem, final Object params) {
		String mensagem = BRANCO;

		try {
			mensagem = bundle.getString(chaveMensagem);
		} catch (final MissingResourceException e) {
			return chaveMensagem;
		}

		return new MessageFormat(mensagem).format(params);
	}

	/**
	 * Preenche um valor com o tamanho desejado, utilizando o caractere informado à
	 * esquerda.
	 *
	 * @param valor
	 * @param tamanho
	 * @param caractereComplementar
	 * @return
	 */
	public static String preencherAEsquerda(final String valor, final int tamanho, final char caractereComplementar) {
		return paddingStr(valor, tamanho, caractereComplementar, DirecaoPreenchimento.ESQUERDA);
	}

	public static String preencherAEsquerda(final Number valor, final int tamanho) {
		return preencherAEsquerda(valor.toString(), tamanho, '0');
	}

	/**
	 * Preenche um valor com o tamanho desejado, utilizando o caractere informado à
	 * direita.
	 *
	 * @param valor
	 * @param tamanho
	 * @param caractereComplementar
	 * @return
	 */
	public static String preencherADireita(final String valor, final int tamanho, final char caractereComplementar) {
		return paddingStr(valor, tamanho, caractereComplementar, DirecaoPreenchimento.DIREITA);
	}

	/**
	 * Remove os acentos de um texto.
	 *
	 * @param texto - Texto
	 * @return - Texto sem acentos
	 */
	public static String removerAcentos(final String texto) {
		return normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", BRANCO);
	}

	public static String removerQuebraDeLinha(final String texto) {
		return texto.replaceAll(System.lineSeparator(), BRANCO);
	}

	private static String paddingStr(final String valor, final int tamanho, final char caractereComplementar,
			final DirecaoPreenchimento direcaoPreenchimento) {
		isTrue(valor.length() <= tamanho, "Erro no preechimento!!!");
		return format(direcaoPreenchimento.regra + tamanho + "s", valor).replace(' ', caractereComplementar);
	}

	public static String preencherComAspas(final String texto) {
		return ASPAS_SIMPLES + texto + ASPAS_SIMPLES;
	}

	public static String preencherComAspas(final LocalDate data, final DateTimeFormatter formato) {
		return ASPAS_SIMPLES + data.format(formato) + ASPAS_SIMPLES;
	}
}