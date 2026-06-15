package trabalho.clinicaveterinaria.servico;

public abstract class ServicoDecorator implements ServicoVeterinario {
    private ServicoVeterinario servico;

    public ServicoDecorator(ServicoVeterinario servico) {
        this.servico = servico;
    }

    public ServicoVeterinario getServico() {
        return servico;
    }

    public abstract float getAjusteValor();

    public float getValor() {
        return this.servico.getValor() + this.getAjusteValor();
    }

    public abstract String getNomeRegra();

    public String getDescricao() {
        return this.servico.getDescricao() + " + " + this.getNomeRegra();
    }
}
