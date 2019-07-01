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
		    <p><span style="font-size:23px;font-family:黑体">普通高校国家奖学金申请审批表</span></p>
		    <p style="height:10px"></p>
			<table>
				<tr>
				<th colspan="8" align="center">(${rs.xn }学年)</th>
				</tr>
				<tr><th colspan="8" align="center"> <p style="height:5px"></p></th></tr>
				<tr>
				  <th width=40  height="30" align="center"> 学校：</th> 
				  <td width=125  align="left">浙江旅游职业学院&nbsp;</td> 
				  <th width=40  align="center"> 院系：</th> 
		  	  	  <td width=125 align="left" >${rs.xymc }</td> 
		  	      <th width=40 align="center" > 专业：</th> 
		          <td width=125 align="left" >${rs.zymc }</td> 
				  <th width=40 align="center" > 班级：</th> 
				  <td width=125 align="left">${rs.bjmc }&nbsp;</td>
				</tr>
			</table>
		</center>
		<p style="height:10px"></p>
  <table class="printtab" > 
    <tr > 
      <th rowspan="4" colspan="2" width=70>
      	基<br/>本<br/>情<br/>况<br/>
      </th>
      <td colspan="3" width=70 align="center">姓名</td>
      <td colspan="4" width=90 align="center">${rs.xm}</td>
      <td colspan="3" width=60 align="center">性别</td>
      <td colspan="4" width=90 align="center">${rs.xb }</td>
      <td colspan="3" width=70 align="center">出生年月</td>
      <td colspan="4" width=90 align="center">${rs.csrq }</td>
    </tr> 
    <tr>
      <td colspan="3" width=70 align="center">学号</td>
      <td colspan="4" width=90 align="center">${rs.xh}</td>
      <td colspan="3" width=60 align="center">民族</td>
      <td colspan="4" width=90 align="center">${rs.mzmc }</td>
      <td colspan="3" width=70 align="center">入学时间</td>
      <td colspan="4" width=90 align="center">${rs.rxrq }</td>
    </tr>
    <tr>
      <td colspan="3" width=70 align="center">身份证号</td>
      <td width=25>${rs.sfzh0 }</td>
      <td width=25>${rs.sfzh1 }</td>
      <td width=25>${rs.sfzh2 }</td>
      <td width=25>${rs.sfzh3 }</td>
      <td width=25>${rs.sfzh4 }</td>
      <td width=25>${rs.sfzh5 }</td>
      <td width=25>${rs.sfzh6 }</td>
      <td width=25>${rs.sfzh7 }</td>
      <td width=24>${rs.sfzh8 }</td>
      <td width=24>${rs.sfzh9 }</td>
      <td width=24>${rs.sfzh10 }</td>
      <td width=24>${rs.sfzh11 }</td>
      <td width=24>${rs.sfzh12 }</td>
      <td width=24>${rs.sfzh13 }</td>
      <td width=24>${rs.sfzh14 }</td>
      <td width=24>${rs.sfzh15 }</td>
      <td width=24>${rs.sfzh16 }</td>
      <td width=24>${rs.sfzh17 }</td>
    </tr>
    <tr>
      <td colspan="3" align="center">政治面貌</td>
      <td colspan="5" align="center">${rs.zzmmmc }</td>
      <td colspan="4" align="center">联系电话</td>
      <td colspan="9" align="center">${rs.sjhm }</td>
    </tr>
    <tr>
      <th colspan="2" align="center" width=70>
		学<br/>习<br/>情<br/>况<br/>
      </th>
      <td width=590  valign=top colspan="21">
      	<p  >本学年必修课程数<U>&nbsp;&nbsp;${rs.bxkms }&nbsp;&nbsp;</U>门，其中，优秀<U>&nbsp;&nbsp;${rs.yxkms }&nbsp;&nbsp;</U>门，良好<U>&nbsp;&nbsp;${rs.lhkms }&nbsp;&nbsp;</U>门<br/>
      		成绩排名（专业或年级）：<U>&nbsp;&nbsp;${rs.zyfnjzypm }&nbsp;&nbsp;</U>（名次/总人数）<br/>
      		综合考评成绩<U>&nbsp;&nbsp;${rs.zd1 }&nbsp;&nbsp;</U>分，排名<U>&nbsp;&nbsp;${rs.zcfnjzypm }&nbsp;&nbsp;</U>（名次/总人数）（如无此项，填写“无”）<br/>
      	</p>
      </td>
    </tr>
    <tr>
      <th rowspan="2" colspan="2" align="center" width=70>
		获<br/>奖<br/>情<br/>况<br/>
      </th>
      <td width=590  valign=top colspan="21">
      	<p>主要奖项：</p>
      	<textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px" >&nbsp;&nbsp;&nbsp;&nbsp;${rs.kzzd1 }</textarea>
      </td>
    </tr>
    <tr>
      <td height="30" width=590  valign="middle" colspan="21">其中，院级奖励<U>&nbsp;&nbsp;${rs.kzzd2 }&nbsp;&nbsp;</U>项；
      校级奖励<U>&nbsp;&nbsp;${rs.kzzd3 }&nbsp;&nbsp;</U>项；省级以上奖励<U>&nbsp;&nbsp;${rs.kzzd4 }&nbsp;&nbsp;</U>项</td>
    </tr>
    
    <tr>
      <th  colspan="2" align="center" width=70>
                      申<br/>
                      请<br/>
                      理<br/>
                      由<br/>
      </th>
      <td width=590  valign=top colspan="21">
          <p  style="height:10px"></p>
          <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px" >&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</textarea>
          <p align="right">申请人签字:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:5px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:5px"></p>
      </td>  
    </tr>
    </table>
    <div style='page-break-before:always;'>&nbsp;</div>
    <table class="printtab">
    <tr > 
      <th width=70  align=center >
      年级<br/>（专业）<br/>推荐<br/>意见<br/>
      </th> 
      <td width=590  valign=top >
          <p  style="height:20px"></p>
          <p style="height:180px"></p>
          <p >推荐人:
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
		      行政职务：
          </p>
          <p style="height:10px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
   <tr > 
      <th width=70  align=center >
                      院<br/>
                      （系）<br/>
                      意<br/>
                      见<br/>
      </th> 
      <td width=590  valign=top >
          <p  style="height:20px"></p>
          <textarea rows="" cols="" class="text_nobor" style="word-break:break-all;width:97%;overflow:hidden;min-height: 250px;_height: 300px;font-size:14px" >&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }</textarea>
          <p align="right">
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;（公章）
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
   <tr > 
      <th width=70  align=center >
                      学<br/>校<br/>意<br/>见<br/>
      </th> 
      <td width=590  valign=top >
          <p  style="height:20px"></p>
          <p style="height:180px" > &nbsp;&nbsp;&nbsp;&nbsp;	经评审，并在
          <U>&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;</U>范围内公示
          <U>&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;</U>
          	天，无异议，现同意该同学获得本学年国家奖学金</p>
          <p align="right">
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;（公章）
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:10px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:20px"></p>
      </td> 
    </tr> 
  </table> 
	</body>
</html>
