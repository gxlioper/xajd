<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>

		<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzjg.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		
		<script type="text/javascript">
		jQuery(function(){
			 var videotype = document.getElementsByName("lsgx");
		      var len = videotype.length;
		      var i=0;
		      for(;i<len;i++){
		       if("����" == videotype[i].value){
		        videotype[i].checked=true;
		        break;
		       }
		      }  
		    //����ʦ����ѧ
		     var xxdm = jQuery("#xxdm").val();
		      if(xxdm == '10511'){
					dklxchange();
					
				}
		});
			
		</script>
	</head>
	<body>
		<html:form action="/tyxs_zzjg" method="post" styleId="tyxsZzjgForm">
			<%@ include file="/comm/hiddenValue.jsp"%>

			<html:hidden property="splcid" value="${cssz.xfzzshlc }"
				styleId="splcid" />
			<html:hidden property="xn" value="${xn}" styleId="xn" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<html:hidden property="dqxn" value="${xn}" styleId="dqxn" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								
								<logic:notEqual value="12688" name="xxdm" >
									<span>������Ϣ</span>
								</logic:notEqual>
								<logic:equal value="12688" name="xxdm" >
									<span>������Ϣ</span>
								</logic:equal>
							</th>
						</tr>
					</thead>
					<tbody>
							<logic:equal name="xxdm" value="10511">
							<tr>
								<th><span class="red">*</span>��������</th>
					    		<td>
					    			<select name="dklx" id="dklx">
					    				<option value=""></option>
					    				<option value="��">��</option>
					    				<option value="��Դ�ش���">��Դ�ش���</option>
					    				<option value="������ѧ����">������ѧ����</option>
					    			</select>
					    		</td>
							</tr>
							</logic:equal>
							<tr>
								<th>
									<span class="red">*</span>����ѧ��
								</th>
								<td>
									<html:select  property="xn" styleId="xn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
								</td>
								<th>
									<span class="red">*</span>�����ܽ�Ԫ��
								</th>
								<td>
									<html:text property="sqxfzj" styleId="sqxfzj" maxlength="10"
										onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
								</td>
							</tr>
							<tr>
								
								<th>
									<span class="red">*</span>����ʱ��
								</th>
								<td>
									<html:text property="sqsj" styleId="sqsj" readonly="true"
									onfocus="showCalendar('sqsj','yyyy-MM-dd HH:mm:ss');"></html:text>
								</td>

								<%--<th>
									<span class="red">*</span>�����ܽ��(Ԫ)
								</th>
								<td>
									<html:text property="zzzje" maxlength="10" styleId="zzzje" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
								</td>--%>
							</tr>

							<tr>
								<th height="49px">
									<span class="red" style="height: 100px">*</span>��������
									<br/><span class="red" >(��250��)</span>
								</th>
								<td colspan="3">
									<html:textarea property="sqly" styleId="sqly"
										style="width:93%;" rows="4"></html:textarea>
								</td>

							</tr>
							<logic:notEqual value="10511" name="xxdm">
								<tr>
									<th align="right">
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
						</tbody>
					</table>
				<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				</div>
				<div style="height:25px;"></div>
				<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">	"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button"
											onclick="saveZzsq('tyxs_zzjg.do?method=save');">
											����
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
			<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
</html>
