package trabalho.clinicaveterinaria.estado;

import trabalho.clinicaveterinaria.modelo.Atendimento;

public class AtendimentoEstadoEmAtendimento extends AtendimentoEstado {
    private static AtendimentoEstadoEmAtendimento instance = new AtendimentoEstadoEmAtendimento();
    private AtendimentoEstadoEmAtendimento() {}
    public static AtendimentoEstadoEmAtendimento getInstance() { return instance; }

    public String getNome() { return "EmAtendimento"; }

    public boolean finalizar(Atendimento atendimento) {
        atendimento.setEstado(AtendimentoEstadoFinalizado.getInstance());
        return true;
    }
}
