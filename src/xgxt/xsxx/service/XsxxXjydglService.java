package xgxt.xsxx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.dao.XsxxXjydglDAO;
import xgxt.xtwh.common.dao.XtlcglDAO;
import xgxt.xtwh.common.service.XtlcglService;

import common.GlobalsVariable;


public class XsxxXjydglService {
	XsxxXjydglDAO xjydglDao = new XsxxXjydglDAO();
	
	/**
	 * ��ʼ���춯��Ϣ
	 * */
	public HashMap<String, String> initYdxx(HashMap<String, String> map){
		XsxxglService xsxxglService = new XsxxglService();
		//�춯���ȡ������
		map.put("ydxh", xsxxglService.getMaxYdxh());
		map.put("ydhxydm", map.get("xydm"));
		map.put("ydhzydm", map.get("zydm"));
		map.put("ydhbdm", map.get("bjdm"));
		map.put("ydhnj", map.get("nj"));
		map.put("ydhxz", map.get("xz"));
		map.put("ydhxjztm", map.get("xjztm"));
		map.put("ydhsfzx", map.get("sfzx"));
		
		map.put("save_ydhxydm", map.get("xydm"));
		map.put("save_ydhzydm", map.get("zydm"));
		map.put("save_ydhbdm", map.get("bjdm"));
		map.put("save_ydhnj", map.get("nj"));
		map.put("save_ydhxz", map.get("xz"));
		map.put("save_ydhxjztm", map.get("xjztm"));
		map.put("save_ydhsfzx", map.get("sfzx"));
		
		map.put("ydqxydm", map.get("xydm"));
		map.put("ydqxymc", map.get("xymc"));
		map.put("ydqzydm", map.get("zydm"));
		map.put("ydqzymc", map.get("zymc"));
		map.put("ydqbdm", map.get("bjdm"));
		map.put("ydqbjmc", map.get("bjmc"));
		map.put("ydqnj", map.get("nj"));
		map.put("ydqxz", map.get("xz"));
		map.put("ydqxjztm", map.get("xjztm"));
		map.put("ydqsfzx", map.get("sfzx"));		
		return map;
	}
	
	/**
	 * ��ʼ��ѧ���춯��˼�¼
	 * */
	public boolean initXjydshjl(StudentInfoForm model,User user){
		boolean result = false;
		XtlcglDAO lcglDao = new XtlcglDAO();
		//��������е����и�λ
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String shlcid = lcglDao.getGnlcxx(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm()).get("lcid");
		list = lcglDao.getShlcgw(shlcid);
		
		xjydglDao.saveXjydshjl(model,list,user);		
		return result;
	}
	
	/**
	 * ��ȡ���ʱҪ��ѯ���ֶ�
	 * */
	public String[] getXjydshCol(){
		XtlcglService lcglService = new XtlcglService();
		String[] colList = getXjydcxColEn("sh");	
		//���붯̬��ȡ�������Ϣ�ֶ�
		String[] shCol = lcglService.getGnshgwmcxx(GlobalsVariable.GNDM_XSXX_XJYDSH);
		colList = StringUtils.joinStrArr(colList,shCol);
		return colList;
	}
	
	/**
	 * ��ȡѧ���춯��ѯ����ʾ����(Ӣ��)
	 * */
	public String[] getXjydcxColEn(String lx){
		String[] colList = new String[]{};	
		if("cx".equalsIgnoreCase(lx)){
			colList = new String[]{"pkValue", "xh", "xm", "xb", "ydlbmc", "ydrq", "ydjzrq", "ydhnj",
					"ydhxymc", "ydhzymc", "ydhbjmc"};
			//, "ydqnj","ydqxymc", "ydqzymc", "ydqbjmc", "ydqxz", "ydhxz"
		}else if("sh".equalsIgnoreCase(lx)){
			colList = new String[]{"disabled", "bgcolor", "pkValue", "xh", "xm", "xb", "ydlbmc", "ydrq", 
					"ydjzrq", "ydhnj", "ydhxymc", "ydhzymc", "ydhbjmc"};
			//, "ydqnj", "ydqxymc", "ydqzymc", "ydqbjmc", "ydqxz", "ydhxz"
		}
		return colList;
	}
	
