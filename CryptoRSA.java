import java.security.*;
import javax.crypto.Cipher;

public class CryptoRSA {

    private KeyPair keyPair;

    public CryptoRSA() throws NoSuchAlgorithmException {
        // Gera um par de chaves (pública e privada)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // Tamanho da chave em bits
        keyPair = keyGen.generateKeyPair();
    }

    // Retorna a chave pública
    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    // Retorna a chave privada
    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    // Criptografa uma mensagem usando a chave pública
    public byte[] encrypt(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes());
    }

    // Descriptografa uma mensagem usando a chave privada
    public String decrypt(byte[] encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(encryptedMessage));
    }

    public static void main(String[] args) {
        try {
            // Instancia a classe de criptografia RSA
            CryptoRSA cryptoRSA = new CryptoRSA();

            // Mensagem a ser criptografada
            String mensagem = "Olá, mundo!";

            // Criptografa a mensagem
            byte[] mensagemCriptografada = cryptoRSA.encrypt(mensagem, cryptoRSA.getPublicKey());
            System.out.println("Mensagem Criptografada: " + new String(mensagemCriptografada));

            // Descriptografa a mensagem
            String mensagemDescriptografada = cryptoRSA.decrypt(mensagemCriptografada, cryptoRSA.getPrivateKey());
            System.out.println("Mensagem Descriptografada: " + mensagemDescriptografada);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}