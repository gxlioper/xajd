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
			<em>您的当前位置:</em><a>思政队伍 -  信息反馈 - 辅导员申报 - 工作信息</a>
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
		<input type="hidden" id="pk" name="pk"
			value="<bean:write name = "pk"/>" />
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
		<div class="tab">
		<table class="formlist" width="90%" id="theTable">
			<thead><tr><th colspan="4"><span>信息维护</span></th></tr></thead>
			<tbody>
			<logic:notPresent name="isSzdw">
			<tr>
				<th>
					部门名称
				</th>
				<logic:notEqual name="userType" value="admin" scope="session">
					<td align="left" width="34%">
						<input type = "hidden" name = "bmdm" value ="${bmdm }" />
						<html:select name = "rs" property="bmdm" styleId="bmdm" disabled="true" style="width:50%" >
							<html:options collection="bmList" property="bmdm"
								labelProperty="bmmc" />
						</html:select>
					</td>
				</logic:notEqual>
				<logic:equal name="userType" value="admin" scope="session">
					<td align="left" width="34%">
						<html:select name = "rs" property="bmdm" styleId="bmdm" onchange="getFdyList()" style="width:100%">
							<html:option value=""></html:option>
							<html:options collection="bmList" property="bmdm"
								labelProperty="bmmc" />
						</html:select>
					</td>
				</logic:equal>
				<th>
					<font color="red">*</font>辅导员
				</th>
					<td width="34%">
						<html:select  property="zgh"  styleId="zgh" style="width:150px" onchange="refreshForm('szdw_zjlg.do?method=xxFkSq')" styleId="zgh">
								<html:option value=""></html:option>
								<html:options collection="fdyList" property="zgh"
											labelProperty="xm" />
								</html:select>
						<input type="hidden" name="zghV" value="<bean:write name="rs" property="zgh" scope="request"/>"/>
					</td>
			</tr>
			</logic:notPresent>
			<logic:present name="isSzdw">
				<tr>
				<th>
					部门名称
				</th>
				<td align="left" width="34%">
					 <bean:write name="rs" property="bmmc" /> 
				</td>
				<th>
					<font color="red">*</font>辅导员
				</th>
					<td width="34%">
						<bean:write name="rs" property="zgh"/>
						<input type="hidden" name="zgh" id="zgh" value="<bean:write name="rs" property="zgh"/>" />
					</td>
			</tr>
			</logic:present>
			<tr>
				<th>
					性别
				</th>
				<td>
					<bean:write name="rs" property="xb"/>
				</td>
				<th>
					从事辅导员工作时间
				</th>
				<td>
					<bean:write name="rs" property="szgzsj"/>
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
			<tr>
				<th>
					信息主题<font color="red">*</font>
				</th>
				<td align="center" colspan="3">
					<html:text property="xxzt" style="width:100%" maxlength="50" styleId="xxzt"/>
				</td>
			</tr>
			<tr id = "jtnr">
				<th>
					建议意见
				</th>
				<td colspan="3">
					<html:textarea property="jyyj" rows='5' style="width: 95%;word-break:break-all;" onblur="chLeng(this,300)"/>
				</td>
			</tr>
			<tr id = "jtnr">
				<th>
					具体内容
				</th>
				<td colspan="3">
					<html:textarea property="jtnr" rows='15' style="width: 95%;word-break:break-all;" onblur="chLeng(this,600)"/>
				</td>
			</tr>
			<tr id = "bz">
				<th>
					备注
				</th>
				<td colspan="3">
					<html:textarea property="bz" rows='3' style="width: 95%;word-break:break-all;" onblur="chLeng(this,300)"/>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
					<logic:notEqual value="view" name="act">
						<button type="button" onclick="dtjsCommonSave('szdw_zjlg.do?method=xxFkSq&doType=save','','','zgh-xxzt');">
							提交
						</button>
					</logic:notEqual>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	
	</html:form>
				<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('申请成功！');
			    Close();
			    if(window.dialogArguments){
	         	window.dialogArguments.document.getElementById('search_go').click();
	         	}
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('申请失败！,请确认该辅导员是否已申请');
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
