<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			document.forms[0].action = "/xgxt/n05_xszz.do?method=dgszxm&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>
			学生资助 - 基础数据维护 - 项目相关单个设置</a></p>
		</div>
	<html:form action="/n05_xszz.do?method=dgszxm" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("保存成功！");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab">
			<table width="99%"  border="0" class="formlist">
			<thead>
   				<tr>
       				<th colspan="4"><span>单个设置</span></th>
       			</tr>
  			</thead>
  			<tbody>
			<tr>
				<th width="50%">
					项目名称
				</th>
				<td width="50%">
					<input type="text" id="xmmc" name="xmmc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xmmc"/>">
					<input type="hidden" id="xmb" name="xmb"
						value="<bean:write name="rs" property="xmb"/>" />
				</td>
			</tr>
			<tr>
				<th>
					审核级别
				</th>
				<td>
					<html:select name="rs" property="shjb">
						<html:option value="2">二级(<bean:message key="lable.xsgzyxpzxy" />、<bean:message key="lable.xsgzyxpzxx" />审核)</html:option>
						<html:option value="3">三级(辅导员、<bean:message key="lable.xsgzyxpzxy" />、<bean:message key="lable.xsgzyxpzxx" />审核)</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					是否必须为困难生
				</th>
				<td>
					<html:select name="rs" property="sfkns">
						<html:option value="0">否</html:option>
						<html:option value="1">是</html:option>
					</html:select>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		          	<button  id="buttonSave" onclick="yz();">
						保 存
					</button>
					&nbsp;
					<button id="buttonClose"
						onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						关 闭
					</button>						           
		          </div>
		          </td>
		      </tr>
		    </tfoot>
		</table>
		
	</html:form>
</body>
</html>
