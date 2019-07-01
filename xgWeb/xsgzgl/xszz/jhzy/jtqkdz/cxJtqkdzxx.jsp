<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});

		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		function searchRs(){
			var url = "xszz_jhzy_jtqkdzAjax.do";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//�޸�
		function updateJtdzxx(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url="xszz_jhzy_xgJtqkdz.do";		
				url+="?pkStr="+pkValue;
				showTopWin(url,900,800);
			}else{
				alertInfo("�빴ѡһ����¼�����޸ģ�");
				return false;
			}
		}

		//�鿴
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url="xszz_jhzy_ckJtqkdz.do";			
				url+="?pkStr="+pkValue;
				showTopWin(url,900,800);
			}else{
				alertInfo("�빴ѡһ����¼���в鿴��");
				return false;
			}
		}
		
		//ɾ��
		function deleteJtqkdzxx(){
			
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			var blog=true;
			if(n>0){
				if(blog){
					confirmInfo("�ò�������ɾ����ͥ���������Ϣ���Ƿ�ȷ������������",function(tag){
						
						if(tag=="ok"){
							
							var pkValue=new Array();
							
							var xh=new Array();
							
							jQuery("[name=div_pkValue]:checked").each(function(i){
								
								pkValue[i]=jQuery(this).val();
							
							});
							
							var parameter={}

							parameter["pkValue"]=escape(pkValue.join("!!@@!!"));
							
							var url= "xszz_jhzy_scJtqkdz.do";
							
							jQuery.ajaxSetup({async:false});	
							
							jQuery.post(url,
								parameter,
								function(result){
								
									alertInfo(result,function(tag){
										
										if(tag=="ok"){
											searchRs();
										}
									
									});
									
								}
							);
							
							jQuery.ajaxSetup({async:true});
						}
					});
				}
			}else{
				alertInfo("�빴ѡ��Ҫɾ�������ݣ�",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		
		
		function showPrint(){
		
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			if(n==1){
				var xh=jQuery("[name=div_pkValue]:checked").parents("tr").find("[name=xh_array]").eq(0).val();
				var xn=jQuery("[name=div_pkValue]:checked").parents("tr").find("[name=xn_array]").eq(0).val();
				showOpenWindow("jtqkdzGl.do?method=jtqkdcb&xh="+xh+"&xn="+xn);
			}else{
				
				alertInfo("�빴ѡһ����Ҫ��ӡ�ļ�¼��");
				return false;
			}
		}
		</script>
	</head>
	<body>

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
				<!-- ��ʾ��Ϣ end-->
		<logic:notEmpty name="czqx">
		<div class="prompt">
			<h3>
				<span>��ʾ��${czqx }</span>
			</h3>
			<p>
				
			</p>
		</div>
</logic:notEmpty>
		<html:form action="/jtqkdzGl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:empty name="czqx"><!-- ��ʱ�䷶Χ�ڲ��ܲ��� -->
						<li>
							<a href="#" class="btn_zj" onclick="showTopWin('xszz_jhzy_zjJtqkdz.do',900,800);return false;">����</a>
						</li>
						<li>
							<a href="#" class="btn_xg" onclick="updateJtdzxx();return false;">�޸�</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="deleteJtqkdzxx();return false;">ɾ��</a>
						</li>
						</logic:empty>
						<li>
							<a href="#" class="btn_zj" onclick="showView();return false;">�鿴</a>
						</li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
						<li>
							<a href="#" class="btn_dy" onclick="showPrint();return false;" onclick="return false;">��ӡ����</a>
						</li>
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
					page="/sjcz/turnpage.jsp?form=xszzJtqkdzActionForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>