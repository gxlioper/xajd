<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
	</head>
<body onload="chkPjzbDoType()"> 
<html:form action="/setPjzb" method="post"> 
<div class="tab_cur">
	<p class="location">
		<em>���ĵ�ǰλ��:</em><a>����Ա���� - ˼������ - ����ָ������</a>
	</p>
</div>
<div class="tab">
	<table width="100%"  border="0" class="formlist">
	  <thead>
	    	<tr>
	        	<th colspan="4"><span>����ָ������</span></th>
	        </tr>
	    </thead>
	 
    <tr> 
      <th><span class="red">*</span>���ۺ�</th> 
      <td align="left" colspan="3" id="doRow">
		<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>"/>
        <html:text property="pjh" name="fdyglForm" styleId="pjh" style="width:100%" maxlength="10" onkeyup="chgPkValue(this);"></html:text>
      </td> 
    </tr> 
    <tr> 
      <th>�������</th> 
      <td align="left">
      <html:select property="mxdx" name="fdyglForm" style="width:100px">
        <html:options collection="mxdxList" property="qtdm" labelProperty="qtmc"/>
      </html:select>
      </td> 
      <th><span class="red">*</span>��Ӧ��ֵ</th> 
      <td align="left">
        <html:text onblur="if(!isDouble(this.value)){this.value='';}" property="fz" name="fdyglForm"  maxlength ="4" style="width:90px" styleId="fz"></html:text>
      </td> 
    </tr>
    <tr> 
      <th>����ָ��</th> 
      <td align="left">
      <html:select property="stlbdm" name="fdyglForm" style="width:100px" >
        <html:options collection="stlblist" property="stlbdm" labelProperty="stlbmc"/>
      </html:select>
      </td> 
      <logic:equal name="xxdm" value="10290" scope="session">
      	<th>����ָ��</th> 
      	<td align="left">
        <html:text property="scojzb" name="fdyglForm"  maxlength ="20" style="width:150px"></html:text>
        </td>
      </logic:equal> 
      <logic:notEqual name="xxdm" value="10290" scope="session">
        <td align="right" width="60"></td> 
      	<td align="left">
      	</td> 
      </logic:notEqual> 
    </tr>  
    <tr> 
      <th><span class="red">*</span>��������<br/><font color="red">(����300��������)</font></th> 
      <td align="left" colspan="3">
        <html:textarea property="pjnr" name="fdyglForm" rows="6" style="width:480px" styleId="pjnr" onblur="chLeng(this,200);"></html:textarea>
      </td> 
    </tr>
   	<tr> 
      <th>��������ָ����Ϣ<br/><font color="red">(����300��������)</font></th> 
      <td align="left" colspan="3">
        <html:textarea property="jtzb" name="fdyglForm" rows="9" style="width:480px" styleId="jtxx" onblur="chLeng(this,300);"></html:textarea>
      </td> 
    </tr>
       <tfoot>
	    	  <tr>
			      <td colspan="4"> <logic:notEqual value="view" name="operation"><div class="bz">"<span class="red">*</span>"Ϊ������</div></logic:notEqual>
			        <div class="btn">
			    	<logic:equal value="yes" name="writeAble" scope="request"> 
					  <logic:notEqual value="view" name="operation">
					  	<button  onclick="savePjzb();return false;">����</button>
					  </logic:notEqual> 
					  <button  onclick="Close();return false;">�ر�</button> 
					</logic:equal>
					</div> 
				</td>
			</tr>
	    </tfoot>
</table> 
</div>

  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("����ɹ���");window.dialogArguments.location="setPjzb.do";Close();</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("����ʧ�ܣ������ԣ�");</script>
    </logic:equal>
  </logic:present>
  
</html:form> 
</body>
</html>
