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
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title></title>
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
		<html:form action="/stgl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>社会工作 - 社团评优 - 社团评优权重设置</a>
				</p>
			</div>
			
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <tfoot>
			      <tr>
			        <td colspan="3"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						<button class=""
						onclick="qzConfDoSave('stgl.do?method=saveStPyQz');" id="buttonSave">
						保存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="" onclick="Close();return false;" 
						id="buttonClose">
						关闭
					</button>
					</div>
				</td>
			      </tr>
			    </tfoot>
			    <thead>
			    	<tr>
			        	<th colspan="3"><span>社团评优权重设置</span></th>
			        </tr>
			    </thead>
					<tbody>
	
								<tr align="center" style="cursor:hand">
										<td id="xmdm" nowrap align="center">
											社团评优项目编号
										</td>
										<td id="xmdm" nowrap align="center">
											社团评优项目名称
										</td>
										<td id="xmdm" nowrap align="center">
											所占权重
										</td>
								</tr>
							<logic:iterate name="rs" id="s">
								<tr style="cursor:hand">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" name="mkdm" value="<bean:write name='v'/>" scope="request" />
									</td>
										<logic:iterate id="v" name="s" offset="1" length="1">
										<td align="center">
											<bean:write name="v" />
										</td>
										</logic:iterate>
									<logic:iterate id="v" name="s" offset="2">
										<td align="center">
											<input type="text"  name="qz" value="<bean:write name='v'/>" scope="request" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