	/**
	 * ��ȡѧ���춯��ѯ����ʾ����(����)
	 * */
	public String[] getXjydcxColCn(String lx){
		String[] colList = new String[]{};	
		if("cx".equalsIgnoreCase(lx)){
			colList = new String[]{"����", "ѧ��", "����", "�Ա�", "�춯���", "�춯����", "�춯��ֹ����", "�춯���꼶", "�춯��"+Base.YXPZXY_KEY, 
					"�춯��רҵ", "�춯��༶"};//, "�춯ǰ�꼶", "�춯ǰѧԺ", "�춯ǰרҵ", "�춯ǰ�༶", "�춯ǰѧ��", "�춯��ѧ��"
		}else if("sh".equalsIgnoreCase(lx)){
			colList = new String[]{"�Ƿ�ɼ�", "����ɫ", "����", "ѧ��", "����", "�Ա�", "�춯���", 
					"�춯����", "�춯��ֹ����", "�춯���꼶", "�춯��"+Base.YXPZXY_KEY, "�춯��רҵ", "�춯��༶"};
			//, "�춯ǰ�꼶", "�춯ǰѧԺ", "�춯ǰרҵ", "�춯ǰ�༶", "�춯ǰѧ��", "�춯��ѧ��"
		}
		return colList;
	}
	
	/**
	 * ��ȡѧ���춯��˲�ѯ��ͷ��Ϣ
	 * */
	public List<HashMap<String, String>> getXjydshToptr(String ydlbdm){	
		XtlcglService lcglService = new XtlcglService();
		//��̬��ȡ�������Ϣ�ֶ�		
		String[] shCol = lcglService.getGnshgwmcxx(GlobalsVariable.GNDM_XSXX_XJYDSH+ydlbdm);
		//Ӣ����
		String[] enCol = getXjydcxColEn("sh");
		//������
		String[] cnCol = getXjydcxColCn("sh");
		//�к���붯̬�������
		enCol = StringUtils.joinStrArr(enCol, shCol);
		cnCol = StringUtils.joinStrArr(cnCol, shCol);
		
		return xjydglDao.arrayToList(enCol, cnCol);
	}
	
	/**
	 * ��ȡѧ���춯������̲�ѯ��ͷ��Ϣ
	 * */
	public List<HashMap<String, String>> getXjydshlcToptr(){	
		//Ӣ����
		String[] enCol = {"ydlbm","ydlbmc","xjzt","sfzx","shlcmc","tjbcyfs","shgw"};
		//������
		String[] cnCol = {"�춯������","�춯���","ѧ��״̬","�Ƿ���У","�������","ͬ������뷽ʽ","��˸�λ"};
		
		return xjydglDao.arrayToList(enCol, cnCol);
	}	
	
	/**
	 * ��ȡѧ���춯��˸�λ
	 * */
	public List<HashMap<String, String>> getXjydshgw(String yhlbm){
		XtlcglService lcglService = new XtlcglService();
		//��̬��ȡ�������Ϣ�ֶ�		
		String[] shColEn = lcglService.getGnshgwxx(GlobalsVariable.GNDM_XSXX_XJYDSH + yhlbm);
		String[] shColCn = lcglService.getGnshgwmcxx(GlobalsVariable.GNDM_XSXX_XJYDSH + yhlbm);
		
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		
		if(shColEn != null && shColEn.length>0 && StringUtils.isNotNull(shColEn[0])){
			rs = xjydglDao.arrayToList(shColEn,shColCn);
		}
		return rs;
	}
		
	/**
	 * ѧ���춯���Ĭ��Ҫ��ѯ���ֶ���Ϣ
	 * */
	public String getXjydshDefColumns(User user){
		XtlcglService lcglService = new XtlcglService();
		String[] column = lcglService.getGnshgwmcxx(GlobalsVariable.GNDM_XSXX_XJYDSH);
		
		return StringUtils.joinStrByArray(column,",");
	}
			
	/**
	 * ��ȡ���������Ϣ
	 * */
	public HashMap<String, String> getXjydshxx(User user,String pkValue,String ydlbm){
		XtlcglService xtlcglService = new XtlcglService();
		HashMap<String, String> map = new HashMap<String, String>();
		//��ѯ�û����и�λ
		List<HashMap<String, String>> yhgwList = xtlcglService.getYhgwList(GlobalsVariable.GNDM_XSXX_XJYDSH+ydlbm, 
																			user.getUserName());
		//��ѯ�û�������
		map = xjydglDao.selectYhshyj(yhgwList,user,pkValue);
		return map;
	}
	
	/**
	 * ��ȡ���������Ϣ
	 * */
	public HashMap<String, String> getXjydshxx(String xtgwid,String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		//��ѯ�û�������
		map = xjydglDao.selectXjydshyj(xtgwid,pkValue);
		return map;
	}
	
	/**
	 * ѧ���춯�����Ϣ
	 * */
	public List<HashMap<String, String>> getXjydshxxOne(String pkValue){
		return xjydglDao.selectXjydshxx(pkValue);
	}
	
