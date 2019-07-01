/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��2��14�� ����3:33:54 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.gprz;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.dtjs.zzgxzc.bysdzbwh.BysdzbwhDao;
import com.zfsoft.xgxt.dtjs.zzgxzc.xxjg.XxjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-��֯��ϵת��-������־ģ��
 * @�๦������: Service
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��14�� ����3:33:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GprzService extends SuperServiceImpl<GprzForm, GprzDao>{

	/**
	 * @throws Exception  
	 * @����:�����޸�ǰ���¼��ֻƴ���иĶ����ֶ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��15�� ����2:03:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xf
	 * @param xxjgForm
	 * @return
	 * String [] �������� 
	 * @throws 
	 */
	public String[] getXgqhjl(XxjgForm xf, XxjgForm xxjgForm) throws Exception {
		StringBuilder xgqjl_sb = new StringBuilder();
		StringBuilder xghjl_sb = new StringBuilder();
		
		List<String[]> list = new ArrayList<String[]>();
		HashMap<String,String> xgqMap = getXgjlMap(xf);
		HashMap<String,String> xghMap = getXgjlMap(xxjgForm);
		
		list.add(new String[]{xgqMap.get("szdzbmc"),xghMap.get("szdzbmc"),"���ڵ�֧����"});
		list.add(new String[]{xgqMap.get("sfsn"),xghMap.get("sfsn"),"�Ƿ�ʡ�ڣ�"});
		list.add(new String[]{xgqMap.get("jsdzz"),xghMap.get("jsdzz"),"���յ���֯��"});
		list.add(new String[]{xgqMap.get("sqdw"),xghMap.get("sqdw"),"��ȥ��λ��"});
		list.add(new String[]{xgqMap.get("dfjzrq"),xghMap.get("dfjzrq"),"���ѽ������ڣ�"});
		list.add(new String[]{xgqMap.get("sfkjhyzm"),xghMap.get("sfkjhyzm"),"�Ƿ񿪾߻���֤����"});
		list.add(new String[]{xgqMap.get("jsxbh"),xghMap.get("jsxbh"),"�����ű�ţ�"});
		
		for(int i=0;i<list.size();i++){
			String [] sa = list.get(i);
			if(!sa[0].equals(sa[1])){
				xgqjl_sb.append(sa[2]);
				xgqjl_sb.append(sa[0]);
				
				xghjl_sb.append(sa[2]);
				xghjl_sb.append(sa[1]);
				
				xgqjl_sb.append(",");
				xghjl_sb.append(",");
			}
		}
		
		String xgqjl = xgqjl_sb.toString();
		String xghjl = xghjl_sb.toString();
		xgqjl = xgqjl.substring(0,xgqjl.length()-1<0?0:xgqjl.length()-1);
		xghjl = xghjl.substring(0,xghjl.length()-1<0?0:xghjl.length()-1);
		
		return new String[]{xgqjl,xghjl};
	}

	/**
	 * @throws Exception  
	 * @����:�����޸�ǰ���󣩼�¼��ȫ��ƴ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��15�� ����3:04:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xxjgForm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getXgqhjl(XxjgForm xxjgForm) throws Exception {
		StringBuilder xgqhjl_sb = new StringBuilder();
		
		List<String[]> list = new ArrayList<String[]>();
		HashMap<String,String> xgqhMap = getXgjlMap(xxjgForm);
		
		list.add(new String[]{xgqhMap.get("xh"),"ѧ�ţ�"});
		list.add(new String[]{xgqhMap.get("szdzbmc"),"���ڵ�֧����"});
		list.add(new String[]{xgqhMap.get("sfsn"),"�Ƿ�ʡ�ڣ�"});
		list.add(new String[]{xgqhMap.get("jsdzz"),"���յ���֯��"});
		list.add(new String[]{xgqhMap.get("sqdw"),"��ȥ��λ��"});
		list.add(new String[]{xgqhMap.get("dfjzrq"),"���ѽ������ڣ�"});
		list.add(new String[]{xgqhMap.get("sfkjhyzm"),"�Ƿ񿪾߻���֤����"});
		list.add(new String[]{xgqhMap.get("jsxbh"),"�����ű�ţ�"});
		
		for(int i=0;i<list.size();i++){
			String [] sa = list.get(i);
			xgqhjl_sb.append(sa[1]);
			xgqhjl_sb.append(sa[0]);
			
			if(i!=list.size()-1){
				xgqhjl_sb.append(",");
			}
				
		}
		
		
		return xgqhjl_sb.toString();
	}
		
	/**
	 * @throws Exception 
	 * @����:�����޸ļ�¼map�����޸�ǰ���޸ĺ��XxjgFormתΪͳһmap
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��15�� ����3:59:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	private HashMap<String,String> getXgjlMap(XxjgForm xxjgForm) throws Exception{
		String xh = xxjgForm.getXh();
		xh = xh == null?"":xh;	//ѧ��
		
		String szdzbmc = xxjgForm.getSzdzbmc();
		if(szdzbmc==null){
			szdzbmc = new BysdzbwhDao().getModel(xxjgForm.getSzdzb()).getDzbmc();
		}
		szdzbmc = szdzbmc == null?"":szdzbmc;	//���ڵ�֧������
		
		String sfsn = xxjgForm.getSfsn();
		sfsn = sfsn == null?"":sfsn;	//�Ƿ�ʡ��
		
		String jsdzz = xxjgForm.getJsdzz();
		jsdzz = jsdzz == null?"":jsdzz;	//���յ���֯
		
		String sqdw = xxjgForm.getSqdw();
		sqdw = sqdw == null?"":sqdw;	//��ȥ��λ
		
		String dfjzrq = xxjgForm.getDfjzrq();
		dfjzrq = dfjzrq == null?"":dfjzrq;	//���ѽ�������
		
		String sfkjhyzm = xxjgForm.getSfkjhyzm();
		sfkjhyzm = sfkjhyzm == null?"":sfkjhyzm;	//�Ƿ񿪾߻���֤��
		
		String jsxbh = xxjgForm.getJsxbh();
		jsxbh = jsxbh == null?"":jsxbh;	//�����ű��
		
		HashMap<String,String> xgjlMap = new HashMap<String,String> ();
		xgjlMap.put("xh", xh);
		xgjlMap.put("szdzbmc", szdzbmc);
		xgjlMap.put("sfsn", sfsn);
		xgjlMap.put("jsdzz", jsdzz);
		xgjlMap.put("sqdw", sqdw);
		xgjlMap.put("dfjzrq", dfjzrq);
		xgjlMap.put("sfkjhyzm", sfkjhyzm);
		xgjlMap.put("jsxbh", jsxbh);
		
		return xgjlMap;
	}

	/**
	 * @throws SQLException  
	 * @����:�������������־
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��15�� ����5:32:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gprzFormList
	 * void �������� 
	 * @throws 
	 */
	public boolean saveGprzFormList(List<GprzForm> gprzFormList) throws SQLException {
		return dao.saveGprzFormList(gprzFormList);
	}



}
