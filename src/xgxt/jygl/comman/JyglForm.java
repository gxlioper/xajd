package xgxt.jygl.comman;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.form.User;
import xgxt.utils.Pages;

public class JyglForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	User user=new User();
	
	Pages pages = new Pages();
	
	private String clientColumns;
	
	private String doType;
	
	private String lx;
	
	private String jsnr;
	
	private String queryequals_bzrsh;
	
	private String queryequals_sybdzt;
	
	private String queryequals_xyshzt;
	
	private String querylike_sbr;
	
	private String queryequals_sbnd;
	
	private String queryequals_lqqk;
	
	private String querylike_shr;
	
	private String querylike_xyshr;
	
	private String querylike_xxshr;
	
	private String[] shr;
	
	private String moreTerm;
	
	private String gwfbsj;
	
	private String queryequals_sfsy;
	
	private String queryequals_sfqr;
	
	private String queryequals_je;
	
	private String queryequals_sfxg;
	
	private String save_bysqrkssj;
	
	private String save_bysqrjssj;
	
	private String save_bysshkssj;
	
	private String save_bysshjssj;
	
	private String save_sfxg;//是否修改
	
	private String save_sfqr;// 是否确认
	
	private String save_qrrbh;//确认人编号
	
	private String save_qrsj;//确认时间
	
	private String save_isgp;//是否改派
	
	private String save_shjg;//审核结果
	
	private String save_byssbkssj;
	
	private String save_byssbjssj;
	
	private String queryequals_shjg;
	
	private String save_kzx1;
	private String save_kzx2;
	private String save_kzx3;
	private String save_kzx4;
	private String save_kzx5;
	private String save_kzx6;
	private String save_kzx7;
	private String save_kzx8;
	private String save_kzx9;
	
	private String querylike_zpzt;
	
	private String querylike_zpdd;
	
	private String querylike_fbr;
	
	private String querygreaterequal_zpsj;
	
	private String querylessequal_zpsj;
		
	private String tempDwmc;
	
	private String tempGwValue;
	
	private String save_zplx;//招聘类型
	
	private String save_zpzt;//招聘主题
	
	private String save_zpnr;//招聘内容
	
	private String save_zpsj;//招聘时间
	
	private String save_zpdd;//招聘地点
	
	private String save_zplxdm;//招聘类型代码
	
	private String zpzw;//招聘职位
	
	private String queryequals_zplxdm;//招聘类型代码
	
	private String save_newjyxybh;//新协议书编号
	
	private String querylike_newjyxybh;
	
	private String save_byssbkg;//毕业生上报开关
	
	private String save_lcbh;//流程编号
	
	private String save_xysbbshjb;//协议书补办审核级别
	
	private String save_xysbbshr;//协议书补办审核人
	
	private String save_bblbdm;//协议书补办类别代码
	
	private String queryequals_bblbdm;
	
	private String save_sqly;//申请理由
	
	private String save_yjyxybh;//原就业协议书编号
	
	private String save_jyxybh;//协议书编号
	
	private String querylike_jyxybh;
	
	private String querylike_xqzy;
	
	private String save_dwdz; 
	
	private String save_xqzy; 

	private String save_jsfldm; //角色分类代码
	
	private String queryequals_jsfldm;
	
	private String save_tjdwmc; //推荐单位名称
	
	private String querylike_tjdwmc;
	
	private String save_tjjgdm; //推荐结果代码
	
	private String queryequals_tjjgdm;
	
	private String save_cyjs; //参与教师
	
	private String save_btjxs; //被推荐学生
	
	private String querylike_btjxs;
	
	private String save_lrsj; 
	
	private String save_jzzd; //讲座主题
	
	private String querylike_jzzd;
	
	private String save_xg; //效果
	
	private String save_cjrs; //参加人数
	
	private String save_mxdx; //面向对象
	
	private String querylike_mxdx;
	
	private String save_zcrjs; //主持人介绍

	private String save_hylxdm;//会议类型
	
	private String queryequals_hylxdm;//会议类型
	
	private String save_hyzt;//会议主题
	
	private String querylike_hyzt;//会议主题
	
	private String save_zcr;//主持人
	
	private String querylike_zcr;//
	
	private String save_chrr;//参会人员
	
	private String save_zyjy;//主要决议
		
	private String querylike_chdw;
	
	private String querylike_xqgw;
	
	private String queryequals_qyfldm;
	
	private String queryequals_zphlxdm;
	
	private String save_sj;
	
	private String save_dd;
	
	private String save_chdw;//参会单位
	
	private String save_xqgw;//需求岗位
	
	private String save_xqsl;//需求数量
	
	private String save_cjxss;//参与学生数
	
	private String save_dccbyxs;//达成初步意向数
	
	private String save_qyfldm;//企业分类代码
	
	private String save_zphlxdm;//招聘会类型代码
	
	private String save_lhcbf;//联合承办方
	
	private String querygreaterequal_qyrs;
	
	private String querylessequal_qyrs;
	
	private String querygreaterequal_qysj;
	
	private String querylessequal_qysj;
	
	private String queryequals_bmdm;
	
	private String querylike_jtmc;
	
	private String querylike_qtr;
	
	private String queryequals_sfba;
	
	private String save_bmdm;//部门
	
	private String save_jtmc;//就业实践基地名称
	
	private String save_qysj;//签约时间
	
	private String save_qtr;//牵头人
	
	private String save_sfba;//是否备案
	
	private String save_qyqkjl;//签约情况记录
	
	private String save_qyrs;//签约人数
	
	private String xmbz;//项目备注
	
	private String save_isjys;//是否结业生
	
	private String save_dtshqk;//报到证当天审核情况
	
	private String queryequals_isjys;
	
	private String zgdw;
	
	private String tjxmdm;
	
	private String queryequals_dwxzdm;
	
	private String save_dwxzdm;
	
	private String save_hyfldm;
	
	private String save_dwdh;
	
	private String save_dwyb;
	//就业网
	private String fileName;//文件名
	
	private String tplj;//图片路径
	
	private String sfzh;
	
	private String tjfs;
	
	private String dwxz;
	
	private String hyfl;
	
	private String querylike_gwmc;
	
	private String save_joblb;//收藏、浏览、投递
	
	private String save_gwmc;
	
	private String querylike_mbgw;
	
	private String queryequals_gzxz;
	
	private String querylike_gzdd;
	
	private String save_mbgw;//目标岗位
	
	private String save_wjlx;
	
	private String save_wjbt;
	
	private String save_wjnr;
	
	private String querylike_zphbt;
	
	private String save_zphbt;//招聘会标题
	
	private String save_zphnr;//招聘会内容
	
	private String save_fbr;//发布人
	
	private String save_czhm;//传真
	
	private String querylike_wjm;
	
	private String querygreaterequal_scsj;//上传时间
	
	private String querylessequal_scsj;//上传时间
	
	private FormFile file;
	
	private String guid;//
	
	private String wjm;//文件名
	
	private String scr;//上传人
	
	private String scsj;//上传时间
	
	private String wjlj;//文件路径
	
	private String wjsm;//文件说明
	
	private String llcs;//浏览次数
	
	private String xzcs;//下载次数
	
	private String querylike_ljmc;
	
	private String querylike_ljwz;
	
	private String queryequals_xssx;
	
	private String ljmc;//链接名称
	
	private String ljlx;//链接类型
	
	private String ljwz;//链接网址
	
	private FormFile tpdz;//图片地址
	
	private String xssx;//显示顺序
	
	private String querylike_sfzh;
	
	private String save_zdyrdw;//只对用人单位公开
	
	private String save_sfzhbm;//身份证号码是否保密
	
	private String save_gzxz;//工作性质
	
	private String save_gzdd;//工作地点
	
	private String save_mbzn;//目标职能
	
	private String save_qwxs;//期望薪水
	
	private String save_yhm;//用户名
	
	private String save_mm;//密码
	
	private String save_qyfr;//企业法人
	
	private String save_jgdmzh;//机构代码证号
	
	private String save_hyfl;//行业分类
	
	private String save_lxr;//联系人
	
	private String save_cz;//传真
	
	private String save_yb;//邮编
	
	private String save_email;//Email
	
	private String save_wz;//网址
	
	private String save_dz;//地址
	
	private String save_dwjj;//单位简介
	
	private String querylike_dwmc;//单位名称
	
	private String queryequals_hyfl;//行业分类
	
	private String querygreaterequal_zcsj;//注册时间
	
	private String querylessequal_zcsj;//注册时间
	
	private String userName;
	
	private String password;
	
	private String wjbt;
	
	private String wjnr;
	
	private String fbr;
	
	private String fbsj;
	
	private String wjlx;
	
	private String querygreaterequal_fbsj;
	
	private String querylessequal_fbsj;
	
	private String querylike_wjbt;
	
	private String queryequals_wjlx;
	
	
	
	//优秀毕业生
	private String save_pjcj1;//平均成绩1
	
	private String save_pjcj2;//平均成绩2
	
	private String save_bysj;//毕业设计
	
	private String save_npjx;//批评奖项
	
	private String save_hjqk;//获奖情况
	
	private String save_zcf;//综测分
	
	private String save_zcpm;//综测排名
	
	private String save_sqlb;//申请类别
	
	private String queryequals_sqlb;
	
	private String queryequals_fclx;
	
	private String save_fcgy;//风采感言
	
	private String sqlb;
	
	//社会工作
	private String save_shgz1;
	private String save_shgz2;
	private String save_shgz3;
	private String save_shgz4;
	private String save_shgz5;
	
	//获奖情况
	private String save_hjqk1;
	private String save_hjqk2;
	private String save_hjqk3;
	private String save_hjqk4;
	private String save_hjqk5;
	
	//风采事迹
	private String save_fcsj1;
	private String save_fcsj2;
	private String save_fcsj3;
	private String save_fcsj4;
	private String save_fcsj5;
	
	private String save_fclx;//风采类型
	
	private String querygreaterequal_sbsj;
	
	private String querylessequal_sbsj;
	
	/*
	 * 就业推荐表
	 */
	
	private String save_zwtj;
	
	private String save_sydq;
	
	private String save_jtdz;
	
	private String save_jkzk;
	
	private String save_xymc;
	
	private String save_zymc;
	
	private String save_fxzy;
	
	private String save_xl;
	
	private String save_jsjsp;//计算机水平
	
	private String save_wysp;//外语水平
	
	private String save_shgzkssj1;
	
	private String save_shgzjssj1;
	
	private String save_gzdw1;
	
	private String save_srzw1;
	
	private String save_shgzkssj2;
	
	private String save_shgzjssj2;
	
	private String save_gzdw2;
	
	private String save_srzw2;
	
	private String save_shgzkssj3;
	
	private String save_shgzjssj3;
	
	private String save_gzdw3;
	
	private String save_srzw3;
	
	private String save_hjsj1;
	
	private String save_rych1;
	
	private String save_bzjg1;
	
	private String save_hjsj2;
	
	private String save_rych2;
	
	private String save_bzjg2;
		
	private String save_hjsj3;
	
	private String save_rych3;
	
	private String save_bzjg3;
	
	private String save_jnzssj1;
	
	private String save_jnzsmc1;
	
	private String save_jnzsbzjg1;
	
	private String save_jnzssj2;
	
	private String save_jnzsmc2;
	
	private String save_jnzsbzjg2;
	
	private String save_jnzssj3;
	
	private String save_jnzsmc3;
	
	private String save_jnzsbzjg3;
	
	private String save_shsjkssj1;
	
	private String save_shsjjssj1;
	
	private String save_shsjdw1;
	
	private String save_shsjcx1;
	
	private String save_shsjkssj2;
	
	private String save_shsjjssj2;
	
	private String save_shsjdw2;
	
	private String save_shsjcx2;
	
	private String save_shsjkssj3;
	
	private String save_shsjjssj3;
	
	private String save_shsjdw3;
	
	private String save_shsjcx3;
	
	//改派、二次分配 
	private String gpdw;//改派单位
	
	private String dwszd;//单位所在地
	
	private String gpsj;//改派时间
	
	private String gpr;//改派人
	
	private String gpyy;//改派原因
	
	private String ydw;//原单位
	
	private String ydwszd;//原单位所在地
	
	private String ecfpdw;//二次分配单位
	
	private String ecfpdwszd;//二次分配单位所在地
	
	private String ecfpsj;//二次分配时间
	
	private String ecfpr;//二次分配人
	
	private String ecfpyy;//二次分配原因
	
	
	//自定义表单
	private String[] arrZd;

	private String[] arrZdz;
	
	private String[] arrZdlx;
	
	private String[] arrZdcd;
	
	private String[] pkV;
	
	private HashMap<String, ArrayList<HashMap<String, String>>> opslist;
	
	/*
	 * 就业协议书管理
	 */
	private String save_sflq;//是否领取
	
	private String save_lqqk;//领取情况
	
	private String save_lqsj;//领取时间
	
	private String save_bblb;//补办类别
	
	private String save_sqbg;//申请报告
	
	private String save_xxysbh;//新协议书编号
	
	private String queryequals_xysbh;
	
	private String queryequals_xxysbh;
	
	private String queryequals_hyfldm;
	
	private String querylike_xsgb;//学生干部
	
	private String querylike_lxdk;//离校待考
	/*
	 *福建工程就业字段 
	 */
	private String xydm;
	
	private String zydm;
	
	private String bjdm;
	
	private String nf;
	
	private String xh;
	
	private String xm;
	
	private String nj;
	
	private String save_sflxdk;//是否离校待考
	
	private String save_lxdk;//离校待考
	
	private String save_sfjy;//是否解约
	
	private String save_sfwy;//是否违约
	
	private String save_xjbdqk;//学籍变动情况
	
	private String save_xsgb;//学生干部
	
	private String save_yydj;//英语等级
	
	private String save_lxfs;//联系方式
	
	private String save_hmcym;//花名册页码
	
	private String save_hmcqk;//花名册核对情况
	
	private String save_bz;//备注
	
	private String save_kqxx;//考取学校
	
	private String save_bdzdw;//报到证单位
	
	private String save_tjlx;//推荐类型
	
	private String save_jyqk;//解约情况
	
	private String save_wjybz;//未就业标志
	
	private String save_wjyyy;//未就业原因
	
	private String save_zzjgdm;//组织机构代码
	
	private String save_dajsd;//档案接收地
	
	private String save_dajsddz;//档案接收地地址
	
	private String save_dajsdyb;//档案接收地邮编
	
	private String save_syqgz;//试用期工资
	
	private String save_zzhgz;//转正后工资
	
	private String save_djrq;//登记日期
	
	private String dwyb;//单位邮编
	
	private String save_xh;//学号
	
	private String save_xm;//姓名
	
	private String save_xb;//性别
	
	private String save_nj;//年级 
	
	private String save_xxmc;
	
	private String save_xydm;//学院代码
	
	private String save_zydm;//专业代码
	
	private String save_bjdm;//班级代码
	
	private String save_xldm;//学历代码
	
	private String save_fdy;//辅导员
	
	private String save_rxnd;//入学年度
	
	private String save_bynd;//毕业年度
	
	private String save_xysbh;//协议书编号
	
	private String save_bbyy;//补办原因
	
	private String save_sxcl;//所需材料
	
	private String save_sqsj;//申请时间
	
	private String save_xysh;//学院审核
	
	private String save_xyyj;//学院意见
	
	private String save_sfzh;//身份证号
	
	private String save_csrq;//出生日期

	private String save_xbdm;//性别代码
	
	private String save_mzdm;//民族代码
	
	private String save_zzmm;//政治面貌

	private String save_lxdz;//联系地址
	
	private String save_yzbh;//邮政编号
	
	private String save_lxdh;//联系电话
	
	private String save_sjhm;//手机号码
	
	private String save_qq;//QQ
	
	private String save_dzyx;//电子邮箱
	
	private String save_xz;//学制
	
	private String save_xxdm;//学校代码
	
	private String save_sfzz;//是否在职
	
	private String save_sfsf;//是否师范
	
	private String save_sfdl;//是否独立
	
	private String save_dxhwp;//定向或委培单位
	
	private String save_zslbdm;//招生类别代码
	
	private String save_pyfsdm;//培养方式代码
	
	private String save_xlccdm;//学历层次代码
	
	private String save_bynf;//毕业年份
	
	private String save_rxnf;//入学年份
	
	private String save_gbzydm;//国标专业
	
	private String save_sydshen;//生源地省
	
	private String save_sydshi;//生源地市
	
	private String save_sydxian;//生源地县
	
	private String save_shzt;//审核状态
	
	private String save_shyj;//审核意见
	
	private String save_shsj;//审核时间
	
	private String save_shr;//审核人
	
	private String save_ksh;//考生号
	
	
	/*就业协议*/
	private String save_jybz;//就业标志
	
	private String save_dwxz;//单位性质
	
	private String save_dwdm;//单位代码
	
	private String save_dwmc;//单位名称
	
	private String save_zgdwdm;//主管单位代码
	
	private String save_zgdwmc;//主管单位名称
	
	private String save_lsbm;//隶属部门
	
	private String save_bdkssj;//报到开始时间
	
	private String save_bdjssj;//报到结束时间
	
	private String save_xjcqk;//下基层情况
	
	private String save_datddw;//档案投递单位
	
	private String save_datddz;//档案投递地址
	
	private String save_hkqydz;//户口迁移地址
	
	private String save_dwlxr;//单位联系人
	
	private String save_dwlxdh;//单位联系电话
	
	private String save_sfdk;//是否对口
	
	private String save_jyhy;//就业行业
	
	private String save_jybz1;
	
	private String save_jybz2;
	
	private String save_jybz3;
	
	private String save_yrdwszd;//用人单位所在地
	
	private String save_bddq;//报到地区
	
	private String save_xxsh;//学校审核
	
	private String save_xyshyj;//学院审核意见
	
	private String save_xxshyj;//学校审核意见
	
	private String save_xyshsj;//学院审核时间
	
	private String save_xxshsj;//学院审核时间
	
	private String save_xyshr;//学院审核人
	
	private String save_xxshr;//学校审核人
	
	private String save_dm;//代码
	
	private String save_mc;//名称
	
	private String save_dwszd;//单位所在地
	
	private String save_zgdw;//主管单位
	
	private String yrdw;
	
	private String jyhy;
	
	private String xysh;//学院审核
	
	private String xxsh;//学校审核
	
	private String xxshr;//学校审核人
	
	private String jybz;//就业标志
	
	private String sfdk;//是否对口
	
	private String save_zyfx;//专业方向
	
	private String save_zxwyyzdm;//主修外语语种
	
	private String save_byqx;//毕业去向
	
	private String save_sjdwszd;//实际所在地
	
	private String save_bdzh;//报到证号
	
	private String save_pqsj;//派遣时间
	
	private String save_daclqk;//档案处理情况
	
	private String save_gpcs;//改派次数
	
	private String save_gpyy;//改派原因
	
	private String save_yyrdw;//原用人单位
	
	private String save_ybdzh;//原报到证号
	
	private String querylike_datddz;
	
	private String querylike_hkqydz;
	
	private String queryequals_bbyy;
	
	private String queryequals_sxcl;
	
	private String queryequals_nj;
	
	private String queryequals_nd;
	
	private String queryequals_bynd;
	
	private String queryequals_xslb;
	
	private String queryequals_xydm;
	
	private String queryequals_zydm;
	
	private String queryequals_bjdm;
	
	private String querylike_xysbh;
	
	private String querylike_xh;
	
	private String querylike_xm;
	
	private String querylike_xsxh;
	
	private String querylike_name;
	
	private String queryequals_rxnf;
	
	private String queryequals_bynf;
	
	private String queryequals_xlccdm;
	
	private String queryequals_pyfsdm;
	
	private String queryequals_zslbdm;
	
	private String queryequals_sfzz;
	
	private String queryequals_sfsf;
	
	private String queryequals_sfdl;
	
	private String queryequals_sydshen;
	
	private String queryequals_sydshi;
	
	private String queryequals_sydxian;
	
	private String queryequals_shzt;
	
	private String queryequals_jybz;
	
	private String queryequals_dwxz;
	
	private String queryequals_sfdk;
	
	private String queryequals_jyhy;
	
	private String queryequals_xysh;
	
	private String queryequals_xxsh;
	
	private String querylike_dm;
	
	private String querylike_mc;
	
	private String queryequals_zxwyyzdm;
	
	private String querygreaterequal_tdrq;
	
	private String querylessequal_tdrq;
	
	private String querylike_sflxdk;
	
	private String querylike_xjbdqk;
	
