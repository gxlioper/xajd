<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+ new Date().getTime());
			});
	   </script>
	</head>
	<body>
		<html:form action="/rcsw_xsgzzb_xsgzzbsqgl" method="post" styleId="xsgzzbsqForm">
		<html:hidden property="sqid" styleId="sqid" />	
			<div style='tab;width:100%;height:410px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ܱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${model.xn}
							</td>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${model.xqmc}
							</td>
					    </tr>
					    <tr>
							<th>�꼶</th>
							<td>
								${model.nj}
							</td>
							<th><bean:message key="lable.xy" /></th>
							<td>
								${model.xymc}
							</td>
					    </tr>
					    <tr>
							<th>רҵ</th>
							<td>
								${model.zymc}
							</td>
							<th>�༶</th>
							<td>
								${model.bjmc}
							</td>
					    </tr>
					    </tr>
							<th>�ܴ�</th>
							<td>
								${model.zcmc}
							</td>
							<th>�ܴ���ֹ����</th>
							<td>
								${model.zcksjsrq}
							</td>
					    </tr>
					    <tr>
							<th>
								Ӧ������
							</th>
							<td>
								${model.ydrs}
							</td>
							<th>
								ʵ������
							</th>
							<td>
								${model.sdrs}
							</td>
			      		</tr>
					    <tr>
							<th>
								�������
							</th>
							<td>
								${model.qjrs}
							</td>
							<th>
								�޹�δ��ѧ������
							</th>
							<td>
								${model.wdrs}
							</td>
			      		</tr>
			      		<tr>
							<th>��д��</th>
							<td colspan="3">
								${model.lrrxm}
							</td>
					    </tr>

						<logic:equal name="xxdm" value="10704">
							<tr>
								<th>
									���ศ��Ա
								</th>
								<td id="dbfdy" colspan="3">
										${model.dbfdy }
								</td>
							</tr>
						</logic:equal>

					    <tr>
							<th>
								���ܶ�ѧ������<br />��������Ҫ����
							</th>
							<td colspan="3">
							    ${model.zynr}
							</td>
			      		</tr>
					    <tr>
							<th>
								����ѧ�����ڵ�<br />��Ҫ����
							</th>
							<td colspan="3">
							    ${model.zywt}
							</td>
			      		</tr>
					    <tr>
							<th>
								����Ϊ�Ϻ����<br />����Բ�
							</th>
							<td colspan="3">
							    ${model.jjdc }
							</td>
			      		</tr>
					    <tr>
					    	<th align="right">
								����
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${model.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
			      		</tr>
					</tbody>
				</table>				
				<logic:notEqual value="�������" name="shztmc">
					<table width="100%" border="0" class="formlist">
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

						</tbody>

					</table>
				</logic:notEqual>
			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</table>
		</html:form>
	</body>
</html>

