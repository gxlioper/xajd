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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		</script>
		<html:form action="/n05_xszz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 家庭情况调查 - 详细信息
				</div>
			</div>
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="7" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
						学号
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="center">
							出生日期
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							年级
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nj" />
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							专业名称
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc" />
					</td>
					<td>
						<div align="center">
							班级名称
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学制
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xz" />
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							入学前户口
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="rxqhk" />
					</td>
					<td>
						<div align="center">
							家庭人口数
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtrks" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							毕业学校
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="byxx" />
					</td>
					<td>
						<div align="center">
							个人特长
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="grtc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							孤残
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfgc" />
					</td>
					<td>
						<div align="center">
							单亲
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfdq" />
					</td>
					<td>
						<div align="center">
							烈士子女
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sflszn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭地址
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtdz" />
					</td>
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学生在校联系电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xszxlxdh" />
					</td>
					<td>
						<div align="center">
							家庭联系电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtlxdh" />
					</td>
				</tr>
				<tr>
					<td rowspan="6" width="4%">
						<div align="center">
							家
							<br />
							庭
							<br />
							成
							<br />
							员
							<br />
							情
							<br />
							况
						</div>
					</td>
					<td width="12%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="17%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="17%">
						<div align="center">
							与本人关系
						</div>
					</td>
					<td>
						<div align="center">
							工作（学习）单位
						</div>
					</td>
					<td width="17%">
						<div align="center">
							年收入（元）
						</div>
					</td>
					<td width="17%">
						<div align="center">
							健康状况
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td>
						&nbsp;
						<bean:write name="rs" property="jtcy1_nl" />
						&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_gz" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_sr" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_zk" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td>
						&nbsp;
						<bean:write name="rs" property="jtcy2_nl" />
						&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_gz" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_sr" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_zk" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td>
						&nbsp;
						<bean:write name="rs" property="jtcy3_nl" />
						&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_gz" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_sr" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_zk" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td>
						&nbsp;
						<bean:write name="rs" property="jtcy4_nl" />
						&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_gz" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_sr" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_zk" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy5_xm" />
					</td>
					<td>
						&nbsp;
						<bean:write name="rs" property="jtcy5_nl" />
						&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_gx" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_gz" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_sr" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_zk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭人均年收入
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtrjnsr" />
					</td>
					<td>
						<div align="center">
							家庭人均月收入
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtrjysr" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							学生本学年已获资助情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="bxnyhzzqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							本学年缴费情况
						</div>
					</td>
					<td colspan="5">
						①
						<logic:equal name='rs' property="sfjxf" value="否">
							√
						</logic:equal>
						<logic:notEqual name='rs' property="sfjxf" value="否">
							□
						</logic:notEqual>
						未缴学费&nbsp;&nbsp;
						<logic:equal name='rs' property="sfjxf" value="是">
							√
						</logic:equal>
						<logic:notEqual name='rs' property="sfjxf" value="是">
							□
						</logic:notEqual>
						已缴学费
						<u>&nbsp; <logic:empty name="rs" property="yjxf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty> <logic:notEmpty name="rs" property="yjxf">
								<bean:write name='rs' property="yjxf" />
							</logic:notEmpty> &nbsp;</u>元；②
						<logic:equal name='rs' property="sfjzsf" value="否">
							√
						</logic:equal>
						<logic:notEqual name='rs' property="sfjzsf" value="否">
							□
						</logic:notEqual>
						未缴住宿费&nbsp;&nbsp;
						<logic:equal name='rs' property="sfjzsf" value="是">
							√
						</logic:equal>
						<logic:notEqual name='rs' property="sfjzsf" value="是">
							□
						</logic:notEqual>
						已缴住宿费
						<u>&nbsp; <logic:empty name="rs" property="yjzsf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty> <logic:notEmpty name="rs" property="yjzsf">
								<bean:write name='rs' property="yjzsf" />
							</logic:notEmpty> &nbsp;</u>元。
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭提供生活费(元/月)
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jttgshf"/>
					</td>
					<td>
						<div align="center">
							在校需消费(元/月)
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zxxxf"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭遭受自然灾害情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtcszrzhqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭遭受突发意外事件
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtcstfywqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭成员因残疾、年迈而劳动能力弱情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtcyycjnmrndnlrqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭成员失业情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtcysyqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							家庭欠债情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtqzqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div align="center">
							<logic:equal name="xxdm" value="11078">
								是否低保户或一、二级残疾者,广州户籍的烈士家属、因公牺牲军人或疾故军人家属、低收入家庭
							</logic:equal>
							<logic:notEqual name="xxdm" value="11078">
								是否低保户或一、二级残疾者,烈士家属、因公牺牲军人或疾故军人家属、低收入家庭
							</logic:notEqual>
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfdbh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							其他情况
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="qtqk"/>
					</td>
				</tr>
<!-- 				<tr>
					<td colspan="2">
						<div align="center">
							民政部门详细通讯地址
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="mzbm_xxtxdz"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							邮政编码
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzbm_yzbm"/>
					</td>
					<td>
						<div align="center">
							当地最低生活保障(元/月)
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="ddzdshbz"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							经办人姓名
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jbrxm"/>
					</td>
					<td>
						<div align="center">
							经办人办公联系电话
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jbrbgdh_qx"/>
						-
						<bean:write name="rs" property="jbrbgdh_dh"/>
					</td>
				</tr>-->
				<tr>
					<td colspan="2">
						<div align="center">
							填写日期
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="txsj"/>
					</td>
					<td>
						<div align="center">
							&nbsp;
						</div>
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
