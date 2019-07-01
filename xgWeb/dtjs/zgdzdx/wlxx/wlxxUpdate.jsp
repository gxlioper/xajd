<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript">
		function saveWlxx(){
			if($("xh").value == ""){
				alert("学号不能为空，请确认！！");
				return false;
			}
			showTips('处理数据中，请等待......');
			refreshForm('/xgxt/zgddWlxx.do?method=wlxxUpdate&doType=save');
		}
</script>
</head>
	<body onload="">
		
		<html:form action="/zgddZbr" method="post">
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>党团建设 - 数据维护 - 网络信息管理</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/dtjs/zgdzdx/wlxx/wlxxUpdate.jsp" />
				<fieldset>
					<div class="tab">
					<table width="100%" class="formlist" border="0">
					<thead>
						<tr><th colspan="4"><span>网络信息</span></th></tr>
					</thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
					          <logic:notEqual name="doType" value="view">
								<button type="button" onclick="saveWlxx();" id="buttonSave">
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
								<font color="red">*</font>学号
							</th>
							<td align="left" style="width:35%">
								<html:text name='rs' property="xh" readonly = "true"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<logic:equal name="doType" value="add">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do?wlxx=yes',750,550);"
									class="btn_01" id="buttonFindStu" >
									选择
								</button>
								</logic:equal>
							</td>
							<th>
								姓名
							</th>
							<td align="left" style="width:35%">
								<html:text name='rs' property="xm" styleId="xm" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly = "true" />
							</td>
							<th>
								年级
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj"  readonly = "true" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly = "true"/>
							</td>
							<th>
								专业
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly = "true"/>
							</td>
							<th>
								值班ID
							</th>
							<td align="left">
								<html:text name='rs' property="zbid" styleId="zbid" maxlength="20" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								岗位
							</th>
							<td align="left">
								<html:text name='rs' property="gwmc" styleId="gwmc" maxlength="25" readonly = "true"/>
							</td>
							<th>
								联系电话
							</th>
							<td align="left">
								<html:text name='rs' property="dh" styleId="dh" maxlength="20" 
								onkeyup="value=value.replace(/[^\d]/g,'') " readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								银行账号
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="yhzh" styleId="yhzh" style="width:95%" maxlength="50"
								onkeyup="value=value.replace(/[^\d]/g,'') " readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								开始时间
							</th>
							<td align="left">
								<html:text name="rs" property="kssj" styleId="kssj" style="width:95%"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('kssj','y-mm-dd');"/>
							</td>
							<th>
								结束时间
							</th>
							<td align="left">
								<html:text name="rs" property="jssj" styleId="jssj" style="width:95%"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jssj','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
							<th>
								值班情况
							</th>
							<td align="left" colspan="3">
								<html:textarea name='rs' property="zbqk" styleId="zbqk" style="width:95%"
								onblur="chLeng(this,250)"/>
							</td>
						</tr>
						<tr>
							<th>
								值班备注
							</th>
							<td align="left" colspan="3"><br /><html:textarea name='rs' property="wlbz" styleId="wlbz" style="width:95%"
								onblur="chLeng(this,250)"/>
							<br /></td>
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
