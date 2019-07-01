/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-22 ����11:01:18 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-12-22 ����11:01:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxzxjlService extends SuperServiceImpl<ZxzxjlModel, ZxzxjlDao>{
	private ZxzxjlDao dao = new ZxzxjlDao();
	private ZxzxjlxxDao jlxxDao = new ZxzxjlxxDao();
	
	/** 
	 * @����:��ȡ�Ƿ�ѡ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����02:14:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getSfList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "��");
		map_one.put("mc", "��");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "��");
		map_two.put("mc", "��");
		list.add(map_two);
		return list;
	}
	
	/** 
	 * @����:��ȡ��ͥ���ڵ�ѡ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����02:17:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJtszdList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "���г���");
		map_one.put("mc", "���г���");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "С����");
		map_two.put("mc", "С����");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "ũ��");
		map_three.put("mc", "ũ��");
		list.add(map_three);
		return list;
	}
	
	/** 
	 * @����:��ȡ����״̬ѡ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����02:19:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJjzkList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "��ԣ");
		map_one.put("mc", "��ԣ");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "һ��");
		map_two.put("mc", "һ��");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "ƶ��");
		map_three.put("mc", "ƶ��");
		list.add(map_three);
		return list;
	}
	
	/** 
	 * @����:��ȡ�Ļ��̶�ѡ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����02:23:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getWhcdList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "�о�������");
		map_one.put("mc", "�о�������");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "��ѧ����ר");
		map_two.put("mc", "��ѧ����ר");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "���С���ר���м�");
		map_three.put("mc", "���С���ר���м�");
		list.add(map_three);
		HashMap<String,String> map_four = new HashMap<String, String>();
		map_four.put("dm", "����");
		map_four.put("mc", "����");
		list.add(map_four);
		HashMap<String,String> map_five = new HashMap<String, String>();
		map_five.put("dm", "Сѧ");
		map_five.put("mc", "Сѧ");
		list.add(map_five);
		HashMap<String,String> map_six = new HashMap<String, String>();
		map_six.put("dm", "����");
		map_six.put("mc", "����");
		list.add(map_six);
		return list;
	}
	
	/** 
	 * @����:��ȡ��ͥ״��ѡ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����02:25:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getHyzkList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "����");
		map_one.put("mc", "����");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "ʧ��");
		map_two.put("mc", "ʧ��");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "ɥ��");
		map_three.put("mc", "ɥ��");
		list.add(map_three);
		HashMap<String,String> map_four = new HashMap<String, String>();
		map_four.put("dm", "����");
		map_four.put("mc", "����");
		list.add(map_four);
		return list;
	}
	
	/** 
	 * @����:��ȡ����ѡ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����02:27:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getYwList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "��");
		map_one.put("mc", "��");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "��");
		map_two.put("mc", "��");
		list.add(map_two);
		return list;
	}
	
	/** 
	 * @����:��ȡϲ���̶�ѡ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����02:28:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXhcdList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "�ǳ�ϲ��");
		map_one.put("mc", "�ǳ�ϲ��");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "һ��");
		map_two.put("mc", "һ��");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "��ϲ��");
		map_three.put("mc", "��ϲ��");
		list.add(map_three);
		return list;
	}
	
	/** 
	 * @����:��ȡ��ѯ����ѡ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����02:31:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZxwtList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "��Ӧ");
		map_one.put("mc", "��Ӧ");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "ѧϰ");
		map_two.put("mc", "ѧϰ");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "�˼ʹ�ϵ");
		map_three.put("mc", "�˼ʹ�ϵ");
		list.add(map_three);
		HashMap<String,String> map_four = new HashMap<String, String>();
		map_four.put("dm", "����");
		map_four.put("mc", "����");
		list.add(map_four);
		HashMap<String,String> map_five = new HashMap<String, String>();
		map_five.put("dm", "��");
		map_five.put("mc", "��");
		list.add(map_five);
		HashMap<String,String> map_six = new HashMap<String, String>();
		map_six.put("dm", "ְҵ���Ĺ滮");
		map_six.put("mc", "ְҵ���Ĺ滮");
		list.add(map_six);
		HashMap<String,String> map_seven = new HashMap<String, String>();
		map_seven.put("dm", "��ͥ");
		map_seven.put("mc", "��ͥ");
		list.add(map_seven);
		HashMap<String,String> map_eight = new HashMap<String, String>();
		map_eight.put("dm", "��������");
		map_eight.put("mc", "��������");
		list.add(map_eight);
		HashMap<String,String> map_nine = new HashMap<String, String>();
		map_nine.put("dm", "ѧУ");
		map_nine.put("mc", "ѧУ");
		list.add(map_nine);
		HashMap<String,String> map_ten = new HashMap<String, String>();
		map_ten.put("dm", "Σ����ѯ");
		map_ten.put("mc", "Σ����ѯ");
		list.add(map_ten);
		return list;
	}
	
	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����03:24:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean addZxjbxx(ZxzxjlModel form) throws Exception{
		String[] wts = form.getYzxwts();
		if(null != wts){			
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i<wts.length;i++){
				sb.append(wts[i]);
				if(i != wts.length-1){
					sb.append(",");
				}
			}
			String yzxwt = sb.toString();
			form.setYzxwt(yzxwt);
		}
		return runInsert(form);
	}
	
	/** 
	 * @����:���»�����Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����03:32:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateZxjbxx(ZxzxjlModel form) throws Exception{
		String[] wts = form.getYzxwts();
		if(null != wts){			
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i<wts.length;i++){
				sb.append(wts[i]);
				if(i != wts.length-1){
					sb.append(",");
				}
			}
			String yzxwt = sb.toString();
			form.setYzxwt(yzxwt);
		}else{
			form.setYzxwt("");
		}
		return runUpdate(form);
	}
	
	/** 
	 * @����:ɾ��������Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����03:55:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public int deleteZxjbxx(String[] xhs) throws Exception{
		dao.deleteZxzxjl(xhs);//��ɾ��������ѯ��¼
		return runDelete(xhs);//ɾ�������¼
	}
	
	/** 
	 * @����:��֤������Ϣ�Ƿ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����04:14:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExists(ZxzxjlModel form){
		String num = dao.getRecord(form);
		return Integer.valueOf(num)>0;
	}
	
	/**
	 * @throws Exception 
	 * @throws SQLException  
	 * @����:ά��������ѯ��¼(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-23 ����11:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean whZxzxJl(String[] zjs,String[] xgs,String[] delIds,String xh,User user) throws Exception{
		List<ZxzxjlxxModel> insertList = new ArrayList<ZxzxjlxxModel>();
		List<ZxzxjlxxModel> updateList = new ArrayList<ZxzxjlxxModel>();
		if(null != zjs && zjs.length>0){//�������������			
			for(int i = 0;i<zjs.length;i++){
				ZxzxjlxxModel model = new ZxzxjlxxModel();
				model.setXh(xh);
				String[] zj = zjs[i].split(",");
				for(int j = 0;j<zj.length;j++){
					if(j==0){						
						model.setBh(zj[j]);
					}else if(j==1){
						model.setZxsxm(zj[j]);
					}else if(j==2){
						model.setZxsj(zj[j]);
					}else if(j==3){
						model.setZxdd(zj[j]);
					}else if(j==4){
						model.setZxpg(zj[j]);
					}else if(j==5){
						model.setZxgc(zj[j]);
					}else if(j==6){
						model.setZxfk(zj[j]);
					}else{
						if(zj[j].equalsIgnoreCase("blank")){
							model.setZxth("");
						}else{							
							model.setZxth(zj[j]);
						}
					}
				}
				insertList.add(model);
			}
		}
		if(null != xgs && xgs.length>0){//������޸�����
			for(int i = 0;i<xgs.length;i++){
				ZxzxjlxxModel model = new ZxzxjlxxModel();
				String[] zj = xgs[i].split(",");
				for(int j = 0;j<zj.length;j++){
					if(j==0){						
						model.setId(zj[j]);
					}else if(j==1){
						model.setBh(zj[j]);
					}else if(j==2){
						model.setZxsxm(zj[j]);
					}else if(j==3){
						model.setZxsj(zj[j]);
					}else if(j==4){
						model.setZxdd(zj[j]);
					}else if(j==5){
						model.setZxpg(zj[j]);
					}else if(j==6){
						model.setZxgc(zj[j]);
					}else if(j==7){
						model.setZxfk(zj[j]);
					}else{
						if(zj[j].equalsIgnoreCase("blank")){
							model.setZxth("");
						}else{							
							model.setZxth(zj[j]);
						}
					}
				}
				updateList.add(model);
			}
		}
		boolean result = true;
		boolean result1 = true;
		if((null != zjs && zjs.length>0) || null != xgs && xgs.length>0){
			result = jlxxDao.plInsertOrUpdate(insertList, updateList, user);
		}
		if(null != delIds && delIds.length>0){//�����ɾ������
			result1 = jlxxDao.plsc(delIds);
		}
		return result&&result1;
	}
	
	/** 
	 * @����:��ȡ��ѯ��¼�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-23 ����02:05:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZxjlList(String xh,User user,boolean sfck) throws Exception{
		List<HashMap<String, String>> list = jlxxDao.getZxjlList(xh,user);
		if(sfck){		
			if(null != list && list.size()>0){
				List<HashMap<String, String>> mapList = new ArrayList<HashMap<String,String>>();
				for(HashMap<String, String> map:list){
					HashMap<String, String> mapp = StringUtils.formatMap(map);
					mapList.add(mapp);
				}
				return mapList;
			}else{
				return list;
			}
		}else{
			return list;
		}
		
	}	
	
	/**
	 * 
	 * @����: ��ȡ�����ǼǱ�����Ҫ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-5 ����10:49:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getExportData(String xh,User user){
		return dao.getXsxlzxxx(xh);
	}
	
	/** 
	 * @����:��ȡ��ѯ��¼idList(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-27 ����02:00:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZxIdList(String xh){
		return dao.getZxIdList(xh);
	}
}
