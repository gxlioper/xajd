package xgxt.pjpy.util;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.New_Random_ID;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.pjpy.model.*;
import java.util.*;

public class zbdx_pjpy_rychsh_util {
	
	//DAO mydao= DAO.getInstance();
	StandardOperation myop = new StandardOperation();
	New_Random_ID newId= new New_Random_ID();
    lrh_commen_util com_util=new lrh_commen_util();
    
    public zbdx_rychsqsh_model zbdx_pjpy_getrychsh_detail(zbdx_rychsqsh_model rychshsh)
    {
    	DAO mydao= DAO.getInstance();
    	String xh=rychshsh.getStu().getXH();
    	String xn=rychshsh.getRychsh().getXn();
//    	String nd=rychshsh.getRychsh().getNd();
    	String rychdm=rychshsh.getRychsh().getRychdm();
    	String rychmc=com_util.get_rychmc_bydm(rychdm);
    	rychshsh.getRychsh().setRychmc(rychmc);
    	String sql_0="select a.NJ,a.ZYDM from VIEW_XSJBXX a where a.xh='"+xh+"'";
    	String []inputValue_0={};
    	String []outputValue_0={"NJ","ZYDM"};
		String []rs_0=mydao.getOneRs(sql_0, inputValue_0, outputValue_0);
		String nj_0=rs_0[0];
    	String zydm_0=rs_0[1];
		
    	
    	String sql="select a.DCJ,a.TCJ,a.ZCJ,a.RWSZF from VIEW_ZHSZCP a where a.xn='"+xn+"' and xh='"+xh+"'";
    	String [] inputValue={};
    	String [] outputValue={"DCJ","TCJ","ZCJ","RWSZF"};
    	String []rs=mydao.getOneRs(sql, inputValue, outputValue);
    	boolean nul_flag=false;
    	for(int i=0;i<rs.length;i++)
    	{
    		if(null!=rs[i]&&""!=rs[i])
    		{
    			nul_flag=true;
    		}
    		else 
    		{
    			nul_flag=false;
    		}
    	}
    	if(true==nul_flag)
    	{
	    	rychshsh.getRychsh().setSjddszcp_1(this.get_dcj_dj(rs[0]));
	    	rychshsh.getRychsh().setStszcp_1(this.get_dcj_dj(rs[1]));
	    	rychshsh.getRychsh().setZylrszf(rs[2]);
	    	rychshsh.getRychsh().setRwszcp_1(rs[3]);
    	}
    	else
    	{
    		if(null==rs[0]||""==rs[0])
    		{
    			rychshsh.getRychsh().setSjddszcp_1(this.get_dcj_dj("0"));
    		}
    		else
    		{
    			rychshsh.getRychsh().setSjddszcp_1(this.get_dcj_dj(rs[0]));
    		}
    		if(null==rs[1]||""==rs[1])
    		{
    			rychshsh.getRychsh().setStszcp_1(this.get_dcj_dj("0"));
    		}
    		else
    		{
    			rychshsh.getRychsh().setStszcp_1(this.get_dcj_dj(rs[1]));
    		}
	    	if(null==rs[2]||""==rs[2])
	    	{
	    		rychshsh.getRychsh().setZylrszf("0");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setZylrszf(rs[2]);
	    	}
	    	if(null==rs[3]||""==rs[3])
	    	{
	    		rychshsh.getRychsh().setRwszcp_1("0");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setRwszcp_1(rs[3]);
	    	}
    	}
    	String pass_cet4=this.pass_cet4(xh, xn);
    	rychshsh.getRychsh().setPass_cet_4(pass_cet4);
    	String pm=this.zhszcpzfpm(xh, xn);
    	String sum=this.get_zyxnnj_rs(nj_0, xn, zydm_0);
    	float pmbfb=(new Float(pm))/(new Float(sum));
    	float save_pm=pmbfb;
    	int index=String.valueOf(pmbfb).indexOf(".");
    	pmbfb=new Float(String.valueOf(pmbfb).substring(0,index+4))*100;
    	String zhszcppm=String.valueOf(pmbfb)+"%";
    	rychshsh.getRychsh().setZhszcpzfpm(zhszcppm);
    	rychshsh.getRychsh().setNo_fail(this.have_nofail(xh,xn));
    		
    	
	    if("三好学生".equalsIgnoreCase(rychmc))
		{
	    	if("优"!=rychshsh.getRychsh().getSjddszcp_1())
	    	{
	    		rychshsh.getRychsh().setSjddszcp_1(rychshsh.getRychsh().getSjddszcp_1()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setSjddszcp_1(rychshsh.getRychsh().getSjddszcp_1()+" (达标)");
	    	}
	    	
	    	if("优"!=rychshsh.getRychsh().getStszcp_1())
	    	{
	    		rychshsh.getRychsh().setStszcp_1(rychshsh.getRychsh().getStszcp_1()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setStszcp_1(rychshsh.getRychsh().getStszcp_1()+" (达标)");
	    	}
	    	
	    	if("通过"!=rychshsh.getRychsh().getNo_fail())
	    	{
	    		rychshsh.getRychsh().setNo_fail(rychshsh.getRychsh().getNo_fail()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setNo_fail(rychshsh.getRychsh().getNo_fail()+" (达标)");
	    	}
	    	
	    	if("通过"!=rychshsh.getRychsh().getPass_cet_4())
	    	{
	    		rychshsh.getRychsh().setPass_cet_4(rychshsh.getRychsh().getPass_cet_4()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setPass_cet_4(rychshsh.getRychsh().getPass_cet_4()+" (达标)");
	    	}
	    	
	    	Float zcj= new Float(rychshsh.getRychsh().getZylrszf());
	    	if(zcj>=80)
	    	{
	    		rychshsh.getRychsh().setZylrszf(rychshsh.getRychsh().getZylrszf()+" (达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setZylrszf(rychshsh.getRychsh().getZylrszf()+" (不达标)");
	    	}
	    	
	    	if(save_pm<=0.2&&save_pm>=0.1)
	    	{
	    		rychshsh.getRychsh().setZhszcpzfpm(rychshsh.getRychsh().getZhszcpzfpm()+" (达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setZhszcpzfpm(rychshsh.getRychsh().getZhszcpzfpm()+" (不达标)");
	    	}
	    	
	    	
		}	
    	else if("优秀学生干部".equalsIgnoreCase(rychmc))
    	{
    		if("优"!=rychshsh.getRychsh().getSjddszcp_1())
	    	{
	    		rychshsh.getRychsh().setSjddszcp_1(rychshsh.getRychsh().getSjddszcp_1()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setSjddszcp_1(rychshsh.getRychsh().getSjddszcp_1()+" (达标)");
	    	}
	    	
	    	if("优"!=rychshsh.getRychsh().getStszcp_1()||"良"!=rychshsh.getRychsh().getStszcp_1())
	    	{
	    		rychshsh.getRychsh().setStszcp_1(rychshsh.getRychsh().getStszcp_1()+" (不达标)");
	    	}
	    	else 
	    	{
	    		rychshsh.getRychsh().setStszcp_1(rychshsh.getRychsh().getStszcp_1()+" (达标)");
	    	}
	    	
	    	if("通过"!=rychshsh.getRychsh().getNo_fail())
	    	{
	    		rychshsh.getRychsh().setNo_fail(rychshsh.getRychsh().getNo_fail()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setNo_fail(rychshsh.getRychsh().getNo_fail()+" (达标)");
	    	}
	    	
	    	if("通过"!=rychshsh.getRychsh().getPass_cet_4())
	    	{
	    		rychshsh.getRychsh().setPass_cet_4(rychshsh.getRychsh().getPass_cet_4()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setPass_cet_4(rychshsh.getRychsh().getPass_cet_4()+" (达标)");
	    	}
	    	
	    	Float zcj= new Float(rychshsh.getRychsh().getZylrszf());
	    	if(zcj>=80)
	    	{
	    		rychshsh.getRychsh().setZylrszf(rychshsh.getRychsh().getZylrszf()+" (达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setZylrszf(rychshsh.getRychsh().getZylrszf()+" (不达标)");
	    	}
	    	
	    	if(save_pm<=0.2&&save_pm>=0.1)
	    	{
	    		rychshsh.getRychsh().setZhszcpzfpm(rychshsh.getRychsh().getZhszcpzfpm()+" (达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setZhszcpzfpm(rychshsh.getRychsh().getZhszcpzfpm()+" (不达标)");
	    	}
	    	
	    	if("优"!=rychshsh.getRychsh().getRwszcp_1())
	    	{
	    		rychshsh.getRychsh().setRwszcp_1(rychshsh.getRychsh().getRwszcp_1()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setRwszcp_1(rychshsh.getRychsh().getRwszcp_1()+" (达标)");
	    	}
    	}
    	else if("三好学生标兵".equalsIgnoreCase(rychmc))
    	{
    		if("优"!=rychshsh.getRychsh().getSjddszcp_1())
	    	{
	    		rychshsh.getRychsh().setSjddszcp_1(rychshsh.getRychsh().getSjddszcp_1()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setSjddszcp_1(rychshsh.getRychsh().getSjddszcp_1()+" (达标)");
	    	}
	    	
	    	if("优"!=rychshsh.getRychsh().getStszcp_1())
	    	{
	    		rychshsh.getRychsh().setStszcp_1(rychshsh.getRychsh().getStszcp_1()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setStszcp_1(rychshsh.getRychsh().getStszcp_1()+" (达标)");
	    	}
	    	
	    	if("通过"!=rychshsh.getRychsh().getNo_fail())
	    	{
	    		rychshsh.getRychsh().setNo_fail(rychshsh.getRychsh().getNo_fail()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setNo_fail(rychshsh.getRychsh().getNo_fail()+" (达标)");
	    	}
	    	
	    	if("通过"!=rychshsh.getRychsh().getPass_cet_4())
	    	{
	    		rychshsh.getRychsh().setPass_cet_4(rychshsh.getRychsh().getPass_cet_4()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setPass_cet_4(rychshsh.getRychsh().getPass_cet_4()+" (达标)");
	    	}
	    	
	    	Float zcj= new Float(rychshsh.getRychsh().getZylrszf());
	    	if(zcj>=85)
	    	{
	    		rychshsh.getRychsh().setZylrszf(rychshsh.getRychsh().getZylrszf()+" (达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setZylrszf(rychshsh.getRychsh().getZylrszf()+" (不达标)");
	    	}
	    	
	    	if(save_pm<=0.2&&save_pm>=0.1)
	    	{
	    		rychshsh.getRychsh().setZhszcpzfpm(rychshsh.getRychsh().getZhszcpzfpm()+" (达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setZhszcpzfpm(rychshsh.getRychsh().getZhszcpzfpm()+" (不达标)");
	    	}
    	}
    	else if("优秀学生干部标兵".equalsIgnoreCase(rychmc))
    	{
    		if("优"!=rychshsh.getRychsh().getSjddszcp_1())
	    	{
	    		rychshsh.getRychsh().setSjddszcp_1(rychshsh.getRychsh().getSjddszcp_1()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setSjddszcp_1(rychshsh.getRychsh().getSjddszcp_1()+" (达标)");
	    	}
	    	
	    	if("优"!=rychshsh.getRychsh().getStszcp_1()||"良"!=rychshsh.getRychsh().getStszcp_1())
	    	{
	    		rychshsh.getRychsh().setStszcp_1(rychshsh.getRychsh().getStszcp_1()+" (不达标)");
	    	}
	    	else 
	    	{
	    		rychshsh.getRychsh().setStszcp_1(rychshsh.getRychsh().getStszcp_1()+" (达标)");
	    	}
	    	
	    	if("通过"!=rychshsh.getRychsh().getNo_fail())
	    	{
	    		rychshsh.getRychsh().setNo_fail(rychshsh.getRychsh().getNo_fail()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setNo_fail(rychshsh.getRychsh().getNo_fail()+" (达标)");
	    	}
	    	
	    	if("通过"!=rychshsh.getRychsh().getPass_cet_4())
	    	{
	    		rychshsh.getRychsh().setPass_cet_4(rychshsh.getRychsh().getPass_cet_4()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setPass_cet_4(rychshsh.getRychsh().getPass_cet_4()+" (达标)");
	    	}
	    	
	    	Float zcj= new Float(rychshsh.getRychsh().getZylrszf());
	    	if(zcj>=80)
	    	{
	    		rychshsh.getRychsh().setZylrszf(rychshsh.getRychsh().getZylrszf()+" (达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setZylrszf(rychshsh.getRychsh().getZylrszf()+" (不达标)");
	    	}
	    	
	    	if(save_pm<=0.2&&save_pm>=0.1)
	    	{
	    		rychshsh.getRychsh().setZhszcpzfpm(rychshsh.getRychsh().getZhszcpzfpm()+" (达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setZhszcpzfpm(rychshsh.getRychsh().getZhszcpzfpm()+" (不达标)");
	    	}
	    	
	    	if("优"!=rychshsh.getRychsh().getRwszcp_1())
	    	{
	    		rychshsh.getRychsh().setRwszcp_1(rychshsh.getRychsh().getRwszcp_1()+" (不达标)");
	    	}
	    	else
	    	{
	    		rychshsh.getRychsh().setRwszcp_1(rychshsh.getRychsh().getRwszcp_1()+" (达标)");
	    	}
    	}
	    return rychshsh;
    }
    
    
    public String get_dcj_dj(String dcj)
    {
    	if(null!=dcj&&""!=dcj)
    	{
    		String dj="";
    		Float cj= new Float(dcj);
    		if(cj>=95)
    		{
    			dj="优";
    		}
    		else if(cj>=85&&cj<95)
    		{
    			dj="良";
    		}
    		else if(cj>=75&&cj<85)
    		{
    			dj="中";
    		}
    		else if(cj>=65&&cj<75)
    		{
    			dj="达标";
    		}
    		else if(cj>=45&&cj<75)
    		{
    			dj="待达标";
    		}
    		return dj;
    	}
    	else
    	{
    		return "eligal_number";
    	}
    }
    
    public String pass_cet4(String xh,String xn)
    {
    	DAO mydao= DAO.getInstance();
    	String [] nd2=xn.split("-");
    	String pass_flag="";
    	String sql="select a.NJ from VIEW_XSJBXX a where a.xh='"+xh+"'";
    	String []inputValue={};
    	String []outputValue={"NJ"};
		String []rs=mydao.getOneRs(sql, inputValue, outputValue);
		String cx_nj=rs[0];
		int tmp=Integer.valueOf(nd2[0])-Integer.valueOf(cx_nj);
		if(tmp>0)
		{
			sql="select a.cj from view_xscetcjb a where a.xh='"+xh+"'";
			String []inputValue2={};
	    	List<HashMap<String, String>> li=mydao.getListNotOut(sql, inputValue2);
	    	HashMap<String, String> map = new HashMap<String, String>();
	 		int tempLen=li.size();
	 		for(int i=0;i<tempLen;i++)
	 		{
	 			map=li.get(i);
	 			if(Integer.valueOf(map.get("cj"))>=60)
	 			{	
	 				pass_flag="通过";
	 				break;
	 			}
	 			else
	 			{
	 				pass_flag="未通过";
	 			}
	 		}
		}
		else
		{
			pass_flag="通过";
		}
		return pass_flag;
    }
    
    
    public String zhszcpzfpm(String xh,String xn)
    {
    	DAO mydao= DAO.getInstance();
    	String sql="select a.ZYDM,a.NJ from VIEW_ZHSZCP a where a.xh='"+xh+"'";
    	String []inputValue={};
    	String []outputValue={"ZYDM","NJ"};
    	String []rs=mydao.getOneRs(sql, inputValue, outputValue);
    	String zydm=rs[0];
    	String nj=rs[1];
    	sql="select a.行号 from (select rownum 行号 ,a.xh from (select a.xh from VIEW_ZHSZCP a where a.ZYDM='"+zydm+"' and a.NJ='"+nj+"' and a.XN='"+xn+"'"
    		+" order by a.ZHSZCPZF desc) a )a  where a.xh='"+xh+"'";
    	String []inputValue3={};
    	String []outputValue3={"行号"};
    	String []rs3=mydao.getOneRs(sql, inputValue3, outputValue3);
    	String pm=rs3[0];
    	return pm;
    }
    
    public String get_zyxnnj_rs(String nj,String xn,String zydm)
    {
    	DAO mydao= DAO.getInstance();
    	String sql="select count(*) SUM from VIEW_ZHSZCP a where a.ZYDM='"+zydm+"' and a.NJ='"+nj+"' and a.XN='"+xn+"'";
    	String []inputValue={};
    	String []outputValue={"SUM"};
    	String []rs=mydao.getOneRs(sql, inputValue, outputValue);
    	String sum=rs[0];
    	return sum;
    }
    
    public String have_nofail(String xh,String xn)
    {
    	DAO mydao= DAO.getInstance();
    	String pass_flag="";
    	String sql="select a.cj from cjb a where a.xh='"+xh+"' and a.xn='"+xn+"'";
    	String []inputValue={};
    	List<HashMap<String, String>> li=mydao.getListNotOut(sql, inputValue);
    	HashMap<String, String> map = new HashMap<String, String>();
 		int tempLen=li.size();
 		for(int i=0;i<tempLen;i++)
 		{
 			map=li.get(i);
 			Float cj=new Float(map.get("cj"));
 			if(cj>=60)
 			{	
 				pass_flag="通过";
 			}
 			else
 			{
 				pass_flag="未通过";
 				break;
 			}
 		}
 		return pass_flag;
    }
    
    
    public String get_zylrszf(String xh,String xn)
    {
    	DAO mydao= DAO.getInstance();
    	float tmp=0;
    	String sql="select a.cj from cjb a where a.xh='"+xh+"' and a.xn='"+xn+"'";
    	String []inputValue={};
    	List<HashMap<String, String>> li=mydao.getListNotOut(sql, inputValue);
    	HashMap<String, String> map = new HashMap<String, String>();
 		int tempLen=li.size();
 		int j=0;
 		for(int i=0;i<tempLen;i++)
 		{
 			map=li.get(i);
 			Float cj= new Float(map.get("cj"));
 			tmp=tmp+cj;
 			j++;
 		}
 		float rs=tmp/j;
 		int index=String.valueOf(rs).indexOf(".");
 		String r=String.valueOf(rs).substring(index, 2);
 		return r;
    }
}
