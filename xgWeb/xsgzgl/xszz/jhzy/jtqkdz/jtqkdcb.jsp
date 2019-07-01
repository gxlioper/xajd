<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
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
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>

		<html:form action="/stu_info_add" method="post">
		<div align="center" style="font-size:28px;font:'黑体' "><b>金华职业技术学院学生及家庭情况调查表</b></div>
		<br>
		<div align="left" style="font-size:15px;">
		<b>
		<bean:message key="lable.xb" />：<u>${rs.xymc }</u>
		专业：<u>${rs.zymc }</u>
		年级：<u>${rs.nj }</u>
		</b>
		</div>
		<br>
		<table width="100%" id="rsT" class="printstyle">
			<!-- 学生本人基本情况 -->
			<!-- 第一行 -->
			<tr style="height:40px">
				<th width="8%" rowspan="4">
					<div align="center">
						学<br>
						生<br>
						本<br>
						人<br>
						基<br>
						本<br>
						情<br>
						况
					</div>
				</th>
				<th width="8%">
					<div align="center">
						姓 名
					</div>
				</th>
				<th width="20%" colspan="2">
					<div align="center">
						${rs.xm }
					</div>
				</th>
				<th width="8%">
					<div align="center">
						性别
					</div>
				</th>
				<th width="8%">
					<div align="center">
						${rs.xb }
					</div>
				</th>
				<th width="8%">
					<div align="center">
						出生<br/>年月
					</div>
				</th>
				<th width="12%">
					<div align="center">
						${rs.csrq }
					</div>
				</th>
				<th width="8%">
					<div align="center">
						民族
					</div>
				</th>
				<th width="12%" colspan="2">
					<div align="center">
						${rs.mzmc }
					</div>
				</th>
			</tr>
			<!-- 第二行 -->
			<tr style="height:40px">
				<th width="">
					<div align="center">
						身份证<br/>号码
					</div>
				</th>
				<th width="20%" colspan="3">
					<div align="center">
						${rs.sfzh }
					</div>
				</th>
				<th >
					<div align="center">
						政治<br/>面貌
					</div>
				</th>
				<th width="" colspan="1">
					<div align="center">
						${rs.zzmmmc }
					</div>
				</th>
				<th width="">
					<div align="center">
						入学前<br/>户口	
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						<logic:empty name="rs" property="jthk">
							□城镇&nbsp;&nbsp;
							□农村
						</logic:empty>
						<logic:equal name="rs" property="jthk" value="城镇">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							城镇&nbsp;&nbsp;
							□农村
						</logic:equal>
						<logic:equal name="rs" property="jthk" value="农村">
							□城镇&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							农村
						</logic:equal>	
					</div>
				</th>
			</tr>
			<!-- 第三行 -->
			<tr style="height:40px">
				<th width="">
					<div align="center">
						家庭人<br/>口数		
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						${rs.jtrks }
					</div>
				</th>
				<th width="">
					<div align="center">
						毕业<br/>学校					
					</div>
				</th>
				<th width="" colspan="1">
					<div align="center">
						${rs.byxx }
					</div>
				</th>
				<th width="" >
					<div align="center">
						个人<br/>特长
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						${rs.tc }
					</div>
				</th>
			</tr>
			<!-- 第四行 -->
			<tr style="height:40px">
				<th width="" >
					<div align="center">
						孤 残
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">					
						<logic:empty name="rs" property="sfgc">
							□是&nbsp;&nbsp;
							□否
						</logic:empty>
						<logic:equal name="rs" property="sfgc" value="是">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							是&nbsp;&nbsp;
							□否
						</logic:equal>
						<logic:equal name="rs" property="sfgc" value="否">
							□是&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							否
						</logic:equal>				
					</div>
				</th>
				<th width="">
					<div align="center">
						单 亲
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						<logic:empty name="rs" property="sfdq">
							□是&nbsp;&nbsp;
							□否
						</logic:empty>
						<logic:equal name="rs" property="sfdq" value="是">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							是&nbsp;&nbsp;
							□否
						</logic:equal>
						<logic:equal name="rs" property="sfdq" value="否">
							□是&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							否
						</logic:equal>		
					</div>
				</th>
				<th width="">
					<div align="center">
						烈士子女
					</div>
				</th>
				<th width="" colspan="3">
					<div align="center">
						<logic:empty name="rs" property="sflszn">
							□是&nbsp;&nbsp;
							□否
						</logic:empty>
						<logic:equal name="rs" property="sflszn" value="是">
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							是&nbsp;&nbsp;
							□否
						</logic:equal>
						<logic:equal name="rs" property="sflszn" value="否">
							□是&nbsp;&nbsp;
							<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							否
						</logic:equal>			
					</div>
				</th>
			</tr>
			<!-- 家庭通讯信息 -->
			<!-- 第一行 -->
			<tr style="height:40px">
				<th  rowspan="2">
					<div align="center">
						家庭<br>
						通讯<br>
						信息
					</div>
				</th>
				<th width="20%" colspan="2">
					<div align="center">
						详细通讯地址
					</div>
				</th>
				<th width="" colspan="8">
					<div align="center">
						${rs.jtdz }
					</div>
				</th>
			</tr>
			<!-- 第二行 -->
			<tr style="height:40px">
				<th colspan="2">
					<div align="center">
						邮政编码
					</div>
				</th>
				<th width="" colspan="2">
					<div align="center">
						${rs.jtyb }
					</div>
				</th>
				<th width="10%" >
					<div align="center">
						联系电话
					</div>
				</th>
				<th width="" colspan="5">
					<div align="center">
						<logic:notEmpty name="rs" property="jtdh" >
						${rs.jtdh }
						</logic:notEmpty>
						<logic:empty name="rs" property="jtdh" >
						（区号）－
						</logic:empty>
					</div>
				</th>
			</tr>
			
			
			<tr style="height:40px">
				<td rowspan=7>
					<p align=center style='text-align:center'>
						<b><span style='font-family:新宋体'>家<br/>庭<br/>成<br/>员<br/>情<br/>况</span> </b>
					</p>
				</td>
				<td align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>姓名</span>
					</p>
				</td>
				<td align=center width="12%">
					<p align=center style='text-align:center' >
						<span style='font-family:新宋体'>年龄</span>
					</p>
				</td>
				<td  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>与本人<br/>关系</span>
					</p>
				</td>
				<td colspan=3 align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>工作（学习）单位</span>
					</p>
				</td>
				<td width="6%" align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>职业</span>
					</p>
				</td>
				<td colspan=2 width=16% align=center>
					<p >
						<span style='font-family:新宋体'>年收入（元）</span>
					</p>
				</td>
				<td  width=8%  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:新宋体'>健康<br/>状况</span>
					</p>
				</td>
			</tr>
			<%
				ArrayList<HashMap<String,String>>cyList=(ArrayList<HashMap<String,String>>)request.getAttribute("cyList");
				int len=0;
				if(cyList!=null && cyList.size()>0){
					len=cyList.size();
				}
				
				for(int i=0;i<len;i++){
					HashMap<String,String>cyMap=cyList.get(i);
				%>
				<tr style="height:40px">
				 <td align=center>
						<div align="center">
							<%=cyMap.get("cyxm")==null ? "" : cyMap.get("cyxm")%>
						</div>
					</td>
					<td align=center>
						<div align="center">
							<%=cyMap.get("cynl")==null ? "" : cyMap.get("cynl")%>
						</div>
					</td>
					<td  align=center>
						<div align="center">
							<%=cyMap.get("cygx")==null ? "" : cyMap.get("cygx")%>
						</div>
					</td>
					<td colspan=3 align=center>
						<div align="center">
							<%=cyMap.get("cygzxxdw")==null ? "" : cyMap.get("cygzxxdw")%>
						</div>
					</td>
					<td  align=center>
						<div align="center">
							<%=cyMap.get("cyzy")==null ? "" : cyMap.get("cyzy")%>
						</div>
					</td>
					<td  colspan=2 width="10%" align=center>
						<div align="center">
							<%=cyMap.get("cynsr")==null ? "" : cyMap.get("cynsr")%>&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=1  align=center>
						<div align="center">
							<%=cyMap.get("cyjkzk")==null ? "" : cyMap.get("cyjkzk")%>
						</div>
					</td>
				</tr>
				<%
				}
				%> 
				 
				 
				<%
				
				for(int i=0;i<6-len;i++){
				%>
				<tr style="height:40px">
					<td  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan=3 align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td  colspan=2 width="10%" align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<%
				}
				%>
			<!-- 相关信息 -->
			<tr >
				<th width="5%">
					影响<br>
					家庭<br>
					经济<br>
					状况<br>
					有关<br>
					信息
				</th>
				<th colspan="10" align="left" style="height:200px;line-height: 25px">
				家庭人均年收入<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>（元）。学生本学年已获资助情况
				<U>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U><br/>                            
                <U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>。<br/>
                家庭遭受自然灾害情况：<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> 。
				家庭遭受突发意外事件：<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>。<br/>
				家庭成员因残疾、年迈而劳动能力减弱情况：<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>。<br/>
				家庭成员失业情况：<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>。家庭欠债情况：<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>。<br/>
				其他情况：<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> 。<br/>
				</th>
			</tr>
			<!-- 相关信息 -->
			<tr style="height:120px">
				<th width="8%">
					签<br>
					章<br>
				</th>
				<th width="">
					学生本<br>
					人签字<br>
				</th>
				<th width="">

				</th>
				<th width="">
					学生家<br>
					长或监<br>
					护人签<br>
					字&nbsp;&nbsp;&nbsp;&nbsp;
				</th>
				<th width="">

				</th>
				<th >
					学生家庭<br>
					所在地乡<br>
					镇或街道<br>
					民政部门<br>
					意见&nbsp;&nbsp;&nbsp;&nbsp;
				</th>
				<th width="" colspan="5">	
					<div align="left">		
						当地低保标准：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元/人.月
						<br>
						经办人签字：
						<br>
						单位名称：
						<br>
					</div>
					<div align="left">
						（加盖公章）<br>
						 <U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>年<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>月<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>日
					</div>
				</th>
			</tr>
			<!-- 民政部门信息 -->
			<tr style="height:40px">
				<th width="5%" rowspan="2">
					民政<br>
					部门<br>
					信息<br>
				</th>
				<th width="" colspan="2">
					详细通讯地址
				</th>
				<th width="" colspan="8">

				</th>
			</tr>
			<tr style="height:40px">
				<th width="" colspan="2">
					邮政编码
				</th>
				<th width="" colspan="2">

				</th>
				<th width="" colspan="2">
					联系电话
				</th>
				<th width="" colspan="4">
				（区号）－
				</th>
			</tr>
		</table>
		<div align="left">
		注：低保户、烈士家庭、五保户、残疾学生等附证明文件复印件。
		</div>
		<br>
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
