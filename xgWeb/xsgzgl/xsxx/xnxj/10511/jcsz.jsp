<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript">
		
		function save(){
			var shlid = jQuery("#shlid").val();
			
			var shjg = jQuery('#shjg').val();

			var kgz = jQuery("input[name='kg']:checked").val();

			var url="xsxx_xnxj_jcszgl.do?method=cssz&type=save&spl="+shlid;
			
			//if(shjg > 0 && kgz == 'y'){
			//	showAlertDivLayer("��ѧ��ѧ��С�������������δ����ʱ�������޸�������̣�");
			//	return false;
			//}
			
			refreshForm(url);
		}
		
		
		function onShow() {
			var shjg = jQuery('#shjg').val();
			document.getElementById('shlc').style.display = "";
			var kg = document.getElementsByName("kg");
			for(var i=0;i<kg.length;i++){
				if(kg[i].checked&&kg[i].value=="n"){
					document.getElementById('shlc').style.display = "none";
				}
						
			}
		}
		jQuery(function(){
			onShow();
		})

		</script>
	</head>
	<body >
		<html:form action="/xsxx_xnxj_jcszgl" method="post">
		<input type="hidden" name="shjg" id="shjg" value="${rs.shjg }"/>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
				</p>
			</div>
			
			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						����ѧ��ѧ��С�Ὺ�أ�����ѧ���ύѧ��С�ᣬ����ѧ��ѧ��С�������������δ����ʱ�������޸��������
					</span>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->

			<div class="tab">
				<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<th>
								С��ѧ��
							</th>
							<td>
								<html:select property="xjxn" styleId="xjxn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
					<tr>
						<th style="width:40%">
								<font color="red">*</font>
								ѧ��С����д����
							</th>
							<td  style="width:60%">
								<html:radio property="kg" value="y" onclick="onShow()">����</html:radio>
								<html:radio property="kg" value="n" onclick="onShow()">�ر�</html:radio>
							</td>
							</tr>
							<tr id="shlc" >
							<th style="width:40%">
								<font color="red">*</font>
								�������
							</th>
								<logic:present name="rs">
<%--								<logic:equal name="rs" property="shjg" value="0">--%>
									<td  style="width:60%">
										<html:select property="spl" styleId="shlid"  style="width:240px">
											<html:option value="wxsh">�������</html:option>
											<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
										</html:select>
									</td>
<%--							</logic:equal>--%>
<%--							<logic:notEqual value="0" name="rs" property="shjg">--%>
<%--								<td  style="width:60%" title="����ѧ��ѧ��С�����������δ���������ܽ����޸ģ�">								--%>
<%--									<html:select property="spl" styleId="shlid"  style="width:240px" disabled="true">--%>
<%--										<html:option value="wxsh">�������</html:option>--%>
<%--										<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>--%>
<%--									</html:select>--%>
<%--								</td>--%>
<%--							</logic:notEqual>--%>
							</logic:present>
							<logic:notPresent name="rs">
								<td  style="width:60%">
									<html:select property="spl" styleId="shlid" style="width:240px">
										<html:option value="wxsh">�������</html:option>
										<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
									</html:select>
								</td>
							</logic:notPresent>
						</tr>
						
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									��"<font color="red">*</font>"Ϊ������
								</div>
								<div class="btn">
								<logic:equal name="writeAble" value="yes">
									<button type="button" class="button2" id="btn_bc"  onclick="save();return false;">
										�� ��
									</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ�")
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���")
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
