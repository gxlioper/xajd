package com.zfsoft.xgxt.base.message;

/**
 * <p>��Ϣ��Ŷ���</p>
 * <p>��message.properties��keyƥ��</p>
 * @author qph
 * ���ڣ�2013-4-16
 */
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-11-3 ����06:33:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public interface MessageKey {

	/*
	 * ϵͳ��Ϣ���
   * һ����ģ����������ѧ��������XSZZ_ ��ͷ
	 */
	public static final String SYS_TOKEN_VALID = "sys_token_valid";//�ظ��ύ
	
	public static final String SYS_OPERATE_FAIL = "sys_operate_fail";//����ʧ��
	public static final String SYS_OPERATE_SUCCESS = "sys_operate_success";//�����ɹ�
	public static final String SYS_AUTH_FAIL = "sys_auth_fail";
	public static final String SYS_TB_SUCCESS = "sys_tb_success";//ͬ���ɹ�
	public static final String SYS_TB_FAIL = "sys_tb_fail";//ͬ��ʧ��
	public static final String SYS_SAVE_SUCCESS = "sys_save_success";//����ɹ�
	public static final String SYS_SAVE_FAIL = "sys_save_fail";//����ʧ��
	public static final String SYS_ZX_SUCCESS = "sys_zx_success";//ע���ɹ�
	public static final String SYS_XZ_FAIL = "sys_zx_fail";//ע��ʧ��
	public static final String SYS_SAVE_FILESIZE_OUT = "sys_save_filesize_out";//�ļ���С����
	public static final String SYS_SUBMIT_SUCCESS = "sys_submit_success";//�ύ�ɹ�
	public static final String SYS_SUBMIT_SUCCESS_NUM = "sys_submit_success_num";//�ύ�ɹ�{0}��
	public static final String SYS_SUBMIT_FAIL = "sys_submit_fail";//�ύʧ��
	public static final String SYS_DEL_SUCCESS  = "sys_del_success";//ɾ���ɹ�
	public static final String SYS_DEL_FAIL = "sys_del_fail";//ɾ��ʧ��
	public static final String SYS_DEL_NUM = "sys_del_num";//�ɹ�ɾ��{0}��
	public static final String SYS_JC_FAIL = "sys_jc_fail";//���ʧ��
	public static final String SYS_JC_NUM = "sys_jc_num";//�ɹ����{0}��
	public static final String SYS_SAVE_DM_REPEAT = "sys_save_dm_repeat";//�������
	public static final String SYS_DEL_NULL = "sys_del_null";//��ѡ����Ҫɾ���ļ�¼��
	public static final String SYS_AUD_SUCCESS = "sys_aud_success";//��˳ɹ�
	public static final String SYS_AUD_FAIL = "sys_aud_fail";//���ʧ��
	public static final String SYS_AUD_ERROR = "sys_aud_error";//���ʧ��
	public static final String SYS_AUD_DOUBLE = "sys_aud_double"; //�ظ��ύ
	
	public static final String SYS_SFQY_Y_FAIL = "sys_sfqy_y_fail";//����ʧ��
	public static final String SYS_SFQY_Y_NUM = "sys_sfqy_y_num";//�ɹ�����{0}��
	public static final String SYS_SFQY_Y_NULL = "sys_sfqy_y_null";//��ѡ����Ҫ���õļ�¼��
	public static final String SYS_SFQY_N_FAIL = "sys_sfqy_n_fail";//ͣ��ʧ��
	public static final String SYS_SFQY_N_NUM = "sys_sfqy_n_num";//�ɹ�ͣ��{0}��
	public static final String SYS_SFQY_N_NULL = "sys_sfqy_n_null";//��ѡ����Ҫͣ�õļ�¼��
	
	public static final String SYS_CANCEL_SUCCESS = "sys_cancel_success";//�����ɹ�
	public static final String SYS_CANCEL_SUCCESS_NUM = "sys_cancel_success_num";//�ɹ�����{0}��,ʧ�ܳ���{1}��
	public static final String SYS_AUD_CANCEL_FAIL = "sys_aud_cancel_fail";
	public static final String SYS_CANCEL_FAIL = "sys_cancel_fail";//����ʧ��
	public static final String SYS_CANCEL_NULL = "sys_cancel_null";//��ѡ����Ҫ�����ļ�¼��
	public static final String SYS_CANCEL_END= "sys_cancel_end";
	public static final String SYS_INIT_SUCCESS= "sys_init_success";//��ʼ���ɹ�
	public static final String SYS_INIT_FAIL= "sys_init_fail";//��ʼ��ʧ��
	public static final String SYS_SELECT_SHLC_FAIL= "sys_select_shlc_fail";//�����趨��������
	public static final String SYS_AUD_PERS_OUT = "sys_aud_pers_out";//ѡ��������ѳ���ָ��ʣ������
	public static final String DATA_OVERLAP = "data_overlap";//��ֹ�����ص�
	public static final String SYS_COPY_SUCCESS = "sys_copy_success";//���Ƴɹ�
	public static final String SYS_COPY_FAIL = "sys_copy_fail";//����ʧ��
	public static final String SYS_SAVE_STUDENT = "sys_save_student";//��ѧ����ͬʱ�䡢��ͬǨ��״̬��¼�Ѵ��ڣ���ȷ�ϣ�

	public static final String SYS_GYGL_SSPYCF = "sys_gygl_sspycf";//�������������ظ�
	public static final String SYS_GYGL_WRZ = "sys_gygl_wrz";//ѧ��δ��ס
	public static final String SYS_CANCEL_TH= "sys_cancel_th";//�Ѿ��˻ص���˲����ٴγ���
	public static final String SYS_CANCEL_Qx= "sys_cancel_qx";//�Ѿ�ȡ���ļ�¼���ܳ���
	
	public static final String SYS_BACK_FAIL = "sys_back_fail";//�˻�ʧ��
	public static final String SYS_PLSH_FAIL = "sys_plsh_fail";//�������ʧ��
	public static final String SYS_CANSQ_FAIL = "sys_cansq_fail";//ȡ������ʧ�� 
	public static final String SYS_CANSQ_SUCCESS = "sys_cansq_success";//ȡ������ɹ� 
	public static final String SYS_PLBC_FAIL = "sys_plbc_fail";//��������ʧ��
	public static final String SYS_CREATE_IMPORT_TEMPLATE_FAIL = "sys_createImportTemplate_fail";
	public static final String SYS_IMPORT_UNLAWFUL_TEMPLATE = "sys_import_unlawful_template";
	public static final String SYS_IMPORT_SUCCESS = "sys_import_success";
	public static final String SYS_SYNC_SUCCESS = "sys_sync_success";
	public static final String SYS_SYNC_FAIL = "sys_sync_fail";
	
	public static final String ZYSZPJ_CANNOT_ADD="zyszpj_cannot_add";//��������ְҵ��������
	
	public static final String SYS_DELDM_EXIST_FAIL = "sys_deldm_exist_fail";//�����Ѿ���ʹ�ã��������޸�
	public static final String SYS_SAVE_UPLOAD_FAIL = "sys_save_upload_fail";//�ϴ�����ʧ��
	
	
	
	/*
	 * �쳣��Ϣ���
	 */
	public static final String EXP_SYS_ERROR = "exp_sys_error";
	
	/*
	 * ˼������
	 */
	public static final String SZDW_REPEAT_ERROR = "szdw_repeat_error";//�ظ�¼��
	public static final String SZDW_FYDPX_XMWKF = "szdw_fydpx_xmwkf" ; //��ǰ������Ŀδ�������룡
	
	/*
	 * �ճ�����
	 */
	public static final String RCSW_REPEAT_ERROR = "rcsw_repeat_error";//�ظ�¼��
	
	public static final String RCSW_KQGL_KQSH_BZCK = "rcsw_kqgl_kqsh_bzck"; //{0}�ڳ�����˽׶Σ������˻ز�����
	public static final String RCSW_KQGL_KQSH_PLSH = "rcsw_kqgl_kqsh_plsh"; //{0}�Ѿ��������û���ˣ�
	
	/*
	 * ѧ����Ϣ
	 */
	public static final String XSXX_TDJS_FAIL = "sys_tdjs_fail";
	public static final String XSXX_TDJS_XHNULL = "sys_tdjs_xhnull";
	public static final String XSXX_TDJS_DEL_FAIL = "xsxx_tdjs_del_fail";
	public static final String XSXX_DAXX_REPEAT_ERROR = "xsxx_daxx_repeat_error";//�ظ�¼��
	public static final String XSXX_ZYFWSQ_REPEAT = "xsxx_zyfwsq_repeat";//��ѧ����{0}-{1}��ʱ���ڣ�־Ը�������еǼǣ���
	/*
	 * ����������
	 */
	public static final String XSZZ_KNSDC_REPEAT = "xszz_knsdc_repeat";
	public static final String XSZZ_KNSDC_EXIST_KNSJG_DELETE = "xszz_knsdc_exist_knsjg_delete";
	public static final String XSZZ_KNSDC_EXIST_KNSSH_DELETE = "xszz_knsdc_exist_knssh_delete";
	
	/*
	 * ����ԭ��
	 */
	public static final String XSZZ_KNYY_REPEAT = "xszz_knyy_repeat";
	public static final String XSZZ_KNYY_USED = "xszz_knyy_used";
	
	public static final String XSZZ_KNSDC_EXIST_KNSJG_UPDATE = "xszz_knsdc_exist_knsjg_update";
	public static final String XSZZ_KNSDC_EXIST_KNSSH_UPDATE = "xszz_knsdc_exist_knssh_update";
	
	/*
	 * ���������
	 */
	public static final String XSZZ_KNSJG_RESULT_REPEAT = "xszz_knsjg_result_repeat";

	public static final String XSZZ_KNSJG_RESULT_REPEAT_XN = "xszz_knsjg_result_repeat_xn";

	public static final String XSZZ_KNSJG_RESULT_REPEAT_XQ = "xszz_knsjg_result_repeat_xq";
	
	/*
	 * ��������������
	 */
	public static final String XSZZ_KNSJCSZ_SHLC_EXIST = "xszz_knsjcsz_shlc_exist";
	
	
	/*
	 * ѧ���춯
	 */
	
	
	public static final String XJYD_XMLBMC_EXIST = "general_mcexist";						//��{0}���Ѵ���
	public static final String XYJD_UPDATE_DMEXIST = "general_update_dmexist";			  	//��{0}���ѱ�ʹ�ã������޸ģ�
	public static final String XYJD_DELETE_DMEXIST = "general_delete_dmexist";				//��{0}���ѱ�ʹ�ã�����ɾ����
	
	/*
	 * ѧ������
	 */		
	public static final String SYS_AUD_FAIL_XSZZ = "sys_aud_fail_xszz"; // ���ʧ�ܣ��������¼�Ѿ��������û���ˣ����ѧ���ѻ�ø���Ŀ��
	public static final String EXP_XSZZ_RSKZFW_NULL = "exp_xszz_rskzfw_null";//ѧ���������������Ʒ�ΧΪ��
	
	public static final String XSZZ_XMLBMC_EXIST = "xszz_xmlbmc_exist";//ѧ����������������Ѵ��ڣ�
	public static final String XSZZ_XMMC_EXIST = "xszz_xmmc_exist";//ѧ����������Ŀ�����Ѵ��ڣ�
	public static final String XSZZ_XMLB_NOTUPDATE = "xszz_xmlb_notupdate";//ѧ����������������������Ŀ���������޸ģ�
	public static final String XSZZ_XMLB_NOTDEL = "xszz_xmlb_notdel";//ѧ����������������������Ŀ��������ɾ����
	public static final String XSZZ_XM_NOTUPDATE = "xszz_xm_notupdate";//ѧ������������Ŀ�ѱ����룬�������޸ģ�
	public static final String XSZZ_XM_NOTDEL = "xszz_xm_notdel";//ѧ����������Ŀ�ѱ����룬������ɾ����
	public static final String RSKZ_FAIL ="rskz_fail";//���������ѵ��������������ˣ�
	public static final String RSKZ_FAIL_CANCEL ="rskz_fail_cancel";//���������ѵ������������������
	public static final String XSZZ_BBDY_FAIL ="xszz_bbdy_fail";//�����ӡ������δ���ã�����Ŀ��δ���ñ�������ϵ����Ա��
	public static final String PJPY_BBDY_FAIL ="pjpy_bbdy_fail";//�����ӡ������δ���ã�����Ŀ��δ���ñ�������ϵ����Ա��
	public static final String XSZZ_RSSZ_ZZRSYZ ="xszz_rssz_zzrsyz";//ѧ���������������ã�����������ͨ����ѧ����������У����ʾ
	public static final String XSZZ_RSSZ_ZZRSTS ="xszz_rssz_zzrsts";//ѧ���������������ã�����������ͨ����ѧ����������У����ʾ
	public static final String PJPY_FAIL="pjpy_fail"; // ����Ŀ����һ����¼��������л���ͨ����
	
	public static final String RCSW_SYBX_RESULT_REPEAT ="rcsw_sybx_result_repeat";//�ճ�������ҵ���գ���ѧԱ�ڱ�ѧ��������ҵ������Ϣ���ڣ�
	public static final String XSZZ_JEKZ_FAIL = "xszz_jekz_fail";
	public static final String XSZZ_JEKZ_TEXT_FAIL = "xszz_jekz_text_fail";
	public static final String XSZZ_BJPY_XZRY_FAIL = "xszz_bjpy_xzry_fail";
	public static final String XSZZ_BJPYXZSZ_XZRSXX_FAIL = "xszz_bjpyxzsz_xzrsxx_fail";
	public static final String XSZZ_BJPYXZSZ_XZRSXX_XSRS_FAIL = "xszz_bjpyxzsz_xzrsxx_xsrs_fail";
	
	public static final String XSZZ_ZZKFF_XMMC_REPEAT = "xszz_zzkff_xmmc_repeat";	//ͬһ��ѧ������ͬһ��ѧ��ѧ�ڡ����ܷ���ͬ��Ŀ���Ƶ�������
	/*
	 * ��������
	 */
	
	public static final String XSZZ_ZZDY_REPEAT = "xszz_zzdy_repeat";
	

	/*
	 * ��������
	 */	
	public static final String PJPY_CPXZCSH_NOTALLOW = "pjpy_cpxzcsh_notAllow";//����С���ʼ����������
	public static final String PJPY_CPXZCSH_QXBZ = "pjpy_cpxzcsh_qxbz";//����С���ʼ��Ȩ�޲���
	public static final String SYS_QXCP_FAIL = "sys_qx_fail"; //ȡ������ʧ��
	public static final String SYS_QXCP_SUCCESS = "sys_qx_success";  //ȡ�������ɹ�
	public static final String SYS_TZ_FAIL = "sys_tz_fail"; //����ʧ��_��������ȷ��
	public static final String SYS_TZ_SUCCESS = "sys_tz_success";  //�����ɹ�_��������ȷ��
	public static final String PJPY_IMPORT_ZCFS_FAIL = "pjpy_import_zcfs_fail";//��Ȩ�����۲��
	public static final String PJPY_IMPORT_ZCFS_XHXM_NULL = "pjpy_import_zcfs_xhxm_null";//�����۲��ѧ�š����� Ϊ��
	public static final String PJPY_IMPORT_ZCFS_NULL = "pjpy_import_zcfs_null";//�����۲�� Ϊ��
	public static final String PJPY_IMPORT_ZCFS_NOTNUMBER = "pjpy_import_zcfs_notnuber";//�����۲�ֱ���Ϊ����
	public static final String PJPY_IMPORT_ZCFS_MORETHEN_MAXLENGTH = "pjpy_import_zcfs_morethen_maxlength";//������󳤶�
	public static final String PJPY_IMPORT_ZCFS_MORETHEN_ZDZX = "pjpy_import_zcfs_zdzx";//������󳤶�
	public static final String PJPY_IMPORT_ZCFS_MORETHEN_ZDDJ = "pjpy_import_zcfs_morethen_zddj"; //�����ȼ�������
	//��羺��
	public static final String PJPY_IMPORT_JSFS_FAIL = "pjpy_import_jsfs_fail";//��Ȩ���뾺���ַ�
	public static final String PJPY_IMPORT_JSFS_BJDM_NULL = "pjpy_import_jsfs_bjdm_null";//���뾺���ַ�BJDM Ϊ��
	public static final String PJPY_IMPORT_JSFS_NULL = "pjpy_import_jsfs_null";//���뾺���ַ� Ϊ��
	public static final String PJPY_IMPORT_JSFS_NOTNUMBER = "pjpy_import_jsfs_notnuber";//���뾺���ֱַ���Ϊ����
	public static final String PJPY_IMPORT_JSFS_MORETHEN_MAXLENGTH = "pjpy_import_jsfs_morethen_maxlength";//������󳤶�
	public static final String PJPY_IMPORT_JSFS_MORETHEN_ZDZX = "pjpy_import_jsfs_zdzx";//������󳤶�
	public static final String PJPY_IMPORT_JSFS_MORETHEN_ZDDJ = "pjpy_import_jsfs_morethen_zddj"; //�����ȼ�������
	public static final String PJPY_AUDING_QXBZ = "pjpy_auding_qxbz";
	public static final String PJPY_XMLX_LXEXIST = "pjpy_xmlx_lxexist";  //��Ŀ���������Ѵ���
	public static final String PJPY_XMXZ_XZEXIST = "pjpy_xmxz_xzexist";	 //��Ŀ���������Ѵ���
	public static final String PJPY_BJDM_DMEXIST = "pjpy_bjdm_dmexist";	 //�༶���������Ѵ���
	public static final String PJPY_DMWH_EXIST_PJJG_UPDATE = "pjpy_dmwh_exist_pjjg_update";  //�����������ʹ�ã������޸ġ�
	public static final String PJPY_DMWH_EXIST_PJJG_DELETE = "pjpy_dmwh_exist_pjjg_delete";	 //�����������ʹ�ã�����ɾ����
	public static final String PJPY_DMWH_EXIST_PJXM_UPDATE = "pjpy_dmwh_exist_pjxm_update";  //����������Ŀʹ�ã������޸ġ�
	public static final String PJPY_DMWH_EXIST_PJXM_DELETE = "pjpy_dmwh_exist_pjxm_delete";	 //����������Ŀʹ�ã�����ɾ����
	public static final String PJPY_BJDM_EXIST_BJDL_UPDATE = "pjpy_bjdm_exist_bjdl_update";  //�����ѱ�ʹ�ã������޸ġ�
	public static final String PJPY_BJDM_EXIST_BJDL_DELETE = "pjpy_bjdm_exist_bjdl_delete";  //�����ѱ�ʹ�ã�ɾ����
	public static final String PJPY_TSXSDM_NAME_REPEAT = "pjpy_tsxsdm_name_repeat";	//���������Ѵ���
	public static final String PJPY_TSXSDM_EXIST_TSXSB_UPDATE = "pjpy_tsxsdm_exist_tsxsb_update"; //�����ѱ�ʹ�ò����޸�
	public static final String PJPY_TSXSDM_EXIST_TSXSB_DELETE = "pjpy_tsxsdm_exist_tsxsb_delete"; //�����ѱ�ʹ�ò���ɾ��
	
	//�������ţ�����Ϣ
	public static final String PJPY_XWHJ_DMWH_EXIST_SQ = "pjpy_xwhj_dmwh_exist_sq";  //����ѧ�����룬����ɾ��
	public static final String PJPY_XWHJ_DMWH_EXIST_JG = "pjpy_xwhj_dmwh_exist_jg";  //���н��ʹ�ã�����ɾ��
	public static final String PJPY_XWHJ_DMWH_EXIST_JXDJ = "pjpy_xwhj_dmwh_exist_jxdj";  //���н���ȼ�ʹ�ã�����ɾ��
	public static final String PJPY_XWHJ_DMWH_EXIST_JXMC = "pjpy_xwhj_dmwh_exist_jxmc";  //���н�������ʹ�ã�����ɾ��
	
	//�������ţ���Ŀά��
	public static final String PJPY_XMMC_EXIST = "pjpy_xmmc_exist";//�������ţ���Ŀ�����Ѵ��ڣ�
	public static final String PJPY_XM_NOTUPDATE = "pjpy_xm_notupdate";//�������ţ�����Ŀ�ѱ����룬�������޸ģ�
	public static final String PJPY_XM_NOTDEL = "pjpy_xm_notdel";//�������ţ���Ŀ�ѱ����룬������ɾ����
	public static final String PJPY_RSKZ_FAIL ="pjpy_rskz_fail";//���������ѵ����������
	public static final String PJPY_RSSZ_ZZRSYZ ="pjpy_rssz_zzrsyz";//�������ţ��������ã�����������ͨ����ѧ����������У����ʾ
	public static final String PJPY_RSSZ_ZZRSTS ="pjpy_rssz_zzrsts";//�������ţ��������ã�����������ͨ����ѧ����������У����ʾ
	public static final String PJPY_RSSZ_ZZRSTS_XX ="pjpy_rssz_zzrsts_xx";//�������ţ��������ã�����������ͨ����ѧ����������У����ʾ
	public static final String PJPY_JXFZ_NOTJL ="pjpy_jxfz_notjl";//�������ţ�����ƣ��޿ɸ��ƵĽ���
	public static final String PJPY_JXFZ_SUCCESS ="pjpy_jxfz_success";//�������ţ�����ƣ����Ƴɹ�
	public static final String PJPY_XSXH_EXIST ="pjpy_xsxh_exist";//�������ţ���Ŀ����Ѵ��ڣ�
	public static final String SYS_SAVE_REPEATING = "sys_save_repeating";//���۲���Ŀ�����ظ������ݴ��ڣ�
	
	/**
	 * �ճ�����-�ճ���Ϊά�� 
	 */
	public static final String RCSW_RCXWWH_XWDLCZ = "rcsw_rcxwwh_xwdlcz";//����Ϊ��������Ѵ��ڣ�
	public static final String RCSW_RCXWWH_XWLBCZ = "rcsw_rcxwwh_xwlbcz";//����Ϊ�������Ѵ��ڣ�
	public static final String RCSW_RCXWWH_XWDLDEL = "rcsw_rcxwwh_xwdldel";
	public static final String RCSW_RCXWWH_XWDLUPDATE = "rcsw_rcxwwh_xwdlupdate";
	public static final String RCSW_RCXWWH_XWDLYSY = "rcsw_rcxwwh_xwdlysy";//����Ϊ�����Ѿ�ʹ�ã�����ɾ������
	public static final String RCSW_RCXWWH_XWLBDEL = "rcsw_rcxwwh_xwlbdel";
	public static final String RCSW_RCXWWH_XWLBUPDATE = "rcsw_rcxwwh_xwlbupdate";
	public static final String RCSW_RCXWWH_XWLBYSY = "rcsw_rcxwwh_xwlbysy";//����Ϊ�������Ѿ�ʹ�ã�����ɾ������
	public static final String RCSW_RCXWWH_XWWHSHZ = "rcsw_rcxwwh_xwwhshz";
	
	public static final String RCSW_RCXWWH_SFQY_Y_FAIL = "rcsw_rcxwwh_sfqy_y_fail";//����ʧ��
	public static final String RCSW_RCXWWH_SFQY_Y_NUM = "rcsw_rcxwwh_sfqy_y_num";//�ɹ�����{0}��
	public static final String RCSW_RCXWWH_SFQY_Y_NULL = "rcsw_rcxwwh_sfqy_y_null";//��ѡ����Ҫ���õļ�¼��
	public static final String RCSW_RCXWWH_SFQY_N_FAIL = "rcsw_rcxwwh_sfqy_n_fail";//ͣ��ʧ��
	public static final String RCSW_RCXWWH_SFQY_N_NUM = "rcsw_rcxwwh_sfqy_n_num";//�ɹ�ͣ��{0}��
	public static final String RCSW_RCXWWH_SFQY_N_NULL = "rcsw_rcxwwh_sfqy_n_null";//��ѡ����Ҫͣ�õļ�¼��
	
	/**
	 * �ճ�����-�ճ���Ϊά�� new
	 */
	public static final String RCSW_RCXWWH_NEW_RCXWLBMCCZ = "rcsw_rcxwwh_new_rcxwlbmccz";//����Ϊ��������Ѵ��ڣ�
	public static final String RCSW_RCXWWH_NEW_RCXWLBDEL = "rcsw_rcxwwh_new_rcxwlbdel";// ��{0}���ѱ�ʹ�ã�����ɾ����
	public static final String RCSW_RCXWWH_NEW_RCXWDLCZ = "rcsw_rcxwwh_new_rcxwdlcz";//����Ϊ����Ѵ�����ͬ��Ϊ�������ƺ�������̣�
	public static final String RCSW_RCXWWH_NEW_RCXWDLDEL = "rcsw_rcxwwh_new_rcxwdldel";// ��{0}���ѱ�ʹ�ã�����ɾ����
	public static final String RCSW_RCXWWH_NEW_RCXWLBUPDATE = "rcsw_rcxwwh_new_rcxwlbupdate";//��{0}���Ѿ�����Ϊά��ʹ�ã������޸ģ�
	public static final String RCSW_RCXWWH_NEW_RCXWXLCZ = "rcsw_rcxwwh_new_rcxwxlcz";//����Ϊ�����Ѵ�����ͬ��ΪС�����ƣ�
	public static final String RCSW_RCXWWH_NEW_RCXWXLDEL = "rcsw_rcxwwh_new_rcxwxldel";//��{0}���Ѿ�����Ϊά��ʹ�ã�����ɾ����
	
	// ���д��¹���-���д��¹���-���ά��
	public static final String KYCXGL_KYCXXM_KYCXXMWH_SAVE = "kycxgl_kycxxm_kycxxmwh_save";
	public static final String KYCXGL_KYCXXM_KYCXXMWH_DEL = "kycxgl_kycxxm_kycxxmwh_del";
	public static final String KYCXGL_KYCXXM_KYCXXMWH_EXISTS = "kycxgl_kycxxm_kycxxmwh_exists";
	public static final String KYCXGL_KYCXXM_KYCX_EXISTS = "kycxgl_kycxxm_kycx_exists";
	
	// ��ҵ����-��ҵ����
	public static final String JYGLNEW_JYGL_BYQX_EXISTS = "jyglnew_jygl_byqx_exists";
	public static final String JYGLNEW_JYGL_CYJYYZD_EXISTS = "jyglnew_jygl_cyjyyzd_exists";
	public static final String JYGLNEW_JYGL_CYYQGL_EXISTS = "jyglnew_jygl_cyyqgl_exists";
	public static final String JYGLNEW_JYGL_CYPX_EXISTS = "jyglnew_jygl_cypx_exists";
	
	// �ճ�����-������ѯ-��ѯ�������
	public static final String RCSW_ZXZX_ZXBKSZ_DEL = "rcsw_zxzx_zxbksz_del";
	public static final String RCSW_ZXZX_ZXBKSZ_EXISTS = "rcsw_zxzx_zxbksz_exists";
	
	//�ճ�����-��ѧ�
	public static final String RCSW_TXHD_LBDMEXIST = "rcsw_txhd_lbdmexist"; //�������Ѵ��ڣ�
	public static final String RCSW_TXHD_LBDMEXIST_JG_UPDATE = "rcsw_txhd_lbdmexist_jg_update"; //�������������Ѵ��ڣ�
	public static final String RCSW_TXHD_LBDMEXIST_JG_DEL = "rcsw_txhd_lbdmexist_jg_del"; 
	public static final String RCSW_TXHD_LBDMEXIST_XMWH_UPDATE = "rcsw_txhd_lbdmexist_xmwh_update"; //��������Ŀά�������Ѵ��ڣ�
	public static final String RCSW_TXHD_LBDMEXIST_XMWH_DEL = "rcsw_txhd_lbdmexist_xmwh_del"; 
	public static final String RCSW_TXHD_XMMC_EXIST = "rcsw_txhd_xmmc_exist";//ѧ�Ż����Ŀ�����Ѵ��ڣ�
	public static final String RCSW_TXHD_XMMC_NOTDEL = "rcsw_txhd_xmmc_notdel";//ѧ�Ż����Ŀ�ѱ����룬������ɾ����
	public static final String RCSW_TXHD_XMMC_FULL = "rcsw_txhd_xmmc_full";//ѧ�Ż����������ޣ�
	public static final String RCSW_TXHD_RSKZ_FAIL_SQ ="rcsw_txhd_rskz_fail_sq";//���������ѳ��������������������趨
	public static final String RCSW_TXHD_RSKZ_FAIL_SH ="rcsw_txhd_rskz_fail_sh";//��������ѳ��������������������趨
	public static final String RCSW_TXHD_DMWH_HDGG_REPEAT = "rcsw_txhd_hdgg_repeat";
	
	//��ٹ���
	public static final String RCSW_QJGL_CFSQ ="rcsw_qjgl_cfsq";//��������ѳ��������������������趨
	
	//Υ�ʹ���
	public static final String WJCF_CFLBDM_CFLBCZ = "wjcf_cflbdm_cflbcz";//����������
	public static final String WJCF_CFLBDM_CFYYCZ = "wjcf_cflbdm_cfyycz";//����ԭ�����
	public static final String WJCF_CFLBDM_CFLBYSY = "wjcf_cflbdm_cflbysy";//���������ʹ��
	public static final String WJCF_CFYYDM_CFYYYSY = "wjcf_cfyydm_cfyyysy";//����ԭ����ʹ��
	public static final String WJCF_CFSS_CFQXSS = "wjcf_cfss_cfqxss";//�ظ�ȡ������
	public static final String WJCF_DEL_CFYSH = "wjcf_del_cfysh";//��������˲���ɾ��
	public static final String WJCF_SBCX_BKCX = "wjcf_sbcx_bkcx";//�����ϱ����һ����˳������������ߡ�������ɳ���
	public static final String WJCF_CFLBMC_BKZZ = "wjcf_cflbmc_bkzz";//�ô�����𲻿���ֹ
	public static final String WJCF_CFWH_FORMAT = "wjcf_cfwh_format";//�����ĺ�
	public static final String WJCF_CFJCWH_FORMAT = "wjcf_cfjcwh_format";//���ֽ���ĺ�

	/**
	 * �ڹ���ѧ
	 */
	public static final String QGZX_CHECK_FFJE = "qgzx_check_ffje";//�ڹ���ѧ������ӣ�������
	public static final String QGZX_CHECK_FFJE_YF = "qgzx_check_ffje_yf";//�ڹ���ѧ������ӣ�������
	public static final String QGZX_CHECK_WFFJC = "qgzx_check_wffjf";//�ڹ���ѧ������ӣ����ܲ�����Ӧ���ѡ�
	public static final String QGZX_CHECK_WFFJF_YF = "qgzx_check_wffjf_yf";//�ڹ���ѧ������ӣ����ܲ�����Ӧ���ѡ�
	/**
	 * �ճ�����-ѧ��֤����
	 */
	public static final String RCSW_XSZBB_BBLXMCCZ = "rcsw_xszbb_bblxmccz";//��ѧ��֤�������������Ѵ��ڣ�
	public static final String RCSW_XSZBB_RESULT_REPEAT = "rcsw_xszbb_result_repeat";
	public static final String RCSW_XSZBB_XSZBBSQ_REPEAT = "rcsw_xszbb_xszbbsq_repeat";
	public static final String RCSW_XSZBB_XSZBBJG_REPEAT = "rcsw_xszbb_xszbbjg_repeat";
	public static final String RCSW_XSZBB_SHLC_EXIST = "rcsw_xszbb_shlc_exist";
	public static final String RCSW_XSZBB_BBLXMCYSY = "rcsw_xszbb_bblxmcysy";
	
	public static final String RCSW_HCYHK_RESULT_REPEAT = "rcsw_hcyhk_result_repeat";//��ѧ����ͬһѧ��,ѧ�ڻ𳵳˳�������д�Ѵ��ڣ�
	public static final String RCSW_HCYHK_SHLC_EXIST = "rcsw_hcyhk_shlc_exist";
	public static final String RCSW_HCYHK_HCYHKLX_EXIST = "rcsw_hcyhk_hcyhklx_exist";
	
	
	/**
	 * �ճ�����--��ɫͨ��
	 */
	public static final String RCSW_LSTD_LSTDMCCZ = "rcsw_lstd_lstdmccz";//����ɫͨ�����������Ѵ��ڣ�
	public static final String RCSW_LSTD_LSTDJG_REPEAT = "rcsw_lstd_lstdjg_repeat"; //��ѧ��ͬһ�������������ɫͨ����
	public static final String RCSW_LSTD_SQ_REPEAT = "rcsw_lstd_sq_repeat";  //��ǰ�����������룬�����ظ��ύ��
	
	/**
	 * �ճ�����--ͨ��ҽ�Ʊ���
	 */
	public static final String RCSW_YLBX_YLBXJG_REPEAT = "rcsw_ylbx_lstdjg_repeat"; //��ѧ��ͬһ�����������ҽ�Ʊ��ա�
	public static final String RCSW_YLBX_SQ_REPEAT = "rcsw_ylbx_sq_repeat";  //��ǰ�����������룬�����ظ��ύ��
	public static final String RCSW_YLBX_YLBXGL_REPEAT = "rcsw_ylbx_ylbxgl_repeat";  //��ǰ���������ظ��ı��ռ�¼��
	public static final String RCSW_YLBX_YLBXSH = "rcsw_ylbx_ylbxsh";  //���ʧ�ܣ��������¼�Ѿ��������û���ˣ����ѧ������������м�¼��
	
	/**
	 * �ճ�����-��ѧ��ҽ�Ʊ���
	 */
	public static final String RCSW_DXSYLBX_BZLXCZ = "rcsw_dxsylbx_bzlxcz";//���������Ѵ��ڣ�
	public static final String RCSW_DXSYLBX_SHLC_EXIST = "rcsw_dxsylbx_shlc_exist";//��������������У������޸ģ�
	public static final String RCSW_DXSYLBX_CZQEBZRYSFCZ = "rcsw_dxsylbx_czqebzrysfcz";//����ȫ�����Ա����Ѵ��ڣ�
	
	public static final String RCSW_DXSYLBX_CBZKLXCZ = "rcsw_dxsylbx_cbzklxcz";//�α�״�������Ѵ��ڣ�
	public static final String RCSW_DXSYLBX_YLBXSQCZ = "rcsw_dxsylbx_ylbxsqcz";//��ѧ���ڵ�ǰѧ��ҽ�Ʊ��������Ѵ��ڣ�
	public static final String RCSW_DXSYLBX_YLBXJGCZ = "rcsw_dxsylbx_ylbxjgcz";//��ѧ���ڵ�ǰѧ��ҽ�Ʊ��ս���Ѵ��ڣ�
	/**
	 * �ճ�����-�ܱ�
	 */
	public static final String RCSW_XSGZZB_XSGZZBSQCZ = "rcsw_xsgzzb_xsgzzbsqcz";//�ڵ�ǰѧ�꣬ѧ�ڸá�ѧԺ���򡾰༶�����ܱ��Ѵ��ڣ�
	/**
	 * �ճ�����-�������϶�ָ��
	 */
	public static final String RCSW_KNSRDZB_QYSUCCESS  = "rcsw_knsrdzb_qysuccess";//�����������϶�ָ��ɹ�
	public static final String RCSW_KNSRDZB_QYFAIL  = "rcsw_knsrdzb_qyfail";//�����������϶�ָ��ʧ��
	public static final String RCSW_KNSRDZB_KNSRDSQ_REPEAT = "rcsw_knsrdzb_knsrdsq_repeat";//�ڵ�ǰѧ�꣬ѧ�ڸ�ѧ���������϶������룡

	/**
	 * �ְ����
	 */
	public static final String FBGL_TJZSK_BCZDYXDS  = "fbgl_tjzsk_bczdyxds";//�����ڶ�Ӧ�ѷְ��ѧ��ѧ��
	public static final String FBGL_CHECK_LSHBZ  = "fbgl_check_lshbz";//��ˮ��λ������
	public static final String FBGL_CHECK_LSH_REPEART  = "fbgl_check_lsh_repeart";//��ˮ���ظ�
	public static final String FBGL_CHECK_BJMC  = "fbgl_check_bjmc";//�༶���ƹ���
	public static final String FBGL_CHECK_BJDM  = "fbgl_check_bjdm";//�༶�������
	public static final String FBGL_CHECK_BJDM_UNIQUE  = "fbgl_check_bjdm_unique";//�༶����Ψһ
	public static final String FBGL_CHECK_OK  = "fbgl_check_ok";//���ͨ��

	/**
	 * �ճ�����-�ڶ�֤��
	 */
	public static final String RCSW_ZDZM_CSSZ_NOT_EXIST  = "rcsw_zdzm_cssz_not_exist";//��������δ�趨�����޷������ڶ�֤����
	public static final String RCSW_ZDZM_CSSZ_SQKG_CLOSE  = "rcsw_zdzm_sqkg_close";//��ǰ����Ϊ�ر�״̬�����޷������ڶ�֤����
	public static final String RCSW_ZDZM_SJ_NOT_IN_SZ = "rcsw_zdzm_sj_not_in_sz";//��ǰʱ�䲻�ڿ�����ʱ�䷶Χ�ڣ����޷������ڶ�֤��!
	public static final String RCSW_ZDZM_ALREADY_IN_SHL = "rcsw_zdzm_alreay_in_shl";//��ǰ�û�����������������У����޷������ڶ�֤��!
	
	/**
	 * �ճ�����-���÷���
	 */
	public static final String RCSW_FYFF_DMWH_EXIST = "rcsw_fyff_dmwh_exist";  //�����Ѵ���
	public static final String RCSW_FYFF_DMWH_MCEXIST= "rcsw_fyff_dmwh_mcexist";  //[i]�����Ѵ��ڡ�
	
	/**
	 * �ճ�����-ѧ���±�_�༶�±�
	 */
	public static final String RCSW_XQYB_BJYBSQ_REPEAT = "rcsw_xqyb_bjybsq_repeat";//�ð༶��ͬһѧ��,ѧ��,�·�ѧ���±������Ѵ��ڣ�
	public static final String RCSW_XQYB_BJYBJG_REPEAT = "rcsw_xqyb_bjybjg_repeat";//�ð༶��ͬһѧ��,ѧ��,�·�ѧ���±�����Ѵ��ڣ�
	
	
	/**
	 * ��Ԣ����
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
	 * �ճ�����-���ع���
	 */
	public static final String RCSW_CDSQ_CHECK_ERROR  = "rcsw_cdsq_check_error";//����ʱ��β��ڿ���ʱ����ڣ�������������ʱ��δ��ڳ�ͻ����ȷ�ϣ�
	
	public static final String RCSW_CDSQ_CHECK_ERROR2  = "rcsw_cdsq_check_error2";//���������Ѵ��ڣ�
	
	
	/**
	 * �ճ�����-���ڹ���
	 */
	public static final String RCSW_KQGL_DMWH_EXIST = "rcsw_kqgl_dmwh_exist";  //�˿��������Լ����ڣ������ظ���ӣ�
	public static final String RCSW_KQGL_DMWH_MCEXIST= "rcsw_kqgl_dmwh_mcexist";  //��{0}�������ѱ�ʹ�ã�
	
	/**
	 * �ճ�����-������ϰ���ڹ���
	 */
	//�ճ�����---�����ֹ���
	public static final String RCSW_XSXWKH_JBFGL_ADD_EXIST = "rcsw_xsxwkh_jbfgl_add_exist";//ͬһѧ���ѧ���Ѿ���һ����¼
	public static final String RCSW_ZWZXKQ_KQSJ_EXIST = "rcsw_zwzxkq_kgsj_exist";  //��{0}����{1}����{2}�����������Ѵ��ڣ������ظ��ύ��
	//public static final String RCSW_KQGL_DMWH_MCEXIST= "rcsw_kqgl_dmwh_mcexist";  //��{0}�������ѱ�ʹ�ã�
	
	/**
	 * ���ݴ�ѧ������ѯ
	 */
	public static final String XLZXWZDX_XSSQ_EXIST= "xlzxwzdx_xssq_exist";  //��ѧ�������룡
	public static final String XLZX_ZXZXTHJL_EXIST= "xlzx_zxzxthjl_exist";  //ͬһ��ͬһ��ѧ���������ж��̸����¼��
	public static final String XLZX_XLSCJG_EXIST= "xlzx_xlscjg_exist";  //��ǰɸ������ѧ���Ѵ��ڣ�
	
	/**
	 * ��������ѵ
	 */
	public static final String XLZX_PXWH_REPEAT= "xlzx_pxwh_repeat";  //��ǰ��ѵʱ���Ѵ��ڸ���ѵ����
	public static final String XLZX_PXWH_CZSUCCESS= "xlzx_pxwh_czsuccess"; //�����ɹ�
	public static final String XLZX_PXWH_CZFAIL= "xlzx_pxwh_czfail"; //����ʧ��

	/**
	 * ���ĳ���
	 */
	public static final String AXCS_AXLB_AXLBYCZ = "axcs_axlb_axlbycz";//�ð����������Ѵ��ڣ�
	public static final String AXCS_AXLB_NOTDEL = "axcs_axlb_axlbysy";//�ð�������ѱ�ʹ�ã�������ɾ��
	public static final String AXCS_AXWP_NOTDEL = "axcs_axwp_axwpysq";//�ð�����Ʒ�ѱ����룬������ɾ��
	public static final String AXCS_WPFZ_NOTJL ="axcs_wpfz_wkfzwp";//���ĳ��У���Ʒ���ƣ��޿ɸ��Ƶ���Ʒ
	public static final String AXCS_WPFZ_SUCCESS ="axcs_wpfz_success";//���ĳ��У���Ʒ���ƣ����Ƴɹ�
	public static final String AXCS_WPFZ_REPEAT = "axcs_wpfz_repeat"; //ѧ�����������������{0}����
	
	/**
	 * ÿ�չ�������
	 */
	public static final String QGZX_MRGZKH_REPEAT = "qgzx_mrgzkh_repeat";//ѧ����{0}������д�������ظ���д��
	public static final String QGZX_MRGZKH_GSOUT = "qgzx_mrgzkh_gsout";//ѧ����{0}����{1}���ܹ�ʱ����ѧ��ÿ�¹�ʱ���ޡ�{1}��Сʱ��
	public static final String QGZX_MRGZKH_JEOUT = "qgzx_mrgzkh_jeout";//ѧ����{0}����{1}�����Ž���ѧ��ÿ�³�����ޡ�{1}��Ԫ��
	public static final String QGZX_MRGZKH_GWJESXOUT = "qgzx_mrgzkh_gwjesxout";//ѧ����{0}�����Ž�����λ��{1}��ÿ�³�����ޡ�{2}��Ԫ��
	public static final String QGZX_MRGZKH_GWJESX_PL_OUT = "qgzx_mrgzkh_gwjesx_pl_out";//ѧ����{0}�����Ž�����λ������ޡ�
	/**
	 * ������չ
	 */
	public static final String SZTZ_XMSB_REPEAT = "sztz_xmsb_repeat";//����Ŀ���걨����������д��
	public static final String SZTZ_XMSB_NOTDEL = "sztz_xmsb_notdel";//����Ŀ�ѱ����룬������ɾ����
	public static final String SZTZ_XMSB_SPLC_USED = "sztz_xmsb_splc_used";//����Ŀ�ѱ����룬�������޸��������̡�
	
	/**
	 * ��������
	 */
	public static final String RCSW_HJJY_WGH = "rcsw_hjjy_wgh";//����Ŀ���걨����������д��

	/**
	 * ��������չѧ����Ŀ���룬�޸��ύ���߹�ѡ�ύʱ�ظ��ж�
	 */
	public static final String XSZTZ_XSXMJG_CFPD = "xsztz_xsxmjg_cfpd";
	public static final String CFTX = "cftx";
	/**
	 * У��ס������
	 */
	public static final String GYGL_XYZS_REPEAT = "gygl_xyzs_repeat";
	
	
	/**
	 * �㽭����ѧԺѧ���ɲ����˽�����ӱ���ʱ����������Ƿ����м�¼
	 */
	public static final String SZSW_XSGBGL_KHJG_ADD = "szdw_xsgbgl_khjg_add";
	
	//�㽭����ѧԺ�ճ�������ŵ������ӽ���������������Ƿ����м�¼
	public static final String RCSW_CXDA_ADD = "rcsw_cxda_add";
	
	/**
	 * ������¼
	 */
	public static final String GZJL_LBGL_LBYCZ = "gzjl_lbgl_lbycz";//���������Ѵ��ڣ�
	public static final String GZJL_LBGL_NOTDEL = "gzjl_lbgl_lbysy";//������ѱ�ʹ�ã�������ɾ��
	/**
	 * ���Ż
	 */
	public static final String STGL_JCSZ_STLB_REPEAT = "stgl_jcsz_stlb_repeat";
	public static final String STGL_JCSZ_XMLB_REPEAT = "stgl_jcsz_xmlb_repeat";
	public static final String STGL_JCSZ_RYLB_REPEAT = "stgl_jcsz_rylb_repeat";//��Ա����ظ�
	public static final String STGL_JCSZ_RYLB_USED = "stgl_jcsz_rylb_used";//��Ա�������״̬
	public static final String STGL_STGL_ST_REPEAT = "stgl_stgl_st_repeat";//�������Ѵ��ڣ������ظ�����
	public static final String STGL_STHDDJ_REPEAT = "stgl_sthddj_repeat";//ѧ����{0}-{1}��ʱ���ڣ����Ż�ѵǼǣ������ظ���д����
	public static final String STGL_STGL_NOTDEL = "stgl_stgl_notdel";//���������г�Ա��������ɾ����
	public static final String STGL_JCSZ_XMLB_USED = "stgl_jcsz_xmlb_used";
	public static final String STGL_JCSZ_STLB_USED = "stgl_jcsz_stlb_used";
	
	/**
	 * �����Ź���
	 */
	public static final String YSTGL_JCSZ_YSTLB_REPEAT = "ystgl_jcsz_ystlb_repeat";
	public static final String YSTGL_JCSZ_XMLB_REPEAT = "ystgl_jcsz_xmlb_repeat";
	public static final String YSTGL_JCSZ_GKDW_REPEAT = "ystgl_jcsz_gkdw_repeat";
	public static final String YSTGL_JCSZ_RYLB_REPEAT = "ystgl_jcsz_rylb_repeat";//��Ա����ظ�
	public static final String YSTGL_YSTGL_ST_REPEAT = "ystgl_ystgl_yst_repeat";//���������Ѵ��ڣ������ظ�����
	
	public static final String YSTGL_YSTGL_NOTDEL = "ystgl_ystgl_notdel";//�����������г�Ա��������ɾ����
	public static final String YSTGL_JCSZ_XMLB_USED = "ystgl_jcsz_xmlb_used";
	public static final String YSTGL_JCSZ_YSTLB_USED = "ystgl_jcsz_ystlb_used";
	public static final String YSTGL_JCSZ_GKDW_USED = "ystgl_jcsz_gkdw_used";
	/**
	 * �����һ�
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
	 * ���˹���
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
	 * վ���Ź���
	 */
	public static final String ZNXGL_XXFSCG = "znxgl_xxfscg";
	public static final String ZNXGL_XXFSSB = "znxgl_xxfssb";
	public static final String ZNXGL_XJCKSB = "znxgl_xjcksb";
	public static final String ZNXGL_XJFBSB = "znxgl_xjfpsb";
	public static final String ZNXGL_XJFBCG = "znxgl_xjfpcg";
	public static final String ZNXGL_XXHFCG = "znxgl_xxhfcg";
	public static final String ZNXGL_XXHFSB = "znxgl_xxhfsb";
	
	/**
	 * �ְ��ѧ��
	 */
	public static final String FBGL_FBGZMC_USED = "fbgl_fbgzmc_used";//�ְ���������Ѵ���
	
	
	/**
	 * ���ܷ�ά��
	 */
	public static final String PJPY_CPFWH_REPEAT = "pjpy_cpfwh_repeat";//��ѧ����ѧ��������
	/**
	 * ���ܷ�ά����������֤��
	 */
	public static final String PJPY_CPFWH_YANZHEN = "pjpy_cpfwh_yanzhen";//����ѧ��ѧ��ѧ�ŷ�������Ϊ��
	/**
	 * ���ܷ�ά����������֤��
	 */
	public static final String PJPY_CPFWH_XHNULL = "pjpy_cpfwh_xhnull";//������֤ѧ���Ƿ����

	/**
	 * ����ѧԺ����ѧ������
	 */
	public static final String XSZZ_HJXF_REPEATED = "xszz_hjxf_repeated";
	
	/**
	 * ������ҽҩ��ѧ�����ά����
	 */
	public static final String RCSW_XQDMWH_REPEATED = "rcsw_xqdmwh_repeated";//��ѧ������ѧ�������Ѵ���
	
	public static final String RCSW_XQDMWH_SQJGEXIST = "rcsw_xqdmwh_sqjgexist";//��ѧ�������ʹ�ã������޸Ļ�ɾ��
	
	public static final String RCSW_TSQKTB_REPEATED = "rcsw_tsqktb_repeated";//��ѧ��������ͨ��
	
	/**
	 * ���ڷ�Уά��
	 */
	public static final String JQFXGL_DMWH_REPEAT = "jqfxgl_dmwh_repeat";//��У���������������Ѵ��ڣ�
	public static final String JQFXGL_JQFXWH_REPEAT = "jqfxgl_jqfxwh_repeat";//�ڸ�ѧ���ѧ�����м�¼�������ظ���д��
	/**
	 * ѧ����ѧ���±�
	 */
	public static final String RCSW_XSCXQYB_REPEAT = "rcsw_xscxqyb_repeat";//��������ѧ���±���¼������������д��
	
	/**
	 * Ժ��ѧ���±�
	 */
	public static final String RCSW_YXYBGLSQ_REPEATED = "rcsw_yxybglsq_repeated";//��������ѧ���±���¼������������д��
	
	

	
	/**
	 * ѧ�������ܱ�
	 */
	public static final String RCSW_XSGZZB_WJLX_REPEAT = "rcsw_xsgzzb_wjlx_repeat";
	public static final String RCSW_XSGZZB_WJLX_USED = "rcsw_xsgzzb_wjlx_used";
	
	

	/**
	 * רҵ���϶��ȼ�
	 */
	public static final String PJPY_ZYJGL_DMWH_REPEAT = "pjpy_zyjgl_dmwh_repeat"; //רҵ���϶��ȼ������Ѵ���
	public static final String PJPY_ZYJGL_DMWH_USED = "pjpy_zyjgl_dmwh_used"; //רҵ���϶��ȼ���ʹ�ã������޸Ļ�ɾ��!
	
	/**
	 * ��ί����
	 */
	public static final String 	RCSWX_SJGL_TWSJ_REPEAT = "rcswx_sjgl_twsj_repaet";
	
	/**
	 * ��������
	 */
	public static final String 	RCSWX_SJGL_HQSJ_REPEAT = "rcswx_sjgl_hqsj_repaet";

	
	/**
	 * ��ý������
	 */
	public static final String ZJCM_WSJC_PFZ_REPEAT = "zjcm_wsjc_pfz_repeat";	//�����������Ѵ���
	public static final String ZJCM_WSJC_PFZ_DFGZYSY = "zjcm_wsjc_pfz_dfgzysy";	//��ֹ�����ʹ��
	public static final String SYS_YXSX_OVER  = "sys_yxsx_over";
	public static final String ZJCM_WSJC_TJ_WTJ = "zjcm_wsjc_tj_wtj"; //��ѯʧ�ܴ���δ�ύ����
	
	/**
	 * ������ҽҩ��ѧ
	 */
	public static final String SZDW_JTFF_RYWH_REPEAT ="szdw_jtff_rywh_repeat";
	
	/**
	 * ��ת��������֤������Ƽ���
	 */
	public static final String GYGL_ZZDGL_REPEAT = "gygl_zzdgl_repeat";//��ѧ����ѧ��������
	
	/**
	 * ѧ����ϢΪ����ע��
	 */
	public static final String XSXX_WBDZC_WBDLBMC = "xsxx_wbdzc_wbdlbmc"; //��δ����ע�����������Ѵ���
	public static final String XSXX_WBDZC_UPDATE = "xsxx_wbdzc_update"; //��������ѱ�ʹ�ò����޸�
	public static final String XSXX_CXDD_REPEAT =  "xsxx_cxdd_repeat";//���еȵڽ���ظ�
	/**
	 * ��Դ�ش���
	 */
	public static final String ZXDK_SYDDK = "zxdk_syddk"; //��ѧ���ڸ�ѧ���Ѿ��д����¼��
	
	/**
	 * �ٿ�ס�޵Ǽǣ�����ѧԺ��
	 */
	public static final String GYGL_LKXX_REPEAT = "gygl_lkxx_repeat";//�����֤�ŵ����ѵǼ�ס�ޣ������ظ��Ǽǣ�
	
	/**
	 * ������Ʒ��������ѧԺ��
	 */
	public static final String GYGL_GGWPJYGL_WGH = "gygl_ggwpjygl_wgh";//��ѧ���й�����Ʒδ�黹�����ȹ黹���ٽ�ȡ��
	
	/**
	 * �������������ظ���ʾ
	 */
	public static final String PJPY_JTPJ_SQ_REPEAT = "pjpy_jtpj_sq_repeat";//ͬһ����������ͬһ�������ڲ����ظ�����ͬһ���
	public static final String PJPY_JTPJ_JXMC_REPEAT = "pjpy_jtpj_jxmc_repeat";//���������Ѵ��ڣ�
	public static final String PJPY_JTPJ_JXMC_DEL = "pjpy_jtpj_jxmc_del";//��{0}���ѱ�ʹ�ã�����ɾ����
	
	/**
	 * �������϶���������ά�������������ѧ��
	 */
	public static final String XSZZ_KNSRD_SQLYWH_REPEAT ="xszz_knsrd_sqlywh_repeat"; //�����������Ѿ����ڣ������ظ���ӣ�
	
	/** 
	 * �㽭�����ۺϷ� 
	 */ 
	public static final String ZJLY_ZHF_SQ_REPEAT = "zjly_zhf_sq_repeat";
	public static final String ZJLY_ZHF_XMWH_XMMK_REPEAT = "zjly_zhf_xmwh_xmmk_repeat";
	public static final String ZJLY_ZHF_XMWH_XMMK_EXIST = "zjly_zhf_xmwh_xmmk_exist";
	public static final String ZJLY_ZHF_XMWH_JFXM_REPEAT = "zjly_zhf_xmwh_jfxm_repeat";
	public static final String ZJLY_ZHF_XMWH_JFXM_JDSZ_ERROR = "zjly_zhf_xmwh_jfxm_jdsz_error";
	public static final String XG_WSBZ_YYRQ_EXIST = "xg_wsbz_yyrq_exist";
	
	/**
	 * ������չ��3.0����ʾ
	 */
	public static final String SZTZ_NEW_TTXM_MCREPEAT = "sztz_new_ttxm_mcrepeat";
	
	
	
	/** 
	 * �������_��Ŀ�����ظ� 
	 */ 
	public static final String GYGL_WSJC_JCXM_REPEAT = "gygl_wsjc_jcxm_repeat";
	
	/**
	 * �Ϻ�Ϸ��ѧԺ    ��������-������������-ȡ��ʧ��
	 */
	public static final String   PJPY_QTSZ_QXSB = "pjpy_qtsz_qxsb";
	/**
	 * �Ϻ�Ϸ��ѧԺ    ��������-������������-ȡ���ɹ�
	 */
	public static final String   PJPY_QTSZ_QXCG = "pjpy_qtsz_qxcg";
	
	/**
	 * ������ҽҩ��ѧ    ������ѯ-������ѯ����-�Ѵ���
	 */
	public static final String  XLZX_ZXZXJL_REPEAT = "xlzx_zxzxjl_repeat";
	
	/**
	 * ���Ž���-��֯��ϵת��-��ҵ����֧��ά��-�Ѵ���
	 */
	public static final String DTJS_ZZGXZC_DZBWH_EXIST = "dtjs_zzgxzc_dzbwh_exist";
	
	/**
	 * ���Ž���-��֯��ϵת��-��ҵ����֧��ά��-��ʹ��
	 */
	public static final String DTJS_ZZGXZC_DZBWH_USED = "dtjs_zzgxzc_dzbwh_used";
	
	/**
	 * ���Ž���-��֯��ϵת��-��Ϣ���-ѧ���Ѵ��ڼ�¼
	 */
	public static final String DTJS_ZZGXZC_XXJG_EXIST = "dtjs_zzgxzc_xxjg_exist";
	
	/**
	 * ���Ž���-��֯��ϵת��-��Ϣ���-�����ű���ظ�ʹ��
	 */
	public static final String DTJS_ZZGXZC_XXJG_JSXBH_REPEAT = "dtjs_zzgxzc_xxjg_jsxbh_repeat";
	
	/**
	 * ͨ�ô���ά����ʾ��
	 */
	public static final String SYS_DMWH_DM_EXISTS = "sys_dmwh_dm_exists";//�����Ѵ��ڣ���ȷ�ϣ�
	public static final String SYS_DMWH_MC_EXISTS = "sys_dmwh_mc_exists";//�����Ѵ��ڣ���ȷ�ϣ�
	public static final String SYS_DMWH_USERD_NOTDEL = "sys_dmwh_userd_notdel";//�����ѱ�ʹ�ã�������ɾ����
	/**
	 * ͨ�õ�����ʾ��
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
	 * ��ְҵ����������ʾ��
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
	 * �Ϻ�Ϸ���޸�������
	 */
	public static final String PJPY_SHXJ_NOATTACHMENT = "pjpy_shxj_noattachment";
	/**
	 * �㽭��ѧ��ʵ����
	 */
	public static final String XG_ZJDX_JSKP_REPEAT = "xg_zjdx_jskp_repeat";
	public static final String XG_ZJDX_JSKP_XMCX_CANCEL_CX ="xg_zjdx_jskp_xmcx_cancel_cx";
	public static final String XG_JSKP_JGZQ_REPEAT ="xg_jskp_jgzq_repeat";
	
	/** 
	 * ��ְҵ
	 */ 
	public static final String JHZY_DEKT_YBM = "jhzy_dekt_ybm";
	public static final String JHZY_DEKT_BM_SUCCESS = "jhzy_dekt_bm_success";
	public static final String JHZT_DEKT_BM_OVERSIZE = "jhzy_dekt_bm_oversize";

	

	
	
	/**
	 *�ൺ����
	 */
	public static final String XG_GYPY_SQ_REPEAT = "xg_gypy_sq_repeat";

	
	/**
	 * �Ƹ�ʦ��
	 */
	public static final String XG_XLZX_SB_REPEAT = "xg_xlzx_sb_repeat";

	/**
	 * �ҳ��û�ע��
	 */
	public static final String YHZC_SUCCESS = "yhzc_success";
	public static final String YHZC_FAILED = "yhzc_failed";
	public static final String YHZC_SJHM_REPEAT = "yhzc_sjhm_repeat";
	public static final String YHZC_CHILDRENINFO_ERROR = "yhzc_childreninfo_error";
	public static final String YHZC_CHILDRENINFO_ASSOCIATED = "yhzc_childreninfo_associated";

	/**
	 * �����Ƽ���ѧ
	 */
	public static final String XG_DTJS_TSDZB_REPEAT = "xg_dtjs_tsdzb_repeat";
	
	/**
	 * ��վ�� 
	 */
	public static final String XG_HCZD_REPEAT = "xg_hczd_repeat";
	public static final String SYS_TRAN_BUSY = "sys_tran_busy";
	
	//����о���ó��ѧУ  �ƶ�������¼�븲���ж���ʾ��
	public static final String XG_RCSW_WSFLR_FG = "xg_rcsw_wsflr_fg";
	
	//ͨ�������ѳ���(������ͨ��ѧ)
	public static final String XG_HDGL_PEOPLE_BEYOND = "xg_hdgl_people_beyond";

	/**
	 * �����ר���ų�Ա����
	 */
	public static final String SYS_FP_FAIL = "sys_fp_fail"; //����ʧ��
	public static final String SYS_FP_SUCCESS = "sys_fp_success";  //����ɹ�

	/**
	 *  ����ũҵ ѧ�������������
	 */
    public static final String RCSW_XSGZQKBB_ZBB_ZCREPEAT = "rcsw_xsgzqkbb_zbb_zcrepeat";	//�ܱ���ͬѧ��ѧ��ͬѧԺ�����ܴ��ظ�
	public static final String RCSW_XSGZQKBB_YBB_XYYFREPEAT = "rcsw_xsgzqkbb_ybb_xyyfrepeat";	//ѧԺ�±���ͬѧ��ѧ��ͬѧԺ�����·��ظ�
    public static final String RCSW_XSGZQKBB_YBB_BJYFREPEAT = "rcsw_xsgzqkbb_ybb_bjyfrepeat";	//�༶�±���ѧԺ�±��иð༶�����Ѵ���
	public static final String RCSW_XSGZQKBB_YBB_BJYBBDATAEXISTS = "rcsw_xsgzqkbb_ybb_bjybbdataexists";	//ѧԺ�±������ڰ༶�±�������
	
	/*��ί����*/
	public static final String TYGL_TZZGL_REPEAT = "tygl_tzzgl_repeat";
	public static final String TYGL_TGBGL_EXIST = "tygl_tgbgl_exist";
	
	/**
	 * �㽭��ѧ(10335)���Ի�
	 */
	public static final String PJPY_YPZL_REPEAT = "pjpy_ypzl_repeat";//��ѧ����ѧ��������
	public static final String PJPY_YPZL_REPEAT_HSD = "pjpy_ypzl_repeat_hsd";//�����ڵ����ظ��ύ��¼
	public static final String PJPY_WDPJ_JXRYSH_FAIL = "pjpy_wdpj_jxrysh_fail";//��������_�ҵ�����_�����������_ʧ�ܣ���{0}����ѧԺ(԰)5����������˴��ѳ���ѧԺ����������35%����ȷ�ϣ�
	public static final String ZXDK_WXDKSQ_REPEATED = "zxdk_wxdksq_repeated";//��Ϣ��������
	public static final String ZXDK_YSJXJ_ERROR = "zxdk_ysjxj_error";//Ժ�轱ѧ��
	public static final String ZXDK_JKHK_REPEAT = "zxdk_jkhk_repeat";//��ѧ�������-��ѧ��������
	public static final String XSZY_ZGH_REPEAT = "xszy_zgh_repeat";//����֮��-��ְ�����Ѵ��ڡ�
}
	
	
							   

