package constants;

import enums.BrowserType;

/**
 * 環境設定.
 *
 * @author cyrus
 */
public interface Configurations {

	/**
	 * GeckoDriverの実行ファイルのパス.
	 */
	String GECKO_DRIVER_EXECUTABLE_PATH = "./drivers/geckodriver-windows-64bit.exe";

	/**
	 * ChromeDriverの実行ファイルのパス.
	 */
	String CHROME_DRIVER_EXECUTABLE_PATH = "./drivers/chromedriver-windows-32bit.exe";

	/**
	 * EdgeDriverの実行ファイルのパス.
	 */
	String EDGE_DRIVER_EXECUTABLE_PATH = "./drivers/edgedriver-windows-64bit.exee";

	/**
	 * WaterFoxの実行ファイルのパス.
	 */
	String WATER_FOX_EXECUTABLE_PATH = "C:\\Program Files\\Waterfox Classic\\waterfox.exe";

	/**
	 * 使用するブラウザの種類.
	 */
	BrowserType USE_BROWSER_TYPE = BrowserType.WATERFOX;

	/**
	 * ブラウザをヘッドレスモードで使用するか.
	 */
	boolean USE_HEADLESS_MODE = false;

	/**
	 * 使用するユーザーエージェント.
	 */
	String USE_UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36";
}