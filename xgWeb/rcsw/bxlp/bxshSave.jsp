<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/checkUtils.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript">
		
			function loadBxgs(text){
				if ("" != text){
					getInsureInfo.getBxgsdm(text,function(data){
						$('tbgsdm').value = data;
					});
				} else {
					$('tbgsdm').value = "";
				}
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>日常事务-保险、理赔-保险信息维护</a>
			</p>
		</div>


		<html:form action="/rcsw_bxlp" method="post">
			<input type="hidden" name="pk" value="${pk }" />
			<input type="hidden" id="url" name="url"
				value="/rcsw_bxlp.do?method=bxwhSave" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>保险审核</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<span> "<font color="red">*</font>"为必填项 </span>
								</div>
								<div class="btn">
									<button type="button" id="buttonSave"
										onclick="refreshForm('/xgxt/rcsw_bxlp.do?method=bxshSave&doType=save&shzt=通过')">
										通  过
									</button>
									<button type="button" id="buttonSave"
										onclick="refreshForm('/xgxt/rcsw_bxlp.do?method=bxshSave&doType=save&shzt=不通过')">
										不通过
									</button>
									<button type="button" id="buttonSave" onclick="window.close();return false;">
										关  闭
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								<html:text name='rs' property="xh" styleId="xh" readonly="true"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								<html:text property="xm" name="rs" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<html:text property="xb" name="rs" readonly="true"></html:text>
							</td>
							<th>
								身份证号
							</th>
							<td>
								<html:text property="sfzh" readonly="true" name="rs"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								<html:select property="nj" name="rs" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								名称
							</th>
							<td>
								<html:select property="xydm" name="rs" disabled="true"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								专业名称
							</th>
							<td>
								<html:select property="zydm" name="rs" disabled="true"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级名称
							</th>
							<td>
								<html:select property="bjdm" name="rs" disabled="true"
									style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>保险公司
							</th>
							<td>
								<html:select property="bxgsdm" name="rs" disabled="true"
									onchange="loadBxxzList()" styleId="tbgsdm">
									<html:options collection="bxgsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>投保险种
							</th>
							<td>
								<html:select property="tbxzdm" name="rs" disabled="true"
									onchange="loadBxgs(this.value)" styleId="tbxzdm">
									<html:options collection="bxxzList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>


						<tr>
							<th>
								金额
							</th>
							<td>
								<html:text property="je" name="rs" onblur="checkMoney(this);"
									maxlength="10" styleId="bf" disabled="true"></html:text>
							</td>
							<th>
								年限
							</th>
							<td>
								<html:text property="nx" name="rs" styleId="bxnx" maxlength="2"
									onkeyup="value=value.replace(/[^\d]/g,'')" disabled="true"></html:text>
							</td>
						</tr> 
						<tr>
							<th>
								审核状态
							</th>
							<td >
								<logic:equal name="fdyQx" value="true">
									${rs.fdysh }
								</logic:equal>
								<logic:notEqual name="fdyQx" value="true">
									<logic:equal name="userType" value="xy">
										${rs.xysh }
									</logic:equal>
									<logic:equal name="userType" value="xx">
										${rs.xxsh }
									</logic:equal>
									<logic:equal name="userType" value="admin">
										${rs.xxsh }
									</logic:equal>
								</logic:notEqual>
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr> 
						<logic:equal name="fdyQx" value="true">
						<tr>
							<th>
								辅导员意见<br/>
								(<font color="red">限500字</font>)
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='fdyshyj' styleId="fdyshyj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
									rows='4' />
							</td>
						</tr> 
						</logic:equal>
						<logic:notEqual name="fdyQx" value="true">
						<tr>
							<th>
								辅导员意见<br/>
								(<font color="red">限500字</font>)
							</th>
							<td colspan="3">
								<html:textarea name='rs' readonly="true" property='fdyshyj' styleId="fdyshyj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
									rows='4' />
							</td>
						</tr> 
						</logic:notEqual>
						
						<logic:equal name="userType" value="xy">
						<logic:notEqual name="fdyQx" value="true">
						<tr>
							<th>
								<bean:message key="lable.xb" />意见<br/>
								(<font color="red">限500字</font>)
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xyshyj' styleId="xyshyj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
									rows='4' />
							</td>
						</tr> 
						</logic:notEqual>
						</logic:equal>
						<logic:equal name="userType" value="xx">
							<tr>
								<th>
									<bean:message key="lable.xb" />意见<br/>
									(<font color="red">限500字</font>)
								</th>
								<td colspan="3">
									<html:textarea name='rs' property='xyshyj' readonly="true" styleId="xyshyj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
								</td>
							</tr> 
						</logic:equal>
						<logic:equal name="userType" value="admin">
							<tr>
								<th>
									<bean:message key="lable.xb" />意见<br/>
									(<font color="red">限500字</font>)
								</th>
								<td colspan="3">
									<html:textarea name='rs' property='xyshyj' readonly="true" styleId="xyshyj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
								</td>
							</tr> 
						</logic:equal>
						<logic:equal name="userType" value="xx">
						<tr>
							<th>
								学校意见<br/>
								(<font color="red">限500字</font>)
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xxshyj' styleId="xxshyj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
									rows='4' />
							</td>
						</tr> 
						</logic:equal>
						<logic:equal name="userType" value="admin">
						<tr>
							<th>
								学校意见<br/>
								(<font color="red">限500字</font>)
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xxshyj' styleId="xxshyj" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
									rows='4' />
							</td>
						</tr> 
						</logic:equal>
						<customTag:customTable rsname="rs" nodeslist="bxlpForm"
							doType="updata" scope="request" />
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alertInfo("保存成功！",function(t){
	         		if(t=="ok"){
	         			 window.close();
					 	window.dialogArguments.document.getElementById('search_go').click();
	         		}
	         	});
	         	
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
