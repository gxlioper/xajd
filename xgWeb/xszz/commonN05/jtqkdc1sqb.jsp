<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/n05_xszz.do?method=jtqkdc1sq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> 
								<bean:write name='rs' property="xxmc" />学生家庭经济情况调查表
							</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="4%" rowspan="6">
								<div align="center">
									学
									<br />
									生
									<br />
									本
									<br />
									人
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
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									性别
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									出生年月
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									民族
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									班级
								</div>
							</td>
							<td colspan="2">
								<logic:empty name="rs" property="nj">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="nj">
									<bean:write name='rs' property="nj" />
								</logic:notEmpty>
								级
								<logic:empty name="rs" property="bjmc">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="bjmc">
									<bean:write name='rs' property="bjmc" />
								</logic:notEmpty>
								班
							</td>
							<td>
								<div align="center">
									学制
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学号
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									入学前户口
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name='rs' property="rxqhk" value="城镇">
										√
									</logic:equal>
									<logic:notEqual name='rs' property="rxqhk" value="城镇">
										□
									</logic:notEqual>
									城镇&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="rxqhk" value="农村">
										√
									</logic:equal>
									<logic:notEqual name='rs' property="rxqhk" value="农村">
										□
									</logic:notEqual>
									农村
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号码
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									家庭人口数
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									毕业学校
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="byxx" />
								</div>
							</td>
							<td>
								<div align="center">
									个人特长
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="grtc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									孤残
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name='rs' property="sfgc" value="是">
										√
									</logic:equal>
									<logic:notEqual name='rs' property="sfgc" value="是">
										□
									</logic:notEqual>
									是&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="sfgc" value="否">
										√
									</logic:equal>
									<logic:notEqual name='rs' property="sfgc" value="否">
										□
									</logic:notEqual>
									否
								</div>
							</td>
							<td>
								<div align="center">
									单亲
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name='rs' property="sfdq" value="是">
										√
									</logic:equal>
									<logic:notEqual name='rs' property="sfdq" value="是">
										□
									</logic:notEqual>
									是&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="sfdq" value="否">
										√
									</logic:equal>
									<logic:notEqual name='rs' property="sfdq" value="否">
										□
									</logic:notEqual>
									否
								</div>
							</td>
							<td>
								<div align="center">
									烈士子女
								</div>
							</td>
							<td>
								<div align="center">
									<logic:equal name='rs' property="sflszn" value="是">
										√
									</logic:equal>
									<logic:notEqual name='rs' property="sflszn" value="是">
										□
									</logic:notEqual>
									是&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="sflszn" value="否">
										√
									</logic:equal>
									<logic:notEqual name='rs' property="sflszn" value="否">
										□
									</logic:notEqual>
									否
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									家
									<br />
									庭
									<br />
									信
									<br />
									息
								</div>
							</td>
							<td>
								<div align="center">
									家庭地址
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="jtdz" />
								</div>
							</td>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									学生在校
									<br />
									联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="xszxlxdh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									家庭联系电话
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtlxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6">
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
							<td>
								<div align="center">
									姓名
								</div>
							</td>
							<td>
								<div align="center">
									年龄
								</div>
							</td>
							<td>
								<div align="center">
									与本人关系
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									工作(学习)情况
								</div>
							</td>
							<td>
								<div align="center">
									年收入(元)
								</div>
							</td>
							<td>
								<div align="center">
									健康状况
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy1_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy1_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_zk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy2_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy2_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_zk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy3_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy3_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_zk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy4_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy4_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_zk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name='rs' property="jtcy5_nl" />
									&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_gx" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtcy5_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_sr" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_zk" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									影
									<br />
									响
									<br />
									家
									<br />
									庭
									<br />
									经
									<br />
									济
									<br />
									状
									<br />
									况
									<br />
									有
									<br />
									关
									<br />
									信
									<br />
									息
								</div>
							</td>
							<td colspan="8">
								<p>
									家庭人均年收入
									<u>&nbsp;
									<logic:empty name="rs" property="jtrjnsr">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtrjnsr">
										<bean:write name='rs' property="jtrjnsr" />
									</logic:notEmpty>
									&nbsp;</u>（元），家庭人均月收入
									<u>&nbsp;
									<logic:empty name="rs" property="jtrjysr">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtrjysr">
										<bean:write name='rs' property="jtrjysr" />
									</logic:notEmpty>
									&nbsp;</u>（元）。
								</p>
								<p>
									学生本学年已获资助情况：
									<u>&nbsp;
									<logic:empty name="rs" property="bxnyhzzqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="bxnyhzzqk">
										<bean:write name='rs' property="bxnyhzzqk" />
									</logic:notEmpty>
									&nbsp;</u>。
								</p>
								<p>
									本学年缴费情况：①
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
									<u>&nbsp;
									<logic:empty name="rs" property="yjxf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="yjxf">
										<bean:write name='rs' property="yjxf" />
									</logic:notEmpty>
									&nbsp;</u>元；②
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
									<u>&nbsp;
									<logic:empty name="rs" property="yjzsf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="yjzsf">
										<bean:write name='rs' property="yjzsf" />
									</logic:notEmpty>
									&nbsp;</u>元。
								</p>
								<p>
									家庭提供生活费
									<u>&nbsp;
									<logic:empty name="rs" property="jttgshf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jttgshf">
										<bean:write name='rs' property="jttgshf" />
									</logic:notEmpty>
									&nbsp;</u>元 /
									月，在校需消费
									<u>&nbsp;
									<logic:empty name="rs" property="zxxxf">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="zxxxf">
										<bean:write name='rs' property="zxxxf" />
									</logic:notEmpty>
									&nbsp;</u>元 / 月。
								</p>
								<p>
									家庭遭受自然灾害情况：
									<u>&nbsp;&nbsp;
									<logic:empty name="rs" property="jtcszrzhqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcszrzhqk">
										<bean:write name='rs' property="jtcszrzhqk" />
									</logic:notEmpty>
									&nbsp;&nbsp;</u>。
								</p>
								<p>
									家庭遭受突发意外事件：
									<u>&nbsp;&nbsp;
									<logic:empty name="rs" property="jtcstfywqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcstfywqk">
										<bean:write name='rs' property="jtcstfywqk" />
									</logic:notEmpty>
									&nbsp;&nbsp;</u>。
								</p>
								<p>
									家庭成员因残疾、年迈而劳动能力弱情况：
									<u>&nbsp;&nbsp;
									<logic:empty name="rs" property="jtcyycjnmrndnlrqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcyycjnmrndnlrqk">
										<bean:write name='rs' property="jtcyycjnmrndnlrqk" />
									</logic:notEmpty>
									&nbsp;&nbsp;</u>。
								</p>
								<p>
									家庭成员失业情况：
									<u>&nbsp;
									<logic:empty name="rs" property="jtcysyqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtcysyqk">
										<bean:write name='rs' property="jtcysyqk" />
									</logic:notEmpty>
									&nbsp;</u>。家庭欠债情况：
									<u>&nbsp;
									<logic:empty name="rs" property="jtqzqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jtqzqk">
										<bean:write name='rs' property="jtqzqk" />
									</logic:notEmpty>
									&nbsp;</u>。
								</p>
								<p>
									<logic:equal name="xxdm" value="11078">
										是否低保户或一、二级残疾者,广州户籍的烈士家属、因公牺牲军人或疾故军人家属、低收入家庭
									</logic:equal>
									<logic:notEqual name="xxdm" value="11078">
										是否低保户或一、二级残疾者,烈士家属、因公牺牲军人或疾故军人家属、低收入家庭
									</logic:notEqual>
									<u>&nbsp;
									<logic:empty name="rs" property="sfdbh">
										&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="sfdbh">
										<bean:write name='rs' property="sfdbh" />
									</logic:notEmpty>
									&nbsp;</u> 。
								</p>
								<p>
									其他情况：
									<u>&nbsp;&nbsp;
									<logic:empty name="rs" property="qtqk">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="qtqk">
										<bean:write name='rs' property="qtqk" />
									</logic:notEmpty>
									&nbsp;&nbsp;</u>。
								</p>
								<div align="center">
									本人承诺上述情况属实，如有虚假，本人愿意承担一切后果。
								</div>
								<br />
								<div align="center">
									学生家长或监护人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学生签名：
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" rowspan="4">
								<div align="center">
									学生家庭
									<br />
									所在地乡
									<br />
									镇或街道
									<br />
									民政部门
									<br />
									信息
								</div>
							</td>
							<td>
								<div align="center">
									通讯地址
								</div>
							</td>
							<td colspan="6">
								<div align="center">
									<bean:write name='rs' property="mzbm_xxtxdz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									邮政编码
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="mzbm_yzbm" />
								</div>
							</td>
							<td colspan="3" rowspan="3">
								<div align="left">
									<strong>当地最低生活保障<u>&nbsp;&nbsp;
									<logic:empty name="rs" property="ddzdshbz">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="ddzdshbz">
										<bean:write name='rs' property="ddzdshbz" />
									</logic:notEmpty>
									&nbsp;&nbsp;</u>元/月。</strong>
								</div>
								<br />
								单位名称：
								<div align="center">
									(盖章)
								</div>
								<br />
								<div align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									经办人姓名
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jbrxm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									经办人办公
									<br />
									联系电话
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<u>&nbsp;
									<logic:empty name="rs" property="jbrbgdh_qx">
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jbrbgdh_qx">
										<bean:write name='rs' property="jbrbgdh_qx" />
									</logic:notEmpty>
									&nbsp;</u>(区号)
									<u>&nbsp;
									<logic:empty name="rs" property="jbrbgdh_dh">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="jbrbgdh_dh">
										<bean:write name='rs' property="jbrbgdh_dh" />
									</logic:notEmpty>
									&nbsp;</u>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
</html:html>
