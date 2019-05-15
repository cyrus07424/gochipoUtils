package constants;

import enums.BrowserType;

/**
 * 環境設定.
 *
 * @author cyrus
 */
public interface Configurations {

	/**
	 * PhantomJSの実行ファイルのパス.
	 */
	String PHANTOMJS_EXECUTABLE_PATH = "";

	/**
	 * GeckoDriverの実行ファイルのパス.
	 */
	String GECKO_DRIVER_EXECUTABLE_PATH = "./src/main/resources/geckodriver_0.17.0.exe";

	/**
	 * ChromeDriverの実行ファイルのパス.
	 */
	String CHROME_DRIVER_EXECUTABLE_PATH = "./src/main/resources/chromedriver.exe";

	/**
	 * EdgeDriverの実行ファイルのパス.
	 */
	String EDGE_DRIVER_EXECUTABLE_PATH = "./src/main/resources/MicrosoftWebDriver.exe";

	/**
	 * WaterFoxの実行ファイルのパス.
	 */
	String WATER_FOX_EXECUTABLE_PATH = "C:\\Program Files\\Waterfox\\waterfox.exe";

	/**
	 * 使用するブラウザの種類.
	 */
	BrowserType USE_BROWSER_TYPE = BrowserType.FIREFOX;

	/**
	 * ブラウザをヘッドレスモードで使用するか.
	 */
	boolean USE_HEADLESS_MODE = false;
}