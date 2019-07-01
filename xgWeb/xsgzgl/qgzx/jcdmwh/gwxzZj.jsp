<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
		//保存 
		function gwxzDivSave(){
			if($("gwxzmc").value.trim()==""){
				showAlert("请输入岗位性质名称！");
				return false;
			}
			var doType = jQuery("#doType").val();
			var url="qgzx_jcdmwh_ajax.do?method=gwxzBc&doType="+doType;
            ajaxSubFormWithFun("qgzxJcdmwhForm",url,function(data){
                if (data["message"] == "保存成功") {
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
	<body >

		<html:form styleId="qgzxJcdmwhForm" action="/qgzx_jcdmwh_ajax" method="post" onsubmit="return false;">
			<input type="hidden" id="doType" name="doType" value="${doType}"/>
			<input type="hidden" name="gwxzdm" value="${model.gwxzdm}"/>
			<table align="center" class="formlist">
				<thead>
				<tr>
					<th colspan="2">
						<span>岗位性质代码维护</span>
					</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<th>
						<span class="red">*</span>岗位类别名称
					</th>
					<td>
						<input type="text" id="gwxzmc" name="gwxzmc" maxlength="50" value="${model.gwxzmc}"/>
					</td>
				</tr>
				<tr>
					<th>
						薪酬上限
					</th>
					<td>
						<input type="text" id="gwxcsx" name="gwxcsx" maxlength="7" value="${model.gwxcsx}"/>
					</td>
				</tr>
				<tr>
					<th>
						工时上限
					</th>
					<td>
						<input type="text" id="gssx" name="gssx" maxlength="4" value="${model.gssx}"/>
					</td>
				</tr>
				<tr>
					<th>
						时薪
					</th>
					<td>
						<input type="text" id="sx" name="sx" maxlength="4" value="${model.sx}"/>
					</td>
				</tr>
				<tr>
					<th>
						说明
					</th>
					<td>
						<textarea rows="4" cols="3" style="width: 98%" name="label">${model.label}</textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">
							"<span class="red">*</span>"为必填项
						</div>
						<div class="btn">
							<button type="button" name="保存" onclick="gwxzDivSave();return false;">
								保 存
							</button>
							<button type="button" name="关闭" onclick="Close();return false;">
								关闭
							</button>
						</div>
					</td>
				</tr>
				</tfoot>
			</table>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
