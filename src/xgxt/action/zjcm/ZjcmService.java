package xgxt.action.zjcm;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.axis.AxisFault;
import org.apache.struts.util.MessageResources;

import com.zfsoft.webService.zjcm.Ws_xgxtLocator;
import com.zfsoft.webService.zjcm.Ws_xgxtSoap12Stub;
import com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap12Stub;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;
import xgxt.utils.QueryReturnData;

public class ZjcmService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	public HashMap<String, String> getXxInfo(String xh) {
		HashMap<String,String> stuRs = new HashMap<String,String>();
		if(xh!=null&&!xh.equalsIgnoreCase("")){
			stuRs = CommonQueryDAO.getDetStuInfo(xh);
		}
		return stuRs;
	}
	
	/**
	 * 获取请假类型列表
	 * @return
	 * @throws RemoteException 
	 */
	
	public List<HashMap<String, String>> getQjlxForWebService() throws RemoteException {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		try {
			myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
			String [][] qjlxList= myStub.wsqj_qjlx_get();
			for(String[] temp : qjlxList){
				if(temp.length>1){
					HashMap<String, String> rs = new HashMap<String, String>();
					rs.put("qjlxdm", temp[0]);
					rs.put("qjlxmc", temp[1]);
					rs.put("bz", temp[2]);
					result.add(rs);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 网上请假保存
	 * @param myModel
	 * @return
	 * @throws RemoteException 
	 */
	public boolean wsqjsqSave(ZjcmModel myModel) throws RemoteException {
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		String update = ""; 
		try {
			myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
			update = myStub.wsqj_qingjia_add(DealString.toGBK(myModel.getXn()), DealString.toGBK(myModel.getXq()), DealString.toGBK(myModel.getXydm()), DealString.toGBK(myModel.getXymc()), DealString.toGBK(myModel.getBjdm()), DealString.toGBK(myModel.getBjmc()), DealString.toGBK(myModel.getXm()),  DealString.toGBK(myModel.getXh()),  DealString.toGBK(myModel.getQjlxdm()),  DealString.toGBK(myModel.getQjsj()), DealString.toGBK(myModel.getQjts()), DealString.toGBK(myModel.getQjyy()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if(update.equalsIgnoreCase("-1")){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 根据辅导员取到班级代码获取班级学生请假信息
	 * @param myModel 
	 * @return
	 */
	public QueryReturnData getQjxx(ZjcmModel myModel) {
		QueryReturnData queryReturnData = new QueryReturnData();
		ZjcmPage zjcmPage = getGetListPage(myModel);
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		Pages pages = myModel.getPages();
		ArrayList<String[]> rs = new ArrayList<String[]>(); 
		String[] colList = new String[]{"QjId","XueNian","XueQi","XyMc","BjMc","XueHao","XingMing","QjlxMc","Qjts","Qsrq","Shzt","Clr"};
		Integer[] indexList = new Integer[colList.length];
		String[] colListCN = new String[colList.length];
		String bjdm = myModel.getBjdm();
		String xydm = myModel.getXydm();
		String[][] wsqjList = null;
		try {
			try {
				myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
				if(bjdm!=null&&!bjdm.equalsIgnoreCase("")){
					wsqjList = myStub.wsqj_qingjia_banji_get(zjcmPage.getCurrentPage(), zjcmPage.getPageSize(), bjdm, myModel.getKssj(), myModel.getJssj(), myModel.getXh(), myModel.getShzt());
				}else if(xydm!=null&&!xydm.equalsIgnoreCase("")){
					wsqjList = myStub.wsqj_qingjia_xueyuan_get(zjcmPage.getCurrentPage(), zjcmPage.getPageSize(), xydm, myModel.getKssj(), myModel.getJssj(), myModel.getXh(), myModel.getShzt());
				}else{
					wsqjList = myStub.wsqj_qingjia_all_get(zjcmPage.getCurrentPage(), zjcmPage.getPageSize(), myModel.getKssj(), myModel.getJssj(), myModel.getXh(), myModel.getShzt());
				}
				pages.setMaxRecord(Integer.parseInt(wsqjList[0][0]));
				String[][] wsqjTopList = myStub.wsqj_biaotou_get();
				queryReturnData = getRsForToptr(rs, colList, indexList, colListCN, wsqjList, wsqjTopList);
			} catch (AxisFault e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return queryReturnData;
	}

	private QueryReturnData getRsForToptr(ArrayList<String[]> rs, String[] colList, Integer[] indexList, String[] colListCN, String[][] wsqjList, String[][] wsqjTopList) {
		QueryReturnData queryReturnData = new QueryReturnData(); 
		String[] wsqjTopCn = wsqjTopList[1];
		String[] wsqjTopEn = wsqjTopList[0];
		DAO dao = DAO.getInstance();
		ArrayList<String> wsqjTopEnArray = new ArrayList<String>();
		for(String temp : wsqjTopEn){
			wsqjTopEnArray.add(temp);
		}
		//取得索引
		for(int i=0;i<colList.length;i++){
			indexList[i] = wsqjTopEnArray.indexOf(colList[i]);
		}
		//单独处理审核状态字段
		int shztIndex = wsqjTopEnArray.indexOf("Shzt");
		//单独处理是否状态字段
		int sfdfIndex = wsqjTopEnArray.indexOf("Sfdf");
		if(wsqjTopList!=null){
			//取得实际表头
			for(int i=0;i<indexList.length;i++){
				colListCN[i] = wsqjTopCn[indexList[i]];
			}
		}
		
		queryReturnData.setTopTr(((ArrayList<HashMap<String, String>>)dao.arrayToList(colList,colListCN)));
		for(String[] temp : wsqjList){
			if(temp.length>1){
			String [] tempRsOne = new String[indexList.length];
			for(int i=0;i<indexList.length;i++){
				tempRsOne[i] = Base.isNull(temp[indexList[i]]) ? "" : temp[indexList[i]];
				if(shztIndex!=-1){
					if(indexList[i].equals(shztIndex)){
						if(tempRsOne[i].equalsIgnoreCase("0")){
							tempRsOne[i]="未处理";
						}else if(tempRsOne[i].equalsIgnoreCase("1")){
							tempRsOne[i]="通过";
						}else if(tempRsOne[i].equalsIgnoreCase("2")){
							tempRsOne[i]="未通过";
						}
					}
				}else if(sfdfIndex!=-1){
					if(indexList[i].equals(sfdfIndex)){
						if(tempRsOne[i].equalsIgnoreCase("true")){
							tempRsOne[i]="已答复";
						}else if(tempRsOne[i].equalsIgnoreCase("false")){
							tempRsOne[i]="未答复";
						}
					}
				}
			}
			rs.add(tempRsOne);
			}
			
		}
		queryReturnData.setRs(rs);
		return queryReturnData;
	}
	
	/**
	 * 
	 * @param myModel
	 * @return
	 */
	private ZjcmPage getGetListPage(ZjcmModel myModel) {
		Pages pages = myModel.getPages();
		String currentPage = ((Integer)(pages.getCurrentPage()-1)).toString();
		String pageSize = ((Integer)pages.getPageSize()).toString();
		ZjcmPage zjcmPage = new ZjcmPage();
		zjcmPage.setCurrentPage(currentPage);
		zjcmPage.setPageSize(pageSize);
		return zjcmPage;
	}
	
	/**
	 * 获取学院学生请假信息
	 * @param myModel 
	 * @param userName
	 * @return
	 */
	public QueryReturnData getXyqjxx(ZjcmModel myModel) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 根据辅导员取到班级代码获取班级学生信息
	 * @param myModel 
	 * @param userName
	 * @return
	 */
	public QueryReturnData getXxqjxx(ZjcmModel myModel) {
		// TODO 自动生成方法存根
		return null;
	}
	
	/**
	 * 网上请假单个审核
	 * @param pk
	 * @return
	 * @throws RemoteException 
	 */
	public HashMap<String, String> getWsqjshOne(String pk) throws RemoteException {
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		HashMap<String,String> rs = new HashMap<String,String>(); 
		try {
			try {
				myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
				String[][] wsqjOneList = myStub.wsqj_qingjia_single_get(pk);
				rs.put("pk", wsqjOneList[1][0]);
				rs.put("xn", wsqjOneList[1][1]);
				rs.put("xq", wsqjOneList[1][2]);
				rs.put("xydm", wsqjOneList[1][3]);
				rs.put("xymc", wsqjOneList[1][4]);
				rs.put("bjdm", wsqjOneList[1][5]);
				rs.put("bjmc", wsqjOneList[1][6]);
				rs.put("xm", wsqjOneList[1][7]);
				rs.put("xh", wsqjOneList[1][8]);
				rs.put("qjlxdm", wsqjOneList[1][9]);
				rs.put("qsrq", wsqjOneList[1][10]);
				rs.put("qjts", wsqjOneList[1][11]);
				rs.put("qjyy", wsqjOneList[1][12]);
				rs.put("qjsj", wsqjOneList[1][13]);
				rs.put("shzt", wsqjOneList[1][14]);
				rs.put("shyj", wsqjOneList[1][15]);
				rs.put("clr", wsqjOneList[1][16]);
				rs.put("clsj", wsqjOneList[1][17]);
			} catch (AxisFault e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean wsqjshSave(String pk, ZjcmModel myModel) {
		String shzt = myModel.getShzt();
		String shyj = myModel.getShyj();
		String clr = myModel.getClr();
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		String result=null;
		try {
			myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
			result = myStub.wsqj_qingjia_reply(pk, shzt, shyj, clr);
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(!result.equalsIgnoreCase("-1")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据辅导员取到班级代码获取班级学生请假信息
	 * @param myModel 
	 * @return
	 */
	public QueryReturnData getLyxx(ZjcmModel myModel) {
		QueryReturnData queryReturnData = new QueryReturnData();
		ZjcmPage zjcmPage = getGetListPage(myModel);
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		Pages pages = myModel.getPages();
		ArrayList<String[]> rs = new ArrayList<String[]>(); 
		String[] colList = new String[]{"LyId","XueNian","XueQi","XyMc","BjMc","XueHao","XingMing","LylxMc","BiaoTi","Lysj","Sfdf","Dfr"};
		Integer[] indexList = new Integer[colList.length];
		String[] colListCN = new String[colList.length];
		String bjdm = myModel.getBjdm();
		String[][] wsqjList = null;
		try {
			try {
				myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
				if(bjdm!=null&&!bjdm.equalsIgnoreCase("")){
					wsqjList = myStub.liuyan_ly_banji_get(zjcmPage.getCurrentPage(), zjcmPage.getPageSize(), bjdm, myModel.getKssj(), myModel.getJssj(), myModel.getXh(), myModel.getShzt());
				}else{
					wsqjList = myStub.liuyan_ly_all_get(zjcmPage.getCurrentPage(),zjcmPage.getPageSize(),myModel.getLxid(),myModel.getKssj(), myModel.getJssj(), myModel.getXh(), myModel.getShzt());
				}
				pages.setMaxRecord(Integer.parseInt(wsqjList[0][0]));
				String[][] wslyTopList = myStub.liuyan_biaotou_get();
				queryReturnData = getRsForToptr(rs, colList, indexList, colListCN, wsqjList, wslyTopList);
			} catch (AxisFault e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return queryReturnData;
	}

	public boolean wsqjlxSave(String pk, ZjcmModel myModel) {
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		String result=null;
		String qjlxmc = myModel.getQjlxmc();
		String bz = myModel.getBz();
		try {
			myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
			if(DealString.toGBK(pk).equalsIgnoreCase("")){
				result = myStub.wsqj_qjlx_create(qjlxmc, bz);
			}else{
				result = myStub.wsqj_qjlx_update(pk,qjlxmc, bz);
			}
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(!result.equalsIgnoreCase("-1")){
			return true;
		}else{
			return false;
		}
	}

	public boolean wsqjlxDel(String pk) {
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		String result=null;
		try {
			myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
			result = myStub.wsqj_qjlx_delete(pk);
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(result.equalsIgnoreCase("0")){
			return true;
		}else{
			return false;
		}
	}

	public List<HashMap<String, String>> getWslyForWebService() {
		// TODO 自动生成方法存根
		return null;
	}

	public QueryReturnData getJszbxx(ZjcmModel myModel) {
		QueryReturnData queryReturnData = new QueryReturnData();
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>(); 
		String[] colList = new String[]{"zbrq","bmid","bmmc","zbry"};
		String[] colListCN = new String[]{"值班日期","部门id","部门名称","值班人员"};
		String[][] JszbList = null;
		try {
			try {
				myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
				JszbList = myStub.zhiban_base_get(DealString.toGBK(myModel.getBmdm()));
				for(String[] temp : JszbList){
					if(temp.length>1){
					rs.add(temp);
					}
				}
				queryReturnData.setRs(rs);
				queryReturnData.setTopTr(((ArrayList<HashMap<String, String>>)dao.arrayToList(colList,colListCN)));
			} catch (AxisFault e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return queryReturnData;
	}

	public boolean jszbSave(ZjcmModel myModel) {
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		String result=null;
		String fdyxm = myModel.getFdyxm();
		String zgh = myModel.getZgh();
		String zbrq = myModel.getZbrq();
		DAO dao = DAO.getInstance();
		String[] bmtemp = dao.getOneRs("select bmdm,bmmc from view_fdyxx where zgh = ?", new String []{zgh}, new String[]{"bmdm","bmmc"});
		String bmdm = bmtemp[0];
		String bmmc = bmtemp[1];
		try {
			myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
			result = myStub.zhiban_base_add(zbrq,bmdm, bmmc, fdyxm);
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(!result.equalsIgnoreCase("-1")){
			return true;
		}else{
			return false;
		}
	}

	public boolean wslySave(ZjcmModel myModel) {
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		String result=null;
		try {
			myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
			result = myStub.liuyan_ly_add(myModel.getXn(), myModel.getXq(), myModel.getXydm(), myModel.getXymc(), myModel.getBjdm(),myModel.getBjmc(), myModel.getXm(), myModel.getXh(),myModel.getLylxdm(),myModel.getBt(),myModel.getNr());
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(!result.equalsIgnoreCase("-1")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取网上留言回复信息列表
	 * @param myModel 
	 * @return
	 */
	public QueryReturnData getWslyhf(ZjcmModel myModel) {
		QueryReturnData queryReturnData = new QueryReturnData();
		ZjcmPage zjcmPage = getGetListPage(myModel);
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		Pages pages = myModel.getPages();
		ArrayList<String[]> rs = new ArrayList<String[]>(); 
		String[] colList = new String[]{"LyId","XueNian","XueQi","XyMc","BjMc","XueHao","XingMing","LylxMc","BiaoTi","Lysj","Sfdf"};
		Integer[] indexList = new Integer[colList.length];
		String[] colListCN = new String[colList.length];
		String bjdm = myModel.getBjdm();
		String[][] lyxxList = null;
		try {
			try {
				myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
				if(bjdm!=null&&!bjdm.equalsIgnoreCase("")){
					lyxxList = myStub.liuyan_ly_banji_get(zjcmPage.getCurrentPage(), zjcmPage.getPageSize(), bjdm, myModel.getKssj(), myModel.getJssj(), myModel.getXh(), myModel.getSfdf());
				}else{
					lyxxList = myStub.liuyan_ly_all_get(zjcmPage.getCurrentPage(), zjcmPage.getPageSize(), DealString.toGBK(myModel.getLylxdm()),DealString.toGBK(myModel.getKssj()), DealString.toGBK(myModel.getJssj()), DealString.toGBK(myModel.getXh()), DealString.toGBK(myModel.getSfdf()));
				}
				pages.setMaxRecord(Integer.parseInt(lyxxList[0][0]));
				String[][] lyxxTopList = myStub.liuyan_biaotou_get();
				queryReturnData = getRsForToptr(rs, colList, indexList, colListCN, lyxxList, lyxxTopList);
			} catch (AxisFault e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return queryReturnData;
	}
	
	/**
	 * 网上请假单个审核
	 * @param pk
	 * @return
	 * @throws RemoteException 
	 */
	public HashMap<String, String> getWslyhfOne(String pk) throws RemoteException {
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		HashMap<String,String> rs = new HashMap<String,String>(); 
		try {
			try {
				myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
				String[][] wsqjOneList = myStub.liuyan_ly_single_get(pk);
				rs.put("pk", wsqjOneList[1][0]);
				rs.put("xn", wsqjOneList[1][1]);
				rs.put("xq", wsqjOneList[1][2]);
				rs.put("xydm", wsqjOneList[1][3]);	
				rs.put("xymc", wsqjOneList[1][4]);
				rs.put("bjdm", wsqjOneList[1][5]);
				rs.put("bjmc", wsqjOneList[1][6]);
				rs.put("xm", wsqjOneList[1][7]);
				rs.put("xh", wsqjOneList[1][8]);
				rs.put("lylxdm", wsqjOneList[1][9]);
				rs.put("bt", wsqjOneList[1][10]);
				rs.put("nr", wsqjOneList[1][11]);
				rs.put("lysj", wsqjOneList[1][12]);
				rs.put("sfdf", wsqjOneList[1][13]);
				rs.put("dfr", wsqjOneList[1][14]);
				rs.put("dfbm", wsqjOneList[1][15]);
				rs.put("dfnr", wsqjOneList[1][16]);
				rs.put("dfsj", wsqjOneList[1][17]);
				rs.put("lylxmc", wsqjOneList[1][18]);
			} catch (AxisFault e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 网上留言答复保存
	 * @param myModel
	 * @param pk
	 * @return
	 */
	public boolean wslyhfSave(ZjcmModel myModel, String pk) {
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		String result=null;
		String lyid = pk;
		String dfr = myModel.getDfr();
		String dfbm =  myModel.getDfbm();
		String dfnr =  myModel.getDfnr();
		try {
			myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
			result = myStub.liuyan_ly_reply(lyid,dfr,dfbm,dfnr);
		} catch (RemoteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(!result.equalsIgnoreCase("-1")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取网上值班信息
	 * @param myModel 
	 * @return
	 */
	public List<HashMap<String, String>> getJszbFww(String day) {
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxt2Soap12Stub myStub;
		String[][] zbxxList = null;
		List<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		String dayi = "0";
		if(day.equalsIgnoreCase("mingtian")){
			dayi="1";
		}else if(day.equalsIgnoreCase("houtian")){
			dayi="2";
		}
		try {
			try {
				myStub = new Ws_xgxt2Soap12Stub(new URL("http://ls.zjicm.edu.cn/WebService/ws_xgxt2.asmx?WSDL"),remoteWebserviceLocator);
				zbxxList = myStub.zhiban_base_zizhu_get2();
				for(String[] temp : zbxxList){
					if(temp.length>1){
						if(temp[0].equalsIgnoreCase(dayi)){
							HashMap<String, String> rs = new HashMap<String, String>();
							rs.put("bmmc", temp[1]);
							rs.put("zbr", temp[2]);
							result.add(rs);
						}
					}
				}
			} catch (AxisFault e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String[][] getLytjxx() throws SQLException {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxt2Soap12Stub myStub;
		String[][] lytjList1 = null;
		String[][] lytjList2 = null;
		String[][] lytjList3 = null;
		DAO dao = DAO.getInstance();
		String[] xymcList = dao.getArray("select distinct xymc from view_njxyzybj order by xymc", new String[]{}, "xymc");
		//定义一个新的列表
		String[][]  result = new String[7][xymcList.length];
		
		for(int i = 0;i<xymcList.length;i++ ){
			result[0][i] = xymcList[i].replace(Base.YXPZXY_KEY,"");
		}
		try {
			try {
				myStub = new Ws_xgxt2Soap12Stub(new URL("http://ls.zjicm.edu.cn/WebService/ws_xgxt2.asmx?WSDL"),remoteWebserviceLocator);
				lytjList1 = myStub.liuyan_ly_stat_get2("1");
				lytjList2 = myStub.liuyan_ly_stat_get2("2");
				lytjList3 = myStub.liuyan_ly_stat_get2("3");
			} catch (AxisFault e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		for(int i =0;i<xymcList.length;i++){
			result[1][i] = "0";
			result[2][i] = "0";
			result[3][i] = "0";
			result[4][i] = "0";
			result[5][i] = "0";
			result[6][i] = "0";
			for(int j =0;j<lytjList1.length;j++){
				if(lytjList1[j][0].equalsIgnoreCase(xymcList[i])){
					result[1][i] = lytjList1[j][1];
					result[2][i] = lytjList1[j][2];
				}
			}
			for(int j =0;j<lytjList2.length;j++){
				if(lytjList2[j][0].equalsIgnoreCase(xymcList[i])){
					result[3][i] = lytjList2[j][1];
					result[4][i] = lytjList2[j][2];
				}
			}
			for(int j =0;j<lytjList3.length;j++){
				if(lytjList3[j][0].equalsIgnoreCase(xymcList[i])){
					result[5][i] = lytjList3[j][1];
					result[6][i] = lytjList3[j][2];
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取网上留言回复信息列表
	 * @param lylxdm 
	 * @return
	 */
	public QueryReturnData getWslyhfForFww(String lylxdm) {
		QueryReturnData queryReturnData = new QueryReturnData();
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		if(lylxdm==null){
			lylxdm="1";
		}
		ArrayList<String[]> rs = new ArrayList<String[]>(); 
		String[] colList = new String[]{"XyMc","BiaoTi","Lysj"};
		Integer[] indexList = new Integer[colList.length];
		String[] colListCN = new String[colList.length];
		String[][] lyxxList = null;
		try {
			try {
				myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
				lyxxList = myStub.liuyan_ly_topn_get(lylxdm,"6");
				String[][] lyxxTopList = myStub.liuyan_biaotou_get();
				queryReturnData = getRsForToptr(rs, colList, indexList, colListCN, lyxxList, lyxxTopList);
			} catch (AxisFault e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return queryReturnData;
	}
	
	
	/**
	 * 获取网上留言回复信息列表
	 * @param myModel 
	 * @return
	 */
	public QueryReturnData wslyFwwMore(ZjcmModel myModel) {
		QueryReturnData queryReturnData = new QueryReturnData();
		ZjcmPage zjcmPage = getGetListPage(myModel);
		Ws_xgxtLocator remoteWebserviceLocator = new Ws_xgxtLocator();
		Ws_xgxtSoap12Stub myStub;
		Pages pages = myModel.getPages();
		ArrayList<String[]> rs = new ArrayList<String[]>(); 
		String[] colList = new String[]{"LylxMc","BiaoTi","NeiRong","Lysj","Dfr","Dfbm","Dfnr","Dfsj"};
		Integer[] indexList = new Integer[colList.length];
		String[] colListCN = new String[colList.length];
		String[][] lyxxList = null;
		try {
			try {
				myStub = new Ws_xgxtSoap12Stub(new URL(Base.getInitProperties().get("zjcmWebUrl")),remoteWebserviceLocator);
				lyxxList = myStub.liuyan_ly_all_get(zjcmPage.getCurrentPage(), zjcmPage.getPageSize(), DealString.toGBK(myModel.getLylxdm()),DealString.toGBK(myModel.getKssj()), DealString.toGBK(myModel.getJssj()), DealString.toGBK(myModel.getXh()), "1");
				pages.setMaxRecord(Integer.parseInt(lyxxList[0][0]));
				String[][] lyxxTopList = myStub.liuyan_biaotou_get();
				queryReturnData = getRsForToptr(rs, colList, indexList, colListCN, lyxxList, lyxxTopList);
			} catch (AxisFault e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return queryReturnData;
	}
}
