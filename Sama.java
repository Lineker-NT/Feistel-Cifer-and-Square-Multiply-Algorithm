// Ntimansiemi Mazamba Lineker
import java.util.*;
import java.math.*;

public class Sama {

    public static BigInteger squareAndMultiply(BigInteger base, BigInteger exponent, BigInteger modulus) {
        // Convertir l'exposant en binaire
        String binaryExponent = exponent.toString(2);
        
        BigInteger result = BigInteger.ONE;
        
        for (int i = binaryExponent.length() - 1; i >= 0; i--) {
            // Carré de la valeur actuelle
            result = result.multiply(result).mod(modulus);
            
            // Si le bit dans la position i est 1, multiplier par la base
            if (binaryExponent.charAt(i) == '1') {
                result = result.multiply(base).mod(modulus);
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner (System.in);
        
        System.out.println("Entrez l'entier pour la base");
        int basee=sc.nextInt(); 
        
        System.out.println("Entrez l'entier pour l'exposant");
        int expo=sc.nextInt();
        
        System.out.println("Entrez l'entier pour le modulo");        
        int mod=sc.nextInt();
        
        BigInteger base = BigInteger.valueOf(basee);
        BigInteger exponent = BigInteger.valueOf(expo);
        BigInteger modulus = BigInteger.valueOf(mod);

        BigInteger result = squareAndMultiply(base, exponent, modulus);
        System.out.println("Resultat : " + result);
    }
}
