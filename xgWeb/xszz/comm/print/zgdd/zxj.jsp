<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//Dth  HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
		.noPrin{display:none;}
	</style>
		<!-- end -->
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
<div align="center"> 
  <p align=center><b><span
style='font-size:22.0pt;font-family:����;letter-spacing:1.0pt'>��&nbsp;${rs.xn }ѧ�꣩${rs.xmmc }����������</span></b></p> 
  <p align=center><font size="3"><b>ѧУ��${rs.xxmc }
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Ժϵ��${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
ѧ�ţ�${rs.xh}</b></font></p> 
  <input type="hidden" id="sfzh" value="${rs.sfzh }"/>
  <table class="printstyle" border="0" align="center" style="width: 90%"> 
    <tr> 
      <th  width=14% rowspan=4><p align=center>��<br/>��<br/>��<br/>��</p></th > 
      <th  width=14%> <p align=center>����</p></th > 
      <th  width=20% colspan="5">${rs.xm } </th > 
      <th  width=12% colspan="3"> <p align=center>�Ա�</p></th > 
      <th  width=12% colspan="3">${rs.xb } </th > 
      <th  width=12% colspan="3"> <p align=center>��������</p></th > 
      <th  width=16% colspan="4">${rs.csrq } </th > 
    </tr> 
    <tr> 
      <th> <p align=center>������ò</p></th > 
      <th  colspan="5">${rs.zzmmmc } </th > 
      <th  colspan="3"> <p align=center>����</p></th > 
      <th  colspan="3">${rs.mzmc } </th > 
      <th  colspan="3"> <p align=center>��ѧʱ��</p></th > 
      <th  colspan="4">${rs.rxrq } </th > 
    </tr> 
    <tr> 
      <th> <p align=center>רҵ</p></th > 
      <th  colspan="5">${rs.zymc } </th > 
      <th  colspan="3"> <p align=center>ѧ��</p></th > 
      <th  colspan="3">${rs.xz } </th > 
      <th  colspan="3"> <p align=center>��ϵ�绰</p></th > 
      <th  colspan="4">${rs.lxdh } </th > 
    </tr> 
   <tr> 
      <th > <p>���֤��</p></th >
      <th  id="s0">&nbsp; </th >  
      <th  id="s1">&nbsp; </th > 
      <th  id="s2">&nbsp; </th > 
      <th  id="s3">&nbsp; </th > 
      <th  id="s4">&nbsp; </th > 
      <th  id="s5">&nbsp; </th > 
      <th  id="s6">&nbsp; </th > 
      <th  id="s7">&nbsp; </th > 
      <th  id="s8">&nbsp; </th > 
      <th  id="s9">&nbsp; </th > 
      <th  id="s10">&nbsp; </th > 
      <th  id="s11">&nbsp; </th > 
      <th  id="s12">&nbsp; </th > 
      <th  id="s13">&nbsp; </th > 
      <th  id="s14">&nbsp; </th > 
      <th  id="s15">&nbsp; </th > 
      <th  id="s16">&nbsp; </th > 
      <th  id="s17">&nbsp; </th > 
   	</tr>
    <tr> 
      <th rowspan="2">
      	<p align=center>ѧϰ<br/>���<br/></p> 
	  </th > 
      <th colspan="9">
      	�ɼ�������${rs.bjpm }/${rs.bjrs }������/��������
      </th > 
      <th colspan="10">
      	ʵ���ۺϿ�����������
      	<logic:notEqual name="rs" property="sxzhpm" value="��">
			��
		</logic:notEqual>
		<logic:equal name="rs" property="sxzhpm" value="��">
			<img src="/xgxt/pictures/xszz/gou.jpg"></img>
		</logic:equal>��
      	��
      	<logic:notEqual name="rs" property="sxzhpm" value="��">
			��
		</logic:notEqual>
		<logic:equal name="rs" property="sxzhpm" value="��">
			<img src="/xgxt/pictures/xszz/gou.jpg"></img>
		</logic:equal>
	  </th > 
    </tr> 
    <tr> 
      <th colspan="9">
      	���޿�${rs.bxkms }�ţ����м�������${rs.jgysms }��
      </th > 
      <th colspan="10">
      	���ǣ�������${rs.zycjpm }/${rs.bjrs }������/��������
      </th > 
    </tr> 
    <tr> 
      <th rowspan="5"> <p align=center>��ѧ<br/>�ڼ�<br/>��<br/>���</p></th> 
      <th colspan="5"><p align=center>����</p></th > 
      <th colspan="5"> <p align=center>��������</p></th > 
      <th colspan="9"> <p align=center>�佱��λ</p></th > 
    </tr> 
    <tr> 
      <th colspan="5">${rs.hjrq1}&nbsp;&nbsp;</th > 
      <th colspan="5">${rs.hjmc1}&nbsp;&nbsp;</th > 
      <th colspan="9">${rs.bjdw1}&nbsp;&nbsp;</th > 
    </tr> 
    <tr> 
      <th colspan="5">${rs.hjrq2}&nbsp;&nbsp;</th > 
      <th colspan="5">${rs.hjmc2}&nbsp;&nbsp;</th > 
      <th colspan="9">${rs.bjdw2}&nbsp;&nbsp;</th > 
    </tr> 
    <tr> 
      <th colspan="5">${rs.hjrq3}&nbsp;&nbsp;</th > 
      <th colspan="5">${rs.hjmc3}&nbsp;&nbsp;</th > 
      <th colspan="9">${rs.bjdw3}&nbsp;&nbsp;</th > 
    </tr> 
    <tr> 
      <th colspan="5">${rs.hjrq4}&nbsp;&nbsp;</th > 
      <th colspan="5">${rs.hjmc4}&nbsp;&nbsp;</th > 
      <th colspan="9">${rs.bjdw4}&nbsp;&nbsp;</th > 
    </tr> 
    <tr> 
      <th >
      	<p align=center>��ͥ<br>����<br>���</p> 
	  </th > 
      <th colspan="19">
			<p style="height: 280px;" align="left">
				${rs.jtjbjjqk }
			</p>
      </th > 
    </tr> 
    <tr> 
      <th> <p align=center>
      	        ��<br/>
       	        ��<br/> 
		        ��<br/> 
		        ��<br/> 
		     </p></th > 
      <th  colspan="19">
     		<p style="height: 150px;" align="left">
					${rs.sqsm }
			</p>
				<div align="right">
					������ǩ��(��ǩ)��
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;
					<br>
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;
					��
				</div>
    </th > 
    </tr> 
    </table>   
    <br><br><br><br><br>
    <table class="printstyle" border="0" align="center" style="width: 90%"> 
       <tr>
         <th >
           <p align=center>�Ƽ�<br/>����</p>
         </th >
         <th  colspan="19">
			<p style="height: 200px;" align="left">
				${rs.shzt1yj }
			</p>
			<div align="right">
				�Ƽ��ˣ�����Ա������Σ�ǩ����
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;<br>
				 <p align="right">����&nbsp; �£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				��&nbsp;&nbsp;&nbsp;&nbsp;
				��&nbsp;&nbsp;&nbsp;&nbsp;
				��
			</div>
     	</tr>
       <tr>
         <th >
           <p align=center>Ժ<br/>ϵ<br/>��<br/>��</p></th >
         <th  colspan="19">
			<p style="height: 190px;" align="left">
				${rs.shzt2yj }
			</p>
			<div align="right">
				Ժϵ����ѧ�������쵼ǩ����
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;<br>
				 <p align="right">����&nbsp; �£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				��&nbsp;&nbsp;&nbsp;&nbsp;
				��&nbsp;&nbsp;&nbsp;&nbsp;
				��
			</div>
       </tr>
       <tr>
         <th >
            <p align=center>ѧ<br/>У<br/>��<br/>��</p>
	  </th >
         <th  colspan="19">
		<logic:notEmpty name="rs" property="shzt3yj">
			${rs.xxyj}
		</logic:notEmpty>
		<logic:empty name="rs" property="shzt3yj">
		<br/>
           <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���ȫУ��Χ�ڹ�ʾ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>�죬�����飬�ֱ���ͬ���ͬѧ���&nbsp;&nbsp;${rs.xn }&nbsp;&nbsp; ѧ��ȹ��ҽ�ѧ��</p>
		</logic:empty>
           <p align="right">����&nbsp; �£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
           <p align=right>��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;</p></th >
       </tr>
     </table>
      	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
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
