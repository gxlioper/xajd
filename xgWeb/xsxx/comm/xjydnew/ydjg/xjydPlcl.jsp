<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsxx/comm/xjydnew/js/xjydjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>	
		<script type="text/javascript">

			jQuery(function() {
				
				initShow();
			});
			
			function saveForm(){
				var xxdm = jQuery("#xxdm").val();
				if(jQuery("#yxzxss").val()=="0" || jQuery("#xzxsKey").val()==""){
					return showAlert("������ѡ��һ��ѧ����");
				}
				var checkData = "ydlbdm-xjydwh-xjydsj";
				
				if(!jQuery("#tzbj").is(":hidden")){
					checkData = "ydlbdm-nj-xydm-zydm-bjdm-xjydwh-xjydsj";
				}
				if(xxdm == "13871"){
					checkData.replace("-xjydwh","");
				}
				if(xxdm == "5002"){
					checkData = "ydlbdm-xjydsj";
					if(!jQuery("#tzbj").is(":hidden")){
					checkData = "ydlbdm-nj-xydm-zydm-bjdm-xjydwh-xjydsj";
					}
				}
				if(!check(checkData)){
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
				}

				
			     var url = "xjydjg.do?method=xjydPlcl&type=save";

			     BatAlert.showTips("���ڱ��棬���Ժ�");
			      ajaxSubFormWithFun("xjydjgForm",url,function(data){
			    	 if(data["message"]=="����ɹ���"){
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
			// ɾ������ѡ��ѧ����Ϣ
			function closeWindows(){
			  var url = "xjydjg.do?method=xjydPlcl&type=close";
		      ajaxSubFormWithFun("xjydjgForm",url,function(data){
    				if (parent.window){
    					refershParent();
    				}
				 });
			 
		      iFClose();
			}


			function initXyzybj(){
				jQuery('#xydm').val("");
				jQuery('#xymc').val("");
				
				jQuery('#zydm').val("");
				jQuery('#zymc').val("");
				
				jQuery('#bjdm').val("");
				jQuery('#bjmc').val("");
			}
		</script>
	</head>
	<body>
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>����ѡ��ѧ��!</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- ��ʾ��Ϣ END-->
		<html:form method="post" styleId="xjydjgForm" action="/xjydjg">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>ѧ���춯��Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th><span class="red">*</span>����ѡ��ѧ��</th>
						<td colspan="3">
								<button type="button" onclick="selectStudent();return false;" id="buttonSelect">
									ѡ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;��ѡ��ѧ����<span style="color:red;">${yxzxss }</span>��
						</td>
					</tr>
					<tr>
						<th align="right" width="16%">
							<span class="red">*</span>ѧ���춯���
						</th>
						<td align="left"  width="34%">
							<html:select property="ydlbdm" styleId="ydlbdm" disabled="false" onchange="initShow();">
								<html:option value=""></html:option>
								<html:options collection="xjlbList" property="xjlbdm"
									labelProperty="xjlbmc" />
							</html:select>
						</td>
						<th align="right"  width="16%">
							<span class="red">*</span>ѧ��/ѧ��
						</th>
						<td align="left"  width="34%">
							<html:select property="xn" styleId="xn" disabled="false">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
							</html:select>
							<html:select property="xq" styleId="xq" disabled="false" >
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>�Ƿ���ѧ��</th>
						<td id="sfyxj">&nbsp;</td>
						<th>�Ƿ���У</th>
						<td id="sfzx">&nbsp;</td>
					</tr>
					<logic:equal name="xxdm" value="10511">		
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>�Ƿ�ʦ����
							</th>
							<td align="left">
								<html:select property="sfsfs" styleId="sfsfs" disabled="false">
									<html:option value=""></html:option>
									<html:option value="��ʦ��">��ʦ��</html:option>
									<html:option value="ʦ��">ʦ��</html:option>
									<html:option value="���ʦ����">���ʦ����</html:option>
								</html:select>
							</td>
						</tr>
					</logic:equal>
					<tr id="tzbj">
						<th><span class="red">*</span>�����༶[�춯��]</th>
						<td colspan="3">
							<div >
							
								<table border="0" style="float:left">
									<tr>
										<th style="width:70px;height:26px;">�춯���꼶</th>
										<td style="width:205px">
											<html:select property="ydhnj" styleId="nj" 
												onchange="initXyzybj();" style="margin-left:3px;width:80px">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th style="height:23px;">�춯��<bean:message key="lable.xb" /></th>
										<td>
											<html:text property="ydhxymc" styleId="xymc" readonly="true"></html:text>
											<html:hidden property="ydhxydm" styleId="xydm" />
										</td>
									</tr>
									<tr>
										<th>�춯��רҵ</th>
										<td>
											<html:text property="ydhzymc" styleId="zymc" readonly="true"></html:text>
											<html:hidden property="ydhzydm" styleId="zydm" />
										</td>
									</tr>
									<tr>
										<th>�춯��༶</th>
										<td>
											<div>
												<html:text property="ydhbjmc" styleId="bjmc" readonly="true"></html:text>
												<html:hidden property="ydhbjdm" styleId="bjdm" />
												<button type="button" class="btn_01" id="button_bj" style="float:right;margin:2px 0px 0px 0px;"
													onclick="getBj();">
													ѡ��
												</button>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��������&nbsp;
							<br />
							<font color="red">(��400��)</font>						
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="sqly" styleId="sqly" style="width:97%" onblur="checkLen(this,400);"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<logic:notEqual name="xxdm" value="13871">
							<logic:notEqual name="xxdm" value="5002">
								<span class="red">*</span>
							</logic:notEqual>
							</logic:notEqual>
							ѧ���춯�ĺ�&nbsp;
							<br />					
						</th>
						<td>
							<html:text property="xjydwh" styleId="xjydwh" maxlength="30"></html:text>
						</td>
						<th align="right" width="10%">
							<span class="red">*</span>ѧ���춯ʱ��&nbsp;
							<br />					
						</th>
						<td>
							<html:text property="xjydsj" styleId="xjydsj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" />
						</td>
					</tr>
					<tr id="lrqzsj">
						<th><span class="red">*</span>�춯��ֹʱ��</th>
						<td colspan="3">
							<html:text property="sqkssj" styleId="sqkssj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'sqjssj');" />
						    &nbsp;��&nbsp;
						    <html:text property="sqjssj" styleId="sqjssj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'sqkssj');" />
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��ע&nbsp;
							<br />
							<font color="red">(��100��)</font>						
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="xjydbz" styleId="xjydbz" style="width:97%" onblur="checkLen(this,100);"></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" id="tssave"  onclick="saveForm();return false;" id="buttonSave">
									�� ��
								</button>
								<button type="button"  onclick="closeWindows();" id="buttonClose">
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
