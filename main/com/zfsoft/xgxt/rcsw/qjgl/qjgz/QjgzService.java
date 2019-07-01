/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjgz;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������:��ٹ���service
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:06:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjgzService extends SuperServiceImpl<QjgzForm, QjgzDao> {
	QjgzDao dao = new QjgzDao();
	/**
	 * �����ڲ���ɾ������
	 */
	public static String _BCZSCID="-1";
	/*
	 * ʹ����
	 */
	public static String _SYZ="-1";
	public QjgzService() {
		this.setDao(dao);
	}
	/**
	 * 
	 * @��������ȡҳ����ʾ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-22 ����02:36:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kssj
	 * @param jssj
	 * @param detail �Ƿ���ʾ��ϸ��Ϣ
	 * @return
	 * String �������� 
	 * @throws
	 */
	private String getShow(String kssj,String jssj,boolean isDetail){
		String show;
	/*	//����ȵ�ʱ�䣬���ÿ�ʼ��1
		if(kssj.equals(jssj)){
			Integer newInt=(Integer.parseInt(kssj)-1);
			kssj=newInt.toString();
		}*/
		show= "����<font color=\"#FF8040\">"+kssj+"</font>��С�ڵ���<font color=\"#FF8040\">"+jssj+"</font>��";
		if(isDetail){
			show+="(<font color='red'>������"+kssj+"��</font>)";
		}
		return show;
	}
	private String getShow(String kssj,String jssj){
		return getShow(kssj, jssj,true);
	}
	/**
	 * @throws Exception
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����06:46:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws
	 */
	public boolean save(QjgzForm qf) throws Exception {
		setKssjForJssj(qf);
		return this.runInsert(qf);
	}
	/**
	 * 
	 * @����:�޸���ٹ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-22 ����11:12:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean update(QjgzForm qf) throws Exception {
		setKssjForJssj(qf);
		return this.runUpdate(qf);
	}
	/**
	 * 
	 * @����:���ݽ���ʱ�����ÿ�ʼʱ��
	 * 		 �����ʼʱ����ڽ���ʱ�䣬��ʼʱ���Զ���1
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-22 ����11:10:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return
	 * String �������� 
	 * @throws
	 */
	private void setKssjForJssj(QjgzForm qf){
		if(StringUtils.isNull(qf.getKssj())||StringUtils.isNull(qf.getJssj())){
			logger.error("��ٿ�ʼʱ��:"+qf.getKssj()+"��ٽ���ʱ��:"+qf.getJssj()+"Ϊ�Ƿ�����!");
		}
		if(qf.getKssj().equals(qf.getJssj())){
			Integer kssj=(Integer.parseInt(qf.getKssj())-1);
			qf.setKssj(kssj.toString());
		}
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡ��ͻ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����07:19:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public String getClash(QjgzForm model) throws Exception {
		//if(dao.isUse(qf.getQjgzid())){
			//return _SYZ;//����ʹ����
		//}
		List<HashMap<String,String>> list=this.getAllList(new QjgzForm());
		for(HashMap<String, String> hm:list){
			//������Լ����������ж�
			if(hm.get("qjgzid").equals(model.getQjgzid())){
				continue;
			}
			String kssj=hm.get("kssj");
			String jssj=hm.get("jssj");
			String qjlxid = hm.get("qjlxid");
			String ssxydm = hm.get("ssxydm");
			//�����ʼʱ��ͽ���ʱ�䶼���ֱ�ӷ���
			if(( Float.parseFloat(jssj)==Float.parseFloat(model.getJssj())
					|| Float.parseFloat(kssj)==Float.parseFloat(model.getKssj()) )
				&& ( (StringUtils.isNull(qjlxid) && StringUtils.isNull(model.getQjlxid()))
					|| ( (StringUtils.isNotNull(qjlxid) && StringUtils.isNotNull(model.getQjlxid()))
					&& qjlxid.equals(model.getQjlxid())) ) && ssxydm.equals(model.getSsxydm()) ){
				return getShow(kssj, jssj,false);
			}
			//����������ʼʱ����������ʱ��
			//��
			//�����������ʱ��С�ڵ����� ��ʼʱ��
			//����Ϊ���������䲻��ͻ 4 4
			//��������Ϊ��ͻ
			if((!(Float.parseFloat(jssj)<=Float.parseFloat(model.getKssj())||
				Float.parseFloat(model.getJssj())<=Float.parseFloat(kssj))) &&
					((StringUtils.isNull(qjlxid) && StringUtils.isNull(model.getQjlxid())) ||
							( (StringUtils.isNotNull(qjlxid) && StringUtils.isNotNull(model.getQjlxid()))
									&& qjlxid.equals(model.getQjlxid())
									) ) && ssxydm.equals(model.getSsxydm()) ){
				return getShow(kssj, jssj,false);
			}
		}
		return null;
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-13 ����02:31:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * String[] ��������   String���� 0Ϊ�ɹ�ɾ������Ϊ����ɾ����
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception{
		//StringBuffer del=new StringBuffer();
		List<String> delId=new ArrayList<String>();
		StringBuffer noDel=new StringBuffer();
		boolean isHaveNoId=false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			//��ʱ�����ж��Ƿ��Ƿ�ʹ�ã�ȫ�����Խ���ɾ��
			//if(!dao.isUse(str)){
				delId.add(str);//��¼ɾ��id
				//del.append(str);
				//del.append(",");
		/*	}else{
				//��ò���ɾ�����������
				QjgzForm qf=getModel(str);
				noDel.append("("+qf.getKssj());
				noDel.append("~");
				noDel.append(qf.getJssj()+")");
				noDel.append(",");
				isHaveNoId=true;
			}*/
		}
		int i=delId.size()>0?runDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str.substring(0,str.length()-1):_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����: ��ȡ�������List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-29 ����11:19:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjlxList(){
		return dao.getQjlxList();
	}

	public HashMap<String, String> getInfo(QjgzForm model) throws Exception {
		return dao.getInfo(model);
	}
}
