<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" defer="defer">

		jQuery(function(){
			jQuery('input[type="radio"]').click(function(){
				calc();
			});;
			calc();
		});


		//��ֵ����
		function calc(){
			var wt1qk = jQuery("input[name='wt1qk']:checked").val();	
			var wt2qk = jQuery("input[name='wt2qk']:checked").val();
			var wt3qk = jQuery("input[name='wt3qk']:checked").val();
			var wt4qk = jQuery("input[name='wt4qk']:checked").val();
			var wt5qk = jQuery("input[name='wt5qk']:checked").val();
			jQuery('#zf').val((wt1qk?new Number(wt1qk):0) + 
								(wt2qk?new Number(wt2qk):0) + 
								(wt3qk?new Number(wt3qk):0) + 
								(wt4qk?new Number(wt4qk):0) + 
								(wt5qk?new Number(wt5qk):0));
		}
		
		function addAction() {
			var xh = jQuery('#xh').val();
			if (xh == "") {
				showAlert("��ѡ��һ��ѧ����");
				return false;
		}

		if (jQuery("input[name='wt1qk']:checked").length != 1
				|| jQuery("input[name='wt2qk']:checked").length != 1
				|| jQuery("input[name='wt3qk']:checked").length != 1
				|| jQuery("input[name='wt4qk']:checked").length != 1
				|| jQuery("input[name='wt5qk']:checked").length != 1) {
			showAlert("�뽫��'*'����Ŀ��д����!");
			return false;
		}

		var url = "xljk_xlwjyjgl_wgwtwh.do?method=xzAction";
		ajaxSubFormWithFun("wgwtwhForm", url, function(data) {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		});
	}
	
	/**
	 * ������Ϣ
	 * @param obj
	 * @return
	 */
	function showXx(obj){
		var className = jQuery(obj).attr("class");
		var newClass = className == "up" ? "down" : "up";

		jQuery(obj).attr("class",newClass);
		jQuery("#t_xx").toggle();
	}
</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none"
			onclick=
	reloadWindow();;
