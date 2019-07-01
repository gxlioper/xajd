
package xgxt.studentInfo.zzdx;

import java.util.List;

import xgxt.DAO.DAO;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中州大学高基报表Dao</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-26</p>
 */
public class XsxxZzdxDao {
	DAO dao = DAO.getInstance();
	
	/**
	 * 获取一个数字
	 * @return sql
	 * */
	public int getOneCount(String sql){
		int iCount= 0;
		iCount = Integer.parseInt(dao.getOneRs(sql, new String[]{}, "count"));
		return iCount;
	}	
	
	/**
	 * Method getReportList 获取报表列表数据 
	 * @return list
	 */
	public List getReportList(){		
		List list = null;
		String sql = "";
		sql = "select distinct  bbbh, bbmc from gjbbxxb";
		list = dao.getList(sql, new String[]{}, new String[]{"bbbh", "bbmc"});
		return list;
	}
	
	/**
	 * Method getCountListOfGjbb222 获取Gjbb222报表的横行总计在校数据 
	 * @return countList
	 */
	public List getLevCountListOfGjbb222(){
		String sql ="select count(a.xh) count,xlccmc from xlccdmb b left join  (" +
					"SELECT xh,pycc FROM bks_xsjbxx a WHERE sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)"+
					" UNION "+ 
					"SELECT xh,pycc FROM xsxxb where sfzx='在校'" +
					") a on a.pycc=b.xlccmc group by xlccmc,xlccdm order by b.xlccdm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});		 
	}
	
	/**
	 * Method getVerCountListOfGjbb222 获取Gjbb222报表的纵行总计在校数据 
	 * @return countList
	 */
	public List getVerCountListOfGjbb222(String condition){
		String sql ="select count(a.xh) count,sydq from dmk_sydq b left join ("+
				    "select xh,jg from ("+
				    "SELECT xh,jgm jg,pycc FROM bks_xsjbxx a WHERE sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)"+
				    " UNION "+ 
				    "SELECT xh,jg, pycc FROM xsxxb where sfzx='在校'"+
				    ")"+ condition +
                    ") a on a.jg=b.sydq where sydqdm<710000 group by sydq,sydqdm order by sydqdm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});		 
	}
	
	/**
	 * Method getSydListOfGjbb222 获取Gjbb222报表的地区数据 
	 * @return countList
	 */
	public List getSydListOfGjbb222(){
		String sql ="select distinct sydqdm, sydq from dmk_sydq where sydqdm<710000 order by sydqdm";
		return dao.getList(sql, new String[]{}, new String[]{"sydq"});		 
	}
	
