package xsgzgl.xtwh.general.cxjgpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_��ѯ�������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class CxjgpzService extends CommService{

	CxjgpzDao dao = new CxjgpzDao();
	
	//δ�����ֶ�
	public List<HashMap<String, String>> getWpzzdlist(CxjgpzForm model) throws Exception{		
		return dao.getWpzzdlist(model);
	}
	
	//�������ֶ�
	public List<HashMap<String, String>> getYpzzdlist(CxjgpzForm model) throws Exception{	
		return dao.getYpzzdlist(model);
	}
	
	//���������ֶ�
	public boolean bcCxjgpz(CxjgpzForm model) throws Exception{
		//�Ȱ������ֶ�����Ϊδ��ʾ
		boolean flag = false;
		flag=dao.xgCxjgSfxs();
		flag = dao.bcCxjg(model);
		return flag;
	}
	
	//��ȡһ������
	public HashMap<String, String> getZd(CxjgpzForm model){	
		return dao.getZd(model);
	}
	
	//���������ֶ�
	public boolean xgZdmc(CxjgpzForm model) throws Exception{
		boolean flag = false;
		flag = dao.xgZdmc(model);
		return flag;
	}
	
	//�������ֶ�
	public ArrayList<String[]> getXsxxlist(CxjgpzForm model) throws Exception{	
		return dao.getXsxxlist(model);
	}
}