></button>
		<html:form action="/xljk_xlwjyjgl_wgwtwh" method="post"
			styleId="wgwtwhForm">
			<div
				style='width: 100%; height: 470px; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="6">
								<span>����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="100%">
									<tr>
										<th>
											<span class="red">*</span>������ɱ�����˼ƻ�
										</th>
										<td  width="70px">
											<html:radio property="wt1qk" value="0" >��</html:radio>
										</td>
										<td  width="70px">
											<html:radio property="wt1qk" value="1" >�У��ͣ�</html:radio>
										</td>
										<td  width="70px">
											<html:radio property="wt1qk" value="2" >�У��ߣ�</html:radio>
										</td>
											<th>
											��ע
										</th>
										<td>
											<html:text styleId="wt1bz" property="wt1bz"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<span class="red">*</span>�������������ɱ�����˾���
										</th>
										<td>
											<html:radio property="wt2qk" value="0">��</html:radio>
										</td>
										<td>
											<html:radio property="wt2qk" value="1">�У��ͣ�</html:radio>
										</td>
										<td>
											<html:radio property="wt2qk" value="2">�У��ߣ�</html:radio>
										</td>
										<th>
											��ע
										</th>
										<td>
											<html:text styleId="wt2bz" property="wt2bz"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<span class="red">*</span>����Ŀǰ��ʵѹ��
										</th>
										<td>
											<html:radio property="wt3qk" value="0">��</html:radio>
										</td>
										<td>
											<html:radio property="wt3qk" value="1">�У��ͣ�</html:radio>
										</td>
										<td>
											<html:radio property="wt3qk" value="2">�У��ߣ�</html:radio>
										</td>
										<th>
											��ע
										</th>
										<td>
											<html:text styleId="wt3bz" property="wt3bz"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<span class="red">*</span>����Ŀǰ֧����Դ
										</th>
										<td>
											<html:radio property="wt4qk" value="2">��</html:radio>
										</td>
										<td>
											<html:radio property="wt4qk" value="1">�У��ͣ�</html:radio>
										</td>
										<td>
											<html:radio property="wt4qk" value="0">�У��ߣ�</html:radio>
										</td>
										<th>
											��ע
										</th>
										<td>
											<html:text styleId="wt4bz" property="wt4bz"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<span class="red">*</span>�ٴ�֢״
										</th>
										<td>
											<html:radio property="wt5qk" value="0">��</html:radio>
										</td>
										<td>
											<html:radio property="wt5qk" value="1">�У��ͣ�</html:radio>
										</td>
										<td>
											<html:radio property="wt5qk" value="2">�У��ߣ�</html:radio>
										</td>
										<th>
											��ע
										</th>
										<td>
											<html:text styleId="wt5bz" property="wt5bz"></html:text>
										</td>
									</tr>
									<tr>
										<th style="font-weight: bold;color:red">
											�ܷ�
										</th>
										<td colspan="5">
											<html:text styleId="zf" property="zf" readonly="true"  style="font-weight:bold;color:red;border-width:0"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											�ܷ�˵��
										</th>
										<td colspan="5">
											2��-Ǳ��Σ�� 4��-���Σ�� 6��-�ж�Σ�� 8��-�߶�Σ�� 10��-��Σ��
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="6">
								<span>������ϸ˵��
								<a onclick="showXx(this);" class="down" href="javascript:void(0);">
										   <font color="blue">���չ��/����</font>	
										</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_xx">
						<tr>
							<td colspan="6">
								<table width="100%">
										<tr>
											<th width="231px" style="font-weight: bold;">��ɱ���������б�</th>
											<th style="font-weight: bold;">��</th>
											<th style="font-weight: bold;">�У��ͣ�</th>
											<th style="font-weight: bold;">�У��ߣ�</th>
										</tr>
										<tr>
											<th >������ɱ�����˼ƻ�</th>
											<th>0</th>
											<th>1</th>
											<th>2</th>
										</tr>
										<tr>
											<th>�������������ɱ�����˾���</th>
											<th>0</th>
											<th>1</th>
											<th>2</th>
										</tr>
										<tr>
											<th>����Ŀǰ��ʵѹ��</th>
											<th>0</th>
											<th>1</th>
											<th>2</th>
										</tr>
										<tr>
											<th>����Ŀǰ֧����Դ</th>
											<th>2</th>
											<th>1</th>
											<th>0</th>
										</tr>
										<tr>
											<th>�ٴ�֢״</th>
											<th>0</th>
											<th>1</th>
											<th>2</th>
										</tr>
										<tr>
											<td colspan="6">
								ע�����У��ͣ������У��ߣ����Ľ綨��<br/>
								������ɱ�����˼ƻ���<br/>
								�У��ͣ�����ż���й���ɱ���뷨�ƻ����Ҽƻ���ģ���ģ���1�֣�<br/>
								�У��ߣ�������������ɱ���뷨�ƻ�������ż���мƻ����ƻ���ϸ�ɲ����Ըߣ���2�֡�<br/>
								����������ɱ������<br/>
								�У��ͣ����������й��ͷ��յ���ɱ��������1�֣�<br/>
								�У��ߣ����������й������ɱ���������й��߷��յ���ɱ��������2�֡�<br/>
								��ʵѹ������ʵѹ���ĸߵ�Ӧ�������ߵ���������������<br/>
								֧�ֵ���Դ��<br/>
								�У��ͣ�������һ�������֧�֣������Ա����ã���1�֣�<br/>
								�У��ߣ����������õ����֧�֣����ܱ����ã���0�֣�<br/>
								�ٴ�֢״��<br/>
								�У��ͣ���������һ��������������⣬��1�֣�<br/>
								�У��ߣ�����������֢�����Ծ��񲡣���2�֡�<br/>
											</td>
										</tr>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="save_button" type="button" onclick=
	addAction();;
>
										����
									</button>
									<button type="button" name="�� ��" onclick=
	iFClose();;
>
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

