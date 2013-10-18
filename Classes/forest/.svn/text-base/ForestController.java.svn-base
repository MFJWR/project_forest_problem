package forest;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * コントローラのクラス。自身をビューのリスナーとし、ビューからマウスイベントを受けとり、<br>
 * そのイベントに応じてビュー、モデルにメッセージを送る。<br>
 * 良好(2013年7月22日,藤原)
 * 
 * @author スタブ作成(2013/07/15 藤原)<br>
 * @author 実装(2013/7/17 藤原)
 */
public class ForestController implements MouseListener, MouseMotionListener
{
	/**
	 * 全てのNodeインスタンスを持っているForestModelのインスタンスを束縛する。
	 */
	private ForestModel aModel;

	/**
	 * 表示を司るForestViewのインスタンスを束縛する。
	 */
	private ForestView aView;

	/**
	 * ドラッグ開始位置の座標を持つPointインスタンスを束縛する。
	 */
	private Point previous;

	/**
	 * 現在読み込まれているテキストファイルのパス。
	 */
	private String currentFilePath;

	/**
	 * ビュー上で右クリックした時表示されるポップアップメニューのインスタンスを束縛する。
	 */
	private JPopupMenu aPopup;

	/**
	 * インスタンスを生成して応答する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	public ForestController()
	{
		super();
		setPopup();
	}

	/**
	 * モデルのインスタンスをフィールドに束縛する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param aModel - ForestModelのインスタンス
	 */
	public void setModel(ForestModel aModel)
	{
		this.aModel = aModel;
	}

	/**
	 * ビューのインスタンスをフィールドに束縛し、<br>
	 * ビューのリスナをこのコントローラに設定する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param aView - ForestViewのインスタンス
	 */

	public void setView(ForestView aView)
	{
		this.aView = aView;
		aView.addMouseMotionListener(this);
		aView.addMouseListener(this);
	}

	/**
	 * モデルにテキストファイルを読み込ませ、Nodeインスタンスを作成、<br>
	 * それらをビューにセットする。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param filePath - テキストファイルのパス文字列
	 */
	public void start(String filePath)
	{
		if (filePath != null)
			currentFilePath = filePath;
		aModel.load(currentFilePath);
		aView.setNode();
	}

	/**
	 * 右クリックした時に表示されるJPopupインスタンスを生成してフィールドに束縛する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	private void setPopup()
	{
		aPopup = new JPopupMenu();
		JMenuItem animation = new JMenuItem("アニメーションを開始");
		animation.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				start(currentFilePath);
				aView.animation();
			}
		});
		aPopup.add(animation);
	}

	/**
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )の実装。<br>
	 * ドラッグイベントが発生した時呼ばれる。<br>
	 * ドラッグイベントが発生している間は、カーソルを十字にする。 <br>
	 * 更に、現在のビューの座標、現在のカーソルの座標、ドラッグ開始位置の座標を用いて<br>
	 * ドラッグ後のビュー座標を計算、位置を更新する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		aView.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		Point newPoint = new Point(aView.getX() - previous.x + e.getX(),
				aView.getY() - previous.y + e.getY());
		aView.setLocation(newPoint);
	}

	/**
	 * java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)の実装。<br>
	 * イベント発生時のカーソル座標をPointインスタンスとして束縛する。 <br>
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
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)の実装。<br>
	 * カーソルの形を元に戻す。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		aView.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)の実装。<br>
	 * 右クリックされた時、ポップアップメニューを表示する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (aModel.getNodes().size() > 0
				&& javax.swing.SwingUtilities.isRightMouseButton(e))
			aPopup.show(aView, e.getX(), e.getY());
	}

	/**
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 * の実装。<br>
	 * 何もしない。
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseMoved(MouseEvent e)
	{
	}

	/**
	 * java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)の実装。<br>
	 * 何もしない。
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	/**
	 * java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)の実装。<br>
	 * 何もしない。
	 * 
	 * @param e - マウスイベント
	 */
	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}
