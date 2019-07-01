<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
	<script language="javascript">
	function saveDzb(){
		if($("xy").value == ""){
			alert("<bean:message key="lable.xsgzyxpzxy" />名称不能为空，请确认！！");
			return false;
		}
		if($("dzbmc").value == ""){
			alert("党支部名称不能为空，请确认！！");
			return false;
		}
		showTips('处理数据中，请等待......');
		var url = '/xgxt/zgddDzb.do?method=dzbUpdate&doType=save';
		if($("xyV")){
			url+="&xyV="+$("xyV").value;
		}
		refreshForm(url);
	}
</script>
	</head>
	<body onload="">
		<html:form action="/zgddZbr" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>党团建设 - 数据维护 - 党支部管理</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/zgddZbr.do?method=zbrUpdate&doType=stuInfo" />
				<fieldset>
					<div class="tab">
					<table width="100%" class="formlist">
						<thead>
						<tr>
						<th colspan="4"><span>党支部信息</span></th>
						</tr>
						</thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
					         <logic:notEqual name="doType" value="view">
								<button type="button" onclick="saveDzb();" id="buttonSave">
									保 存
								</button>
								</logic:notEqual>
								<button type="button" onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
					          </div></td>
					      </tr>
					    </tfoot>
						
						<tbody>
						<tr>
							<th>
								<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left" style="width:35%">
								<logic:equal name="doType" value="add">
								<html:select name="rs" property="xydm" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:equal>	
								<logic:notEqual name="doType" value="add">
								<input type="hidden" id="xyV" name="xyV" value="<bean:write name="rs" property="xydm"/>" />
								<html:select name="rs" property="xydm" style="width:180px" styleId="xy" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>	
								</logic:notEqual>				
							</td>
							<th>
								<font color="red">*</font>党支部名称
							</th>
							<td align="left">
								<logic:equal name="doType" value="add">
								<html:text name='rs' property="dzbmc" styleId="dzbmc" maxlength="25"/>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
								<html:text name='rs' property="dzbmc" styleId="dzbmc" maxlength="25" readonly="true"/>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								责任区
							</th>
							<td align="left">
								<html:text name='rs' property="zrq" styleId="zrq" maxlength="10"/>
							</td>
							<th>
								党支部成员数
							</th>
							<td align="left">
								<html:text name='rs' property="dzbcys" styleId="dzbcys" maxlength="5" 
								onkeyup="value=value.replace(/[^\d]/g,'') "/>
							</td>
						</tr>
						<tr>
							<th>
								党支部与<br/>教育活动<br/>举措
							</th>
							<td align="left" colspan="3">
								<html:textarea name='rs' property="dzbjycs" styleId="dzbjycs" style="width:95%" rows="5"
								onblur="chLeng(this,250)"/>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td align="left" colspan="3">
								<html:textarea name='rs' property="dzbbz" styleId="dzbbz" style="width:95%" rows="5"
								onblur="chLeng(this,250)"/>
							</td>
						</tr>
						</tbody>
					</table>
					</div>
				</fieldset>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("提交成功！");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("提交失败！");
				    </script>				
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
