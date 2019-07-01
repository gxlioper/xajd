<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load(
						"comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="
								+ new Date().getTime());
				if( jQuery("[name='sfkjhyzm']").length == 2&&jQuery("[name='sfkjhyzm']:checked").length == 0){
					jQuery("#no").attr("checked","checked");
				}
			});
			//����
			function cancelZdy() {
				var shzt = jQuery("#shzt").val();
				if (shzt != '5') {
					showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
					return false;
				}

				showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
					"okFun" : function() {
						jQuery.post("dzzgxsq.do?method=cancelSq", {
							values : jQuery("#sqid").val(),
							splcid :  jQuery("#splcid").val()
						}, function(data) {
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
									document.location.href = "dtjs_dzzgxsq_xs.do";
								}});
						}, 'json');
					}
				});

			}
			/**
			 * ��������
			 * @param type
			 * @return
			 */
			function saveSq(type){
				var ids = "xh"+"-"+"szdzb"+"-"+"sfsn"+"-"+"jsdzz"+"-"+"sqdw"+"-"+"dfjzrq";
				if(checkNotNull(ids) == false){
					showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д����");
					return false;
				}
				if(jQuery("[name='sfkjhyzm']:checked").length == 0){
					showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д����");
					return false;
				}
				var url = "dzzgxsq.do?method=save&type=" + type;
				ajaxSubFormWithFun("ZcsqForm", url, function(data) {
					 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
						 showAlertDivLayer(data["message"],{},{"clkFun":function(){
								document.location.href = "dtjs_dzzgxsq_xs.do";
							}});
			    	 }else{
			    		 showAlertDivLayer(data["message"]);
			    		}
					});
			}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/dzzgxsq" method="post" styleId="ZcsqForm">
			<html:hidden property="sqid" styleId="sqid"  value="${rs.sqid}" />
			<html:hidden property="xh" styleId="xh"  value="${xh}" />
			<html:hidden property="shzt" styleId="shzt"  value="${rs.shzt}" />
			<html:hidden property="splcid" styleId="splcid" value="${rs.splcid}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�Ǽ���Ϣ</span>&nbsp;
							</th>
						</tr>
					</thead>
					<logic:equal value="1" name="sqkg">
					<logic:notEqual value="1" name="rs" property="shzt" >
					<logic:notEqual value="2" name="rs" property="shzt" >
					<logic:notEqual value="5" name="rs" property="shzt" >
						<tbody>
							<tr>
								<th><font color="red">*</font>���ڵ�֧��</th>
								<td>
									<html:select name="rs" property="szdzb" styleId="szdzb" style="width:90%">
										<html:options collection="dzbList" property="dzbdm" labelProperty="dzbmc"/>
									</html:select>							
								</td>
								<th><font color="red">*</font>�Ƿ�ʡ��</th>
								<td>
									 <html:select name="rs" property="sfsn" styleId="sfsn" style="width:90%">
										<html:option value="ʡ��">ʡ��</html:option>
										<html:option value="ʡ��">ʡ��</html:option>
									 </html:select>
								</td>
							</tr>
							<tr>
								<th><font color="red">*</font>���ձ�����֯��ϵ�ĵ���֯</th>
								<td colspan="3">
									<html:text name="rs" property="jsdzz" styleId="jsdzz" style="width:90%" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<th><font color="red">*</font>������֯��ϵ��ȥ��λ</th>
								<td colspan="3">
									<html:text name="rs" property="sqdw" styleId="sqdw"  maxlength="50" style="width:90%"/>
								</td>
							</tr>
							<tr>
								<th><font color="red">*</font>���ѽ�������</th>
								<td >
									<html:text name="rs" property="dfjzrq" styleId="dfjzrq" onclick="return showCalendar('dfjzrq','y-mm-dd',true);" style="width:90%"  />
								</td>
								<th><font color="red">*</font>�Ƿ���Ҫ���߻���֤��</th>
								<td >
									<html:radio name="rs" property="sfkjhyzm" value="��" styleId="yes"/><label for="yes">��</label>
									<html:radio name="rs" property="sfkjhyzm" value="��" styleId="no"/><label for="no">��</label>
								</td>
							</tr>
						</tbody>
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEqual>
					<logic:notEmpty name="rs" property="shzt">
					<logic:notEqual value="0" name="rs" property="shzt" >
					<logic:notEqual value="3" name="rs" property="shzt" >
					<tbody>
						<tr>
							<th>���ڵ�֧��</th>
							<td>
								<bean:write name="rs"  property="dzbmc"/>					
							</td>
							<th>�Ƿ�ʡ��</th>
							<td>
								<bean:write name="rs"  property="sfsn"/>
							</td>
						</tr>
						<tr>
							<th>���ձ�����֯��ϵ�ĵ���֯</th>
							<td colspan="3">
								<bean:write name="rs"  property="jsdzz"/>
							</td>
						</tr>
						<tr>
							<th>������֯��ϵ��ȥ��λ</th>
							<td colspan="3">
								<bean:write name="rs"  property="sqdw"/>
							</td>
						</tr>
						<tr>
							<th>���ѽ�������</th>
							<td >
								<bean:write name="rs"  property="dfjzrq"/>
							</td>
							<th>�Ƿ���Ҫ���߻���֤��</th>
							<td >
								<bean:write name="rs"  property="sfkjhyzm"/>
							</td>
						</tr>
					</tbody>
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEmpty>
					</logic:equal>
					
					<logic:equal value="0" name="sqkg">
					<tbody>
						<tr>
							<th>���ڵ�֧��</th>
							<td>
								<bean:write name="rs"  property="dzbmc"/>					
							</td>
							<th>�Ƿ�ʡ��</th>
							<td>
								<bean:write name="rs"  property="sfsn"/>
							</td>
						</tr>
						<tr>
							<th>���ձ�����֯��ϵ�ĵ���֯</th>
							<td colspan="3">
								<bean:write name="rs"  property="jsdzz"/>
							</td>
						</tr>
						<tr>
							<th>������֯��ϵ��ȥ��λ</th>
							<td colspan="3">
								<bean:write name="rs"  property="sqdw"/>
							</td>
						</tr>
						<tr>
							<th>���ѽ�������</th>
							<td >
								<bean:write name="rs"  property="dfjzrq"/>
							</td>
							<th>�Ƿ���Ҫ���߻���֤��</th>
							<td >
								<bean:write name="rs"  property="sfkjhyzm"/>
							</td>
						</tr>
					</tbody>
					</logic:equal>
					<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
					       </tr>
				     </thead>
					<tbody>
						<tr>
								<td colspan="4" id="shlccx">

								</td>
						</tr>

					</tbody>
				</table>
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" >
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
								<logic:equal value="1" name="sqkg">
									<logic:notEqual value="1" name="rs" property="shzt" >
					                <logic:notEqual value="2" name="rs" property="shzt" >
					                <logic:notEqual value="5" name="rs" property="shzt" >
									<button type="button" onclick="saveSq('${saveType}');">
										����ݸ�
									</button>
									<button type="button" onclick="saveSq('${submitType}');">
										�ύ����
									</button>
									</logic:notEqual>
									</logic:notEqual>
									</logic:notEqual>
								</logic:equal>
								<logic:equal value="0" name="sqkg" >
									<logic:equal value="3" name="rs" property="shzt" >
									<button type="button" onclick="saveSq('${submitType}');">
										�ύ����
									</button>
									</logic:equal>
								</logic:equal>
								<logic:equal value="1" name="sqkg">
									<logic:equal value="5" name="rs" property="shzt" >
									<button type="button" onclick="cancelZdy();">
										��������
									</button>
									</logic:equal>
								</logic:equal>	
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	
</html>