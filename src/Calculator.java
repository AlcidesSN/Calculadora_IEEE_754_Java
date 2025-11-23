public abstract class Calculator {

    public static double subBin(Conversor a, Conversor b) {

        int sinalA = a.getSign();
        int sinalB = b.getSign() == 0 ? 1 : 0;

        int expoenteA = a.getExponentReal();
        int expoenteB = b.getExponentReal();

        String mantissaA = a.getMantissa();
        String mantissaB = b.getMantissa();

        String fullA = "1" + mantissaA;
        String fullB = "1" + mantissaB;

        int exponentFinal = 0;

        // Alinhamento de expoentes
        if (expoenteA > expoenteB) {
            exponentFinal = expoenteA - expoenteB;
            fullB = shiftRight(fullB, exponentFinal);
        } else if (expoenteB > expoenteA) {
            exponentFinal = expoenteB - expoenteA;
            fullA = shiftRight(fullA, exponentFinal);
        }

        // Ajuste de tamanho
        while (fullA.length() < fullB.length()) fullA += "0";
        while (fullB.length() < fullA.length()) fullB += "0";

        String res;

        // Se os sinais são iguais: SOMA
        if (sinalA == sinalB) {
            res = somarBinarios(fullA, fullB);
        }
        // Se são diferentes: SUBTRAÇÃO REAL ENTRE MANTISSAS
        else {
            // Descobre qual é maior para não dar resultado negativo
            if (fullA.compareTo(fullB) >= 0) {
                res = subtrairBinarios(fullA, fullB);
            } else {
                res = subtrairBinarios(fullB, fullA);
                sinalA = sinalA == 0 ? 1 : 0; // Inverte sinal do resultado
            }
        }

        // Normalização
        int mantSize = fullA.length();
        int expoenteAjuste = 0;

        while (res.length() < mantSize) res += "0";

        if (res.length() > mantSize) {
            res = res.substring(0, res.length() - 1);
            expoenteAjuste = 1;
        }

        int maxShifts = mantSize;
        int shifts = 0;
        boolean allZeros = res.matches("^0+$");

        while (!allZeros && shifts < maxShifts && res.charAt(0) == '0') {
            res = res.substring(1) + "0";
            expoenteAjuste--;
            shifts++;
            allZeros = res.matches("^0+$");
        }

        int baseExpoente = Math.max(expoenteA, expoenteB);
        int expoenteFinal = baseExpoente + expoenteAjuste;

        String mantissaFinal = res.substring(1, 24);

        if(sinalA == 0){
            return BinaryToDecimal(mantissaFinal, expoenteFinal);
        }
        else return -(BinaryToDecimal(mantissaFinal, expoenteFinal));

    }
    public static double sumBin(Conversor a, Conversor b) {
        int espoenteA = a.getExponentReal();
        int espoenteB = b.getExponentReal();

        String mantissaA = a.getMantissa();
        String mantissaB = b.getMantissa();
        int exponentFinal = 0;

        int sinalA = a.getSign();
        int sinalB = b.getSign();

        String res = "";

        String fullA = "1" + mantissaA;
        String fullB = "1" + mantissaB;

        if (espoenteA > espoenteB) {
            exponentFinal = espoenteA - espoenteB;
            fullB = shiftRight(fullB, exponentFinal);
        } else if (espoenteB > espoenteA) {
            exponentFinal = espoenteB - espoenteA;
            fullA = shiftRight(fullA, exponentFinal);
        }

        int exp = exponentFinal;
        while (fullA.length() < fullB.length()) {
            fullA = fullA + "0";
        }
        while (fullB.length() < fullA.length()) {
            fullB = fullB + "0";
        }


        res = somarBinarios(fullA, fullB);


        int mantSize = fullB.length();
        int expoenteAjuste = 0;

        while (res.length() < mantSize) {
            res = res + "0";
        }

        if (res.length() > mantSize) {
            res = res.substring(0, res.length() - 1);
            expoenteAjuste = 1;
        }

        int shifts = 0;
        boolean allZeros = res.matches("^0+$");
        while (!allZeros && shifts < mantSize && res.charAt(0) == '0') {
            res = res.substring(1) + "0";
            expoenteAjuste--;
            shifts++;
            allZeros = res.matches("^0+$");
        }
        int baseExpoente = Math.max(espoenteA, espoenteB);
        int expoenteFinal = baseExpoente + expoenteAjuste;

        exp = expoenteFinal;

        String mantissaFinal = res.substring(1, 24);

        System.out.println("mantissaFinal = " + mantissaFinal);
        return BinaryToDecimal(mantissaFinal, exp);
    }

    public static double BinaryToDecimal(String mantissa, int exponentReal) {
        double value = 1.0;
        for (int i = 0; i < mantissa.length(); i++) {
            if (mantissa.charAt(i) == '1') {
                value += Math.pow(2, -(i + 1));
            }
        }

        return value * Math.pow(2, exponentReal);
    }

    public static String shiftRight(String bin, int n) {
        for (int i = 0; i < n; i++) {
            bin = "0" + bin;
        }
        return bin;
    }


    private static String somarBinarios(String a, String b) {
        StringBuilder resultado = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int bitA = (i >= 0 ? a.charAt(i) - '0' : 0);
            int bitB = (j >= 0 ? b.charAt(j) - '0' : 0);

            int soma = bitA + bitB + carry;

            resultado.append(soma % 2); // bit atual
            carry = soma / 2;           // vai 1?

            i--;
            j--;
        }

        return resultado.reverse().toString();
    }

    public static String subtrairBinarios(String a, String b) {
        StringBuilder result = new StringBuilder();

        int carry = 0;

        for (int i = a.length() - 1; i >= 0; i--) {
            int bitA = a.charAt(i) - '0';
            int bitB = b.charAt(i) - '0';

            int diff = bitA - bitB - carry;

            if (diff == -1) {
                diff = 1;
                carry = 1;
            } else if (diff == -2) {
                diff = 0;
                carry = 1;
            } else {
                carry = 0;
            }

            result.insert(0, diff);
        }

        return result.toString();
    }


}
