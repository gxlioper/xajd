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
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglForZjlyzy.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglnewcomm.js"></script>
		<script type="text/javascript">	
			jQuery(function() {
				initData();
				changeYxssz();
				//���ݴ�ѧ���Ի�Ĭ��ѡ���û����ڲ���Ϊ���˵�λ���뷶Χ
				if(jQuery("#xxdm").val() == "10351"){
					var sfxy = jQuery("#sfxy").val();
					var checkboxs = jQuery("input[name='sqxy']");
					var bmdm = jQuery("#userDep").val();
					if(sfxy != '1'){//���ΪУ���û���ȫ��ѡ��
						jQuery(checkboxs).each(function(i,n){						
							jQuery(n).attr("checked",true);
						})
					}else{//���ΪԺϵ�û���ֻѡ���Լ��Ĳ���
						jQuery(checkboxs).each(function(i,n){
							if(jQuery(n).val() == bmdm){
								jQuery(n).attr("checked",true);
								return;
							}
						})
					}
					jQuery("#qxCheck").bind("click",function(){
						xz(this);
					});
				}	
				//�㽭����ְҵѧԺ���Ի�
				if("12867"==jQuery("#xxdm").val()){
					changeXq();
					changeYxssz();
				}			
			});
			function changeXq(){
				if("ʵϰ��" == jQuery("#gwlx").val()){
					jQuery("#xq").attr("disabled","disabled");
					jQuery("#xq").hide();
					jQuery("#xn").attr("disabled","disabled");
					jQuery("#xn").hide();
					jQuery("#nd").show();
					jQuery("#nd").attr("disabled",false);
				}else if("�̶���" == jQuery("#gwlx").val()){
					jQuery("#xq").attr("disabled",false);
					jQuery("#xq").show();
					jQuery("#xn").attr("disabled",false);
					jQuery("#xn").show();
					jQuery("#nd").attr("disabled","disabled");
					jQuery("#nd").hide();
				}
			}

			function selTea(){
				showDialog("ѡ�����Ա", 770, 520, "stglStsq.do?method=getTea")	
			}
			
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzx_gwglnew" method="post" styleId="form">
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<input type="hidden"  id="qgzq" name="qgzq" value="${rs.qgzq}" />
			<input type="hidden" id="userDep" value="${bmdm}" />
			<input type="hidden" id="sfxy" value="${sfxy}" />
			<input type="hidden" id="xxdm" value="${xxdm}" />
			<!-- ��ʾ��Ϣ -->
			<div class="prompt" id="div_help" >
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
					ͬһѧ��
					<logic:equal name="rs" property="qgzq" value="xq">
						ͬһѧ��
					</logic:equal>
					ͬһ������<font color="blue">��������</font>��λ<font color="blue">������ͬ</font>�ĸ�λ
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>���˲���
							</th>
							
							<td width="34%" >
								<html:select name="rs" property="yrbm" styleId="yrbm" style="width:200px" disabled="${rs.dis}" onchange="changeYrdw(this);return false;">
									<html:options collection="yrbmList" property="bmdm" labelProperty="bmmc"/>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>��ϵ��
							</th>
							<td width="34%">
								<input type="text" id="lxr" name="lxr" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								�걨ʱ��
							</th>
							<td width="34%" >
								<input type="hidden" id="sqsj" name="sqsj" value="${sqsj }"/>
								${sqsj }
							</td>
							<th width="16%">
								<font color="red">*</font>��ϵ�绰
							</th>
							<td width="34%">
								<input type="text" id="lxPhone" name="lxPhone" onblur="checkPhone(this);" />
							</td>
						</tr>
					<thead>
							<tr>
								<th colspan="5">
									<span>��λ������Ϣ
									</span>
								</th>
							</tr>
					</thead>
						<tr>
							<th width="16%">
								<font color="red">*</font>��λ����
							</th>
							
							<td width="34%">
								
								<html:text property="gwmc" styleId="gwmc" maxlength="50" style="width:200px" ></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>��������
							</th>
							
							<td width="34%">
								<html:text property="xqrs" styleId="xqrs" size="10" maxlength="3" onblur="checkXqrs(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��λ����
							</th>
							<td>
								<html:select property="gwlx" styleId="gwlx" onclick="changeXq();">
									<html:option value="�̶���">�̶���</html:option>
									<html:option value="ʵϰ��">ʵϰ��</html:option>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>��������
							</th>
							<td width="34%">
								<html:text property="knsrs" styleId="knsrs" size="10" maxlength="3" onblur="checkXqrs(this)" value="0"></html:text> 
							</td>
						</tr>
						<tr id="zxsInfo">
								<th><span class="red">*</span>��λ�����</th>
								<td>
									<input type="text" id="gwshr" class="gwshr" name="gwshr" style="width:120px;" readonly="readonly"/>
									<button type="button" onclick="showDialog('��ʦѡ��',680,480,'qgzx_gwglnew.do?method=showFdys');return false;" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</td>
								<th>��λ���������</th>
								<td width="30%"  class="xm">
									<input type="text" id="gwshrxm" class="gwshrxm" name="gwshrxm" style="width:120px;" readonly="readonly"/>
								</td>
						</tr>
						<tr>
								<th><span class="red">*</span>��λҪ��</th>
								<td colspan="3" >
									<html:textarea  property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
										rows='6' />
								</td>
						</tr>
						<tr>
								<th align="right" >
									<font color="red">*</font>��������<br/><font color="red">(��500��)</font>
								</th>
								<td colspan="3" >
									<html:textarea property="gwms" style="width:97%" rows="6" onblur="chLengs(this,500);" styleId="gwms" />
								</td>
						</tr>
						<tr>
								<th width="16%">
									<span class="red">*</span>�ڸ����
								</th>
								<td colspan="3">
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									<html:select property="xq" styleId="xq">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									
									<html:select property="nd" styleId="nd" style="display:none">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span>��Чʱ����</th>
								<td>
							 	 	 <logic:present name="yxsszList">
										<logic:iterate id="yxsszMap" name="yxsszList" >
											<html:radio property="yxssz" onclick="changeYxssz();"  styleId="yxssz" value="${yxsszMap.dm}">${yxsszMap.mc}</html:radio>
										</logic:iterate>								
								 	 </logic:present>
								</td>
								<th><span class="red">*</span>�Ƿ��ܸ�λ����������</th>
								<td>
							   		<logic:present name="isnotList">
										<logic:iterate id="o" name="isnotList" >
											<html:radio property="sfsgwsqsxz" styleId="sfsgwsqsxz" onclick="" value="${o.dm}">${o.mc}</html:radio>
										</logic:iterate>								
									</logic:present>
								</td>
						
							</tr>
							<tr>
								<th><span class="red">*</span>��λ��ʼ����</th>
								<td>
									<html:text  property="gwkssj" styleId="gwkssj" size="10" readonly="true"></html:text>
								</td>
								<th id="gwjssj_th"></th>
								<td id="gwjssj_td">
									<html:text  property="gwjssj" styleId="gwjssj" size="10" readonly="true"></html:text>
								</td>
							</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 50px">
			</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="zjBcGwxx()">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

