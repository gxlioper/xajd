<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			//
			searchRs();
		}

		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_wdpj_jgcx_ajax.do?method=searchWdpjBcpj";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		function showWdpjBcpj(){
			var len = jQuery("input[name=div_pkValue]:checked").length;
			if(len==1){
				var pk=jQuery("input[name=div_pkValue]:checked").eq(0).val()
				var url = "general_pjpy.do?method=bcpjDetail";
				url+="&pkValue="+pk;
				showTopWin(url,"800","600");
			}else{
				alertInfo("�빴ѡһ����Ҫ�鿴�����ݣ�");
			}
		}
		
		//��ӡ����
		function printPj(url){
				
				var num = jQuery("input[name=div_pkValue]:checked").length;
				var flag = false;
				var n = 0;
				var jxjName = "";
				var xh = "";
				
				if(num==1){
					jQuery("input[name=div_pkValue]:checked").each(function(i){
						jxjName = jQuery(this).parents("tr").children("td").eq(7).html();
						xh = jQuery(this).parents("tr").children("td").eq(1).html();
					})
					
					var xxdm = jQuery("#xxdm").val();
					if("11647" == xxdm || "12741" == xxdm || "10338" == xxdm || "10653" == xxdm ||"12867"==xxdm||"10657"==xxdm){
						url = "general_pjpy_djbg.do?method=printDjbg";
						url+= "&str_xh=" + xh;
						url+= "&str_xmmc=" + jxjName;
						url+= "&str_lx=bcpj";
						
						document.forms[0].action = url;
						document.forms[0].target = "_blank";
						document.forms[0].submit();
					    document.forms[0].target = "_self";
					    
					}else{
						 document.forms[0].action = url+"&jxjName="+jxjName+"&xh="+xh;
						 document.forms[0].target = "_blank";
						 document.forms[0].submit();
						 document.forms[0].target = "_self";
					}
					
				}else{
					alertInfo("�빴ѡһ����Ҫ��ӡ��¼��");
				
				}
				
		    }
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �ҵ����� - �����������</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.������չʾ����<font color="blue">����������</font>��ѧ�������¼��<br/>
				2.���״̬Ϊ<font color="blue">δ���</font>�ļ�¼�������û���κ�һ���û���˹��������¼��<br/>
				3.���״̬Ϊ<font color="blue">��˲�ͨ��</font>�ļ�¼���������ĳһ�����Ϊ��ͨ���������¼��<br/>
				4.���״̬Ϊ<font color="blue">���ͨ��</font>�ļ�¼����������һ���û����ͨ���������¼��<br/>
				5.�������Ϊ<font color="blue">�����</font>�������¼��<br/>
				6.<font color="blue">��ѡһ��</font>��¼�����<font color="blue">�鿴��ϸ</font>�����Բ鿴�������¼����ϸ��Ϣ��<br/>
				7.<font color="blue">��ѡһ��</font>��¼�����<font color="blue">��ӡ�ǼǱ�</font>�����������Ӧ�ĵǼǱ�<br/>
				8.�ǼǱ��ӡ���������ѧУʵ�����������ϵ����˾�ͷ���Ա��<br/>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								�����ҵ�����
							</a>
						</li>
						<li>
							<a href="#" onclick="showWdpjBcpj();return false;" class="btn_ck">
								�鿴��ϸ
							</a>
						</li>
						<li>
							<a href="#" onclick="printPj('pjpyCommPrint.do?method=printJsp');return false;" class="btn_dy">
								��ӡ�ǼǱ�
							</a>
						</li>
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;">
								��������
							</a>
						</li>
						<li>
							<a href="#" onclick="configureExportData();return false;" class="btn_dc">
								����
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<div id="div_rs" style="height:400px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>