<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
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
<body>
<div align="center"> 
<br/>
<br/>
<br/>
<br/>
  <p align=center><b><span style='font-size:30px;font-family:黑体;font-weight:bold'>(${rs.xn }学年)国家奖学金申请审批表</span></b></p> 
  <br/>
  <br/>
  <table width="800px" style="font-size:20px;font-family:宋体;font-weight:bold">
  <tr align="left">
  <td>学校：${xxmc }</td>
  <td>院系：${rs.xymc }</td>
  <td>学号：${rs.xh }</td>
  </tr>
  </table>
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table width="800px"  class="printtab" style="font-size:20px">
    <tr> 
      <th width=12% rowspan=4><p align=center>基本<br/>情况</p></th> 
      <td width=14%> <p align=center>姓名</p></td> 
      <td width=10% colspan="5" align="center">${rs.xm } </td> 
      <td width=12% colspan="3"> <p align=center>性别</p></td> 
      <td width=12% colspan="3" align="center">${rs.xb } </td> 
      <td width=12% colspan="3"> <p align=center>出生年月</p></td> 
      <td width=16% colspan="4" align="center">${rs.csrq } </td> 
    </tr> 
    <tr align="center"> 
      <td> <p align=center>政治面貌</p></td> 
      <td colspan="5">${rs.zzmmmc} </td> 
      <td colspan="3"> <p align=center>民族</p></td> 
      <td colspan="3">${rs.mzmc } </td> 
      <td colspan="3"> <p align=center>入学时间</p></td> 
      <td colspan="4">${rs.rxrq } </td> 
    </tr> 
    <tr align="center"> 
      <td> <p align=center>专业</p></td> 
      <td colspan="5">${rs.zymc } </td> 
      <td colspan="3"> <p align=center>学制</p></td> 
      <td colspan="3">${rs.xz } </td> 
      <td colspan="3"> <p align=center>联系电话</p></td> 
      <td colspan="4">${rs.lxdh } </td> 
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
    <tr> 
      <th rowspan="2">
      	<p align=center>学习<br/>情况<br/></p> 
	  </th> 
      <td colspan="9">成绩排名：
      <logic:equal name="rs" property="bjpm" value="">
       <u>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;</u>
      </logic:equal>
      <logic:notEqual name="rs" property="bjpm" value="">
      <u>&nbsp;&nbsp;&nbsp;${rs.bjpm }&nbsp;&nbsp;&nbsp;</u>
      </logic:notEqual>
             （名次/总人数）</td>
      <td colspan="10">实行综合考评排名：
      <logic:notEqual name="rs" property="sxzhpm" value="">
      <logic:equal value="否" property="sxzhpm" name="rs"> 是<input type="checkbox"/>；否<input type="checkbox" checked="checked"/></logic:equal>
      <logic:equal value="是" property="sxzhpm" name="rs"> 是<input type="checkbox" checked="checked"/>；否<input type="checkbox" /></logic:equal>
      </logic:notEqual >
      <logic:equal name="rs" property="sxzhpm" value="">
		是<input type="checkbox"/>；否<input type="checkbox" />
	  </logic:equal>
       </td>
    </tr> 
    <tr>
    <td colspan="9"> 必修课<u>&nbsp;&nbsp;&nbsp;&nbsp; ${rs.bxkms}&nbsp;&nbsp;&nbsp;&nbsp;</u>门，其中及格以上<u>&nbsp;&nbsp;${rs.jgysms }&nbsp;&nbsp;</u>门
    </td> 
    <td colspan="10">
                     如是，排名：
       <logic:equal name="rs" property="zcbjpm" value="">
       <u>&nbsp;&nbsp;/&nbsp;&nbsp;</u>（名次/总人数）
      </logic:equal>
      <logic:notEqual name="rs" property="zcbjpm" value="">
                     <u>&nbsp;&nbsp;&nbsp;${rs.zcbjpm }&nbsp;&nbsp;&nbsp; </u>（名次/总人数）
       </logic:notEqual>
    </td>
    </tr>
    <tr align="center"> 
      <th rowspan=5"> <p align=center>大学<br>期间<br>主要<br>获奖<br>情况</p> 
      </th> 
    <td>日期</td>
    <td colspan="10">奖项名称</td>
    <td colspan="8">颁奖单位</td>
    </tr> 
    <tr align="center">
    <td>${rs.hjrq1 }</td>
    <td colspan="10">${rs.hjmc1 }&nbsp;&nbsp;</td>
    <td colspan="8">${rs.bjdw1 }&nbsp;&nbsp;</td>
    </tr>
    <tr align="center">
    <td>${rs.hjrq2 }</td>
    <td colspan="10">${rs.hjmc2 }&nbsp;&nbsp;</td>
    <td colspan="8">${rs.bjdw2 }&nbsp;&nbsp;</td>
    </tr>
    <tr align="center">
    <td>${rs.hjrq3 }</td>
    <td colspan="10">${rs.hjmc3 }&nbsp;&nbsp;</td>
    <td colspan="8">${rs.bjdw3 }&nbsp;&nbsp;</td>
    </tr>
     <tr align="center">
    <td>${rs.hjrq4 }</td>
    <td colspan="10">${rs.hjmc4 }&nbsp;&nbsp;</td>
    <td colspan="8">${rs.bjdw4 }&nbsp;&nbsp;</td>
    </tr>
    <tr height="420px"> 
      <th> <p align=center>
      	        申请<br/> 
		        理由<br/> 
		 <span style="font-size:10px;font-weight:normal">(200字)</span>
		  </p></th> 
     	 <td colspan="19">
      		<p style="height:330px">&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sqly }</p> 
      		<p align="right">
			申请人签名(手签)：
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			 &nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			<p style="height:20px"></p>
            <p align="right">年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
            </p>
            <p style="height:15px"></p>
       </td> 
      </tr> 
    </table>
    
   <p style="height:60px"></p>
   
   
    <table width="800px"  class="printtab" style="font-size:20px">
  
       <tr height="350px">
         <th width=12%>
           <p align=center>推荐<br/>理由<br><span style="font-size:10px;font-weight:normal">(100字)</span> </p>
         </th>
         <td colspan="19">
           <p style="height:300px">&nbsp;&nbsp;&nbsp;</p>
           <p></p>
           <p align=right>
		        推荐人（辅导员或班主任）签名：
		   &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		   &nbsp;&nbsp;&nbsp;&nbsp;
		 </p>
		<p style="height:20px"></p>
        <p align=right>年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp; &nbsp;&nbsp;日&nbsp;&nbsp;
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
        </p>
        <p style="height:15px"></p>
        </td>
     	</tr>
     
    	
       <tr height="210px">
         <th>
           <p align=center>院<br/>(系)<br/>意<br/>见</p></th>
         <td colspan="19">
           <p style="height:160px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj}</p>
           <p></p>
           <p align="right"> 院系主管学生工作领导签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p style="height:15px"></p>
           <p align="right">（院系公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p style="height:15px"></p>
           <p align=right>年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp; 
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
        </p>
        <p style="height:15px"></p></td>
       </tr>
       <tr height="340px">
         <th>
            <p align=center>学<br/>校<br/>意<br/>见</p>
	  </th>
         <td colspan="19">
		<logic:notEmpty name="rs" property="xxyj">
			&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj}
		</logic:notEmpty>
		<logic:empty name="rs" property="xxyj">
		<br/>
		<br/>
           <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在校内公示<u>&nbsp;&nbsp;五&nbsp;&nbsp; </u>个工作日，无异议，	现报请批准该同学获得国家奖学金。</p>
		</logic:empty>
		   <p style="height:120px"></p>
           <p align="right">（学校公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p style="height:30px"></p>
           <p align=right>年&nbsp;&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp;&nbsp; 日&nbsp;&nbsp;  
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
        </p>
        <p style="height:20px"></p></td>
       </tr>
     </table>
     <center><p style="width:800px;font-size:16px" align="right">制表：全国学生资助管理中心　2010年版</p></center>
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
