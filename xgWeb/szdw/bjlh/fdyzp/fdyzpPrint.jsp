<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel=Edit-Time-Data href="奖学金.files/editdata.mso">
<title>辅导员考核统计表</title>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<style>
			.tbstyle tr th{
				line-height: 20px;
				font-size: 14px;
				border: 1px solid #000000;
			}
			.tbstyle tr td{
				line-height: 20px;
				font-size: 14px;
				border: 1px solid #000000;
			}
			.tbstyle tr{
				height: 33px;
			}
			body{
				font-size: 12px;
			}
		</style>
		<!-- end -->
</head>
<body bgcolor="#000000" lang=ZH-CN> 
<div align="center"> 
  <p align=center><b style="font-size: 14px;font-weight: normal;"><span
style='font-size:20.0pt;font-family:宋体;letter-spacing:1.0pt; font-weight: bold;'>北京联合大学辅导员考核统计表</span>（辅导员填写）</b></p> 
  <p align="center" ><b style="font-size: 14px;font-weight: normal;"><bean:message key="lable.xb" />：</b>${rs.bmmc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b style="font-size: 14px;font-weight: normal;">辅导员姓名：</b>${rs.xm }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  <table  class="tbstyle" border="0" align="center" style="width: 90%"> 
    <tr> 
      <th width=33%>考核项目</th> 
      <th width=33%>完成情况</th> 
      <th width=34%>备注</th> 
    </tr> 
    <logic:present name="xmList">
    <logic:iterate id="xm" name="xmList">
	    <tr align="center"> 
	      <td>${xm.khxm}
	      	  <logic:equal name="xm" property="xmlx" value="必选项">
	      	  	◎
	      	  </logic:equal>
	      	  <logic:equal name="xm" property="xmlx" value="加分项">
	      	  	※
	      	  </logic:equal>
	      	  <logic:equal name="xm" property="xmlx" value="参选项">
	      	  	*
	      	  </logic:equal>
	      </td>
	      <td>${xm.wcqk}</td>
	      <td>${xm.bz}</td>
	    </tr> 
    </logic:iterate>
    </logic:present>
     </table>
     <div style="width:80%;text-align: left; line-height: 20px; margin-top: 5px; margin-bottom: 5px;">
     	<p style="float: left; width: 10px;">注：</p>
     	<p style="float:right;text-align: left;">1请根据实际情况，简要填写该表，注明有关工作的完成情况，可根据内容加页。<br/>
    		   2参考了京联学【2009】85号文中附件2的辅导员考核量化表；参照了“四个一”要求：每学年指导一个社会实践社团、完成一篇调查报告、发表一篇研究论文、符合教师任职条件的辅导员承担一门德育课程教学；参考了新三率：就业率、考研率、出国率。<br/>
			   3考核项目的符号表示：◎必选项  *参选项    ※加分项
     	</p>
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
