package trabalho.clinicaveterinaria.modelo;

public class Veterinario {
    private String nome;
    private String crmv;

    public Veterinario(String nome, String crmv) {
        this.nome = nome;
        this.crmv = crmv;
    }

    public String getNome() {
        return nome;
    }
}
