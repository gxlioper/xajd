/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-10-8 ����09:26:47 
 */  
package com.zfsoft.xgxt.xtwh.ksdh;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.JsonUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ���ٵ����� 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-10-8 ����09:26:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KsdhAction extends SuperAction<KsdhForm, KsdhService> {
	private KsdhService service = new KsdhService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	/**
	 * 
	 * @�������༭�ҵ�Ӧ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-10-8 ����11:48:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getAppView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user = getUser(request);
		//�ж��Ƿ�������ֵ�����û�оͲ���Ĭ��ֵ
		if(!service.checkExistSz(user)){
			service.insertInto_mrz(user);
		}
		//���ܲ˵���ѯ
		List<HashMap<String, String>> gncdszlist = service.getGncdSzList(user);
		JSONArray json_gncdszlist = JsonUtil.ListToJSON(gncdszlist);
		//�����ѯ
		List<HashMap<String, String>> flheadlist = service.getFlcx_head(user);
		JSONArray json_flheadlist = JsonUtil.ListToJSON(flheadlist);
		//�������ݲ�ѯ
		List<HashMap<String, String>> flContentlist = service.getFlcx(user.getUserType(), user.getUserName());
		JSONArray json_flContentlist = JsonUtil.ListToJSON(flContentlist);
		HashMap<String, JSONArray> datamap = new HashMap<String, JSONArray>();
		datamap.put("gncd", json_gncdszlist);
		datamap.put("flhead", json_flheadlist);
		datamap.put("flContent", json_flContentlist);
		JSONObject JsonObj = JSONObject.fromObject(datamap);
		response.getWriter().print(JsonObj);
		return null;
	}
	
	

	
	/**
	 * 
	 * @����:@gnmkdm ����dm,@gnmkmc ����,��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-10-12 ����03:15:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
    public ActionForward QueryData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	//String gnmkdm = request.getParameter("gnmkdm");
    	KsdhForm ksdh = (KsdhForm)form;
		String gnmkmc = URLDecoder.decode(URLDecoder.decode(ksdh.getGnmkmc(),"UTF-8"),"UTF-8");
		String mkfldm = URLDecoder.decode(URLDecoder.decode(ksdh.getMkfldm(),"UTF-8"),"UTF-8");
		String[] htmlgnmkdm =ksdh.getGnmkms();
		User user = getUser(request);
		List<HashMap<String, String>> flContentlist = service.QueryData(user.getUserType(), gnmkmc, mkfldm, user.getUserName(), htmlgnmkdm);
		JSONArray json_flContentlist = JsonUtil.ListToJSON(flContentlist);
		response.getWriter().print(json_flContentlist);
		return null;
	}
    
    public ActionForward close_saveData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	User user = getUser(request);
    	boolean result = service.del_originalData(user);
    	if(result){
    		KsdhForm ksdh = (KsdhForm)form;
    		String[] gnmkdms = ksdh.getGnmkms();
    		String[] xssxs = ksdh.getXssxs();
    		if(xssxs != null && xssxs.length > 0 && gnmkdms != null && gnmkdms.length >0 ){
    			for (int i = 0; i < xssxs.length; i++) {
        			KsdhForm saveform = new KsdhForm();
        			saveform.setCzsj(GetTime.getTimeByFormat(DATE_FORMAT));
        			saveform.setYhm(user.getUserName());
        			saveform.setCdid(gnmkdms[i]);
        			saveform.setXssx(xssxs[i]);
        			saveform.setYhlx(user.getUserType());
        			try {
        				result = service.runInsert(saveform);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.print(e.getStackTrace());
						response.getWriter().print("����ʧ�ܣ�");
						return null;
					}
        			
    			}
    			if(result){
    				response.getWriter().print("���óɹ���");
    				return null;
    			}
    		}
    		
    	}else{
    		response.getWriter().print("����ʧ�ܣ�");
    		return null;
    	}
    	return null;
    	
    }
    
    /**
     * 
     * @����:��ҳ�ҵ�Ӧ�����ˢ��
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-10-21 ����09:44:43
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward �������� 
     * @throws
     */
    public ActionForward refreshIndexHTML(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	User user = getUser(request);
    	List<HashMap<String, String>> gncdszlist = service.getGncdSzList(user);
		JSONArray json_gncdszlist = JsonUtil.ListToJSON(gncdszlist);
		response.getWriter().print(json_gncdszlist);
    	return null;
    }
}
