<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
		function saveGwsz(){
			refreshForm('/xgxt/xtwhGwsz.do?method=gwszInsert&doType=add');
		}
		
		function updateGwsz(){
			refreshForm('/xgxt/xtwhGwsz.do?method=gwszInsert&doType=update');
		}
	</script>
	</head>
	<body>
		
		<html:form action="/xtwhGwsz" method="post">
			<input type="hidden" name ="xxdm" id = "xxdm" value="<bean:write name="xxdm" />"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>
				
						<div class="tab">
							<table width="100%"  border="0" class="formlist">
							 <thead>
			    				<tr>
			        				<th colspan="4"><span>岗位维护</span></th>
			        			</tr>
			   				 </thead>
			   				
			   				 <tbody>
							<tr>
								<th style="width:16%">
									<font color="red">*</font>岗位名称
								</th>
								<td style="width:34%">
								<logic:empty name="rs">
									<html:text property="save_xtgwmc"/>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_xtgwmc"/>
								</logic:notEmpty>
								</td>
								<th style="width:16%">
									<font color="red">*</font>是否可见
								</th>
								<td style="width:34%"> 
								<logic:empty name="rs">
									<html:select property="save_sfqy" styleId="sfqy"  style="width:150px"
											>
											<html:option value=""></html:option>
											<html:options collection="sfqyList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:select name="rs" property="save_sfqy" styleId="sfqy"  style="width:150px"
											>
											<html:option value=""></html:option>
											<html:options collection="sfqyList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>录入人
								</th>
								<td>
								<logic:empty name="rs">
									<input type="text" name="lrr" value="${userInfo.xm}" disabled="true"/>
									<html:hidden property="save_lrr" value="${userName}"/>
								</logic:empty>
								<logic:notEmpty name="rs">
									<input type="text" name="lrr" value="${userInfo.xm}" disabled="true"/>
									<html:hidden name="rs" property="save_lrr" value="${userName}"/>
								</logic:notEmpty>
								</td>
								<th align="right">
									录入时间
								</th>
								<td>
								<logic:empty name="rs">
									 <html:text property="save_lrsj" styleId="lrsj" 
										onclick="return showCalendar('lrsj','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" />
								</logic:empty>
								<logic:notEmpty name="rs">
									 <html:text name="rs" property="save_lrsj" styleId="lrsj" 
										onclick="return showCalendar('lrsj','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" />
								</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>岗位说明
								</th>
								<td colspan="3">
								<logic:empty name="rs">
									<html:textarea rows="8" style="width:98%" property="save_gwsm" onblur="chLeng(this,200)"/>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:textarea rows="8" style="width:98%" property="save_gwsm" onblur="chLeng(this,200)"/>
								</logic:notEmpty>
								</td>
								
							</tr>
							</tbody>
							<tfoot>
								<tr>
									 <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
										<div class="btn">
											<logic:equal name="doType" value="add">
												<button type="button" onclick="saveGwsz()">保 存</button>
											</logic:equal>
											<logic:equal name="doType" value="modi">
												<button type="button" onclick="updateGwsz()">保 存</button>
											</logic:equal>
											<button type="button" onclick="Close();return false;">关 闭</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
						</div>
						<logic:present name="result">
						<script>
							alert('操作成功!');
							if (window.dialogArguments) {
								window.close();
								window.dialogArguments.document.getElementById('search_go').click();
							}
						</script>
						</logic:present>
		</html:form>
	</body>
</html>
