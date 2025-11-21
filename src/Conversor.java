public class Conversor {
    private int sign;
    private int exponentReal;
    private int exponentBias;
    private String exponentBits;
    private String mantissa;


    private void setValues(int sign, int exponentReal, int exponentBias, String exponentBits, String mantissa) {
        this.sign = sign;
        this.exponentReal = exponentReal;
        this.exponentBias = exponentBias;
        this.exponentBits = exponentBits;
        this.mantissa = mantissa;

    }

    public String decimalToBinary(double decimal) {
        int bits = 23;
        int sign = 0;
        String binFrac = ".";
        if (decimal < 0) {
            sign = 1;
            decimal *= -1;
        }

        //separação da parte fracional da parte inteira
        int integer = (int) decimal;
        double frac = decimal - integer;

        //conversão da parte interia pra binario
        String binInt = Integer.toBinaryString(integer);

        //conversão da parte fracionaria
        if (frac == 0)
            return normalizeBinary(sign,binInt,binFrac);
        for (int i = 0; i < bits; i++) {
            frac *= 2;
            if (frac >= 1) {
                binFrac += "1";
                frac -= 1;
            } else {
                binFrac += "0";
            }

            if (frac == 0)
                break;
        }

        return normalizeBinary(sign, binInt, binFrac);
    }

    private String normalizeBinary(int sign, String binInt, String binFrac) {
        String binary = binInt + binFrac;

        int ponto = binary.indexOf(".");

        String intPart = binInt;
        String fracPart = binFrac.substring(1);
        String full = intPart + fracPart;

        //Caso 1: Parte inteira != 0
        if (!intPart.equals("0")) {
            //Pego o tamanho da parte interia e reduzo em 1 assim pegado o expoente
            int expoenteReal = intPart.length() - 1;

            //Pego atodo o binario sem (sem o ponto) e retio o primiro "1" da primeira casa para normalizar
            String mantissa = full.substring(1);

            // Preencho o restantes das casas com 0 até os 23 bits
            mantissa = (mantissa + "00000000000000000000000").substring(0, 23);

            //pego o esponte bias para a normalizaçã
            int expoenteBias = expoenteReal + 127;
            String binExpoenteBias = Integer.toBinaryString(expoenteBias);
            setValues(sign,expoenteReal,expoenteBias,binExpoenteBias,mantissa);
            return
                    "SINAL: " + sign +
                    "\nEXPOENTE REAL: " + expoenteReal +
                    "\nEXPOENTE + BIAS (127): " + expoenteBias + " → " + binExpoenteBias +
                    "\nMANTISSA: " + mantissa;
        }
        //Caso 2 parte interia = 0

        int firstOne = fracPart.indexOf("1");

        int expoenteReal = -(firstOne + 1);

        String mantissa = fracPart.substring(firstOne + 1);

        mantissa = (mantissa + "00000000000000000000000").substring(0, 23);

        //pego o esponte bias para a normalizaçã
        int expoenteBias = expoenteReal + 127;
        String binExpoenteBias = Integer.toBinaryString(expoenteBias);
        setValues(sign,expoenteReal,expoenteBias,binExpoenteBias,mantissa);
        return
                "SINAL: " + sign +
                "\nEXPOENTE REAL: " + expoenteReal +
                "\nEXPOENTE + BIAS (127): " + expoenteBias + " → " + binExpoenteBias +
                "\nMANTISSA: " + mantissa;

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


    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int getExponentReal() {
        return exponentReal;
    }

    public void setExponentReal(int exponentReal) {
        this.exponentReal = exponentReal;
    }

    public int getExponentBias() {
        return exponentBias;
    }

    public void setExponentBias(int exponentBias) {
        this.exponentBias = exponentBias;
    }

    public String getExponentBits() {
        return exponentBits;
    }

    public void setExponentBits(String exponentBits) {
        this.exponentBits = exponentBits;
    }

    public String getMantissa() {
        return mantissa;
    }

    public void setMantissa(String mantissa) {
        this.mantissa = mantissa;
    }

}
