# Classificador Ambiental com Perceptron

Perceptron de camada única que classifica leituras de temperatura (°C) e umidade relativa (%) em **NORMAL (0)** ou **ALERTA (1)**.

## Arquivos

| Arquivo | Conteúdo |
|---|---|
| `Dados.java` | Formato do registro e os conjuntos de treino (20) e teste (8) |
| `Perceptron.java` | Modelo: normalização, previsão, atualização, treinamento e teste |
| `ClassificadorAmbiental.java` | `main`, saída no console e entrada interativa |

## Como executar

Com os três arquivos na mesma pasta:

```
javac *.java
java ClassificadorAmbiental
```

Após o treino e o teste, o programa pede temperatura e umidade para classificar novas leituras. Para encerrar, digite um valor **negativo** na temperatura.

## Registro da execução

```
=== RESULTADOS DO TREINO ===
Total de épocas executadas: 71
Erros cometidos na última época: 0
Valor final - Peso Temperatura: 0.5500
Valor final - Peso Umidade: 0.2650
Valor final - Viés: -0.6000

=== AVALIAÇÃO DE TESTE ===
Taxa de acurácia: 100.00%
```

- Parâmetros finais: peso da temperatura = 0,5500; peso da umidade = 0,2650; viés = −0,6000
- Épocas: 71 (parou por ter zerado os erros, bem abaixo do limite de 1000)
- Acurácia no teste: 100% (8 de 8)

## Respostas curtas

**(a) Para que serve o viés?**
Ele desloca a fronteira de decisão, tornando o limiar de ativação ajustável. Sem o viés, a reta que separa as classes seria obrigada a passar pela origem, o que limita bastante o que o modelo consegue aprender.

**(b) Por que normalizar as entradas?**
Temperatura (0 a 40) e umidade (0 a 100) estão em escalas diferentes. Sem normalização, a variável de maior escala domina a soma ponderada e as atualizações dos pesos ficam desiguais, deixando o treino mais lento e instável. Dividindo por 40 e por 100, as duas ficam entre 0 e 1 e os pesos se tornam comparáveis.

**(c) O que um Perceptron simples não consegue classificar?**
Problemas que não são linearmente separáveis, como o XOR. Um único Perceptron só traça uma reta (ou hiperplano) de separação, então não consegue separar classes que exigem uma fronteira curva ou com mais de uma região.
