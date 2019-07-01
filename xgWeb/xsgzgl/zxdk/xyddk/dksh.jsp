<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dksq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//��������ѡ��
				loadViewMkxxSelectOptions();
				//����radioѡ��
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				
				if (jQuery.trim(xh) != ""){
				
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zxdkXyddkForm.id}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zxdkXyddkForm.splcid}&shid=${zxdkXyddkForm.id}");
			});
			function saveAudit(){
				var shzt=jQuery("#shjg").val();
				jQuery("#shzt").val(shzt);
				if (jQuery("#shyj").val() == ""){
					showAlert("����д��������");
					return false;
				}
				if (jQuery("#shyj").val().length>200){
					showAlert("���������ܳ���200��");
					return false;
				}
				if("${xxdm}" == "10511"){
					if(jQuery.trim(jQuery("#zd3").val()) == ""){
						showAlert("��ͬ��Ų���Ϊ�գ�");
					     return false;
					}
				}
				var text=jQuery("#shjg").find("option:selected").text();
				//�ύ���
				zx(shzt,text);
			}
			
			function zx(shzt,text){
			
				if("${xxdm}" == "10511"){
					var message = "";
				    var data = {shzt:jQuery("#shjg").val(),htbh:jQuery("#zd3").val()}
					var url = "zxdkXyddk.do?method=checkHtbhIsExists";
					jQuery.ajax({
					type:'post',
					url:url,
					dataType:'json',
					contentType:"application/x-www-form-urlencoded; charset=UTF-8",
					data:data,
					async: false,
					success:function(result){
						if(result==null||result=="null"){
							message = "δ֪����";
						}else{
							message = result["message"];
						}
					 }
					
				     });
				     if(message != "true"){
				     	showAlert(message);
				     	return false;
				     }
				}
				
				var url = "zxdkXyddk.do?method=submitAudit&tt="+new Date();
				ajaxSubFormWithFun("xyddkForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/zxdkXyddk" method="post" styleId="xyddkForm">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			<html:hidden property="xh" styleId="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ���
									<logic:notEqual value="" property="xh" name="zxdkXyddkForm">
										<a onclick="showJtqk(this);" class="up" 
										   href="javascript:void(0);">
										   <font color="blue">���չ��/����</font>	
										</a>
									</logic:notEqual>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">
								
								</div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;��λ��Ԫ��</font></span>
							</th>
						</tr>
					</thead>
					<logic:notEqual name="xxdm" value="10511">
						<tbody>			
						<tr>
							<th>����ѧ��</th>
							<td>
								${zxdkXyddkForm.xn }
							</td>
							<th>�����ܽ�Ԫ��</th>
							<td>
								${zxdkXyddkForm.dkje }
							</td>
						</tr>
						<tr>
							<th>ס�޷�Ӧ������Ԫ��</th>
							<td>
								${zxdkXyddkForm.zsf }
							</td>
							<th>ѧ��Ӧ������Ԫ��</th>
							<td>
								${zxdkXyddkForm.xzf }
							</td>
						</tr>
						<tr>
							<th>
								<logic:equal value="10704" name="xxdm">
									ס�޷��´�������Ԫ��
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									ס�޷����������Ԫ��
								</logic:notEqual>
							</th>
							<td>
								${zxdkXyddkForm.zsfdks }
							</td>
							<th>
								<logic:equal value="10704" name="xxdm">
									ѧ���´�������Ԫ��
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									ѧ�����������Ԫ��
								</logic:notEqual>							
							</th>
							<td>
								${zxdkXyddkForm.xfdks }
							</td>
						</tr>
						<tr>
							<th>
								<logic:equal value="10704" name="xxdm">
									������´�������Ԫ��
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									��������������Ԫ��
								</logic:notEqual>
							</th>
							<td>
								${zxdkXyddkForm.shfdks }
							</td>
							<th>
								<logic:equal value="10704" name="xxdm">
									�������ޣ��£�
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									�������ޣ��꣩
								</logic:notEqual>								
							</th>
							<td>
								${zxdkXyddkForm.dkqx }
							</td>
						</tr>
						<tr>
							<th>ÿ������ܶԪ��</th>
							<td>
								${zxdkXyddkForm.mnje }
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
								${zxdkXyddkForm.sqly }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${zxdkXyddkForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>
					</tbody>				
					<logic:equal value="12688" name="xxdm">
						<thead>
							<tr>
								<th colspan="4">
									<span>��ͬ�������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>����</th>
								<td>
									${zxdkXyddkForm.gtjkrxm }
								</td>
								<th>��ͥ�绰</th>
								<td>
									${zxdkXyddkForm.gtjkrjtdh }
								</td>
							</tr>
							<tr>
								<th>��ѧ����ϵ</th>
								<td>
									${zxdkXyddkForm.gtjkrgx }
								</td>
								<th>���֤����</th>
								<td>
									${zxdkXyddkForm.gtjkrsfzh }
								</td>
							</tr>
							<tr>
								<th>�ֻ�����</th>
								<td>
									${zxdkXyddkForm.gtjkrsjhm }
								</td>
								<th>����״��</th>
								<td>
									${zxdkXyddkForm.gtjkrjkzk }
								</td>
							</tr>
							<tr>
								<th>��������</th>
								<td>
									${zxdkXyddkForm.gtjkryb }
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>��ͥ��ϸ��ַ</th>
								<td colspan="3">
									${zxdkXyddkForm.gtjkrjtdz }
								</td>
							</tr>
						</tbody>
					</logic:equal>
					</logic:notEqual>
					<logic:equal name="xxdm" value="10511">
						<tbody>
						<tr>
							<th width="16%">��������</th>
							<td width="34%" id='dkqxtd'>
							  ${zxdkXyddkForm.dkqx}
							</td>
							<th width="16%">�������ޣ��£�</th>
							<td width="34%">
								 ${zxdkXyddkForm.jhr1}
							</td>
						</tr>
						<tr  id = "tableshow" >
							<td colspan="4"  width="100%">
								<table id="table" width="100%">
									<tr width="100%">
										<th style="text-align:center" width="15%" >ѧ��</th>
										<th style="text-align:center" >ס�޷Ѵ����</th>
										<th style="text-align:center" >ѧ�Ѵ����</th>
										<th style="text-align:center" >����Ѵ����</th>
										<th style="text-align:center" >��ס�޷�Ӧ�ɶ�</th>
										<th style="text-align:center" >��ѧ��Ӧ�ɶ�</th>
									</tr>
									<logic:iterate id="i" name="nddkList">
									<tr class ='showtr'>
										<td>
											${i.xn}
										</td>
										 <td>
										 	${i.nzsfdk}
										 </td>
										 <td>
										 	${i.nxfdk}
										 </td>
										 <td>
										 	${i.nshfdk}
										 </td>
										 <td>
										 	${i.nzsfyje}
										 </td>
										 <td>
										 	${i.nxfyje}
										  </td>
										</tr>
									</logic:iterate>
									
								</table>
							</td>
						</tr>
						<tr>
							<th width="16%">ס�޷Ѵ����ܶ�</th>
							<td width="34%">
								 ${zxdkXyddkForm.zsfdks}
							</td>
							<th width="16%">ѧ�Ѵ����ܶ�</th>
							<td width="34%">
								 ${zxdkXyddkForm.xfdks}
							</td>
						</tr>
						<tr>
							<th width="16%">����Ѵ����ܶ�</th>
							<td width="34%">
								${zxdkXyddkForm.shfdks}
							</td>
							<th width="16%">�����ܽ��</th>
							<td width="34%">
								${zxdkXyddkForm.dkje}
							</td>
						</tr>
						<tr>
							<th width="16%">��������</th>
							<td width="84%" colspan="3">
								${zxdkXyddkForm.sqly}
							</td>
						</tr>
						<tr>
							<th align="right" width="16%">
								������Ϣ
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${zxdkXyddkForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
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
						<tr>
							<th>
								<font color="red">*</font>��˽��
							</th>
							<td id="shjgSpan">
								 
							</td>
							<th><logic:equal name="xxdm" value="10511"><font color="red">*</font></logic:equal>�����ͬ���</th>
							<td>
								<input type="hidden" name="zd1" value="�����ͬ���"/>
								<input type="text" name="zd3" id="zd3" maxlength="20" value = "${sjxx.zd3}"/>
							</td>
						</tr>
						<!-- �����Ƽ���ѧ���Ի� -->
						<logic:equal value="10704" name="xxdm">
							<tr>
								<th>
									������
								</th>
								<td>
									<input type="hidden" name="dkje" value="${zxdkXyddkForm.dkje}"/>
									<logic:present name="sjxx" property="zd7">									
										<logic:empty name="sjxx" property="zd7">
											<input type="text" name="zd7" id="zd7" maxlength="10" value="${zxdkXyddkForm.dkje}" onblur="checkMoneyForBlur(this);return false;" />
										</logic:empty>
										<logic:notEmpty name="sjxx" property="zd7">								
											<input type="text" name="zd7" id="zd7" maxlength="10" value="${sjxx.zd7}" onblur="checkMoneyForBlur(this);return false;" /> 
										</logic:notEmpty>
									</logic:present>
									<logic:notPresent name="sjxx" property="zd7">
										<input type="text" name="zd7" id="zd7" maxlength="10" value="${zxdkXyddkForm.dkje}" onblur="checkMoneyForBlur(this);return false;" />
									</logic:notPresent>
								</td>
								<th>
									�����������
								</th>
								<td>								
									<input type="text" name="zd8" id="zd8" maxlength="10" value="${sjxx.zd8}" onfocus="showCalendar('zd8','y-mm-dd');" />  
								</td>
							</tr>
							<tr>
								<th>
									�����ͬ���ޣ��£�
								</th>
								<td>
									<input type="hidden" name="dkqx" value="${zxdkXyddkForm.dkqx}" />
									<logic:present name="sjxx" property="zd9">
										<logic:empty name="sjxx" property="zd9">
											<input type="text" name="zd9" id="zd9" maxlength="2" value="${zxdkXyddkForm.dkqx}" onblur="checkZs(this);return false;"/>
										</logic:empty>
										<logic:notEmpty name="sjxx" property="zd9">								
											<input type="text" name="zd9" id="zd9" maxlength="2" value="${sjxx.zd9}" onblur="checkZs(this);return false;"/> 
										</logic:notEmpty>
									</logic:present>
									<logic:notPresent name="sjxx" property="zd9">
											<input type="text" name="zd9" id="zd9" maxlength="2" value="${zxdkXyddkForm.dkqx}" onblur="checkZs(this);return false;"/>
									</logic:notPresent>
								</td>
								<th>
									��ͬǩ���ص�
								</th>
								<td>
									<input type="text" name="zd10" id="zd10" maxlength="20" value="${sjxx.zd10}" />   
								</td>
							</tr>
						</logic:equal>
						
						<tr>
							<th>��������</th>
							<td>
								<input type="hidden" name="zd1" value="��������"/>
								<input type="text" name="zd5" maxlength="25" value = "${sjxx.zd5}"/>
							</td>
							<th>���е绰</th>
							<td>
								<input type="hidden" name="zd1" value="���е绰"/>
								<input type="text" name="zd6" maxlength="15" value = "${sjxx.zd6}"/>
							</td>
						</tr>
						
						<!-- �����Ƽ���ѧ���Ի� -->
						<logic:equal value="10704" name="xxdm">							
							<tr>
								<th>
									ѧԺ������
								</th>
								<td>
									<input type="text" name="zd2" maxlength="10" value = "${sjxx.zd2}"/>
								</td>
							</tr>
						</logic:equal>
						
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> ������&nbsp;
								<br />
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gjzxdk&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="saveAudit();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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