package xgxt.comm.chart.interfaces;


/**
 * <p>
 * 	为图片刷水印，暂时未用到！！！
 * </p>
 */
public interface WaterMarkInterface {

	/**
	 * 把图片印刷到图片上
	 * 
	 * @param pressImg --
	 *            水印文件
	 * @param targetImg --
	 *            目标文件
	 * @param x
	 * @param y
	 */
	public abstract void pressImage(String pressImg, String targetImg, int x,
			int y);

	/**
	 * 打印文字水印图片
	 * 
	 * @param pressText
	 *            --文字
	 * @param targetImg --
	 *            目标图片
	 * @param fontName --
	 *            字体名
	 * @param fontStyle --
	 *            字体样式
	 * @param color --
	 *            字体颜色
	 * @param fontSize --
	 *            字体大小
	 * @param x --
	 *            偏移量
	 * @param y
	 */

	public abstract void pressText(String pressText, String targetImg,
			String fontName, int fontStyle, int color, int fontSize, int x,
			int y);

}