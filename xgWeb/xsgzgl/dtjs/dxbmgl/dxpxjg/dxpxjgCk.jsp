<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" href="xsgzgl/dtjs/dtxxglnew/color/dtxxglnew.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/dtxxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				autoChange();
				var zpcj=jQuery("#zpcj").val();
				if(zpcj==null||zpcj==''){
					var kqcjbfb=jQuery('#kqcjbfb').val()/100;
					var sjcjbfb=jQuery('#sjcjbfb').val()/100;
					var bjcjbfb=jQuery('#bjcjbfb').val()/100;
					var kscjbfb=jQuery('#kscjbfb').val()/100;
					var kqcj=jQuery('#kqcj').val()==''?0:jQuery('#kqcj').val();
					var sjcj=jQuery('#sjcj').val()==''?0:jQuery('#sjcj').val();
					var bjcj=jQuery('#bjcj').val()==''?0:jQuery('#bjcj').val();
					var kscj=jQuery('#kscj').val()==''?0:jQuery('#kscj').val();
					jQuery('#xszpcj').html(kqcj*kqcjbfb+sjcj*sjcjbfb+bjcj*bjcjbfb+kscj*kscjbfb);
				}
			});
		</script>
	</head>
	<body>
	<html:form method="post" styleId="form" action="dxbmgl_dxpxjg.do">
		<html:hidden property="xh" styleId="xh"/>
		 <!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						<img src="xsgzgl/dtjs/dtxxglnew/color/sys.png" alt="����ɫ" style="height: 100%;"/> &nbsp;����ɫΪ���е��Ž׶Σ�
						<img src="xsgzgl/dtjs/dtxxglnew/color/qys.png" alt="ǳ��ɫ" /> &nbsp;ǳ��ɫΪ��δ����ĵ��Ž׶Ρ�
					</span>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
		 <!-- ��ʾ��Ϣ end-->		
		<div style="tab;width:100%;height:375px;overflow-x:hidden;overflow-y:auto;top: 22px;">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>ѧ��</th>
						<td>${dxpxjgForm.xh }</td>
						<th>����</th>
						<td>${dxpxjgForm.xm }</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>${dxpxjgForm.xb }</td>
						<th>���֤��</th>
						<td>${dxpxjgForm.sfzh }</td>
					</tr>
					<tr>
						<th>�꼶</th>
						<td>${dxpxjgForm.nj }</td>
						<th>ѧԺ</th>
						<td>${dxpxjgForm.xymc }</td>
					</tr>
					<tr>
						<th>רҵ</th>
						<td>${dxpxjgForm.zymc }</td>
						<th>�༶</th>
						<td>${dxpxjgForm.bjmc }</td>
					</tr>
				</tbody>
			</table>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѵ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��ѵ�ڴ�</th>
							<td>${dxpxjgForm.pxqc }</td>
							<th>��ѵʱ��</th>
							<td>${dxpxjgForm.pxsj }</td>
						</tr>
						<tr>
							<th>���ڳɼ�<input type="hidden" id="kqcjbfb" value="${dxpxjgForm.kqcjbfb }"/>
							<input type="kqcj" id="kqcj" value="${dxpxjgForm.kqcj }"/></th>
							<td>${dxpxjgForm.kqcj }</td>
							<th>ʵ���ɼ�<input type="hidden" id="sjcjbfb" value="${dxpxjgForm.sjcjbfb }"/>
							<input type="sjcj" id="sjcj" value="${dxpxjgForm.sjcj }"/></th>
							<td>${dxpxjgForm.sjcj }</td>
						</tr>
						<tr>
							<th>�ʼǳɼ�<input type="hidden" id="bjcjbfb" value="${dxpxjgForm.bjcjbfb }"/>
							<input type="bjcj" id="bjcj" value="${dxpxjgForm.bjcj }"/></th>
							<td>${dxpxjgForm.bjcj }</td>
						</tr>
						<tr>
							<th>���Գɼ�<input type="hidden" id="kscjbfb" value="${dxpxjgForm.kscjbfb }"/>
							<input type="kscj" id="kscj" value="${dxpxjgForm.kscj }"/></th>
							<td>${dxpxjgForm.kscj }</td>
							<th>�����ɼ�<input type="zpcj" id="zpcj" value="${dxpxjgForm.zpcj }"/></th>
							<td id="xszpcj">${dxpxjgForm.zpcj }</td>
						</tr>
						<tr>
							<th>���۽��</th>
							<td>${dxpxjgForm.pjjg }</td>
						</tr>
					</tbody>
				</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
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
