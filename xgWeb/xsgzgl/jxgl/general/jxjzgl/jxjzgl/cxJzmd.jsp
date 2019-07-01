<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>			
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/xsgzgl/jxgl/general/jxxxwh/jxmd.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});


		//��ѯ�����
		function searchRs(){
			//��ť����

			//controlBtn();
			var url = "jxjzgl_cxJxjz_ajax.do?method=cxJzmdAjax";
		
			var ie = "ie";

			var otherValue =new Array(1);
			otherValue[0] = ie;
			
			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","2000");
		
			
		}

		//
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//���潨������
		function saveJzmd(){
			var jqCheck=jQuery("[name=div_pkValue]:checked");
			var jqDqCheck=null;
			var pkValues="";
			if(jqCheck.length==0){
				alertInfo("��ѡ��ѧ����Ϣ");
				return false;
			}else{
				for(var i=0;i<jqCheck.length;i++){
					if(pkValues==""){
						pkValues=jqCheck[i].value;
					}else{
						pkValues=pkValues+"@@##"+jqCheck[i].value;
					}
				}
			}
			var jzid=jQuery("#jzid").val();
			var url="jxjzgl_cxJxjz_ajax.do?method=zjBcWhjxjzmd";
			var map={"pks":pkValues,"jzid":jzid};
			jQuery.post(url,map,function(data){
				if(data==true){
					alertInfo("����ɹ�!");
					//ˢ�¸�ҳ��
					window.parent.dialogArguments.refurbishJxjz();
					Close();
				}else{
					alertInfo("����ʧ��!");
				}
			},"json");
		}

		//���ؾ�ѵ����
		function fhJxjz(){
			var url = "jxjzgl_jxjzgl_cxJxjz.do";
			window.location.href=url;
		}

		//������������
		function dcJzmd(){
			var url = "jxjzgl_cxJxjz.do?method=dcJzmd";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		
		function jxjzglExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("jxjzgl_cxJxjz_ajax.do", jxjzglExportData);
			}
			
		
			
		// ��������
		function jxjzglExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "jxjzgl_cxJxjz_ajax.do?method=jxjzglExportData&dcclbh=" + "jxjzgl_cxJxjz_ajax.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		</script>
	</head>
	<body>

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
				<span>
				��������Ϊ������ǰ��ѯ�����µı����Ƶ�ѧ����Ϣ��
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="jxjzgl_cxJxjz.do?method=cxJzmd" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="jzid" name="jzid" value="${model.jzid }"/>
			<input type="hidden" id="sjid" name="sjid" value="${model.sjid }"/>
			<input type="hidden" id="jxid" name="jxid" value="${jxxxwhModel.jxid }"/>		
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<logic:present name="resultPath">
								<logic:empty name="resultPath">
									<a href="#" class="btn_fh" onclick="fhJxjz();return false;">����</a>
								</logic:empty>
								<logic:notEmpty name="resultPath">
									<a href="${resultPath }" class="btn_fh">����</a>
								</logic:notEmpty>
							</logic:present>
							<logic:notPresent name="resultPath">
								<a href="#" class="btn_fh" onclick="fhJxjz();return false;">����</a>
							</logic:notPresent>
						</li>
						<%--<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
						--%><li><a href="#" class="btn_dc" onclick="jxjzglExportConfig();return false;">�Զ��嵼��</a></li>
						</ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxjzglFrom"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
	</body>
</html>