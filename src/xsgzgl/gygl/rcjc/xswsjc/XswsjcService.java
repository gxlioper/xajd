package xsgzgl.gygl.rcjc.xswsjc;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import xgxt.action.Base;
import xgxt.form.User;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XswsjcService extends SuperServiceImpl<XswsjcForm,XswsjcDao> {
	private XswsjcDao dao= new XswsjcDao();
	
	public XswsjcService(){
		super.setDao(dao);
	 }
	 

	/** 
	 * @������
	 * @throws Exception 
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��5��11�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * boolean �������� 
	 */
	public boolean updateXswsjc(XswsjcForm myForm) throws Exception {
		return dao.updateXswsjc(myForm);
	}
	
	public File getHmcFile(String jcrqSta,String jcrqEnd,User user) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		List<HashMap<String,String>> wsjcdjList=dao.getWsjcdj_10344(jcrqSta,jcrqEnd,user);
		data.put("wsjcdjList", wsjcdjList);
		data.put("xn",Base.currXn);
		data.put("xq",Base.getDqxqmc());
		data.put("xxmc",Base.xxmc);
		data.put("jcrqSta",jcrqSta);
		data.put("jcrqEnd",jcrqEnd);
		File file = FreeMarkerUtil.getExcelFile(data, "classpath:templates/gygl", "gygl_wsjcdjtj_10344.xml", "�������ȼ�ͳ��_"+jcrqSta+"-"+jcrqEnd);
		return file;
	}
	
	public List<HashMap<String,String>> getWsjcdj_10344(String jcrqSta,String jcrqEnd,User user){
		return dao.getWsjcdj_10344(jcrqSta,jcrqEnd,user);
	}
	
} 


