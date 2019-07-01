<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="gygl/sspy/pysq/js/pysq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				getSscyxx();
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="sspysqForm" action="/sspysq">
		<input type="hidden" id="lddm" value="${lddm }"/>
		<input type="hidden" id="qsh" value="${qsh }"/>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>��λ��Ϣ</span>
						</th>
					</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								¥������
							</th>
							<td align="left">
								${cwxxData.ldmc}
							</td>
							<th align="right">
								���Һ�
							</th>
							<td align="left">
								${cwxxData.qsh}
							</td>
						</tr>
						<tr>
							<th align="right" >
								��λ��
							</th>
							<td align="left" id="td_cwh">
								${cwxxData.cwh}
							</td>
							<th align="right">
								���ҵ绰
							</th>
							<td align="left">
								${cwxxData.qsdh}
							</td>
						</tr>
						<tr>
							<th align="right" >
								�շѱ�׼
							</th>
							<td align="left">
								${cwxxData.sfbz}
							</td>
							<th align="right">
								�����꼶
							</th>
							<td align="left">
								${cwxxData.nj}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								����<bean:message key="lable.xb" />
							</th>
							<td align="left">
								${cwxxData.xymc}
							</td>
							<th align="right">
								�����༶
							</th>
							<td align="left">
								${cwxxData.bjmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ա</span>
							</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<th  width="20%">
								<div align="center" >ѧ��</div>
							</th>
							<th>
								<div align="center" >����</div>
							</th>
							<th>
								<div align="center" >�༶</div>
							</th>
							<th>
								<div align="center" >��ס��λ</div>
							</th>
						</tr>
					</thead>
					<tbody id="xsList">
					</tbody>
					
				<thead>
						<tr>
							<th colspan="4">
								<span>����������Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								ѧ��
							</th>
							<td align="left">
								${dqxn}
							</td>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
								${dqxq}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>������Ŀ
							</th>
							<td align="left" colspan="3">
								<html:select property="pyxmdm" styleId="pyxmdm" disabled="false" >
							<html:options collection="pyxmList" property="pyxmdm"
								labelProperty="pyxmmc" />
						</html:select>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>��������&nbsp;
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="5">
								<html:textarea rows="4" property="sqly" styleId="sqly"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
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
								��ע&nbsp;
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="5">
								<html:textarea rows="4" property="bz" styleId="bz"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					
					</tbody>
			</table>
		</div>
		<div style="height:25px"></div> 
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"
										onclick="save('sspysq.do?method=add&type=save');return false;"
										id="buttonSave">
										����ݸ�
									</button>
									<button type="button"
										onclick="save('sspysq.do?method=add&type=submit');return false;"
										id="buttonSave">
										�ύ����
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
