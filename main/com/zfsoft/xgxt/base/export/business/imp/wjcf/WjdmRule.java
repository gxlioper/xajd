/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����11:15:06 
 */
package com.zfsoft.xgxt.base.export.business.imp.wjcf;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�ʹ���ҵ����չ
 * @�๦������: ���ö�ӦΥ�����ͣ�ԭ�����
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-4-23 ����11:15:06
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WjdmRule extends BusinessExtend {
	private final static String _YWKZ_KEY = "YWKZ_WJCF";

	public WjdmRule(String drmkdm) {
		super(drmkdm, _YWKZ_KEY);
	}

	@Override
	protected String check(String message, List<ImportModel> data) {
		return this._YZTG;
	}

	public HashMap<String, Object> formartData(List<List<ImportModel>> success, List<String[]> succeedExcelDataList,
			List<String[]> error) {
		String nowCflbmc="";
		String nowCfyymc="";
		for (List<ImportModel> list : success) {
			for(ImportModel im:list){
				//���ö�Ӧ���ƵĴ���
				if(im.getZdm().equals("CFLBMC")){
					nowCflbmc=im.getValue().toString();
				}else if(im.getZdm().equals("CFYYMC")){
					nowCfyymc=im.getValue().toString();
				}
				if(im.getZdm().equals("CFLBDM")){
					im.setValue(getCflbdm(nowCflbmc));
				}else if(im.getZdm().equals("CFYYDM")){
					im.setValue(getCfyydm(nowCfyymc));
				}
			}
		}
		return super.formartData(success, succeedExcelDataList, error);
	}
	private String getCflbdm(String cflbmc){
			String sql = " select cflbdm from xg_wjcf_cflbdmb where cflbmc =?";
			return DAO.getInstance().getOneRs(sql, new String[]{cflbmc}, "cflbdm");
	}
	private String getCfyydm(String cfyydm){
		String sql = " select cfyydm from xg_wjcf_cfyydmb where cfyymc=?";
		return DAO.getInstance().getOneRs(sql, new String[]{cfyydm}, "cfyydm");

	}
}
