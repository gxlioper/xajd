<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="gygl/sspy/pyjg/js/sspyjg.js"></script>
		<script>
			jQuery(function(){
				loadLdxx();
			});

			jQuery(function() {
				jQuery("#xsList").empty();
				var ldqsxx=jQuery('#lddm').val()+jQuery('#qsh').val();
				var html = "";
				jQuery.post("sspysq.do?method=getCwxx", {
					ldqsxx : ldqsxx
				}, function(data) {
					for(var i =0;i<data.length;i++){
						html+="<tr><td align='center'>"+data[i]["xh"]+"</td><td align='center'>"+data[i]["xm"]+"</td><td align='center'>"+data[i]["xsbjmc"]+"</td><td align='center'>"+data[i]["cwh"]+"</td></tr>";
					}
					jQuery("#xsList").append(html);
				}, 'json');
		});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="sspyjgForm" action="/sspyjg">
		<input type="hidden" id="guid" name="guid" value="${sspyjgForm.guid }"/>
		<input type="hidden" id="lddm" name="lddm" value="${sspyjgForm.lddm}"/>
		<input type="hidden" id="qsh" name="qsh" value="${sspyjgForm.qsh}"/>
		<input type="hidden" id="ch" name="ch" value="${sspyjgForm.ch}"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>¥��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							¥��
						</th>
						<td>
							${qsxxMap.ldmc}
						</td>
						<th>
							���
						</th>
						<td>
							${qsxxMap.ch}
						</td>
					</tr>
					<tr>
						<th>���Һ�</th>
						<td>
							${qsxxMap.qsh}
						</td>
						<th>���ҵ绰��</th>
						<td>
							${qsxxMap.qsdh}
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
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td>
							<html:select property="xn" styleId="xn" style="width:155px;">
								<html:options collection="xnList" labelProperty="xn" property="xn" />
							</html:select>
						</td>
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td>
							<html:select property="xq" styleId="xq" style="width:155px;">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>������Ŀ
						</th>
						<td align="left" colspan="3">
							<html:select property="pyxmdm" styleId="pyxmdm" disabled="false" >
								<html:options collection="pyxmList" property="pyxmdm" labelProperty="pyxmmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>��������&nbsp;<br/>
							<font color="red">(��500��)</font>
						</th>
						<td colspan="5">
							<html:textarea rows="4" property="sqly" styleId="sqly" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">��ע&nbsp;<br/>
							<font color="red">(��500��)</font>
						</th>
						<td colspan="5">
							<html:textarea rows="4" property="bz" styleId="bz" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
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
				</tbody>
			</table>
		</div>
		<div style="height:30px;"></div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="sspyjgSave();">
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
</html>