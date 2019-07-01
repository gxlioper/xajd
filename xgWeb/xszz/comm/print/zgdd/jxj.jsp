<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//Dth  HTML 4.01 Transitional//EN">
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
<div align="center"> 
  <p align=center><b><span
style='font-size:22.0pt;font-family:宋体;letter-spacing:1.0pt'>（&nbsp;${rs.xn }学年）${rs.xmmc }申请审批表</span></b></p> 
  <p align=center><font size="3"><b>学校：${rs.xxmc }
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
院系：${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
学号：${rs.xh }</b></font></p> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table class="printstyle" border="0" align="center" style="width: 90%"> 
    <tr> 
      <th  width=14% rowspan=4><p align=center>基<br/>本<br/>情<br/>况</p></th > 
      <th  width=14%> <p align=center>姓名</p></th > 
      <th  width=20% colspan="5">${rs.xm } </th > 
      <th  width=12% colspan="3"> <p align=center>性别</p></th > 
      <th  width=12% colspan="3">${rs.xb } </th > 
      <th  width=12% colspan="3"> <p align=center>出生年月</p></th > 
      <th  width=16% colspan="4">${rs.csrq } </th > 
    </tr> 
    <tr> 
      <th> <p align=center>政治面貌</p></th > 
      <th  colspan="5">${rs.zzmmmc } </th > 
      <th  colspan="3"> <p align=center>民族</p></th > 
      <th  colspan="3">${rs.mzmc } </th > 
      <th  colspan="3"> <p align=center>入学时间</p></th > 
      <th  colspan="4">${rs.rxrq } </th > 
    </tr> 
    <tr> 
      <th> <p align=center>专业</p></th > 
      <th  colspan="5">${rs.zymc } </th > 
      <th  colspan="3"> <p align=center>学制</p></th > 
      <th  colspan="3">${rs.xz } </th > 
      <th  colspan="3"> <p align=center>联系电话</p></th > 
      <th  colspan="4">${rs.lxdh } </th > 
    </tr> 
   <tr> 
      <th > <p>身份证号</p></th >
      <th  id="s0">&nbsp; </th >  
      <th  id="s1">&nbsp; </th > 
      <th  id="s2">&nbsp; </th > 
      <th  id="s3">&nbsp; </th > 
      <th  id="s4">&nbsp; </th > 
      <th  id="s5">&nbsp; </th > 
      <th  id="s6">&nbsp; </th > 
      <th  id="s7">&nbsp; </th > 
      <th  id="s8">&nbsp; </th > 
      <th  id="s9">&nbsp; </th > 
      <th  id="s10">&nbsp; </th > 
      <th  id="s11">&nbsp; </th > 
      <th  id="s12">&nbsp; </th > 
      <th  id="s13">&nbsp; </th > 
      <th  id="s14">&nbsp; </th > 
      <th  id="s15">&nbsp; </th > 
      <th  id="s16">&nbsp; </th > 
      <th  id="s17">&nbsp; </th > 
   	</tr>
    <tr> 
      <th rowspan="2">
      	<p align=center>学习<br/>情况<br/></p> 
	  </th > 
      <th colspan="9">
      	成绩排名：${rs.bjpm }/${rs.bjrs }（名次/总人数）
      </th > 
      <th colspan="10">
      	实行综合考评排名：是
      	<logic:notEqual name="rs" property="sxzhpm" value="是">
			□
		</logic:notEqual>
		<logic:equal name="rs" property="sxzhpm" value="是">
			<img src="/xgxt/pictures/xszz/gou.jpg"></img>
		</logic:equal>；
      	否
      	<logic:notEqual name="rs" property="sxzhpm" value="否">
			□
		</logic:notEqual>
		<logic:equal name="rs" property="sxzhpm" value="否">
			<img src="/xgxt/pictures/xszz/gou.jpg"></img>
		</logic:equal>
	  </th > 
    </tr> 
    <tr> 
      <th colspan="9">
      	必修课${rs.bxkms }门，其中及格以上${rs.jgysms }门
      </th > 
      <th colspan="10">
      	如是，排名：${rs.zycjpm }/${rs.bjrs }（名次/总人数）
      </th > 
    </tr> 
    <tr> 
      <th rowspan="5"> <p align=center>大学<br/>期间<br/>获奖<br/>情况</p></th> 
      <th colspan="5"><p align=center>日期</p></th > 
      <th colspan="5"> <p align=center>奖项名称</p></th > 
      <th colspan="9"> <p align=center>颁奖单位</p></th > 
    </tr> 
    <tr> 
      <th colspan="5">${rs.hjrq1}&nbsp;&nbsp;</th > 
      <th colspan="5">${rs.hjmc1}&nbsp;&nbsp;</th > 
      <th colspan="9">${rs.bjdw1}&nbsp;&nbsp;</th > 
    </tr> 
    <tr> 
      <th colspan="5">${rs.hjrq2}&nbsp;&nbsp;</th > 
      <th colspan="5">${rs.hjmc2}&nbsp;&nbsp;</th > 
      <th colspan="9">${rs.bjdw2}&nbsp;&nbsp;</th > 
    </tr> 
    <tr> 
      <th colspan="5">${rs.hjrq3}&nbsp;&nbsp;</th > 
      <th colspan="5">${rs.hjmc3}&nbsp;&nbsp;</th > 
      <th colspan="9">${rs.bjdw3}&nbsp;&nbsp;</th > 
    </tr> 
    <tr> 
      <th colspan="5">${rs.hjrq4}&nbsp;&nbsp;</th > 
      <th colspan="5">${rs.hjmc4}&nbsp;&nbsp;</th > 
      <th colspan="9">${rs.bjdw4}&nbsp;&nbsp;</th > 
    </tr> 
    <tr> 
      <th >
      	<p align=center>科研<br/>情况<br/>及发<br/>表论文<br/></p> 
	  </th > 
      <th colspan="19">
			<p style="height: 280px;" align="left">
				${rs.kyqk }
			</p>
      </th > 
    </tr> 
    <tr> 
      <th> <p align=center>
      	        申<br/>
       	        请<br/> 
		        理<br/> 
		        由<br/> 
		     </p></th > 
      <th  colspan="19">
     		<p style="height: 150px;" align="left">
					${rs.sqsm }
			</p>
				<div align="right">
					申请人签名(手签)：
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;
					<br>
					年&nbsp;&nbsp;&nbsp;&nbsp;
					月&nbsp;&nbsp;&nbsp;&nbsp;
					日
		</div>
    </th > 
    </tr> 
    </table>   
    <br><br><br><br><br>
    <table class="printstyle" border="0" align="center" style="width: 90%"> 
       <tr>
         <th >
           <p align=center>推荐<br/>理由</p>
         </th >
         <th  colspan="19">
			<p style="height: 200px;" align="left">
				${rs.shzt1yj }
			</p>
			<div align="right">
				推荐人（辅导员或班主任）签名：
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;<br>
				 <p align="right">（公&nbsp; 章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				年&nbsp;&nbsp;&nbsp;&nbsp;
				月&nbsp;&nbsp;&nbsp;&nbsp;
				日
			</div>
     	</tr>
       <tr>
         <th >
           <p align=center>院<br/>系<br/>意<br/>见</p></th >
         <th  colspan="19">
			<p style="height: 190px;" align="left">
				${rs.shzt2yj }
			</p>
			<div align="right">
				院系主管学生工作领导签名：
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;<br>
				 <p align="right">（公&nbsp; 章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				年&nbsp;&nbsp;&nbsp;&nbsp;
				月&nbsp;&nbsp;&nbsp;&nbsp;
				日
			</div>
       </tr>
       <tr>
         <th >
            <p align=center>学<br/>校<br/>意<br/>见</p>
	  </th >
         <th  colspan="19">
		<logic:notEmpty name="rs" property="shzt3yj">
			${rs.xxyj}
		</logic:notEmpty>
		<logic:empty name="rs" property="shzt3yj">
		<br/>
           <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在全校范围内公示<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>天，无异议，现报请同意该同学获得&nbsp;&nbsp;${rs.xn }&nbsp;&nbsp; 学年度国家奖学金。</p>
		</logic:empty>
           <p align="right">（公&nbsp; 章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right>年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;</p></th >
       </tr>
     </table>
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
    <script type="text/javascript">
    	var sfzh = $('sfzh').value;
    	for(var i=0;i<sfzh.length;i++){
    	var id = "s" + i;
    	var sfzhs = sfzh.substring(i,i+1);
    	if($(id)){
    		$(id).innerHTML = sfzhs; 
    	}
    	}
    
    </script>
    </div>
    <p>&nbsp;</p>
</body>
</html>
