
package xgxt.wjcf.csmz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ɳ����ѧԺΥ�ʹ���DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-05</p>
 */
public class WjcfCsmzDAO  {
	DAO dao = DAO.getInstance();
	List<String> values = new ArrayList<String>();//��ѯ����ֵ
	
	/**
	 * ����TYPE���������������ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle(int iType) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = null;
		String[] cn = null;
		switch (iType) {
		case 1: {
			en = new String[] {"xh||cfwh||cfsj","bgcolor", "xh", "xm", "xn", "xymc", "bjmc", "cfwh",
					"cflbmc", "cfyymc", "cxsj", "fdysh" };
			cn = new String[] {"����","bgcolor" ,"ѧ��", "����", "ѧ��", Base.YXPZXY_KEY+"����", "�༶����", "�����ĺ�",
					"�������", "����ԭ��", "����ʱ��", "����Ա���" };
			break;//����Ա
		}
		case 2: {
			en = new String[] {"xh||cfwh||cfsj","bgcolor", "xh", "xm", "xn", "xymc", "bjmc", "cfwh",
					"cflbmc", "cfyymc", "cxsj", "sh" };
			cn = new String[] {"����","bgcolor", "ѧ��", "����", "ѧ��", Base.YXPZXY_KEY+"����", "�༶����", "�����ĺ�",
					"�������", "����ԭ��", "����ʱ��", Base.YXPZXY_KEY+"���" };
			break;//ѧԺ
		}
		case 3: {
			en = new String[] {"xh||cfwh||cfsj", "bgcolor","xh", "xm", "xn", "xymc", "bjmc", "cfwh",
					"cflbmc", "cfyymc", "cxsj", "jd" };
			cn = new String[] {"����","bgcolor", "ѧ��", "����", "ѧ��",Base.YXPZXY_KEY+"����", "�༶����", "�����ĺ�",
					"�������", "����ԭ��", "����ʱ��", "ѧУ���" };
			break;//ѧУ
		}
		default:
			break;
		}
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);
		}//end for
		return topList;
	}
	
	/**
	 * ����TYPE����������������
	 * @param iType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSearchResult(String userName, int iType, WjcfCsmzCxcfModel wjcfcsmzcxcfModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String[] opCol = new String[]{"xh||cfwh||cfsj","bgcolor","xh", "xm", "xn", "xymc", "bjmc", "cfwh",
				"cflbmc", "cfyymc", "cxsqsj", "shjg"};
		
		System.out.println("xxdm:"+xxdm+";itype:"+iType);
		 if (Globals.XXDM_CQGCZY.equalsIgnoreCase(xxdm)) {
				switch (iType) {
				case 1: {
					sql = "select xh||cfwh||cfsj,(case when(fdysh='δ���') then '' else '' end) bgcolor,xh,xm,xn,xymc,bjmc,cfwh,cflbmc,cfyymc,cxsqsj,fdysh shjg from view_wjcf_cxcf a where 1=1";
					wjcfcsmzcxcfModel.setXydm(null);
					break;//����Ա
				}
				case 2: {
					sql = "select xh||cfwh||cfsj,(case when(xysh='δ���') then '' else 'F' end) bgcolor,xh,xm,xn,xymc,bjmc,cfwh,cflbmc,cfyymc,cxsqsj,xysh shjg from view_wjcf_cxcf where 1=1 and fdysh='ͨ��'";
					break;//ѧԺ
				}
				case 3: {
					sql = "select xh||cfwh||cfsj,(case when(xxsh='δ���') then '' else '' end) bgcolor,xh,xm,xn,xymc,bjmc,cfwh,cflbmc,cfyymc,cxsqsj,xxsh shjg from view_wjcf_cxcf where 1=1 and fdysh='ͨ��' and xysh='ͨ��'";
					break;//ѧУ
				}
				default:
					break;
				}
		}else if (!Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {
			switch (iType) {
			case 1: {
				sql = "select xh||cfwh||cfsj,(case when(xysh='δ���') then '' else '' end) bgcolor,xh,xm,xn,xymc,bjmc,cfwh,cflbmc,cfyymc,cxsqsj,xysh shjg from view_wjcf_cxcf where 1=1";
				break;//����Ա
			}
			case 2: {
				sql = "select xh||cfwh||cfsj,(case when(xysh='δ���') then '' else '' end) bgcolor,xh,xm,xn,xymc,bjmc,cfwh,cflbmc,cfyymc,cxsqsj,xysh shjg from view_wjcf_cxcf where 1=1";
				break;//ѧԺ
			}
			case 3: {
				sql = "select xh||cfwh||cfsj,(case when(xxsh='δ���') then '' else '' end) bgcolor,xh,xm,xn,xymc,bjmc,cfwh,cflbmc,cfyymc,cxsqsj,xxsh shjg from view_wjcf_cxcf where 1=1";
				break;//ѧУ
			}
			default:
				break;
			}
		} else {
			switch (iType) {
			case 1: {
				sql = "select xh||cfwh||cfsj,(case when(fdysh='δ���') then '' else '' end) bgcolor,xh,xm,xn,xymc,bjmc,cfwh,cflbmc,cfyymc,cxsqsj,fdysh shjg from view_wjcf_cxcf where 1=1";
				break;//����Ա
			}
			case 2: {
				sql = "select xh||cfwh||cfsj,(case when(xysh='δ���') then '' else '' end) bgcolor,xh,xm,xn,xymc,bjmc,cfwh,cflbmc,cfyymc,cxsqsj,xysh shjg from view_wjcf_cxcf where 1=1 and fdysh='ͨ��'";
				break;//ѧԺ
			}
			case 3: {
				sql = "select xh||cfwh||cfsj,(case when(xxsh='δ���') then '' else '' end) bgcolor,xh,xm,xn,xymc,bjmc,cfwh,cflbmc,cfyymc,cxsqsj,xxsh shjg from view_wjcf_cxcf where 1=1 and fdysh='ͨ��' and xysh='ͨ��'";
				break;//ѧУ
			}
			default:
				break;
			}
		}
		
		StringBuffer whereSql = getWhereSql(wjcfcsmzcxcfModel, iType, userName);
		
		System.out.println(sql + whereSql.toString()+values
				.toArray(new String[0]));
		resList = dao.rsToVator(sql + whereSql.toString() + " order by xxsh", values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return resList;
	}
	
	/**
	 * ��ȡ����б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getList(int iType) throws Exception {
		String[] shList = null;
		switch (iType) {
		case 1: {
			shList = new String[]{"δ���","ͨ��","��ͨ��"};
			break;//����Ա
		}
		case 2: {
			shList = new String[]{"δ����","����","������"};
			break;//ѧԺ
		}
		case 3: {
			shList = new String[]{"δ����","�������","���Ĵ���","ά��ԭ����"};
			break;//ѧУ
		}
		case 4: {
			shList = new String[]{"δ���","ͨ��","��ͨ��", "�������"};
			break;//����Ա
		}
		default:
			break;
		}
		return dao.arrayToList(shList, shList);
	}
	
	/**
	 * ��ȡ��ͬ�ĳ������ֲ�ѯ���
	 * @param iType
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCxcfQryResult(int iType, String pkVal) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql ="";
		String[] opCol = null;
		String xxdm = StandardOperation.getXxdm();
		 if (Globals.XXDM_CQGCZY.equalsIgnoreCase(xxdm)) {
			 switch (iType) {
			case 1: {
				sql ="select xh,xm,xb,xq,xymc,zymc,bjmc,cfwh,xn,cfsj,cflbmc,cfyymc,cxsqsj cxsj,fdysh sh,fdyyj shyj,bz,cxjg,jcsj from view_wjcf_cxcf  where xh||cfwh||cfsj = ?";
				opCol = new String[]{"xh","xm","xb","xq","xymc","zymc","bjmc","cfwh","xn","cfsj","cflbmc","cfyymc","cxsj","sh","shyj","cxjg","jcsj","bz"};
				break;//����Ա
			}
			case 2: {
				sql ="select xh,xm,xb,xq,xymc,zymc,bjmc,cfwh,xn,cfsj,cflbmc,cfyymc,cxsqsj cxsj,xysh sh,xyyj shyj,bz,cxjg,jcsj from view_wjcf_cxcf  where xh||cfwh||cfsj = ?";
				opCol = new String[]{"xh","xm","xb","xq","xymc","zymc","bjmc","cfwh","xn","cfsj","cflbmc","cfyymc","cxsj","sh","shyj", "bz","cxjg","jcsj"};
				break;//ѧԺ
			}
			case 3: {
				sql ="select xh,xm,xb,xq,xymc,zymc,bjmc,cfwh,xn,cfsj,cflbmc,cfyymc,cxsqsj cxsj,xxsh sh,xxyj shyj,bz,jcsj,jcwh,fwbm,cxjg from view_wjcf_cxcf  where xh||cfwh||cfsj = ?";
				opCol = new String[]{"xh","xm","xb","xq","xymc","zymc","bjmc","cfwh","xn","cfsj","cflbmc","cfyymc","cxsj","sh","shyj", "bz", "jcsj", "jcwh", "fwbm","cxjg"};
				break;//ѧУ
			}
			default:
				break;
			}//end case
		}else if (!Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {
			switch (iType) {
			case 1: {
					sql ="select xh,xm,xb,xq,xymc,zymc,bjmc,cfwh,xn,cfsj,cflbmc,cfyymc,cxsqsj cqsj,xysh sh,xyyj shyj,bz,cxjg,jcsj from view_wjcf_cxcf  where xh||cfwh||cfsj = ?";
				opCol = new String[]{"xh","xm","xb","xq","xymc","zymc","bjmc","cfwh","xn","cfsj","cflbmc","cfyymc","cxsj","sh","shyj","cxjg","jcsj"};
				break;//����Ա
			}
			case 2: {
				sql ="select xh,xm,xb,xq,xymc,zymc,bjmc,cfwh,xn,cfsj,cflbmc,cfyymc,cxsqsj cxsj,xysh sh,xyyj shyj,bz,cxjg,jcsj from view_wjcf_cxcf  where xh||cfwh||cfsj = ?";
				opCol = new String[]{"xh","xm","xb","xq","xymc","zymc","bjmc","cfwh","xn","cfsj","cflbmc","cfyymc","cxsj","sh","shyj", "bz","cxjg","jcsj"};
				break;//ѧԺ
			}
			case 3: {
				sql ="select xh,xm,xb,xq,xymc,zymc,bjmc,cfwh,xn,cfsj,cflbmc,cfyymc,cxsqsj cxsj,xxsh sh,xxyj shyj,bz,jcsj,jcwh,fwbm,cxjg from view_wjcf_cxcf  where xh||cfwh||cfsj = ?";
				opCol = new String[]{"xh","xm","xb","xq","xymc","zymc","bjmc","cfwh","xn","cfsj","cflbmc","cfyymc","cxsj","sh","shyj", "bz", "jcsj", "jcwh", "fwbm","cxjg"};
				break;//ѧУ
			}
			default:
				break;
			}//end case
		} else {
			switch (iType) {
			case 1: {
				sql ="select xh,xm,xb,xq,xymc,zymc,bjmc,cfwh,xn,cfsj,cflbmc,cfyymc,cxsj,fdysh sh,fdyyj shyj from view_wjcf_cxcf  where xh||cfwh||cfsj = ?";
				opCol = new String[]{"xh","xm","xb","xq","xymc","zymc","bjmc","cfwh","xn","cfsj","cflbmc","cfyymc","cxsj","sh","shyj"};
				break;//����Ա
			}
			case 2: {
				sql ="select xh,xm,xb,xq,xymc,zymc,bjmc,cfwh,xn,cfsj,cflbmc,cfyymc,cxsj,xysh sh,xyyj shyj from view_wjcf_cxcf  where xh||cfwh||cfsj = ?";
				opCol = new String[]{"xh","xm","xb","xq","xymc","zymc","bjmc","cfwh","xn","cfsj","cflbmc","cfyymc","cxsj","sh","shyj"};
				break;//ѧԺ
			}
			case 3: {
				sql ="select xh,xm,xb,xq,xymc,zymc,bjmc,cfwh,xn,cfsj,cflbmc,cfyymc,cxsj,xxsh sh,xxyj shyj,jcsj,jcwh,fwbm from view_wjcf_cxcf  where xh||cfwh||cfsj = ?";
				opCol = new String[]{"xh","xm","xb","xq","xymc","zymc","bjmc","cfwh","xn","cfsj","cflbmc","cfyymc","cxsj","sh","shyj", "jcsj", "jcwh", "fwbm"};
				break;//ѧУ
			}
			default:
				break;
			}//end case
		}
		
		String[] temp = dao.getOneRs(sql, new String[]{pkVal}, opCol);
		for (int i = 0; i < opCol.length; i++) {
			resMap.put(opCol[i], temp[i]);
		}//end if
		return resMap;
	}
	
	/**
	 * �������������Ϣ����
	 * @param userType
	 * @param isFdy
	 * @param pkVal
	 * @param wjcfsmzcxcfsaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfInfo(int iType,
			WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel,
			HttpServletRequest request, String pkValue) throws Exception {
		boolean bFlag = false;
		String xxdm = StandardOperation.getXxdm();
		String sh = DealString.toGBK(wjcfsmzcxcfsaveModel.getSh());
		String shyj = DealString.toGBK(wjcfsmzcxcfsaveModel.getShyj());
		
		String[] inCol = null;
		if (Globals.XXDM_CQGCZY.equalsIgnoreCase(xxdm)) {
			switch (iType) {
			case 1: {
				inCol = new String[] { "fdysh", "fdyyj" };
				bFlag = StandardOperation.update("wjcf_cxcfb", inCol,
						new String[] {sh,shyj}, "xh||cfwh||cfsj", pkValue, request);
				break;// ѧԺ
			}
			case 2: {
				inCol = new String[] { "xysh", "xyyj" };
				bFlag = StandardOperation.update("wjcf_cxcfb", inCol,
						new String[] {sh,shyj}, "xh||cfwh||cfsj", pkValue, request);
				break;// ѧԺ
			}
			case 3: {
				inCol = new String[] { "xxsh", "xxyj" ,"jcsj", "jcwh", "fwbm", "cxjg"};
				bFlag = StandardOperation.update("wjcf_cxcfb", inCol,
						new String[] {sh,shyj, wjcfsmzcxcfsaveModel.getJcsj(), DealString.toGBK(wjcfsmzcxcfsaveModel.getJcwh()), DealString.toGBK(wjcfsmzcxcfsaveModel.getFwbm()),DealString.toGBK(wjcfsmzcxcfsaveModel.getCxjg())}, "xh||cfwh||cfsj", pkValue, request);
				if (!Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
					if (bFlag) {//�޸ĳɹ������WJCFB��
						if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
							if (StringUtils.isEqual(sh, "ͨ��")) {
								StandardOperation.update("wjcfb", new String[] { "cxjg" },
										new String[] { sh }, "xh||cfwh||cfsj", pkValue,
										request);
							}
						} else {
							if (StringUtils.isEqual(sh, "ͨ��")) {
								StandardOperation.update("wjcfb", new String[] { "cxjg" ,"cxclsj","cxclwh"},
										new String[] { DealString.toGBK(wjcfsmzcxcfsaveModel.getCxjg()),wjcfsmzcxcfsaveModel.getJcsj(),DealString.toGBK(wjcfsmzcxcfsaveModel.getJcwh()) }, "xh||cfwh||cfsj", pkValue,
										request);
							}
						}
						
					}
				}
				break;// ѧУ
			}
			default:
				break;
			}//end case
		}else if (!Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {
			switch (iType) {
			case 1: {
				inCol = new String[] { "xysh", "xyyj" };
				bFlag = StandardOperation.update("wjcf_cxcfb", inCol,
						new String[] {sh,shyj}, "xh||cfwh||cfsj", pkValue, request);
				break;// ѧԺ
			}
			case 2: {
				inCol = new String[] { "xysh", "xyyj" };
				bFlag = StandardOperation.update("wjcf_cxcfb", inCol,
						new String[] {sh,shyj}, "xh||cfwh||cfsj", pkValue, request);
				break;// ѧԺ
			}
			case 3: {
				inCol = new String[] { "xxsh", "xxyj" ,"jcsj", "jcwh", "fwbm", "cxjg"};
				bFlag = StandardOperation.update("wjcf_cxcfb", inCol,
						new String[] {sh,shyj, wjcfsmzcxcfsaveModel.getJcsj(), DealString.toGBK(wjcfsmzcxcfsaveModel.getJcwh()), DealString.toGBK(wjcfsmzcxcfsaveModel.getFwbm()),DealString.toGBK(wjcfsmzcxcfsaveModel.getCxjg())}, "xh||cfwh||cfsj", pkValue, request);
				if (!Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
					if (bFlag) {//�޸ĳɹ������WJCFB��
						if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
							if (StringUtils.isEqual(sh, "ͨ��")) {
								StandardOperation.update("wjcfb", new String[] { "cxjg" },
										new String[] { sh }, "xh||cfwh||cfsj", pkValue,
										request);
							}
						} else {
							if (StringUtils.isEqual(sh, "ͨ��")) {
								StandardOperation.update("wjcfb", new String[] { "cxjg" ,"cxclsj","cxclwh"},
										new String[] { DealString.toGBK(wjcfsmzcxcfsaveModel.getCxjg()),wjcfsmzcxcfsaveModel.getJcsj(),DealString.toGBK(wjcfsmzcxcfsaveModel.getJcwh()) }, "xh||cfwh||cfsj", pkValue,
										request);
							}
							//����ɹ��޸Ĵ��ֱ��е�ѧУ���
							if (StringUtils.isEqual(sh, "�������")) {
								StandardOperation.update("wjcfb", new String[] { "xxsh","cxjg" ,"cxclsj","cxclwh"},
										new String[] {"�������(���ֽ��)", DealString.toGBK(wjcfsmzcxcfsaveModel.getCxjg()),wjcfsmzcxcfsaveModel.getJcsj(),DealString.toGBK(wjcfsmzcxcfsaveModel.getJcwh()) }, "xh||cfwh||cfsj", pkValue,
										request);
							}
						}
						
					}
				}
				break;// ѧУ
			}
			default:
				break;
			}//end case
		} else {
			switch (iType) {
			case 1: {
				inCol = new String[] { "fdysh", "fdyyj" };
				bFlag = StandardOperation.update("wjcf_cxcfb", inCol,
						new String[] {sh,shyj}, "xh||cfwh||cfsj", pkValue, request);
				break;// ����Ա
			}
			case 2: {
				inCol = new String[] { "xysh", "xyyj" };
				bFlag = StandardOperation.update("wjcf_cxcfb", inCol,
						new String[] {sh,shyj}, "xh||cfwh||cfsj", pkValue, request);
				break;// ѧԺ
			}
			case 3: {
				inCol = new String[] { "xxsh", "xxyj" ,"jcsj", "jcwh", "fwbm"};
				bFlag = StandardOperation.update("wjcf_cxcfb", inCol,
						new String[] {sh,shyj, wjcfsmzcxcfsaveModel.getJcsj(), DealString.toGBK(wjcfsmzcxcfsaveModel.getJcwh()), DealString.toGBK(wjcfsmzcxcfsaveModel.getFwbm())}, "xh||cfwh||cfsj", pkValue, request);
				if (!Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
					if (bFlag) {//�޸ĳɹ������WJCFB��
						if (StringUtils.isEqual(sh, "ͨ��")) {
							StandardOperation.update("wjcfb", new String[] { "cxjg" },
									new String[] { sh }, "xh||cfwh||cfsj", pkValue,
									request);
						}
					}
				}
				break;// ѧУ
			}
			default:
				break;
			}//end case
		}
		
		return bFlag;
	}
	
	/**
	 * �������泷�����������Ϣ
	 * @param iType
	 * @param wjcfsmzcxcfsaveModel
	 * @param request
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfInfoPl(int iType,
			WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String sh = DealString.toGBK(wjcfsmzcxcfsaveModel.getSh());
		String shyj = DealString.toGBK(wjcfsmzcxcfsaveModel.getShyj());
		
		StringBuilder sql = new StringBuilder("");
		String[] pkArr = wjcfsmzcxcfsaveModel.getPkString().split("!@");
		for(String pk : pkArr){
			if (!Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {
				switch (iType) {
				case 1: {
					sql.append("update wjcf_cxcfb set fdysh='").append(sh).append("',fdyyj='").append(shyj).append("' where xh||cfwh||cfsj='").append(pk).append("'").append("!@");
					break;
				}
				case 2: {
					sql.append("update wjcf_cxcfb set xysh='").append(sh).append("',xyyj='").append(shyj).append("' where xh||cfwh||cfsj='").append(pk).append("'").append("!@");
					break;
				}
				case 3: {
					sql.append("update wjcf_cxcfb set xxsh='")
						.append(sh).append("',xxyj='").append(shyj)
						.append("',jcsj='")
						.append(wjcfsmzcxcfsaveModel.getJcsj())
						.append("',jcwh='")
						.append(wjcfsmzcxcfsaveModel.getJcwh())
						.append("',fwbm='")
						.append(wjcfsmzcxcfsaveModel.getFwbm())
						.append("',cxjg='")
						.append(wjcfsmzcxcfsaveModel.getCxjg())
						.append("' where xh||cfwh||cfsj='")
						.append(pk).append("'").append("!@");
					if (!Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
						if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
							if (StringUtils.isEqual(sh, "ͨ��")) {
								sql.append("update wjcfb set cxjg='").append(sh).append("' where xh||cfwh||cfsj='").append(pk).append("'").append("!@");
							}
						} else {
							if (StringUtils.isEqual(sh, "ͨ��")) {
								sql.append("update wjcfb set cxjg='").append(wjcfsmzcxcfsaveModel.getCxjg()).append("',cxclsj='").append(wjcfsmzcxcfsaveModel.getJcsj()).append("',cxclwh='").append(wjcfsmzcxcfsaveModel.getJcwh()).append("' where xh||cfwh||cfsj='").append(pk).append("'").append("!@");
							}
							//�Ѵ��ֱ��е�ѧУ��˱��Ϊ�������
							if (StringUtils.isEqual(sh, "�������")) {
								sql.append("update wjcfb set xxsh='�������(���ֽ��)'").append(" where xh||cfwh||cfsj='").append(pk).append("'").append("!@");
							}
						}
					} else {
						
					}
					break;// ѧУ
				}
				default:
					break;
				}//end case
			} else {
				switch (iType) {
				case 1: {
					sql.append("update wjcf_cxcfb set fdysh='").append(sh).append("',fdyyj='").append(shyj).append("' where xh||cfwh||cfsj='").append(pk).append("'").append("!@");
					break;// ����Ա
				}
				case 2: {
					sql.append("update wjcf_cxcfb set xysh='").append(sh).append("',xyyj='").append(shyj).append("' where xh||cfwh||cfsj='").append(pk).append("'").append("!@");
					break;// ѧԺ
				}
				case 3: {
					sql.append("update wjcf_cxcfb set xxsh='")
						.append(sh).append("',xxyj='").append(shyj)
						.append("',jcsj='")
						.append(wjcfsmzcxcfsaveModel.getJcsj())
						.append("',jcwh='")
						.append(wjcfsmzcxcfsaveModel.getJcwh())
						.append("',fwbm='")
						.append(wjcfsmzcxcfsaveModel.getFwbm())
						.append("' where xh||cfwh||cfsj='")
						.append(pk).append("'").append("!@");
					if (!Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
						if (StringUtils.isEqual(sh, "ͨ��")) {
							sql.append("update wjcfb set cxjg='").append(sh).append("' where xh||cfwh||cfsj='").append(pk).append("'").append("!@");
						}
					}
					break;// ѧУ
				}
				default:
					break;
				}//end case
			}
		}
		return dao.checkBatch(dao.runBatch(sql.toString().split("!@")));
	}
	
	/**
	 * ���÷�������ȡ��ѯ����
	 * @param wjcfcsmzcxcfModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(WjcfCsmzCxcfModel wjcfcsmzcxcfModel, int iType, String userName) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = wjcfcsmzcxcfModel.getXn();
		String xh = DealString.toGBK(wjcfcsmzcxcfModel.getXh());
		String xm = DealString.toGBK(wjcfcsmzcxcfModel.getXm());
		String xydm = wjcfcsmzcxcfModel.getXydm();
		String zydm = wjcfcsmzcxcfModel.getZydm();
		String bjdm = wjcfcsmzcxcfModel.getBjdm();
		String nj = wjcfcsmzcxcfModel.getNj();
		String xq = wjcfcsmzcxcfModel.getXq();
		String xxsh = wjcfcsmzcxcfModel.getXxsh();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}//end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm = ?");
			values.add(xm);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}//end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}//end if
		if (!StringUtils.isNull(xxsh)) {
			whereSql.append(" and xxsh = ?");
			values.add(xxsh);
		}
		
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			User user=wjcfcsmzcxcfModel.getUser();
			String isFdy=user.getFdyQx();
			String isBzr=user.getBzrQx();
			if (iType == 1) {
				if("true".equalsIgnoreCase(isFdy) && "true".equalsIgnoreCase(isBzr)){
					
					whereSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"' ");
					whereSql.append(" union select 1 from bzrbbb c where a.bjdm=c.bjdm and c.zgh='"+userName+"') ");
				}else  if("true".equalsIgnoreCase(isBzr)){
					
					whereSql.append(" and exists( select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
				}else  if("true".equalsIgnoreCase(isFdy)){
					
					whereSql.append(" and exists( select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
				}
			}
		}else{
			if ((iType == 1) && StringUtils.isNull(bjdm)) {
				ArrayList<String> userBj = dao.getUserBj(userName);
				if (userBj.size() != 0) {
					whereSql.append(" and bjdm in ('###'");
					for (Iterator<String> it = userBj.iterator(); it.hasNext();) {
						whereSql.append(", '");
						whereSql.append(it.next());
						whereSql.append("'");
					}
					whereSql.append(" ) ");
				}
			}
		}
		return whereSql;
	}
	
	/**
	 * ͨ��ѧ�Ż�ȡѧ���������Ϣ���������༶��רҵ��ѧԺ���꼶���Ա�
	 * ͨ��Υ���ĺŻ�ȡѧ��Υ����Ϣ��ʱ�䣬������𣬴���ԭ��ѧ�꣬ѧ�ڣ�
	 * @param xh,pkval
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh, String pkVal) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "";
		String[] opCol = null;
		if (!StringUtils.isNull(xh)) {
			sql = "select xh,xb,xm,bjdm,bjmc,xydm,xymc,zydm,zymc,nj from view_xsjbxx where xh = ?";
			opCol = new String[]{"xh","xb","xm","bjmc","zymc","xymc","nj"};
			String[] stuInfo = dao.getOneRs(sql, new String[]{xh.trim()}, opCol);
			if (stuInfo != null && stuInfo.length > 0) {
				for (int i = 0; i < opCol.length; i++) {
					resMap.put(opCol[i], stuInfo[i]);
				}//end for
			}//end if
		}//end if
		if (!StringUtils.isNull(pkVal)) {
			sql ="select cflb,cfyy,cflbmc,cfyymc,cfsj,cfwh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq from view_wjcf a where xh||trim(cfwh)||cfsj = ?";
			opCol = new String[]{"cflb","cfyy","cflbmc","cfyymc","cfsj","cfwh","xn","xq"};
			String[] wjcfInfo = dao.getOneRs(sql, new String[]{pkVal.trim()}, opCol);
			if (wjcfInfo != null && wjcfInfo.length > 0) {
				for (int i = 0; i < opCol.length; i++) {
					resMap.put(opCol[i], wjcfInfo[i]);
				}//end for
			}//end if
		}//end if
		return resMap;
	}
	
	/**
	 * ���泷��������Ϣ
	 * @param cxcfSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveCxcfSqlInfo(CxcfSqSaveModel cxcfSaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = cxcfSaveModel.getXh();
		String xn = cxcfSaveModel.getXn();
		String xq = cxcfSaveModel.getXq();
		String cfwh = DealString.toGBK(cxcfSaveModel.getCfwh());
		String cfsj = cxcfSaveModel.getCfsj();
		String cflbmc = DealString.toGBK(cxcfSaveModel.getCflbmc());
		String cfyymc = DealString.toGBK(cxcfSaveModel.getCfyymc());
		String bz = DealString.toGBK(cxcfSaveModel.getBz());
		String cxsj = cxcfSaveModel.getCxsj();
		bFlag = StandardOperation
				.insert("wjcf_cxcfb", new String[] { "xh", "xn", "xq", "cfwh",
						"cfsj", "cflbmc", "cfyymc", "bz", "cxsqsj" },
						new String[] { xh, xn, xq, cfwh, cfsj, cflbmc, cfyymc,
								bz, cxsj }, request);
		return bFlag;
	}
	
	/**
	 * ����ǰ���ѧ������ʱ���Ƿ���һ��
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean chkStuTj(String cfsj) throws Exception {
		boolean bFlag = false;
		String sql = "select round(months_between(to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),to_date('" + cfsj + "','yyyy-mm-dd'))) hg from dual";
		String[] flag = dao.getOneRs(sql, new String[]{}, new String[]{"hg"});
		if ((flag.length > 0 && (!StringUtils.isNull(flag[0])))) {
			int iTj = Integer.parseInt(flag[0]);//Υ�ʹ���ʱ�䳤��
			if (iTj >= 12) {
				bFlag = true;
			}
		}
		return bFlag;
	}
	
	/**
	 * �������ֲ�ѯ��ͷ
	 * cxcfqrytit -----�������ֲ�ѯ��ͷ
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxcfQryTit(WjcfCsmzCxcfModel cxcfModel, String xxdm) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = null;
		String[] cn = null;
		if (Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {
			en = new String[]{"xh||cfwh||cfsj","dis", "xh","xm","xn","xq","bjmc","cflbmc","cfwh","cfsj","cxsj","fdysh","xysh","xxsh"};
			cn = new String[]{"����","dis", "ѧ��","����","ѧ��","ѧ��","�༶����","�������","�����ĺ�","����ʱ��","��������ʱ��","����Ա���",Base.YXPZXY_KEY+"���","ѧУ���"};
		}else if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
			en = new String[]{"xh||cfwh||cfsj","dis", "xh","xm","xn","xq","bjmc","cflbmc","cfwh","cfsj","cxsj","cxjg"};
			cn = new String[]{"����","dis", "ѧ��","����","ѧ��","ѧ��","�༶����","�������","�����ĺ�","����ʱ��","����ʱ��","�������"};
		} else {
			en = new String[]{"xh||cfwh||cfsj","dis", "xh","xm","xn","xq","bjmc","cflbmc","cfwh","cfsj","cxsj","xxsh"};
			cn = new String[]{"����","dis", "ѧ��","����","ѧ��","ѧ��","�༶����","�������","�����ĺ�","����ʱ��","����ʱ��","ѧУ���"};
		}
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);
		}//end for
		return topList;
	}
	
	/**
	 * �������ֲ�ѯ��ͷ
	 * cxcfqryres -----�������ֲ�ѯ�����
	 * @param cxcfModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxcfQryRes(WjcfCsmzCxcfModel cxcfModel, String xxdm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] opCol = null;
		String sql = "";
		if (Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {
			opCol = new String[]{"xh||cfwh||cfsj","dis", "xh","xm","xn","xq","bjmc","cflbmc","cfwh","cfsj","cxsqsj","fdysh","xysh","xxsh"};
			sql = "select xh||cfwh||cfsj,'' dis,xh,xm,xn,xq,bjmc,cflbmc,cfwh,cfsj,cxsqsj,fdysh,xysh,xxsh,jcsj,jcwh,cxjg from view_wjcf_cxcf where 1=1";
		} else if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
			opCol = new String[]{"xh||cfwh||cfsj||cxsqsj","dis", "xh","xm","xn","xq","bjmc","cflbmc","cfwh","cfsj","cxsqsj","cxjg"};
			sql = "select xh||cfwh||cfsj||cxsqsj,'' dis,xh,xm,xn,xq,bjmc,cflbmc,cfwh,cfsj,cxsqsj,fdysh,xysh,xxsh,jcsj,jcwh,cxjg from view_wjcf_cxcf where 1=1";
		} else {
			opCol = new String[]{"xh||cfwh||cfsj","dis", "xh","xm","xn","xq","bjmc","cflbmc","cfwh","cfsj","cxsqsj", "xxsh"};
			
			if ("xy".equalsIgnoreCase(cxcfModel.getUserType()) || "fdy".equalsIgnoreCase(cxcfModel.getUserType())) {
				sql = "select xh||cfwh||cfsj,(case when xxsh='ͨ��' then 'disabled' end) dis,xh,xm,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,bjmc,cflbmc,cfwh,cfsj,cxsqsj,fdysh,xysh,xxsh,jcsj,jcwh,cxjg from view_wjcf_cxcf a where 1=1";
			} else {
				sql = "select xh||cfwh||cfsj,(case when xxsh='ͨ��' then 'disabled' end) dis,xh,xm,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,bjmc,cflbmc,cfwh,cfsj,cxsqsj,fdysh,xysh,xxsh,jcsj,jcwh,cxjg from view_wjcf_cxcf a where 1=1";
			}
			
		}
		
		StringBuilder where=new StringBuilder();
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			User user=cxcfModel.getUser();
			String isFdy=user.getFdyQx();
			String isBzr=user.getBzrQx();
			String userName=user.getUserName();
			if("true".equalsIgnoreCase(isFdy) && "true".equalsIgnoreCase(isBzr)){
				
				where.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"' ");
				where.append(" union select 1 from bzrbbb c where a.bjdm=c.bjdm and c.zgh='"+userName+"') ");
			}else  if("true".equalsIgnoreCase(isBzr)){
				
				where.append(" and exists( select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
			}else  if("true".equalsIgnoreCase(isFdy)){
				
				where.append(" and exists( select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
			}
			
		}
		
		StringBuffer whereSql = getWhereSql(cxcfModel,0,"");
		resList = dao.rsToVator(sql + whereSql.toString() + where+" order by xxsh", values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return resList;
	}
	
	/**
	 * ����ɾ������������Ϣ
	 * cxcfinfo----����������Ϣ
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String delCxcfInfo(String[] keys) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from wjcf_cxcfb where xh||cfwh||cfsj = '" + whichxh + "'";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				sql = "delete from wjcf_cxcfb where xh||cfwh||cfsj||cxsqsj = '" + whichxh + "'";
			}
			// ��������ϳ�sql���
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql����ֳ�����
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * �������ֲ�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> stuCxcfTitle(String xxdm) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = new String[]{"xh||cfwh||cfsj","xh","xm","xb","bjmc","cflbmc","cfyymc","cfsj","cfwh","cxsj","fdysh","xysh","xxsh"};
		String[] cn = new String[]{"����","ѧ��","����","�Ա�","�༶����","�������","����ԭ��","����ʱ��","�����ĺ�","��������ʱ��","����Ա���",Base.YXPZXY_KEY+"���","ѧУ���"};
		if (!Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {
			en = new String[]{"xh||cfwh||cfsj","xn","xq","cflbmc","cfyymc","jcwh","jcsj","xxsh"};
			cn = new String[]{"����","ѧ��","ѧ��","�������","����ԭ��","�����ĺ�","����ʱ��","ѧУ���"};
			if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				en = new String[]{"xh||cfwh||cfsj","cflbmc","cfyymc","cfsj","cfwh","cxsj","jcsj","cxjg"};
				cn = new String[]{"����","�������","����ԭ��","����ʱ��","�����ĺ�","����ʱ��","����ʱ��","����������"};
			}
		}
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);
		}//end for
		return topList;
	}
	
	/**
	 * �������ֲ�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stuCxcfResult(String xh, String xxdm) throws Exception {
		String sql = "select xh||cfwh||cfsj,xh,xm,xb,bjmc,cflbmc,cfyymc,cfsj,cfwh,cxsj,fdysh,xysh,xxsh from view_wjcf_cxcf where xh=?";
		List<String[]> stuList = new ArrayList<String[]>();
		if (!Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select xh||cfwh||cfsj,xn,xq,xh,xm,xb,bjmc,cflbmc,cfyymc,cfsj,cfwh,cxsqsj,fdysh,xysh,xxsh,cxjg,jcsj,jcwh from view_wjcf_cxcf where xh=?";
			stuList = dao.rsToVator(sql, new String[]{xh}, new String[]{"xh||cfwh||cfsj","xn","xq","cflbmc","cfyymc","jcwh","jcsj","xxsh"});
			if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				stuList = dao.rsToVator(sql, new String[]{xh}, new String[]{"xh||cfwh||cfsj","cflbmc","cfyymc","cfsj","cfwh","cxsqsj","jcsj","cxjg"});
			}
		} else {
			stuList = dao.rsToVator(sql, new String[]{xh}, new String[]{"xh||cfwh||cfsj","xh","xm","xb","bjmc","cflbmc","cfyymc","cfsj","cfwh","cxsj","fdysh","xysh", "xxsh"});
		}
		
		return stuList;
	}
	
	/**
	 * ͨ��������ȡ����������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> stuInfoByPk(String pkValue) throws Exception{
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xh||a.cfwh||a.cfsj,a.xh,a.nj,a.xymc,a.zymc,a.xm,a.xn,a.xq,a.bjmc,a.cflbmc,a.cfyymc,a.cfwh,a.cfsj,a.cxsqsj cxsj,a.bz,a.fdysh,a.xysh,a.xxsh,b.nd,b.xn,b.xb,a.jcsj,a.jcwh,a.cxjg from view_wjcf_cxcf a left join view_wjcf b on a.xh=b.xh and a.cfwh=b.cfwh and a.cfsj=b.cfsj where a.xh||a.cfwh||a.cfsj=?";
		if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
			sql = "select a.xh||a.cfwh||a.cfsj||a.cxsqsj,a.xh,a.nj,a.xymc,a.zymc,a.xm,a.xn,a.xq,a.bjmc,a.cflbmc,a.cfyymc,a.cfwh,a.cfsj,a.cxsqsj cxsj,a.bz,a.fdysh,a.xysh,a.xxsh,b.nd,b.xn,b.xb,a.jcsj,a.jcwh,a.cxjg from view_wjcf_cxcf a left join view_wjcf b on a.xh=b.xh and a.cfwh=b.cfwh and a.cfsj=b.cfsj where a.xh||a.cfwh||a.cfsj||a.cxsqsj=?";
		}
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	public boolean update(String pkValue, String bz, HttpServletRequest request) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
			return StandardOperation.update("wjcf_cxcfb", new String[]{"bz"}, new String[]{bz}, "xh||cfwh||cfsj||cxsqsj", pkValue, request);
		}
		return StandardOperation.update("wjcf_cxcfb", new String[]{"bz"}, new String[]{bz}, "xh||cfwh||cfsj", pkValue, request);
	}
	
	public HashMap<String, String> getcfInfo(String pkValue) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,cfsj,cfwh,cflbmc,cfyymc,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq from view_wjcf a where xh||xn||nd||sbsj=?", new String[]{pkValue});
	}
	
	/**
	 * ��ӳ���������Ϣ   ���칤��ְҵ����ѧԺ
	 * @return
	 */
	public boolean updateCxcfInfo(WjcfCsmzCxcfSaveModel model,String sfcx) throws Exception{
		String sql=" update WJCFB set sfcx=?,cxrq=? where xh=? and cfwh=? and cfsj=? ";
		String[] input=new String[]{sfcx,model.getJcsj(),model.getXh(),model.getCfwh(),model.getCfsj()};
		return dao.runUpdate(sql, input);
	}
}
