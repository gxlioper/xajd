package xgxt.pjpy.mjxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.tginterface.PjpyCommonInterface;

public class PjpyMjxyDao {
	
	/**
	 * 不及格课数量
	 * @param xh
	 * @return
	 */
	public String checkBjgkms(String xh,String jxjlb){
		String blog="false";
		StringBuilder strBuilder=new StringBuilder();
		DAO dao=DAO.getInstance();
		String bjgSql="select xn,tjzd,tjlx,tjz from zjcm_pjpy_tjsz where tjzd='bjgkms'  and jxjdm=? ";
		List<HashMap<String,String>>bjglist=dao.getList(bjgSql, new String[]{jxjlb},new String[]{"xn","tjzd","tjlx","tjz"} );
		if(bjglist.size()>0){
			HashMap<String,String>bjgMap=bjglist.get(0);
			String tjzd=bjgMap.get("tjzd");
			String tjlx=bjgMap.get("tjlx");
			String tjz=bjgMap.get("tjz");
			String xn=bjgMap.get("xn");
			String jls="";
			String bjgkms="";
			//判断不及格课有多少门
			
			jls="select count(xh)jls,xh from cjb where  xh=? and xn=?  group by xh ";
			bjgkms="select count(xh)bjgkms,xh from cjb where cj<60 and xh=? and xn=?  group by xh";
			strBuilder.append("select xh,bjgkms from(select count(xh)bjgkms,xh from cjb where cj<60 and xh=? and xn=?  group by xh) where ");
			strBuilder.append(tjzd);
			strBuilder.append(tjlx);
			strBuilder.append(tjz);
			List<HashMap<String,String>>list=new ArrayList<HashMap<String,String>>();
			List<HashMap<String,String>>jlsList=new ArrayList<HashMap<String,String>>();
			List<HashMap<String,String>>bjgkmList=new ArrayList<HashMap<String,String>>();
			jlsList=dao.getList(jls, new String[]{xh,xn},new String[]{"jls"} );
			bjgkmList=dao.getList(bjgkms,new String[]{xh,xn}, new String[]{"bjgkms"});
			list=dao.getList(strBuilder.toString(), new String[]{xh,xn},new String[]{"bjgkms"} );
			//该学生在当前学年的考试记录数
			if(jlsList.size()>0){
				//有不及格科目
				if(bjgkmList.size()>0){
					if(list.size()<=0){
						blog="false";
					}
				}else{
					blog="true";
				}
			}else{
				blog="nocj";
			}
		}else{
			blog="true";
			
		}
		
		return blog;
	}
	
	/**
	 * 处分次数
	 */
	public String checkCfcs(String xh,String jxjlb){
		String blog="false";
		StringBuilder strBuilder=new StringBuilder();
		DAO dao=DAO.getInstance();
		String cfSql="select xn,tjzd,tjlx,tjz from zjcm_pjpy_tjsz where tjzd='cfcs' and jxjdm= ? ";
		List<HashMap<String,String>>cflist=dao.getList(cfSql, new String[]{jxjlb},new String[]{"xn","tjzd","tjlx","tjz"} );
		if(cflist.size()>0){
			HashMap<String,String>cfMap=cflist.get(0);
			String tjzd=cfMap.get("tjzd");
			String tjlx=cfMap.get("tjlx");
			String tjz=cfMap.get("tjz");
			String xn=cfMap.get("xn");
			//判断不及格课有多少门
			String jls="select xh,jls from(select xh,count(xh)jls from wjcfb where xh=? and xn=? and xxsh='通过' group by xh)";
			strBuilder.append("select xh,cfcs from(select xh,count(xh)cfcs from wjcfb where xh=? and xn=? and xxsh='通过' group by xh) where ");
			strBuilder.append(tjzd);
			strBuilder.append(tjlx);
			strBuilder.append(tjz);
			List<HashMap<String,String>>list=dao.getList(strBuilder.toString(), new String[]{xh,xn},new String[]{"cfcs"} );
			
			List<HashMap<String,String>>jlslist=dao.getList(jls, new String[]{xh,xn},new String[]{"jls"} );
			//满足条件的数据
			if(jlslist.size()>0){
				if(list.size()>0){
					blog="true";
				}
			}else{
				blog="true";
			}
		}else{
			blog="true";
		}
		return blog;
	}
	
