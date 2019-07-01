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
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
		
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/szdwfzjy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>当前所在位置：辅导员管理 - 思政考核 - 自评信息 </a>
				</p>
			</div>
			<div class="tab">
			<table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>辅导员自评信息填</span></th>
			        </tr>
			    </thead>
			 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button type="button" name="关闭"  id="buttonSave" onclick="szsxDataDoSave('szdwfzjy.do?method=saveZwpjb','');" > 保存 </button>
			          </div></td>
			      </tr>
			    </tfoot>
		
			<input type="hidden" id="zgh" name="zgh"
					value="<bean:write name="rs" property="zgh" scope="request"/>" />
					<tbody>
						<tr>
							<th align="right">
								姓名：
							</th>
							<td align="left">
								<bean:write name='rs' property="xm"/>
							</td>
							<th align="right">
								部门名称：
							</th>
							<td align="left">
								<bean:write  name='rs' property="bmmc"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								负责工作：
							</th>
							<td align="left">
								<html:text name='rs' property="fzgz" />
							</td>
							<th align="right">
								负责人数：
							</th>
							<td align="left">
								<html:text  name='rs' property="fzrs"/>
							</td>
						</tr>
						<tr>
							<th align="right" >
								个人年度小结：
							</th>
							<td align="left" colspan="3" >
								<html:textarea name = "rs" property="grndxj" style="width:99%" rows="15"/>
							</td>
						</tr>
						<tr>
							<th align="right" >
								减分（提供说明）：
							</th>
							<td align="left" colspan="3" >
								<html:textarea name = "rs" property="delfsm" style="width:99%"  rows="3" />
							</td>
						</tr>
						<tr>
							<th align="right" >
								加分（提供说明）：
							</th>
							<td align="left" colspan="3" >
								<html:textarea name = "rs" property="addfsm" style="width:99%"  rows="3" />
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								特色工作 ：
							</th>
							<td colspan="3" >
								<html:textarea name = "rs" property="tsgz" style="width:99%" rows="3" />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("修改成功！");
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("修改失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
