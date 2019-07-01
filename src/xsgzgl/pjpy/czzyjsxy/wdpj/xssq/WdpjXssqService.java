package xsgzgl.pjpy.czzyjsxy.wdpj.xssq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.pjpy.czzyjsxy.zhcp.PjpyZhcpDAO;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_ѧ������_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class WdpjXssqService extends xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqService  {

	WdpjXssqDAO dao = new WdpjXssqDAO();

	/**
	 * ��ȡѧ�������۲���Ϣ
	 */
	public List<Object> getZcxxByZq(WdpjXssqModel model, User user) throws Exception {
		
		
		String[]orderByArr={"������[��]","������[��]","������[��]","������[��]","������[��]","������[��]","�۲��[��]","�۲��[��]",
				"��������[��]","��������[��]","�۲�����[��]","�۲�����[��]","�ȼ�[��]","�ȼ�[��]","��ע[��]","��ע[��]","�۲��ܷ�",
				"�۲�ְ༶����"};
		
		String xh=user.getUserName();
		
		PjpyZhcpDAO dao=new PjpyZhcpDAO();
		
		List<Object>zcList=dao.getZcByZqList(xh);
		
		// title
		String[]topArr=(String[])zcList.get(0);
		// value
		String[]valueArr=(String[])zcList.get(1);
			
		HashMap<String,String>leftMap=new HashMap<String,String>();
		HashMap<String,String>rightMap=new HashMap<String,String>();
			
		List<Object>list=new ArrayList<Object>();
		
		HashMap<String,Object>infoMap=new HashMap<String,Object>();
		
		for(int i=1;i<=orderByArr.length;i++){
			
			for(int j=1;j<=topArr.length;j++){
				
				if(orderByArr[i-1].equalsIgnoreCase(topArr[j-1])){
					
					if(i%2==1 && i!=1){
						
						infoMap=new HashMap<String,Object>();
					}
					
					if(i%2==1){
						leftMap=new HashMap<String,String>();
						leftMap.put("top", topArr[j-1]);
						leftMap.put("value", valueArr[j-1]);
						infoMap.put("left", leftMap);
						
					}else if(i%2==0){
						rightMap=new HashMap<String,String>();
						rightMap.put("top", topArr[j-1]);
						rightMap.put("value", valueArr[j-1]);
						infoMap.put("right", rightMap);
						
					}
					
					if(i%2==0){
						
						list.add(infoMap);
					}
					
				}
			}
		}
		
		return list;
	}

}
