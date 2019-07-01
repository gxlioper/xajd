/*
 * 创建日期 2006-8-24
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @author bat_zzj
 * 
 *  要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class CommanForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SearchModel searchModel = new SearchModel();
	
	private String wjh;//文件号
	
	private String ffr;
	
	private String[] primary_key;
	
	private String wjkssj;//违纪开始时间
	
	private String wjjssj;//违纪结束时间
	
	private String jydw;//就业单位
	
	private String [] xyyrdwsh_hid;//需要用人单位审核
	
	private String  xyyrdwsh;//需要用人单位审核
	
	private String xxdm;//学校代码 
	
	private String gzzsj;//工作总时间
	
	private String gzzsj_ks;//工作总时间开始时间
	
	private String gzzsj_js;//工作总时间结束时间
	
	private String xmlb;//项目类别
	private String xmlbdm;//项目类别代码
	private String sfjq;//是否缴清
	private String zd1;//字段1（湖州师范：学籍预警）
	private String sftsxs;//是否特殊学生
	private String errMsg2;

	public String getErrMsg2() {
		return errMsg2;
	}

	public void setErrMsg2(String errMsg2) {
		this.errMsg2 = errMsg2;
	}

	public String getSftsxs() {
		return sftsxs;
	}

	public void setSftsxs(String sftsxs) {
		this.sftsxs = sftsxs;
	}

	public String[] getXyyrdwsh_hid() {
		return xyyrdwsh_hid;
	}

	public void setXyyrdwsh_hid(String[] xyyrdwsh_hid) {
		this.xyyrdwsh_hid = xyyrdwsh_hid;
	}

	public String getJydw() {
		return jydw;
	}

	public void setJydw(String jydw) {
		this.jydw = jydw;
	}

	public String getWjjssj() {
		return wjjssj;
	}

	public void setWjjssj(String wjjssj) {
		this.wjjssj = wjjssj;
	}

	public String getWjkssj() {
		return wjkssj;
	}

	public void setWjkssj(String wjkssj) {
		this.wjkssj = wjkssj;
	}

	public String[] getPrimary_key() {
		return primary_key;
	}

	public void setPrimary_key(String[] primary_key) {
		this.primary_key = primary_key;
	}

	public String getFfr() {
		return ffr;
	}

	public void setFfr(String ffr) {
		this.ffr = ffr;
	}

	public String getWjh() {
		return wjh;
	}

	public void setWjh(String wjh) {
		this.wjh = wjh;
	}

	public String getWjffKssj() {
		return wjffKssj;
	}

	public void setWjffKssj(String wjffKssj) {
		this.wjffKssj = wjffKssj;
	}

	public String getWjffJssj() {
		return wjffJssj;
	}

	public void setWjffJssj(String wjffJssj) {
		this.wjffJssj = wjffJssj;
	}


	private String wjffKssj;//文件发放开始时间
	
	private String wjffJssj;//文件发放结束时间
	
	private String pubKssj;
	
	private String pubJssj;
	
	private String tagId;
	
	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}


	FormFile bkzp;
	FormFile rxzp;
	FormFile byzp;
	FormFile uploadFile;
	FormFile impFilePath;
	FormFile checkFilePath;
	private String zddwmc;	
	private String fdylxdh;
	private String bzrxm;
	private String zlbzrxm;
	private String jyh;
	private String sfgat;
	private String sfssmz;
	private String shjg;
	private String gwflag;
	private String rxqdwdz;
	private String rxqdwyb;
	private String gzbx;	
	private String pk;
	private String sydshen;
	private String sydshi;
	private String sydxian;
	private String jtcy1_zjxy;
	private String jtcy2_zjxy;
	private String jtcy3_zjxy;
	private String jl1_qzny;
	private String jl1_xxjgzdw;
	private String jl1_xxhrzw;	
	private String jl2_qzny;
	private String jl2_xxjgzdw;
	private String jl2_xxhrzw;	
	private String jl3_qzny;
	private String jl3_xxjgzdw;
	private String jl3_xxhrzw;	
	private String jl4_qzny;
	private String jl4_xxjgzdw;
	private String jl4_xxhrzw;	
	private String jl5_qzny;
	private String jl5_xxjgzdw;
	private String jl5_xxhrzw;	
	private String jl6_qzny;
	private String jl6_xxjgzdw;
	private String jl6_xxhrzw;
	private String dah;         
	private String ylbxh;
	private String month;//月
	private String tjfs;//统计方式
	private String zwxzdm;       //职位性质代码
	private String gwfbr;        //岗位发布人
	private String sfdbh;        //是否低保户
	private String sfgr;         //是否孤儿
	private String sfyfdx;       //是否优抚
	private String yjshf;        //月均生活费
	private String jfglkg="1";//经费管理开关
	private String drms="1";//导入模式 1.导入新数据 2.更新数据s
	private String sfgq;//是否过期
	private String sfkx = "kx";//是否空闲
	private String pkValue;
	private String excelList;
	private String yhkh;//银行卡号
	private String ykth;//一卡通号
	private String sfbys;//是否毕业生
	private String glsjTable;
	private String yskts;       //应上课天数
	private String sskts;       //实上课天数
	private String kuangke;     //旷课
	private String bingjia;     //病假
	private String shijia;      //事假
	private String chidao;      //迟到
	private String qita;        //其它
	private String zhszpd;      //综合素质评定
	private String bzr;         //班主任
	private String bzrlxdh;     //班主任联系电话
	private String yhdm;        //银行代码
	private String sfyx;		//是否有效
	private String[] checkVal;
	private String[] pkV;
	private String xfje;        //学费金额
	private String ysbjdm;      //原始班级代码
	private String mkbjdm;      //模块班级代码
	private String xysh;			//学院审核
	
	private String[] pk1;
	
	private String[] keyval;
	
	private String tableName;
	
	private String jfqk;//缴费情况
	
	private String sqrq;//申请日期

	private String dzyx;//电子邮箱

	private String xjzt;//学籍状态
	private String sfby;//是否毕业

	private String XXCLYJ="";

	private String lydq;//来源地区

	private String syd;//来源地区

	private String lxdh;//联系电话

	private String dhqh;//电话区号
	private String zt;
	private String sjhm;//手机号码

	private String pycc;//培养层次

	private String pyfs;//培养方式

	private String tc;//特长

	private String ah;//爱好

	private String sfdk;//是否贷款

	private String jtqkjj;//个人及家庭情况简介
	
	private String jtjjqk;//家庭经济情况

	private String rxfs;//入学方式

	private String tz;//体重

	private String xmpy;//姓名拼音

	private String sg;//身高

	private String cym;//曾用名

	private String whcd;//文化程度

	private String rxqdw;//入学前单位

	private String jtdh;//家庭电话

	private String jtzd;//家庭地址
	
	private String jjzk;//家庭经济状况
	
	private String byxx;//毕业信息

	private String jrgqtsj;//入团时间

	private String jrgcdsj;//入党时间

	private String jlcfjl;	//奖励处分经历
	
	private String grjl;//个人简历
	
	private String zkzh;//准考证号
	private String isFdy;
	
	private String jcsjsz;//基础数据设置
	
	public String getJcsjsz() {
		return jcsjsz;
	}

	public void setJcsjsz(String jcsjsz) {
		this.jcsjsz = jcsjsz;
	}

	public String getPubKssj() {
		return pubKssj;
	}
	
	public void setPubKssj(String pubKssj) {
		this.pubKssj = pubKssj;
	}
	
	public String getPubJssj() {
		return pubJssj;
	}
	
	public void setPubJssj(String pubJssj) {
		this.pubJssj = pubJssj;
	}
	
	
	private String sfcj;//是否残疾
	
	private String jtyb;//家庭邮编

	private String xx;//血型

	private String jkzk;//健康状况	

	private String mz;//名族
	
	private String sfpk;//是否贫困生

	private String kslb;//考生类别

	private String zzmm;//政治面貌		

	private String bjmc;//班级名称	

	private String jxjdm1;

	private String ssdh;//宿舍电话

	private String xszp;//学生照片

	private String zsrq;//住宿时间
	
	private String sfzfx;//是否在分校
	
	private String zjdm;//宗教信息

	private String zsjzrq;//住宿截止日期

	private String ssbz;//住宿备注
	
	private String mzdm;//民族代码  
	
	private String zzmmdm;//政治面貌代码 
	
	private String sfqsz;//是否寝室长

	private String ssch;//宿舍床号

	private String qsdh;//寝室电话

	private String jtszd;//家庭所在地
	
	private String lxdh1;//联系电话

	private String jtcy1_xm;//家庭成员1姓名
	
	private String jtcy1_sfzh;//家庭成员1姓名

	private String jtcy1_zzmm;//家庭成员1政治面貌

	private String jtcy1_ch;//家庭成员1称呼
	
	private String jtcy1_gx;//家庭成员1称呼

	private String jtcy1_zy;//家庭成员１职业

	private String jtcy1_zw;//家庭成员1职务

	private String jtcy1_nl;//家庭成员1年龄

	private String jtcy1_gzdz;//家庭成员1工作地址

	private String jtcy1_mz;//家庭成员1名族

	private String jtcy1_whcd;//家庭成员1文化程度

	private String jtcy1_lxdh1;//家庭成员1联系电话1

	private String jtcy1_lxdh2;//家庭成员1联系电话2

	private String jtcy1_lxdh3;//家庭成员1联系电话3

	private String jtcy1_yzbm;//家庭成员1邮政编码
	
	private String jtcy1_jtdz;//家庭成员1家庭地址

	private String kcjqgzxsj;		//可参加勤工助学时间
	
	private String jtcy2_xm;//家庭成员2姓名
	
	private String jtcy2_sfzh;//家庭成员2姓名

	private String jtcy2_zzmm;//家庭成员2政治面貌
	
	private String xssq;//学生申请理由

	private String jtcy2_ch;//家庭成员2称呼
	
	private String jtcy2_gx;//家庭成员2称呼

	private String jtcy2_zy;//家庭成员2职业

	private String jtcy2_zw;//家庭成员2职务

	private String jtcy2_nl;//家庭成员2年龄

	private String jtcy2_gzdz;//家庭成员2工作地址

	private String jtcy2_mz;//家庭成员2名族

	private String jtcy2_yzbm;//家庭成员2邮政编码

	private String jtcy2_lxdh1;//家庭成员2联系电话1

	private String jtcy2_lxdh2;//家庭成员2联系电话2

	private String jtcy2_lxdh3;//家庭成员2联系电话3

	private String jtcy2_whcd;
	
	private String jtcy2_jtdz;//家庭成员2家庭地址


	private String jtcy3_xm;//家庭成员3姓名
	
	private String jtcy3_sfzh;//家庭成员3姓名

	private String jtcy3_zzmm;//家庭成员3政治面貌

	private String jtcy3_ch;//家庭成员3称呼
	
	private String jtcy3_gx;//家庭成员3称呼

	private String jtcy3_zy;//家庭成员３职业

	private String jtcy3_zw;//家庭成员3职务

	private String jtcy3_nl;//家庭成员3年龄

	private String jtcy3_gzdz;//家庭成员3工作地址

	private String jtcy3_mz;//家庭成员3名族

	private String jtcy3_yzbm;//家庭成员3邮政编码

	private String jtcy3_lxdh1;//家庭成员3联系电话1

	private String jtcy3_lxdh2;//家庭成员3联系电话2

	private String jtcy3_lxdh3;//家庭成员3联系电话3

	private String jtcy3_whcd;
	
	private String jtcy3_jtdz;//家庭成员3家庭地址
	
	

	private String jtcy4_xm;//家庭成员4姓名

	private String jtcy4_zzmm;//家庭成员4政治面貌

	private String jtcy4_gx;//家庭成员4称呼

	private String jtcy4_zy;//家庭成员4职业

	private String jtcy4_zw;//家庭成员4职务

	private String jtcy4_nl;//家庭成员4年龄

	private String jtcy4_gzdz;//家庭成员4工作地址

	private String jtcy4_mz;//家庭成员4名族

	private String jtcy4_whcd;//家庭成员4文化程度

	private String jtcy4_lxdh1;//家庭成员4联系电话1

	private String jtcy4_lxdh2;//家庭成员4联系电话2

	private String jtcy5_xm;//家庭成员5姓名

	private String jtcy5_zzmm;//家庭成员5政治面貌

	private String jtcy5_gx;//家庭成员5称呼

	private String jtcy5_zy;//家庭成员5职业

	private String jtcy5_zw;//家庭成员5职务

	private String jtcy5_nl;//家庭成员5年龄

	private String jtcy5_gzdz;//家庭成员5工作地址

	private String jtcy5_mz;//家庭成员5名族

	private String jtcy5_whcd;//家庭成员5文化程度

	private String jtcy5_lxdh1;//家庭成员5联系电话1

	private String jtcy5_lxdh2;//家庭成员5联系电话2

	private String jtcy6_xm;//家庭成员6姓名

	private String jtcy6_zzmm;//家庭成员6政治面貌

	private String jtcy6_gx;//家庭成员6称呼

	private String jtcy6_zy;//家庭成员6职业

	private String jtcy6_zw;//家庭成员6职务

	private String jtcy6_nl;//家庭成员6年龄

	private String jtcy6_gzdz;//家庭成员6工作地址

	private String jtcy6_mz;//家庭成员6名族

	private String jtcy6_whcd;//家庭成员6文化程度

	private String jtcy6_lxdh1;//家庭成员6联系电话1

	private String jtcy6_lxdh2;//家庭成员6联系电话2
	
	private String jtcy1_cw ;//家庭成员1称谓
	
	private String jtcy2_cw ;//家庭成员2称谓
	
	private String jtcy3_cw ;//家庭成员3称谓
	
	private String jtcy4_cw ;//家庭成员4称谓
	
	private String  jtcy1_gzdwjzw;//家庭成员1工作单位及职务
	
	private String  jtcy2_gzdwjzw;//家庭成员2工作单位及职务
	
	private String  jtcy3_gzdwjzw;//家庭成员3工作单位及职务
	
	private String  jtcy4_gzdwjzw;//家庭成员4工作单位及职务
	
	private String jtcy1_nsr ;//家庭成员1年收入
	
	private String jtcy2_nsr ;//家庭成员2年收入
	
	private String jtcy3_nsr ;//家庭成员3年收入
	
	private String jtcy4_nsr ;//家庭成员4年收入
	
	private String jtcy1_lxr;//家庭成员1联系人 
	
	private String jtcy2_lxr;//家庭成员2联系人
	
	private String jtcy3_lxr;//家庭成员3联系人
	
	private String jtcy4_sfzh;//家庭成员4身份证号
	
	private String jtcy5_sfzh;//家庭成员5身份证号
	
	private String jtcy4_yzbm;//家庭成员4邮政编码
	
	private String jtcy5_yzbm;//家庭成员5邮政编码
	
	private String jtzyjjly ;//家庭主要经济来源

	private String shgxxm1;//社会关系姓名1

	private String shgxgx1;//与本人关系1

	private String shgxgzdw1;//工作单位1

	private String shgxzw1;//职务1

	private String shgxdwdh1;//单位电话1

	private String shgxsjhm1;//手机号码1

	private String shgxxm2;//社会关系姓名2

	private String shgxgx2;//与本人关系2

	private String shgxgzdw2;//工作单位2

	private String shgxzw2;//职务2

	private String shgxdwdh2;//单位电话2

	private String shgxsjhm2;//手机号码2
	
	private String qslb;//寝室类别
	
	private String jtcyxm;//家庭成员姓名
	
	private String xxfx;//学习方向
	
	private String jsr; //接收人
	
	private String wjm; //文件名
	
	/**广东女子***/
	private String rxrq;//入学日期
	
	private String fdyxm;//辅导员姓名
	
	private String gkcj;//高考成绩
	
	
	private String qqhm;//QQ号码
	private String zw;//职务
	private String jgshen;//籍贯省
	private String jgshi;//籍贯市
	private String jgxian;//籍贯县
	private String jgz;
	private String syshen;//生源省
	private String syshi;//生源市
	private String syxian;//生源县
	private String rxqxxshen;//入校前学校省
	private String rxqxxshi;//入校前学校市
	private String rxqxxxian;//入校前学校县
	private String jtdzshen;//入校前学校省
	private String jtdzshi;//入校前学校市
	private String jtdzxian;//入校前学校县
	private String mph;//门牌号
	private String rxqbyxx;//入校前毕业学校
	
	private String xwzsxxdz;//校外住宿详细地址
	private String jgs;//籍贯省	
	private String jgx;//籍贯县
	
	/**苏州工业园区职业技术学院**/	
	private String qsrq;//起始日期
	/**南昌大学科技学院**/	
	private String jtdzs;//家庭地址省
	
	private String jtdzx;//家庭地址县
	
	private String ssyq;//宿舍苑区
	
	private String ssld;//宿舍楼栋
	
	/**白云学院***/
	private String hkszd;//户口所在地
	
	private String hkxz;//户口性质
	
	private String kh;//银行卡号
	
	private String zyjb;//专业级别
	
	private String sfdb;//是否低保
	
	private String srly;//收入来源 
	
	private String jtzsr;//家庭总收入
	
	private String jtzsrd;//家庭总收入低
	
	private String jtzsrg;//家庭总收入高
	
	private String jtrjsr;//家庭人均收入 
	
	private String kncddm;//困难程度代码
	
	/****中州大学*****/
	
	private String sfzx;//是否在校
	private String sfyby;//是否毕业
	
	/********西北二民院********/
	private String zyfx;//专业方向
	
	private String pyfx;//培养方向
	
	private String xxszd;//学校所在地
	
	private String zylb;//专业类别
	
	private String fxzy;//辅修专业
	
	private String fxzyfx;//辅修专业方向
	
	private String bxxs;//办学形式
	
	private String bxlx;//办学类型
	
	private String xxxs;//学习形式
	
	private String zsjj;//招生季节
	
	private String csd;//出生地
	
	private String bjyjl;//毕结业结论
	
	private String zsbh;//证书编号
	
	private String zsxlh;//证书序列号
	
	private String xzxm;//校长姓名
	
	private String xw;//学位
	
	private String xwzsbh;//学位证书编号
	
	private String xwzsxlh;//学位证书序列号
	
	private String shbj;//审核标记
	
	private String dybj;//打印标记
	
	private String thbs;//替换标识
	
	/**井冈山大学*/	
	private String sfzsb;//是否专达本
	
	/** 武汉理工大学**/
	private String zslb;//招生类别
	
	private String gj;//国籍
	
	private String sfjh;//是否结婚
	
	private String ccqj;//乘车区间
	
	/***长沙民政****/
	private String byzffztdm;//毕业证发放状态代码
	
	/*******湖南工业大学**********/
	private String dasfyl;//档案是否遗留
	private String daylyy;//档案遗留原因
	
	/*******浙江工商职业技术学院****/
	private String yxdm;//院校代码
	private String xwdm;//学位代码
	private String sfzz;//是否在职
	private String sfsf;//是否师范
	private String sfdl;//是否独立
	private String dxhwp;//定向或委培
	private String bysj;//毕业时间
	private String zxwyyzdm;//主修外语语种
	private String wydj;//外语等级
	private String jsjdj;//计算机等级
//	private String yzbm;
	private String lxdz;
	private String shzw;//社会职务
	private String jypx;//教育培训
	private String xmsj;//项目实践
	private String zgzs;//资格证书
	private String jljn;//奖励技能
	private String sybz1;//生源备注1
	private String sybz2;//生源备注2
	private String sybz3;//生源备注3
	private String xldm;//学历代码
