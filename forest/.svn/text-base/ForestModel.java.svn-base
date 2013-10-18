package forest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JOptionPane;

/**
 * モデルのクラス。木構造の情報を持つ。テキストファイルを読み込み、Nodeクラスインスタンスを作成、<br>
 * それらをHashMapに保持する。また、木構造の開始点となるNodeインスタンスの情報も持つ。 <br>
 * 良好(2013年7月22日,藤原)
 * 
 * @author スタブ作成(2013/07/15 藤原)<br>
 * @author 実装(2013/7/18 藤原)
 */
public class ForestModel
{
	/**
	 * テキストファイルに書き込まれている番号をキーとして、<br>
	 * Nodeインスタンスを格納するHashMapを束縛する。
	 */
	private HashMap<Integer, Node> nodes;

	/**
	 * 最も上位のNode(root)インスタンスを格納する。
	 */
	private ArrayList<Node> roots;

	/**
	 * nodesとrootsを初期化、インスタンスを作成して応答する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	public ForestModel()
	{
		nodes = new HashMap<Integer, Node>();
		roots = new ArrayList<Node>();
	}

	/**
	 * テキストファイルから読み取ったデータを全て消去する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	private void reset()
	{
		nodes.clear();
		roots.clear();
	}

	/**
	 * Nodeインスタンスが格納されたHashMapを応答する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @return nodes - Nodeインスタンスが格納されたHashMap
	 */
	public HashMap<Integer, Node> getNodes()
	{
		return nodes;
	}

	/**
	 * 最も上位のNode(root)インスタンスが格納されたArrayListを応答する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @return roots - 最も上位のNode(root)インスタンスが格納されたArrayList
	 */
	public ArrayList<Node> getRoots()
	{
		Collections.sort(roots, new NodeComparator());
		return roots;
	}

	/**
	 * テキストファイルを読み込み、Nodeインスタンスを作成、格納する。 <br>
	 * またその際、最も上位のNode(root)インスタンスを別で格納しておく。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param fileName - テキストファイルのパス文字列
	 */
	protected void load(String fileName)
	{
		ArrayList<String> rootValues = new ArrayList<String>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String line = null;
		this.reset();

		try
		{
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			line = br.readLine();

			while (!line.equals("nodes:"))
			{
				String[] splitLine;
				line = br.readLine();
				splitLine = line.split(" ");

				if (!splitLine[0].equals("|--"))
					rootValues.add(splitLine[0]);
			}

			line = br.readLine();

			while (!line.equals("branches:"))
			{
				String[] keyAndValue = line.split(",");
				int key = Integer.parseInt(keyAndValue[0]);
				String value = keyAndValue[1].replaceAll(" ", "");
				nodes.put(key, new Node(value));
				if (rootValues.contains(value))
					roots.add(nodes.get(key));
				line = br.readLine();
			}
			line = br.readLine();

			while (line != null)
			{
				String[] numbers = line.split(",");
				nodes.get(Integer.parseInt(numbers[0])).setNextNode(
						nodes.get(Integer.parseInt(numbers[1].replaceAll(" ",
								""))));
				line = br.readLine();
			}

		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "ファイルの読み込みに失敗しました。");
		}
		finally
		{
			try
			{
				fis.close();
				isr.close();
				br.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
}
