<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
			function shFdgl(fdxxid,shzt,obj){
				if(jQuery(obj).parents("tr").find("td[id='shzt']").text()=="ͬ��"&&shzt=="1"){
					showAlert("�����ظ�������");
					return false;
				}
				if(jQuery(obj).parents("tr").find("td[id='shzt']").text()=="��ͬ��"&&shzt=="0"){
					showAlert("�����ظ�������");
					return false;
				}
				var url = "zzyrxmglfdgl.do?method=shFdgl";
				jQuery.post(url, {
					fdxxid:fdxxid,shzt : shzt
				}, function(data) {
					if (data["message"] == "����ɹ���") {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								if(shzt=="1"){
									jQuery(obj).css("display","none");//visible
									jQuery(obj).parents("tr").find("td[id='shzt']").text("ͬ��");
									jQuery(obj).parents("tr").find("button[id='bty']").css("display","");
								}else{
									jQuery(obj).css("display","none");
									jQuery(obj).parents("tr").find("td[id='shzt']").text("��ͬ��");
									jQuery(obj).parents("tr").find("button[id='ty']").css("display","");
								}
							}
						});
					} else {
						showAlert(data["message"]);
					}
				}, 'json');
			}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zzyrxmglfdgl" method="post" styleId="fdglForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="9">
								<span>��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
						<td>����</td>
						<td>ѧ��</td>
						<td>רҵ</td>
						<td>����ѧԺ</td>
						<td>�༶</td>
						<td>��ϵ�绰</td>
						<td>״̬</td>
						<td>���״̬</td>
						<td>����</td>
					</tr>
					<logic:iterate name="rs" id="k">
						<tr>
							<td>${k.xm }</td>
							<td>${k.xh }</td>
							<td>${k.xymc }</td>
							<td>${k.zymc }</td>
							<td>${k.bjmc }</td>
							<td>${k.lxdh }</td>
							<td >
								<logic:equal value="0" name="k" property="shzt">��ͨ��</logic:equal>
								<logic:equal value="1" name="k" property="shzt">ͨ��</logic:equal>
								<logic:equal value="2" name="k" property="shzt">δ���</logic:equal>
								<logic:equal value="3" name="k" property="shzt">�����</logic:equal>
								<logic:equal value="4" name="k" property="shzt">�����</logic:equal>
								<logic:equal value="5" name="k" property="shzt">�����</logic:equal>
							</td>
							<td id="shzt">
								<logic:equal value="0" name="k" property="dqshzt">��ͬ��</logic:equal>
								<logic:equal value="1" name="k" property="dqshzt">ͬ��</logic:equal>
								
							</td>
							<td>
								<button type="button" id="ty" onclick="shFdgl('${k.fdxxid}','1',this);" 
									<logic:equal value="0" name="k" property="shzt">style="display: none;"</logic:equal>
									<logic:equal value="1" name="k" property="shzt">style="display: none;"</logic:equal>
									<logic:equal value="2" name="k" property="shzt"></logic:equal>
									<logic:equal value="3" name="k" property="shzt">style="display: none;"</logic:equal>
									<logic:equal value="4" name="k" property="shzt"></logic:equal>
									<logic:equal value="5" name="k" property="shzt">style="display: none;"</logic:equal>
								>ͬ��</button>
								<button type="button" id="bty" onclick="shFdgl('${k.fdxxid}','0',this);" 
									<logic:equal value="0" name="k" property="shzt">style="display: none;"</logic:equal>
									<logic:equal value="1" name="k" property="shzt">style="display: none;"</logic:equal>
									<logic:equal value="2" name="k" property="shzt"></logic:equal>
									<logic:equal value="3" name="k" property="shzt"></logic:equal>
									<logic:equal value="4" name="k" property="shzt">style="display: none;"</logic:equal>
									<logic:equal value="5" name="k" property="shzt">style="display: none;"</logic:equal>
								>��ͬ��</button>
							</td>
						</tr>
					</logic:iterate>
				 </table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
									<button type="button" onclick="refershParent();iFClose();">
										�ر�
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

