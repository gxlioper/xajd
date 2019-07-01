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

		function ssjgsave() {
			var sswh =jQuery("#sswh").val();//�����ʺ�
			var sssj = jQuery("#sssj").val();//����ʱ��
			var ssjg = jQuery("#ssjg").val();//���߽��
			var cflbmc = jQuery("#cflbdm").find("option:selected").text();//����������
			var ssyj = jQuery("#ssyj").val();//���߽��
			if("block"==document.getElementById('cfggw').style.display){
				if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg||null==cflbmc||""==cflbmc){
					alertError("�뽫��*����Ŀ��д������");
					return false;
				}
			}else{
				if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg){
					alertError("�뽫��*����Ŀ��д������");
					return false;
				}
			}
			var api = frameElement.api,W = api.opener;
			var pkValue=W.jQuery("#dataTable").getSeletIds();
			var url = "wjcf_cfjg.do?method=saveWjcfss";
			  jQuery.post(url,{cfid:pkValue,sswh:sswh,sssj:sssj,ssjg:ssjg,cflbmc:cflbmc,ssyj:ssyj},function(data){
				 if(data["message"]=="�����ɹ���"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		 
			    	 }
			},'json');
		}

		
		jQuery(function(){
			var ssjg =jQuery("#ssjg").val();
			if (ssjg == '���Ĵ���') {
				jQuery("#cfggw").show();
			}else{
				jQuery("#cfggw").hide();
			}
			jQuery("#cflbdm").find("option:selected").text("${wjcfss.cflbmc}");//����������
			jQuery("#showCfqx").html("${cfqx}");
		});

		function discfgg() {
			var ssjg = jQuery("#ssjg").val();
			if (ssjg =='���Ĵ���') {
				jQuery("#cfggw").show();
			} else {
				jQuery("#cfggw").hide();
			}
		}
		
		function showCfqxFlag(cflbdm){
			if(cflbdm==""){return false;}
			var cfqx = "";
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
		}
		
		</script>
	</head>
	<body >
		<html:form action="/wjcf_cfjg" method="post" styleId="cfjgForm" onsubmit="return false;" >
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
									<span class="red">*</span>�����ĺ�
								</th>
								<td>
									<html:text property="sswh" styleId="sswh" maxlength="30" value="${wjcfss.sswh}"></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<span class="red">*</span>����ʱ��
								</th>
								<td>
									<html:text property="sssj" styleId="sssj"  value="${wjcfss.sssj}" style="cursor:hand;"
								onclick="return showCalendar('sssj','y-mm-dd','','','100','50');" ></html:text>
								</td>
							</tr>
							<tr id="" style="">
								<th>
									<span class="red">*</span>���߽��
								</th>
								<td>
									<html:select property="ssjg" styleId="ssjg" value="${wjcfss.ssjg}" style="width:140px" onchange="discfgg()">
										<html:option value=""></html:option>
										<html:option value="ά��ԭ����">ά��ԭ����</html:option>
										<html:option value="��������">��������</html:option>
										<html:option value="���Ĵ���">���Ĵ���</html:option>
									</html:select>
								</td> 
							</tr>
							<tr id="cfggw">
								<th>
									<span class="red">*</span>���ָ���Ϊ
								</th>
								<td>
									<html:select property="cflbdm" styleId="cflbdm" value="${wjcfss.cflbmc}" style="width:100px"  onchange="showCfqxFlag(this.value);">
										<html:options collection="cflbsList" property="dm" labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
								</td> 
								
							</tr>
							<tr id="">
								<th>
									�������<br/>
									<font color="red">(����200��)</font>
								</th>
								<td>
									<html:textarea property="ssyj" styleId="ssyj" rows="5" value="${wjcfss.ssyj}" style="width:290px" onblur="checkLen(this,200)"></html:textarea>
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
										<button type="button"  name="����" onclick="ssjgsave();">
											�� ��
										</button>
										<button type="button"  name="ȡ��" onclick="Close();return false;">
											ȡ ��
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
