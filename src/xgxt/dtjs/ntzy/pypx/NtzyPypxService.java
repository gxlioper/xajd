package xgxt.dtjs.ntzy.pypx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.utils.CommonQueryDAO;

public class NtzyPypxService extends BasicExtendService{
	private DAO dao = null;
	
	public NtzyPypxService(){
		dao = DAO.getInstance();
	}
	
	/**
	 * ģ���·���Ķ�Ӧ
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getPath(String lx){
		String[] en = null;
		String[] cn = null;
		
		if("sq".equalsIgnoreCase(lx)){
			en = new String[]{
				"ntzy_pypx.do?method=yxtzbsq",
				"ntzy_pypx.do?method=tytgbsq&mk=yxty",	
				"ntzy_pypx.do?method=tytgbsq&mk=yxtgb",
				"ntzy_pypx.do?method=tzzxshsq&mk=tzzgb",
				"ntzy_pypx.do?method=tzzxshsq&mk=xshgb"
				};
			
			cn = new String[]{
					"������֧������",
					"������Ա����",
					"�����Ÿɲ�����",
					"��������֧�ɲ�����",
					"����ѧ����ɲ�����"
					};
		}else if("sh".equalsIgnoreCase(lx)){
			en = new String[]{
					"ntzy_pypx.do?method=yxtzbsh",
					"ntzy_pypx.do?method=tytgbsh&mk=yxty",	
					"ntzy_pypx.do?method=tytgbsh&mk=yxtgb",
					"ntzy_pypx.do?method=tytgbsh&mk=tzzgb",
					"ntzy_pypx.do?method=tytgbsh&mk=xshgb"};
			
			cn = new String[]{
					"������֧�����",
					"������Ա���",
					"�����Ÿɲ����",
					"��������֧�ɲ����",
					"����ѧ����ɲ����"};
		}else if("cx".equalsIgnoreCase(lx)){
			en = new String[]{
					"ntzy_pypx.do?method=yxtzbcx",
					"ntzy_pypx.do?method=tytgbcx&mk=yxty",	
					"ntzy_pypx.do?method=tytgbcx&mk=yxtgb",
					"ntzy_pypx.do?method=tytgbcx&mk=tzzgb",
					"ntzy_pypx.do?method=tytgbcx&mk=xshgb"};
			
			cn = new String[]{
					"������֧����ѯ",
					"������Ա��ѯ",
					"�����Ÿɲ���ѯ",
					"��������֧�ɲ���ѯ",
					"����ѧ����ɲ���ѯ"};
		}
		
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ����ѧ�Ż��ĳѧ��ְ��
	 * @param xh
	 * @return
	 */
	public String getZw(String xh){
		String sql = "select bjgbmc from view_bjgbxx where jssj>to_char(sysdate,'yyyymmdd') and xh=?";
		
		List<HashMap<String, String>> list = dao.getArrayList(sql, new String[]{xh}, new String[]{"bjgbmc"});
	
		StringBuilder zw = new StringBuilder();
		
		for(HashMap<String,String> map : list){
			zw.append(map.get("bjgbmc")).append(",");
		}
		
		if(zw.length()>0){
			zw.deleteCharAt(zw.length()-1);
		}
		
		return zw.toString();
	
	}
	
	/**
	 * ���ѧ����Ϣ
	 * @param xh
	 * @return
	 */
	public Map<String, String> getXsInfo(String xh){
		StringBuilder sql = new StringBuilder();
		
		sql = sql.append("select a.*,")
				.append("(select rtrq from tyxxb where xh=a.xh) rtrq,")
				.append("(select rdsj from dyxxb where xh=a.xh) rdsj ")
				.append("from view_xsxxb a where a.xh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	
	/**
	 * �ж��Ƿ�����Ա
	 * @param pkValue
	 * @return
	 */
	public boolean checkSfty(String pkValue){
		String[] colList = new String[]{"sfty"};
		Map<String,String> map = CommonQueryDAO.commonQueryOne("view_czxx_tyxx", colList, "pk", pkValue);
		boolean isTy = "��".equalsIgnoreCase(map.get("sfty")) ? true : false;
		return isTy;
	}
	
	/**
	 * �����������������Ϣ
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getPypxInfo(String pkValue){
		StringBuilder sql = new StringBuilder();	
		
		sql = sql.append("select a.*,")
				.append("(select rtrq from tyxxb where xh=a.xh) rtrq,")
				.append("(select rdsj from dyxxb where xh=a.xh) rdsj,")
				.append("(select whcd from view_xsxxb where xh = a.xh) whcd,")
				.append("(select sjhm from view_xsxxb where xh = a.xh) sjhm,")
				.append("(select jg from view_xsxxb where xh = a.xh) jg,")
				.append("(select zzmmmc from view_xsxxb where xh = a.xh) zzmmmc,")
				.append("(select csrq from view_xsxxb where xh = a.xh) csrq,")
				.append("(select mzmc from view_xsxxb where xh = a.xh) mzmc ")
				.append("from view_dtjs_ntzy_pypx a ")
				.append("where xh||xn||mk=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pkValue});
	}
	
	public Map<String, String> getYxtzbInfo(String pkValue){
		String[] output = new String[]{"tzbmc", "xn", "sqr", "szyxtzz", "zygz", "xysh", "xxsh",
				"xyshyj", "xxshyj"};
		String tableName = "dtjs_ntzy_yxtzbsqb";
		return CommonQueryDAO.commonQueryOne(tableName, output, "tzbmc||xn", pkValue);
	}
}
