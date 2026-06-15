package trabalho.clinicaveterinaria.estado;

import trabalho.clinicaveterinaria.modelo.Atendimento;

public class AtendimentoEstadoAgendado extends AtendimentoEstado {
    private static AtendimentoEstadoAgendado instance = new AtendimentoEstadoAgendado();
    private AtendimentoEstadoAgendado() {}
    public static AtendimentoEstadoAgendado getInstance() { return instance; }

    public String getNome() { return "Agendado"; }

    public boolean iniciar(Atendimento atendimento) {
        atendimento.setEstado(AtendimentoEstadoEmAtendimento.getInstance());
        return true;
    }

    public boolean cancelar(Atendimento atendimento) {
        atendimento.setEstado(AtendimentoEstadoCancelado.getInstance());
        return true;
    }
}
