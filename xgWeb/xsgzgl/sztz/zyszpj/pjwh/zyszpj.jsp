<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
			<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/comm/ymPrompt.js" ></script>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script>
				function save(){
					var hpxx=jQuery("textarea[name='hpxx']").val();
					var spxx=jQuery("textarea[name='spxx']").val();
					if(hpxx==""||spxx==""){
						return alertError("�뽫��<font color='red'>*</font>����Ŀ��д������");
					}
					//��ⳤ��
					function checkLength(obj,len){
						var str=obj.value;
					     	if(str.replace(/[^\u0000-\u00ff]/g, "**").length > len){	         
					     		alertError("����ܴ���"+len+"���ַ���");
					      		 return false;
					   		 }
					}
				 	jQuery("#form").ajaxSubmit({
						url:"zyszpjwh.do?method=zyszpj&type=save",
						type:"post",
						dataType:"json",
						success:function(data){
					 		 if(data["message"]=="����ɹ���"){
					    		 showAlert(data["message"],{},{"clkFun":function(){
					    				if (parent.window){
					    					refershParent();
					    				}
					    			}});
					    	 }else{
					    		 showAlert(data["message"]);
					    		 
					    	 }
						},
						contentType:"application/x-www-form-urlencoded;charset=utf-8"
					});	
				}
			</script>
	</head>
	<html:form action="/zyszpjwh" method="post"
		 styleId="form"> 
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" width="15%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" width="40%">
								<html:hidden property="zyszid" value="${data.zyszid}"/>
								<input type="hidden" name="pjlx" value="${pjlx}"/>
								${stuInfo.xh}
							</td>
							<td align="right" width="15%">
								������
							</td>
							<td align="left">
								${stuInfo.xm}
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								${stuInfo.nj}
							</td>
							<td align="right">
								<bean:message key="lable.xb" />��
							</td>
							<td align="left">
								${stuInfo.xymc}
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								${stuInfo.zymc}
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								${stuInfo.bjmc}
							</td>
						</tr>
						<tr>
							<td align="right" width="10%">
								ѧ�꣺
							</td>
							<td align="left">
								${data.xn}
							</td>
							<td align="right">
								ѧ�ڣ�
							</td>
							<td align="left">
								${data.xq}
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>ְҵ���ʻ���� </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								���
							</td>
							<td>
								����Ŀ
							</td>
							<td>
								ʱ��
							</td>
							<td>
								�ص�
							</td>
							<td>
								�����
							</td>
							<td>
								���뼰�����
							</td>
						</tr>
							<tbody id="tbody_add">
							<logic:notEmpty name="zxm">
							<logic:iterate name="zxm" id="s"  indexId="i">
								<tr>
									<td>
										${i+1}
									</td>
									<td width="120px">
										${s.xmlbmc}
									</td>
									<td width="120px">
										${s.sj}
									</td>
									<td width="120px">
										${s.dd}
									</td>
									<td width="120px">
									${s.hdnr}
									</td>
									<td width="120px">
									${s.cyjhjqk}
									</td>
								</tr>
							</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="15%" height="120px">����</td>
							<td width="85%">${data.zpxx}</td>
						</tr>
					</tbody>
				</table>
				<!-- ��ǰ�Ƿ���Խ����޸�  1����������ӡ��޸�-->
				<logic:equal name="sfkypj" value="1">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px"><font color='red'>*</font>����<br /><font color="red">(��200��)</font></td>
								<td width="85%"><html:textarea property="hpxx" onblur="checkLen(this,200)" rows="8" style="width:100%"/></td>
							</tr>
						</tbody>
					</table>
				</logic:equal>
				<logic:notEqual name="sfkypj" value="1">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px">����</td>
								<td width="85%">${data.hpxx}</td>
							</tr>
							<tr>
								<td width="15%" height="20px">������</td>
								<td width="85%">${data.hpr}</td>
							</tr>
						</tbody>
					</table>
				</logic:notEqual>
				<logic:equal name="pjlx" value="ls">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>ʦ����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px"><font color='red'>*</font>����<br /><font color="red">(��200��)</font></td>
								<td width="85%"><html:textarea property="spxx" rows="8" onblur="checkLen(this,200)" style="width:100%"/></td>
							</tr>
								<tr>
								<td width="15%" height="120px">���۵ȼ�</td>
								<td width="85%">
								<html:select property="pjdj" styleId="pjdj"><html:options collection="pjdjlist" property="pjdj" labelProperty="pjdjmc" />
								</html:select>
							</td>
							</tr>
						</tbody>
					</table>
				</logic:equal>
			<logic:notEqual name="sfkypj" value="1">
						<table width="100%" border="0" class="formlist">
							<tfoot>
							<tr>
								<td colspan="4">
								<logic:notEqual name="pjlx" value="ls">
									<div class="bz">
									<logic:equal name="sfkypj" value="-3">
										<span class="red">�ѱ����������ٽ��л���</span>
									</logic:equal>
									<logic:equal name="sfkypj" value="0">
										<span class="red">���ܶ��Լ�����</span>
									</logic:equal>
									<logic:equal name="sfkypj" value="-1">
										<span class="red">�Ѿ���ʦ�����ܽ��л�������</span>
									</logic:equal>
									</div>
									</logic:notEqual>
									<div class="btn">
										<logic:equal name="pjlx" value="ls">
										<button type="button"  onclick="save();return false;" id="buttonSave">
											�� ��
										</button>
										</logic:equal>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  onclick="iFClose();" id="buttonClose">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
			</logic:notEqual>
			<logic:equal name="sfkypj" value="1">
				<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="window.parent.ymPrompt.close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</logic:equal>
	</html:form>
</html>