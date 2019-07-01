/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-8 ����11:56:10 
 */
package com.zfsoft.xgxt.zxdk.tyxs.zzsq;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.axcs.wpjg.WpjgForm;
import com.zfsoft.xgxt.axcs.wpjg.WpjgService;
import com.zfsoft.xgxt.axcs.wpsh.WpshForm;
import com.zfsoft.xgxt.axcs.wpsq.WpsqDao;
import com.zfsoft.xgxt.axcs.wpsq.WpsqForm;
import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.xyddk.XyddkDao;
import com.zfsoft.xgxt.zxdk.xyddk.XyddkModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ѧ����������ģ��
 * @�๦������: �����������
 * @���ߣ�����Ӣ[���ţ�1177]
 * @ʱ�䣺 2015-4-8 ����11:56:10
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TyxsZzsqService extends SuperAuditService<TyxsZzsq, TyxsZzsqDao>
		implements Constants {
	/**
	 * 
	 * @����:�޸�����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-9 ����04:18:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateZzsq(TyxsZzsq t) {

		try {
			dao.runUpdate(t);
			return dao.runInsert(t);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 
	 * @����:����idɾ��
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����04:01:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	public boolean delZxsq(TyxsZzsq t) {
		try {
			return dao.runDelete(new String[] { t.getId() }) > 0;
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @����:�߼���ѯ��ȡ����б�
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����04:00:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getAudingList(TyxsZzsq t, User user) {

		try {
			return dao.getShList(t, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return null;
		}
	}

	public HashMap<String, String> getSqxxByID(String id) {
		return dao.getSqxxByID(id);
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-21 ����11:52:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean addSq(TyxsZzsq t) {
		try {
			return dao.runInsert(t);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @throws ��������	 *             
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-14 ����01:36:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	public boolean saveSqDraft(TyxsZzsq t) {

		// �鿴��ѧ���Ƿ��Ѿ������
		HashMap<String, String> map = dao.getZzsqByXh(t);
		try {
			if (map == null) {
				return dao.runInsert(t);
			} else {
				return dao.runUpdate(t, map.get("id"));
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return false;

		}

	}

	/**
	 * @����:����ѧ�ú�ѧ���ȡ��¼����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-14 ����04:46:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	public String getCountByXhAndXn(TyxsZzsq t) {
		return dao.getCountByXhAndXn(t);

	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com
	 * .zfsoft.xgxt.base.model.SuperAuditModel)
	 */

	@Override
	public boolean afterLastAudit(TyxsZzsq model) {
		// TODO �Զ����ɷ������

		return false;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft
	 * .xgxt.base.model.SuperAuditModel)
	 */

	@Override
	public boolean deleteResult(TyxsZzsq model) {
		// TODO �Զ����ɷ������
		return false;
	}
	
	/**
	 * 
	 * @����:��ȡ����List
	 * @���� ChenQ[���ţ�856]
	 * @���ڣ�2015-9-6 ����02:32:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYhList(){
		return dao.getYhList();
	}
	/*��ʦ�󱣴���Ƚ��*/
	public boolean saveNdJeHsd(String[] nds,String[] jes,String id) throws Exception{
		return dao.saveNdJeHsd(nds, jes, id);
	}
	
	/*��ʦ����Ƚ��ɾ��*/
	public boolean delNdJe(String id) throws Exception{
		return dao.delNdJe(id);
	}
	
	/*��Ƚ��list*/
	public List<HashMap<String, String>> getNdJe(String id){
		return dao.getNdJe(id);
	}
	
	public  String getYhListByYhdm(String yhdm){
		return dao.getYhListByYhdm(yhdm);
	}
	
	public boolean afterLastAuditself(TyxsZzsq model) throws Exception {
		// TODO �Զ����ɷ������

		return dao.afterLastAudit(model.getXh(),model.getXn());
	}
}