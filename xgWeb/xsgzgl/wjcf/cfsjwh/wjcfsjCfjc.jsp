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

		function jcjgsave() {
			var api = frameElement.api,W = api.opener;
			var jcwh = $("jcwh").value;//����ʺ�
			var jcsj = $("jcsj").value;//���ʱ��
			if(null==jcsj||""==jcsj||null==jcwh||""==jcwh){
				alertError("�뽫��*����Ŀ��д������");
				return false;
			}
			var pkValue=W.jQuery("[name=div_pkValue]:checked").val();
			//refreshForm('saveWjcfjcjg.do?cfid='+pkValue);
			 var url = "wjcfCfshwh_cfsjwh.do?method=saveWjcfjc&cfid="+pkValue;
		      ajaxSubFormWithFun("wjcfCfsjwhActionForm",url,function(data){
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
		<html:form action="/wjcfCfshwh_cfsjwh" method="post" styleId="wjcfCfsjwhActionForm" onsubmit="return false;">
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
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
									<span class="red">*</span><bean:message key="wjcf.text" />�ĺ�
								</th>
								<td>
									<html:text property="jcwh" styleId="jcwh" maxlength="30"></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<span class="red">*</span><bean:message key="wjcf.text" />ʱ��
								</th>
								<td>
									<html:text property="jcsj" styleId="jcsj"  style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd','','','100','40');" ></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<bean:message key="wjcf.text" />���<br/>
									<font color="red">(����200��)</font>
								</th>
								<td>
									<html:textarea property="jcyj" styleId="jcyj" rows="4" style="width:280px" onblur="checkLen(this,200)"></html:textarea>
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
										<button type="button"  name="����" onclick="jcjgsave()">
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
