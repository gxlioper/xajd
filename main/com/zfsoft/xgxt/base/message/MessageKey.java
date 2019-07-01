package com.zfsoft.xgxt.base.message;

/**
 * <p>消息编号定义</p>
 * <p>与message.properties中key匹配</p>
 * @author qph
 * 日期：2013-4-16
 */
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-11-3 下午06:33:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public interface MessageKey {

	/*
	 * 系统消息编号
   * 一般以模块来区别，如学生资助以XSZZ_ 开头
	 */
	public static final String SYS_TOKEN_VALID = "sys_token_valid";//重复提交
	
	public static final String SYS_OPERATE_FAIL = "sys_operate_fail";//操作失败
	public static final String SYS_OPERATE_SUCCESS = "sys_operate_success";//操作成功
	public static final String SYS_AUTH_FAIL = "sys_auth_fail";
	public static final String SYS_TB_SUCCESS = "sys_tb_success";//同步成功
	public static final String SYS_TB_FAIL = "sys_tb_fail";//同步失败
	public static final String SYS_SAVE_SUCCESS = "sys_save_success";//保存成功
	public static final String SYS_SAVE_FAIL = "sys_save_fail";//保存失败
	public static final String SYS_ZX_SUCCESS = "sys_zx_success";//注销成功
	public static final String SYS_XZ_FAIL = "sys_zx_fail";//注销失败
	public static final String SYS_SAVE_FILESIZE_OUT = "sys_save_filesize_out";//文件大小超出
	public static final String SYS_SUBMIT_SUCCESS = "sys_submit_success";//提交成功
	public static final String SYS_SUBMIT_SUCCESS_NUM = "sys_submit_success_num";//提交成功{0}条
	public static final String SYS_SUBMIT_FAIL = "sys_submit_fail";//提交失败
	public static final String SYS_DEL_SUCCESS  = "sys_del_success";//删除成功
	public static final String SYS_DEL_FAIL = "sys_del_fail";//删除失败
	public static final String SYS_DEL_NUM = "sys_del_num";//成功删除{0}条
	public static final String SYS_JC_FAIL = "sys_jc_fail";//解除失败
	public static final String SYS_JC_NUM = "sys_jc_num";//成功解除{0}条
	public static final String SYS_SAVE_DM_REPEAT = "sys_save_dm_repeat";//代码存在
	public static final String SYS_DEL_NULL = "sys_del_null";//请选择您要删除的记录！
	public static final String SYS_AUD_SUCCESS = "sys_aud_success";//审核成功
	public static final String SYS_AUD_FAIL = "sys_aud_fail";//审核失败
	public static final String SYS_AUD_ERROR = "sys_aud_error";//审核失败
	public static final String SYS_AUD_DOUBLE = "sys_aud_double"; //重复提交
	
	public static final String SYS_SFQY_Y_FAIL = "sys_sfqy_y_fail";//启用失败
	public static final String SYS_SFQY_Y_NUM = "sys_sfqy_y_num";//成功启用{0}条
	public static final String SYS_SFQY_Y_NULL = "sys_sfqy_y_null";//请选择您要启用的记录！
	public static final String SYS_SFQY_N_FAIL = "sys_sfqy_n_fail";//停用失败
	public static final String SYS_SFQY_N_NUM = "sys_sfqy_n_num";//成功停用{0}条
	public static final String SYS_SFQY_N_NULL = "sys_sfqy_n_null";//请选择您要停用的记录！
	
	public static final String SYS_CANCEL_SUCCESS = "sys_cancel_success";//撤消成功
	public static final String SYS_CANCEL_SUCCESS_NUM = "sys_cancel_success_num";//成功撤销{0}条,失败撤销{1}条
	public static final String SYS_AUD_CANCEL_FAIL = "sys_aud_cancel_fail";
	public static final String SYS_CANCEL_FAIL = "sys_cancel_fail";//撤消失败
	public static final String SYS_CANCEL_NULL = "sys_cancel_null";//请选择您要撤销的记录！
	public static final String SYS_CANCEL_END= "sys_cancel_end";
	public static final String SYS_INIT_SUCCESS= "sys_init_success";//初始化成功
	public static final String SYS_INIT_FAIL= "sys_init_fail";//初始化失败
	public static final String SYS_SELECT_SHLC_FAIL= "sys_select_shlc_fail";//请先设定审批流程
	public static final String SYS_AUD_PERS_OUT = "sys_aud_pers_out";//选择的人数已超过指标剩余名额
	public static final String DATA_OVERLAP = "data_overlap";//起止日期重叠
	public static final String SYS_COPY_SUCCESS = "sys_copy_success";//复制成功
	public static final String SYS_COPY_FAIL = "sys_copy_fail";//复制失败
	public static final String SYS_SAVE_STUDENT = "sys_save_student";//该学生相同时间、相同迁移状态记录已存在，请确认！

	public static final String SYS_GYGL_SSPYCF = "sys_gygl_sspycf";//宿舍评奖宿舍重复
	public static final String SYS_GYGL_WRZ = "sys_gygl_wrz";//学生未入住
	public static final String SYS_CANCEL_TH= "sys_cancel_th";//已经退回的审核不能再次撤销
	public static final String SYS_CANCEL_Qx= "sys_cancel_qx";//已经取消的记录不能撤销
	
	public static final String SYS_BACK_FAIL = "sys_back_fail";//退回失败
	public static final String SYS_PLSH_FAIL = "sys_plsh_fail";//批量审核失败
	public static final String SYS_CANSQ_FAIL = "sys_cansq_fail";//取消申请失败 
	public static final String SYS_CANSQ_SUCCESS = "sys_cansq_success";//取消申请成功 
	public static final String SYS_PLBC_FAIL = "sys_plbc_fail";//批量保存失败
	public static final String SYS_CREATE_IMPORT_TEMPLATE_FAIL = "sys_createImportTemplate_fail";
	public static final String SYS_IMPORT_UNLAWFUL_TEMPLATE = "sys_import_unlawful_template";
	public static final String SYS_IMPORT_SUCCESS = "sys_import_success";
	public static final String SYS_SYNC_SUCCESS = "sys_sync_success";
	public static final String SYS_SYNC_FAIL = "sys_sync_fail";
	
	public static final String ZYSZPJ_CANNOT_ADD="zyszpj_cannot_add";//不能增加职业素质评价
	
	public static final String SYS_DELDM_EXIST_FAIL = "sys_deldm_exist_fail";//代码已经被使用，不允许修改
	public static final String SYS_SAVE_UPLOAD_FAIL = "sys_save_upload_fail";//上传附件失败
	
	
	
	/*
	 * 异常消息编号
	 */
	public static final String EXP_SYS_ERROR = "exp_sys_error";
	
	/*
	 * 思政队伍
	 */
	public static final String SZDW_REPEAT_ERROR = "szdw_repeat_error";//重复录入
	public static final String SZDW_FYDPX_XMWKF = "szdw_fydpx_xmwkf" ; //当前申请项目未开放申请！
	
	/*
	 * 日常事务
	 */
	public static final String RCSW_REPEAT_ERROR = "rcsw_repeat_error";//重复录入
	
	public static final String RCSW_KQGL_KQSH_BZCK = "rcsw_kqgl_kqsh_bzck"; //{0}在初次审核阶段，无需退回操作！
	public static final String RCSW_KQGL_KQSH_PLSH = "rcsw_kqgl_kqsh_plsh"; //{0}已经被其他用户审核！
	
	/*
	 * 学生信息
	 */
	public static final String XSXX_TDJS_FAIL = "sys_tdjs_fail";
	public static final String XSXX_TDJS_XHNULL = "sys_tdjs_xhnull";
	public static final String XSXX_TDJS_DEL_FAIL = "xsxx_tdjs_del_fail";
	public static final String XSXX_DAXX_REPEAT_ERROR = "xsxx_daxx_repeat_error";//重复录入
	public static final String XSXX_ZYFWSQ_REPEAT = "xsxx_zyfwsq_repeat";//该学生【{0}-{1}】时间内，志愿服务已有登记！。
	/*
	 * 困难生档次
	 */
	public static final String XSZZ_KNSDC_REPEAT = "xszz_knsdc_repeat";
	public static final String XSZZ_KNSDC_EXIST_KNSJG_DELETE = "xszz_knsdc_exist_knsjg_delete";
	public static final String XSZZ_KNSDC_EXIST_KNSSH_DELETE = "xszz_knsdc_exist_knssh_delete";
	
	/*
	 * 困难原因
	 */
	public static final String XSZZ_KNYY_REPEAT = "xszz_knyy_repeat";
	public static final String XSZZ_KNYY_USED = "xszz_knyy_used";
	
	public static final String XSZZ_KNSDC_EXIST_KNSJG_UPDATE = "xszz_knsdc_exist_knsjg_update";
	public static final String XSZZ_KNSDC_EXIST_KNSSH_UPDATE = "xszz_knsdc_exist_knssh_update";
	
	/*
	 * 困难生结果
	 */
	public static final String XSZZ_KNSJG_RESULT_REPEAT = "xszz_knsjg_result_repeat";

	public static final String XSZZ_KNSJG_RESULT_REPEAT_XN = "xszz_knsjg_result_repeat_xn";

	public static final String XSZZ_KNSJG_RESULT_REPEAT_XQ = "xszz_knsjg_result_repeat_xq";
	
	/*
	 * 困难生基础设置
	 */
	public static final String XSZZ_KNSJCSZ_SHLC_EXIST = "xszz_knsjcsz_shlc_exist";
	
	
	/*
	 * 学籍异动
	 */
	
	
	public static final String XJYD_XMLBMC_EXIST = "general_mcexist";						//【{0}】已存在
	public static final String XYJD_UPDATE_DMEXIST = "general_update_dmexist";			  	//【{0}】已被使用，不能修改！
	public static final String XYJD_DELETE_DMEXIST = "general_delete_dmexist";				//【{0}】已被使用，不能删除！
	
	/*
	 * 学生资助
	 */		
	public static final String SYS_AUD_FAIL_XSZZ = "sys_aud_fail_xszz"; // 审核失败，该申请记录已经被其他用户审核，或该学生已获得该项目！
	public static final String EXP_XSZZ_RSKZFW_NULL = "exp_xszz_rskzfw_null";//学生资助，人数控制范围为空
	
	public static final String XSZZ_XMLBMC_EXIST = "xszz_xmlbmc_exist";//学生资助，类别名称已存在！
	public static final String XSZZ_XMMC_EXIST = "xszz_xmmc_exist";//学生资助，项目名称已存在！
	public static final String XSZZ_XMLB_NOTUPDATE = "xszz_xmlb_notupdate";//学生资助，该类别下已添加项目，不允许修改！
	public static final String XSZZ_XMLB_NOTDEL = "xszz_xmlb_notdel";//学生资助，该类别下已添加项目，不允许删除！
	public static final String XSZZ_XM_NOTUPDATE = "xszz_xm_notupdate";//学生资助，该项目已被申请，不允许修改！
	public static final String XSZZ_XM_NOTDEL = "xszz_xm_notdel";//学生资助，项目已被申请，不允许删除！
	public static final String RSKZ_FAIL ="rskz_fail";//人数控制已到达最大人数（审核）
	public static final String RSKZ_FAIL_CANCEL ="rskz_fail_cancel";//人数控制已到达最大人数（撤销）
	public static final String XSZZ_BBDY_FAIL ="xszz_bbdy_fail";//报表打印，报表未设置，该项目尚未设置报表，请联系管理员！
	public static final String PJPY_BBDY_FAIL ="pjpy_bbdy_fail";//报表打印，报表未设置，该项目尚未设置报表，请联系管理员！
	public static final String XSZZ_RSSZ_ZZRSYZ ="xszz_rssz_zzrsyz";//学生资助，人数设置，根据已申请通过的学生人数进行校验提示
	public static final String XSZZ_RSSZ_ZZRSTS ="xszz_rssz_zzrsts";//学生资助，人数设置，根据已申请通过的学生人数进行校验提示
	public static final String PJPY_FAIL="pjpy_fail"; // 该项目已有一条记录正在审核中或已通过。
	
	public static final String RCSW_SYBX_RESULT_REPEAT ="rcsw_sybx_result_repeat";//日常事务，商业保险，该学员在本学年已有商业保险信息存在！
	public static final String XSZZ_JEKZ_FAIL = "xszz_jekz_fail";
	public static final String XSZZ_JEKZ_TEXT_FAIL = "xszz_jekz_text_fail";
	public static final String XSZZ_BJPY_XZRY_FAIL = "xszz_bjpy_xzry_fail";
	public static final String XSZZ_BJPYXZSZ_XZRSXX_FAIL = "xszz_bjpyxzsz_xzrsxx_fail";
	public static final String XSZZ_BJPYXZSZ_XZRSXX_XSRS_FAIL = "xszz_bjpyxzsz_xzrsxx_xsrs_fail";
	
	public static final String XSZZ_ZZKFF_XMMC_REPEAT = "xszz_zzkff_xmmc_repeat";	//同一个学生，在同一个学年学期、不能发放同项目名称的资助款
	/*
	 * 资助待遇
	 */
	
	public static final String XSZZ_ZZDY_REPEAT = "xszz_zzdy_repeat";
	

	/*
	 * 评奖评优
	 */	
	public static final String PJPY_CPXZCSH_NOTALLOW = "pjpy_cpxzcsh_notAllow";//参评小组初始化不被允许
	public static final String PJPY_CPXZCSH_QXBZ = "pjpy_cpxzcsh_qxbz";//参评小组初始化权限不足
	public static final String SYS_QXCP_FAIL = "sys_qx_fail"; //取消参评失败
	public static final String SYS_QXCP_SUCCESS = "sys_qx_success";  //取消参评成功
	public static final String SYS_TZ_FAIL = "sys_tz_fail"; //调整失败_评奖名单确认
	public static final String SYS_TZ_SUCCESS = "sys_tz_success";  //调整成功_评奖名单确认
	public static final String PJPY_IMPORT_ZCFS_FAIL = "pjpy_import_zcfs_fail";//无权导入综测分
	public static final String PJPY_IMPORT_ZCFS_XHXM_NULL = "pjpy_import_zcfs_xhxm_null";//导入综测分学号、姓名 为空
	public static final String PJPY_IMPORT_ZCFS_NULL = "pjpy_import_zcfs_null";//导入综测分 为空
	public static final String PJPY_IMPORT_ZCFS_NOTNUMBER = "pjpy_import_zcfs_notnuber";//导入综测分必须为数字
	public static final String PJPY_IMPORT_ZCFS_MORETHEN_MAXLENGTH = "pjpy_import_zcfs_morethen_maxlength";//超过最大长度
	public static final String PJPY_IMPORT_ZCFS_MORETHEN_ZDZX = "pjpy_import_zcfs_zdzx";//超过最大长度
	public static final String PJPY_IMPORT_ZCFS_MORETHEN_ZDDJ = "pjpy_import_zcfs_morethen_zddj"; //“”等级不存在
	//班风竞赛
	public static final String PJPY_IMPORT_JSFS_FAIL = "pjpy_import_jsfs_fail";//无权导入竞赛分分
	public static final String PJPY_IMPORT_JSFS_BJDM_NULL = "pjpy_import_jsfs_bjdm_null";//导入竞赛分分BJDM 为空
	public static final String PJPY_IMPORT_JSFS_NULL = "pjpy_import_jsfs_null";//导入竞赛分分 为空
	public static final String PJPY_IMPORT_JSFS_NOTNUMBER = "pjpy_import_jsfs_notnuber";//导入竞赛分分必须为数字
	public static final String PJPY_IMPORT_JSFS_MORETHEN_MAXLENGTH = "pjpy_import_jsfs_morethen_maxlength";//超过最大长度
	public static final String PJPY_IMPORT_JSFS_MORETHEN_ZDZX = "pjpy_import_jsfs_zdzx";//超过最大长度
	public static final String PJPY_IMPORT_JSFS_MORETHEN_ZDDJ = "pjpy_import_jsfs_morethen_zddj"; //“”等级不存在
	public static final String PJPY_AUDING_QXBZ = "pjpy_auding_qxbz";
	public static final String PJPY_XMLX_LXEXIST = "pjpy_xmlx_lxexist";  //项目类型名称已存在
	public static final String PJPY_XMXZ_XZEXIST = "pjpy_xmxz_xzexist";	 //项目性质名称已存在
	public static final String PJPY_BJDM_DMEXIST = "pjpy_bjdm_dmexist";	 //班级大类名称已存在
	public static final String PJPY_DMWH_EXIST_PJJG_UPDATE = "pjpy_dmwh_exist_pjjg_update";  //已有评奖结果使用，不能修改。
	public static final String PJPY_DMWH_EXIST_PJJG_DELETE = "pjpy_dmwh_exist_pjjg_delete";	 //已有评奖结果使用，不能删除。
	public static final String PJPY_DMWH_EXIST_PJXM_UPDATE = "pjpy_dmwh_exist_pjxm_update";  //已有评奖项目使用，不能修改。
	public static final String PJPY_DMWH_EXIST_PJXM_DELETE = "pjpy_dmwh_exist_pjxm_delete";	 //已有评奖项目使用，不能删除。
	public static final String PJPY_BJDM_EXIST_BJDL_UPDATE = "pjpy_bjdm_exist_bjdl_update";  //大类已被使用，不能修改。
	public static final String PJPY_BJDM_EXIST_BJDL_DELETE = "pjpy_bjdm_exist_bjdl_delete";  //大类已被使用，删除。
	public static final String PJPY_TSXSDM_NAME_REPEAT = "pjpy_tsxsdm_name_repeat";	//类型名称已存在
	public static final String PJPY_TSXSDM_EXIST_TSXSB_UPDATE = "pjpy_tsxsdm_exist_tsxsb_update"; //类型已被使用不能修改
	public static final String PJPY_TSXSDM_EXIST_TSXSB_DELETE = "pjpy_tsxsdm_exist_tsxsb_delete"; //类型已被使用不能删除
	
	//评奖评优，获奖信息
	public static final String PJPY_XWHJ_DMWH_EXIST_SQ = "pjpy_xwhj_dmwh_exist_sq";  //已有学生申请，不能删除
	public static final String PJPY_XWHJ_DMWH_EXIST_JG = "pjpy_xwhj_dmwh_exist_jg";  //已有结果使用，不能删除
	public static final String PJPY_XWHJ_DMWH_EXIST_JXDJ = "pjpy_xwhj_dmwh_exist_jxdj";  //已有奖项等级使用，不能删除
	public static final String PJPY_XWHJ_DMWH_EXIST_JXMC = "pjpy_xwhj_dmwh_exist_jxmc";  //已有奖项名次使用，不能删除
	
	//评奖评优，项目维护
	public static final String PJPY_XMMC_EXIST = "pjpy_xmmc_exist";//评奖评优，项目名称已存在！
	public static final String PJPY_XM_NOTUPDATE = "pjpy_xm_notupdate";//评奖评优，该项目已被申请，不允许修改！
	public static final String PJPY_XM_NOTDEL = "pjpy_xm_notdel";//评奖评优，项目已被申请，不允许删除！
	public static final String PJPY_RSKZ_FAIL ="pjpy_rskz_fail";//人数控制已到达最大人数
	public static final String PJPY_RSSZ_ZZRSYZ ="pjpy_rssz_zzrsyz";//评奖评优，人数设置，根据已申请通过的学生人数进行校验提示
	public static final String PJPY_RSSZ_ZZRSTS ="pjpy_rssz_zzrsts";//评奖评优，人数设置，根据已申请通过的学生人数进行校验提示
	public static final String PJPY_RSSZ_ZZRSTS_XX ="pjpy_rssz_zzrsts_xx";//评奖评优，人数设置，根据已申请通过的学生人数进行校验提示
	public static final String PJPY_JXFZ_NOTJL ="pjpy_jxfz_notjl";//评奖评优，奖项复制，无可复制的奖项
	public static final String PJPY_JXFZ_SUCCESS ="pjpy_jxfz_success";//评奖评优，奖项复制，复制成功
	public static final String PJPY_XSXH_EXIST ="pjpy_xsxh_exist";//评奖评优，项目序号已存在！
	public static final String SYS_SAVE_REPEATING = "sys_save_repeating";//此综测项目中有重复的数据存在！
	
	/**
	 * 日常事务-日常行为维护 
	 */
	public static final String RCSW_RCXWWH_XWDLCZ = "rcsw_rcxwwh_xwdlcz";//该行为大类代码已存在！
	public static final String RCSW_RCXWWH_XWLBCZ = "rcsw_rcxwwh_xwlbcz";//该行为类别代码已存在！
	public static final String RCSW_RCXWWH_XWDLDEL = "rcsw_rcxwwh_xwdldel";
	public static final String RCSW_RCXWWH_XWDLUPDATE = "rcsw_rcxwwh_xwdlupdate";
	public static final String RCSW_RCXWWH_XWDLYSY = "rcsw_rcxwwh_xwdlysy";//该行为大类已经使用，不能删除！！
	public static final String RCSW_RCXWWH_XWLBDEL = "rcsw_rcxwwh_xwlbdel";
	public static final String RCSW_RCXWWH_XWLBUPDATE = "rcsw_rcxwwh_xwlbupdate";
	public static final String RCSW_RCXWWH_XWLBYSY = "rcsw_rcxwwh_xwlbysy";//该行为类别代码已经使用，不能删除！！
	public static final String RCSW_RCXWWH_XWWHSHZ = "rcsw_rcxwwh_xwwhshz";
	
	public static final String RCSW_RCXWWH_SFQY_Y_FAIL = "rcsw_rcxwwh_sfqy_y_fail";//启用失败
	public static final String RCSW_RCXWWH_SFQY_Y_NUM = "rcsw_rcxwwh_sfqy_y_num";//成功启用{0}条
	public static final String RCSW_RCXWWH_SFQY_Y_NULL = "rcsw_rcxwwh_sfqy_y_null";//请选择您要启用的记录！
	public static final String RCSW_RCXWWH_SFQY_N_FAIL = "rcsw_rcxwwh_sfqy_n_fail";//停用失败
	public static final String RCSW_RCXWWH_SFQY_N_NUM = "rcsw_rcxwwh_sfqy_n_num";//成功停用{0}条
	public static final String RCSW_RCXWWH_SFQY_N_NULL = "rcsw_rcxwwh_sfqy_n_null";//请选择您要停用的记录！
	
	/**
	 * 日常事务-日常行为维护 new
	 */
	public static final String RCSW_RCXWWH_NEW_RCXWLBMCCZ = "rcsw_rcxwwh_new_rcxwlbmccz";//该行为类别名称已存在！
	public static final String RCSW_RCXWWH_NEW_RCXWLBDEL = "rcsw_rcxwwh_new_rcxwlbdel";// 【{0}】已被使用，不能删除！
	public static final String RCSW_RCXWWH_NEW_RCXWDLCZ = "rcsw_rcxwwh_new_rcxwdlcz";//该行为类别已存在相同行为大类名称和审核流程！
	public static final String RCSW_RCXWWH_NEW_RCXWDLDEL = "rcsw_rcxwwh_new_rcxwdldel";// 【{0}】已被使用，不能删除！
	public static final String RCSW_RCXWWH_NEW_RCXWLBUPDATE = "rcsw_rcxwwh_new_rcxwlbupdate";//【{0}】已经被行为维护使用，不能修改！
	public static final String RCSW_RCXWWH_NEW_RCXWXLCZ = "rcsw_rcxwwh_new_rcxwxlcz";//该行为大类已存在相同行为小类名称！
	public static final String RCSW_RCXWWH_NEW_RCXWXLDEL = "rcsw_rcxwwh_new_rcxwxldel";//【{0}】已经被行为维护使用，不能删除！
	
	// 科研创新管理-科研创新管理-类别维护
	public static final String KYCXGL_KYCXXM_KYCXXMWH_SAVE = "kycxgl_kycxxm_kycxxmwh_save";
	public static final String KYCXGL_KYCXXM_KYCXXMWH_DEL = "kycxgl_kycxxm_kycxxmwh_del";
	public static final String KYCXGL_KYCXXM_KYCXXMWH_EXISTS = "kycxgl_kycxxm_kycxxmwh_exists";
	public static final String KYCXGL_KYCXXM_KYCX_EXISTS = "kycxgl_kycxxm_kycx_exists";
	
	// 就业管理-就业管理
	public static final String JYGLNEW_JYGL_BYQX_EXISTS = "jyglnew_jygl_byqx_exists";
	public static final String JYGLNEW_JYGL_CYJYYZD_EXISTS = "jyglnew_jygl_cyjyyzd_exists";
	public static final String JYGLNEW_JYGL_CYYQGL_EXISTS = "jyglnew_jygl_cyyqgl_exists";
	public static final String JYGLNEW_JYGL_CYPX_EXISTS = "jyglnew_jygl_cypx_exists";
	
	// 日常事务-在线咨询-咨询版块设置
	public static final String RCSW_ZXZX_ZXBKSZ_DEL = "rcsw_zxzx_zxbksz_del";
	public static final String RCSW_ZXZX_ZXBKSZ_EXISTS = "rcsw_zxzx_zxbksz_exists";
	
	//日常事务-团学活动
	public static final String RCSW_TXHD_LBDMEXIST = "rcsw_txhd_lbdmexist"; //类别代码已存在！
	public static final String RCSW_TXHD_LBDMEXIST_JG_UPDATE = "rcsw_txhd_lbdmexist_jg_update"; //类别代码结果当中已存在！
	public static final String RCSW_TXHD_LBDMEXIST_JG_DEL = "rcsw_txhd_lbdmexist_jg_del"; 
	public static final String RCSW_TXHD_LBDMEXIST_XMWH_UPDATE = "rcsw_txhd_lbdmexist_xmwh_update"; //类别代码项目维护当中已存在！
	public static final String RCSW_TXHD_LBDMEXIST_XMWH_DEL = "rcsw_txhd_lbdmexist_xmwh_del"; 
	public static final String RCSW_TXHD_XMMC_EXIST = "rcsw_txhd_xmmc_exist";//学团活动，项目名称已存在！
	public static final String RCSW_TXHD_XMMC_NOTDEL = "rcsw_txhd_xmmc_notdel";//学团活动，项目已被申请，不允许删除！
	public static final String RCSW_TXHD_XMMC_FULL = "rcsw_txhd_xmmc_full";//学团活动，名额超出上限！
	public static final String RCSW_TXHD_RSKZ_FAIL_SQ ="rcsw_txhd_rskz_fail_sq";//申请人数已超过设置人数，请重新设定
	public static final String RCSW_TXHD_RSKZ_FAIL_SH ="rcsw_txhd_rskz_fail_sh";//审核人数已超过设置人数，请重新设定
	public static final String RCSW_TXHD_DMWH_HDGG_REPEAT = "rcsw_txhd_hdgg_repeat";
	
	//请假管理
	public static final String RCSW_QJGL_CFSQ ="rcsw_qjgl_cfsq";//审核人数已超过设置人数，请重新设定
	
	//违纪处分
	public static final String WJCF_CFLBDM_CFLBCZ = "wjcf_cflbdm_cflbcz";//处分类别存在
	public static final String WJCF_CFLBDM_CFYYCZ = "wjcf_cflbdm_cfyycz";//处分原因存在
	public static final String WJCF_CFLBDM_CFLBYSY = "wjcf_cflbdm_cflbysy";//处分类别已使用
	public static final String WJCF_CFYYDM_CFYYYSY = "wjcf_cfyydm_cfyyysy";//处分原因已使用
	public static final String WJCF_CFSS_CFQXSS = "wjcf_cfss_cfqxss";//重复取消申诉
	public static final String WJCF_DEL_CFYSH = "wjcf_del_cfysh";//处分已审核不能删除
	public static final String WJCF_SBCX_BKCX = "wjcf_sbcx_bkcx";//处分上报最后一级审核撤销，已有申诉、解除不可撤销
	public static final String WJCF_CFLBMC_BKZZ = "wjcf_cflbmc_bkzz";//该处分类别不可终止
	public static final String WJCF_CFWH_FORMAT = "wjcf_cfwh_format";//处分文号
	public static final String WJCF_CFJCWH_FORMAT = "wjcf_cfjcwh_format";//处分解除文号

	/**
	 * 勤工助学
	 */
	public static final String QGZX_CHECK_FFJE = "qgzx_check_ffje";//勤工助学结果增加，金额超出。
	public static final String QGZX_CHECK_FFJE_YF = "qgzx_check_ffje_yf";//勤工助学结果增加，金额超出。
	public static final String QGZX_CHECK_WFFJC = "qgzx_check_wffjf";//勤工助学结果增加，不能操作对应经费。
	public static final String QGZX_CHECK_WFFJF_YF = "qgzx_check_wffjf_yf";//勤工助学结果增加，不能操作对应经费。
	/**
	 * 日常事务-学生证补办
	 */
	public static final String RCSW_XSZBB_BBLXMCCZ = "rcsw_xszbb_bblxmccz";//该学生证补办类型名称已存在！
	public static final String RCSW_XSZBB_RESULT_REPEAT = "rcsw_xszbb_result_repeat";
	public static final String RCSW_XSZBB_XSZBBSQ_REPEAT = "rcsw_xszbb_xszbbsq_repeat";
	public static final String RCSW_XSZBB_XSZBBJG_REPEAT = "rcsw_xszbb_xszbbjg_repeat";
	public static final String RCSW_XSZBB_SHLC_EXIST = "rcsw_xszbb_shlc_exist";
	public static final String RCSW_XSZBB_BBLXMCYSY = "rcsw_xszbb_bblxmcysy";
	
	public static final String RCSW_HCYHK_RESULT_REPEAT = "rcsw_hcyhk_result_repeat";//该学生在同一学年,学期火车乘车区间填写已存在！
	public static final String RCSW_HCYHK_SHLC_EXIST = "rcsw_hcyhk_shlc_exist";
	public static final String RCSW_HCYHK_HCYHKLX_EXIST = "rcsw_hcyhk_hcyhklx_exist";
	
	
	/**
	 * 日常事务--绿色通道
	 */
	public static final String RCSW_LSTD_LSTDMCCZ = "rcsw_lstd_lstdmccz";//该绿色通道类型名称已存在！
	public static final String RCSW_LSTD_LSTDJG_REPEAT = "rcsw_lstd_lstdjg_repeat"; //该学生同一周期已申请过绿色通道。
	public static final String RCSW_LSTD_SQ_REPEAT = "rcsw_lstd_sq_repeat";  //当前周期已有申请，请勿重复提交！
	
	/**
	 * 日常事务--通用医疗保险
	 */
	public static final String RCSW_YLBX_YLBXJG_REPEAT = "rcsw_ylbx_lstdjg_repeat"; //该学生同一周期已申请过医疗保险。
	public static final String RCSW_YLBX_SQ_REPEAT = "rcsw_ylbx_sq_repeat";  //当前周期已有申请，请勿重复提交！
	public static final String RCSW_YLBX_YLBXGL_REPEAT = "rcsw_ylbx_ylbxgl_repeat";  //当前周期已有重复的保险记录！
	public static final String RCSW_YLBX_YLBXSH = "rcsw_ylbx_ylbxsh";  //审核失败，该申请记录已经被其他用户审核，或该学生结果库中已有记录！
	
	/**
	 * 日常事务-大学生医疗保险
	 */
	public static final String RCSW_DXSYLBX_BZLXCZ = "rcsw_dxsylbx_bzlxcz";//补助类型已存在！
	public static final String RCSW_DXSYLBX_SHLC_EXIST = "rcsw_dxsylbx_shlc_exist";//有流程正在审核中，不能修改！
	public static final String RCSW_DXSYLBX_CZQEBZRYSFCZ = "rcsw_dxsylbx_czqebzrysfcz";//财政全额补助人员身份已存在！
	
	public static final String RCSW_DXSYLBX_CBZKLXCZ = "rcsw_dxsylbx_cbzklxcz";//参保状况类型已存在！
	public static final String RCSW_DXSYLBX_YLBXSQCZ = "rcsw_dxsylbx_ylbxsqcz";//该学生在当前学期医疗保险申请已存在！
	public static final String RCSW_DXSYLBX_YLBXJGCZ = "rcsw_dxsylbx_ylbxjgcz";//该学生在当前学期医疗保险结果已存在！
	/**
	 * 日常事务-周报
	 */
	public static final String RCSW_XSGZZB_XSGZZBSQCZ = "rcsw_xsgzzb_xsgzzbsqcz";//在当前学年，学期该【学院】或【班级】的周报已存在！
	/**
	 * 日常事务-困难生认定指标
	 */
	public static final String RCSW_KNSRDZB_QYSUCCESS  = "rcsw_knsrdzb_qysuccess";//启用困难生认定指标成功
	public static final String RCSW_KNSRDZB_QYFAIL  = "rcsw_knsrdzb_qyfail";//启用困难生认定指标失败
	public static final String RCSW_KNSRDZB_KNSRDSQ_REPEAT = "rcsw_knsrdzb_knsrdsq_repeat";//在当前学年，学期该学生困难生认定已申请！

	/**
	 * 分班管理
	 */
	public static final String FBGL_TJZSK_BCZDYXDS  = "fbgl_tjzsk_bczdyxds";//不存在对应已分班编学号学生
	public static final String FBGL_CHECK_LSHBZ  = "fbgl_check_lshbz";//流水号位数不足
	public static final String FBGL_CHECK_LSH_REPEART  = "fbgl_check_lsh_repeart";//流水号重复
	public static final String FBGL_CHECK_BJMC  = "fbgl_check_bjmc";//班级名称过长
	public static final String FBGL_CHECK_BJDM  = "fbgl_check_bjdm";//班级代码过长
	public static final String FBGL_CHECK_BJDM_UNIQUE  = "fbgl_check_bjdm_unique";//班级代码唯一
	public static final String FBGL_CHECK_OK  = "fbgl_check_ok";//编班通过

	/**
	 * 日常事务-在读证明
	 */
	public static final String RCSW_ZDZM_CSSZ_NOT_EXIST  = "rcsw_zdzm_cssz_not_exist";//基础设置未设定，暂无法申请在读证明！
	public static final String RCSW_ZDZM_CSSZ_SQKG_CLOSE  = "rcsw_zdzm_sqkg_close";//当前申请为关闭状态，暂无法申请在读证明！
	public static final String RCSW_ZDZM_SJ_NOT_IN_SZ = "rcsw_zdzm_sj_not_in_sz";//当前时间不在可申请时间范围内，暂无法申请在读证明!
	public static final String RCSW_ZDZM_ALREADY_IN_SHL = "rcsw_zdzm_alreay_in_shl";//当前用户已有申请在审核流中，暂无法申请在读证明!
	
	/**
	 * 日常事务-费用发放
	 */
	public static final String RCSW_FYFF_DMWH_EXIST = "rcsw_fyff_dmwh_exist";  //名称已存在
	public static final String RCSW_FYFF_DMWH_MCEXIST= "rcsw_fyff_dmwh_mcexist";  //[i]名称已存在。
	
	/**
	 * 日常事务-学情月报_班级月报
	 */
	public static final String RCSW_XQYB_BJYBSQ_REPEAT = "rcsw_xqyb_bjybsq_repeat";//该班级在同一学年,学期,月份学情月报申请已存在！
	public static final String RCSW_XQYB_BJYBJG_REPEAT = "rcsw_xqyb_bjybjg_repeat";//该班级在同一学年,学期,月份学情月报结果已存在！
	
	
	/**
	 * 公寓管理
	 */
	public static final String GYGL_PLYD_BKTJ = "gygl_plyd_bktj";
	public static final String GYGL_PLYD_QRTZ_SUCCESS = "gygl_plyd_qrtz_success";
	public static final String GYGL_PLYD_QRTZ_FAIL = "gygl_plyd_qrtz_fail";
	public static final String GYGL_WSJC_IMPORT_NULL = "gygl_wsjc_import_null";
	
	public static final String GYGL_YJXFLDM_CHECK_ERROR  = "gygl_yjxfldm_check_error";
	public static final String GYGL_QSDSWH_FALL = "gygl_qsdswh_fall";
	public static final String GYGL_YDGL_ADD_EXIST = "gygl_ydgl_add_exist";
	public static final String GYGL_WJXX_EXIST = "gygl_wjxx_exist";
	public static final String SZDW_XFJS_ADD_EXIST = "szdw_xfjs_add_exist";
	/**
	 * 日常事务-场地管理
	 */
	public static final String RCSW_CDSQ_CHECK_ERROR  = "rcsw_cdsq_check_error";//申请时间段不在开放时间段内，或者与已申请时间段存在冲突，请确认！
	
	public static final String RCSW_CDSQ_CHECK_ERROR2  = "rcsw_cdsq_check_error2";//场地名称已存在！
	
	
	/**
	 * 日常事务-考勤管理
	 */
	public static final String RCSW_KQGL_DMWH_EXIST = "rcsw_kqgl_dmwh_exist";  //此考勤类型以及存在，不能重复添加！
	public static final String RCSW_KQGL_DMWH_MCEXIST= "rcsw_kqgl_dmwh_mcexist";  //【{0}】名称已被使用！
	
	/**
	 * 日常事务-早晚自习考勤管理
	 */
	//日常事务---基本分管理
	public static final String RCSW_XSXWKH_JBFGL_ADD_EXIST = "rcsw_xsxwkh_jbfgl_add_exist";//同一学年该学生已经有一条记录
	public static final String RCSW_ZWZXKQ_KQSJ_EXIST = "rcsw_zwzxkq_kgsj_exist";  //【{0}】【{1}】【{2}】考勤数据已存在，请勿重复提交！
	//public static final String RCSW_KQGL_DMWH_MCEXIST= "rcsw_kqgl_dmwh_mcexist";  //【{0}】名称已被使用！
	
	/**
	 * 温州大学心理咨询
	 */
	public static final String XLZXWZDX_XSSQ_EXIST= "xlzxwzdx_xssq_exist";  //该学生已申请！
	public static final String XLZX_ZXZXTHJL_EXIST= "xlzx_zxzxthjl_exist";  //同一天同一个学生不允许有多次谈话记录！
	public static final String XLZX_XLSCJG_EXIST= "xlzx_xlscjg_exist";  //当前筛查日期学生已存在！
	
	/**
	 * 心理健康培训
	 */
	public static final String XLZX_PXWH_REPEAT= "xlzx_pxwh_repeat";  //当前培训时间已存在该培训主题
	public static final String XLZX_PXWH_CZSUCCESS= "xlzx_pxwh_czsuccess"; //操作成功
	public static final String XLZX_PXWH_CZFAIL= "xlzx_pxwh_czfail"; //操作失败

	/**
	 * 爱心超市
	 */
	public static final String AXCS_AXLB_AXLBYCZ = "axcs_axlb_axlbycz";//该爱心类别代码已存在！
	public static final String AXCS_AXLB_NOTDEL = "axcs_axlb_axlbysy";//该爱心类别已被使用，不允许删除
	public static final String AXCS_AXWP_NOTDEL = "axcs_axwp_axwpysq";//该爱心物品已被申请，不允许删除
	public static final String AXCS_WPFZ_NOTJL ="axcs_wpfz_wkfzwp";//爱心超市，物品复制，无可复制的物品
	public static final String AXCS_WPFZ_SUCCESS ="axcs_wpfz_success";//爱心超市，物品复制，复制成功
	public static final String AXCS_WPFZ_REPEAT = "axcs_wpfz_repeat"; //学生该周期已申请过【{0}】。
	
	/**
	 * 每日工作考核
	 */
	public static final String QGZX_MRGZKH_REPEAT = "qgzx_mrgzkh_repeat";//学生【{0}】已填写，请勿重复填写。
	public static final String QGZX_MRGZKH_GSOUT = "qgzx_mrgzkh_gsout";//学生【{0}】【{1}】总工时超过学生每月工时上限【{1}】小时。
	public static final String QGZX_MRGZKH_JEOUT = "qgzx_mrgzkh_jeout";//学生【{0}】【{1}】发放金额超过学生每月酬金上限【{1}】元。
	public static final String QGZX_MRGZKH_GWJESXOUT = "qgzx_mrgzkh_gwjesxout";//学生【{0}】发放金额超过岗位【{1}】每月酬金上限【{2}】元。
	public static final String QGZX_MRGZKH_GWJESX_PL_OUT = "qgzx_mrgzkh_gwjesx_pl_out";//学生【{0}】发放金额超过岗位酬金上限。
	/**
	 * 素质拓展
	 */
	public static final String SZTZ_XMSB_REPEAT = "sztz_xmsb_repeat";//该项目已申报，请重新填写。
	public static final String SZTZ_XMSB_NOTDEL = "sztz_xmsb_notdel";//该项目已被申请，不允许删除。
	public static final String SZTZ_XMSB_SPLC_USED = "sztz_xmsb_splc_used";//该项目已被申请，不允许修改审批流程。
	
	/**
	 * 户籍借用
	 */
	public static final String RCSW_HJJY_WGH = "rcsw_hjjy_wgh";//该项目已申报，请重新填写。

	/**
	 * 新素质拓展学生项目申请，修改提交或者勾选提交时重复判断
	 */
	public static final String XSZTZ_XSXMJG_CFPD = "xsztz_xsxmjg_cfpd";
	public static final String CFTX = "cftx";
	/**
	 * 校外住宿申请
	 */
	public static final String GYGL_XYZS_REPEAT = "gygl_xyzs_repeat";
	
	
	/**
	 * 浙江警察学院学生干部考核结果增加保存时检测结果表中是否已有记录
	 */
	public static final String SZSW_XSGBGL_KHJG_ADD = "szdw_xsgbgl_khjg_add";
	
	//浙江警察学院日常事务诚信档案增加结果保存检测结果表中是否已有记录
	public static final String RCSW_CXDA_ADD = "rcsw_cxda_add";
	
	/**
	 * 工作记录
	 */
	public static final String GZJL_LBGL_LBYCZ = "gzjl_lbgl_lbycz";//该类别代码已存在！
	public static final String GZJL_LBGL_NOTDEL = "gzjl_lbgl_lbysy";//该类别已被使用，不允许删除
	/**
	 * 社团活动
	 */
	public static final String STGL_JCSZ_STLB_REPEAT = "stgl_jcsz_stlb_repeat";
	public static final String STGL_JCSZ_XMLB_REPEAT = "stgl_jcsz_xmlb_repeat";
	public static final String STGL_JCSZ_RYLB_REPEAT = "stgl_jcsz_rylb_repeat";//人员类别重复
	public static final String STGL_JCSZ_RYLB_USED = "stgl_jcsz_rylb_used";//人员类别再用状态
	public static final String STGL_STGL_ST_REPEAT = "stgl_stgl_st_repeat";//该社团已存在，请勿重复申请
	public static final String STGL_STHDDJ_REPEAT = "stgl_sthddj_repeat";//学生【{0}-{1}】时间内，社团活动已登记，请勿重复填写！。
	public static final String STGL_STGL_NOTDEL = "stgl_stgl_notdel";//该社团已有成员，不允许删除。
	public static final String STGL_JCSZ_XMLB_USED = "stgl_jcsz_xmlb_used";
	public static final String STGL_JCSZ_STLB_USED = "stgl_jcsz_stlb_used";
	
	/**
	 * 艺术团管理
	 */
	public static final String YSTGL_JCSZ_YSTLB_REPEAT = "ystgl_jcsz_ystlb_repeat";
	public static final String YSTGL_JCSZ_XMLB_REPEAT = "ystgl_jcsz_xmlb_repeat";
	public static final String YSTGL_JCSZ_GKDW_REPEAT = "ystgl_jcsz_gkdw_repeat";
	public static final String YSTGL_JCSZ_RYLB_REPEAT = "ystgl_jcsz_rylb_repeat";//人员类别重复
	public static final String YSTGL_YSTGL_ST_REPEAT = "ystgl_ystgl_yst_repeat";//该艺术团已存在，请勿重复申请
	
	public static final String YSTGL_YSTGL_NOTDEL = "ystgl_ystgl_notdel";//该艺术团已有成员，不允许删除。
	public static final String YSTGL_JCSZ_XMLB_USED = "ystgl_jcsz_xmlb_used";
	public static final String YSTGL_JCSZ_YSTLB_USED = "ystgl_jcsz_ystlb_used";
	public static final String YSTGL_JCSZ_GKDW_USED = "ystgl_jcsz_gkdw_used";
	/**
	 * 密码找回
	 */
	public static final String XTWH_MMZH_WYHM = "xtwh_mmzh_wyhm";
	public static final String XTWH_MMZH_WMMWT = "xtwh_mmzh_wmmwt";
	public static final String XTWH_MMZH_ZHYZ = "xtwh_mmzh_zhyz";
	public static final String XTWH_MMZH_MMWTYZ= "xtwh_mmzh_mmwtyz";
	public static final String XTWH_MMZH_MMWTYZSB= "xtwh_mmzh_mmwtyzsb";
	public static final String XTWH_MMZH_MMGXCG= "xtwh_mmzh_mmgxcg";
	public static final String XTWH_MMZH_MMGXSB= "xtwh_mmzh_mmgxsb";
	public static final String XTWH_MMZH_MBSZCG= "xtwh_mmzh_mbszcg";
	public static final String XTWH_MMZH_MBSZSB= "xtwh_mmzh_mbszsb";
	
	/**
	 * 考核管理
	 */
	public static final String KHGL_KHDXGL_KHDX_REPEAT = "khgl_khdxgl_khdx_repeat";
	public static final String KHGL_KHDXGL_PFDX_REPEAT = "khgl_khdxgl_pfdx_repeat";
	public static final String KHGL_KHBGL_KHB_REPEAT = "khgl_khbgl_khb_repeat";
	public static final String KHGL_KHXMGL_KHXM_REPEAT = "khgl_khxmgl_khxm_repeat";
	public static final String KHGL_KHDXGL_KHDX_USED = "khgl_khdxgl_khdx_used";
	public static final String KHGL_KHDXGL_PFZ_USED = "khgl_khdxgl_pfz_used";
	public static final String KHGL_KHBGL_KHNR_USED = "khgl_khbgl_khnr_used";
	public static final String KHGL_JGCX_KHDX_NOTBZR = "khgl_jgcx_khdx_notbzr";
	public static final String KHGL_KHPF_FSBC_FAIL = "khgl_khpf_fsbc_fail";
	
	/**
	 * 站内信管理
	 */
	public static final String ZNXGL_XXFSCG = "znxgl_xxfscg";
	public static final String ZNXGL_XXFSSB = "znxgl_xxfssb";
	public static final String ZNXGL_XJCKSB = "znxgl_xjcksb";
	public static final String ZNXGL_XJFBSB = "znxgl_xjfpsb";
	public static final String ZNXGL_XJFBCG = "znxgl_xjfpcg";
	public static final String ZNXGL_XXHFCG = "znxgl_xxhfcg";
	public static final String ZNXGL_XXHFSB = "znxgl_xxhfsb";
	
	/**
	 * 分班编学号
	 */
	public static final String FBGL_FBGZMC_USED = "fbgl_fbgzmc_used";//分班规则名称已存在
	
	
	/**
	 * 晨跑分维护
	 */
	public static final String PJPY_CPFWH_REPEAT = "pjpy_cpfwh_repeat";//该学生该学期已申请
	/**
	 * 晨跑分维护（导入验证）
	 */
	public static final String PJPY_CPFWH_YANZHEN = "pjpy_cpfwh_yanzhen";//导入学年学期学号分数不能为空
	/**
	 * 晨跑分维护（导入验证）
	 */
	public static final String PJPY_CPFWH_XHNULL = "pjpy_cpfwh_xhnull";//导入验证学号是否存在

	/**
	 * 梧州学院缓交学费申请
	 */
	public static final String XSZZ_HJXF_REPEATED = "xszz_hjxf_repeated";
	
	/**
	 * 北京中医药（学情代码维护）
	 */
	public static final String RCSW_XQDMWH_REPEATED = "rcsw_xqdmwh_repeated";//该学情代码或学情名称已存在
	
	public static final String RCSW_XQDMWH_SQJGEXIST = "rcsw_xqdmwh_sqjgexist";//该学情代码已使用，不能修改或删除
	
	public static final String RCSW_TSQKTB_REPEATED = "rcsw_tsqktb_repeated";//该学生当天已通报
	
	/**
	 * 假期返校维护
	 */
	public static final String JQFXGL_DMWH_REPEAT = "jqfxgl_dmwh_repeat";//返校代码或者类别名称已存在！
	public static final String JQFXGL_JQFXWH_REPEAT = "jqfxgl_jqfxwh_repeat";//在该学年该学期已有记录，请勿重复填写！
	/**
	 * 学生处学情月报
	 */
	public static final String RCSW_XSCXQYB_REPEAT = "rcsw_xscxqyb_repeat";//本月已有学情月报记录，请勿重新填写！
	
	/**
	 * 院级学情月报
	 */
	public static final String RCSW_YXYBGLSQ_REPEATED = "rcsw_yxybglsq_repeated";//本月已有学情月报记录，请勿重新填写！
	
	

	
	/**
	 * 学生工作周报
	 */
	public static final String RCSW_XSGZZB_WJLX_REPEAT = "rcsw_xsgzzb_wjlx_repeat";
	public static final String RCSW_XSGZZB_WJLX_USED = "rcsw_xsgzzb_wjlx_used";
	
	

	/**
	 * 专业奖认定等级
	 */
	public static final String PJPY_ZYJGL_DMWH_REPEAT = "pjpy_zyjgl_dmwh_repeat"; //专业奖认定等级名称已存在
	public static final String PJPY_ZYJGL_DMWH_USED = "pjpy_zyjgl_dmwh_used"; //专业奖认定等级已使用，不能修改或删除!
	
	/**
	 * 团委数据
	 */
	public static final String 	RCSWX_SJGL_TWSJ_REPEAT = "rcswx_sjgl_twsj_repaet";
	
	/**
	 * 后勤数据
	 */
	public static final String 	RCSWX_SJGL_HQSJ_REPEAT = "rcswx_sjgl_hqsj_repaet";

	
	/**
	 * 传媒卫生分
	 */
	public static final String ZJCM_WSJC_PFZ_REPEAT = "zjcm_wsjc_pfz_repeat";	//评分组名称已存在
	public static final String ZJCM_WSJC_PFZ_DFGZYSY = "zjcm_wsjc_pfz_dfgzysy";	//打分规则已使用
	public static final String SYS_YXSX_OVER  = "sys_yxsx_over";
	public static final String ZJCM_WSJC_TJ_WTJ = "zjcm_wsjc_tj_wtj"; //查询失败存在未提交寝室
	
	/**
	 * 北京中医药大学
	 */
	public static final String SZDW_JTFF_RYWH_REPEAT ="szdw_jtff_rywh_repeat";
	
	/**
	 * 走转读管理验证（金陵科技）
	 */
	public static final String GYGL_ZZDGL_REPEAT = "gygl_zzdgl_repeat";//该学生该学年已申请
	
	/**
	 * 学生信息为报到注册
	 */
	public static final String XSXX_WBDZC_WBDLBMC = "xsxx_wbdzc_wbdlbmc"; //该未报到注册类型名称已存在
	public static final String XSXX_WBDZC_UPDATE = "xsxx_wbdzc_update"; //类别名称已被使用不能修改
	public static final String XSXX_CXDD_REPEAT =  "xsxx_cxdd_repeat";//操行等第结果重复
	/**
	 * 生源地贷款
	 */
	public static final String ZXDK_SYDDK = "zxdk_syddk"; //该学生在该学年已经有贷款记录！
	
	/**
	 * 临客住宿登记（衢州学院）
	 */
	public static final String GYGL_LKXX_REPEAT = "gygl_lkxx_repeat";//该身份证号当天已登记住宿，请勿重复登记！
	
	/**
	 * 公共物品管理（衢州学院）
	 */
	public static final String GYGL_GGWPJYGL_WGH = "gygl_ggwpjygl_wgh";//该学生有公共物品未归还，请先归还，再借取！
	
	/**
	 * 集体评奖申请重复提示
	 */
	public static final String PJPY_JTPJ_SQ_REPEAT = "pjpy_jtpj_sq_repeat";//同一评奖集体在同一评奖周期不能重复申请同一奖项！
	public static final String PJPY_JTPJ_JXMC_REPEAT = "pjpy_jtpj_jxmc_repeat";//奖项名称已存在！
	public static final String PJPY_JTPJ_JXMC_DEL = "pjpy_jtpj_jxmc_del";//【{0}】已被使用，不能删除！
	
	/**
	 * 困难生认定申请理由维护（西北民族大学）
	 */
	public static final String XSZZ_KNSRD_SQLYWH_REPEAT ="xszz_knsrd_sqlywh_repeat"; //此申请理由已经存在，不能重复添加！
	
	/** 
	 * 浙江旅游综合分 
	 */ 
	public static final String ZJLY_ZHF_SQ_REPEAT = "zjly_zhf_sq_repeat";
	public static final String ZJLY_ZHF_XMWH_XMMK_REPEAT = "zjly_zhf_xmwh_xmmk_repeat";
	public static final String ZJLY_ZHF_XMWH_XMMK_EXIST = "zjly_zhf_xmwh_xmmk_exist";
	public static final String ZJLY_ZHF_XMWH_JFXM_REPEAT = "zjly_zhf_xmwh_jfxm_repeat";
	public static final String ZJLY_ZHF_XMWH_JFXM_JDSZ_ERROR = "zjly_zhf_xmwh_jfxm_jdsz_error";
	public static final String XG_WSBZ_YYRQ_EXIST = "xg_wsbz_yyrq_exist";
	
	/**
	 * 素质拓展改3.0版提示
	 */
	public static final String SZTZ_NEW_TTXM_MCREPEAT = "sztz_new_ttxm_mcrepeat";
	
	
	
	/** 
	 * 卫生检查_项目名称重复 
	 */ 
	public static final String GYGL_WSJC_JCXM_REPEAT = "gygl_wsjc_jcxm_repeat";
	
	/**
	 * 上海戏剧学院    评奖评优-其他奖项设置-取消失败
	 */
	public static final String   PJPY_QTSZ_QXSB = "pjpy_qtsz_qxsb";
	/**
	 * 上海戏剧学院    评奖评优-其他奖项设置-取消成功
	 */
	public static final String   PJPY_QTSZ_QXCG = "pjpy_qtsz_qxcg";
	
	/**
	 * 北京中医药大学    心理咨询-心理咨询管理-已存在
	 */
	public static final String  XLZX_ZXZXJL_REPEAT = "xlzx_zxzxjl_repeat";
	
	/**
	 * 党团建设-组织关系转出-毕业生党支部维护-已存在
	 */
	public static final String DTJS_ZZGXZC_DZBWH_EXIST = "dtjs_zzgxzc_dzbwh_exist";
	
	/**
	 * 党团建设-组织关系转出-毕业生党支部维护-被使用
	 */
	public static final String DTJS_ZZGXZC_DZBWH_USED = "dtjs_zzgxzc_dzbwh_used";
	
	/**
	 * 党团建设-组织关系转出-信息结果-学生已存在记录
	 */
	public static final String DTJS_ZZGXZC_XXJG_EXIST = "dtjs_zzgxzc_xxjg_exist";
	
	/**
	 * 党团建设-组织关系转出-信息结果-介绍信编号重复使用
	 */
	public static final String DTJS_ZZGXZC_XXJG_JSXBH_REPEAT = "dtjs_zzgxzc_xxjg_jsxbh_repeat";
	
	/**
	 * 通用代码维护提示语
	 */
	public static final String SYS_DMWH_DM_EXISTS = "sys_dmwh_dm_exists";//代码已存在，请确认！
	public static final String SYS_DMWH_MC_EXISTS = "sys_dmwh_mc_exists";//名称已存在，请确认！
	public static final String SYS_DMWH_USERD_NOTDEL = "sys_dmwh_userd_notdel";//代码已被使用，不允许删除！
	/**
	 * 通用导入提示语
	 */
	public static final String SYS_DR_FAIL = "sys_dr_fail";
	public static final String SYS_DR_DRCGBZ = "sys_dr_drcgbz";
	public static final String SYS_DR_DRSBBZ = "sys_dr_drsbbz";
	public static final String SYS_DR_KBG = "sys_dr_kbg";
	public static final String SYS_DR_KFILE = "sys_dr_kfile";
	public static final String SYS_DR_FORMATER = "sys_dr_formater";
	public static final String SYS_DR_EXCELREPEAT = "sys_dr_excelrepeat";
	public static final String SYS_DR_DRBFSB = "sys_dr_drbfsb";
	public static final String XG_XLZX_PB_ADD_REPEAT = "xg_xlzx_pb_add_repeat";
	public static final String XG_XLZX_ADD_PBDATE_CAN_NOTNULL  = "xg_xlzx_add_pbdae_can_notnull";
	
	/**
	 * 金华职业文明寝室提示语
	 */
	public static final String XG_GYJC_PFBZ_REPEAT = "xg_gyjc_pfbz_repeat";
	public static final String XG_GYJC_PFBZ_XH_REPEAT = "xg_gyjc_pfbz_xh_repeat";
	public static final String XG_GYJC_PFBZ_USED = "xg_gyjc_pfbz_used";
	public static final String XG_GYJC_PFBZ_CZZXM = "xg_gyjc_pfbz_czzxm";
	public static final String XG_GYJC_DEL_TJ_TS = "xg_gyjc_del_tj_ts";
	public static final String XG_GYJC_DEL_LASTNEW_TS = "xg_gyjc_del_lastnew_ts";
	public static final String XG_GYJC_DEL_RQ_REPEAT_TS = "xg_gyjc_del_rq_repeat_ts";
	public static final String XG_GYJC_FJ_BT = "xg_gyjc_fj_bt";
	public static final String XG_GYJC_CCJG_REPEAT = "xg_gyjc_ccjg_repeat";
	public static final String XG_GYJC_FJSC_PHOTO_SIZE_LIM = "xg_gyjc_fjsc_photo_size_lim";
	public static final String XG_GYJC_JCRC_NO_DATA = "xg_gyjc_jcrc_no_data";
	
	public static final String XG_SZDW_BFJS_REPEAT = "xg_szdw_bfjs_repeat";
	
	/**
	 * 上海戏剧无附件导出
	 */
	public static final String PJPY_SHXJ_NOATTACHMENT = "pjpy_shxj_noattachment";
	/**
	 * 浙江大学纪实考评
	 */
	public static final String XG_ZJDX_JSKP_REPEAT = "xg_zjdx_jskp_repeat";
	public static final String XG_ZJDX_JSKP_XMCX_CANCEL_CX ="xg_zjdx_jskp_xmcx_cancel_cx";
	public static final String XG_JSKP_JGZQ_REPEAT ="xg_jskp_jgzq_repeat";
	
	/** 
	 * 金华职业
	 */ 
	public static final String JHZY_DEKT_YBM = "jhzy_dekt_ybm";
	public static final String JHZY_DEKT_BM_SUCCESS = "jhzy_dekt_bm_success";
	public static final String JHZT_DEKT_BM_OVERSIZE = "jhzy_dekt_bm_oversize";

	

	
	
	/**
	 *青岛滨海
	 */
	public static final String XG_GYPY_SQ_REPEAT = "xg_gypy_sq_repeat";

	
	/**
	 * 黄冈师范
	 */
	public static final String XG_XLZX_SB_REPEAT = "xg_xlzx_sb_repeat";

	/**
	 * 家长用户注册
	 */
	public static final String YHZC_SUCCESS = "yhzc_success";
	public static final String YHZC_FAILED = "yhzc_failed";
	public static final String YHZC_SJHM_REPEAT = "yhzc_sjhm_repeat";
	public static final String YHZC_CHILDRENINFO_ERROR = "yhzc_childreninfo_error";
	public static final String YHZC_CHILDRENINFO_ASSOCIATED = "yhzc_childreninfo_associated";

	/**
	 * 西安科技大学
	 */
	public static final String XG_DTJS_TSDZB_REPEAT = "xg_dtjs_tsdzb_repeat";
	
	/**
	 * 火车站点 
	 */
	public static final String XG_HCZD_REPEAT = "xg_hczd_repeat";
	public static final String SYS_TRAN_BUSY = "sys_tran_busy";
	
	//天津市经济贸易学校  移动卫生分录入覆盖判断提示语
	public static final String XG_RCSW_WSFLR_FG = "xg_rcsw_wsflr_fg";
	
	//通过人数已超过(西安交通大学)
	public static final String XG_HDGL_PEOPLE_BEYOND = "xg_hdgl_people_beyond";

	/**
	 * 活动管理，专家团成员设置
	 */
	public static final String SYS_FP_FAIL = "sys_fp_fail"; //分配失败
	public static final String SYS_FP_SUCCESS = "sys_fp_success";  //分配成功

	/**
	 *  安徽农业 学生工作情况报表
	 */
    public static final String RCSW_XSGZQKBB_ZBB_ZCREPEAT = "rcsw_xsgzqkbb_zbb_zcrepeat";	//周报表：同学年学期同学院报表周次重复
	public static final String RCSW_XSGZQKBB_YBB_XYYFREPEAT = "rcsw_xsgzqkbb_ybb_xyyfrepeat";	//学院月报表：同学年学期同学院报表月份重复
    public static final String RCSW_XSGZQKBB_YBB_BJYFREPEAT = "rcsw_xsgzqkbb_ybb_bjyfrepeat";	//班级月报表：学院月报中该班级数据已存在
	public static final String RCSW_XSGZQKBB_YBB_BJYBBDATAEXISTS = "rcsw_xsgzqkbb_ybb_bjybbdataexists";	//学院月报表：存在班级月报表数据
	
	/*团委管理*/
	public static final String TYGL_TZZGL_REPEAT = "tygl_tzzgl_repeat";
	public static final String TYGL_TGBGL_EXIST = "tygl_tgbgl_exist";
	
	/**
	 * 浙江大学(10335)个性化
	 */
	public static final String PJPY_YPZL_REPEAT = "pjpy_ypzl_repeat";//该学生该学年已申请
	public static final String PJPY_YPZL_REPEAT_HSD = "pjpy_ypzl_repeat_hsd";//不能在当天重复提交记录
	public static final String PJPY_WDPJ_JXRYSH_FAIL = "pjpy_wdpj_jxrysh_fail";//评奖评优_我的评奖_奖项荣誉审核_失败，【{0}】本学院(园)5项标兵获得总人次已超过学院参评总人数35%，请确认！
	public static final String ZXDK_WXDKSQ_REPEATED = "zxdk_wxdksq_repeated";//无息贷款申请
	public static final String ZXDK_YSJXJ_ERROR = "zxdk_ysjxj_error";//院设奖学金
	public static final String ZXDK_JKHK_REPEAT = "zxdk_jkhk_repeat";//助学贷款借款还款-该学生已申请
	public static final String XSZY_ZGH_REPEAT = "xszy_zgh_repeat";//新生之友-该职工号已存在。
}
	
	
							   

