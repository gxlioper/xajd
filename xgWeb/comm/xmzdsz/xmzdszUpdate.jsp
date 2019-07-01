<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/xszz/xszzComm.js"></script>
		<script type="text/javascript">
			function plbczd(){
			
				refreshForm('/xgxt/xmzdsz.do?method=xmzdszUpdate&doType=save');
			}
			
			function setSjy(obj){
				
				var sjy = $('sjy').value;
				
				if (obj.checked){
					$('sjy').value+=","+obj.value;
				} else {
					$('sjy').value = $('sjy').value.replace(obj.value,"");
				}
				
				$('search_go').click();
				
			}
		</script>
	</head>

	<body>
		<!-- ���� -->
		<!-- ���� end-->
		<html:form action="/xmzdsz">
			<!-- ������ -->
			<input type="hidden" name="mkmc" value="${mkmc }"/>
			<input type="hidden" name="xmdm" value="${xmdm }"/>
			<input type="hidden" name="sjy" value="${sjyArr }" id="sjy"/>
			<html:hidden property="queryequals_xmdm" styleId="xmdm"/>
			<!-- ������ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<!-- ��ť end-->
				<!-- �������� -->
				 <div class="searchtab">
				<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xmzdsz.do?method=xmzdszUpdate')">
											�� ѯ
										</button>
										 <button type="button" class="btn_cz" id="btn_cz" onclick="plbczd();">
											 �� ��
										 </button>
										 <button type="button" class="btn_cz"  onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();">
											 �� ��
										 </button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<td colspan="4">
									 <logic:match value="view_xsjbxx" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="view_xsjbxx" checked="checked" onclick="setSjy(this)"/>ѧ��������Ϣ
									 </logic:match>
									 <logic:notMatch value="view_xsjbxx" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="view_xsjbxx" onclick="setSjy(this)"/>ѧ��������Ϣ
									 </logic:notMatch>
									 <logic:match value="jtqkdcb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="jtqkdcb" checked="checked" onclick="setSjy(this)"/>ѧ����ͥ���
									 </logic:match>
									 <logic:notMatch value="jtqkdcb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="jtqkdcb" onclick="setSjy(this)"/>ѧ����ͥ���
									 </logic:notMatch>
									 
									 <logic:match value="xszz_knsb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="xszz_knsb" checked="checked" onclick="setSjy(this)"/>��������Ϣ
									 </logic:match>
									 <logic:notMatch value="xszz_knsb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="xszz_knsb" onclick="setSjy(this)"/>��������Ϣ
									 </logic:notMatch>
									 
									  <logic:match value="cjb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="cjb" checked="checked" onclick="setSjy(this)"/>ѧ���ɼ�
									  </logic:match>
									  <logic:notMatch value="cjb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="cjb" onclick="setSjy(this)"/>ѧ���ɼ�
									 </logic:notMatch>
									 
									 
									 
									 <logic:match value="xsqtxxb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="xsqtxxb" checked="checked" onclick="setSjy(this)"/>ѧ��������Ϣ
									 </logic:match>
									 <logic:notMatch value="xsqtxxb" name="sjyArr">
									 	<input type="checkbox" name="sjly" value="xsqtxxb" onclick="setSjy(this)"/>ѧ��������Ϣ
									 </logic:notMatch>
									 
									 <logic:match value="${xmmc }" name="sjyArr">
										<input type="checkbox" name="sjly" value="${xmmc }" checked="checked" onclick="setSjy(this)"/>����Ŀ����ֶ�
									 </logic:match>
									  <logic:notMatch value="${xmmc }" name="sjyArr">
										<input type="checkbox" name="sjly" value="${xmmc }" onclick="setSjy(this)"/>����Ŀ����ֶ�
									 </logic:notMatch>
									 
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->								
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
						</span>
					</h3>
					
					<table summary="" class="dateline"  width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" style="height:17.5px" />
								</td>
								<td onclick="tableSort(this)">
									������Դ	
								</td>
								<td onclick="tableSort(this)">
									�ֶ�����
								</td>
								<td onclick="tableSort(this)">
									��������	
								</td>
								<td onclick="tableSort(this)">
									�Ƿ����	
								</td>
								<td onclick="tableSort(this)">
									�ֶ�����	
								</td>
								<td onclick="tableSort(this)">
									¼������	
								</td>
								<td onclick="tableSort(this)">
									��ʾ˳��	
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)">
										<td>
											<input type="checkbox" value="${s.pkValue }" name="primarykey_cbv" ${s.checked } />
											<input type="hidden" value="${s.pkValue }" name="flg"/>
										</td>
										<td>
											${s.lybmc }
											<input type="hidden" value="${s.lyb }" name="lyb"/>
										</td>
										<td>
											${s.zdmc }
											<input type="hidden" value="${s.zd }" name="zd"/>
										</td>
										<td>
											<input type="text" value="${s.zdm }" name="zdm" style="width:100px" maxlength="25"/>
										</td>
										<td>
											<html:select property="bxlr" name="s">
												<html:option value="��">��</html:option>
												<html:option value="��">��</html:option>
											</html:select>
										</td>
										<td>
											<html:select property="zdlx" name="s">
												<logic:equal value="VIEW_XSJBXX" property="lyb" name="s">
													<html:option value="short">���ı�</html:option>
													<html:option value="long">���ı�</html:option>
												</logic:equal>
												
												<logic:equal value="view_xsjbxx" property="lyb" name="s">
													<html:option value="short">���ı�</html:option>
													<html:option value="long">���ı�</html:option>
												</logic:equal>
												
												<logic:notEqual value="view_xsjbxx" property="lyb" name="s">
												<logic:notEqual value="VIEW_XSJBXX" property="lyb" name="s">
													<html:option value="text">���ı���</html:option>
													<html:option value="long">���ı���</html:option>
													<html:option value="select">������</html:option>
													<html:option value="textarea">�ı���</html:option>
												</logic:notEqual>
												</logic:notEqual>
											</html:select>
										</td>
										
										<td>
											<html:select property="lrxz" name="s">
												<html:option value="������">������</html:option>
												<html:option value="����">����</html:option>
												<html:option value="���">���</html:option>
												<html:option value="����">����</html:option>
											</html:select>
										</td>
										
										<td>
											<input type="text" name="zdsx" 
												   onkeyup="value=value.replace(/[^\d]/g,'')"
												   maxlength="5"
												   value="${s.zdsx }" style="width:50px"/>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
					<!--��ҳ��ʾ-->
					 <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xmzdszForm"></jsp:include>
					<!--��ҳ��ʾ-->
				
				</div>
				<!-- ��ѯ��� end-->
		</html:form>
		
		<logic:present name="message">
			<script defer="defer">
				alert("${message}");
			</script>
		</logic:present>
	</body>
</html>