<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsxx/comm/xjydnew/js/xjydjg.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>		
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/comm/jcsjld.js"></script>
		<script type="text/javascript">

			jQuery(function() {
				initShow();
			});
			
			function saveForm(){
				var xxdm = jQuery("#xxdm").val();
				var checkData = "xh-ydlbdm-xjydwh-xjydsj";

				if(!jQuery("#tzbj").is(":hidden")){
					checkData = "xh-ydlbdm-nj-xydm-zydm-bjdm-xjydwh-xjydsj";
				}

				if("10511" == xxdm) {
					checkData = "xh-ydlbdm";
					if(!jQuery("#xzpd").is(":hidden")){
						checkData += "-xz";
					}
					if(!jQuery("#xxpd").is(":hidden")){
						checkData += "-lyqxxxdm";
					}
					checkData += "-ydyydm-xjydwh-xjydsj";
					if(!jQuery("#tzbj").is(":hidden")){
						checkData = "xh-ydlbdm-nj-xydm-zydm-bjdm";
						if(!jQuery("#xzpd").is(":hidden")){
							checkData += "-xz";
						}
						if(!jQuery("#xxpd").is(":hidden")){
							checkData += "-lyqxxxdm";
						}
						checkData += "-ydyydm-xjydwh-xjydsj";
					}
				}
				if(xxdm == "13871"){
					checkData.replace("-xjydwh","");
				}
				if("5002" == xxdm) {
					checkData = "xh-ydlbdm-xjydsj";
					if(!jQuery("#tzbj").is(":hidden")){
					checkData = "xh-ydlbdm-nj-xydm-zydm-bjdm-xjydwh-xjydsj";
					}
				}
				if(!check(checkData)){
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
				} 
				
				var sfzx=jQuery("#sfzx").html();
				var sfzx0=jQuery("#sfzx0").val();
				
			    var url = "xjydjg.do?method=xjydjgAdd&type=save";
			    if("12309"==jQuery("#xxdm").val()){  //ѧ���춯�����ޣ��������ѧԺ���Ի�
			    	ajaxSubFormWithFun("xjydjgForm",url,function(data){
			    		if(data["message"]=="����ɹ���"){
							if("��У"==sfzx0&&"����У"==sfzx&&data["sfycw"]=="y"){  //������������
								confirmInfo("����ɹ����Ƿ�ͬʱ�������޴���",function(ty){
					    			if(ty=="ok"){
					    				showDialog("����", 730, 365, "gyglnew_cwgl.do?method=cwglPlts2&xh="+jQuery('#xh').val());
					    			}
					    			if (parent.window){
				    					refershParent();
				    				}
					    		});
							}else{  //��������������
								showAlert(data["message"],{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
							}
			    	 	}else{
			    			showAlert(data["message"]);
			    		}
					});
			    }else{
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
			 }

		</script>
	</head>
	<body>
	<input type="hidden" id="sfzx0" name="sfzx0" value="${jbxx.sfzx}"/>
	<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
		<html:form method="post" styleId="xjydjgForm" action="/xjydjg">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsxx/comm/selectStudent/selectStudentAll.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>ѧ���춯������Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>ѧ���춯���
						</th>
						<td align="left">
							<html:select property="ydlbdm" styleId="ydlbdm" disabled="false" onchange="initShow();">
								<html:option value=""></html:option>
								<html:options collection="xjlbList" property="xjlbdm"
									labelProperty="xjlbmc" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>ѧ��/ѧ��
						</th>
						<td align="left">
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
						<th>ѧ��״̬[�춯]</th>
						<td colspan="3">
							<div >
								<table border="0" style="float:left">
									<tr>
										<th style="width:70px">ԭѧ�����</th>
										<td style="width:180px">&nbsp;<bean:write name="jbxx" property="xjlbmc"/></td>
									</tr>
									<tr>
										<th>�Ƿ���ѧ��</th>
										<td>&nbsp;<bean:write name="jbxx" property="xjztm"/></td>
									</tr>
									<tr>
										<th>�Ƿ���У</th>
										<td>&nbsp;<bean:write name="jbxx" property="sfzx"/></td>
									</tr>
								</table>
								<img style="float:left;margin:33px 5px" src='images/ssyd/to.gif' ></img>
								<table border="0"  style="float:left">
									<tr>
										<th style="width:90px">�춯��ѧ�����</th>
										<td style="width:187px" id="xjlbmc">&nbsp;</td>
									</tr>
									<tr>
										<th>�Ƿ���ѧ��</th>
										<td id="sfyxj">&nbsp;</td>
									</tr>
									<tr>
										<th>�Ƿ���У</th>
										<td id="sfzx" name="zxzt" >&nbsp;</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr id="tzbj">
						<th><span class="red">*</span>�����༶</th>
						<td colspan="3">
							<div >
								<table border="0"  style="float:left">
									<tr>
										<th style="width:70px;height:20px;">ԭ�꼶</th>
										<td style="width:180px" id="ydqnj">&nbsp;<bean:write name="jbxx" property="nj"/></td>
									</tr>
									<tr>
										<th style="height:20px;">ԭ<bean:message key="lable.xb" /></th>
										<td id="ydqxy">&nbsp;<bean:write name="jbxx" property="xymc"/></td>
									</tr>
									<tr>
										<th style="height:20px;">ԭרҵ</th>
										<td id="ydqzy">&nbsp;<bean:write name="jbxx" property="zymc"/></td>
									</tr>
									<tr>
										<th style="height:20px;">ԭ�༶</th>
										<td id="ydqbj">&nbsp;<bean:write name="jbxx" property="bjmc"/></td>
									</tr>
								</table>
								<img style="float:left;margin:70px 5px" src='images/ssyd/to.gif' ></img>
								<table border="0" style="float:left">
									<tr>
										<th style="width:80px;height:20px;"><span class="red">*</span>�춯���꼶</th>
										<td style="width:205px">
											<html:select property="ydhnj" styleId="nj" 
												onchange="onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');" style="width:100px">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th style="height:20px;"><span class="red">*</span>�춯��<bean:message key="lable.xb" /></th>
										<td>											
											<html:select property="ydhxydm" styleId="xydm"
											onchange="onChangJcsj('nj','xydm','zydm','bjdm','xy','1','1');"  style="width:180px;">
												<html:option value="">&nbsp;</html:option>
											</html:select>
							
										</td>
									</tr>
									<tr>
										<th style="height:20px;"><span class="red">*</span>�춯��רҵ</th>
										<td>
											<html:select property="ydhzydm" styleId="zydm"
											onchange="onChangJcsj('nj','xydm','zydm','bjdm','zy','1','1');"   style="width:180px;">
												<html:option value="">&nbsp;</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<th style="height:20px;"><span class="red">*</span>�춯��༶</th>
										<td>
											<html:select property="ydhbjdm" styleId="bjdm" style="width:180px;">
												<html:option value="">&nbsp;</html:option>
											</html:select>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
									
					<logic:equal name="xxdm" value="10511">					
						<tr id="xzpd">
							<th align="right" width="10%">
								<span class="red">*</span>ѧ��
							</th>
							<td align="left" colspan="3">
								<html:text property="xz" styleId="xz" maxlength="1" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr id="xxpd">
							<th align="right" width="18%">
								<span class="red">*</span>��ԴѧУ/ȥ��ѧУ
							</th>
							<td align="left" colspan="3">
								<html:select property="lyqxxxdm" styleId="lyqxxxdm" disabled="false">
									<html:option value=""></html:option>
									<html:options collection="lyqxxxList" property="lyqxxxdm"
										labelProperty="lyqxxxmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>�춯ԭ��
							</th>
							<td align="left" colspan="3">
								<html:select property="ydyydm" styleId="ydyydm" disabled="false">
									<html:option value=""></html:option>
									<html:options collection="ydyyList" property="ydyydm"
										labelProperty="ydyymc" />
								</html:select>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th align="right" width="10%">
							������Ϣ
						</th>
						<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//��׺
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//����ļ���С ��λM
												maxsize: 10,
												//��Ÿ������������id
												elementid : 'filepath'
												});
										});
									</script>
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
						<logic:notEqual name="xxdm" value="10511">
							<th align="right" width="10%">
								<span class="red">*</span>ѧ���춯ʱ��&nbsp;
								<br />					
							</th>
							<td>
								<html:text property="xjydsj" styleId="xjydsj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" />
							</td>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10511">
							<th align="right" width="10%">
								<span class="red">*</span>ѧ���춯���ʱ��&nbsp;
								<br />					
							</th>
							<td>
								<html:text property="xjydsj" styleId="xjydsj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" />
							</td>
						</logic:equal>
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
		<div style="height:35px"></div>			
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
