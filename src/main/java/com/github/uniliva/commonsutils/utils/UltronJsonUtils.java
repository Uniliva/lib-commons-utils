package com.github.uniliva.commonsutils.utils;

import static java.lang.String.format;

import java.io.IOException;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.uniliva.commonsutils.exception.ParseException;
import com.github.uniliva.commonsutils.exception.ValidacaoException;

public class UltronJsonUtils {
	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
	}

	private UltronJsonUtils() {
		super();
	}

	public static String paraJson(final Object obj) {
		String jsonFormatado = null;
		try {
			jsonFormatado = mapper.writeValueAsString(obj);
		} catch (final JsonProcessingException e) {
			throw new ParseException("Erro ao converter o DTO para Json.", e.getMessage());
		}
		return jsonFormatado;
	}

	public static void validarJson(final String json) {
		try {
			final JsonNode readTree = mapper.readTree(json);
			if (readTree.isNull()) {
				throw new ValidacaoException("Erro de Json nulo.");
			}
		} catch (final IOException e) {
			throw new ValidacaoException("Erro ao validar Json.", e.getMessage());
		}
	}

	public static boolean isJsonValido(final String json) {
		try {
			mapper.readTree(json);
			return true;
		} catch (final IOException e) {
			return false;
		}

	}

	/**
	 * <p>
	 * Classe que carrega um arquivo .json e tenta converter para uma classe de
	 * acordo com o tipo passado.
	 * 
	 * @param <T>  - Classe que representa do tipo de objeto as ser deserializado
	 * @param caminhoJson  Caminho a partir do classpath. <br>
	 *                     Exemplo: (utron/src/main/resources/)
	 *                     'entrada/agendamento-01.json'
	 * @param tipoObjeto - Tipo do objeto
	 * @return Objeto de acordo com o tipo passado.
	 */

	public static <T> T paraObjViaArquivoJson(final String caminhoJson, final TypeReference<T> tipoObjeto) {
		try {
			return mapper.readValue(ResourceUtils.getFile(format("classpath:%s", caminhoJson)), tipoObjeto);
		} catch (final IOException e) {
			throw new ParseException("Erro ao tentar converter Json.", e.getMessage());
		}
	}

	
	/**
	 * Classe que o conteúdo de um json e tenta converter para uma classe de acordo
	 * com o tipo passado.
	 * @param <T>  - Classe que representa do tipo de objeto as ser deserializado
	 * @param conteudoJson - String do Json do objeto
	 * @param tipoObjeto - Tipo do objeto
	 * @return 0bjeto gerado com o Json
	 */
	public static <T> T paraObjViaJson(final String conteudoJson, final TypeReference<T> tipoObjeto) {
		try {
			return mapper.readValue(conteudoJson, tipoObjeto);
		} catch (final IOException e) {
			throw new ParseException("Erro ao tentar converter Json.", e.getMessage());
		}
	}

	public static ObjectMapper getMapper() {
		return mapper;
	}

}