package xsgzgl.comm.globals;

import xgxt.action.Base;

public class GlobalsValue {

	public static String xxpymc;// ѧУƴ������

	public static String[] xxdmValue = new String[] { "11647", "12036",
			"12741", "13060", "10491", "10338", "13107", "12870", "10653",
			"11417", "12867","10657","10279","13579","13828","12751",
			"10488","10046","10834","11600","11660","12036","11110","10052"};// ѧУ����

	public static String[] xxmcValue = new String[] { "zjcmxy", "zjjtzyjsxy",
			"gdjszyjsxy", "czzyjsxy", "zgdzdx", "zjlgdx", "xzgyzyjsxy",
			"zjjrzyxy", "cdtyxy", "bjlhdx", "zjlyzyxy","gzdx","shxjxy","zgkydxxhxy","gxgszyjsxy","scjdzyjsxy",
			"whkjdx","zgyyxy","whzyjsxy","hbjjxy","cqlgdx","zjzyjt","gdjgxy","zymzdx"};// ѧУ

	// ###########################��ע####################################
	// 10491���й����ʴ�ѧ
	// 11647���㽭��ýѧԺ
	// 12036���㽭��ְͨҵ����ѧԺ
	// 13060������ְҵ����ѧԺ
	// 13107: ���ݹ�ҵְҵ����ѧԺ
	// 10338���㽭����ѧ
	// 12870: �㽭����ְҵѧԺ
	// 10653: �ɶ�����ѧԺ
	// 12867: �㽭����ְҵѧԺ
	// 11417: �������ϴ�ѧ
	// 10657: ���ݴ�ѧ
	// 13579���й���ҵ��ѧ�캣ѧԺ
	// 13828: ��������ְҵ����ѧԺ
	// 12751: �Ĵ�����ְҵ����ѧԺ
	// 10488: �人�Ƽ���ѧ
	// 10046: �й�����ѧԺ
	// 10834: �人ְҵ����ѧԺ
	// 11600: ��������ѧԺ
	// 10052: ���������ѧ
	// ###########################end#####################################

	public static String getXxpymc(String xxdm) {
		xxpymc=null;
		for (int i = 0; i < xxdmValue.length; i++) {
			if (xxdm.equalsIgnoreCase(xxdmValue[i])) {
				xxpymc = xxmcValue[i];
				break;
			}
		}

		if (Base.isNull(xxpymc)) {
			xxpymc = "general";
		}

		return xxpymc;
	}
}
