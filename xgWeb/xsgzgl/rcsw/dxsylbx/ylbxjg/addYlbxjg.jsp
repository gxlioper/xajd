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
		
		<script	type="text/javascript">
		function saveForm(obj){
			var czqebzIds = "";
			var cbzkIds = "";
			var checkCzqebz = "";
			var checkCbzk = "";
			jQuery("input[name=czqebz]:checked").each(function(){
				czqebzIds += jQuery(this).val()+ "," ;
			});						
			jQuery("input[name=cbzk]:checked").each(function(){
				cbzkIds += jQuery(this).val() + ",";
			});
			if(cbzkIds.length>0){
				cbzkIds = cbzkIds.substring(0, cbzkIds.length-1);
				checkCbzk ="1";
			}
			if(czqebzIds.length>0){
				czqebzIds = czqebzIds.substring(0, czqebzIds.length-1);
				checkCzqebz = "1";
			}
			//����֤ѧ��
			var xh = jQuery("#xh").val();
			var sqly = jQuery("#sqly").val();
			var zjsyrxm = jQuery("#zjsyrxm").val();
			var zjh = jQuery("#zjh").val();
			//var cbsj = jQuery("#cbsj").val();
			if (jQuery.trim(xh) == ""){
				showAlert("����ѡ��ѧ����");
				return false;
			}	

			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			
			if (jQuery.trim(xn) == ""){
				showAlert("����ѡ��ѧ�꣡");
				return false;
			}
			if (jQuery.trim(xq) == ""){
				showAlert("����ѡ��ѧ�ڣ�");
				return false;
			}
			
			if (jQuery.trim(checkCzqebz) == ""){
				showAlert("����ѡ������Ա��ݣ�");
				return false;
			}
			/*
			if (jQuery.trim(zjsyrxm) == ""){
				showAlert("��������֤��������������");
				return false;
			}

			if (jQuery.trim(zjh) == ""){
				showAlert("��������֤���ţ�");
				return false;
			}*/

			if (jQuery.trim(checkCbzk) == ""){
				showAlert("����ѡ��α�״����");
				return false;
			}

			/*if (jQuery.trim(cbsj) == ""){
				showAlert("����ѡ��α�ʱ�䣡");
				return false;
			}*/
			
			if (jQuery.trim(sqly) == ""){
				showAlert("�������ɲ���Ϊ�գ�");
				return false;
			} 

			
			
			var url = "";
			if(obj == 'save'){
				 url = "rcsw_dxsylbx_ylbxjggl.do?method=addYlbxjg&type=save&czqebzdm="+czqebzIds+"&cbzkdm="+cbzkIds;
			}
			
			
	      	ajaxSubFormWithFun("ylbxjgForm",url,function(data){
	    	 if(data["message"]=="����ɹ���" ){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    	 }});
			
		  }

		function showInput(obj){
			if(obj.checked){
				jQuery("#qtcbzkval").show();
			}else{
				jQuery("#qtcbzkval").hide();
			}
		}

		
		/*ids = ids + "";
		var pkVs = ids.split(",");
		for ( var i = 0; i < pkVs.length; i++) {
			var row = jQuery("#tabGrid").jqGrid('getRowData', pkVs[i]);
			xhs+=row["XH"]+",";
			sfzqs+=row["SFZQ"]+",";
		}*/
		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_dxsylbx_ylbxjggl" method="post" styleId="ylbxjgForm">
			<%--<html:hidden property="xq"  styleId="xq" />
			
			
			--%><div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/dxsylbx/comm/selectStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>ҽ�Ʊ���������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<%--
						<tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:hidden property="xn" />
								<bean:write name="ylbxjgForm" property="xn"/>
							</td>
							
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:hidden property="xqmc"/>
								<bean:write name="ylbxjgForm" property="xqmc"/>
							</td>
							
					    </tr>
					     --%>
					     <tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xq" styleId="xq" style="width:130px">
								<html:option value=""></html:option>
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
					    </tr>
					
					    <tr>
							<th ><span class="red">*</span>��������</th>
							<td colspan="3" >
							<logic:notEmpty name="czqebzryList">
							<logic:iterate name="czqebzryList" id="s"  indexId="i" >
								<label><input type="checkbox" name="czqebz" value="${s.czqebzdm}"/>${s.czqebzmc}</label>
								<br/>
							</logic:iterate>
							</logic:notEmpty>
							</td>
					    </tr>

					    <tr>
							<th>֤������������</th>
							<td>
								<html:text property="zjsyrxm"  styleId="zjsyrxm" maxlength="10"></html:text>
							</td>
							
							<th>֤����</th>
							<td>
								<html:text property="zjh" styleId="zjh" maxlength="25"></html:text>
							</td>
					    </tr>
					    
					     <tr>
							<th><span class="red">*</span>�α�״��</th>
							<td colspan="3" >
							
								<logic:notEmpty name="cbzkList">
								<logic:iterate name="cbzkList" id="b"  indexId="i" >
									<label><input type="checkbox" name="cbzk" value="${b.cbzkdm}"/>${b.cbzkmc}</label>
									<br/>
								</logic:iterate>
								</logic:notEmpty>
							
								<label><input type="checkbox" name="qtcbzk" value="0" onclick="showInput(this);"/>����</label>
								<input type="text" name ="qtcbzkval" style="width:100px;display:none" styleId="qtcbzkval" id="qtcbzkval">
							</td>
					    </tr>
					    <%--
					    <tr>
							<th><span class="red">*</span>�α�ʱ��</th>
							<td colspan="3">
								<html:text style="cursor:hand;" styleId="cbsj"
									property="cbsj"
									onclick="return showCalendar('cbsj','y-mm-dd');"
									readonly="readonly" />
							</td>
					    </tr>
					    --%>
					    <tr>
							<th>
								<span class="red">*</span>��������
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
							    <html:textarea property='sqly' style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
			      		
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('save');">
										�� ��
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

