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
		    <p><span style="font-size:26px;font-family:楷体_GB2312">纺织之光科技教育基金会</span></p>
		    <p style="height:50px"></p>
            <p><span style="font-size:22px;font-family:楷体_GB2312">学生奖评审表</span></p>  
		    <p style="height:300px"></p>
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
	        <td>
	        <p><span style="font-size:22px;font-family:楷体_GB2312">姓   名：</span></td>
	         <td style="border-bottom:1px solid #070707"  align="center"><span style="font-size:22px;font-family:楷体_GB2312;">
	         ${rs.xm}
	        </span></td>
	        </tr>
	        </table>
            <p style="height:200px"></p>
            <p><span style="font-size:18px;font-family:楷体_GB2312">纺织之光科技教育基金会</span></p>
            <p style="height:20px"></p>
            <p><span style="font-size:18px;font-family:楷体_GB2312">   年   月   日</span></p>
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
      <td width=79  align=center >
        <p align="center">
			${rs.xb }
		</p>
      </td> 
      <td width=65  align=center >
        <p align="center">
                                出生日期
		</p>
      </td> 
      <td width=68  align=center >
        <p align="center">
			${rs.csrq }
		</p>
      </td> 
      <td width=106  align=center  rowspan=3 >
      <img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
      </td> 
    </tr> 
    <tr > 
      <td width=58  align=center >
         <p align="center">
			  民族
		 </p>
      </td> 
      <td width=88  align=center  >
         <p align="center">
			${rs.mzmc }
		 </p>
      
      </td> 
      <td width=65  align=center  >
         <p align="center">
		          政治<br/>面貌
	     </p>
      </td> 
      <td   align=center  >${rs.zzmmmc }</td> 
      <td width=65  align=center  >
         <p align="center">
			入学<br/>日期
		 </p>
      </td> 
      <td width=88  align=center  >
         <p align="center">
			${rs.rxrq }
		 </p>
      </td> 
    </tr> 
    <tr > 
      <td width=60  align=center >
                                     院系
      </td> 
      <td width=83  align=center  colspan="2">${xymc }</td> 
       <td   align=center >
                                     专业
      </td> 
      <td width=83  align=center colspan="2">${zymc }</td> 
    </tr> 
    <tr > 
      <td align="center">
                      担任 <br/>
                      社会<br/>
                     工作<br/>
      </td> 
      <td colspan="8">
           <p style="height:150px"></p>
      </td>
    </tr> 
     <tr > 
      <td align="center">
                      何时<br/>
		何地<br/>
		受何<br/>
		表彰<br/>
      </td> 
      <td colspan="8">
           <p style="height:150px"></p>
      </td>
    </tr> 
    <tr > 
      <td width=688  align=top  colspan=9 >
          <p  style="height:20px"></p>
          <p align="center"><span style="font-weight:bold; font-size:15px;font-family:黑体;">个人主要事迹</span></p>
          <p  style="height:20px"></p>
          <p style="height:400px"></p>
      </td> 
    </tr> 
  </table> 
  <p style="height:40px"></p>
  <table class="printtab"> 
    <tr > 
      <td width=688  valign=top  colspan="9">
          <p  style="height:20px"></p>
          <p align="center"><span style="font-weight:bold; font-size:15px;font-family:黑体;">个人主要事迹</span></p>
          <p  style="height:20px"></p>
      </td> 
    </tr> 
    <tr><td colspan="9"><p style="height:870px"></p></td></tr>
  </table> 
  <p style="height:40px"></p>
  
  <table class="printtab"> 
     <tr > 
      <td width=55  align=center >
                      学<br/>
                      校<br/>
                      评<br/>
                      审<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:50px"></p>
          <p style="height:230px">&nbsp;&nbsp;&nbsp;&nbsp;根据该同学各方面表现，经学校奖学金评审委员会审核，符合评奖条件，同意该生获得纺织之光奖学金。</p>
          <p align="center">校领导签名   
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
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
                           公章
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:40px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
      </td> 
    </tr> 
    <tr > 
      <td width=55  align=center >
                     理<br/>
		事<br/>
		会<br/>
                      评<br/>
                      审<br/>
                      意<br/>
                      见<br/>
      </td> 
      <td width=633  valign=top >
          <p  style="height:10px"></p>
          <p style="height:400px"></p>
          <p align="center">签名
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
           &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
                            公章                 
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p style="height:40px"></p>
          <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;
          </p>
      </td> 
    </tr> 
    <tr > 
      <td width=55  align=center >
		      备<br/>
		      注<br/>
      </td> 
      <td width=600  align=center >
            <p align="left">1.本表一式三份，请正反面打印。</p>
            <p align="left">2.学校年、院、系、专业不可缩写。</p>
            <p align="left">在“专业”栏目中，注明高职、本科、硕士或博士。</p>	
	

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
