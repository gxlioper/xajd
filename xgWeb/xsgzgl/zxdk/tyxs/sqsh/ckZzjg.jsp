<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dkjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		
	</head>
	<body>
		<html:form action="/tyxs_zzjg" method="post" styleId="tyxsZzjgForm">
			<html:hidden property="id" styleId="id"/>
		
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
						<logic:equal name="xxdm" value="10511">
							<tr>
								<th>��������</th>
					    		<td>
					    			${tyxsZzjgForm.dklx }
					    		</td>
							</tr>
							</logic:equal>
							
						<tr>
							<th>����ѧ��</th>
							<td>
								${tyxsZzjgForm.xn }
							</td>
							<th>����ʱ��</th>
							<td>
								${tyxsZzjgForm.sqsj }
							</td>
						</tr>
						<logic:equal name="xxdm" value="10511">
						<tr>
							<th>������(Ԫ)</th>
							<td>
								${tyxsZzjgForm.dkbj }
							</td>
							<th>��������</th>
							<td>
							   ${yhmc}
							</td>
						</tr>
						<tr>
							<th>�����ͬ��</th>
							<td>
								${tyxsZzjgForm.dkhth}
							</td>
							<th>������ֹʱ��</th>
							<td>
							    ${tyxsZzjgForm.dkkssj}
							    &nbsp;
								��
								&nbsp;
								 ${tyxsZzjgForm.dkjssj}
							</td>
						</tr>
							</logic:equal>
						<tr>
							<th>�����ܽ�Ԫ��</th>
							<td>
								${tyxsZzjgForm.sqxfzj }
							</td>
							<%--<th>�����ܽ�Ԫ��</th>
							<td>
								${tyxsZzjgForm.zzzje }
							</td>--%>
						</tr>
						
						<tr>
							<th height="49px">��������</th>
							<td colspan="3" width="100%">
								${tyxsZzjgForm.sqly }
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="10511">
							<tr>
								<th align="right" width="10%">
									������Ϣ
								</th>
								<td colspan="3">
									<div id="commonfileupload-list-0" style="padding: 5px;"></div>
									<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											var gid = "${tyxsZzjgForm.filepath}";
											jQuery.MultiUploader_q({
												gid : gid,
												targetEl : 'commonfileupload-list-0'
											});
										});
									</script>
								</td>
							</tr>					
						</logic:notEqual>			
					</tbody>
					</tbody>
				</table>
				
			</div>
			<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" ></div>
			</div>
			<div style="height:25px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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