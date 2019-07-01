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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>

	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>

	<script type="text/javascript">
    
     function jyxyblsh(){
      var xh = $("xh").value;
      var jyxybh = $("jyxybh").value;
      var bysj = $("bysj").value;
      var sqbg = $("sqbg").value;
     
      if(xh==""){
       alert("学号不能为空！");
       return false;
      }
     
      if(jyxybh==""){   
       alert("原就业协议编号不能为空！");
        return false;
      }
     
      if(bysj==""){   
       alert("请填写毕业时间！");
        return false;
      }
      
      if(sqbg==""){   
       alert("请简单填写申请补领原因！");
        return false;
      }
      
      BatAlert.showTips('正在提交，请等待...');
		document.forms[0].action = "jygl_xyblsq.do?doType=save";
		document.forms[0].submit();
      
     
     }
    
    
    
    </script>



	<body>
		<html:form action="/jygl_xyblsq">
			<br>
			<div align="center">
				<table width="80%" id="rsT" class="tbstyle">
					<tr style="height:22px">
						<td align="right" width="13%">
							姓名:
						</td>
						<td align="left" width="25%">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right" width="10%">
							性别：
						</td>
						<td align="left" width="13%">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right" width="13%">
							学历：
						</td>
						<td align="left" width="25%">
							<html:select name="rs" property="xl">
								<html:option value="本科"></html:option>
								<html:option value="专科"></html:option>
								<html:option value="硕士"></html:option>
								<html:option value="博士"></html:option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							学号:
						</td>
						<td align="left" colspan="2">
							<html:hidden name="rs" property="xh" />
							<bean:write name="rs" property="xh" />
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							毕业时间:
						</td>
						<td align="left" colspan="2">
							<html:text name="rs" style="cursor:hand; width:110px;"
								styleId="day1" property="bysj"
								onclick="return showCalendar('day1','y-mm-dd');" readonly="true" />
						</td>
						<td align="right">
							专业：
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							原协议书编号:
						</td>
						<td align="left" colspan="5">
							<html:hidden name="rs" property="jyxybh" />
							<bean:write name="rs" property="jyxybh" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							申请报告：
						</td>
						<td align="left" colspan="5">
							<html:textarea name="rs" property="sqbg" rows="15"
								style="width:100%" />
						</td>
					</tr>
				</table>
				<button onclick="jyxyblsh();" class="button2" style="width:70px">
					提交
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="reset" class="button2" style="width:70px">
					重置
				</button>
			</div>
		</html:form>

		<logic:notEmpty name="nojyxy">
			<logic:equal name="nojyxy" value="nojyxy">
				<script>
                      alert("你还未领取就业协议书，无法申请补领!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="nopower">
			<logic:equal name="nopower" value="nopower">
				<script>
                      alert("你不属于用户范围，该功能只面向学生开放!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("提交成功!");
                </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("提交失败!请检查是否重复提交");
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

