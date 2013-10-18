package forest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * ビューのクラス。モデルからNodeインスタンスを受け取り<br>
 * ビューに表示、座標変更を行う事でアニメーションを表現する。 <br>
 * 良好(2013年7月22日,藤原)
 * 
 * @author スタブ作成(2013/07/15 藤原)<br>
 * @author 実装(2013/7/18 藤原)
 */
public class ForestView extends JPanel implements Runnable
{
	/**
	 * 全てのNodeインスタンスを持っているForestModelのインスタンスを束縛する。
	 */
	private ForestModel aModel;

	/**
	 * Nodeインスタンスが格納されたHashMapを束縛する。
	 */
	private HashMap<Integer, Node> nodes;

	/**
	 * ビュー内で、最も下に配置されているNodeのY座標。<br>
	 * メソッド間の値の共有の為、やむなくフィールドに記述した。
	 */
	private int bottomY = 0;

	/**
	 * モデルとコントローラを設定し、ビューのサイズをウィンドウと同じにする。<br>
	 * 更に、背景色を白にし、レイアウトマネージャをnullにする。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param aModel - モデルのインスタンス
	 */
	public ForestView(ForestModel aModel)
	{
		this.aModel = aModel;
		this.setBounds(0, 0, 1280, 800);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
	}

	/**
	 * ビューを初期状態にする。<br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	public void reset()
	{
		this.removeAll();
		this.nodes = null;
		this.bottomY = 0;
		this.repaint();
	}

	/**
	 * モデルから取得したNodeインスタンスをビューに追加する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	public void setNode()
	{
		this.reset();
		this.nodes = aModel.getNodes();

		bottomY = 0;
		for (int i = 1; i <= nodes.size(); i++)
		{
			Node aNode = nodes.get(i);
			aNode.setLocation(0, bottomY);
			this.add(aNode);
			this.bottomY += aNode.getPreferredSize().height + 2;
		}
		this.setSize(1280, bottomY == 0 ? 800 : bottomY);
		this.repaint();
	}

	/**
	 * アニメーションを開始する。 swingの仕様上スレッド処理を用いて描画を行う。 <br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	public void animation()
	{
		new Thread(this).start();
	}

	/**
	 * java.lang.Runnable#runの実装。<br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	@Override
	public void run()
	{
		ArrayList<Node> roots = aModel.getRoots();
		int count = 0;
		this.bottomY = 0;
		for (Node node : roots)
		{
			this.recursion(count, null, node);
			count++;
		}
		this.setSize(1280, bottomY + nodes.get(1).getPreferredSize().height + 2);
	}

	/**
	 * アニメーションアルゴリズム。 Nodeを再帰処理で辿りながら、木構造に並ぶ様位置を変更していく。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param count - 対象のNodeが、親Nodeから見て何番目にあるかを示す数値
	 * @param parentNode - 親Node
	 * @param aNode - 座標指定の対象となるNode
	 */
	public void recursion(int count, Node parentNode, Node aNode)
	{
		ArrayList<Node> nextNodes = aNode.getNextNodes();

		if (parentNode == null)
		{
			aNode.setLocation(25, bottomY + (aNode.getHeight()));
		}
		else
			if (aNode.getX() == 0)
			{
				Point parentNodePoint = parentNode.getLocation();
				parentNodePoint.x += (parentNode.getWidth() + 25);
				parentNodePoint.y = bottomY + ((parentNode.getHeight() + 2));
				aNode.setLocation(parentNodePoint);
			}
			else
				return;

		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		this.repaint();

		if (nextNodes != null)
		{

			int i = 0;
			for (Node nextNode : nextNodes)
			{
				recursion(i, aNode, nextNode);
				i++;
			}

			Point firstNodePoint = nextNodes.get(0).getLocation();
			Point lastNodePoint = nextNodes.get(nextNodes.size() - 1)
					.getLocation();

			int middleY = (lastNodePoint.y - firstNodePoint.y) / 2;
			Point middlePoint = new Point(aNode.getX(), middleY
					+ firstNodePoint.y);

			if (firstNodePoint.y < aNode.getY())
			{
				middlePoint.y = aNode.getY();
			}

			aNode.setLocation(middlePoint);

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			this.repaint();
		}

		if (bottomY <= aNode.getY())
			bottomY = aNode.getY();
		return;
	}

	/**
	 * Javax.swing.JComponent#paintComponentのオーバーライド。<br>
	 * ノード間の線描画を行う。<br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		Point parentNodePoint = new Point();
		Point childNodePoint = new Point();
		Node aNode;
		ArrayList<Node> nextNodes;
		if (nodes != null)
			for (int i = 1; i <= nodes.size(); i++)
			{
				aNode = nodes.get(i);
				parentNodePoint = new Point(aNode.getX()
						+ aNode.getSize().width, aNode.getY()
						+ aNode.getSize().height / 2);
				nextNodes = aNode.getNextNodes();
				if (nextNodes == null)
					continue;

				for (Node childNode : nextNodes)
				{
					childNodePoint = childNode.getLocation();
					childNodePoint.y += childNode.getHeight() / 2;
					g.drawLine(parentNodePoint.x, parentNodePoint.y,
							childNodePoint.x, childNodePoint.y);
				}
			}
	}
}
