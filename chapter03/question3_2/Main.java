package question3_2;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		// (1) 勇者2名（斎藤、鈴木）をHeroクラスのインスタンスとして用意
		Hero h1 = new Hero("斎藤");
		Hero h2 = new Hero("鈴木");
		// (2) Listコレクションを用意して、勇者の名前を格納
		List<Hero> heroes = new ArrayList<Hero>();
		heroes.add(h1);
		heroes.add(h2);
		// (3) for文で1つずつ順番に取り出して、勇者の名前を表示
		for(Hero h : heroes) {
			System.out.println(h.getName());
		}
	}
}
