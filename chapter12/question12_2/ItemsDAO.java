package question12_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import question12_1.Item;

public class ItemsDAO {
	public static ArrayList<Item> findByMinimumPrice(int i) {
		// (1) ドライバクラスを指定
		try {
			// h2ドライバを指定
			Class.forName("org.h2.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		// (2) Connectionオブジェクトを初期化
		Connection con = null;
		// (3) データベースに接続
		try {
			// 接続先のデータベースを指定
			con = DriverManager.getConnection("jdbc:h2:~/rpgdb");
			// PreparedStatementクラスでSQL文を用意
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ITEMS WHERE PRICE > ?");
			// SQLに挿入する値を用意
			pstmt.setInt(1, i);
			// SQLの実行結果を保存
			ResultSet rs = pstmt.executeQuery();
			// Itemを格納するArrayListを用意
			ArrayList<Item> items = new ArrayList<Item>();
			// SQLの実行結果を処理
			while(rs.next()) {
				// itemインスタンスを用意
				Item item = new Item();
				// 1行分の情報をインスタンスに変換
				item.setName(rs.getString("NAME"));
				item.setPrice(rs.getInt("PRICE"));
				item.setWeight(rs.getInt("WEIGHT"));
				// インスタンスをArrayListに格納
				items.add(item);
			}
			// SQLの実行を終了
			rs.close();
			pstmt.close();
			// SQLの結果を返す
			return items;
		// (4) 例外処理
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		// (5) 必ず行う処理
		} finally {
			// Connectionオブジェクトがある（nullではない）場合
			if(con != null) {
				try {
					// データベースを終了
					con.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
