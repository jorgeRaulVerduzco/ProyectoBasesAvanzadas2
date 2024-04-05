/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosAleatorios;

import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


/**
 *
 * @author INEGI
 */
public class CryptoUtils {
   private static final String SECRET_KEY = "secret_key"; // Clave secreta para la encriptación
    private static final int TELEFONO_LENGTH = 10; // Longitud máxima del teléfono

    // Método para encriptar el teléfono
    public static String encrypt(String strToEncrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            // Solo toma los primeros 10 caracteres antes de encriptar
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.substring(0, TELEFONO_LENGTH).getBytes()));
        } catch (Exception e) {
            System.out.println("Error al encriptar: " + e.toString());
        }
        return null;
    }

    // Método para desencriptar el teléfono
    public static String decrypt(String strToDecrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error al desencriptar: " + e.toString());
        }
        return null;
    }

    // Método para obtener la clave secreta
    private static SecretKeySpec getSecretKey() {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SECRET_KEY.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            return new SecretKeySpec(tmp.getEncoded(), "AES");
        } catch (Exception e) {
            System.out.println("Error al obtener la clave secreta: " + e.toString());
        }
        return null;
    }
    
}
