package br.com.stela;

import java.util.Scanner;

public class CalculadoraSalario {
    public static void main(String[] args) {
        System.out.println("===================================================\n");
        System.out.println("Seja bem-vindo(a) a calculadora de salário líquido!\n");
        System.out.println("Por favor, informe os 5 salários que você deseja calcular");
        System.out.println("\n===================================================");

        double[] salarios = new double[5];
        processaInformacoes(salarios);
    }

    public static void processaInformacoes(double[] salarios) {
        Scanner scanner = new Scanner(System.in);
        int contador = 1;

        do {
            System.out.printf("\nInforme o %s salário: ", contador);
            String salario  = scanner.nextLine();
            salarios[contador - 1] = Double.parseDouble(salario.replace(",", "."));

            contador++;
        }while (contador < 6);

        for (int index = 0 ; index < salarios.length; index++)  {
            double salario = salarios[index];

            if (salario <= 1212) {
                aplicaDesconto(index, salario, 0.075);
            }

            if (salario >= 1212.01 && salario <= 2427.35) {
                aplicaDesconto(index, salario, 0.09);
            }

            if (salario >= 2427.36 && salario <= 3641.03) {
                aplicaDesconto(index, salario, 0.12);
            }

            if (salario >= 3641.04) {
                aplicaDesconto(index, salario, 0.14);
            }
        }
    }

    public static void aplicaDesconto(int index, double salario, double inss) {
        double impostoRenda = 0;
        index++;

        if (salario >= 1903.99 && salario <= 2826.65) {
            impostoRenda = 0.075;
        }

        if (salario >= 2826.66 && salario <= 3751.05) {
            impostoRenda = 0.15;
        }

        if (salario >= 3751.06 && salario <= 4664.68) {
            impostoRenda = 0.225;
        }

        if (salario >= 4664.68) {
            impostoRenda = 0.275;
        }

        double descontoINSS = salario * inss;
        double descontoIR = salario * impostoRenda;
        double salariLiquido = salario - descontoINSS - descontoIR;

        StringBuilder informacoes = new StringBuilder("\nO %s salario de R$ %.2f foi calculado com base em: \n")
                .append("Valor descontado de INSS: R$ %.2f\n")
                .append("Valor descontado de Imposto de Renda: R$ %.2f\n")
                .append("Salário Líquido: R$ %.2f\n");

        System.out.printf(informacoes.toString(), index, salario, descontoINSS, descontoIR, salariLiquido);
    }
}