//	private String zslb;
	private String imgNj;
	private String imgXy;
	//勤工助学
	
	private String hgdm;
	private String hgsj;
	private String gwmc;//岗位名称
	
	private String gwxz;//岗位性质
	
	private String gwsl;//岗位数量
	
	private String ryyq;//人员要求
	
	private String gznr;//工作内容
	
	private String xyboy;//需要男生数
	
	private String xygirl;//需要女生数	
	
	private String gwtsyq;//岗位特殊要求
	
	private String sqdwyj;//申请单位意见
	
	private String sqdw;//申请单位
	
	private String hffy;//花费费用
	
	private String lpje;//理赔金额
	
	private String gzsj;//工作时间
	
	private String jcfs;//计酬方式
	
	private String jybcbz;//计酬标准
	
	private String gzkssj;//工作开始时间
	
	private String gzjssj;//工作结束时间
	
	private String fzr;//联系人
	
	private String dwdz;//单位地址
	
	private String gwsbsj;//岗位申报时间
	
	private String sqsj;//申请时间
	
	private String xyrs;//需要人数
	
	private String syknss;//使用困难生数
	
	private String xyknsrs;//需要困难生人数
	
	private String mssjd;//面试时间段
	
	private String msdd;//面试地点
	
	private String gzdd;//工作地点
	
	private String xyddm;//信用度代码
	
	private String rzyq_nj;//任职要求年级
	
	private String rzyq_xb;//任职要求性别
	
	private String rzyq_zy;//任职要求专业
	
	private String rzyq_wyyq;//任职外语要求
	
	private String rzyq_gzjn;//任职要求工作技能
	
	private String rzyq_qt;//任职要求其它
	
	private String gzmd;//工作目的
	
	private String gzyd;//工作要点
	
	private String gznd;//工作难点
	
	private String gzjj;//工作禁忌
	
	private String mtbzgz;//每天必做工作
	
	private String dqbzgz;//定期必做工作
	
	private String xueqi;//学期
	
	private String myzgsjfs;//每月最高时间方式
	
	private String xyyj;
	
	private String xxyj;
	
	private String xxsh;
	
	private String myzgbc;//每月最高报酬
	
	private String cjffsj ;//酬金发放时间 
	private String ffsjks;//发放时间开始
	private String ffsjjs;//发放时间结束
	private String bkkmsxz;//补考科目限制
	
	private String fflx;
	
	private String ffsjjg;
	
	/**家庭成员7姓名*/
	private String jtcy7_xm;
	/**家庭成员7关系*/
	private String jtcy7_gx;
	/**家庭成员7职业*/
	private String jtcy7_zy;
	/**家庭成员7工作地址*/
	private String jtcy7_gzdz;
	/**家庭成员7职务*/
	private String jtcy7_zw;
	/**家庭成员7联系电话1*/
	private String jtcy7_lxdh1;
	/**家庭成员7联系电话2*/
	private String jtcy7_lxdh2;
	/**家庭成员7政治面貌*/
	private String jtcy7_zzmm;
	/**家庭成员7年龄*/
	private String jtcy7_nl;
	/**家庭成员7民族*/
	private String jtcy7_mz;
	/**家庭成员7文化程度*/
	private String jtcy7_whcd;


	/**家庭成员8姓名*/
	private String jtcy8_xm;
	/**家庭成员8关系*/
	private String jtcy8_gx;
	/**家庭成员8职业*/
	private String jtcy8_zy;
	/**家庭成员8工作地址*/
	private String jtcy8_gzdz;
	/**家庭成员8职务*/
	private String jtcy8_zw;
	/**家庭成员8联系电话1*/
	private String jtcy8_lxdh1;
	/**家庭成员8联系电话2*/
	private String jtcy8_lxdh2;
	/**家庭成员8政治面貌*/
	private String jtcy8_zzmm;
	/**家庭成员8年龄*/
	private String jtcy8_nl;
	/**家庭成员8民族*/
	private String jtcy8_mz;
	/**家庭成员8文化程度*/
	private String jtcy8_whcd;


	/**家庭成员9姓名*/
	private String jtcy9_xm;
	/**家庭成员9关系*/
	private String jtcy9_gx;
	/**家庭成员9职业*/
	private String jtcy9_zy;
	/**家庭成员9工作地址*/
	private String jtcy9_gzdz;
	/**家庭成员9职务*/
	private String jtcy9_zw;
	/**家庭成员9联系电话1*/
	private String jtcy9_lxdh1;
	/**家庭成员9联系电话2*/
	private String jtcy9_lxdh2;
	/**家庭成员9政治面貌*/
	private String jtcy9_zzmm;
	/**家庭成员9年龄*/
	private String jtcy9_nl;
	/**家庭成员9民族*/
	private String jtcy9_mz;
	/**家庭成员9文化程度*/
	private String jtcy9_whcd;


	/**家庭成员10姓名*/
	private String jtcy10_xm;
	/**家庭成员10关系*/
	private String jtcy10_gx;
	/**家庭成员10职业*/
	private String jtcy10_zy;
	/**家庭成员10工作地址*/
	private String jtcy10_gzdz;
	/**家庭成员10职务*/
	private String jtcy10_zw;	
	/**家庭成员10联系电话1*/
	private String jtcy10_lxdh1;
	/**家庭成员10联系电话2*/
	private String jtcy10_lxdh2;
	/**家庭成员10政治面貌*/
	private String jtcy10_zzmm;
	/**家庭成员10年龄*/
	private String jtcy10_nl;
	/**家庭成员10民族*/
	private String jtcy10_mz;
	/**家庭成员10文化程度*/
	private String jtcy10_whcd;

	private String sj;	
	private String xxjl_nyr1;
	private String xxjl_nr1;
	private String xxjl_zw1;
	private String xxjl_zmr1;
	private String xxjl_nyr2;
	private String xxjl_nr2;
	private String xxjl_zw2;
	private String xxjl_zmr2;
	private String xxjl_nyr3;
	private String xxjl_nr3;
	private String xxjl_zw3;
	private String xxjl_zmr3;
	private String xxjl_nyr4;
	private String xxjl_nr4;
	private String xxjl_zw4;
	private String xxjl_zmr4;
	private String xxjl_nyr5;
	private String xxjl_nr5;
	private String xxjl_zw5;
	private String xxjl_zmr5;
    private String csjg;
	private String csnj;
	private String zc;
	private String cfdj;
	private String kf;
	private String wsjc;
	private String pbzq;
	//领取情况
	private String lqqk;
	
	//备注
	private String remark;
	private String bkqx;
	private String bynd;
	
	private String lx;
	private String lb;
	private String bmbynd;
	
	private String xslb;
	private String zyshgxxx3;
	private String zyshgxxx2;
	private String zyshgxxx1;
	private String xsczqk;
	private String yqljlx;
	private String sfsq;
	
	private String lxcksj;
	private String ycflb;
	private String sfjw;
	private String xlcslb;
	private String xlwtlx;
	private String sfkns;
	private String sfdq;
	
	private String dwlxr;
	private String dajsddwmc;
	private String dwgm;
	private String dwzczj;
	private String sh;
	private String xsqr;
	private String qrsj;
	private String ydlbdm;//异动类别代码
	
	//南京技师 学生信息
	private String hkshen;//户口所在省
	private String hkshi;//户口所在市
	private String hkxian;//户口所在县
	private String rxqwhcd;//入学前文化程度
	private String zcsxhm;//'注册顺序号码';
	
	//浙江理工
	private String dwlbdm;//队伍类别代码;
	
	// 中国地大
	private String dj;// 卫生检查等级

	private String lbmc;// 类别名称
	
	//用户权限
	private String purview;
	
	private String tabname;     //表名
	
	private String nspName;    //新闻模块名
	
	/*南京技师保险*/
	private String save_dm;
	private String save_mc;
	private String querylike_dm;
	private String querylike_mc;
	private String save_lpxmdm;
	private String queryequals_lpxmdm;
	private String save_lpxm;
	private String save_lpcl;
	private String save_jtsm;
	private String queryequals_nj;
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_bjdm;
	private String queryequals_lpxm;
	private String queryequals_shjg;
	private String queryequals_cs;
	private String queryequals_lddm;
	private String queryequals_qsh;
	private String queryequals_xqdm;
	private String queryequals_sfqsz;
	private String queryequals_xb;
	private String sfyh;
	
	private String bkbysj;//本科生毕业时间
	private String yjsbysj;//研究生毕业时间
	private String yjsbyzy;//研究生毕业专业
	private String yjsbyyx;//研究生毕业院校
	private String sdxsqk;//所带学生情况
	private String xhfz;//辅导员协会分组
	private String ndkhy;//年度考核为优
	private String zzdy;//政治待遇;
	private String urls;//左菜单URL的保存
	private String sfzd;//是否走读
	
	 
	private String xssx;//显示顺序
	private String picid;//图片ID
	private String[] xssxArr;//显示顺序数组
	private String[] picArr;//图片ID数组
	private String puber;//发布人
	
	// ============学生身体信息============
	private String xzcm;
	private String xsxw;
	
	private String save_xzcm;
	private String save_xsxw;
    // =============学生身体信息end=================
	
	private String cfkssj;//处分开始时间
	
	private String cfjssj;//处分结束时间
	
	private String byzh;//毕业证号
	
	private String gzkssj_ks;
	
	private String gzkssj_js;
	
	private String gzjssj_ks;
	
	private String gzjssj_js;
	
	
	private String qsxn;
	
	private String zzxn;
	
	private String lydm;//来源代码
	
	private String yjzt;//预警状态【杭州师范大学】

	
	/**
	 * @return the yjzt
	 */
	public String getYjzt() {
		return yjzt;
	}

	/**
	 * @param yjzt要设置的 yjzt
	 */
	public void setYjzt(String yjzt) {
		this.yjzt = yjzt;
	}

	public String getLydm() {
		return lydm;
	}

	public void setLydm(String lydm) {
		this.lydm = lydm;
	}

	public String getByzh() {
		return byzh;
	}

	public void setByzh(String byzh) {
		this.byzh = byzh;
	}

	public String getPuber() {
		return puber;
	}

	public void setPuber(String puber) {
		this.puber = puber;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getBkbysj() {
		return bkbysj;
	}

	public void setBkbysj(String bkbysj) {
		this.bkbysj = bkbysj;
	}

	public String getNdkhy() {
		return ndkhy;
	}

	public void setNdkhy(String ndkhy) {
		this.ndkhy = ndkhy;
	}

	public String getSdxsqk() {
		return sdxsqk;
	}

	public void setSdxsqk(String sdxsqk) {
		this.sdxsqk = sdxsqk;
	}

	public String getXhfz() {
		return xhfz;
	}

	public void setXhfz(String xhfz) {
		this.xhfz = xhfz;
	}

	public String getYjsbysj() {
		return yjsbysj;
	}

	public void setYjsbysj(String yjsbysj) {
		this.yjsbysj = yjsbysj;
	}

	public String getYjsbyyx() {
		return yjsbyyx;
	}

	public void setYjsbyyx(String yjsbyyx) {
		this.yjsbyyx = yjsbyyx;
	}

	public String getYjsbyzy() {
		return yjsbyzy;
	}

	public void setYjsbyzy(String yjsbyzy) {
		this.yjsbyzy = yjsbyzy;
	}

	public String getZzdy() {
		return zzdy;
	}

	public void setZzdy(String zzdy) {
		this.zzdy = zzdy;
	}

	public String getSfyh() {
		return sfyh;
	}

	public void setSfyh(String sfyh) {
		this.sfyh = sfyh;
	}

	public String getQueryequals_cs() {
		return queryequals_cs;
	}

	public void setQueryequals_cs(String queryequals_cs) {
		this.queryequals_cs = queryequals_cs;
	}

	public String getQueryequals_lddm() {
		return queryequals_lddm;
	}

	public void setQueryequals_lddm(String queryequals_lddm) {
		this.queryequals_lddm = queryequals_lddm;
	}

	public String getQueryequals_qsh() {
		return queryequals_qsh;
	}

	public void setQueryequals_qsh(String queryequals_qsh) {
		this.queryequals_qsh = queryequals_qsh;
	}

	public String getQueryequals_sfqsz() {
		return queryequals_sfqsz;
	}

	public void setQueryequals_sfqsz(String queryequals_sfqsz) {
		this.queryequals_sfqsz = queryequals_sfqsz;
	}

	public String getQueryequals_xb() {
		return queryequals_xb;
	}

	public void setQueryequals_xb(String queryequals_xb) {
		this.queryequals_xb = queryequals_xb;
	}

	public String getQueryequals_xqdm() {
		return queryequals_xqdm;
	}

	public void setQueryequals_xqdm(String queryequals_xqdm) {
		this.queryequals_xqdm = queryequals_xqdm;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_lpxm() {
		return queryequals_lpxm;
	}

	public void setQueryequals_lpxm(String queryequals_lpxm) {
		this.queryequals_lpxm = queryequals_lpxm;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}

	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}

	public String getQuerylike_xh() {
		return querylike_xh;
	}

	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}

	public String getQuerylike_xm() {
		return querylike_xm;
	}

	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}

	public String getSave_jtsm() {
		return save_jtsm;
	}

	public void setSave_jtsm(String save_jtsm) {
		this.save_jtsm = save_jtsm;
	}

	public String getSave_lpcl() {
		return save_lpcl;
	}

	public void setSave_lpcl(String save_lpcl) {
		this.save_lpcl = save_lpcl;
	}

	public String getSave_lpxm() {
		return save_lpxm;
	}

	public void setSave_lpxm(String save_lpxm) {
		this.save_lpxm = save_lpxm;
	}

	public String getQueryequals_lpxmdm() {
		return queryequals_lpxmdm;
	}

	public void setQueryequals_lpxmdm(String queryequals_lpxmdm) {
		this.queryequals_lpxmdm = queryequals_lpxmdm;
	}

	public String getQuerylike_dm() {
		return querylike_dm;
	}

	public void setQuerylike_dm(String querylike_dm) {
		this.querylike_dm = querylike_dm;
	}

	public String getQuerylike_mc() {
		return querylike_mc;
	}

	public void setQuerylike_mc(String querylike_mc) {
		this.querylike_mc = querylike_mc;
	}

	public String getSave_dm() {
		return save_dm;
	}

	public void setSave_dm(String save_dm) {
		this.save_dm = save_dm;
	}

	public String getSave_lpxmdm() {
		return save_lpxmdm;
	}

	public void setSave_lpxmdm(String save_lpxmdm) {
		this.save_lpxmdm = save_lpxmdm;
	}

	public String getSave_mc() {
		return save_mc;
	}

	public void setSave_mc(String save_mc) {
		this.save_mc = save_mc;
	}

	public String getBkkmsxz() {
		return bkkmsxz;
	}

	public void setBkkmsxz(String bkkmsxz) {
		this.bkkmsxz = bkkmsxz;
	}

	public String getYdlbdm() {
		return ydlbdm;
	}

	public void setYdlbdm(String ydlbdm) {
		this.ydlbdm = ydlbdm;
	}

	public String getTabname() {
		return tabname;
	}

	public void setTabname(String tabname) {
		this.tabname = tabname;
	}

	public String getFfsjks() {
		return ffsjks;
	}

	public void setFfsjks(String ffsjks) {
		this.ffsjks = ffsjks;
	}

	public String getFfsjjs() {
		return ffsjjs;
	}

	public void setFfsjjs(String ffsjjs) {
		this.ffsjjs = ffsjjs;
	}

	public String getSfjw() {
		return sfjw;
	}

	public void setSfjw(String sfjw) {
		this.sfjw = sfjw;
	}

	public String getYcflb() {
		return ycflb;
	}

	public void setYcflb(String ycflb) {
		this.ycflb = ycflb;
	}

	public String getZyshgxxx3() {
		return zyshgxxx3;
	}

	public void setZyshgxxx3(String zyshgxxx3) {
		this.zyshgxxx3 = zyshgxxx3;
	}

	public String getZyshgxxx2() {
		return zyshgxxx2;
	}

	public void setZyshgxxx2(String zyshgxxx2) {
		this.zyshgxxx2 = zyshgxxx2;
	}

	public String getZyshgxxx1() {
		return zyshgxxx1;
	}

	public void setZyshgxxx1(String zyshgxxx1) {
		this.zyshgxxx1 = zyshgxxx1;
	}

	public String getBmbynd() {
		return bmbynd;
	}

	public void setBmbynd(String bmbynd) {
		this.bmbynd = bmbynd;
	}
	public String getBynd() {
		return bynd;
	}

	public void setBynd(String bynd) {
		this.bynd = bynd;
	}

	public String getBkqx() {
		return bkqx;
	}

	public void setBkqx(String bkqx) {
		this.bkqx = bkqx;
	}

	public String getLqqk() {
		return lqqk;
	}

	public void setLqqk(String lqqk) {
		this.lqqk = lqqk;
	}

	public String getKf() {
		return kf;
	}

	public void setKf(String kf) {
		this.kf = kf;
	}

	public String getCfdj() {
		return cfdj;
	}

	public void setCfdj(String cfdj) {
		this.cfdj = cfdj;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String getCsnj() {
		return csnj;
	}

	public void setCsnj(String csnj) {
		this.csnj = csnj;
	}

	public String getCsjg() {
		return csjg;
	}

	public void setCsjg(String csjg) {
		this.csjg = csjg;
	}
	private String nf;
	
	private String znszcj;//智能素质总积分
	
	private String ffsj;//发放时间

	private String userName;
	private String[] stuNum;

	private String password;

	private String userType;

	private String[] errMsgs;

	private String errMsg;

	private String sfzh;

	private String htjbjrjg;

	private String htfzjgmc;

	private String htpzrq;

	private String htzje;

	private String htjby;

	private String zymc;

	private String xymc;

	private String htpzhz;

	private String hthkksrq;

	private String hthkjzrq;

	private String htzqly;

	private String htzqsj;

	private String htdkfs;

	private String hthkfs;

	private String hthkjzmc;

	private String hthkjzzh;

	private String httqyhbxje;

	private String httqjzsj;

	private String ksh;

	private String mzmc;

	private String xmc;

	private String csrq;

	private String xz;

	private String rxny;

	private String byny;

	private String xslx;

	private String yzbm;

	private String sedh;

	private String dkze;

	private String xfdks;

	private String shfdks;

	private String zsfdks;

	private String yhdkje;

	private String scbyqx;

	private String yxlxfs;

	private String dqgzdw;

	private String dqgzdwdz;

	private String dqgzdwyb;

	private String dqgzdwdh;

	private String jtzz;

	private String jtsr;

	private String fqxm;

	private String fqlxdh;

	private String fqgzdw;

	private String fqsfzh;

	private String mqxm;

	private String mqlxdh;

	private String mqgzdw;

	private String mqsfzh;

	private String hth1;

	private String ht1_jbjrjg;

	private String ht1_fzjgmc;

	private String ht1_pzrq;

	private String ht1_zje;

	private String ht1_jby;

	private String ht1_pzhz;

	private String ht1_hkksrq;

	private String ht1_hkjzrq;

	private String ht1_zqsj;

	private String ht1_zqly;

	private String ht1_dkfs;

	private String ht1_hkfs;

	private String ht1_hkjzmc;

	private String ht1_hkjzzh;

	private String ht1_tqyhbxje;

	private String ht1_tqjzsj;

	private String ht1_tqbz;

	private String hth2;

	private String ht2_jbjrjg;

	private String ht2_fzjgmc;

	private String ht2_pzrq;

	private String ht2_zje;

	private String ht2_jby;

	private String ht2_pzhz;

	private String ht2_hkksrq;

	private String ht2_hkjzrq;

	private String ht2_zqsj;

	private String ht2_zqly;

	private String ht2_dkfs;

	private String ht2_hkfs;

	private String ht2_hkjzmc;

	private String ht2_hkjzzh;

	private String ht2_tqyhbxje;

	private String ht2_tqjzsj;

	private String ht2_tqbz;

	private String hth3;

	private String ht3_jbjrjg;

	private String ht3_fzjgmc;

	private String ht3_pzrq;

	private String ht3_zje;

	private String ht3_jby;

	private String ht3_pzhz;

	private String ht3_hkksrq;

	private String ht3_hkjzrq;

	private String ht3_zqsj;

	private String ht3_zqly;

	private String ht3_dkfs;

	private String ht3_hkfs;

	private String ht3_hkjzmc;

	private String ht3_hkjzzh;

	private String ht3_tqyhbxje;

	private String ht3_tqjzsj;

	private String ht3_tqbz;

	private String hth4;

	private String ht4_jbjrjg;

	private String ht4_fzjgmc;

	private String ht4_pzrq;

	private String ht4_zje;

	private String ht4_jby;

	private String ht4_pzhz;

	private String ht4_hkksrq;

	private String ht4_hkjzrq;

	private String ht4_zqsj;

	private String ht4_zqly;

	private String ht4_dkfs;

	private String ht4_hkfs;

	private String ht4_hkjzmc;

	private String ht4_hkjzzh;

	private String ht4_tqyhbxje;

	private String ht4_tqjzsj;

	private String ht4_tqbz;

	private String httqbz;

	private String lxyy;

	private String dkll;

	private String dklb;

	private String yzm;

	private String groupName;

	private String displayGroupPower;

	private String displaySubPower;

	private String zmc;

	private String zdm;

	private String gnmkmc;

	private String gnmkdm;

	private String partList;

	private String userPower;
	
	private String groupList;

	private String codeType;

	private String codeDm;

	private String codeMc;

	private String rowNum;

	private String codeLb;

	private String codeJb;

	private String tName;

	private String tips;

	private String[] newCode;

	private String yhm;

	private String dateF;

	private String dateT;

	private String ipF;

	private String ipT;

	private String nj;

	private String xn;

	private String nd;

	private String xq;

	private String xydm;

	private String zydm;

	private String bjdm;

	private String xmdm;

	private String xmmc;

	private String xh;

	private String xm;

	private String yesNo;

	private String yesNoEnd;

	private String itemStr;

	private int itemNum;

	private String oracleItem;

	private String tabName;

	private String cols;

	private FormFile file;

	private FormFile file2;

	private String changed;

	private String tjxm;

	private String jtxm;

	private String zxm;

	private String tjtjz;

	private String titList;

	private String conditionSqlText;

	private String conditionSqlValue;
	
	private String oldCondiSqlValue;

	private String tjxmdyb;

	private String sql;

	private String xy;

	private String zy;

	private String dqszj;

	private String tjdw;

	private String jllb;

	private String jlfs;

	private String insertFlag;

	private String jltj;

	private String jlxx;

	private String qwdq;

	private String jlqx;

	private String pcsj;

	private String zgje;

	private String jzrq;

	private String zxfh;

	private String zxfx;

	private String jfxcm;

	private String zxmxh;

	private String whsxh;

	private String cxbm;

	private String act;

	private String[] njL;

	private String xqdm;

	private String sjlsh;

	private String stxh;

	private String stlxdm;

	private String stndjbdm;
	
	private String bzffny;

	private String stlsh;

	private String yxstlb;

	private String dispFlag;

	private String lddm;

	private String qsh;

	private String sslx;//宿舍类型
	
	private String cws;

	private String realTable;

	private String xxbz;

	private String xxbzfx;

	private String jxbz;

	private String jxbzfx;

	private String excelItem;

	private String xb="1";

	private String count;

	private String boy;

	private String girl;

	private String[] sfcp;

	private String[] qtdm;

	private String[] pfbl;

	private String cc;

	private String qdz;

	private String hth;

	private String zdz;

	private String tkz;

	private String dph;

	private String yrdwdm;
	
	private String yrdwmc;//用人单位名称

	private String hblb;

	private String gwdm;

	private String yf;
	
	private String rq;

	private String knsbl;

	private String ndfw ;

	private String zxjxjdm;

	private String shxm;

	private String zxjdm;//外设助学金代码

	private String zbrydm;

	private String bmdm;

	private String syrq;

	private String lpsj;

	private String dxzsx;

	private String[] rscb;

	private String bxsj;

	private String wxsj;   

	private String rydm;

	private String jcsj;

	private String jcbm;

	private String wjsj;

	private String dtsj;

	private String ssbh;   

	private String dektxf;//第二课堂学分

	private String nsepjbdm;//NSEP级别代码

	private String ydsj;

	private String qflxdm; 

	private String hzr;//公寓一周值班汇总人

	private String jxjdm;

	private String rychdm;

	private String jg;//籍贯

	private String rxsj;

	private String jtdz;

	private String sqly;

	private String lxfs;

	private String zjh ;

	private String gsqk;

	private String blqk;

	private String slr ;

	private String slrgh;

	private String gzbc ;

	private String slbh ;

	private String bbfys;

	private String xkbl ;

	private String xxyhd;

	private String yclhzjxgbm;

	private String bjf;        

	private String yy;       

	private String dlr;  

	private String dlrzjh;

	private String bz;

	private String hczdz;

	private String hdzj;

	private String yzxtzhd;

	private String ff;

	private String qt;

	private String yktbz;

	private String hcpbz;

	private String bbyy;

	private String xbyy;

	private String grhj;

	private String ydqssbh;

	private String ydqcwh;

	private String ydqrzsj;

	private String xydybl;

	private String gydybl;

	private String zybl;

	private String tybl;

	private String xxcxbl;

	private String xydybl1;

	private String gydybl1;

	private String zybl1;

	private String tybl1;

	private String xxcxbl1;

	private String pysj;

	private String cwfp;//床位分配

	private String sbsj;

	private String zbrlx;//（公寓管理）值班人员类型
	
	/** mc property */
	private String mc;    //代码名称

	/** sjzd property */
	private String sjzd;  //数据字典

	/** dm property */
	private String dm;    //代码

	private String shzt;

	private String bxxzdm;

	private String cflb;

	private String unitList;

	private String doType;

	private String bzlxdm;

	private String bzrq;

	private String bzrq1;

	private String bzrq2;

	private String bzrqt;

	private String bzrq4;

	private String xydm1;

	private String xydmt;

	private String mappingItem;

	private String cs;

	private String fzrq;

	private String bfrq;

	private String yxrq;
	
	private String mxsbc;//每小时报酬
	
	private String mtzgxs;//每天最高小时
	
	private String myzgxs;//每月最高小时
	
	private String sbts;//中国地质大学勤工助学参数设置上报提示
	
	private String xg;//性格
	
	private String zzqk;//资助情况
	
	private String gjzxdk;//国家助学贷款
	
	private String jtysr;//家庭月收入
	
	private String yhtc;//有何特长
	
	private String gzjl;//工作经历
	
	private String ldyx;//劳动意向
	
	private String pkdj;//贫困等级
	
	private String jtcy;//家庭成员信息
	
	private String nr;
	
	private String qk;
	
	private String jjf;
	
	private String fpfs = "ass";
	
	private String xxqdm;//校区代码
	
	private String sfzblx;//是否征兵离校  //北京市工贸技师学院
	
	public String getSfzblx() {
		return sfzblx;
	}

	public void setSfzblx(String sfzblx) {
		this.sfzblx = sfzblx;
	}


	private String tsgw;
	
	private String cxfs;

	private String lrr;//录入人
	private String lrsj;//录入时间
	
	private String kxf1;
	private String kxf2;
	private String kxf3;
	private String kxf4;
	private String kxf5;
	private String kxf6;
	private String kxf7;
	private String lc;
	
	private String yqljlxxn;
	private String yqljlxgx;
	private String yqljlxgn;
	private String yqljlxjy;
	
	private String dz;
	
	private String ccrq;
	
	private String bxcc;//不限车次
	
	private String ksy;//可顺延
	 
	private String klc;//可联程
	
	private String kwz;//可无座
	
	private String kxdyt;
	
	private String xqzs;
	
	private String xb1;//学生性别	
	
	private String xdwyj;//校党委意见
	
	private String xzyj;//校长意见
	
	private String pjf;//平均分
	
	private String bjbl;//班级比例
	private String cxjg;//撤消结果
	private String pubTime;
	private String cfsj;
	private String cfwh;
	private String cfyy;
	private String wmqsbz;//文明寝室比重
	private String jlqk;//奖励情况
	private String cxcj;//操行成绩
	
	private String wxzt;//维修状态
	private String sfbd;//是否宿舍报到模式
	
	private String rychlb;//荣誉称号类别
	private String jxjdmlb;//奖学金代码类别
	private String jxjfl;//奖学金分类
	private String jxjlb;
	
	private String gzs;//工作室(公寓管理）
	
	private String yqdm;//园区代码
	
	private String xsccdm;//党团建设　学生层次
	private String gjz;//关键字
	private String find;//
	private String sfzc;
	private String gsmc;
	
	private String szmc1;
	private String szcj1;
	private String szmc2;
	private String szcj2;
	private String szmc3;
	private String szcj3;
	private String szmc4;
	private String szcj4;
	private String szmc5;
	private String szcj5;
	private String zhszcpcj;
	private String zhszcpcjpm;
	private String wjlbdm;
	private String wxnr;
	private String fdyyj;
	private String hyfl;
	
	private String colName;
	
	private String sql1;
	
	private String xbcx;
	private String dwxx;
	private String xyjbqk;
	private String pymb;
	private String zykc;
	private String jyqj;
	private String zplx;
	private String zpcs;
	private String dwsl;
	private String ky;
	private String bkgwy;
	private String szyf;
	private String xbjh;
	private String zgz;
	private String zzcy;
	private String gjj;
	private String bjs;
	private String dfss;
	private String wtqt;
	
	private String jxnd;
	
	//学风建设
	private String jclxdm;
	private String wjlxdm;

	private String cfnx;//处分处限
	private String xftz;
	private String sbsy;
	private String sfjy;
	private String sfqy;
	private String xmlx;
	private String rowspan;
	private String newsId;
	
	// --------------是否签收------------------
	private String sfqs;//是否签收
	
	private String wtid;
	
	/**
	 * @return the wtid
	 */
	public String getWtid() {
		return wtid;
	}

	/**
	 * @param wtid要设置的 wtid
	 */
	public void setWtid(String wtid) {
		this.wtid = wtid;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getJclxdm() {
		return jclxdm;
	}

	public void setJclxdm(String jclxdm) {
		this.jclxdm = jclxdm;
	}

	public String getWjlxdm() {
		return wjlxdm;
	}

	public void setWjlxdm(String wjlxdm) {
		this.wjlxdm = wjlxdm;
	}

	public String getGjj() {
		return gjj;
	}

	public void setGjj(String gjj) {
		this.gjj = gjj;
	}

	public String getBjs() {
		return bjs;
	}

	public void setBjs(String bjs) {
		this.bjs = bjs;
	}

	public String getDfss() {
		return dfss;
	}

	public void setDfss(String dfss) {
		this.dfss = dfss;
	}

	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}
	private String wd;
	private String bj;
	public String getKy() {
		return ky;
	}

	public void setKy(String ky) {
		this.ky = ky;
	}

	public String getBkgwy() {
		return bkgwy;
	}

	public void setBkgwy(String bkgwy) {
		this.bkgwy = bkgwy;
	}

	public String getSzyf() {
		return szyf;
	}

	public void setSzyf(String szyf) {
		this.szyf = szyf;
	}

	public String getXbjh() {
		return xbjh;
	}

	public void setXbjh(String xbjh) {
		this.xbjh = xbjh;
	}

	public String getZgz() {
		return zgz;
	}

	public void setZgz(String zgz) {
		this.zgz = zgz;
	}

	public String getZzcy() {
		return zzcy;
	}

	public void setZzcy(String zzcy) {
		this.zzcy = zzcy;
	}

	public String getZplx() {
		return zplx;
	}

	public void setZplx(String zplx) {
		this.zplx = zplx;
	}

	public String getZpcs() {
		return zpcs;
	}

	public void setZpcs(String zpcs) {
		this.zpcs = zpcs;
	}

	public String getDwsl() {
		return dwsl;
	}

	public void setDwsl(String dwsl) {
		this.dwsl = dwsl;
	}

	public String getZpsj() {
		return zpsj;
	}

	public void setZpsj(String zpsj) {
		this.zpsj = zpsj;
	}
	private String zpsj;

	
	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getXyjbqk() {
		return xyjbqk;
	}

	public void setXyjbqk(String xyjbqk) {
		this.xyjbqk = xyjbqk;
	}

	public String getPymb() {
		return pymb;
	}

	public void setPymb(String pymb) {
		this.pymb = pymb;
	}

	public String getZykc() {
		return zykc;
	}

	public void setZykc(String zykc) {
		this.zykc = zykc;
	}

	public String getJyqj() {
		return jyqj;
	}

	public void setJyqj(String jyqj) {
		this.jyqj = jyqj;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDwxx() {
		return dwxx;
	}

	public void setDwxx(String dwxx) {
		this.dwxx = dwxx;
	}

	public String getXbcx() {
		return xbcx;
	}

	public void setXbcx(String xbcx) {
		this.xbcx = xbcx;
	}
	//职务职称：
	private String szdw;
	//职务职称
	private String zwzc;
	private String dd;
	
	//浙江传媒 家庭意见
	private String jtyj;
	
	// 中国矿业大学
	// 确定为入党积极分子时间
	private String rdjjfzsj;

	// 入党积极分子培训结业证书编号
	private String rdjjfzpxbh;

	// 确定为发展对象时间
	private String fzdxsj;

	// 发展对象培训结业证书编号
	private String fzdxpxbh;

	// 书面思想汇报时间
	private String smhbsj;

	// 思想汇报次数
	private String hbcs;
	// 思想汇报次数
	private String pxlx;

	//处分依据
	private String cfyj;
	
	private String ywss;
	
	private String ywcx;
	private String dwmc; // 单位名称
	private String dwdh; // 单位电话
	private String dwcz; // 单位传真
	private String dwyx; // 单位邮箱
	private String dwyb; // 单位邮编
	private String bm; // 部门
	private String gddh; // 固定电话
	private String yddh; // 移动电话
	private String lfsy; // 来访事宜
	private String ytdd; // 会谈地点
	private String rs1; // 人数
	private String jddd; // 接待地点
	private String jdcyr1; // 接待参与人1
	private String jdcyr2; // 接待参与人2
	private String jdcyr3; // 接待参与人3
	private String jdcyr4; // 接待参与人4
	private String dwbh; // 编号
	private String xysbh; //协议书编号
	private String wpdw; // 委培单位
	private String zsmc; // 招生名册
	private String zdcl; // 自动处理
	private String bdzbz; // 报到证备注
	private String sjdw; // 实际单位
	private String pqdw; // 派遣单位
	private String pqdwid; //派遣单位id
	private String id;
	private String jyzk;
	private String dwxz1; // 单位性质
	private String dwxz2; // 单位性质
	private String dwxzmc; // 单位性质名称
	private String zgbm1; // 主管部门
	private String zgbm2; // 主管部门
	private String zgbmmc; // 主管部门
	private String dwszd1; // 单位所在地
	private String dwszd2; // 单位所在地市
	private String dwszd3; // 单位所在地
	private String sjszd1; // 实际所在地3
	private String sjszd2; // 实际所在地4
	private String sjszd3; // 实际所在地
	private String hkdz; // 户口地质
	private String gprq; // 改派日期
	private String gpcs; // 改派次数
	private String gpyy; // 改派原因
	private String ydwmc; // 原单位名称
	private String bdzbh; // 报道证编号
	private String qq;
	private String byqx;
	private String jgshi2;
	private String jgshi3;
	private String xlmc;
	private String fdyQx;
	private String ccjydw;
	private String xgzdw;
	private String xgwjrzqk;
	private String yb;
	private String zydkcd;
	private String gwsyzt;
	private String gwmycd;
	private String gzbdqk;
	private String hdhzjxhbz;
	private String slkc;
	private String rwshkc;
	private String wykc;
	private String jsjkc;
	private String synl;
	private String kynl;
	private String sjdsnl;
	private String dlgznl;
	private String zzglnl;
	private String zwhqzsnl;
	private String wzbdnl;
	private String ktbdnl;
	private String shggnl;
	private String sykc;
	private String qqkc;
	
	//一到五年级奖惩任职情况
	private String yqk;
	private String rqk;
	private String sqk;
	private String xqk;
	private String fqk;
	private String rxnj;
	//就业协议编号
	private String jyxybh;
	
	private String  dwid;//表id
	
	private String xsxh;
	
	private String cxbb;
	private String yx;//月旬
	
		
	private String num;
	private String name;
	private String age;
	private String Userid;
	private String zxszg;
	private String zxsjj;
	private String Readtimes ;
	
	private String byqxdm;
	private String sydmc;
	private String xyclyj;
	private String newsTitle;//新闻标题
	private String shzh; 
	private String xl; 
	private String syszd; 
	private String sfpks;
	private String bmxm;
	private String gzjcs;
	private String zywt;
	private String jyxsfx;
	private String newsType;//新闻类型
	
	//北京联合
	private String kfpcws;//可分配床位数
	private String xlcws;//行李床位数
	
	private String bdkssj;
	private String bdjssj;
	private String bdzh;
	
	//宁波天一奖学金
	private String dykhdj; //德育考核等级
	private String xstzjkbz; //学生体质健康标准
	
	private String xfjdcj;//学分绩点平均成绩
	
	private String cssj;//测试时间
	
	private String zgh; //职工号
	private String kssj;//开始时间
	private String jssj;//结束时间
	
	//广东体育职业技术学院
	private String xjh;//学籍号
	private String jrzzmmrq;//加入政治面貌日期
	private String sfhq;//是否华侨
	private String csds;//出生地省
	private String csdshi;//出生地市
	private String csdxian;//出生地县
	
	
	public String getCsds() {
		return csds;
	}

	public void setCsds(String csds) {
		this.csds = csds;
	}

	public String getCsdshi() {
		return csdshi;
	}

	public void setCsdshi(String csdshi) {
		this.csdshi = csdshi;
	}

	public String getCsdxian() {
		return csdxian;
	}

	public void setCsdxian(String csdxian) {
		this.csdxian = csdxian;
	}

	public String getJrzzmmrq() {
		return jrzzmmrq;
	}

	public void setJrzzmmrq(String jrzzmmrq) {
		this.jrzzmmrq = jrzzmmrq;
	}

	public String getSfhq() {
		return sfhq;
	}

	public void setSfhq(String sfhq) {
		this.sfhq = sfhq;
	}

	public String getXjh() {
		return xjh;
	}

	public void setXjh(String xjh) {
		this.xjh = xjh;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getCssj() {
		return cssj;
	}

	public void setCssj(String cssj) {
		this.cssj = cssj;
	}

	public String getXfjdcj() {
		return xfjdcj;
	}

	public void setXfjdcj(String xfjdcj) {
		this.xfjdcj = xfjdcj;
	}

	public String getDykhdj() {
		return dykhdj;
	}

	public void setDykhdj(String dykhdj) {
		this.dykhdj = dykhdj;
	}

	public String getXstzjkbz() {
		return xstzjkbz;
	}

	public void setXstzjkbz(String xstzjkbz) {
		this.xstzjkbz = xstzjkbz;
	}

	public String getBdkssj() {
		return bdkssj;
	}

	public void setBdkssj(String bdkssj) {
		this.bdkssj = bdkssj;
	}

	public String getBdjssj() {
		return bdjssj;
	}

	public void setBdjssj(String bdjssj) {
		this.bdjssj = bdjssj;
	}

	public String getBdzh() {
		return bdzh;
	}

	public void setBdzh(String bdzh) {
		this.bdzh = bdzh;
	}

	public String getSfyx() {
		return sfyx;
	}
	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}
	public String getXlcws() {
		return xlcws;
	}
	public void setXlcws(String xlcws) {
		this.xlcws = xlcws;
	}
	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getGzjcs() {
		return gzjcs;
	}

	public void setGzjcs(String gzjcs) {
		this.gzjcs = gzjcs;
	}

	public String getZywt() {
		return zywt;
	}

	public void setZywt(String zywt) {
		this.zywt = zywt;
	}

	public String getJyxsfx() {
		return jyxsfx;
	}

	public void setJyxsfx(String jyxsfx) {
		this.jyxsfx = jyxsfx;
	}

	public String getTbsm() {
		return tbsm;
	}

	public void setTbsm(String tbsm) {
		this.tbsm = tbsm;
	}
	private String tbsm;
	
	public String getBmxm() {
		return bmxm;
	}

	public void setBmxm(String bmxm) {
		this.bmxm = bmxm;
	}

	public String getSydmc() {
		return sydmc;
	}

	public String getShzh() {
		return shzh;
	}

	public void setShzh(String shzh) {
		this.shzh = shzh;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getSyszd() {
		return syszd;
	}

	public void setSyszd(String syszd) {
		this.syszd = syszd;
	}

	public String getSfpks() {
		return sfpks;
	}

	public void setSfpks(String sfpks) {
		this.sfpks = sfpks;
	}

	public void setSydmc(String sydmc) {
		this.sydmc = sydmc;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getUserid() {
		return Userid;
	}

	public void setUserid(String userid) {
		Userid = userid;
	}

	public String getZxszg() {
		return zxszg;
	}

	public void setZxszg(String zxszg) {
		this.zxszg = zxszg;
	}

	public String getZxsjj() {
		return zxsjj;
	}

	public void setZxsjj(String zxsjj) {
		this.zxsjj = zxsjj;
	}

	public String getReadtimes() {
		return Readtimes;
	}

	public void setReadtimes(String readtimes) {
		Readtimes = readtimes;
	}

	public String getByqxdm() {
		return byqxdm;
	}

	public void setByqxdm(String byqxdm) {
		this.byqxdm = byqxdm;
	}
	
	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}

	public String getCxbb() {
		return cxbb;
	}

	public void setCxbb(String cxbb) {
		this.cxbb = cxbb;
	}

	public String getXsxh() {
		return xsxh;
	}

	public void setXsxh(String xsxh) {
		this.xsxh = xsxh;
	}

	public String getDwid() {
		return dwid;
	}

	public void setDwid(String dwid) {
		this.dwid = dwid;
	}

	public String getDwxz() {
		return dwxz;
	}

	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}

	public String getDwlb() {
		return dwlb;
	}

	public void setDwlb(String dwlb) {
		this.dwlb = dwlb;
	}

	public String getZgbm() {
		return zgbm;
	}

	public void setZgbm(String zgbm) {
		this.zgbm = zgbm;
	}

	public String getSshy() {
		return sshy;
	}

	public void setSshy(String sshy) {
		this.sshy = sshy;
	}

	public String getSzdqsh() {
		return szdqsh;
	}

	public void setSzdqsh(String szdqsh) {
		this.szdqsh = szdqsh;
	}

	public String getSzdqsi() {
		return szdqsi;
	}

	public void setSzdqsi(String szdqsi) {
		this.szdqsi = szdqsi;
	}

	public String getClrq() {
		return clrq;
	}

	public void setClrq(String clrq) {
		this.clrq = clrq;
	}

	public String getTxdz() {
		return txdz;
	}

	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}

	public String getLxbm() {
		return lxbm;
	}

	public void setLxbm(String lxbm) {
		this.lxbm = lxbm;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDwzy() {
		return dwzy;
	}

	public void setDwzy(String dwzy) {
		this.dwzy = dwzy;
	}

	public String getDazjdz() {
		return dazjdz;
	}

	public void setDazjdz(String dazjdz) {
		this.dazjdz = dazjdz;
	}

	public String getDayzbm() {
		return dayzbm;
	}

	public void setDayzbm(String dayzbm) {
		this.dayzbm = dayzbm;
	}

	public String getDwjs() {
		return dwjs;
	}

	public void setDwjs(String dwjs) {
		this.dwjs = dwjs;
	}
	private String dwxz;//单位性质
	private String dwlb;//单位类别
	private String zgbm;//主管部门
	private String sshy;//所属行业
	private String szdqsh;//所在地区省
	private String szdqsi;//所在地区市
	private String clrq;//成立日期
	private String txdz;//通讯地址
	private String lxbm;//联系部门
	private String lxr;//联系人
	private String email;//邮箱
	private String dwzy;//单位主页
	private String dazjdz;//档案转寄地址
	private String dayzbm;//档案邮政编码
	private String dwjs;//单位介绍
	FormFile tpdz;
	private String dwdm;
	private String fkh;
	private String ykt;
	private String dwxxdm;
	private String sydq;
	private String bynf;
	private String byyf;
	//辅导员组
	private String fdyz;
	public String getBynf() {
		return bynf;
	}

	public void setBynf(String bynf) {
		this.bynf = bynf;
	}

	public String getByyf() {
		return byyf;
	}

	public void setByyf(String byyf) {
		this.byyf = byyf;
	}

	public String getSydq() {
		return sydq;
	}

	public void setSydq(String sydq) {
		this.sydq = sydq;
	}

	public String getShsj1() {
		return shsj1;
	}

	public void setShsj1(String shsj1) {
		this.shsj1 = shsj1;
	}

	public String getShsj2() {
		return shsj2;
	}

	public void setShsj2(String shsj2) {
		this.shsj2 = shsj2;
	}

	public String getShsj3() {
		return shsj3;
	}

	public void setShsj3(String shsj3) {
		this.shsj3 = shsj3;
	}

	public String getWyyz() {
		return wyyz;
	}

	public void setWyyz(String wyyz) {
		this.wyyz = wyyz;
	}

	public String getJb() {
		return jb;
	}

	public void setJb(String jb) {
		this.jb = jb;
	}

	public String getJsjsp() {
		return jsjsp;
	}

	public void setJsjsp(String jsjsp) {
		this.jsjsp = jsjsp;
	}

	public String getTcnl() {
		return tcnl;
	}

	public void setTcnl(String tcnl) {
		this.tcnl = tcnl;
	}

	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}

	public String getYxyj() {
		return yxyj;
	}

	public void setYxyj(String yxyj) {
		this.yxyj = yxyj;
	}

	public String getJybmyj() {
		return jybmyj;
	}

	public void setJybmyj(String jybmyj) {
		this.jybmyj = jybmyj;
	}

	public String getBmlxr() {
		return bmlxr;
	}

	public void setBmlxr(String bmlxr) {
		this.bmlxr = bmlxr;
	}

	public String getBmlxdh() {
		return bmlxdh;
	}

	public void setBmlxdh(String bmlxdh) {
		this.bmlxdh = bmlxdh;
	}
	private String shsj1;
	private String shsj2;
	private String shsj3;
	private String wyyz;
	private String jb;
	private String jsjsp;
	private String tcnl;
	private String jyfw;
	private String yxyj;
	private String jybmyj;
	private String bmlxr;
	private String bmlxdh;
	public String getDwxxdm() {
		return dwxxdm;
	}

	public void setDwxxdm(String dwxxdm) {
		this.dwxxdm = dwxxdm;
	}

	public FormFile getTpdz() {
		return tpdz;
	}

	public void setTpdz(FormFile tpdz) {
		this.tpdz = tpdz;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public String getDwdh() {
		return dwdh;
	}

	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}

	public String getDwcz() {
		return dwcz;
	}

	public void setDwcz(String dwcz) {
		this.dwcz = dwcz;
	}

	public String getDwyx() {
		return dwyx;
	}

	public void setDwyx(String dwyx) {
		this.dwyx = dwyx;
	}

	public String getDwyb() {
		return dwyb;
	}

	public void setDwyb(String dwyb) {
		this.dwyb = dwyb;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getGddh() {
		return gddh;
	}

	public void setGddh(String gddh) {
		this.gddh = gddh;
	}

	public String getYddh() {
		return yddh;
	}

	public void setYddh(String yddh) {
		this.yddh = yddh;
	}

	public String getLfsy() {
		return lfsy;
	}

	public void setLfsy(String lfsy) {
		this.lfsy = lfsy;
	}

	public String getYtdd() {
		return ytdd;
	}

	public void setYtdd(String ytdd) {
		this.ytdd = ytdd;
	}

	public String getRs1() {
		return rs1;
	}

	public void setRs1(String rs1) {
		this.rs1 = rs1;
	}

	public String getJddd() {
		return jddd;
	}

	public void setJddd(String jddd) {
		this.jddd = jddd;
	}

	public String getJdcyr1() {
		return jdcyr1;
	}

	public void setJdcyr1(String jdcyr1) {
		this.jdcyr1 = jdcyr1;
	}

	public String getJdcyr2() {
		return jdcyr2;
	}

	public void setJdcyr2(String jdcyr2) {
		this.jdcyr2 = jdcyr2;
	}

	public String getJdcyr3() {
		return jdcyr3;
	}

	public void setJdcyr3(String jdcyr3) {
		this.jdcyr3 = jdcyr3;
	}

	public String getJdcyr4() {
		return jdcyr4;
	}

	public void setJdcyr4(String jdcyr4) {
		this.jdcyr4 = jdcyr4;
	}

	public String getDwbh() {
		return dwbh;
	}

	public void setDwbh(String dwbh) {
		this.dwbh = dwbh;
	}

	public String getXysbh() {
		return xysbh;
	}

	public void setXysbh(String xysbh) {
		this.xysbh = xysbh;
	}

	public String getWpdw() {
		return wpdw;
	}

	public void setWpdw(String wpdw) {
		this.wpdw = wpdw;
	}

	public String getZsmc() {
		return zsmc;
	}

	public void setZsmc(String zsmc) {
		this.zsmc = zsmc;
	}

	public String getZdcl() {
		return zdcl;
	}

	public void setZdcl(String zdcl) {
		this.zdcl = zdcl;
	}

	public String getBdzbz() {
		return bdzbz;
	}

	public void setBdzbz(String bdzbz) {
		this.bdzbz = bdzbz;
	}

	public String getSjdw() {
		return sjdw;
	}

	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}

	public String getPqdw() {
		return pqdw;
	}

	public void setPqdw(String pqdw) {
		this.pqdw = pqdw;
	}

	public String getPqdwid() {
		return pqdwid;
	}

	public void setPqdwid(String pqdwid) {
		this.pqdwid = pqdwid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJyzk() {
		return jyzk;
	}

	public void setJyzk(String jyzk) {
		this.jyzk = jyzk;
	}

	public String getDwxz1() {
		return dwxz1;
	}

	public void setDwxz1(String dwxz1) {
		this.dwxz1 = dwxz1;
	}

	public String getDwxz2() {
		return dwxz2;
	}

	public void setDwxz2(String dwxz2) {
		this.dwxz2 = dwxz2;
	}

	public String getDwxzmc() {
		return dwxzmc;
	}

	public void setDwxzmc(String dwxzmc) {
		this.dwxzmc = dwxzmc;
	}

	public String getZgbm1() {
		return zgbm1;
	}

	public void setZgbm1(String zgbm1) {
		this.zgbm1 = zgbm1;
	}

	public String getZgbm2() {
		return zgbm2;
	}

	public void setZgbm2(String zgbm2) {
		this.zgbm2 = zgbm2;
	}

	public String getZgbmmc() {
		return zgbmmc;
	}

	public void setZgbmmc(String zgbmmc) {
		this.zgbmmc = zgbmmc;
	}

	public String getDwszd1() {
		return dwszd1;
	}

	public void setDwszd1(String dwszd1) {
		this.dwszd1 = dwszd1;
	}

	public String getDwszd2() {
		return dwszd2;
	}

	public void setDwszd2(String dwszd2) {
		this.dwszd2 = dwszd2;
	}

	public String getDwszd3() {
		return dwszd3;
	}

	public void setDwszd3(String dwszd3) {
		this.dwszd3 = dwszd3;
	}

	public String getSjszd1() {
		return sjszd1;
	}

	public void setSjszd1(String sjszd1) {
		this.sjszd1 = sjszd1;
	}

	public String getSjszd2() {
		return sjszd2;
	}

	public void setSjszd2(String sjszd2) {
		this.sjszd2 = sjszd2;
	}

	public String getSjszd3() {
		return sjszd3;
	}

	public void setSjszd3(String sjszd3) {
		this.sjszd3 = sjszd3;
	}

	public String getHkdz() {
		return hkdz;
	}

	public void setHkdz(String hkdz) {
		this.hkdz = hkdz;
	}

	public String getGprq() {
		return gprq;
	}

	public void setGprq(String gprq) {
		this.gprq = gprq;
	}

	public String getGpcs() {
		return gpcs;
	}

	public void setGpcs(String gpcs) {
		this.gpcs = gpcs;
	}

	public String getGpyy() {
		return gpyy;
	}

	public void setGpyy(String gpyy) {
		this.gpyy = gpyy;
	}

	public String getYdwmc() {
		return ydwmc;
	}

	public void setYdwmc(String ydwmc) {
		this.ydwmc = ydwmc;
	}

	public String getBdzbh() {
		return bdzbh;
	}

	public void setBdzbh(String bdzbh) {
		this.bdzbh = bdzbh;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getByqx() {
		return byqx;
	}

	public void setByqx(String byqx) {
		this.byqx = byqx;
	}

	public String getJgshi2() {
		return jgshi2;
	}

	public void setJgshi2(String jgshi2) {
		this.jgshi2 = jgshi2;
	}

	public String getJgshi3() {
		return jgshi3;
	}

	public void setJgshi3(String jgshi3) {
		this.jgshi3 = jgshi3;
	}

	public String getXlmc() {
		return xlmc;
	}

	public void setXlmc(String xlmc) {
		this.xlmc = xlmc;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getYwcx() {
		return ywcx;
	}

	public void setYwcx(String ywcx) {
		this.ywcx = ywcx;
	}

	public String getYwss() {
		return ywss;
	}

	public void setYwss(String ywss) {
		this.ywss = ywss;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getHdrq() {
		return hdrq;
	}

	public void setHdrq(String hdrq) {
		this.hdrq = hdrq;
	}
	private String hdrq;
	public String getSzdw() {
		return szdw;
	}

	public void setSzdw(String szdw) {
		this.szdw = szdw;
	}

	public String getZwzc() {
		return zwzc;
	}

	public void setZwzc(String zwzc) {
		this.zwzc = zwzc;
	}

	public String getFdyyj() {
		return fdyyj;
	}

	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}

	public String getXsccdm() {
		return xsccdm;
	}
 
	public void setXsccdm(String xsccdm) {
		this.xsccdm = xsccdm;
	}

	public String getYqdm() {
		return yqdm;
	}

	public void setYqdm(String yqdm) {
		this.yqdm = yqdm;
	}

	public String getGzs() {
		return gzs;
	}

	public void setGzs(String gzs) {
		this.gzs = gzs;
	}

	public String getJxjlb() {
		return jxjlb;
	}

	public void setJxjlb(String jxjlb) {
		this.jxjlb = jxjlb;
	}

	public String getJxjfl() {
		return jxjfl;
	}

	public void setJxjfl(String jxjfl) {
		this.jxjfl = jxjfl;
	}

	public String getRychlb() {
		return rychlb;
	}

	public void setRychlb(String rychlb) {
		this.rychlb = rychlb;
	}
	
	public String getWxzt() {
		return wxzt;
	}

	public void setWxzt(String wxzt) {
		this.wxzt = wxzt;
	}

	public String getJlqk() {
		return jlqk;
	}

	public void setJlqk(String jlqk) {
		this.jlqk = jlqk;
	}

	public String getCfyy() {
		return cfyy;
	}
	
	public void setCfyy(String cfyy) {
		this.cfyy = cfyy;
	}

	public String getCfsj() {
		return cfsj;
	}

	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}

	public String getCfwh() {
		return cfwh;
	}

	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	
	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	public String getCxjg() {
		return cxjg;
	}

	public void setCxjg(String cxjg) {
		this.cxjg = cxjg;
	}

	public String getXzyj() {
		return xzyj;
	}

	public void setXzyj(String xzyj) {
		this.xzyj = xzyj;
	}

	public String getXb1() {
		return xb1;
	}

	public void setXb1(String xb1) {
		this.xb1 = xb1;
	}
	
	public String getLc() {
		return lc;
	}
	
	public void setLc(String lc) {
		this.lc = lc;
	}

	public String getCxfs() {
		return cxfs;
	}

	public void setCxfs(String cxfs) {
		this.cxfs = cxfs;
	}

	public String getTsgw() {
		return tsgw;
	}

	public void setTsgw(String tsgw) {
		this.tsgw = tsgw;
	}

	public String getXxqdm() {
		return xxqdm;
	}

	public void setXxqdm(String xxqdm) {
		this.xxqdm = xxqdm;
	}
	
	public String getFpfs() {
		return fpfs;
	}

	public void setFpfs(String fpfs) {
		this.fpfs = fpfs;
	}

	public String getBfrq() {
		return bfrq;
	}

	public void setBfrq(String bfrq) {
		this.bfrq = bfrq;
	}

	public String getYxrq() {
		return yxrq;
	}

	public void setYxrq(String yxrq) {
		this.yxrq = yxrq;
	}

	public String getFzrq() {
		return fzrq;
	}

	public void setFzrq(String fzrq) {
		this.fzrq = fzrq;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getMappingItem() {
		return mappingItem;
	}

	public void setMappingItem(String mappingItem) {
		this.mappingItem = mappingItem;
	}

	public String getBzlxdm() {
		return bzlxdm;
	}

	public void setBzlxdm(String bzlxdm) {
		this.bzlxdm = bzlxdm;
	}

	public String getBzrq() {
		return bzrq;
	}

	public void setBzrq(String bzrq) {
		this.bzrq = bzrq;
	}

	public String getBzrq1() {
		return bzrq1;
	}

	public void setBzrq1(String bzrq1) {
		this.bzrq1 = bzrq1;
	}

	public String getBzrq2() {
		return bzrq2;
	}

	public void setBzrq2(String bzrq2) {
		this.bzrq2 = bzrq2;
	}

	public String getBzrqt() {
		return bzrqt;
	}

	public void setBzrqt(String bzrqt) {
		this.bzrqt = bzrqt;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getXydmt() {
		return xydmt;
	}

	public void setXydmt(String xydmt) {
		this.xydmt = xydmt;
	}

	public String getUnitList() {
		return unitList;
	}

	public void setUnitList(String unitList) {
		this.unitList = unitList;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSjzd() {
		return sjzd;
	}

	public void setSjzd(String sjzd) {
		this.sjzd = sjzd;
	}

	public String getCwfp() {
		return cwfp;
	}

	public void setCwfp(String cwfp) {
		this.cwfp = cwfp;
	}

	public String getPysj() {
		return pysj;
	}

	public void setPysj(String pysj) {
		this.pysj = pysj;
	}

	public String getGydybl() {
		return gydybl;
	}

	public void setGydybl(String gydybl) {
		this.gydybl = gydybl;
	}

	public String getTybl() {
		return tybl;
	}

	public void setTybl(String tybl) {
		this.tybl = tybl;
	}

	public String getXxcxbl() {
		return xxcxbl;
	}

	public void setXxcxbl(String xxcxbl) {
		this.xxcxbl = xxcxbl;
	}

	public String getXydybl() {
		return xydybl;
	}

	public void setXydybl(String xydybl) {
		this.xydybl = xydybl;
	}

	public String getZybl() {
		return zybl;
	}

	public void setZybl(String zybl) {
		this.zybl = zybl;
	}

	public String getGrhj() {
		return grhj;
	}

	public void setGrhj(String grhj) {
		this.grhj = grhj;
	}

	public String getYdqcwh() {
		return ydqcwh;
	}

	public void setYdqcwh(String ydqcwh) {
		this.ydqcwh = ydqcwh;
	}

	public String getYdqrzsj() {
		return ydqrzsj;
	}

	public void setYdqrzsj(String ydqrzsj) {
		this.ydqrzsj = ydqrzsj;
	}

	public String getYdqssbh() {
		return ydqssbh;
	}

	public void setYdqssbh(String ydqssbh) {
		this.ydqssbh = ydqssbh;
	}

	public String getXbyy() {
		return xbyy;
	}

	public void setXbyy(String xbyy) {
		this.xbyy = xbyy;
	}

	public String getBbyy() {
		return bbyy;
	}

	public void setBbyy(String bbyy) {
		this.bbyy = bbyy;
	}

	public String getYktbz() {
		return yktbz;
	}

	public void setYktbz(String yktbz) {
		this.yktbz = yktbz;
	}

	public String getHcpbz() {
		return hcpbz;
	}

	public void setHcpbz(String hcpbz) {
		this.hcpbz = hcpbz;
	}


	public String getFf() {
		return ff;
	}

	public void setFf(String ff) {
		this.ff = ff;
	}

	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	public String getYzxtzhd() {
		return yzxtzhd;
	}

	public void setYzxtzhd(String yzxtzhd) {
		this.yzxtzhd = yzxtzhd;
	}

	public String getHdzj() {
		return hdzj;
	}

	public void setHdzj(String hdzj) {
		this.hdzj = hdzj;
	}

	public String getHczdz() {
		return hczdz;
	}

	public void setHczdz(String hczdz) {
		this.hczdz = hczdz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDlrzjh() {
		return dlrzjh;
	}

	public void setDlrzjh(String dlrzjh) {
		this.dlrzjh = dlrzjh;
	}

	public String getDlr() {
		return dlr;
	}

	public void setDlr(String dlr) {
		this.dlr = dlr;
	}

	public String getYy() {
		return yy;
	}

	public void setYy(String yy) {
		this.yy = yy;
	}

	public String getBjf() {
		return bjf;
	}

	public void setBjf(String bjf) {
		this.bjf = bjf;
	}

	public String getYclhzjxgbm() {
		return yclhzjxgbm;
	}

	public void setYclhzjxgbm(String yclhzjxgbm) {
		this.yclhzjxgbm = yclhzjxgbm;
	}

	public String getXxyhd() {
		return xxyhd;
	}

	public void setXxyhd(String xxyhd) {
		this.xxyhd = xxyhd;
	}

	public String getXkbl() {
		return xkbl;
	}

	public void setXkbl(String xkbl) {
		this.xkbl = xkbl;
	}

	public String getBbfys() {
		return bbfys;
	}

	public void setBbfys(String bbfys) {
		this.bbfys = bbfys;
	}

	public String getSlbh() {
		return slbh;
	}

	public void setSlbh(String slbh) {
		this.slbh = slbh;
	}

	public String getGzbc() {
		return gzbc;
	}

	public void setGzbc(String gzbc) {
		this.gzbc = gzbc;
	}

	public String getSlrgh() {
		return slrgh;
	}

	public void setSlrgh(String slrgh) {
		this.slrgh = slrgh;
	}

	public String getSlr() {
		return slr;
	}

	public void setSlr(String slr) {
		this.slr = slr;
	}

	public String getBlqk() {
		return blqk;
	}

	public void setBlqk(String blqk) {
		this.blqk = blqk;
	}

	public String getGsqk() {
		return gsqk;
	}

	public void setGsqk(String gsqk) {
		this.gsqk = gsqk;
	}

	public String getZjh() {
		return zjh;
	}

	public void setZjh(String zjh) {
		this.zjh = zjh;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public String getRxsj() {
		return rxsj;
	}

	public void setRxsj(String rxsj) {
		this.rxsj = rxsj;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getRychdm() {
		return rychdm;
	}

	public String getYdsj() {
		return ydsj;
	}

	public void setYdsj(String ydsj) {
		this.ydsj = ydsj;
	}

	public String getRychdm(String rychdm) {
		return this.rychdm;
	}

	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}

	public String getJxjdm() {
		return jxjdm;
	}

	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}

	public String getHzr() {
		return hzr;
	}

	public void setHzr(String hzr) {
		this.hzr = hzr;
	}

	public String getQflxdm() {
		return qflxdm;
	}

	public void setQflxdm(String qflxdm) {
		this.qflxdm = qflxdm;
	}

	public String getNsepjbdm() {
		return nsepjbdm;
	}

	public void setNsepjbdm(String nsepjbdm) {
		this.nsepjbdm = nsepjbdm;
	}

	public String getDektxf() {
		return dektxf;
	}

	public void setDektxf(String dektxf) {
		this.dektxf = dektxf;
	}

	public String getSsbh() {
		return ssbh;
	}

	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}

	public String getDtsj() {
		return dtsj;
	}

	public void setDtsj(String dtsj) {
		this.dtsj = dtsj;
	}

	public String getWjsj() {
		return wjsj;
	}

	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
	}

	public String getJcbm() {
		return jcbm;
	}

	public void setJcbm(String jcbm) {
		this.jcbm = jcbm;
	}

	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}

	public String getRydm() {
		return rydm;
	}

	public void setRydm(String rydm) {
		this.rydm = rydm;
	}

	public String getBxsj() {
		return bxsj;
	}

	public void setBxsj(String bxsj) {
		this.bxsj = bxsj;
	}

	public String getWxsj() {
		return wxsj;
	}

	public void setWxsj(String wxsj) {
		this.wxsj = wxsj;
	}

	public String getDxzsx() {
		return dxzsx;
	}

	public void setDxzsx(String dxzsx) {
		this.dxzsx = dxzsx;
	}

	public String getLpsj() {
		return lpsj;
	}

	public void setLpsj(String lpsj) {
		this.lpsj = lpsj;
	}

	public String getSyrq() {
		return syrq;
	}

	public void setSyrq(String syrq) {
		this.syrq = syrq;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getZxjdm(){//外设助学金代码
		return zxjdm;
	}

	public void setZxjdm(String zxjdm){//外设助学金代码
		this.zxjdm = zxjdm;
	}

	public String getZxjxjdm(){
		return zxjxjdm;
	}

	public void setZxjxjdm(String zxjxjdm){
		this.zxjxjdm = zxjxjdm;
	}

	public String getNdfw(){
		return ndfw;
	}

	public void setNdfw(String ndfw){
		this.ndfw = ndfw;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getYxstlb() {
		return yxstlb;
	}

	public void setYxstlb(String yxstlb) {
		this.yxstlb = yxstlb;
	}

	public String getStlsh() {
		return stlsh;
	}

	public void setStlsh(String stlsh) {
		this.stlsh = stlsh;
	}

	public String getStlxdm() {
		return stlxdm;
	}

	public void setStlxdm(String stlxdm) {
		this.stlxdm = stlxdm;
	}

	public String getStndjbdm() {
		return stndjbdm;
	}

	public void setStndjbdm(String stndjbdm) {
		this.stndjbdm = stndjbdm;
	}

	public String getStxh() {
		return stxh;
	}

	public void setStxh(String stxh) {
		this.stxh = stxh;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getChanged() {
		return changed;
	}

	public void setChanged(String changed) {
		this.changed = changed;
	}

	public String getCodeDm() {
		return codeDm;
	}

	public void setCodeDm(String codeDm) {
		this.codeDm = codeDm;
	}

	public String getCodeJb() {
		return codeJb;
	}

	public void setCodeJb(String codeJb) {
		this.codeJb = codeJb;
	}

	public String getCodeLb() {
		return codeLb;
	}

	public void setCodeLb(String codeLb) {
		this.codeLb = codeLb;
	}

	public String getCodeMc() {
		return codeMc;
	}

	public void setCodeMc(String codeMc) {
		this.codeMc = codeMc;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getConditionSqlText() {
		return conditionSqlText;
	}

	public void setConditionSqlText(String conditionSqlText) {
		this.conditionSqlText = conditionSqlText;
	}

	public String getConditionSqlValue() {
		return conditionSqlValue;
	}

	public void setConditionSqlValue(String conditionSqlValue) {
		this.conditionSqlValue = conditionSqlValue;
	}

	public String getDateF() {
		return dateF;
	}

	public void setDateF(String dateF) {
		this.dateF = dateF;
	}

	public String getDateT() {
		return dateT;
	}

	public void setDateT(String dateT) {
		this.dateT = dateT;
	}

	public String getDisplayGroupPower() {
		return displayGroupPower;
	}

	public void setDisplayGroupPower(String displayGroupPower) {
		this.displayGroupPower = displayGroupPower;
	}

	public String getDisplaySubPower() {
		return displaySubPower;
	}

	public void setDisplaySubPower(String displaySubPower) {
		this.displaySubPower = displaySubPower;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String[] getErrMsgs() {
		return errMsgs;
	}

	public void setErrMsgs(String[] errMsgs) {
		this.errMsgs = errMsgs;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getGnmkdm() {
		return gnmkdm;
	}

	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}

	public String getGnmkmc() {
		return gnmkmc;
	}

	public void setGnmkmc(String gnmkmc) {
		this.gnmkmc = gnmkmc;
	}

	public String getGroupList() {
		return groupList;
	}

	public void setGroupList(String groupList) {
		this.groupList = groupList;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getIpF() {
		return ipF;
	}

	public void setIpF(String ipF) {
		this.ipF = ipF;
	}

	public String getIpT() {
		return ipT;
	}

	public void setIpT(String ipT) {
		this.ipT = ipT;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemStr() {
		return itemStr;
	}

	public void setItemStr(String itemStr) {
		this.itemStr = itemStr;
	}

	public String getJtxm() {
		return jtxm;
	}

	public void setJtxm(String jtxm) {
		this.jtxm = jtxm;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String[] getNewCode() {
		return newCode;
	}

	public void setNewCode(String[] newCode) {
		this.newCode = newCode;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getOracleItem() {
		return oracleItem;
	}

	public void setOracleItem(String oracleItem) {
		this.oracleItem = oracleItem;
	}

	public String getPartList() {
		return partList;
	}

	public void setPartList(String partList) {
		this.partList = partList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getTitList() {
		return titList;
	}

	public void setTitList(String titList) {
		this.titList = titList;
	}

	public String getTjtjz() {
		return tjtjz;
	}

	public void setTjtjz(String tjtjz) {
		this.tjtjz = tjtjz;
	}

	public String getTjxm() {
		return tjxm;
	}

	public void setTjxm(String tjxm) {
		this.tjxm = tjxm;
	}

	public String getTjxmdyb() {
		return tjxmdyb;
	}

	public void setTjxmdyb(String tjxmdyb) {
		this.tjxmdyb = tjxmdyb;
	}

	public String getTName() {
		return tName;
	}

	public void setTName(String name) {
		tName = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPower() {
		return userPower;
	}

	public void setUserPower(String userPower) {
		this.userPower = userPower;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getYesNo() {
		return yesNo;
	}

	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getZmc() {
		return zmc;
	}

	public void setZmc(String zmc) {
		this.zmc = zmc;
	}

	public String getZxm() {
		return zxm;
	}

	public void setZxm(String zxm) {
		this.zxm = zxm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getDqszj() {
		return dqszj;
	}

	public void setDqszj(String dqszj) {
		this.dqszj = dqszj;
	}

	public String getTjdw() {
		return tjdw;
	}

	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getJlfs() {
		return jlfs;
	}

	public void setJlfs(String jlfs) {
		this.jlfs = jlfs;
	}

	public String getJllb() {
		return jllb;
	}

	public void setJllb(String jllb) {
		this.jllb = jllb;
	}

	public String getInsertFlag() {
		return insertFlag;
	}

	public void setInsertFlag(String insertFlag) {
		this.insertFlag = insertFlag;
	}

	public String getJlqx() {
		return jlqx;
	}

	public void setJlqx(String jlqx) {
		this.jlqx = jlqx;
	}

	public String getJltj() {
		return jltj;
	}

	public void setJltj(String jltj) {
		this.jltj = jltj;
	}

	public String getJlxx() {
		return jlxx;
	}

	public void setJlxx(String jlxx) {
		this.jlxx = jlxx;
	}

	public String getJzrq() {
		return jzrq;
	}

	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}

	public String getPcsj() {
		return pcsj;
	}

	public void setPcsj(String pcsj) {
		this.pcsj = pcsj;
	}

	public String getQwdq() {
		return qwdq;
	}

	public void setQwdq(String qwdq) {
		this.qwdq = qwdq;
	}

	public String getZgje() {
		return zgje;
	}

	public void setZgje(String zgje) {
		this.zgje = zgje;
	}

	public String getJfxcm() {
		return jfxcm;
	}

	public void setJfxcm(String jfxcm) {
		this.jfxcm = jfxcm;
	}

	public String getWhsxh() {
		return whsxh;
	}

	public void setWhsxh(String whsxh) {
		this.whsxh = whsxh;
	}

	public String getZxfh() {
		return zxfh;
	}

	public void setZxfh(String zxfh) {
		this.zxfh = zxfh;
	}

	public String getZxfx() {
		return zxfx;
	}

	public void setZxfx(String zxfx) {
		this.zxfx = zxfx;
	}

	public String getZxmxh() {
		return zxmxh;
	}

	public void setZxmxh(String zxmxh) {
		this.zxmxh = zxmxh;
	}

	public String getCxbm() {
		return cxbm;
	}

	public void setCxbm(String cxbm) {
		this.cxbm = cxbm;
	}

	public String[] getStuNum() {
		return stuNum;
	}

	public void setStuNum(String[] stuNum) {
		this.stuNum = stuNum;
	}

	public String[] getNjL() {
		return njL;
	}

	public void setNjL(String[] njL) {
		this.njL = njL;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getSjlsh() {
		return sjlsh;
	}

	public void setSjlsh(String sjlsh) {
		this.sjlsh = sjlsh;
	}

	public String getDispFlag() {
		return dispFlag;
	}

	public void setDispFlag(String dispFlag) {
		this.dispFlag = dispFlag;
	}

	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getXxbz() {
		return xxbz;
	}

	public void setXxbz(String xxbz) {
		this.xxbz = xxbz;
	}

	public String getXxbzfx() {
		return xxbzfx;
	}

	public void setXxbzfx(String xxbzfx) {
		this.xxbzfx = xxbzfx;
	}

	public String getJxbz() {
		return jxbz;
	}

	public void setJxbz(String jxbz) {
		this.jxbz = jxbz;
	}

	public String getJxbzfx() {
		return jxbzfx;
	}

	public void setJxbzfx(String jxbzfx) {
		this.jxbzfx = jxbzfx;
	}

	public String getExcelItem() {
		return excelItem;
	}

	public void setExcelItem(String excelItem) {
		this.excelItem = excelItem;
	}

	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getBoy() {
		return boy;
	}
	public void setBoy(String boy) {
		this.boy = boy;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getGirl() {
		return girl;
	}
	public void setGirl(String girl) {
		this.girl = girl;
	}

	public String[] getQtdm() {
		return qtdm;
	}

	public void setQtdm(String[] qtdm) {
		this.qtdm = qtdm;
	}

	public String[] getSfcp() {
		return sfcp;
	}

	public void setSfcp(String[] sfcp) {
		this.sfcp = sfcp;
	}

	public String[] getPfbl() {
		return pfbl;
	}

	public void setPfbl(String[] pfbl) {
		this.pfbl = pfbl;
	}

	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getQdz() {
		return qdz;
	}
	public void setQdz(String qdz) {
		this.qdz = qdz;
	}
	public String getTkz() {
		return tkz;
	}
	public void setTkz(String tkz) {
		this.tkz = tkz;
	}
	public String getZdz() {
		return zdz;
	}
	public void setZdz(String zdz) {
		this.zdz = zdz;
	}

	public String getDph() {
		return dph;
	}

	public void setDph(String dph) {
		this.dph = dph;
	}

	public String getHblb() {
		return hblb;
	}

	public void setHblb(String hblb) {
		this.hblb = hblb;
	}

	public String getYrdwdm() {
		return yrdwdm;
	}

	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}

	public String getGwdm() {
		return gwdm;
	}

	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getKnsbl() {
		return knsbl;
	}

	public void setKnsbl(String knsbl) {
		this.knsbl = knsbl;
	}

	public String getShxm() {
		return shxm;
	}

	public void setShxm(String shxm) {
		this.shxm = shxm;
	}

	public String getZbrydm() {
		return zbrydm;
	}

	public void setZbrydm(String zbrydm) {
		this.zbrydm = zbrydm;
	}

	public String[] getRscb() {
		return rscb;
	}

	public void setRscb(String[] rscb) {
		this.rscb = rscb;
	}

	public String getGydybl1() {
		return gydybl1;
	}

	public void setGydybl1(String gydybl1) {
		this.gydybl1 = gydybl1;
	}

	public String getTybl1() {
		return tybl1;
	}

	public void setTybl1(String tybl1) {
		this.tybl1 = tybl1;
	}

	public String getXxcxbl1() {
		return xxcxbl1;
	}

	public void setXxcxbl1(String xxcxbl1) {
		this.xxcxbl1 = xxcxbl1;
	}

	public String getXydybl1() {
		return xydybl1;
	}

	public void setXydybl1(String xydybl1) {
		this.xydybl1 = xydybl1;
	}

	public String getZybl1() {
		return zybl1;
	}

	public void setZybl1(String zybl1) {
		this.zybl1 = zybl1;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getBxxzdm() {
		return bxxzdm;
	}

	public void setBxxzdm(String bxxzdm) {
		this.bxxzdm = bxxzdm;
	}

	public String getCflb() {
		return cflb;
	}

	public void setCflb(String cflb) {
		this.cflb = cflb;
	}

	public String getCym() {
		return cym;
	}

	public void setCym(String cym) {
		this.cym = cym;
	}

	public String getDzyx() {
		return dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getPyfs() {
		return pyfs;
	}

	public void setPyfs(String pyfs) {
		this.pyfs = pyfs;
	}

	public String getRxfs() {
		return rxfs;
	}

	public void setRxfs(String rxfs) {
		this.rxfs = rxfs;
	}



	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getXjzt() {
		return xjzt;
	}

	public void setXjzt(String xjzt) {
		this.xjzt = xjzt;
	}

	public String getXmpy() {
		return xmpy;
	}

	public void setXmpy(String xmpy) {
		this.xmpy = xmpy;
	}



	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getKslb() {
		return kslb;
	}

	public void setKslb(String kslb) {
		this.kslb = kslb;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getSsdh() {
		return ssdh;
	}

	public void setSsdh(String ssdh) {
		this.ssdh = ssdh;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getXszp() {
		return xszp;
	}

	public void setXszp(String xszp) {
		this.xszp = xszp;
	}

	public String getSsbz() {
		return ssbz;
	}

	public void setSsbz(String ssbz) {
		this.ssbz = ssbz;
	}

	public String getZsjzrq() {
		return zsjzrq;
	}

	public void setZsjzrq(String zsjzrq) {
		this.zsjzrq = zsjzrq;
	}

	public String getZsrq() {
		return zsrq;
	}

	public void setZsrq(String zsrq) {
		this.zsrq = zsrq;
	}

	public String getSsch() {
		return ssch;
	}

	public void setSsch(String ssch) {
		this.ssch = ssch;
	}

	public String getJtcy1_ch() {
		return jtcy1_ch;
	}

	public void setJtcy1_ch(String jtcy1_ch) {
		this.jtcy1_ch = jtcy1_ch;
	}

	public String getJtcy1_gzdz() {
		return jtcy1_gzdz;
	}

	public void setJtcy1_gzdz(String jtcy1_gzdz) {
		this.jtcy1_gzdz = jtcy1_gzdz;
	}

	public String getJtcy1_lxdh1() {
		return jtcy1_lxdh1;
	}

	public void setJtcy1_lxdh1(String jtcy1_lxdh1) {
		this.jtcy1_lxdh1 = jtcy1_lxdh1;
	}

	public String getJtcy1_lxdh2() {
		return jtcy1_lxdh2;
	}

	public void setJtcy1_lxdh2(String jtcy1_lxdh2) {
		this.jtcy1_lxdh2 = jtcy1_lxdh2;
	}

	public String getJtcy1_lxdh3() {
		return jtcy1_lxdh3;
	}

	public void setJtcy1_lxdh3(String jtcy1_lxdh3) {
		this.jtcy1_lxdh3 = jtcy1_lxdh3;
	}

	public String getJtcy1_mz() {
		return jtcy1_mz;
	}

	public void setJtcy1_mz(String jtcy1_mz) {
		this.jtcy1_mz = jtcy1_mz;
	}

	public String getJtcy1_nl() {
		return jtcy1_nl;
	}

	public void setJtcy1_nl(String jtcy1_nl) {
		this.jtcy1_nl = jtcy1_nl;
	}

	public String getJtcy1_xm() {
		return jtcy1_xm;
	}

	public void setJtcy1_xm(String jtcy1_xm) {
		this.jtcy1_xm = jtcy1_xm;
	}

	public String getJtcy1_zw() {
		return jtcy1_zw;
	}

	public void setJtcy1_zw(String jtcy1_zw) {
		this.jtcy1_zw = jtcy1_zw;
	}

	public String getJtcy1_zzmm() {
		return jtcy1_zzmm;
	}

	public void setJtcy1_zzmm(String jtcy1_zzmm) {
		this.jtcy1_zzmm = jtcy1_zzmm;
	}

	public String getJtcy2_ch() {
		return jtcy2_ch;
	}

	public void setJtcy2_ch(String jtcy2_ch) {
		this.jtcy2_ch = jtcy2_ch;
	}

	public String getJtcy2_gzdz() {
		return jtcy2_gzdz;
	}

	public void setJtcy2_gzdz(String jtcy2_gzdz) {
		this.jtcy2_gzdz = jtcy2_gzdz;
	}

	public String getJtcy2_lxdh1() {
		return jtcy2_lxdh1;
	}

	public void setJtcy2_lxdh1(String jtcy2_lxdh1) {
		this.jtcy2_lxdh1 = jtcy2_lxdh1;
	}

	public String getJtcy2_lxdh2() {
		return jtcy2_lxdh2;
	}

	public void setJtcy2_lxdh2(String jtcy2_lxdh2) {
		this.jtcy2_lxdh2 = jtcy2_lxdh2;
	}

	public String getJtcy2_lxdh3() {
		return jtcy2_lxdh3;
	}

	public void setJtcy2_lxdh3(String jtcy2_lxdh3) {
		this.jtcy2_lxdh3 = jtcy2_lxdh3;
	}

	public String getJtcy2_mz() {
		return jtcy2_mz;
	}

	public void setJtcy2_mz(String jtcy2_mz) {
		this.jtcy2_mz = jtcy2_mz;
	}

	public String getJtcy2_nl() {
		return jtcy2_nl;
	}

	public void setJtcy2_nl(String jtcy2_nl) {
		this.jtcy2_nl = jtcy2_nl;
	}

	public String getJtcy2_xm() {
		return jtcy2_xm;
	}

	public void setJtcy2_xm(String jtcy2_xm) {
		this.jtcy2_xm = jtcy2_xm;
	}

	public String getJtcy2_zw() {
		return jtcy2_zw;
	}

	public void setJtcy2_zw(String jtcy2_zw) {
		this.jtcy2_zw = jtcy2_zw;
	}

	public String getJtcy2_zzmm() {
		return jtcy2_zzmm;
	}

	public void setJtcy2_zzmm(String jtcy2_zzmm) {
		this.jtcy2_zzmm = jtcy2_zzmm;
	}

	public String getJtcy3_ch() {
		return jtcy3_ch;
	}

	public void setJtcy3_ch(String jtcy3_ch) {
		this.jtcy3_ch = jtcy3_ch;
	}

	public String getJtcy3_gzdz() {
		return jtcy3_gzdz;
	}

	public void setJtcy3_gzdz(String jtcy3_gzdz) {
		this.jtcy3_gzdz = jtcy3_gzdz;
	}

	public String getJtcy3_lxdh1() {
		return jtcy3_lxdh1;
	}

	public void setJtcy3_lxdh1(String jtcy3_lxdh1) {
		this.jtcy3_lxdh1 = jtcy3_lxdh1;
	}

	public String getJtcy3_lxdh2() {
		return jtcy3_lxdh2;
	}

	public void setJtcy3_lxdh2(String jtcy3_lxdh2) {
		this.jtcy3_lxdh2 = jtcy3_lxdh2;
	}

	public String getJtcy3_lxdh3() {
		return jtcy3_lxdh3;
	}

	public void setJtcy3_lxdh3(String jtcy3_lxdh3) {
		this.jtcy3_lxdh3 = jtcy3_lxdh3;
	}

	public String getJtcy3_mz() {
		return jtcy3_mz;
	}

	public void setJtcy3_mz(String jtcy3_mz) {
		this.jtcy3_mz = jtcy3_mz;
	}

	public String getJtcy3_nl() {
		return jtcy3_nl;
	}

	public void setJtcy3_nl(String jtcy3_nl) {
		this.jtcy3_nl = jtcy3_nl;
	}

	public String getJtcy3_xm() {
		return jtcy3_xm;
	}

	public void setJtcy3_xm(String jtcy3_xm) {
		this.jtcy3_xm = jtcy3_xm;
	}

	public String getJtcy3_zw() {
		return jtcy3_zw;
	}

	public void setJtcy3_zw(String jtcy3_zw) {
		this.jtcy3_zw = jtcy3_zw;
	}

	public String getJtcy1_yzbm() {
		return jtcy1_yzbm;
	}

	public void setJtcy1_yzbm(String jtcy1_yzbm) {
		this.jtcy1_yzbm = jtcy1_yzbm;
	}

	public String getJtcy2_yzbm() {
		return jtcy2_yzbm;
	}

	public void setJtcy2_yzbm(String jtcy2_yzbm) {
		this.jtcy2_yzbm = jtcy2_yzbm;
	}

	public String getJtcy3_yzbm() {
		return jtcy3_yzbm;
	}

	public void setJtcy3_yzbm(String jtcy3_yzbm) {
		this.jtcy3_yzbm = jtcy3_yzbm;
	}

	public String getJtcy3_zzmm() {
		return jtcy3_zzmm;
	}

	public void setJtcy3_zzmm(String jtcy3_zzmm) {
		this.jtcy3_zzmm = jtcy3_zzmm;
	}

	public String getJtszd() {
		return jtszd;
	}

	public void setJtszd(String jtszd) {
		this.jtszd = jtszd;
	}
	public FormFile getFile2() {
		return file2;
	}

	public void setFile2(FormFile file2) {
		this.file2 = file2;
	}

	public String getHth() {
		return hth;
	}

	public void setHth(String hth) {
		this.hth = hth;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getDklb() {
		return dklb;
	}

	public void setDklb(String dklb) {
		this.dklb = dklb;
	}

	public String getDkll() {
		return dkll;
	}

	public void setDkll(String dkll) {
		this.dkll = dkll;
	}

	public String getHtdkfs() {
		return htdkfs;
	}

	public void setHtdkfs(String htdkfs) {
		this.htdkfs = htdkfs;
	}

	public String getHtfzjgmc() {
		return htfzjgmc;
	}

	public void setHtfzjgmc(String htfzjgmc) {
		this.htfzjgmc = htfzjgmc;
	}

	public String getHthkfs() {
		return hthkfs;
	}

	public void setHthkfs(String hthkfs) {
		this.hthkfs = hthkfs;
	}

	public String getHthkjzmc() {
		return hthkjzmc;
	}

	public void setHthkjzmc(String hthkjzmc) {
		this.hthkjzmc = hthkjzmc;
	}

	public String getHthkjzrq() {
		return hthkjzrq;
	}

	public void setHthkjzrq(String hthkjzrq) {
		this.hthkjzrq = hthkjzrq;
	}

	public String getHthkjzzh() {
		return hthkjzzh;
	}

	public void setHthkjzzh(String hthkjzzh) {
		this.hthkjzzh = hthkjzzh;
	}

	public String getHthkksrq() {
		return hthkksrq;
	}

	public void setHthkksrq(String hthkksrq) {
		this.hthkksrq = hthkksrq;
	}

	public String getHtjbjrjg() {
		return htjbjrjg;
	}

	public void setHtjbjrjg(String htjbjrjg) {
		this.htjbjrjg = htjbjrjg;
	}

	public String getHtjby() {
		return htjby;
	}

	public void setHtjby(String htjby) {
		this.htjby = htjby;
	}

	public String getHtpzhz() {
		return htpzhz;
	}

	public void setHtpzhz(String htpzhz) {
		this.htpzhz = htpzhz;
	}

	public String getHtpzrq() {
		return htpzrq;
	}

	public void setHtpzrq(String htpzrq) {
		this.htpzrq = htpzrq;
	}

	public String getHttqbz() {
		return httqbz;
	}

	public void setHttqbz(String httqbz) {
		this.httqbz = httqbz;
	}

	public String getHttqjzsj() {
		return httqjzsj;
	}

	public void setHttqjzsj(String httqjzsj) {
		this.httqjzsj = httqjzsj;
	}

	public String getHttqyhbxje() {
		return httqyhbxje;
	}

	public void setHttqyhbxje(String httqyhbxje) {
		this.httqyhbxje = httqyhbxje;
	}

	public String getHtzje() {
		return htzje;
	}

	public void setHtzje(String htzje) {
		this.htzje = htzje;
	}

	public String getHtzqly() {
		return htzqly;
	}

	public void setHtzqly(String htzqly) {
		this.htzqly = htzqly;
	}

	public String getHtzqsj() {
		return htzqsj;
	}

	public void setHtzqsj(String htzqsj) {
		this.htzqsj = htzqsj;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getByny() {
		return byny;
	}

	public void setByny(String byny) {
		this.byny = byny;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getDkze() {
		return dkze;
	}

	public void setDkze(String dkze) {
		this.dkze = dkze;
	}

	public String getDqgzdw() {
		return dqgzdw;
	}

	public void setDqgzdw(String dqgzdw) {
		this.dqgzdw = dqgzdw;
	}

	public String getDqgzdwdh() {
		return dqgzdwdh;
	}

	public void setDqgzdwdh(String dqgzdwdh) {
		this.dqgzdwdh = dqgzdwdh;
	}

	public String getDqgzdwdz() {
		return dqgzdwdz;
	}

	public void setDqgzdwdz(String dqgzdwdz) {
		this.dqgzdwdz = dqgzdwdz;
	}

	public String getDqgzdwyb() {
		return dqgzdwyb;
	}

	public void setDqgzdwyb(String dqgzdwyb) {
		this.dqgzdwyb = dqgzdwyb;
	}

	public String getFqgzdw() {
		return fqgzdw;
	}

	public void setFqgzdw(String fqgzdw) {
		this.fqgzdw = fqgzdw;
	}

	public String getFqlxdh() {
		return fqlxdh;
	}

	public void setFqlxdh(String fqlxdh) {
		this.fqlxdh = fqlxdh;
	}

	public String getFqsfzh() {
		return fqsfzh;
	}

	public void setFqsfzh(String fqsfzh) {
		this.fqsfzh = fqsfzh;
	}

	public String getFqxm() {
		return fqxm;
	}

	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}

	public String getHt1_dkfs() {
		return ht1_dkfs;
	}

	public void setHt1_dkfs(String ht1_dkfs) {
		this.ht1_dkfs = ht1_dkfs;
	}

	public String getHt1_fzjgmc() {
		return ht1_fzjgmc;
	}

	public void setHt1_fzjgmc(String ht1_fzjgmc) {
		this.ht1_fzjgmc = ht1_fzjgmc;
	}

	public String getHt1_hkfs() {
		return ht1_hkfs;
	}

	public void setHt1_hkfs(String ht1_hkfs) {
		this.ht1_hkfs = ht1_hkfs;
	}

	public String getHt1_hkjzmc() {
		return ht1_hkjzmc;
	}

	public void setHt1_hkjzmc(String ht1_hkjzmc) {
		this.ht1_hkjzmc = ht1_hkjzmc;
	}

	public String getHt1_hkjzrq() {
		return ht1_hkjzrq;
	}

	public void setHt1_hkjzrq(String ht1_hkjzrq) {
		this.ht1_hkjzrq = ht1_hkjzrq;
	}

	public String getHt1_hkjzzh() {
		return ht1_hkjzzh;
	}

	public void setHt1_hkjzzh(String ht1_hkjzzh) {
		this.ht1_hkjzzh = ht1_hkjzzh;
	}

	public String getHt1_hkksrq() {
		return ht1_hkksrq;
	}

	public void setHt1_hkksrq(String ht1_hkksrq) {
		this.ht1_hkksrq = ht1_hkksrq;
	}

	public String getHt1_jbjrjg() {
		return ht1_jbjrjg;
	}

	public void setHt1_jbjrjg(String ht1_jbjrjg) {
		this.ht1_jbjrjg = ht1_jbjrjg;
	}

	public String getHt1_jby() {
		return ht1_jby;
	}

	public void setHt1_jby(String ht1_jby) {
		this.ht1_jby = ht1_jby;
	}

	public String getHt1_pzhz() {
		return ht1_pzhz;
	}

	public void setHt1_pzhz(String ht1_pzhz) {
		this.ht1_pzhz = ht1_pzhz;
	}

	public String getHt1_pzrq() {
		return ht1_pzrq;
	}

	public void setHt1_pzrq(String ht1_pzrq) {
		this.ht1_pzrq = ht1_pzrq;
	}

	public String getHt1_tqbz() {
		return ht1_tqbz;
	}

	public void setHt1_tqbz(String ht1_tqbz) {
		this.ht1_tqbz = ht1_tqbz;
	}

	public String getHt1_tqjzsj() {
		return ht1_tqjzsj;
	}

	public void setHt1_tqjzsj(String ht1_tqjzsj) {
		this.ht1_tqjzsj = ht1_tqjzsj;
	}

	public String getHt1_tqyhbxje() {
		return ht1_tqyhbxje;
	}

	public void setHt1_tqyhbxje(String ht1_tqyhbxje) {
		this.ht1_tqyhbxje = ht1_tqyhbxje;
	}

	public String getHt1_zje() {
		return ht1_zje;
	}

	public void setHt1_zje(String ht1_zje) {
		this.ht1_zje = ht1_zje;
	}

	public String getHt1_zqly() {
		return ht1_zqly;
	}

	public void setHt1_zqly(String ht1_zqly) {
		this.ht1_zqly = ht1_zqly;
	}

	public String getHt1_zqsj() {
		return ht1_zqsj;
	}

	public void setHt1_zqsj(String ht1_zqsj) {
		this.ht1_zqsj = ht1_zqsj;
	}

	public String getHt2_dkfs() {
		return ht2_dkfs;
	}

	public void setHt2_dkfs(String ht2_dkfs) {
		this.ht2_dkfs = ht2_dkfs;
	}

	public String getHt2_fzjgmc() {
		return ht2_fzjgmc;
	}

	public void setHt2_fzjgmc(String ht2_fzjgmc) {
		this.ht2_fzjgmc = ht2_fzjgmc;
	}

	public String getHt2_hkfs() {
		return ht2_hkfs;
	}

	public void setHt2_hkfs(String ht2_hkfs) {
		this.ht2_hkfs = ht2_hkfs;
	}

	public String getHt2_hkjzmc() {
		return ht2_hkjzmc;
	}

	public void setHt2_hkjzmc(String ht2_hkjzmc) {
		this.ht2_hkjzmc = ht2_hkjzmc;
	}

	public String getHt2_hkjzrq() {
		return ht2_hkjzrq;
	}

	public void setHt2_hkjzrq(String ht2_hkjzrq) {
		this.ht2_hkjzrq = ht2_hkjzrq;
	}

	public String getHt2_hkjzzh() {
		return ht2_hkjzzh;
	}

	public void setHt2_hkjzzh(String ht2_hkjzzh) {
		this.ht2_hkjzzh = ht2_hkjzzh;
	}

	public String getHt2_hkksrq() {
		return ht2_hkksrq;
	}

	public void setHt2_hkksrq(String ht2_hkksrq) {
		this.ht2_hkksrq = ht2_hkksrq;
	}

	public String getHt2_jbjrjg() {
		return ht2_jbjrjg;
	}

	public void setHt2_jbjrjg(String ht2_jbjrjg) {
		this.ht2_jbjrjg = ht2_jbjrjg;
	}

	public String getHt2_jby() {
		return ht2_jby;
	}

	public void setHt2_jby(String ht2_jby) {
		this.ht2_jby = ht2_jby;
	}

	public String getHt2_pzhz() {
		return ht2_pzhz;
	}

	public void setHt2_pzhz(String ht2_pzhz) {
		this.ht2_pzhz = ht2_pzhz;
	}

	public String getHt2_pzrq() {
		return ht2_pzrq;
	}

	public void setHt2_pzrq(String ht2_pzrq) {
		this.ht2_pzrq = ht2_pzrq;
	}

	public String getHt2_tqbz() {
		return ht2_tqbz;
	}

	public void setHt2_tqbz(String ht2_tqbz) {
		this.ht2_tqbz = ht2_tqbz;
	}

	public String getHt2_tqjzsj() {
		return ht2_tqjzsj;
	}

	public void setHt2_tqjzsj(String ht2_tqjzsj) {
		this.ht2_tqjzsj = ht2_tqjzsj;
	}

	public String getHt2_tqyhbxje() {
		return ht2_tqyhbxje;
	}

	public void setHt2_tqyhbxje(String ht2_tqyhbxje) {
		this.ht2_tqyhbxje = ht2_tqyhbxje;
	}

	public String getHt2_zje() {
		return ht2_zje;
	}

	public void setHt2_zje(String ht2_zje) {
		this.ht2_zje = ht2_zje;
	}

	public String getHt2_zqly() {
		return ht2_zqly;
	}

	public void setHt2_zqly(String ht2_zqly) {
		this.ht2_zqly = ht2_zqly;
	}

	public String getHt2_zqsj() {
		return ht2_zqsj;
	}

	public void setHt2_zqsj(String ht2_zqsj) {
		this.ht2_zqsj = ht2_zqsj;
	}

	public String getHt3_dkfs() {
		return ht3_dkfs;
	}

	public void setHt3_dkfs(String ht3_dkfs) {
		this.ht3_dkfs = ht3_dkfs;
	}

	public String getHt3_fzjgmc() {
		return ht3_fzjgmc;
	}

	public void setHt3_fzjgmc(String ht3_fzjgmc) {
		this.ht3_fzjgmc = ht3_fzjgmc;
	}

	public String getHt3_hkfs() {
		return ht3_hkfs;
	}

	public void setHt3_hkfs(String ht3_hkfs) {
		this.ht3_hkfs = ht3_hkfs;
	}

	public String getHt3_hkjzmc() {
		return ht3_hkjzmc;
	}

	public void setHt3_hkjzmc(String ht3_hkjzmc) {
		this.ht3_hkjzmc = ht3_hkjzmc;
	}

	public String getHt3_hkjzrq() {
		return ht3_hkjzrq;
	}

	public void setHt3_hkjzrq(String ht3_hkjzrq) {
		this.ht3_hkjzrq = ht3_hkjzrq;
	}

	public String getHt3_hkjzzh() {
		return ht3_hkjzzh;
	}

	public void setHt3_hkjzzh(String ht3_hkjzzh) {
		this.ht3_hkjzzh = ht3_hkjzzh;
	}

	public String getHt3_hkksrq() {
		return ht3_hkksrq;
	}

	public void setHt3_hkksrq(String ht3_hkksrq) {
		this.ht3_hkksrq = ht3_hkksrq;
	}

	public String getHt3_jbjrjg() {
		return ht3_jbjrjg;
	}

	public void setHt3_jbjrjg(String ht3_jbjrjg) {
		this.ht3_jbjrjg = ht3_jbjrjg;
	}

	public String getHt3_jby() {
		return ht3_jby;
	}

	public void setHt3_jby(String ht3_jby) {
		this.ht3_jby = ht3_jby;
	}

	public String getHt3_pzhz() {
		return ht3_pzhz;
	}

	public void setHt3_pzhz(String ht3_pzhz) {
		this.ht3_pzhz = ht3_pzhz;
	}

	public String getHt3_pzrq() {
		return ht3_pzrq;
	}

	public void setHt3_pzrq(String ht3_pzrq) {
		this.ht3_pzrq = ht3_pzrq;
	}

	public String getHt3_tqbz() {
		return ht3_tqbz;
	}

	public void setHt3_tqbz(String ht3_tqbz) {
		this.ht3_tqbz = ht3_tqbz;
	}

	public String getHt3_tqjzsj() {
		return ht3_tqjzsj;
	}

	public void setHt3_tqjzsj(String ht3_tqjzsj) {
		this.ht3_tqjzsj = ht3_tqjzsj;
	}

	public String getHt3_tqyhbxje() {
		return ht3_tqyhbxje;
	}

	public void setHt3_tqyhbxje(String ht3_tqyhbxje) {
		this.ht3_tqyhbxje = ht3_tqyhbxje;
	}

	public String getHt3_zje() {
		return ht3_zje;
	}

	public void setHt3_zje(String ht3_zje) {
		this.ht3_zje = ht3_zje;
	}

	public String getHt3_zqly() {
		return ht3_zqly;
	}

	public void setHt3_zqly(String ht3_zqly) {
		this.ht3_zqly = ht3_zqly;
	}

	public String getHt3_zqsj() {
		return ht3_zqsj;
	}

	public void setHt3_zqsj(String ht3_zqsj) {
		this.ht3_zqsj = ht3_zqsj;
	}

	public String getHt4_dkfs() {
		return ht4_dkfs;
	}

	public void setHt4_dkfs(String ht4_dkfs) {
		this.ht4_dkfs = ht4_dkfs;
	}

	public String getHt4_fzjgmc() {
		return ht4_fzjgmc;
	}

	public void setHt4_fzjgmc(String ht4_fzjgmc) {
		this.ht4_fzjgmc = ht4_fzjgmc;
	}

	public String getHt4_hkfs() {
		return ht4_hkfs;
	}

	public void setHt4_hkfs(String ht4_hkfs) {
		this.ht4_hkfs = ht4_hkfs;
	}

	public String getHt4_hkjzmc() {
		return ht4_hkjzmc;
	}

	public void setHt4_hkjzmc(String ht4_hkjzmc) {
		this.ht4_hkjzmc = ht4_hkjzmc;
	}

	public String getHt4_hkjzrq() {
		return ht4_hkjzrq;
	}

	public void setHt4_hkjzrq(String ht4_hkjzrq) {
		this.ht4_hkjzrq = ht4_hkjzrq;
	}

	public String getHt4_hkjzzh() {
		return ht4_hkjzzh;
	}

	public void setHt4_hkjzzh(String ht4_hkjzzh) {
		this.ht4_hkjzzh = ht4_hkjzzh;
	}

	public String getHt4_hkksrq() {
		return ht4_hkksrq;
	}

	public void setHt4_hkksrq(String ht4_hkksrq) {
		this.ht4_hkksrq = ht4_hkksrq;
	}

	public String getHt4_jbjrjg() {
		return ht4_jbjrjg;
	}

	public void setHt4_jbjrjg(String ht4_jbjrjg) {
		this.ht4_jbjrjg = ht4_jbjrjg;
	}

	public String getHt4_jby() {
		return ht4_jby;
	}

	public void setHt4_jby(String ht4_jby) {
		this.ht4_jby = ht4_jby;
	}

	public String getHt4_pzhz() {
		return ht4_pzhz;
	}

	public void setHt4_pzhz(String ht4_pzhz) {
		this.ht4_pzhz = ht4_pzhz;
	}

	public String getHt4_pzrq() {
		return ht4_pzrq;
	}

	public void setHt4_pzrq(String ht4_pzrq) {
		this.ht4_pzrq = ht4_pzrq;
	}

	public String getHt4_tqbz() {
		return ht4_tqbz;
	}

	public void setHt4_tqbz(String ht4_tqbz) {
		this.ht4_tqbz = ht4_tqbz;
	}

	public String getHt4_tqjzsj() {
		return ht4_tqjzsj;
	}

	public void setHt4_tqjzsj(String ht4_tqjzsj) {
		this.ht4_tqjzsj = ht4_tqjzsj;
	}

	public String getHt4_tqyhbxje() {
		return ht4_tqyhbxje;
	}

	public void setHt4_tqyhbxje(String ht4_tqyhbxje) {
		this.ht4_tqyhbxje = ht4_tqyhbxje;
	}

	public String getHt4_zje() {
		return ht4_zje;
	}

	public void setHt4_zje(String ht4_zje) {
		this.ht4_zje = ht4_zje;
	}

	public String getHt4_zqly() {
		return ht4_zqly;
	}

	public void setHt4_zqly(String ht4_zqly) {
		this.ht4_zqly = ht4_zqly;
	}

	public String getHt4_zqsj() {
		return ht4_zqsj;
	}

	public void setHt4_zqsj(String ht4_zqsj) {
		this.ht4_zqsj = ht4_zqsj;
	}

	public String getHth1() {
		return hth1;
	}

	public void setHth1(String hth1) {
		this.hth1 = hth1;
	}

	public String getHth2() {
		return hth2;
	}

	public void setHth2(String hth2) {
		this.hth2 = hth2;
	}

	public String getHth3() {
		return hth3;
	}

	public void setHth3(String hth3) {
		this.hth3 = hth3;
	}

	public String getHth4() {
		return hth4;
	}

	public void setHth4(String hth4) {
		this.hth4 = hth4;
	}

	public String getJtsr() {
		return jtsr;
	}

	public void setJtsr(String jtsr) {
		this.jtsr = jtsr;
	}

	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	public String getKsh() {
		return ksh;
	}

	public void setKsh(String ksh) {
		this.ksh = ksh;
	}

	public String getMqgzdw() {
		return mqgzdw;
	}

	public void setMqgzdw(String mqgzdw) {
		this.mqgzdw = mqgzdw;
	}

	public String getMqlxdh() {
		return mqlxdh;
	}

	public void setMqlxdh(String mqlxdh) {
		this.mqlxdh = mqlxdh;
	}

	public String getMqsfzh() {
		return mqsfzh;
	}

	public void setMqsfzh(String mqsfzh) {
		this.mqsfzh = mqsfzh;
	}

	public String getMqxm() {
		return mqxm;
	}

	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}

	public String getMzmc() {
		return mzmc;
	}

	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}

	public String getRxny() {
		return rxny;
	}

	public void setRxny(String rxny) {
		this.rxny = rxny;
	}

	public String getScbyqx() {
		return scbyqx;
	}

	public void setScbyqx(String scbyqx) {
		this.scbyqx = scbyqx;
	}

	public String getSedh() {
		return sedh;
	}

	public void setSedh(String sedh) {
		this.sedh = sedh;
	}

	public String getShfdks() {
		return shfdks;
	}

	public void setShfdks(String shfdks) {
		this.shfdks = shfdks;
	}

	public String getXfdks() {
		return xfdks;
	}

	public void setXfdks(String xfdks) {
		this.xfdks = xfdks;
	}

	public String getXmc() {
		return xmc;
	}

	public void setXmc(String xmc) {
		this.xmc = xmc;
	}

	public String getXslx() {
		return xslx;
	}

	public void setXslx(String xslx) {
		this.xslx = xslx;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getYhdkje() {
		return yhdkje;
	}

	public void setYhdkje(String yhdkje) {
		this.yhdkje = yhdkje;
	}

	public String getYxlxfs() {
		return yxlxfs;
	}

	public void setYxlxfs(String yxlxfs) {
		this.yxlxfs = yxlxfs;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public String getZsfdks() {
		return zsfdks;
	}

	public void setZsfdks(String zsfdks) {
		this.zsfdks = zsfdks;
	}

	public String getLxyy() {
		return lxyy;
	}

	public void setLxyy(String lxyy) {
		this.lxyy = lxyy;
	}

	public String getXydm1() {
		return xydm1;
	}

	public void setXydm1(String xydm1) {
		this.xydm1 = xydm1;
	}

	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public String getBzrq4() {
		return bzrq4;
	}

	public void setBzrq4(String bzrq4) {
		this.bzrq4 = bzrq4;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getXXCLYJ() {
		return XXCLYJ;
	}

	public void setXXCLYJ(String xxclyj) {
		XXCLYJ = xxclyj;
	}

	public String getYesNoEnd() {
		return yesNoEnd;
	}

	public void setYesNoEnd(String yesNoEnd) {
		this.yesNoEnd = yesNoEnd;
	}

	public String getJlcfjl() {
		return jlcfjl;
	}

	public void setJlcfjl(String jlcfjl) {
		this.jlcfjl = jlcfjl;
	}

	public String getJrgcdsj() {
		return jrgcdsj;
	}

	public void setJrgcdsj(String jrgcdsj) {
		this.jrgcdsj = jrgcdsj;
	}

	public String getJrgqtsj() {
		return jrgqtsj;
	}

	public void setJrgqtsj(String jrgqtsj) {
		this.jrgqtsj = jrgqtsj;
	}


	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getJtyb() {
		return jtyb;
	}

	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}

	public String getJtzd() {
		return jtzd;
	}

	public void setJtzd(String jtzd) {
		this.jtzd = jtzd;
	}	

	public String getRxqdw() {
		return rxqdw;
	}

	public void setRxqdw(String rxqdw) {
		this.rxqdw = rxqdw;
	}

	public String getWhcd() {
		return whcd;
	}

	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}

	public String getXx() {
		return xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public String getJkzk() {
		return jkzk;
	}

	public void setJkzk(String jkzk) {
		this.jkzk = jkzk;
	}

	public String getAh() {
		return ah;
	}

	public void setAh(String ah) {
		this.ah = ah;
	}

	public String getJtjjqk() {
		return jtjjqk;
	}

	public void setJtjjqk(String jtjjqk) {
		this.jtjjqk = jtjjqk;
	}

	public String getJtqkjj() {
		return jtqkjj;
	}

	public void setJtqkjj(String jtqkjj) {
		this.jtqkjj = jtqkjj;
	}

	public String getSfdk() {
		return sfdk;
	}

	public void setSfdk(String sfdk) {
		this.sfdk = sfdk;
	}

	public String getShgxdwdh1() {
		return shgxdwdh1;
	}

	public void setShgxdwdh1(String shgxdwdh1) {
		this.shgxdwdh1 = shgxdwdh1;
	}

	public String getShgxdwdh2() {
		return shgxdwdh2;
	}

	public void setShgxdwdh2(String shgxdwdh2) {
		this.shgxdwdh2 = shgxdwdh2;
	}

	public String getShgxgzdw1() {
		return shgxgzdw1;
	}

	public void setShgxgzdw1(String shgxgzdw1) {
		this.shgxgzdw1 = shgxgzdw1;
	}

	public String getShgxgzdw2() {
		return shgxgzdw2;
	}

	public void setShgxgzdw2(String shgxgzdw2) {
		this.shgxgzdw2 = shgxgzdw2;
	}

	public String getShgxsjhm1() {
		return shgxsjhm1;
	}

	public void setShgxsjhm1(String shgxsjhm1) {
		this.shgxsjhm1 = shgxsjhm1;
	}

	public String getShgxsjhm2() {
		return shgxsjhm2;
	}

	public void setShgxsjhm2(String shgxsjhm2) {
		this.shgxsjhm2 = shgxsjhm2;
	}

	public String getShgxxm1() {
		return shgxxm1;
	}

	public void setShgxxm1(String shgxxm1) {
		this.shgxxm1 = shgxxm1;
	}

	public String getShgxxm2() {
		return shgxxm2;
	}

	public void setShgxxm2(String shgxxm2) {
		this.shgxxm2 = shgxxm2;
	}

	public String getShgxzw1() {
		return shgxzw1;
	}

	public void setShgxzw1(String shgxzw1) {
		this.shgxzw1 = shgxzw1;
	}

	public String getShgxzw2() {
		return shgxzw2;
	}

	public void setShgxzw2(String shgxzw2) {
		this.shgxzw2 = shgxzw2;
	}

	public String getShgxgx1() {
		return shgxgx1;
	}

	public void setShgxgx1(String shgxgx1) {
		this.shgxgx1 = shgxgx1;
	}

	public String getShgxgx2() {
		return shgxgx2;
	}

	public void setShgxgx2(String shgxgx2) {
		this.shgxgx2 = shgxgx2;
	}

	public String getQsdh() {
		return qsdh;
	}

	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}

	public String getJtcy1_zy() {
		return jtcy1_zy;
	}

	public void setJtcy1_zy(String jtcy1_zy) {
		this.jtcy1_zy = jtcy1_zy;
	}

	public String getJtcy2_zy() {
		return jtcy2_zy;
	}

	public void setJtcy2_zy(String jtcy2_zy) {
		this.jtcy2_zy = jtcy2_zy;
	}

	public String getJtcy3_zy() {
		return jtcy3_zy;
	}

	public void setJtcy3_zy(String jtcy3_zy) {
		this.jtcy3_zy = jtcy3_zy;
	}

	public String getJtcy1_whcd() {
		return jtcy1_whcd;
	}

	public void setJtcy1_whcd(String jtcy1_whcd) {
		this.jtcy1_whcd = jtcy1_whcd;
	}

	public String getJtcy2_whcd() {
		return jtcy2_whcd;
	}

	public void setJtcy2_whcd(String jtcy2_whcd) {
		this.jtcy2_whcd = jtcy2_whcd;
	}

	public String getJtcy3_whcd() {
		return jtcy3_whcd;
	}

	public void setJtcy3_whcd(String jtcy3_whcd) {
		this.jtcy3_whcd = jtcy3_whcd;
	}


	public String getJtcy4_gzdz() {
		return jtcy4_gzdz;
	}

	public void setJtcy4_gzdz(String jtcy4_gzdz) {
		this.jtcy4_gzdz = jtcy4_gzdz;
	}

	public String getJtcy4_lxdh1() {
		return jtcy4_lxdh1;
	}

	public void setJtcy4_lxdh1(String jtcy4_lxdh1) {
		this.jtcy4_lxdh1 = jtcy4_lxdh1;
	}

	public String getJtcy4_lxdh2() {
		return jtcy4_lxdh2;
	}

	public void setJtcy4_lxdh2(String jtcy4_lxdh2) {
		this.jtcy4_lxdh2 = jtcy4_lxdh2;
	}

	public String getJtcy4_mz() {
		return jtcy4_mz;
	}

	public void setJtcy4_mz(String jtcy4_mz) {
		this.jtcy4_mz = jtcy4_mz;
	}

	public String getJtcy4_nl() {
		return jtcy4_nl;
	}

	public void setJtcy4_nl(String jtcy4_nl) {
		this.jtcy4_nl = jtcy4_nl;
	}

	public String getJtcy4_whcd() {
		return jtcy4_whcd;
	}

	public void setJtcy4_whcd(String jtcy4_whcd) {
		this.jtcy4_whcd = jtcy4_whcd;
	}

	public String getJtcy4_xm() {
		return jtcy4_xm;
	}

	public void setJtcy4_xm(String jtcy4_xm) {
		this.jtcy4_xm = jtcy4_xm;
	}

	public String getJtcy4_zw() {
		return jtcy4_zw;
	}

	public void setJtcy4_zw(String jtcy4_zw) {
		this.jtcy4_zw = jtcy4_zw;
	}

	public String getJtcy4_zy() {
		return jtcy4_zy;
	}

	public void setJtcy4_zy(String jtcy4_zy) {
		this.jtcy4_zy = jtcy4_zy;
	}

	public String getJtcy4_zzmm() {
		return jtcy4_zzmm;
	}

	public void setJtcy4_zzmm(String jtcy4_zzmm) {
		this.jtcy4_zzmm = jtcy4_zzmm;
	}



	public String getJtcy4_gx() {
		return jtcy4_gx;
	}

	public void setJtcy4_gx(String jtcy4_gx) {
		this.jtcy4_gx = jtcy4_gx;
	}

	public String getJtcy5_gx() {
		return jtcy5_gx;
	}

	public void setJtcy5_gx(String jtcy5_gx) {
		this.jtcy5_gx = jtcy5_gx;
	}

	public String getJtcy6_gx() {
		return jtcy6_gx;
	}

	public void setJtcy6_gx(String jtcy6_gx) {
		this.jtcy6_gx = jtcy6_gx;
	}

	public String getJtcy5_gzdz() {
		return jtcy5_gzdz;
	}

	public void setJtcy5_gzdz(String jtcy5_gzdz) {
		this.jtcy5_gzdz = jtcy5_gzdz;
	}

	public String getJtcy5_lxdh1() {
		return jtcy5_lxdh1;
	}

	public void setJtcy5_lxdh1(String jtcy5_lxdh1) {
		this.jtcy5_lxdh1 = jtcy5_lxdh1;
	}

	public String getJtcy5_lxdh2() {
		return jtcy5_lxdh2;
	}

	public void setJtcy5_lxdh2(String jtcy5_lxdh2) {
		this.jtcy5_lxdh2 = jtcy5_lxdh2;
	}

	public String getJtcy5_mz() {
		return jtcy5_mz;
	}

	public void setJtcy5_mz(String jtcy5_mz) {
		this.jtcy5_mz = jtcy5_mz;
	}

	public String getJtcy5_nl() {
		return jtcy5_nl;
	}

	public void setJtcy5_nl(String jtcy5_nl) {
		this.jtcy5_nl = jtcy5_nl;
	}

	public String getJtcy5_whcd() {
		return jtcy5_whcd;
	}

	public void setJtcy5_whcd(String jtcy5_whcd) {
		this.jtcy5_whcd = jtcy5_whcd;
	}

	public String getJtcy5_xm() {
		return jtcy5_xm;
	}

	public void setJtcy5_xm(String jtcy5_xm) {
		this.jtcy5_xm = jtcy5_xm;
	}

	public String getJtcy5_zw() {
		return jtcy5_zw;
	}

	public void setJtcy5_zw(String jtcy5_zw) {
		this.jtcy5_zw = jtcy5_zw;
	}

	public String getJtcy5_zy() {
		return jtcy5_zy;
	}

	public void setJtcy5_zy(String jtcy5_zy) {
		this.jtcy5_zy = jtcy5_zy;
	}

	public String getJtcy5_zzmm() {
		return jtcy5_zzmm;
	}

	public void setJtcy5_zzmm(String jtcy5_zzmm) {
		this.jtcy5_zzmm = jtcy5_zzmm;
	}



	public String getJtcy6_gzdz() {
		return jtcy6_gzdz;
	}

	public void setJtcy6_gzdz(String jtcy6_gzdz) {
		this.jtcy6_gzdz = jtcy6_gzdz;
	}

	public String getJtcy6_lxdh1() {
		return jtcy6_lxdh1;
	}

	public void setJtcy6_lxdh1(String jtcy6_lxdh1) {
		this.jtcy6_lxdh1 = jtcy6_lxdh1;
	}

	public String getJtcy6_lxdh2() {
		return jtcy6_lxdh2;
	}

	public void setJtcy6_lxdh2(String jtcy6_lxdh2) {
		this.jtcy6_lxdh2 = jtcy6_lxdh2;
	}

	public String getJtcy6_mz() {
		return jtcy6_mz;
	}

	public void setJtcy6_mz(String jtcy6_mz) {
		this.jtcy6_mz = jtcy6_mz;
	}

	public String getJtcy6_nl() {
		return jtcy6_nl;
	}

	public void setJtcy6_nl(String jtcy6_nl) {
		this.jtcy6_nl = jtcy6_nl;
	}

	public String getJtcy6_whcd() {
		return jtcy6_whcd;
	}

	public void setJtcy6_whcd(String jtcy6_whcd) {
		this.jtcy6_whcd = jtcy6_whcd;
	}

	public String getJtcy6_xm() {
		return jtcy6_xm;
	}

	public void setJtcy6_xm(String jtcy6_xm) {
		this.jtcy6_xm = jtcy6_xm;
	}

	public String getJtcy6_zw() {
		return jtcy6_zw;
	}

	public void setJtcy6_zw(String jtcy6_zw) {
		this.jtcy6_zw = jtcy6_zw;
	}

	public String getJtcy6_zy() {
		return jtcy6_zy;
	}

	public void setJtcy6_zy(String jtcy6_zy) {
		this.jtcy6_zy = jtcy6_zy;
	}

	public String getJtcy6_zzmm() {
		return jtcy6_zzmm;
	}

	public void setJtcy6_zzmm(String jtcy6_zzmm) {
		this.jtcy6_zzmm = jtcy6_zzmm;
	}

	public String getXxjl_nr1() {
		return xxjl_nr1;
	}

	public void setXxjl_nr1(String xxjl_nr1) {
		this.xxjl_nr1 = xxjl_nr1;
	}

	public String getXxjl_nr2() {
		return xxjl_nr2;
	}

	public void setXxjl_nr2(String xxjl_nr2) {
		this.xxjl_nr2 = xxjl_nr2;
	}

	public String getXxjl_nr3() {
		return xxjl_nr3;
	}

	public void setXxjl_nr3(String xxjl_nr3) {
		this.xxjl_nr3 = xxjl_nr3;
	}

	public String getXxjl_nr4() {
		return xxjl_nr4;
	}

	public void setXxjl_nr4(String xxjl_nr4) {
		this.xxjl_nr4 = xxjl_nr4;
	}

	public String getXxjl_nr5() {
		return xxjl_nr5;
	}

	public void setXxjl_nr5(String xxjl_nr5) {
		this.xxjl_nr5 = xxjl_nr5;
	}

	public String getXxjl_nyr1() {
		return xxjl_nyr1;
	}

	public void setXxjl_nyr1(String xxjl_nyr1) {
		this.xxjl_nyr1 = xxjl_nyr1;
	}

	public String getXxjl_nyr2() {
		return xxjl_nyr2;
	}

	public void setXxjl_nyr2(String xxjl_nyr2) {
		this.xxjl_nyr2 = xxjl_nyr2;
	}

	public String getXxjl_nyr3() {
		return xxjl_nyr3;
	}

	public void setXxjl_nyr3(String xxjl_nyr3) {
		this.xxjl_nyr3 = xxjl_nyr3;
	}

	public String getXxjl_nyr4() {
		return xxjl_nyr4;
	}

	public void setXxjl_nyr4(String xxjl_nyr4) {
		this.xxjl_nyr4 = xxjl_nyr4;
	}

	public String getXxjl_nyr5() {
		return xxjl_nyr5;
	}

	public void setXxjl_nyr5(String xxjl_nyr5) {
		this.xxjl_nyr5 = xxjl_nyr5;
	}

	public String getXxjl_zmr1() {
		return xxjl_zmr1;
	}

	public void setXxjl_zmr1(String xxjl_zmr1) {
		this.xxjl_zmr1 = xxjl_zmr1;
	}

	public String getXxjl_zmr2() {
		return xxjl_zmr2;
	}

	public void setXxjl_zmr2(String xxjl_zmr2) {
		this.xxjl_zmr2 = xxjl_zmr2;
	}

	public String getXxjl_zmr3() {
		return xxjl_zmr3;
	}

	public void setXxjl_zmr3(String xxjl_zmr3) {
		this.xxjl_zmr3 = xxjl_zmr3;
	}

	public String getXxjl_zmr4() {
		return xxjl_zmr4;
	}

	public void setXxjl_zmr4(String xxjl_zmr4) {
		this.xxjl_zmr4 = xxjl_zmr4;
	}

	public String getXxjl_zmr5() {
		return xxjl_zmr5;
	}

	public void setXxjl_zmr5(String xxjl_zmr5) {
		this.xxjl_zmr5 = xxjl_zmr5;
	}

	public String getXxjl_zw1() {
		return xxjl_zw1;
	}

	public void setXxjl_zw1(String xxjl_zw1) {
		this.xxjl_zw1 = xxjl_zw1;
	}

	public String getXxjl_zw2() {
		return xxjl_zw2;
	}

	public void setXxjl_zw2(String xxjl_zw2) {
		this.xxjl_zw2 = xxjl_zw2;
	}

	public String getXxjl_zw3() {
		return xxjl_zw3;
	}

	public void setXxjl_zw3(String xxjl_zw3) {
		this.xxjl_zw3 = xxjl_zw3;
	}

	public String getXxjl_zw4() {
		return xxjl_zw4;
	}

	public void setXxjl_zw4(String xxjl_zw4) {
		this.xxjl_zw4 = xxjl_zw4;
	}

	public String getXxjl_zw5() {
		return xxjl_zw5;
	}

	public void setXxjl_zw5(String xxjl_zw5) {
		this.xxjl_zw5 = xxjl_zw5;
	}

	public String getSyd() {
		return syd;
	}

	public void setSyd(String syd) {
		this.syd = syd;
	}

	public String getDhqh() {
		return dhqh;
	}

	public void setDhqh(String dhqh) {
		this.dhqh = dhqh;
	}

	public String getSj() {
		return sj;
	}

	public String getLydq() {
		return lydq;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public void setLydq(String lydq) {
		this.lydq = lydq;
	}

	public String getJtcy10_gx() {
		return jtcy10_gx;
	}

	public void setJtcy10_gx(String jtcy10_gx) {
		this.jtcy10_gx = jtcy10_gx;
	}

	public String getJtcy10_gzdz() {
		return jtcy10_gzdz;
	}

	public void setJtcy10_gzdz(String jtcy10_gzdz) {
		this.jtcy10_gzdz = jtcy10_gzdz;
	}

	public String getJtcy10_lxdh1() {
		return jtcy10_lxdh1;
	}

	public void setJtcy10_lxdh1(String jtcy10_lxdh1) {
		this.jtcy10_lxdh1 = jtcy10_lxdh1;
	}

	public String getJtcy10_lxdh2() {
		return jtcy10_lxdh2;
	}

	public void setJtcy10_lxdh2(String jtcy10_lxdh2) {
		this.jtcy10_lxdh2 = jtcy10_lxdh2;
	}

	public String getJtcy10_mz() {
		return jtcy10_mz;
	}

	public void setJtcy10_mz(String jtcy10_mz) {
		this.jtcy10_mz = jtcy10_mz;
	}

	public String getJtcy10_nl() {
		return jtcy10_nl;
	}

	public void setJtcy10_nl(String jtcy10_nl) {
		this.jtcy10_nl = jtcy10_nl;
	}

	public String getJtcy10_whcd() {
		return jtcy10_whcd;
	}

	public void setJtcy10_whcd(String jtcy10_whcd) {
		this.jtcy10_whcd = jtcy10_whcd;
	}

	public String getJtcy10_xm() {
		return jtcy10_xm;
	}

	public void setJtcy10_xm(String jtcy10_xm) {
		this.jtcy10_xm = jtcy10_xm;
	}

	public String getJtcy10_zw() {
		return jtcy10_zw;
	}

	public void setJtcy10_zw(String jtcy10_zw) {
		this.jtcy10_zw = jtcy10_zw;
	}

	public String getJtcy10_zy() {
		return jtcy10_zy;
	}

	public void setJtcy10_zy(String jtcy10_zy) {
		this.jtcy10_zy = jtcy10_zy;
	}

	public String getJtcy10_zzmm() {
		return jtcy10_zzmm;
	}

	public void setJtcy10_zzmm(String jtcy10_zzmm) {
		this.jtcy10_zzmm = jtcy10_zzmm;
	}

	public String getJtcy7_gx() {
		return jtcy7_gx;
	}

	public void setJtcy7_gx(String jtcy7_gx) {
		this.jtcy7_gx = jtcy7_gx;
	}

	public String getJtcy7_gzdz() {
		return jtcy7_gzdz;
	}

	public void setJtcy7_gzdz(String jtcy7_gzdz) {
		this.jtcy7_gzdz = jtcy7_gzdz;
	}

	public String getJtcy7_lxdh1() {
		return jtcy7_lxdh1;
	}

	public void setJtcy7_lxdh1(String jtcy7_lxdh1) {
		this.jtcy7_lxdh1 = jtcy7_lxdh1;
	}

	public String getJtcy7_lxdh2() {
		return jtcy7_lxdh2;
	}

	public void setJtcy7_lxdh2(String jtcy7_lxdh2) {
		this.jtcy7_lxdh2 = jtcy7_lxdh2;
	}

	public String getJtcy7_mz() {
		return jtcy7_mz;
	}

	public void setJtcy7_mz(String jtcy7_mz) {
		this.jtcy7_mz = jtcy7_mz;
	}

	public String getJtcy7_nl() {
		return jtcy7_nl;
	}

	public void setJtcy7_nl(String jtcy7_nl) {
		this.jtcy7_nl = jtcy7_nl;
	}

	public String getJtcy7_whcd() {
		return jtcy7_whcd;
	}

	public void setJtcy7_whcd(String jtcy7_whcd) {
		this.jtcy7_whcd = jtcy7_whcd;
	}

	public String getJtcy7_xm() {
		return jtcy7_xm;
	}

	public void setJtcy7_xm(String jtcy7_xm) {
		this.jtcy7_xm = jtcy7_xm;
	}

	public String getJtcy7_zw() {
		return jtcy7_zw;
	}

	public void setJtcy7_zw(String jtcy7_zw) {
		this.jtcy7_zw = jtcy7_zw;
	}

	public String getJtcy7_zy() {
		return jtcy7_zy;
	}

	public void setJtcy7_zy(String jtcy7_zy) {
		this.jtcy7_zy = jtcy7_zy;
	}

	public String getJtcy7_zzmm() {
		return jtcy7_zzmm;
	}

	public void setJtcy7_zzmm(String jtcy7_zzmm) {
		this.jtcy7_zzmm = jtcy7_zzmm;
	}

	public String getJtcy8_gx() {
		return jtcy8_gx;
	}

	public void setJtcy8_gx(String jtcy8_gx) {
		this.jtcy8_gx = jtcy8_gx;
	}

	public String getJtcy8_gzdz() {
		return jtcy8_gzdz;
	}

	public void setJtcy8_gzdz(String jtcy8_gzdz) {
		this.jtcy8_gzdz = jtcy8_gzdz;
	}

	public String getJtcy8_lxdh1() {
		return jtcy8_lxdh1;
	}

	public void setJtcy8_lxdh1(String jtcy8_lxdh1) {
		this.jtcy8_lxdh1 = jtcy8_lxdh1;
	}

	public String getJtcy8_lxdh2() {
		return jtcy8_lxdh2;
	}

	public void setJtcy8_lxdh2(String jtcy8_lxdh2) {
		this.jtcy8_lxdh2 = jtcy8_lxdh2;
	}

	public String getJtcy8_mz() {
		return jtcy8_mz;
	}

	public void setJtcy8_mz(String jtcy8_mz) {
		this.jtcy8_mz = jtcy8_mz;
	}

	public String getJtcy8_nl() {
		return jtcy8_nl;
	}

	public void setJtcy8_nl(String jtcy8_nl) {
		this.jtcy8_nl = jtcy8_nl;
	}

	public String getJtcy8_whcd() {
		return jtcy8_whcd;
	}

	public void setJtcy8_whcd(String jtcy8_whcd) {
		this.jtcy8_whcd = jtcy8_whcd;
	}

	public String getJtcy8_xm() {
		return jtcy8_xm;
	}

	public void setJtcy8_xm(String jtcy8_xm) {
		this.jtcy8_xm = jtcy8_xm;
	}

	public String getJtcy8_zw() {
		return jtcy8_zw;
	}

	public void setJtcy8_zw(String jtcy8_zw) {
		this.jtcy8_zw = jtcy8_zw;
	}

	public String getJtcy8_zy() {
		return jtcy8_zy;
	}

	public void setJtcy8_zy(String jtcy8_zy) {
		this.jtcy8_zy = jtcy8_zy;
	}

	public String getJtcy8_zzmm() {
		return jtcy8_zzmm;
	}

	public void setJtcy8_zzmm(String jtcy8_zzmm) {
		this.jtcy8_zzmm = jtcy8_zzmm;
	}

	public String getJtcy9_gx() {
		return jtcy9_gx;
	}

	public void setJtcy9_gx(String jtcy9_gx) {
		this.jtcy9_gx = jtcy9_gx;
	}

	public String getJtcy9_gzdz() {
		return jtcy9_gzdz;
	}

	public void setJtcy9_gzdz(String jtcy9_gzdz) {
		this.jtcy9_gzdz = jtcy9_gzdz;
	}

	public String getJtcy9_lxdh1() {
		return jtcy9_lxdh1;
	}

	public void setJtcy9_lxdh1(String jtcy9_lxdh1) {
		this.jtcy9_lxdh1 = jtcy9_lxdh1;
	}

	public String getJtcy9_lxdh2() {
		return jtcy9_lxdh2;
	}

	public void setJtcy9_lxdh2(String jtcy9_lxdh2) {
		this.jtcy9_lxdh2 = jtcy9_lxdh2;
	}

	public String getJtcy9_mz() {
		return jtcy9_mz;
	}

	public void setJtcy9_mz(String jtcy9_mz) {
		this.jtcy9_mz = jtcy9_mz;
	}

	public String getJtcy9_nl() {
		return jtcy9_nl;
	}

	public void setJtcy9_nl(String jtcy9_nl) {
		this.jtcy9_nl = jtcy9_nl;
	}

	public String getJtcy9_whcd() {
		return jtcy9_whcd;
	}

	public void setJtcy9_whcd(String jtcy9_whcd) {
		this.jtcy9_whcd = jtcy9_whcd;
	}

	public String getJtcy9_xm() {
		return jtcy9_xm;
	}

	public void setJtcy9_xm(String jtcy9_xm) {
		this.jtcy9_xm = jtcy9_xm;
	}

	public String getJtcy9_zw() {
		return jtcy9_zw;
	}

	public void setJtcy9_zw(String jtcy9_zw) {
		this.jtcy9_zw = jtcy9_zw;
	}

	public String getJtcy9_zy() {
		return jtcy9_zy;
	}

	public void setJtcy9_zy(String jtcy9_zy) {
		this.jtcy9_zy = jtcy9_zy;
	}

	public String getJtcy9_zzmm() {
		return jtcy9_zzmm;
	}

	public void setJtcy9_zzmm(String jtcy9_zzmm) {
		this.jtcy9_zzmm = jtcy9_zzmm;
	}

	public String getMtzgxs() {
		return mtzgxs;
	}

	public void setMtzgxs(String mtzgxs) {
		this.mtzgxs = mtzgxs;
	}

	public String getMxsbc() {
		return mxsbc;
	}

	public void setMxsbc(String mxsbc) {
		this.mxsbc = mxsbc;
	}

	public String getMyzgxs() {
		return myzgxs;
	}

	public void setMyzgxs(String myzgxs) {
		this.myzgxs = myzgxs;
	}

	public String getZnszcj() {
		return znszcj;
	}

	public void setZnszcj(String znszcj) {
		this.znszcj = znszcj;
	}
	
		public String getJjf() {
		return jjf;
	}

	public void setJjf(String jjf) {
		this.jjf = jjf;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getQk() {
		return qk;
	}

	public void setQk(String qk) {
		this.qk = qk;
	}
	//通用
	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
    

	public String getJxjdm1() {
		return jxjdm1;
	}

	public void setJxjdm1(String jxjdm1) {
		this.jxjdm1 = jxjdm1;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String[] getKeyval() {
		return keyval;
	}

	public void setKeyval(String[] keyval) {
		this.keyval = keyval;
	}
	public String getKxf1() {
		return kxf1;
}
	public void setKxf1(String kxf1) {
		this.kxf1 = kxf1;
	}

	public String getKxf2() {
		return kxf2;
	}

	public void setKxf2(String kxf2) {
		this.kxf2 = kxf2;
	}

	public String getKxf3() {
		return kxf3;
	}

	public void setKxf3(String kxf3) {
		this.kxf3 = kxf3;
	}

	public String getKxf4() {
		return kxf4;
	}

	public void setKxf4(String kxf4) {
		this.kxf4 = kxf4;
	}

	public String getKxf5() {
		return kxf5;
	}

	public void setKxf5(String kxf5) {
		this.kxf5 = kxf5;
	}

	public String getKxf6() {
		return kxf6;
	}

	public void setKxf6(String kxf6) {
		this.kxf6 = kxf6;
	}

	public String getKxf7() {
		return kxf7;
	}

	public void setKxf7(String kxf7) {
		this.kxf7 = kxf7;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getJtcy1_gx() {
		return jtcy1_gx;
	}

	public void setJtcy1_gx(String jtcy1_gx) {
		this.jtcy1_gx = jtcy1_gx;
	}

	public String getJtcy2_gx() {
		return jtcy2_gx;
	}

	public void setJtcy2_gx(String jtcy2_gx) {
		this.jtcy2_gx = jtcy2_gx;
	}

	public String getJtcy3_gx() {
		return jtcy3_gx;
	}

	public void setJtcy3_gx(String jtcy3_gx) {
		this.jtcy3_gx = jtcy3_gx;
	}

	public String getJtcy1_sfzh() {
		return jtcy1_sfzh;
	}

	public void setJtcy1_sfzh(String jtcy1_sfzh) {
		this.jtcy1_sfzh = jtcy1_sfzh;
	}

	public String getJtcy2_sfzh() {
		return jtcy2_sfzh;
	}

	public void setJtcy2_sfzh(String jtcy2_sfzh) {
		this.jtcy2_sfzh = jtcy2_sfzh;
	}

	public String getJtcy3_sfzh() {
		return jtcy3_sfzh;
	}

	public void setJtcy3_sfzh(String jtcy3_sfzh) {
		this.jtcy3_sfzh = jtcy3_sfzh;
	}

	public String getBxcc() {
		return bxcc;
	}

	public void setBxcc(String bxcc) {
		this.bxcc = bxcc;
	}

	public String getCcrq() {
		return ccrq;
	}

	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getKlc() {
		return klc;
	}

	public void setKlc(String klc) {
		this.klc = klc;
	}

	public String getKsy() {
		return ksy;
	}

	public void setKsy(String ksy) {
		this.ksy = ksy;
	}

	public String getKwz() {
		return kwz;
	}

	public void setKwz(String kwz) {
		this.kwz = kwz;
	}

	public String getJjzk() {
		return jjzk;
	}

	public void setJjzk(String jjzk) {
		this.jjzk = jjzk;
	}

	public String getByxx() {
		return byxx;
	}

	public void setByxx(String byxx) {
		this.byxx = byxx;
	}
	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getQslb() {
		return qslb;
	}

	public void setQslb(String qslb) {
		this.qslb = qslb;
	}

	public String getFdyxm() {
		return fdyxm;
	}

	public void setFdyxm(String fdyxm) {
		this.fdyxm = fdyxm;
	}

	public String getRxrq() {
		return rxrq;
	}

	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}

	public String getGkcj() {
		return gkcj;
	}

	public void setGkcj(String gkcj) {
		this.gkcj = gkcj;
	}

	public String getQqhm() {
		return qqhm;
	}

	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}

	public String getFfsj() {
		return ffsj;
	}

	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}

	public String getGjzxdk() {
		return gjzxdk;
	}

	public void setGjzxdk(String gjzxdk) {
		this.gjzxdk = gjzxdk;
	}

	public String getGzjl() {
		return gzjl;
	}

	public void setGzjl(String gzjl) {
		this.gzjl = gzjl;
	}

	public String getLdyx() {
		return ldyx;
	}

	public void setLdyx(String ldyx) {
		this.ldyx = ldyx;
	}

	public String getPkdj() {
		return pkdj;
	}

	public void setPkdj(String pkdj) {
		this.pkdj = pkdj;
	}

	public String getXg() {
		return xg;
	}

	public void setXg(String xg) {
		this.xg = xg;
	}

	public String getYhtc() {
		return yhtc;
	}

	public void setYhtc(String yhtc) {
		this.yhtc = yhtc;
	}

	public String getZzqk() {
		return zzqk;
	}

	public void setZzqk(String zzqk) {
		this.zzqk = zzqk;
	}

	public String getJtysr() {
		return jtysr;
	}

	public void setJtysr(String jtysr) {
		this.jtysr = jtysr;
	}

	public String getJtcy() {
		return jtcy;
	}

	public void setJtcy(String jtcy) {
		this.jtcy = jtcy;
	}

	public String getGwmc() {
		return gwmc;
	}

	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}

	public String getGwsl() {
		return gwsl;
	}

	public void setGwsl(String gwsl) {
		this.gwsl = gwsl;
	}

	public String getGwtsyq() {
		return gwtsyq;
	}

	public void setGwtsyq(String gwtsyq) {
		this.gwtsyq = gwtsyq;
	}

	public String getGwxz() {
		return gwxz;
	}

	public void setGwxz(String gwxz) {
		this.gwxz = gwxz;
	}

	public String getGznr() {
		return gznr;
	}

	public void setGznr(String gznr) {
		this.gznr = gznr;
	}

	public String getRyyq() {
		return ryyq;
	}

	public void setRyyq(String ryyq) {
		this.ryyq = ryyq;
	}

	public String getSqdwyj() {
		return sqdwyj;
	}

	public void setSqdwyj(String sqdwyj) {
		this.sqdwyj = sqdwyj;
	}

	public String getXyboy() {
		return xyboy;
	}

	public void setXyboy(String xyboy) {
		this.xyboy = xyboy;
	}

	public String getXygirl() {
		return xygirl;
	}

	public void setXygirl(String xygirl) {
		this.xygirl = xygirl;
	}

	public String getHkszd() {
		return hkszd;
	}

	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}

	public String getHkxz() {
		return hkxz;
	}

	public void setHkxz(String hkxz) {
		this.hkxz = hkxz;
	}

	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	public String getZyjb() {
		return zyjb;
	}

	public void setZyjb(String zyjb) {
		this.zyjb = zyjb;
	}

	public String getJtrjsr() {
		return jtrjsr;
	}

	public void setJtrjsr(String jtrjsr) {
		this.jtrjsr = jtrjsr;
	}

	public String getJtzsr() {
		return jtzsr;
	}

	public void setJtzsr(String jtzsr) {
		this.jtzsr = jtzsr;
	}

	public String getSfdb() {
		return sfdb;
	}

	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}

	public String getSrly() {
		return srly;
	}

	public void setSrly(String srly) {
		this.srly = srly;
	}

	public String getJtdzs() {
		return jtdzs;
	}

	public void setJtdzs(String jtdzs) {
		this.jtdzs = jtdzs;
	}

	public String getJtdzx() {
		return jtdzx;
	}

	public void setJtdzx(String jtdzx) {
		this.jtdzx = jtdzx;
	}

	public String getSsld() {
		return ssld;
	}

	public void setSsld(String ssld) {
		this.ssld = ssld;
	}

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}
	
	
	public String getMzdm() {
		return mzdm;
	}

	public void setMzdm(String mzdm) {
		this.mzdm = mzdm;
	}

	public String getZzmmdm() {
		return zzmmdm;
	}

	public void setZzmmdm(String zzmmdm) {
		this.zzmmdm = zzmmdm;
	}

	public String getJtcyxm() {
		return jtcyxm;
	}

	public void setJtcyxm(String jtcyxm) {
		this.jtcyxm = jtcyxm;
	}

	public String getSfzsb() {
		return sfzsb;
	}

	public void setSfzsb(String sfzsb) {
		this.sfzsb = sfzsb;
	}

	public String getJtcy1_cw() {
		return jtcy1_cw;
	}

	public void setJtcy1_cw(String jtcy1_cw) {
		this.jtcy1_cw = jtcy1_cw;
	}

	public String getJtcy2_cw() {
		return jtcy2_cw;
	}

	public void setJtcy2_cw(String jtcy2_cw) {
		this.jtcy2_cw = jtcy2_cw;
	}

	public String getJtcy3_cw() {
		return jtcy3_cw;
	}

	public void setJtcy3_cw(String jtcy3_cw) {
		this.jtcy3_cw = jtcy3_cw;
	}

	public String getJtcy4_cw() {
		return jtcy4_cw;
	}

	public void setJtcy4_cw(String jtcy4_cw) {
		this.jtcy4_cw = jtcy4_cw;
	}

	public String getJtcy1_gzdwjzw() {
		return jtcy1_gzdwjzw;
	}

	public void setJtcy1_gzdwjzw(String jtcy1_gzdwjzw) {
		this.jtcy1_gzdwjzw = jtcy1_gzdwjzw;
	}

	public String getJtcy2_gzdwjzw() {
		return jtcy2_gzdwjzw;
	}

	public void setJtcy2_gzdwjzw(String jtcy2_gzdwjzw) {
		this.jtcy2_gzdwjzw = jtcy2_gzdwjzw;
	}

	public String getJtcy3_gzdwjzw() {
		return jtcy3_gzdwjzw;
	}

	public void setJtcy3_gzdwjzw(String jtcy3_gzdwjzw) {
		this.jtcy3_gzdwjzw = jtcy3_gzdwjzw;
	}

	public String getJtcy4_gzdwjzw() {
		return jtcy4_gzdwjzw;
	}

	public void setJtcy4_gzdwjzw(String jtcy4_gzdwjzw) {
		this.jtcy4_gzdwjzw = jtcy4_gzdwjzw;
	}

	public String getJtcy1_nsr() {
		return jtcy1_nsr;
	}

	public void setJtcy1_nsr(String jtcy1_nsr) {
		this.jtcy1_nsr = jtcy1_nsr;
	}

	public String getJtcy2_nsr() {
		return jtcy2_nsr;
	}

	public void setJtcy2_nsr(String jtcy2_nsr) {
		this.jtcy2_nsr = jtcy2_nsr;
	}

	public String getJtcy3_nsr() {
		return jtcy3_nsr;
	}

	public void setJtcy3_nsr(String jtcy3_nsr) {
		this.jtcy3_nsr = jtcy3_nsr;
	}

	public String getJtcy4_nsr() {
		return jtcy4_nsr;
	}

	public void setJtcy4_nsr(String jtcy4_nsr) {
		this.jtcy4_nsr = jtcy4_nsr;
	}

	public String getJtzyjjly() {
		return jtzyjjly;
	}

	public void setJtzyjjly(String jtzyjjly) {
		this.jtzyjjly = jtzyjjly;
	}

	public String getJtcy1_jtdz() {
		return jtcy1_jtdz;
	}

	public void setJtcy1_jtdz(String jtcy1_jtdz) {
		this.jtcy1_jtdz = jtcy1_jtdz;
	}

	public String getJtcy2_jtdz() {
		return jtcy2_jtdz;
	}

	public void setJtcy2_jtdz(String jtcy2_jtdz) {
		this.jtcy2_jtdz = jtcy2_jtdz;
	}

	public String getJtcy3_jtdz() {
		return jtcy3_jtdz;
	}

	public void setJtcy3_jtdz(String jtcy3_jtdz) {
		this.jtcy3_jtdz = jtcy3_jtdz;
	}

	public String getXxyj() {
		return xxyj;
	}

	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}

	public String getXyyj() {
		return xyyj;
	}

	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}

	public String getSfzfx() {
		return sfzfx;
	}

	public void setSfzfx(String sfzfx) {
		this.sfzfx = sfzfx;
	}

	public String getZjdm() {
		return zjdm;
	}

	public void setZjdm(String zjdm) {
		this.zjdm = zjdm;
	}
	
	public String getKxdyt() {
		return kxdyt;
	}

	public void setKxdyt(String kxdyt) {
		this.kxdyt = kxdyt;
	}

	public String getXqzs() {
		return xqzs;
	}

	public void setXqzs(String xqzs) {
		this.xqzs = xqzs;
	}

	public String getBzffny() {
		return bzffny;
	}

	public void setBzffny(String bzffny) {
		this.bzffny = bzffny;
	}
	
	public String getGlsjTable() {
		return glsjTable;
	}

	public void setGlsjTable(String glsjTable) {
		this.glsjTable = glsjTable;
	}

	public String getXdwyj() {
		return xdwyj;
	}

	public void setXdwyj(String xdwyj) {
		this.xdwyj = xdwyj;
	}

	public String getSqdw() {
		return sqdw;
	}

	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}

	public String getSfzx() {
		return sfzx;
	}

	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}

	public String getHffy() {
		return hffy;
	}

	public void setHffy(String hffy) {
		this.hffy = hffy;
	}

	public String getLpje() {
		return lpje;
	}

	public void setLpje(String lpje) {
		this.lpje = lpje;
	}
	
	public String getBjbl() {
		return bjbl;
	}

	public void setBjbl(String bjbl) {
		this.bjbl = bjbl;
	}

	public String getPjf() {
		return pjf;
	}

	public void setPjf(String pjf) {
		this.pjf = pjf;
	}
	public String getZbrlx() {
		return zbrlx;
	}

	public void setZbrlx(String zbrlx) {
		this.zbrlx = zbrlx;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getJgshen() {
		return jgshen;
	}

	public void setJgshen(String jgshen) {
		this.jgshen = jgshen;
	}

	public String getJgshi() {
		return jgshi;
	}

	public void setJgshi(String jgshi) {
		this.jgshi = jgshi;
	}

	public String getJgxian() {
		return jgxian;
	}

	public void setJgxian(String jgxian) {
		this.jgxian = jgxian;
	}

	public String getDrms() {
		return drms;
	}

	public void setDrms(String drms) {
		this.drms = drms;
	}

	public String getBjyjl() {
		return bjyjl;
	}

	public void setBjyjl(String bjyjl) {
		this.bjyjl = bjyjl;
	}

	public String getBxlx() {
		return bxlx;
	}

	public void setBxlx(String bxlx) {
		this.bxlx = bxlx;
	}

	public String getBxxs() {
		return bxxs;
	}

	public void setBxxs(String bxxs) {
		this.bxxs = bxxs;
	}

	public String getCsd() {
		return csd;
	}

	public void setCsd(String csd) {
		this.csd = csd;
	}

	public String getDybj() {
		return dybj;
	}

	public void setDybj(String dybj) {
		this.dybj = dybj;
	}

	public String getFxzy() {
		return fxzy;
	}

	public void setFxzy(String fxzy) {
		this.fxzy = fxzy;
	}

	public String getFxzyfx() {
		return fxzyfx;
	}

	public void setFxzyfx(String fxzyfx) {
		this.fxzyfx = fxzyfx;
	}

	public String getPyfx() {
		return pyfx;
	}

	public void setPyfx(String pyfx) {
		this.pyfx = pyfx;
	}

	public String getShbj() {
		return shbj;
	}

	public void setShbj(String shbj) {
		this.shbj = shbj;
	}

	public String getThbs() {
		return thbs;
	}

	public void setThbs(String thbs) {
		this.thbs = thbs;
	}

	public String getXw() {
		return xw;
	}

	public void setXw(String xw) {
		this.xw = xw;
	}

	public String getXwzsbh() {
		return xwzsbh;
	}

	public void setXwzsbh(String xwzsbh) {
		this.xwzsbh = xwzsbh;
	}

	public String getXwzsxlh() {
		return xwzsxlh;
	}

	public void setXwzsxlh(String xwzsxlh) {
		this.xwzsxlh = xwzsxlh;
	}

	public String getXxszd() {
		return xxszd;
	}

	public void setXxszd(String xxszd) {
		this.xxszd = xxszd;
	}

	public String getXxxs() {
		return xxxs;
	}

	public void setXxxs(String xxxs) {
		this.xxxs = xxxs;
	}

	public String getXzxm() {
		return xzxm;
	}

	public void setXzxm(String xzxm) {
		this.xzxm = xzxm;
	}

	public String getZsbh() {
		return zsbh;
	}

	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}

	public String getZsjj() {
		return zsjj;
	}

	public void setZsjj(String zsjj) {
		this.zsjj = zsjj;
	}

	public String getZyfx() {
		return zyfx;
	}

	public void setZyfx(String zyfx) {
		this.zyfx = zyfx;
	}

	public String getZylb() {
		return zylb;
	}

	public void setZylb(String zylb) {
		this.zylb = zylb;
	}

	public String getZsxlh() {
		return zsxlh;
	}

	public void setZsxlh(String zsxlh) {
		this.zsxlh = zsxlh;
	}

	public String getWmqsbz() {
		return wmqsbz;
	}

	public void setWmqsbz(String wmqsbz) {
		this.wmqsbz = wmqsbz;
	}

	public String getGzsj() {
		return gzsj;
	}

	public void setGzsj(String gzsj) {
		this.gzsj = gzsj;
	}

	public String getGzjssj() {
		return gzjssj;
	}

	public void setGzjssj(String gzjssj) {
		this.gzjssj = gzjssj;
	}

	public String getGzkssj() {
		return gzkssj;
	}

	public void setGzkssj(String gzkssj) {
		this.gzkssj = gzkssj;
	}

	public String getJybcbz() {
		return jybcbz;
	}

	public void setJybcbz(String jybcbz) {
		this.jybcbz = jybcbz;
	}

	public String getJcfs() {
		return jcfs;
	}

	public void setJcfs(String jcfs) {
		this.jcfs = jcfs;
	}

	public String getDwdz() {
		return dwdz;
	}

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}

	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}

	public String getGwsbsj() {
		return gwsbsj;
	}

	public void setGwsbsj(String gwsbsj) {
		this.gwsbsj = gwsbsj;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getXxfx() {
		return xxfx;
	}

	public void setXxfx(String xxfx) {
		this.xxfx = xxfx;
	}
	
	public String getCxcj() {
		return cxcj;
	}

	public void setCxcj(String cxcj) {
		this.cxcj = cxcj;
	}

	public String getSfbd() {
		return sfbd;
	}

	public void setSfbd(String sfbd) {
		this.sfbd = sfbd;
	}
	
		public String getCcqj() {
		return ccqj;
	}

	public void setCcqj(String ccqj) {
		this.ccqj = ccqj;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getSfjh() {
		return sfjh;
	}

	public void setSfjh(String sfjh) {
		this.sfjh = sfjh;
	}

	public String getZslb() {
		return zslb;
	}

	public void setZslb(String zslb) {
		this.zslb = zslb;
	}
	
	public String getJxjdmlb() {
		return jxjdmlb;
	}

	public void setJxjdmlb(String jxjdmlb) {
		this.jxjdmlb = jxjdmlb;
	}

	public String getDqbzgz() {
		return dqbzgz;
	}

	public void setDqbzgz(String dqbzgz) {
		this.dqbzgz = dqbzgz;
	}

	public String getGzdd() {
		return gzdd;
	}

	public void setGzdd(String gzdd) {
		this.gzdd = gzdd;
	}

	public String getGzjj() {
		return gzjj;
	}

	public void setGzjj(String gzjj) {
		this.gzjj = gzjj;
	}

	public String getGznd() {
		return gznd;
	}

	public void setGznd(String gznd) {
		this.gznd = gznd;
	}

	public String getGzyd() {
		return gzyd;
	}

	public void setGzyd(String gzyd) {
		this.gzyd = gzyd;
	}

	public String getMsdd() {
		return msdd;
	}

	public void setMsdd(String msdd) {
		this.msdd = msdd;
	}

	public String getMssjd() {
		return mssjd;
	}

	public void setMssjd(String mssjd) {
		this.mssjd = mssjd;
	}

	public String getMtbzgz() {
		return mtbzgz;
	}

	public void setMtbzgz(String mtbzgz) {
		this.mtbzgz = mtbzgz;
	}

	public String getRzyq_gzjn() {
		return rzyq_gzjn;
	}

	public void setRzyq_gzjn(String rzyq_gzjn) {
		this.rzyq_gzjn = rzyq_gzjn;
	}

	public String getRzyq_nj() {
		return rzyq_nj;
	}

	public void setRzyq_nj(String rzyq_nj) {
		this.rzyq_nj = rzyq_nj;
	}

	public String getRzyq_qt() {
		return rzyq_qt;
	}

	public void setRzyq_qt(String rzyq_qt) {
		this.rzyq_qt = rzyq_qt;
	}

	public String getRzyq_wyyq() {
		return rzyq_wyyq;
	}

	public void setRzyq_wyyq(String rzyq_wyyq) {
		this.rzyq_wyyq = rzyq_wyyq;
	}

	public String getRzyq_xb() {
		return rzyq_xb;
	}

	public void setRzyq_xb(String rzyq_xb) {
		this.rzyq_xb = rzyq_xb;
	}

	public String getRzyq_zy() {
		return rzyq_zy;
	}

	public void setRzyq_zy(String rzyq_zy) {
		this.rzyq_zy = rzyq_zy;
	}

	public String getSyknss() {
		return syknss;
	}

	public void setSyknss(String syknss) {
		this.syknss = syknss;
	}

	public String getXyddm() {
		return xyddm;
	}

	public void setXyddm(String xyddm) {
		this.xyddm = xyddm;
	}

	public String getXyrs() {
		return xyrs;
	}

	public void setXyrs(String xyrs) {
		this.xyrs = xyrs;
	}

	public String getGzmd() {
		return gzmd;
	}

	public void setGzmd(String gzmd) {
		this.gzmd = gzmd;
	}

	public String getXueqi() {
		return xueqi;
	}

	public void setXueqi(String xueqi) {
		this.xueqi = xueqi;
	}

	public String getExcelList() {
		return excelList;
	}

	public void setExcelList(String excelList) {
		this.excelList = excelList;
	}

	public String getOldCondiSqlValue() {
		return oldCondiSqlValue;
	}

	public void setOldCondiSqlValue(String oldCondiSqlValue) {
		this.oldCondiSqlValue = oldCondiSqlValue;
	}

	public String getByzffztdm() {
		return byzffztdm;
	}

	public void setByzffztdm(String byzffztdm) {
		this.byzffztdm = byzffztdm;
	}

	public String getXwzsxxdz() {
		return xwzsxxdz;
	}

	public void setXwzsxxdz(String xwzsxxdz) {
		this.xwzsxxdz = xwzsxxdz;
	}

	public String getJgs() {
		return jgs;
	}

	public void setJgs(String jgs) {
		this.jgs = jgs;
	}

	public String getJgx() {
		return jgx;
	}

	public void setJgx(String jgx) {
		this.jgx = jgx;
	}

	public String getLxdh1() {
		return lxdh1;
	}

	public void setLxdh1(String lxdh1) {
		this.lxdh1 = lxdh1;
	}

	public String getJtzsrd() {
		return jtzsrd;
	}

	public void setJtzsrd(String jtzsrd) {
		this.jtzsrd = jtzsrd;
	}

	public String getJtzsrg() {
		return jtzsrg;
	}

	public void setJtzsrg(String jtzsrg) {
		this.jtzsrg = jtzsrg;
	}

	public String getCws() {
		return cws;
	}

	public void setCws(String cws) {
		this.cws = cws;
	}

	public String getSslx() {
		return sslx;
	}

	public void setSslx(String sslx) {
		this.sslx = sslx;
	}

	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}
	
	public FormFile getImpFilePath() {
		return impFilePath;
	}

	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}

	public FormFile getCheckFilePath() {
		return checkFilePath;
	}

	public void setCheckFilePath(FormFile checkFilePath) {
		this.checkFilePath = checkFilePath;
	}

	public String getMyzgbc() {
		return myzgbc;
	}

	public void setMyzgbc(String myzgbc) {
		this.myzgbc = myzgbc;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	
	public String getDasfyl() {
		return dasfyl;
	}

	public void setDasfyl(String dasfyl) {
		this.dasfyl = dasfyl;
	}

	public String getDaylyy() {
		return daylyy;
	}

	public void setDaylyy(String daylyy) {
		this.daylyy = daylyy;
	}

	public String getBysj() {
		return bysj;
	}

	public void setBysj(String bysj) {
		this.bysj = bysj;
	}

	public String getDxhwp() {
		return dxhwp;
	}

	public void setDxhwp(String dxhwp) {
		this.dxhwp = dxhwp;
	}

	public String getJljn() {
		return jljn;
	}

	public void setJljn(String jljn) {
		this.jljn = jljn;
	}

	public String getJsjdj() {
		return jsjdj;
	}

	public void setJsjdj(String jsjdj) {
		this.jsjdj = jsjdj;
	}

	public String getJypx() {
		return jypx;
	}

	public void setJypx(String jypx) {
		this.jypx = jypx;
	}

	public String getSfdl() {
		return sfdl;
	}

	public void setSfdl(String sfdl) {
		this.sfdl = sfdl;
	}

	public String getSfsf() {
		return sfsf;
	}

	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}

	public String getSfzz() {
		return sfzz;
	}

	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}

	public String getShzw() {
		return shzw;
	}

	public void setShzw(String shzw) {
		this.shzw = shzw;
	}

	public String getSybz1() {
		return sybz1;
	}

	public void setSybz1(String sybz1) {
		this.sybz1 = sybz1;
	}

	public String getSybz2() {
		return sybz2;
	}

	public void setSybz2(String sybz2) {
		this.sybz2 = sybz2;
	}

	public String getSybz3() {
		return sybz3;
	}

	public void setSybz3(String sybz3) {
		this.sybz3 = sybz3;
	}

	public String getWydj() {
		return wydj;
	}

	public void setWydj(String wydj) {
		this.wydj = wydj;
	}

	public String getXldm() {
		return xldm;
	}

	public void setXldm(String xldm) {
		this.xldm = xldm;
	}

	public String getXmsj() {
		return xmsj;
	}

	public void setXmsj(String xmsj) {
		this.xmsj = xmsj;
	}

	public String getXwdm() {
		return xwdm;
	}

	public void setXwdm(String xwdm) {
		this.xwdm = xwdm;
	}

	public String getYxdm() {
		return yxdm;
	}

	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}

	public String getZgzs() {
		return zgzs;
	}

	public void setZgzs(String zgzs) {
		this.zgzs = zgzs;
	}

	public String getZxwyyzdm() {
		return zxwyyzdm;
	}

	public void setZxwyyzdm(String zxwyyzdm) {
		this.zxwyyzdm = zxwyyzdm;
	}

	public String getLxdz() {
		return lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	public String getJtcy1_lxr() {
		return jtcy1_lxr;
	}

	public void setJtcy1_lxr(String jtcy1_lxr) {
		this.jtcy1_lxr = jtcy1_lxr;
	}

	public String getJtcy2_lxr() {
		return jtcy2_lxr;
	}

	public void setJtcy2_lxr(String jtcy2_lxr) {
		this.jtcy2_lxr = jtcy2_lxr;
	}

	public String getJtcy3_lxr() {
		return jtcy3_lxr;
	}

	public void setJtcy3_lxr(String jtcy3_lxr) {
		this.jtcy3_lxr = jtcy3_lxr;
	}

	public String getXyknsrs() {
		return xyknsrs;
	}

	public void setXyknsrs(String xyknsrs) {
		this.xyknsrs = xyknsrs;
	}

	public String getYrdwmc() {
		return yrdwmc;
	}

	public void setYrdwmc(String yrdwmc) {
		this.yrdwmc = yrdwmc;
	}

	public String getGrjl() {
		return grjl;
	}

	public void setGrjl(String grjl) {
		this.grjl = grjl;
	}

	public String getZkzh() {
		return zkzh;
	}

	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}

	public String getSfcj() {
		return sfcj;
	}

	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}

	public String getSfpk() {
		return sfpk;
	}

	public void setSfpk(String sfpk) {
		this.sfpk = sfpk;
	}

	public String getMyzgsjfs() {
		return myzgsjfs;
	}

	public void setMyzgsjfs(String myzgsjfs) {
		this.myzgsjfs = myzgsjfs;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getSfgq() {
		return sfgq;
	}

	public void setSfgq(String sfgq) {
		this.sfgq = sfgq;
	}

	public String getFind() {
		return find;
	}

	public void setFind(String find) {
		this.find = find;
	}

	public String getGjz() {
		return gjz;
	}

	public void setGjz(String gjz) {
		this.gjz = gjz;
	}

	public String getSfzc() {
		return sfzc;
	}

	public void setSfzc(String sfzc) {
		this.sfzc = sfzc;
	}

	public String getGsmc() {
		return gsmc;
	}

	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}

	public String getFflx() {
		return fflx;
	}

	public void setFflx(String fflx) {
		this.fflx = fflx;
	}

	public String getFfsjjg() {
		return ffsjjg;
	}

	public void setFfsjjg(String ffsjjg) {
		this.ffsjjg = ffsjjg;
	}

	public String getJtcy4_sfzh() {
		return jtcy4_sfzh;
	}

	public void setJtcy4_sfzh(String jtcy4_sfzh) {
		this.jtcy4_sfzh = jtcy4_sfzh;
	}

	public String getJtcy4_yzbm() {
		return jtcy4_yzbm;
	}

	public void setJtcy4_yzbm(String jtcy4_yzbm) {
		this.jtcy4_yzbm = jtcy4_yzbm;
	}

	public String getJtcy5_sfzh() {
		return jtcy5_sfzh;
	}

	public void setJtcy5_sfzh(String jtcy5_sfzh) {
		this.jtcy5_sfzh = jtcy5_sfzh;
	}

	public String getJtcy5_yzbm() {
		return jtcy5_yzbm;
	}

	public void setJtcy5_yzbm(String jtcy5_yzbm) {
		this.jtcy5_yzbm = jtcy5_yzbm;
	}

	public String getSqrq() {
		return sqrq;
	}

	public void setSqrq(String sqrq) {
		this.sqrq = sqrq;
	}

	public String getSfyby() {
		return sfyby;
	}

	public void setSfyby(String sfyby) {
		this.sfyby = sfyby;
	}
	
	public String getSzcj1() {
		return szcj1;
	}

	public void setSzcj1(String szcj1) {
		this.szcj1 = szcj1;
	}

	public String getSzcj2() {
		return szcj2;
	}

	public void setSzcj2(String szcj2) {
		this.szcj2 = szcj2;
	}

	public String getSzcj3() {
		return szcj3;
	}

	public void setSzcj3(String szcj3) {
		this.szcj3 = szcj3;
	}

	public String getSzcj4() {
		return szcj4;
	}

	public void setSzcj4(String szcj4) {
		this.szcj4 = szcj4;
	}

	public String getSzcj5() {
		return szcj5;
	}

	public void setSzcj5(String szcj5) {
		this.szcj5 = szcj5;
	}

	public String getSzmc1() {
		return szmc1;
	}

	public void setSzmc1(String szmc1) {
		this.szmc1 = szmc1;
	}

	public String getSzmc2() {
		return szmc2;
	}

	public void setSzmc2(String szmc2) {
		this.szmc2 = szmc2;
	}

	public String getSzmc3() {
		return szmc3;
	}

	public void setSzmc3(String szmc3) {
		this.szmc3 = szmc3;
	}

	public String getSzmc4() {
		return szmc4;
	}

	public void setSzmc4(String szmc4) {
		this.szmc4 = szmc4;
	}

	public String getSzmc5() {
		return szmc5;
	}

	public void setSzmc5(String szmc5) {
		this.szmc5 = szmc5;
	}

	public String getZhszcpcj() {
		return zhszcpcj;
	}

	public void setZhszcpcj(String zhszcpcj) {
		this.zhszcpcj = zhszcpcj;
	}

	public String getZhszcpcjpm() {
		return zhszcpcjpm;
	}

	public void setZhszcpcjpm(String zhszcpcjpm) {
		this.zhszcpcjpm = zhszcpcjpm;
	}

	public String getWjlbdm() {
		return wjlbdm;
	}

	public void setWjlbdm(String wjlbdm) {
		this.wjlbdm = wjlbdm;
	}

	public String getWxnr() {
		return wxnr;
	}

	public void setWxnr(String wxnr) {
		this.wxnr = wxnr;
	}

	public String getJtyj() {
		return jtyj;
	}

	public void setJtyj(String jtyj) {
		this.jtyj = jtyj;
	}

	public String getFzdxpxbh() {
		return fzdxpxbh;
	}

	public void setFzdxpxbh(String fzdxpxbh) {
		this.fzdxpxbh = fzdxpxbh;
	}

	public String getFzdxsj() {
		return fzdxsj;
	}

	public void setFzdxsj(String fzdxsj) {
		this.fzdxsj = fzdxsj;
	}

	public String getHbcs() {
		return hbcs;
	}

	public void setHbcs(String hbcs) {
		this.hbcs = hbcs;
	}

	public String getRdjjfzpxbh() {
		return rdjjfzpxbh;
	}

	public void setRdjjfzpxbh(String rdjjfzpxbh) {
		this.rdjjfzpxbh = rdjjfzpxbh;
	}

	public String getRdjjfzsj() {
		return rdjjfzsj;
	}

	public void setRdjjfzsj(String rdjjfzsj) {
		this.rdjjfzsj = rdjjfzsj;
	}

	public String getSmhbsj() {
		return smhbsj;
	}

	public void setSmhbsj(String smhbsj) {
		this.smhbsj = smhbsj;
	}	

	public String getCfyj() {
		return cfyj;
	}

	public void setCfyj(String cfyj) {
		this.cfyj = cfyj;
	}

	public String getFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(String fdyQx) {
		this.fdyQx = fdyQx;
	}
	
	public String getPxlx() {
		return pxlx;
	}

	public void setPxlx(String pxlx) {
		this.pxlx = pxlx;
	}
	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}

	public String getCjffsj() {
		return cjffsj;
	}

	public void setCjffsj(String cjffsj) {
		this.cjffsj = cjffsj;
	}

	public String getWsjc() {
		return wsjc;
	}

	public void setWsjc(String wsjc) {
		this.wsjc = wsjc;
	}

	public String getPbzq() {
		return pbzq;
	}

	public void setPbzq(String pbzq) {
		this.pbzq = pbzq;
	}

	public String getJyxybh() {
		return jyxybh;
	}

	public void setJyxybh(String jyxybh) {
		this.jyxybh = jyxybh;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String[] getPk1() {
		return pk1;
	}

	public void setPk1(String[] pk1) {
		this.pk1 = pk1;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getSql1() {
		return sql1;
	}

	public void setSql1(String sql1) {
		this.sql1 = sql1;
	}

	public String getImgNj() {
		return imgNj;
	}

	public void setImgNj(String imgNj) {
		this.imgNj = imgNj;
	}

	public String getImgXy() {
		return imgXy;
	}

	public void setImgXy(String imgXy) {
		this.imgXy = imgXy;
	}

	public String getXyclyj() {
		return xyclyj;
	}

	public void setXyclyj(String xyclyj) {
		this.xyclyj = xyclyj;
	}
	
	public String getYhkh() {
		return yhkh;
	}

	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	public String getYkth() {
		return ykth;
	}

	public void setYkth(String ykth) {
		this.ykth = ykth;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getFkh() {
		return fkh;
	}

	public void setFkh(String fkh) {
		this.fkh = fkh;
	}

	public String getYkt() {
		return ykt;
	}

	public void setYkt(String ykt) {
		this.ykt = ykt;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getXslb() {
		return xslb;
	}

	public void setXslb(String xslb) {
		this.xslb = xslb;
	}

	public String getRxqxxshen() {
		return rxqxxshen;
	}

	public void setRxqxxshen(String rxqxxshen) {
		this.rxqxxshen = rxqxxshen;
	}

	public String getRxqxxshi() {
		return rxqxxshi;
	}

	public void setRxqxxshi(String rxqxxshi) {
		this.rxqxxshi = rxqxxshi;
	}

	public String getRxqxxxian() {
		return rxqxxxian;
	}

	public void setRxqxxxian(String rxqxxxian) {
		this.rxqxxxian = rxqxxxian;
	}

	public String getSyshen() {
		return syshen;
	}

	public void setSyshen(String syshen) {
		this.syshen = syshen;
	}

	public String getSyshi() {
		return syshi;
	}

	public void setSyshi(String syshi) {
		this.syshi = syshi;
	}

	public String getSyxian() {
		return syxian;
	}

	public void setSyxian(String syxian) {
		this.syxian = syxian;
	}

	public String getJtdzshen() {
		return jtdzshen;
	}

	public void setJtdzshen(String jtdzshen) {
		this.jtdzshen = jtdzshen;
	}

	public String getJtdzshi() {
		return jtdzshi;
	}

	public void setJtdzshi(String jtdzshi) {
		this.jtdzshi = jtdzshi;
	}

	public String getJtdzxian() {
		return jtdzxian;
	}

	public void setJtdzxian(String jtdzxian) {
		this.jtdzxian = jtdzxian;
	}

	public String getMph() {
		return mph;
	}

	public void setMph(String mph) {
		this.mph = mph;
	}

	public String getRxqbyxx() {
		return rxqbyxx;
	}

	public void setRxqbyxx(String rxqbyxx) {
		this.rxqbyxx = rxqbyxx;
	}
	public String getSbts() {
		return sbts;
	}

	public void setSbts(String sbts) {
		this.sbts = sbts;
	}

	public String getXsczqk() {
		return xsczqk;
	}

	public void setXsczqk(String xsczqk) {
		this.xsczqk = xsczqk;
	}

	public String getWtqt() {
		return wtqt;
	}

	public void setWtqt(String wtqt) {
		this.wtqt = wtqt;
	}

	public String getQsrq() {
		return qsrq;
	}

	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}

	public String getYqljlx() {
		return yqljlx;
	}

	public void setYqljlx(String yqljlx) {
		this.yqljlx = yqljlx;
	}

	public String getYqljlxxn() {
		return yqljlxxn;
	}

	public void setYqljlxxn(String yqljlxxn) {
		this.yqljlxxn = yqljlxxn;
	}

	public String getYqljlxgx() {
		return yqljlxgx;
	}

	public void setYqljlxgx(String yqljlxgx) {
		this.yqljlxgx = yqljlxgx;
	}

	public String getYqljlxgn() {
		return yqljlxgn;
	}

	public void setYqljlxgn(String yqljlxgn) {
		this.yqljlxgn = yqljlxgn;
	}

	public String getYqljlxjy() {
		return yqljlxjy;
	}

	public void setYqljlxjy(String yqljlxjy) {
		this.yqljlxjy = yqljlxjy;
	}

	public String getCcjydw() {
		return ccjydw;
	}

	public void setCcjydw(String ccjydw) {
		this.ccjydw = ccjydw;
	}

	public String getXgzdw() {
		return xgzdw;
	}

	public void setXgzdw(String xgzdw) {
		this.xgzdw = xgzdw;
	}

	public String getXgwjrzqk() {
		return xgwjrzqk;
	}

	public void setXgwjrzqk(String xgwjrzqk) {
		this.xgwjrzqk = xgwjrzqk;
	}

	public String getYb() {
		return yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}

	public String getZydkcd() {
		return zydkcd;
	}

	public void setZydkcd(String zydkcd) {
		this.zydkcd = zydkcd;
	}

	public String getGwsyzt() {
		return gwsyzt;
	}

	public void setGwsyzt(String gwsyzt) {
		this.gwsyzt = gwsyzt;
	}

	public String getGwmycd() {
		return gwmycd;
	}

	public void setGwmycd(String gwmycd) {
		this.gwmycd = gwmycd;
	}

	public String getGzbdqk() {
		return gzbdqk;
	}

	public void setGzbdqk(String gzbdqk) {
		this.gzbdqk = gzbdqk;
	}

	public String getHdhzjxhbz() {
		return hdhzjxhbz;
	}

	public void setHdhzjxhbz(String hdhzjxhbz) {
		this.hdhzjxhbz = hdhzjxhbz;
	}

	public String getSlkc() {
		return slkc;
	}

	public void setSlkc(String slkc) {
		this.slkc = slkc;
	}

	public String getRwshkc() {
		return rwshkc;
	}

	public void setRwshkc(String rwshkc) {
		this.rwshkc = rwshkc;
	}

	public String getWykc() {
		return wykc;
	}

	public void setWykc(String wykc) {
		this.wykc = wykc;
	}

	public String getJsjkc() {
		return jsjkc;
	}

	public void setJsjkc(String jsjkc) {
		this.jsjkc = jsjkc;
	}

	public String getSynl() {
		return synl;
	}

	public void setSynl(String synl) {
		this.synl = synl;
	}

	public String getKynl() {
		return kynl;
	}

	public void setKynl(String kynl) {
		this.kynl = kynl;
	}

	public String getSjdsnl() {
		return sjdsnl;
	}

	public void setSjdsnl(String sjdsnl) {
		this.sjdsnl = sjdsnl;
	}

	public String getDlgznl() {
		return dlgznl;
	}

	public void setDlgznl(String dlgznl) {
		this.dlgznl = dlgznl;
	}

	public String getZzglnl() {
		return zzglnl;
	}

	public void setZzglnl(String zzglnl) {
		this.zzglnl = zzglnl;
	}

	public String getZwhqzsnl() {
		return zwhqzsnl;
	}

	public void setZwhqzsnl(String zwhqzsnl) {
		this.zwhqzsnl = zwhqzsnl;
	}

	public String getWzbdnl() {
		return wzbdnl;
	}

	public void setWzbdnl(String wzbdnl) {
		this.wzbdnl = wzbdnl;
	}

	public String getKtbdnl() {
		return ktbdnl;
	}

	public void setKtbdnl(String ktbdnl) {
		this.ktbdnl = ktbdnl;
	}

	public String getShggnl() {
		return shggnl;
	}

	public void setShggnl(String shggnl) {
		this.shggnl = shggnl;
	}

	public String getSykc() {
		return sykc;
	}

	public void setSykc(String sykc) {
		this.sykc = sykc;
	}

	public String getQqkc() {
		return qqkc;
	}

	public void setQqkc(String qqkc) {
		this.qqkc = qqkc;
	}

	public String getJxnd() {
		return jxnd;
	}

	public void setJxnd(String jxnd) {
		this.jxnd = jxnd;
	}

	public String getSfsq() {
		return sfsq;
	}

	public void setSfsq(String sfsq) {
		this.sfsq = sfsq;
	}

	public String getKfpcws() {
		return kfpcws;
	}

	public void setKfpcws(String kfpcws) {
		this.kfpcws = kfpcws;
	}

	public String getJgz() {
		return jgz;
	}

	public void setJgz(String jgz) {
		this.jgz = jgz;
	}

	public String getSfby() {
		return sfby;
	}

	public void setSfby(String sfby) {
		this.sfby = sfby;
	}

	public String getHgdm() {
		return hgdm;
	}

	public void setHgdm(String hgdm) {
		this.hgdm = hgdm;
	}

	public String getHgsj() {
		return hgsj;
	}

	public void setHgsj(String hgsj) {
		this.hgsj = hgsj;
	}

	public String getSfbys() {
		return sfbys;
	}

	public void setSfbys(String sfbys) {
		this.sfbys = sfbys;
	}

	public String getYskts() {
		return yskts;
	}

	public void setYskts(String yskts) {
		this.yskts = yskts;
	}

	public String getSskts() {
		return sskts;
	}

	public void setSskts(String sskts) {
		this.sskts = sskts;
	}

	public String getBingjia() {
		return bingjia;
	}

	public void setBingjia(String bingjia) {
		this.bingjia = bingjia;
	}

	public String getShijia() {
		return shijia;
	}

	public void setShijia(String shijia) {
		this.shijia = shijia;
	}

	public String getChidao() {
		return chidao;
	}

	public void setChidao(String chidao) {
		this.chidao = chidao;
	}

	public String getQita() {
		return qita;
	}

	public void setQita(String qita) {
		this.qita = qita;
	}

	public String getZhszpd() {
		return zhszpd;
	}

	public void setZhszpd(String zhszpd) {
		this.zhszpd = zhszpd;
	}

	public String getBzr() {
		return bzr;
	}

	public void setBzr(String bzr) {
		this.bzr = bzr;
	}

	public String getBzrlxdh() {
		return bzrlxdh;
	}

	public void setBzrlxdh(String bzrlxdh) {
		this.bzrlxdh = bzrlxdh;
	}

	public String getKuangke() {
		return kuangke;
	}

	public void setKuangke(String kuangke) {
		this.kuangke = kuangke;
	}

	public String[] getPkV() {
		return pkV;
	}

	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}

	public String getYhdm() {
		return yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getLxcksj() {
		return lxcksj;
	}

	public void setLxcksj(String lxcksj) {
		this.lxcksj = lxcksj;
	}

	public String getFdyz() {
		return fdyz;
	}

	public void setFdyz(String fdyz) {
		this.fdyz = fdyz;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String getFqk() {
		return fqk;
	}

	public void setFqk(String fqk) {
		this.fqk = fqk;
	}

	public String getRqk() {
		return rqk;
	}

	public void setRqk(String rqk) {
		this.rqk = rqk;
	}

	public String getSqk() {
		return sqk;
	}

	public void setSqk(String sqk) {
		this.sqk = sqk;
	}

	public String getXqk() {
		return xqk;
	}

	public void setXqk(String xqk) {
		this.xqk = xqk;
	}

	public String getYqk() {
		return yqk;
	}

	public void setYqk(String yqk) {
		this.yqk = yqk;
	}

	public String getRxnj() {
		return rxnj;
	}

	public void setRxnj(String rxnj) {
		this.rxnj = rxnj;
	}

	public String getXfje() {
		return xfje;
	}

	public void setXfje(String xfje) {
		this.xfje = xfje;
	}

	public String getXlcslb() {
		return xlcslb;
	}

	public void setXlcslb(String xlcslb) {
		this.xlcslb = xlcslb;
	}

	public String getXlwtlx() {
		return xlwtlx;
	}

	public void setXlwtlx(String xlwtlx) {
		this.xlwtlx = xlwtlx;
	}

	public String getSfkns() {
		return sfkns;
	}

	public void setSfkns(String sfkns) {
		this.sfkns = sfkns;
	}

	public String getSfdq() {
		return sfdq;
	}

	public void setSfdq(String sfdq) {
		this.sfdq = sfdq;
	}

	public String getDwlxr() {
		return dwlxr;
	}

	public void setDwlxr(String dwlxr) {
		this.dwlxr = dwlxr;
	}

	public String getDajsddwmc() {
		return dajsddwmc;
	}

	public void setDajsddwmc(String dajsddwmc) {
		this.dajsddwmc = dajsddwmc;
	}

	public String getDwgm() {
		return dwgm;
	}

	public void setDwgm(String dwgm) {
		this.dwgm = dwgm;
	}

	public String getDwzczj() {
		return dwzczj;
	}

	public void setDwzczj(String dwzczj) {
		this.dwzczj = dwzczj;
	}

	public String getCfnx() {
		return cfnx;
	}

	public void setCfnx(String cfnx) {
		this.cfnx = cfnx;
	}

	public String getXftz() {
		return xftz;
	}

	public void setXftz(String xftz) {
		this.xftz = xftz;
	}
	public String getSbsy() {
		return sbsy;
	}

	public void setSbsy(String sbsy) {
		this.sbsy = sbsy;
	}

	public String getSfjy() {
		return sfjy;
	}

	public void setSfjy(String sfjy) {
		this.sfjy = sfjy;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getSh() {
		return sh;
	}

	public void setSh(String sh) {
		this.sh = sh;
	}

	public String getXsqr() {
		return xsqr;
	}

	public void setXsqr(String xsqr) {
		this.xsqr = xsqr;
	}

	public String getQrsj() {
		return qrsj;
	}

	public void setQrsj(String qrsj) {
		this.qrsj = qrsj;
	}
	
	public String getJfglkg() {
		return jfglkg;
	}

	public void setJfglkg(String jfglkg) {
		this.jfglkg = jfglkg;
	}	

	public String getPurview() {
		return purview;
	}

	public void setPurview(String purview) {
		this.purview = purview;
	}

	public String getHkshen() {
		return hkshen;
	}

	public void setHkshen(String hkshen) {
		this.hkshen = hkshen;
	}

	public String getHkshi() {
		return hkshi;
	}

	public void setHkshi(String hkshi) {
		this.hkshi = hkshi;
	}

	public String getHkxian() {
		return hkxian;
	}

	public void setHkxian(String hkxian) {
		this.hkxian = hkxian;
	}

	public String getZcsxhm() {
		return zcsxhm;
	}

	public void setZcsxhm(String zcsxhm) {
		this.zcsxhm = zcsxhm;
	}

	public String getRxqwhcd() {
		return rxqwhcd;
	}

	public void setRxqwhcd(String rxqwhcd) {
		this.rxqwhcd = rxqwhcd;
	}

	public String getDwlbdm() {
		return dwlbdm;
	}

	public void setDwlbdm(String dwlbdm) {
		this.dwlbdm = dwlbdm;
	}

	public String getYsbjdm() {
		return ysbjdm;
	}

	public void setYsbjdm(String ysbjdm) {
		this.ysbjdm = ysbjdm;
	}

	public String getMkbjdm() {
		return mkbjdm;
	}

	public void setMkbjdm(String mkbjdm) {
		this.mkbjdm = mkbjdm;
	}

	public String getSfdbh() {
		return sfdbh;
	}

	public void setSfdbh(String sfdbh) {
		this.sfdbh = sfdbh;
	}

	public String getSfgr() {
		return sfgr;
	}

	public void setSfgr(String sfgr) {
		this.sfgr = sfgr;
	}

	public String getSfyfdx() {
		return sfyfdx;
	}

	public void setSfyfdx(String sfyfdx) {
		this.sfyfdx = sfyfdx;
	}

	public String getYjshf() {
		return yjshf;
	}

	public void setYjshf(String yjshf) {
		this.yjshf = yjshf;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getLbmc() {
		return lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	public String getGwfbr() {
		return gwfbr;
	}

	public void setGwfbr(String gwfbr) {
		this.gwfbr = gwfbr;
	}

	public String getZwxzdm() {
		return zwxzdm;
	}

	public void setZwxzdm(String zwxzdm) {
		this.zwxzdm = zwxzdm;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getTjfs() {
		return tjfs;
	}

	public void setTjfs(String tjfs) {
		this.tjfs = tjfs;
	}

	public String getQueryequals_shjg() {
		return queryequals_shjg;
	}

	public void setQueryequals_shjg(String queryequals_shjg) {
		this.queryequals_shjg = queryequals_shjg;
	}

	public String getKncddm() {
		return kncddm;
	}

	public void setKncddm(String kncddm) {
		this.kncddm = kncddm;
	}

	public String getKcjqgzxsj() {
		return kcjqgzxsj;
	}

	public void setKcjqgzxsj(String kcjqgzxsj) {
		this.kcjqgzxsj = kcjqgzxsj;
	}

	public String getXssq() {
		return xssq;
	}

	public void setXssq(String xssq) {
		this.xssq = xssq;
	}

	public String getJl1_qzny() {
		return jl1_qzny;
	}

	public void setJl1_qzny(String jl1_qzny) {
		this.jl1_qzny = jl1_qzny;
	}

	public String getJl1_xxjgzdw() {
		return jl1_xxjgzdw;
	}

	public void setJl1_xxjgzdw(String jl1_xxjgzdw) {
		this.jl1_xxjgzdw = jl1_xxjgzdw;
	}

	public String getJl1_xxhrzw() {
		return jl1_xxhrzw;
	}

	public void setJl1_xxhrzw(String jl1_xxhrzw) {
		this.jl1_xxhrzw = jl1_xxhrzw;
	}

	public String getJl2_qzny() {
		return jl2_qzny;
	}

	public void setJl2_qzny(String jl2_qzny) {
		this.jl2_qzny = jl2_qzny;
	}

	public String getJl2_xxjgzdw() {
		return jl2_xxjgzdw;
	}

	public void setJl2_xxjgzdw(String jl2_xxjgzdw) {
		this.jl2_xxjgzdw = jl2_xxjgzdw;
	}

	public String getJl2_xxhrzw() {
		return jl2_xxhrzw;
	}

	public void setJl2_xxhrzw(String jl2_xxhrzw) {
		this.jl2_xxhrzw = jl2_xxhrzw;
	}

	public String getJl3_qzny() {
		return jl3_qzny;
	}

	public void setJl3_qzny(String jl3_qzny) {
		this.jl3_qzny = jl3_qzny;
	}

	public String getJl3_xxjgzdw() {
		return jl3_xxjgzdw;
	}

	public void setJl3_xxjgzdw(String jl3_xxjgzdw) {
		this.jl3_xxjgzdw = jl3_xxjgzdw;
	}

	public String getJl3_xxhrzw() {
		return jl3_xxhrzw;
	}

	public void setJl3_xxhrzw(String jl3_xxhrzw) {
		this.jl3_xxhrzw = jl3_xxhrzw;
	}

	public String getJl4_qzny() {
		return jl4_qzny;
	}

	public void setJl4_qzny(String jl4_qzny) {
		this.jl4_qzny = jl4_qzny;
	}

	public String getJl4_xxjgzdw() {
		return jl4_xxjgzdw;
	}

	public void setJl4_xxjgzdw(String jl4_xxjgzdw) {
		this.jl4_xxjgzdw = jl4_xxjgzdw;
	}

	public String getJl4_xxhrzw() {
		return jl4_xxhrzw;
	}

	public void setJl4_xxhrzw(String jl4_xxhrzw) {
		this.jl4_xxhrzw = jl4_xxhrzw;
	}

	public String getJl5_qzny() {
		return jl5_qzny;
	}

	public void setJl5_qzny(String jl5_qzny) {
		this.jl5_qzny = jl5_qzny;
	}

	public String getJl5_xxjgzdw() {
		return jl5_xxjgzdw;
	}

	public void setJl5_xxjgzdw(String jl5_xxjgzdw) {
		this.jl5_xxjgzdw = jl5_xxjgzdw;
	}

	public String getJl5_xxhrzw() {
		return jl5_xxhrzw;
	}

	public void setJl5_xxhrzw(String jl5_xxhrzw) {
		this.jl5_xxhrzw = jl5_xxhrzw;
	}

	public String getJl6_qzny() {
		return jl6_qzny;
	}

	public void setJl6_qzny(String jl6_qzny) {
		this.jl6_qzny = jl6_qzny;
	}

	public String getJl6_xxjgzdw() {
		return jl6_xxjgzdw;
	}

	public void setJl6_xxjgzdw(String jl6_xxjgzdw) {
		this.jl6_xxjgzdw = jl6_xxjgzdw;
	}

	public String getJl6_xxhrzw() {
		return jl6_xxhrzw;
	}

	public void setJl6_xxhrzw(String jl6_xxhrzw) {
		this.jl6_xxhrzw = jl6_xxhrzw;
	}

	public String getSfqsz() {
		return sfqsz;
	}

	public void setSfqsz(String sfqsz) {
		this.sfqsz = sfqsz;
	}

	public String getJfqk() {
		return jfqk;
	}

	public void setJfqk(String jfqk) {
		this.jfqk = jfqk;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getNspName() {
		return nspName;
	}

	public void setNspName(String nspName) {
		this.nspName = nspName;
	}

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}

	public String getYlbxh() {
		return ylbxh;
	}

	public void setYlbxh(String ylbxh) {
		this.ylbxh = ylbxh;
	}

	public String getJtcy1_zjxy() {
		return jtcy1_zjxy;
	}

	public void setJtcy1_zjxy(String jtcy1_zjxy) {
		this.jtcy1_zjxy = jtcy1_zjxy;
	}

	public String getJtcy2_zjxy() {
		return jtcy2_zjxy;
	}

	public void setJtcy2_zjxy(String jtcy2_zjxy) {
		this.jtcy2_zjxy = jtcy2_zjxy;
	}

	public String getJtcy3_zjxy() {
		return jtcy3_zjxy;
	}

	public void setJtcy3_zjxy(String jtcy3_zjxy) {
		this.jtcy3_zjxy = jtcy3_zjxy;
	}

	public String getDwdm() {
		return dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}

	public String getSydshen() {
		return sydshen;
	}

	public void setSydshen(String sydshen) {
		this.sydshen = sydshen;
	}

	public String getSydshi() {
		return sydshi;
	}

	public void setSydshi(String sydshi) {
		this.sydshi = sydshi;
	}

	public String getSydxian() {
		return sydxian;
	}

	public void setSydxian(String sydxian) {
		this.sydxian = sydxian;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getRxqdwdz() {
		return rxqdwdz;
	}

	public void setRxqdwdz(String rxqdwdz) {
		this.rxqdwdz = rxqdwdz;
	}

	public String getRxqdwyb() {
		return rxqdwyb;
	}

	public void setRxqdwyb(String rxqdwyb) {
		this.rxqdwyb = rxqdwyb;
	}

	public String getGzbx() {
		return gzbx;
	}

	public void setGzbx(String gzbx) {
		this.gzbx = gzbx;
	}

	public FormFile getBkzp() {
		return bkzp;
	}

	public void setBkzp(FormFile bkzp) {
		this.bkzp = bkzp;
	}

	public FormFile getRxzp() {
		return rxzp;
	}

	public void setRxzp(FormFile rxzp) {
		this.rxzp = rxzp;
	}

	public FormFile getByzp() {
		return byzp;
	}

	public void setByzp(FormFile byzp) {
		this.byzp = byzp;
	}

	public String getGwflag() {
		return gwflag;
	}

	public void setGwflag(String gwflag) {
		this.gwflag = gwflag;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getSfgat() {
		return sfgat;
	}

	public void setSfgat(String sfgat) {
		this.sfgat = sfgat;
	}

	public String getSfssmz() {
		return sfssmz;
	}

	public void setSfssmz(String sfssmz) {
		this.sfssmz = sfssmz;
	}

	public String getZddwmc() {
		return zddwmc;
	}

	public void setZddwmc(String zddwmc) {
		this.zddwmc = zddwmc;
	}

	public String getJyh() {
		return jyh;
	}

	public void setJyh(String jyh) {
		this.jyh = jyh;
	}

	public String getSfzd() {
		return sfzd;
	}

	public void setSfzd(String sfzd) {
		this.sfzd = sfzd;
	}

	public String getFdylxdh() {
		return fdylxdh;
	}

	public void setFdylxdh(String fdylxdh) {
		this.fdylxdh = fdylxdh;
	}

	public String getBzrxm() {
		return bzrxm;
	}

	public void setBzrxm(String bzrxm) {
		this.bzrxm = bzrxm;
	}

	public String getZlbzrxm() {
		return zlbzrxm;
	}

	public void setZlbzrxm(String zlbzrxm) {
		this.zlbzrxm = zlbzrxm;
	}	
	
	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getPicid() {
		return picid;
	}

	public void setPicid(String picid) {
		this.picid = picid;
	}

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	public String[] getPicArr() {
		return picArr;
	}

	public void setPicArr(String[] picArr) {
		this.picArr = picArr;
	}

	public String[] getXssxArr() {
		return xssxArr;
	}

	public void setXssxArr(String[] xssxArr) {
		this.xssxArr = xssxArr;
	}

	public String getRowspan() {
		return rowspan;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getSave_xsxw() {
		return save_xsxw;
	}

	public void setSave_xsxw(String save_xsxw) {
		this.save_xsxw = save_xsxw;
	}

	public String getSave_xzcm() {
		return save_xzcm;
	}

	public void setSave_xzcm(String save_xzcm) {
		this.save_xzcm = save_xzcm;
	}

	public String getXsxw() {
		return xsxw;
	}

	public void setXsxw(String xsxw) {
		this.xsxw = xsxw;
	}

	public String getXzcm() {
		return xzcm;
	}

	public void setXzcm(String xzcm) {
		this.xzcm = xzcm;
	}		
	
	public String getSfkx() {
		return sfkx;
	}

	public void setSfkx(String sfkx) {
		this.sfkx = sfkx;
	}

	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO 自动生成方法存根
		searchModel = new SearchModel();
		super.reset(arg0, arg1);
	}

	public String getSfqs() {
		return sfqs;
	}

	public void setSfqs(String sfqs) {
		this.sfqs = sfqs;
	}

	public String getXyyrdwsh() {
		return xyyrdwsh;
	}

	public void setXyyrdwsh(String xyyrdwsh) {
		this.xyyrdwsh = xyyrdwsh;
	}

	public String getCfjssj() {
		return cfjssj;
	}

	public void setCfjssj(String cfjssj) {
		this.cfjssj = cfjssj;
	}

	public String getCfkssj() {
		return cfkssj;
	}

	public void setCfkssj(String cfkssj) {
		this.cfkssj = cfkssj;
	}

	public String getGzjssj_js() {
		return gzjssj_js;
	}

	public void setGzjssj_js(String gzjssj_js) {
		this.gzjssj_js = gzjssj_js;
	}

	public String getGzjssj_ks() {
		return gzjssj_ks;
	}

	public void setGzjssj_ks(String gzjssj_ks) {
		this.gzjssj_ks = gzjssj_ks;
	}

	public String getGzkssj_js() {
		return gzkssj_js;
	}

	public void setGzkssj_js(String gzkssj_js) {
		this.gzkssj_js = gzkssj_js;
	}

	public String getGzkssj_ks() {
		return gzkssj_ks;
	}

	public void setGzkssj_ks(String gzkssj_ks) {
		this.gzkssj_ks = gzkssj_ks;
	}

	public String getIsFdy() {
		return isFdy;
	}

	public void setIsFdy(String isFdy) {
		this.isFdy = isFdy;
	}

	public String getXxdm() {
		return xxdm;
	}

	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}

	public String getGzzsj() {
		return gzzsj;
	}

	public void setGzzsj(String gzzsj) {
		this.gzzsj = gzzsj;
	}

	public String getGzzsj_ks() {
		return gzzsj_ks;
	}

	public void setGzzsj_ks(String gzzsjKs) {
		gzzsj_ks = gzzsjKs;
	}

	public String getGzzsj_js() {
		return gzzsj_js;
	}

	public void setGzzsj_js(String gzzsjJs) {
		gzzsj_js = gzzsjJs;
	}

	public String getQsxn() {
		return qsxn;
	}

	public void setQsxn(String qsxn) {
		this.qsxn = qsxn;
	}

	public String getZzxn() {
		return zzxn;
	}

	public void setZzxn(String zzxn) {
		this.zzxn = zzxn;
	}

	public String getXmlb() {
		return xmlb;
	}

	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
	}

	public String getXmlbdm() {
		return xmlbdm;
	}

	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
	}

	public String getSfjq() {
		return sfjq;
	}

	public void setSfjq(String sfjq) {
		this.sfjq = sfjq;
	}

	public String getZd1() {
		return zd1;
	}

	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}

}
