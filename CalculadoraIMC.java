import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraIMC extends JFrame {
    private JTextField campoPeso;
    private JTextField campoAltura;
    private JLabel labelResultado;
    private JLabel labelClassificacao;

    public CalculadoraIMC() {
        // Configurações da Janela
        setTitle("Calculadora de IMC");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Rótulo e campo para peso
        JLabel labelPeso = new JLabel("Peso (Kg):");
        campoPeso = new JTextField();
        painel.add(labelPeso);
        painel.add(campoPeso);

        // Rótulo e campo para altura
        JLabel labelAltura = new JLabel("Altura (Cm):");
        campoAltura = new JTextField();
        painel.add(labelAltura);
        painel.add(campoAltura);

        // Botão para calcular
        JButton botaoCalcular = new JButton("Calcular IMC");
        painel.add(new JLabel()); // Espaço vazio
        painel.add(botaoCalcular);

        // Rótulo para resultado
        labelResultado = new JLabel("Resultado: ");
        painel.add(labelResultado);
        painel.add(new JLabel()); // Espaço vazio

        // Rótulo para classificação
        labelClassificacao = new JLabel("Classificação: ");
        painel.add(labelClassificacao);
        painel.add(new JLabel()); // Espaço vazio

        // Ação do botão
        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });

        // Adicionar painel à janela
        add(painel);
        setVisible(true);
    }

    private void calcularIMC() {
        try {
            // Obter valores dos campos
            double peso = Double.parseDouble(campoPeso.getText());
            double altura = Double.parseDouble(campoAltura.getText());

            // Validar entrada
            if (peso <= 0 || altura <= 0) {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores positivos!", 
                    "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Converter altura de cm para metros
            altura = altura / 100;

            // Calcular IMC
            double imc = peso / (altura * altura);

            // Obter classificação
            String classificacao = obterClassificacao(imc);

            // Exibir resultados
            labelResultado.setText(String.format("Resultado: %.2f", imc));
            labelClassificacao.setText("Classificação: " + classificacao);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos!", 
                "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obterClassificacao(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else if (imc < 35) {
            return "Obesidade Grau I";
        } else if (imc < 40) {
            return "Obesidade Grau II";
        } else {
            return "Obesidade Grau III";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculadoraIMC();
            }
        });
    }
}

