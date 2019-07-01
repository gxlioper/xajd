package xgxt.qgzx.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.dao.XsgwglDAO;
import xgxt.qgzx.form.QgzxForm;
import xgxt.qgzx.hngydx.service.HngydxXsqgzxService.saveFreeTime;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧѧ����λ����Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2009-01-05</p>
 */
public class XsgwglService {
	QgzxDao dao = new QgzxDao();
	
	/**
	 * ��ȡ���ݴ�ѧѧ������ʱ���б�
	 * @param xh
	 * @return List
	 * @throws Exception
	 * */
	public void freeTimeTableGzdx(String xh, HttpServletRequest request) throws Exception{
		String[] week = { "mon", "tue", "wed", "thu", "fri"};
		String[] freeTime = {"��һ���", "�ڶ����", "�������", "���Ĵ��"};
		String tableName = "xsqgzxsjb";
		fillArray(xh, tableName, freeTime, week, request);
	}
	
	/**
	 * ��ȡ�㽭��ýѧ������ʱ���б�
	 * @param xh
	 * @return List
	 * @throws Exception
	 * */
	public void freeTimeTableZjcm(String xh, HttpServletRequest request) throws Exception{
		String[] week = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		String[] freeTime = {"��1��", "��2��", "��3��", "��4��", "��5��", "��6��", "��7��", "��8��" };
		String tableName = "xsqgzxsjb";
		fillArray(xh, tableName, freeTime, week, request);
	}
	
	/**
	 * ��ȡ�Ϻ�����ѧ������ʱ���б�
	 * @param xh
	 * @return List
	 * @throws Exception
	 * */
	public void freeTimeTableShgc(String xh, HttpServletRequest request) throws Exception{
		String[] week = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		String[] freeTime = {"��1-2��", "��3-4��", "����", "��5-6��", "��7-8��", "����" };
		String tableName = "xsqgzxsjb";
		fillArray(xh, tableName, freeTime, week, request);
	}
	
	/**
	 * ��ȡ����Ƽ�ѧ������ʱ���б�
	 * @param xh
	 * @return List
	 * @throws Exception
	 * */
	public void freeTimeTableCqkj(String xh, HttpServletRequest request) throws Exception{
		//����Ƽ�
		String[] week = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		String[] freeTime = { "�����ޣ�7:30��8:20��", "��1-2�ڣ�8:30��10:05��", "��3-4�ڣ�10:25��12:00��", "���ݣ�12:00��13:45��",
				"��5-6�ڣ�13:50��15:25��", "��7-8�ڣ�15:30��17:05��", "�����ޣ�17:50��20:15��" };
		
		String tableName = "xsqgzxsjb";
		fillArray(xh, tableName, freeTime, week, request);
	}
	
	/**
	 * ��ȡ�й���ַ��ѧѧ������ʱ���б�
	 * @param xh
	 * @return List
	 * @throws Exception
	 * */
	public void freeTimeTableZgdzdx(String xh, HttpServletRequest request) throws Exception{
		String[] week = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		String[] freeTime = {"��һ���", "�ڶ����", "�������", "���Ĵ��"};
		String tableName = "xsqgzxsjb";
		fillArray(xh, tableName, freeTime, week, request);
	}
	
