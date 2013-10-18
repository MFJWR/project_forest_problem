package forest;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * メインプログラム。<br>
 * 木構造の集まりを表現したテキストファイル群が用意されているので、それらを読み込み、<br>
 * それぞれの木構造を樹状に美しく整列させ、複数の木構造をきれいに配置して、<br>
 * 一つのウィンドウの中に可視化するプログラムである。<br>
 * 良好(2013年7月22日,藤原)
 * 
 * @author スタブ作成(2013/07/10 藤原)<br>
 * @author 実装(2013/7/17 藤原)
 */
public class Forest
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
	 * 制御を司るForestControllerのインスタンスを束縛する。
	 */
	private ForestController aController;

	/**
	 * ウィンドウを表示するJFrameのインスタンスを束縛する。
	 */
	private JFrame aFrame;

	/**
	 * メインメソッド。 動作確認(2013年7月22日,藤原)
	 * 
	 * @param args - コマンドライン引数
	 */
	public static void main(String[] args)
	{
		new Forest();
	}

	/**
	 * Forestクラスインスタンスを作成して応答する。<br>
	 * Model・View・Controllerの各インスタンスと、新規ウィンドウをそれぞれ1つ生成、フィールドに束縛する。<br>
	 * 動作確認(2013年7月22日,藤原)
	 */
	public Forest()
	{
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		aController = new ForestController();
		aModel = new ForestModel();
		aView = new ForestView(aModel);
		aController.setModel(aModel);
		aController.setView(aView);
		aFrame = new JFrame("Forest");
		aFrame.setBounds(0, 0, 1280, 800);
		aFrame.getContentPane().setLayout(null);
		aFrame.getContentPane().add(aView);
		aFrame.setBackground(Color.WHITE);
		aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aFrame.setJMenuBar(createMenuBar());
		aFrame.setVisible(true);
	}

	/**
	 * JMenuBarインスタンスを生成して応答する。 <br>
	 * 動作確認(2013年7月22日,藤原)
	 * 
	 * @return aMenuBar - JMenuBarインスタンス
	 */
	public JMenuBar createMenuBar()
	{
		JMenuBar aMenuBar = new JMenuBar();
		JMenu file = new JMenu("ファイル");
		JMenuItem fileOpen = new JMenuItem("ファイルを開く…");

		fileOpen.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser aFileChooser = new JFileChooser(
						"Requirement/texts");
				if (aFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					aController.start(aFileChooser.getSelectedFile().getPath());
					aFrame.setTitle(aFileChooser.getSelectedFile().getName()
							+ " - Forest");
				}
			}
		});

		file.add(fileOpen);
		aMenuBar.add(file);
		return aMenuBar;
	}
}
