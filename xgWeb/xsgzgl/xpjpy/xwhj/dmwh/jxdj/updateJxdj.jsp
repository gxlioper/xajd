<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xwhj/dmwh/jxdj/js/jxdj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	</head>
	<body >
		<html:form action="/xpj_jxdj" method="post" styleId="dmwhJxdjForm" onsubmit="return false;">
		   <html:hidden property="jxdjdm"  styleId="jxdjdm"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>修改奖项等级</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>等级名称
							</th>
							<td width="34%">
								<html:text property="jxdjmc" styleId="jxdjmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>所属类别
							</th>
							<td width="34%">
								<html:select property="jxlbdm" styleId="jxlbdm" style="width:150px;">
									<html:option value=""></html:option>
									<html:options collection="jxlbList" property="jxlbdm" labelProperty="jxlbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>竞赛方式
							</th>
							<td width="34%">
								<html:select property="jsfs" styleId="jsfs" style="width:150px;">
									<html:option value=""></html:option>
									<html:option value="1">个人</html:option>
									<html:option value="2">团体</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="updSaveForm();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
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