	/**
	 * 综测分数与排名
	 * 
	 */
	public List<HashMap<String,String>> getZcCjPm(String xh,String xn){
		DAO dao=DAO.getInstance();
		String sql="select fs,pm from view_pjpy_zhszcpb where xh=? and xn=? and jb='1'";
		return dao.getList(sql, new String[]{xh,xn},new String[]{"fs","pm"} );
	}
	
	
	/**
	 * 综测分排名
	 * @return
	 */
	public String getZcfpm(String xh){
		String blog="false";
		PjpyMjxyDao pjpyMjxyDao=new PjpyMjxyDao();
		PjpyCommonInterface itface=new PjpyCommonInterface();
		//该学生综测总分 取接口数据
		List<HashMap<String,String>> list=itface.queryStuZcPmxx(pjpyMjxyDao.getStu(xh));
		if(list.size()>0){
			HashMap<String,String>map=list.get(0);
			if(Base.isNull(map.get("pm"))){
				blog="false";
			}else{
				blog= map.get("pm");
			}
		}
		return blog;
	}
	
	public Map<String,String>getStu(String xh){
		String sql="select zydm,nj,xh,'1'jb from xsxxb where xh=? ";
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{xh}, new String[]{"zydm","nj","xh","jb"});
		if(list.size()>0){
			Map map=list.get(0);
			return map;
		}else{
			return null;
		}
	}
	
	

	
	public List<HashMap<String,String>>getJxjTjList(){
		String sql="select tjzd,tjlx,tjz from zjcm_pjpy_tjsz where lx='jxj'";
		DAO dao=DAO.getInstance();
		return dao.getList(sql, new String[]{}, new String[]{"tjzd","tjlx","tjz"});
	}
	
	
	/**
	 * 综测分比例的确认
	 */
	public String checkZcbl(float pmbl ,String jxjlb){
		String blog="false";
		DAO dao=DAO.getInstance();
		String cfSql="select tjzd,tjlx,tjz,xn,xq from zjcm_pjpy_tjsz where tjzd='pmbl' and jxjdm= ? ";
		List<HashMap<String,String>>cflist=dao.getList(cfSql, new String[]{jxjlb},new String[]{"tjzd","tjlx","tjz","xn","xq"} );
		if(cflist.size()>0){
			HashMap<String,String>cfMap=cflist.get(0);
			String tjzd=cfMap.get("tjzd");
			String tjlx=cfMap.get("tjlx");
			float tjz=Float.parseFloat(cfMap.get("tjz"));
			if("=".equals(tjlx)){
				blog=(pmbl==tjz)? "true" : "false";
			}else if("<".equals(tjlx)){
				blog=(pmbl<tjz)? "true" : "false";
			}else if(">".equals(tjlx)){
				blog=(pmbl>tjz)? "true" : "false";
			}else if("<=".equals(tjlx)){
				blog=(pmbl<=tjz)? "true" : "false";
			}else if(">=".equals(tjlx)){
				blog=(pmbl>=tjz)? "true" : "false";
			}
		}else{
			blog="true";
		}
		
		
		return blog;
	}
	
	/**
	 * 体育成绩的确认
	 */
	public String checkTycj(String xh,String jxjlb){
		String blog="false";
		StringBuilder strBuilder=new StringBuilder();
		DAO dao=DAO.getInstance();
	
		String tyfSql="select tjzd,tjlx,tjz,xn from zjcm_pjpy_tjsz where tjzd='tydb' and jxjdm= ? ";
		List<HashMap<String,String>>tyflist=dao.getList(tyfSql, new String[]{jxjlb},new String[]{"tjzd","tjlx","tjz","xn"} );
		if(tyflist.size()>0){
			HashMap<String,String>tyMap=tyflist.get(0);
			String tjzd=tyMap.get("tjzd");
			String tjlx=tyMap.get("tjlx");
			String tjz=tyMap.get("tjz");
			String xn=tyMap.get("xn");
			//判断不及格课有多少门
			strBuilder.append("select xh,tydb tydb from(select xh,cj tydb from cjb where kcmc like '%体育%' and xn=? and xh=? ) where  ");
			String jls= "select * from cjb where kcmc like '%体育%' and xn=? and xh=? ";
			strBuilder.append(tjzd);
			strBuilder.append(tjlx);
			strBuilder.append(tjz);
			List<HashMap<String,String>>list=new ArrayList<HashMap<String,String>>();
			List<HashMap<String,String>>jlslist=new ArrayList<HashMap<String,String>>();
			list=dao.getList(strBuilder.toString(), new String[]{xn,xh},new String[]{"xh"} );
			jlslist=dao.getList(jls, new String[]{xn,xh},new String[]{"xh"} );
			//满足条件的数据
			if(jlslist.size()>0){
				if(list.size()>0){
					blog="false";
				}else{
					blog="true";
				}
			}else{
				blog="nojl";
			}
		}else{
			blog="true";
		}
		return blog;
	}
}
