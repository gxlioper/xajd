<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/xxxgsh/js/xgsh.js"></script>
	</head>
	<body>	
	<html:form action="/xsxx_bysxx_xxxgsh" method="post" styleId="form1">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfxsgban" id="sfxsgban" value="${sfxsgban} "/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>
			<input type="hidden" name="xh" id="xh" value='${xh}'/>
			<input type="hidden" name="gwid" id="gwid" value='${gwid}'/>
			<input type="hidden" name="ywid" id="ywid" value='${ywid}'/>
			<input type="hidden" name="lcid" id="lcid" value='${lcid}'/>
			<input type="hidden" name="shid" id="shid" value='${shid}'/>
			<!-- ������� -->
			<div style="height: 50px;">
				<div id="navigation" style="top: 0; background: #fff; position: fixed; width: 100%;_position:absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop));">
					<div class="title_xxxx">
						<span class="people_xx"><span id="xmView"></span> ��ѧ�ţ� ${xh }��</span>
						<span class="wxts">��ܰ���ѣ� <span>�����������
								���Կ��ٶ�λ������Ҫ�鿴����Ϣ</span>
						</span>
					</div>
				</div>
			</div>
			<div class="demo_xxxx" style="margin-top: 20px; overflow-x:hidden;" id="content">
			</div>

			<table width="100%" border="0" style="margin-bottom: 5px" class="formlist" >
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
						<tr>
							<th width="16%" >
								<font width="13%" color="red">*</font>��˽��
							</th>
							<td  colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						
						
						<tr>
							<th>
								<font color="red">*</font>������
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=bysxxxg&id=shyj" />
								<textarea rows="5" style="width: 90%;margin-top: 5px;" id="shyj" name="shyj"></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<div class="btn">
										<button type="button" id="btqd" onclick="save_sh();">
											ȷ��
										</button>
										<button type="button" name="�� ��" onclick="iFClose();">
											�� ��
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

							
			<div id="zpHidDiv" style="display: none;">			
				<jsp:include flush="true" page="/xsgzgl/xsxx/xsxxgl/zpxg.jsp"></jsp:include>
			</div>

			<div id="jtcyxxHidDiv" style="display: none;">
				<jsp:include flush="true" page="/xsgzgl/xsxx/xsxxgl/jtcyxxxg.jsp"></jsp:include>
			</div>						

			</html:form>
		
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
	</body>
</html>

