package mains;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.NavigateOptions;
import com.microsoft.playwright.Page.WaitForURLOptions;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import utils.PlaywrightHelper;

/**
 * Journey to American Beef キャンペーン 自動抽選(2023/08).
 *
 * @author cyrus
 */
public class GochiPonAutoLottery202308 {

	/**
	 * 抽選画面のURL.
	 */
	private static final String LOTTERY_URL = "https://journey-to-american-beef-2023-summer-cp.com/csm/present/230703/form/movie.html";

	/**
	 * メイン処理.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("■start.");

		// Playwrightを作成
		try (Playwright playwright = Playwright.create()) {
			// ブラウザ起動オプションを取得
			BrowserType.LaunchOptions launchOptions = PlaywrightHelper.getLaunchOptions();

			// ブラウザを起動
			try (Browser browser = playwright.firefox().launch(launchOptions)) {
				System.out.println("■launched");

				// ブラウザコンテキストオプションを取得
				Browser.NewContextOptions newContextOptions = PlaywrightHelper.getNewContextOptions(true);

				// ブラウザコンテキストを取得
				try (BrowserContext context = browser.newContext(newContextOptions)) {
					// ページを取得
					try (Page page = context.newPage()) {
						while (true) {
							try {
								// 抽選画面を表示
								log("抽選画面を表示");
								NavigateOptions navigateOptions = new NavigateOptions().setReferer(
										"https://journey-to-american-beef-2023-summer-cp.com/csm/present/230703/");
								page.navigate(LOTTERY_URL, navigateOptions);

								// 読み込み完了まで待機
								log("読み込み完了まで待機1");
								page.waitForLoadState(LoadState.NETWORKIDLE);

								// 抽選完了まで待機
								log("抽選完了まで待機");
								WaitForURLOptions waitForURLOptions = new WaitForURLOptions()
										.setTimeout(120000);
								page.waitForURL((String url) -> {
									System.out.println(url);
									// 抽選画面から遷移するまで待機
									return !StringUtils.equals(url, LOTTERY_URL);
								}, waitForURLOptions);

								// 読み込み完了まで待機
								log("読み込み完了まで待機2");
								page.waitForLoadState(LoadState.NETWORKIDLE);

								try {
									System.out.println(page.locator("p.result-emphasis").textContent());

									// ハズレ以外の場合
									if (!StringUtils.contains(page.locator("p.result-emphasis").textContent(), "ハズレ")) {
										// 現在のURLを出力
										System.out.println(page.url());

										// FIXME Scannerで待機
										Scanner scanner = new Scanner(System.in);
										String text = scanner.next();
										System.out.println(text);
									}
								} catch (Exception e) {
									// 現在のURLを出力
									System.out.println(page.url());

									// FIXME Scannerで待機
									Scanner scanner = new Scanner(System.in);
									String text = scanner.next();
									System.out.println(text);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} finally {
						// コンテキストのステートを出力
						PlaywrightHelper.storageState(context);
					}
				}
			}
		} finally {
			System.out.println("■done.");
		}
	}

	/**
	 * タイムスタンプとログを出力.
	 * 
	 * @param message
	 */
	private static void log(String message) {
		System.out.println(
				String.format("%s: %s", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date()), message));
	}
}