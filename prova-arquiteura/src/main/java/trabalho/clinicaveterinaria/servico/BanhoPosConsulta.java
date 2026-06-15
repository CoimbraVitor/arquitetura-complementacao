package trabalho.clinicaveterinaria.servico;

public class BanhoPosConsulta extends ServicoDecorator {
    public BanhoPosConsulta(ServicoVeterinario servico) {
        super(servico);
    }

    public float getAjusteValor() {
        return 30.0f;
    }

    public String getNomeRegra() {
        return "Banho pos consulta";
    }
}
