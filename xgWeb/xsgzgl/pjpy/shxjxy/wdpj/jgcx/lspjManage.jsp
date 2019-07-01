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
			
			var url = "general_wdpj_jgcx_ajax.do?method=searchWdpjLspj";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);
			
			jQuery.ajaxSetup({async:true});
		}
		
		//��ӡ����
		function printPj(url){
			
			var num = jQuery("input[name=primarykey_checkVal]:checked").length;
			
			var flag = false;
			var n = 0;
			var jxjName = "";
			var xh = "";
			
			jQuery("input[name=primarykey_checkVal]:checked").each(function(i){
				jxjName = jQuery(this).parents("tr").children("td").eq(7).html();
				xh = jQuery(this).parents("tr").children("td").eq(2).html();
				xn = jQuery(this).parents("tr").children("td").eq(1).html();
			});
			
			var xxdm = jQuery("#xxdm").val();
			
			//�㽭��ýѧԺ
			if("11647" == xxdm || "12741" == xxdm || "10338" == xxdm || "10653" == xxdm||"12867"==xxdm||"10657"==xxdm){
				url = "general_pjpy_djbg.do?method=printDjbg";
				url+= "";
				jQuery("input[name=primarykey_checkVal]:checked").each(function(i){
					var pkValue = jQuery(this).val();
<%--					url+= "&str_xn=" + (pkValue.split("luojw")[0]);--%>
<%--					url+= "&str_xq=" + (pkValue.split("luojw")[1]);--%>
<%--					url+= "&str_xh=" + (pkValue.split("luojw")[2]);--%>
<%--					url+= "&str_xmmc=" + (pkValue.split("luojw")[3]);							--%>
<%--					url+= "&str_lx=lspj";--%>
						url+= "&str_xh=" + xh;
						url+= "&str_xmmc=" + jxjName;
						url+= "&str_lx=bcpj";
				})
			}else{
				url=url+"&jxjName="+jxjName+"&xh="+xh+"&xn="+xn;
			}
			
			if(num==1){	
				 document.forms[0].action = url;
				 document.forms[0].target = "_blank";
				 document.forms[0].submit();
				 document.forms[0].target = "_self";
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
				<em>���ĵ�ǰλ�ã�</em><a>�������� - ��ʷ������Ϣ</a>
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
				1.�����ܵ���ɾ�Ĳ���ֻ����<font color="blue">ѧУ�û�(����Ա)</font>���в�����<br/>
				2.�������������<font color="blue">�޷���ʾ</font>����ʱ���������Ŀ��������ά��һ����¼�����½��뱾���ܡ�<br/>
				3.<font color="blue">��ѡһ��</font>��¼�����<font color="blue">��ӡ�ǼǱ�</font>�����������Ӧ�ĵǼǱ�<br/>
				4.�ǼǱ��ӡ���������ѧУʵ�����������ϵ����˾�ͷ���Ա��<br/>
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
						<!-- ҳ����Դ -->
						<logic:equal name="forward" value="jbsz">
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								�����ҵ�����
							</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_ck" onclick="showWdpjLspj('view');return false;">
								�鿴
							</a>
						</li>
						<!-- ����Ա����  begin -->
						<logic:equal name="userStatus" value="xx">
							<li>
								<a href="#" onclick="showWdpjLspj('add');return false;" class="btn_zj">
									����
								</a>
							</li>
							<li>
								<a href="#" onclick="showWdpjLspj('edit');return false;" class="btn_xg">
									�޸�
								</a>
							</li>
							<li>
								<a href="#" onclick="checkDelWdpjLspj();return false;" class="btn_sc">
									ɾ��
								</a>
							</li>
							
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">
									����
								</a>
							</li>
						</logic:equal>
						<!-- ����Ա����  end -->
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
						<li>
							<a href="#" onclick="printPj('pjpyCommPrint.do?method=printJsp');return false;" class="btn_dy">
								��ӡ�ǼǱ�
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
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