<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<!-- 头文件 -->
<html>
<body>
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->


  <p align=center><b><span
style='font-size:22.0pt;font-family:宋体;letter-spacing:1.0pt'>（${xn}学年）国家奖学金申请审批表</span></b></p> 
  <p align=center><b>学校：</b>${xxmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>院系：</b>${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>学号：${rs.xh }</b></p> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table width="100%" class="printstyle" align="center">
    <tr style="height:45px"> 
      <td width="8%" rowspan=4 align="center"><B>基本<br/>情况</B></td> 
      <td width="12%" align="center">姓名</td> 
      <td width="15%" align="center" colspan=4 >&nbsp;${rs.xm } </td> 
      <td width="8%" align="center" colspan=4 >性别</td> 
      <td align="center" colspan=5 >&nbsp;${rs.xb } </td> 
      <td align="center"  colspan=4  >出生年月</td> 
      <td align="center"  colspan=6 >&nbsp;${rs.csrq } </td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center" >政治面貌</td> 
      <td align="center" colspan=4 >&nbsp;${rs.zzmmmc } </td> 
      <td align="center" colspan=4 >民族</td> 
      <td align="center" colspan=5 >&nbsp;${rs.mzmc } </td> 
      <td align="center" colspan=4 >入学时间</td> 
      <td align="center" colspan=6 >&nbsp;${rs.rxrq } </td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center">专业</td> 
      <td align="center" colspan=4 >&nbsp; ${rs.zymc }</td> 
      <td align="center" colspan=4 >学制</td> 
      <td align="center" colspan=5 >&nbsp;${rs.xz } </td> 
      <td align="center" colspan=4 >联系电话</td> 
      <td align="center" colspan=6 >&nbsp;${rs.lxdh } </td> 
    </tr> 
    <tr style="height:45px"> 

      <td align="center" >身份证号</td> 
      <td align="center" width=4.2% id="s0">&nbsp; </td> 
      <td align="center" width=4.2% id="s1">&nbsp; </td> 
      <td align="center" width=4.2% id="s2">&nbsp; </td> 
      <td align="center" width=4.2% id="s3" colspan=2 >&nbsp; </td> 
      <td align="center" width=4.2% id="s4">&nbsp; </td> 
      <td align="center" width=4.2% id="s5">&nbsp; </td> 
      <td align="center" width=4.2% id="s6" colspan=2 >&nbsp; </td> 
      <td align="center" width=4.2% id="s7" colspan=2 >&nbsp; </td> 
      <td align="center" width=4.2% id="s8">&nbsp; </td> 
      <td align="center" width=4.2% id="s9" colspan=2 >&nbsp; </td> 
      <td align="center" width=4.2% id="s10">&nbsp; </td> 
      <td align="center" width=4.2% id="s11">&nbsp; </td> 
      <td align="center" width=4.2% id="s12" colspan=2 >&nbsp; </td> 
      <td align="center" width=4.2% id="s13">&nbsp; </td> 
      <td align="center" width=4.2% id="s14">&nbsp; </td> 
      <td align="center" width=4.2% id="s15">&nbsp; </td> 
      <td align="center" width=4.2% id="s16">&nbsp; </td> 
      <td align="center" width=4.2% id="s17">&nbsp; </td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center" rowspan=2 ><B>学习<br/>情况</B></td> 
      <td align="center" colspan=11 >成绩排名：<u>&nbsp;&nbsp;&nbsp; &nbsp;</u><u>/</u><u>&nbsp; &nbsp;</u>（名次/总人数）</span></p></td> 
      <td align="center" colspan=13 >实行综合考评排名：是□；否□</td> 
    </tr> 
    <tr style="height:45px"> 
      <td colspan=11 align="center">必修课<u>${rs.bxkms}</u>门，其中及格以上<u>　　</u>门</td> 
      <td colspan=13 align="center">如是，排名：<u>&nbsp;&nbsp;&nbsp;&nbsp;</u><u>/&nbsp;&nbsp;&nbsp;&nbsp;（名次/总人数）</td> 
    </tr> 
    <tr style="height:45px"> 
      <td rowspan=5 align="center" >
      <B>大学<br/>期间<br/>主要<br/>获奖<br/>情况</B></td> 
      <td align="center" >日期</td> 
      <td align="center"  colspan=13 >奖项名称</td> 
      <td align="center" colspan=10 >颁奖单位</td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center">&nbsp;${rs.hjrq1} </td> 
      <td align="center" colspan=13 >&nbsp;${rs.hjmc1}</td> 
      <td align="center" colspan=10 >&nbsp;${rs.bjdw1}</td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center" >&nbsp;${rs.hjrq2} </td> 
      <td align="center" colspan=13 >&nbsp;${rs.hjmc2} </td> 
      <td align="center" colspan=10 >&nbsp;${rs.bjdw2} </td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center"  >&nbsp;${rs.hjrq3} </td> 
      <td align="center"   colspan=13 >&nbsp;${rs.hjmc3}  </td> 
      <td align="center"  colspan=10 >&nbsp;${rs.bjdw3} </td> 
    </tr> 
    <tr style="height:45px"> 
      <td align="center">&nbsp; ${rs.hjrq4}</td> 
      <td align="center" colspan=13 >&nbsp;${rs.hjmc4}  </td> 
      <td align="center" colspan=10 >&nbsp;${rs.bjdw4} </td> 
    </tr> 
    <tr style="height:320px"> 
      <td align="center" ><B>申请<br/>理由</B></td> 
      <td colspan=24 align="right" valign="bottom" >
      	<p align="left" style="vertical-align: top;height:200px" >${rs.sqly }</p>
      	申请人签名(手签)： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><br/><br/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日</td> 
    </tr> 
    <tr height=0> 
      <td width=55 ></td> 
      <td width=84 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=21 ></td> 
      <td width=8 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=6 ></td> 
      <td width=23 ></td> 
      <td width=1 ></td> 
      <td width=28 ></td> 
      <td width=29 ></td> 
      <td width=9 ></td> 
      <td width=20 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=6 ></td> 
      <td width=23 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
      <td width=29 ></td> 
    </tr> 
  </table> 
 	<br/><br/><br/>
   <table width="100%" class="printstyle" align="center" style="font-size:12.0pt;font-family:宋体;">
    <tr  style="height: 300px"> 
      <td width="7.5%" align="center"><B>推荐<br/>理由</B><br/>(100<br/>字)</td> 
      <td style="vertical-align: text-bottom">
      <p align="right" style="vertical-align:bottom;">推荐人（辅导员或班主任）签名：&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
      <p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp; &nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
      </td> 
    </tr> 
    <tr  style="height: 300px"> 
      <td align="center"><B>院<br/><br/>（系）<br/><br/>意<br/><br/>见</B></td> 
      <td width=605 ><p style="height: 200px"><br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;经审查，以上情况属实。经<bean:message key="lable.xb" />评选，在全院范围内公示
      <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>天，无异议，<br/>现拟确定该同学为2010-2011学年国家奖学金候选人。<br/></p>
        <p align="right" style="vertical-align:bottom;">院系主管学生工作领导签名：&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</p>
        <p align="right" style="vertical-align:bottom;">（院系公章）&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
        <p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp; &nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p></td>
    </tr> 
    <tr style="height: 300px"> 
      <td align="center"><B>学<br/><br/>校<br/><br/>意<br/><br/>见</B></td> 
     <td width=605 ><p style="height: 200px"><br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在校内公示
      <u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>个工作日，无异议，现报请批准该同学获得国<br/>家奖学金。<br/></p>
        <p align="right" style="vertical-align:bottom;">（学校公章）&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
        <p align="right" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp; &nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p></td>
    </tr> 
  </table> 
  <p align="right">制表：全国学生资助管理中心&nbsp;&nbsp;2010年版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
 
  <div algin="left" style='layout-grid:15.6pt;line-height:28.0pt;'> 
  <p align=center style='
text-align:center;
line-height:150%;layout-grid-mode:char;'><b><span style='font-size:16.0pt;line-height:
150%;font-family:仿宋_GB2312;color:#333333'>《国家奖学金申请审批表》填写说明</span></b></p> 
  <span style='font-size:14.0pt;
font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;各<bean:message key="lable.xb" />从学工处网站下载或复印《国家奖学金申请审批表》，组织人员认真填写。</span><br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;1. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格为一页，正反两面，不得随意增加页数。</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312;'>表格填写应当字迹清晰、信息完整，</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>不得涂改数据或出现空白项，数字全部使用阿拉伯数字，日期书写格式<span
lang=EN-US>****</span>年<span lang=EN-US>**</span>月<span lang=EN-US>**</span>日。（打印使用宋体五号字体）</span><br/> 
 <span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;2. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格标题中学年的填写为评审工作开始所在学年的上一学年。如<span
lang=EN-US>2010</span>年秋季学期填表，应填写“<span lang=EN-US>2009</span>－<span
lang=EN-US>2010</span>学年”，以此类推。</span><br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;3. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格中学习成绩排名范围指本年级本专业，综合考评成绩排名范围指班级，必须注明评选范围的总人数。</span><br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;4. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格中“申请理由”栏的填写应当全面详实，</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312;'>能够如实反映学生学习</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>成绩优异</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312;'>、社会实践、创新能力、综合素质等方面特别突出。</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>字数控制在<span lang=EN-US>200</span>字左右。</span><br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;5. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格中“推荐意见”栏的填写应当简明扼要，字数控制在<span
lang=EN-US>100</span>字左右。推荐人必须是申请学生的辅导员或班主任，其他人无权推荐。</span><br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;6. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>表格中凡需签名之处，必须由相关人员亲手签写。不得由他人代写推荐意见或签名。</span> <br/> 
  <span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;7. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>“院（系）意见”一栏中加盖院（系）行政公章。</span><br/> 
 <span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;8. </span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>学生获奖证书等证明材料只需经过<bean:message key="lable.xb" />审查，不需随表报送，但要做好备案备查。</span><br/> 
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
