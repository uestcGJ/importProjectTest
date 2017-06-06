package forOffer;
/***正则表达式**/
public class Rudolph {
	public static void main(String[] args){
		/***
		 *   ? 表示可能存在前一个表达式也可能不存在
		 *   \\d 匹配一位数字
		 *   +表示存在一个或多个之前的表达式，至少要存在一个
		 *   *表示存在0个或多个之前的表达式 
		 *   |表示或运算
		 *   ()表示在括号之间的表达式为一个整体
		 *   \\W表示非单词
		 *   \\w表示一个单词字符
		 *   .匹配任意字符
		 *   \\xhh表示十六进制值为hh的字符集
		 *   \\uhhhh十六进制表示为oxhhhh的Unicode字符
		 *   \\t制表符Tab
		 *   \\n换行符
		 *   \\r回车
		 *   \\f换页
		 *   \\e转义
		 *   [abc]含义同[a|b|c]
		 *   [^abc]除了a、b、c之外的任何字符，^表示取反
		 *   [a-zA-Z]从a-z或A-Z范围内的任意字符，-表示范围
		 *   &&表示交集  [a-z&&[hij]]h、i、j中的任意字符
		 *   \\s 空白字符(空格、tab、换行、回车、换页)
		 *   \\S 非空白字符[^\\s]
		 *   \\d 数字0-9，同[0-9]
		 *   \\D 非数字0-9，同[^0-9]
		 *   \\w词字符，同[a-zA-Z0-9]
		 *   \\W非词字符，同[^\\w]
		 *   
		 *   逻辑操作符
		 *   XY 表示Y跟在X后面
		 *   X|Y X或Y
		 *   ^ 一行的开始
		 *   $ 一行的结束
		 *   \\G 前一个匹配的结束
		 *   \\b 词的边界
		 *   \\B 非词的边界
		 * **/
		String regex="(-hh|-)?[\\d+][\\w+\\s,]*";
		String match="-23hello world,hello guniang,";
		System.out.println(match.matches(regex));
		String reg="[A|E|I|O|U|a|e|i|o|u]";
		String reg1="[A-Z](.)*\\!$";
		String text="Hello world, hello AeHIF.!";
		System.out.println(text.matches(reg1));
		System.out.println(text.replaceAll(reg, "_"));
	}
}
