<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.gygl.cssz.CsszForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			function disp(val) {
				if (val=='��' || val=='1') {
					document.getElementById("tmp1").style.display='';
					document.getElementById("tmp2").style.display='';
					document.getElementById("s_ks").style.display = '';
					document.getElementById("s_js").style.display = '';
				} else {
					document.getElementById("tmp1").style.display='none';
					document.getElementById("tmp2").style.display='none';
					//document.getElementById("hjkssj").value = '';
					//document.getElementById("hjjssj").value = '';
					document.getElementById("s_ks").style.display = 'none';
					document.getElementById("s_js").style.display = 'none';
				}
			}
			//�ύ����
			function subm() {
				var array = document.getElementsByName("kg");
				var ks = document.getElementById("hjkssj").value;
				var js = document.getElementById("hjjssj").value;
				var flag = 0;
				for (var i=0;i<array.length;i++) {
					if (array[i].value=='1' && array[i].checked==true) {
						if (ks ==null || ks=='' ||js==null||js=='') {
							alert("��ʼʱ�䣬����ʱ��Ϊ���");
							return false;
						}
						ks = ks.replace("-","").replace("-","");
						ks = ks.replace(":","").replace(":","");
						ks = ks.replace(" ","");
						
						js = js.replace("-","").replace("-","");
						js = js.replace(":","").replace(":","");
						js = js.replace(" ","");

				if (parseFloat(js) <= parseFloat(ks) ) {
					alert("��ʼʱ��������ڽ���ʱ�䣡");
					return false;
				}
					}
				}
				
						
				refreshForm('rcsw_nthy_xfhjsjkz.do?type=save');
			}
		</script>
	</head>
	<body onload="disp('${rs.kg }')">
		<html:form action="/rcsw_nthy_xfhjgl" method="post">

			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>			
			<!-- ���� end-->
			<!-- ��ʾ��Ϣ START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					1.���뿪�ش򿪺󣬲��ܶ�ʱ��������á�<br/>
					2.��ǰʱ��λ�����뿪ʼʱ�����������ʱ���ʱ��ѧ���ſ��Խ���ѧ�ѻ������룡
				</p>
				<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
			</div>

				<div class="open_win01">
					<table align="center" class="formlist">
						<tbody>
						<tr>
								<th width="50%">
									<span class="red">*</span>���뿪��
								</th>
								<td  width="50%">
									<html:radio name="rs" property="kg" value="1" onclick="disp('��')"/>��
									<html:radio name="rs" property="kg" value="0" onclick="disp('��')"/>��
								</td>
							</tr>
							<tr id="tmp1">
								<th>
									<span class="red" id="s_ks">*</span>���뿪ʼʱ��
								</th>
								<td>
									<input type="text" id="kssj" name="hjkssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" value="${rs.hjkssj  }"/>
								</td>
							</tr>
							<tr id="tmp2">
								<th>
									<span class="red" id="s_js">*</span>���뿪ʼʱ��
								</th>
								<td>
									<input type="text" id="jssj" name="hjjssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" value="${rs.hjjssj }"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="subm()">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alert($('message').value);
			</script>
		</logic:present>
	</body>
</html>
