<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		</head>
	<body onload="checkWinType();">
		<html:form action="/zgdzdxXxwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>主题教育活动 维护</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<fieldset>
					
					<div class="tab">
					<table width="100%" class="formlist" border="0">
						<thead>
							<tr>
								<th colspan="4"><span>主题教育活动</span></th>
							</tr>
						</thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
					        	  <button type="button" 
									onclick="dtjsCommonSave('zgdzdxXxwh.do?method=sxjyhdSave','hdnr-hdxg-bz','600-600-300','xh-xydm');"
									id="buttonSave">
									保 存
								</button>
								<button type="button" onclick="Close();return false;"
									id="buttonClose">
									关 闭
								</button>
					          </div></td>
					      </tr>
					    </tfoot>
						
						<tbody>
						<tr>
							<th>
								<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td align="left">
								<html:select name = "rs" property="xydm" style="width:180px" styleId="xy" >
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>序号
							</th>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh" maxlength="10" onkeypress='return numInputValue(this,10,event)' style="width:180px"/>
							</td>
							<th>
								发生时间
							</th>
							<td align="left">
								<html:text name='rs' property="fssj" styleId="fssj" readonly="true"
									onblur="dateFormatChg(this)" style="width:180px;cursor:hand;"
									onclick="return showCalendar('fssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap">
								活动主题
							</td>
							<td align="left" colspan="3">
								<html:text name='rs' property="hdzt" styleId="hdzt" maxlength="100" style="width:90%"/>
							</td>
						</tr>
						<tr>
							<th>
								活动地点
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="hddd" styleId="hddd" maxlength="100" style="width:90%"/>
							</td>
						</tr>
						
						<tr align="left" id="hdnr">
							<th>
								活动内容
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='hdnr' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left" id="hdxg">
							<td align="right" >
								活动效果
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='hdxg' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left" id="bz">
							<th>
								备注
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
					</div>
				</fieldset>
				<div class="buttontool">
				</div>
			</logic:notEmpty>
			<logic:present name="update">
				<logic:equal name="update" value="yes">
					<script>
    alert("提交成功！");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
