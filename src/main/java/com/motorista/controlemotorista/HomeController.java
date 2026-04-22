package com.motorista.controlemotorista;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired 
    private LancamentoRepository repository;

    @GetMapping("/")
    public String paginaInicial(Model model) {
        // Busca tudo o que salvamos no banco
        var lista = repository.findAll();
        
        // Calcula o saldo (Ganhos - Gastos)
        double total = lista.stream().mapToDouble(Lancamento::getValor).sum();
        
        // Envia a lista, o total e um objeto vazio para o formulário de cadastro
        model.addAttribute("lancamentos", lista);
        model.addAttribute("total", total);
        model.addAttribute("lancamento", new Lancamento()); 
        
        return "index";
    }
    
    @PostMapping("/salvar")
    public String salvar(Lancamento lancamento) {
        // Se não tiver data (novo registro), coloca a data de hoje
        if (lancamento.getData() == null) {
            lancamento.setData(java.time.LocalDate.now());
        }
        repository.save(lancamento);
        return "redirect:/";
    }

    // Método para EXCLUIR
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/"; 
    }

    // Método para EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Optional<Lancamento> lancamento = repository.findById(id);
        
        if (lancamento.isPresent()) {
            model.addAttribute("lancamento", lancamento.get());
            var lista = repository.findAll();
            model.addAttribute("lancamentos", lista);
            return "index"; // Se você usa a mesma página para cadastrar e editar
        }
        
        return "redirect:/";
    }
}