import java.util.Scanner;

public class Feistel {
    
    public static final int NUM_ROUNDS = 4;
    
    public static void main(String[] args) {
        // Demander à l'utilisateur d'entrer la valeur de la permutation en 8 bits
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez la valeur de la permutation (8 bits): ");
        int permutation = scanner.nextInt();
        
        // Demander à l'utilisateur d'entrer la valeur du décalage pour générer la clé
        System.out.print("Entrez la valeur du decalage pour generer la cle: ");
        int shiftValue = scanner.nextInt();
        
        // Chiffrer le message
        int message = getUserInput(scanner, "Entrez le message a chiffrer (valeur decimale): ");
        int encryptedMessage = encrypt(message, permutation, shiftValue);
        System.out.println("Message chiffre: " + encryptedMessage);
        
        // Déchiffrer le message
        int decryptedMessage = decrypt(encryptedMessage, permutation, shiftValue);
        System.out.println("Message déchiffre: " + decryptedMessage);
        
        scanner.close();
    }
    
    public static int getUserInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextInt();
    }
    
    public static int encrypt(int message, int permutation, int shiftValue) {
        // Diviser le message en deux parties de 16 bits
        int left = message >> 16;
        int right = message & 0xFFFF;
        
        // Effectuer les rounds du réseau de Feistel
        for (int i = 0; i < NUM_ROUNDS; i++) {
            int roundKey = generateRoundKey(i, shiftValue);
            int temp = right;
            right = f(right, roundKey) ^ left;
            left = temp;
        }
        
        // Concaténer les parties pour obtenir le message chiffré
        return (left << 16) | right;
    }
    
    public static int generateRoundKey(int round, int shiftValue) {
        return shiftValue << round;
    }
    
    public static int f(int value, int roundKey) {
        return value ^ roundKey;
    }
    
    public static int decrypt(int message, int permutation, int shiftValue) {
        // Diviser le message chiffré en deux parties de 16 bits
        int left = message >> 16;
        int right = message & 0xFFFF;
        
        // Effectuer les rounds du réseau de Feistel dans l'ordre inverse
        for (int i = NUM_ROUNDS - 1; i >= 0; i--) {
            int roundKey = generateRoundKey(i, shiftValue);
            int temp = left;
            left = f(left, roundKey) ^ right;
            right = temp;
        }
        
        // Concaténer les parties pour obtenir le message déchiffré
        return (left << 16) | right;
    }
}

