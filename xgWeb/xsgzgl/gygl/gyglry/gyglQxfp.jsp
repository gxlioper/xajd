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
		<script language="javascript">
		

		function gyglryQxfp_save(){
			var lzsj=jQuery("#rzjsrq").val();
			if(""==lzsj||null==lzsj){
				 showAlert("�뽫��*����Ŀ��д����");
				 return false;
			}
			var api = frameElement.api,W = api.opener;
			jQuery(W.document).find("input[type='checkbox'][name='checkVal']:checked").each(function(){
				pkValue = jQuery(this).val();        
			});
			
			var pks=pkValue.split("!$$!");
			var lddm=pks[0];
			var ch=pks[1].replace("#","").trim();
			var qsh=pks[2].replace("#","").trim();
			var url = 'gyglnew_gyglry.do?method=gyglryManage&doType=qxfp';
			url+="&lddm="+lddm;
			if(ch!=""){url+="&fp_ch="+ch;};
			if(qsh!=""){url+="&fp_qsh="+qsh;};
			url+="&old_xh="+jQuery("#oldxh").val();
			url+="&pkValue="+pkValue;
			confirmInfo("ȷ��ȡ��������",function(ok){
				if(ok=="ok"){
				      ajaxSubFormWithFun("gyglnewGyglryForm",url,function(data){
				    	 if(data["message"]=="�����ɹ���"){
				    		 showAlert(data["message"],{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
				    	 }else{
				    		 showAlert(data["message"]);
				    		 
				    	 }
						});
				}
			})
		}

	
		</script>
	</head>
	<body >

		<html:form styleId="gyglnewGyglryForm" action="/gyglnew_gyglry" method="post">
			<%--<input type="hidden" id="xh" name="xh" value="${xh}"/>
				--%>
				<input type="hidden" id="oldxh" name="oldxh" value="${oldxh}"/>
				<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>������Ա����</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>����ʱ��
								</th>
								<td>
									<html:text property="rzjsrq" styleId="rzjsrq" onkeypress="onlyBackSpace(this,event);" 
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"></html:text>
								</td>
							</tr>
<%--							<tr>--%>
<%--								<th>--%>
<%--									��ע--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<input type="text" name="bz"  maxlength="100"/>--%>
<%--								</td>--%>
<%--							</tr>--%>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									
									<div class="btn">
										<button type="button" name="����"  onclick="gyglryQxfp_save()">
											���� 
										</button>
										<button type="button" name="ȡ��"  onclick="Close();return false;">
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
