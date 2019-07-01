package xgxt.pjpy.comm.pjpy.pjlc.xmsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.form.SaveForm;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.tjsz.PjpyTjszUtils;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��Ŀ����_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class PjpyXmsqService extends PjpyCommService{
	private DAO dao = DAO.getInstance();
	
	/**
	 * ��ȡ������Ŀ��Ϣ
	 * @param xh
	 * @param xmszModel
	 * @return
	 */
	public Map<String, String> getSqxmInfo(String xh, PjpyXmszModel xmszModel){
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,sqsj,sqly,tjr from xg_pjpy_pjxmsqb where xh=? and xmdm=? ")
		   .append("and pjxn=? and pjxq=? and pjnd=?");
		
		String[] input = new String[]{xh, xmszModel.getXmdm(), xmszModel.getSqxn(), xmszModel.getSqxq(), xmszModel.getSqnd()};
		
		return dao.getMapNotOut(sql.toString(), input);
	}
	
	/**
	 * ����ѧ������
	 * @param xmszModel
	 * @param xmsqModel
	 * @return
	 */
	public boolean saveXssq(PjpyXmszModel xmszModel, PjpyXmsqForm xmsqModel){
		boolean flag = false;
		List<HashMap<String, String>> shlc = xmszModel.getShlc();
		
		String[] sqlArr = new String[shlc.size()+1];
		
		// �����������
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_pjxmsqb(xmdm,pjxn,pjxq,pjnd,xh,sqly,tjr,sqsj,kzzd1,kzzd2,kzzd3,kzzd4,kzzd5,kzzd6,kzzd7) values('")
		   .append(xmszModel.getXmdm()).append("','").append(xmszModel.getSqxn()).append("','")
		   .append(xmszModel.getSqxq()).append("','").append(xmszModel.getSqnd()).append("','")
		   .append(xmsqModel.getXh()).append("','").append(xmsqModel.getSqly()).append("','")
		   .append(xmsqModel.getTjr()).append("','").append(xmsqModel.getSqsj()).append("','")
		   .append(xmsqModel.getKzzd1()).append("','").append(xmsqModel.getKzzd2()).append("','")
		   .append(xmsqModel.getKzzd3()).append("','").append(xmsqModel.getKzzd4()).append("','")
		   .append(xmsqModel.getKzzd5()).append("','").append(xmsqModel.getKzzd6()).append("','")
		   .append(xmsqModel.getKzzd7()).append("')");
		
		sqlArr[0] = sql.toString();
		
		// ������Ŀ������̣�������Ŀ��˱���
		for(int i=1; i<sqlArr.length;i++){
			Map<String, String> shMap = shlc.get(i-1);
			String spgwId = shMap.get("gwid");
			sql = new StringBuilder();
			sql.append("insert into xg_pjpy_pjxmshb(xmdm,pjxn,pjxq,pjnd,xh,xtgwid) values('")
				 .append(xmszModel.getXmdm()).append("','").append(xmszModel.getSqxn()).append("','")
				 .append(xmszModel.getSqxq()).append("','").append(xmszModel.getSqnd()).append("','")
				 .append(xmsqModel.getXh()).append("','").append(spgwId).append("')");
			
			sqlArr[i] = sql.toString();
		}
		
		try {
			dao.runBatch(sqlArr);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * �޸�ѧ��������Ϣ
	 * @param xmsqModel
	 * @return
	 * @throws Exception
	 */
	public boolean updateXssq(PjpyXmsqForm xmsqModel){
		boolean flag = false;
		SaveForm saveForm = new SaveForm();
		
		String[] onezd = new String[]{"sqly","kzzd1","kzzd2","kzzd3","kzzd4","kzzd5","kzzd6","kzzd7"};
		
		String pkValue = xmsqModel.getXmdm() + xmsqModel.getSqxn() + xmsqModel.getSqxq()
						 + xmsqModel.getSqnd() + xmsqModel.getXh();
		
		saveForm.setOnezd(onezd);
		saveForm.setTableName("xg_pjpy_pjxmsqb");
		saveForm.setPkValue(new String[]{pkValue});
		saveForm.setPk("xmdm||pjxn||pjxq||pjnd||xh");
		
		try {
			flag = dao.updateData(saveForm, xmsqModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ���ѧ���������������Ŀ
	 * @param jbszMap
	 * @return
	 * @throws SQLException 
	 */
	public List<HashMap<String, String>> getPjxmForXssq(String xh, Map<String, String> map){
		
		// ���п�������Ŀ
		List<HashMap<String, String>> pjxmList = getPjxmForXssq(map);
		// ��������Ŀ
		List<HashMap<String, String>> xsxmList = getXsxmList(xh);
		// ѧ�������Ϣ
		List<HashMap<String, String>> xsshList = getXsshList(xh);
		//��������ʱ���ڵ���Ŀ
		List<HashMap<String, String>> notExistsTimeXmdm = getNotExistsTime();
		//������ƿ��عرյ���Ŀ
		List<HashMap<String, String>> kzkgColseXmdm = getKzkgColse();
		
		judgeTjk(pjxmList, xh);
		judgeSfsq(pjxmList, xsxmList);
		judgeSfsh(pjxmList, xsshList);
		judgeSqkg(pjxmList,notExistsTimeXmdm,"time");
		judgeSqkg(pjxmList,kzkgColseXmdm,"kzkg");
		return pjxmList;
	}
	
	
	
	/**
	 * ��������ʱ���ڵ���Ŀ
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String,String>> getNotExistsTime(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xmdm,sqkssj,sqjssj from xg_pjpy_sjszb where (")
		   .append("sysdate not between to_date(sqkssj,'yyyy-mm-dd')")
		   .append(" and to_date(sqjssj,'yyyy-mm-dd')+1)")
		   .append(" and pjxn=? and pjxq=? and pjnd=?");
		
		return dao.getListNotOut(sql.toString(), 
				new String[]{PjxtszModel.pjxtszModel.getPjxn(),
							 PjxtszModel.pjxtszModel.getPjxq(),
							 PjxtszModel.pjxtszModel.getPjnd()});
	}
	
	
	
	/**
	 * ������ƿ���Ϊ�رյ���Ŀ
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String,String>> getKzkgColse(){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select xmdm from xg_pjpy_sjszb where sqkzkg='1' ")
		   .append(" and pjxn=? and pjxq=? and pjnd=? ");
		
		return dao.getListNotOut(sql.toString(), 
				new String[]{PjxtszModel.pjxtszModel.getPjxn(),
							 PjxtszModel.pjxtszModel.getPjxq(),
							 PjxtszModel.pjxtszModel.getPjnd()});
	}
	
	
	
	/**
	 * �������жϣ���ʦѡ��ѧ��ʱ�ж�
	 * @param xh
	 * @param pkVal
	 * @return
	 */
	public String judgeTjk(String xh, String pkVal){
		PjpyTjszUtils tjszUtils = new PjpyTjszUtils();
		String message = "";
		
		PjpyXmszModel xmszModel = getXmszForXmdm(pkVal);		
		List<HashMap<String, String>> tjList = tjszUtils.getAllTj(xh, xmszModel.getXmdm());
		
		boolean[] flags = dao.checkExists("xg_pjpy_pjxmsqb", "xmdm||pjxn||pjxq||pjnd||xh", new String[]{xmszModel.getXmdm() + xmszModel.getSqxn()
				+ xmszModel.getSqxq() + xmszModel.getSqnd() + xh});

		for(boolean flag : flags){
			if(flag){
				message = "��ѧ�������������Ŀ��";
			}
		}
		
		for(Map<String, String> tjMap : tjList){
			tjMap.put("sqzq", xmszModel.getSqzq());
			
			//===========2011.07.22 edit by great luo=================
			message = tjszUtils.getStuPjzg(xh, tjMap);
			//message = tjszUtils.judgeTj(xh, tjMap);
			
			if(StringUtils.isNotNull(message)){
				break;
			}
		}
		
		return message;
	}
	
	/**
	 * ѧ���������ж�
	 * @param allXmList
	 * @param xh
	 */
	private void judgeTjk(List<HashMap<String, String>> allXmList,String xh){
		PjpyTjszUtils tjszUtils = new PjpyTjszUtils();
		
		for(Map<String, String> map : allXmList){
			String xmdm = map.get("xmdm");
			String sqzq = map.get("sqzq");
			
			List<HashMap<String, String>> tjList = tjszUtils.getAllTj(xh, xmdm);
			
			for(Map<String, String> tjMap : tjList){
				tjMap.put("sqzq", sqzq);
				
				//==============2011.7.22 edit by great luo======================
				//String message = tjszUtils.judgeTj(xh, tjMap);
				String message = tjszUtils.getStuPjzg(xh, tjMap);
				if(StringUtils.isNotNull(message)){
					map.put("cz", "bkxg");
					map.put("yy", message);
					map.put("sfmztjk", "no");
					break;
				}
			}
		}
	}
	
	/**
	 * ���ѧ���������������Ŀ.��������ǰ���״̬
	 * @param xh
	 * @param map
	 * @return
	 */
	public List<HashMap<String, String>> getPjxmForXssq(Map<String, String> map){
		// ���п�������Ŀ
		List<HashMap<String, String>> pjxmList = getPjxm(map, new String[]{"xmdm", "xmmc", "xmlx", "lcid", "sqzq"});
		for(Map<String, String> xmMap : pjxmList){
			String lcid = xmMap.get("lcid");
			
			xmMap.put("shlc", formatShlc(getShlcForPjxm(lcid)));
			xmMap.put("mqshzt", "δ����");
			xmMap.put("cz", "ksq");
		
		}
		
		return pjxmList;
	}
	
	/**
	 * �ж��Ƿ�����
	 * @param allXmList
	 * @param sqXmList
	 */
	public void judgeSfsq(List<HashMap<String, String>> allXmList, List<HashMap<String, String>>  sqXmList){
		for(Map<String, String> xmMap : allXmList){
			if("no".equalsIgnoreCase(xmMap.get("sfmztjk"))){	// �Ƿ�����������
				continue;
			}
			String xmdm = xmMap.get("xmdm");
			
			for(Map<String, String> sqxmMap : sqXmList){
				if(xmdm.equalsIgnoreCase(sqxmMap.get("xmdm"))){
					xmMap.put("sfsq", "yes");
					xmMap.put("mqshzt", "������");
					xmMap.put("cz", "kxg");
					break;
				}
			}
		}
	}
	
	/**
	 * �ж��Ƿ����
	 * @param allXmList
	 * @param shXmList
	 */
	public void judgeSfsh(List<HashMap<String, String>> allXmList, List<HashMap<String, String>>  shXmList){
		for(Map<String, String> xmMap : allXmList){
			if("no".equalsIgnoreCase(xmMap.get("sfmztjk"))){	// �Ƿ�����������
				continue;
			}
			String xmdm = xmMap.get("xmdm");
			
			for(Map<String, String> shxmMap : shXmList){
				String shzt = shxmMap.get("shzt");
				String shjb = shxmMap.get("shjb");
				String mc = shxmMap.get("mc");
				if(xmdm.equalsIgnoreCase(shxmMap.get("xmdm"))){
					if(!"δ���".equalsIgnoreCase(shzt)){
						xmMap.put("mqshzt", mc+" ��"+shzt);
						xmMap.put("shjb", shjb);
						xmMap.put("shzt", shzt);
						xmMap.put("yy", "������Ϣ�ѱ��ϼ����ͨ���������ظ����룡");
						xmMap.put("cz", "bkxg");
						break;
					}else if(shjb.equalsIgnoreCase("1")){
						xmMap.put("mqshzt", mc+" ��"+shzt);
						xmMap.put("shjb", shjb);
						xmMap.put("shzt", shzt);
						xmMap.put("cz", "kxg");
						break;
					}
				}
			}
		}
	}
	
	/**
	 * ��ʽ���������,���� "ѧԺ���->ѧУ���" ���Ƹ�ʽ
	 * @param lcList
	 * @return
	 */
	public String formatShlc(List<HashMap<String, String>> list){
		List<String> lcList = new ArrayList<String>();
		for(Map<String, String> map : list){
			lcList.add(map.get("shlc"));
		}
		
		StringBuilder lc = new StringBuilder();
		
		for(int j=0;j<lcList.size();j++){
			if(j==lcList.size()-1){
				lc.append(lcList.get(j));
			}else {
				lc.append(lcList.get(j)).append("->");
			}
		}
		
		return StringUtils.isNotNull(lc.toString()) ? lc.toString() : "�������";
	}
	
	/**
	 * ��ȡĳ��������Ŀ������
	 * @param lcid
	 * @return
	 */
	private List<HashMap<String, String>> getShlcForPjxm(String lcid){
		String lcsql = "select a.id gwid,a.mc shlc from xg_xtwh_spgw a,xg_xtwh_spbz b where a.id = b.spgw and " +
						"splc=? order by b.xh";
		
		List<HashMap<String,String>> list = dao.getList(lcsql, new String[]{lcid}, new String[]{"gwid","shlc"});
		
		return list;
	}
	
	/**
	 * ��ȡѧ��������Ŀ�����Ϣ
	 */
	private List<HashMap<String, String>> getXsshList(String xh){
		String sql = "select * from xg_view_pjpy_xmsh a where xh = ? "
					+"and (pjxn || pjxq || pjnd = ? or pjxn || pjxq || pjnd = ? or pjxn || pjxq || pjnd=?) order by xmdm, to_number(shjb) desc";
		
		String[] input = new String[]{xh, PjxtszModel.pjxtszModel.getPjxn()+"����", PjxtszModel.pjxtszModel.getPjxn()+PjxtszModel.pjxtszModel.getPjxq()+"��", 
				"����"+PjxtszModel.pjxtszModel.getPjnd()};
		return dao.getList(sql, input, new String[]{"xmdm", "shzt", "mc", "shjb"});
	}
	
	/**
	 * ��ȡѧ�������������Ŀ
	 * @param xh
	 * @return
	 */
	private List<HashMap<String, String>> getXsxmList(String xh){
		String sql = "select * from xg_pjpy_pjxmsqb a where xh = ? "
			+"and (pjxn || pjxq || pjnd = ? or pjxn || pjxq || pjnd = ? or pjxn || pjxq || pjnd=?)";

		String[] input = new String[]{xh, PjxtszModel.pjxtszModel.getPjxn()+"����", PjxtszModel.pjxtszModel.getPjxn()+PjxtszModel.pjxtszModel.getPjxq()+"��", 
				"����"+PjxtszModel.pjxtszModel.getPjnd()};
		return dao.getList(sql, input, new String[]{"xmdm"});
	}
	
	/**
	 * �Ƿ���������Ա
	 * @param xh
	 * @return
	 */
	public boolean isPjry(String xh){
		
		String sql = "select count(1) count from view_xg_pjpy_ryqd where xh=?";
		Map<String, String> rs = dao.getMapNotOut(sql, new String[]{xh});
		
		return Integer.parseInt(rs.get("count")) > 0 ?  true : false;
	}
	
	/**
	 * ��ò�ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>>getToptr(String userType){
		String[] cn = null;
		String[] en = null;
		if("stu".equalsIgnoreCase(userType)){
			cn = new String[]{"��Ŀ����", "��Ŀ���", "�������", "Ŀǰ���״̬", "����"};
			en = new String[]{"xmmc", "xmlb", "shlc", "shzt", "cz"};
		}else{
			cn = new String[]{"��Ŀ����", "��Ŀ���", "�������", "����"};
			en = new String[]{"xmmc", "xmlb", "shlc", "cz"};
		}
		return dao.arrayToList(en, cn);
	}
	
	
	
	/**
	 * ����ʱ�俪�ؿ�����Ŀ�Ƿ�������� 
	 * @param allXmList
	 * @param xmdms
	 * @param type
	 */
		public void judgeSqkg(List<HashMap<String, String>> allXmList, List<HashMap<String, String>> xmdmList,String type){
			for(Map<String, String> xmMap : allXmList){
				if("no".equalsIgnoreCase(xmMap.get("sfmztjk"))){	// �Ƿ�����������
					continue;
				}
				String xmdm = xmMap.get("xmdm");
				
				for(HashMap<String,String> map : xmdmList){
					if(xmdm.equalsIgnoreCase(map.get("xmdm"))){
						
						if ("time".equalsIgnoreCase(type)){
							xmMap.put("yy", "��ǰ��Ŀ��������ʱ����!<br/>����ʱ��:"+map.get("sqkssj")+"��"+map.get("sqjssj"));
						} else {
							xmMap.put("yy", "��ǰ��Ŀδ��������!");
						}
						xmMap.put("cz", "bkxg");
						break;
					}
				}
			}
		}
	
}
