<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khdxgl/pfzgl/js/pfzwh.js"></script>
		<script type="text/javascript" >
		var demoHtml = "";

		jQuery(function() {
			initData();
		});


		function initData(){	
			demoHtml = "请按如下格式输入学号\n\n";
			demoHtml += "例如：\n";
			demoHtml += "20110019\n20100019\n20090026";
			demoHtml += "\n或者：\n";
			demoHtml += "20110019 20100019 20090026";

			jQuery("#xh").val(demoHtml);
			jQuery("#xh").css("color", "#999");
			jQuery("#xh").focus( function() {
				if (jQuery(this).val() == demoHtml) {
					jQuery(this).val("");
					jQuery(this).css("color", "");
				}else{
					jQuery(this).css("color", "");
				}
			});

			jQuery("#xh").blur( function() {
				if (jQuery(this).val() == "") {
					jQuery(this).val(demoHtml);
					jQuery(this).css("color", "#999");
				}
			});
		}

	

		function pfcydrsave() {

			if (jQuery.trim(jQuery("#xh").val()) == "" || jQuery("#xh").val() === demoHtml){
				showAlert("请输入学生学号！");
				return false;
			}

			jQuery("button").attr("disabled","disabled");
			var url = "khglPfzgl.do?method=pfcydrSave&type=save";
			ajaxSubFormWithFun("form", url, function(data) {
				if (data["success"] != undefined && (data["success"] == false || data["success"] == "false" )) {
					showAlert(data["message"]);
				} else {
					showAlert(data["message"], {}, {
						"clkFun" : function(tag) {
							if (tag == "ok") {
								refershParent();
							}
						}
					});
				}
			});

		}
		</script>
	</head>
	<body>
		<html:form action="/khglPfzgl" method="post" styleId="form">
			<input type="hidden" id ="pfzid" name="pfzid" value="${pfzid}"/>
			<input type="hidden" id ="zgh" name="zgh" value="${zgh}"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>评分成员导入</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="20%">
								考核对象职工号
							</th>
							<td width="30%">
								${zgh}
							</td>
							<th width="20%">
								考核对象姓名
							</th>
							<td width="30%">
								${xm}
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>评分成员学号
							</th>
							<td colspan="3">
								<textarea name="xh" id="xh" style="width:95%;height:400px" rows="25" >
								</textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="pfcydrsave();">
										导 入
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

