<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script type="text/javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
</head>
<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>辅导员工作信息</a>
		</p>
	</div>
	<%--	<logic:equal name="sfksq" value="-1">--%>
	<%--		<center>--%>
	<%--			<p>--%>
	<%--				现在不在申请时间内！--%>
	<%--			</p>--%>
	<%--		</center>--%>
	<%--	</logic:equal>--%>
	<html:form action="/szdw_zjlg" method="post">
		<input type="hidden" id="xxfkdm" name="xxfkdm"
			value="<bean:write name = "xxfkdm" />" />	
		<input type="hidden" id="act" name="act"
			value="${act}" />
		<input type="hidden" id="pkValue" name="pkValue"
			value="<bean:write name = "pkValue"/>" />
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
		<logic:present name="isPASS">
			<logic:match value="is" name="isPASS">
				<script language="javascript">
	         			alert("已通过学校审核，不能申请！");
	         		</script>
			</logic:match>
		</logic:present>
		<table class="formlist" width="90%" id="theTable">
			<thead><tr><th colspan="4"><span>信息维护</span></th></tr></thead>
			<tbody>
			<tr>
				<th>
					部门名称
				</th>
				<td align="left" width="34%">
						<bean:write name = "rs" property="bmmc"/>
						<input type = "hidden" name = "bmdm" value="<bean:write name = "rs" property="bmdm"/>" /> 
				</td>
				<th>
					职工号
				</th>
					<td width="34%">
						<bean:write name = "rs" property="zgh"/>
						<input type = "hidden" name = "zgh" value="<bean:write name = "rs" property="zgh"/>" /> 
					</td>
			</tr>
			<tr>
				<th>
					姓名
				</th>
				<td width="34%">
					<bean:write name="rs" property="xm"/>
				</td>
				<th> 
					性别
				</th>
				<td>
					<bean:write name="rs" property="xb"/>
				</td>
			</tr>
			<tr>
				<th>
					民族
				</th>
				<td>
					<bean:write name="rs" property="mzmc"/>
				</td>
				<th>
					政治面貌
				</th>
				<td>
					<bean:write name="rs" property="zzmm"/>
				</td>
			</tr>
			<tr>
				<th>
					出生年月
				</th>
				<td>
					<bean:write name="rs" property="csrq"/>
				</td>
				<th>
					籍贯
				</th>
				<td>
					<bean:write name="rs" property="sfmc"/>
				</td>
			</tr>
			<tr>
				<th>
					职务
				</th>
				<td>
					<bean:write name="rs" property="zwmc"/>
				</td>
				<th>
					毕业院校
				</th>
				<td>
					<bean:write name="rs" property="byyx"/>
				</td>
			</tr>
			<tr>
				<th>
					学历
				</th>
				<td>
					<bean:write name="rs" property="xl"/>
				</td>
				<th>
					所属工作组
				</th>
				<td>
					<bean:write name="rs" property="fdyzmc"/>
				</td>
			</tr>
			<logic:notEqual value="view" name="act">
			<tr>
				<th>
					审核状态
				</th>
				<td>
					<html:select name = "xxfkrs" property="shzt" styleId="shzt">
						<html:options collection="chkList" property="en"
							labelProperty="cn" />
					</html:select>	
				</td>
				<th>
				</th>
				<td>
				</td>
			</tr>
			</logic:notEqual>
			<logic:equal value="view" name="act">
			<tr>
				<th>
					审核状态
				</th>
				<td>
					<bean:write name = "xxfkrs" property="shzt" />
				</td>
				<th>
				</th>
				<td>
				</td>
			</tr>
			</logic:equal>
			<tr>
				<th>
					信息主题
				</th>
				<td colspan="3">
					<bean:write name="xxfkrs" property="xxzt"/>
					<input type="hidden" name="xxzt" value="<bean:write name="xxfkrs" property="xxzt"/>" />
				</td>
			</tr>
			<tr id = "jtnr">
				<th>
					建议意见
				</th>
				<td colspan="3">
					<html:textarea name="xxfkrs" property="jyyj" rows='5' style="width: 95%;word-break:break-all;" onblur="chLeng(this,300)" readonly="true"/>
				</td>
			</tr>
			<tr id = "jtnr">
				<th>
					具体内容
				</th>
				<td colspan="3">
					<html:textarea name="xxfkrs" property="jtnr" rows='15' style="width: 95%;word-break:break-all;" onblur="chLeng(this,600)" readonly="true"/>
				</td>
			</tr>
			<tr id="bz">
				<th>
					备注
				</th>
				<td colspan="3">
					<html:textarea name="xxfkrs" property="bz" rows='3' style="width: 95%;word-break:break-all;" onblur="chLeng(this,300)" readonly="true"/>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4">
		          <div class="btn">
					 <logic:notEqual value="view" name="act">
						<button type="button" onclick="dtjsCommonSave('szdw_zjlg.do?method=xxFkChek&doType=save','','','zgh');">
							审核
						</button>
					</logic:notEqual>
					  <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</html:form>
			<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('审核成功！');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('审核失败');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>
			
</body>
<logic:present name="msg">
	<script>
			alert(''+document.getElementById('msg').value);
		</script>
</logic:present>
</html>
