package constants;

import java.io.File;
import java.nio.file.Path;

/**
 * 環境設定.
 *
 * @author cyrus
 */
public interface Configurations {

	/**
	 * ChromeDriverの実行ファイルのパス.
	 */
	@Deprecated
	String CHROME_DRIVER_EXECUTABLE_PATH = "./drivers/chromedriver-windows-32bit.exe";

	/**
	 * ブラウザをヘッドレスモードで使用するか.
	 */
	boolean USE_HEADLESS_MODE = false;

	/**
	 * ブラウザのステートの出力先.
	 */
	Path STATE_PATH = new File("data/state.json").toPath();
}