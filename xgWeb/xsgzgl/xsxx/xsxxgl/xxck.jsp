<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xxck.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
			<script type="text/javascript">
								//���ø��� 
							function  getfj(){
								jQuery('#fjid').val(jQuery('#fj').val());
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								}
							</script>
	</head>
	<body >
	<html:form action="/xsxx_xsxxgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfxsgban" id="sfxsgban" value="${sfxsgban} "/>
			<input type="hidden" name="xh" id="xh" value='${xh}'/>
			<input type="hidden" name="xxdm" id="xxdm" value='${xxdm}'/>
			<input type="hidden" name="zq" id="zq" value='${zq}'/>
			<input type="hidden" name="zzxmzje" id="zzxmzje" value='${zzxmxx.zje}'/>
			<input type="hidden" name="zzxmsl" id="zzxmsl" value='${zzxmxx.sl}'/>
			<input type="hidden" name="fj" id="fj" value="${rs.zd6 }" />
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
			</html:form>	
			
			<span id="dySpan" style="display: none;" >
				<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
										<button type="button" name="btn_dy" onclick="printZxsxx();return false;" id="btn_dy">ѧ���ǼǱ��ӡ</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>					
			</span>
					
			<div id="zpHidDiv" style="display: none;">
				<jsp:include flush="true" page="zpxs.jsp"></jsp:include>
			</div>
		
			<div id="szxxHidDiv" style="display: none;">
				<table style="margin-bottom: 5px" width="100%" border="0"
					class="formlist" id="tab_szxx">
					<tbody id="hi_szxx">
						<tr>
							<th width="13%">
								<div align="center">
									����Ա����
								</div>
							</th>
							<th>
								<div align="center">
									����Աְ����
								</div>
							</th>
							<th>
								<div align="center">
									����Ա��ϵ�绰
								</div>
							</th>

						</tr>
						<logic:notEmpty name="fdyList">
							<logic:iterate id="fdy" name="fdyList">
								<tr>
									<td align="center">
										${fdy.xm }
									</td>
									<td align="center">
										${fdy.zgh }
									</td>
									<td align="center">
										${fdy.lxdh }
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="fdyList">
							<tr>
								<td colspan="3" align="center">
									�������ݣ�
								</td>
							</tr>
						</logic:empty>
						<tr>
							<th width="13%">
								<div align="center">
									����������
								</div>
							</th>
							<th>
								<div align="center">
									������ְ����
								</div>
							</th>
							<th>
								<div align="center">
									��������ϵ�绰
								</div>
							</th>

						</tr>
						<logic:notEmpty name="bzrList">
							<logic:iterate id="bzr" name="bzrList">
								<tr>
									<td align="center">
										${bzr.xm }
									</td>
									<td align="center">
										${bzr.zgh }
									</td>
									<td align="center">
										${bzr.lxdh }
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="bzrList">
							<tr>
								<td colspan="3" align="center">
									�������ݣ�
								</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
			
			
			</div>
			
			<div id="rcxwHidDiv" style="display: none;">
				<table style="margin-bottom: 5px" width="100%" border="0"
					class="formlist" id="tab_rcxw">
					<tbody id="hi_rcxw">
						<th width="12%">
							<div align="center">
								��Ϊ��¼ѧ��
							</div>
						</th>
						<logic:equal value="0" name="zq">
						<th width="12%">
							<div align="center">
								��Ϊ��¼ѧ��
							</div>
						</th>
						</logic:equal>
						<th width="22%">
							<div align="center">
								��Ϊ��¼����
							</div>
						</th>
						<th width="22%">
							<div align="center">
								��Ϊ��¼���
							</div>
						</th>
						<th width="20%">
							<div align="center">
								��Ϊ��¼ʱ��
							</div>
						</th>						
						<th width="12%">
							<div align="center">
								��Ϊ��¼��ֵ
							</div>
						</th>
						<logic:notEmpty name="rcswList">
							<logic:iterate id="rcxw" name="rcswList">
								<tr>
									<td align="center">
										${rcxw.xn }
									</td>
									<logic:equal value="0" name="zq">
									<td align="center">
										${rcxw.xqmc }
									</td>
									</logic:equal>
									<td align="center">
										${rcxw.rcxwlbdlmc}
									</td>
									<td align="center">
										${rcxw.rcxwlbmc}
									</td>
									<td align="center">
										${rcxw.rcxwjlsj }
									</td>
									<td align="center">
										${rcxw.fz }
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="rcswList">
							<tr>
								<td colspan="6" align="center">
									�������ݣ�
								</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
			</div>
			
	</body>
	<script type='text/javascript'>
		jQuery(function() {		
			parent.moreClick();
		});
	</script>
</html>

