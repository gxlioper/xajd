/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����10:50:33 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.cdxxwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.attachupload.model.FileInfo;
import com.zfsoft.xgxt.comm.attachupload.service.FileInfoService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ������Ϣά��Service 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-23 ����10:50:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdxxwhService extends SuperServiceImpl<CdxxwhForm, CdxxwhDao> {

	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡpopup������Ϣ�б���Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����01:45:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPopupCdxx(CdxxwhForm model) throws Exception{
		return dao.getPopupCdxx(model);
	}
	
	/**
	 * 
	 * @����:��ȡ���ſ����б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����02:26:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getKfkgList(){
		/**
		 *  �̶�ѡ�����
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> xzkgList = optionUtil.getOptions(OptionUtil.ONOFF);
		
		return xzkgList;
	}
	
	/**
	 * 
	 * @����:���ݳ���ID��ȡ������ϢMap
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����04:21:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cdid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getCdxxByCdid(String cdid){
		HashMap<String , String> cdxx = dao.getCdxxByCdid(cdid);
		// ========== Э���ļ��Ƿ���� begin =============
		String xysfilepathGid = cdxx.get("xysfilepath");
		if(xysfilepathGid != null && !"".equals(xysfilepathGid)){
			FileInfoService fileInfoService  = new FileInfoService();
			List<FileInfo> infoList = fileInfoService.getFileInfoList(xysfilepathGid);
			if(infoList.size() == 0){
				cdxx.put("xysfilepath","");
			}else{
				cdxx.put("xysfilepathfid",infoList.get(0).getFid());
			}
		}
		// ========== Э���ļ��Ƿ���� end =============
		return cdxx;
	}
	
	/**
	 * 
	 * @����:���ݳ������ƻ�ȡ������
	 */
	public int getCdsByCdmc(String cdmc){
		return dao.getCdsByCdmc(cdmc);
	}
	
	
	/**
	 * 
	 * @����:���ݳ���id����Ƿ�����
	 */
	public boolean check(String cdid){
		return dao.check(cdid);
	}
	
	/**
	 * @���� �����췽�� ��ʼ��DAO
	 */
	public CdxxwhService() {
		super();
		super.setDao(new CdxxwhDao());
	}

	/**
	 * 
	 * @����: ��ȡ������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-17 ����03:44:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCdxx(String sqid,String tableName){
		return dao.getCdxx(sqid,tableName);
	}
	
	/**
	 * 
	 * @����: ��ȡ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-17 ����04:24:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getShyjList(String ywid){
		return dao.getShyjList(ywid);
	}
}
