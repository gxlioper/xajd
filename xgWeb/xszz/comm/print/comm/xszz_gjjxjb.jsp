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
style='font-size:22.0pt;font-family:宋体;letter-spacing:1.0pt'>国家奖学金申请审批表</span></b></p> 
  <p align=center>（&nbsp;${rs.xn } &nbsp; 学年）</p> 
  <p align=center><b>学校：</b>${xxmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>院系：</b>${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>专业：</b>${rs.zymc }&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>班级：</b>${rs.bjmc }</p> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table  class="tbstyle" border="0" align="center" style="width: 90%"> 
    <tr> 
      <th width=14% rowspan=4><p align=center>基<br/>本<br/>情<br/>况</p></th> 
      <td width=14%> <p align=center>姓名</p></td> 
      <td width=20% colspan="5">${rs.xm } </td> 
      <td width=12% colspan="3"> <p align=center>性别</p></td> 
      <td width=12% colspan="3">${rs.xb } </td> 
      <td width=12% colspan="3"> <p align=center>出生年月</p></td> 
      <td width=16% colspan="4">${rs.csrq } </td> 
    </tr> 
    <tr align="center"> 
      <td> <p align=center>学号</p></td> 
      <td colspan="5">${rs.xh } </td> 
      <td colspan="3"> <p align=center>民族</p></td> 
      <td colspan="3">${rs.mzmc } </td> 
      <td colspan="3"> <p align=center>入学时间</p></td> 
      <td colspan="4">${rs.rxrq } </td> 
    </tr> 
    <tr align="center"> 
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
    <tr align="center"> 
      <td> <p>政治面貌</p></td> 
      <td colspan="5">${rs.zzmmmc } </td> 
      <td colspan="6"> <p align=center>联系电话</p></td> 
      <td colspan="7">${rs.lxdh } </td> 
    </tr> 
    <tr> 
      <th>
      	<p align=center>学<br/>习<br/>等<br/>情<br/>况<br/></p> 
	  </th> 
      <td colspan="19"> <p>该学年必修课程数<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${rs.bxkms}</u>门，其中，优秀<u>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;${rs.yxkms}</u>门，良好<u>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;${rs.lhkms}</u>门成绩排名（专业或年级）：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xnzypm}<logic:notEmpty name="rs" property="xnzypm">/${rs.zyzrs}</logic:notEmpty> </u>（名次/总人数）</p> 
        <p>综合考评成绩<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${rs.zhzf}</u>分，排名<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <logic:notEmpty name="rs" property="zhzfpm">${rs.zhzfpm}/${rs.zrs}</logic:notEmpty ><logic:empty name="rs" property="zhzfpm">无</logic:empty> </u>（名次/总人数）（如无此项，填写“无”）</p>
      </td> 
    </tr> 
    <tr> 
      <th> <p align=center>获<br/>奖<br/>情<br/>况<br/></p> 
      </th> 
      <td colspan="19"> <p>主要奖项：</p> 
		<p>${rs.hjqk}</p>
        <p>其中，院级奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.yjjlcs} </u>项；校级奖励<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${rs.xjjlcs}</u>项；省级以上奖励<u>&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sjjlcs}</u>项。</p></td> 
    </tr> 
    <tr> 
      <th> <p align=center>
      	        申<br/>
       	        请<br/> 
		        理<br/> 
		        由<br/> 
		        ︵<br/> 
		        全<br/> 
		        面<br/> 
		        反<br/> 
		        映<br/> 
		        德<br/> 
		        智<br/> 
		        体<br/> 
		        美<br/> 
		        情<br/> 
		        况<br/> 
		        ︶<br/></p></th> 
     	 <td colspan="19">
      		<p style="heigth:200px">&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sqsm }</p> 
      		<p>
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			申请人签名：</p>
			
            <p align=right>年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp; </p>
      </td> 
    </tr> 
       <tr>
         <th>
           <p align=center>年&nbsp; 级<br/>（专业）<br/>  推&nbsp; 荐<br/>意&nbsp; 见</p>
         </th>
         <td colspan="19">
           <p>&nbsp;&nbsp;&nbsp;</p>
           <p align=left>
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 推荐人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 行政职务：</p>
           <p align=right>年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp; &nbsp;&nbsp;日&nbsp;&nbsp;</p></td>
     	</tr>
       <tr>
         <th>
           <p align=center>院<br/>系<br/>意<br/>见</p></th>
         <td colspan="19">
         	<p>&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align="right">（公&nbsp; 章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right>年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp; </p></td>
       </tr>
       <tr>
         <th>
            <p align=center>学<br/>校<br/>意<br/>见</p>
	  </th>
         <td colspan="19">
		<logic:notEmpty name="rs" property="xxyj">
			${rs.xxyj}
		</logic:notEmpty>
		<logic:empty name="rs" property="xxyj">
		<br/>
           <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>范围内公示<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>天，无异议，现报请同意该同学获本学年度国家奖学金。</p>
		</logic:empty>
           <p align="right">（公&nbsp; 章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right>年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;</p></td>
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
