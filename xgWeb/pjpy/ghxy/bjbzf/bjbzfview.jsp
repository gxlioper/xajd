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
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript">
		function selText(obj){
			var text = "";
			if(obj.selectedIndex>=0){
				text = obj.options[obj.selectedIndex].text;
			}
			text = text === null || text === "" || text === "null" ? "" : text;
	
			return text;
		}
		
		function changeBzdj(obj){
			var bzdj = selText(obj);
			var bzf = obj.value;
			
			$('bzdj').value =  bzdj;
			$('span_bzf').innerHTML = bzf;
		}
		
	</script>

	<body>
		<html:form action="/ghxy_bjbz.do">
			<input type="hidden" name="pkValue" value="${pkValue }" />

			<div class="title">
				<logic:equal value="view" name="operation">当前位置：班级表彰分单个查看
				</logic:equal>
				<logic:equal value="modi" name="operation">当前位置：班级表彰分单个修改
				</logic:equal>
			</div>
			<fieldset>
				<legend>
					学生基本信息
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>个人信息</b>
							</td>
						</tr>
					</thead>
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font style="color: red">*</font>学号：
						</td>
						<td align="left" width="35%">
							<html:text styleId="xh" property="save_xh"
								style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							<input type="hidden" id="xn" name="save_xn" value="${rs.xn }" />
							${rs.xn }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							学期：
						</td>
						<td align="left">
							<input type="hidden" id="xq" name="save_xq" value="${rs.xq }" />
							${rs.xq }
						</td>
						<td align="right">
							年级：
						</td>
						<td align="left">
							${rs.nj }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							月度：
						</td>
						<td align="left">
							<input type="hidden" id="yd" name="save_yd" value="${rs.yd }" />
							${rs.yd}
						</td>
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							${rs.xm }
						</td>

						<td align="right">
							性别：
						</td>
						<td align="left">
							${rs.xb }
						</td>
					</tr>
					<logic:equal value="view" name="operation">
						<tr style="height: 23px">
							<td align="right">
								表彰等级：
							</td>
							<td align="left">
								${rs.bzdj }
							</td>

							<td align="right">
								表彰分：
							</td>
							<td align="left">
								${rs.bzf }
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="modi" name="operation">
						<tr style="height: 23px">
							<td align="right">
								表彰等级：
							</td>
							<td align="left">
							<html:select property="save_bzf" style="width:90px"
											value="${rs.bzf }" onchange="changeBzdj(this);">
											<option></option>
											<html:options collection="djList" property="jf"
												labelProperty="dj" />
										</html:select>
							<input type="hidden" id="bzdj" name="save_bzdj" value="${rs.bzdj }"/>
							</td>
							<td align="right">
								表彰分：
							</td>
							<td align="left">
								<span id="span_bzf">${rs.bzf }<span>
							</td>
						</tr>
					</logic:equal>
					<tr align="left" style="height: 23px">
						<td>
							<div align="right">
								年级部主任审核
							</div>
						</td>
						<td>
							${rs.njbzrsh }
						</td>
						<td align="right">
							学校审核：
						</td>
						<td>
							${rs.xxsh }
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton"> <logic:equal value="modi"
						name="operation">
						<button type="button" class="button2" id="buttonSave"
							onclick="BatAlert.showTips('正在操作...请稍候');saveData('/xgxt/ghxy_bjbz.do?method=bjbzfViewAndModi&doType=save','');"
							style="width: 80px">
							保 存
						</button>
					</logic:equal>
					<button type="button" class="button2" onclick="Close();return false;" style="width: 80px">
						关 闭
					</button> </span>
			</div>
			<logic:present name="result">
				<hidden type="hidden" id="msg" value="${message}" />
				<script>
					alert($("msg").value);
					Close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						window.dialogArguments.document.getElementById('search_go').click();	
					}
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
