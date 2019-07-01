package xsgzgl.gygl.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zfsoft.xgxt.gygl.gywp.GywpxxDAO;

import common.GlobalsVariable;

public class GyglNewInter {
	
	public List<HashMap<String, Object>> getStuGyAllList(String xh) {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "��Ԣ��Ϣ");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, getStuGyxxList(xh));
		
		rs.add(map);
		List<String[]> list=getQswpList(xh);
		//listΪ0����û��ά��xg_gygl_wfxy_qswpxxb������Ϋ��ѧԺ
		if(list.size()!=0){
			map = new HashMap<String, Object>();
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "������Ʒ��Ϣ");
			String[] title = new String[]{"��Ʒ����","��������","��Ʒ���","����","�Ƿ��ںϸ���","�Ƿ����"};
			list.add(0, title);
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
			rs.add(map);
		}
		return rs;
	}
	
	/** 
	 * @����:(��ȡѧ��������ƷList)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����05:04:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<String[]> �������� 
	 * @throws 
	 */
	private List<String[]> getQswpList(String xh) {
		GywpxxDAO dao=new GywpxxDAO();
		List<String[]> rs=dao.getQswpList(xh);
		return rs;
	}

	public List<String[]> getStuGyxxList(String xh) {
		GyglNewInit init = new GyglNewInit();		
		GyglNewDAO dao = new GyglNewDAO();		
		
		String[] title = new String[]{};
		String[] out = new String[]{};

		if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//У����԰��
			title = new String[]{"У��","԰��","¥��","���","���Һ�", "��λ��", "���ҵ绰"};
			out = new String[]{"xqmc","yqmc","ldmc","chmc","qsh","cwh", "qsdh"};
		}else if(!"0".equals(GyglNewInit.XQBJ)){//У��
			title = new String[]{"У��","¥��","���","���Һ�", "��λ��", "���ҵ绰"};
			out = new String[]{"xqmc","ldmc","chmc","qsh","cwh", "qsdh"};
		}else if(!"0".equals(GyglNewInit.YQBJ)){//԰��
			title = new String[]{"԰��","¥��","���","���Һ�", "��λ��", "���ҵ绰"};	
			out = new String[]{"yqmc","ldmc","chmc","qsh","cwh", "qsdh"};
		}else{
			title = new String[]{"¥��","���","���Һ�", "��λ��", "���ҵ绰"};	
			out = new String[]{"ldmc","chmc","qsh","cwh", "qsdh"};
		}
		
		List<String[]> rs = dao.getStuGyxxList(xh,out);//ѧ���ɼ��б�
		rs.add(0, title);
		return rs;
	}
}
