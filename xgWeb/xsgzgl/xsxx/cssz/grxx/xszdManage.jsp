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
		function showZdxzDiv(){
			
			var url="xsxx_cssz_grxx_method.do?method=showZdxzDiv";
			
			//��������
		 	var parameter = {
				"yhlx":"xsqx"
			};
		  
			jQuery("#div_zdxz").load(url,parameter,function(){});
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
		
		//ǰһ��
		function preStep(tag){
			if(tag == "ok"){
				saveZdqx("pre");
			}
		}
		
		//��һ��
		function nextStep(tag){
			if(tag == "ok"){
				saveZdqx("next");
			}
		}
		
		function saveZdqx(step){
		
			var i = 0;
			var zd = new Array();   				  
			jQuery("input[type=checkbox][value!=all]:checked").each(
				function(){zd[i] = escape(jQuery(this).val());i++;}
			);
			
			var url="xsxx_cssz_grxx_method.do?method=saveZdqx";
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
				
			var over = "no";
			if("next" == step){
				over = "yes";
			}
			
			//����
		 	var parameter = {
				"yhlx":"xsqx",
				"zd":zd.join("!!@@!!"),
				"over":over
			};
			
			jQuery.post(url,parameter,function(result){
			
				//$("divWaiting").style.display="none";
				//$("divDisable").style.display="none";
				
				if("pre" == step){
					refreshForm("xsxx_cssz_grxx.do");
				}else if("next" == step){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					alertInfo(result);
					//refreshForm("xsxx_cssz_grxx_method.do?method=lszdManage");
				}
			});
		}
		</script>
	</head>
	<body onload="showZdxzDiv()" >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
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
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
				<span id="div_help" style="display: none">
				1.�������г����ֶ�ΪѧУѧ����Ϣ�������ֶΣ������������ѧУ��ʵ���������<font color="blue">��ϵ�����Ա</font>��</br>
				2.��ѡ����ֶκ���<font color="blue">����</font>�󣬱���������ɡ�</br>
				3.ֻ�б���ѡ���ֶΣ�ѧ���û�����<font color="blue">Ȩ��</font>�����޸ġ�</br>
				4.����������<font color="blue">��һ��</font>����ôϵͳ����Ϊ����δ������ɣ�����Ҫ�ٲ���һ��<font color="blue">����</font>��ť��</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
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
							<div style="width:100%;height:420px;overflow-x:hidden;overflow-y:auto;" id="div_zdxz">
							
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>
							<div class="btn">
								<!-- ��һ�� -->
								<button type="button" onclick="confirmInfo('��ȷ����������',preStep);">
									��һ��
								</button>
								
								<!-- ��һ�� -->
<%--								<button type="button" onclick="confirmInfo('��ȷ����������',nextStep);">--%>
<%--									��һ��--%>
<%--								</button>--%>
								
								<button type="button" onclick="confirmInfo('��ȷ����������',nextStep);">
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