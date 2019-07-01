<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zdxljk/tbxs/thjl.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#saveBtn").bind("click",function(){
					if(""==jQuery("#jyjzsj").val().trim()){
						showAlert("�뽫��������д������");
						return false;
						}
					if(jQuery("#ghbz").val().length>500){
						showAlert("�黹��ע�����д500�֡�");
						return false;
						}
					var url = "hjjyJyjg.do?method=saveJygh";
					ajaxSubFormWithFun("HjjyJgForm",url,function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
						
					});
					
				});
			});
		</script>
  	</head>
  
  	<body>
  		<html:form action="/hjjyJyjg" method="post" styleId="HjjyJgForm">
  			<html:hidden property="jgid" styleId="jgid"/>
  			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����黹</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								���ÿ�ʼʱ��
							</th>
							<td width="30%">
								${rs.jykssj}
								<html:hidden property="jykssj" value="${rs.jykssj}" styleId="jykssj"/>
							</td>
							<th width="20%">
								<font color="red">*</font>���ý�ֹʱ��
							</th>
							<td width="30%">
								<html:text property="jyjzsj" styleId="jyjzsj"
									onfocus="showCalendar('jyjzsj','y-mm-dd',false,'jykssj');" />
							</td>
						</tr>
						
						<tr>
							<th width="16%">�黹��ע</th>
							<td colspan="3" style="text-align:left;">
								<textarea name="ghbz"  id="ghbz" onblur="checkLen(this,500);"
							   	 style="width:99%;" rows="4"></textarea>
							</td>
						</tr>
										
										
					</tbody>
					</table>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="2">
							<div class="btn">
								<button type="button" id="saveBtn">
									����
								</button>
								<button type="button" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
			
  			</html:form>
  	</body>
</html>
