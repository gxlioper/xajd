<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/lxsq/js/lxsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/lxsq/js/comm.js"></script>
		<script type="text/javascript">
			function changeBmmc(){
				//ȡ�����ݱ�xg_zjdx_pjpy_pjxmb; �ֶΣ�xmmc																							
				var autoSetting = {
					dataTable:"zxbz_xxbmdm",
					dataField:"bmmc",
					dataFieldKey:"bmdm",
					dataFieldKeyId:"zdbm",
					scrollHeight:135										
				}
				// ģ��������������Ŀ���ơ�
				jQuery("#bmmc").setAutocomplete(autoSetting);
			}
			jQuery(function(){
				changeBmmc();
				initData();
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jskp_lxsq" method="post" styleId="LxsqForm">
			<input type="hidden" value="add" name="ryflag" id="ryflag"/>
			<input type="hidden" value="${sfsh}" name="sfsh" id="sfsh"/>
			<html:hidden property="sqid" styleId="sqid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%"><font color="red">*</font>��Ŀ����</th>
							<td style="width:35%">
								<input type="text" name="xmmc" id="xmmc" maxlength="25" value="${LxsqForm.xmmc}"/>
							</td>
							<th style="width:15%"><font color="red">*</font>ָ������</th>
							<td style="width:35%">
								<input type="text" name="bmmc" id="bmmc" value="${bmmc}" />
								<input type="hidden" id="zdbm" name="zdbm" value="${LxsqForm.zdbm}" />
							</td>
						</tr>	
						<tr>
							<th><font color="red">*</font>��Ŀ���</th>
							<td>
								<html:select   property="xmlb" styleId="xmlb" value="${LxsqForm.xmlb}" style="width:173px;">
									<html:options collection ="xmlbList" property="xmlbdm" labelProperty="xmlbmc" />
								</html:select>
							</td>
							<th><font color="red">*</font>����ʱ��</th>
							<td>
								<html:text property="lxsj" styleId="lxsj" onclick="return showCalendar('lxsj','y-mm-dd',true,'maxtime');"  maxlength="10" ></html:text>
								<input type="hidden" name="maxtime" id="maxtime" maxlength="10" value="${maxtime}"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>������</th>
							<td>
								<span>${fzrxm}</span>
								<input type="hidden" id="fzr" name="fzr" value="${fzr}" />
							</td>
							<th>��������ϵ��ʽ</th>
							<td>
								<html:text property="fzrlxfs" styleId="fzrlxfs" value="${LxsqForm.fzrlxfs}" maxlength="15" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>ָ����ʦ</th>
							<td>
								<html:text property="zdls" styleId="zdls" value="${LxsqForm.zdls}" maxlength="10" ></html:text>
							</td>
							<th>ָ����ʦ��ϵ��ʽ</th>
							<td>
								<html:text property="zdlslxfs" styleId="zdlslxfs" value="${LxsqForm.zdlslxfs}"  maxlength="15" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
						</tr>
						<logic:equal value="1" name="sfsh">
							<tr>
								<th><font color="red">*</font>��������</th>
								<td colspan="3">
									<input name="zxf" type="text" maxlength="8" id="zxf" value="${LxsqForm.zxf}" style="width:80px" onkeyup="checkInput(this)" onblur="replaceAboveZero(this)" /> - <input name="zdf" style="width:80px" value="${LxsqForm.zdf}" maxlength="8"  type="text" id="zdf" onkeyup="checkInput(this)" onblur="replaceAboveZero(this)"/>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>������</th>
							<logic:equal value="1" name="sfsh">
								<td colspan="3">
									<textarea rows="8" style="width:99%;" name="cyry" id="cyry" >${xhs}</textarea>
								</td>
							</logic:equal>
							<logic:equal value="0" name="sfsh">
								<td>
									<html:text  property="xh" styleId="xh" maxlength="10" value="${xhs}"></html:text>
								</td>
							</logic:equal>
						</tr>
						<tr id="yztr1" style="display:none">
							<th>�ɲ�����Ա</th>
							<td colspan="3">
								<span id="kcyry">
									<logic:iterate id="i" name="xhList">
										${i.xh}&nbsp;
									</logic:iterate>
								</span>
								<div style="display:none" id="kcyryspan">
									<logic:iterate id="i" name="xhList">
										<input type="hidden" name="xh" value="${i.xh}" />
									</logic:iterate>
								</div>
							</td>
						</tr>
						<tr id="yztr2" style="display:none">
							<th>��֤δͨ����Ա</th>
							<td colspan="3">
								<span id="wtgry"  class="red"></span>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath_f" />
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
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
									<html:textarea property="lxly" styleId="lxly" 
								   onkeyup="checkLen(this,500);" 
								   style="width:99%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:equal value="1" name="sfsh">
										<button type="button" onclick="saveLxsq('save');">
											����ݸ�
										</button>
										<button type="button" onclick="saveLxsq('submit');">
										�ύ����
									</button>
									</logic:equal>
									<logic:equal value="0" name="sfsh">
										<button type="button" onclick="saveLxsq('submitUpdate');">
											�ύ����
										</button>
									</logic:equal>
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