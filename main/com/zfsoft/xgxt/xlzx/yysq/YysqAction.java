/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-13 ����08:54:28 
 */  
package com.zfsoft.xgxt.xlzx.yysq;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.xlzx.yysq.xstxxx.XstxxxAction;
import com.zfsoft.xgxt.xlzx.yysq.xstxxx.XstxxxForm;
import com.zfsoft.xgxt.xlzx.zxxzwh.ZxxzwhForm;
import com.zfsoft.xgxt.xlzx.zxxzwh.ZxxzwhService;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xlzx.yysq.zwpg.ZwpgService;
import com.zfsoft.xgxt.xlzx.zxswh.ZxsglService;
import com.zfsoft.xgxt.xlzx.zxswh.ZxspbService;
import com.zfsoft.xgxt.xlzx.zxyycl.ZxyyclService;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlAction;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlModel;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ԤԼ����
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-9-13 ����08:54:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class YysqAction extends SuperAction{

	private static YysqService service = new YysqService();

	/**
	 * ����������ѯԤԼ������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	 */
	private static final String XLJKYYSQ = "xljkyysq";
	private List<HashMap<String,String>> jbxxList = null;
	BdpzService bdpzService = new BdpzService();
	/** 
	 * @����:ѧ��ԤԼ�����ѯҳ�桢ԤԼ��ѯ�����ѯҳ��
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 * @throws 
	 */
	public ActionForward yysqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm myForm = (YysqForm) form;
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
		if(user.getUserStatus().equals("stu")){
			if(service.isExists(user.getUserName())){
				request.setAttribute("ydj", "1");
			}else{
				request.setAttribute("ydj", "0");
			}
		}

		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> YysqInfoList = service.queryYyzxInfoList(myForm, user);
			
			JSONArray dataList = JSONArray.fromObject(YysqInfoList);
			response.getWriter().print(dataList);
			return null;
		}
	
		if(userStatus.equals("stu")){
			request.setAttribute("path", "xlzx_yysq_yysq.do");
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("yysqManage");
		}else{
			request.setAttribute("path", "xlzx_zxyycl_zxyycl.do");
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("zxyyclManage");
		}
	}
	
	
	/** 
	 * @����:ѧ��������Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-8-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 * @throws 
	 */
	public ActionForward xspjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm myForm = (YysqForm) form;
		ZxyyclService zxyyclSv = new ZxyyclService();
		HashMap<String, String> zxInfo  = zxyyclSv.getXlzxInfoByYyId(myForm.getId());
		request.setAttribute("zxInfo", StringUtils.formatData(zxInfo));
		return mapping.findForward("xspjInfo");
	}
	/** 
	 * @����:����������ѯԤԼ��ѯ����
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-8-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 * @throws 
	 */
	public ActionForward yyzxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm myForm = (YysqForm) form;
		String id = request.getParameter("id");
		String zxrq = request.getParameter("zxrq");
		String doType = request.getParameter("doType");
		String pbfs = new ZxspbService().getPbfs();
		int yjdrs = 0;
		String date = "";
		HashMap<String, String> yyzxInfo = new HashMap<String,String>();
		if(!StringUtil.isNull(id)){
			yyzxInfo = service.getYyzxDetail(id);
			if(StringUtils.isNull(zxrq)){
				if(!StringUtils.isNull(yyzxInfo.get("zxrq"))){
					date = yyzxInfo.get("zxrq");
				}else{
					date = yyzxInfo.get("yyzxrq");
				}
			}else {
				date = zxrq;
				yyzxInfo.put("zxrq", zxrq);
			}
			List zxsInfo = service.getYysqByZghAnddDate(date,yyzxInfo.get("zgh"));
			if(zxsInfo!=null && zxsInfo.size()>0){
				yjdrs = zxsInfo.size();
			}
		}
		request.setAttribute("yjdrs", yjdrs);
		request.setAttribute("yyzxInfo", StringUtils.formatData(yyzxInfo));
		request.setAttribute("doType", doType);
		request.setAttribute("pbfs", pbfs);
		String currxn=Base.currXn;
		String currxq=Base.getDqxqmc();
		request.setAttribute("currxn", currxn);
		request.setAttribute("currxq", currxq);
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if(!"view".equals(doType) && (StringUtils.isNotNull(pbfs) && "2".equals(pbfs))){
			request.setAttribute("sjddmList", service.getXsYySjd(yyzxInfo.get("yyzxrq"), yyzxInfo.get("zgh"), yyzxInfo.get("xh")));
		}
		if(userStatus.equals("stu")){
			XstxxxForm xstxxxForm = XstxxxAction.getModel(user.getUserName());
			request.setAttribute("xstxxx", xstxxxForm);
			BeanUtils.copyProperties(myForm,service.getModel(id));

			//ѧ���Ƿ��״�����
			String count = service.getCountJg(user.getUserName());
			request.setAttribute("sfscsq", "0".equals(count)?"0":"1");

			return mapping.findForward("yyzxDetail");
		}else{
			// ��ѧ����Ϣ
			if(yyzxInfo!=null && yyzxInfo.size()>0 && StringUtils.isNotNull(yyzxInfo.get("xh"))){
				
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(yyzxInfo.get("xh"));
				request.setAttribute("jbxx", xsjbxx);
			}
			
			//ѧ��������Ϣ��ʾ����
			jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
			request.setAttribute("jbxxList", jbxxList);
			
			return mapping.findForward("zxyyclDetail");
		}
	}
	
	/** 
	 * @����:ԤԼ�����Ƿ�Լ��
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-8-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 * @throws 
	 */
	public ActionForward getSfymFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String date = request.getParameter("date");
		String sfym="";

		ZxspbService zxspbSv = new ZxspbService();
		String pbfs = zxspbSv.getPbfs();
		String[] pbzgh = null;
		if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
			HashMap<String, String> zxspbInfo = zxspbSv.getPbInfoByDate(date);
			if(zxspbInfo==null || zxspbInfo.size()<=0){
				return null;
			}
			String _pbzgh = zxspbInfo.get("zgh");
			pbzgh = _pbzgh.split(",");
		}else{
			List<HashMap<String,String>> zxsJbxxList = zxspbSv.getZxsjbxxForSjdPb(date);
			List<String> zghList = new ArrayList<String>();
			for (HashMap<String, String> map : zxsJbxxList) {
				zghList.add(map.get("zgh"));
			}
			pbzgh = zghList.toArray(new String[]{});
		}
		
		
		//�жϵ�ǰ���������Ѱ�����ѯʦ��ԤԼ�����Ƿ�����
		int count=0;//������ԤԼ������
		int kjdrs =0;//���տɽӴ�������
		for(int i=0;i<pbzgh.length;i++){
			List yysqInfo = service.getYysqByZghAnddDate(date,pbzgh[i]);
			if(!yysqInfo.isEmpty()){
				count +=yysqInfo.size();
			}
		}
		if(count!=0){
			//��ȡ���տ�ԤԼ����
			if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
				ZxsglService zxsglSv = new ZxsglService();
				List<HashMap<String,String>> zxsInfoList = zxsglSv.getZxsInfoByZgh(pbzgh);
				if(!zxsInfoList.isEmpty()){
					for(int j=0;j<zxsInfoList.size();j++){
						if(zxsInfoList.get(j).get("kjdrs") != null){
							kjdrs += Integer.parseInt(zxsInfoList.get(j).get("kjdrs"));
						}
					}
				}
			}else{
				kjdrs = Integer.parseInt(service.getSjdYySx(date));
			}
			
			if(count>=kjdrs){
				sfym="Y";
			}
		}
		
		response.getWriter().print(sfym);
		return null;
	}
	
	
	/** 
	 * @����:ԤԼ���ڶ�Ӧ����ѯʦ�Ű���Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-8-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 * @throws 
	 */
	public ActionForward yysqDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglService zxsglSv = new ZxsglService();
		String date = request.getParameter("date");
		String doType = request.getParameter("doType");
		ZxspbService zxspbSv = new ZxspbService();
		String pbfs = new ZxspbService().getPbfs();
		HashMap<String, String> zxspbInfo = null;
		if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
			zxspbInfo = zxspbSv.getPbInfoByDate(date);
		}else{
			zxspbInfo = zxspbSv.getPbInfoByDateForsjd(date);
		}
	    
	
		List<HashMap<String,String>> zxsInfoList  = new ArrayList<HashMap<String,String>>();
		String xq = request.getParameter("xxxq");
		User user = getUser(request);
		String xh = user.getUserName();
		HashMap<String,String> yysqInfo = service.getYysqByXhAnddDate(date,xh);
		if(yysqInfo!=null && yysqInfo.size()>0){//�鿴ԤԼ��Ϣ
			if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
				zxsInfoList.add(zxsglSv.getZxsInfoByZgh(yysqInfo.get("zgh")));
			}else{
				zxsInfoList = zxsglSv.getZxsInfoByZghForsjdYy(new String[]{yysqInfo.get("zgh")}, null, date);
			}
			
			request.setAttribute("yysqInfo", StringUtils.formatData(yysqInfo));
		}else{//����ԤԼ��Ϣ
			if(zxspbInfo!=null && zxspbInfo.size()>0){
				String _pbzgh = zxspbInfo.get("zgh");
				String[] pbzgh = _pbzgh.split(",");
				
				if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
					zxsInfoList = zxsglSv.getZxsInfoByZgh(pbzgh,xq);
				}else{
					zxsInfoList = zxsglSv.getZxsInfoByZghForsjdYy(pbzgh, xq, date);	
				}
				
			}
		}
		//������ѯʦ��ǰ���ڵ��ѽӴ�����
		if(zxsInfoList!=null && zxsInfoList.size()>0){
			for(int i=0;i<zxsInfoList.size();i++){
				String zgh = zxsInfoList.get(i).get("zgh");
				List zxsInfo = service.getYysqByZghAnddDate(date,zgh);
				int yjdrs=0;
				if(zxsInfo!=null && zxsInfo.size()>0){
					yjdrs = zxsInfo.size();
				}
				zxsInfoList.get(i).put("yjdrs", String.valueOf(yjdrs));
			}
		}

		String currxn=Base.currXn;
		String currxq=Base.getDqxqmc();
		request.setAttribute("currxn", currxn);
		request.setAttribute("currxq", currxq);
		request.setAttribute("zxspbInfo", StringUtils.formatData(zxspbInfo));
		request.setAttribute("zxsInfoList", zxsInfoList);
		ZxsglService zxsservice = new ZxsglService();
		request.setAttribute("xqList",zxsservice.getXq());
		request.setAttribute("doType", doType);
		request.setAttribute("path", "xlzx_yysq_yysq.do");
		request.setAttribute("pbfs", pbfs);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yysqDetail");
	}
	
	/** 
	 * @����:����ԤԼ������Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-8-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 * @throws 
	 */
	public ActionForward addYysqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			YysqForm model = (YysqForm) form;
			User user = getUser(request);
			HashMap<String,String> yysqInfo = service.getYysqByCn(user.getUserName(),model.getZgh(),model.getYyzxrq());
			String currxn=Base.currXn;
			String currxq=Base.getDqxqmc();
			request.setAttribute("currxn", currxn);
			request.setAttribute("currxq", currxq);
			yysqInfo.put("xh",  user.getUserName());
			yysqInfo.put("zgh",  model.getZgh());
			yysqInfo.put("yyzxrq",  model.getYyzxrq());
			String pbfs = new ZxspbService().getPbfs();
			request.setAttribute("pbfs", pbfs);
			if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
				List<HashMap<String, String>> sjddmList = service.getXsYySjd(model.getYyzxrq(), model.getZgh(),null);
				request.setAttribute("sjddmList",sjddmList );
				String flag = "0";
				if(sjddmList.size() > 0){
					flag="1";
				}
				request.setAttribute("flag", flag);
			}
			//��ȡѧ����Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			request.setAttribute("jbxx", xsjbxx);
			jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
			request.setAttribute("jbxxList", jbxxList);
			yysqInfo.put("xstell",  xsjbxx.get("sjhm"));

			//ѧ����д��Ϣ
			XstxxxForm xstxxxForm = XstxxxAction.getModel(user.getUserName());
			request.setAttribute("xstxxx", xstxxxForm);


			request.setAttribute("yysqInfo", StringUtils.formatData(yysqInfo));
			//ѧ���Ƿ��״�����
			String count = service.getCountJg(user.getUserName());
			request.setAttribute("sfscsq", "0".equals(count)?"0":"1");
			return mapping.findForward("addYysqInfo");
	}
	
	/** 
	 * @����:ѡ��ԤԼ��ѯ����ѯʦ������Ա������
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-8-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 * @throws 
	 */
	public ActionForward addYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm model = (YysqForm) form;
		HashMap<String, String> zxsInfo = new HashMap<String, String>();
		ZxsglService zxsSv = new ZxsglService();
		// ѡ��ѧ��
		String xh = StringUtil.isNull(request.getParameter("xh"))? model.getXh() : request.getParameter("xh");
		if(StringUtils.isNotNull(xh) ){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		User user = getUser(request);
		String zgh = user.getUserName();
		// ��ѯʦ�����Ϣ
		zxsInfo = zxsSv.getZxsInfoByZgh(zgh);

		// ���䵽ѧԺ
		if(zxsInfo == null || zxsInfo.size()==0){
			 String msg = "����ѯʦû�иò���Ȩ�޵ģ���ȷ�ϣ�";
			 request.setAttribute("yhInfo", msg);
			 return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("zxsInfo", zxsInfo);
		
		//ѧ��������Ϣ
		String path = "xlzx_yysq.do?method=addYyzxInfo";
		if(StringUtils.isNotNull(zxsInfo.get("zgh"))){
			path += "&zgh=" + zxsInfo.get("zgh");
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("addYyzxInfo");
	}
	/** 
	 * @����:����ԤԼ������Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-8-14 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 * @throws 
	 */
	public ActionForward saveYysqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			YysqForm model = (YysqForm) form;
			if(StringUtils.isNotNull(model.getYyzxzt())){
				model.setYyzxzt(URLDecoder.decode((URLDecoder.decode(model.getYyzxzt(),"UTF-8")),"UTF-8"));
			}
			if(StringUtils.isNotNull(model.getYyzxxq())){
				model.setYyzxxq(URLDecoder.decode((URLDecoder.decode(model.getYyzxxq(),"UTF-8")),"UTF-8"));
			}
			if(StringUtils.isNotNull(model.getBczxwt())){
				model.setYyzxzt(URLDecoder.decode((URLDecoder.decode(model.getBczxwt(),"UTF-8")),"UTF-8"));
			}
			if(StringUtils.isNotNull(model.getZxhzt())){
				model.setYyzxxq(URLDecoder.decode((URLDecoder.decode(model.getZxhzt(),"UTF-8")),"UTF-8"));
			}
			try {
				boolean flag = service.saveYysqInfo(model);
				response.getWriter().print(flag);
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return null;
	}
	
	/**
	 * 
	 * @����:ɾ��ԤԼ��ѯ��Ϣ(��ʦ׷�ӵ���ѯ��δ��ѯ��)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-28 ����02:23:40
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward delYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// �h����Ո
			int count = service.delYyzxInfo(values.split(","));
			String message = "";
			if(count > 0){
				message = "�ɹ�ɾ����<font color='green'>&nbsp;"+count+"&nbsp;</font>������";

			}else{
				message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			}
			response.getWriter().print(getJsonMessage(message));
			
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * @����:�޸ġ�ȡ�����÷���
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-9-1 ����11:35:40
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
	public ActionForward updateYysqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			YysqForm model = (YysqForm) form;
			String doType = request.getParameter("doType");
			//ѧ���޸�ԤԼ������Ϣʱ����if�������ȡ����������
			if("update".equalsIgnoreCase(doType)){
				//�˴����ڴ����޸����ݴ������ݿ����루�ֶ�Ϊ����ѯ���⡢��ѯ��Ҫ��
				if(StringUtils.isNotNull(model.getYyzxzt())){
					model.setYyzxzt(URLDecoder.decode((URLDecoder.decode(model.getYyzxzt(),"UTF-8")),"UTF-8"));
				}
				if(StringUtils.isNotNull(model.getYyzxxq())){
					model.setYyzxxq(URLDecoder.decode((URLDecoder.decode(model.getYyzxxq(),"UTF-8")),"UTF-8"));
				}
				if(StringUtils.isNotNull(model.getBczxwt())){
					model.setBczxwt(URLDecoder.decode((URLDecoder.decode(model.getBczxwt(),"UTF-8")),"UTF-8"));
				}
				if(StringUtils.isNotNull(model.getZxhzt())){
					model.setZxhzt(URLDecoder.decode((URLDecoder.decode(model.getZxhzt(),"UTF-8")),"UTF-8"));
				}
			}
			try {
				boolean flag = service.updateYysqInfo(model);
				
				// ���³ɹ��� 4ԤԼ�ɹ�(ѧ��ȡ��)��ɾ����ѯ
				if(flag && "4".equals(model.getStatus())){
					ZxyyclService zxyyclService = new ZxyyclService();
					String[] yyids = new String[]{model.getId()};
					int count = zxyyclService.delZxInfoByYyid(yyids);
					if(count>0){
						flag = true;
					}else{
						flag = false;
					}
				}

				response.getWriter().print(flag);
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return null;
	}

	/**
	 * 
	 * @����:����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-28 ����05:49:59
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YysqForm model = (YysqForm) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> YysqInfoList = service.queryYyzxInfoList(model, user);

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(YysqInfoList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @����:��ѯ��֪��ʾ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/12/27 9:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward zxxz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YysqForm model = (YysqForm) form;
		ZxxzwhForm zxxzwhForm = new ZxxzwhService().getModel();
		request.setAttribute("zxxzwhForm",StringUtils.formatData(zxxzwhForm));
		return mapping.findForward("zxxz");
	}

}
