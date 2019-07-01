/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-28 ����10:54:14 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpzsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-4-28 ����10:54:14
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjSzService extends
		SuperServiceImplExtend<JtpjSzForm, JtpjSzDao> {
	JtpjSzDao jsd = new JtpjSzDao();
	/**
	 * ����
	 */
	private final String SFKFSQ_KQ = "1";
	/**
	 * �ر�
	 */
	private final String SFKFSQ_GB = "0";

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public JtpjSzService() {
		this.setDao(jsd);
	}

	/**
	 * 
	 * @����: ��ȡְ��list
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����09:27:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getZwList(String xh) {
		return jsd.getZwList(xh);
	}
	public List<HashMap<String, String>> getZwList() {
		return jsd.getZwList();
	}
	public boolean copy(String lyxn, String lyxq, String sqxn, String sqxq)
			throws Exception {
		boolean isok = true;
		List<HashMap<String, String>> list = dao.getJxxxForSqXnXq(lyxn, lyxq);
		JtpjSzForm jsf = new JtpjSzForm();
		for (HashMap<String, String> hm : list) {
			BeanUtils.copyProperties(jsf, hm);
			jsf.setJxid(null);
			jsf.setSqxn(sqxn);
			jsf.setSqxq(sqxq);
			isok = runInsert(jsf);
		}
		return isok;
	}

	/**
	 * 
	 * 
	 * @����: ��ȡ������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-6 ����09:30:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getSqZqList() {
		return jsd.getSqZqList();
	}

	/**
	 * 
	 * @����: ��ȡ�������ڣ�ȥ���ض�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-7 ����04:37:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqzq
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getSqZqListNotIsHave(String sqzq) {
		List<HashMap<String, String>> list = jsd.getSqZqList();
		for (HashMap<String, String> hm : list) {
			if (hm.get("dm").equals(sqzq)) {
				list.remove(hm);
				break;
			}
		}
		return list;
	}

	/**
	 * 
	 * @����: ��ȡ�������ڣ����������ǰѧ��ѧ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-7 ����04:35:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getNowSqZqList() {
		List<HashMap<String, String>> list = jsd.getSqZqList();
		boolean isadd = true;
		for (HashMap<String, String> hm : list) {
			if (hm.get("dm").equals(Base.currXn + "," + Base.currXq)) {
				isadd = false;
			}
		}
		if (isadd) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("dm", Base.currXn + "," + Base.currXq);
			hm.put("mc", Base.currXn + " " + Base.getDqxqmc());
			list.add(hm);
		}
		return list;
	}

	@Override
	public JtpjSzForm getModel(String keyValue) throws Exception {
		JtpjSzForm jsf = super.getModel(keyValue);
		jsf.setSqxqmc(BaseDAO.getInstance().getXqmcForXqdm(jsf.getSqxq()));
		jsf.setPdxqmc(BaseDAO.getInstance().getXqmcForXqdm(jsf.getPdxq()));
		return jsf;
	}

	/**
	 * 
	 * @����: ��ȡ����list
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����09:28:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getJxList(String xn, String xq,
			String flg, User user) {
		List<HashMap<String, String>> newlist = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> list = jsd.getJxList(xn, xq, flg,"",user);
		// �����ѧ���û�������֤��ְ���Ƿ����趨�Ŀ�����ְ����
		if (null != list && list.size() > 0 && user.getUserType().equals("stu")&&Globals.XXDM_WZDX.equals(Base.xxdm)) {
			String ksqxslx = "";
			String ksqxslxs[] = null;
			for (HashMap<String, String> hm : list) {
				ksqxslx = hm.get("ksqxslx");
				if (StringUtils.isNotNull(ksqxslx)) {
					ksqxslxs = ksqxslx.split(",");
					if (isInZwList(ksqxslxs, user.getUserName())) {
						newlist.add(hm);
					}
				}
			}
		} else {
			newlist = list;
		}
		return newlist;
	}
	/**
	 * 
	 * @����: �Ƿ���ְ���б���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-29 ����04:56:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ksqxslxs
	 *            �趨��ְ���б�
	 * @return boolean ��������
	 */
	public boolean isInZwList(String ksqxslxs[], String xh) {
		List<HashMap<String, String>> zwlist = getZwList(xh);
		for (HashMap<String, String> hm : zwlist) {
			for (String ksqxs : ksqxslxs) {
				if (ksqxs.equals(hm.get("zwid"))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @����:��ȡְ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-29 ����02:15:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zwid
	 * @return String ��������
	 */
	public String getZwMc(String zwid) {
		if (null == zwid || zwid.length() <= 0) {
			return null;
		}
		StringBuffer zwmc = new StringBuffer();
		String zwids[] = zwid.split(",");
		for (String id : zwids) {
			zwmc.append(jsd.getZwMc(id));
			zwmc.append(" ");
		}
		return zwmc.toString();
	}

	/**
	 * 
	 * @����: ��ȡ�Ƿ����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-29 ����02:27:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sfkfsq
	 * @return String ��������
	 */
	public String getSfkfsqmc(String sfkfsq) {
		String mc = "";
		if (SFKFSQ_KQ.equals(sfkfsq)) {
			mc = "����";
		} else if (SFKFSQ_GB.equals(sfkfsq)) {
			mc = "�ر�";
		}
		return mc;
	}

	/**
	 * 
	 * @����: ��ȡ������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-29 ����02:14:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lcid
	 * @param lcxxlx
	 * @return String ��������
	 */
	public String getLcxxMc(String lcid, String lcxxlx) {
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx(lcxxlx);
		for (HashMap<String, String> hm : shlc) {
			if (StringUtils.isNotNull(lcid) && lcid.equals(hm.get("splc"))) {
				return hm.get("lcxx");
			}
		}
		return null;
	}

	/** 
	 * @����: ��ȡ����list
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-28 ����02:33:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param currXn
	 * @param currXq
	 * @param string
	 * @param jxid
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public Object getJxList(String currXn, String currXq, String flg,
			String jxid,User user) {
		return jsd.getJxList(currXn, currXq, flg, jxid,user);
	}
	
	/**
	 * 
	 * @����:����������Ŀ�ж��Ƿ��ѱ�ʹ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-16 ����10:53:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExists(String jxid){
		return dao.checkIsNotExists(jxid);
	}
	
	/**
	 * 
	 * @����:����������Ŀ�ж��Ƿ��ѱ�ʹ��(ɾ��)
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2016-6-20 ����02:26:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	  public String isCheckExistForDel(String value)throws Exception{
		  String resultjxmc="";
	      String[] jxmcArr =dao.isCheckExistForDel(value);
	      for(int i=0;i<jxmcArr.length;i++){ 
			  if(i==jxmcArr.length-1){
				  resultjxmc+=jxmcArr[i];
			  }else{
				  resultjxmc+=jxmcArr[i]+",";
			  }
		  }
		  return resultjxmc;
	  }
	
	/**
	 * 
	 * @����:����������Ŀ�����ظ����ж�
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2016-6-20 ����11:10:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByJxmc(JtpjSzForm model) {
		
		String num=dao.isExistByJxmc(model);
    	return  "0".equalsIgnoreCase(num);
	}
	
	public List<HashMap<String,String>> getBbxxList(){
		return dao.getBbxxList();
	}
	
	public List<HashMap<String,String>> getBbxxList(String bbdm){
		return dao.getBbxxList(bbdm);
	}
	
}
