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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="正方软件 zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xsfwzdzx.js'></script>
	<script>
		function save(){
			refreshForm('bylfgl.do?method=saveBylfghdj');
		}
	</script>
	
	<base target="_self">
	<body>
		<html:form action="/bylfgl.do">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：日常事务 - 学生指导服务中心 - 毕业典礼服装归还登记 - 归还
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								<bean:message key="lable.xsgzyxpzxy" />毕业礼服归还信息
							</td>
						</tr>
					</thead>
					
					<tr>
						<td>
							<div align="right">
								年度：
							</div>
						</td>
						<td>
							${rs.nd}
							<html:hidden property="nd" name="rs"/>
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>				
							${rs.xymc}
							<html:hidden property="xydm" name="rs"/>
						</td>
					</tr>		
					<tr>						
						<td align="right">
							本科服：
						</td>
						<td>
							<b>xl</b>:${rs.bkfxl}
							<b>l</b>:${rs.bkfl}
							<b>m</b>:${rs.bkfm}
							<b>s</b>:${rs.bkfs}
						</td>
						<td align="right">
							归还本科服：
						</td>
						<td>
							<b>xl</b>:<html:text property="ghbkfxl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>l</b>:<html:text property="ghbkfl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>m</b>:<html:text property="ghbkfm" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>s</b>:<html:text property="ghbkfs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
						</td>				
					</tr>
					<tr>
						<td align="right">
							专科服：
						</td>
						<td>
							<b>xl</b>:${rs.zkfxl}
							<b>l</b>:${rs.zkfl}
							<b>m</b>:${rs.zkfm}
							<b>s</b>:${rs.zkfs}
						</td>
						<td align="right">
							归还专科服：
						</td>
						<td>
							<b>xl</b>:<html:text property="ghzkfxl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>l</b>:<html:text property="ghzkfl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>m</b>:<html:text property="ghzkfm" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>s</b>:<html:text property="ghzkfs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
						</td>											
					</tr>
					<tr>
						<td align="right">
							校长服：
						</td>
						<td>
							<b>xl</b>:${rs.xzfxl}
							<b>l</b>:${rs.xzfl}
							<b>m</b>:${rs.xzfm}
							<b>s</b>:${rs.xzfs}
						</td>
						<td align="right">
							归还校长服：
						</td>
						<td>
							<b>xl</b>:<html:text property="ghxzfxl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>l</b>:<html:text property="ghxzfl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>m</b>:<html:text property="ghxzfm" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>s</b>:<html:text property="ghxzfs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
						</td>	
					</tr>
					<tr>
						<td align="right">
							导师服：
						</td>
						<td>
							<b>xl</b>:${rs.dsfxl}
							<b>l</b>:${rs.dsfl}
							<b>m</b>:${rs.dsfm}
							<b>s</b>:${rs.dsfs}
						</td>
						<td align="right">
							归还导师服：
						</td>
						<td>
							<b>xl</b>:<html:text property="ghdsfxl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>l</b>:<html:text property="ghdsfl" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>m</b>:<html:text property="ghdsfm" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
							<b>s</b>:<html:text property="ghdsfs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs" style="width:60px"/>
						</td>												
					</tr>
					<tr>
						<td align="right">
							帽子：
						</td>
						<td>
							${rs.maozi}
						</td>
						<td align="right">
							归还帽子：
						</td>
						<td>
							<html:text property="ghmaozi" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							披肩：
						</td>
						<td>
							${rs.pijian}
						</td>
						<td align="right">
							归还披肩：
						</td>
						<td>
							<html:text property="ghpijian" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs"/>
						</td>		
					</tr>					
					<tr>
						<td align="right">
							领带：
						</td>
						<td>
							${rs.lingdai}
						</td>
						<td align="right">
							归还领带：
						</td>
						<td>
							<html:text property="ghlingdai" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							领结：
						</td>
						<td>
							${rs.lingjie}
						</td>
						<td align="right">
							归还领结：
						</td>
						<td>
							<html:text property="ghlingjie" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs"/>
						</td>					
					</tr>
					<tr>
						<td align="right">
							领取人：
						</td>
						<td>
							${rs.lqr}
						</td>
						<td align="right">
							损坏帽子：
						</td>
						<td>
							<html:text property="shmaozi" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs"/>
						</td>										
					</tr>
					<tr>
						<td align="right">
							损坏服装：
						</td>
						<td>
							<html:text property="shfz" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs"/>
						</td>
						<td align="right">
							损坏领结：
						</td>	
						<td>
							<html:text property="shlingjie" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs"/>
						</td>
					</tr>
					<tr>
							
						<td align="right">
							损坏领带：
						</td>
						<td>
							<html:text property="shlingdai" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') " name="rs"/>
						</td>
						<td align="right">
							赔偿金额：
						</td>
						<td>
							<html:text property="pcje" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') " name="rs"/>
						</td>
					</tr>
				</table>
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="save();return false;"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;"
							style="width:80px">
							关 闭
						</button>
					</div>
				</center>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("操作成功！");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}		
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("操作失败！");
					Close();
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
