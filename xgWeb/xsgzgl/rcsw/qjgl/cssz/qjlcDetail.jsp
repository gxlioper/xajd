<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//�����������
		function saveQjlc(tag){
			
			if(tag == "ok"){
				//�������
				var lxmc = $("lxmc").value;
				if(lxmc == ""){
					alertError("�������Ʋ���Ϊ�գ���ȷ��");
					return false;
				}
				
				var qjlx = jQuery("[name=qjlx]:checked").eq(0).val();
				if(qjlx == ""){
					alertError("������Ͳ���Ϊ�գ���ȷ��");
					return false; 
				}
				
				//�����С����
				var mints = $("mints").value;
				var maxts = $("maxts").value;
				
				if(mints == ""){
					alertError("����ĳ�첻��Ϊ�գ���ȷ��");
					return false;
				}else if(maxts == ""){
					alertError("С�ڵ���ĳ�첻��Ϊ�գ���ȷ��");
					return false;
				}else if(parseInt(mints) > parseInt(maxts)){
					alertError("������ĳ�졿���ܴ��ڡ�С��ĳ�졿����ȷ��");
					return false;
				}
				
				//����
				var lcid = $("lcid").value;
				if(lcid == ""){
					alertError("��Ϊ�����͵����ѡ��������̣�");
					return false;
				}
				
				var id = $("id").value;
				
				var url="rcsw_qjgl.do?method=saveQjlc";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
					
				//����
			 	var parameter = {
					"id":id,
					"lxmc":escape(lxmc),
					"mints":mints,
					"maxts":maxts,
					"lcid":lcid
					//,"qjlx":qjlx
				};
				
				jQuery.post(url,parameter,function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					if(result=='-999'){
						alertError("��С��������������������������������̴��ڽ�������˶�");
					}else{
						 showAlert(result,{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
					}
					
				});
			}
		}
		</script>
	</head>
	<body onload="" >
	
		<!-- ���� -->
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>--%>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" >
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.���������<font color="blue">ϵͳά�� - ��������ά����ά����</font>��</br>
				2.��չ������ģ��Ϊ<font color="blue">�ճ�����</font>���������
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/rcsw_qjgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="id" styleId="id"/>
			<!-- ������ end-->
			
			<!-- ѧ��������Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>�������ά��</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>�������
						</th>
						<td width="">
							<html:text name="rs" property="lxmc" 
								style="width:80%"
								styleId="lxmc" maxlength="20"/>
						</td>
					</tr>
					
					<tr>
						<th>
							<font color="red">*</font>�������
						</th>
						<td>
							����
							<html:text name="rs" property="mints" styleId="mints"
								onkeyup="checkInputNum(this)" 
								onblur="checkInputNum(this)" maxlength="5" 
								style="width : 30px;ime-mode:disabled;"
							/>��
							
							С�ڵ���
							<html:text name="rs" property="maxts" styleId="maxts"
								onkeyup="checkInputNum(this)" 
								onblur="checkInputNum(this)" maxlength="5" 
								style="width : 30px;ime-mode:disabled;"
							/>��
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>�������
						</th>
						<td>
							<html:hidden name="rs" property="lcid" styleId="lcid"/>
							<div style="width:100%;height:130px;overflow-x:hidden;overflow-y:auto;">
								<logic:iterate name="splcList" id="splcRs">
									<input type="radio" name="rad_lcid" 
										onclick="$('lcid').value=this.value"
										value="${splcRs.dm }"
										<logic:equal name="rs" property="lcid" value="${splcRs.dm }">checked="checked"</logic:equal>
									/>${splcRs.mc}
										
									<br/>
								</logic:iterate>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- ���� -->
								<button type="button" onclick="saveQjlc('ok');return false;">
									<bean:message key="lable.btn_bc_space" />
								</button>
								<!-- �ر�-->
								<button type="button" onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ѧ��������Ϣ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
			</div>

		</html:form>
	</body>
</html>