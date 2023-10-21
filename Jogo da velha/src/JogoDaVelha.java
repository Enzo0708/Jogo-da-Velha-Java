import javax.swing.JOptionPane;

public class JogoDaVelha {
    private char[][] tabuleiro;
    private boolean jogadorX;

    public JogoDaVelha() {
        tabuleiro = new char[3][3];
        jogadorX = true;
        inicializarTabuleiro();
    }

    public void jogar() {
        while (true) {
            exibirTabuleiro();

            int[] jogada = obterJogada();
            int linha = jogada[0];
            int coluna = jogada[1];

            if (tabuleiro[linha][coluna] == ' ') {
                tabuleiro[linha][coluna] = jogadorX ? 'X' : 'O';
                jogadorX = !jogadorX;
            } else {
                JOptionPane.showMessageDialog(null, "Essa posição já está ocupada. Tente novamente.");
            }

            char vencedor = verificarVencedor();
            if (vencedor != ' ') {
                exibirTabuleiro();
                JOptionPane.showMessageDialog(null, "Jogador " + vencedor + " venceu!");
                break;
            }

            if (verificarEmpate()) {
                exibirTabuleiro();
                JOptionPane.showMessageDialog(null, "O jogo terminou em empate!");
                break;
            }
        }
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    private void exibirTabuleiro() {
        StringBuilder tab = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tab.append(tabuleiro[i][j]);
                if (j < 2) {
                    tab.append(" | ");
                }
            }
            tab.append("\n");
            if (i < 2) {
                tab.append("---------\n");
            }
        }
        JOptionPane.showMessageDialog(null, tab.toString());
    }

    private int[] obterJogada() {
        int[] jogada = new int[2];
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                jogada[0] = Integer.parseInt(JOptionPane.showInputDialog("Digite a linha (0, 1, 2):"));
                jogada[1] = Integer.parseInt(JOptionPane.showInputDialog("Digite a coluna (0, 1, 2):"));
                if (jogada[0] >= 0 && jogada[0] < 3 && jogada[1] >= 0 && jogada[1] < 3) {
                    entradaValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Tente novamente.");
            }
        }
        return jogada;
    }

    private char verificarVencedor() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] != ' ' && tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0];
            }
            if (tabuleiro[0][i] != ' ' && tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i];
            }
        }
        if (tabuleiro[0][0] != ' ' && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0];
        }
        if (tabuleiro[0][2] != ' ' && tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2];
        }
        return ' ';
    }

    private boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}