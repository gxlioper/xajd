package common;

public class GlobalsVariable {

	//系统级
	public final static String XTDM_ADMIN = "admin";
	public final static String XTDM_XY = "xy";//学院用户类型
	public final static String XTDM_XX = "xx";//学校用户类型
	public final static String XTDM_FDY = "fdy";//辅导员,班主任用户类型
	public final static String XTDM_JWFLAG_XF = "1";//学分版教务
	public final static String XTDM_JWFLAG_GD = "0";//过渡版教务
	
	public final static String XTDM_ISFDY = "isFdy";//用于从SESSION取是否辅导员类型
	public final static String XTDM_FDYQX = "fdyQx";//用于从SESSION取是否辅导员类型
	
	public final static String XTDM_STUDENT = "student";//用于从SESSION取是否学生类型
	public final static String XTDM_STU = "stu";//用于从SESSION取是否学生类型
	public final static String XTWH_SH_LIST = "shList";//审核
	public final static String XTWH_XXBMLB_LIST = "xxbmlbList";//学校部门类别
	public final static String XTWH_MZ_LIST = "mzList";//民族
	public final static String XTWH_ZZMM_LIST = "zzmmList";//政治面貌
	public final static String XTWH_PYCC_LIST = "pyccList";//培养层次
	public final static String XTWH_RXFS_LIST = "rxfsList";//入学方式
	public final static String XTWH_KSLB_LIST = "kslbList";//考生类别
	public final static String XTWH_PYFS_LIST = "pyfsList";//培养方式
	public final static String XTWH_YH_LIST = "yhList";//银行
	
	public final static String XTWH_GNMK_PJPY = "评奖评优";
	public final static String XTWH_GNMK_XSXX = "学生信息";
	public final static String XTWH_GNMK_XSCJ = "课程成绩";
	public final static String XTWH_GNMK_WJCF = "违纪处分";
	public final static String XTWH_GNMK_QGZX = "勤工助学";
	public final static String XTWH_GNMK_XSZZ = "学生资助";
	public final static String XTWH_GNMK_GYGL = "公寓管理";
	public final static String XTWH_GNMK_XJYD = "学籍异动";
	public final static String XTWH_GNMK_CWSJ = "财务数据";
	public final static String XTWH_GNMK_RCSW = "日常事务";
	public final static String XTWH_GNMK_XJYDXX = "学籍信息";
	
	//==================统一身份认证使用学校代码=========
	public final static String[] XTWH_TYSFRZ_DR = {"13798", "10022", "10143","10029"};//东软认证学校代码列表,湖北财税职业 北京林业大学 沈阳航空工业学院,首都体育学院
	public final static String[] XTWH_TYSFRZ_DR_NEW = {"10426"};//青岛科技大学
	public final static String[] XTWH_TYSFRZ_JZ = {"13022", "14139", "12049", "10395", "10478", "10718"};//金智认证学校代码列表,浙大宁波理工,北京社会管理职业学院,吉林交通职业技术学院,闽江学院,周口师范学院,陕西师范大学
	public final static String[] XTWH_TYSFRZ_JZ_NEW = {"14073","51221"};//（new）金智认证学校代码列表,北京经济管理职业学院
	public final static String[] XTWH_TYSFRZ_SW = {"12865", "11080"};//数维认证学校代码列表,浙江商业职业技术学院,西安文理学院
	public final static String[] XTWH_TYSFRZ_YB = {"10264", "11458"};//易班认证学校代码列表，上海海洋大学,上海电机学院
	public final static String[] XTWH_TYSFRZ_DK = {"13108"};//达科认证学校代码列表，江苏信息职业技术学院
	public final static String[] XTWH_TYSFRZ_KB = {"400030","13657"};//康邦认证学校代码列表，北京商贸学校,北京市实美职业学校
	public final static String[] XTWH_TYSFRZ_KS = {"13815"};//康赛认证学校代码列表，四川信息职业技术学院
	public final static String[] XTWH_TYSFRZ_XE = {"10504"};//希尔认证学校代码列表，华中农业大学
	public final static String[] XTWH_TYSFRZ_HS = {"10742"};//华三认证学校代码列表，西北名族大学
	
	//评奖评优级
	public final static String PJPY_JXJ = "jxj";
	public final static String PJPY_RYCH = "rych";
	public final static String PJPY_ZHCPJXJ = "zhcpjxj";
	
	// 党团建设 通用 各级别表
	public final static String DTJS_DYXXB = "dyxxb";// 党员信息
	public final static String DTJS_YBDYB = "ybdyxxb";// 预备党员
	public final static String DTJS_FZDXB = "czxx_fzdxb";// 发展对象
	public final static String DTJS_JJFZB = "rdjjfzxxb";// 入党积极分子
	public final static String DTJS_RDSQB = "czxx_rdsqb";//入党申请
	
	// 党团建设 通用 各级别表时间字段
	public final static String DTJS_DYXXB_RDSJ = "rdsj";// 党员信息(入党时间)
	public final static String DTJS_DYXXB_ZZSJ = "zzsj";// 党员信息(转正时间)
	public final static String DTJS_YBDYB_KSSJ = "kssj";// 预备党员(开始时间)
	public final static String DTJS_FZDXB_KSSJ = "kssj";// 发展对象(开始时间)
	public final static String DTJS_JJFZB_KSSJ = "kssj";// 入党积极分子(开始时间)
	

