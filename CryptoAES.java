import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.cert.*;
  public class CryptoAES
  {

    private   byte[] textoCifrado;
    private   byte[] textoDecifrado;
       public  CryptoAES()
    {
    textoCifrado = null;
    textoDecifrado = null;
  }
  public void geraChave(File fSim)
   throws IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertificateException, KeyStoreException
{
KeyGenerator kg = KeyGenerator.getInstance("AES");
kg.init(128);
SecretKey sk = kg.generateKey();
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fSim));
oos.writeobject(sk); 
oos.close(); 
}
Exemplo 
public void geraCifra (byte[] texto, File f5in) 
 throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, Illegal@lockSizeException, BadPaddingException, 
InvalidAlgoritheParameterException, IOException, ClassliotFoundException {ObjectInputStream ois new ObjectInputStream (new FileInputStream (fSim)); 
 Secretkey Sim (SecretKey) ois.readObject(); 
 byte[] chave isim.getEncoded(); 
 ois.close(); 
 Cipher aescf Cipher.getInstance ("AES/CBC/PKCS5Padding"); 
 IvParameterSpec Ivspec new IvParameterSpec (new byte[16]); 
 aescf. Init (Cipher. ENCRYPT HOOC, new SecretKeySpec (chave, "AES"), ivspec); 
 textoCifrado nescf.doFinal (texto); 
 
 public byte[] getTextoCifrado() throws Exception 
 (return textoCifrado) 
  public void geradecifra (byte[] testo, File fsin) 
 throws RoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
 IllegalBlockSizeException, BadPaddingException, 
 InvalidAlgorithaParameterException, IOException, ClassNotFoundException (ObjectInputStream sis пен ObjectInputStream (new FileInputStream (f5im)); 
 SecretkeySpec isim (SecretKeySpec) ois.readObject(); 
 sis.close(); 
 Cipher aescf Cipher.getInstance ("AES/CBC/PKCS5Padding"); 
 IvParameterSpec ivspec new IvParameterSpec (new byte[16]); 
 aescf.init (Cipher. DECRYPT MODE, ISin, lvspec); 
 textoDecifrado anscf.doFinal (texto); 
 public byte[] getTextoDecifrado() throws Exception 
 
 

 
{
  return textoDecifrado; 
}
}