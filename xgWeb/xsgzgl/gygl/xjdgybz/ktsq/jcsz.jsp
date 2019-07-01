<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

            function saveZwsq(){
                var checkId = 'splc';
                if(!checkNotNull(checkId)){
                    showAlertDivLayer("请将必填项填写完整！");
                    return false;
                }
                var url = "gygl_gybz_wh.do?method=jcsz&type=save";
                ajaxSubFormWithFun("demoForm",url,function(data){
                    showAlertDivLayer(data["message"]);
                });
            }
		</script>
	</head>
	<body style="width:97%">
		<html:form action="/gygl_gybz_wh" method="post" styleId="demoForm">
			<div>
				<table width="100%" border="0" class="formlist" align="center">
					<thead>
						<tr>
							<th colspan="2">
								<span>宿舍空调申请培训参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_zwcs">
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>申请审批流
							</td>
							<td align="left" style="width: 60%">
								<html:select property="splc" name="model" styleId="splc">
									<html:options collection="splcList" property="splc" labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveZwsq()" >
										保 存
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

