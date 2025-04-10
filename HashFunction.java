import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction {

    // Método para gerar o hash de uma mensagem
    public static String generateHash(String message) throws NoSuchAlgorithmException {
        // Obtém uma instância do algoritmo SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Calcula o hash da mensagem
        byte[] hashBytes = digest.digest(message.getBytes());

        // Converte o hash para uma representação hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static void main(String[] args) {
        try {
            // Mensagem a ser hasheada
            String mensagem = "Olá, mundo!";

            // Gera o hash da mensagem
            String hash = generateHash(mensagem);

            // Exibe o hash no console
            System.out.println("Mensagem: " + mensagem);
            System.out.println("Hash (SHA-256): " + hash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}