public class HelloNumbers {
	public static void main(String[] args) {
		int x = 0;
		while (x < 10) {
			int sum = 0;
			for(int i = 0; i <= x; i++) {
				sum = sum + i;
			}
			System.out.print(sum + " ");
			x = x + 1;
		}
		System.out.println("");
	}
}