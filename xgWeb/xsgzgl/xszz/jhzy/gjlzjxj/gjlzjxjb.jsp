<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<title>普通本科高校、高等职业学校国家励志奖学金申请表</title>
</head>
<body > 
<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
<div class=Section1 style='layout-grid:15.6pt'> 
  
  <p align=center style='text-align:center;'>
  		<b><span style='font-size:18.0pt;font-family:黑体'>（${rs.xn}学年）国家励志奖学金申请审批表</span></b></p> 
  		<b>学校：</b>金华职业技术学院<b><span style='font-family:"Times New Roman";'> </span></b>
  		<b>院系</b>：${rs.xymc }<span style='font-family:&quot;Times New Roman&quot;'> </span>
  		<b>专业</b>：${rs.zymc }<span style='font-family:"Times New Roman";'> </span>
		<b>班级</b>：${rs.bjmc }</span>
  	<table width="100%" class="printstyle">
    <tr> 
      <td height="55px" rowspan=4 align="center" >基<br/>本<br/>情<br/>况</td> 
      <td height="55px" width="10%" align="center" >姓名</td> 
      <td colspan=5 align="center" >${rs.xm }</td> 
      <td colspan=2 align="center" >性别</td> 
      <td colspan=5 align="center" >${rs.xb }</td> 
      <td colspan=6 align="center" >出生年月</td> 
      <td  colspan=6 align="center" >${rs.csrq }</td> 
    </tr> 
    <tr> 
      <td   height="55px"align="center">学号</td> 
      <td  colspan=5 align="center">${rs.xh }</td> 
      <td  colspan=2 align="center">民族</td> 
      <td  colspan=5 align="center">${rs.mzmc }</td> 
      <td  colspan=6 align="center">入学时间</td> 
      <td  colspan=6 align="center">${rs.rxrq }</td> 
    </tr> 
    <tr> 
      <td height="55px" align="center">政治面貌</td> 
      <td colspan=12 align="center">${rs.zzmmmc }</td> 
      <td colspan=6 align="center">联系电话</td> 
      <td colspan=6 align="center">${rs.sjhm }</td> 
    </tr> 
    <tr align="center"> 
    <td height="55px">身份证号</td> 
    <td width="4%" colspan=2 >
		<div align="center">
			<bean:write name='rs' property="sfzh1" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh2" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh3" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh4" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh5" />
		</div>
	</td>
	<td width="4%" colspan=2 >
		<div align="center">
			<bean:write name='rs' property="sfzh6" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh7" />
		</div>
	</td>
	<td width="4%" >
		<div align="center">
			<bean:write name='rs' property="sfzh8" />
		</div>
	</td>
	<td width="4%" colspan=3 >
		<div align="center">
			<bean:write name='rs' property="sfzh9" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh10" />
		</div>
	</td>
	<td width="4%"  colspan=2 >
		<div align="center">
			<bean:write name='rs' property="sfzh11" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh12" />
		</div>
	</td>
	<td width="4%"  colspan=2 >
		<div align="center">
			<bean:write name='rs' property="sfzh13" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh14" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh15" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh16" />
		</div>
	</td>
	<td width="4%">
		<div align="center">
			<bean:write name='rs' property="sfzh17" />
		</div>
	</td>
	<td width="4%" >
		<div align="center">
			<bean:write name='rs' property="sfzh18" />
		</div>
	</td>
    </tr> 
    <tr align="center"> 
      <td rowspan=2 >学<br/>习<br/>情<br/>况</td> 
      <td height="55px" colspan=10 >成绩排名：<U>${rs.cjpm}/${rs.bjzrs}</U>（名次/总人数）</td> 
      <td height="55px" colspan=15 >
      	实行综合考评排名：
      	<logic:notEmpty name="rs" property="sxzhkppm">
      		<logic:equal  name="rs" property="sxzhkppm" value="是">
      			是<img src="/xgxt/pictures/xszz/gou.jpg"></img>
      			否□
      		</logic:equal>
      		<logic:equal  name="rs" property="sxzhkppm" value="否">
      			是□
      			否<img src="/xgxt/pictures/xszz/gou.jpg"></img>
      		</logic:equal>
      	</logic:notEmpty>
      	<logic:empty name="rs" property="sxzhkppm">
      			是□
      			否□
      	</logic:empty>
  	  </td> 
    </tr> 
    <tr align="center"> 
      <td height="55px" colspan=10 >必修课<U>${rs.bxkms}</U>门，其中及格以上<U>${rs.jgms}</U>门</td> 
      <td colspan=15 >如是，排名：<U>${rs.zhkppm}/${rs.bjzrs }</U>（名次/总人数）</td> 
    </tr> 
    <tr align="center" > 
      <td rowspan=5 >大学期<br/>间主要<br/>获奖<br/>情况</td> 
      <td height="55px" colspan=2 >日期</td> 
      <td colspan=10 >奖项名称</td> 
      <td colspan=13 >颁奖单位</td> 
    </tr> 
    <tr align="center"> 
      <td height="55px" colspan=2  >${rs.hjsj1}</td> 
      <td colspan=10  >${rs.hjmc1}</td> 
      <td colspan=13  >${rs.bjdw1}</td> 
    </tr> 
    <tr align="center"> 
      <td height="55px" colspan=2  >${rs.hjsj2}</td> 
      <td colspan=10  >${rs.hjmc2}</td> 
      <td colspan=13  >${rs.bjdw2}</td> 
    </tr> 
    <tr align="center"> 
      <td height="55px" colspan=2  >${rs.hjsj3}</td> 
      <td colspan=10  >${rs.hjmc3}</td> 
      <td colspan=13  >${rs.bjdw3}</td> 
    </tr> 
    <tr align="center"> 
      <td height="55px" colspan=2  >${rs.hjsj4}</td> 
      <td colspan=10  >${rs.hjmc4}</td> 
      <td colspan=13  >${rs.bjdw4}</td> 
    </tr> 
    <tr align="center" > 
      <td width=8% height="55px" rowspan=4 > 家庭经济情况</td> 
      <td  > 家庭户口</td> 
      <td colspan=11 >
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
	  </td> 
      <td colspan=4 height="55px">收入来源</td> 
      <td colspan=9 >${rs.srly }</td> 
    </tr> 
    <tr align="center"> 
      <td width=8% height="55px">家庭月总<br/>收入</td> 
      <td colspan=11 >${rs.jtnzsr }</td> 
      <td colspan=4 >家庭人口<br/>总数</td> 
      <td colspan=9 >${rs.jtrks }人</td> 
    </tr> 
    <tr align="center"> 
      <td width=8% height="55px">家庭住址</td> 
      <td colspan=11 >${rs.jtdz }</td> 
      <td colspan=4 >邮政编码</td> 
      <td colspan=9 >${rs.jtyb }</td> 
    </tr> 
    <tr align="center"> 
      <td width=8%  height="55px">认定情况</td> 
      <td colspan=24 >
     	<logic:notEmpty name="rs" property="xxtjdc">
			<logic:equal name="rs" property="xxtjdc" value="特别困难">
				<u>A、家庭经济特别困难</U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B、家庭经济一般困难 
			</logic:equal>
			<logic:equal name="rs" property="xxtjdc" value="一般困难">
				A、家庭经济特别困难&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>B、家庭经济一般困难 </U> 
			</logic:equal>
		</logic:notEmpty>
		<logic:empty name="rs" property="xxtjdc">
				A、家庭经济特别困难&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B、家庭经济一般困难
		</logic:empty>
      </td> 
    </tr> 
    </table> 
   <br/> <br/> 
    <table width="100%" class="printstyle">
    <tr height="270px"> 
      <td width=8%  align="center"> 申请<br/>理由<br/>(200字)</td> 
      <td colspan=25 >
      		<p align="left " style="height: 100px">${rs.sqly }</p>
      		<p align="right" style="padding-right: 100px">申请人签名(手签)：</p><br/>
      		<p align="right" style="padding-right: 50px">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p>
      		<br/><br/>
      </td> 
    </tr> 
    <tr align="center" height="270px"> 
      <td >院<br/>（系）<br/>意<br/>见</td> 
      <td  colspan=25 valign=top >
      		<p align="left " style="height: 100px">${rs.xyshyj }</p>
      		<p align="right" style="padding-right: 100px">院系主管学生工作领导签名：</p><br/>
      		<p align="right" style="padding-right: 70px">（院系公章）</p><br/>
    		<p align="right" style="padding-right: 50px">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p><br/>
    </tr> 
    <tr align="center" height="270px"> 
      <td  >学<br/>校<br/>意<br/>见</td> 
      <td  colspan=25 valign=top >
      		<p align="left " style="height: 40px"></p>
			<p align="left " style="height: 100px">经评审，并在校内公示<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>个工作日，无异议，现报请批准该同学获得国家励志奖学金。</p>
      		<p align="right" style="padding-right: 70px">（学校公章）</p><br/>
    		<p align="right" style="padding-right: 50px">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</p><br/>
	  </td> 
    </tr> 
<%--    <tr height=0 align="center"> --%>
<%--      <td width=64 ></td> --%>
<%--      <td width=72 ></td> --%>
<%--      <td width=42 ></td> --%>
<%--      <td width=6 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=22 ></td> --%>
<%--      <td width=6 ></td> --%>
<%--      <td width=31 ></td> --%>
<%--      <td width=25 ></td> --%>
<%--      <td width=9 ></td> --%>
<%--      <td width=1 ></td> --%>
<%--      <td width=18 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=27 ></td> --%>
<%--      <td width=1 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=20 ></td> --%>
<%--      <td width=8 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=27 ></td> --%>
<%--      <td width=29 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--      <td width=28 ></td> --%>
<%--    </tr> --%>
  </table> 
  <p align="right"><span
style='font-family:宋体;"Times New Roman"'>制表：浙江省学生资助管理中心　</span><span lang=EN-US>2010</span><span
style='font-family:宋体;"Times New Roman"'>年版</span></p> 
</div> 
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
</body>
</html>
