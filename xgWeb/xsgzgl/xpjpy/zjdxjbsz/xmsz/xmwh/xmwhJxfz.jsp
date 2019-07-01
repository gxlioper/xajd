<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				onShow();
			});
	
			function onShow() {
				var url = 'xpjpy_xmwh.do?method=xmwhJxfz&type=query';
				jQuery.post(url, {
				}, function(data) {
					/*设置初值*/
					setInit(data);
				}, 'json');
			}
	
			function setInit(data){
				if(data == null || data.length == 0	){
					return ;
				}
				var sHtml = "";
				for ( var i = 0; i < data.length; i++) {
					var o = data[i];
					var dm = o.dm;
					var mc = o.mc;
					if(dm != null && dm != ""){
						sHtml += "<option value='"+dm+"'>"+mc+"</option>";
					}
				}
				jQuery("#jxfznd").html(sHtml);
			}
			/*保存操作*/
			function saveForm() {
				var jxfznd = jQuery("#jxfznd").val();
				if(jxfznd == null || jxfznd == ""){
					showAlert("请选择复制来源年度！");
					return false;
				}
				var url = 'xpjpy_xmwh.do?method=saveFormCopy';
				ajaxSubFormWithFun("jxfzForm", url, function(data) {
					if (data["success"] != undefined && (data["success"] == false || data["success"] == "false" )) {
						showAlert(data["message"]);
					} else {
						showAlert(data["message"], {}, {"clkFun" : function(tag) {
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
		<html:form action="/xpjpy_xmwh" method="post" styleId="jxfzForm">
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>"<font color="red">人数设置</font>"不可复制</p>
				<p>
					复制奖项后请<font color="red">注意修改</font>各奖项的"<font color="red">条件设置</font>"、"<font color="red">兼得设置</font>"、"<font color="red">奖项调整设置</font>"，
					特别是"<font color="red">条件设置”</font>"中各条件的<font color="red">依赖范围</font>项的修改
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>评奖项目复制</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>复制来源年度</th>
							<td>
								<select id="jxfznd" name="jxfznd" style="width:100px"></select>
							</td>
						</tr>
						<tr>
							<th>复制目标年度</th>
							<td>
								${pjzq}
							</td>
						</tr>

					</tbody>
				</table>
			</div>	
			<div style="height:15px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
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
		</html:form>
	</body>
</html>