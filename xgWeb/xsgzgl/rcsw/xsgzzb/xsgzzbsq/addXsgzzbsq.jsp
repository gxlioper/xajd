<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xsgzzb/xsgzzbsq/js/xsgzzbsq.js"></script>
		<script	type="text/javascript">
			function saveForm(type){
				var yzzygz = jQuery("#yzzygz").val();
				var xzzygz = jQuery("#xzzygz").val();
				if(jQuery.trim(yzzygz) == "" || jQuery.trim(xzzygz) == ""){
					showAlert("�뽫��������д������");
					return false;					
				}
				/*
				if (jQuery.trim(yzzygz) == ""){
					showAlert("һ����Ҫ��������Ϊ�գ�");
					return false;
				}
				if (yzzygz.length > 500){
					showAlert("һ����Ҫ�������500�֣�");
					return false;
				}
				if (jQuery.trim(xzzygz) == ""){
					showAlert("������Ҫ��������Ϊ�գ�");
					return false;
				}
				if (xzzygz.length > 500){
					showAlert("������Ҫ�������500�֣�");
					return false;
				}	
				var qtgz = jQuery("#qtgz").val();
				if (qtgz.length > 500){
					showAlert("�����������500�֣�");
					return false;
				}	
				var yj = jQuery("#yj").val();
				if (yj.length > 500){
					showAlert("��ѧ������������ͽ������500�֣�");
					return false;
				}
				*/
				var flag = true;
				var scbz = "wfj";//�Ĵ���Ϣְҵ����ѧԺ�����ϴ���־������Ϊ�޸���
				if(jQuery("#tbody_wjlx > tr").length > 0){
					scbz = "yfj";
					if(jQuery("#xxdm").val() == '13815' && jQuery("#tbody_wjlx tr").length != 0){
						jQuery("#tbody_wjlx tr").each(function(){
							var wjlxdm = jQuery(this).find("select[name='wjlxdm']").val();
							//�ϴ�����Ϊ������,��֤�Ƿ�Ϊ��
							var sfyfj = jQuery(this).find("input:file").attr('value');
							if(sfyfj == null || sfyfj == '' || wjlxdm == null || wjlxdm == ''){
								flag = false;
								return false;
							}
						});
					}
				}
				if(!flag){
					showAlert("�ϴ��������ļ����Ͳ���Ϊ�գ�");
					return false;
				}
				var url = "rcsw_xsgzzb_xsgzzbsqgl.do?method=addXsgzzbsq&type="+type+"&scbz="+scbz;;
					ajaxSubFormWithFun("xsgzzbsqForm",url,function(data){
				    	 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
				    		 showAlert(data["message"],{},{"clkFun":function(){
									if (parent.window){
										refershParent();
									}
								}});
				    	 }else{
				    		 showAlert(data["message"]);
				    	 }}
			    	);
		      	
		  	}
		  	// �����ܴ���ʾ�ܴ���ֹ����
			function changeZcksjsrq(){
				var zcObj = jQuery("#zc").find("option:selected");
				var html = zcObj.attr("ksrq") + " �� " + zcObj.attr("jsrq");
				jQuery("#zcksjsrq_td").html(html);
				jQuery("#zcksjsrq_hidden").val(html);
			}
			jQuery(function(){
				changeZcksjsrq();
			});

		</script>
	</head>
	<body>
		<html:form action="/rcsw_xsgzzb_xsgzzbsqgl" method="post" styleId="xsgzzbsqForm">
			<html:hidden property="xn"  styleId="xn" />
			<html:hidden property="xq"  styleId="xq" />
			<html:hidden property="xxdm" styleId="xxdm" value="${xxdm}"/>
			<input type="hidden" name="zcksjsrq" id="zcksjsrq_hidden" value="${xsgzzbsqMap.zcksjsrq}"/>
			<% String userStatus = (String)request.getAttribute("userStatus"); %>
			<div id= "main_xsgzzzb" style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ܱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${xsgzzbsqMap.xn}
							</td>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${xsgzzbsqMap.xqmc}
							</td>
					    </tr>
					    <tr>
							<th><% if("xx".equals(userStatus)){ %><span class="red">*</span><% } %><bean:message key="lable.xy" /></th>
							<td colspan="3">
								<% if("xx".equals(userStatus)){ %>
								<html:select property="bmdm"  styleId="bmdm" value="${xsgzzbsqMap.bmdm}">
									<html:options collection="xyList" property="xydm" labelProperty="pyszm" />
								</html:select>
								<% }else{ %>
									${xsgzzbsqMap.bmdmmc}
									<input type="hidden" name="bmdm" value="${xsgzzbsqMap.bmdm}"/>
								<% } %>
							</td>
					    </tr>
							<th><span class="red">*</span>�ܴ�</th>
							<td>
								<select name="zc" id="zc" value="${xsgzzbsqMap.zc}" onchange="changeZcksjsrq();">
									<logic:iterate id="zc" name="zcList">
									<option value="${zc.dm}" ksrq="${zc.ksrq}" jsrq="${zc.jsrq}" <logic:equal name="zc" property="dm" value="${xsgzzbsqMap.zc}">selected="selected"</logic:equal>>${zc.mc}</option>
									</logic:iterate>
								</select>
							</td>
							<th>�ܴ���ֹ����</th>
							<td id="zcksjsrq_td">
								
							</td>
					    </tr>
					    <tr>
							<th>
								<span class="red">*</span>һ����Ҫ����
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='yzzygz' style="width:98%" styleId="yzzygz" rows='5' onblur="checkLenN('һ����Ҫ����',this,500)"/>
							</td>
			      		</tr>
					    <tr>
							<th>
								<span class="red">*</span>������Ҫ����
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='xzzygz' style="width:98%" styleId="xzzygz" rows='5' onblur="checkLenN('������Ҫ����',this,500)"/>
							</td>
			      		</tr>
					    <tr>
							<th>
								��������
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='qtgz' style="width:98%" styleId="qtgz" rows='5' onblur="checkLenN('��������',this,500)"/>
							</td>
			      		</tr>
					    <tr>
							<th>
								��ѧ��������<br />����ͽ���
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='yj' style="width:98%" styleId="yj" rows='5' onblur="checkLenN('��ѧ������������ͽ���',this,500)"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<logic:equal value="13815" name = "xxdm">
			   <div style = "display:none" >
			       <html:select  styleId="hideselect" disabled="true"  property="wjlxdm">
			          <html:options collection="wjlxlist" labelProperty="wjlxmc" property="wjlxdm" />
			       </html:select>
			   </div> 
				<div style = "">
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
							<thead style="">
								<tr>
									<td colspan="3">
										<button type="button" onclick="addFj();return false;" class="btn_01">���Ӹ���</button>
										<button type="button" onclick="delFj();return false;" class="btn_01">ɾ������</button>
									</td>
								</tr>
								<tr>
									<td width="10%"><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
									<td width="40%">�ϴ��ļ����</td>
									<td width="50%">����</td>
								</tr>
							</thead>
					</table>
				</div>
				<div style="height:200px;overflow-y:auto;overflow-x:hidden">
					<input type="hidden" name="filegid" value="" id="filegid">
					<table width="100%" border="0" class="datelist">
						<tbody id="tbody_wjlx">
										
						</tbody>
					</table>
				</div>
				</logic:equal>
				<div style="height:30px;width:100%"></div>
				<table width="100%" border="0" class="formlist" style = "position:fixed;bottom:0">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('save');">
										����ݸ�
									</button>
									
									<button type="button" type="button" onclick="saveForm('submit');">
										�ύ����
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
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

