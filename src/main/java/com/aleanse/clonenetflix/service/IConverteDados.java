package com.aleanse.clonenetflix.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException;
}
