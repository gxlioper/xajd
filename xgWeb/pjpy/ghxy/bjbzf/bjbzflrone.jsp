<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />

		<script type="text/javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&queryequals_bjdm='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要录入的班级！');
				return false;
			}
		}
		
		function selText(obj){
			var text = "";
			if(obj.selectedIndex>=0){
				text = obj.options[obj.selectedIndex].text;
			}
			text = text === null || text === "" || text === "null" ? "" : text;
	
			return text;
		}
		
		function saveBatch(url){
		document.forms[0].action = url;
		document.forms[0].submit();
		}
		
		function changeBzdj(obj,num){
			var text = selText(obj);
			var id = "bzdj"+num;
			if($(id)){
			$(id).value = text;
			}
			//curr_row.getElementsByTagName('input')[1].value = text;
		}
		
		</script>
	</head>

	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>

		<html:form action="ghxy_bjbz.do?method=bjbzflrone" method="post">
			<input type="hidden" name="yd" value="${yd }" />
			<input type="hidden" name="xn" value="${xn }" />
			<input type="hidden" name="xq" value="${xq }" />
			<input type="hidden" name="bjdm" value="${bjdm }" />
			<div class="title">
				<div class="title_img" id="title_m">
					单个班级录入
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center">
							职工号：
							<bean:write name="userName" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							姓名：
							<bean:write name="userNameReal" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

							学年：${xn }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							学期：${xq }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							月度：${param.yd }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</thead>
			</table>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						班级学生表彰分录入
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr>
								<td align="center" colspan="4">
									所带班级录入
								</td>
							</tr>
							<tr>
								<td align="center">
									学号
								</td>
								<td align="center">
									姓名
								</td>
								<td align="center">
									班级
								</td>
								<td align="center">
									表彰等级
									<font color="red">(正在被审核学生不能修改)</font>
								</td>
							</tr>
						</thead>
						<logic:iterate id="map" name="rs" indexId="num">
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
								<logic:equal value="yes" name="map" property="sfsh">
									<td align="center">
										<input type="hidden" name="counts" value="1"/>
										${map.xh }
									</td>

									<td align="center">
										${map.xm }
									</td>

									<td align="center">
										${map.bjmc }
									</td>
									<td align="center">
										<span> ${map.bzdj }</span>
									</td>
								</logic:equal>

								<logic:notEqual value="yes" name="map" property="sfsh">
									<td align="center">
										<input type="hidden" name="counts" value="1"/>
										<input type="hidden" name="xhs" value=${map.xh } />
										${map.xh }
									</td>

									<td align="center">
										${map.xm }
									</td>

									<td align="center">
										${map.bjmc }
									</td>
									<input type="hidden" name="bzdj" id="bzdj${num }" value="${map.bzdj }" />
								
									<td align="center">
										<html:select property="bzf" style="width:90px"
											value="${map.bzf }" onchange="changeBzdj(this,'${num }');">
											<option></option>
											<html:options collection="djList" property="jf"
												labelProperty="dj" />
										</html:select>
									</td>
							</logic:notEqual>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button type="button" class="button2"
					onclick="saveBatch('ghxy_bjbz.do?method=bjbzflrone&doType=save');"
					style="width:80px">
					保 存
				</button>

				<button type="button" class="button2" onclick="Close();return false;" style="width:80px">
					返 回
				</button>
			</div>

			<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
			</script>
			<logic:present name="msg">
				<hidden type="hidden" id="msg" value="${msg}" />
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>

		</html:form>
	</body>
</html>
