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
		    <p style="height:150px"></p>
		    <p><span style="font-size:26px;font-family:黑体">天 然 奖 学 金</span></p>
		    <p style="height:50px"></p>
            <p><span style="font-size:22px;font-family:楷体_GB2312">评  审  表</span></p>  
		    <p style="height:250px"></p>
           <table>
		    <tr>
		    <td>
            <p><span style="font-size:22px;font-family:楷体_GB2312">学   校：</span>
            </td>
            <td style="border-bottom:1px solid #070707"  align="center">
            <span style="font-size:22px;font-family:楷体_GB2312">
	               ${xxmc}
            </span>
            </td>
            </tr>
            <tr>
	        <td ><p><span style="font-size:22px;font-family:楷体_GB2312">院   系：</span></td>
	        <td  style="border-bottom:1px solid #070707"  align="center"><span style="font-size:22px;font-family:楷体_GB2312">
	        ${rs.xymc}
	        </span></td>
	        </tr>
	        <tr>
	        <td ><p><span style="font-size:22px;font-family:楷体_GB2312">班   级：</span></td>
	        <td  style="border-bottom:1px solid #070707"  align="center"><span style="font-size:22px;font-family:楷体_GB2312">
	        ${rs.bjmc}
	        </span>
	        </td>
	        </tr>
	        <tr>
	        <td>
	        <p><span style="font-size:22px;font-family:楷体_GB2312">姓   名：</span></td>
	         <td style="border-bottom:1px solid #070707"  align="center"><span style="font-size:22px;font-family:楷体_GB2312;">
	         ${rs.xm}
	        </span></td>
	        </tr>
	        </table>
            <p style="height:200px"></p>
            <p><span style="font-size:18px;font-family:楷体_GB2312">天然奖学金管理委员会</span></p>
            <p style="height:20px"></p>
            <p><span style="font-size:18px;font-family:楷体_GB2312">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</span></p>
            <p style="height:100px"></p>
		</center>
  <table class="printtab">
    <tr > 
      <td width=58  align=center >
         <p align="center">
			姓 名
		 </p>
      </td> 
      <td width=88  align=center >
         <p align="center">
			${rs.xm }
		 </p>
      </td> 
      <td width=65  align=center >
         <p align="center">
			性 别
		 </p>
      </td> 
      <td width=59  align=center >
        <p align="center">
			${rs.xb }
		</p>
      </td> 
      <td width=65  align=center >
        <p align="center">
                                 民族
		</p>
      </td> 
      <td width=68  align=center >
        <p align="center">
			${rs.mzmc }
		</p>
      </td> 
      <td width=60  align=center >
        <p align="center">
			出生<br/>年月
		</p>
      </td> 
      <td width=83  align=center >
        <p align="center">
		    ${rs.csrq }
		</p>
      </td> 
      <td width=106  align=center  rowspan=4 >
      <img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
      </td> 
    </tr> 
    <tr > 
      <td width=58  align=center  >
         <p align="center">
			  政治<br/>面貌
		 </p>
      </td> 
      <td width=88  align=center   >
         <p align="center">
			${rs.zzmmmc }
		 </p>
      
      </td> 
        <td width=58  align=center   >
         <p align="center">
			 学历
		 </p>
      </td> 
      <td width=88  align=center colspan="2" >
         <p align="center">
		 </p>
      
      </td> 
          <td width=58  align=center   >
         <p align="center">
			专业
		 </p>
      </td> 
      <td width=88  align=center colspan="2" >
         <p align="center">
		 </p>
      
      </td> 
      </tr>
      <tr>
      <td width=65  align=center   >
         <p align="center">
		          担任<br/>职务
	     </p>
      </td> 
      <td width=59  align=center   >&nbsp;</td> 
      <td width=65  align=center   >
         <p align="center">
			专业<br/>排名
		 </p>
      </td> 
      <td width=68  align=center  colspan=2 >
         <p align="center">
		 </p>
      </td> 
      <td width=60  align=center >
                                   申请<br/>等级
      </td> 
      <td width=83  align=center colspan="2" >
            &nbsp;
      </td> 
      </tr>
    <tr > 
      <td width=60  align=center >
                                   家庭<br/>地址
      </td> 
      <td width=83  align=center colspan="7" >&nbsp;</td> 
    </tr> 
    <tr > 
      <td width=58  align=center >
                 大<br/>
                 学<br/>
                 期<br/>
                 间<br/>
                 获<br/>
                 奖<br/>
                 情<br/>
                 况<br/>
       </td> 
      <td width=597  align=center  colspan=8 ><p style="height:300px"></p></td> 
    </tr> 
    <tr > 
      <td width=655  align=top  colspan=9 >
          <p  style="height:20px"></p>
          <p align="center"><span style="font-weight:bold; font-size:15px;font-family:宋体;">个人主要事迹</span></p>
          <p  style="height:20px"></p>
          <p align="left"></p>
          <p style="height:400px"></p>
      </td> 
    </tr> 
  </table> 
  <p style="height:30px"></p>
  <table class="printtab"> 
    <tr > 
      <td width=688  valign=top >
          <p  style="height:20px"></p>
          <p align="center"><span style="font-weight:bold; font-size:15px;font-family:宋体;"></span></p>
          <p  style="height:20px"></p>
          <p style="height:900px"></p>
      </td> 
    </tr> 
  </table> 
  <p style="height:40px"></p>
  <table class="printtab"> 
    <tr > 
      <td width=55  align=center >
                      班<br/>
                      级<br/>
                      辅<br/>
                      导<br/>
                      员<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:150px">&nbsp;&nbsp;&nbsp;&nbsp;</p>
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
                      <br/>
                      院<br/>
                      审<br/>
                      核<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:150px">&nbsp;&nbsp;&nbsp;&nbsp;该同学以上所填情况均属实，根据其各方面表现，经<bean:message key="lable.xb" />奖学金评审委员会审核，符合评奖条件，同意该生获得天然奖学金。</p>
          <p align="left">院长签名：                                      
         &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
                           公   章
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
                     天<br/>
                     然<br/>
                      奖<br/>
                      学<br/>
                      金<br/>
                      评<br/>
                      审<br/>
                      委<br/>
                      员<br/>
                      会<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:300px">&nbsp;&nbsp;&nbsp;&nbsp;根据该同学各方面表现，经天然奖学金评审委员会审核，符合评奖条件，同意该生获得天然奖学金。</p>
          <p align="left">评审组长签名：                                                                      
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
                           公   章
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
            <p align="left">1.本表一式三份，表中填写内容要求电脑打印；</p>
            <p align="left">2.学校、院、系、班级不可缩写。</p>
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