	/**
	 * Method getGatListOfGjbb222 获取Gjbb222报表的港澳台地区在校数据 
	 * @return countList
	 */
	public List getGatListOfGjbb222(){
		String sql ="select count(a.xh) count,xlccmc from xlccdmb b left join (" +
                    "select xh,jg,pycc from(" +
                    "SELECT xh,jgm jg,pycc FROM bks_xsjbxx a WHERE sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)"+
                    " UNION " +  
                    "SELECT xh,jg, pycc FROM xsxxb where sfzx='在校'" + 
                    ") where jg='台湾省' or jg='香港特别行政区' or jg='澳门特别行政区'" + 
                    ") a  on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";		
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	/**
	 * Method getBzkGatListOfGjbb222 获取Gjbb222报表的各地区普通本、专科招生数据 
	 * @return countList
	 */
	public List getBzkListOfGjbb222(){
		String sql ="select count(a.xh) count,sydq from dmk_sydq b left join " + 
                    "(select xh,lys,pycc from(" + 
                    "SELECT xh,lys,cc pycc FROM xsjbxxlsb a " + 
                    ")" + 
                    "where pycc='普通本科' or pycc='普通专科'" + 
                    ") a on a.lys=b.sydq where sydqdm<710000  group by sydq,sydqdm order by sydqdm";		
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	/**
	 * Method getTotalStudentCount 获取总的在校学生数
	 * @return iTotal
	 */
	public String getTotalStudentCount(){
		String sql = "select count(xh) count from (select xh from(" +
				"SELECT xh FROM bks_xsjbxx a WHERE sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" +
				" UNION " +
				"SELECT xh FROM xsxxb where sfzx='在校'" + 
				")) ";
		
		return dao.getOneRs(sql, new String[]{}, "count");		
	}
	
	/**
	 * Method getGatTotalStuCount 获取港澳台总的在校学生数
	 * @return iTotal
	 */
	public String getGatTotalStuCount(){
		String sql = "select count(*) count from (" + 
                     "SELECT xh,jgm jg FROM bks_xsjbxx a WHERE sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +  
                     "SELECT xh,jg FROM xsxxb WHERE sfzx='在校'" + 
                     ") where jg='台湾省' or jg='香港特别行政区' or jg='澳门特别行政区'";		
		return dao.getOneRs(sql, new String[]{}, "count");		
	}	
	
	/**
	 * Method getBzkTotalStuCount 获取总的普通本专科招生学生数
	 * @return iTotal
	 */
	public String getBzkTotalStuCount(){
		String sql = "select count(a.xh)count from " +
                     "(select xh,pycc from(" + 
                     "SELECT xh,cc pycc FROM xsjbxxlsb" + 
                     ")" + 
                     "where pycc='普通本科' or pycc='普通专科'" + 
                     ") a";		
		return dao.getOneRs(sql, new String[]{}, "count");		
	}	
	
	/**
	 * Method getBzkGatTotal 获取港澳台总的普通本专科招生数
	 * @return iTotal
	 */
	public String getBzkGatTotal(){
		String sql = "select count(*) count from (" + 
                     "SELECT xh,lys FROM xsjbxxlsb a WHERE NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +  
                     "SELECT xh,jg FROM xsxxb" + 
                     ") where lys='台湾省' or lys='台湾' or lys='香港特别行政区' or lys='香港'  or lys='澳门特别行政区' or lys='澳门'";		
		return  dao.getOneRs(sql, new String[]{}, "count");		
	}
	
	/**
	 * Method getOtherInfo 获取在校学生中其他情况
	 * @param condition
	 * @return list
	 */
	public List getOtherInfo(String condition){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join(" + 
                     "select xh,zzmm,mz,pycc from(" + 
                     "SELECT a.xh,b.zzmmm zzmm,b.mzdm mz,a.pycc FROM bks_xsjbxx a left join bks_xsqtxx b on a.xh=b.xh WHERE a.sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +  
                     "SELECT xh,zzmm,mz,pycc FROM xsxxb where sfzx='在校'" + 
                     ") where 1=1" +  condition+
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	/**
	 * Method getStuAgeInfo 获取在校学生年龄情况
	 * @param condition
	 * @return list
	 */
	public List getStuAgeInfo(String condition){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join(" +
                     "select xh,csrq,pycc,xb,nl from (" + 
                     "select xh,csrq,pycc,xb,(to_number(dqrq)-to_number(csrq)) nl from (" + 
                     "select xh,to_number(nvl(csrq,'0')) csrq,pycc,xb,substr((select to_char(sysdate,'yyyy-dd-mm') from dual),0,4) dqrq from(" + 
                     "SELECT a.xh,substr(0,4,b.csrq) csrq,a.pycc,a.xbm xb FROM bks_xsjbxx a left join bks_xsqtxx b on a.xh=b.xh WHERE a.sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" +
                     " UNION " +  
                     "SELECT xh,substr(csrq,0,4) csrq,pycc,xb FROM xsxxb where sfzx='在校'" + 
                     ")" + 
                     ")" + 
                     ")where 1=1 " + condition + 
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	/**
	 * 获取在校学生残疾人数
	 * @return List
	 * */
	public List getCjrInfo(){
		//在校学生残疾人数＝特级学院学生数
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " +
                     "select xh,pycc,xydm from(" + 
                     "SELECT a.xh,pycc,bmdm xydm  FROM bks_xsjbxx a left join bks_xsqtxx b on a.xh=b.xh WHERE a.sfzx='在校'  and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +    
                     "SELECT xh,pycc,xydm  FROM xsxxb where sfzx='在校'" + 
                     ") where xydm=(select distinct xydm from view_njxyzybj where xymc='计算机科学与技术学院')" + 
                     ") a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	/**
	 * 获取普通本专科专业名称列表
	 * @return List
	 * */
	public List getZymcList(){
		String sql = "select distinct a.zydm,a.zymc,(select distinct xz from zydmb b where a.zydm=b.zydm) xz from view_njxyzybj a order by a.zydm";
		return dao.getList(sql, new String[]{}, new String[]{"zymc","zydm","xz"});		
	}
	
	/**
	 * 普通本专科毕业生数据
	 * @return List
	 * */
	public List getBysCountByZy(){
		String sql = "select distinct count(a.xh) count,b.zydm from view_njxyzybj b left join(" + 
                     "select xh,zydm from(" + 
                     "SELECT a.xh,(select zydm from view_xsjbxx b where a.xh=b.xh ) zydm FROM bysfzxxb@dblink_jw a where byjr='毕业' " +     
                     ")" + 
                     ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}	
	
	/**
	 * 普通本专科授予学位数
	 * @return List
	 * */
	public List getXwCountList(){
		String sql = "select distinct count(a.xh) count,b.zydm from view_njxyzybj b left join(" + 
                     "select xh,zydm from(" + 
                     "SELECT a.xh,(select zydm from view_xsjbxx b where a.xh=b.xh ) zydm FROM bysfzxxb@dblink_jw a where xwzsh is not null " +     
                     ")" + 
                     ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});		
	}
	
	
	/**
	 * 分专业招生数
	 * @param nd
	 * @return List
	 * */
	public List getZsList(String nd){
		String sql = "select distinct count(a.xh) count,b.zydm,max(b.zymc) from view_njxyzybj b left join(" + 
                     "select xh,zydm from(" + 
                     "SELECT a.xh,(select zydm from view_xsjbxx b where a.xh=b.xh ) zydm FROM xsjbxxlsb a where substr(zssj,0,4)='"+ nd +"'" + 
                     ")" + 
                     ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	/**
	 * 应届生招生数
	 * @param nd
	 * @return List
	 * */
	public List getYjsList(String nd){
		String sql = "select distinct count(a.xh) count,b.zydm,max(b.zymc) from view_njxyzybj b left join(" + 
			        "select xh,zydm from(" + 
			        "SELECT a.xh,(select zydm from view_xsjbxx b where a.xh=b.xh ) zydm FROM xsjbxxlsb a where substr(zssj,0,4)='"+ nd +"' and zslb='应届生'" + 
			        ")" + 
			        ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * 春季招生数
	 * @param nd
	 * @return List
	 * */	
	public List getCjzsList(String nd){
		String sql = "select distinct count(a.xh) count,b.zydm,max(b.zymc) from view_njxyzybj b left join(" + 
			        "select xh,zydm from(" + 
			        "SELECT a.xh,(select zydm from view_xsjbxx b where a.xh=b.xh ) zydm FROM xsjbxxlsb a where substr(zssj,0,4)='"+ nd +"' and zsjj like '%春%'" + 
			        ")" + 
			        ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * 在校学生数
	 * @param nd 
	 * @return List
	 * */
	public List getZxListByZy(String nd){
		String sql = "select b.zydm,count(distinct a.xh) count from view_njxyzybj b  left join (" +                      
                     "select distinct a.zydm,a.xh from (" + 
                     "SELECT a.xh,a.xbm xb,zydm FROM bks_xsjbxx a  WHERE a.sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +  
                     "SELECT xh,xb,zydm FROM xsxxb where sfzx='在校'" + 
                     ") a " +      
                     ") a on a.zydm = b.zydm group by b.zydm order by b.zydm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * 分年级学生数
	 * @param condition
	 * @return List
	 * */
	public List getZxxsByGrade(String condition){
		String sql = "select b.zydm,count(distinct a.xh)count from view_njxyzybj b  left join (" +   
                     "select zydm,xh,xb,dqnj from ( " +       
                     "select zydm,xh,xb,(dqn-nj) dqnj from ( " +              
                     "select distinct a.zydm,a.xh,xb,nj,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn from dual)dqn from (" + 
                     "SELECT a.xh,a.xbm xb,zydm,to_char(nj)nj FROM bks_xsjbxx a  WHERE a.sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +  
                     "SELECT xh,xb,zydm,nj FROM xsxxb where sfzx='在校' " + 
                     ") a " +      
                     ")" + 
                     ")  where 1=1 " + condition + 
                     ") a on a.zydm = b.zydm  group by b.zydm order by b.zydm";
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * 预计毕业生数
	 * */
	public List getYjbysList(){
		String sql = "select b.zydm,count(distinct a.xh)count from view_njxyzybj b  left join (" +   
                     "select zydm,xh,xb,nj,dqn,xz from ( " +              
                     "select distinct a.zydm,a.xh,xb,nj,xz,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn from dual)dqn from ( " + 
                     "SELECT a.xh,a.xbm xb,zydm,to_char(nj)nj,to_char(xz)xz FROM bks_xsjbxx a  WHERE a.sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh) " + 
                     " UNION " +  
                     "SELECT xh,xb,zydm,nj,xz FROM xsxxb where sfzx='在校' " + 
                     ") a " +      
                     ") where (nj+xz) = dqn " + 
                     ") a on a.zydm = b.zydm  group by b.zydm order by b.zydm"; 
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * 查询毕业女生总数
	 * @return int
	 * */
	public int getByCountOfGirl(){		
		String sql = "select count(*) count from (select a.xh,(select b.xb from view_xsjbxx b where a.xh=b.xh)xb from bysfzxxb@dblink_jw a where byjr='毕业') where xb='女' or xb='2'";
		return this.getOneCount(sql);		
	}
	
	/**
	 * 授予学位女生总数
	 * @return int
	 * */
	public int getTotalGirlOfXw(){		
		String sql = "select count(*) count from (select a.xh,(select b.xb from view_xsjbxx b where a.xh=b.xh)xb from bysfzxxb@dblink_jw a where xwzsh is not null) where xb='女' or xb='2'";
		return this.getOneCount(sql);		
	}
	
	/**
	 * 招女生总数
	 * @param nd
	 * @return int
	 * */
	public int getTotalGirlOfZs(String nd){
		String sql = "select count(*) count from xsjbxxlsb where xb='女' and substr(zssj,0,4)='"+ nd +"' ";
		return this.getOneCount(sql);
	}
	
	/**
	 * 招收应届生中女生总数
	 * @param nd
	 * @return int
	 * */
	public int getTotalGirlOfZsyjs(String nd){
		String sql = "select count(*) count from xsjbxxlsb where xb='女' and zslb='应届生' and substr(zssj,0,4)='"+ nd +"' ";
		return this.getOneCount(sql);
	}
	
	/**
	 * 春季招女生总数
	 * @param nd
	 * @return int
	 * */
	public int getTotalGirlOfCjzs(String nd){
		String sql = "select count(*) count from xsjbxxlsb where xb='女' and (zsjj='春' or zsjj='春季') and substr(zssj,0,4)='"+ nd +"' ";
		return this.getOneCount(sql);
	}
	
	/**
	 * 在校学生女生总数
	 * @param nd
	 * @return int
	 * */
	public int getTotalGirlOfZx(){
		String sql = "select max(xb),count(distinct a.xh) count from (" + 
                     "SELECT a.xh,a.xbm xb FROM bks_xsjbxx a  WHERE a.sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh) " + 
                     " UNION " +  
                     "SELECT xh,xb FROM xsxxb where sfzx='在校' " + 
                     ") a  where xb='女' or xb='2'  ";
		return this.getOneCount(sql);
	}
	
	/**
	 * 分年级女生数
	 * @param condition
	 * @return int
	 * */
	public int getTotalGrilByGrade(String condition){
		String sql = "select count(xh)count from (" + 
                     "select xh,xb,dqnj from (" +    
                     "select zydm,xh,xb,(dqn-nj) dqnj from ( " +              
                     "select distinct a.zydm,a.xh,xb,nj,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn from dual)dqn from ( " + 
                     "SELECT a.xh,a.xbm xb,zydm,to_char(nj)nj FROM bks_xsjbxx a  WHERE a.sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh) " + 
                     " UNION " +  
                     "SELECT xh,xb,zydm,nj FROM xsxxb where sfzx='在校' " + 
                     ") a " +      
                     ")" + 
                     ")  where 1=1 and xb='女'" + condition +  
                     ")";
		return this.getOneCount(sql);
	}
	
	/**
	 * 预计毕业女生数
	 * @return int
	 * */
	public int getGirlOfYjby(){
		String sql = "select count(xh)count from (" + 
                     "select zydm,xh,xb,nj,dqn,xz from (" +                
                     "select distinct a.zydm,a.xh,xb,nj,xz,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn from dual)dqn from (" +  
                     "SELECT a.xh,a.xbm xb,zydm,to_char(nj)nj,to_char(xz)xz FROM bks_xsjbxx a  WHERE a.sfzx='在校' and NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" + 
                     " UNION " +   
                     "SELECT xh,xb,zydm,nj,xz FROM xsxxb where sfzx='在校'" + 
                     ") a" +       
                     ") where (nj+xz) = dqn and (xb='女' or xb='2')" + 
                     ")";
		return this.getOneCount(sql);
	}
	
	/**
	 * 上学年初报表在校学生数
	 * @return List
	 * */
	public List getSuperData(){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " + 
                     "select xh,nj,dqn,xz,pycc from (" +                 
                     "select distinct a.xh,xb,nj,xz,(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqn from dual)dqn,pycc from (" +   
                     "SELECT a.xh,a.xbm xb,to_char(nj)nj,to_char(xz)xz,pycc FROM bks_xsjbxx a  WHERE NOT EXISTS(SELECT 1 FROM xsxxb b WHERE a.xh=b.xh)" +  
                     " UNION " +     
                     "SELECT xh,xb,nj,xz,pycc FROM xsxxb" +   
                     ") a" +       
                     ") where (nj+xz) <= dqn-1 " + 
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * 增加学生总数
	 * @return List
	 * */
	public List getIncrease(){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " + 
                     "select xh,pycc from (" +                 
                        "select distinct xh,xm,ydlbmc ,(select pycc from view_xsxxb b where b.xh=a.xh)pycc from view_xjydjbxx a where (ydlbmc='复学' or ydlbmc='转入') and substr(ydrq,0,4)=(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" +  
                     ")" +  
                     " union all " +  
                     "select xh,cc pycc from (" + 
                            "select xh,xm,cc from xsjbxxlsb where substr(zssj,0,4) = (select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" + 
                     ")" + 
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * 分层次招生数
	 * @return List
	 * */
	public List getZszxByCc(){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " +                     
                     "select xh,cc pycc from (" + 
                            "select xh,xm,cc from xsjbxxlsb where substr(zssj,0,4) = (select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" + 
                     ")" + 
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * 分层次异动学生数
	 * @return List
	 * */
	public List getYdxxByCc(String condition){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " + 
                     "select xh,pycc from (" +                 
                        "select distinct xh,xm,ydlbmc ,(select pycc from view_xsxxb b where b.xh=a.xh)pycc from view_xjydjbxx a where  1=1 and substr(ydrq,0,4)=(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" +  condition + 
                     ")" +                      
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	
	 /**
	  * 分层次减少学生总计数
	  * @return List
	  * */
	public List getJsxxList(){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " + 
                     "select xh,pycc from (" +                 
                        "select distinct xh,xm,(select pycc from view_xsxxb b where b.xh=a.xh)pycc from bysfzxxb@dblink_jw a where  (byjr='毕业' or byjr='结业') and substr(byrq,0,4) = (select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" + 
                     ")" +                          
                     " union all " +                     
                     "select xh, pycc from (" + 
                           "select distinct xh,xm,(select pycc from view_xsxxb b where b.xh=a.xh)pycc from view_xjydjbxx a where (ydlbmc='休学' or ydlbmc='退学' or ydlbmc='开除' or ydlbmc='转出' or ydlbmc='死亡') and substr(ydrq,0,4)=(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" + 
                     ")" +                  
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";
		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	/**
	 * 毕业结业学生数
	 * @return List
	 * */
	public List getBjyList(String condition){
		String sql = "select count(a.xh) count,xlccmc from xlccdmb b left join( " + 
                     "select xh,pycc from ( " +                 
                        "select distinct xh,xm,(select pycc from view_xsxxb b where b.xh=a.xh)pycc from bysfzxxb@dblink_jw a where 1=1 and substr(byrq,0,4) = (select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)" + condition + 
                     ")" +             
                     ")a on a.pycc = b.xlccmc group by xlccdm,xlccmc order by xlccdm";		
		return dao.getList(sql, new String[]{}, new String[]{"count"});
	}
	
	
	/**
	 * 本学年初报表在校学生数
	 *@return List
	 * */
	public List getCurrYear(){
		return this.getLevCountListOfGjbb222();//在校学生数
	}
}