	/**
	 * �������
	 * @param xh
	 * @param tableName
	 * @param freeTime
	 * @param week
	 * @param request
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public void fillArray(String xh, String tableName,String[] freeTime, String[] week, HttpServletRequest request) throws Exception{
		List kxList = new ArrayList<HashMap>();
		int dataCount = dao.getDataCount(tableName, "xh", xh);
		if(xh != null && !xh.equalsIgnoreCase("") && dataCount>0){
			String[] kxbz = dao.getFreeTimeArray(xh);
			if(kxbz.length >= freeTime.length*week.length){
				String[] kx = new String[week.length];
				int j = 0;
				
				for (int i = 0; i < freeTime.length; i++) {
					for (int x = 0; x < week.length; x++) {
						
						kx[x] = kxbz[x + j];
					}
				j += week.length;
				HashMap<String, String> map2 = new HashMap<String, String>();
				for (int p = 0; p < week.length; p++) {
					map2.put(week[p], String.valueOf(kx[p]));
				}
				map2.put("sj", freeTime[i]);
				map2.put("sjIndex", String.valueOf(i));
				kxList.add(map2);
				}
			}else if(kxbz.length == 28){//�й����ʴ�ѧ���ѧ������ʱ���б�
				String[] kx = new String[week.length];
				int j = 0;
				
				for (int i = 0; i < freeTime.length; i++) {
					for (int x = 0; x < week.length; x++) {
						
						kx[x] = kxbz[x + j];
					}
				j += 7;
				HashMap<String, String> map2 = new HashMap<String, String>();
				for (int p = 0; p < week.length; p++) {
					map2.put(week[p], String.valueOf(kx[p]));
				}
				map2.put("sj", freeTime[i]);
				map2.put("sjIndex", String.valueOf(i));
				kxList.add(map2);
				}
			}else{
				for (int i = 0; i < freeTime.length; i++) {
					HashMap<String, String> map2 = new HashMap<String, String>();
					map2.put("sj", freeTime[i]);
					map2.put("sjIndex", String.valueOf(i));
					kxList.add(map2);
				}
			}
			request.setAttribute("kxList", kxList);
		}else{
			for (int i = 0; i < freeTime.length; i++) {
				HashMap<String, String> map2 = new HashMap<String, String>();
				map2.put("sj", freeTime[i]);
				map2.put("sjIndex", String.valueOf(i));
				kxList.add(map2);
			}
			request.setAttribute("whkxList", kxList);
			request.setAttribute("kxbz", "no");			
		}
	}
	
	/**
	 * ��ȡѧ���ĸ�����Ϣ
	 * @param xh
	 * @return HashMap<String,String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		return dao.getStuInfo(xh);		
	}
	
	/**
	 * �ж��Ƿ����趨��ѧ�������λʱ����
	 * @return String 
	 * @throws SQLException
	 * */
	public String checkTime() throws SQLException{
		String sql = "select * from gwsqsjb where kssj<to_char(sysdate,'yyyymmddhh24miss') and jssj>to_char(sysdate,'yyyymmddhh24miss')";
		String tag = dao.returntag(sql, new String[] {});
		return tag;
	}
	
	/**
	 * ����λ���ø�λ������ʱ������ȵĿ������λ�б�
	 * @return List
	 * */
	public List getKsqgw(){
		XsgwglDAO gwglDAO = new XsgwglDAO();
		return gwglDAO.getKsqgw();
	}
	
	/**
	 * ��ȡ�ڹ���ѧ����������Ϣ
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxConfig(){
		XsgwglDAO gwglDAO = new XsgwglDAO();
		return gwglDAO.getQgzxConfig();
	}
	
	/**
	 * ����ѧ�������λ��Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception  
	 * */
	public boolean saveGwsq(QgzxForm model, HttpServletRequest request) throws Exception{
		XsgwglDAO gwglDAO = new XsgwglDAO();
		boolean flag = false;
		String xh = DealString.toGBK(model.getXh());
		String xn = DealString.toGBK(model.getXn());
		String xq = DealString.toGBK(model.getXq());
		String nd = DealString.toGBK(model.getNd());
		String xmdm = DealString.toGBK(model.getXmdm());
		String jsjsp = DealString.toGBK(model.getJsjsp());
		String sffcfp = DealString.toGBK(model.getSffcfp());
		String lxdh = DealString.toGBK(model.getLxdh());
		String yhtc = DealString.toGBK(model.getYhtc());
		String mqqgzxqk = DealString.toGBK(model.getMqqgzxqk());
		
		String tableName = "xsgwxxb";
		String pk = "xh||gwdm||'-'||gwsbsj";
		String pkValue = xh+xmdm;
		String[] columns = {"jsjsp","sffcfp","lxdh","yhtc","mqqgzxqk"};
		String[] values = {jsjsp, sffcfp, lxdh, yhtc,mqqgzxqk};
		
		//�ж�ѧ���Ƿ����ڹ���ѧ��������
		if(dao.checkExists("qgzx_zgdzdx_hmdb", "xh", xh)){
			request.setAttribute("isHmd", "true");
		}else{
			if(dao.checkExists(tableName, pk, pkValue)){
				//update
				flag = StandardOperation.update(tableName, columns, values, pk, pkValue, request);
			}else{
				//insert
				HashMap<String, String> map = gwglDAO.getGwxxByPk(xmdm);
				String gwdm = map.get("gwdm");
				String gwsbsj = map.get("gwsbsj");
				
				columns = new String[]{"xh","gwdm","gwsbsj","jsjsp","sffcfp","lxdh","yhtc","xn","nd","xq","mqqgzxqk"};
				values = new String[]{xh, gwdm, gwsbsj, jsjsp, sffcfp, lxdh, yhtc,xn,nd,xq,mqqgzxqk};
				flag = StandardOperation.insert(tableName, columns, values, request);
			}
			
			if(flag){//����ѧ���ɲμ��ڹ���ѧʱ����Ϣ
				saveFreeTime sft = new saveFreeTime(request,xh,8,7);
				new Thread(sft).start();
			}
		}
		
		return flag;
	}
	
