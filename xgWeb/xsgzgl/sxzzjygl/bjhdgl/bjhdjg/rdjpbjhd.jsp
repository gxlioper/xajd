<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/bjhdgl/bjhdjg/js/bjhdJg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type='text/javascript'>

            jQuery(function($){
                selectOption();
            });

            function selectOption() {
                var sfjp = jQuery('#sfjpSelect').val();
                jQuery("#sfjp option").each(function () {
                    if (sfjp == (jQuery(this).val())) {
                        jQuery(this).prop('selected', true);
                    }
                });
            }


		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/bjhdgl_bjhdjg" method="post" styleId="BjhdJgForm" onsubmit="return false;">
			<html:hidden property="jgid" styleId="jgid"></html:hidden>
			<input type="hidden" id="sfjpSelect" value="${sfjpSelect}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>认定精品班级活动</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>是否为精品班级活动
							</th>
							<td width="30%">
								<select id ="sfjp" name = "sfjp">
										<option value="0">否</option>
										<option value="1">是</option>
								</select>
							</td>
						</tr>
					</tbody>
				 </table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="saveSfjp();">
										保存
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

