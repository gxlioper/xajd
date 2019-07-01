<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	      <style>
			.title{
			  font-family:"华文中宋";
			  font-size:24px;
			  font-weight:bolder
			}
			p{
				font-size:16px;
				font-family:"宋体";
				letter-spacing:2;
			}
			div{
			margin-left:2em;
			margin-right:2em;
			
			}
			.style{
				font-size: 14px;
				font-family: "仿宋_GB2312";
			}
			.wholeStyle{
			line-height:25px;			
			}
			.style7 {font-size: 16px}
			.stylebold{
			   font-weight: 900;
			}
		</style>
  </head>
  			<script type="text/javascript" src="/xgxt/dwr/interface/getNewStuBdd.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
    		<script type="text/javascript" src="/xgxt/js/yxglFunction.js"></script>
  <body>
            <input type="hidden" id="sfjyFlag" name="sfjyFlag" value="<bean:write name="rs" property="sfjy"/>">
			<div align="left" class="wholeStyle">
			<p align="center" class="title">北京林业大学“绿色通道”学生通知单</p>
			<p style="font-family:华文楷体;font-size: 18px">编号：<bean:write name='rs' property='lsh' /> 学号：<bean:write name='rs' property='xh' /> 
			<br/><bean:message key="lable.xsgzyxpzxy" />：<bean:write name='rs' property='xymc' /> 班级：<bean:write name='rs' property='bjmc' /> 姓名：<bean:write name='rs' property='xm' /></p>			
			<p style="font-family:'宋体'; text-indent: 2.5em;">由于你家庭经济困难，学校允许你通过“绿色通道”入学，并提醒你向你所在的<bean:message key="lable.xsgzyxpzxy" />团总支提出办理国家助学贷款申请，并了解学校的相关资助政策！
			<br/>经办人签字：</p> 
			<p class="style7" align="right" >北京林业大学学生资助管理中心
			<br/><bean:write name='rs' property='dqrq' /></p>
			<p style="font-family:'宋体'; text-indent: 1em;font-size:13px;"><bean:message key="lable.xsgzyxpzxy" />负责家庭经济困难学生工作老师联系方式：
			<br/>姓名：<bean:write name='rs' property='lsxm' />&nbsp;&nbsp;办公电话：<bean:write name='rs' property='lxdh' /> &nbsp;&nbsp;办公地点：<bean:write name='rs' property='bgdd' />
			<br/>&nbsp;&nbsp;&nbsp;&nbsp;国家助学贷款是指贷款人向借款人发放的由中央财政财政贴息，用于借款学生在国内高等学校就读所需学费、住宿费和生活费的助学贷款，实行一次申请、一次授信、分期发放的管理方式，贷款期限一般不超过10年，最长不超过借款学生毕业后六年，借款学生可在毕业后1—2年开始还款、6年内必须还清；借款学生申请贷款金额每人每学年不超过6000元；国家助学贷款实行借款人在校期间100%由中央财政贴息，借款人毕业后的贷款利息和违约罚息由其本人全额支付。 </p>
			<p style="font-family:'宋体'; text-indent: 1em;font-size:13px;">北京林业大学申请国家助学贷款需要准备的材料：
			<br/>1．本人身份证、户口本或户口迁移证明（复印在A4纸上）。 
			<br/>2．乡一级以上（含）的民政部门及县一级以上（含）教育主管部门关于其家庭经济困难的证明。
			<br/>3．父母（或法定监护人）双方的身份证及户口本复印件（复印在A4纸上，父母各一张）。
			<br/>4．未成年人须提供法定监护人书面同意其申请贷款的证明。
			<br/>-------------------------------------------------------------------</p>
			<p style="font-weight:bolder;text-indent: 4em;" align="center">北京林业大学“绿色通道”通知书 </p> 
			<p style="font-family:华文楷体;font-size: 18px">编号：<bean:write name='rs' property='lsh' /> 学号：<bean:write name='rs' property='xh' />
			<br/><bean:message key="lable.xsgzyxpzxy" />：<bean:write name='rs' property='xymc' /> 班级：<bean:write name='rs' property='bjmc' /> 姓名：<bean:write name='rs' property='xm' /></p>	
			<p class="style7">财务处：
			<br/>由于该生家庭经济困难，暂时不能缴纳：
			<br/>学  费:<bean:write name='rs' property='hyjxf' />元       住宿费：<bean:write name='rs' property='hyjqsf' />元
			<br/>&nbsp;&nbsp;&nbsp;&nbsp;学生本人已经提出缓交申请，请贵处先为其办理相关入学手续为谢。
			<br/>经办人签字：</p> 
			<p class="style7" align="right" >北京林业大学学生资助管理中心
			<br/><bean:write name='rs' property='dqrq' /></p>
			<img id="txm" src="/xgxt/BarcodeServlet?image=3&type=6&data=<bean:write name='rs' property='ksh' />&height=90&fontsize=20&barWidth=0.5" align="right" style="position:absolute;top:200;left:100;width:250px;height:80px"/>
			<img id="tss" src="/xgxt/BarcodeServlet?image=3&type=6&data=<bean:write name='rs' property='ksh' />&height=90&fontsize=20&barWidth=0.5" align="right" style="position:absolute;top:750;left:445;width:250px;height:80px"/>
			</div>
  </body>
</html:html>