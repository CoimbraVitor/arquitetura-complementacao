package trabalho.clinicaveterinaria.observador;

import java.util.Observable;
import java.util.Observer;

import trabalho.clinicaveterinaria.modelo.Atendimento;

public class ObservadorRecepcao implements Observer {

    private String ultimoAviso;

    public void acompanhar(Atendimento atendimento) {
        atendimento.addObserver(this);
    }

    public String getUltimoAviso() {
        return ultimoAviso;
    }

    @Override
    public void update(Observable atendimento, Object novoEstado) {
        if ("Finalizado".equals(novoEstado)) {
            this.ultimoAviso = "Recepcao: atendimento finalizado, preparar pagamento.";
        }
    }
}
