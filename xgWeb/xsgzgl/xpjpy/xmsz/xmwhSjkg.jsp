<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xmsz/js/xmwhSjkg.js"></script>
	</head>
	<body>
		<html:form action="/xpj_xmwh" method="post" styleId="form1">
		<html:hidden property="xmdm" styleId="xmdm" />
		<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前设置项目为：<font color="red">${xmmc} &nbsp;	
				</font>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
			<div style='tab;width:100%;height:300px;overflow-x:hidden;overflow-y:auto;' id ="kgszDiv">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>时间设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">

						<tr>
							<th>
								开放申请时间
							</th>
							<td>
								<html:text property="sqkssj" styleId="sqkssj"
								onfocus="showCalendar('sqkssj','yyyy-MM-dd HH:mm',true,'sqjssj');" />
								&nbsp;至
								<html:text property="sqjssj" styleId="sqjssj"
								onfocus="showCalendar('sqjssj','yyyy-MM-dd HH:mm',false,'sqkssj');" />
							</td>
						</tr>		

						<tr>
							<th>
								申请开关
							</th>
							<td>
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<label>
											<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>申请理由填写说明<br/>
								<font color="red">(限制录入100字)</font></th>
							<td >
								<html:textarea property="kgbz" styleId="kgbz"  style="width:98%"  rows="5"/>
							</td>
						</tr>

						<tr>
							<th>
								开放审核时间
							</th>
							<td>
								<html:text property="shkssj" styleId="shkssj"
								onfocus="showCalendar('shkssj','yyyy-MM-dd HH:mm',true,'shjssj');" />
								&nbsp;至
								<html:text property="shjssj" styleId="shjssj"
								onfocus="showCalendar('shjssj','yyyy-MM-dd HH:mm',false,'shkssj');" />
							</td>
						</tr>		

						<tr>
							<th>
								审核开关
							</th>
							<td>
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<label>
											<html:radio property="shkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<logic:equal name="xxdm" value="10355">
						<tr>
							<th>
								学生处填表时间
							</th>
							<td>
								<html:text property="xxtbsj" styleId="xxtbsj"
								onfocus="showCalendar('xxtbsj','yyyy-MM-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								是否需要开放学生<br/>主要获奖情况选择
							</th>
							<td>
								<html:radio property="sfkfhjqkxz" value="1">是</html:radio>&nbsp;
								<html:radio property="sfkfhjqkxz" value="0">否</html:radio>
							</td>
						</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
			<div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

