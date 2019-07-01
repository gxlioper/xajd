<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
		var var_issdsr1 = "<input type='text' name='csrq' id='csrq' " 
			+ " onblur='dateFormatChg(this)' style='cursor:hand;width:100px' " 
			+ " onclick=\"return showCalendar('csrq','y-mm-dd');\" />";
		var var_issdsr2 = "<input type=\"text\" style=\"width:100px;\" name=\"csrq\" id=\"csrq\">&nbsp;&nbsp;"+
			"<font color=\"red\">例:19850227</font><br>";
		function chgSrfs(){
			var chk_srfs = document.getElementById("chk_srfs");
			if (chk_srfs.checked){
				document.getElementById("issdsr1").style.display = "none";
				document.getElementById("issdsr1").innerHTML = "";
				document.getElementById("issdsr2").style.display = "";
				document.getElementById("issdsr2").innerHTML = var_issdsr2;
			}else{
				document.getElementById("issdsr1").style.display = "";
				document.getElementById("issdsr1").innerHTML = var_issdsr1;
				document.getElementById("issdsr2").style.display = "none";
				document.getElementById("issdsr2").innerHTML = "";
			}
		}
	</script>
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/yxglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/yxglFun.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>	
  <body onload="document.getElementById('ksh').focus()">
  <html:form action="yxgl_xybd_one.do">
  	<input type="hidden" id="pageType" name="pageType" value="<bean:write name="pageType" />" />
    <input type="hidden" id="bdType" name="bdType" value="xybd" />
    <input type="hidden" id="sessionId" name="sessionId" value="<bean:write name="sessionId" />" />
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>迎新管理 － 新生管理 － 单个学生修改</a>
		</p>
	</div>
	<div class="tab">
		<table width="100%"  border="0" class="formlist">
			<thead>
		    	<tr>
		        	<th colspan="6"><span>单个学生修改</span></th>
		        </tr>
		    </thead>
		    <logic:notEqual value="view" name="doType">
		     <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			       		  <logic:notPresent name="modify">
					       <button name="提交信息" onclick="beforeSubmit()">
					          提交信息
					       </button>
					       </logic:notPresent>
					       <logic:present name="modify" >
					       <button name="修改信息" onclick="document.forms[0].action='yxgl_xsgl_one.do?doType=modify';document.forms[0].submit();alert('修改成功');window.dialogArguments.document.getElementById('search_go').click();window.close();">
					          修改信息
					       </button>
					       </logic:present>  
			            <button name="取消" onclick="window.close();return false;">关 闭</button>
			          </div></td>
			      </tr>
			  </tfoot>
			 </logic:notEqual> 
			<tbody>
		  <tr>
		    <th width="16%" height="20%"><div align="right"><font color="red">*</font>考生号:</div></th>
		    <td width="37%" height="20%"><html:text name="rs" property="ksh" styleId="ksh" readonly="true"/></td>
		  	<th width="11%" height="20%"><div align="right">学号:</div></th>
		    <td width="36%" height="20%"><html:text name="rs" property="xh" styleId="xh" readonly="true"/></td>
		    <td width="36%" height="60%" rowspan="3"  align="center">
		    	<img alt="图片不存在或无法显示" height="100" width="75"  border="1" 
		    		src="<bean:write name="rs" property="picture"/>"></img>
		    </td>
		  </tr>
		  <tr>
		  	<th width="16%" height="20%"><div align="right">身份证号:</div></th>
		    <td width="20%" height="20%"><html:text name="rs" property="sfzh" styleId="sfzh" readonly="true"/></td>
		    <th width="16%" height="20%"><div align="right"><font color="red">*</font>姓名:</div></th>
		    <td width="37%" height="20%"><html:text name="rs" property="xm" styleId="xm" readonly="true"/></td>
		  </tr>
		  <tr>
		    <th width="11%" height="20%"><div align="right"><font color="red">*</font>性别：</div></th>
		    <td height="20%">
				<html:select name="rs" property="xb" style="width:98%">
		          <html:option value=""></html:option>
		          <html:options collection="xbList" property="cn" labelProperty="cn" />
		       </html:select>			
			</td>
			<th width="16%" height="20%"><div align="right"></div></th>
		    <td width="37%" height="20%"></td>
		  <tr>
		  	<th width="20%" height="20%"><div align="right">省份：</div></th>
		    <td height="20%">
				<html:select name="rs" property="sfdm" style="width:98%" >
		          <html:option value=""></html:option>
		          <html:options collection="sfList" property="sfdm" labelProperty="sfmc" />
		       </html:select>			
			</td>
		    <th width="20%" height="20%"><div align="right">出生日期：</div></th>
		    <td height="20%" colspan="2">
		    	<span id="issdsr1">
					<html:text name='rs' property="csrq" styleId="csrq"
						onblur="dateFormatChg(this)" style="cursor:hand;width:100px"
						onclick="return showCalendar('csrq','y-mm-dd');" />
				</span>	
				<span id="issdsr2"></span>	
			<%--<logic:equal value="10463" name="xxdm" scope="request">	
				&nbsp;&nbsp;&nbsp;
				--%><input type="checkbox" name="chk_srfs" id="chk_srfs" onclick="chgSrfs();">手动输入	
			<%--</logic:equal>					
			--%></td>
			
		  </tr>
		  <tr>
		  	<th width="16%" height="20%"><div align="right">籍贯:</div></th>
		    <td width="20%" height="20%"colspan="4"><html:text name="rs" property="jg" styleId="jg" style="width:100%"/></td>
		  </tr>
		  <tr>
		    <th height="20%"><div align="right"><font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />名称:</div></th>
		    <td height="20%">
		      <html:select name="rs" property="xydm" styleId="xy" style="width:98%" onchange="refreshForm('/xgxt/yxgl_xsgl_one.do')">
		          <html:option value=""></html:option>
		          <html:options collection="xyList" property="xydm" labelProperty="xymc" />
		       </html:select>	
			</td>
		    <th height="20%"><div align="right"><font color="red">*</font>专业名称:</div></th>
		    <td height="20%" colspan="2">
				<html:select name="rs" property="zydm" styleId="zy" style="width:98%" onchange="refreshForm('/xgxt/yxgl_xsgl_one.do')">
		          <html:option value=""></html:option>
		          <html:options collection="zyList" property="zydm" labelProperty="zymc" />
		       </html:select>			
			</td>
		  </tr>
		  <tr>
		    <th height="20%"><div align="right">班级名称:</div></th>
		    <td height="20%">
				<html:select name="rs" property="bjdm" styleId="bj" style="width:98%" onchange="refreshForm('/xgxt/yxgl_xsgl_one.do')">
		          <html:option value=""></html:option>
		          <html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
		       </html:select>				
			</td>
		    <th height="20%"><div align="right"></div></th>
		    <td height="20%" colspan="2">&nbsp;</td>
		  </tr>	  
		  </tbody>
	</table>
	</div>
    </html:form>
    <script type="text/javascript">
      window.unload = yxglXybdOneUnLoad('search_go'); 
    </script>
    <logic:equal value="view" name="doType">
	  	<script type="text/javascript">
	      setAllDisabled('csrq-jg');
	    </script>
  	</logic:equal>
  	<logic:equal value="add" name="doType">
	  	<script type="text/javascript">
	      releaseAllElement('ksh-xh-sfzh-xm-xb');
	    </script>
  	</logic:equal>
  	<logic:equal value="modify" name="doType">
  		<script type="text/javascript">
	      releaseAllElement('sfzh-xm-xb');
	    </script>
  	</logic:equal>
	<logic:empty name="doType">
		<script type="text/javascript">
		    releaseAllElement('ksh-xh-sfzh-xm-xb');
		</script>
	</logic:empty>
	
	    <logic:present name="dors">
    	<logic:equal value="ok" name="dors">
    		<script>
    		   alert("保存成功！");
    		   Close();
    		</script>
    	</logic:equal>
    	<logic:equal value="no" name="dors">
    		<script>
    		   alert("保存失败！");
    		</script>
    	</logic:equal>
    </logic:present>
  </body>
</html>
