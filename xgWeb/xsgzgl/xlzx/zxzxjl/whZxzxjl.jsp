<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
<%--		<script type="text/javascript" src="xsgzgl/xlzx/zxzxjl/js/zxzxjl.js"></script>--%>
		<script type="text/javascript" src="xsgzgl/xlzx/zxzxjl/js/zxzxjlxx.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
			
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zxzx_zxzxjl" method="post" styleId="zxzxjlForm" onsubmit="return false;">
		<html:hidden property="xh" styleId="xh"/>
		<input type="hidden" name="userNameReal" id="userNameReal" value="${userNameReal }"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
						<tr>
								<th>
									ѧ��
								</th>
								<td>
									${jbxx.xh}
								</td>													
								<th>
									����
								</th>
								<td>
									${jbxx.xm}
								</td>
							</tr>						
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ
								<a onclick="showqtxx(this);" class="up"
											href="javascript:void(0);"> <font color="blue">���չ��/����</font>
										</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="qtxx" style="display: none">
						<tr>
							<th width="20%">
								�Ƿ������Ů
							</th>
							<td width="30%">
								${rs.sfdszn}
							</td>							
							<th>��ͥ���ڵ�</th>
							<td>
								${rs.jtszd}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ͥ����״��
							</th>
							<td width="30%">
								${rs.jtjjzk}
							</td>							
							<th>�����Ļ��̶�</th>
							<td>
								${rs.fqwhcd}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ĸ���Ļ��̶�
							</th>
							<td width="30%">
								${rs.mqwhcd}
							</td>
							<th>��ĸ�Ļ���״��</th>
							<td>
								${rs.fmhyzk}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ͥ��Ա���޾���ʷ
							</th>
							<td width="30%">
								${rs.jtjsbs}
							</td>					
							<th>�Լ�ͥ��ϲ���̶�</th>
							<td>
								${rs.jtxhcd}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�Ƿ���ܹ�������ѯ����������
							</th>
							<td width="30%">
								${rs.sfzl}
							</td>							
							<th>�Ǽ�����</th>
							<td>
								${rs.djrq}
							</td>
						</tr>
						<tr>
							<th>
								����ѯ����
							</th>
							<td colspan="3">
								${rs.yzxwt}
							</td>
			      		</tr>
			      		<tr>
						<th>
							��ѯ���ⲹ��
						</th>
						<td colspan="3">
							${rs.wtbc}					
						</td>
					</tr>
					<tr>
						<th>
							��ѯ��������Ԥ�ڽ��
						</th>
						<td colspan="3">
							${rs.zxqw}					
						</td>
					</tr>						
					</tbody>
				 </table>
				 <table id="zxjl" width="100%" border="0" class="formlist">				 
					 <thead>
							<tr>
								<th colspan="5">
									<span>��ѯ��¼<a onclick="addRow();" href="javascript:void(0);">
											<img src="images/knsrd/jiahao.gif" />
										</a></span>
								</th>
							</tr>
					</thead>
					<logic:present name="jlList">						
							<logic:iterate name="jlList" id="jl" indexId="s">
								<tbody id="t_${s}" name='xgTbody'>
									<input type="hidden" value="${jl.id}" />
									<tr>
										<th width="25%"><span><font color='red'>*</font></span>���</th>
										<td width="20%">
											<input type="text" id="bh_${s}" value="${jl.bh}" maxlength="10"/>
										</td>
										<th width="25%"><span><font color='red'>*</font></span>��ѯʦ����</th>
										<td width="20%">
											${jl.txrxm}
											<input type="hidden" id="zxsxm_${s}" value="${jl.zxsxm}" maxlength="10"/>
										</td>
										<th width="10%" style="text-align: center;">����</th>
									</tr>
									<tr>
										<th><span><font color='red'>*</font></span>��ѯʱ��</th>
										<td>
											<input type="text" id="zxsj_${s}" value="${jl.zxsj}" onfocus='showCalendar("zxsj_${s}","yyyy-MM-dd HH:mm");' maxlength="20"/>
										</td>
										<th><span><font color='red'>*</font></span>��ѯ�ص�</th>
										<td>
											<input type="text" id="zxdd_${s}" value="${jl.zxdd}" maxlength="20"/>
										</td>
										<td rowspan="5" style="text-align: center"><a href='javascript:void(0)' onclick='scXg("t_${s}")'>ɾ��</a></td>
									</tr>
									<tr>
										<th><span><font color='red'>*</font></span>�����߶�����ĳ�������ѯĿ�ļ���ѯʦ������ӡ��<br /><font color="red">&lt;��500��&gt;</th>
										<td colspan="3">
											<textarea rows='4' style='width: 99%' id="zxpg_${s}" onblur="checkLen(this,500);">${jl.zxpg}</textarea>
										</td>
									</tr>
									<tr>
										<th><span><font color='red'>*</font></span>��ѯ����<br /><font color="red">&lt;��2000��&gt;</th>
										<td colspan="3">
											<textarea rows='4' style='width: 99%' id="zxgc_${s}" onblur="checkLen(this,2000);">${jl.zxgc}</textarea>										
										</td>
									</tr>
									<tr>
										<th><span><font color='red'>*</font></span>��ѯ����<br /><font color="red">&lt;��500��&gt;</th>
										<td colspan="3">
											<textarea rows='4' style='width: 99%' id="zxfk_${s}" onblur="checkLen(this,500);">${jl.zxfk}</textarea>							
										</td>
									</tr>
									<tr>
										<th>��ѯ���<br /><font color="red">&lt;��500��&gt;</th>
										<td colspan="3">
											<textarea rows='4' style='width: 99%' id="zxth_${s}" onblur="checkLen(this,500);">${jl.zxth}</textarea>										
										</td>
									</tr>
								</tbody>
							</logic:iterate>					
					</logic:present>								
				 </table>
				</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="saveJlxx();">
										����
									</button>
									<button type="button" onclick="reSearch();">
										�ر�
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

