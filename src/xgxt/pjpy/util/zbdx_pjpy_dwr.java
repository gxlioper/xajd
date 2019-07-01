package xgxt.pjpy.util;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.New_Random_ID;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.pjpy.model.zbdx_zhszcp_model;
import java.text.DecimalFormat;  
public class zbdx_pjpy_dwr {
	//public DAO mydao= DAO.getInstance();
	public StandardOperation myop = new StandardOperation();
	public New_Random_ID newId= new New_Random_ID();
	public lrh_commen_util commen_util= new lrh_commen_util();
	
	public zbdx_zhszcp_model pjpy_countZhszcpzf(zbdx_zhszcp_model mymodel)
	{
//		DAO mydao= DAO.getInstance();
//		String xh=mymodel.getXh();
//		String xn=mymodel.getXn();
//		String nd=mymodel.getNd();
		String dcj=Base.chgNull(mymodel.getDcj(),"0", 0);
		String tcj=Base.chgNull(mymodel.getTcj(),"0", 0);
		String zcj=Base.chgNull(mymodel.getZcj(),"0", 0);
		String rwszf=Base.chgNull(mymodel.getRwszf(),"0", 0);
		String cxysjszf=Base.chgNull(mymodel.getCxysjszf(),"0", 0);
		String zhszcpzf=Base.chgNull(mymodel.getZhszcpzf(),"0", 0);
		float temp=0;
		temp=(float)(new Float(dcj)*0.1+new Float(zcj)*0.6+new Float(tcj)*0.1+new Float(rwszf)*0.1+new Float(cxysjszf)*0.1);
		zhszcpzf=this.xiaoshu_sheru(temp,2);
		mymodel.setZhszcpzf(zhszcpzf);
		return mymodel;
	}
	
	public String  xiaoshu_sheru(float inNum, int weishu)
	{
//		DAO mydao= DAO.getInstance();
	  DecimalFormat   df0   =   new   DecimalFormat("###");   
	  DecimalFormat   df1   =   new   DecimalFormat("###.0");   
	  DecimalFormat   df2   =   new   DecimalFormat("###.00"); 
	  DecimalFormat   df3   =   new   DecimalFormat("###.000"); 
	  float f = inNum;
	  String rs="";
	  if(0==weishu)
	  {
		  rs= df0.format(f);   
	  }
	  else if(1==weishu)
	  {
		  rs= df1.format(f);   
	  }
	  else if(2==weishu)
	  {
		  rs= df2.format(f);  
	  }
	  else if(3==weishu)
	  {
		  rs= df3.format(f);  
	  }
	  return rs;
	}
}
