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
		    <p><span style="font-size:18px;font-family:黑体">${rs.xmmc }奖学金申请表</span></p>
		    <p style="height:50px"></p>
		</center>
  <table border="0" width="652px">
  <tr>
  <td></td>
  <td width="652px" align="right">填表日期：&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</td>
  </tr>
  </table>		
  <table class="printtab" > 
    <tr > 
      <td width=58  valign=center >
         <p align="center">
			姓 名
		 </p>
      </td> 
      <td width=89  valign=center >
         <p align="center">
			${rs.xm }
		 </p>
      </td> 
      <td width=47  valign=center >
         <p align="center">
			性 别
		 </p>  
      </td> 
      <td width=50  valign=center >
         <p align="center">
			${rs.xb }
		 </p>
      </td> 
      <td width=65  valign=center >
        <p align="center">
			  政治面貌
		 </p>
      </td> 
      <td width=63  valign=center >
         <p align="center">
			${rs.zzmmmc }
		 </p>
      </td> 
      <td width=63  valign=center >
         <p align="center">
			专业名次
		 </p>
      </td> 
      <td width=72  valign=center >&nbsp;</td> 
      <td width=151  valign=center  colspan=2  rowspan=3 >
      <img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
      </td> 
    </tr> 
    <tr > 
      <td width=58  valign=center >
         <p align="center">
			学号
		 </p>
      </td> 
      <td width=89  valign=center >
         <p align="center">
			${rs.xh }
		 </p>
      </td> 
      <td width=47  valign=center >
          <p align="center">
                                 民族
		  </p>
      </td> 
      <td width=50  valign=center >
          <p align="center">
			${rs.mzmc }
		  </p>
      </td> 
      <td width=65  valign=center >
           <p align="center">
			出生年月
		   </p>
      </td> 
      <td width=63  valign=center >
           <p align="center">
		    ${rs.csrq }
		   </p>
      </td> 
      <td width=63  valign=center >
          <p align="center">
		               现任职务
		  </p>
      </td> 
      <td width=72  valign=center >&nbsp;</td> 
    </tr> 
    <tr > 
      <td width=58  valign=center >
      　　　　<p align="center">
                                   专业班级
         </p>
      
      </td> 
      <td width=186  valign=center  colspan=3 >&nbsp;</td> 
      <td width=65  valign=center >
          <p align="center">
			联系电话
		   </p>
      </td> 
      <td width=198  valign=center  colspan=3 >&nbsp;</td> 
    </tr> 
    <tr > 
      <td width=58  valign=center >
            <p align="center">
                                         家庭地址
            </p>
      </td> 
      <td width=315  valign=center  colspan=5 >&nbsp;</td> 
      <td width=63  valign=center >
          <p align="center">
                                         邮编
          </p>
      </td> 
      <td width=72  valign=center >&nbsp;</td> 
      <td width=75  valign=center >
          <p align="center">
                                         申报等级
          </p>
      </td> 
      <td width=75  valign=center >&nbsp;</td> 
    </tr> 
    <tr > 
      <td   align=center >
                  大<br/>
                 学<br/>
                 期<br/>
                 间<br/>
                 获<br/>
                 奖<br/>
                 情<br/>
                 况<br/>
      </td> 
      <td width=602  valign=center  colspan=9 ><p style="height:320px"></p></td> 
    </tr> 
    <tr > 
      <td  align=center >
       主<br/>
		要<br/>
		社<br/>
		会<br/>
		活<br/>
		动<br/>
		经<br/>
		历<br/>
      </td> 
      <td width=602  valign=top  colspan=9 >
      <p>（包括参加各类社会活动及表现。） </p>
      <p style="height:330px"></p>
      </td> 
    </tr> 
    </table>
    <p style="height:30px"></p>
    <table class="printtab">
    <tr > 
      <td width=55  align=center >
                      辅<br/>
                      导<br/>
                      员<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=613  valign=top >
          <p  style="height:40px"></p>
          <p style="height:160px"></p>
          <p align="right">签名:
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
   <tr > 
      <td width=55  align=center >
                      学<br/>
                      院<br/>
                      审<br/>
                      核<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=613  valign=top >
          <p  style="height:30px"></p>
          <p style="height:160px">	&nbsp;&nbsp;&nbsp;&nbsp;</p>
          <p align="right">签名（盖章）:
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
   <tr > 
      <td width=55  align=center >
                      学<br/>
                      校<br/>
                      审<br/>
                      核<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=613  valign=top >
          <p  style="height:40px"></p>
          <p style="height:160px">&nbsp;&nbsp;&nbsp;&nbsp;	</p>
          <p align="right">签名（公章）:
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
    <tr > 
      <td width=55  align=center >备注</td> 
      <td width=600  align=center >
            <p style="height:20px"></p>
            <p align="left">1、此表内容请如实认真填写；</p>
            <p align="left">2、本表一式三份，表中填写内容要求电脑录入，正反面打印。</p>
            <p style="height:20px"></p>
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
