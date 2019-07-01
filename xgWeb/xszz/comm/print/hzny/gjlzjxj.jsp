<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	<html:form action="/typj" method="post">
		<p align=center style='text-align:center;layout-grid-mode:char'><b><span style='font-size:18.0pt;font-family:
黑体'>国家励志奖学金申请审批表</span></b></p> 
  <span style='font-size:12.0pt;
font-family:宋体'>学校：${xxmc }&nbsp;&nbsp;<bean:message key="lable.xb" />：${rs.xymc }&nbsp;&nbsp;班级：${rs.bjmc }&nbsp;&nbsp;学号：${rs.xh }</span> 
 <table width="100%" class="printstyle" align="center">
    <tr> 
      <td width="7%" rowspan=4 style="height: 70px"> <p align=center style='text-align:center'><b><span style='font-family:宋体;"Times New Roman";"Times New Roman"'>本人<br/>情况</span></b></p></td> 
      <td width="11%" > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>姓名</span></p></td> 
      <td width="15%" colspan=2 align=center>&nbsp;${rs.xm }</td> 
      <td width="10%" > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>性别</span></p></td> 
      <td width="10%" align=center>&nbsp;${rs.xb }</td> 
      <td width=72 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>出生年月</span></p></td> 
      <td width="18%" colspan=2 align=center>&nbsp; ${rs.csrq }</td> 
      <td    rowspan=4 align=center> <p align=center style='text-align:center'><b><span style='font-family:宋体;"Times New Roman";"Times New Roman"'><img
												src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
												width="100" height="100" /></span></b></p></td> 
    </tr> 
    <tr> 
      <td width=84 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>民族</span></p></td> 
      <td width=100 colspan=2 align=center>&nbsp;${rs.mzmc } </td> 
      <td width=75 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>政治面貌</span></p></td> 
      <td width=69 align=center>&nbsp;${rs.zzmmmc } </td> 
      <td width=72 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>入学时间</span></p></td> 
      <td width=116 colspan=2 align=center>&nbsp; ${rs.rxrq }</td> 
    </tr> 
    <tr> 
      <td width=84 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>家庭住址</span></p></td> 
      <td width=244 colspan=4 align=center>&nbsp;${rs.jtdz } </td> 
      <td width=72 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>宿舍号码</span></p></td> 
      <td width=116 colspan=2 align=center>&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=84 > <p align=center style='text-align:center'><span
  style='font-size:9.5pt;font-family:宋体;"Times New Roman";"Times New Roman"'>身份证号码</span></p></td> 
      <td width=244 colspan=4 align=center>&nbsp;${rs.sfzh } </td> 
      <td width=72 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>联系电话</span></p></td> 
      <td width=116 colspan=2 align=center>&nbsp;${rs.lxdh } </td> 
    </tr> 
    <tr> 
      <td width=48 rowspan=3 > <p align=center style='text-align:center'><b><span style='font-family:宋体;"Times New Roman";"Times New Roman"'>学习<br/>情况</span></b></p></td> 
      <td width=623 colspan=9 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>该学年必修课程</span><u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u><span
  style='font-family:宋体;"Times New Roman"'>门，其中优秀</span><u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u><span
  style='font-family:宋体;"Times New Roman"'>门，良好</span><u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u><span
  style='font-family:宋体;"Times New Roman"'>门</span></p></td> 
    </tr> 
    <tr> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>本学年所获荣誉称号</span></p></td> 
      <td colspan=7 >&nbsp; </td> 
    </tr> 
    <tr> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>学习成绩、排名（名次/<br/>总人数）</span></p></td> 
      <td colspan=3 >&nbsp; </td> 
      <td colspan=2 align="center">综合测评成绩、排名<br/>（名次/总人数）</td> 
      <td colspan=2 >&nbsp; </td> 
    </tr> 
    <tr> 
      <td rowspan=2 > <p align=center style='text-align:center'><b><span style='font-family:宋体;"Times New Roman";"Times New Roman"'>家庭<br/>经济<br/>情况</span></b></p></td> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>家庭户口</span></p></td> 
      <td colspan=3 align="center">
		<logic:present name="rs" property="jthk">
			<logic:equal value="城镇" property="jthk" name="rs">
				城镇
			</logic:equal>
			<logic:equal value="农村" property="jthk" name="rs">
				农村
			</logic:equal>
		</logic:present>
		<logic:notPresent name="rs" property="jthk">
			A、城镇 B、农村
		</logic:notPresent>
      </td> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>家庭人口总数</span></p></td> 
      <td align="center" colspan="2">
		${rs.jtrs }
	</td>
    </tr> 
    <tr> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>家庭月总收入</span></p></td> 
      <td align="center" colspan="3">
		${rs.jtyzsr }
	</td>
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>人均月收入</span></p></td> 
      <td align="center" colspan="2">
		${rs.jtrjysr }
	</td>
    </tr> 
    <tr> 
      <td  rowspan=5 > <p align=center style='text-align:center'><b><span style='font-family:宋体;"Times New Roman";"Times New Roman"'>家庭<br/>成员<br/>情况</span></b></p></td> 
      <td  colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>姓名</span></p></td> 
      <td  colspan=3 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>年龄</span></p></td> 
      <td colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>与本人关系</span></p></td> 
      <td  colspan=2 > <p align=center style='text-align:center'><span
  style='font-family:宋体;"Times New Roman"'>工作（或学习）单位</span></p></td> 
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
		<tr>	
			<td colspan=2 align=center>
				<div align="center">
					<%=cyMap.get("cyxm")%>
				</div>
			</td>
			<td align=center  colspan=3>
				<div align="center">
					<%=cyMap.get("cynl")%>
				</div>
			</td>
			<td colspan=2 align=center>
				<div align="center">
					<%=cyMap.get("mc")%>
				</div>
			</td>
			<td colspan=2 align=center>
				<div align="center">
					<%=cyMap.get("cygzdw")%>
				</div>
			</td>
		</tr>
		<%
		}
		%> 
		 
		 
		<%
		
		for(int i=0;i<4-len;i++){
		%>
		<tr>
			 <td colspan=2 align=center>
				<div align="center">
					&nbsp;
				</div>
			</td>
			<td align=center  colspan=3>
				<div align="center">
					&nbsp;
				</div>
			</td>
			<td colspan=2 align=center>
				<div align="center">
					&nbsp;
				</div>
			</td>
			<td colspan=2 align=center>
				<div align="center">
					&nbsp;
				</div>
			</td>
		</tr>
		<%
		}
		%>
    <tr> 
      <td width=671 colspan=10 > <p align=center style='text-align:center'><b><span style='font-family:宋体;"Times New Roman";"Times New Roman"'>本人保证以上所填情况真实有效！</span></b></p> 
        <p align=center style='text-align:center'><b><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></b><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
  style='font-family:宋体;"Times New Roman"'>申请人签名：</span></p> 
        <p align=center style='text-align:center;'><span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span style='font-family:宋体;
  &quot;Times New Roman&quot;'>年</span><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:宋体;"Times New Roman"'>月</span><span lang=EN-US>&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:宋体;"Times New Roman"'>日</span></p></td> 
    </tr> 
    <tr> 
      <td style="height:135px"> <p align=center style='text-align:center'><b><span style='font-family:宋体;"Times New Roman";"Times New Roman"'>班级<br/>意见</span></b></p></td> 
      <td colspan=9 style="text-align: right;vertical-align: bottom">班长签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班主任签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
        <p  style='text-align:right;
  line-height:23.0pt;'>年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr> 
      <td style="height:135px"> <p align=center style='text-align:center'><b><span style='font-family:宋体;"Times New Roman";"Times New Roman"'><bean:message key="lable.xb" /><br/>意见</span></b></p></td> 
      <td  colspan=9 > <p style='  
  line-height:25.0pt;'><span
  style='font-size:12.0pt;font-family:宋体;text-indent: 35px'>通过审查，该生填报内容属实。经<bean:message key="lable.xb" />评选，并在全院范围内公示<u><span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u>天，无异议，现拟确定该同学为<span
  lang=EN-US>2010-2011</span>学年国家励志奖学金候选人。</span></p> 
        <p align="right">（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p> 
        <p align="right" >年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr> 
      <td style="height:135px"> <p align=center style='text-align:center'><b><span style='font-family:宋体;"Times New Roman";"Times New Roman"'>学校<br/>意见</span></b></p></td> 
      <td  colspan=9 > <p style='  
  line-height:25.0pt;'><span
  style='font-size:12.0pt;font-family:宋体;text-indent: 35px'>经评审，并在全校范围内公示<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u>天，无异议，现报请同意该同学获<span lang=EN-US>2010-2011</span>学年度国家励志奖学金。</span></p> 
        <p align="right"><span style='font-family:
  宋体;&quot;Times New Roman&quot;'>华中农业大学（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
        <p align="right"><span style='font-family:宋体;"Times New Roman";"Times New Roman"'>年</span><span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:宋体;"Times New Roman"'>月</span><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:宋体;"Times New Roman"'>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
    </tr> 
    <tr height=0> 
      <td width=48 ></td> 
      <td width=84 ></td> 
      <td width=76 ></td> 
      <td width=24 ></td> 
      <td width=75 ></td> 
      <td width=69 ></td> 
      <td width=72 ></td> 
      <td width=68 ></td> 
      <td width=48 ></td> 
      <td width=107 ></td> 
    </tr> 
  </table> 
  <p><span style='font-family:宋体;
&quot;Times New Roman&quot;'>说明：</span><span lang=EN-US>1.</span><span
style='font-family:宋体;"Times New Roman"'>课程成绩界定&#8212;优秀：</span><span lang=EN-US>85</span><span
style='font-family:宋体;"Times New Roman"'>（含）&#8212;</span><span lang=EN-US>100</span><span
style='font-family:宋体;"Times New Roman"'>分；良好：</span><span lang=EN-US>75</span><span
style='font-family:宋体;"Times New Roman"'>（含）&#8212;</span><span lang=EN-US>85</span><span style='font-family:
宋体;&quot;Times New Roman&quot;'>分。</span></p> 
  <p><span
lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.</span><span style='font-family:宋体;
&quot;Times New Roman&quot;'>学习成绩计算方法同综合测评，即必修课和专业选修课平均成绩</span><span
lang=EN-US>=</span><span style='font-family:宋体;
&quot;Times New Roman&quot;'>∑课程成绩×学分</span><span lang=EN-US>/</span><span
style='font-family:宋体;"Times New Roman"'>∑课程学分。</span></p> 
	</html:form>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</body>
</html:html>
