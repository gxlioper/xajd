<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script type="text/javascript" src="js/function.js"></script>		
<script type="text/javascript" src="js/jsFunction.js"></script>
<script type="text/javascript" src="dwr/interface/getJxjList.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="js/String.js"></script>

  <body >
  	<html:form action="/pjpy_apply">
    
    <div class="title">
       <div class="title_img" id="title_m">
          ��ǰ����λ�ã��������� �� ��ѧ������ �� ��д�����
       </div>
    </div>
    <logic:notPresent name="haveRequested">
		<script type="text/javascript">
		   		showTop();
		</script>
	</logic:notPresent>
      <input type="hidden" name="url" id="url" value="/pjpy/pjpy_shgc_apply.jsp">
      <input type="hidden" name="redirect" id="redirect"  value="true">
      <input type="hidden" name="variable" id="variable" value="haveRequested">
      <input type="hidden" name="jxjlx" id="jxjlx" value="excellentstu">
      <logic:present name="dboperation">
         <logic:equal value="true" name="dboperation">
		      <script type="text/javascript">
		        alert("����ɹ���");  
		      </script>
         </logic:equal>
		 <logic:equal value="false" name="dboperation">
		 	<script type="text/javascript">
		        alert("����ʧ�ܣ�");  
		      </script>
		 </logic:equal>	    
      </logic:present>
      <table class="tbstyle" style="width:88%" align=center>
              <thead>
              	<tr>
              		<td colspan=4 align="center" style="font-size:18px">�Ϻ����̼�����ѧ����ѧ����ѧ����ǿ��ѧ�������</td>
              	</tr>
              </thead>
			  <tr>
			    <logic:equal value="teacher" name="userOnLine" scope="session">
			       <td width="18%"><div align="center"><font color="red">*</font>ѧ��</div></td>
			       <td width="26%">
				     <html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
				   </td>
			    </logic:equal>
			    <logic:notEqual value="teacher" name="userOnLine" scope="session">
			    	<td width="18%"><div align="center"><font color="red">*</font>ѧ��</div></td>
			       <td width="26%">
				     <html:text name="rs" property="xh" styleId="xh" />
				   </td>
			    </logic:notEqual>
			    <td width="21%"><div align="center">����</div></td>
			    <td width="35%"><html:text name="rs" property="xm" style="width:98%" readonly="true"/></td>
			  </tr>
			  <tr>
			    <td><div align="center">�Ա�</div></td>
			    <td><html:text  name="rs" property="xb" style="width:98%"  readonly="true"/></td>
			    <td><div align="center">�������ڣ������գ�</div></td>
			    <td><html:text  name="rs" property="csrq" style="width:98%"  readonly="true"/></td>
			  </tr>
			  <tr>
			    <td><div align="center">����</div></td>
			    <td><html:text  name="rs" property="mzmc" style="width:98%" readonly="true"/></td>
			    <td><div align="center">��ѧ����</div></td>
			    <td><html:text  name="rs" property="rxrq" style="width:98%" readonly="true"/></td>
			  </tr>
			  <tr>
			    <td><div align="center">ѧУ</div></td>
			    <td><html:text  name="rs" property="xxmc" style="width:98%" readonly="true"/></td>
			    <td><div align="center"><bean:message key="lable.xsgzyxpzxy" /></div></td>
			    <td><html:text  name="rs" property="xymc" style="width:98%" readonly="true"/></td>
			  </tr>
			  <tr>
			    <td><div align="center">רҵ</div></td>
			    <td><html:text  name="rs" property="zymc" style="width:98%" readonly="true"/></td>
			    <td><div align="center">�༶</div></td>
			    <td><html:text  name="rs" property="bjmc" style="width:98%" readonly="true"/></td>
			  </tr>
			  <tr>
			    <td rowspan="6"><div align="center">����ѧ����ѧ��</div></td>
			    <td align="right"><input name="excellentStu" type="radio" value="0000000001">&nbsp;</td>
			    <td colspan="2">�صȽ� </td>
			  </tr>
			  <tr>
			    <td align="right"><input name="excellentStu" type="radio" value="0000000002">&nbsp;</td>
			    <td colspan="2">һ�Ƚ�</td>
			  </tr>
			  <tr>
			    <td align="right"><input name="excellentStu" type="radio" value="0000000003">&nbsp;</td>
			    <td colspan="2">���Ƚ�</td>
			  </tr>
			  <tr>
			    <td align="right"><input name="excellentStu" type="radio" value="0000000004">&nbsp;</td>
			    <td colspan="2">���Ƚ�</td>
			  </tr>
			  <tr>
			    <td align="right"><input name="innovationfirst" type="checkbox" value="0000000005">&nbsp;</td>
			    <td colspan="2">���¼�</td>
			  </tr>
			  <tr>
			    <td align="right"><input name="innovationsecond" type="checkbox" value="0000000006">&nbsp;</td>
			    <td colspan="2">������</td>
			  </tr>
			  <tr>
			    <td rowspan="2"><div align="center">��ǿ��ѧ��</div></td>
			    <td align="right"><input name="zqPrise" type="radio" value="0000000007">&nbsp;</td>
			    <td colspan="2">��</td>
			  </tr>
			  <tr>
			    <td align="right"><input name="zqPrise" type="radio" value="0000000008">&nbsp;</td>
			    <td colspan="2">��</td>
			  </tr>
			  <logic:equal value="teacher" name="userOnLine" scope="session"> 
			  <logic:equal value="xy" name="userType" scope="session">
			  <tr>
			    <td height="104"><div align="center"><bean:message key="lable.xsgzyxpzxy" />��������</div></td>
			    <td height="104" colspan="3"><html:textarea property="xyshyj" rows="6" style="width:98%"/></td>
			  </tr>		
			  </logic:equal>
			  <logic:equal value="admin" name="userType" scope="session">
			  <tr>
			    <td height="139"><div align="center">ѧУ��������</div></td>
			    <td height="139" colspan="3"><html:textarea property="xxshyj" rows="8" style="width:98%"/></td>
			  </tr>
			  </logic:equal>
			  </logic:equal>
      </table>

      <div id="tmpdiv"></div>
      <div class="buttontool">
         <button class="button2" onclick="shgcPriseApplication()">�ύ����</button>
         <button class="button2" onclick="shgcPriseAppicationPrint()">��ӡ����</button>
      </div>
       
       <logic:equal value="no" name="jdxz">
          <script type="text/javascript">
             alert("��ļ����ֵ���㣡�����Ϣ�����ѧ���ֲάѧ��������׼��");
          </script>
       </logic:equal>
    </html:form>
  </body>
</html:html>