	/**
	 * ��ȡѧ�������λ����ϸ��Ϣ
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuGwxx(QgzxForm model){
		XsgwglDAO gwglDAO = new XsgwglDAO();
		return gwglDAO.getStuGwxx(model);
	}
	
	/**
	 * ��ȡѧ������ʱ���־
	 * @param xh
	 * @return List
	 * @throws Exception 
	 * */
	public List getFreeTimeList(String xh) throws Exception{
		XsgwglDAO gwglDAO = new XsgwglDAO();
		return gwglDAO.getFreeTimeList(xh);
	}
	
	/**
	 * ����������ȡ��λ��Ϣ
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwxxByPk(String pkValue){
		XsgwglDAO gwglDAO = new XsgwglDAO();
		return gwglDAO.getGwxxByPk(pkValue);
	}
	
	/**
	 * ��ѯѧ���ɼ���Ϣ
	 * @param String xh
	 * @return List<String[]>
	 * */
	public List<String[]> queryXscj(String xh){
		XsgwglDAO dao = new XsgwglDAO();		
		return dao.selectXscj(xh);
	}
	
	/**
	 * ��ѯѧ��Υ����Ϣ
	 * @param String xh
	 * @return List<String[]>
	 * */
	public List<String[]> queryXswj(String xh){
		XsgwglDAO dao = new XsgwglDAO();		
		return dao.selectWjcf(xh);
	}
	
	/**
	 * ��ѯ���ݴ�ѧ��λ��ϸ��Ϣ
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryGzdxXsgwxxb(String pk, String pkValue){
		XsgwglDAO dao = new XsgwglDAO();
		return dao.selectGzdxXsgwxxb(pk,pkValue);
	}
	
	/**
	 * ��ѯ�й����ʴ�ѧ��λ��ϸ��Ϣ
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryZgdzdxXsgwxxb(String pk, String pkValue){
		XsgwglDAO dao = new XsgwglDAO();
		return dao.selectZgdzdxXsgwxxb(pk,pkValue);
	}
	
	/**
	 * ��ȡѧ����λ��Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsgwxxbTopTr(){
		XsgwglDAO dao  = new XsgwglDAO();
		String[] colList = dao.getXsgwxxbColList();
		String[] colListCN = dao.getColumnNameCN(colList, "view_xsgwxx");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * ��ѯѧ����λ��Ϣ
	 * @param CommanForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwxxb(CommanForm model){
		XsgwglDAO dao  = new XsgwglDAO();
		return dao.selectXsgwxxb(model);
	}
	
	/**
	 * �ڸ�ѧ����λ��Ϣ������ѯ
	 * @param CommanForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryZgxsxxForExport(CommanForm model,String[] colList){
		XsgwglDAO dao  = new XsgwglDAO();
		return dao.selectZgxsxxForExport(model,colList);
	}
	
	/**
	 * ��ȡѧУ��ѧ����λ�������
	 * @param xxdm
	 * @return String
	 * */
	public String getXsgwshShlx(String xxdm){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(Globals.XXDM_XNMZ, "yrdw-xy-xx");
		map.put(Globals.XXDM_XNMZ, "xy-yrdw-xx");
		map.put(Globals.XXDM_ZGMSXY, "xy-yrdw-xx");
		map.put(Globals.XXDM_NBTYZYJSXY, "xy-xx");
		map.put(Globals.XXDM_NBLGXY, "qtyh-xx");
		map.put(Globals.XXDM_HBJTZYJSXY, "fdy-yrdw-xx");
		map.put(Globals.XXDM_ZJJTZYJSXY, "fdy-yrdw-xx");
		map.put(Globals.XXDM_SZWBZYJSXY,"xy-xx");
		map.put(Globals.XXDM_WHSYFWXY, "fdy-yrdw-xx");
		map.put(Globals.XXDM_ZJJSZYJSXY, "qgzxgly"); //�㽭�����ڹ���ѧ����Ա��ˣ�2011.12.5 qlj��
		String shlx = map.get(xxdm);
		shlx = StringUtils.isNull(shlx) ? "yrdw-xx" : shlx;
		return shlx;
	}
	
