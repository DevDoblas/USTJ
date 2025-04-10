import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

// cryp2Test.java

@Test
public void testEmptyPlaintext() throws Exception {
    // Generate key
    cryptoAES.geraChave(keyFile);

    // Empty plaintext
    byte[] plaintext = new byte[0];

    // Encrypt
    cryptoAES.geraCifra(plaintext, keyFile);
    byte[] encryptedText = cryptoAES.getTextoCifrado();
    assertNotNull(encryptedText, "Encrypted text should not be null");
    assertTrue(encryptedText.length > 0, "Encrypted text should not be empty");

    // Decrypt
    cryptoAES.geraDecifra(keyFile);
    byte[] decryptedText = cryptoAES.getTextoDecifrado();
    assertNotNull(decryptedText, "Decrypted text should not be null");
    assertEquals(0, decryptedText.length, "Decrypted text should be empty");
}

@Test
public void testInvalidKeyFile() {
    File invalidKeyFile = new File("nonexistent.key");

    Exception exception = assertThrows(IOException.class, () -> {
        cryptoAES.geraCifra("Test".getBytes(StandardCharsets.UTF_8), invalidKeyFile);
    });

    assertTrue(exception.getMessage().contains("nonexistent.key"), "Exception should mention the invalid key file");
}

@Test
public void testCorruptedKeyFile() throws Exception {
    // Create a corrupted key file
    try (FileWriter writer = new FileWriter(keyFile)) {
        writer.write("corrupted data");
    }

    Exception exception = assertThrows(ClassNotFoundException.class, () -> {
        cryptoAES.geraCifra("Test".getBytes(StandardCharsets.UTF_8), keyFile);
    });

    assertNotNull(exception, "Exception should be thrown for corrupted key file");
}
