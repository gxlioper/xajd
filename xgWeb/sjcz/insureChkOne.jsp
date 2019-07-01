<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function sele(){
			var userType = document.getElementById("userType").value;
			if(userType=="xy"){
				document.getElementById("xxyj").disabled="disabled";
			}else{
				document.getElementById("xyyj").disabled="disabled";
			}
		}
	</script>
	</head>
	<body onload="checkWinType();sele()">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>其他数据 - 保险信息 - 学校审核 - 单个审核</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<%--非上海工程技术大学--%>
			<logic:notEqual value="10856" name="xxdm">
				<input type="hidden" name="pkVal"
					value="<bean:write name="xh||nd||tbgsdm||tbxzdm"/>" />
			</logic:notEqual>
			<%--上海工程技术大学--%>
			<logic:equal value="10856" name="xxdm">
				<input type="hidden" name="pkVal"
					value="<bean:write name="xh||nd"/>" />
			</logic:equal>
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType"/>" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生保险单个审核</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"
										onclick="refreshForm('/xgxt/insureChkOne.do?act=save');Close();window.dialogArguments.document.getElementById('search_go').click();"
										id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								学号
							</th>
							<td align="left">
								<bean:write name="XH" />
							</td>
							<th>
								年度
							</th>
							<td align="left">
								<bean:write name="ND" />
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								<bean:write name="XM" />
							</td>
							<th>
								学年
							</th>
							<td align="left">
								<bean:write name="XN" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<bean:write name="XB" />
							</td>
							<th>
								保险公司
							</th>
							<td align="left">
								${bxgsmc }
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td align="left">
								<bean:write name="NJ" />
							</td>
							<th>
								保险险种
							</th>
							<td align="left">
								${bxxzmc }
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />

							</th>
							<td align="left">
								${XYMC}
							</td>
							<th>
								保险期限
							</th>
							<td align="left">
								${bxnx }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td align="left">
								${ZYMC}
							</td>
							<th>
								保费
							</th>
							<td align="left">
								${bf}
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								${BJMC}
							</td>
							<th>
								审核
							</th>
							<td align="left">
								<html:select property="yesNo" name="rs">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								意见
							</th>
							<td align="left" colspan="3">
								<html:textarea property="xyyj" styleId="xyyj"
									style="width:100%;height:45" name="rs"
									onblur="chLeng(this,100)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								学校意见
							</th>
							<td align="left" colspan="3">
								<html:textarea property="xxyj" styleId="xxyj"
									style="width:100%;height:45" name="rs"
									onblur="chLeng(this,100)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
