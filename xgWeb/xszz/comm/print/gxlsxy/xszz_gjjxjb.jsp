<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel=Edit-Time-Data href="奖学金.files/editdata.mso">
<title>国家奖学金申请审批表</title>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>

<body bgcolor="#FFFFFF" lang=ZH-CN> 
<div align="center"> 
  <p align=center><b><span
style='font-size:22.0pt;font-family:宋体;letter-spacing:1.0pt'>(${rs.xn}学年)国家奖学金申请审批表</span></b></p> 
  
  <p align=center><b>学校：</b>${xxmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>院系：</b>${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>学号：</b>${rs.xh }</p> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table  class="printstyle" border="0" align="center" style="width: 90%"> 
    <tr height="50px"  align=center> 
      <th  width="10%" rowspan=4><p align=center>基本<br/>情况</p></th> 
      <td width=14%> <p align=center>姓名</p></td> 
      <td width=20% colspan="5">${rs.xm } </td> 
      <td width=12% colspan="3"> <p align=center>性别</p></td> 
      <td width=12% colspan="3">${rs.xb } </td> 
      <td width=12% colspan="3"> <p align=center>出生年月</p></td> 
      <td width=16% colspan="4">${rs.csrq } </td> 
    </tr> 
    <tr align="center" height="50px"> 
      <td> <p align=center>政治面貌</p></td> 
      <td colspan="5">${rs.zzmmmc } </td> 
      <td colspan="3"> <p align=center>民族</p></td> 
      <td colspan="3">${rs.mzmc } </td> 
      <td colspan="3"> <p align=center>入学时间</p></td> 
      <td colspan="4">${rs.rxrq } </td> 
    </tr> 
    <tr align="center" height="50px"> 
      <td> <p>专业</p></td> 
      <td colspan="5">${rs.zymc }</td> 
      <td colspan="3"> <p>学制</p></td> 
      <td colspan="3">${rs.xz } </td> 
      <td colspan="3"> <p align=center>联系电话</p></td> 
      <td colspan="4">${rs.lxdh } </td> 
    </tr> 
    <tr align="center" height="50px"> 
      <td> <p>身份证号</p></td>
      <td id="s0">&nbsp; </td>  
      <td id="s1">&nbsp; </td> 
      <td id="s2">&nbsp; </td> 
      <td id="s3">&nbsp; </td> 
      <td id="s4">&nbsp; </td> 
      <td id="s5">&nbsp; </td> 
      <td id="s6">&nbsp; </td> 
      <td id="s7">&nbsp; </td> 
      <td id="s8">&nbsp; </td> 
      <td id="s9">&nbsp; </td> 
      <td id="s10">&nbsp; </td> 
      <td id="s11">&nbsp; </td> 
      <td id="s12">&nbsp; </td> 
      <td id="s13">&nbsp; </td> 
      <td id="s14">&nbsp; </td> 
      <td id="s15">&nbsp; </td> 
      <td id="s16">&nbsp; </td> 
      <td id="s17">&nbsp; </td> 
   	</tr>
    
    <tr  align="center" height="50px"> 
      <th rowspan="2">
      	<p align=center>学习<br/>情况</p> 
	  </th> 
      <td colspan="10"> <p>成绩排名：<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>/<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U> （名次/总人数）</p> </td>
      <td colspan="9"> <p>实行综合考评排名：是□；否□</p></td> 
    </tr> 
    <tr  align="center" height="50px"> 
      <td colspan="10"> <p>必修课<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>门，其中及格以上<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>门</p> </td>
      <td colspan="9"> <p>如是，排名：<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>/<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>（名次/总人数）</p></td> 
    </tr> 
    
    <tr  align="center" height="50px"> 
      <th rowspan=5> <p align=center>大学<br/>期间<br/>主要<br/>获奖<br/>情况</p> 
      </th> 
      <td colspan="2">日期</td>
      <td colspan="10">奖项名称</td>
      <td colspan="7">颁奖单位</td>
    </tr>
    <%
    for(int i=0;i<4;i++){
    %>
    <tr  align="center" height="50px">  
      <td colspan="2">&nbsp;</td>
      <td colspan="10">&nbsp;</td>
      <td colspan="7">&nbsp;</td>
    </tr> 
    <%
    }
    %>
    <tr height="350px"> 
      <th > <p align=center >
      	        申请<br/>
		        理由<br/> 
		        （200字）
		        <br/></p></th> 
     	 <td colspan="19" style="vertical-align: bottom" align=right> 申请人签名(手签)：&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
            <p align=right>年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
      </td> 
    </tr> 
    </table>
    <br/><br/>
     <table  class="printstyle" border="0" align="center" style="width: 90%"> 
       <tr  height="300px">
         <th  width="10%" >
           <p align=center>推荐<br/>理由<br/>(100字)</p>
         </th>
         <td colspan="19" >
           <p style="height: 220px">${rs.shzt1yj } </p>
           <p align=right style="vertical-align: bottom"> 推荐人（辅导员或班主任）签名：&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
           <p align=right style="vertical-align: bottom">年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp; &nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p></td>
     	  
     	</tr>
       <tr  height="300px">
         <th>
           <p align=center>院<br/>（系）<br/>意<br/>见</p></th>
           <td colspan="19">
           <p style="height: 220px">${rs.shzt2yj }</p>
           <p align=right style="vertical-align: bottom">&nbsp;&nbsp;&nbsp;&nbsp; 院系主管学生工作领导签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
           <p align="right" style="vertical-align: bottom">（院系公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right style="vertical-align: bottom">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p></td>
       </tr>
       
       <tr  height="300px">
         <th><p align=center>学<br/>校<br/>意<br/>见</p>
	   </th>
       <td colspan="19">
			
	         <p>&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在校内公示<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>个工作日，无异议，现报请批准该同学获得国家奖学金。</p>
			 <p style="height: 110px">${rs.shzt3yj}</p>
			 <p align="right" style="vertical-align: bottom">（学校公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	         <p align="right" style="vertical-align: bottom">年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p></td>
	      	
	       </tr>
       </table>
      </div>
      
      
      
     	<p align="right">制表：全国学生资助管理中心　2010年版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
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
