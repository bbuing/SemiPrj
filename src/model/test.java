package model;

import java.util.StringTokenizer;

public class test {

	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer("안녕 ㅎㅎ 바보  정말   재미없다  아비치  다시보고싶다 흡흑", " ");
		String[] keyWords = new String[st.countTokens()];

		int i = 0;
		while (st.hasMoreElements())

		{

			keyWords[i] = st.nextToken().trim();
			i++;
		}
		
		for( i =0;i<keyWords.length ;i++){
			System.out.print(keyWords[i]);
		}
	}

}
