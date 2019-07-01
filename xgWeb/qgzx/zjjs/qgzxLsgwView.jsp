<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveSyddk(){
			var url="zgmsxy_xszz.do?method=syddkUpdate&doType=save";
			
			if($("xh") && $("xh").value==""){
				alertInfo("ѧ�Ų���Ϊ��!");
				return false;
			}
			showTips("������,���Ժ�...");
			refreshForm(url);
		}
		
		function updateSyddk(){
			var url="zgmsxy_xszz.do?method=syddkOne&doType=save";
			showTips("������,���Ժ�...");
			refreshForm(url);
		}
		
		function setTextRed(){
			if($("doType") && $("doType").value=="view"){
			 jQuery('input[type=text]').attr('readonly',true);
			}
		}
		</script>
	</head>
	<body onload="setTextRed()">
		<html:form action="/qgzxLsgwView" method="post">
			<!-- ������ -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/zgmsxy_xszz.do?method=syddkUpdate" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="tableName" id="tableName"
				value='view_xsjbxx' />
			<input type="hidden" name="pkValue" id="pkValue"
				value='${rs.pkValue}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<!-- ������ -->

			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div class="tab">
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
							<th style="width:16%">
								ѧ��
							</th>
							<td style="width:34%">
								${rs.xh}
							</td>
							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								�Ա�
							</th>
							<td style="width:34%">
								${rs.xb}
							</td>
							<th style="width:16%">
								�꼶
							</th>
							<td style="width:34%">
								${rs.nj}
							</td>
						</tr>

						<tr>

							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${rs.xymc}
							</td>
							<th style="width:16%">
								רҵ
							</th>
							<td style="width:34%">
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								�༶
							</th>
							<td style="width:34%">
								${rs.bjmc}
							</td>
							<th style="width:16%">
								��λ
							</th>
							<td style="width:34%">
								${rs.gwmc}
							</td>
						</tr>
						<logic:notEqual value="12862" name="xxdm">
						<tr>
							<th style="width:16%">
								������ʼʱ��
							</th>
							<td style="width:34%">
								${rs.gzkssj}
							</td>
							<th style="width:16%">
								��������ʱ��
							</th>
							<td style="width:34%">
								${rs.gzjssj}
							</td>
						</tr>
						</logic:notEqual>
						<tr>
							<th style="width:16%">
								���
							</th>
							<td style="width:34%">
								${rs.cj}Ԫ
							</td>
							<logic:equal value="12862" name="xxdm">
							<th style="width:16%">
								������ʱ��
							</th>
							<td style="width:34%">
								${rs.gzzsj }
							</td>
							</logic:equal>
							<logic:notEqual value="12862" name="xxdm">
							<th style="width:16%">
								
							</th>
							<td style="width:34%">
								
							</td>
							</logic:notEqual>
						</tr>
						<tr>
							<th style="width:16%">
								�����ص�
							</th>
							<td style="width:84%" colspan="3">
								<html:textarea name='rs' property='gzdz' styleId="gzdz"
									style="word-break:break-all;width:99%" rows='4' disabled="true" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								��������
							</th>
							<td style="width:34%" colspan="3">
								<html:textarea name='rs' property='gznr' styleId="gznr"
									style="word-break:break-all;width:99%" rows='5' disabled="true" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								
								<div class="btn">

									<button type="button" name="�ر�" onclick="Close();return false;" id="buttonClose">
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
