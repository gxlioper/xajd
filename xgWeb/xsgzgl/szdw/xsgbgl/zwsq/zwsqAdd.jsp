<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/zwsq.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//Ϊbuttonע���¼�
				//jQuery("#but_save").click(function(){save("zwSq")});
				jQuery("#tbody_zwxx").load("szdw_xsgb_zwwh.do?method=zwView");
				var xh = jQuery("#xh").val();
				if(xh=="" || xh == undefined){
					jQuery("#xh").val('${xh}');
				}
			});

			function saveZwsq(obj){
				if(yanzheng()){
					jQuery.post("szdw_zwsq.do?method=yzZwsq",{zwid:jQuery("#zwid").val(),xh:jQuery("#xh").val()},function(data){
						if(data.message!="true"){
							alertInfo(data.message);
						}else{
							var url = "";
							if(obj == "submit"){
								url = "szdw_zwsq.do?method=zwSq&type=submit";
							}else{
								url = "szdw_zwsq.do?method=zwSq&type=save";
							}
							//��֤�ɹ�����ܽ��б���
							//var url = "szdw_zwsq.do?method=zwSq&type=save";
							ajaxSubFormWithFun("demoForm",url,function(data){
								 showAlertDivLayer(data["message"],{},{"clkFun":function(){
										if (parent.window){
											refershParent();
										}
										iFClose();
									}});
							});
						}
					},'json');
					
				}
			}
			
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/szdw_zwsq" method="post" styleId="demoForm">
			<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
								
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ���ɲ�ְ������</span> 
							</th>
						</tr>
					</thead>
					<tbody id="tbody_zwxx">
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								��������
								<br /><font color="red">(��200��)</font>
							</th>
							<td colspan="4">
								<html:textarea property='sqly' style="width:95%" styleId="sqly" rows='5' onblur="checkLen(this,200);"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveZwsq('save');return false;" >
										����ݸ�
									</button>
									<button type="button" type="button" onclick="saveZwsq('submit');return false;" >
										�ύ����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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

