<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xsxxgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/xxgl/js/xxgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/comm/js/commUpdate.js"></script>
	</head>
	<body >	
	<html:form action="/xsxx_bysxx_xxgl" method="post" styleId="form1">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfxsgban" id="sfxsgban" value="${sfxsgban} "/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>
			<input type="hidden" name="xxdm" id="xxdm"  value="${xxdm }"/>
			<input type="hidden" name="xh" id="xh" value='${xh}'/>
			<input type="hidden" name="bynd" id="bynd" value='${bynd}'/>
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
			<div style="height: 15px"></div>			
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"Ϊ������  </div>
								<div class="btn">
										<button name="����" id="buttonSave" type="button" onclick="saveForm();">
											�� ��
										</button>
									<button type="button"  name="�ر�" onclick="Close()" id="buttonClose">�� ��</button>
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
	</body>
</html>

