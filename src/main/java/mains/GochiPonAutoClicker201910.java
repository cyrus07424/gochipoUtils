package mains;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.SeleniumHelper;

/**
 * ごちポン！自動クリック.
 *
 * @author cyrus
 */
public class GochiPonAutoClicker201910 {

	/**
	 * 最大ループ回数.
	 */
	private static final int MAX_LOOP_COUNT = 10;

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

			// 使用するクイズの回答のインデックス
			int correctAnswerIndex = 0;

			while (true) {
				// キャンペーン画面を表示
				webDriver.get("https://cp2.americanmeat.jp/csm/present/191001/");
				waitForBrowserToLoadCompletely(webDriver);

				// スタートボタンをクリック
				webDriver.findElement(By.cssSelector("a.quiz_start")).click();

				// クイズの回答をクリック
				new WebDriverWait(webDriver, 10)
						.until(ExpectedConditions.urlToBe("https://cp2.americanmeat.jp/csm/present/191001/entry"));
				waitForBrowserToLoadCompletely(webDriver);
				List<WebElement> answerButtonList = webDriver.findElements(By.cssSelector("a.answer_btn"));
				answerButtonList.get(correctAnswerIndex).click();

				// ルーレットに挑戦ボタンを取得
				new WebDriverWait(webDriver, 10)
						.until(ExpectedConditions.urlToBe("https://cp2.americanmeat.jp/csm/present/191001/results"));
				waitForBrowserToLoadCompletely(webDriver);
				WebElement resultButton = webDriver.findElement(By.cssSelector("a.btn.result_btn"));

				// クイズが間違いの場合
				if (StringUtils.equals(resultButton.getAttribute("href"),
						"https://cp2.americanmeat.jp/csm/present/191001/")) {
					// 全てのクッキーを削除
					webDriver.manage().deleteAllCookies();
					correctAnswerIndex++;
					continue;
				}else {
					// ルーレットに挑戦ボタンをクリック
					resultButton.click();
				}

				// スタートボタンをクリック
				new WebDriverWait(webDriver, 10)
						.until(ExpectedConditions.urlToBe("https://cp2.americanmeat.jp/csm/present/191001/roulette"));
				waitForBrowserToLoadCompletely(webDriver);
				webDriver.findElement(By.cssSelector("a.roulette_btn.btn")).click();

				// 結果を取得
				int loopCount = 0;
				WebElement h3 = null;
				while (loopCount < MAX_LOOP_COUNT) {
					loopCount++;
					h3 = webDriver.findElement(By.cssSelector("#wrapper div.inner.quiz_box > h3"));
					String opacity = h3.getCssValue("opacity");
					if (StringUtils.equals(opacity, "1")) {
						break;
					} else {
						Thread.sleep(1000);
					}
				}
				WebElement result = h3.findElement(By.cssSelector("img"));

				// 結果を判定
				String src = result.getAttribute("src");
				System.out.println(src);
				if (StringUtils.equals(src,
						"https://cp2.americanmeat.jp/csm/present/191001/asset/image/quiz/result_ttl_02.png")) {
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

	/**
	 * ページが完全に読み込まれるまで待機.<br>
	 * https://code-examples.net/ja/q/598b97
	 *
	 * @param driver
	 */
	public static void waitForBrowserToLoadCompletely(WebDriver driver) {
		String state = null;
		String oldstate = null;
		try {
			System.out.print("Waiting for browser loading to complete");

			int i = 0;
			while (i < 5) {
				Thread.sleep(1000);
				state = ((JavascriptExecutor) driver).executeScript("return document.readyState;").toString();
				System.out.print("." + Character.toUpperCase(state.charAt(0)) + ".");
				if (state.equals("interactive") || state.equals("loading"))
					break;
				/*
				 * If browser in 'complete' state since last X seconds. Return.
				 */

				if (i == 1 && state.equals("complete")) {
					System.out.println();
					return;
				}
				i++;
			}
			i = 0;
			oldstate = null;
			Thread.sleep(2000);

			/*
			 * Now wait for state to become complete
			 */
			while (true) {
				state = ((JavascriptExecutor) driver).executeScript("return document.readyState;").toString();
				System.out.print("." + state.charAt(0) + ".");
				if (state.equals("complete"))
					break;

				if (state.equals(oldstate))
					i++;
				else
					i = 0;
				/*
				 * If browser state is same (loading/interactive) since last 60
				 * secs. Refresh the page.
				 */
				if (i == 15 && state.equals("loading")) {
					System.out.println("\nBrowser in " + state + " state since last 60 secs. So refreshing browser.");
					driver.navigate().refresh();
					System.out.print("Waiting for browser loading to complete");
					i = 0;
				} else if (i == 6 && state.equals("interactive")) {
					System.out.println(
							"\nBrowser in " + state + " state since last 30 secs. So starting with execution.");
					return;
				}

				Thread.sleep(4000);
				oldstate = state;

			}
			System.out.println();

		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}