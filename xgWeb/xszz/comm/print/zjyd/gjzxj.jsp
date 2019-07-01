<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel=Edit-Time-Data href="奖学金.files/editdata.mso">
<title>国家助学金申请审批表</title>
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
style='font-size:22.0pt;font-family:宋体;letter-spacing:1.0pt'>（${rs.xn }学年）国家助学金申请审批表</span></b></p> 
  <div align="left"><b>学校：</b><b>${xxmc }</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>院系：</b><b>${rs.xymc }</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>专业：</b><b>${rs.zymc }</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>班级：</b><b>${rs.bjmc }</b></div> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table  class="tbstyle" border="0" align="center" style="width: 100%"> 
    <tr align="center" > 
      <th width=7% rowspan=4 height="35px"><p align=center>本<br/>人<br/>情<br/>况</p></th> 
      <td width=11% height="35px"> <p align=center>姓名</p></td> 
      <td width=20% colspan="4" height="35px">${rs.xm } </td> 
      <td width=12% colspan="4" height="35px"> <p align=center>性别</p></td> 
      <td width=12% colspan="2" height="35px">${rs.xb } </td> 
      <td width=12% colspan="3" height="35px"> <p align=center>出生年月</p></td> 
      <td width=16% colspan="5" height="35px">${rs.csrq } </td> 
    </tr> 
    <tr align="center" height="35px"> 
      <td> <p align=center>学号</p></td> 
      <td colspan="4">${rs.xh } </td> 
      <td colspan="4"> <p align=center>民族</p></td> 
      <td colspan="2">${rs.mzmc } </td> 
      <td colspan="3"> <p align=center>入学时间</p></td> 
      <td colspan="5">${rs.rxrq } </td> 
    </tr> 
    <tr align="center" height="35px"> 
      <td> <p>政治面貌</p></td> 
      <td colspan="4">${rs.zzmmmc } </td> 
      <td colspan="4"> <p align=center>联系电话</p></td> 
      <td colspan="10">${rs.lxdh } </td> 
    </tr> 
    <tr align="center" height="35px"> 
      <td> <p>身份证号</p></td>
      <td id="s0" width="4.5%">&nbsp; </td>  
      <td id="s1" width="4.5%">&nbsp; </td> 
      <td id="s2" width="4.5%">&nbsp; </td> 
      <td id="s3" width="4.5%">&nbsp; </td> 
      <td id="s4" width="4.5%">&nbsp; </td> 
      <td id="s5" width="4.5%">&nbsp; </td> 
      <td id="s6" width="4.5%">&nbsp; </td> 
      <td id="s7" width="4.5%">&nbsp; </td> 
      <td id="s8" width="4.5%">&nbsp; </td> 
      <td id="s9" width="4.5%">&nbsp; </td> 
      <td id="s10" width="4.5%">&nbsp; </td> 
      <td id="s11" width="4.5%">&nbsp; </td> 
      <td id="s12" width="4.5%">&nbsp; </td> 
      <td id="s13" width="4.5%">&nbsp; </td> 
      <td id="s14" width="4.5%">&nbsp; </td> 
      <td id="s15" width="4.5%">&nbsp; </td> 
      <td id="s16" width="4.5%">&nbsp; </td> 
      <td id="s17" width="4.5%">&nbsp; </td> 
   	</tr>
   	<tr align="center" height="35px">
   		<td width=7% rowspan=4 ><b>家<br/>庭<br/>经<br/>济<br/>情<br/>况</b></td> 
   		<td>家庭户口</td>
   		<td colspan="10">
   			A、<logic:equal value="城镇" property="jthk" name="rs">
				√
			</logic:equal>城镇
			&nbsp;&nbsp;
			B、<logic:equal value="农村" property="jthk" name="rs">
				√
			</logic:equal>
			农村
   		</td>
   		<td colspan="3">收入来源</td>
   		<td colspan="5">${rs.srly }</td>
   	</tr>
   	<tr align="center" height="35px">
   		<td>家庭月总<br/>收入</td>
   		<td colspan="10">${rs.jtyzsr }</td>
   		<td colspan="3">家庭人口<br/>总数</td>
   		<td colspan="5">${rs.jtrs }</td>
   	</tr>
   	<tr align="center" height="35px">
   		<td>家庭住址</td>
   		<td colspan="10">${rs.jtdz }</td>
   		<td colspan="3">邮政编码</td>
   		<td colspan="5">${rs.jtyb }</td>
   	</tr>
   	<tr align="center" height="35px">
   		<td>认定情况</td>
   		<td colspan="18">
	   		 A、家庭经济特别困难
	   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    
	   		 B、家庭经济一般困难
   		 </td>
   	</tr>
   	
   <logic:lessEqual value="3" name="cyNum">
	<tr style="height:22px" height="35px">
		<th width="7%" rowspan="${cyNum+6}">
			<div align="center">
				家<br>
				庭<br>
				成<br>
				员<br>
				情<br>
				况
			</div>
		</th>
		<td width="10%" height="35px">
			<div align="center">
				姓名
			</div>
		</td>
		<td width="10%" colspan="5">
			<div align="center">
				年龄
			</div>
		</td>
		<td width="10%" colspan="3">
			<div align="center">
				与本人关系
			</div>
		</td>
		<td width="10%" colspan="10">
			<div align="center">
				工作或学习单位
			</div>
		</td>
	</tr>
	<logic:iterate name="cyList" id="cy">
		<tr height="35px">
			<td width="10%">
				<div align="center">
					${cy.cyxm }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="5">
				<div align="center">
					${cy.cynl }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="3">
				<div align="center">
					${cy.mc }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="10">
				<div align="center">
					${cy.cygzdw }&nbsp;&nbsp;
				</div>
			</td>
		</tr>
	</logic:iterate>
	<%	
		for(int i=0;i<5;i++){
	%>
		<tr height="35px">
			<td width="10%">
				<div align="center">
					&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="5">
				<div align="center">
					&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="3">
				<div align="center">
					&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="10">
				<div align="center">
					&nbsp;&nbsp;
				</div>
			</td>
		</tr>
	<%} %>
	</logic:lessEqual>
   	
   	<logic:greaterThan value="3" name="cyNum">
	<tr style="height:22px">
		<th width="7%" rowspan="${cyNum+1 }">
			<div align="center">
				家<br>
				庭<br>
				成<br>
				员<br>
				情<br>
				况
			</div>
		</th>
		<td width="10%">
			<div align="center">
				姓名
			</div>
		</td>
		<td width="10%" colspan="5">
			<div align="center">
				年龄
			</div>
		</td>
		<td width="10%" colspan="3">
			<div align="center">
				与本人关系
			</div>
		</td>
		<td width="10%" colspan="10">
			<div align="center">
				工作或学习单位
			</div>
		</td>
	</tr>
	<logic:iterate name="cyList" id="cy">
		<tr>
			<td width="10%">
				<div align="center">
					${cy.cyxm }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="5">
				<div align="center">
					${cy.cynl }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="3">
				<div align="center">
					${cy.mc }&nbsp;&nbsp;
				</div>
			</td>
			<td width="10%" colspan="10">
				<div align="center">
					${cy.cygzdw }&nbsp;&nbsp;
				</div>
			</td>
		</tr>
	</logic:iterate>
	</logic:greaterThan>
    <tr height="110px"> 
      <th width="7%"> <p align=center>
      	        申请<br/> 
		        理由<br/> 
		</p></th> 
     	 <td colspan="19">
      		<p style="height: 50px">&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sqly }</p> 
            <p align=right>申请人签名（手签）：
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp; </p>
      </td> 
    </tr> 
       <tr height="110px">
         <th>
           <p align=center>院<br/>(系)<br/>意<br/>见</p>
         </th>
         <td colspan="19">
      		<p style="height: 50px">&nbsp;&nbsp;&nbsp;&nbsp; </p> 
            <p align=right>（院系公章）
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp; </p>
     	 </td> 
     	</tr>
       <tr height="110px">
         <th width="7%">
           <p align=center>学<br/>校<br/>意<br/>见</p></th>
        <td colspan="19">
      		<p style="height: 50px">&nbsp;&nbsp;&nbsp;&nbsp;</p> 
            <p align=right>（学校公章）
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp; </p>
     	 </td> 
       </tr>
     </table>
     <br/>
     <div align="right">制表：浙江省学生资助管理中心　2010年版</div>
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
