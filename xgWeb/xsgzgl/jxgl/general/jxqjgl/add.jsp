<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/jxqjgl/js/jxqjjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		jQuery(function(){ 
			var qjkssjV = '${jxxxMap.kssj }';
			var qjjssjV = '${jxxxMap.jssj }';
					jQuery("#qjkssj").bind("focus", function (){
						var qjjssj = jQuery("#qjjssj").val();
						if(qjjssj == ''){
							qjjssj = qjjssjV;
						}
						WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:qjjssj,minDate:qjkssjV}); 
					}); 
					jQuery("#qjjssj").bind("focus", function (){
						var qjkssj = jQuery("#qjkssj").val();
						if(qjkssj == ''){
							qjkssj = qjkssjV;
						}
						WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:qjjssjV,minDate:qjkssj});
					}); 
				
				});
		
		</script>
	</head>
	<body>
		<html:form method="post" styleId="JxqjjgForm" action="/jxqjjg" enctype="multipart/form-data">
		<html:hidden property="xq" styleId="xq" />
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="jxkssj" value='${jxxxMap.kssj}'/>
		<input type="hidden" id="jxjssj" value='${jxxxMap.jssj}'/>
		<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>��ѵ��Ϣ
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr >
				<td colspan="4">
				<table width="100%" class="formlist" >
	            <tr >
				<th style="text-align:center;">��ѵ����</th>
				<th style="text-align:center;">��ѵ��ʼʱ��</th>
				<th style="text-align:center;">��ѵ����ʱ��</th>
				</tr>
				<tr>
				<td align="center">${jxxxMap.jxmc}</td>
				<td align="center">${jxxxMap.kssj}</td>
				<td align="center">${jxxxMap.jssj}</td>
				</tr>
				</table>
				</td>
				</tr>
				</tbody>
				<tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<logic:equal name="xxdm" value="10026">
						<tr>
							<th align="right" width="10%">
								����
							</th>
							<td align="left" colspan="3">
								${jzmc}
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th align="right" width="10%">
							ѧ��
						</th>
						<td align="left">
							<html:text property="xn" styleId="xn" style="width:120px;" maxlength="4" readonly="true"></html:text>
						</td>
						<th align="right">
							ѧ��
						</th>
						<td align="left">
							<html:text property="xqmc" styleId="xqmc" style="width:120px;" maxlength="4" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>�������
						</th>
						<td align="left">
							<html:text property="qjts" styleId="qjts" style="width:120px;" maxlength="4" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"></html:text>&nbsp;&nbsp;��&nbsp;&nbsp;
						</td>
						<th align="right">
							<span class="red">*</span>�������
						</th>
						<td align="left">
								<html:select property="qjlx" styleId="qjlx" disabled="false" 
									style="width:125px;">
									<html:options collection="qjlxList" property="qjlxid"
										labelProperty="qjlxmc" />
								</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<logic:equal name="xxdm" value="10026">
								<span class="red">*</span>��Ӫʱ��
							</logic:equal>
							<logic:notEqual name="xxdm" value="10026">
								<span class="red">*</span>��ٿ�ʼʱ��
							</logic:notEqual>
						</th>
						<td align="left">
							<html:text property="qjkssj" styleId="qjkssj" style="width:120px;"/>
						</td>
						<th align="right">
							<logic:equal name="xxdm" value="10026">
								<span class="red">*</span>��Ӫʱ��
							</logic:equal>
							<logic:notEqual name="xxdm" value="10026">
								<span class="red">*</span>��ٽ���ʱ��
							</logic:notEqual>
						</th>
						<td align="left">
							<html:text property="qjjssj" styleId="qjjssj" style="width:120px;" />
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<logic:equal name="xxdm" value="10026">
								<span class="red">*</span>���ԭ��&nbsp;
							</logic:equal>	
							<logic:notEqual name="xxdm" value="10026">
								<span class="red">*</span>�������&nbsp;
							</logic:notEqual>
							<br />
							<font color="red">(��500��)</font>	
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="qjsy" styleId="qjsy" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							������Ϣ
						</th>
						<td colspan="3">
							<html:hidden property="fjxx" styleId="fjxx" />
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
												elementid : 'fjxx'
												});
										});
									</script>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save('jxqjjg.do?method=save','xh-qjts-qjlx-qjkssj-qjjssj-qjsy','false');return false;" id="buttonSave">
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
