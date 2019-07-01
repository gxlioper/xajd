<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<!-- 头文件 -->
<html:html>
<body>
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		  <p align=center style='text-align:center'><b><span
style='font-size:18.0pt;font-family:黑体'>华中农业大学家庭经济困难学生认定申请表</span></b></p> 
			<table width="100%" id="rsT" class="printstyle" align="center">
      <tr  style="height: 45px" align="center"> 
        <td width="5%" rowspan=4> <p align=center style='
  text-align:center'><b><span
  style='font-family:新宋体'>学<br/>生<br/>本<br/>人<br/>基<br/>本<br/>情<br/>况</span></b></p></td> 
        <td width="9%"> <p align=center style='text-align:center'><span
  style='font-family:新宋体'>姓&nbsp;名</td> 
        <td width="18%"  colspan=3>&nbsp;${rs.xm } </td> 
        <td width="6%"  colspan=2> <p align=center style='text-align:center'><span
  style='font-family:新宋体'>性&nbsp;别</span></p></td> 
        <td width="11%"  colspan=2>&nbsp;${rs.xb } </td> 
        <td width="8.5%"  colspan=2> <p align=center style='text-align:center'><span
  style='font-family:新宋体'>出生年月</span></p></td> 
        <td width="14%"  colspan=2>&nbsp; ${rs.csrq }</td> 
        <td width="7%" >民&nbsp;族</td> 
        <td width="7%" >${rs.mzmc }</td>
      </tr> 
      <tr  style="height: 45px" align=center> 
        <td > 身份证<br/>号码</td> 
        <td  colspan=5>&nbsp;${rs.sfzh } </td> 
        <td  colspan=2> <p align=center style='text-align:center'><span
  style='font-family:新宋体'>政治面貌</span></p></td> 
        <td  colspan=2>&nbsp;${rs.zzmmmc } </td> 
        <td  colspan=2> <p align=center style='text-align:center'><span
  style='font-family:新宋体'>家庭人均<br/>年收入</span></p></td> 
        <td   colspan=2> <p><span style='font-family:新宋体'>${rs.jtrjsr }元</span></p></td> 
      </tr> 
      <tr  style="height: 45px" align=center> 
        <td >学&nbsp;院</td> 
        <td colspan=3 align=center >${rs.xymc } </td> 
        <td colspan=2 align=center>专&nbsp;业</td> 
        <td colspan=4>${rs.zymc }</td> 
        <td width="7%">学&nbsp;号</td> 
        <td colspan=3>&nbsp; ${rs.xh }</td> 
      </tr> 
      <tr  style="height: 45px" align=center> 
        <td> <p align=center style='text-align:center'><span
  style='font-family:新宋体'>年&nbsp;级</td> 
        <td  width="13%">&nbsp;${rs.nj } </td> 
        <td  colspan=2 width="5%"> <p align=center style='text-align:center'><span
  style='font-family:新宋体'>班</span></p></td> 
        <td  colspan=2>&nbsp;${rs.bjmc } </td> 
        <td  colspan=2> <p align=center style='text-align:center'><span
  style='font-family:新宋体'>在校联系电话</span></p></td> 
        <td  colspan=6>&nbsp; ${rs.lxdh }</td> 
      </tr> 
      <tr  > 
        <td style="height: 210px" > <p align=center style='text-align:center'><b>
        <span style='font-family:新宋体'>
        	学<br/>生<br/>陈<br/>述<br/>申<br/>请<br/>认<br/>定<br/>理<br/>由
        </span></b></p></td> 
       
        <td colspan=14 valign="bottom" align=right> 
          <p align="left"><span lang=EN-US style='font-family:新宋体;height:100px;text-align: left'  >${rs.sqly }</span></p>
           <p><span style='font-family:新宋体;text-align: right'  >学生签字：<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></span>年<b><u><span
  lang=EN-US>&nbsp;&nbsp; </span></u></b>月<u><span lang=EN-US>&nbsp;&nbsp; </span></u>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p> 
          <p align="left"><b><span style='font-family:新宋体' >注：可另附详细情况说明。</span></b></p></td> 
      </tr> 
      <tr  style="height: 120px"> 
        <td rowspan=3> <p align=center style='
  text-align:center'><b><span
  style='font-family:新宋体'>民<br/>主<br/>评<br/>议</span></b></p></td> 
        <td  rowspan=3 > <p align=center style='
  text-align:center'><span
  style='font-family:新宋体'>推<br/>荐<br/>档<br/>次</span></p></td> 
        <td colspan=4 style="height: 65px"> A.家庭经济困难□</td> 
        <td rowspan=3  align="center">陈<br/>述<br/>理<br/>由</td> 
        <td colspan=8 rowspan=3 valign="bottom">认定评议小组组长签字：<span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></span>年<u><span
  lang=EN-US>&nbsp;&nbsp;&nbsp; </span></u>月<u><span lang=EN-US>&nbsp;&nbsp;&nbsp; </span></u>日</span></p></td> 
      </tr> 
      <tr  style="height: 65px"> 
        <td colspan=4>B.家庭经济特别困难□</td>
  	  </tr> 
      <tr style="height: 65px">  
        <td colspan=4>C.家庭经济不困难□</td> 
        </tr> 
      <tr  style="height: 250px"> 
        <td> <p align=center style='
  text-align:center'><b><span
  style='font-family:新宋体'>认<br/>定<br/>决<br/>定</span></b></p></td> 
        <td align="center">院（系）<br/>意见</td> 
        <td colspan=6 valign=top> 
          <span style="line-height: 30px">经认定评议小组推荐、院（系）认定工<br/>作组认真审核后，<br/>
          □&nbsp;同意认定评议小组意见。<br/>
          □&nbsp;不同意认定评议小组意见，调整<br/>为<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u><span
  lang=EN-US>&nbsp;</span>。<br/>
          认定工作组组长签字：
        <p align="right"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;<br/>
        （加盖院系公章）&nbsp;&nbsp;</p> </span>
        <td  colspan=2  align=center> 
        学生工<br/>作部<br/>（处）</p>
        </td> 
        <td  colspan=5 valign=top>
         <span style="line-height: 30px">
		经学生所在院（系）提请，本机构认真核<br/>实后，<br/>
		□同意认定工作组意见。<br/>
		□不同意认定工作组意见，调整<br/>&nbsp;&nbsp;&nbsp;&nbsp;为：<u>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。<br/> 
        负责人签字：
        <p align="right">&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;
         <br/>（加盖部门公章）&nbsp;&nbsp;</p></span></td>
      </tr> 
    </table> 
  </div> 
  <p><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-family:黑体'>注：此表填写完整后和《华中农业大学学生家庭情况调查表》一并交评议小组评议认定。</span></b></p> 
	</html:form>
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
</body>
</html:html>
