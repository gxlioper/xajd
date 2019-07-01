<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/qtsj/xywhgl.js"></script>
	<script type="text/javascript" src="js/qtsjFunction.js"></script>

	<body>
		<html:form action="/gdby_xywhgl.do" enctype="multipart/form-data">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置:
					<logic:equal value="shone" name="option">校园文化管理 - 单个审核</logic:equal>
					<logic:equal value="view" name="option">校园文化管理 - 单个查看</logic:equal>
					<logic:equal value="modi" name="option">校园文化管理 - 单个修改</logic:equal>
				</div>
			</div>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
			<fieldset>
				<legend>
					校园文化管理
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>
								<logic:equal value="shone" name="option">校园文化单个审核</logic:equal>
								<logic:equal value="view" name="option">校园文化单个查看</logic:equal>
								<logic:equal value="modi" name="option">校园文化单个修改</logic:equal></b>
							</td>
						</tr>

						<tr>
							<td align="right" width="20%">
								活动代码：
							</td>
							<td align="left" width="30%">
								<html:text property="hddm" value="${rs.hddm }" readonly="true"></html:text>
							</td>
							<td align="right" width="20%">
								活动名称：
							</td>
							<td align="left" width="30%">
								<html:text property="hdmc" value="${rs.hdmc }" readonly="true"></html:text>
							</td>
						</tr>

						<tr>
							<td align="right" width="20%">
								申请人：
							</td>
							<td align="left" width="30%">
								<html:text property="username" value="${rs.username}"
									readonly="true"></html:text>
							</td>
							<td align="right" width="20%">
								申请人电话：
							</td>
							<td align="left" width="30%">
								<html:text property="lxdh" value="${rs.lxdh }" styleId="lxdh"
									readonly="true"></html:text>
							</td>
						</tr>
							<logic:notEqual value="modi" name="option">
						<tr>
							<td align="right" width="20%">
								所需场地：
							</td>
							<td align="left" width="30%" colspan="3">
								<html:text property="cdmc" value="${rs.cdmc }" readonly="true"
									style="width: 500px"></html:text>
							</td>

						</tr>
						<tr>
							<td align="right" width="20%">
								所需物资：
							</td>
							<td align="left" width="30%" colspan="3" styleId="wzmc">
								<html:text property="wzmc" value="${rs.wzmc }" readonly="true"
									style="width: 500px"></html:text>
							</td>
						</tr>
						</logic:notEqual>
						
						<logic:equal value="modi" name="option">
							<tr>
								<input type="hidden" name="xxshsj" value="${rs.xxshsj }"/>
								<input type="hidden" name="xxsh" value="${rs.xxsh }"/>
							</tr>
							<td align="right" width="20%">
								所需场地：
							</td>
							<td align="left" width="30%" colspan="1">
								<html:select property="cdmc" style="width:180px" styleId="cdmc" value="${rs.cdmc }">
									<html:option value=""></html:option>
									<logic:iterate id="map" name="cdList">
										<html:option value="${map.cdmc }">${map.cdmc }</html:option>
									</logic:iterate>
								</html:select>
							</td>
							<td align="right" width="20%">
								所需物资：
							</td>
							<td align="left" width="30%" colspan="1" styleId="wzmc">
								<html:select property="wzmc" style="width:180px" value="${rs.wzmc }">
									<html:option value=""></html:option>
									<logic:iterate id="map" name="wzList">
										<html:option value="${map.wzmc }">${map.wzmc }</html:option>
									</logic:iterate>
								</html:select>
							</td>
						</logic:equal>
						
						<tr>
						<logic:notEqual value="shone" name="option">
							<td align="right" width="20%">
								社团名称：
							</td>
							<td align="left" colspan="3">
								<logic:equal value="" name="rs" property="stmc">
									非社团成员
								</logic:equal>
								<logic:notEqual value="" name="rs" property="stmc">
									<html:text property="stmc" value="${rs.stmc }" style="width: 500px" readonly="true"></html:text>
								</logic:notEqual>
							</td>
						</logic:notEqual>
						
						<logic:equal value="shone" name="option">
							<td align="right" width="20%">
								社团名称：
							</td>
							<td align="left" width="30%">
								<html:text property="stmc" value="${rs.stmc }" style="width:180px" readonly="true"></html:text>
							</td>

								<td align="right" width="20%">
									学校审核：
								</td>
								<td align="left" width="30%">
									<input type="hidden" name="xxshsj" value="${nowtime }" />
									<html:select property="xxsh" value="${rs.xxsh }">
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
							</logic:equal>
						</tr>
					
						<tr>
							<td align="right">
								申请理由：
							</td>
							<td align="left" colspan="3">
								<html:textarea property="sqly" value="${rs.sqly }"
									readonly="true" style="width:99%" rows='8'
									onblur="chLeng(this,200)"></html:textarea>
							</td>
						</tr>

						<tr>
							<td align="right">
								学校审核意见：
								<br />
								<font color="red">(限制在400字内)</font>
							</td>
							<td align="left" colspan="3">
								<html:textarea property="xxshyj" value="${rs.xxshyj }"
									style="width:99%" rows='8' onblur="chLeng(this,200)"></html:textarea>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>


			<div class="buttontool" align="center">
				<logic:notEqual value="view" name="option">
					<button class="button2" id="buttonSave"
						onclick="saveData('/xgxt/gdby_xywhgl.do?method=xywhshone&doType=save','');"
						style="width: 80px">
						保 存
					</button> 
				&nbsp;&nbsp;
				</logic:notEqual>
				<button class="button2" id="buttonClose" onclick="Close();return false;"
					style="width: 80px" id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:present name="msg">
			<input type="hidden" id="message" value="${msg }" />
			<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();	
			}
		</script>
		</logic:present>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
