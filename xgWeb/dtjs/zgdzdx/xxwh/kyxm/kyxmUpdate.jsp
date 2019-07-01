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
					<em>您的当前位置:</em><a>科研项目单个维护</a>
				</p>
			</div>
			
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
					<div class="tab">
					<table width="100%" class="formlist">
						<thead>
						<tr><th colspan="4"><span>科研项目</span></th></tr>
						</thead>
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>部门
							</th>
							<td align="left">
								<html:select name = "rs" property="bmdm" style="width:180px" styleId="xy" onchange="getFdyList();">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>项目负责
							</th>
							<td align="left">
								<html:select name = "rs" property="zgh"  styleId="zgh" style="width:150px" >
										<html:option value=""></html:option>
										<html:options collection="fdyList" property="zgh"
											labelProperty="xm" />
								</html:select>
							<input type="hidden" name="zghV" value="<bean:write name="rs" property="zgh" scope="request"/>"/>
							</td>
						</tr>
						<tr>
						<logic:equal name="rs" property="pk" value="">
							<th>
								<font color="red">*</font>序号
							</th>
							<td align="left">
								系统生成
							</td>
						</logic:equal>
						<logic:notEqual name="rs" property="pk" value="">
							<th>
								<font color="red">*</font>序号
							</th>
							<td align="left">
								<bean:write name="rs" property="xh"/>
							</td>
						</logic:notEqual>
							<th>
								负责人
							</th>
							<td align="left">
								<html:text name='rs' property="fzr" styleId="fzr" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目级别
							</th>
							<td align="left">
									<html:select name= "rs" property="xmjbdm" style="width:180px" styleId="xmjbdm" >
										<html:option value=""></html:option>
										<html:options collection="xmjbList" property="xmjbdm"
											labelProperty="xmjbmc" />
									</html:select>
							</td>	
							<th>
								参与人
							</th>
							<td align="left">
								<html:text name='rs' property="cyr" styleId="cyr" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								开始时间
							</th>
							<td align="left">
								<html:text name='rs' property="kssj" styleId="kssj" readonly="true"
									onblur="dateFormatChg(this)" style="width:180px;cursor:hand;"
									onclick="return showCalendar('kssj','y-mm-dd');" />
							</td>
							<th>
								结束时间
							</th>
							<td align="left">
								<html:text name='rs' property="jssj" styleId="jssj" readonly="true"
									onblur="dateFormatChg(this)" style="width:180px;cursor:hand;"
									onclick="return showCalendar('jssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								科研经费
							</th>
							<td align="left">
								<html:text name='rs' property="kyjf" styleId="kyjf"  onkeypress="return sztzNumInputValue(this,20)"/>
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目名称
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="xmmc" styleId="xmmc" maxlength="50" style="width:90%"/>
							</td>
						</tr>
						<tr align="left" id="xmjs">
							<th>
								项目介绍
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xmjs' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left" id="xmly">
							<th>
								项目来源
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xmly' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
						
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
								<button type="button" onclick="dtjsCommonSave('zgdzdxXxwh.do?method=kyxmSave','xmjs-xmly','300-300','zgh-bmdm-xmmc');" 
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
