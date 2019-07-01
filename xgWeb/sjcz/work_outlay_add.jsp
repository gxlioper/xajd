<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
	function checkThenSubmit(url, pkFields) {
		var eles = pkFields.split("-");
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
		if (confirm('确认要对经费增拨数据进行保存吗？')) {
			document.forms[0].action = url;
			document.forms[0].submit();
			return true;
		}
		
	}
	</script>
</head>
	<body>
		<html:form action="/comm_pub" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em>
						<a>
							<%--长沙民政--%>
							<logic:equal value="10827" name="xxdm">
								学生义工 - 经费管理 - 经费增拨
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								勤工助学 - 经费管理 - 经费增拨
							</logic:notEqual>
						</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<div class="tab">
		  		<table width="100%" border="0" class="formlist">
				<thead>
					<tr align="center">
						<th colspan="4">
							<span>各<bean:message key="lable.xsgzyxpzxy" />及单位勤工经费增拨</span>
						</th>
					</tr>
				 </thead>
				 <tbody>
					<tr>
						<th>学年</th>
						<td>
							<html:text name="rs" property="xn" styleId="xn" style="width: 90px" readonly="true"/>
						</td>						
						<th>年度</th>					
						<td>
							<html:text name="rs" property="nd" styleId="nd" style="width: 90px" readonly="true"/>
						</td>						
					</tr>
					<tr>
						<th>学期</th>
						<td>
							<html:text name="rs" property="xqmc" styleId="xqmc" style="width: 90px" readonly="true"/>
							<html:hidden name="rs" property="xq" styleId="xq" style="width: 90px"/>
						</td>
						<logic:equal value="11417" name="xxdm">
						<th><span class="red">*</span>所属单位</th>
						<td>
							<html:select name="rs" property="yrdwdm" styleId="yrdwdm" style="width:180px" onchange="getGwDetInfo()">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>
						</td>
						</logic:equal>
					
						<logic:notEqual value="11417" name="xxdm">
						<th><span class="red">*</span>用人单位</th>
						<td>
							<html:select name="rs" property="yrdwdm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>
						</td>
						</logic:notEqual>
					</tr>
					<logic:present name="showbjlh">
						<tr>
							<td height="22" align="right">所属部门</td>
							<td height="22" align="left"></td>
							<td height="22" align="right">岗位数</td>
							<td height="22" align="left"></td>
						</tr>
						<tr>
							<td height="22" align="right">用人量</td>
							<td height="22" align="left"></td>
							<td height="22" align="right">已划拨经费总量</td>
							<td height="22" align="left"></td>
						</tr>
						<tr>
							<td height="22" align="right">已使用量</td>
							<td height="22" align="left"></td>
							<td height="22" align="right">节余</td>
							<td height="22" align="left"></td>
						</tr>
					</logic:present>
					<tr>
						<th><span class="red">*</span>增拨金额</td>
						<td>
							<html:text name="rs" property="hbje" styleId="hbje" maxlength="8" onkeyup="value=value.replace(/[^\d|.]/g,'') "></html:text>(元)
						</td>
						<th><span class="red">*</span>岗位性质</th>
						<td>
							<html:select name="rs" property="gwxzdm" styleId="gwxzdm" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="gwxzList" property="gwxzdm"
										labelProperty="gwxzmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="3">
							<html:textarea name="rs" property="bz" style="width:85%" rows="5" onblur="chLeng(this,'250')"></html:textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <logic:notPresent name="showbjlh">
							<button type="button" onclick="checkThenSubmit('/xgxt/work_outlay_add.do?dotype=add','xn-nd-xq-yrdwdm-hbje-gwxzdm');">保存
							</button>
						</logic:notPresent>

						<logic:present name="showbjlh">
							<button type="button" onclick="if(identifyInt()){checkThenSubmit('/xgxt/work_outlay_add.do?dotype=add','xn-xq-yrdwdm-hbje-gwxzdm')};">保存
							</button>
						</logic:present>
			          </div>
			        </td>
			      </tr>
			    </tfoot>	
				</table>
				</div>
			</logic:notEmpty>

			<logic:notEmpty name="ok">
				<logic:equal name="ok" value="ok">
					<script>alert("保存成功!")</script>
				</logic:equal>
				<logic:equal name="ok" value="no">
					<script>alert("保存失败!")</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
