<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">	
		function addly(){
			var zt = document.getElementById("zt").value;
			if(zt == ""){
				alert("对不起，请填写主题！");
				return;
			}
			var nr = document.getElementById('nr').value = frames('eWebEditor1').getHTML();
			if(nr==""){
			 	alert("对不起，不能发表空记录！");
			 	return false;
		 	}
			document.forms[0].action = "/xgxt/whlgdx_xljk.do?method=fdygzjlpre&doType=add";
			document.forms[0].submit();
		}	
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>心理健康 - <bean:message key="lable.xsgzyxpzxy" />心理健康管理 - 辅导员工作记录 - 单个记录维护</a>
		</p>
	</div>
	<html:form action="/whlgdx_xljk.do?method=fdygzjlpre" method="post">
		<%--
			<input type="hidden" id="userOnLine" name="userOnLine"
				value="<bean:write name="userOnLine" scope="session"/>" />	
		--%>
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="rs" property="pkVal"/>" />
		<input type="hidden" id="nr" name="nr"/>
		<div class="tab">	
		<table class="formlist" align="center" width="100%">
		<tbody>
			<tr style="height:22px"><%--
				<td align="center">
					用户帐号：
				</td>
				<td >
					<html:text property="zgh" styleId="zt" name="rs" />
				</td>
				--%>
				<th align="right">
					<font color="red">*</font>主题：
				</th>
				<td>
					<html:text style="width:99%" property="zt" styleId="zt" name="rs" />
				</td>
			</tr>	
			<tr>
				<th align=right width="100">
					<font color="red">*</font>编辑内容
				</th>
				<td align=center>
					<input type="hidden" name="content1" value="<bean:write name="rs" property="nr"/>"/>		
					<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
						scrolling="no" width="100%" height="350"></iframe>
				</td>			
			</tr>
			<tr style="height:22px">
				<th align="right">
					备注
				</th>
				<td>
					<html:textarea property="bz" styleId="bz" 
					style="width: 98%" styleClass="inputtext" rows="5" onblur="chLeng(this,'500')"></html:textarea>
				</td>
			</tr>	
			</tbody>
			
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			      	  <logic:equal value="fdy" name="userType" scope="request">
			          		<button name="提交" onclick="addly();" id="buttonSave">提 交</button>
			        </logic:equal>
			            <button name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<div id="tmpdiv"></div>
</body>
<logic:present name="ok">
	<logic:match value="ok" name="ok">
		<script language="javascript">
	   			alert("保存成功！");
	   			dialogArgumentsQueryChick();;
	   			Close();
	        </script>
	</logic:match>
	<logic:match value="no" name="ok">
		<script language="javascript">
	        	alert("保存失败！");
	        </script>
	</logic:match>
</logic:present>
</html>
