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
                    showAlertDivLayer("�뽫��������д������");
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
								<span>����յ�������ѵ��������</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_zwcs">
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>����������
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
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveZwsq()" >
										�� ��
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

