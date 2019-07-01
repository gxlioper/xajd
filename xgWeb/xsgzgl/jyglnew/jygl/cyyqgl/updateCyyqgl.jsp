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
		<script type="text/javascript" src="xsgzgl/jyglnew/jygl/js/jygl.js"></script>
		<script	type="text/javascript">
			function saveForm(type){
				var xn = jQuery("#xn").val();
				if (jQuery.trim(xn) == ""){
					showAlert("��ѡ��ѧ�꣡");
					return false;
				}
				var xq = jQuery("#xq").val();
				if (jQuery.trim(xq) == ""){
					showAlert("��ѡ��ѧ�ڣ�");
					return false;
				}
				var gsmc = jQuery("#gsmc").val();
				if (jQuery.trim(gsmc) == ""){
					showAlert("��˾���Ʋ���Ϊ�գ�");
					return false;
				}
				var gslx = jQuery("#gslx").val();
				if (jQuery.trim(gslx) == ""){
					showAlert("��ѡ��˾���ͣ�");
					return false;
				}
				var rzsj = jQuery("#rzsj").val();
				if (jQuery.trim(rzsj) == ""){
					showAlert("��ѡ����פʱ�䣡");
					return false;
				}
				var sshy = jQuery("#sshy").val();
				if (jQuery.trim(sshy) == ""){
					showAlert("��ѡ��������ҵ��");
					return false;
				}
				var sfzc = jQuery("#sfzc").val();
				if(sfzc == '1'){
					var zcgsmc = jQuery("#zcgsmc").val();
					if (jQuery.trim(zcgsmc) == ""){
						showAlert("ע�ṫ˾���Ʋ���Ϊ�գ�");
						return false;
					}
					var zcsj = jQuery("#zcsj").val();
					if (jQuery.trim(zcsj) == ""){
						showAlert("��ѡ��ע��ʱ�䣡");
						return false;
					}
					var zczb = jQuery("#zczb").val();
					if (jQuery.trim(zczb) == ""){
						showAlert("ע���ʱ�����Ϊ�գ�");
						return false;
					}
					var zcsshy = jQuery("#zcsshy").val();
					if (jQuery.trim(zcsshy) == ""){
						showAlert("��ѡ��������ҵ��");
						return false;
					}
				}
				var url = "jyglnew_jygl_cyyqglgl.do?method=updateCyyqgl&type="+type;
		      	ajaxSubFormWithFun("cyyqglForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
		  	function changeZcgsxx(obj){
				if(obj.value == '1'){
					jQuery(".zcgsxx_class").show();
				}else{
					jQuery(".zcgsxx_class").hide();
				}
		  	}
			jQuery(function(){
				jQuery("#sfzc").change();
			});
		</script>
	</head>
	<body>
		<html:form action="/jyglnew_jygl_cyyqglgl" method="post" styleId="cyyqglForm">
			<html:hidden property="gsid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;height: 465px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵ԰����˾��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%"><span class="red">*</span>ѧ��</th>
							<td width="30%">
								<html:select  property="xn" styleId="xn" style="width:150px">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="20%"><span class="red">*</span>ѧ��</th>
							<td width="30%">
								<html:select  property="xq" styleId="xq" style="width:150px">
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width=""><font class="red">*</font>��˾����</th>
							<td width="" colspan="3">
								<html:text property="gsmc" styleId="gsmc" maxlength="80" style="width:513px;" />
							</td>
					    </tr>
						<tr>
							<th width=""><font class="red">*</font>��˾����</th>
							<td width="">
								<html:select property="gslx" styleId="gslx" style="width:150px;">
									<html:options collection="gslxList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th width=""><font class="red">*</font>��פʱ��</th>
							<td width="">
								<html:text property="rzsj" styleId="rzsj" onclick="return showCalendar('rzsj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
					    </tr>
						<tr>
							<th width="">�Ŷ�����</th>
							<td width="">
								<html:text property="tdrs" styleId="tdrs" maxlength="10" style="" onkeyup="checkInputData(this);"/>
							</td>
					    	<th width=""><font class="red">*</font>������ҵ</th>
							<td width="" colspan="3">
								<html:select property="sshy" styleId="sshy" style="width:150px;">
									<html:options collection="sshyList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
					    <tr>
					    	<th width=""><font class="red">*</font>�Ƿ�ע��</th>
							<td width="" colspan="3">
								<html:select property="sfzc" styleId="sfzc" style="width:150px;" onchange="changeZcgsxx(this);">
									<html:options collection="isnotList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
					    </tr>
					    <tr class="zcgsxx_class">
							<th width=""><font class="red">*</font>ע�ṫ˾����</th>
							<td width="" colspan="3">
								<html:text property="zcgsmc" styleId="zcgsmc" maxlength="80" style="width:513px;" />
							</td>
					    </tr>
						<tr class="zcgsxx_class">
							<th width=""><font class="red">*</font>ע��ʱ��</th>
							<td width="">
								<html:text property="zcsj" styleId="zcsj" onclick="return showCalendar('zcsj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
							<th width=""><font class="red">*</font>ע���ʱ�</th>
							<td width="">
								<html:text property="zczb" styleId="zczb" maxlength="10" style="" onkeyup="checkInputData(this);"/>
							</td>
					    </tr>
						<tr class="zcgsxx_class">
							<th width="">��ҵ����</th>
							<td width="">
								<html:text property="jyrs" styleId="jyrs" maxlength="10" style="" onkeyup="checkInputData(this);"/>
							</td>
					    	<th width=""><font class="red">*</font>ע��������ҵ</th>
							<td width="" colspan="3">
								<html:select property="zcsshy" styleId="zcsshy" style="width:150px;">
									<html:options collection="sshyList" property="lxdm" labelProperty="lxmc" />
								</html:select>
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵ�Ŷӳ�Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
				 </table>
				
				<div style="height:200px;overflow-y:auto;">
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						<thead>
							<tr>
								<td colspan="8">
								<button type="button" onclick="addXs();return false;" class="btn_01">����ѧ��</button>
								<button type="button" onclick="delXs();return false;" class="btn_01">ɾ��ѧ��</button>
								</td>
							</tr>
							<tr>
								<td width='5%'><input type="checkbox" name="selectAll" onclick="selectAllXs(this);" /></td>
								<td width='15%'>ѧ��</td>
								<td width='12%'>����</td>
<%--								<td width='9%'>�Ա�</td>--%>
<%--								<td width='9%'>�꼶</td>--%>
								<td width='17%'><bean:message key="lable.xb" /></td>
								<td width='17%'>רҵ</td>
								<td width='17%'>�༶</td>
								<td width='11%'>�ֻ�����</td>
								<td width='11%'>QQ����</td>
							</tr>
						</thead>
						<tbody id="tbody_xs">
							<logic:notEmpty name="cyList">
								<logic:iterate id="cy" name="cyList">
									<tr>
										<td><input type='checkbox'/></td>
										<td><input type='hidden' name='xhArr' value='${cy.xh }' />${cy.xh }</td>
										<td>${cy.xm }</td>
<%--										<td>${cy.xb }</td>--%>
<%--										<td>${cy.nj }</td>--%>
										<td>${cy.xymc }</td>
										<td>${cy.zymc }</td>
										<td>${cy.bjmc }</td>
										<td><input type='text' name='sjhmArr' value='${cy.sjhm }' style='width:86px;' maxlength='11' /></td>
										<td><input type='text' name='qqhmArr' value='${cy.qqhm }' style='width:90px;' maxlength='12' /></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>
				
				</div>
				<div>
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"Ϊ������</div>
									<div class="btn">
										<button type="button" type="button" onclick="saveForm('save');">
											����
										</button>
										<button type="button" type="button" onclick="iFClose();">
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

