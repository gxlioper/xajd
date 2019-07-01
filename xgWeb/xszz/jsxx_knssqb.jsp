<%@ page language="java" contentType="text/html;charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />

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
<body>
	<html:form action="zxdksq.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
			<br>
			经济困难学生及家庭情况调查审定表
		</p>
		<table width="100%" border="1" class="tbstyle" id="theTable">
			<tr>
				<td colspan="9">
					<div align="center">
						系
						<u>&nbsp; <bean:write name='rs' property="zymc" /> &nbsp;</u>&nbsp;
						班级
						<u>&nbsp; <bean:write name='rs' property="bjmc" /> &nbsp;</u>&nbsp;
						姓名
						<u>&nbsp; <bean:write name='rs' property="xm" /> &nbsp;</u>&nbsp;
						性别
						<u>&nbsp; <bean:write name='rs' property="xb" /> &nbsp;</u>&nbsp;
						出生日期
						<u>&nbsp; <bean:write name='rs' property="csrq" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="5" scope="col" width="10%">
					<div align="center">
						学生本人基本情况
					</div>
				</td>
				<td scope="col" width="8%">
					<div align="center">
						家庭地址
					</div>
				</td>
				<td colspan="4" scope="col">
					<div align="center">
						<bean:write name='rs' property="jtdz" />
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						家庭电话
					</div>
				</td>
				<td colspan="2" scope="col">
					<div align="center">
						<bean:write name='rs' property="jtdh" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						身份证号
					</div>
				</td>
				<td colspan="4">
					<div align="center">
						<bean:write name='rs' property="sfzh" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />统编学号
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="xh" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="8%">
					<div align="center">
						政治面貌
					</div>
				</td>
				<td width="10%">
					<div align="center">
						<bean:write name='rs' property="zzmm" />
					</div>
				</td>
				<td width="10%">
					<div align="center">
						学生电话
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="xsdh" />
					</div>
				</td>
				<td width="15%">
					<div align="center">
						入学前户口
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="rxqhk" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						孤儿:
						<bean:write name='rs' property="sfgr" />
						,&nbsp; 残疾:
						<bean:write name='rs' property="sfcj" />
						,&nbsp; 单亲:
						<bean:write name='rs' property="sfdq" />
						,&nbsp; 烈士子女:
						<bean:write name='rs' property="sfjszn" />
						,&nbsp; 少数民族:
						<bean:write name='rs' property="sfssmz" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						无收入户:
						<bean:write name='rs' property="sfwsrh" />
						,&nbsp; 重病户:
						<bean:write name='rs' property="sfzbh" />
						,&nbsp; 低保户:
						<bean:write name='rs' property="sfdbh" />
						,&nbsp; 父母双下岗:
						<bean:write name='rs' property="sffmsxg" />
						,&nbsp; 纯农户:
						<bean:write name='rs' property="sfcnh" />
						,&nbsp; 低收入:
						<bean:write name='rs' property="sfdsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						入学成绩:
						<u> <bean:write name='rs' property="rxcj" />
						</u> 分,&nbsp; 上学期班内排名第
						<u> <bean:write name='rs' property="sxqpm" />
						</u> 名,&nbsp; 各课程成绩平均分:
						<u> <bean:write name='rs' property="pjcj" />
						</u> 分,&nbsp; 操行登第:
						<u> <bean:write name='rs' property="cxdd" />
						</u> 。
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="6" scope="row" width="10%">
					<div align="center">
						家庭人员
					</div>
				</td>
				<td width="8%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="10%">
					<div align="center">
						称谓
					</div>
				</td>
				<td width="10%">
					<div align="center">
						年龄
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						工作单位及职务
					</div>
				</td>
				<td width="10%">
					<div align="center">
						年收入
					</div>
				</td>
				<td width="10%">
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
						<bean:write name='rs' property="jtcy1_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;
						<bean:write name='rs' property="jtcy1_nl" />
						&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy1_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_jkzk" />
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
						<bean:write name='rs' property="jtcy2_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;
						<bean:write name='rs' property="jtcy2_nl" />
						&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy2_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_jkzk" />
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
						<bean:write name='rs' property="jtcy3_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;
						<bean:write name='rs' property="jtcy3_nl" />
						&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy3_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_jkzk" />
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
						<bean:write name='rs' property="jtcy4_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;
						<bean:write name='rs' property="jtcy4_nl" />
						&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy4_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_jkzk" />
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
						<bean:write name='rs' property="jtcy5_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;
						<bean:write name='rs' property="jtcy5_nl" />
						&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy5_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						家庭经济困难状况(糟灾,意外事故,父母病残,欠债,失业下岗,低保)
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="jtjjknqk" />
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						有关民政部门信息
					</div>
				</td>
				<td colspan="8">
					<div align="left">
						学生家庭所在民政部门意见(盖章):
					</div>
					<br />
					<div align="right">
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						邮政编码
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="mzbm_yzbm" />
					</div>
				</td>
				<td width="10%">
					<div align="center">
						联系电话
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="mzbm_lxdh" />
					</div>
				</td>
				<td>
					<div align="center">
						联系人
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="mzbm_lxr" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="3" scope="row">
					<div align="center">
						缴费情况
					</div>
				</td>
				<td colspan="8">
					<div align="left">
						每年本人和家庭提供费用
						<u> <bean:write name='rs' property="jfqk_jttg" />
						</u> 元,&nbsp; 其他亲友提供
						<u> <bean:write name='rs' property="jfqk_qtqytg" />
						</u> 元,&nbsp; 合计
						<u> <bean:write name='rs' property="jfqk_hjtg" />
						</u> 元
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						每年应缴各种费用
						<u> <bean:write name='rs' property="jfqk_yjfy" />
						</u> 元,&nbsp; 没月生活费
						<u> <bean:write name='rs' property="jfqk_mysffy" />
						</u> 元,&nbsp; 全年合计费用:
						<u> <bean:write name='rs' property="jfqk_qnhjfy" />
						</u> 元
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						本学年家庭提供费用不足数:
						<u> <bean:write name='rs' property="jfqk_bxnjttgfybzs" />
						</u> 元,&nbsp; 累计欠费:
						<u> <bean:write name='rs' property="jfqk_ljqf" />
						</u> 元,&nbsp; 有无缓缴计划:
						<u> <bean:write name='rs' property="jfqk_ywhjjh" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="5" scope="row">
					<div align="center">
						资助情况
					</div>
				</td>
				<td colspan="8">
					<div align="left">
						校内勤工助学:
						<u> <bean:write name='rs' property="zzqk_sfsqxnqgzx" />
						</u> ,&nbsp; 岗位:
						<u> <bean:write name='rs' property="zzqk_xnqgzxyapgw" />
						</u> 。&nbsp; 校外勤工助学:
						<u> <bean:write name='rs' property="zzqk_sfsqxwqgzx" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						本学年农村助学贷款:
						<u> <bean:write name='rs' property="zzqk_sfsbnczxdk" />
						</u> ,&nbsp; 历年已获农村助学贷款累计
						<u> <bean:write name='rs' property="zzqk_lnyhnczxdhje" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						本学年高校助学贷款:
						<u> <bean:write name='rs' property="zzqk_sfsbgxzxdk" />
						</u> ,&nbsp; 历年已获高校助学贷款累计
						<u> <bean:write name='rs' property="zzqk_lnyhgxzxdhje" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						本学年保险助学贷款:
						<u> <bean:write name='rs' property="zzqk_sfsbbxzxdk" />
						</u> ,&nbsp; 历年已获保险助学贷款累计
						<u> <bean:write name='rs' property="zzqk_lnyhbxzxdhje" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						历年已获奖、助学金等资助情况:
					</div>
					<logic:equal name="notJzxj" value="is">
						<div align="center">
							无
						</div>
					</logic:equal>
					<logic:equal name="notJzxj" value="no">
						<%
							@SuppressWarnings("unchecked")
								ArrayList<String> list = (ArrayList<String>) request
										.getAttribute("xszzList");
								for (Iterator<String> it = list.iterator(); it
										.hasNext();) {
									String temp = it.next();
						%>
								&nbsp;&nbsp;&nbsp;&nbsp;<%=temp%>
						<br />
						<%
						}
						%>
					</logic:equal>
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						班级评议班主任意见
					</div>
				</td>
				<td colspan="8">
					<div align="left">
						有无因什么违纪而受学校什么等级行政处分:无
						□、有(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						)
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						有无学生家庭所在地乡镇(街办)民政部门出具的家庭经济困难证明:有 □(应随付复印件) 无 □
					</div>
					<div align="left">
						消费水平:中以上 □、低 □、很低 □。班级评议、班主任查核意见:
					</div>
					<br />
					<div align="right">
						认定为:不困难 □,一般困难 □,特别困难 □
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 班主任签名
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />意见
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="xyshyj" />
					<br />
					<div align="right">
						认定为:不困难 □,一般困难 □,特别困难 □
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 负责人签名
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td height="17" scope="row">
					<div align="center">
						学校意见
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="xxshyj" />
					<br />
					<p align="right">
						认定为:不困难 □,一般困难 □,特别困难 □
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 负责人签名
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<div align="left">
						此表请於开学后即如实填写一式两份,系部、学工处各一份
					</div>
					<div align="right">
						填表日期:&nbsp;&nbsp;
						<bean:write name='rs' property="sqsj" />
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','江苏信息职业技术<bean:message key="lable.xsgzyxpzxy" />经济困难学生及家庭情况调查审定表')" />
	</div>
</body>
</html:html>
