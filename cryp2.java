
import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.cert.*;
import java.util.Scanner;

public class CryptoAES {

    private byte[] textoCifrado;
    private byte[] textoDecifrado;

    public CryptoAES() {
        textoCifrado = null;
        textoDecifrado = null;
    }

    // Gera uma chave AES e a salva em um arquivo
    public void geraChave(File fSim)
            throws IOException, NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128); // Tamanho da chave em bits
        SecretKey sk = kg.generateKey();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fSim))) {
            oos.writeObject(sk);
        }
    }

    // Gera a cifra (criptografa o texto)
    public void geraCifra(byte[] texto, File fSim)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, IOException, ClassNotFoundException {
        // Lê a chave secreta do arquivo
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fSim));
        SecretKey sk = (SecretKey) ois.readObject();
        ois.close();

        // Inicializa o cifrador
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, sk);

        // Criptografa o texto
        textoCifrado = cipher.doFinal(texto);
    }

    // Decifra o texto criptografado
    public void geraDecifra(File fSim)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, IOException, ClassNotFoundException {
        // Lê a chave secreta do arquivo
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fSim));
        SecretKey sk = (SecretKey) ois.readObject();
        ois.close();

        // Inicializa o cifrador no modo de decifração
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sk);

        // Decifra o texto
        textoDecifrado = cipher.doFinal(textoCifrado);
    }

    // Retorna o texto cifrado
    public byte[] getTextoCifrado() {
        return textoCifrado;
    }

    // Retorna o texto decifrado
    public byte[] getTextoDecifrado() {
        return textoDecifrado;
    }
}