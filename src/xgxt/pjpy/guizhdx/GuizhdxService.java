package xgxt.pjpy.guizhdx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.lsxy.LsxyPjpyService;
import xgxt.pjpy.nbcs.pxpj.PxpjNbcsService;
import xgxt.pjpy.ntzydx.PjpyNtzydxService;
import xgxt.pjpy.tginterface.PjpyCommonInterface;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;


/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���ݴ�ѧ����
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author �����
 * @version 1.0
 */
public class GuizhdxService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	GuizhdxDAO dao = new GuizhdxDAO();
	
	
	/**
	 * ��ȡ ĳ��İ�����
	 * @param bjdm
	 * @return
	 * @throws SQLException
	 */
	public String getBzr(String bjdm) throws SQLException {
		
		return dao.getBzr(bjdm);
	}
	
	
	/**
	 * ����ҳ�����
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request , String flg) {
		DAO dao = DAO.getInstance();
		GuizhdxDAO gDao = new GuizhdxDAO();
		if ("tjsz".equals(flg)) {
			List jxjList = dao.getWhList("jxjdmb", "jxjdm", "jxjmc", "", "sfqy", "yes");
			request.setAttribute("jxjList", jxjList);
			
			request.setAttribute("fjshList", getSelectList("fjsh"));
			
			request.setAttribute("isNot", getSelectList("isNot"));
			request.setAttribute("sqlb", getSelectList("sqlb"));
		} else if ("rych".equals(flg)) {
			List rychList = dao.getWhList("rychdmb", "rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);
			
			request.setAttribute("shjbList", getSelectList("fjsh"));
			request.setAttribute("isNot", getSelectList("isNot"));
		} else if ("priseCheck".equals(flg)) {
			request.setAttribute("xn", Base.getJxjsqxn());
			request.setAttribute("nd", Base.getJxjsqnd());
			
			List jxjList = dao.getWhList("jxjdmb", "jxjdm", "jxjmc", "", "", "");
			request.setAttribute("jxjList", jxjList);
		} else if ("creditCheck".equals(flg)) {
			request.setAttribute("xn", Base.getJxjsqxn());
			request.setAttribute("nd", Base.getJxjsqnd());
			
			List rychList = dao.getWhList("rychdmb", "rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);
		} else if ("zhcpjxj".equalsIgnoreCase(flg)){
			//�ۺϲ�����ѧ��
			List jxjList = dao.getWhList("zhcpjxjdmb", "jxjdm", "jxjmc", "", "", "");
			request.setAttribute("jxjList", jxjList);
		}
		
		List<HashMap<String, String>> shztList = getSelectList("shzt");
		request.setAttribute("shztList", shztList);
		
		
		//�۲��
		String[] title = new String[] {"�����ɼ�","�����ɼ�","�����ɼ�","�ܷ�","�����ɼ�����","�ܷ�����"};
		request.setAttribute("zcTitle", dao.arrayToList(title, title));
		
		
		request.setAttribute("nowTime", getNow());
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
	}
	
	
	/**
	 * �����б�ֵ
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String flg) {
		DAO dao = DAO.getInstance();
		String[] dm = null;
		String[] mc = null;
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		 if ("shzt".equalsIgnoreCase(flg)) {
			dm = new String[] { "δ���", "ͨ��","��ͨ��" };
			mc = new String[] { "δ���", "ͨ��" ,"��ͨ��"};
		} else if ("fjsh".equals(flg)){
			dm = new String[] { "1", "2","3" };
			mc = new String[] { "һ������ѧ���û���", "����("+Base.YXPZXY_KEY+"��ѧУ)" ,"����������Ա��"+Base.YXPZXY_KEY+"��ѧУ��"};
		} else if ("isNot".equals(flg)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��"};
		} else if ("sqlb".equals(flg)) {
			dm = new String[] { "����", "����/����","ר��������","����" };
			mc = new String[] { "����", "����/����","ר��������","����"};
		}
		 
		 
		return dao.arrayToList(dm, mc);
	}
	
	
	/**
	 * ��ȡ ѧ��������Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String , String> getStuInfo(String xh) {
		XsxxglService service = new XsxxglService();
		
		return service.selectStuinfo(xh);
	}
	
	
	/**
	 * ��ǰʱ��
	 * @return
	 */
	public String getNow() {
		
		return dao.getNow();
	}
	
	
	/**
	 * ��ȡ��ѧ����˼���
	 * @param jxjdm
	 * @return
	 */
	public String getShjb(String jxjdm) {
		
		return dao.getShjb(jxjdm);
	}
	
	
	/**
	 * ��ȡ��˲�ѯ��Ҫ�Զ�����ֶ�
	 * @param yhlx
	 * @param shjb
	 * @return String
	 * */
	public void getCustomAudiColumn(HttpServletRequest request, String yhlx, String shjb){
		
		StringBuilder sb = new StringBuilder();
		if("3".equalsIgnoreCase(shjb)){//�������
			if (yhlx.equalsIgnoreCase("xy")) {
				sb.append(" (case when xxsh='δ���' then '' else 'disabled' end) disabled,");
				sb.append(" (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else if (yhlx.equalsIgnoreCase("fdy")){
				sb.append(" (case when xysh='δ���' then '' else 'disabled' end) disabled, ");
				sb.append(" (case when nvl(fdysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else if (Base.isNull(yhlx)) {
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(shzt,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else if ("stu".equals(yhlx)){
				sb.append(" (case when fdysh='δ���' then '' else 'disabled' end) disabled,");
			}else{
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}
		}else if("2".equalsIgnoreCase(shjb)){//�������
			if (yhlx.equalsIgnoreCase("xy")) {
				sb.append(" (case when xxsh='δ���' then '' else 'disabled' end) disabled,");
				sb.append(" (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else if (Base.isNull(yhlx)) {
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(shzt,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else{
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}
		} else if("1".equalsIgnoreCase(shjb)){//һ�����
			if (Base.isNull(yhlx)) {
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(shzt,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else{
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}
		}
		
		request.setAttribute("clientColumns", sb.toString());
		
		if (Globals.XXDM_NBCSZYJSXY.equals(Base.xxdm) && Base.isNull(request.getParameter("jxjdm"))) {
			
			
			request.setAttribute("annexTerm", " and (not exists " +
					"(select 1 from (select a.*,case when a.shjb = 1 then " +
					"a.shzt else a.xxsh end shzz from view_typj_jxjsqb a) where xh=a.xh and  shzz='ͨ��' " +
					"and xn=(select jxjsqxn from xtszb) and a.jxjmc != '������������չ�ɾͽ�ѧ��') or shzt='ͨ��' or xxsh='ͨ��')");
		}
		
	}
	
	
	/**
	 * ��ȡѧ���Ĵ�����Ϣ
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<String[]> getStuCfxx(String xh, String xn){
		
		PjpyCommonInterface pjpy = new PjpyCommonInterface();
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("xh", xh);
		map.put("xn", xn);
		
		return pjpy.queryStuCfxx(map);
	} 
	
	
	/**
	 * ��ѯѧ���ɼ�
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<String[]> getStuCjxx(String xh, String xn) {
		PjpyCommonInterface pjpy = new PjpyCommonInterface();
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("xh", xh);
		map.put("xn", xn);
		
		return pjpy.queryStuCjxx(map);
	}
	
	/**
	 * ��ѯѧ���ɼ�
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<HashMap<String,String>> getStuCjxx(HashMap<String, String> map) {
		
		
		return dao.queryStuCjxx(map);
	}
	
	
	/**
	 * ��ȡ�����ƺ���˼���
	 * @param rychdm
	 * @return
	 */
	public String getShjbForRych(String rychdm) {
		
		return dao.getShjbForRych(rychdm);
	}

	
	/**
	 * ʣ������
	 * @param map
	 * @return
	 */
    public String getSyme(HashMap<String, String> map) {
		
		String syme = "";
		
		if (!Base.isNull(map.get("jxjdm"))) {
			syme = dao.getSymeForJxj(map);
		} else if (!Base.isNull(map.get("rychdm"))) {
			syme = dao.getSymeForRych(map);
		}
		
		if (Base.isNull(syme)) {
			syme = "������";
		}
		return syme;
	}
    
    
    /**
     * ����������˼���
     * @param dm
     * @param shjb
     * @param flg
     * @return
     * @throws SQLException 
     */
    public boolean saveBatchShjb(String[] dm,String shjb,String flg) throws SQLException {
    	
    	if ("jxj".equals(flg)) {
    		return dao.saveBatchShjbForJxj(dm, shjb);
    	} else {
    		return dao.saveBatchShjbForRych(dm, shjb);
    	}
    	
    }
    
    /**
	 * �۲�ּ�����
	 * @param xh
	 * @param xn
	 * @return
	 */
    public List<HashMap<String, String>> getZcfPm(String xh, String xn) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xh", xh);
		map.put("xn", xn);
		PjpyCommonInterface commService = new PjpyCommonInterface();

		List<HashMap<String, String>> zccj = commService
				.queryStuZhszcpCjAndPm(map);
		
		zccj.addAll(commService.queryStuCjAndPm(map));

		return zccj;
	}
	
    /**
	 * �۲�ּ�����
	 * @param xh
	 * @param xn
	 * @return
	 */
    public List<HashMap<String, String>> getZcfPm(String xh, String xn,String xq) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xh", xh);
		map.put("xn", xn);
		map.put("xq", xq);
		PjpyCommonInterface commService = new PjpyCommonInterface();

		List<HashMap<String, String>> zccj = commService
				.queryStuZhszcpCjAndPm(map);
		
		zccj.addAll(commService.queryStuCjAndPm(map));

		return zccj;
	}
    
    /**
	 * �۲�ּ�����
	 * @param xh
	 * @param xn
	 * @return
	 */
    public List<HashMap<String, String>> getZcfPm(String xh) {
    	PjpyTyService tyService = new PjpyZjkjxyService();
    	String zczq = tyService.getZhcpSqzq();
    	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xh", xh);	
		
		if("xq".equalsIgnoreCase(zczq) || "xn".equalsIgnoreCase(zczq)){
			map.put("xn", Base.getJxjsqxn());
		}
		if("xq".equalsIgnoreCase(zczq)){
			map.put("xq", Base.getJxjsqxq());
		}
		
		PjpyCommonInterface commService = new PjpyCommonInterface();

		List<HashMap<String, String>> zccj = commService.queryStuZhszcpCjAndPm(map);
		zccj.addAll(commService.queryStuCjAndPm(map));

		return zccj;
	}
	
	/**
	 * ѧ�����ڰ�İ༶����
	 * @param xh
	 * @return
	 */
	public String getBjrs(String xh) {
		return CommonQueryDAO.getBjrs(xh);
	}


	/**
	 * �Ƿ�Υ��
	 * @param xh
	 * @param xn
	 * @return
	 */
	protected boolean isWj(HashMap<String, String> map) {
		
		return Boolean.valueOf(dao.getIswj(map));
	}
	
	
	/**
	 * 
	 *���ݴ�ѧ�����ж�
	 */
	public HashMap<String, String> pjpyTjsz(HashMap<String, String> map) {
		List<HashMap<String, String>> tjList = dao.getTjList(map);
		List<HashMap<String, String>> zcfList = dao.getZcf(map);
		List<HashMap<String, String>> zfList = dao.getZf(map);
		String mc = "";//�����ֶζ�Ӧ�۲���������
		String flg = "";//fs:������pm:����
		String data = "0"; //�����ֶζ�Ӧ�۲���������
		String msg = "";//������Ϣ
		boolean result = true;
		
		/*
		 * �����������ѭ���ж���ȡ��Ӧ����
		 */
		if(zcfList.size()>0){
			for (HashMap<String, String> temp : tjList) {
				
				if ("dycpf".equals(temp.get("tjzd"))) {
					mc = "����������";
					flg = "fs";
					msg = "���������ֲ���������!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("zycpf".equals(temp.get("tjzd"))) {
					mc = "����������";
					flg = "fs";
					msg = "���������ֲ���������!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("wtcpf".equals(temp.get("tjzd"))) {
					mc = "���������";
					flg = "fs";
					msg = "��������ֲ���������!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("zhszcpf".equals(temp.get("tjzd"))) {
					mc = "�ܷ�";
					flg = "fs";
					msg = "�ۺ����ʲ����ֲ���������!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("zhszcpfpm".equals(temp.get("tjzd"))) {
					mc = "�ܷ�";
					flg = "pm";
					msg = "�ۺ����ʲ�������������������!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("zycpfpm".equals(temp.get("tjzd"))) {
					mc = "����������";
					flg = "pm";
					msg = "���������ֲ���������!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("zdkccj".equals(temp.get("tjzd"))) {
					msg = "��Ϳγ̳ɼ�����������!";
					data = dao.getMincj(map);
				} else if ("bjgmc".equals(temp.get("tjzd"))) {
					msg = "�������Ŵβ���������";
					data = dao.getBjgmc(map);
				} else if("zyjd".equals(temp.get("tjzd"))){//�����ܼ���
					mc="zyjd";
					msg="�ǳɼ��ܼ��㲻����������";
					flg="zyjd";
					data = getZf(zfList, mc, flg);
					if(data==null){
						result=false;
					}
				} else if("zpmbl".equals(temp.get("tjzd"))){//�ܷ���������
					mc="zpmbl";
					msg="�ܷ���������������������";
					flg="zpmbl";
					data = getZf(zfList, mc, flg);
					if(data==null){
						result=false;
					}
				} else if("zypm".equals(temp.get("tjzd"))){//�����ɼ�����
					mc="zypm";
					msg="�����ɼ�����������������";
					flg="zypm";
					data = getZf(zfList, mc, flg);
					if(data==null){
						result=false;
					}
				} else if("zypmbl".equals(temp.get("tjzd"))){//�����ɼ���������
					mc="zypmbl";
					msg="�����ɼ���������������������";
					flg="zypmbl";
					data = getZf(zfList, mc, flg);
					if(data==null){
						result=false;
					}
				}
				
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);//�Ƚ�����
				
				if (!result) {
					break;
				}
				
			}
		}
		
		if (isWj(map)) {
			result = false;
			msg = "����Υ�ͼ�¼!";
		}
		
		HashMap<String, String> tjgl = new HashMap<String, String>();
		
		tjgl.put("message", msg);
		tjgl.put("result", String.valueOf(result));
		
		return tjgl;
	}
	
	/**
	 * ��ȡ�۲�ֽ��������Ӧ������
	 * @param zcfList
	 * @param mc
	 * @param flg
	 * @return
	 */
	public String getZcfxx(List<HashMap<String, String>> zcfList,String mc,String flg) {
		
		for (HashMap<String, String> map : zcfList) {
			if (mc.equals(map.get("mc"))) {
				return map.get(flg);
			}
		}
		
		return null;
	}
	
	/**
	 * ��ȡ�ܷ���Ϣ
	 * @param zcfList
	 * @param mc
	 * @param flg
	 * @return
	 */
	public String getZf(List<HashMap<String, String>> zfList,String mc,String flg) {
		
		if(zfList.size()>0){
			HashMap<String, String> map =zfList.get(0);
			return map.get(flg);
		}
		
		return null;
	}
	
	
	/**
	 * �ж����������Ƿ���������
	 * @param tjlx
	 * @param tjz
	 * @param data
	 * @return
	 */
	public boolean isFhtj(String tjlx,String tjz,String data) {
		
		if (Base.isNull(data)) {
			return false;
		}
		
		double dtjz = Double.valueOf(tjz);
		double ddata = Double.valueOf(data);
		
		if (">".equals(tjlx)) {
			return ddata > dtjz;
		} else if(">=".equals(tjlx)) {
			return ddata >= dtjz;
		} else if("=".equals(tjlx)) {
			return ddata == dtjz;
		} else if("<".equals(tjlx)) {
			return ddata < dtjz;
		} else if("<=".equals(tjlx)) {
			return ddata <= dtjz;
		}
		
		return true;
	}
	
	
	/**
	 * �Ƚ��༶��ʣ������ 
	 * @param xn
	 * @param xydm
	 * @return
	 */
	public String getXjbjSyme(String xn, String xydm) {
		
		return dao.getXjbjSyme(xn, xydm);
	}
	
	
	/**
	 * �������������ж�
	 * @param map
	 * @return
	 */
	public HashMap<String, String> nbcxPjpyTjsz(HashMap<String, String> map) {
		List<HashMap<String, String>> tjList = dao.getTjList(map);

		String msg = "";
		String data = "0";
		boolean result = true;
		
		for (HashMap<String, String> temp : tjList) {

			if ("xjf".equals(temp.get("tjzd"))) {
				float bjrs = Float.parseFloat(CommonQueryDAO.getBjrs(map.get("xh")));
				float xjfpm = Base.isNull(getXjf(map).get("xjfpm")) ? bjrs : Float.parseFloat(getXjf(map).get("xjfpm"));
				data = String.valueOf(Double.valueOf((xjfpm/bjrs*100)));
				msg = "ѧ�����ڰ༶�����ٷֱȲ�����!";
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);
			} else if ("pxdj".equals(temp.get("tjzd"))) {
				msg = "ѧ��Ʒ�еȼ�����������!";
				data =  getPxdj(map);
				result = isPxdj(temp.get("tjlx"), temp.get("tjz"), data);
			}
			
			if (!result) {
				break;
			}
		}
		
		if (isWj(map)) {
			result = false;
			msg = "����Υ�ͼ�¼!";
		} 
		if(dao.iscx(map)) {
			result =false;
			msg = "�Բ�����ı��޿��к������޼�¼����������ý�ѧ��!";
		}
		
		
		HashMap<String, String> tjgl = new HashMap<String, String>();
		
		tjgl.put("message", msg);
		tjgl.put("result", String.valueOf(result));
		
		return tjgl;
	}
	
	
	/**
	 * ��ȡѧ����Ʒ�еȼ�
	 * @param map
	 * @return
	 */
	public String getPxdj(HashMap<String, String> map) {

		PjpyTyForm model = new PjpyTyForm();
		PxpjNbcsService service = new PxpjNbcsService();
		List<HashMap<String, String>> list = null;

		model.setBpjr(map.get("xh"));
		model.setXn(map.get("xn"));

		try {
			list = service.getPxpjInfo(model);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if (null != list && !list.isEmpty()) {	
			return list.get(0).get("pj");
		} else {
			return "��";
		}
		
	}
	
	/**
	 * �ж����������Ƿ���������
	 * @param tjlx
	 * @param tjz
	 * @param data
	 * @return
	 */
	public boolean isPxdj(String tjlx, String tjz, String data) {

		if (Base.isNull(data)) {
			return false;
		}

		if ("A".equals(tjz)) {
			if (">".equals(tjlx)) {
				return false;
			} else if(">=".equals(tjlx)) {
				return "A".equals(data);
			} else if("=".equals(tjlx)) {
				return "A".equals(data);
			} else if("<".equals(tjlx)) {
				return "B".equals(data) || "C".equals(data) || "D".equals(data);
			} else if("<=".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data) || "C".equals(data) || "D".equals(data);
			}
		} else if ("B".equals(tjz)) {
			if (">".equals(tjlx)) {
				return "A".equals(data);
			} else if(">=".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data);
			} else if("=".equals(tjlx)) {
				return "B".equals(data);
			} else if("<".equals(tjlx)) {
				return "C".equals(data) || "D".equals(data);
			} else if("<=".equals(tjlx)) {
				return "B".equals(data) || "C".equals(data) || "D".equals(data);
			}
		} else if ("C".equals(tjz)) {
			if (">".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data);
			} else if(">=".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data) || "C".equals(data);
			} else if("=".equals(tjlx)) {
				return "C".equals(data);
			} else if("<".equals(tjlx)) {
				return "D".equals(data);
			} else if("<=".equals(tjlx)) {
				return "C".equals(data) || "D".equals(data);
			}
		} else if ("D".equals(tjz)) {
			if (">".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data) || "C".equals(data);
			} else if(">=".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data) || "C".equals(data) || "D".equals(data);
			} else if("=".equals(tjlx)) {
				return "D".equals(data);
			} else if("<".equals(tjlx)) {
				return false;
			} else if("<=".equals(tjlx)) {
				return "D".equals(data);
			}
		} else {
			return false;
		}

		return false;
	}
	
	
	/**
	 * ��������ѧ����
	 * @param map
	 * @return
	 */
	public HashMap<String,String> getXjf(HashMap<String, String> map) {
		
		return dao.getXjf(map);
	}
	
	
	/**
	 * ��ͥ������Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsfzxx(String xh) {
			return dao.getXsfzxx(xh);
	}
	
	/**
	 * ��ȡ�ɼ���������Ϣ
	 */
	public HashMap<String,String>getCjpmbXx(String xh){
		return dao.getCjpmXx(xh);
	}
	
	/**
	 * ��ȡ��������
	 * @param xxdm
	 * @param xnnd
	 * @return String[]
	 * @author lr
	 * */
	public String[] getXnndArr(String xxdm, String[] xnnd){
		HashMap<String, String[]> xnndMap = new HashMap<String, String[]>();
		xnndMap.put(Globals.XXDM_LSXY, new String[]{"xn"});//��ˮѧԺ
		
		xnnd = xnndMap.get(xxdm) == null ? xnnd : xnndMap.get(xxdm);
		return xnnd;
	}
	
	/**
	 * �����ƺ���˼���
	 * @param xxdm
	 * @return String
	 * @author lr
	 * */
	public String getRychShjb(String xxdm){
		String result = "3";
		if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){
			result = "2";
		}
		return result;
	}
	
	/**
	 * �ж������ƺ��������
	 * @param xxdm
	 * @param model
	 * @param shjb
	 * @return String
	 * @author lr
	 * */
	public String checkRychTj(String xxdm, 
							  String[] pkValue,
							  String shzd,
							  String shjg,
							  User user){
		String result = "";
		if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm) && "ͨ��".equalsIgnoreCase(shjg)){//��ˮѧԺ
			for(int i=0; i<pkValue.length; i++){
				LsxyPjpyService service = new LsxyPjpyService();
				HashMap<String, String> map = service.getXsrychbByPk(pkValue[i]);
				map.put("jxjdm", map.get("rychdm"));
				boolean flag = service.checkRychRsxz(map);
				
				if (!flag) {
					result += (i+1);	
					result += ",";
					continue;
				}
				try{
					StandardOperation.update("xsrychb",new String[]{shzd},new String[]{shjg},"xh||xn||rychdm||xq",pkValue[i],user);
				} catch(Exception e){					
				}				
			}
		}else{
			for(int i=0; i<pkValue.length; i++){				
				try{
					StandardOperation.update("xsrychb",new String[]{shzd},new String[]{shjg},"xh||xn||rychdm||xq",pkValue[i],user);
				} catch(Exception e){	
					
				}				
			}
		}
		result = StringUtils.isNull(result) ? "�����ɹ���" 
				                            : "��" + result.substring(0, 
				                            		 result.length()-1) 
				                            		+ "�����ݲ���ʧ�ܣ�������������������";
		return result;
	}
	
	/**
	 * �Ƿ����������
	 * @param xxdm
	 * @return boolean
	 * */
	public boolean hasShtj(String xxdm){
		boolean result = false;
		if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){
			//��ˮѧԺ����������
			result = true;
		}
		
		return result;
	}
	
	/**
	 * ��ѯ�༶ѧ������������Ϣ
	 * @param map ����key����Ҫ�� bjdm,xn
	 * @return {"ѧ��", "�༶", "�񽱽���", "������"}
	 */
	public List<String[]> getStuPjpyxx(HashMap<String, String> map) {
		return dao.getStuPjpyxx(map);
	}
	
	/**
	 * �ж�ѧ���Ƿ������
	 * @param xh
	 * @param xmdm
	 * @param key
	 * @return boolean
	 * */
	public boolean xssqFlag(String xh,String xmdm, String key){
		String tableName = "pjpy_jxjsqxsb";
		String pk = "xh";
		String pkValue = xh;
		
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		String pjzq = service.getPjpySqzq();//��������
		
		if("xq".equalsIgnoreCase(pjzq)){
			pk += "||xn||xq||xmdm||key";
			pkValue += Base.getJxjsqxn()+Base.getJxjsqxq()+xmdm+key;
		}else if("xn".equalsIgnoreCase(pjzq)){
			pk += "||xn||xmdm||key";
			pkValue += Base.getJxjsqxn()+xmdm+key;
		}else if("nd".equalsIgnoreCase(pjzq)){
			pk += "||nd||xmdm||key";
			pkValue += Base.getJxjsqnd()+xmdm+key;
		}
		
		return dao.checkExists(tableName, pk, pkValue) ? true : false;
	}
	
	/**
	 * �жϰ������Ƿ����
	 * @return String
	 * */
	public String getBzrshFlag(){
		String bzrSh = "false";
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			bzrSh = "true";
		}		
		return bzrSh;
	}
	
	/**
	 * ���ҽ�ѧ���������ڻ�ȡ����ʱ����Ϣ
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getJxjSqsjMap(){
		HashMap<String, String> map = new HashMap<String, String>();
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		String pjzq = service.getPjpySqzq();//��������
		
		if(!Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){//���㽭�Ƽ�
			pjzq = "xn";
		}
		
		if("xq".equalsIgnoreCase(pjzq)){
			//ѧ��
			map.put("xn", Base.getJxjsqxn());
			map.put("xq", Base.getJxjsqxq());
			map.put("nd", "��");
			map.put("sqsj", "��");
		}else if("xn".equalsIgnoreCase(pjzq)){
			//ѧ��
			map.put("xn", Base.getJxjsqxn());
			map.put("xq", "��");
			map.put("nd", "��");
			map.put("sqsj", "��");
		}else if("nd".equalsIgnoreCase(pjzq)){
			//���
			map.put("xn", "��");
			map.put("xq", "��");
			map.put("nd", Base.getJxjsqnd());
			map.put("sqsj", "��");
		}else{
			//������
			map.put("xn", "��");
			map.put("xq", "��");
			map.put("nd", "��");
			map.put("sqsj", GetTime.getNowTime2());
		}		
		return map;
	}
	
	/**
	 * ��ѧ�������ж�
	 * @param jxjpdModel
	 * @param lb
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> checkSqtj(JxjpdxxModel jxjpdModel, String lb){
		HashMap<String, String> tjInfo = new HashMap<String, String>();
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//�㽭�Ƽ�
			LsxyPjpyService lsxyService = new LsxyPjpyService();
			jxjpdModel.setXq("");//��������Ϊѧ��
			tjInfo = lsxyService.pdStuTjFlag(jxjpdModel,lb);
		}else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//��ְͨҵ��ѧ
			PjpyNtzydxService service = new PjpyNtzydxService();
			tjInfo = service.pdStuTjFlag(jxjpdModel, lb);
		}else {
			tjInfo.put("result","true");
			tjInfo.put("message","");
		}
		return tjInfo;
	}


	
	/**
	 * ������Ŀ��
	 * @param map
	 * @return
	 */
	public String getBkkms(HashMap<String,String> map) {
		return dao.getBkkms(map);
	}


	/**
	 * �۲��ּܷ�����
	 * @param map
	 */
	public HashMap<String,String> getZcf(HashMap<String,String> map) {
		List<HashMap<String, String>> zcfList = dao.getZcf(map);
		
		String mc = "�ܷ�";
		String flg = "fs";
		String data = getZcfxx(zcfList, mc, flg);
		
		map.put("zcf", data);
		
		flg = "pm";
		data = getZcfxx(zcfList, mc, flg);
		map.put("zcfPm", data);
		
		return map;
	}
	
	
	/**
	 * ���ݴ�ѧ�۲�ͷ���
	 * @param map
	 * @return
	 */
	public HashMap<String,String> getGuizhdxCj(HashMap<String,String> map){
		
		return dao.getGuizhdxCj(map);
	}
	
	/**
	 * ��ȡ��ѧ���ϱ���ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getJxjsbTitle(){
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = new String[]{"pk","xn","xqmc","xh","xm","nj","xymc","zymc","bjmc","jxjmc","je","xysh","shzt"};
		String[] cn = new String[]{"����","ѧ��","ѧ��","ѧ��","����","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","��ѧ��","���","ѧԺ���","���״̬"};
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ��ѯ��ѧ���ϱ���Ϣ
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> queryJxjsb(GuizhdxForm model,User user){
		String[] en = new String[]{"pk","xn","xqmc","xh","xm","nj","xymc","zymc","bjmc","jxjmc","je","xysh","shzt"};
		return dao.selectJxjsb(model, en,user);
	}
	
	/**
	 * ���潱ѧ���ϱ���Ϣ
	 * @param pkValues
	 * @param jxjdm
	 * @param user
	 * @return boolean
	 * */
	public boolean jxjSb(String[] pkValues, GuizhdxForm model, User user){
		return dao.saveJxjsb(pkValues, model, user);
	}
	
	/**
	 * ȡ����ѧ���ϱ�
	 * @param pkValues
	 * @param user
	 * @return boolean
	 * */
	public boolean jxjQxsb(String[] pkValues, User user){
		return dao.delJxjsb(pkValues, user);
	}
}

