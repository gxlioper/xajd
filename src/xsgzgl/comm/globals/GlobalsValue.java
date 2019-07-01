package xsgzgl.comm.globals;

import xgxt.action.Base;

public class GlobalsValue {

	public static String xxpymc;// 学校拼音名称

	public static String[] xxdmValue = new String[] { "11647", "12036",
			"12741", "13060", "10491", "10338", "13107", "12870", "10653",
			"11417", "12867","10657","10279","13579","13828","12751",
			"10488","10046","10834","11600","11660","12036","11110","10052"};// 学校代码

	public static String[] xxmcValue = new String[] { "zjcmxy", "zjjtzyjsxy",
			"gdjszyjsxy", "czzyjsxy", "zgdzdx", "zjlgdx", "xzgyzyjsxy",
			"zjjrzyxy", "cdtyxy", "bjlhdx", "zjlyzyxy","gzdx","shxjxy","zgkydxxhxy","gxgszyjsxy","scjdzyjsxy",
			"whkjdx","zgyyxy","whzyjsxy","hbjjxy","cqlgdx","zjzyjt","gdjgxy","zymzdx"};// 学校

	// ###########################备注####################################
	// 10491：中国地质大学
	// 11647：浙江传媒学院
	// 12036：浙江交通职业技术学院
	// 13060：池州职业技术学院
	// 13107: 徐州工业职业技术学院
	// 10338：浙江理工大学
	// 12870: 浙江金融职业学院
	// 10653: 成都体育学院
	// 12867: 浙江旅游职业学院
	// 11417: 北京联合大学
	// 10657: 贵州大学
	// 13579：中国矿业大学徐海学院
	// 13828: 广西工商职业技术学院
	// 12751: 四川机电职业技术学院
	// 10488: 武汉科技大学
	// 10046: 中国音乐学院
	// 10834: 武汉职业技术学院
	// 11600: 湖北经济学院
	// 10052: 中央民族大学
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
