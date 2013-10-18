package forest;

import java.util.Comparator;

/**
 * Nodeを、それらが持つ要素名の辞書順にソートするための比較器(コンパレータ)。 <br>
 * 良好(2013年7月22日,藤原)
 * 
 * @author スタブ作成(2013/07/17 藤原)<br>
 * @author 実装(2013/07/17 藤原)
 */
public class NodeComparator implements Comparator<Node>
{

	/**
	 * java.util.Comparator#compareの実装。<br>
	 * Nodeの要素名を辞書順で比較した結果を返す。<br>
	 * 二つのNodeの要素名が等しい場合は、そのNodeが持つ1番目の子同士<br>
	 * を比較した結果を返す。 <br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @return node1 > node2 なら 1、<br>
	 *         node1 < node2 なら -1
	 */
	public int compare(Node node1, Node node2)
	{
		String value1 = node1.getText();
		String value2 = node2.getText();

		if (value1.compareTo(value2) != 0)
			return value1.compareTo(value2);
		else
			return node1.getNextNodes().get(0).getText()
					.compareTo(node1.getNextNodes().get(0).getText());
	}
}
