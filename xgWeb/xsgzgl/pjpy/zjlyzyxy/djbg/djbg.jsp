<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<%--<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>--%>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
		    <p><span style="font-size:23px;font-family:黑体">浙江旅游职业学院${rs.xn }学年评奖评优申请表</span></p>
		    <p style="height:20px"></p>
		</center>

  <table class="printtab" > 
    <tr > 
      <td width=60 colspan="4" height="30" align="center"> 姓 名</td> 
	  <td width=90 colspan="6" align="center">${rs.xm }&nbsp;</td> 
	  <td width=60 colspan=4 align="center"> 系部</td> 
      <td width=105 colspan=7 align="center">${rs.xymc }</td> 
      <td width=75 colspan=5 align="center"> 班级</td> 
      <td width=90 colspan=6 align="center">${rs.bjmc }</td> 
	  <td width=75 colspan=5 align="center"> 学号</td> 
	  <td width=105 colspan=9 align="center">${rs.xh }&nbsp;</td>
	   
    </tr> 
    <tr >
   	  <td rowspan="2" width=70 colspan="4" valign=center>
   	  	<p align="center">
			申报奖项
		 </p>
   	  </td>
   	  <td rowspan="2" colspan="10" width=145 valign="middle">
   	  	<p align="center">
   	  	${rs.xmmc}
   	  	</p>
   	  </td>
   	  <td colspan="10" width=150 valign=center>
   	  	<p align="center">
			综合素质测评班级排名
		 </p>
   	  </td>
      <td width=60 colspan="4" valign="middle">
   	  	<p align="center">
   	  	${rs.zcfbjpm }
   	  	</p>
   	  </td>
   	  <td rowspan="2" colspan="5" width=75  valign=center>
   	  	<p align="center">
			联系方式
		 </p>
   	  </td>
   	  <td rowspan="2" colspan="15" width=165 valign=center>
   	  	<p align="center">
   	  	${rs.sjhm}
   	  	</p>
   	  </td>
    </tr>
    <tr>
      <td colspan="10"  valign=center>
   	  	<p align="center">
			成绩班级排名
		 </p>
   	  </td>
      <td width=60 colspan="4" valign="middle">
   	  	<p align="center">
   	  	${rs.zyfbjpm }
   	  	</p>
   	  </td>
    </tr>
    <tr> 
	<td width=30 height="30" rowspan="8" colspan=2  style="text-align:center"> 本<br>学<br>年<br>学<br>习<br>成<br>绩</td> 
	<td width=165 colspan=11 height="30" > 课 程 名 称</td> 
	<td width=45 colspan=3 > 成绩</td> 
	<td width=165 colspan=11 > 课 程 名 称</td> 
	<td width=45 colspan=3> 成绩</td> 
	<td width=165 colspan=11 > 课 程 名 称</td> 
	<td width=45 colspan=7> 成绩</td>
	</tr>
	<tr>
	  <td width=165 colspan=11 height="30" >${rs.kcmc0 }&nbsp;</td> 
	  <td width=45 colspan=3 > ${rs.cj0 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc1 }&nbsp;</td> 
  	  <td width=45 colspan=3> ${rs.cj1 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc2 }&nbsp;</td> 
	  <td width=45 colspan=7> ${rs.cj2 }</td> 
	</tr>
	<tr>
	  <td width=165 colspan=11 height="30" >${rs.kcmc3 }&nbsp;</td> 
	  <td width=45 colspan=3 > ${rs.cj3 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc4 }&nbsp;</td> 
  	  <td width=45 colspan=3> ${rs.cj4 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc5 }&nbsp;</td> 
	  <td width=45 colspan=7> ${rs.cj5}</td> 
	</tr>
	<tr>
	  <td width=165 colspan=11 height="30" >${rs.kcmc6 }&nbsp;</td> 
	  <td width=45 colspan=3 > ${rs.cj6 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc7 }&nbsp;</td> 
  	  <td width=45 colspan=3> ${rs.cj7}</td> 
	  <td width=165 colspan=11 > ${rs.kcmc8 }&nbsp;</td> 
	  <td width=45 colspan=7> ${rs.cj8}</td> 
	</tr>
	<tr>
	  <td width=165 colspan=11 height="30" >${rs.kcmc9 }&nbsp;</td> 
	  <td width=45 colspan=3 > ${rs.cj9 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc10 }&nbsp;</td> 
  	  <td width=45 colspan=3> ${rs.cj10 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc11 }&nbsp;</td> 
	  <td width=45 colspan=7> ${rs.cj11 }</td> 
	</tr>
	<tr>
	  <td width=165 colspan=11 height="30" >${rs.kcmc12 }&nbsp;</td> 
	  <td width=45 colspan=3 > ${rs.cj12 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc13 }&nbsp;</td> 
  	  <td width=45 colspan=3> ${rs.cj13 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc14 }&nbsp;</td> 
	  <td width=45 colspan=7> ${rs.cj14 }</td> 
	</tr>
	<tr>
	  <td width=165 colspan=11 height="30" >${rs.kcmc15 }&nbsp;</td> 
	  <td width=45 colspan=3 > ${rs.cj15 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc16}&nbsp;</td> 
  	  <td width=45 colspan=3> ${rs.cj16 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc17 }&nbsp;</td> 
	  <td width=45 colspan=7> ${rs.cj17 }</td> 
	</tr>
	<tr>
	  <td width=165 colspan=11 height="30" >${rs.kcmc18 }&nbsp;</td> 
	  <td width=45 colspan=3 > ${rs.cj18 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc19 }&nbsp;</td> 
  	  <td width=45 colspan=3> ${rs.cj19 }</td> 
	  <td width=165 colspan=11 > ${rs.kcmc20 }&nbsp;</td> 
	  <td width=45 colspan=7> ${rs.cj20 }</td> 
	</tr>
