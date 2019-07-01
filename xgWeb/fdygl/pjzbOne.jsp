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
		<em>您的当前位置:</em><a>辅导员管理 - 思政考核 - 评价指标设置</a>
	</p>
</div>
<div class="tab">
	<table width="100%"  border="0" class="formlist">
	  <thead>
	    	<tr>
	        	<th colspan="4"><span>评价指标设置</span></th>
	        </tr>
	    </thead>
	 
    <tr> 
      <th><span class="red">*</span>评价号</th> 
      <td align="left" colspan="3" id="doRow">
		<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>"/>
        <html:text property="pjh" name="fdyglForm" styleId="pjh" style="width:100%" maxlength="10" onkeyup="chgPkValue(this);"></html:text>
      </td> 
    </tr> 
    <tr> 
      <th>面向对象</th> 
      <td align="left">
      <html:select property="mxdx" name="fdyglForm" style="width:100px">
        <html:options collection="mxdxList" property="qtdm" labelProperty="qtmc"/>
      </html:select>
      </td> 
      <th><span class="red">*</span>对应分值</th> 
      <td align="left">
        <html:text onblur="if(!isDouble(this.value)){this.value='';}" property="fz" name="fdyglForm"  maxlength ="4" style="width:90px" styleId="fz"></html:text>
      </td> 
    </tr>
    <tr> 
      <th>评价指标</th> 
      <td align="left">
      <html:select property="stlbdm" name="fdyglForm" style="width:100px" >
        <html:options collection="stlblist" property="stlbdm" labelProperty="stlbmc"/>
      </html:select>
      </td> 
      <logic:equal name="xxdm" value="10290" scope="session">
      	<th>二级指标</th> 
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
      <th><span class="red">*</span>评价内容<br/><font color="red">(限制300字数以内)</font></th> 
      <td align="left" colspan="3">
        <html:textarea property="pjnr" name="fdyglForm" rows="6" style="width:480px" styleId="pjnr" onblur="chLeng(this,200);"></html:textarea>
      </td> 
    </tr>
   	<tr> 
      <th>具体评价指标信息<br/><font color="red">(限制300字数以内)</font></th> 
      <td align="left" colspan="3">
        <html:textarea property="jtzb" name="fdyglForm" rows="9" style="width:480px" styleId="jtxx" onblur="chLeng(this,300);"></html:textarea>
      </td> 
    </tr>
       <tfoot>
	    	  <tr>
			      <td colspan="4"> <logic:notEqual value="view" name="operation"><div class="bz">"<span class="red">*</span>"为必填项</div></logic:notEqual>
			        <div class="btn">
			    	<logic:equal value="yes" name="writeAble" scope="request"> 
					  <logic:notEqual value="view" name="operation">
					  	<button  onclick="savePjzb();return false;">保存</button>
					  </logic:notEqual> 
					  <button  onclick="Close();return false;">关闭</button> 
					</logic:equal>
					</div> 
				</td>
			</tr>
	    </tfoot>
</table> 
</div>

  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("保存成功！");window.dialogArguments.location="setPjzb.do";Close();</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("保存失败，请重试！");</script>
    </logic:equal>
  </logic:present>
  
</html:form> 
</body>
</html>
