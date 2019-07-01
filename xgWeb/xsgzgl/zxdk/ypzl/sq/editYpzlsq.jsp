<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/ypzl/sq/js/ypzl.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/ypzl_sq" method="post" styleId="ypzlsqForm" onsubmit="return false;">
		<html:hidden property="sqid" styleId="sqid"/>
		<html:hidden property="splc" styleId="splc"/>
		<input type="hidden" id="xxdm" value="${xxdm}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����˾����������ѧ�꣩</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${xnsr}
							</td>
							</td>
							<th>ѧ��</th>
							<td>
								${xqsr}
							</td>
						</tr>			
						<logic:notEqual value="10511" name="xxdm">
						<tr>
					    	<th><span class="red">*</span>�����Ԫ��</th>
					    	<td>
								<select id="sqje" name="sqje" style="width:200px">
									<option value="6000">6000</option>
									<option value="10000">10000</option>
								</select>
							</td>
						</tr>
						</logic:notEqual>
						<logic:equal value="10511" name="xxdm">
						<tr>
							<th><span class="red">*</span>�����Ԫ��</th>
					    	<td>
								<html:text styleId="sqje" property="sqje" onblur="replaceAboveZero(this)" maxlength="4" onkeyup="checkInput(this)" />
							</td>
							<th><span class="red">*</span>����ԭ�����</th>
							<td>
								<html:select  property="ytlb" styleId="ytlb" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="lbList" labelProperty="lbmc" property="lbdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�����ϴ�
							</th>
							<td colspan="3">
								<html:hidden property="ytms" styleId="ytms" />
				                 <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
						           <script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//��׺
												accept : 'pdf|doc|docx|xls|xlsx|jpg|jpeg',
												//����ļ���С ��λM
												maxsize: 10,
												//��Ÿ������������id
												elementid : 'ytms'
												});
										});
										
							</script>
									</td>
								</tr>
							</logic:equal>
					    </tr>
					    <logic:notEqual value="10511" name="xxdm">					    	
					    	<tr>
								<th align="right" width="16%">
									������Ϣ
								</th>
								<td colspan="3">
									<html:hidden property="filepath" styleId="filepath" />
									<input type="file" id="filepath_f" name="filepath" />
									<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											jQuery('#filepath_f').multiUploader({
												maxcount : 3,
												//��׺
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//����ļ���С ��λM
												maxsize: 10,
												//��Ÿ������������id
												elementid : 'filepath',
				
												eid : 'filepath_f'
											});
										});
									</script>
								</td>
							</tr>    
					    </logic:notEqual>			    
						<tr>
							<th><span><font color="red">*</font></span>
								��������
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='8' onblur="checkLen(this,500);"/>
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
								<button type="button" onclick="saveYpzl('update');">
										����ݸ�
									</button>
									<button type="button" onclick="saveYpzl('updatesubmit');">
										�ύ����
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

