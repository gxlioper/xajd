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
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xsxxglCk.js"></script>
		<script type="text/javascript" src="js/extend/zfsoft-dataExtend.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
   		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			//���ø��� 
			function getfj(){
				jQuery('#fjid').val(jQuery('#fj').val());
				var gid = jQuery('#fjid').val();
				jQuery.MultiUploader_q({
					gid : gid
					});
			}
		</script>
	</head>
	<script type="text/javascript">
	</script>
	<body >
	<html:form action="/xsxx_xsxxgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfxsgban" id="sfxsgban" value="${sfxsgban} "/>
			<input type="hidden" name="jxjs" id="jxjs" value="${jxhj.js }" />
			<input type="hidden" name="jxzje" id="jxzje" value="${jxhj.zje }" />
			<input type="hidden" name="pjzje" id="pjzje" value="${pjzje.pjzje }" />
			<input type="hidden" name="xh" id="xh" value='${xh}'/>
			<input type="hidden" name="zq" id="zq" value='${zq}'/>
			<input type="hidden" name="fj" id="fj" value="${rs.zd6 }" />
			<input type="hidden" name="mklb" id="mklb" value='${mklb}'/>
			<input type="hidden" name="kzxxid" id="kzxxid" value='${kzxxModel.jgid}'/>
			<input type="hidden" name="zzxmzje" id="zzxmzje" value='${zzxmxx.zje}'/>
			<input type="hidden" name="zzxmsl" id="zzxmsl" value='${zzxmxx.sl}'/>
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
			<div id="zdybdfl_xsxx_query_wdwj" style="display: none;">
				<h3 class="college_title" style="margin-bottom: 5px;">
					<span class="title_name">�ҵ��ʾ�</span>
				</h3>
				<div id="zdybdfl_xsxx_query_wdwj_wdwj">
					<table width="100%" border="0" style="margin-bottom: 5px"
						class="formlist">
						<thead>
							<tr style="cursor: hand;">
								<th colspan="5">
									<span>�ҵ��ʾ�</span>
								</th>
							</tr>
						</thead>
					</table>
					<table width="100%" border="0" style="margin-bottom: 20px"
						class="formlist">
						<tbody>
							<tr>
								<td colspan="6" id="wdwjcx"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</html:form>
				<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button name="�ر�" onclick="Close()" type="button"
										id="buttonClose">
										�� ��
									</button>
									<span id="dySpan" style="display: none;" >
										<button type="button" name="btn_dy" onclick="printZxsxx();return false;" id="btn_dy">ѧ���ǼǱ��ӡ</button>					           
									</span>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>			
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
				<logic:equal value="1" name="mklb">
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
								��Ϊ��¼���
							</div>
						</th>
						<th width="12%">
							<div align="center">
								��Ϊ��¼�ܷ�
							</div>
						</th>
						<th width="12%">
							<div align="center">
								ѧ��
							</div>
						</th>
						<logic:notEmpty name="rcswList">
							<logic:iterate id="v" name="rcswList">
								<tr>
									<td align="center">
										${v.rdnd }
									</td>
									<logic:equal value="0" name="zq">
									<td align="center">
										${v.xqmc }
									</td>
									</logic:equal>
									<td align="center">
									${v.rcxwlbmc}<a data="${v.xh}_${v.xqdm}_${v.rdnd}_${v.rcxwlbdm}" onclick = "showInfo(this);" class="up" href="javascript:void(0);" value="${v.xh}-${v.xqdm}-${v.rdnd}-${v.rcxwlbdm}"/>
									</td>
									<td align="center">
										${v.fz}
									</td>
									<td align="center">
										${v.xf }
									</td>
								</tr>
								<tr>
									<td colspan="5" data="${v.xh}_${v.xqdm}_${v.rdnd}_${v.rcxwlbdm}" type="detail-data" style="display: none;" >
											<table class="formList" width="100%" id="tab_rcxw">
												<thead>
													<tr align="left">
														<td align="center" width="32%">
															��Ϊ��¼����
														</td>
														<td align="center" width="15%">
															��Ϊ��¼С��
														</td>
														<td align="center" width="20%">
															��Ϊ��¼����ʱ��
														</td>
														<td align="center" width="13%">
															��Ϊ��¼��ֵ
														</td>
													</tr>
												</thead>
												<tbody class="tbody_rcxw">
												</tbody>
											</table>
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
				</logic:equal>
				<logic:notEqual value="1" name="mklb">
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
					</logic:notEqual>
				</table>
			</div>
		</body>
</html>

