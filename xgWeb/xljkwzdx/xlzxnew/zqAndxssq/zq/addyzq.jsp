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
		<script type="text/javascript" defer="defer">

			function addAction(){
				var checkids = "xn-yf";
				
				if(!checkNotNull(checkids)){
					showAlert("请将带<font color='red'>*</font>的项目填写完整!");
					return false;
				}
				
				var url = "xlzxnew_zqrcgl.do?method=saveYzqsz";
					ajaxSubFormWithFun("ZqszForm",url,function(data){
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
	</head>
	<body>
		<html:form action="/xlzxnew_zqrcgl" method="post" styleId="ZqszForm">
			<div style='tab;width:100%;height:330px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>周报详情</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								<span class="red">*</span>学年
							</th>
							<td align="left">
								<html:select property="xn" styleId="xn" disabled="false" 
										style="width:125px;">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
							</td>
							<th align="right">
								<span class="red">*</span>月份
							</th>
							<td align="left">
								<html:select property="yf" styleId="yf" disabled="false" 
										style="width:125px;">
										<html:option value="01">1月</html:option>
										<html:option value="02">2月</html:option>
										<html:option value="03">3月</html:option>
										<html:option value="04">4月</html:option>
										<html:option value="05">5月</html:option>
										<html:option value="06">6月</html:option>
										<html:option value="07">7月</html:option>
										<html:option value="08">8月</html:option>
										<html:option value="09">9月</html:option>
										<html:option value="10">10月</html:option>
										<html:option value="11">11月</html:option>
										<html:option value="12">12月</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="addAction();">
										保存
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

