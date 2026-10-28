import java.util.List;


public class Dados {

    /** Registro de dados ambientais (classe 0 = NORMAL, 1 = ALERTA). */
    public record Registro(double temperatura, double umidade, int classeEsperada) {
    }

    /** 20 registros: 10 NORMAL (0) e 10 ALERTA (1). */
    public static final List<Registro> TREINO = List.of(
            // Classe 0 (NORMAL)
            reg(24, 40, 0), reg(27, 45, 0), reg(29, 40, 0), reg(25, 70, 0), reg(31, 45, 0),
            reg(28, 75, 0), reg(35, 30, 0), reg(30, 60, 0), reg(27, 75, 0), reg(26, 50, 0),
            // Classe 1 (ALERTA)
            reg(32, 65, 1), reg(34, 60, 1), reg(36, 50, 1), reg(31, 80, 1), reg(29, 90, 1),
            reg(35, 75, 1), reg(37, 40, 1), reg(33, 85, 1), reg(34, 70, 1), reg(30, 85, 1));

    /** 8 registros variados para teste. */
    public static final List<Registro> TESTE = List.of(
            reg(23, 55, 0), reg(30, 50, 0), reg(34, 35, 0), reg(28, 85, 1),
            reg(33, 60, 1), reg(36, 70, 1), reg(26, 85, 0), reg(32, 75, 1));

    private static Registro reg(double temperatura, double umidade, int classe) {
        return new Registro(temperatura, umidade, classe);
    }
}
