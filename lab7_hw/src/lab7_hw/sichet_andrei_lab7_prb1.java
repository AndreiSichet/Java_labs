package lab7_hw;
import java.util.*;
interface  RandomGenerator<T>{
	public T nextVar(T var);
}
class IntegerGenerator implements RandomGenerator<Integer>{
	private Random rnd=new Random();
	@Override
	public Integer nextVar(Integer max) {
		return rnd.nextInt(max);
	}
}
class CharGenerator implements RandomGenerator<Character>{
	private Random rnd=new Random();
	@Override
	public Character nextVar(Character v) {
		return (char)('a'+rnd.nextInt(26));
	}
}
public class sichet_andrei_lab7_prb1 {
	public static void main(String[] args) {
		IntegerGenerator a=new IntegerGenerator();
		System.out.println("Random int: "+a.nextVar(100));
		CharGenerator b=new CharGenerator();
		System.out.println("Random char: "+b.nextVar('a'));

	}

}
