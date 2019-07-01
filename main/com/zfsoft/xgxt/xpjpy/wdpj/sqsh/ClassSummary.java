/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��4��10�� ����9:57:13 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ���ɼ����ܵ���
 * @�๦������: ѧ���ɼ����ܰ༶��
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��4��10�� ����9:57:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ClassSummary {
	private String xn;	//ѧ��
	
	private String xqmc;	//ѧ������
	
	private String xymc;	//ѧԺ����
	
	private String zymc;	//רҵ����
	
	private String bjdm;	//�༶����
	
	private String bjmc;	//�༶����

	private Map<String,XskcModel> kcMap;	//�γ̼��ϣ�������һѧ�ڿγ̺͵ڶ�ѧ�ڿγ̣�keyΪxq
	
	private Map<String,XscjModel> stuMap;	//ѧ�����ϣ�keyΪxh
	
	/**
	 * @���� �����캯������ʼ���γ̼�����ѧ������
	 */
	public ClassSummary() {
		
		kcMap = new LinkedHashMap<String,XskcModel>();
		stuMap = new LinkedHashMap<String,XscjModel>();
	}
	/**
	 * @param map 
	 * @param id  
	 * @����:��༶����������һ����¼������ѧ���б�������һ����¼��ͬʱ����Ӱ��ð༶�Ŀγ̼���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��10�� ����11:00:15
	 * @�޸ļ�¼: xuwen-2017��5��17������-�༶�Ŀγ��ַ�Ϊ��һѧ�ڿγ̺͵ڶ�ѧ�ڿγ�
	 * void �������� 
	 * @throws 
	 */
	public void addRecord(HashMap<String, String> map) {
		
		String xh = map.get("xh");	//ѧ��
		String kcmc = map.get("kcmc");	//�γ�����
		String kccj = map.get("cj");	//�γ̳ɼ�
		String kcjd = map.get("jd");	//�γ̼���
		String xq = map.get("xq");	//ѧ�ڴ���
		
		String pjcj = map.get("pjcj");	//ƽ���ɼ�
		String pjjd = map.get("pjjd");	//ƽ������
		String pjcjpm = map.get("pjcjpm");	//ƽ���ɼ�����
		
		XskcModel xskcModel = kcMap.get(xq);
		XscjModel xscjModel = stuMap.get(xh);
		
		if(xskcModel==null){
			
			String xqmc = map.get("xqmc"); //ѧ������
			xskcModel = new XskcModel(xqmc);
			kcMap.put(xq, xskcModel);
		}
		if(xscjModel==null){
			
			String xm = map.get("xm");
			xscjModel = new XscjModel(xh, xm, pjcj, pjjd, pjcjpm);
			//����ѧ�ſ����ظ�����Ϊ��һ������ѧ�ڵ�ѧ����¼�����ù�ֱ�Ӹ��Ǽ���
			stuMap.put(xh, xscjModel);	
		}
		
		
		
		Map<String,String[]> cj = xscjModel.getCjMap().get(xq);	//��ȡ��Ӧѧ�ڵĳɼ���ȡmap
		Set<String> kc = xskcModel.getKcSet();
		
//		if(cj==null){
//			cj = new LinkedHashMap<String,String>();
//			xscjModel.getCjMap().put(xq, cj);
//			//����ֱ��д��2��ѧ���ˣ����Ը�Ϊ�����ݿ�ȡѧ�ڴ���������
//			xscjModel.getCjMap().put("01", new LinkedHashMap<String,String>());
//			xscjModel.getCjMap().put("02", new LinkedHashMap<String,String>());
//		}
	
		if(StringUtils.isNotNull(kcmc)){
			kc.add(kcmc);	//�ռ��༶�γ�
			cj.put(kcmc,new String[]{kccj,kcjd});	//�ռ�ѧ����Ŀ�ɼ��ͼ���
		}
		
		
	}
	
	/**
	 * @return the kcMap
	 */
	public Map<String, XskcModel> getKcMap() {
		return kcMap;
	}
	/**
	 * @return the stuMap
	 */
	public Map<String, XscjModel> getStuMap() {
		return stuMap;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymcҪ���õ� xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymcҪ���õ� zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdmҪ���õ� bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmcҪ���õ� bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param xn
	 * @param xqmc
	 * @param xymc
	 * @param zymc
	 * @param bjmc
	 */
	public ClassSummary(String xn, String xqmc, String xymc, String zymc, String bjdm,String bjmc) {
		this();
		this.xn = xn;
		this.xqmc = xqmc;
		this.xymc = xymc;
		this.zymc = zymc;
		this.bjdm = bjdm;
		this.bjmc = bjmc;
	}
	
	
	
}
