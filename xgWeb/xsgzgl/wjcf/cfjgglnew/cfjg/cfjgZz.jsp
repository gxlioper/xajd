<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
		<%@ include file="/syscommon/head.ini"%> 
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/uicomm.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script language="javascript" defer="defer">

		function zzjgsave() {
			var api = frameElement.api,W = api.opener;
			var zzwh = jQuery("#zzwh").val();
			var zzsj = jQuery("#zzsj").val();
			if(null==zzsj||""==zzsj||null==zzwh||""==zzwh){
				alertError("�뽫��*����Ŀ��д������");
				return false;
			}
			var pkValue=jQuery("#cfid").val();
			 var url = "wjcf_cfjg.do?method=saveWjcfzz&cfid="+pkValue;
		      ajaxSubFormWithFun("cfjgForm",url,function(data){
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
		
		</script>
	</head>
	<body >
		<html:form action="/wjcf_cfjg" method="post" styleId="cfjgForm" onsubmit="return false;">
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
			<input type="hidden" id="cfid" value="${cfid}"/>
			<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<th>
									<span class="red">*</span>��ֹ�ĺ�
								</th>
								<td>
									<html:text property="zzwh" styleId="zzwh" maxlength="30"></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<span class="red">*</span>��ֹʱ��
								</th>
								<td>
									<html:text property="zzsj" styleId="zzsj" style="cursor:hand;width:80px;"
								onclick="return showCalendar('zzsj','y-mm-dd','','','180','10');"  ></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									��ֹ���<br/>
									<font color="red">(����200��)</font>
								</th>
								<td>
									<html:textarea property="zzyj" styleId="zzyj" rows="4" style="width:280px" onblur="checkLen(this,200)"></html:textarea>
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
										<button type="button"  name="����" onclick="zzjgsave()">
											�� ��
										</button>
										<button type="button"  name="ȡ��" onclick="Close();return false;">
											ȡ��
										</button>

									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