	// 问卷调查 试题类型代码
	public final static String WJDC_ONECHOOSE = "oneChoose";// 单选题
	public final static String WJDC_ALLCHOOSE = "allChoose";// 多选题
	public final static String WJDC_QUESTIONS = "questions";// 问答题

	// 问卷调查 所属模块代码
	public final static String WJDC_XLPC = "xlpc";// 心理普查
	public final static String WJDC_SXZK = "sxzk";// 思想状况调查
	public final static String WJDC_SXSZK = "sxszk";// 实习生状况调查
	
	//心理健康
	public final static String XLJK_DXSRGYS_QTZZYZ = "躯体症状";
	public final static String XLJK_DXSRGYS_JSFLYZ = "精神分裂";
	public final static String XLJK_DXSRGYS_YYZYZ = "抑郁症";
	public final static String XLJK_DXSRGYS_SJZYZ = "神经症";
	public final static String XLJK_DXSRGYS_QTZZYW = "qtzz";
	public final static String XLJK_DXSRGYS_JSFLYW = "jsfl";
	public final static String XLJK_DXSRGYS_YYZYW = "yyz";
	public final static String XLJK_DXSRGYS_SJZYW = "sjz";
	public final static String XLJK_KTEYS_AYZ  = "ayz";
	public final static String XLJK_KTEYS_BYZ  = "byz";
	public final static String XLJK_KTEYS_CYZ  = "cyz";
	public final static String XLJK_KTEYS_DYZ  = "dyz";
	public final static String XLJK_KTEYS_EYZ  = "eyz";
	public final static String XLJK_KTEYS_FYZ  = "fyz";
	public final static String XLJK_KTEYS_GYZ  = "gyz";
	public final static String XLJK_KTEYS_HYZ  = "hyz";
	public final static String XLJK_KTEYS_IYZ  = "iyz";
	public final static String XLJK_KTEYS_LYZ  = "lyz";
	public final static String XLJK_KTEYS_MYZ  = "myz";
	public final static String XLJK_KTEYS_NYZ  = "nyz";
	public final static String XLJK_KTEYS_OYZ  = "oyz";
	public final static String XLJK_KTEYS_Q1YZ = "q1yz";
	public final static String XLJK_KTEYS_Q2YZ = "q2yz";
	public final static String XLJK_KTEYS_Q3YZ = "q3yz";
	public final static String XLJK_KTEYS_Q4YZ = "q4yz";
	public final static String XLJK_KTEYS_X1YZ = "x1yz";
	public final static String XLJK_KTEYS_X2YZ = "x2yz";
	public final static String XLJK_KTEYS_X3YZ = "x3yz";
	public final static String XLJK_KTEYS_X4YZ = "x4yz";
	public final static String XLJK_KTEYS_Y1YZ = "y1yz";
	public final static String XLJK_KTEYS_Y2YZ = "y2yz";
	public final static String XLJK_KTEYS_Y3YZ = "y3yz";
	public final static String XLJK_KTEYS_Y4YZ = "y4yz";
	//======学生信息============
	public final static String XSXX_KTEYS_XJZTLIST = "xjztList";
	public final static String XSXX_KTEYS_YDLBLIST = "ydlbList";
	public final static String XSXX_KTEYS_SFZXLIST = "sfzxList";
	public final static String XSXX_KTEYS_GNMK = "mkmc";//模块名称
	public final static String XSXX_KTEYS_CXJG = "cxjg";//查询结果
	
	
   //=======================功能代码=======================
	//==================================评奖评优==============================================
	
	public final static String GNDM_PJPY_RSTZ = "pjpy_rstz";                //评奖评优人数调整
	public final static String GNDM_PJPY_JXJSQRSZ = "pjpy_jxjsqrsz";        //评奖评优奖学金、荣誉称号申请人设置
	public final static String GNDM_PJPY_JXJSQ = "pjpy_jxjsq";              //评奖评优奖学金申请
	public final static String GNDM_PJPY_RYCHSQ = "pjpy_rychsq";            //评奖评优荣誉称号申请
	public final static String GNDM_PJPY_JXJSH = "pjpy_jxjsh";              //评奖评优奖学金审核
	public final static String GNDM_PJPY_ZHSZCP = "pjpy_zhcpwh";            //综合素质测评
	public final static String GNDM_PJPY_TJSZ = "pjpy_tjsz";                //条件设置
	public final static String GNDM_PJPY_ZHCPJXJSQ = "pjpy_zhcpjxjsq";      //综合测评奖学金申请
	public final static String GNDM_PJPY_ZHCPJXJSH = "pjpy_zhcpjxjsh";      //综合测评奖学金审核
	public final static String GNDM_PJPY_JXJJESZ = "pjpy_jxjjesz";          //奖学金金额设置
	public final static String GNDM_PJPY_ZHCPJXJSB = "pjpy_zhcpjxjsb";      //综合测评奖学金上报
	public final static String GNDM_PJPY_JXJSB = "pjpy_jxjsb";              //奖学金上报
	//==================================end评奖评优==============================================
	
	//==================学生信息========================
	public final static String GNDM_XSXX_XJYDSH = "xjydsh";              //学籍异动审核
	
	//==================违纪处分========================
	public final static String GNDM_WJCF_WXSH = "wxsh"; //无需审核标志
	
	//==================建制5,6级控制========================
	public final static String[] JXGL_JXJZ_FIVELV = {""};
	public final static String[] JXGL_JXJZ_SIXLV = {"14073"};//北京经济管理职业学院
	
}
