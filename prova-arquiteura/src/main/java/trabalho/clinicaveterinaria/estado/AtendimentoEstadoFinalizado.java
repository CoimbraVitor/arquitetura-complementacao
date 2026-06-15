package trabalho.clinicaveterinaria.estado;

public class AtendimentoEstadoFinalizado extends AtendimentoEstado {
    private static AtendimentoEstadoFinalizado instance = new AtendimentoEstadoFinalizado();
    private AtendimentoEstadoFinalizado() {}
    public static AtendimentoEstadoFinalizado getInstance() { return instance; }

    public String getNome() { return "Finalizado"; }
}