	/**
	 * ��ȡѧ����λ��Ϣ��˲�ѯ��ͷ
	 * @param shlx
	 * @return String[]
	 * */
	public String[] getXsgwshCol(String shlx,User user){
		QgzxDao dao = new QgzxDao();
		String[] colList  = null;
		if(StringUtils.isNotNull(shlx)){
			
			String[] jtlx = shlx.split("-");
			if(jtlx.length == 3){
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", 
						"xqmc", "xh", "xm", "bjmc", "sfpks", "gwdm", "fdyyj", "xyyj", "xxyj", "sqsj" };
			}else if(jtlx.length==2 && "fdy".equalsIgnoreCase(jtlx[0])){
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", 
						"xqmc", "xh", "xm", "bjmc", "sfpks", "gwdm","fdyyj", "xxyj", "sqsj" };
			}else{
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", 
						"xqmc", "xh", "xm", "bjmc", "sfpks", "gwdm","xyyj", "xxyj", "sqsj" };
			}
		}
		//�㽭��ְͨҵ����ѧԺ
		if(Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(Base.xxdm)){
			colList = new String[] { "bgcolor","���", "����", "�к�", "xn", "nd", 
					"xqmc", "xh", "xm", "bjmc", "sfpks", "gwdm", "fdyyj", "xyyj", "xxyj", "sqsj" };
		}
		if(Globals.XXDM_YNYS.equalsIgnoreCase(Base.xxdm)){
			colList = StringUtils.joinStrArr(colList, new String[] { "bh","gh" });
		}
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			if(dao.isYrdwUser(user.getUserName())){
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd","xqmc",
						 "xh", "xm", "bjmc", "sfpks", "gwdm", "xyyj", "xxyj", "��¼�ø�λ", "sqsj" };
			}
		}
		if(Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)){
			if(dao.isYrdwUser(user.getUserName())){
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd","xqmc",
						 "xh", "xm", "bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj" };
			}else if("xx".equalsIgnoreCase(user.getUserType())
					|| "admin".equalsIgnoreCase(user.getUserType())){
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd","xqmc",
						 "xh", "xm", "bjmc", "sfpks", "gwdm", "sqsj",  "xxyj" };
			}
		}
		if(Globals.XXDM_ZJJSZYJSXY.equalsIgnoreCase(Base.xxdm)){
			
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd","xqmc",
						 "xh", "xm", "bjmc", "sfpks", "gwdm", "sqsj",  "xxyj" };
			
		}
		return colList;
	}
	
	/**
	 * ѧ����λ��Ϣ��˲�ѯ
	 * @param model
	 * @param user
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwsh(CommanForm model,User user){
		QgzxService service = new QgzxService();
		XsgwglDAO dao = new XsgwglDAO();
		List<String[]> rs = new ArrayList<String[]>();
		//�Ƿ������˵�λ
		boolean isYrdw = service.isYrdwUser(model.getUserName());
		//�Ƿ��Ǹ���Ա
		boolean isFdy = "true".equalsIgnoreCase(user.getFdyQx()) ? true : false;
		//�Ƿ��ǰ�����
		boolean isBzr = "true".equalsIgnoreCase(user.getBzrQx()) ? true : false;
		//�������
		String shlx = getXsgwshShlx(Base.xxdm);
		
		//��ȡ��ͷ
		String[] colList = getXsgwshCol(shlx,user);
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(Base.xxdm)){
			//�й����ʴ�ѧ
			rs = dao.queryXsgwshForZgdzdx(model, user, colList);			
		}else if(Globals.XXDM_HNGYDX.equalsIgnoreCase(Base.xxdm)){
			//���Ϲ�ҵ��ѧ
			rs = dao.queryXsgwshForHngydx(model, user, colList);
		}else if(Globals.XXDM_YNYS.equalsIgnoreCase(Base.xxdm)){
			//��������ѧԺ
			rs = dao.queryXsgwshForYnysxy(model, user, colList);
		}else if(Globals.XXDM_ZJJDZYJSXY.equalsIgnoreCase(Base.xxdm)){
			//�㽭����ְҵ����ѧԺ
			rs = dao.queryXsgwshForZjjdzyjsxy(model, user, colList);
		}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(Base.xxdm)){
			//�������ϴ�ѧ
			rs = dao.queryXsgwshForBjlhdx(model, user, colList);
		}else if(Globals.XXDM_HYGXY.equalsIgnoreCase(Base.xxdm)){
			//������ѧԺ
			rs = dao.queryXsgwshForHygxy(model, user, colList);
		}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//�㽭�Ƽ�
			rs = dao.queryXsgwshForZjkjxy(model, user, colList);
		}else if(Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)){
			//�ɶ�����
			rs = dao.queryXsgwshForCdtyxy(model, user, colList);
		}else if(Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(Base.xxdm)){
			//�㽭��ͨ
			if("fdy-yrdw-xx".equalsIgnoreCase(shlx)){
				//����Ա-���˵�λ���-ѧУ
				if(isFdy){
					//����Ա
					rs = dao.queryXsgwshForFirstZjjtzyjsxy(model, user, colList);
				}else if(isYrdw || isBzr ){
					//���˵�λ
					rs = dao.queryXsgwshForSecondZjjtzyjsxy(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//ѧУ
					rs = dao.queryXsgwshForXxThirdZjjtzyjsxy(model, user, colList);
				}
			}
		}else{
			if("yrdw-xx".equalsIgnoreCase(shlx)){
				//���˵�λ-ѧУ
				if(isYrdw){
					//���˵�λ
					rs = dao.queryXsgwshForTowStepFirst(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//ѧУ
					rs = dao.queryXsgwshForTowStepXxSecond(model, user, colList);
				}
			}
			if("fdy-xx".equalsIgnoreCase(shlx)){
				//����Ա-ѧУ
				if(isFdy){
					//����Ա
					rs = dao.queryXsgwshForTowStepFirst(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//ѧУ
					rs = dao.queryXsgwshForTowStepXxSecond(model, user, colList);
				}
			}
			if("xy-xx".equalsIgnoreCase(shlx)){
				//ѧԺ-ѧУ
				if("xy".equalsIgnoreCase(user.getUserType())){
					//ѧԺ
					rs = dao.queryXsgwshForTowStepFirst(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//ѧУ
					rs = dao.queryXsgwshForTowStepXxSecond(model, user, colList);
				}
			}
			if("qtyh-xx".equalsIgnoreCase(shlx)){
				//�����û�-ѧУ
				if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//ѧУ
					rs = dao.queryXsgwshForTowStepXxSecond(model, user, colList);
				}else{
					//�����û�
					rs = dao.queryXsgwshForTowStepFirst(model, user, colList);
				}
			}
			if("yrdw-xy-xx".equalsIgnoreCase(shlx)){
				//���˵�λ���-ѧԺ-ѧУ
				if(isYrdw){
					//���˵�λ
					rs = dao.queryXsgwshForFirst(model, user, colList);
				}else if("xy".equalsIgnoreCase(model.getUserType())){
					//ѧԺ
					rs = dao.queryXsgwshForSecond(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//ѧУ
					rs = dao.queryXsgwshForXxThird(model, user, colList);
				}
			}
			if("xy-yrdw-xx".equalsIgnoreCase(shlx)){
				//ѧԺ-���˵�λ-ѧУ
				if("xy".equalsIgnoreCase(model.getUserType())){
					//ѧԺ
					rs = dao.queryXsgwshForFirst(model, user, colList);
				}else if(isYrdw){
					//���˵�λ
					rs = dao.queryXsgwshForSecond(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//ѧУ
					rs = dao.queryXsgwshForXxThird(model, user, colList);
				}
			}
			
			if("fdy-yrdw-xx".equalsIgnoreCase(shlx)){
				//����Ա-���˵�λ-ѧУ
				if(isFdy){
					//ѧԺ
					rs = dao.queryXsgwshForTowStepFirst(model, user, colList);
				}else if(isYrdw){
					//���˵�λ
					rs = dao.queryXsgwshForSecond(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//ѧУ
					rs = dao.queryXsgwshForXxThird(model, user, colList);
				}
			}
			if("qgzxgly".equalsIgnoreCase(shlx)){
				//�ڹ���ѧ����Ա��ˣ��з���Ȩ���߱����ˣ�2011.12.5 qlj��
				rs = dao.queryXsgwshForQgglyThird(model, user, colList);
				
			}
		}
		//�Ƿ��������ֶ���Ϣȡ����
		rs = service.changeRsValue(rs);
		return rs;
	}
	
	/**
	 * ��ȡѧ����λ��˲�ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsgwxxShTopTr(User user){
		XsgwglDAO dao  = new XsgwglDAO();
		//�������
		String shlx = getXsgwshShlx(Base.xxdm);
		
		//��ȡ��ͷ
		String[] colList = getXsgwshCol(shlx,user);
		String[] colListCN = dao.getColumnNameCN(colList, "view_xsgwxx");
		return dao.arrayToList(colList, colListCN);
	}
}
