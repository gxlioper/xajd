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
style='font-size:22.0pt;font-family:宋体;letter-spacing:1.0pt'>（${rs.xn }学年）国家励志奖学金申请审批表</span></b></p> 
 <br/> <br/>
  <div align="left"><b>学校：</b><b>${xxmc }</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>院系：</b><b>${rs.xymc }</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>专业：</b><b>${rs.zymc }</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>班级：</b><b>${rs.bjmc }</b></div> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table  class="tbstyle" border="0" align="center" style="width: 100%;"> 
    <tr align="center" height="50"> 
      <th width=14% rowspan=4><p align=center>基<br/>本<br/>情<br/>况</p></th> 
      <td width=6%> <p align=center>姓名</p></td> 
      <td width=20% colspan="4">${rs.xm } </td> 
      <td width=12% colspan="2"> <p align=center>性别</p></td> 
      <td width=12% colspan="2">${rs.xb } </td> 
      <td width=12% colspan="4"> <p align=center>出生年月</p></td> 
      <td width=16% colspan="6">${rs.csrq } </td> 
    </tr> 
    <tr align="center" height="50"> 
      <td> <p align=center>学号</p></td> 
      <td colspan="4">${rs.xh } </td> 
      <td colspan="2"> <p align=center>民族</p></td> 
      <td colspan="2">${rs.mzmc } </td> 
      <td colspan="4"> <p align=center>入学时间</p></td> 
      <td colspan="6">${rs.rxrq } </td> 
    </tr> 
    <tr align="center" height="50"> 
      <td> <p>政治<br/>面貌</p></td> 
      <td colspan="8">${rs.zzmmmc } </td> 
      <td colspan="4"> <p align=center>联系电话</p></td> 
      <td colspan="6">${rs.lxdh } </td> 
    </tr> 
    <tr align="center" height="50"> 
      <td> <p>身份<br/>证号</p></td>
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
    <tr  height="50"> 
      <th rowspan="2"> <p align=center>学习<br/>情况<br/></p> 
      </th> 
      <td colspan="8">成绩排名：<u>&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;</u>（名次/总人数）</td>
      <td colspan="11">实行综合考评排名：是□ ；&nbsp;&nbsp;否□</td>
    </tr>
    <tr  height="50">
      <td colspan="8">必修课<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>门，其中及格以上<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>门</td>
      <td colspan="11">如是，排名：<u>&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;</u>（名次/总人数）</td>
    </tr>
    <tr align="center" height="50"> 
      <th rowspan="5"> <p align=center>大学<br/>期间<br/>主要<br/>获奖<br/>情况</p> 
      </th>
      <td colspan="2">日期</td> 
      <td colspan="8">奖项名称</td>
      <td colspan="9">颁奖单位</td>
    </tr>
    <tr align="center" height="50">
      <td colspan="2">&nbsp;${rs.hjsj1}&nbsp;</td>
      <td colspan="8">&nbsp;${rs.hjmc1 }&nbsp;</td>
      <td colspan="9">&nbsp;${rs.bjdw1 }&nbsp;</td>
    </tr>
     <tr align="center" height="50">
      <td colspan="2">&nbsp;${rs.hjsj2}&nbsp;</td>
      <td colspan="8">&nbsp;${rs.hjmc2 }&nbsp;</td>
      <td colspan="9">&nbsp;${rs.bjdw2 }&nbsp;</td>
    </tr>
     <tr align="center" height="50">
      <td colspan="2">${rs.hjsj3}</td>
      <td colspan="8">&nbsp;${rs.hjmc3 }&nbsp;</td>
      <td colspan="9">&nbsp;${rs.bjdw3 }&nbsp;</td>
    </tr>
     <tr align="center" height="50">
      <td colspan="2">&nbsp;${rs.hjsj4}&nbsp;</td>
      <td colspan="8">&nbsp;${rs.hjmc4 }&nbsp;</td>
      <td colspan="9">&nbsp;${rs.bjdw4 }&nbsp;</td>
    </tr>
     
    <tr align="center" height="50"> 
      <th rowspan=4>
      	<p align=center>家<br/>庭<br/>经<br/>济<br/>情<br/>况<br/></p> 
	  </th> 
     <td>家庭<br/>户口</td>
     <td colspan="8"> 
     	A、<logic:equal value="城镇" property="jthk" name="rs">
				√
			</logic:equal>城镇
			&nbsp;&nbsp;
			B、<logic:equal value="农村" property="jthk" name="rs">
				√
			</logic:equal>农村
     </td>
     <td colspan="3">收入来<br/>源</td>
     <td colspan="7">${rs.srly }</td>
    </tr> 
    <tr align="center" height="50">
     <td>家庭<br/>月总<br/>收入</td>
     <td colspan="8">${rs.jtyzsr }</td>
     <td colspan="3">家庭人<br/>口总数</td>
     <td colspan="7">${rs.jtrs }</td>
    </tr>
    <tr align="center" height="50">
     <td>家庭<br/>住址</td>
     <td colspan="8">${rs.jtdz }</td>
     <td colspan="3">邮政编<br/>码</td>
     <td colspan="7">${rs.jtyb }</td>
    </tr>
    <tr align="center" height="50">
    	<td>认定<br/>情况</td>
    	<td colspan="18">A、家庭经济特别困难&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    					 B、家庭经济一般困难</td>
    </tr>
    </table>
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
    <table  class="tbstyle" border="0" align="center" style="width: 100%;">
    <tr height="280" > 
      <th width="14%"> <p align=center>
      	        申请<br/> 
		        理由<br/> 
		   （200字）
		   <br/></p></th> 
     	 <td colspan="19">
      		<p style="height: 200">
      		<br/>
      		&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sqly }</p> 
      		<p align="right">
			申请人签名(手签)：
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
            <p align=right>年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp; </p>
      </td> 
    </tr> 
       <tr height="280" >
         <th>
           <p align=center>院<br/>(系)<br/>意<br/>见</p></th>
         <td colspan="19">
         	<p style="height: 90">&nbsp;&nbsp;&nbsp;&nbsp;</p>
         	  <p align="right"> 院系主管学生工作领导签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align="right">（院系公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right>年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp; </p></td>
       </tr>
       <tr height="280">
         <th>
            <p align=center>学<br/>校<br/>意<br/>见</p>
	  </th>
         <td colspan="19">
           <p style="height: 100">
           <br/><br/>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在校内公示 <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>个工作日，无异议，现报请批准该同学获得国家励志奖学金。</p>
           <p align="right">（学校公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right>年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;</p></td>
       </tr>
     </table>
      <div align="right">
      	<br/>
     	制表：浙江省学生资助管理中心&nbsp;&nbsp;&nbsp;&nbsp;2010年版
     </div>
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