//	private String queryequals_sydshen;
//	
//	private String queryequals_sydshi;
//	
//	private String queryequals_sydxian;
	
	//========================================招聘信息发布 sjf========================================
	private String save_gsmc;//公司名称
	
	private String save_zpzw;//招聘名称
	
	private String save_gwxz;//岗位性质
	
	private String save_zprs;//招聘人数
	
	private String save_xlyq;//学历要求
	
	private String save_wyyq;//外语要求
	
	private String save_fbsj;//发布时间
	
	private String save_yxqx;//有效期限
	
	private String save_gwzz;//岗位职责
	
	private String save_zwyq;//岗位要求
	
	private String querylike_gsmc;
	
	private String querylike_zpzw; 
	
	private String queryequals_gwxz;
	
	private String save_hfxx;
	
	//================================单位注册 sjf================================
	private String yhm;//用户名
	
	private String oldmm;//旧密码
	
	private String mm;//密码
	
	private String mm2;//密码2
	
	private String dwlx;//单位类型
	
	private String qyfr;//企业法人
	
	private String lxdh;//联系电话
	
	private String dwmc;//单位名称
	
	private String da1;//答案1
	
	private String da2;//答案2
	
	private String tswt1;//提示问题1
	
	private String tswt2;//提示问题2
	//========================================end================================
	
	//========================================招聘信息回复 sjf====================
	private String hfxx;
	
	private String czlx;
	
	private String gwmc;
	
	private String querygreaterequal_sj;
	
	private String querylessequal_sj;
	
	//========================================end ==============================
	
	//========================================考研考公  就业援助sjf====================
	private String sqxm;
	
	private String save_bkmb;
	
	private String save_fs;
	
	private String save_sfsx;
	
	private String save_sfxtj;
	
	private String save_lqdw;
	
	private String save_bklx;
	
	private String save_tjdw;
	
	private String save_cyxs;
	
	private String save_yzjg;
	
	private String queryequals_mk;
	
	private String queryequals_yzjg;
	
	//========================================end ==============================
	
	// --------------------毕业生上报 begin------------------------
	private String sbr;
	
	private String sbnd;
	
	private String csrq;     //出生日期
	
	private String xz;     //学制
	
	private String dzyx;     //电子邮箱
	
	private String shyj;     //审核意见
	
	private String sflxdk;     //是否离校待考
	
	private String qrrbh;     //确认人
	
	private String mzmc;     //民族
	
	private String zslb;     //招生类别
	
	private String zslbdm;     //招生类别代码
	
	private String xyshr;     //学院审核人
	
	private String zxwyyzdm;     //主修外语语种代码
	
	private String xsgb;     //学生干部
	
	private String lxdk;     //离校待考
	
	private String qrsj;     //确认时间
	
	private String pyfs;     //培养方式
	
	private String zymc;     //专业名称
	
	private String xbdm;     //性别代码
	
	private String sydxian;     //生源地县代码
	
	private String sfzz;     //是否在职
	
	private String sjhm;     //手机号码
	
	private String qq;     //QQ号码
	
	private String zyfx;     //专业方向
	
	private String ksh;     //考生号
	
	private String hmcym;     //花名册页码
	
	private String bz;     //备注
	
	private String je;     //届
	
	private String ysyd;     //原生源地
	
	private String bjmc;     //所在班级
	
	private String gbzy;     //国标专业
	
	private String gbzydm;     //国标专业代码
	
	private String dxhwp;     //定向或委培单位
	
	private String shzt;     //审核状态
	
	private String hmcqk;     //花名册核对情况
	
	private String isjys;     //是否结业生
	
	private String qrr;     //确认人
	
	private String zzmmmc;     //政治面貌
	
	private String sydq;     //生源地省
	
	private String syds;     //生源地市
	
	private String sydx;     //生源地县
	
	private String zzmm;     //政治面貌代码
	
	private String rxnf;     //入学年份
	
	private String yzbh;     //邮编
	
	private String xjbdqk;     //学籍变动情况
	
	private String yydj;     //英语等级
	
	private String sbsj;     //上报时间
	
	private String sfxg;     //是否修改
	
	private String xl;     //学历
	
	private String sfsf;     //是否师范
	
	private String xyshyj;     //学院审核意见
	
	private String sbrzgh;     //上报人职工号
	
	private String xxmc;     //学校名称
	
	private String mzdm;     //民族代码
	
	private String sfdl;     //是否独立
	
	private String pyfsdm;     //培养方式代码
	
	private String lxdz;     //联系地址
	
	private String shsj;     //审核时间
	
	private String xyshzt;     //学院审核
	
	private String sfqr;     //是否确认
	
	private String xxdm;     //学校代码
	
	private String xymc;     //院系名称

	private String sydshen;     //生源地省代码
	
	private String sydshi;     //生源地市代码
	
	private String xlccdm;     //学历层次代码
	
	private String bynf;     //毕业年份
	
	private String xyshsj;     //学院审核时间
	
	private String xb;     //性别
	
	private String sybdzt; //
	
	private String yrdwshen;
	
	private String yrdwshi;
	
	private String yrdwxian;
	
	private String bddqshen;
	
	private String bddqshi;
	
	private String bddqxian;
	
	// --------------------毕业生上报 end------------------
	
	public String getYrdwshen() {
		return yrdwshen;
	}

	public void setYrdwshen(String yrdwshen) {
		this.yrdwshen = yrdwshen;
	}

	public String getYrdwshi() {
		return yrdwshi;
	}

	public void setYrdwshi(String yrdwshi) {
		this.yrdwshi = yrdwshi;
	}

	public String getYrdwxian() {
		return yrdwxian;
	}

	public void setYrdwxian(String yrdwxian) {
		this.yrdwxian = yrdwxian;
	}

	public String getSave_zplx() {
		return save_zplx;
	}

	public void setSave_zplx(String saveZplx) {
		save_zplx = saveZplx;
	}
	
	public String getSave_zpsj() {
		return save_zpsj;
	}

	public String getSave_zpzt() {
		return save_zpzt;
	}

	public void setSave_zpzt(String saveZpzt) {
		save_zpzt = saveZpzt;
	}

	public String getSave_zpnr() {
		return save_zpnr;
	}

	public void setSave_zpnr(String saveZpnr) {
		save_zpnr = saveZpnr;
	}

	public void setSave_zpsj(String saveZpsj) {
		save_zpsj = saveZpsj;
	}

	public String getSave_zpdd() {
		return save_zpdd;
	}

	public void setSave_zpdd(String saveZpdd) {
		save_zpdd = saveZpdd;
	}

	
	public String getSave_zplxdm() {
		return save_zplxdm;
	}

	public void setSave_zplxdm(String saveZplxdm) {
		save_zplxdm = saveZplxdm;
	}

	public String getQueryequals_zplxdm() {
		return queryequals_zplxdm;
	}

	public void setQueryequals_zplxdm(String queryequalsZplxdm) {
		queryequals_zplxdm = queryequalsZplxdm;
	}
	
	public String getQueryequals_zxwyyzdm() {
		return queryequals_zxwyyzdm;
	}

	public String getSqxm() {
		return sqxm;
	}

	public void setSqxm(String sqxm) {
		this.sqxm = sqxm;
	}

	public String getSave_bkmb() {
		return save_bkmb;
	}

	public void setSave_bkmb(String saveBkmb) {
		save_bkmb = saveBkmb;
	}

	public String getSave_fs() {
		return save_fs;
	}

	public void setSave_fs(String saveFs) {
		save_fs = saveFs;
	}

	public String getSave_sfsx() {
		return save_sfsx;
	}

	public void setSave_sfsx(String saveSfsx) {
		save_sfsx = saveSfsx;
	}

	public String getSave_sfxtj() {
		return save_sfxtj;
	}

	public void setSave_sfxtj(String saveSfxtj) {
		save_sfxtj = saveSfxtj;
	}

	public String getSave_lqdw() {
		return save_lqdw;
	}

	public void setSave_lqdw(String saveLqdw) {
		save_lqdw = saveLqdw;
	}

	public String getSave_bklx() {
		return save_bklx;
	}

	public void setSave_bklx(String saveBklx) {
		save_bklx = saveBklx;
	}

	public String getSave_tjdw() {
		return save_tjdw;
	}

	public void setSave_tjdw(String saveTjdw) {
		save_tjdw = saveTjdw;
	}

	public String getSave_cyxs() {
		return save_cyxs;
	}

	public void setSave_cyxs(String saveCyxs) {
		save_cyxs = saveCyxs;
	}

	public String getSave_yzjg() {
		return save_yzjg;
	}

	public void setSave_yzjg(String saveYzjg) {
		save_yzjg = saveYzjg;
	}

	public String getQueryequals_mk() {
		return queryequals_mk;
	}

	public void setQueryequals_mk(String queryequalsMk) {
		queryequals_mk = queryequalsMk;
	}

	public String getQueryequals_yzjg() {
		return queryequals_yzjg;
	}

	public void setQueryequals_yzjg(String queryequalsYzjg) {
		queryequals_yzjg = queryequalsYzjg;
	}

	public String getQuerygreaterequal_sj() {
		return querygreaterequal_sj;
	}

	public void setQuerygreaterequal_sj(String querygreaterequalSj) {
		querygreaterequal_sj = querygreaterequalSj;
	}

	public String getQuerylessequal_sj() {
		return querylessequal_sj;
	}

	public void setQuerylessequal_sj(String querylessequalSj) {
		querylessequal_sj = querylessequalSj;
	}

	public String getGwmc() {
		return gwmc;
	}

	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}

	public String getHfxx() {
		return hfxx;
	}

	public void setHfxx(String hfxx) {
		this.hfxx = hfxx;
	}

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

	public String getSave_gsmc() {
		return save_gsmc;
	}

	public void setSave_gsmc(String saveGsmc) {
		save_gsmc = saveGsmc;
	}

	public String getSave_zpzw() {
		return save_zpzw;
	}

	public void setSave_zpzw(String saveZpzw) {
		save_zpzw = saveZpzw;
	}

	public String getSave_gwxz() {
		return save_gwxz;
	}

	public void setSave_gwxz(String saveGwxz) {
		save_gwxz = saveGwxz;
	}

	public String getSave_zprs() {
		return save_zprs;
	}

	public void setSave_zprs(String saveZprs) {
		save_zprs = saveZprs;
	}

	public String getSave_xlyq() {
		return save_xlyq;
	}

	public void setSave_xlyq(String saveXlyq) {
		save_xlyq = saveXlyq;
	}

	public String getSave_wyyq() {
		return save_wyyq;
	}

	public void setSave_wyyq(String saveWyyq) {
		save_wyyq = saveWyyq;
	}

	public String getSave_fbsj() {
		return save_fbsj;
	}

	public void setSave_fbsj(String saveFbsj) {
		save_fbsj = saveFbsj;
	}

	public String getSave_yxqx() {
		return save_yxqx;
	}

	public void setSave_yxqx(String saveYxqx) {
		save_yxqx = saveYxqx;
	}

	public String getSave_gwzz() {
		return save_gwzz;
	}

	public void setSave_gwzz(String saveGwzz) {
		save_gwzz = saveGwzz;
	}

	public String getSave_zwyq() {
		return save_zwyq;
	}

	public void setSave_zwyq(String saveZwyq) {
		save_zwyq = saveZwyq;
	}

	public String getQuerylike_gsmc() {
		return querylike_gsmc;
	}

	public void setQuerylike_gsmc(String querylikeGsmc) {
		querylike_gsmc = querylikeGsmc;
	}

	public String getQuerylike_zpzw() {
		return querylike_zpzw;
	}

	public void setQuerylike_zpzw(String querylikeZpzw) {
		querylike_zpzw = querylikeZpzw;
	}

	public String getQueryequals_gwxz() {
		return queryequals_gwxz;
	}

	public void setQueryequals_gwxz(String queryequalsGwxz) {
		queryequals_gwxz = queryequalsGwxz;
	}

	public void setQueryequals_zxwyyzdm(String queryequals_zxwyyzdm) {
		this.queryequals_zxwyyzdm = queryequals_zxwyyzdm;
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

	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}

	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}

	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}

	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}

	public String getQueryequals_dwxz() {
		return queryequals_dwxz;
	}

	public void setQueryequals_dwxz(String queryequals_dwxz) {
		this.queryequals_dwxz = queryequals_dwxz;
	}

	public String getQueryequals_jybz() {
		return queryequals_jybz;
	}

	public void setQueryequals_jybz(String queryequals_jybz) {
		this.queryequals_jybz = queryequals_jybz;
	}

	public String getQueryequals_jyhy() {
		return queryequals_jyhy;
	}

	public void setQueryequals_jyhy(String queryequals_jyhy) {
		this.queryequals_jyhy = queryequals_jyhy;
	}

	public String getQueryequals_sfdk() {
		return queryequals_sfdk;
	}

	public void setQueryequals_sfdk(String queryequals_sfdk) {
		this.queryequals_sfdk = queryequals_sfdk;
	}

	public String getQueryequals_shzt() {
		return queryequals_shzt;
	}

	public void setQueryequals_shzt(String queryequals_shzt) {
		this.queryequals_shzt = queryequals_shzt;
	}

	public String getQueryequals_rxnf() {
		return queryequals_rxnf;
	}

	public void setQueryequals_rxnf(String queryequals_rxnf) {
		this.queryequals_rxnf = queryequals_rxnf;
	}

	public String getQuerylike_name() {
		return querylike_name;
	}

	public void setQuerylike_name(String querylike_name) {
		this.querylike_name = querylike_name;
	}

	public String getQuerylike_xsxh() {
		return querylike_xsxh;
	}

	public void setQuerylike_xsxh(String querylike_xsxh) {
		this.querylike_xsxh = querylike_xsxh;
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

	public String getQuerylike_xysbh() {
		return querylike_xysbh;
	}

	public void setQuerylike_xysbh(String querylike_xysbh) {
		this.querylike_xysbh = querylike_xysbh;
	}

	public String getQueryequals_bbyy() {
		return queryequals_bbyy;
	}

	public void setQueryequals_bbyy(String queryequals_bbyy) {
		this.queryequals_bbyy = queryequals_bbyy;
	}

	public String getQueryequals_sxcl() {
		return queryequals_sxcl;
	}

	public void setQueryequals_sxcl(String queryequals_sxcl) {
		this.queryequals_sxcl = queryequals_sxcl;
	}

	public String getSave_sqsj() {
		return save_sqsj;
	}

	public void setSave_sqsj(String save_sqsj) {
		this.save_sqsj = save_sqsj;
	}

	public String getSave_bbyy() {
		return save_bbyy;
	}

	public void setSave_bbyy(String save_bbyy) {
		this.save_bbyy = save_bbyy;
	}

	public String getSave_sxcl() {
		return save_sxcl;
	}

	public void setSave_sxcl(String save_sxcl) {
		this.save_sxcl = save_sxcl;
	}

	public String getSave_bjdm() {
		return save_bjdm;
	}

	public void setSave_bjdm(String save_bjdm) {
		this.save_bjdm = save_bjdm;
	}

	public String getSave_bynd() {
		return save_bynd;
	}

	public void setSave_bynd(String save_bynd) {
		this.save_bynd = save_bynd;
	}

	public String getSave_fdy() {
		return save_fdy;
	}

	public void setSave_fdy(String save_fdy) {
		this.save_fdy = save_fdy;
	}

	public String getSave_rxnd() {
		return save_rxnd;
	}

	public void setSave_rxnd(String save_rxnd) {
		this.save_rxnd = save_rxnd;
	}

	public String getSave_xb() {
		return save_xb;
	}

	public void setSave_xb(String save_xb) {
		this.save_xb = save_xb;
	}

	public String getSave_xh() {
		return save_xh;
	}

	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}

	public String getSave_xldm() {
		return save_xldm;
	}

	public void setSave_xldm(String save_xldm) {
		this.save_xldm = save_xldm;
	}

	public String getSave_xm() {
		return save_xm;
	}

	public void setSave_xm(String save_xm) {
		this.save_xm = save_xm;
	}

	public String getSave_xydm() {
		return save_xydm;
	}

	public void setSave_xydm(String save_xydm) {
		this.save_xydm = save_xydm;
	}

	public String getSave_xysbh() {
		return save_xysbh;
	}

	public void setSave_xysbh(String save_xysbh) {
		this.save_xysbh = save_xysbh;
	}

	public String getSave_zydm() {
		return save_zydm;
	}

	public void setSave_zydm(String save_zydm) {
		this.save_zydm = save_zydm;
	}

	public String getSave_nj() {
		return save_nj;
	}

	public void setSave_nj(String save_nj) {
		this.save_nj = save_nj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
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

	public String getSave_xysh() {
		return save_xysh;
	}

	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}

	public String getSave_xyyj() {
		return save_xyyj;
	}

	public void setSave_xyyj(String save_xyyj) {
		this.save_xyyj = save_xyyj;
	}

	public String getQueryequals_xslb() {
		return queryequals_xslb;
	}

	public void setQueryequals_xslb(String queryequals_xslb) {
		this.queryequals_xslb = queryequals_xslb;
	}

	public String getQueryequals_bynd() {
		return queryequals_bynd;
	}

	public void setQueryequals_bynd(String queryequals_bynd) {
		this.queryequals_bynd = queryequals_bynd;
	}

	public String getQueryequals_nd() {
		return queryequals_nd;
	}

	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
	}

	public String getSave_sfzh() {
		return save_sfzh;
	}

	public void setSave_sfzh(String save_sfzh) {
		this.save_sfzh = save_sfzh;
	}

	public String getSave_csrq() {
		return save_csrq;
	}

	public void setSave_csrq(String save_csrq) {
		this.save_csrq = save_csrq;
	}

	public String getSave_xbdm() {
		return save_xbdm;
	}

	public void setSave_xbdm(String save_xbdm) {
		this.save_xbdm = save_xbdm;
	}

	public String getSave_mzdm() {
		return save_mzdm;
	}

	public void setSave_mzdm(String save_mzdm) {
		this.save_mzdm = save_mzdm;
	}

	public String getSave_zzmm() {
		return save_zzmm;
	}

	public void setSave_zzmm(String save_zzmm) {
		this.save_zzmm = save_zzmm;
	}

	public String getSave_lxdz() {
		return save_lxdz;
	}

	public void setSave_lxdz(String save_lxdz) {
		this.save_lxdz = save_lxdz;
	}

	public String getSave_yzbh() {
		return save_yzbh;
	}

	public void setSave_yzbh(String save_yzbh) {
		this.save_yzbh = save_yzbh;
	}

	public String getSave_lxdh() {
		return save_lxdh;
	}

	public void setSave_lxdh(String save_lxdh) {
		this.save_lxdh = save_lxdh;
	}

	public String getSave_sjhm() {
		return save_sjhm;
	}

	public void setSave_sjhm(String save_sjhm) {
		this.save_sjhm = save_sjhm;
	}

	public String getSave_qq() {
		return save_qq;
	}

	public void setSave_qq(String save_qq) {
		this.save_qq = save_qq;
	}

	public String getSave_dzyx() {
		return save_dzyx;
	}

	public void setSave_dzyx(String save_dzyx) {
		this.save_dzyx = save_dzyx;
	}

	public String getSave_xz() {
		return save_xz;
	}

	public void setSave_xz(String save_xz) {
		this.save_xz = save_xz;
	}

	public String getSave_xxdm() {
		return save_xxdm;
	}

	public void setSave_xxdm(String save_xxdm) {
		this.save_xxdm = save_xxdm;
	}

	public String getSave_sfdl() {
		return save_sfdl;
	}

	public void setSave_sfdl(String save_sfdl) {
		this.save_sfdl = save_sfdl;
	}

	public String getSave_sfsf() {
		return save_sfsf;
	}

	public void setSave_sfsf(String save_sfsf) {
		this.save_sfsf = save_sfsf;
	}

	public String getSave_sfzz() {
		return save_sfzz;
	}

	public void setSave_sfzz(String save_sfzz) {
		this.save_sfzz = save_sfzz;
	}

	public String getSave_dxhwp() {
		return save_dxhwp;
	}

	public void setSave_dxhwp(String save_dxhwp) {
		this.save_dxhwp = save_dxhwp;
	}

	public String getSave_zslbdm() {
		return save_zslbdm;
	}

	public void setSave_zslbdm(String save_zslbdm) {
		this.save_zslbdm = save_zslbdm;
	}


	public String getSave_xlccdm() {
		return save_xlccdm;
	}

	public void setSave_xlccdm(String save_xlccdm) {
		this.save_xlccdm = save_xlccdm;
	}

	public String getSave_bynf() {
		return save_bynf;
	}

	public void setSave_bynf(String save_bynf) {
		this.save_bynf = save_bynf;
	}

	public String getSave_rxnf() {
		return save_rxnf;
	}

	public void setSave_rxnf(String save_rxnf) {
		this.save_rxnf = save_rxnf;
	}

	public String getSave_gbzydm() {
		return save_gbzydm;
	}

	public void setSave_gbzydm(String save_gbzydm) {
		this.save_gbzydm = save_gbzydm;
	}


	public String getSave_sydshen() {
		return save_sydshen;
	}

	public void setSave_sydshen(String save_sydshen) {
		this.save_sydshen = save_sydshen;
	}

	public String getSave_sydshi() {
		return save_sydshi;
	}

	public void setSave_sydshi(String save_sydshi) {
		this.save_sydshi = save_sydshi;
	}

	public String getSave_sydxian() {
		return save_sydxian;
	}

	public void setSave_sydxian(String save_sydxian) {
		this.save_sydxian = save_sydxian;
	}

	public String getSave_pyfsdm() {
		return save_pyfsdm;
	}

	public void setSave_pyfsdm(String save_pyfsdm) {
		this.save_pyfsdm = save_pyfsdm;
	}

	public String getSave_shr() {
		return save_shr;
	}

	public void setSave_shr(String save_shr) {
		this.save_shr = save_shr;
	}

	public String getSave_shsj() {
		return save_shsj;
	}

	public void setSave_shsj(String save_shsj) {
		this.save_shsj = save_shsj;
	}

	public String getSave_shyj() {
		return save_shyj;
	}

	public void setSave_shyj(String save_shyj) {
		this.save_shyj = save_shyj;
	}

	public String getSave_shzt() {
		return save_shzt;
	}

	public void setSave_shzt(String save_shzt) {
		this.save_shzt = save_shzt;
	}

	public String getSave_bdjssj() {
		return save_bdjssj;
	}

	public void setSave_bdjssj(String save_bdjssj) {
		this.save_bdjssj = save_bdjssj;
	}

	public String getSave_bdkssj() {
		return save_bdkssj;
	}

	public void setSave_bdkssj(String save_bdkssj) {
		this.save_bdkssj = save_bdkssj;
	}

	public String getSave_datddw() {
		return save_datddw;
	}

	public void setSave_datddw(String save_datddw) {
		this.save_datddw = save_datddw;
	}

	public String getSave_datddz() {
		return save_datddz;
	}

	public void setSave_datddz(String save_datddz) {
		this.save_datddz = save_datddz;
	}

	public String getSave_dwdm() {
		return save_dwdm;
	}

	public void setSave_dwdm(String save_dwdm) {
		this.save_dwdm = save_dwdm;
	}

	public String getSave_dwlxdh() {
		return save_dwlxdh;
	}

	public void setSave_dwlxdh(String save_dwlxdh) {
		this.save_dwlxdh = save_dwlxdh;
	}

	public String getSave_dwlxr() {
		return save_dwlxr;
	}

	public void setSave_dwlxr(String save_dwlxr) {
		this.save_dwlxr = save_dwlxr;
	}

	public String getSave_dwmc() {
		return save_dwmc;
	}

	public void setSave_dwmc(String save_dwmc) {
		this.save_dwmc = save_dwmc;
	}

	public String getSave_dwxz() {
		return save_dwxz;
	}

	public void setSave_dwxz(String save_dwxz) {
		this.save_dwxz = save_dwxz;
	}

	public String getSave_hkqydz() {
		return save_hkqydz;
	}

	public void setSave_hkqydz(String save_hkqydz) {
		this.save_hkqydz = save_hkqydz;
	}

	public String getSave_jybz() {
		return save_jybz;
	}

	public void setSave_jybz(String save_jybz) {
		this.save_jybz = save_jybz;
	}

	public String getSave_jybz1() {
		return save_jybz1;
	}

	public void setSave_jybz1(String save_jybz1) {
		this.save_jybz1 = save_jybz1;
	}

	public String getSave_jybz2() {
		return save_jybz2;
	}

	public void setSave_jybz2(String save_jybz2) {
		this.save_jybz2 = save_jybz2;
	}

	public String getSave_jybz3() {
		return save_jybz3;
	}

	public void setSave_jybz3(String save_jybz3) {
		this.save_jybz3 = save_jybz3;
	}

	public String getSave_jyhy() {
		return save_jyhy;
	}

	public void setSave_jyhy(String save_jyhy) {
		this.save_jyhy = save_jyhy;
	}

	public String getSave_lsbm() {
		return save_lsbm;
	}

	public void setSave_lsbm(String save_lsbm) {
		this.save_lsbm = save_lsbm;
	}

	public String getSave_sfdk() {
		return save_sfdk;
	}

	public void setSave_sfdk(String save_sfdk) {
		this.save_sfdk = save_sfdk;
	}

	public String getSave_xjcqk() {
		return save_xjcqk;
	}

	public void setSave_xjcqk(String save_xjcqk) {
		this.save_xjcqk = save_xjcqk;
	}

	public String getSave_zgdwdm() {
		return save_zgdwdm;
	}

	public void setSave_zgdwdm(String save_zgdwdm) {
		this.save_zgdwdm = save_zgdwdm;
	}

	public String getSave_zgdwmc() {
		return save_zgdwmc;
	}

	public void setSave_zgdwmc(String save_zgdwmc) {
		this.save_zgdwmc = save_zgdwmc;
	}

	public String getSave_bddq() {
		return save_bddq;
	}

	public void setSave_bddq(String save_bddq) {
		this.save_bddq = save_bddq;
	}

	public String getSave_yrdwszd() {
		return save_yrdwszd;
	}

	public void setSave_yrdwszd(String save_yrdwszd) {
		this.save_yrdwszd = save_yrdwszd;
	}

	public String getSave_xxsh() {
		return save_xxsh;
	}

	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}

	public String getSave_xxshr() {
		return save_xxshr;
	}

	public void setSave_xxshr(String save_xxshr) {
		this.save_xxshr = save_xxshr;
	}

	public String getSave_xxshsj() {
		return save_xxshsj;
	}

	public void setSave_xxshsj(String save_xxshsj) {
		this.save_xxshsj = save_xxshsj;
	}

	public String getSave_xxshyj() {
		return save_xxshyj;
	}

	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
	}

	public String getSave_xyshr() {
		return save_xyshr;
	}

	public void setSave_xyshr(String save_xyshr) {
		this.save_xyshr = save_xyshr;
	}

	public String getSave_xyshsj() {
		return save_xyshsj;
	}

	public void setSave_xyshsj(String save_xyshsj) {
		this.save_xyshsj = save_xyshsj;
	}

	public String getSave_xyshyj() {
		return save_xyshyj;
	}

	public void setSave_xyshyj(String save_xyshyj) {
		this.save_xyshyj = save_xyshyj;
	}

	public String getSave_dm() {
		return save_dm;
	}

	public void setSave_dm(String save_dm) {
		this.save_dm = save_dm;
	}

	public String getSave_mc() {
		return save_mc;
	}

	public void setSave_mc(String save_mc) {
		this.save_mc = save_mc;
	}

	public String getSave_dwszd() {
		return save_dwszd;
	}

	public void setSave_dwszd(String save_dwszd) {
		this.save_dwszd = save_dwszd;
	}

	public String getSave_zgdw() {
		return save_zgdw;
	}

	public void setSave_zgdw(String save_zgdw) {
		this.save_zgdw = save_zgdw;
	}

	public String getQueryequals_bynf() {
		return queryequals_bynf;
	}

	public void setQueryequals_bynf(String queryequals_bynf) {
		this.queryequals_bynf = queryequals_bynf;
	}

	public String getQueryequals_pyfsdm() {
		return queryequals_pyfsdm;
	}

	public void setQueryequals_pyfsdm(String queryequals_pyfsdm) {
		this.queryequals_pyfsdm = queryequals_pyfsdm;
	}

	public String getQueryequals_sfdl() {
		return queryequals_sfdl;
	}

	public void setQueryequals_sfdl(String queryequals_sfdl) {
		this.queryequals_sfdl = queryequals_sfdl;
	}

	public String getQueryequals_sfsf() {
		return queryequals_sfsf;
	}

	public void setQueryequals_sfsf(String queryequals_sfsf) {
		this.queryequals_sfsf = queryequals_sfsf;
	}

	public String getQueryequals_sfzz() {
		return queryequals_sfzz;
	}

	public void setQueryequals_sfzz(String queryequals_sfzz) {
		this.queryequals_sfzz = queryequals_sfzz;
	}

	public String getQueryequals_sydshen() {
		return queryequals_sydshen;
	}

	public void setQueryequals_sydshen(String queryequals_sydshen) {
		this.queryequals_sydshen = queryequals_sydshen;
	}

	public String getQueryequals_sydshi() {
		return queryequals_sydshi;
	}

	public void setQueryequals_sydshi(String queryequals_sydshi) {
		this.queryequals_sydshi = queryequals_sydshi;
	}

	public String getQueryequals_sydxian() {
		return queryequals_sydxian;
	}

	public void setQueryequals_sydxian(String queryequals_sydxian) {
		this.queryequals_sydxian = queryequals_sydxian;
	}

	public String getQueryequals_xlccdm() {
		return queryequals_xlccdm;
	}

	public void setQueryequals_xlccdm(String queryequals_xlccdm) {
		this.queryequals_xlccdm = queryequals_xlccdm;
	}

	public String getQueryequals_zslbdm() {
		return queryequals_zslbdm;
	}

	public void setQueryequals_zslbdm(String queryequals_zslbdm) {
		this.queryequals_zslbdm = queryequals_zslbdm;
	}

	public String getYrdw() {
		return yrdw;
	}

	public void setYrdw(String yrdw) {
		this.yrdw = yrdw;
	}

	public String getSave_ksh() {
		return save_ksh;
	}

	public void setSave_ksh(String save_ksh) {
		this.save_ksh = save_ksh;
	}

	public String getSave_zxwyyzdm() {
		return save_zxwyyzdm;
	}

	public void setSave_zxwyyzdm(String save_zxwyyzdm) {
		this.save_zxwyyzdm = save_zxwyyzdm;
	}

	public String getSave_zyfx() {
		return save_zyfx;
	}

	public void setSave_zyfx(String save_zyfx) {
		this.save_zyfx = save_zyfx;
	}

	public String getSave_byqx() {
		return save_byqx;
	}

	public void setSave_byqx(String save_byqx) {
		this.save_byqx = save_byqx;
	}

	public String getSave_bdzh() {
		return save_bdzh;
	}

	public void setSave_bdzh(String save_bdzh) {
		this.save_bdzh = save_bdzh;
	}

	public String getSave_pqsj() {
		return save_pqsj;
	}

	public void setSave_pqsj(String save_pqsj) {
		this.save_pqsj = save_pqsj;
	}

	public String getSave_sjdwszd() {
		return save_sjdwszd;
	}

	public void setSave_sjdwszd(String save_sjdwszd) {
		this.save_sjdwszd = save_sjdwszd;
	}

	public String getSave_daclqk() {
		return save_daclqk;
	}

	public void setSave_daclqk(String save_daclqk) {
		this.save_daclqk = save_daclqk;
	}

	public String getSave_gpcs() {
		return save_gpcs;
	}

	public void setSave_gpcs(String save_gpcs) {
		this.save_gpcs = save_gpcs;
	}

	public String getSave_gpyy() {
		return save_gpyy;
	}

	public void setSave_gpyy(String save_gpyy) {
		this.save_gpyy = save_gpyy;
	}

	public String getSave_yyrdw() {
		return save_yyrdw;
	}

	public void setSave_yyrdw(String save_yyrdw) {
		this.save_yyrdw = save_yyrdw;
	}

	public String getSave_ybdzh() {
		return save_ybdzh;
	}

	public void setSave_ybdzh(String save_ybdzh) {
		this.save_ybdzh = save_ybdzh;
	}

	public String getQuerylike_datddz() {
		return querylike_datddz;
	}

	public void setQuerylike_datddz(String querylike_datddz) {
		this.querylike_datddz = querylike_datddz;
	}

	public String getQuerylike_hkqydz() {
		return querylike_hkqydz;
	}

	public void setQuerylike_hkqydz(String querylike_hkqydz) {
		this.querylike_hkqydz = querylike_hkqydz;
	}

	public String getSave_xxmc() {
		return save_xxmc;
	}

	public void setSave_xxmc(String save_xxmc) {
		this.save_xxmc = save_xxmc;
	}

	public String getSave_bz() {
		return save_bz;
	}

	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}

	public String getSave_hmcqk() {
		return save_hmcqk;
	}

	public void setSave_hmcqk(String save_hmcqk) {
		this.save_hmcqk = save_hmcqk;
	}

	public String getSave_hmcym() {
		return save_hmcym;
	}

	public void setSave_hmcym(String save_hmcym) {
		this.save_hmcym = save_hmcym;
	}

	public String getSave_lxfs() {
		return save_lxfs;
	}

	public void setSave_lxfs(String save_lxfs) {
		this.save_lxfs = save_lxfs;
	}

	public String getSave_sflxdk() {
		return save_sflxdk;
	}

	public void setSave_sflxdk(String save_sflxdk) {
		this.save_sflxdk = save_sflxdk;
	}

	public String getSave_xjbdqk() {
		return save_xjbdqk;
	}

	public void setSave_xjbdqk(String save_xjbdqk) {
		this.save_xjbdqk = save_xjbdqk;
	}

	public String getSave_xsgb() {
		return save_xsgb;
	}

	public void setSave_xsgb(String save_xsgb) {
		this.save_xsgb = save_xsgb;
	}

	public String getSave_yydj() {
		return save_yydj;
	}

	public void setSave_yydj(String save_yydj) {
		this.save_yydj = save_yydj;
	}

	public String getQuerylike_sflxdk() {
		return querylike_sflxdk;
	}

	public void setQuerylike_sflxdk(String querylike_sflxdk) {
		this.querylike_sflxdk = querylike_sflxdk;
	}

	public String getQuerylike_xjbdqk() {
		return querylike_xjbdqk;
	}

	public void setQuerylike_xjbdqk(String querylike_xjbdqk) {
		this.querylike_xjbdqk = querylike_xjbdqk;
	}

	public String getSave_bdzdw() {
		return save_bdzdw;
	}

	public void setSave_bdzdw(String save_bdzdw) {
		this.save_bdzdw = save_bdzdw;
	}

	public String getSave_jyqk() {
		return save_jyqk;
	}

	public void setSave_jyqk(String save_jyqk) {
		this.save_jyqk = save_jyqk;
	}

	public String getSave_kqxx() {
		return save_kqxx;
	}

	public void setSave_kqxx(String save_kqxx) {
		this.save_kqxx = save_kqxx;
	}

	public String getSave_tjlx() {
		return save_tjlx;
	}

	public void setSave_tjlx(String save_tjlx) {
		this.save_tjlx = save_tjlx;
	}

	public String getSave_wjybz() {
		return save_wjybz;
	}

	public void setSave_wjybz(String save_wjybz) {
		this.save_wjybz = save_wjybz;
	}

	public String getSave_wjyyy() {
		return save_wjyyy;
	}

	public void setSave_wjyyy(String save_wjyyy) {
		this.save_wjyyy = save_wjyyy;
	}

	public String getSave_zzjgdm() {
		return save_zzjgdm;
	}

	public void setSave_zzjgdm(String save_zzjgdm) {
		this.save_zzjgdm = save_zzjgdm;
	}

	public String getSave_dajsd() {
		return save_dajsd;
	}

	public void setSave_dajsd(String save_dajsd) {
		this.save_dajsd = save_dajsd;
	}

	public String getSave_dajsddz() {
		return save_dajsddz;
	}

	public void setSave_dajsddz(String save_dajsddz) {
		this.save_dajsddz = save_dajsddz;
	}

	public String getSave_dajsdyb() {
		return save_dajsdyb;
	}

	public void setSave_dajsdyb(String save_dajsdyb) {
		this.save_dajsdyb = save_dajsdyb;
	}

	public String getSave_syqgz() {
		return save_syqgz;
	}

	public void setSave_syqgz(String save_syqgz) {
		this.save_syqgz = save_syqgz;
	}

	public String getSave_zzhgz() {
		return save_zzhgz;
	}

	public void setSave_zzhgz(String save_zzhgz) {
		this.save_zzhgz = save_zzhgz;
	}

	public String getSave_djrq() {
		return save_djrq;
	}

	public void setSave_djrq(String save_djrq) {
		this.save_djrq = save_djrq;
	}

	public String getDwyb() {
		return dwyb;
	}

	public void setDwyb(String dwyb) {
		this.dwyb = dwyb;
	}

	public String getSave_lxdk() {
		return save_lxdk;
	}

	public void setSave_lxdk(String save_lxdk) {
		this.save_lxdk = save_lxdk;
	}

	public String getSave_sfjy() {
		return save_sfjy;
	}

	public void setSave_sfjy(String save_sfjy) {
		this.save_sfjy = save_sfjy;
	}

	public String getSave_sfwy() {
		return save_sfwy;
	}

	public void setSave_sfwy(String save_sfwy) {
		this.save_sfwy = save_sfwy;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getSave_lqqk() {
		return save_lqqk;
	}

	public void setSave_lqqk(String save_lqqk) {
		this.save_lqqk = save_lqqk;
	}

	public String getSave_lqsj() {
		return save_lqsj;
	}

	public void setSave_lqsj(String save_lqsj) {
		this.save_lqsj = save_lqsj;
	}

	public String getSave_sflq() {
		return save_sflq;
	}

	public void setSave_sflq(String save_sflq) {
		this.save_sflq = save_sflq;
	}

	public String getSave_bblb() {
		return save_bblb;
	}

	public void setSave_bblb(String save_bblb) {
		this.save_bblb = save_bblb;
	}

	public String getSave_sqbg() {
		return save_sqbg;
	}

	public void setSave_sqbg(String save_sqbg) {
		this.save_sqbg = save_sqbg;
	}

	public String getSave_xxysbh() {
		return save_xxysbh;
	}

	public void setSave_xxysbh(String save_xxysbh) {
		this.save_xxysbh = save_xxysbh;
	}

	public String getQueryequals_xxysbh() {
		return queryequals_xxysbh;
	}

	public void setQueryequals_xxysbh(String queryequals_xxysbh) {
		this.queryequals_xxysbh = queryequals_xxysbh;
	}

	public String getQueryequals_xysbh() {
		return queryequals_xysbh;
	}

	public void setQueryequals_xysbh(String queryequals_xysbh) {
		this.queryequals_xysbh = queryequals_xysbh;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
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

	public String[] getArrZd() {
		return arrZd;
	}

	public void setArrZd(String[] arrZd) {
		this.arrZd = arrZd;
	}

	public String[] getArrZdcd() {
		return arrZdcd;
	}

	public void setArrZdcd(String[] arrZdcd) {
		this.arrZdcd = arrZdcd;
	}

	public String[] getArrZdlx() {
		return arrZdlx;
	}

	public void setArrZdlx(String[] arrZdlx) {
		this.arrZdlx = arrZdlx;
	}

	public String[] getArrZdz() {
		return arrZdz;
	}

	public void setArrZdz(String[] arrZdz) {
		this.arrZdz = arrZdz;
	}

	public String[] getPkV() {
		return pkV;
	}

	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}

	public HashMap<String, ArrayList<HashMap<String, String>>> getOpslist() {
		return opslist;
	}

	public void setOpslist(
			HashMap<String, ArrayList<HashMap<String, String>>> opslist) {
		this.opslist = opslist;
	}

	public String getDwszd() {
		return dwszd;
	}

	public void setDwszd(String dwszd) {
		this.dwszd = dwszd;
	}

	public String getGpdw() {
		return gpdw;
	}

	public void setGpdw(String gpdw) {
		this.gpdw = gpdw;
	}

	public String getGpr() {
		return gpr;
	}

	public void setGpr(String gpr) {
		this.gpr = gpr;
	}

	public String getGpsj() {
		return gpsj;
	}

	public void setGpsj(String gpsj) {
		this.gpsj = gpsj;
	}

	public String getGpyy() {
		return gpyy;
	}

	public void setGpyy(String gpyy) {
		this.gpyy = gpyy;
	}

	public String getYdw() {
		return ydw;
	}

	public void setYdw(String ydw) {
		this.ydw = ydw;
	}

	public String getYdwszd() {
		return ydwszd;
	}

	public void setYdwszd(String ydwszd) {
		this.ydwszd = ydwszd;
	}

	public String getEcfpdw() {
		return ecfpdw;
	}

	public void setEcfpdw(String ecfpdw) {
		this.ecfpdw = ecfpdw;
	}

	public String getEcfpdwszd() {
		return ecfpdwszd;
	}

	public void setEcfpdwszd(String ecfpdwszd) {
		this.ecfpdwszd = ecfpdwszd;
	}

	public String getEcfpr() {
		return ecfpr;
	}

	public void setEcfpr(String ecfpr) {
		this.ecfpr = ecfpr;
	}

	public String getEcfpsj() {
		return ecfpsj;
	}

	public void setEcfpsj(String ecfpsj) {
		this.ecfpsj = ecfpsj;
	}

	public String getEcfpyy() {
		return ecfpyy;
	}

	public void setEcfpyy(String ecfpyy) {
		this.ecfpyy = ecfpyy;
	}

	public String getQuerylike_xsgb() {
		return querylike_xsgb;
	}

	public void setQuerylike_xsgb(String querylike_xsgb) {
		this.querylike_xsgb = querylike_xsgb;
	}

	public String getQuerylike_lxdk() {
		return querylike_lxdk;
	}

	public void setQuerylike_lxdk(String querylike_lxdk) {
		this.querylike_lxdk = querylike_lxdk;
	}

	public String getQueryequals_hyfldm() {
		return queryequals_hyfldm;
	}

	public void setQueryequals_hyfldm(String queryequals_hyfldm) {
		this.queryequals_hyfldm = queryequals_hyfldm;
	}

	public String getSave_sydq() {
		return save_sydq;
	}

	public void setSave_sydq(String save_sydq) {
		this.save_sydq = save_sydq;
	}

	public String getSave_jtdz() {
		return save_jtdz;
	}

	public void setSave_jtdz(String save_jtdz) {
		this.save_jtdz = save_jtdz;
	}

	public String getSave_jkzk() {
		return save_jkzk;
	}

	public void setSave_jkzk(String save_jkzk) {
		this.save_jkzk = save_jkzk;
	}

	public String getSave_fxzy() {
		return save_fxzy;
	}

	public void setSave_fxzy(String save_fxzy) {
		this.save_fxzy = save_fxzy;
	}

	public String getSave_xl() {
		return save_xl;
	}

	public void setSave_xl(String save_xl) {
		this.save_xl = save_xl;
	}

	public String getSave_xymc() {
		return save_xymc;
	}

	public void setSave_xymc(String save_xymc) {
		this.save_xymc = save_xymc;
	}

	public String getSave_zymc() {
		return save_zymc;
	}

	public void setSave_zymc(String save_zymc) {
		this.save_zymc = save_zymc;
	}

	public String getSave_jsjsp() {
		return save_jsjsp;
	}

	public void setSave_jsjsp(String save_jsjsp) {
		this.save_jsjsp = save_jsjsp;
	}

	public String getSave_wysp() {
		return save_wysp;
	}

	public void setSave_wysp(String save_wysp) {
		this.save_wysp = save_wysp;
	}

	public String getSave_gzdw1() {
		return save_gzdw1;
	}

	public void setSave_gzdw1(String save_gzdw1) {
		this.save_gzdw1 = save_gzdw1;
	}

	public String getSave_gzdw2() {
		return save_gzdw2;
	}

	public void setSave_gzdw2(String save_gzdw2) {
		this.save_gzdw2 = save_gzdw2;
	}

	public String getSave_gzdw3() {
		return save_gzdw3;
	}

	public void setSave_gzdw3(String save_gzdw3) {
		this.save_gzdw3 = save_gzdw3;
	}

	public String getSave_shgzjssj1() {
		return save_shgzjssj1;
	}

	public void setSave_shgzjssj1(String save_shgzjssj1) {
		this.save_shgzjssj1 = save_shgzjssj1;
	}

	public String getSave_shgzjssj2() {
		return save_shgzjssj2;
	}

	public void setSave_shgzjssj2(String save_shgzjssj2) {
		this.save_shgzjssj2 = save_shgzjssj2;
	}

	public String getSave_shgzjssj3() {
		return save_shgzjssj3;
	}

	public void setSave_shgzjssj3(String save_shgzjssj3) {
		this.save_shgzjssj3 = save_shgzjssj3;
	}

	public String getSave_shgzkssj1() {
		return save_shgzkssj1;
	}

	public void setSave_shgzkssj1(String save_shgzkssj1) {
		this.save_shgzkssj1 = save_shgzkssj1;
	}

	public String getSave_shgzkssj2() {
		return save_shgzkssj2;
	}

	public void setSave_shgzkssj2(String save_shgzkssj2) {
		this.save_shgzkssj2 = save_shgzkssj2;
	}

	public String getSave_shgzkssj3() {
		return save_shgzkssj3;
	}

	public void setSave_shgzkssj3(String save_shgzkssj3) {
		this.save_shgzkssj3 = save_shgzkssj3;
	}

	public String getSave_srzw1() {
		return save_srzw1;
	}

	public void setSave_srzw1(String save_srzw1) {
		this.save_srzw1 = save_srzw1;
	}

	public String getSave_srzw2() {
		return save_srzw2;
	}

	public void setSave_srzw2(String save_srzw2) {
		this.save_srzw2 = save_srzw2;
	}

	public String getSave_srzw3() {
		return save_srzw3;
	}

	public void setSave_srzw3(String save_srzw3) {
		this.save_srzw3 = save_srzw3;
	}

	public String getSave_bzjg1() {
		return save_bzjg1;
	}

	public void setSave_bzjg1(String save_bzjg1) {
		this.save_bzjg1 = save_bzjg1;
	}

	public String getSave_bzjg2() {
		return save_bzjg2;
	}

	public void setSave_bzjg2(String save_bzjg2) {
		this.save_bzjg2 = save_bzjg2;
	}

	public String getSave_bzjg3() {
		return save_bzjg3;
	}

	public void setSave_bzjg3(String save_bzjg3) {
		this.save_bzjg3 = save_bzjg3;
	}

	public String getSave_hjsj1() {
		return save_hjsj1;
	}

	public void setSave_hjsj1(String save_hjsj1) {
		this.save_hjsj1 = save_hjsj1;
	}

	public String getSave_hjsj2() {
		return save_hjsj2;
	}

	public void setSave_hjsj2(String save_hjsj2) {
		this.save_hjsj2 = save_hjsj2;
	}

	public String getSave_hjsj3() {
		return save_hjsj3;
	}

	public void setSave_hjsj3(String save_hjsj3) {
		this.save_hjsj3 = save_hjsj3;
	}

	public String getSave_rych1() {
		return save_rych1;
	}

	public void setSave_rych1(String save_rych1) {
		this.save_rych1 = save_rych1;
	}

	public String getSave_rych2() {
		return save_rych2;
	}

	public void setSave_rych2(String save_rych2) {
		this.save_rych2 = save_rych2;
	}

	public String getSave_rych3() {
		return save_rych3;
	}

	public void setSave_rych3(String save_rych3) {
		this.save_rych3 = save_rych3;
	}

	public String getSave_jnzsbzjg1() {
		return save_jnzsbzjg1;
	}

	public void setSave_jnzsbzjg1(String save_jnzsbzjg1) {
		this.save_jnzsbzjg1 = save_jnzsbzjg1;
	}

	public String getSave_jnzsbzjg2() {
		return save_jnzsbzjg2;
	}

	public void setSave_jnzsbzjg2(String save_jnzsbzjg2) {
		this.save_jnzsbzjg2 = save_jnzsbzjg2;
	}

	public String getSave_jnzsbzjg3() {
		return save_jnzsbzjg3;
	}

	public void setSave_jnzsbzjg3(String save_jnzsbzjg3) {
		this.save_jnzsbzjg3 = save_jnzsbzjg3;
	}

	public String getSave_jnzsmc1() {
		return save_jnzsmc1;
	}

	public void setSave_jnzsmc1(String save_jnzsmc1) {
		this.save_jnzsmc1 = save_jnzsmc1;
	}

	public String getSave_jnzsmc2() {
		return save_jnzsmc2;
	}

	public void setSave_jnzsmc2(String save_jnzsmc2) {
		this.save_jnzsmc2 = save_jnzsmc2;
	}

	public String getSave_jnzsmc3() {
		return save_jnzsmc3;
	}

	public void setSave_jnzsmc3(String save_jnzsmc3) {
		this.save_jnzsmc3 = save_jnzsmc3;
	}

	public String getSave_jnzssj1() {
		return save_jnzssj1;
	}

	public void setSave_jnzssj1(String save_jnzssj1) {
		this.save_jnzssj1 = save_jnzssj1;
	}

	public String getSave_jnzssj2() {
		return save_jnzssj2;
	}

	public void setSave_jnzssj2(String save_jnzssj2) {
		this.save_jnzssj2 = save_jnzssj2;
	}

	public String getSave_jnzssj3() {
		return save_jnzssj3;
	}

	public void setSave_jnzssj3(String save_jnzssj3) {
		this.save_jnzssj3 = save_jnzssj3;
	}

	public String getSave_shsjcx1() {
		return save_shsjcx1;
	}

	public void setSave_shsjcx1(String save_shsjcx1) {
		this.save_shsjcx1 = save_shsjcx1;
	}

	public String getSave_shsjcx2() {
		return save_shsjcx2;
	}

	public void setSave_shsjcx2(String save_shsjcx2) {
		this.save_shsjcx2 = save_shsjcx2;
	}

	public String getSave_shsjcx3() {
		return save_shsjcx3;
	}

	public void setSave_shsjcx3(String save_shsjcx3) {
		this.save_shsjcx3 = save_shsjcx3;
	}

	public String getSave_shsjdw1() {
		return save_shsjdw1;
	}

	public void setSave_shsjdw1(String save_shsjdw1) {
		this.save_shsjdw1 = save_shsjdw1;
	}

	public String getSave_shsjdw2() {
		return save_shsjdw2;
	}

	public void setSave_shsjdw2(String save_shsjdw2) {
		this.save_shsjdw2 = save_shsjdw2;
	}

	public String getSave_shsjdw3() {
		return save_shsjdw3;
	}

	public void setSave_shsjdw3(String save_shsjdw3) {
		this.save_shsjdw3 = save_shsjdw3;
	}

	public String getSave_shsjjssj1() {
		return save_shsjjssj1;
	}

	public void setSave_shsjjssj1(String save_shsjjssj1) {
		this.save_shsjjssj1 = save_shsjjssj1;
	}

	public String getSave_shsjjssj2() {
		return save_shsjjssj2;
	}

	public void setSave_shsjjssj2(String save_shsjjssj2) {
		this.save_shsjjssj2 = save_shsjjssj2;
	}

	public String getSave_shsjjssj3() {
		return save_shsjjssj3;
	}

	public void setSave_shsjjssj3(String save_shsjjssj3) {
		this.save_shsjjssj3 = save_shsjjssj3;
	}

	public String getSave_shsjkssj1() {
		return save_shsjkssj1;
	}

	public void setSave_shsjkssj1(String save_shsjkssj1) {
		this.save_shsjkssj1 = save_shsjkssj1;
	}

	public String getSave_shsjkssj2() {
		return save_shsjkssj2;
	}

	public void setSave_shsjkssj2(String save_shsjkssj2) {
		this.save_shsjkssj2 = save_shsjkssj2;
	}

	public String getSave_shsjkssj3() {
		return save_shsjkssj3;
	}

	public void setSave_shsjkssj3(String save_shsjkssj3) {
		this.save_shsjkssj3 = save_shsjkssj3;
	}

	public String getSave_zwtj() {
		return save_zwtj;
	}

	public void setSave_zwtj(String save_zwtj) {
		this.save_zwtj = save_zwtj;
	}

	public String getQuerygreaterequal_sbsj() {
		return querygreaterequal_sbsj;
	}

	public void setQuerygreaterequal_sbsj(String querygreaterequal_sbsj) {
		this.querygreaterequal_sbsj = querygreaterequal_sbsj;
	}

	public String getQuerylessequal_sbsj() {
		return querylessequal_sbsj;
	}

	public void setQuerylessequal_sbsj(String querylessequal_sbsj) {
		this.querylessequal_sbsj = querylessequal_sbsj;
	}

	public String getSave_fclx() {
		return save_fclx;
	}

	public void setSave_fclx(String save_fclx) {
		this.save_fclx = save_fclx;
	}

	public String getSave_fcsj1() {
		return save_fcsj1;
	}

	public void setSave_fcsj1(String save_fcsj1) {
		this.save_fcsj1 = save_fcsj1;
	}

	public String getSave_fcsj2() {
		return save_fcsj2;
	}

	public void setSave_fcsj2(String save_fcsj2) {
		this.save_fcsj2 = save_fcsj2;
	}

	public String getSave_fcsj3() {
		return save_fcsj3;
	}

	public void setSave_fcsj3(String save_fcsj3) {
		this.save_fcsj3 = save_fcsj3;
	}

	public String getSave_fcsj4() {
		return save_fcsj4;
	}

	public void setSave_fcsj4(String save_fcsj4) {
		this.save_fcsj4 = save_fcsj4;
	}

	public String getSave_fcsj5() {
		return save_fcsj5;
	}

	public void setSave_fcsj5(String save_fcsj5) {
		this.save_fcsj5 = save_fcsj5;
	}

	public String getSave_hjqk1() {
		return save_hjqk1;
	}

	public void setSave_hjqk1(String save_hjqk1) {
		this.save_hjqk1 = save_hjqk1;
	}

	public String getSave_hjqk2() {
		return save_hjqk2;
	}

	public void setSave_hjqk2(String save_hjqk2) {
		this.save_hjqk2 = save_hjqk2;
	}

	public String getSave_hjqk3() {
		return save_hjqk3;
	}

	public void setSave_hjqk3(String save_hjqk3) {
		this.save_hjqk3 = save_hjqk3;
	}

	public String getSave_hjqk4() {
		return save_hjqk4;
	}

	public void setSave_hjqk4(String save_hjqk4) {
		this.save_hjqk4 = save_hjqk4;
	}

	public String getSave_hjqk5() {
		return save_hjqk5;
	}

	public void setSave_hjqk5(String save_hjqk5) {
		this.save_hjqk5 = save_hjqk5;
	}

	public String getSave_shgz1() {
		return save_shgz1;
	}

	public void setSave_shgz1(String save_shgz1) {
		this.save_shgz1 = save_shgz1;
	}

	public String getSave_shgz2() {
		return save_shgz2;
	}

	public void setSave_shgz2(String save_shgz2) {
		this.save_shgz2 = save_shgz2;
	}

	public String getSave_shgz3() {
		return save_shgz3;
	}

	public void setSave_shgz3(String save_shgz3) {
		this.save_shgz3 = save_shgz3;
	}

	public String getSave_shgz4() {
		return save_shgz4;
	}

	public void setSave_shgz4(String save_shgz4) {
		this.save_shgz4 = save_shgz4;
	}

	public String getSave_shgz5() {
		return save_shgz5;
	}

	public void setSave_shgz5(String save_shgz5) {
		this.save_shgz5 = save_shgz5;
	}

	public String getSave_fcgy() {
		return save_fcgy;
	}

	public void setSave_fcgy(String save_fcgy) {
		this.save_fcgy = save_fcgy;
	}

	public String getQueryequals_fclx() {
		return queryequals_fclx;
	}

	public void setQueryequals_fclx(String queryequals_fclx) {
		this.queryequals_fclx = queryequals_fclx;
	}

	public String getSave_pjcj1() {
		return save_pjcj1;
	}

	public void setSave_pjcj1(String save_pjcj1) {
		this.save_pjcj1 = save_pjcj1;
	}

	public String getSave_pjcj2() {
		return save_pjcj2;
	}

	public void setSave_pjcj2(String save_pjcj2) {
		this.save_pjcj2 = save_pjcj2;
	}

	public String getSave_bysj() {
		return save_bysj;
	}

	public void setSave_bysj(String save_bysj) {
		this.save_bysj = save_bysj;
	}

	public String getSave_npjx() {
		return save_npjx;
	}

	public void setSave_npjx(String save_npjx) {
		this.save_npjx = save_npjx;
	}

	public String getSave_hjqk() {
		return save_hjqk;
	}

	public void setSave_hjqk(String save_hjqk) {
		this.save_hjqk = save_hjqk;
	}

	public String getSave_zcf() {
		return save_zcf;
	}

	public void setSave_zcf(String save_zcf) {
		this.save_zcf = save_zcf;
	}

	public String getSave_zcpm() {
		return save_zcpm;
	}

	public void setSave_zcpm(String save_zcpm) {
		this.save_zcpm = save_zcpm;
	}

	public String getSave_sqlb() {
		return save_sqlb;
	}

	public void setSave_sqlb(String save_sqlb) {
		this.save_sqlb = save_sqlb;
	}

	public String getQueryequals_sqlb() {
		return queryequals_sqlb;
	}

	public void setQueryequals_sqlb(String queryequals_sqlb) {
		this.queryequals_sqlb = queryequals_sqlb;
	}

	public String getSqlb() {
		return sqlb;
	}

	public void setSqlb(String sqlb) {
		this.sqlb = sqlb;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQuerygreaterequal_fbsj() {
		return querygreaterequal_fbsj;
	}

	public void setQuerygreaterequal_fbsj(String querygreaterequal_fbsj) {
		this.querygreaterequal_fbsj = querygreaterequal_fbsj;
	}

	public String getQuerylessequal_fbsj() {
		return querylessequal_fbsj;
	}

	public void setQuerylessequal_fbsj(String querylessequal_fbsj) {
		this.querylessequal_fbsj = querylessequal_fbsj;
	}

	public String getQuerylike_wjbt() {
		return querylike_wjbt;
	}

	public void setQuerylike_wjbt(String querylike_wjbt) {
		this.querylike_wjbt = querylike_wjbt;
	}

	public String getFbr() {
		return fbr;
	}

	public void setFbr(String fbr) {
		this.fbr = fbr;
	}

	public String getFbsj() {
		return fbsj;
	}

	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}

	public String getWjbt() {
		return wjbt;
	}

	public void setWjbt(String wjbt) {
		this.wjbt = wjbt;
	}

	public String getWjnr() {
		return wjnr;
	}

	public void setWjnr(String wjnr) {
		this.wjnr = wjnr;
	}

	public String getWjlx() {
		return wjlx;
	}

	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}

	public String getQueryequals_wjlx() {
		return queryequals_wjlx;
	}

	public void setQueryequals_wjlx(String queryequals_wjlx) {
		this.queryequals_wjlx = queryequals_wjlx;
	}

	public String getQueryequals_hyfl() {
		return queryequals_hyfl;
	}

	public void setQueryequals_hyfl(String queryequals_hyfl) {
		this.queryequals_hyfl = queryequals_hyfl;
	}

	public String getQuerygreaterequal_zcsj() {
		return querygreaterequal_zcsj;
	}

	public void setQuerygreaterequal_zcsj(String querygreaterequal_zcsj) {
		this.querygreaterequal_zcsj = querygreaterequal_zcsj;
	}

	public String getQuerylessequal_zcsj() {
		return querylessequal_zcsj;
	}

	public void setQuerylessequal_zcsj(String querylessequal_zcsj) {
		this.querylessequal_zcsj = querylessequal_zcsj;
	}

	public String getQuerylike_dwmc() {
		return querylike_dwmc;
	}

	public void setQuerylike_dwmc(String querylike_dwmc) {
		this.querylike_dwmc = querylike_dwmc;
	}

	public String getSave_yhm() {
		return save_yhm;
	}

	public void setSave_yhm(String save_yhm) {
		this.save_yhm = save_yhm;
	}

	public String getSave_cz() {
		return save_cz;
	}

	public void setSave_cz(String save_cz) {
		this.save_cz = save_cz;
	}

	public String getSave_dwjj() {
		return save_dwjj;
	}

	public void setSave_dwjj(String save_dwjj) {
		this.save_dwjj = save_dwjj;
	}

	public String getSave_dz() {
		return save_dz;
	}

	public void setSave_dz(String save_dz) {
		this.save_dz = save_dz;
	}

	public String getSave_email() {
		return save_email;
	}

	public void setSave_email(String save_email) {
		this.save_email = save_email;
	}

	public String getSave_hyfl() {
		return save_hyfl;
	}

	public void setSave_hyfl(String save_hyfl) {
		this.save_hyfl = save_hyfl;
	}

	public String getSave_jgdmzh() {
		return save_jgdmzh;
	}

	public void setSave_jgdmzh(String save_jgdmzh) {
		this.save_jgdmzh = save_jgdmzh;
	}

	public String getSave_lxr() {
		return save_lxr;
	}

	public void setSave_lxr(String save_lxr) {
		this.save_lxr = save_lxr;
	}

	public String getSave_mm() {
		return save_mm;
	}

	public void setSave_mm(String save_mm) {
		this.save_mm = save_mm;
	}

	public String getSave_qyfr() {
		return save_qyfr;
	}

	public void setSave_qyfr(String save_qyfr) {
		this.save_qyfr = save_qyfr;
	}

	public String getSave_wz() {
		return save_wz;
	}

	public void setSave_wz(String save_wz) {
		this.save_wz = save_wz;
	}

	public String getSave_yb() {
		return save_yb;
	}

	public void setSave_yb(String save_yb) {
		this.save_yb = save_yb;
	}

	public String getSave_gzdd() {
		return save_gzdd;
	}

	public void setSave_gzdd(String save_gzdd) {
		this.save_gzdd = save_gzdd;
	}

	public String getSave_gzxz() {
		return save_gzxz;
	}

	public void setSave_gzxz(String save_gzxz) {
		this.save_gzxz = save_gzxz;
	}

	public String getSave_mbzn() {
		return save_mbzn;
	}

	public void setSave_mbzn(String save_mbzn) {
		this.save_mbzn = save_mbzn;
	}

	public String getSave_sfzhbm() {
		return save_sfzhbm;
	}

	public void setSave_sfzhbm(String save_sfzhbm) {
		this.save_sfzhbm = save_sfzhbm;
	}

	public String getSave_zdyrdw() {
		return save_zdyrdw;
	}

	public void setSave_zdyrdw(String save_zdyrdw) {
		this.save_zdyrdw = save_zdyrdw;
	}

	public String getSave_qwxs() {
		return save_qwxs;
	}

	public void setSave_qwxs(String save_qwxs) {
		this.save_qwxs = save_qwxs;
	}

	public String getQuerylike_sfzh() {
		return querylike_sfzh;
	}

	public void setQuerylike_sfzh(String querylike_sfzh) {
		this.querylike_sfzh = querylike_sfzh;
	}

	public String getLjlx() {
		return ljlx;
	}

	public void setLjlx(String ljlx) {
		this.ljlx = ljlx;
	}

	public String getLjmc() {
		return ljmc;
	}

	public void setLjmc(String ljmc) {
		this.ljmc = ljmc;
	}

	public String getLjwz() {
		return ljwz;
	}

	public void setLjwz(String ljwz) {
		this.ljwz = ljwz;
	}


	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	public FormFile getTpdz() {
		return tpdz;
	}

	public void setTpdz(FormFile tpdz) {
		this.tpdz = tpdz;
	}

	public String getQueryequals_xssx() {
		return queryequals_xssx;
	}

	public void setQueryequals_xssx(String queryequals_xssx) {
		this.queryequals_xssx = queryequals_xssx;
	}

	public String getQuerylike_ljmc() {
		return querylike_ljmc;
	}

	public void setQuerylike_ljmc(String querylike_ljmc) {
		this.querylike_ljmc = querylike_ljmc;
	}

	public String getQuerylike_ljwz() {
		return querylike_ljwz;
	}

	public void setQuerylike_ljwz(String querylike_ljwz) {
		this.querylike_ljwz = querylike_ljwz;
	}

	public String getLlcs() {
		return llcs;
	}

	public void setLlcs(String llcs) {
		this.llcs = llcs;
	}

	public String getScr() {
		return scr;
	}

	public void setScr(String scr) {
		this.scr = scr;
	}

	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
	}

	public String getWjlj() {
		return wjlj;
	}

	public void setWjlj(String wjlj) {
		this.wjlj = wjlj;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}

	public String getWjsm() {
		return wjsm;
	}

	public void setWjsm(String wjsm) {
		this.wjsm = wjsm;
	}

	public String getXzcs() {
		return xzcs;
	}

	public void setXzcs(String xzcs) {
		this.xzcs = xzcs;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getQuerygreaterequal_scsj() {
		return querygreaterequal_scsj;
	}

	public void setQuerygreaterequal_scsj(String querygreaterequal_scsj) {
		this.querygreaterequal_scsj = querygreaterequal_scsj;
	}

	public String getQuerylessequal_scsj() {
		return querylessequal_scsj;
	}

	public void setQuerylessequal_scsj(String querylessequal_scsj) {
		this.querylessequal_scsj = querylessequal_scsj;
	}

	public String getQuerylike_wjm() {
		return querylike_wjm;
	}

	public void setQuerylike_wjm(String querylike_wjm) {
		this.querylike_wjm = querylike_wjm;
	}

	public String getSave_czhm() {
		return save_czhm;
	}

	public void setSave_czhm(String save_czhm) {
		this.save_czhm = save_czhm;
	}

	public String getSave_fbr() {
		return save_fbr;
	}

	public void setSave_fbr(String save_fbr) {
		this.save_fbr = save_fbr;
	}

	public String getSave_zphbt() {
		return save_zphbt;
	}

	public void setSave_zphbt(String save_zphbt) {
		this.save_zphbt = save_zphbt;
	}

	public String getSave_zphnr() {
		return save_zphnr;
	}

	public void setSave_zphnr(String save_zphnr) {
		this.save_zphnr = save_zphnr;
	}

	public String getQuerylike_zphbt() {
		return querylike_zphbt;
	}

	public void setQuerylike_zphbt(String querylike_zphbt) {
		this.querylike_zphbt = querylike_zphbt;
	}

	public String getSave_wjbt() {
		return save_wjbt;
	}

	public void setSave_wjbt(String save_wjbt) {
		this.save_wjbt = save_wjbt;
	}

	public String getSave_wjlx() {
		return save_wjlx;
	}

	public void setSave_wjlx(String save_wjlx) {
		this.save_wjlx = save_wjlx;
	}

	public String getSave_wjnr() {
		return save_wjnr;
	}

	public void setSave_wjnr(String save_wjnr) {
		this.save_wjnr = save_wjnr;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getQyfr() {
		return qyfr;
	}

	public void setQyfr(String qyfr) {
		this.qyfr = qyfr;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public String getDa1() {
		return da1;
	}

	public void setDa1(String da1) {
		this.da1 = da1;
	}

	public String getDa2() {
		return da2;
	}

	public void setDa2(String da2) {
		this.da2 = da2;
	}

	public String getTswt1() {
		return tswt1;
	}

	public void setTswt1(String tswt1) {
		this.tswt1 = tswt1;
	}

	public String getTswt2() {
		return tswt2;
	}

	public String getSave_hfxx() {
		return save_hfxx;
	}

	public void setSave_hfxx(String saveHfxx) {
		save_hfxx = saveHfxx;
	}

	public void setTswt2(String tswt2) {
		this.tswt2 = tswt2;
	}	
	public String getSave_mbgw() {
		return save_mbgw;
	}

	public void setSave_mbgw(String save_mbgw) {
		this.save_mbgw = save_mbgw;
	}

	public String getQueryequals_gzxz() {
		return queryequals_gzxz;
	}

	public void setQueryequals_gzxz(String queryequals_gzxz) {
		this.queryequals_gzxz = queryequals_gzxz;
	}

	public String getQuerylike_gzdd() {
		return querylike_gzdd;
	}

	public void setQuerylike_gzdd(String querylike_gzdd) {
		this.querylike_gzdd = querylike_gzdd;
	}

	public String getQuerylike_mbgw() {
		return querylike_mbgw;
	}

	public void setQuerylike_mbgw(String querylike_mbgw) {
		this.querylike_mbgw = querylike_mbgw;
	}

	public String getMm2() {
		return mm2;
	}

	public void setMm2(String mm2) {
		this.mm2 = mm2;
	}

	public String getDwlx() {
		return dwlx;
	}

	public void setDwlx(String dwlx) {
		this.dwlx = dwlx;
	}
	
	public String getSave_gwmc() {
		return save_gwmc;
	}

	public void setSave_gwmc(String save_gwmc) {
		this.save_gwmc = save_gwmc;
	}

	public String getSave_joblb() {
		return save_joblb;
	}

	public void setSave_joblb(String save_joblb) {
		this.save_joblb = save_joblb;
	}

	public String getQuerylike_gwmc() {
		return querylike_gwmc;
	}

	public void setQuerylike_gwmc(String querylike_gwmc) {
		this.querylike_gwmc = querylike_gwmc;
	}

	public String getDwxz() {
		return dwxz;
	}

	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}

	public String getHyfl() {
		return hyfl;
	}

	public String getOldmm() {
		return oldmm;
	}

	public void setOldmm(String oldmm) {
		this.oldmm = oldmm;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}

	public String getQuerygreaterequal_tdrq() {
		return querygreaterequal_tdrq;
	}

	public void setQuerygreaterequal_tdrq(String querygreaterequal_tdrq) {
		this.querygreaterequal_tdrq = querygreaterequal_tdrq;
	}

	public String getQuerylessequal_tdrq() {
		return querylessequal_tdrq;
	}

	public void setQuerylessequal_tdrq(String querylessequal_tdrq) {
		this.querylessequal_tdrq = querylessequal_tdrq;
	}

	public String getTjfs() {
		return tjfs;
	}

	public void setTjfs(String tjfs) {
		this.tjfs = tjfs;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getTplj() {
		return tplj;
	}

	public void setTplj(String tplj) {
		this.tplj = tplj;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSave_dwdh() {
		return save_dwdh;
	}

	public void setSave_dwdh(String save_dwdh) {
		this.save_dwdh = save_dwdh;
	}

	public String getSave_dwxzdm() {
		return save_dwxzdm;
	}

	public void setSave_dwxzdm(String save_dwxzdm) {
		this.save_dwxzdm = save_dwxzdm;
	}

	public String getSave_dwyb() {
		return save_dwyb;
	}

	public void setSave_dwyb(String save_dwyb) {
		this.save_dwyb = save_dwyb;
	}

	public String getSave_hyfldm() {
		return save_hyfldm;
	}

	public String getZpzw() {
		return zpzw;
	}

	public void setZpzw(String zpzw) {
		this.zpzw = zpzw;
	}
	
	public void setSave_hyfldm(String save_hyfldm) {
		this.save_hyfldm = save_hyfldm;
	}

	public String getZgdw() {
		return zgdw;
	}

	public void setZgdw(String zgdw) {
		this.zgdw = zgdw;
	}

	public String getQueryequals_dwxzdm() {
		return queryequals_dwxzdm;
	}

	public void setQueryequals_dwxzdm(String queryequals_dwxzdm) {
		this.queryequals_dwxzdm = queryequals_dwxzdm;
	}

	public String getTjxmdm() {
		return tjxmdm;
	}

	public void setTjxmdm(String tjxmdm) {
		this.tjxmdm = tjxmdm;
	}

	public String getSave_isjys() {
		return save_isjys;
	}

	public void setSave_isjys(String save_isjys) {
		this.save_isjys = save_isjys;
	}

	public String getQueryequals_isjys() {
		return queryequals_isjys;
	}

	public void setQueryequals_isjys(String queryequals_isjys) {
		this.queryequals_isjys = queryequals_isjys;
	}

	public String getSave_dtshqk() {
		return save_dtshqk;
	}

	public void setSave_dtshqk(String save_dtshqk) {
		this.save_dtshqk = save_dtshqk;
	}

	public String getXmbz() {
		return xmbz;
	}

	public void setXmbz(String xmbz) {
		this.xmbz = xmbz;
	}

	public String getSave_bmdm() {
		return save_bmdm;
	}

	public void setSave_bmdm(String save_bmdm) {
		this.save_bmdm = save_bmdm;
	}

	public String getSave_qtr() {
		return save_qtr;
	}

	public void setSave_qtr(String save_qtr) {
		this.save_qtr = save_qtr;
	}

	public String getSave_qyqkjl() {
		return save_qyqkjl;
	}

	public void setSave_qyqkjl(String save_qyqkjl) {
		this.save_qyqkjl = save_qyqkjl;
	}

	public String getSave_qyrs() {
		return save_qyrs;
	}

	public void setSave_qyrs(String save_qyrs) {
		this.save_qyrs = save_qyrs;
	}

	public String getSave_qysj() {
		return save_qysj;
	}

	public void setSave_qysj(String save_qysj) {
		this.save_qysj = save_qysj;
	}

	public String getSave_sfba() {
		return save_sfba;
	}

	public void setSave_sfba(String save_sfba) {
		this.save_sfba = save_sfba;
	}

	public String getSave_jtmc() {
		return save_jtmc;
	}

	public void setSave_jtmc(String save_jtmc) {
		this.save_jtmc = save_jtmc;
	}

	public String getQueryequals_sfba() {
		return queryequals_sfba;
	}

	public void setQueryequals_sfba(String queryequals_sfba) {
		this.queryequals_sfba = queryequals_sfba;
	}

	public String getQuerygreaterequal_qyrs() {
		return querygreaterequal_qyrs;
	}

	public void setQuerygreaterequal_qyrs(String querygreaterequal_qyrs) {
		this.querygreaterequal_qyrs = querygreaterequal_qyrs;
	}

	public String getQuerygreaterequal_qysj() {
		return querygreaterequal_qysj;
	}

	public void setQuerygreaterequal_qysj(String querygreaterequal_qysj) {
		this.querygreaterequal_qysj = querygreaterequal_qysj;
	}

	public String getQuerylessequal_qyrs() {
		return querylessequal_qyrs;
	}

	public void setQuerylessequal_qyrs(String querylessequal_qyrs) {
		this.querylessequal_qyrs = querylessequal_qyrs;
	}

	public String getQuerylessequal_qysj() {
		return querylessequal_qysj;
	}

	public void setQuerylessequal_qysj(String querylessequal_qysj) {
		this.querylessequal_qysj = querylessequal_qysj;
	}

	public String getQuerylike_jtmc() {
		return querylike_jtmc;
	}

	public void setQuerylike_jtmc(String querylike_jtmc) {
		this.querylike_jtmc = querylike_jtmc;
	}

	public String getQuerylike_qtr() {
		return querylike_qtr;
	}

	public void setQuerylike_qtr(String querylike_qtr) {
		this.querylike_qtr = querylike_qtr;
	}

	public String getQueryequals_bmdm() {
		return queryequals_bmdm;
	}

	public void setQueryequals_bmdm(String queryequals_bmdm) {
		this.queryequals_bmdm = queryequals_bmdm;
	}

	public String getSave_chdw() {
		return save_chdw;
	}

	public void setSave_chdw(String save_chdw) {
		this.save_chdw = save_chdw;
	}

	public String getSave_cjxss() {
		return save_cjxss;
	}

	public void setSave_cjxss(String save_cjxss) {
		this.save_cjxss = save_cjxss;
	}

	public String getSave_dccbyxs() {
		return save_dccbyxs;
	}

	public void setSave_dccbyxs(String save_dccbyxs) {
		this.save_dccbyxs = save_dccbyxs;
	}

	public String getSave_dd() {
		return save_dd;
	}

	public void setSave_dd(String save_dd) {
		this.save_dd = save_dd;
	}

	public String getSave_lhcbf() {
		return save_lhcbf;
	}

	public void setSave_lhcbf(String save_lhcbf) {
		this.save_lhcbf = save_lhcbf;
	}

	public String getSave_qyfldm() {
		return save_qyfldm;
	}

	public void setSave_qyfldm(String save_qyfldm) {
		this.save_qyfldm = save_qyfldm;
	}

	public String getSave_sj() {
		return save_sj;
	}

	public void setSave_sj(String save_sj) {
		this.save_sj = save_sj;
	}

	public String getSave_xqgw() {
		return save_xqgw;
	}

	public void setSave_xqgw(String save_xqgw) {
		this.save_xqgw = save_xqgw;
	}

	public String getSave_xqsl() {
		return save_xqsl;
	}

	public void setSave_xqsl(String save_xqsl) {
		this.save_xqsl = save_xqsl;
	}

	public String getSave_zphlxdm() {
		return save_zphlxdm;
	}

	public void setSave_zphlxdm(String save_zphlxdm) {
		this.save_zphlxdm = save_zphlxdm;
	}

	public String getQueryequals_qyfldm() {
		return queryequals_qyfldm;
	}

	public void setQueryequals_qyfldm(String queryequals_qyfldm) {
		this.queryequals_qyfldm = queryequals_qyfldm;
	}

	public String getQueryequals_zphlxdm() {
		return queryequals_zphlxdm;
	}

	public void setQueryequals_zphlxdm(String queryequals_zphlxdm) {
		this.queryequals_zphlxdm = queryequals_zphlxdm;
	}

	public String getQuerylike_chdw() {
		return querylike_chdw;
	}

	public void setQuerylike_chdw(String querylike_chdw) {
		this.querylike_chdw = querylike_chdw;
	}

	public String getQuerylike_xqgw() {
		return querylike_xqgw;
	}

	public void setQuerylike_xqgw(String querylike_xqgw) {
		this.querylike_xqgw = querylike_xqgw;
	}

	public String getQueryequals_hylxdm() {
		return queryequals_hylxdm;
	}

	public void setQueryequals_hylxdm(String queryequals_hylxdm) {
		this.queryequals_hylxdm = queryequals_hylxdm;
	}

	public String getQuerylike_hyzt() {
		return querylike_hyzt;
	}

	public void setQuerylike_hyzt(String querylike_hyzt) {
		this.querylike_hyzt = querylike_hyzt;
	}

	public String getQuerylike_zcr() {
		return querylike_zcr;
	}

	public void setQuerylike_zcr(String querylike_zcr) {
		this.querylike_zcr = querylike_zcr;
	}

	public String getSave_chrr() {
		return save_chrr;
	}

	public void setSave_chrr(String save_chrr) {
		this.save_chrr = save_chrr;
	}

	public String getSave_hylxdm() {
		return save_hylxdm;
	}

	public void setSave_hylxdm(String save_hylxdm) {
		this.save_hylxdm = save_hylxdm;
	}

	public String getSave_hyzt() {
		return save_hyzt;
	}

	public void setSave_hyzt(String save_hyzt) {
		this.save_hyzt = save_hyzt;
	}

	public String getSave_zcr() {
		return save_zcr;
	}

	public void setSave_zcr(String save_zcr) {
		this.save_zcr = save_zcr;
	}

	public String getSave_zyjy() {
		return save_zyjy;
	}

	public void setSave_zyjy(String save_zyjy) {
		this.save_zyjy = save_zyjy;
	}

	public String getQuerylike_jzzd() {
		return querylike_jzzd;
	}

	public void setQuerylike_jzzd(String querylike_jzzd) {
		this.querylike_jzzd = querylike_jzzd;
	}

	public String getQuerylike_mxdx() {
		return querylike_mxdx;
	}

	public void setQuerylike_mxdx(String querylike_mxdx) {
		this.querylike_mxdx = querylike_mxdx;
	}

	public String getSave_cjrs() {
		return save_cjrs;
	}

	public void setSave_cjrs(String save_cjrs) {
		this.save_cjrs = save_cjrs;
	}

	public String getSave_jzzd() {
		return save_jzzd;
	}

	public void setSave_jzzd(String save_jzzd) {
		this.save_jzzd = save_jzzd;
	}

	public String getSave_mxdx() {
		return save_mxdx;
	}

	public void setSave_mxdx(String save_mxdx) {
		this.save_mxdx = save_mxdx;
	}

	public String getSave_xg() {
		return save_xg;
	}

	public void setSave_xg(String save_xg) {
		this.save_xg = save_xg;
	}

	public String getSave_zcrjs() {
		return save_zcrjs;
	}

	public void setSave_zcrjs(String save_zcrjs) {
		this.save_zcrjs = save_zcrjs;
	}

	public String getQueryequals_jsfldm() {
		return queryequals_jsfldm;
	}

	public void setQueryequals_jsfldm(String queryequals_jsfldm) {
		this.queryequals_jsfldm = queryequals_jsfldm;
	}

	public String getQueryequals_tjjgdm() {
		return queryequals_tjjgdm;
	}

	public void setQueryequals_tjjgdm(String queryequals_tjjgdm) {
		this.queryequals_tjjgdm = queryequals_tjjgdm;
	}

	public String getQuerylike_btjxs() {
		return querylike_btjxs;
	}

	public void setQuerylike_btjxs(String querylike_btjxs) {
		this.querylike_btjxs = querylike_btjxs;
	}

	public String getQuerylike_tjdwmc() {
		return querylike_tjdwmc;
	}

	public void setQuerylike_tjdwmc(String querylike_tjdwmc) {
		this.querylike_tjdwmc = querylike_tjdwmc;
	}

	public String getSave_btjxs() {
		return save_btjxs;
	}

	public void setSave_btjxs(String save_btjxs) {
		this.save_btjxs = save_btjxs;
	}

	public String getSave_cyjs() {
		return save_cyjs;
	}

	public void setSave_cyjs(String save_cyjs) {
		this.save_cyjs = save_cyjs;
	}

	public String getSave_jsfldm() {
		return save_jsfldm;
	}

	public void setSave_jsfldm(String save_jsfldm) {
		this.save_jsfldm = save_jsfldm;
	}

	public String getSave_lrsj() {
		return save_lrsj;
	}

	public void setSave_lrsj(String save_lrsj) {
		this.save_lrsj = save_lrsj;
	}

	public String getSave_tjdwmc() {
		return save_tjdwmc;
	}

	public void setSave_tjdwmc(String save_tjdwmc) {
		this.save_tjdwmc = save_tjdwmc;
	}

	public String getSave_tjjgdm() {
		return save_tjjgdm;
	}

	public void setSave_tjjgdm(String save_tjjgdm) {
		this.save_tjjgdm = save_tjjgdm;
	}

	public String getSave_dwdz() {
		return save_dwdz;
	}

	public void setSave_dwdz(String save_dwdz) {
		this.save_dwdz = save_dwdz;
	}

	public String getSave_xqzy() {
		return save_xqzy;
	}

	public void setSave_xqzy(String save_xqzy) {
		this.save_xqzy = save_xqzy;
	}

	public String getQuerylike_xqzy() {
		return querylike_xqzy;
	}

	public void setQuerylike_xqzy(String querylike_xqzy) {
		this.querylike_xqzy = querylike_xqzy;
	}

	public String getSave_jyxybh() {
		return save_jyxybh;
	}

	public void setSave_jyxybh(String save_jyxybh) {
		this.save_jyxybh = save_jyxybh;
	}

	public String getSave_yjyxybh() {
		return save_yjyxybh;
	}

	public void setSave_yjyxybh(String save_yjyxybh) {
		this.save_yjyxybh = save_yjyxybh;
	}

	public String getSave_bblbdm() {
		return save_bblbdm;
	}

	public void setSave_bblbdm(String save_bblbdm) {
		this.save_bblbdm = save_bblbdm;
	}

	public String getSave_sqly() {
		return save_sqly;
	}

	public void setSave_sqly(String save_sqly) {
		this.save_sqly = save_sqly;
	}

	public String getSave_byssbkg() {
		return save_byssbkg;
	}

	public void setSave_byssbkg(String save_byssbkg) {
		this.save_byssbkg = save_byssbkg;
	}

	public String getSave_lcbh() {
		return save_lcbh;
	}

	public void setSave_lcbh(String save_lcbh) {
		this.save_lcbh = save_lcbh;
	}

	public String getSave_xysbbshjb() {
		return save_xysbbshjb;
	}

	public void setSave_xysbbshjb(String save_xysbbshjb) {
		this.save_xysbbshjb = save_xysbbshjb;
	}

	public String getSave_xysbbshr() {
		return save_xysbbshr;
	}

	public void setSave_xysbbshr(String save_xysbbshr) {
		this.save_xysbbshr = save_xysbbshr;
	}

	public String getSave_newjyxybh() {
		return save_newjyxybh;
	}

	public void setSave_newjyxybh(String save_newjyxybh) {
		this.save_newjyxybh = save_newjyxybh;
	}

	public String getQueryequals_bblbdm() {
		return queryequals_bblbdm;
	}

	public void setQueryequals_bblbdm(String queryequals_bblbdm) {
		this.queryequals_bblbdm = queryequals_bblbdm;
	}

	public String getQuerylike_jyxybh() {
		return querylike_jyxybh;
	}

	public void setQuerylike_jyxybh(String querylike_jyxybh) {
		this.querylike_jyxybh = querylike_jyxybh;
	}

	public String getQuerylike_newjyxybh() {
		return querylike_newjyxybh;
	}

	public void setQuerylike_newjyxybh(String querylike_newjyxybh) {
		this.querylike_newjyxybh = querylike_newjyxybh;
	}

	public String getTempDwmc() {
		return tempDwmc;
	}

	public void setTempDwmc(String tempDwmc) {
		this.tempDwmc = tempDwmc;
	}

	public String getTempGwValue() {
		return tempGwValue;
	}

	public void setTempGwValue(String tempGwValue) {
		this.tempGwValue = tempGwValue;
	}

	public String getQuerylike_zpzt() {
		return querylike_zpzt;
	}

	public void setQuerylike_zpzt(String querylike_zpzt) {
		this.querylike_zpzt = querylike_zpzt;
	}

	public String getQuerygreaterequal_zpsj() {
		return querygreaterequal_zpsj;
	}

	public void setQuerygreaterequal_zpsj(String querygreaterequal_zpsj) {
		this.querygreaterequal_zpsj = querygreaterequal_zpsj;
	}

	public String getQuerylessequal_zpsj() {
		return querylessequal_zpsj;
	}

	public void setQuerylessequal_zpsj(String querylessequal_zpsj) {
		this.querylessequal_zpsj = querylessequal_zpsj;
	}

	public String getQuerylike_fbr() {
		return querylike_fbr;
	}

	public void setQuerylike_fbr(String querylike_fbr) {
		this.querylike_fbr = querylike_fbr;
	}

	public String getQuerylike_zpdd() {
		return querylike_zpdd;
	}

	public void setQuerylike_zpdd(String querylike_zpdd) {
		this.querylike_zpdd = querylike_zpdd;
	}

	public String getSave_isgp() {
		return save_isgp;
	}

	public void setSave_isgp(String save_isgp) {
		this.save_isgp = save_isgp;
	}

	public String getSave_kzx1() {
		return save_kzx1;
	}

	public void setSave_kzx1(String save_kzx1) {
		this.save_kzx1 = save_kzx1;
	}

	public String getSave_kzx2() {
		return save_kzx2;
	}

	public void setSave_kzx2(String save_kzx2) {
		this.save_kzx2 = save_kzx2;
	}

	public String getSave_kzx3() {
		return save_kzx3;
	}

	public void setSave_kzx3(String save_kzx3) {
		this.save_kzx3 = save_kzx3;
	}

	public String getSave_kzx4() {
		return save_kzx4;
	}

	public void setSave_kzx4(String save_kzx4) {
		this.save_kzx4 = save_kzx4;
	}

	public String getSave_kzx5() {
		return save_kzx5;
	}

	public void setSave_kzx5(String save_kzx5) {
		this.save_kzx5 = save_kzx5;
	}

	public String getSave_kzx6() {
		return save_kzx6;
	}

	public void setSave_kzx6(String save_kzx6) {
		this.save_kzx6 = save_kzx6;
	}

	public String getSave_kzx7() {
		return save_kzx7;
	}

	public void setSave_kzx7(String save_kzx7) {
		this.save_kzx7 = save_kzx7;
	}

	public String getSave_kzx8() {
		return save_kzx8;
	}

	public void setSave_kzx8(String save_kzx8) {
		this.save_kzx8 = save_kzx8;
	}

	public String getSave_kzx9() {
		return save_kzx9;
	}

	public void setSave_kzx9(String save_kzx9) {
		this.save_kzx9 = save_kzx9;
	}

	public String getSave_byssbjssj() {
		return save_byssbjssj;
	}

	public void setSave_byssbjssj(String save_byssbjssj) {
		this.save_byssbjssj = save_byssbjssj;
	}

	public String getSave_byssbkssj() {
		return save_byssbkssj;
	}

	public void setSave_byssbkssj(String save_byssbkssj) {
		this.save_byssbkssj = save_byssbkssj;
	}

	public String getSave_shjg() {
		return save_shjg;
	}

	public void setSave_shjg(String save_shjg) {
		this.save_shjg = save_shjg;
	}

	public String getQueryequals_shjg() {
		return queryequals_shjg;
	}

	public void setQueryequals_shjg(String queryequals_shjg) {
		this.queryequals_shjg = queryequals_shjg;
	}


	public String getSave_qrsj() {
		return save_qrsj;
	}

	public void setSave_qrsj(String save_qrsj) {
		this.save_qrsj = save_qrsj;
	}

	public String getSave_sfqr() {
		return save_sfqr;
	}

	public void setSave_sfqr(String save_sfqr) {
		this.save_sfqr = save_sfqr;
	}

	public String getSave_qrrbh() {
		return save_qrrbh;
	}

	public void setSave_qrrbh(String save_qrrbh) {
		this.save_qrrbh = save_qrrbh;
	}

	public String getQueryequals_sfqr() {
		return queryequals_sfqr;
	}

	public void setQueryequals_sfqr(String queryequals_sfqr) {
		this.queryequals_sfqr = queryequals_sfqr;
	}

	public String getQueryequals_sfsy() {
		return queryequals_sfsy;
	}

	public void setQueryequals_sfsy(String queryequals_sfsy) {
		this.queryequals_sfsy = queryequals_sfsy;
	}

	public String getGwfbsj() {
		return gwfbsj;
	}

	public void setGwfbsj(String gwfbsj) {
		this.gwfbsj = gwfbsj;
	}

	public String getMoreTerm() {
		return moreTerm;
	}

	public void setMoreTerm(String moreTerm) {
		this.moreTerm = moreTerm;
	}

	public String[] getShr() {
		return shr;
	}

	public void setShr(String[] shr) {
		this.shr = shr;
	}

	public String getQuerylike_shr() {
		return querylike_shr;
	}

	public void setQuerylike_shr(String querylike_shr) {
		this.querylike_shr = querylike_shr;
	}

	public String getQuerylike_xxshr() {
		return querylike_xxshr;
	}

	public void setQuerylike_xxshr(String querylike_xxshr) {
		this.querylike_xxshr = querylike_xxshr;
	}

	public String getQuerylike_xyshr() {
		return querylike_xyshr;
	}

	public void setQuerylike_xyshr(String querylike_xyshr) {
		this.querylike_xyshr = querylike_xyshr;
	}

	public String getQueryequals_lqqk() {
		return queryequals_lqqk;
	}

	public void setQueryequals_lqqk(String queryequals_lqqk) {
		this.queryequals_lqqk = queryequals_lqqk;
	}

	public String getQueryequals_sbnd() {
		return queryequals_sbnd;
	}

	public void setQueryequals_sbnd(String queryequals_sbnd) {
		this.queryequals_sbnd = queryequals_sbnd;
	}

	public String getQuerylike_sbr() {
		return querylike_sbr;
	}

	public void setQuerylike_sbr(String querylike_sbr) {
		this.querylike_sbr = querylike_sbr;
	}

	public String getSave_sfxg() {
		return save_sfxg;
	}

	public void setSave_sfxg(String save_sfxg) {
		this.save_sfxg = save_sfxg;
	}

	public String getQueryequals_je() {
		return queryequals_je;
	}

	public void setQueryequals_je(String queryequals_je) {
		this.queryequals_je = queryequals_je;
	}

	public String getQueryequals_sfxg() {
		return queryequals_sfxg;
	}

	public void setQueryequals_sfxg(String queryequals_sfxg) {
		this.queryequals_sfxg = queryequals_sfxg;
	}

	public String getSave_bysqrjssj() {
		return save_bysqrjssj;
	}

	public void setSave_bysqrjssj(String save_bysqrjssj) {
		this.save_bysqrjssj = save_bysqrjssj;
	}

	public String getSave_bysqrkssj() {
		return save_bysqrkssj;
	}

	public void setSave_bysqrkssj(String save_bysqrkssj) {
		this.save_bysqrkssj = save_bysqrkssj;
	}

	public String getSave_bysshjssj() {
		return save_bysshjssj;
	}

	public void setSave_bysshjssj(String save_bysshjssj) {
		this.save_bysshjssj = save_bysshjssj;
	}

	public String getSave_bysshkssj() {
		return save_bysshkssj;
	}

	public void setSave_bysshkssj(String save_bysshkssj) {
		this.save_bysshkssj = save_bysshkssj;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getQueryequals_xyshzt() {
		return queryequals_xyshzt;
	}

	public void setQueryequals_xyshzt(String queryequals_xyshzt) {
		this.queryequals_xyshzt = queryequals_xyshzt;
	}

	public String getQueryequals_sybdzt() {
		return queryequals_sybdzt;
	}

	public void setQueryequals_sybdzt(String queryequals_sybdzt) {
		this.queryequals_sybdzt = queryequals_sybdzt;
	}

	public String getJsnr() {
		return jsnr;
	}

	public void setJsnr(String jsnr) {
		this.jsnr = jsnr;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getSbnd() {
		return sbnd;
	}

	public void setSbnd(String sbnd) {
		this.sbnd = sbnd;
	}

	public String getSbr() {
		return sbr;
	}

	public void setSbr(String sbr) {
		this.sbr = sbr;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getClientColumns() {
		return clientColumns;
	}

	public void setClientColumns(String clientColumns) {
		this.clientColumns = clientColumns;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getBynf() {
		return bynf;
	}

	public void setBynf(String bynf) {
		this.bynf = bynf;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getDxhwp() {
		return dxhwp;
	}

	public void setDxhwp(String dxhwp) {
		this.dxhwp = dxhwp;
	}

	public String getDzyx() {
		return dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getGbzy() {
		return gbzy;
	}

	public void setGbzy(String gbzy) {
		this.gbzy = gbzy;
	}

	public String getGbzydm() {
		return gbzydm;
	}

	public void setGbzydm(String gbzydm) {
		this.gbzydm = gbzydm;
	}

	public String getHmcqk() {
		return hmcqk;
	}

	public void setHmcqk(String hmcqk) {
		this.hmcqk = hmcqk;
	}

	public String getHmcym() {
		return hmcym;
	}

	public void setHmcym(String hmcym) {
		this.hmcym = hmcym;
	}

	public String getIsjys() {
		return isjys;
	}

	public void setIsjys(String isjys) {
		this.isjys = isjys;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getKsh() {
		return ksh;
	}

	public void setKsh(String ksh) {
		this.ksh = ksh;
	}

	public String getLxdk() {
		return lxdk;
	}

	public void setLxdk(String lxdk) {
		this.lxdk = lxdk;
	}

	public String getLxdz() {
		return lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	public String getMzdm() {
		return mzdm;
	}

	public void setMzdm(String mzdm) {
		this.mzdm = mzdm;
	}

	public String getMzmc() {
		return mzmc;
	}

	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}

	public String getPyfs() {
		return pyfs;
	}

	public void setPyfs(String pyfs) {
		this.pyfs = pyfs;
	}

	public String getPyfsdm() {
		return pyfsdm;
	}

	public void setPyfsdm(String pyfsdm) {
		this.pyfsdm = pyfsdm;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getQrr() {
		return qrr;
	}

	public void setQrr(String qrr) {
		this.qrr = qrr;
	}

	public String getQrrbh() {
		return qrrbh;
	}

	public void setQrrbh(String qrrbh) {
		this.qrrbh = qrrbh;
	}

	public String getQrsj() {
		return qrsj;
	}

	public void setQrsj(String qrsj) {
		this.qrsj = qrsj;
	}

	public String getRxnf() {
		return rxnf;
	}

	public void setRxnf(String rxnf) {
		this.rxnf = rxnf;
	}

	public String getSbrzgh() {
		return sbrzgh;
	}

	public void setSbrzgh(String sbrzgh) {
		this.sbrzgh = sbrzgh;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getSfdl() {
		return sfdl;
	}

	public void setSfdl(String sfdl) {
		this.sfdl = sfdl;
	}

	public String getSflxdk() {
		return sflxdk;
	}

	public void setSflxdk(String sflxdk) {
		this.sflxdk = sflxdk;
	}

	public String getSfqr() {
		return sfqr;
	}

	public void setSfqr(String sfqr) {
		this.sfqr = sfqr;
	}

	public String getSfsf() {
		return sfsf;
	}

	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}

	public String getSfxg() {
		return sfxg;
	}

	public void setSfxg(String sfxg) {
		this.sfxg = sfxg;
	}

	public String getSfzz() {
		return sfzz;
	}

	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getSydq() {
		return sydq;
	}

	public void setSydq(String sydq) {
		this.sydq = sydq;
	}

	public String getSyds() {
		return syds;
	}

	public void setSyds(String syds) {
		this.syds = syds;
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

	public String getSydx() {
		return sydx;
	}

	public void setSydx(String sydx) {
		this.sydx = sydx;
	}

	public String getSydxian() {
		return sydxian;
	}

	public void setSydxian(String sydxian) {
		this.sydxian = sydxian;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXbdm() {
		return xbdm;
	}

	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
	}

	public String getXjbdqk() {
		return xjbdqk;
	}

	public void setXjbdqk(String xjbdqk) {
		this.xjbdqk = xjbdqk;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getXlccdm() {
		return xlccdm;
	}

	public void setXlccdm(String xlccdm) {
		this.xlccdm = xlccdm;
	}

	public String getXsgb() {
		return xsgb;
	}

	public void setXsgb(String xsgb) {
		this.xsgb = xsgb;
	}

	public String getXxdm() {
		return xxdm;
	}

	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}

	public String getXxmc() {
		return xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getXyshr() {
		return xyshr;
	}

	public void setXyshr(String xyshr) {
		this.xyshr = xyshr;
	}

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getXyshzt() {
		return xyshzt;
	}

	public void setXyshzt(String xyshzt) {
		this.xyshzt = xyshzt;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getYsyd() {
		return ysyd;
	}

	public void setYsyd(String ysyd) {
		this.ysyd = ysyd;
	}

	public String getYydj() {
		return yydj;
	}

	public void setYydj(String yydj) {
		this.yydj = yydj;
	}

	public String getYzbh() {
		return yzbh;
	}

	public void setYzbh(String yzbh) {
		this.yzbh = yzbh;
	}

	public String getZslb() {
		return zslb;
	}

	public void setZslb(String zslb) {
		this.zslb = zslb;
	}

	public String getZslbdm() {
		return zslbdm;
	}

	public void setZslbdm(String zslbdm) {
		this.zslbdm = zslbdm;
	}

	public String getZxwyyzdm() {
		return zxwyyzdm;
	}

	public void setZxwyyzdm(String zxwyyzdm) {
		this.zxwyyzdm = zxwyyzdm;
	}

	public String getZyfx() {
		return zyfx;
	}

	public void setZyfx(String zyfx) {
		this.zyfx = zyfx;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getZzmmmc() {
		return zzmmmc;
	}

	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}

	public String getSybdzt() {
		return sybdzt;
	}

	public void setSybdzt(String sybdzt) {
		this.sybdzt = sybdzt;
	}

	public String getJybz() {
		return jybz;
	}

	public void setJybz(String jybz) {
		this.jybz = jybz;
	}

	public String getSfdk() {
		return sfdk;
	}

	public void setSfdk(String sfdk) {
		this.sfdk = sfdk;
	}

	public String getJyhy() {
		return jyhy;
	}

	public void setJyhy(String jyhy) {
		this.jyhy = jyhy;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXxshr() {
		return xxshr;
	}

	public void setXxshr(String xxshr) {
		this.xxshr = xxshr;
	}

	public String getQueryequals_bzrsh() {
		return queryequals_bzrsh;
	}

	public void setQueryequals_bzrsh(String queryequals_bzrsh) {
		this.queryequals_bzrsh = queryequals_bzrsh;
	}

	public String getBddqshen() {
		return bddqshen;
	}

	public void setBddqshen(String bddqshen) {
		this.bddqshen = bddqshen;
	}

	public String getBddqshi() {
		return bddqshi;
	}

	public void setBddqshi(String bddqshi) {
		this.bddqshi = bddqshi;
	}

	public String getBddqxian() {
		return bddqxian;
	}

	public void setBddqxian(String bddqxian) {
		this.bddqxian = bddqxian;
	}


}
