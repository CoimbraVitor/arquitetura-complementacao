package trabalho.clinicaveterinaria.servico;

public class DescontoAnimalAdotado extends ServicoDecorator {

    public DescontoAnimalAdotado(ServicoVeterinario servico) {
        super(servico);
    }

    public float getAjusteValor() {
        return -1 * 0.15f * getServico().getValor();
    }

    public String getNomeRegra() {
        return "Desconto animal adotado (15%)";
    }
}
