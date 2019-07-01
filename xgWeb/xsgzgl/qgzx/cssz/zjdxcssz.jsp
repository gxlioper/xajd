<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript">
		function changKssj(value){
			if(value==""){
				$("kssj").focus();
				jQuery("span[id=cjffError]").text("��𷢷ſ���ʱ�䲻��Ϊ��!");
		 		return false;
			}
			if(Number(value)>Number(jQuery("#jssj").val())){
				$("kssj").focus();
				jQuery("span[id=cjffError]").text("ʱ�����䲻��ȷ!");
				return false;
			}else{
				jQuery("span[id=cjffError]").text("");
			}
		}
		
		function changJssj(value){
			if(value==""){
				$("jssj").focus();
				jQuery("span[id=cjffError]").text("��𷢷Ž���ʱ�䲻��Ϊ��!");
		 		return false;
			}
			if(Number(value)<Number(jQuery("#kssj").val())){
				$("jssj").focus();
				jQuery("span[id=cjffError]").text("ʱ�����䲻��ȷ!");
		 		return false;
			}else{
				jQuery("span[id=cjffError]").text("");
			}
		}

		function SaveXqgzx(){
			var cjbz = jQuery("#cjbz").val();
			var sxsz = jQuery("#sxsz").val();
			var ksyf = jQuery("#ksyf").val();
			var jsyf = jQuery("#jsyf").val();
			var sfyxcgcjsx = jQuery("input[name='sfyxcgcjsx']:checked").val();
			if(cjbz == "" || cjbz == null|| cjbz<=0){
				showAlertDivLayer("����׼����Ϊ��Ҳ����Ϊ0!");
				return false;
			}
			if(sxsz == "" || sxsz == null|| sxsz<=0){
				showAlertDivLayer("ѧ��ÿ����߱��겻��Ϊ��Ҳ����Ϊ0!");
				return false;
			}
			if((ksyf == "" || ksyf == null) && (jsyf == "" || jsyf == null)){
				showAlertDivLayer("������дһ����𷢷��·�");
				return false;
			}
			if(Number($("kssj").value) > Number($("jssj").value)){
		 		alertInfo("��𷢷ſ�ʼʱ�䲻�ܴ��ڽ���ʱ��!");
		 		return false;
			}
			if("" == sfyxcgcjsx || null == sfyxcgcjsx){
				showAlertDivLayer("��ѡ���Ƿ�������������ޣ�");
				return false;
			}
			var url = "qgzx_jcsz.do?method=saveZjdxqgzx";
			ajaxSubFormWithFun("qgzxCsszForm",url,function(data){
				showAlertDivLayer(data["message"]);
			});
		}
		/*���أ�Ϊ���Ƿ�������������ޡ���ֵ*/
		jQuery(function(){
			jQuery("input[name='sfyxcgcjsx'][value='${sfyxcgcjsx}']").attr("checked",true); 
		})
	</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					1����𷢷�ʱ���ֹ��ϵͳ��<font color="blue">�Զ��ύ</font>�����Ϣ���ύ�󣨳��ڹ�����Ա�û���<font color="blue">�����ٴ��޸�</font>�����Ϣ��
				</br>2����𷢷ſ���ʱ��Ĭ��Ϊ��ʼ���ڵ�00:00:00������ʱ���23:59:59��
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/qgzx_jcsz" method="post" styleId="qgzxCsszForm">
		<input type="hidden" name="sxzd" id="sxzd" value="je"/>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<div class="tab">
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>��𷢷Ų�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>����׼
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="cjbz" styleId="cjbz"  size="10" maxlength="5" onkeyup="checkInput(this)"></html:text>
								Ԫ/Сʱ��Ĭ�ϰ��˱�׼������
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>ѧ��ÿ����߱���
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="sxsz" styleId="sxsz"  size="10" maxlength="6" onkeyup="checkInput(this)"></html:text>
								<font id="font_sxsz">Ԫ��ѧ��ÿ�³�𲻵ó�����ֵ��</font>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>��𷢷��·��趨
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="ksyf" styleId="ksyf"   size="10"
									onclick="return showCalendar('ksyf','yyyyMM');" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>��𷢷ſ���ʱ��
							</td>
							<td align="left" style="width: 60%">
								<html:select name="rs" property="kssj" styleId="kssj" style="width:50px" onchange="changKssj(this.value);">
									<html:option value="1">1</html:option><html:option value="2">2</html:option>
									<html:option value="3">3</html:option><html:option value="4">4</html:option>
									<html:option value="5">5</html:option><html:option value="6">6</html:option>
									<html:option value="7">7</html:option><html:option value="8">8</html:option>
									<html:option value="9">9</html:option><html:option value="10">10</html:option>
									<html:option value="11">11</html:option><html:option value="12">12</html:option>
									<html:option value="13">13</html:option><html:option value="14">14</html:option>
									<html:option value="15">15</html:option><html:option value="16">16</html:option>
									<html:option value="17">17</html:option><html:option value="18">18</html:option>
									<html:option value="19">19</html:option><html:option value="20">20</html:option>
									<html:option value="21">21</html:option><html:option value="22">22</html:option>
									<html:option value="23">23</html:option><html:option value="24">24</html:option>
									<html:option value="25">25</html:option><html:option value="26">26</html:option>
									<html:option value="27">27</html:option><html:option value="28">28</html:option>
									<html:option value="29">29</html:option><html:option value="30">30</html:option>
									<html:option value="31">31</html:option>
								</html:select>��
								-
								<html:select name="rs" property="jssj" styleId="jssj" style="width:50px" onchange="changJssj(this.value);">
									<html:option value="1">1</html:option><html:option value="2">2</html:option>
									<html:option value="3">3</html:option><html:option value="4">4</html:option>
									<html:option value="5">5</html:option><html:option value="6">6</html:option>
									<html:option value="7">7</html:option><html:option value="8">8</html:option>
									<html:option value="9">9</html:option><html:option value="10">10</html:option>
									<html:option value="11">11</html:option><html:option value="12">12</html:option>
									<html:option value="13">13</html:option><html:option value="14">14</html:option>
									<html:option value="15">15</html:option><html:option value="16">16</html:option>
									<html:option value="17">17</html:option><html:option value="18">18</html:option>
									<html:option value="19">19</html:option><html:option value="20">20</html:option>
									<html:option value="21">21</html:option><html:option value="22">22</html:option>
									<html:option value="23">23</html:option><html:option value="24">24</html:option>
									<html:option value="25">25</html:option><html:option value="26">26</html:option>
									<html:option value="27">27</html:option><html:option value="28">28</html:option>
									<html:option value="29">29</html:option><html:option value="30">30</html:option>
									<html:option value="31">31</html:option>
								</html:select>��
									(ֻ���ڳ�𷢷�ʱ����ڲ��ܷ��ų��)  <span id ="cjffError" style="color:red"></span>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>�Ƿ��������������
							</td>
							<td>
								<input type="radio" name="sfyxcgcjsx" property="sfyxcgcjsx" value="��"/>��
								<input type="radio" name="sfyxcgcjsx" property="sfyxcgcjsx" value="��"/>��
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="2">
			        			<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
								<logic:equal name="writeAble" value="yes">
									<button type="button" onclick="SaveXqgzx();return false;" id="buttonSave">
										�� ��
									</button>
								</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>