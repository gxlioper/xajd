<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="/style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commFunction.js"></script>
	<script type="text/javascript">
		function loadCard(){
			var kh=document.forms[0].kh.value;
			if(kh!=null&&kh!=""){
				for(var i=0;i<16;i++){
					document.getElementById("kh"+i).innerText=kh.substring(i,i+1);
					alert("kh"+i);
				}
			}
		}
		
		function sumTime(){
			var sum=0;
			var tem=0;
			var mxsbc=document.forms[0].mxsbc.value;
			for(var i=1;i<32;i++){
				tem=document.getElementById("sj"+i).value;
				sum=sum+tem*1;
			}
			document.getElementById("ljxs").innerText=sum;
			
			document.getElementById("zq").innerText=sum*mxsbc
		}
	</script>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body onload="loadCard();sumTime();"> 
    <html:form action="specialprise.do" method="post" >
    <input type="hidden" id="kh" name="kh" value="<bean:write name="rs" property="kh"/>"/>
    <input type="hidden" id="mxsbc" name="mxsbc" value="<bean:write name="mxsbc"/>"/>
<center>
<h3>北京联合大学学生勤工助学月考核表</h3>
<table width="100%" class="tbstyle" id="rsTable">
	<tr><td colspan="17">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月（&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日----&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日）</td> </tr>
	<tr height="35">
    <td align="center" width="60">学生姓名</td>
    <td colspan="8"><bean:write name="rs" property="xm"/></td>
    <td align="center" colspan="2" width="60">联系电话</td>
    <td colspan="6">&nbsp;</td>     
  </tr>
  <tr height="35">
    <td align="center" width="60">所在<bean:message key="lable.xsgzyxpzxy" /></td>
    <td colspan="5"><bean:write name="rs" property="xymc"/></td>  
    <td align="center" colspan="2" width="60">所在班级</td>
    <td colspan="5"><bean:write name="rs" property="bjmc"/></td>
    <td align="center" colspan="2" width="80">类别（本/专科）</td>
    <td colspan="2">&nbsp;</td>         
  </tr>
   <tr height="35">
    <td align="center" width="80">一卡通号码</td>
    <td width="30" id="kh1">&nbsp;&nbsp;&nbsp;</td> 
    <td width="30" id="kh2">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh3">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh4">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh5">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh6">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh7">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh8">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh9">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh10">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh11">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh12">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh13">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh14">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh15">&nbsp;&nbsp;&nbsp;</td>
    <td width="30" id="kh16">&nbsp;&nbsp;&nbsp;</td>
            
  </tr>
  <tr height="35">
    <td align="center">用人部门</td>
    <td colspan="8"><bean:write name="rs" property="yrdwmc"/></td>  
     <td align="center" colspan="2" width="60">联系电话</td>
    <td colspan="6"><bean:write name="rs" property="lxdh"/></td>        
  </tr>
   <tr height="35">
    <td align="center">工作内容</td>   
    <td colspan="16"><bean:write name="rs" property="xsgzqk"/></td>      
  </tr>
   <tr height="35">
    <td align="center" colspan="10">考核等级（优秀、良好、合格、不合格）</td>   
    <td colspan="7"><bean:write name="rs" property="gzbx"/></td>      
  </tr>  
   <tr height="25">
    <td align="center" colspan="17"> 学生月工作时间详情统计</td> 
  </tr> 
  <tr height="25">
    <td align="center"> 27</td> 
    <td align="center" colspan="2"> 28</td> 
    <td align="center" colspan="2"> 29</td> 
    <td align="center" colspan="3"> 30</td> 
    <td align="center" colspan="3"> 31</td> 
    <td align="center" colspan="3"> 1</td> 
    <td align="center" colspan="3">2</td> 
  </tr> 
  <tr height="45">
    <td align="center">
    	<html:select name = "rs" property="day27"  styleId="sj1" disabled="true">
    		<html:option value="0"> 0 </html:option> 
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="2">    
    	<html:select name = "rs" property="day28"  styleId="sj2" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="2">
    	<html:select name = "rs" property="day29"  styleId="sj3" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3">
    	<html:select name = "rs" property="day30"  styleId="sj4" disabled="true">
    		<html:option value="0"> 0 </html:option> 
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3"> 
    	<html:select name = "rs" property="day31"  styleId="sj5" disabled="true">
    		<html:option value="0"> 0 </html:option> 
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3">
    	<html:select name = "rs" property="day1"  styleId="sj6" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3">
    	<html:select name = "rs" property="day2"  styleId="sj7" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
  </tr> 
  <tr height="25">
    <td align="center">3</td> 
    <td align="center" colspan="2">4</td> 
    <td align="center" colspan="2">5</td> 
    <td align="center" colspan="3">6</td> 
    <td align="center" colspan="3">7</td> 
    <td align="center" colspan="3">8</td> 
    <td align="center" colspan="3">9</td> 
  </tr> 
  <tr height="45">
    <td align="center">
    	<html:select name = "rs" property="day3"  styleId="sj8" disabled="true">
    		<html:option value="0"> 0 </html:option> 
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="2">
    	<html:select name = "rs" property="day4"  styleId="sj9" disabled="true">
    		<html:option value="0"> 0 </html:option> 
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td>    
    <td align="center" colspan="2"> 
    	<html:select name = "rs" property="day5"  styleId="sj10" disabled="true">
    		<html:option value="0"> 0 </html:option> 
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3"> 
    	<html:select name = "rs" property="day6"  styleId="sj11" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3">
    	<html:select name = "rs" property="day7"  styleId="sj12" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3">
    	<html:select name = "rs" property="day8"  styleId="sj13" disabled="true">
    		<html:option value="0"> 0 </html:option> 
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3">
    	<html:select name = "rs" property="day9"  styleId="sj14" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
  </tr> 
  <tr height="25">
    <td align="center">10</td> 
    <td align="center" colspan="2">11</td> 
    <td align="center" colspan="2">12</td> 
    <td align="center" colspan="3">13</td> 
    <td align="center" colspan="3">14</td> 
    <td align="center" colspan="3">15</td>   
     <td align="center"  colspan="3">16</td>  
  </tr> 
  <tr height="45">
    <td align="center" > 
    	<html:select name = "rs" property="day10"  styleId="sj15" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="2"> 
    	<html:select name = "rs" property="day11"  styleId="sj16" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="2"> 
    	<html:select name = "rs" property="day12"  styleId="sj17" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3"> 
    	<html:select name = "rs" property="day13"  styleId="sj18" disabled="true">
    		<html:option value="0"> 0 </html:option> 
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3"> 
    	<html:select name = "rs" property="day14"  styleId="sj19" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3">
    	<html:select name = "rs" property="day15"  styleId="sj20" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3">
    	<html:select name = "rs" property="day16"  styleId="sj21" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
  </tr> 
  <tr height="25">
    <td align="center">17</td> 
    <td align="center" colspan="2">18</td> 
    <td align="center" colspan="2">19</td> 
    <td align="center" colspan="3">20</td> 
    <td align="center" colspan="3">21</td> 
    <td align="center" colspan="3">22</td> 
    <td align="center" colspan="3">23</td> 
  </tr> 
  <tr height="45">
    <td align="center"> 
    	<html:select name = "rs" property="day17"  styleId="sj22" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="2"> 
    	<html:select name = "rs" property="day18"  styleId="sj23" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="2"> 
    	<html:select name = "rs" property="day19"  styleId="sj24" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3"> 
    	<html:select name = "rs" property="day20"  styleId="sj25" disabled="true">
    		<html:option value="0"> 0 </html:option> 
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3"> 
    	<html:select name = "rs" property="day21"  styleId="sj26" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3">
    	<html:select name = "rs" property="day22"  styleId="sj27" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="3">
    	<html:select name = "rs" property="day23"  styleId="sj28" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
  </tr> 
  <tr height="25">
    <td align="center">24</td> 
    <td align="center" colspan="2">25</td> 
    <td align="center" colspan="2">26</td> 
    <td align="center" rowspan="2" colspan="12"> 月上岗时间累计<span id="ljxs">0</span>小时<br/>总钱<span id="zq">0</span>元</td>    
  </tr> 
   <tr height="45">
    <td align="center">
    	<html:select name = "rs" property="day23"  styleId="sj29" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="2">
    	<html:select name = "rs" property="day24"  styleId="sj30" disabled="true"> 
    		<html:option value="0"> 0 </html:option>
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td> 
    <td align="center" colspan="2">
    	<html:select name = "rs" property="day25"  styleId="sj31" disabled="true">
    		<html:option value="0"> 0 </html:option> 
    		<html:options collection="sjList" labelName="sj" property="sj"/>   		
    	</html:select> 
    </td>        
  </tr> 
  <tr height="75">
    <td align="center" colspan="5">考核责任人（签字）</td> 
    <td align="center" colspan="7"><br/><br/><br/>年&nbsp;&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;&nbsp;日</td> 
    <td align="center" colspan="5">部门审核(盖章)</td>        
  </tr> 
  <tr height="55">
    <td align="center" colspan="5">学生处审批<br/> 意见</td> 
    <td align="center" colspan="12"><br/><br/><br/><br/><br/>负责人签字（盖章）：<br/><p align="right">年 &nbsp;&nbsp;&nbsp;&nbsp;   月&nbsp;&nbsp;&nbsp;   日</p></td>           
  </tr> 
  <tr>
  <td>
  <p>
  填表须知：<br/>
  &nbsp;&nbsp;&nbsp;1.此表由用人部门如实填写（每人一式一份），不得缺项，不得涂改．<br/>
  &nbsp;&nbsp;&nbsp;2.部门没有填写考核等级的学生，视为放弃本学年勤工助学先进个人的评选资格；考核<br/>
  不合格,当月获80％的报酬，如一学期内连续两次月考核不合格，则取消该生的上岗资格．<br/>
  &nbsp;&nbsp;&nbsp;3.本考核表务必于次月３日前用A4纸打印上交，迟交的考核表将延迟至下一个月处理．
  
  </p>
  </td>
  </tr>
</table>
	<div class="buttontool" id="btu" width="100%">
	<button type="button" class="button2" onclick=" expTab('rsTable','北京联合大学学生勤工助学月考核表','')">
			打印
	</button>
	</div>
</center>
</html:form>
</body>
</html:html>
