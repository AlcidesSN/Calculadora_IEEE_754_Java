public class Main {
    public static void main(String[] args) {

        Conversor binary1 = new Conversor();
        Conversor binary2 = new Conversor();

        double valor1 = 0.1;
        double valor2 = 1.0;

        binary1.decimalToBinary(valor1);
        binary2.decimalToBinary(valor2);

        String res =
                "VALOR: " + valor1 +
                "\nSINAL: " + binary1.getSign() +
                "\nEXPOENTE REAL: " + binary1.getExponentReal() +
                "\nEXPOENTE + BIAS (127): " + binary1.getExponentBias() + " → " + binary1.getExponentBits() +
                "\nMANTISSA: " + binary1.getMantissa()+
                "\nDECIMAL CONVERTIDO: " + binary1.BinaryToDecimal(binary1.getMantissa(), binary1.getExponentReal());

        System.out.println(res);

        System.out.println("--------");

        String res2 =
                "VALOR: " + valor2 +
                        "\nSINAL: " + binary2.getSign() +
                        "\nEXPOENTE REAL: " + binary2.getExponentReal() +
                        "\nEXPOENTE + BIAS (127): " + binary2.getExponentBias() + " → " + binary2.getExponentBits() +
                        "\nMANTISSA: " + binary2.getMantissa()+
                        "\nDECIMAL CONVERTIDO: " + binary2.BinaryToDecimal(binary2.getMantissa(), binary2.getExponentReal());

        System.out.println(res2);

        System.out.println("--------");
        System.out.println("A soma dos valores pela IEEE é: " + (Calculator.sumBin(binary1 , binary2)));
        System.out.println("A subtração dos valores pela IEEE é: " + (Calculator.subBin(binary1 , binary2)));

        System.out.println("A soma dos valores pela v1 + v2 é: " + (valor1 + valor2));
        System.out.println("A subtração dos valores v1 - v1 é: " + (valor1 - valor2));
    }
}