package com.motorista.controlemotorista;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String descricao; // Ex: Corrida Uber, Gasolina, Troca de óleo
    private Double valor;     // Ex: 50.00 ou -20.00
    private LocalDate data;

    // Construtor vazio (obrigatório para o Spring)
    public Lancamento() {}

    // Getters e Setters (Necessários para o Java ler os dados)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}