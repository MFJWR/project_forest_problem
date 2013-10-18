package forest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 * 木構造の各要素を形づくるクラス。 <br>
 * 良好(2013年7月22日,藤原)
 * 
 * @author スタブ作成(2013/07/15 藤原)<br>
 * @author 実装(2013/07/18 藤原)
 */
public class Node extends JLabel implements MouseListener, MouseMotionListener
{
	/**
	 * このNodeの子Nodeを格納するリスト。
	 */
	private ArrayList<Node> nextNodes = new ArrayList<Node>();

	private Point previous;

	/**
	 * 文字列を引数として受け取り、それを要素名として持つNodeインスタンスを作成して応答する。 <br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param value - 要素名
	 */
	public Node(String value)
	{
		super(value);
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setFont(new Font(Font.SERIF, Font.PLAIN, 12));

		this.setSize(this.getPreferredSize());
		this.setOpaque(true);

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	/**
	 * 子Nodeを格納しているArrayListを応答する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @return nextNodes : 子Nodeを格納しているArrayList。<br>
	 *         長さが0の場合、null
	 */
	public ArrayList<Node> getNextNodes()
	{
		if (nextNodes.size() != 0)
		{
			Collections.sort(nextNodes, new NodeComparator());
			return nextNodes;
		}
		else
			return null;
	}

	/**
	 * リストへ子Nodeを追加する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param aNode : 子Node
	 */
	public void setNextNode(Node aNode)
	{
		this.nextNodes.add(aNode);
	}

	/**
	 * javax.swing.JComponent#paintComponentのオーバーライド。<br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

	/**
	 * java.awt.event.MouseListener#mouseClickedの実装。<br>
	 * 自身が持っている要素名を標準出力へ出力する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		System.out.println(this.getText());
	}

	/**
	 * java.awt.event.MouseListener#mouseEnteredの実装。<br>
	 * カーソルがノードに重なった時、背景色をグレーにする。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{
		this.setBackground(Color.LIGHT_GRAY);
		this.repaint();
	}

	/**
	 * java.awt.event.MouseListener#mouseExitedの実装。<br>
	 * カーソルがノードから出た時、背景色を白にする。 <br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseExited(MouseEvent e)
	{
		this.setBackground(Color.WHITE);
		this.repaint();
	}

	/**
	 * java.awt.event.MouseListener#mousePressedの実装。<br>
	 * カーソルが押された位置のPointインスタンスをpreviousに束縛する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		previous = e.getPoint();
	}

	/**
	 * java.awt.event.MouseMotionListener#mouseDraggedの実装。<br>
	 * 現在のカーソルの座標、ドラッグ開始位置の座標、及びこのノードの現在の座標を用いて<br>
	 * 新たな位置を計算し、それを適用する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		this.setLocation(this.getX() - previous.x + e.getX(), this.getY()
				- previous.y + e.getY());
		this.getParent().repaint();
	}

	/**
	 * java.awt.event.MouseMotionListener#mouseMovedの実装。<br>
	 * 何もしない。 <br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseMoved(MouseEvent e)
	{
	}

	/**
	 * java.awt.event.MouseListener#mouseReleasedの実装。<br>
	 * 何もしない。 <br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
	}
}