<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	</head>
	<body>
		<html:form action="/zbglZbjg" method="post" styleId="form">
			
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:420px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>������Ů</th>
							<td>
								${zbglZbjgForm.dszn }
							</td>
							<th>����״��</th>
							<td>
								${zbglZbjgForm.hyzk }
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								${zbglZbjgForm.sl }
							</td>
							<th>���</th>
							<td>
								${zbglZbjgForm.sg } cm
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								${zbglZbjgForm.tz } kg
							</td>
							<th>Ӧ����</th>
							<td>
								${zbglZbjgForm.yzd }
							</td>
						</tr>
						<tr>
							<th>Ӧ����Դ</th>
							<td>
								${zbglZbjgForm.yzly }
							</td>
							<th>�ξ���Ը</th>
							<td>
								${zbglZbjgForm.cjyy }
							</td>
						</tr>
						<logic:equal name="xxdm" value="14073">
							<tr>
								<th>�Ͷ���ֹ����</th>
								<td>
									${zbglZbjgForm.ylzd1}
									��
									${zbglZbjgForm.ylzd2}
								</td>
								<th>ѧϰ����</th>
								<td>
									${zbglZbjgForm.ylzd3}
								</td>
							</tr>
							<tr>
								<th>ѧҵ���</th>
								<td>
									${zbglZbjgForm.ylzd4}
								</td>
								<th>ѧУ����</th>
								<td>
									${zbglZbjgForm.ylzd5}
								</td>
							</tr>
							<tr>
								<th>ѧУ�������ŵ�ַ</th>
								<td colspan="3">
									${zbglZbjgForm.ylzd7}
								</td>
							</tr>
							<tr>
								<th>�ʱ�</th>
								<td>
									${zbglZbjgForm.ylzd8}
								</td>
								<th>ԺУ���ڵ�</th>
								<td>
									${zbglZbjgForm.ylzd6}
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>��ҵ���</th>
							<td>
								${zbglZbjgForm.cylb }
							</td>
							<th>ְҵ�ʸ�֤��</th>
							<td>
								${zbglZbjgForm.zgzs }
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="">
								${zbglZbjgForm.bz }
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
										var gid = "${zbglZbjgForm.filepath}";
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
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
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