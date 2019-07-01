package xgxt.comm.chart.interfaces;


/**
 * <p>
 * 	ΪͼƬˢˮӡ����ʱδ�õ�������
 * </p>
 */
public interface WaterMarkInterface {

	/**
	 * ��ͼƬӡˢ��ͼƬ��
	 * 
	 * @param pressImg --
	 *            ˮӡ�ļ�
	 * @param targetImg --
	 *            Ŀ���ļ�
	 * @param x
	 * @param y
	 */
	public abstract void pressImage(String pressImg, String targetImg, int x,
			int y);

	/**
	 * ��ӡ����ˮӡͼƬ
	 * 
	 * @param pressText
	 *            --����
	 * @param targetImg --
	 *            Ŀ��ͼƬ
	 * @param fontName --
	 *            ������
	 * @param fontStyle --
	 *            ������ʽ
	 * @param color --
	 *            ������ɫ
	 * @param fontSize --
	 *            �����С
	 * @param x --
	 *            ƫ����
	 * @param y
	 */

	public abstract void pressText(String pressText, String targetImg,
			String fontName, int fontStyle, int color, int fontSize, int x,
			int y);

}