	/**
	 * ѧ���춯��˲�ѯ
	 * */
	public List<String[]> queryXjydsh(StudentInfoForm model,User user){
		XtlcglService lcglService = new XtlcglService();
		String[] colList = getXjydcxColEn("sh");
		String[] shgwArr = lcglService.getGnshgwxx(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm());
			
		
		return xjydglDao.selectXjydsh(model,user,shgwArr,colList);
	}
	
	/**
	 * ѧ���춯�����ѯ
	 * */
	public List<String[]> queryXjydsqxx(StudentInfoForm model,User user){
		XtlcglService lcglService = new XtlcglService();
		String[] colList = getXjydcxColEn("cx");
		String[] shgwArr = lcglService.getGnshgwxx(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm());
		
		return xjydglDao.selectXjydsqxx(model,user,shgwArr,colList);
	}
	
	
	/**
	 * ѧ���춯������̲�ѯ
	 * */
	public List<String[]> queryXjydlbshlc(StudentInfoForm model){
		return xjydglDao.selectXjydshlc(model);
	}
	
	/**
	 * ��ȡѧ���춯��Ϣ
	 * */
	public HashMap<String, String> getXjydshlcxx(String ydlbm){
		HashMap<String, String> map = xjydglDao.selectXjydlbxx(ydlbm);
		map.put("save_shlcid", map.get("shlcid"));
		return map;
	}
	
	/**
	 * ѧ���춯��˱���
	 * */
	public boolean modifyXsxjxx(StudentInfoForm model, String pkValue){
		boolean result = false;
		String shjg = model.getShjg();
		XtlcglService lcglService = new XtlcglService();
		
		if("ͨ��".equalsIgnoreCase(shjg)){
			//ԭʼֵ�����غ� ���ϼ���˵�״̬���˻أ���Ӧ���������״̬��Ϊ������
			boolean backFlag = xjydglDao.isBack(model,pkValue);			
			if(backFlag){
				xjydglDao.updateXjydsjshzt(model, pkValue);
			}
			result = xjydglDao.updateXjydsh(model,pkValue);				
			//�����ǰ�û������һ����ˣ������޸�ѧ����ѧ����Ϣ
			boolean last = lcglService.sfzhyjsh(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm(),
												model.getXtgwid(),
												"xg_xsxx_xjydxx_shb");
			if(result && last){
				//�޸�ѧ����Ϣ
				xjydglDao.updateXjxx(pkValue);
			}
		}else if("�˻�".equalsIgnoreCase(shjg)){
			//�޸ĵ�ǰ�û�����˽����Ϣ
			result = xjydglDao.updateXjydsh(model,pkValue);	
			//�޸���һ�������״̬Ϊ������
//			if(result){
//				xjydglDao.updateXjydxjshzt(model, pkValue);
//			}
		}else{
			//�޸ĵ�ǰ�û�����˽����Ϣ
			result = xjydglDao.updateXjydsh(model, pkValue);
		}
		return result;
	}
	
	/**
	 * ѧ���춯�������
	 * */
	public boolean xjydshBatch(StudentInfoForm model){
		boolean result = false;
		String[] pkValue = model.getPrimarykey_cbv();
		
		for(int i=0; i< pkValue.length; i++){
			result = modifyXsxjxx(model, pkValue[i]);
			if (!result) break;
		}
		return result;
	}
	
	/**
	 * ѧ���춯ȡ�����
	 * */
	public boolean xjydqxsh(StudentInfoForm model, User user){
		boolean result = false;
		String[] pkValue = model.getPrimarykey_cbv();
		try{
			for(int i=0; i< pkValue.length; i++){
				result = StandardOperation.update("xg_xsxx_xjydxx_shb", 
										new String[]{"shzt","shr","shsj","shyj"}, 
										new String[]{"δ���","","",""}, 
										"ydxh||xtgwid", 
										pkValue[i]+model.getXtgwid(), 
										user);
				if (!result) break;
			}
		}catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * ����������̶�Ӧ��Ϣ
	 * */
	public boolean saveShlcdyxxb(StudentInfoForm model, User user){
		return xjydglDao.saveShlcdyxxb(model,user);
	}
	
	/**
	 * �ж��Ƿ���޸�ѧ���춯��Ϣ
	 * */
	public boolean sfkxgXjydxx(String ydxh){
		//�ж��Ƿ������ͨ������ͨ���ļ�¼
		int yshs = xjydglDao.getXjydyshjls(ydxh);//����˼�¼��
		
		return yshs >0 ? false : true;
	}
}
