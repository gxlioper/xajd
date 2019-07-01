<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	
	<style type="text/css">
	<!--
		#formlist th{width:30%}
		#formlist td{width:70%}
		.impFilePath{width:60%}
	-->
	</style>
	
	<script language="javascript" defer="defer">
	function impZcf(tag){
	
			
		$("filePath").value = $("impFilePath").value;
		
		if($("filePath").value == ""){
			alertInfo("��ѡ������Ҫ������ļ���");
			return false;
		}
		
		if(tag=='ok'){
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			refreshForm('/xgxt/zhcpSjdr.do?method=sjdrImp&doType=imp','');
		}
	}
	
	function checkImpGs(){
		var flag = false;
		var impFilePath = $("impFilePath").value;
		
		if(impFilePath != ""){
			var filegs = impFilePath.substring(impFilePath.length-3,impFilePath.length);
			
			if(filegs != "xls"){
				alertError("��ѡ��������ݵ�Excelģ��");
			}else{
				flag = true;
			}
		}
		
		return flag;
	}
	</script>
</head>
	<body onload="" >	
	
    <html:form action="/zhcpSjdr" enctype='multipart/form-data' method="post">
	
	<div class="tab">
		<table class="formlist" border="0" align="center" style="width: 95%;" >	
			<thead>
				<tr>
					<td colspan="2"><span>��Ϣ����</span></td>
				</tr>
			</thead>
			<tbody>	
				<tr>
					<th>�ļ�ѡ��</th>
					<td>
						<input type="file" 	id="impFilePath" name="impFilePath" style="width:300px" contenteditable="false"/>
						<input type="hidden" id="filePath"/>
					</td>
				</tr>
				<tr>
					<th>˵��</th>
					<td>
						1.��ʹ�ñ�����ģ�����ṩ��<font color="blue">EXCELģ��</font>���е��룬������ܳ������⡣</br>
						2.ģ���е�������Ϣ�����޸ģ���ֻ��Ҫά����Ҫ�����<font color="blue">����</font>��</br>
						3.�ڱ�ϵͳ���Ѵ���ĳѧ���ķ�������ִ�е��룬���ܻᱻ<font color="blue">����</font>��</br>
						4.������븲��ĳѧ����¼�Ļ�������ģ����ɾ����ѧ�����мɣ�
						&nbsp;&nbsp;���ÿշ���<font color="blue">��Ч</font>��</br>
					</td>
				</tr>
			</tbody>
			
			<tfoot>
		      <tr>
		        <td colspan="2">
		          <div class="btn">
		          	<button type="button" id="btn_save" onclick="if(checkImpGs()){confirmInfo('��ȷ���Ƿ�Ҫִ�е������',impZcf)};">
						ȷ ��
					</button>
					
		            <button type="button" id="btn_clo" onclick="Close();return false;">
						�� ��
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
		
		<!-- ��ʾ��Ϣ -->
		<logic:present name="message">
			<script defer="defer">
				if($("message") && $("message").value != ""){
				
					alertInfo($("message").value);
					
					$("message").value = "";
					$("doType").value = "";
		
					if($("importResult")){
						$("importResult").value = "";
					}
				}
			</script>
		</logic:present>
		
		<!-- �ȴ�ing -->
		<div id="divWaiting" style="display: none; z-index: 2100; left: 25%; right: 25%; position: absolute;
		     text-align: center; width: 50%; height: 50px;left: expression((this.offsetParent.clientWidth/2)-(this.clientWidth/2)+this.offsetParent.scrollLeft);
		     top: expression((this.offsetParent.clientHeight/2)-(this.clientHeight/2)+this.offsetParent.scrollTop);">
		   <img src="<%=stylePath%>images/ico_loading.gif"/>
		   <p id="p_tsxx"><B>���ݴ����У����Ժ󡣡���</B></p>
		</div>
		
		<div id="divDisable" style="display: none;width:expression(document.body.offsetWidth);height:expression(document.body.offsetHeight); z-index: 2000; position: absolute;left: 0px; top: 0px;filter:alpha(opacity=50); background-color:White"></div>
				
    </html:form>
  </body>
</html>
