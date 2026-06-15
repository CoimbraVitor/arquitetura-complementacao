package trabalho.clinicaveterinaria.estado;

public class AtendimentoEstadoCancelado extends AtendimentoEstado {
    private static AtendimentoEstadoCancelado instance = new AtendimentoEstadoCancelado();
    private AtendimentoEstadoCancelado() {}
    public static AtendimentoEstadoCancelado getInstance() { return instance; }

    public String getNome() { return "Cancelado"; }
}
