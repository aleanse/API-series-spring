package br.com.aleanse.clonenetflix.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
