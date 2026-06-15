package trabalho.clinicaveterinaria.observador;

import java.util.Observable;
import java.util.Observer;

import trabalho.clinicaveterinaria.modelo.Atendimento;
import trabalho.clinicaveterinaria.modelo.Veterinario;

public class ObservadorVeterinario implements Observer {

    private Veterinario veterinario;
    private String ultimoAviso;

    public ObservadorVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public void acompanhar(Atendimento atendimento) {
        atendimento.addObserver(this);
    }

    public String getUltimoAviso() {
        return ultimoAviso;
    }

    @Override
    public void update(Observable atendimento, Object novoEstado) {
        if ("Cancelado".equals(novoEstado)) {
            this.ultimoAviso = "Veterinario " + veterinario.getNome() + ": um atendimento foi cancelado.";
        }
    }
}
