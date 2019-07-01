<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		function saveForm(url){	
			if (!checkNull("xh-jlxx-jlkssj-jljssj-jlzy")) {
				 return false;
			 }
				var url = "xg_dwjl_dwjl.do?method=updateDwjl&type=save";
				ajaxSubFormWithFun("dwjlForm", url, function(data) {
					if (data["message"] == "保存成功！") {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								if (parent.window) {
									refershParent();
								}
							}
						});
					} else {
						showAlert(data["message"]);
					}
				});
			}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xg_dwjl_dwjl" method="post" styleId="dwjlForm" onsubmit="return false;">
		<html:hidden property="id"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>对外交流信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>交流学校</th>
							<td>
								<html:text styleId="jlxx" property="jlxx"  maxlength="50"/>
							</td>
							<th><font color="red">*</font>交流专业</th>
							<td>
								<html:text styleId="jlzy" property="jlzy"  maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>交流开始时间</th>
							<td>
							<html:text property="jlkssj" styleId="jlkssj" style="width:120px;"
								onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'jljssj','','','');" />
							</td>
							<th><font color="red">*</font>交流结束时间</th>
							<td>
							<html:text property="jljssj" styleId="jljssj" style="width:120px;"
								onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'jlkssj','','','');" />
							</td>
						</tr>
						<tr>
							<th>备注<br><font color="red">限制字数（200）</font><br/></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" onblur="chLengs(this,200);"
								   style="width:90%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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