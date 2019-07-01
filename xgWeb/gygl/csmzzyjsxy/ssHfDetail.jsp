<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>就业管理信息系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
    <script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
  		function xsryxx(){
  			if (curr_row==null || curr_row=='') {
  				alert('请选择要操作的数据行!');
  				return;
  			} else {
  				var url = 'csmz_gygl.do?method=ssrzxxView&pkValue=' + curr_row.cells[0].getElementsByTagName("input")[0].value;
  				url += '&ldmc=';
  				url += curr_row.cells[0].getElementsByTagName("input")[1].value;
  				showTopWin(url,'650','450');
  				return;
  			}
  		}
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/csmz_gygl" method="post">

			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 宿舍分配 - 宿舍划分
				</div>
			</div>			

			<logic:empty name="list">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="list">
				<fieldset>
					<legend>
						<font color="blue">&nbsp;&nbsp;&nbsp;宿舍划分详细信息;双击一行查看宿舍入住详情;</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
							</tr>
						</thead>
						<logic:iterate id="rs" name="list" offset="0">
						<logic:iterate id="s" name="rs" offset="0" indexId="index">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;" ondblclick="xsryxx();">
										<td align="center"><input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									    	<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>
									    	<input type="hidden" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
									    </td>
										<logic:iterate id="v" name="s" offset="2">
											<td>
												<div align="center">
													<bean:write name="v" />
												</div>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>



