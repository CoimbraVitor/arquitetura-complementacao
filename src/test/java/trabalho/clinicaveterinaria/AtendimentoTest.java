package trabalho.clinicaveterinaria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import trabalho.clinicaveterinaria.modelo.Animal;
import trabalho.clinicaveterinaria.modelo.Atendimento;
import trabalho.clinicaveterinaria.modelo.Tutor;
import trabalho.clinicaveterinaria.modelo.Veterinario;
import trabalho.clinicaveterinaria.observador.ObservadorRecepcao;
import trabalho.clinicaveterinaria.observador.ObservadorTutor;
import trabalho.clinicaveterinaria.observador.ObservadorVeterinario;
import trabalho.clinicaveterinaria.servico.BanhoPosConsulta;
import trabalho.clinicaveterinaria.servico.ConsultaBasica;
import trabalho.clinicaveterinaria.servico.DescontoAnimalAdotado;
import trabalho.clinicaveterinaria.servico.ServicoVeterinario;
import trabalho.clinicaveterinaria.servico.TaxaAtendimentoDomiciliar;

class AtendimentoTest {

    private Atendimento novoAtendimento() {
        return new Atendimento(
                new Tutor("Vitor"),
                new Animal("Rex", "Cachorro", false),
                new Veterinario("Marco Antonio", "CRMV-1234"),
                new ConsultaBasica(100.0f));
    }

    @Test
    void deveIniciarComoAgendado() {
        Atendimento a = novoAtendimento();
        assertEquals("Agendado", a.getNomeEstado());
    }

    @Test
    void deveIniciarAtendimentoAgendado() {
        Atendimento a = novoAtendimento();
        assertTrue(a.iniciar());
        assertEquals("EmAtendimento", a.getNomeEstado());
    }

    @Test
    void deveCancelarAtendimentoAgendado() {
        Atendimento a = novoAtendimento();
        assertTrue(a.cancelar());
        assertEquals("Cancelado", a.getNomeEstado());
    }

    @Test
    void deveFinalizarAtendimentoEmAtendimento() {
        Atendimento a = novoAtendimento();
        a.iniciar();
        assertTrue(a.finalizar());
        assertEquals("Finalizado", a.getNomeEstado());
    }

    @Test
    void naoDeveCancelarAtendimentoFinalizado() {
        Atendimento a = novoAtendimento();
        a.iniciar();
        a.finalizar();
        assertFalse(a.cancelar());
        assertEquals("Finalizado", a.getNomeEstado());
    }

    @Test
    void naoDeveFinalizarAtendimentoAgendado() {
        Atendimento a = novoAtendimento();
        assertFalse(a.finalizar());
        assertEquals("Agendado", a.getNomeEstado());
    }

    @Test
    void naoDeveIniciarAtendimentoCancelado() {
        Atendimento a = novoAtendimento();
        a.cancelar();
        assertFalse(a.iniciar());
        assertEquals("Cancelado", a.getNomeEstado());
    }

    @Test
    void deveAvisarTutorQuandoAtendimentoIniciar() {
        Tutor tutor = new Tutor("Felipe");
        Atendimento a = new Atendimento(tutor,
                new Animal("Rex", "Cachorro", false),
                new Veterinario("Marco Antonio", "CRMV-1"),
                new ConsultaBasica(100.0f));
        ObservadorTutor obs = new ObservadorTutor(tutor);
        obs.acompanhar(a);

        a.iniciar();

        assertEquals("Tutor Felipe: seu atendimento foi iniciado.", obs.getUltimoAviso());
    }

    @Test
    void deveAvisarVeterinarioQuandoAtendimentoCancelar() {
        Veterinario vet = new Veterinario("Marco Antonio", "CRMV-1");
        Atendimento a = new Atendimento(new Tutor("Vitor"),
                new Animal("Rex", "Cachorro", false),
                vet,
                new ConsultaBasica(100.0f));
        ObservadorVeterinario obs = new ObservadorVeterinario(vet);
        obs.acompanhar(a);

        a.cancelar();

        assertEquals("Veterinario Marco Antonio: um atendimento foi cancelado.", obs.getUltimoAviso());
    }

    @Test
    void deveAvisarRecepcaoQuandoAtendimentoFinalizar() {
        Atendimento a = novoAtendimento();
        ObservadorRecepcao obs = new ObservadorRecepcao();
        obs.acompanhar(a);

        a.iniciar();
        a.finalizar();

        assertEquals("Recepcao: atendimento finalizado, preparar pagamento.", obs.getUltimoAviso());
    }

    @Test
    void recepcaoNaoDeveSerAvisadaSeAtendimentoForCancelado() {
        Atendimento a = novoAtendimento();
        ObservadorRecepcao obs = new ObservadorRecepcao();
        obs.acompanhar(a);

        a.cancelar();

        assertNull(obs.getUltimoAviso());
    }

    @Test
    void deveCalcularValorBase() {
        Atendimento a = novoAtendimento();
        assertEquals(100.0f, a.getValorFinal());
    }

    @Test
    void deveAplicarDescontoAnimalAdotado() {
        ServicoVeterinario servico = new DescontoAnimalAdotado(new ConsultaBasica(100.0f));
        assertEquals(85.0f, servico.getValor());
    }

    @Test
    void deveAplicarTaxaDomiciliar() {
        ServicoVeterinario servico = new TaxaAtendimentoDomiciliar(new ConsultaBasica(100.0f));
        assertEquals(150.0f, servico.getValor());
    }

    @Test
    void deveAplicarBanho() {
        ServicoVeterinario servico = new BanhoPosConsulta(new ConsultaBasica(100.0f));
        assertEquals(130.0f, servico.getValor());
    }

    @Test
    void deveCombinarVariasRegras() {
        ServicoVeterinario servico = new ConsultaBasica(100.0f);
        servico = new DescontoAnimalAdotado(servico);
        servico = new TaxaAtendimentoDomiciliar(servico);
        servico = new BanhoPosConsulta(servico);
        assertEquals(165.0f, servico.getValor());
    }

    @Test
    void deveDescreverServicoComVariasRegras() {
        ServicoVeterinario servico = new ConsultaBasica(100.0f);
        servico = new DescontoAnimalAdotado(servico);
        servico = new TaxaAtendimentoDomiciliar(servico);
        servico = new BanhoPosConsulta(servico);
        assertEquals(
                "Consulta basica + Desconto animal adotado (15%) + Taxa atendimento domiciliar + Banho pos consulta",
                servico.getDescricao());
    }

    @Test
    void deveCalcularValorFinalAtravesDoAtendimento() {
        ServicoVeterinario servico = new BanhoPosConsulta(
                new TaxaAtendimentoDomiciliar(
                        new DescontoAnimalAdotado(
                                new ConsultaBasica(100.0f))));
        Atendimento a = new Atendimento(
                new Tutor("Felipe"),
                new Animal("Mel", "Gato", true),
                new Veterinario("Marco Antonio", "CRMV-1"),
                servico);
        assertEquals(165.0f, a.getValorFinal());
    }
}
