package com.motorista.controlemotorista;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    // Aqui não precisa escrever nada, o Spring já faz a mágica de salvar!
}