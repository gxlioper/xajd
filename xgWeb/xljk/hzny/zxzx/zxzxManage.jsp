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
			
			searchRs()
			
		}
		
		//��ѯ�����
		function searchRs(){
		
				//��ť����
				var url = "xljk_hzny_ajax.do?method=zxzxglSearch";
				
				//ie�汾
				var ie = "ie";
	
				var parameter={}
				parameter["ie"]=ie;
	
				showWaitingDiv("1000");
				
				zxzxSearch(url,parameter);
			
		
		}
		
		function zxzxSearch(url,parameter){

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

		
		function save(tag){
			if(tag=="ok"){
		
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};;
			
				jQuery("[name=div_pkValue]:checked").each(function(i){
							
					pkValue[i] =escape(jQuery(this).val());
							
				});
				
				var xh=new Array();
				jQuery("[name=div_pkValue]:checked").each(function(i){
							
					xh[i] =escape(jQuery(this).val());
							
				});
				
				var url = "xljk_hzny_ajax.do?method=zxzxlModi";
	          	 
			 	parameter["str_byjd"]=escape(byjdV);
				parameter["array_pkValue"]=pkValue.join("!!array!!");
				parameter["array_xh"]=xh.join("!!array!!");
	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result,function(tag){
						
							if(tag=="ok"){
						
								closeWindown();	
								searchRs();
								
							}
						});
						
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
			
		
		}
		
		function showModi(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var url="xljk_hzny.do?method=zxzxDetail&doType=modi";
				
				url+="&pkValue="+pkValue;
				
				showTopWin(url,800,600);
			}else{
				
				alertInfo("�빴ѡһ����Ҫ�޸ĵļ�¼��");
				
				return false;
			}
		}
		
		function showView(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var url="xljk_hzny.do?method=zxzxDetail&&doType=view";
				
				url+="&pkValue="+pkValue;
				
				showTopWin(url,800,600);
			}else{
				
				alertInfo("�빴ѡһ����Ҫ�鿴�ļ�¼��");
				
				return false;
			}
		}
		
		function deleteYbbx(){
			
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			var blog=true;
			if(n>0){
				

				if(blog){
					confirmInfo("�ò�������ɾ������ѯ��¼���Ƿ�ȷ������������",function(tag){
						
						if(tag=="ok"){
							
							var pkValue=new Array();
							
							var xh=new Array();
							
							jQuery("[name=div_pkValue]:checked").each(function(i){
								
								pkValue[i]=jQuery(this).val();
							
							});
							
							var parameter={}
							
							
							parameter["pkValue"]=escape(pkValue.join("!!array!!"));
							
							var url= "xljk_hzny_ajax.do?method=deleteZxzx";
							
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
		</script>
	</head>
	<body onload="onShow()">

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/xszzYbbx" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:equal name="userType" value="stu">
								<li>
									<a href="#" onclick="showTopWin('xljk_hzny.do?method=zxzxDetail',800,460);return false;" class="btn_zj">��ѯ</a>
								</li>
								<li>
									<a href="#" onclick="showModi();return false;" class="btn_xg">�޸�</a>
								</li>
								<li>
									<a href="#" onclick="deleteYbbx();return false;" class="btn_sc">ɾ��</a>
								</li>
							</logic:equal>
						
							<logic:equal name="zxs" value="true">
								<li>
									<a href="#" onclick="showModi();return false;" class="btn_xg">�ظ�</a>
								</li>
							</logic:equal>	
						</logic:equal>
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">�鿴</a>
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
					page="/sjcz/turnpage.jsp?form=xszzYbbxForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
