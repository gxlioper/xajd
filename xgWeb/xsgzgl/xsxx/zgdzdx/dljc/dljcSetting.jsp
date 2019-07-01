<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʾ�ֶ�ѡ��Div
		function createZdszDiv(){
			
			var url="general_xsxx_dljc.do?method=createZdszDiv";
			
			//��������
		 	var parameter = {
				
			};
		  
			jQuery("#div_zdsz").load(url,parameter,function(){});
		}
		
		//���ȫ��
		function checkAll(num){
			var cb_name = "cb_"+num;
			
			var count = jQuery("input[name="+cb_name+"]").length;

			var cb_check = $("cb_all_"+num).checked;
			
			for(var i=0;i<count;i++){
				var obj = jQuery("input[name="+cb_name+"]")[i];
				obj.checked=cb_check;
			}
			
		}
		//�����ֶ�����
		function saveZdsz(tag){
		
			if(tag == "ok"){
				var i = 0;
				var zd = new Array();   				  
				jQuery("input[type=checkbox][value!=all]:checked").each(
					function(){zd[i] = escape(jQuery(this).val());i++;}
				);
				
				var url="general_xsxx_dljc.do?method=saveZdsz";
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//����
			 	var parameter = {
					"array_zd":zd.join("!!array!!")
				};
				
				jQuery.post(url,parameter,function(result){	
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					alertInfo(result);
				});
			}
		}
		</script>
	</head>
	<body onload="createZdszDiv()" ondrag="return false">	
		<html:form action="/xsxx_cssz" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			
			<!-- ά����Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th>
							<span>ѧ�����޸��ֶ�ѡ��</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="">	
							<div style="width:100%;height:420px;overflow-x:hidden;overflow-y:auto;" id="div_zdsz">
							
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>
							<div class="btn">							
								<button type="button" onclick="confirmInfo('ȷ�����Զ�����ȫУѧ����������Ϣ״̬Ϊ�������ٴ�ȷ������ѡ���ֶ�',saveZdsz);">
									�� ��
								</button>
								
								<button type="button" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ά����Ϣ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>

		</html:form>
	</body>
</html>