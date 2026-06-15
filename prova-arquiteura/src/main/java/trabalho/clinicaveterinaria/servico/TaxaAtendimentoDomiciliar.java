package trabalho.clinicaveterinaria.servico;

public class TaxaAtendimentoDomiciliar extends ServicoDecorator {
    public TaxaAtendimentoDomiciliar(ServicoVeterinario servico) {
        super(servico);
    }

    public float getAjusteValor() {
        return 50.0f;
    }

    public String getNomeRegra() {
        return "Taxa atendimento domiciliar";
    }
}