<tr>
      <td width=60 colspan="4"  valign=center>
   	  	<p align="center">
			平均分
		 </p>
   	  </td>
   	  <td colspan="12" valign=center>
   	  	<p align="center">
   	  	${rs.pjf }
   	  	</p>
   	  </td>
   	  <td width=60  colspan="4" valign=center>
   	  	<p align="center">
			最低分
		 </p>
   	  </td>
   	  <td colspan="12" valign=center>
   	  	<p align="center">
   	  	${rs.zdf }
   	  	</p>
   	  </td>
   	  <td width=75 colspan="5" valign=center>
   	  	<p align="center">
			补考门数
		 </p>
   	  </td>
   	  <td valign=center colspan="11" width=95>
   	  	<p align="center">
   	  	${rs.bjg }
   	  	</p>
   	  </td>
    </tr>
    <tr>
      <td  colspan="4" align="center">
                      申<br/>
                      请<br/>
                      理<br/>
                      由<br/>
      </td>
      <td width=600  valign=top colspan="44">
          <p  style="height:30px"></p>
          <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 350px;_height: 300px;font-size:14px" >&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</textarea>
      
          <p align="right">申请人:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td>  
    </tr>
    </table>
    <div style='page-break-before:always;'>&nbsp;</div>
    <table class="printtab">
    <tr > 
      <td width=60  align=center >
                      班级<br/>
                      考核<br/>
                      小组<br/>
                      意见<br/>
      </td> 
      <td width=610  valign=top >
          <p  style="height:20px"></p>
          <p style="height:160px"></p>
          <p align="right">班主任:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
   <tr > 
      <td width=60  align=center >
                      系部<br/>
                      审核<br/>
      </td> 
      <td width=610  valign=top >
          <p  style="height:20px"></p>
          <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 245px;_height: 180px;font-size:14px" >&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }</textarea>
          <p align="right">院（系）党总支盖章:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
      </td> 
    </tr> 
   <tr > 
      <td width=69  align=center >
                      学生处<br/>审批
      </td> 
      <td width=621  valign=top >
          <p  style="height:20px"></p>
          <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 245px;_height: 180px;font-size:14px" >&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }</textarea>
          <p align="right">盖章（签名）:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
      </td> 
    </tr> 
  </table> 
	</body>
</html>
