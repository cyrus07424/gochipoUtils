package mains;

import org.openqa.selenium.WebDriver;

import utils.SeleniumHelper;

/**
 * ごちポン！自動クリック(2020/04).
 *
 * @author cyrus
 */
public class GochiPonAutoClicker202004 {

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

			// キャンペーン画面を表示
			webDriver.get("https://cp2.americanmeat.jp/csm/present/200401/");
			SeleniumHelper.waitForBrowserToLoadCompletely(webDriver);

			// TODO

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