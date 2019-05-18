package mains;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.SeleniumHelper;

/**
 * ごちポン！自動クリック.
 *
 * @author cyrus
 */
public class GochiPonAutoClicker {

	/**
	 * メイン処理.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// WebDriver
		WebDriver webDriver = null;
		try {
			// WebDriverを取得
			webDriver = SeleniumHelper.getWebDriver();

			while (true) {
				// キャンペーン画面を表示
				webDriver.get("https://cp2.americanmeat.jp/csm/present/190401/");

				// 鼻をクリック
				webDriver.findElement(By.cssSelector("form[name='gacha'] a.link_gacha")).click();

				// 結果を取得
				WebElement result = webDriver.findElement(By.cssSelector(".img-responsive.ie"));

				// 結果を判定
				String alt = result.getAttribute("alt");
				System.out.println(alt);
				if (StringUtils.equals(alt, "ハズレ")) {
					// ハズレ

					// 全てのクッキーを削除
					webDriver.manage().deleteAllCookies();
				} else {
					// アタリ

					// 全てのクッキーを出力
					for (Cookie cookie : webDriver.manage().getCookies()) {
						System.out.println(cookie);
					}

					// ループ終了
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// WebDriverを終了
			if (webDriver != null) {
				webDriver.quit();
			}
		}
	}
}