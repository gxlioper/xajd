<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript">
		//���� 		
		function saveData(url,sfqy){
			var form = document.forms[0];
			var kssj = jQuery('#kssj').val();
			var jssj = jQuery('#jssj').val();
			var idList = jQuery('#idList').val();
			if(jQuery('#kssj').val()=="" && jQuery('#jssj').val()=="" && sfqy=="yes"){
				alertInfo('��ʼʱ�������ʱ�䣬������дһ����ܿ�����ȡ����ס����ť��');
				return false;
			}
			
			if(jQuery('#kssj').val()!="" && jQuery('#jssj').val()!="" && jQuery('#kssj').val()>= jQuery('#jssj').val()){
				alertError('����ʱ�������ڿ�ʼʱ�䣡');
				return false;
			}
			url += "&kssj=" + kssj;
			url += "&jssj=" + jssj;
			url += "&idList=" + idList;
		      ajaxSubFormWithFun("gyglnewCsszForm",url,function(data){
		    	 if(data["message"]=="�����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }
				});
		}
		</script>
	</head>
	<body >

		<html:form styleId="gyglnewCsszForm" action="/gyglnew_cssz" method="post">
		<input type="hidden" id="idList" name="idList" value="${idList}" />	
			<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��ѡ��ȡ����ס����ť�ɲ���ʱ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>��ʼʱ��
								</th>
								<td>
									<input type="text" id="kssj" name="kssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>����ʱ��
								</th>
								<td>
									<input type="text" id="jssj" name="jssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="saveData('gyglnew_cssz.do?method=qySjsz&sfqy=yes&doType=xg','yes');return false;">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="iFClose();return false;">
											ȡ ��
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
