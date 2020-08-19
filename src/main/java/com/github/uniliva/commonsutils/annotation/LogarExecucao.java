package com.github.uniliva.commonsutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.uniliva.commonsutils.config.AopConfig;

/**
 * <p>
 * Anotação utilizada para tornar os métodos visíveis ao log de execuções via
 * AspectJ <br>
 *
 * Métodos anotados irão medir o tempo de início e fim de suas execuções.
 *
 * @see AopConfig
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogarExecucao {

}
