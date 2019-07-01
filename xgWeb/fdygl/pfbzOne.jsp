<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />

<base target="_self">
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />

<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/comm/commFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/basicOperaDwr.js'></script>
<script language="javascript" src="js/fdyglFunction.js"></script>
</head>
<body onload="chkPfbzDoType()"> 
<html:form action="/setPfbz" method="post"> 
<div class="tab_cur">
	<p class="location">
		<em>您的当前位置:</em><a>辅导员管理 - 思政考核 - 评价指标设置</a>
	</p>
</div>
<div class="tab">
  <input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>">
  <table width="100%"  border="0" class="formlist">
    <thead>
    	<tr>
        	<th colspan="4"><span>评价指标设置</span></th>
        </tr>
    </thead>
     <tfoot>
      <tr>
      	<logic:equal value="yes" name="writeAble" scope="request"> 
          <td colspan="4"><logic:notEqual name="operation" value="view"><div class="bz">"<span class="red">*</span>"为必填项</div></logic:notEqual>
          <div class="btn">
          <logic:notEqual name="operation" value="view">
          		<button name="提交" id="buttonSave" onclick="savePfbz()"> 保存 </button>
          </logic:notEqual>
            <button name="关闭" onclick="Close()"> 关闭 </button>
          </div></td>
          </logic:equal>
      </tr>
    </tfoot>
    <tr> 
      <th align="right" width="60"><span class="red">*</span>编号</th> 
      <td align="left">
        <html:text property="bzbh" name="fdyglForm" styleId="bzbhT" style="width:100%" maxlength="4"></html:text>
        
        <div id="msg_div1" class="hide">
        	<div class="prompcon">
          		<p>编号已经存在！</p>
          	</div>
        </div>
      </td> 
      <th align="right" width="60"><span class="red">*</span>名称</th> 
      <td align="left" valign="middle">
      <html:text property="bzmc" name="fdyglForm" style="width:90px;" maxlength="20"></html:text><span style="width:18px;border:0px;">
      <html:select property="bzbh" name="fdyglForm" styleId="bzbhS" style="border:0px;margin-left:-100px;width:118px;background-color:#98BCE1">
        <option value="">添加...</option>
        <html:options collection="bzList" property="bzbh" labelProperty="bzmc"/>
      </html:select></span>
      </td> 
    </tr>  
    <tr> 
      <th align="right" width="60"><span class="red">*</span>评分项目</th> 
      <td align="left" colspan="3">
        <input type="hidden" name="oldPfxm" value="<bean:write property="pfxm" name="fdyglForm"/>">
        <html:textarea property="pfxm" name="fdyglForm" style="width: 95%;word-break:break-all;" onblur="chLeng(this,100);"></html:textarea>
      </td> 
    </tr>
    <tr> 
      <th align="right" width="60"><span class="red">*</span>权重</th> 
      <td align="left">
        <html:text onblur="numFormatChk(this,0,1)" property="qz" styleId="qz" name="fdyglForm" style="width:90px" maxlength="10"></html:text>
      </td> 
      <th align="right" width="60"><span class="red">*</span>显示顺序</th> 
      <td align="left">
        <html:text onblur="numFormatChk(this,0)" property="xssx" name="fdyglForm" style="width:90px" maxlength="2"></html:text>
      </td> 
    </tr>
</table> 
</div>


  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("保存成功！");window.dialogArguments.refreshForm("setPfbz.do");Close();</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("保存失败，请重试！");</script>
    </logic:equal>
  </logic:present>
  
</html:form> 
</body>
</html>
