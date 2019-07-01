<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:directive.page import="java.util.HashMap" />
		<jsp:directive.page import="java.util.List" />
		<jsp:directive.page import="java.util.ArrayList" />
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
		.noPrin{display:none;}
	</style>
		<!-- end -->
		<script type="text/javascript">
		
		</script>
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>

		<html:form action="/stu_info_add" method="post">

			<p align="center">
				<strong style="font-size:28px;font-family:黑体"><font><B>${xxmc}${rs.pjxn}学年<bean:write name="rs" property="xmlxmc"/>申报表</B>
				</font>
				</strong>
			</p>
			<p align="left" style="font-size:15px">
				学号:${rs.xh}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xb" />:${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申报类别:${rs.xmmc }
			</p>
			<table width="100%" id="rsT" class="printstyle">
				<tr height="40px" >
					<td align="center">
						姓名
					</td>
					<td align="center">
						&nbsp;${rs.xm }
					</td>
					<td align="center">
						专业
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.zymc }
					</td>
					<td align="center">
						班级
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.bjmc}
					</td>
					<td colspan="2" rowspan="3" align="center">
						<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
							width="96" height="128" />
					</td>
				</tr>
				<tr height="40px" >
					<td align="center">
						民族
					</td>
					<td align="center">
						${rs.mzmc}
					</td>
					<td align="center">
						性别
					</td>
					<td align="center">
						${rs.xb}
					</td>
					<td colspan="2" align="center">
						政治面貌
					</td>
					<td colspan="2" align="center">
						&nbsp;${rs.zzmmmc}
					</td>
				</tr>
				<tr  height="40px">
					<td colspan="5" align="center">
						评优学年担任职务(申报优干必须填写)
					</td>
					<td colspan="3" align="center">
						&nbsp;${rs.bjgb}
					</td>
				</tr>
				<tr  height='30px' >
					<td rowspan="6" align="center" valign="middle">
						第
						<br />
						一
						<br />
						学
						<br />
						期
						<br />
						成
						<br />
						绩
					</td>
					<td colspan="2"  width="140px" align="center" valign="middle">
						科目
					</td>
					<td align="center" valign="middle">
						成绩
					</td>
					<td  colspan="2" width="140px" align="center" valign="middle">
						科目
					</td>
					<td align="center"  valign="middle">
						成绩
					</td>
					<td colspan="2"  width="140px" align="center" valign="middle">
						科目
					</td>
					<td align="center" valign="middle">
						成绩
					</td>
				</tr>


				<%
				HashMap<String, Object> xscj = (HashMap<String, Object>) request.getAttribute("xscj");
				List<HashMap<String, String>> dyxqcjList = (ArrayList<HashMap<String, String>>) xscj.get("dyxqcj");
				String dyxq = "";
				for (int i = 1; i <= dyxqcjList.size(); i++) {
					HashMap<String, String> dyxqcjMap = dyxqcjList.get(i - 1);
					if(i<=15){
						if (i % 3 == 1  ) {
							dyxq += "<tr  height='30px' >";
						}
						if (i % 3 == 0) {
							dyxq += "<td colspan='2' width='140px'  align='center' valign='middle'>&nbsp;"
							+ dyxqcjMap.get("kcmc") + "</td><td align='center' valign='middle'>&nbsp;"
							+ dyxqcjMap.get("cj") + "</td></tr>";
						} else {
							dyxq += "<td colspan='2' width='140px'  align='center' valign='middle'>&nbsp;"
							+ dyxqcjMap.get("kcmc") + "</td><td align='center' valign='middle'>&nbsp;"
							+ dyxqcjMap.get("cj") + "</td>";
						}
					}
				}
				%>
				<%=dyxq%>

				<tr  height='30px'>
					<td rowspan="6" align='center' valign='middle'>
						第
						<br />
						二
						<br />
						学
						<br />
						期
						<br />
						成
						<br />
						绩
					</td>
					<td colspan="2" width="140px" align='center' valign='middle'>
						科目
					</td>
					<td align='center' valign='middle'>
						成绩
					</td>
					<td colspan="2" width="140px" align='center' valign='middle'>
						科目
					</td>
					<td align='center' valign='middle'>
						成绩
					</td>
					<td colspan="2" width="140px" align='center' valign='middle'>
						科目
					</td>
					<td align='center' valign='middle'>
						成绩
					</td>
				</tr>
				<%
						List<HashMap<String, String>> dexqcjList = (ArrayList<HashMap<String, String>>) xscj
						.get("dexqcj");
						String dexq = "";
						for (int i = 1; i <= dexqcjList.size(); i++) {
							if(i<=15){
								HashMap<String, String> dexqcjMap = dexqcjList.get(i - 1);
								
								if (i % 3 == 1 ) {
									dexq += "<tr height='30px' >";
								}
								if (i % 3 == 0) {
									dexq += "<td colspan='2' width='140px' align='center'  valign='middle'>&nbsp;"
									+ dexqcjMap.get("kcmc") + "</td><td align='center' valign='middle'>&nbsp;"
									+ dexqcjMap.get("cj") + "</td></tr>";
								} else {
									dexq += "<td colspan='2' width='140px' align='center' valign='middle'>&nbsp;"
									+ dexqcjMap.get("kcmc") + "</td><td align='center' valign='middle'>&nbsp;"
									+ dexqcjMap.get("cj") + "</td>";
								}
							}
						}
				%>
				<%=dexq%>
				<tr  height='40px'>
					<td rowspan="3" align='center' valign='middle'>
						综
						<br />
						合
						<br />
						测
						<br />
						评
					</td>
					<td align='center' valign='middle'>
						班级
						<br />
						人数
					</td>
					<td align='center' valign='middle'>
						总成绩
						<br />
						排名
					</td>
					<td align='center' valign='middle'>
						加权平均分/<br/>加权平均绩点
					</td>
					<td colspan="2" align='center' valign='middle'>
						综合测
						<br />
						评分数
					</td>
					<td colspan="2" align='center' valign='middle'>
						综合测
						<br />
						评名次
					</td>
					<td colspan="2" align='center' valign='middle'>
						补考科目数
						<br />
						(无补考者填无)
					</td>
				</tr>
				<tr  height='40px'>
					<td align='center' valign='middle'>
						&nbsp;${rs.pjbjrs }
					</td>
					<td align='center' valign='middle'>
						&nbsp;${rs.zyfnjzypm }
					</td>
					<td align='center' valign='middle'>
						&nbsp;${rs.jqpjf }/${rs.jqpjjd }
					</td>
					<td colspan="2" align='center' valign='middle'>
						&nbsp;${rs.zd1 }
					</td>
					<td colspan="2" align='center' valign='middle'>
						&nbsp;${rs.zcfbjpm }
					</td>
					<td colspan="2" align='center' valign='middle'>
						${rs.bkks }
					</td>
				</tr>
				<tr  height='40px'>
					<td colspan="3" align='center' valign='middle'>
						是否受过处分
					</td>
					<td align='center' valign='middle'>
						<logic:equal name="rs" property="sfwj" value="是">
							
							是
						</logic:equal>
						<logic:equal name="rs" property="sfwj" value="否">
							否
						</logic:equal>
					</td>
					<td colspan="3" align='center' valign='middle'>
						是否欠学费
					</td>
					<td colspan="2" align='center' valign='middle'>
						是○否○
					</td>
				</tr>
				<tr height='170px'>
					<td align='center' valign='middle'>
						<bean:message key="lable.xb" />
						<br />
						意见
					</td>
					<td colspan="4">
						<p align='left' valign="top">${rs.xyyj }</p>
						<br/><br/><br/><br/><br/>
						<p align='right' valign="top">审核人:
						<logic:empty name="rs" property="xyshr" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xyshr" >
							${rs.xyshr}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEmpty>
						<br/>
						
						<logic:empty name="rs" property="xynian" >
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xynian" >
							${rs.xynian}
						</logic:notEmpty>年
						<logic:empty name="rs" property="xyyue" >
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xyyue" >
							${rs.xyyue}
						</logic:notEmpty>月
						<logic:empty name="rs" property="xyri" >
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xyri" >
							${rs.xyri}
						</logic:notEmpty>日
						
						
					</td>
					<td align='center' valign='middle'>
						校审
						<br />
						<br />
						批意
						<br />
						<br />
						见
					</td>
					<td colspan="4" >
						<p align='left' valign="top">${rs.xxyj }</p>
						<br/><br/><br/><br/><br/>
						<p align='right' valign="top">
						审核人:
						<logic:empty name="rs" property="xxshr" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xxshr" >
							${rs.xxshr}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEmpty>
						<br/>
						<logic:empty name="rs" property="xxnian" >
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xxnian" >
							${rs.xxnian}
						</logic:notEmpty>年
						<logic:empty name="rs" property="xxyue" >
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xxyue" >
							${rs.xxyue}
						</logic:notEmpty>月
						<logic:empty name="rs" property="xxri" >
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xxri" >
							${rs.xxri}
						</logic:notEmpty>日
					</td>
				</tr>
				<tr>
					<td style="width:10%"></td>
					<td style="width:10%"></td>
					<td style="width:10%"></td>
					<td style="width:10%"></td>
					<td style="width:10%"></td>
					<td style="width:10%"></td>
					<td style="width:10%"></td>
					<td style="width:10%"></td>
					<td style="width:10%"></td>
					<td style="width:10%"></td>
				</tr>
			</table>
			
			<div align="center" class='noPrin'>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
					页面设置
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
					打印预览
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
					直接打印
				</button>
			</div>
		</html:form>
	</body>
</html>
