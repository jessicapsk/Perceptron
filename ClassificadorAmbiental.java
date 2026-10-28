import java.util.List;
import java.util.Scanner;

public class ClassificadorAmbiental {

    public static void main(String[] args) {
        Perceptron perceptron = new Perceptron();

        mostrarTreino(perceptron, perceptron.treinar(Dados.TREINO));
        mostrarTeste(perceptron, Dados.TESTE);
        modoInterativo(perceptron);
    }

    private static void mostrarTreino(Perceptron p, Perceptron.Treino t) {
        System.out.println("=== RESULTADOS DO TREINO ===");
        System.out.println("Total de épocas executadas: " + t.epocas());
        System.out.println("Erros cometidos na última época: " + t.errosUltimaEpoca());
        System.out.printf("Valor final - Peso Temperatura: %.4f\n", p.getPesoTemperatura());
        System.out.printf("Valor final - Peso Umidade: %.4f\n", p.getPesoUmidade());
        System.out.printf("Valor final - Viés: %.4f\n", p.getVies());
        System.out.println("============================\n");
    }

    private static void mostrarTeste(Perceptron p, List<Dados.Registro> teste) {
        System.out.println("=== AVALIAÇÃO DE TESTE ===");
        System.out.println("ID\tTemp\tUmid\tPrev\tEsp\tAcertou?");

        int id = 1;
        for (var r : teste) {
            int previsto = p.prever(r.temperatura(), r.umidade());
            boolean acertou = previsto == r.classeEsperada();

            System.out.printf("%d\t%.1f\t%.1f\t%d\t%d\t%s\n", id++,
                    r.temperatura(), r.umidade(), previsto, r.classeEsperada(),
                    acertou ? "Sim" : "Não");
        }
        System.out.printf("\nTaxa de acurácia: %.2f%%\n", (double) p.testar(teste) / teste.size() * 100);
        System.out.println("==========================\n");
    }

    private static void modoInterativo(Perceptron p) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== CLASSIFICAÇÃO INTERATIVA ===");
            System.out.println("Para encerrar, digite um valor negativo para a temperatura.");

            while (true) {
                System.out.print("\nDigite a temperatura (°C): ");
                double temperatura = sc.nextDouble();
                if (temperatura < 0) {
                    System.out.println("A encerrar o sistema...");
                    return;
                }

                System.out.print("Digite a umidade relativa (%): ");
                double umidade = sc.nextDouble();

                System.out.println(p.prever(temperatura, umidade) == 1
                        ? "-> Classificação: ALERTA (1)"
                        : "-> Classificação: NORMAL (0)");
            }
        }
    }
}
