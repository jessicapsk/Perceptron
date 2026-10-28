import java.util.List;

/**
 * Perceptron de camada única com função de ativação degrau.
 * Só lógica do modelo: nenhuma leitura ou impressão.
 */
public class Perceptron {

    /** Resumo do treinamento. */
    public record Treino(int epocas, int errosUltimaEpoca) {
    }

    private static final double TAXA = 0.1;
    private static final int MAX_EPOCAS = 1000;

    private double pesoTemperatura = 0.0;
    private double pesoUmidade = 0.0;
    private double vies = 0.0;

    public double getPesoTemperatura() { return pesoTemperatura; }
    public double getPesoUmidade()     { return pesoUmidade; }
    public double getVies()            { return vies; }

    // ---------- Normalização ----------

    private double normalizarTemperatura(double temperatura) {
        return temperatura / 40.0;
    }

    private double normalizarUmidade(double umidade) {
        return umidade / 100.0;
    }

    // ---------- Previsão ----------

    /** Normaliza, calcula a soma ponderada + viés e aplica o degrau. Retorna 1 (ALERTA) ou 0 (NORMAL). */
    public int prever(double temperatura, double umidade) {
        double soma = pesoTemperatura * normalizarTemperatura(temperatura)
                    + pesoUmidade * normalizarUmidade(umidade)
                    + vies;
        return soma >= 0 ? 1 : 0;
    }

    // ---------- Atualização ----------

    /** Regra do Perceptron: w += taxa * erro * x  e  viés += taxa * erro. */
    private void atualizar(Dados.Registro r, int erro) {
        pesoTemperatura += TAXA * erro * normalizarTemperatura(r.temperatura());
        pesoUmidade     += TAXA * erro * normalizarUmidade(r.umidade());
        vies            += TAXA * erro;
    }

    // ---------- Treinamento ----------

    /** Treina até zerar os erros em uma época ou atingir o limite de épocas. */
    public Treino treinar(List<Dados.Registro> dados) {
        int epocas = 0;
        int erros = 0;

        while (epocas < MAX_EPOCAS) {
            erros = 0;
            for (Dados.Registro r : dados) {
                int erro = r.classeEsperada() - prever(r.temperatura(), r.umidade());
                if (erro != 0) {
                    atualizar(r, erro);
                    erros++;
                }
            }
            epocas++;
            if (erros == 0) break;   // parada: época inteira sem erros
        }
        return new Treino(epocas, erros);
    }

    // ---------- Teste ----------

    /** Conta os acertos no conjunto, sem alterar os pesos. */
    public int testar(List<Dados.Registro> dados) {
        int acertos = 0;
        for (Dados.Registro r : dados) {
            if (prever(r.temperatura(), r.umidade()) == r.classeEsperada()) {
                acertos++;
            }
        }
        return acertos;
    }
}
