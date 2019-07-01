<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		
	</head>
	<body onload="">
	
		<html:form action="/gyglnew_jcrcgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="str" id="str" value="${str }" />
			<div style='width:100%;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="6">
								<span>��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								¥��
							</th>
							
							<td width="34%" colspan="2" >
								${rsAll.ldmc}
							</td>
							<th width="16%">
								���
							</th>
							
							<td width="34%" colspan="2" >
								${rsAll.ch}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								���Һ�
							</th>
							
							<td width="34%" colspan="2" >
								${rsAll.qsh}
							</td>
							<th width="16%">
								�����꼶
							</th>
							
							<td width="34%" colspan="2" >
								${rsAll.shnj}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								����<bean:message key="lable.xb" />
							</th>
							
							<td width="34%" colspan="2" >
								${rsAll.shxy}
							</td>
							<th width="16%">
								����ճ�
							</th>
							
							<td width="34%" colspan="2" >
								${jcrc}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								��λ��
							</th>
							
							<td width="34%" colspan="2" >
								${rsAll.cws}
							</td>
							
							<th align="right">
								��鲿��
							</th>
							<td align="left" colspan="2">
								${rs.jcbm }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								��ס����
							</th>
							
							<td width="34%" colspan="2" >
								${rsAll.rzrs}
							</td>
							<th width="16%">
								�������
							</th>
							<td width="34%" colspan="2" >
								${rs.jcrq}
							</td>
						</tr>
						<tr style="height:22px">
							<logic:equal value="0" name="jclx">
								<th width="16%">
									��ֵ
								</th>
								<td align="left" colspan="2">
									${rs.fs }
								</td>
							</logic:equal>
							<logic:equal value="1" name="jclx">
								<th width="16%">
									�ȼ�
								</th>
								<td align="left" colspan="2">
									${rs.dj }
								</td>
							</logic:equal>
							<logic:equal value="2" name="jclx">
								<th width="16%">
									�Ǽ�
								</th>
								<td align="left" colspan="2">
									${rs.dj }
								</td>
							</logic:equal>
							<th align="right">
								�����Ա
							</th>
							<td align="left" colspan="2">
								${rs.jcry }
							</td>
						</tr>
						<tr style="height:90px">
							<th align="right">
								��ֵ��ע
							</th>
							<td colspan="2" style="word-break:break-all;">
								<logic:equal value="1103202" name="xxdm">
								${rs.kfmc }
								</logic:equal>
								<logic:notEqual value="1103202" name="xxdm">
								${rs.pfbz }
								</logic:notEqual>
							</td>
							<th align="right">
								��鱸ע
							</th>
							<td colspan="2" style="word-break:break-all;">
								${rs.bz }
							</td>
						</tr>
						<%--����о���ó��ѧУ���Ի�--%>
						<logic:equal value="1103202" name="xxdm">
							<tr>
								<th>������Ϣ</th>
								<td colspan="5">
									<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${rs.fjpath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
									</script>
								</td>
							</tr>
						</logic:equal>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" onclick="Close();return false;">
										�� ��
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

