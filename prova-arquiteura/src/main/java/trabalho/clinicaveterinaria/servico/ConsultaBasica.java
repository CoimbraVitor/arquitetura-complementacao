package trabalho.clinicaveterinaria.servico;

public class ConsultaBasica implements ServicoVeterinario {
    private float valorBase;

    public ConsultaBasica(float valorBase) {
        this.valorBase = valorBase;
    }

    public float getValor() {
        return valorBase;
    }

    public String getDescricao() {
        return "Consulta basica";
    }
}
