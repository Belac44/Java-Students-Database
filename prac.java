public class prac {

    public static void main(String[] args) {

        int[] numbers = new int[5];
        int largest = 0;

        for (int i : numbers){
            if (i > largest){
                largest = i;
            }

        }
        System.out.println(largest);
    }

}
