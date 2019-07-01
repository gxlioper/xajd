<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
		    <p><span style="font-size:18px;font-family:黑体">天津市普通高校优秀学生推荐登记表</span></p>
		    <p style="height:50px"></p>
		
  <table border="0" width="652px">
  <tr>
  <td align="left">学校名称</td>
  <td align="left">${xxmc }</td>
  <td  align="right">推荐受奖励类别</td>
  <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
  </table>	
  </center>
   <table class="printtab"> 
    <tr align="center"> 
      <td width=91 colspan=2 nowrap class="Normal"> <b>姓名</b></td> 
      <td width=94 nowrap class="Normal">${rs.xm }</td> 
      <td width=59 nowrap class="Normal"> <b>性别</b></td> 
      <td width=99 colspan=3 nowrap class="Normal">${rs.xb } </td> 
      <td width=84 colspan=2 nowrap class="Normal"> <b>出生年月</b></td> 
      <td width=96 nowrap class="Normal">${rs.csrq }</td> 
      <td width=132 rowspan=4 nowrap class="Normal"> 
      <img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
      </td> 
    </tr> 
    <tr align="center"> 
      <td width=91 colspan=2 nowrap class="Normal"> <b>年级</b></td> 
      <td width=94 nowrap class="Normal">${rs.nj }</td> 
      <td width=59 nowrap class="Normal"> <b>系</b></td> 
      <td width=99 colspan=3 nowrap class="Normal">${rs.xymc } </td> 
      <td width=84 colspan=2 nowrap class="Normal"> <b>专业</b></td> 
      <td width=96 nowrap class="Normal">${rs.zymc } </td> 
    </tr> 
    <tr align="center"> 
      <td width=91 colspan=2 nowrap class="Normal"> <b>社会职务</b></td> 
      <td width=94 nowrap class="Normal">&nbsp; </td> 
      <td width=59 nowrap class="Normal"> <b>特长</b></td> 
      <td width=99 colspan=3 nowrap class="Normal">${rs.tc } </td> 
      <td width=84 colspan=2 nowrap class="Normal"> <b>政治面貌</b></td> 
      <td width=96 nowrap class="Normal">${rs.zzmmmc } </td> 
    </tr> 
    <tr align="center"> 
      <td width=91 colspan=2 nowrap class="Normal"> <b>通讯地址</b></td> 
      <td width=252 colspan=5 nowrap class="Normal">&nbsp; ${rs.jtdz }</td> 
      <td width=84 colspan=2 nowrap class="Normal"> <b>邮编</b></td> 
      <td width=96 nowrap class="Normal">${rs.jtyb } </td> 
    </tr> 
    <tr> 
      <td width=91 colspan=2 nowrap class="Normal" align="center"> 
      <b>
               主<br/>
               要<br/>
               事<br/>
               迹</b>
      </td> 
      <td width=564 colspan=9 valign=top nowrap class="Normal"> <p style="height:200px"></p></td> 
    </tr> 
    <tr align="center"> 
      <td width=288 colspan=5 class="Normal"> <b>学年总成绩在年级或专业排序位置</b></td> 
      <td width=367 colspan=6 nowrap class="Normal">&nbsp; </td> 
    </tr> 
    <tr align="center"> 
      <td width=288 colspan=5 class="Normal"> <b>学年综合测评在年级或专业排序位置</b></td> 
      <td width=367 colspan=6 nowrap class="Normal">&nbsp; </td> 
    </tr> 
    <tr  height="200px"> 
      <td width=77 nowrap class="Normal" align="center"> <b>
                  学<br/>
                  术<br/>
                  科<br/>
                  技<br/>
                  成<br/>
                  果<br/>
                  简<br/>
                  述</b></td> 
      <td width=579 colspan=10 valign=top nowrap class="Normal"> </td> 
    </tr> 
    <tr > 
      <td  rowspan=3 nowrap class="Normal" align="center" > 
      <b>
                   学<br/>
                   校<br/>
                   意<br/>
                   见<br/>
      </b>
      </td> 
      <td colspan="5">
      <p style="height:180px"></p>
         <p align="right">公章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
         <p align="right">&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
      </td>
      <td  colspan=2 rowspan=3 nowrap class="Normal" align="center" > 
      <b>
                  市<br/>
                  评<br/>
                  委<br/>
                  会<br/>
                  意<br/>
                  见<br/></b></td> 
       <td colspan="3">
         <p style="height:180px"></p>
         <p align="right">公章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
         <p align="right">&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日 </p>
       </td>
    </tr> 
  </table> 
    <table align="center">
    <tr>
    <td width="650">
    <div align="left">
                           注：此表一式两份，其中一份学生部门保存，一份存入本人档案。
    </div>
    </td>
    </tr>
    </table>
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
		</div>
	</body>
</html>
