/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-19 ����04:35:22 
 */  
package com.zfsoft.xgxt.xlzx.zxswh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xlzx.yysq.zwpg.ZwpgService;
import com.zfsoft.xgxt.xlzx.yysqnew.YysqService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ������ѯ-��ѯʦ�Ű����(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-8-19 ����04:35:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxspbAction extends SuperAction{
	// ��ѯʦ�Ű�-���������Ƿ���޸ģ�1���ǣ�0����
	private static final boolean XLZX_ZXSPB_N = "0".equals(MessageUtil.getText("xlzx.zxspb"));
	private static ZxspbService service = new ZxspbService();

	private static final String url = "xlzx_zxspb_zxspb.do";
	private static final String MESSAGE = "����ά��ʱ��δ��������ݣ�";
	/** 
	 * @����:��ѯʦ�Ű���Ϣ����չʾ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-8-24 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward zxsPbDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String queryDate = request.getParameter("queryDate");
		if (QUERY.equals(doType)){	//��ѯ
			List<HashMap<String, String>>  zxspbInfoList = service.getZxspbAsRl(queryDate);
			JSONArray data = JSONArray.fromObject(zxspbInfoList);
			response.getWriter().print(data);
			return null;
		}
		request.setAttribute("XLZX_ZXSPB_N", XLZX_ZXSPB_N);
		return mapping.findForward("zxsPbDeal");
	}
	
	/** 
	 * @����:��ѯʦ�Ű���Ϣ����չʾ for ѧ��
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-8-24 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public ActionForward zxsPbbForXs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String queryDate = request.getParameter("queryDate");

		if (QUERY.equals(doType)){	//��ѯ
			String xh = getUser(request).getUserName();
			if(!StringUtil.isNull(xh)){
				//����xg_xlzx_csszb����[pbfs]�ֶξ������ĸ���ѯ���Ű෽ʽ 1������  2����ʱ���
				String pbfs = service.getPbfs();
				List<HashMap<String, String>>  zxspbInfoList = null;
				if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
					zxspbInfoList = service.getZxspbAsRlForXs(xh,queryDate);
				}else{
					zxspbInfoList = service.getZxspbAsRlForXsSjd(xh,queryDate);
				}
				
				JSONArray data = JSONArray.fromObject(zxspbInfoList);
				response.getWriter().print(data);
				return null;
			}
		}
		return mapping.findForward("zxsPbbForXs");
	}
	/** 
	 * @����:��ѯʦ�Ű�����
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward zxspbDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		ZxsglService zxsglSv = new ZxsglService();
	 	String date = request.getParameter("date");
	 	String doType = request.getParameter("doType");
	 	HashMap<String, String> zxspbList=new HashMap<String,String>();
	 	if(!StringUtil.isNull(model.getId())){
	 		zxspbList = service.getPbInfoById(model.getId());
	 		date = zxspbList.get("pbdate");
	 	}
	 	//��ȡ�����ڸڿ�ԤԼ����ѯʦ�б�
	 	String status= "1";
		List<HashMap<String,String>> zxsList = zxsglSv.getZxsListXssq(status, date);
	 	request.setAttribute("zxsList", zxsList);
		request.setAttribute("zxspbList", StringUtils.formatData(zxspbList));
		request.setAttribute("date", date);
		if(StringUtils.isNotNull(date)){
			String weekday = service.getWeekdayByDate(date).get("weekday");
			request.setAttribute("weekday", weekday);
			request.setAttribute("week", weekday.substring(2));
		}
		request.setAttribute("doType", doType);
		request.setAttribute("path", "xlzx_zxspb_zxspb.do");
		FormModleCommon.commonRequestSet(request);
		//����xg_xlzx_csszb����[pbfs]�ֶξ�����תҳ�棬�Ű෽ʽ 1������  2����ʱ���
		//�������Ϊ����תԭҳ��
		String pbfs = service.getPbfs();
		request.setAttribute("xxdm", Base.xxdm);
		if( StringUtils.isNull(pbfs) || "1".equals(pbfs)){
			return mapping.findForward("zxspbDetail");
		}else{
			//��ʱ��Σ�ͨ��ҳ�棬�������Ķ�
			List<HashMap<String, String>> sjdList = service.getSjdList();
			//���û��ά��ʱ��α�ת����ʾҳ��
			if(sjdList == null || sjdList.size() == 0){
				request.setAttribute("message", MESSAGE);
				return mapping.findForward("error");
			}
			return mapping.findForward("zxspbDetailSJD");
		}
		
	}
	
	
	/** 
	 * @����:��ѯʦ�Ű�����
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	/*public ActionForward zxspbInfoById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
	 	HashMap<String, String> zxspbList=new HashMap<String,String>();
	 	if(!StringUtil.isNull(model.getId())){
	 		zxspbList = service.getPbInfoById(model.getId());
	 	}
		response.getWriter().print(zxspbList);
		return null;
	}*/
	
	
	/** 
	 * @����:�������ڲ�ѯ�����Ƿ����Ű�
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public ActionForward getPbInfoByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		String flag = "false";
		if(!StringUtil.isNull(model.getPbdate())){
			HashMap<String, String> pbInfo = service.getPbInfoByDate(model.getPbdate());
			if(pbInfo!=null && pbInfo.size()>0){
				flag = "true";
			}
		}
		response.getWriter().print(flag);
		return null;
	}
	
	
	/** 
	 * @����:��ǰ���ڸ���ѯʦ�Ƿ��ԤԼ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-10-11 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public ActionForward getSfkyFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String message ="";
		YysqService yysqSv = new YysqService();
		ZxspbForm model = (ZxspbForm) form;
		String date = model.getPbdate();
		String zgh = model.getZgh();
		String pbfs = service.getPbfs();
		String xh = request.getParameter("xh");
		String id = request.getParameter("id");
		HashMap<String, String> zxspbInfo = null;
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
			zxspbInfo = service.getPbInfoByDateForsjd(date);
		}else{
			zxspbInfo = service.getPbInfoByDate(date);
		}
		//�жϵ�ǰ������ѯʦ�Ƿ��н����Ű�
		if(zxspbInfo!=null && zxspbInfo.size()>0){
			String zghs = zxspbInfo.get("zgh");
			if( StringUtils.isNull(zghs) || zghs.indexOf(zgh)==-1){
				message = "��ѡ���ڸ���ѯʦ�����Ű��У�";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}else{//��ѡ��ѯ����δ�����Ű�
			message = "��ѡ����û�н����Ű࣡";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		//�жϵ�ǰ������ѡѧ���Ƿ��Ѿ�ԤԼ ��
		HashMap<String,String> yysqInfoByXh = null;//yysqSv.getYysqByXhAnddDate(date,xh);
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
			yysqInfoByXh = yysqSv.getYysqByXhAnddDateForSjd(date,xh,id);
		}else{
			yysqInfoByXh = yysqSv.getYysqByXhAnddDate(date,xh);
		}
		if(yysqInfoByXh!=null && yysqInfoByXh.size()>0){
			message = "��ѡѧ����ԤԼ��ǰ��ѡ��ѯ���ڣ�";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		//�жϵ�ǰ������ѯʦ��ԤԼ�����Ƿ�����
		int count=0;//������ԤԼ������
		int kjdrs =0;//���տɽӴ�������
		List yysqInfo = new com.zfsoft.xgxt.xlzx.yysq.YysqService().getYysqByZghAnddDate(date,zgh);
		if(yysqInfo!=null && yysqInfo.size()>0){
			count +=yysqInfo.size();
		}
		if(count!=0){
			//��ȡ���տ�ԤԼ����
			ZxsglService zxsglSv = new ZxsglService();
			HashMap<String, String> zxsInfo = null;//zxsglSv.getZxsInfoByZgh(zgh);
			if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
				List<HashMap<String, String>> tempList = zxsglSv.getZxsInfoByZghForsjdYy(new String[]{zgh}, null, date);
				if(tempList != null && tempList.size() > 0){
					zxsInfo = tempList.get(0);
				}
			}else{
				zxsInfo = zxsglSv.getZxsInfoByZgh(zgh);
			}
			if(zxsInfo!=null && zxsInfo.size()>0){
				kjdrs += Integer.parseInt(zxsInfo.get("kjdrs"));
			}
			if(count>=kjdrs){
				message="��ѡ���ڸ���ѯʦ��Լ����";
			}
		}
		JSONObject json = getJsonMessage(message);
		List<HashMap<String, String>> sjddmList = new ArrayList<HashMap<String,String>>();
		if(StringUtils.isNull(message) && (StringUtils.isNotNull(pbfs) && "2".equals(pbfs))){
			 sjddmList = new com.zfsoft.xgxt.xlzx.yysq.YysqService().getXsYySjd(date, zgh, xh);
			
		}
		json.put("sjddmList", JSONArray.fromObject(sjddmList));
		json.put("xqmc",service.getXqmc(date, zgh));
		response.getWriter().print(json);
		return null;
	}
	/** 
	 * ��ǰ���ڿ�ԤԼ��ѯʦ
	 */
	public ActionForward getOkZxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		YysqService yysqSv = new YysqService();
		ZxspbForm model = (ZxspbForm) form;
		String date = model.getPbdate();
		String id = model.getId();
		String xh = request.getParameter("xh");
		Map<String,String> rs = new HashMap<String, String>();
		String pbfs = service.getPbfs();
		HashMap<String, String> yyzxInfo =  new YysqService().getYyzxDetail(id);
		//�жϵ�ǰ������ѡѧ���Ƿ��Ѿ�ԤԼ ��
		HashMap<String,String> yysqInfoByXh = yysqSv.getYysqByXhAnddDateId(date,xh,id);
		if(yysqInfoByXh!=null && yysqInfoByXh.size()>0){
			rs.put("message", "��ѡѧ����ԤԼ��ǰ��ѡ��ѯ���ڣ�");
			JSONObject json = JSONObject.fromObject(rs); 
			response.getWriter().print(json);
			return null;
		}
		HashMap<String, String> zxspbInfo =  (StringUtils.isNotNull(pbfs) && "2".equals(pbfs)) ? service.getPbInfoByDateForsjd(date) : service.getPbInfoByDate(date);
		//�жϵ�ǰ������ѯʦ�Ƿ��н����Ű�
		if(zxspbInfo!=null && zxspbInfo.size()>0){
			String[] zghs = zxspbInfo.get("zgh").split(",");
			List<String> zghsList = new ArrayList<String>();
			for (int i = 0; i < zghs.length; i++) {
				String zgh = zghs[i];
				//�жϵ�ǰ������ѯʦ��ԤԼ�����Ƿ�����
				int count=0;//������ԤԼ������
				int kjdrs =0;//���տɽӴ�������
				List yysqInfo = new com.zfsoft.xgxt.xlzx.yysq.YysqService().getYysqByZghAnddDate(date,zgh);
				if(yysqInfo!=null && yysqInfo.size()>0){
					count +=yysqInfo.size();
				}
				if(count!=0){
					//��ȡ���տ�ԤԼ����
					ZxsglService zxsglSv = new ZxsglService();
					HashMap<String, String> zxsInfo = null;
					if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
						List<HashMap<String, String>> tempList = zxsglSv.getZxsInfoByZghForsjdYy(new String[]{zgh}, null, date);
						if(tempList != null && tempList.size() > 0){
							zxsInfo = tempList.get(0);
						}
					}else{
						zxsInfo = zxsglSv.getZxsInfoByZgh(zgh);
					}
					//zxsglSv.getZxsInfoByZgh(zgh)
					if(zxsInfo!=null && zxsInfo.size()>0){
						String kjdrsTemp = zxsInfo.get("kjdrs");
						kjdrs += Integer.parseInt(kjdrsTemp == null ? "999999999" : kjdrsTemp);
					}
					if(count>=kjdrs && !yyzxInfo.get("zgh").equals(zgh)){//count>=kjdrs,�޴��bug
						// ��ѡ���ڸ���ѯʦ��Լ����
						continue;
					}
				}
				zghsList.add(zgh);
			}
			ZxsglService zxsglService = new ZxsglService();
			if(zghsList.size() > 0){
//				YysqService yysqService = new YysqService();
//				boolean isZxs = yysqService.isZxs(user.getUserName()); // ��ǰ�û�����ѯʦ
				String[] zghsNew = zghsList.toArray(new String[]{});
				// ֻ��ʾ��ǰ�û�
//				if(isZxs){
//					boolean pb_Y = zghsList.contains(user.getUserName()); // ��ǰ�û����Ű�
//					if(pb_Y){
//						zghsNew = new String[]{ user.getUserName() };
//					}else{
//						rs.put("message", "��ѡ����û�н����Ű࣡");
//						JSONObject json = JSONObject.fromObject(rs); 
//						response.getWriter().print(json);
//						return null;
//					}
//				}
				List<HashMap<String,String>> zxslist = null;//
				if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
					zxslist = zxsglService.getZxsListYyfkForSjd(zghsNew, date);
				}else{
					zxslist = zxsglService.getZxsListYyfk(zghsNew, date);
				}
				JSONArray dataList = JSONArray.fromObject(zxslist);
				response.getWriter().print(dataList);
				return null;
			}else{
				rs.put("message", "��ѡ����û�н����Ű࣡");
				JSONObject json = JSONObject.fromObject(rs); 
				response.getWriter().print(json);
				return null;
			}
		}else{//��ѡ��ѯ����δ�����Ű�
			rs.put("message", "��ѡ����û�н����Ű࣡");
			JSONObject json = JSONObject.fromObject(rs); 
			response.getWriter().print(json);
			return null;
		}
	}
	

	/** 
	 * @����:�������ڲ�ѯ�������Ű����ѯʦ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-24 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public ActionForward getPbZxsListByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglService zxsglSv = new ZxsglService();
		ZxspbForm model = (ZxspbForm) form;
		List<HashMap<String,String>> zghInfoList= new ArrayList<HashMap<String,String>>();
		String pbfs = service.getPbfs();
		if(StringUtils.isNull(pbfs)|| "1".equals(pbfs)){
			if(!StringUtil.isNull(model.getPbdate())){
				HashMap<String, String> zxspbInfo = service.getPbInfoByDate(model.getPbdate());
				if(zxspbInfo!=null && zxspbInfo.size()>0){
					String _zgh = zxspbInfo.get("zgh");
					if(!StringUtil.isNull(_zgh)){
						String[] zgh = _zgh.split(",");
						zghInfoList = zxsglSv.getZxsInfoByZgh(zgh);
					}
				}
			}
		}else{
			if(StringUtils.isNotNull(model.getPbdate())){
				zghInfoList = service.getZxsjbxxForSjdPb(model.getPbdate());
			}
		}
	
		response.getWriter().print(JSONArray.fromObject(zghInfoList));
		return null;
	}
	/** 
	 * @����:�������ڲ�ѯ�����A�s�f��
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-24 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward getYysmByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		if(!StringUtil.isNull(model.getPbdate())){
			HashMap<String, String> zxspbInfo = service.getPbInfoByDate(model.getPbdate());
			response.getWriter().print(JSONArray.fromObject(zxspbInfo));
		}
		return null;
	}
	
	/** 
	 * @����:�����Ű���Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveZxspbInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		if(!StringUtil.isNull(doType) && doType.equals("add")){
			ZxspbForm model = (ZxspbForm) form;
			try {
				boolean flag = service.saveZxspbInfo(model);
				response.getWriter().print(flag);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("zxspbDetail");
	}
	
	/** 
	 * @����:���������Ű���Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveBatchZxspbInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		
		String doType = request.getParameter("doType");
		String zgh = model.getZgh();
		String bz = model.getBz();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		List<HashMap<String, String>> pbdateInfoList = service.getDateByWeekBetweenDate(startDate, endDate);
		if(pbdateInfoList!=null && pbdateInfoList.size()>0){
			String[] pbdate = new String[pbdateInfoList.size()];
			for(int i=0;i<pbdateInfoList.size();i++){
				pbdate[i] = pbdateInfoList.get(i).get("tdate");
			}
			if(StringUtils.isArrayNotNull(pbdate) && !StringUtil.isNull(zgh)){
				try {
					boolean flag = service.saveBatchZxspbInfo(pbdate, zgh, bz);
					response.getWriter().print(flag);
					return null;
				} catch (Exception e) {
					e.printStackTrace();
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("zxspbDetail");
	}
	
	/** 
	 * @����:�޸��Ű���Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateZxspbInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;

		try {
			boolean flag = service.updateZxspbInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/** 
	 * @����:�޸��Ű���Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delZxspbById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;

		try {
			Boolean flag= false;
			int count = service.delZxspbById(model.getId());
			if(count>0){
				flag = true;
			}
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/** 
	 * @����:������ѯʦ��Ų�ѯ��ѯʦ���Ű����
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward getZxspbInfoByZgh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		List<HashMap<String,String>> zxsPbInfoList = new ArrayList<HashMap<String,String>>();
		try {
			if(!StringUtil.isNull(model.getZgh())){
				zxsPbInfoList = service.getZxspbInfoByZgh(model.getZgh());
				JSONArray dataList = JSONArray.fromObject(zxsPbInfoList);
				response.getWriter().print(dataList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * @����:�������ڻ�ȡ���ڼ�
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward getWeekdayByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String date = request.getParameter("date");
		try {
			HashMap<String, String> m = service.getWeekdayByDate(date);
			String week = (String)m.get("weekday");
			response.getWriter().print(week);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @����: ���ϳ���ѧԺ���Ի���������
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-6-29 ����01:49:23
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
	public ActionForward exportConfig (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZxspbService service = new ZxspbService();
		CommService comService = new CommService();
		ZxspbForm exporModel = new ZxspbForm();
		comService.getModelValue(exporModel, request);
		response.reset();//����ײ��Ŀհ���
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+new String("����ѯʦ�Ű���Ϣ".getBytes("gb2312"),"iso-8859-1")+".xls");
		service.exportConfig_11527(exporModel,response.getOutputStream());
		return null;
	}
	
	/**
	 * 
	 * @����: ��ʼ����ʱ����Ű�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-20 ����03:43:44
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
	public ActionForward initSjdPb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pbdate = request.getParameter("pbdate");
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		JSONArray jsonList = service.createSjdPbInitList(pbdate);
		List<HashMap<String, String>>  xqList = new ZxsglService().getXq();
		JSONObject jsonMap = JSONObject.fromObject(dataMap);
		jsonMap.put("xqList", JSONArray.fromObject(xqList));
		jsonMap.put("dataList", jsonList);
		jsonMap.put("sjdList", JSONArray.fromObject(service.getSjdList()));
		response.getWriter().print(jsonMap);
		return null;
	}
	
	/**
	 * 
	 * @�����������Ű���Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-23 ����10:59:57
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
	public ActionForward savePbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		model.setId(request.getParameter("pbid"));
		ZxspbService transService = TransactionControlProxy.newProxy(new ZxspbService());
		boolean rs = transService.savePbxxb(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ��ѯʱ������������¸�ֵ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-30 ����02:53:07
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
	public ActionForward changeSjdSelect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String date = request.getParameter("date");
		String zgh = request.getParameter("zgh");
		String xh = request.getParameter("xh");
		List<HashMap<String, String>> sjddmList = new com.zfsoft.xgxt.xlzx.yysq.YysqService().getXsYySjd(date, zgh, xh);
		JSONArray json = JsonUtil.ListToJSON(sjddmList);
		response.getWriter().print(json);
		return null;
	}
	
	public ActionForward xlzxcns(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String xh = getUser(request).getUserName();
		ZwpgService zwpgService = new ZwpgService();
		HashMap<String, String> map =  zwpgService.getInfoByXh(xh);
		request.setAttribute("map", map);
		return mapping.findForward("xlzxcns");
	}
}
