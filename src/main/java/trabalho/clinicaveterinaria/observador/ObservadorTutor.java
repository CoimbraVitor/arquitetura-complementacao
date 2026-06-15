package trabalho.clinicaveterinaria.observador;

import java.util.Observable;
import java.util.Observer;

import trabalho.clinicaveterinaria.modelo.Atendimento;
import trabalho.clinicaveterinaria.modelo.Tutor;

public class ObservadorTutor implements Observer {

    private Tutor tutor;
    private String ultimoAviso;

    public ObservadorTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void acompanhar(Atendimento atendimento) {
        atendimento.addObserver(this);
    }

    public String getUltimoAviso() {
        return ultimoAviso;
    }

    @Override
    public void update(Observable atendimento, Object novoEstado) {
        if ("EmAtendimento".equals(novoEstado)) {
            this.ultimoAviso = "Tutor " + tutor.getNome() + ": seu atendimento foi iniciado.";
        }
    }
}
