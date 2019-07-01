<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html >
<base target="_self" />
  <head>
    
    
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<% 
	response.setHeader("Pragma","no-cache");
	response.setHeader("Cache-Control","no-cache");	
	response.setDateHeader("Expires", 0);	
	%>	
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
 </head>
  
<body onload="pageLoadBbsq()">
	<html:form action="/bbsqb">
      <div class="title">
        <div class="title_img" id="title_m">
                当前所在位置：日常事务 - 申请 - 补办申请
        </div>
      </div>
      <logic:present name="dors">
      		<logic:equal value="true" name="dors">
      			<script type="text/javascript">
      				alert("保存成功！");
      			</script>
      		</logic:equal>
      		<logic:equal value="false" name="dors">
      			<script type="text/javascript">
      				alert("保存失败！");
      			</script>
      		</logic:equal>
      </logic:present>
      <input type="hidden" id="bblx" name="bblx" value="<bean:write name="bblx"/>"/>
      <input type="hidden" id="url" name="url" value="/sjcz/bbsqb.jsp" />
      <input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-zymc-bjmc-xymc-lxdh" />
      	<div class="xxk"> 
					<logic:notEmpty name="pages">
						<logic:iterate id="card" name="pages" scope="request">
							<ul>
								<li id="<bean:write name='card' property='en'/>l"
									class="xxk_off_l"></li>
								<li id="<bean:write name='card' property='en'/>m"
									onclick="onclickPage(this)" class="xxk_off_m">
									&nbsp;
									<bean:write name='card' property='cn' />
									&nbsp;
								</li>
								<li id="<bean:write name='card' property='en'/>r"
									class="xxk_off_r"></li>
							</ul>							
						</logic:iterate>
				    </logic:notEmpty>					
		</div>
			    
		<%--学生证办理--begin--%>
		
      	<div name="xsz" id="xsz" style="display:none;">
      		<div align="center" style="font-size:16px">学生证补办申请表</div>
			<table width="98%" class="tbstyle" style="font-size:14px" name="xsz" id="xsz">
			  <tr>
			    <td width="7%" style="height:30"><div align="center"><font color="red">*</font>学号</div></td>
			    <td width="27%" colspan="2">
			    <logic:equal value="teacher" name="userOnLine" scope="session">
			    	<html:text name='rs' property="xh" styleId="xh" 
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
				</logic:equal>
				<logic:equal value="student" name="userOnLine" scope="session">
					<html:text name="rs" property="xh" style="width:95%"/>
				</logic:equal>				
			    </td>
			    <td width="7%"><div align="center">性别</div></td>
			    <td width="25%"><html:text name="rs" property="xb" style="width:95%" readonly="true"/></td>
			    <td width="9%"><div align="center">籍贯</div></td>
			    <td width="25%"><html:text name="rs" property="jg" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td style="height:30" ><div align="center">学制</div></td>
			    <td style="height:30" colspan="2"><html:text name="rs" property="xz" style="width:95%" readonly="true"/></td>
			    <td style="height:30"><div align="center">姓名</div></td>
			    <td style="height:30"><html:text name="rs" property="xm" style="width:95%" readonly="true"/></td>
			    <td style="height:30"><div align="center">入学时间</div></td>
			    <td style="height:30"><html:text name="rs" property="rxsj" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td height="30" colspan="2"><div align="center">专业与班级</div></td>
			    <td height="30" colspan="5"><html:text name="rs" property="zybj" style="width:95%" readonly="true"/></td>
			  </tr>
			  <tr>
			    <td height="30" colspan="2"><div align="center">家庭地址</div></td>
			    <td height="30" colspan="5"><html:text name="rs" property="jtdz" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td height="138"><div align="center">申<br>
			      请<br>
			      理<br>
			    由</div></td>
			    <td height="138" colspan="6"><html:textarea name="rs" property="sqly" cols="100" rows="5" style="height:100%"></html:textarea><br>
			    </td>
			  </tr>
			  
			</table>
     <p>&nbsp;&nbsp;&nbsp;&nbsp;注：领取时，请携带本单并上交</p>
      </div>
      <%--学生证办理--end--%>
      
      
      
      <%--一卡通办理--begin--%>
      <div name="ykt" id="ykt" style="display:none;">
			<div align="center" style="font-size:16px">一卡通办理申请表</div>
			<table width="98%" height="475" class="tbstyle" style="font-size:14px">
			  <tr>
			    <td width="18%" height="15%"><div align="center"><font color="red">*</font>学号</div></td>
			    <td width="30%" height="15%" colspan="2">
			    <logic:equal value="teacher" name="userOnLine" scope="session">
			    	<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
				</logic:equal>
			    <logic:equal value="student" name="userOnLine" scope="session">
			    	<html:text name="rs" property="xh" style="width:95%"/>
			    </logic:equal>	
			    </td>
			    <td width="12%" height="15%"><div align="center">姓名</div></td>
			    <td width="38%" height="15%"><html:text name="rs" property="xm" style="width:95%" readonly="true"/></td>
			  </tr>
			  <tr>
			    <td height="15%"><div align="center">班级</div></td>
			    <td height="15%" colspan="2"><html:text name="rs" property="bjmc" style="width:95%" readonly="true"/></td>
			    <td height="15%"><div align="center">联系方式</div></td>
			    <td height="15%"><html:text name="rs" property="lxfs" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td height="15%"><div align="center">证件号</div></td>
			    <td height="15%" colspan="2"><html:text name="rs" property="zjh" style="width:95%"/></td>
			    <td height="15%">&nbsp;</td>
			    <td height="15%">&nbsp;</td>
			  </tr>
			  <tr>
			    <td><div align="center">挂失情况</div></td>
			    <td colspan="4"><div align="center">
			      <html:radio property="gsqk" value="yes">&nbsp;&nbsp;&nbsp;&nbsp;已挂失&nbsp;&nbsp;</html:radio>
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  <html:radio property="gsqk" value="no">&nbsp;&nbsp;&nbsp;&nbsp;未挂失&nbsp;&nbsp;</html:radio>
			      </div></td>
			  </tr>
			  <tr>
			    <td height="27" rowspan="2">申请理由</td>
			    <td height="27" colspan="2"><p align="center">&nbsp;&nbsp;
			      <html:radio property="blqk" value="bb">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;补办</html:radio>
			    </td>
			    <td align=center>补办原因：</td>
			    <td><html:text name="rs" property="bbyy" style="width:98%"/></td>
			  </tr>
			  <tr>
			    <td height="27" colspan="2"><p align="center">&nbsp;&nbsp;
			        <html:radio property="blqk" value="xb">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新办</html:radio> 
			    </td>
			    <td align=center>新办原因：</td>
			    <td><html:text name="rs" property="xbyy" style="width:98%"/></td>
			  </tr>
			  <tr>
			    <td height="23"><div align="center">受理人</div></td>
			    <td height="23" colspan="2"><html:text name="rs" property="slr" style="width:95%"/></td>
			    <td height="23"><div align="center">工号</div></td>
			    <td height="23"><html:text name="rs" property="slrgh" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td height="15%"><div align="center">工作班次</div></td>
			    <td height="15%" colspan="2"><html:text name="rs" property="gzbc" style="width:95%"/></td>
			    <td height="15%"><div align="center">受理编号</div></td>
			    <td height="15%"><html:text name="rs" property="slbh" style="width:95%"/></td>
			  </tr>
			  <tr>
			    <td height="39" rowspan="2">受理意见</td>
			    <td height="39" colspan="4">&nbsp;&nbsp;<html:checkbox property="bbfys">
			      &nbsp;&nbsp;&nbsp;补办费已收 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:checkbox>
			      <html:checkbox property="xkbl">&nbsp;&nbsp;&nbsp;
			      新卡办理 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:checkbox>
			     <html:checkbox property="xxyhd">&nbsp;&nbsp;&nbsp;
			      信息已核对 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:checkbox>
			     <html:checkbox property="yclhzjxgbm">&nbsp;&nbsp;&nbsp;
			      已处理或转交相关部门</html:checkbox></td>
			  </tr>
			  <tr>
			    <td height="40" colspan="4" align="center" id="thtd">
			       <html:radio property="bjf" value="yes" onclick="var thistd=document.getElementById('thtd');thistd.colSpan='4';document.getElementById('yy1').style.display='none';document.getElementById('yy2').style.display='none';">&nbsp;&nbsp;已办结</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			       <html:radio property="bjf" value="yes" onclick="var thistd=document.getElementById('thtd');thistd.colSpan='1';document.getElementById('yy1').style.display='block';document.getElementById('yy2').style.display='block';">&nbsp;&nbsp;未办结 </html:radio> 
			    </td>
			    <td height="40" name="yy1" id="yy1" style="display: none" colspan="2" align="center">原因</td>
			    <td height="40" name="yy2" id="yy2" style="display: none"><html:text name="rs" property="yy" style="width:98%"/></td>
			  </tr>
			  <tr>
			    <td height="50" rowspan="3">发放情况</td>
			  </tr>
			  <tr>
			    <td height="23"><div align="center">代理人签名：</div></td>
			    <td height="23" colspan="2"><html:text name="rs" property="dlr" /></td>
			    <td height="23">&nbsp;</td>
			  </tr>
			  <tr>
			    <td height="23"><div align="center">代领人证件号码：</div></td>
			    <td height="23" colspan="3"><html:text name="rs" property="dlrzjh" style="width:80%"/></td>
			  </tr>
			  <tr>
			    <td>备注</td>
			    <td colspan="4"><html:textarea name="rs" property="yktbz" cols="80" rows="5" style="width:98%"/></td>
			  </tr>
			</table>
      </div>
    <%--一卡通办理--end--%>  
    
    
    <%--火车优惠卡办理--begin--%>  
      <div name="hcpyh" id="hcpyh" style="display:none;">
      		<div align="center" style="font-size:16px">火车优惠卡补办申请表<br>
  
			</div>
			<table width="98%" class="tbstyle" height="405" style="font-size:14px" name="hcpyh" id="hcpyh">
			  <tr>
			    <td width="14%"><div align="center"><font color="red">*</font>学号</div></td>
			    <td width="36%">
			    <logic:equal value="teacher" name="userOnLine" scope="session">
			    	<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
				</logic:equal>
			    <logic:equal value="student" name="userOnLine" scope="session">
			    	<html:text name="rs" property="xh" style="width:95%"/>
			    </logic:equal>
			    </td>
			    <td width="14%"><div align="center">姓名</div></td>
			    <td width="36%"><html:text name="rs" property="xm" /></td>
			  </tr>
			  <tr>
			    <td><div align="center">班级</div></td>
			    <td><html:text name="rs" property="bjmc" /></td>
			    <td><div align="center">火车终点站</div></td>
			    <td><html:text name="rs" property="hczdz" /></td>
			  </tr>
			  <tr>
			    <td colspan="1"><div align="center">家庭地址</div></td>
			    <td colspan="3"><html:text name="rs" property="jtdz" style="width:100%"/></td>
			  </tr>
			  <tr>
			    <td><div align="center">申请理由</div></td>
			    <td colspan="3"><html:textarea name="rs" property="sqly" cols="100" rows="10"></html:textarea> </td>
			  </tr>
			  <tr>
			    <td height="84">受理意见</td>
			    <td colspan="3">
			    
			      <p>&nbsp;&nbsp;&nbsp;
			        <input name="hdzj" type="checkbox" value="yes"> &nbsp;&nbsp;
				已核对身份证件（学生证）</p>
			    <p>&nbsp;&nbsp;&nbsp;
			      <input name="yzxtzhd" type="checkbox" value="yes"> &nbsp;&nbsp;
			      已在系统中核对，可发放
			</p>
			    <p>&nbsp;&nbsp;&nbsp;
			      <input name="ydj" type="checkbox" value="ydj"> &nbsp;&nbsp;
			已登记</p>
			    </td>
			  </tr>
			  <tr>
			    <td height="121">处理意见</td>
			    <td colspan="3"><p>&nbsp;&nbsp;&nbsp;
			        <input name="ff" type="checkbox" value="yes"> &nbsp;&nbsp;
			    &nbsp;发放，代收卡费柒元整</p>
			    <p>&nbsp;&nbsp;&nbsp;
			      <input name="qt" type="checkbox" value="yes"> &nbsp;&nbsp;
			    &nbsp;其他</p>
				</td>
			  </tr>
			  <tr>
			    <td>备注</td>
			    <td colspan="3"><html:textarea name="rs" property="hcpbz" cols="100" rows="5"/></td>
			  </tr>
			</table>
      </div>
      <%--火车优惠卡办理--end--%>
      </html:form>
    <div class="buttontool">
    	<button type="button" class="button2" onclick="chkNull2();document.forms[0].action='bbsqb.do?doType=save&bblx='+document.getElementById('bblx').value;document.forms[0].submit();">提交申请</button>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<button type="button" class="button2" onclick="document.forms[0].action='';document.forms[0].submit();">输出打印</button>
    </div>
</body>
</html:html>
