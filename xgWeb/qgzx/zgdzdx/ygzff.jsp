<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qgzxFunction.js"></script>
</head>
	<body onload="initZje();initNext();" >
		<html:form action="/cjff" styleId="ygzff">
			<input type="hidden" value="<bean:write name="xxdm"/>" id="xxdm" />
			<input type="hidden" value="<bean:write name="userType"/>" id="userType" />
			<input type="hidden" value="<bean:write name="gwxzmc"/>" id="gwxzmc" />
			<input type="hidden" value="<bean:write name="pTotal"/>" id="count"  name="count"/>
			<input type="hidden" value="<bean:write name="currentPage"/>" id="currentPage"  name="currentPage"/>
			<input type="hidden" value="<bean:write name="startPage"/>" id="startPage"  name="startPage"/>
			<input type="hidden" value="<bean:write name="pageSize"/>" id="pageSize"  name="pageSize"/>
			<input type="hidden" value="<bean:write name="post" property="spbcbz"/>" id="spbcbz"  name="spbcbz"/>
			<input type="hidden" value="<bean:write name="pkValue"/>" id="pkValue" name="pkValue"/>
			<input type="hidden" value="<bean:write name="pk"/>" id="pk" name="pk"/>
			<input type="hidden" value="<bean:write name="zgcjje"/>" id="zgcjje" name="zgcjje"/>
			<input type="hidden" value="<bean:write name="zggzsj"/>" id="zggzsj" name="zggzsj"/>
			<input type="hidden" value="<bean:write name="xn"/>" id="xn" name="xn"/>
			<input type="hidden" value="<bean:write name="year"/>" id="nd" name="nd"/>
			<input type="hidden" value="<bean:write name="xq"/>" id="xq" name="xq"/>
			<input type="hidden" value="<bean:write name="month"/>" id="yf" name="yf"/>
			<input type="hidden" value="" id="printFlag" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ-��𷢷�-�¹��ʷ���</a>
				</p>
			</div>
			<div class="tab">
			<table border="0" id="rsTable" class="formlist">
			<tbody>
			<tr>
			<td>
			<div align="center" id="title">
   				<p><font size="5"><b>�� <bean:write name="year"/> ���꣨ <bean:write name="month"/>  ����<bean:write name="title"/> </b> </font>
				</p>
   				<p>&nbsp;   </p>
 			</div>
			<div class="con_overlfow">
 			<logic:equal value="ygzff" name="type">
			<div align="right">${nowtime}</div>
			<table class="dateline" style="width:100%" id="rsT">
				<tbody>				
				<!-- ��ӡʱ��һ�в���ʾ- -->
				<tr>
					<td colspan="11">
					</td>
				</tr>
			  <tr>
			    <td height="29" colspan="11">
			   		 ��������<bean:write name="pTotal"/> 
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	�ܽ� 
			    	<span id="zje"> <bean:write name="zje"/></span>
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	������ʦǩ�����£�
			   	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	������ʦ��ϵ�绰��
				</td>
			  </tr>
			  <tr>
			    <th>���</th>
			    <th>������λ</th>
			    <th>��������</th>
			    <th>�༶���</th>
			    <th>ѧ��</th>
			    <th>����</th>
			    <th>�¹�����ʱ��</th>
			    <th>���</th>
			    <th>��ϵ�绰</th>
			    <th>ѧ��ǩ��</th>
			    <th>��ע</th>
			  </tr>
			  <logic:empty name="rs">
			  	<tr>
			  		<td colspan="11" align="center">
			  			�����ڸ�ѧ����
			  		</td>
			  	</tr>
			  </logic:empty>
			  
			  <logic:notEmpty name="rs">
				  <logic:iterate id="list" name="rs" indexId="index" offset="${startPage}" length="${pageSize}">
				  <tr>
				  	
				  	<logic:iterate id="v" name="list" offset="0" length="1">
				  	<td>
				  		${index+1}
						<logic:iterate id="v" name="list" offset="4" length="1">
				  		<input type="hidden" id="xh${index}" value="<bean:write name="v" />" name="xh${index}"/>	  
				  	</logic:iterate>
				  	</td>
				  	</logic:iterate>
				  	
				  	<logic:iterate id="v" name="list" offset="1" length="5">
				  	<td>
				  		<bean:write name="v"/>
				  	</td>
				  	</logic:iterate>
				  	<td>
				  	<logic:iterate id="v" name="list" offset="6" length="1">	  
				  		<input type="text" id="gzsj@@!!${index}" style="width:80px" value="<bean:write name="v" />" name="gzsj${index}" onchange="changeCjje(this)"/>	  	
				  	</logic:iterate>
				  	</td>
				  	<td>
				  	<logic:iterate id="v" name="list" offset="7" length="1">	  
				  		<input type="text" id="cjje${index}" style="width:40px" value="<bean:write name="v" />" name="cjje${index}" onchange="checkRange(this,${index})"/>	  	
				  	</logic:iterate>
				  	</td>
				  	<logic:iterate id="v" name="list" offset="8" length="2">
				  	<td>
				  		<bean:write name="v" />
				  	</td>
				  	</logic:iterate>
				  	<td>
				  	<logic:iterate id="v" name="list" offset="10" length="1">	  
				  		<input type="text" id="bz${index}" style="width:80px" value="<bean:write name="v" />" name="bz${index}"/>	  	
				  	</logic:iterate>
					<logic:iterate id="v" name="list" offset="11" length="1">	  
				  		<input type="hidden" id="zgcjje${index}" style="width:80px" value="<bean:write name="v" />" name="zgcjje${index}"/>	  	
				  	</logic:iterate>
				  	</td>
				  </tr>
				  </logic:iterate>
			  </logic:notEmpty>
			  </tbody>
			  </table>
		  </logic:equal>

		   <logic:equal value="lsggzff" name="type">
			<table class="formlist" style="width:100%" id="rsT">
			<tbody>
			<!-- ��ӡʱ��һ�в���ʾ- -->
			<tr>
				<td colspan="10">
				</td>
			</tr>
			  <tr>
			    <td height="29" colspan="10">
			   		 ��������<bean:write name="pTotal"/> 
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	�ܽ�
			    	<span id="zje"> <bean:write name="zje"/></span>
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	������ʦǩ�����£�
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	������ʦ��ϵ�绰��
				</td>
			  </tr>
			  <tr>
			    <th>���</th>
			    <th>��������</th>    
			    <th>ѧ��</th>
			    <th>����</th>
			    <th><bean:message key="lable.xsgzyxpzxy" /></th>
			    <th>�༶���</th>
			    <th>�¹�����ʱ��</th>
			    <th>���</th>
			    <th>��ϵ�绰</th>
			    <th>��ע</th>
			  </tr>
			  <logic:empty name="rs">
			  	<tr>
			  		<td colspan="11" align="center">
			  			�����ڸ�ѧ����
			  		</td>
			  	</tr>
			  </logic:empty>
			  
			  <logic:notEmpty name="rs">
				  <logic:iterate id="list" name="rs" indexId="index" offset="${startPage}" length="${pageSize}">
				  <tr>
				  	
				  	<logic:iterate id="v" name="list" offset="0" length="1">
				  	<td>
				  		${index+1}
						<logic:iterate id="v" name="list" offset="1" length="1">
				  		<input type="hidden" id="xh${index}" value="<bean:write name="v" />" name="xh${index}"/>	  
				  	</logic:iterate>
				  	</td>
				  	</logic:iterate>
				  	
				  	<logic:iterate id="v" name="list" offset="1" length="5">
				  	<td>
				  		<bean:write name="v"/>
				  	</td>
				  	</logic:iterate>
				  	<td>
				  	<logic:iterate id="v" name="list" offset="6" length="1">	  
				  		<input type="text" id="gzsj@@!!${index}" style="width:80px" value="<bean:write name="v" />" name="gzsj${index}" onchange="changeCjje(this)"/>	  	
				  	</logic:iterate>
				  	</td>
				  	<td>
				  	<logic:iterate id="v" name="list" offset="7" length="1">	  
				  		<input type="text" id="cjje${index}" style="width:40px" value="<bean:write name="v" />" name="cjje${index}" onchange="checkRange(this)"/>	  	
				  	</logic:iterate>
				  	</td>
				  	<logic:iterate id="v" name="list" offset="8" length="1">
				  	<td>
				  		<bean:write name="v" />
				  	</td>
				  	</logic:iterate>
				  	<td>
				  	<logic:iterate id="v" name="list" offset="9" length="1">	  
				  		<input type="text" id="bz${index}" style="width:80px" value="<bean:write name="v" />" name="bz${index}"/>	  	
				  	</logic:iterate>
				  	</td>
				  </tr>
				  </logic:iterate>
			  </logic:notEmpty>
			  </tbody>
			  </table>		  
		  </logic:equal>
			</div>
  		</td>
  	</tr>
	<tfoot>
      <tr>
        <td colspan="4">
          <div class="btn">
            <logic:equal value="yes" name="writeAble">
			  <logic:notEmpty name="rs">
					<button type="button" class="button2" onclick="saveYgzbb()" style="width:80px">
							�� �� 
					</button>
					<button type="button" class="button2" onclick="goUp();" style="width:80px" disabled="disabled" id="up">
							��һҳ 
					</button>
					<button type="button" class="button2" onclick="goNext();" style="width:80px" disabled="disabled" id="nextOne">
							��һҳ 
					</button>
					<button type="button" class="button2" onclick="printYgzbb()" style="width:80px">
							�� ӡ
					</button>
			  </logic:notEmpty>
		  </logic:equal>
          </div>
        </td>
      </tr>
    </tfoot>
  </table>
  

  <logic:present name="result">
  <logic:equal value="true" name="result">
  	<script>
  		alert('�����ɹ���');
  	</script>
  </logic:equal>
  <logic:equal value="false" name="result">
  	<logic:present name="msg">
  		<input type="hidden" id="msg" name="msg" value="<bean:write name="msg"/>"/>
  		<script>
  			alert(document.getElementById('msg').value);
  		</script>
  	</logic:present>
  	<logic:notPresent name="msg">
  		<script>
  			alert('����ʧ�ܣ�');
  		</script>
  	</logic:notPresent>
  </logic:equal>
  </logic:present>

</html:form>
	
</body>
</html>
