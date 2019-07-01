<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 		
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			//��ť����
			
				//controlBtn();
				var url = "xsxx_qtxx_ajax.do?method=qtxxSearch";
				
				//��Ŀ����
				var xmdm = $("xmdm").value;
				//ie�汾
				var ie = "ie";
	
				var parameter={}
				parameter["ie"]=ie;
				parameter["xmdm"]=xmdm;
	
				//showWaitingDiv("1000");
				qtxxSearch(url,parameter);
			
		
		}
		
		function qtxxSearch(url,parameter){

			var currentPage = "1";
			if($("currentPage")){
				currentPage = $("currentPage").value;
			}
			
			var editPageSize = "";
			if($("editPageSize")){
				editPageSize = $("editPageSize").value;	
			}
				
			var pagesize = "";
			if($("pagesize")){
				pagesize = $("pagesize").value;
			}
		
			var input_mhcx = "";
			if($("input_mhcx")){
				input_mhcx = $("input_mhcx").value;
			}
			
			var mhcx_lx = "";
			if($("mhcx_lx")){
				mhcx_lx = $("mhcx_lx").value;
			}
		
			var searchLx = new Array();
			var searchTj = new Array();
			var searchTjz = new Array();
			
			var n=0;
			var m=3;
			
			searchLx[0]="xy";
			searchLx[1]="zy";
			searchLx[2]="bj";
			
			for(var i=0;i<jytj.length;i++){
				searchLx[m]=jytj[i];
				m++;
			}
		
			var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
				
			for(var j=0;j<tj_num;j++){
				var obj = $("searchTjDiv").getElementsByTagName('input')[j];
				searchTj[n]=obj.name;
				searchTjz[n]=escape(obj.value);
				n++;
			}
			
			parameter["currentPage"]=currentPage;
			parameter["editPageSize"]=editPageSize;
			parameter["pagesize"]=pagesize;
			parameter["input_mhcx"]=escape(input_mhcx);
			parameter["mhcx_lx"]=mhcx_lx;
			parameter["searchTj"]=searchTj.join("!!@@!!");
			parameter["searchTjz"]=searchTjz.join("!!@@!!");
			parameter["searchLx"]=searchLx.join("!!@@!!");
		  
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery("#div_rs").load(url,parameter,function(){
				setTimeout("setPageInfo()",500);
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
			});
		}


		function add(){
			var xmdm=$("xmdm").value;
			showTopWin("/xgxt/xsxx_qtxx.do?method=xsqtxxDetail&doType=add&xmdm="+xmdm,800,600);
		}
		
		function update(){
			
			var xmdm=$("xmdm").value;
			
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){
				
				var xh=jQuery("[name=div_pkValue]:checked").parents("tr").children().find("[name=xh]").val();
			
				showTopWin("/xgxt/xsxx_qtxx.do?method=xsqtxxDetail&doType=update&xmdm="+xmdm+"&xh="+xh,800,600);
			}else {
				
				alertInfo("�빴ѡһ����Ҫ�޸ĵ����ݣ�");
			}
		}
		
		function deleteInfo(){
			
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			if(n>0){
				confirmInfo("�ò�������ɾ��ѧ�����ϰ�ѧ��Ϣ<br/>���Ƿ�ȷ��ɾ����",function(tag){
					
					if(tag=="ok"){
						
						var pkValue=new Array();
						
						jQuery("[name=div_pkValue]:checked").each(function(i){
							
							pkValue[i]=jQuery(this).val();
						
						});
						
						var parameter={}
						
						
						parameter["pkValue"]=escape(pkValue.join("!!array!!"));
						
						var url= "xsxx_qtxx_ajax.do?method=delete";
						
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
			}else{
				
				alertInfo("�빴ѡ��Ҫɾ�������ݣ�",function(tag){
					
					if(tag=="ok"){
						return false;
					}
				
				});
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body >

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
<%--			<p class="help">--%>
<%--				<a href="#" onclick="return false;" onmouseover="showHelpDiv()"--%>
<%--					onmouseout="showHelpDiv()"> ��������</a>--%>
<%--				<img src="<%=stylePath%>/images/ico_new02.gif" />--%>
<%--			</p>--%>
		</div>
		<!-- ���� end-->

		<!-- ��ʾ��Ϣ end-->
<%--		<div class="prompt">--%>
<%--			<h3>--%>
<%--				<span>ϵͳ��ʾ��</span>--%>
<%--			</h3>--%>
<%--			<p>--%>
<%--				����ƶ������Ͻ�--%>
<%--				<font color="blue">��������</font>���ɲ鿴��ģ������˵����--%>
<%--				</br>--%>
<%--				<span id="div_help" style="display: none"> </span>--%>
<%--			</p>--%>
<%--		</div>--%>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/xsxx_qtxx" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>

						<li>
							<a href="#" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="#" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="#" onclick="deleteInfo();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="#" onclick="impAndChkData();return false;"
								class="btn_dr">��������</a>
						</li>
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a>
						</li>
						<li>
							<a href="#" class="btn_dc"
								onclick="configureExportData();return false;">��������</a>
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
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xsxxQtxxForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
