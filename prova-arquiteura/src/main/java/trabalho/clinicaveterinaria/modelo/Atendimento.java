package trabalho.clinicaveterinaria.modelo;

import java.util.Observable;

import trabalho.clinicaveterinaria.estado.AtendimentoEstado;
import trabalho.clinicaveterinaria.estado.AtendimentoEstadoAgendado;
import trabalho.clinicaveterinaria.servico.ServicoVeterinario;

public class Atendimento extends Observable {

    private Tutor tutor;
    private Animal animal;
    private Veterinario veterinario;
    private ServicoVeterinario servico;
    private AtendimentoEstado estado;

    public Atendimento(Tutor tutor, Animal animal, Veterinario veterinario, ServicoVeterinario servico) {
        this.tutor = tutor;
        this.animal = animal;
        this.veterinario = veterinario;
        this.servico = servico;
        this.estado = AtendimentoEstadoAgendado.getInstance();
    }

    public void setEstado(AtendimentoEstado estado) {
        this.estado = estado;
        setChanged();
        notifyObservers(estado.getNome());
    }

    public boolean iniciar() { 
        return estado.iniciar(this); 
    }
    public boolean finalizar() { 
        return estado.finalizar(this); 
    }
    public boolean cancelar() { 
        return estado.cancelar(this); 
    }

    public String getNomeEstado() { 
        return estado.getNome(); 
    }

    public float getValorFinal() {
        return servico.getValor();
    }

    public String getDescricaoServico() {
        return servico.getDescricao();
    }
}
