/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午04:44:40 
 */
package com.zfsoft.xgxt.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.action.Base;

import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 固定选项统一管理
 * @作者： Penghui.Qu
 * @时间： 2013-4-18 下午04:44:40
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class OptionUtil {

	public final static String ISNOT = "isNot";// 是否
	public final static String HAVENOT = "haveNot";// 有无
	public final static String SHZT = "shzt";// 审核状态
	public final static String ONOFF = "on-off";// 开启、关闭
	public final static String FMJZ = "fmjz";// 父母健在
	public final static String PKXJB = "pkxjb";// 贫困县级别.
	public final static String PKLX = "pklx";// 贫困类型
	public final static String PKDJ = "pkdj";// 贫困等级
	public final static String SFKN = "sfkn";// 是否困难
	public final static String KNDJ = "kndj";// 困难等级
	public final static String PYJG = "pyjg";// 评议结果
	public final static String JTHKXZ = "jthkxz";// 家庭户口性质
	public final static String YXSSZ = "yxssz";// 有效时设置
	public final static String GSLX = "gslx";// 公司类型
	public final static String FYLX = "fylx";// 费用类型
	public final static String XMNZT = "xmnzt";// 项目内状态
	//北京林业大学个性化开始	 
	public final static String FMJZQK = "fmjzqk"; //父母健在情况
	public final static String JRJKZK = "jrjkzk"; //家人健康状况
	public final static String BRJKZK = "brjkzk"; //家人健康状况
	public final static String JTZNS = "jtzns"; //家庭子女数
	public final static String ZDZNS = "zdzns"; //除自己在读的子女数
	public final static String JTSYS = "jtsys"; //家庭赡养数
	public final static String JTSRQK = "jtsrqk"; //家庭收入情况 
	//北京林业大学个性化结束	
	//永康职业技术学校个性化开始
	public final static String XSLB = "xslb"; //学生类别
	//永康职业技术学校个性化结束	
	//上海电机学院个性化开始	 
	public final static String DKQK = "dkqk"; //贷款情况
	public final static String JTCS = "jtcs"; //家庭出身
	public final static String JTSRLY = "jtsrly"; //收入来源
	public final static String JQLX = "jqlx"; //假期类型
	//上海电机学院个性化结束
	//黑龙江农垦职业学院 个性化开始	 
	public final static String DBHTKZ = "dbhtkz"; //低保或特困证
	public final static String TTXDJDXXQK = "ttxdjdxxqk"; //同胎兄弟就读学校情况
	public final static String FMSFJK = "fmsfjk"; //父母是否健康
	public final static String TBXDSTJK = "tbxdstjk"; //同胎兄弟身体健康
	public final static String ZXDKQK = "zxdkqk"; //有无国家助学贷款
	public final static String QGZXQK = "qgzxqk"; //勤工助学情况
	public final static String XSRCXFQK = "xsrcxfqk"; //学生在校日常消费情况	
	//黑龙江农垦职业学院 个性化结束
	//浙江机电
	public final static String GZKH_SJXZ = "gzkh_sjxz"; //工作考核-时间选择
	public final static String ZHCP_XMLX = "zhcp_xmlx";// 综合测评--项目类型
	public final static String BFJS_XMLX = "bfjs_xmlx";// 班风竞赛--项目类型
	public final static String ZHCP_TJZT = "zhcp_tjzt";// 综合测评--提交状态
	public final static String PJPY_TJDW = "pjpy_tjdw";// 评奖评优-统计单位

	public final static String RCSW_XZKG = "rcsw_xzkg";// 日常事务-下载开关
	public final static String ENCN = "en-cn"; // 中英文类型
	
	//华中师范 个性化开始
	public final static String YLZD9 = "ylzd9"; // 家庭情况
	public final static String YLZD10 = "ylzd10"; // 灾害情况
	//华中师范 个性化结束
	
	public final static String ABC = "abc";//家庭户口性质
	public final static String THJL_GZDJ = "thjl_gzdj";//关注等级
	public final static String THJL_GZLX = "thjl_gzlx";//关注类型
	public final static String GYBX_MYD = "gybx_myd";//满意度
	public final static String SFDQ = "sfdq";//是否单亲（陕西师大）
	public final static String SFDQ_10335 = "sfdq_10335";//是否单亲（浙江大學家庭情況調查家庭情況字段，該字段原顯示名字為是否單親）
	public final static String SFGE = "sfge";//是否孤儿（陕西师大）
	public final static String SFCJ = "sfcj";//是否残疾（陕西师大）
	public final static String YWJY = "ywjy";//义务教育人数
	public final static String GZJY = "gzjy";//高中教育人数
	public final static String BKJY = "bkjy";//本科教育及以上人数
	public final static String SFSSMZ_DZN = "sfssmzjtknzn";//是否少数民族家庭困难子女（陕西师大）
	public final static String SFBLDK = "sfbldk";//是否办理贷款（陕西师大）
	public final static String CYJKZK = "cyjkzk";//家庭成员健康状况（陕西师大）
	public final static String XB = "xb";//性别（陕西师大）
	//西安科技大学个性化
	public final static String SFDQ_10704 = "sfdq_10704";
	public final static String ZXDK_10704 = "zxdk_10704";
	
	
	public final static String GKDW="gkdw";//挂靠单位（社团管理）
	public final static String KHLX="khlx";//考核类型
	public final static String FZLX = "fzlx";//辅导员考核:分值类型
	public final static String JSFS = "jsfs";//辅导员考核:计算方式
	private String[][] values = null;
	private final static String DM = "dm";
	private final static String MC = "mc";
	private final static String CJPD = "cjpd";
	private final static String ZTWH = "ztwh";
	private final static String KQDJ = "kqdj";
	private final static String KHPF = "khpf";
	private final static String SSXQ = "ssxq";  //传媒所属校区
	
	private final static String CJLB = "cjlb";
	
	public static final String JCLX_WSJC = "1";//卫生检查
	public static final String JCLX_AQJC = "2";//安全检查
	public static final String JCLX_JLJC = "3";//纪律检查
	public static final String SFZJLX = "sfzjlx";
	public static final String SQLY_10026 = "sqly";
	//南京高等职业技术学院
	public static final String JTKNLX = "jtknlx";

	//华中师范
	public static final String CJLX_10511 = "cjlx_10511";
	public static final String JTSZD_10511 = "jtszd_10511";
	public static final String JTFZYY_10511 = "jtfzyy_10511";




	public static int page=0;
	private Log logger = LogFactory.getLog(OptionUtil.class);

	/**
	 * 
	 * @描述:按类型获取选项集合
	 * @作者：Penghui.Qu
	 * @日期：2013-4-18 下午04:49:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param type
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getOptions(String type) {

		if (ISNOT.equalsIgnoreCase(type)) {
			values = new String[][] { { "1", "是" }, { "0", "否" } };
		} else if (HAVENOT.equalsIgnoreCase(type)) {
			values = new String[][] { { "1", "有" }, { "0", "无" } };
		} else if (SHZT.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "未审核" }, { "1", "通过" },
					{ "2", "不通过" } }; //, { "3", "退回" }, { "4", "需重审" }
		} else if (YXSSZ.equalsIgnoreCase(type)) {
			values = new String[][] { { "cq", "长期" }, { "xs", "限时" } };
		} else if (GSLX.equalsIgnoreCase(type)) {
			values = new String[][] { { "st", "实体" }, { "wd", "网店" } };
		} else if (ONOFF.equalsIgnoreCase(type)) {
			values = new String[][] { { "1", "开启" }, { "0", "关闭" } };
		} else if (FMJZ.equalsIgnoreCase(type)) {
			values = new String[][] { { "父母双全", "父母双全" }, { "父母双亡", "父母双亡" },
					{ "父亡母在", "父亡母在" }, { "父在母亡", "父在母亡" } };
		} else if (PKXJB.equalsIgnoreCase(type)) {
			values = new String[][] { { "国家级贫困县", "国家级贫困县" },
					{ "省级贫困县", "省级贫困县" }, { "非贫困县", "非贫困县" } };
		} else if (JTHKXZ.equalsIgnoreCase(type)) {
			if(Base.xxdm.equals("11458")){
				values = new String[][] { { "家业户口", "家业户口" }, { "非农业户口", "非农业户口" } };
			}else if(Base.xxdm.equals("90211")) {
				values = new String[][] { { "农业", "农业" }, { "非农业", "非农业" } };
			}else {
				values = new String[][] { { "城镇", "城镇" }, { "农村", "农村" } };
			}
		} else if (ZHCP_XMLX.equalsIgnoreCase(type)) {
			CsszService csszService = new CsszService();
			String zclxdjxs = csszService.getCsz("zclxdjxs"); //综测等级显示
			
			//浙江理工大学个性化显示
			if("1".equals(zclxdjxs)){
				values = new String[][] { { "1", "加分" }, { "2", "减分" },
						{ "3", "默认分" },{ "4", "等级" } };
			}else {
				values = new String[][] { { "1", "加分" }, { "2", "减分" },
						{ "3", "默认分" } };
			}
		} else if (BFJS_XMLX.equalsIgnoreCase(type)) {
				values = new String[][] { { "1", "加分" }, { "2", "减分" },
						{ "3", "默认分" } };
		}else if (PJPY_TJDW.equalsIgnoreCase(type)) {
			values = new String[][] { { "cpz", "参评组" }, { "njzy", "年级专业" },
					{ "bj", "班级" } };
		} else if (ZHCP_TJZT.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "未提交" }, { "1", "已提交" },
					{ "2", "取消提交" } };
		} else if (RCSW_XZKG.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "申请后可下载" }, { "1", "审核通过后方可下载" } };
		} else if (ENCN.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "中文" }, { "1", "中英文" } };
		} else if (PKDJ.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "一般困难" }, { "1", "困难" },
					{ "2", "特殊困难" } };
		} else if (SFKN.equalsIgnoreCase(type)) {
			values = new String[][] { { "不困难", "不困难" }, { "困难", "困难" } };
		} else if (KNDJ.equalsIgnoreCase(type)) {
			values = new String[][] { { "一般困难", "一般困难" }, { "特别困难", "特别困难" } };
		} else if (PYJG.equalsIgnoreCase(type)) {
			values = new String[][] { { "1", "同意申请" }, { "0", "不同意申请" } };
		} else if (PKLX.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "孤儿" }, { "1", "烈士子女" },
					{ "2", "无收入" }, { "3", "重病户" }, { "4", "低保户" },
					{ "5", "双下岗" }, { "6", "纯农户" }, { "7", "低收入" },
					{ "8", "因灾致困" }, { "9", "多子女" }, { "10", "本人残疾" } };
			if(Base.xxdm.equals("11998") || Base.xxdm.equals("10344")){
				values = new String[][] {{"孤儿","孤儿"},{"低收入家庭","低收入家庭"},{"因病致贫","因病致贫"},{"其他","其他"},{"困难归侨","困难归侨"},
						{"品学兼优","品学兼优"},{"残疾学生","残疾学生"},{"单亲家庭 ","单亲家庭 "},{"五保户 ","五保户 "},{"低保户 ","低保户 "},{"农村低保户 ","农村低保户 "}
						,{"残疾人 ","残疾人 "},{"子女 ","子女 "},{"建档立卡 ","建档立卡 "},{"父母丧失劳动能力 ","父母丧失劳动能力 "}
						,{"军烈属或优抚子女 ","军烈属或优抚子女 "},{"农村特困供养 ","农村特困供养 "},{"因灾致贫 ","因灾致贫 "}};
			}
		}else if (ABC.equalsIgnoreCase(type)){
			values = new String[][] { { "A", "A" }, { "B", "B" }, { "C", "C" }};
		}else if (FMJZQK.equalsIgnoreCase(type)) {
			values = new String[][] {{ "双亡", "双亡" }, { "父亲过世", "父亲过世" },
					{ "母亲过世", "母亲过世" }, { "均健在(离异)", "均健在(离异)" }, { "均健在", "均健在" }};		
		}else if (JRJKZK.equalsIgnoreCase(type)) {
			values = new String[][] {{ "0", "绝症、身体残疾" },
					{ "2", "慢性病、传染病" }, { "4", "普通疾病(暂时性、需要手术治疗或较为严重的外伤)" },
					{ "5", "健康" }};
		}else if (BRJKZK.equalsIgnoreCase(type)) {
			values = new String[][] {{ "1", "身体残疾" },
					{ "2", "慢性病、传染病" }, { "4", "普通疾病(暂时性、需要手术治疗或较为严重的外伤)" },
					{ "5", "健康" }}; 
		}else if (JTZNS.equalsIgnoreCase(type)) {
			values = new String[][] {{ "0", "4名及以上" }, { "1", "3名" },
					{ "2", "2名" }, { "3", "1名" }};
		}else if (ZDZNS.equalsIgnoreCase(type)) {
			values = new String[][] {{ "0", "4名及以上" }, { "1", "3名" },
					{ "2", "2名" }, { "3", "1名" }, { "4", "无" }};
		}else if (JTSYS.equalsIgnoreCase(type)) {
			values = new String[][] {{ "0", "4名及以上" }, { "1", "2-3名" },
					{ "2", "1名" }};
		}else if (JTSRQK.equalsIgnoreCase(type)) {
			values = new String[][] {{ "父母双方均无收入来源", "父母双方均无收入来源" }, { "一方有收入来源(父亲)", "一方有收入来源(父亲)" },
					{ "一方有收入来源(母亲)", "一方有收入来源(母亲)" }, { "父母双方均有收入来源", "父母双方均有收入来源" }};
		}else if (DKQK.equalsIgnoreCase(type)) {
			values = new String[][] {{ "尚未贷款且不准备贷款", "尚未贷款且不准备贷款" },{ "已经办理好贷款手续", "已经办理好贷款手续" },{ "尚未贷款但准备贷款", "尚未贷款但准备贷款" },{ "准备办理生源地贷款", "准备办理生源地贷款" },{ "已经办妥生源地贷款", "已经办妥生源地贷款" }};
		}else if (JTCS.equalsIgnoreCase(type)) {
			values = new String[][] {{ "农民", "农民" },{ "工人", "工人" },{ "干部", "干部" },{ "其他", "其他" }};
		}else if (JTSRLY.equalsIgnoreCase(type)) {
			values = new String[][] {
					{ "工资、奖金、津贴、补贴和其他劳动收入", "工资、奖金、津贴、补贴和其他劳动收入" },
					{ "离退休金、基本养老金、基本生活费、失业保险金", "离退休金、基本养老金、基本生活费、失业保险金" },
					{ "继承、接受赠予、出租或出售家庭财产获得的收入", "继承、接受赠予、出租或出售家庭财产获得的收入" },
					{ "存款及利息，有价证券及红利、股票、博彩收入", "存款及利息，有价证券及红利、股票、博彩收入" },
					{ "经商、办厂以及从事种植业、 养植业、加工业扣除必要成本后的收入", "经商、办厂以及从事种植业、 养植业、加工业扣除必要成本后的收入" },
					{ "赡养费、抚(扶)养费", "赡养费、抚(扶)养费" },
					{ "自谋职业收入", "自谋职业收入" },
					{ "其他应当计入家庭的收入", "其他应当计入家庭的收入" }};
		}else if(YLZD9.equalsIgnoreCase(type)) {
			if(Base.xxdm.equals("10511")) {
				values = new String[][] {{ "双亲健全", "双亲健全" },{ "孤儿", "孤儿" },{ "单亲（父母一方去世）", "单亲（父母一方去世）" },{ "父母离异", "父母离异" },{ "双亲有残疾", "双亲有残疾" },{ "本人残疾", "本人残疾" },{ "军烈属或优抚对象", "军烈属或优抚对象" },{ "重病", "重病" },{ "五保户", "五保户" }};
			}		
		}else if(YLZD10.equalsIgnoreCase(type)) {
			if(Base.xxdm.equals("10511")) {
				values = new String[][] {{ "自然灾害", "自然灾害" },{ "突发意外", "突发意外" },{ "家庭成员病残", "家庭成员病残" },{ "家长失业", "家长失业" },{ "其他", "其他" }};
			}
		}else if (JQLX.equalsIgnoreCase(type)) {
			values = new String[][] {{ "暑假", "暑假" },{ "寒假", "寒假" },{ "其他", "其他" }};
		}
		else if(GZKH_SJXZ.equals(type)){
			values = new String[][]{{"07","7时"},{"08","8时"},{"09","9时"},{"10","10时"},{"11","11时"},{"12","12时"},{"13","13时"},{"14","14时"},{"15","15时"},{"16","16时"},{"17","17时"},{"18","18时"},{"19","19时"},{"20","20时"},{"21","21时"},{"22","22时"},{"23","23时"},{"24","24时"}};
		}else if(THJL_GZDJ.equals(type)){
			values = new String[][]{{"三星","三星"},{"四星","四星"},{"五星","五星"}};
		}else if(THJL_GZLX.equals(type)){
			values = new String[][]{{"危机个案","危机个案"},{"重点关注","重点关注"},{"一般关注","一般关注"},{"取消关注","取消关注"},{"不关注","不关注"}};
		}else if(GYBX_MYD.equals(type)){
			values = new String[][]{{"满意","满意"},{"较满意","较满意"},{"非常满意","非常满意"}};
		}else if(DBHTKZ.equals(type)){
			values = new String[][]{{"无","无"},{"有低保证","有低保证"},{"有特困证","有特困证"}};
		}else if(TTXDJDXXQK.equals(type)){
			values = new String[][]{{"同胞兄弟姐妹中一名与学生本人同时在高中或大学就读","同胞兄弟姐妹中一名与学生本人同时在高中或大学就读"},{"多名与学生本人同时在高中或大学就读","多名与学生本人同时在高中或大学就读"}};
		}else if(FMSFJK.equals(type)){
			values = new String[][]{{"是","是"},{"身患重病","身患重病"},{"身体残疾","身体残疾"}};
		}else if(TBXDSTJK.equals(type)){
			values = new String[][]{{"健康","健康"},{"身患重病","身患重病"},{"身体残疾","身体残疾"}};
		}else if(ZXDKQK.equals(type)) {
			values = new String[][]{{"无","无"},{"有校园地国家助学贷款","有校园地国家助学贷款"},{"有生源地国家助学贷款","有生源地国家助学贷款"}};
		}else if(QGZXQK.equals(type)) {
			values = new String[][]{{"未参加过","未参加过"},{"长期参加","长期参加"},{"短期参加","短期参加"}};
		}else if(XSRCXFQK.equals(type)) {
			values = new String[][]{{"月生活费不超过800元","月生活费不超过800元"},{"月生活费超过800元","月生活费超过800元"}};
		}else if(SFDQ.equals(type)){
			values = new String[][]{{"否","否"},{"离异","离异"},{"父亲去世","父亲去世"},{"母亲去世","母亲去世"},{"其他","其他"}};
		}else if(SFGE.equals(type)){
			values = new String[][]{{"否","否"},{"是","是"}};
		}else if(SFCJ.equals(type)){
			values = new String[][]{{"否","否"},{"是","是"}};
		}else if(YWJY.equals(type)){
			values = new String[][]{{"",""},{"除本人外，0人接受义务教育","除本人外，0人接受义务教育"},{"除本人外，1人接受义务教育","除本人外，1人接受义务教育"},{"除本人外，2人接受义务教育","除本人外，2人接受义务教育"},{"除本人外，3人及以上接受义务教育","除本人外，3人及以上接受义务教育"}};
		}else if(GZJY.equals(type)){
			values = new String[][]{{"",""},{"除本人外，0人接受高中教育","除本人外，0人接受高中教育"},{"除本人外，1人接受高中教育","除本人外，1人接受高中教育"},{"除本人外，2人接受高中教育","除本人外，2人接受高中教育"},{"除本人外，3人及以上接受高中教育","除本人外，3人及以上接受高中教育"}};
		}else if(BKJY.equals(type)){
			values = new String[][]{{"",""},{"除本人外，0人接受本科及以上教育","除本人外，0人接受本科及以上教育"},{"除本人外，1人接受本科及以上教育","除本人外，1人接受本科及以上教育"},{"除本人外，2人接受本科及以上教育","除本人外，2人接受本科及以上教育"},{"除本人外，3人及以上接受本科及以上教育","除本人外，3人及以上接受本科及以上教育"}};
		}else if(SFSSMZ_DZN.equals(type)){
			values = new String[][]{{"否","否"},{"是","是"}};
		}else if(SFBLDK.equals(type)){
			values = new String[][]{{"否","否"},{"是，已办理校园地国家助学贷款","是，已办理校园地国家助学贷款"},{"是，已办理生源地信用助学贷款","是，已办理生源地信用助学贷款"},{"是，已办理其他贷款","是，已办理其他贷款"}};
		}else if(CYJKZK.equals(type)){
			values = new String[][]{{"良好","良好"},{"残疾","残疾"},{"慢性病","慢性病"},{"心理疾病","心理疾病"}};
		}else if(XB.equals(type)){
			values = new String[][]{{"男","男"},{"女","女"}};
		}else if(GKDW.equals(type)){
			values = new String[][]{{"二级学院","二级学院"}};
		}
		else if(KHLX.equals(type)){
			values = new String[][]{{"1","学生"},{"2","教师"}};
		}else if(CJPD.equalsIgnoreCase(type)){
			values = new String[][]{{"",""},{"优秀","优秀"},{"良","良"},{"及格","及格"},{"不及格","不及格"}};
		}else if(ZTWH.equalsIgnoreCase(type)){
			values = new String[][]{{"",""},{"正常","正常"},{"退团","退团"}};
		}
		else if(FZLX.equals(type)){
			values = new String[][]{{"1","固定"},{"0","区间"}};
		}else if(KQDJ.equalsIgnoreCase(type)){
			values = new String[][]{{"",""},{"是","是"},{"否","否"}};
		}
		else if(JSFS.equals(type)){
			values = new String[][]{{"0","平均分"},{"1","去头尾求平均"},{"2","去头尾求平均(比例)"}};
		}else if(KHPF.equalsIgnoreCase(type)){
			values = new String[][]{{"是","是"},{"否","否"}};
		}else if(SFDQ_10335.equalsIgnoreCase(type)){
			values = new String[][]{{"单亲","单亲"},{"孤儿","孤儿"},{"双亲","双亲"}};
		}else if(SFDQ_10704.equalsIgnoreCase(type)){
			values = new String[][]{{"离异","离异"},{"母亲去世","母亲去世"},{"父亲去世","父亲去世"},{"父亲走失","父亲走失"},{"母亲走失","母亲走失"}};
		}else if(ZXDK_10704.equalsIgnoreCase(type)){
			values = new String[][]{{"国家助学贷款","国家助学贷款"},{"生源地助学贷款","生源地助学贷款"},{"其他贷款","其他贷款"}};
		}else if(XSLB.equalsIgnoreCase(type)) {
			values = new String[][]{{"福利机构监护的未成年人","福利机构监护的未成年人"},{"革命烈士子女","革命烈士子女"},{"五保供养的未成年人","五保供养的未成年人"},{"残疾学生","残疾学生"},{"低保家庭子女","低保家庭子女"},{"低收入家庭子女","低收入家庭子女"}};
		}
		else if(FYLX.equalsIgnoreCase(type)) {
			values = new String[][]{{"0","报销费用"},{"1","经费追加"}};
		}
		else if(XMNZT.equalsIgnoreCase(type)) {
			values = new String[][]{{"0","离岗"},{"1","正常"}};
		}else if(SSXQ.equals(type)){
			values = new String[][]{{"001","下沙"},{"002","桐乡"}};
		}else if(CJLB.equals(type)){
			values = new String[][]{{"视力残疾","视力残疾"},{"听力残疾","听力残疾"},{"智力残疾","智力残疾"},{"其他残疾","其他残疾"}};
		}else if(SFZJLX.equals(type)){
			values = new String[][]{{"居民身份证","居民身份证"},{"军官证","军官证"},{"士兵证","士兵证"},{"文职干部证","文职干部证"},{"部队离退休证","部队离退休证"},{"香港特区护照/身份证明","香港特区护照/身份证明"}
			,{"澳门特区护照/身份证明","澳门特区护照/身份证明"},{"台湾居民来往大陆通行证","台湾居民来往大陆通行证"},{"境外永久居住证","境外永久居住证"},{"护照","护照"},{"户口薄","户口薄"},{"其他","其他"}};
		}else if(SQLY_10026.equals(type)){
			values = new String[][]{{"家庭遭受自然灾害","家庭遭受自然灾害"},{"家庭遭受突发意外事件","家庭遭受突发意外事件"},{"家庭成员因残疾","家庭成员因残疾"},{"年迈而劳动能力弱情况","年迈而劳动能力弱情况"},{"家庭适龄就学子女较多","家庭适龄就学子女较多"},{"家庭成员失业","家庭成员失业"}
			,{"家庭欠债","家庭欠债"},{"建档立卡家庭","建档立卡家庭"},{"低保","低保"},{"其他","其他"}};
		}else if(JTKNLX.equals(type)){
			values = new String[][]{{"城乡最低生活保障家庭子女，即低保户","城乡最低生活保障家庭子女，即低保户"},{"孤儿","孤儿"},{"建档立卡家庭经济困难学生，即持有《帮扶手册》","建档立卡家庭经济困难学生，即持有《帮扶手册》"},{"残疾学生","残疾学生"},{"特困职工家庭子女","特困职工家庭子女"},{"特困残疾人家庭子女","特困残疾人家庭子女"}
			,{"农村低收入纯农户家庭子女","农村低收入纯农户家庭子女"},{"低保边缘家庭子女","低保边缘家庭子女"},{"革命烈士或因公牺牲军人、警察子女","革命烈士或因公牺牲军人、警察子女"},{"少数民族家庭经济困难学生","少数民族家庭经济困难学生"},{"六盘山特困地区学生","六盘山特困地区学生"},{"其他因突发情况致贫家庭子女","其他因突发情况致贫家庭子女"}		
			};
		}
		else if(CJLX_10511.equals(type)){
			values = new String[][]{{"视力残疾","视力残疾"},{"听力残疾","听力残疾"},
					{"智力残疾","智力残疾"},{"其他残疾","其他残疾"}
			};
		}else if(JTSZD_10511.equals(type)){
			values = new String[][]{{"县城及以上","县城及以上"},{"小城镇","小城镇"}, {"农村","农村"}
			};
		}else if(JTFZYY_10511.equals(type)){
			values = new String[][]{{"住建房贷款","住建房贷款"},{"购车贷款","购车贷款"}, {"教育类贷款","教育类贷款"}
					, {"医疗借款","医疗借款"}, {"其他","其他"}
			};
		}else if(CJLB.equals(type)){
			values = new String[][]{{"住建房贷款","住建房贷款"},{"购车贷款","购车贷款"}, {"教育类贷款","教育类贷款"}
					, {"医疗借款","医疗借款"}, {"其他","其他"}
			};
		}
	
		return creatList();
	}

	// 创建列表项
	private List<HashMap<String, String>> creatList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (values == null) {
			logger.error("未定义该类型选项！");
			throw new NullPointerException();
		}

		for (int i = 0; i < values.length; i++) {

			HashMap<String, String> map = new HashMap<String, String>();

			map.put(DM, values[i][0]);
			map.put(MC, values[i][1]);

			list.add(map);
		}

		return list;
	}

}
