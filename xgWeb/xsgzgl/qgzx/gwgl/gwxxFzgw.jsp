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
		<script language="javascript">

		function checkFzInfo(str){
			var date = "true";
			var parameter={};
			var url="qgzx_gwgl_ajax.do?method=checkFzInfo&xn=";
			url+=$("xn").value.trim()
			parameter["pkValue"]=str;							
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
		//���� 
		//���Ʊ���
		function gwxxDivSave(){
			if($("xn").value.trim()==""){
				alertInfo("��ѡ��Ҫ���Ƶ�ѧ�꣡",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			var str = jQuery("#idList").val();
			var message = checkFzInfo(str);
			if(message!="true"){
				alertInfo(message,function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			var parameter={}
			var url="qgzx_gwgl_ajax.do?method=gwxxFz&doType=fz&xn=";
			url+=$("xn").value.trim()
			parameter["pkValue"]=str;							
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
					alertInfo(result,function(tag){
						if(tag=="ok"){
							refershParent();
						}
					});
				}
			);
			
		}

		function checkFzInfo(str){
			var date = "true";
			var parameter={};
			var url="qgzx_gwgl_ajax.do?method=checkFzInfo&xn=";
			url+=$("xn").value.trim()
			parameter["pkValue"]=str;							
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
		jQuery(function(){
			var num = jQuery("#num").val();
			var len = jQuery("#len").val();
			var str = jQuery("#str").val();
			var parameter={};
			url = "qgzx_gwgl_ajax.do?method=getXn";
			jQuery.getJSON(url,parameter,function(data){
				jQuery('#xn').empty();
				jQuery('#xn').append("<option value=''>--��ѡ��--</option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						if(str.indexOf(data[i].xn)==-1){
							var option = "<option value=\"" + data[i].xn + "\">" + data[i].xn + "</option>";
							jQuery('#xn').append(option);
						}
					}
				}
			});	

			jQuery('#yxgw').html("��ǰ��ѡ��<font class='red'>"+num+"</font>��ѧ���е�<font class='red'>"+len+"</font>����λ");	
			
		});
	
		</script>
	</head>
	<body >

		<html:form styleId="qgzxGwglForm" action="/qgzx_gwgl_ajax"  method="post">
			<input type="hidden" id="num" name="num" value="${num}" />
			<input type="hidden" id="len" name="len" value="${len}" />
			<input type="hidden" id="str" name="str" value="${str}" />
			<input type="hidden" id="idList" name="idList" value="${idList}" />
			
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
				<table align="center" class="formlist">
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
								<span class="red">*</span>Ŀ��ѧ��
							</th>
							<td width="60%">
								<html:select property="xn" styleId="xn" style="width:120px" >
								</html:select>
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
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
