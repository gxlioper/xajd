package xgxt.utils.date;

import xgxt.utils.String.StringUtils;

/**
 * ���ڽ��������תΪ��д���Ĺ�����
 *     ע�⣺ֻ�ܶԶ�λ��С�������ת����������λ��ֻתǰ���λ��
 */
public class MoneyFormat {
	//���ִ�д
	private static final String[] pattern = { "��", "Ҽ", "��", "��", "��", "��", "½", "��",
			"��", "��" };

	//λ�����ִ�д
	private static final String[] cPattern = { "", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ",
			"��" };

	//С��λ���ִ�д
	private static final String[] cfPattern = { "", "��", "��" };

	//0���ִ�д
	private static final String ZEOR = "��";

	/**
	 * ���������֣�����Ϊ���ִ�д
	 *    ���������Ƿ���������׳�ArrayIndexOutOfBoundsException
	 * @param moneyString
	 * @return
	 */
	public static String format(String moneyString) {
		if (StringUtils.isNull(moneyString)) {//�ǿ��ж�
			return "";
		}
		int dotPoint = moneyString.indexOf("."); //�ж��Ƿ�ΪС��       
		String moneyStr;
		if (dotPoint != -1) {
			moneyStr = moneyString.substring(0, moneyString.indexOf("."));
		} else {
			moneyStr = moneyString;
		}
		StringBuffer fraction = null; //С�����ֵĴ���,�Լ�����yuan.       
		StringBuffer ms = new StringBuffer();
		for (int i = 0; i < moneyStr.length(); i++) {
			ms.append(pattern[moneyStr.charAt(i) - 48]); //������ı�ż����Ӧ��д����       
		}

		int cpCursor = 1;
		for (int j = moneyStr.length() - 1; j > 0; j--) {
			ms.insert(j, cPattern[cpCursor]); //��j֮����ַ�,��Ӱ��j��ԭ�ַ��������λ��       
			//ֻ��moneyStr.length()��������       
			//insert(j,"string")����jλ�ô�����,j=0ʱΪ��һλ       
			cpCursor = cpCursor == 8 ? 1 : cpCursor + 1; //��λ֮������ѭ��       
		}

		while (ms.indexOf("��ʰ") != -1) { //��ʮλΪ��ʱ��һ��"��"����"��ʰ"       
			//replace����ʼ����ֹλ��       
			ms.replace(ms.indexOf("��ʰ"), ms.indexOf("��ʰ") + 2, ZEOR);
		}
		while (ms.indexOf("���") != -1) { //����λΪ��ʱ,ͬ��       
			ms.replace(ms.indexOf("���"), ms.indexOf("���") + 2, ZEOR);
		}
		while (ms.indexOf("��Ǫ") != -1) { //ͬ��       
			ms.replace(ms.indexOf("��Ǫ"), ms.indexOf("��Ǫ") + 2, ZEOR);
		}
		while (ms.indexOf("����") != -1) { //���豣��������ϰ��       
			ms.replace(ms.indexOf("����"), ms.indexOf("����") + 2, "��");
		}
		while (ms.indexOf("����") != -1) { //ͬ��       
			ms.replace(ms.indexOf("����"), ms.indexOf("����") + 2, "��");
		}
		while (ms.indexOf("����") != -1) {//��������λ�����㣬���������������ʱ����ϰ�߱���һ���㼴��       
			ms.replace(ms.indexOf("����"), ms.indexOf("����") + 2, ZEOR);
		}
		while (ms.indexOf("����") != -1) { //�����������:100000000,����ϰ�߱�����λ       
			ms.replace(ms.indexOf("����"), ms.indexOf("����") + 2, "��");
		}
		while (ms.lastIndexOf("��") == ms.length() - 1) { //����βΪ��j��������ʾ,��������Ҳֻ���ܳ���һ����       
			ms.delete(ms.lastIndexOf("��"), ms.lastIndexOf("��") + 1);
		}

		int end;
		if ((dotPoint = moneyString.indexOf(".")) != -1) { //��С���Ľ���        
			String fs = moneyString.substring(dotPoint + 1, moneyString
					.length());
			if (fs.indexOf("00") == -1 || fs.indexOf("00") >= 2) {//��ǰ��λС��ȫΪ�㣬����������       
				end = fs.length() > 2 ? 2 : fs.length(); //��������λС��       
				fraction = new StringBuffer(fs.substring(0, end));
				for (int j = 0; j < fraction.length(); j++) {
					fraction.replace(j, j + 1,
							pattern[fraction.charAt(j) - 48]); //�滻��д����       
				}
				for (int i = fraction.length(); i > 0; i--) { //�������ı�ʶ       
					fraction.insert(i, cfPattern[i]);
				}
				fraction.insert(0, "Ԫ"); //Ϊ����������ӱ�ʶ       
			} else {
				fraction = new StringBuffer("Ԫ��");
			}

		} else {
			fraction = new StringBuffer("Ԫ��");
		}

		ms.append(fraction); //����С������       
		return ms.toString();
	}

	public static void main(String[] ar) {
		System.out.println(MoneyFormat.format("812123.23"));
	}
}
