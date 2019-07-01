<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
	</head>
	<body>
		<html:form action="/zgdzdxXxwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>研究资料单个维护</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="xh" scope="request"/>" />
					<div class="tab">
					<table width="100%" class="formlist">
					<thead>
						<tr><th colspan="4"><span>研究资料</span></th></tr>
					</thead>
					<tbody>
						<tr>
						<logic:equal name="rs" property="xh" value="">
							<th>
								<font color="red">*</font>序号
							</th>
							<td align="left">
								系统生成
							</td>
						</logic:equal>
						<logic:notEqual name="rs" property="xh" value="">
							<th>
								<font color="red">*</font>序号
							</th>
							<td align="left">
								<bean:write name="rs" property="xh"/>
							</td>
						</logic:notEqual>
							<th>
								出版社
							</th>
							<td align="left">
								<html:text name='rs' property="cbdw" styleId="cbdw" maxlength="100" />
							</td>
						</tr>
						<tr>
							<th>
								出版时间
							</th>
							<td align="left">
								<html:text name='rs' property="cbsj" styleId="cbsj" readonly="true"
									onblur="dateFormatChg(this)" style="width:180px;cursor:hand;"
									onclick="return showCalendar('cbsj','y-mm-dd');" />
							</td>
							<th>
								价格
							</th>
							<td align="left">
								<html:text name='rs' property="jg" styleId="jg" maxlength="50"  onkeypress="return sztzNumInputValue(this,6,event)"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>书名(期刊名)
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="sm" styleId="sm" maxlength="50" style="width:90%"/>
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
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
								<button type="button" onclick="dtjsCommonSave('zgdzdxXxwh.do?method=yjzlSave','bz','300','sm');" 
									id="buttonSave">
									保 存
								</button>
					            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table>
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
