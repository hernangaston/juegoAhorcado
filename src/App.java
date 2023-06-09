import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String[] palabras = { "gato", "perro", "elefante", "jirafa", "tortuga", "murcielago", "camaleon", "hipopotamo", "gacela", "leopardo", "cebra" };
        String palabraSeleccionada = palabras[(int) (Math.random() * palabras.length)];
        int intentosRestantes = 6;
        boolean[] letrasAdivinadas = new boolean[palabraSeleccionada.length()];

        System.out.println("Bienvenido al juego del ahorcado!");
        System.out.println("Adivina la palabra:");
        System.out.println(mostrarAhorcado(intentosRestantes));

        while (true) {
            System.out.println(obtenerEstadoPalabra(palabraSeleccionada, letrasAdivinadas));
            System.out.println("Intentos restantes: " + intentosRestantes);
            System.out.print("Ingresa una letra: ");
            String input = scanner.nextLine();

            if (input.length() != 1) {
                System.out.println(mostrarAhorcado(intentosRestantes));
                System.out.println("Ingresa solo una letra: \n");
                continue;
            }

            char letra = input.charAt(0);
            boolean acierto = false;

            for (int i = 0; i < palabraSeleccionada.length(); i++) {
                if (palabraSeleccionada.charAt(i) == letra) {
                    if (!letrasAdivinadas[i]) {
                        letrasAdivinadas[i] = true;
                        acierto = true;
                    }
                }
            }

            if (!acierto) {
                intentosRestantes--;
                System.out.println("++++++++++++++++++++++++++++++++");
                System.out.println("Letra incorrecta.");
                System.out.println(mostrarAhorcado(intentosRestantes));
            }else{
                System.out.println("++++++++++++++++++++++++++++++++");
                System.out.println(mostrarAhorcado(intentosRestantes));
            }

            if (intentosRestantes == 0) {
                System.out.println("++++++++++++++++++++++++++++++++");
                System.out.println("¡Perdiste! La palabra correcta era: " + palabraSeleccionada);
                System.out.println(mostrarAhorcado(intentosRestantes));
                break;
            }

            if (todasLetrasAdivinadas(letrasAdivinadas)) {
                System.out.println("++++++++++++++++++++++++++++++++");
                System.out.println("¡Ganaste! Has adivinado la palabra: " + palabraSeleccionada);
                System.out.println(mostrarAhorcado(intentosRestantes));
                System.out.println("Te sobraron "+intentosRestantes+" intentos!");
                break;
            }

        }

        scanner.close();
    }

    //generar una funcion para mostrar el ahorcado hacer switch case para ir dibujando a medida que restan intentos
    public static String mostrarAhorcado(int intentos){
        StringBuilder dibujo = new StringBuilder();
        dibujo.append(" _______\n");
        dibujo.append("/       \\\n");
        switch (intentos) {
            case 6:
                dibujo.append("|\n");
                dibujo.append("|\n");
                dibujo.append("|\n");
                break;
            case 5:
                dibujo.append("|       O\n");
                dibujo.append("|\n");
                dibujo.append("|\n");
                break;
            case 4:
                dibujo.append("|       O\n");
                dibujo.append("|       |\n");
                dibujo.append("|\n");
                break;
            case 3:
                dibujo.append("|       O\n");
                dibujo.append("|       |\n");
                dibujo.append("|      /\n");
                break;
            case 2:
                dibujo.append("|       O\n");
                dibujo.append("|       |\n");
                dibujo.append("|      / \\\n");
                break;
            case 1:
                dibujo.append("|       O\n");
                dibujo.append("|      -|\n");
                dibujo.append("|      / \\\n");
                break;
            case 0:
                dibujo.append("|       O\n");
                dibujo.append("|      -|-\n");
                dibujo.append("|      / \\\n");
                break;
            default:
        }
        dibujo.append("|\n");
        dibujo.append("|\n");
        return dibujo.toString();
    }
    public static String obtenerEstadoPalabra(String palabra, boolean[] letrasAdivinadas) {
        StringBuilder estado = new StringBuilder();

        for (int i = 0; i < palabra.length(); i++) {
            if (letrasAdivinadas[i]) {
                estado.append(palabra.charAt(i));
            } else {
                estado.append(" __");
            }
            estado.append(" ");
        }
        return estado.toString();
    }
    public static boolean todasLetrasAdivinadas(boolean[] letrasAdivinadas) {
        for (boolean adivinada : letrasAdivinadas) {
            if (!adivinada) {
                return false;
            }
        }
        return true;
    }
}
