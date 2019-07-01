<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="/xgxt/zxdk/gzdx/zxdkjs/zxdkjs.js"></script>
		<script type="text/javascript" src="js/dtjsFuction.js"></script>
		<script type="text/javascript">
</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/zxdk_gzdx" method="post">
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="failinfo" id="failinfo"
				value="${failinfo}" />
			<input type="hidden" name="lx" value="学生" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="zxdk_gzdx_addZxdkSjwh.do?a=null" />

			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单个助学贷款数据增加</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="15%">
								<font color="red">*</font>学号
							</th>
							<td width="35%">
								<html:text name='rs' style="width:90px" property="xh" styleId="xh" readonly="true" />
								<button
									onclick="sendXx();return false;"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
							<th width="15%">
								<font color="red">*</font>学年
							</th>
							<td width="35%">
								<html:select property="xn" styleId="xn" style="width:110px"
									styleClass="select">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								${rs.xm }
							</td>
							<th>
								助学贷款名称
							</th>
							<td align="left">
								<html:select property="zxdkmc" style="width:110px" styleId="zxdkmc">
									<html:option value=""></html:option>
									<html:option value="身源地贷款">身源地贷款</html:option>
									<html:option value="国家助学贷款">国家助学贷款</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								贷款金额
							</th>
							<td align="left">
								<html:text property="dkje" styleId="dkje" style="width:90px" maxlength="5" onblur="checkInputNum(this);"></html:text> 元
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td align="left">
								${rs.nj }
							</td>
							<th>
								
							</th>
							<td align="left">
							
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />

							</th>
							<td align="left">
								${rs.xymc }
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td align="left">
								${rs.zymc }
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left" colspan="">
								${rs.bjmc }
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						<tr>
							<th>
								备注<br/> <font color="blue">(最多能输入255个字符)</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea property="bz" styleId="bz" rows="7"
									style="width:450px"></html:textarea>
							</td>
						</tr>

						</tbody>
						<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="btn_save"
										onclick="saveinfo();">
										保存
									</button>
									<button id="btn_close" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</div>
				<!-- 保存后提示页面 -->
				<logic:present name="inserted">
					<logic:equal value="yes" name="inserted">
						<script>
					alert("操作成功!");
					Close();
					if (window.dialogArguments) {
						window.dialogArguments.document.getElementById('search_go').click();	
					}
				</script>
					</logic:equal>
					<logic:equal value="no" name="inserted">
						<script>
					alert("操作失败,原因可能是数据库中已存在相同记录!");
					Close();
					if (window.dialogArguments) {
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
					</logic:equal>
					<logic:equal value="exists" name="inserted">
						<script>
					alert("数据库中已存在相同记录，请确认！");
					Close();
					if (window.dialogArguments) {
						window.dialogArguments.document.getElementById('search_go').click();					
					}
				</script>
					</logic:equal>
				</logic:present>
		</html:form>
	</body>
	</html>