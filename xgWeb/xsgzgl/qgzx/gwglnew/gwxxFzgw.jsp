<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglnewcomm.js"></script>
		<script language="javascript">
		function checkFzInfo(str){
			var date = "true";
			var parameter={};
			var url="qgzx_gwglnew_ajax.do?method=checkFzInfo&xn=";
			url+=$("xn").value.trim()
			parameter["pkValue"]=str;	
			parameter["xq"]=jQuery("#xq").val();
			var yxssz = jQuery("[name=yxssz]:checked").val();
			if(yxssz=='cq'){
				parameter["gwjssj"]="";
			}else{
				parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
			}
			parameter["yxssz"]=yxssz;
			parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
					data = result;
				}
			);
			jQuery.ajaxSetup({async:true});
			return data;
		}

		//���Ʊ���
		function gwxxDivSave(){
			if($("xn").value.trim()==""){
				showAlert("��ѡ����Ҫ���Ƶ�ѧ�꣡");
				return false;
			}
			var yxsszLen = jQuery("[name=yxssz]:checked").length;
			if(yxsszLen==0){
				showAlert("��ѡ����Чʱ���ã�");
				return false;
			}
			if($("gwkssj").value==""){
				showAlert("��λ��ʼʱ�䲻��Ϊ�գ�");
		 		return false;
			}
			var yxssz = jQuery("[name=yxssz]:checked").val();
			if(yxssz=='xs' && $("gwjssj").value==""){
				showAlert("��λ����ʱ�䲻��Ϊ�գ�");
				return false;
			}
			var str = jQuery("#idList").val();
			var message = checkFzInfo(str);
			if(message!="true"){
				showAlert(message);
				return false;
			}
			var parameter={}
			var url="qgzx_gwglnew_ajax.do?method=gwxxFz&doType=fz&xn=";
			url+=$("xn").value.trim()
			parameter["pkValue"]=str;
			parameter["xq"]=jQuery("#xq").val();
			var yxssz = jQuery("[name=yxssz]:checked").val();
			if(yxssz=='cq'){
				parameter["gwjssj"]="";
			}else{
				parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
			}
			parameter["yxssz"]=yxssz;
			parameter["gwkssj"]=escape(jQuery("#gwkssj").val());							
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
					showAlert(result,{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				}
			);
			
		}

		jQuery(function(){
			var num = jQuery("#num").val();
			var len = jQuery("#len").val();
			var str = jQuery("#str").val();

			var strName = jQuery("#qgzq").val() == "xq" ? "ѧ��" : "ѧ��";
			jQuery('#yxgw').html("��ǰ��ѡ��<font class='red'>"+num+"</font>��"+strName+"�е�<font class='red'>"+len+"</font>����λ");	
			changeYxssz();
			
		});
	
		</script>
	</head>
	<body >

		<html:form styleId="qgzxGwglForm" action="/qgzx_gwglnew_ajax"  method="post">
			<input type="hidden" id="num" name="num" value="${num}" />
			<input type="hidden" id="len" name="len" value="${len}" />
			<input type="hidden" id="str" name="str" value="${str}" />
			<input type="hidden" id="idList" name="idList" value="${idList}" />
			<input type="hidden" id="qgzq" name="qgzq" value="${cs.qgzq}" />
			
			
				<!-- ��ʾ��Ϣ -->
				<div class="prompt" id="promptTs">
					<h3>
						<span>��ʾ��</span>
					</h3>
					<p>
						���ܽ�ѡ�и�λ���Ƶ��Ǹ�λ����ѧ����
					</p>
					<a class="close" title="����"
					   onclick="this.parentNode.style.display='none'"></a>
				</div>
				<!-- ��ʾ��Ϣ end-->	
				<div style="tab;overflow-x:hidden;overflow-y:auto;height:315px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>���Ƹ�λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="40%">
								��ѡ��λ
							</th>
							<td width="60%">
								<font id="yxgw"></font>
							</td>
						</tr>
						<tr>
							<th width="40%">
								<span class="red">*</span>
								<logic:equal name="cs" property="qgzq" value="xn">
									Ŀ��ѧ��
								</logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									Ŀ��ѧ��ѧ��
								</logic:equal>
							</th>
							<td width="60%">
								<logic:equal name="cs" property="qgzq" value="xn">
									<html:select property="xn" styleId="xn" style="width:120px" >
										<html:options labelProperty="xn" property="xn" collection="xnList"/>
									</html:select>
									<input type="hidden" id="xq" name="xq" value="" />
								</logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									<html:select property="xn" styleId="xn" style="width:100px" >
										<html:options labelProperty="xn" property="xn" collection="xnList"/>
									</html:select>
									<html:select property="xq" styleId="xq" style="width:100px" >
										<html:option value=""></html:option>
										<html:options labelProperty="xqmc" property="xqdm" collection="xqList"/>
									</html:select>
								</logic:equal>
							</td>
						</tr>
						
						<tr>
							<th><span class="red">*</span>��Чʱ����</th>
							<td>
							   <logic:present name="yxsszList">
									<logic:iterate id="yxsszMap" name="yxsszList" >
										<html:radio property="yxssz" onclick="changeYxssz();" value="${yxsszMap.dm}">${yxsszMap.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>��λ��ʼʱ��</th>
							<td>
								<html:text  property="gwkssj" styleId="gwkssj" size="10" readonly="true"></html:text>
							</td>
						</tr>
						<tr id="gwjssj_tr">
							<th id="gwjssj_th"></th>
							<td id="gwjssj_td">
								<html:text  property="gwjssj" styleId="gwjssj" size="10" readonly="true"></html:text>
							</td>
						</tr>
					</tbody>
					</table>
				</div>
				<div>
					<table class="formlist">
						<tfoot>
							<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="����" onclick="gwxxDivSave();">
										�� ��
									</button>
									<button type="button" name="�ر�" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>				
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
