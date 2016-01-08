package it.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		List<Object[]> list = new ArrayList<>();
		list.add(new Object[] { "a", 12, 22, 43 });
		list.add(new Object[] { "b", 44, 23, 43 });
		list.add(new Object[] { "c", 6, 21, 77 });
		list.add(new Object[] { "d", 72, 62, 49 });
		list.add(new Object[] { "e", 10, 29, 11 });

		Scanner sc = new Scanner(System.in);
		int index = sc.nextInt();
		int[] value = new int[5];
		for (int i = 0; i < list.size()-1; i++) {
			for(int j=i+1;j<list.size();j++){
				int n1 = (int) list.get(i)[index];
				int n2 = (int) list.get(j)[index];
				if(n1>n2){
					Object[] temp  = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
//					System.out.println(i);
				}
			}
		}
		for(Object[] objs:list){
//			syso
			System.out.println("1:"+objs[0]+"  2:"+objs[1]+"  3:"+objs[2]+"  4:"+objs[3]);
		}
	}
}
