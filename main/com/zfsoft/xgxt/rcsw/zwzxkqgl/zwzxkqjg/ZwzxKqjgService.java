/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-20 ����11:40:07 
 */
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-20 ����11:40:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZwzxKqjgService extends SuperServiceImpl<ZwzxKqjgForm, ZwzxKqjgDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ZwzxKqjgDao kqjgDao = new ZwzxKqjgDao();

	/**
	 *��ѯȱ������
	 */
	public List<HashMap<String, String>> getQqlxList() {
		return kqjgDao.getQqlxList();
	}

	public boolean isHaveKgjg(ZwzxKqjgForm model) {
		return kqjgDao.isHaveKgjg(model);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:���ڽ�����ӱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-22 ����09:54:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean ��������
	 * @throws
	 */
	public boolean editKqjg(ZwzxKqjgForm model,List<ZwzxKqjgForm> qqxxList) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String jgid = UniqID.getInstance().getUniqIDHash();
			model.setJgid(jgid);
			model.setSqid(jgid);
			model.setLrsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
			//ɾ��ȱ��ѧ����Ϣ���ٲ���
			result = dao.delQqxs(model.getJgid());
		}
		List<String[]> qqxsxxList = new ArrayList<String[]>();
		String[] qqxsxx = null;
		for (ZwzxKqjgForm kqjgForm : qqxxList) {
			
		
			qqxsxx = new String[16];
			qqxsxx[0] = model.getJgid();
			qqxsxx[1] = kqjgForm.getXh();
			qqxsxx[2] = model.getXn();
			qqxsxx[3] = model.getXq();
			qqxsxx[4] = model.getLrsj();
			qqxsxx[5] = model.getCclxdm();
			qqxsxx[6] = kqjgForm.getQqlxdm();
			qqxsxx[7] = kqjgForm.getKkjs();
			qqxsxx[8] = model.getBjdm();
			qqxsxx[9] = model.getCcrq();
			qqxsxx[10] = model.getJlf();
			qqxsxx[11] = model.getJlr();
			qqxsxx[12] = kqjgForm.getYlzd1();
			qqxsxx[13] = "";
			qqxsxx[14] = "";
			qqxsxx[15] = "";
			qqxsxxList.add(qqxsxx);

		}
		if (result) {
			result = dao.qqxsPlbc(qqxsxxList);
		}
		return result;

	}

	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ���ڽ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public ZwzxKqjgForm getKqjg(ZwzxKqjgForm t) throws Exception {
		return dao.getKqjg(t);
	}
	/**
	 * 
	 * @����:ɾ�����ڽ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-26 ����11:32:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * ZwzxKqjgForm �������� 
	 * @throws
	 */
	public boolean delQqxs(String[] ids) throws Exception {
		List<String[]> qqxsList = new ArrayList<String[]>();
		String[] idArr = null;
		for (int i = 0; i < ids.length; i++) {
			idArr = new String[1];
			idArr[0]=ids[i];
			qqxsList.add(idArr);
		}
		return  dao.delPlQqxs(qqxsList);
	}

	/**
	 * 
	 * @����:��ȡȱ��ѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����04:50:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getQqxsList(String id) {
		return dao.getQqxsList(id);

	}
	
	

	/**
	 * 
	 * @����:��ȡ�༶�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����09:16:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBjListNew(ZwzxKqjgForm model, User user) throws Exception {
		return dao.getBjListNew(model, user);

	}
	/**
	 * 
	 * @����:��ȡѧ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-30 ����11:19:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xq
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getXqmc(String xq) throws Exception {
		return dao.getXqmc(xq);

	}
	
	

	/**
	 * 
	 * @����:��ȡѧ����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-23 ����11:03:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(ZwzxKqjgForm model, User user,HttpServletRequest request) throws Exception {
		String xhArr = request.getParameter("xhArr");
		if("".equals(xhArr)){
			model.setXhArr(new String[]{});
		}else{
		String[] xhs = xhArr.split(",");
		model.setXhArr(xhs);
		}
		return dao.getXsxxList(model, user);

	}
	/**
	 * 
	 * @����:��ѯȱ��ѧ���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����04:05:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQqxsList(ZwzxKqjgForm model, User user) throws Exception {
		return dao.getQqxsList(model, user);

	}
	/**
	 * 
	 * @����:��ѯȱ��ѧ���б�(����ҳ)
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-28 ����10:59:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllQqxsList(ZwzxKqjgForm model, User user) throws Exception {
		return dao.getAllQqxsList(model, user);

	}
	/**
	 * 
	 * @����:ѧ��ȱ����Ϣ����鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����05:09:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getQqxsxx(ZwzxKqjgForm model) throws Exception {
		return dao.getQqxsxx(model);

	}
	/**
	 * @throws Exception 
	 * 
	 * @����:����ȱ��ѧ����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����11:00:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws
	 */
	public boolean updateQqxs(String jgid,String sjzt) throws Exception{
		return dao.updateQqxs(jgid,sjzt);
		
	}
	/**
	 * 
	 * @����:ȱ��ѧ������ͬ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-30 ����04:22:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qqxsxxTb() throws Exception{
		DAO dao = DAO.getInstance();
		boolean flag = false;

		try {
			flag = dao.runProcuder("{call pro_rcsw_zwzxkq_kqrs_tb()}",
					new String[] {});
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
		
	}

}
