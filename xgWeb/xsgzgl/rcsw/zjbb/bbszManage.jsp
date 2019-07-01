<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			searchRs();
		}

		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "rcsw_zjbb_ajax.do?method=bbszSearch";
			var ie = "ie";
			
			var zjmc =$("str_zjmc").value;
			
			var sfksc=$("sfksc").value;
			
			var parameter = {
				"ie":ie,
				"str_zjmc":zjmc,
				"str_sfksc":escape(sfksc)
			}
			;

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			bbszSearch(url,parameter);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		function bbszSearch(url,parameter){

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
		

			parameter["currentPage"]=currentPage;
			parameter["editPageSize"]=editPageSize;
			parameter["pagesize"]=pagesize;
	
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery("#div_rs").load(url,parameter,function(){
				setTimeout("setPageInfo()",500);
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
			});
		}
		
		function zjbbModi(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){
				showZjbb('modi');
			}else{
				alertInfo("�빴ѡһ����Ҫ�޸ĵ�����!");
				return false;
			}
		}
		
		function showZjbb(option){
		
			var ie = "ie";
			
			var url="rcsw_zjbb_ajax.do?method=showZjbb";
			
			var id=jQuery("[name=div_pkValue]:checked").eq(0).val();
			//��������
		 	var parameter = {
		 		"ie":ie,
		 		"stylePath":stylePath,
		 		"id":id,
		 		"option":option
			};
		 	var zjmc=jQuery("zjmc").value;

	
			var lcid=escape(jQuery("[name=lcid]:checked").eq(0).val());
			
			url+="&ie="+ie+"&stylePath="+stylePath+"&id="+id+"&option="+option+"&zjmc="+zjmc+"&lcid="+lcid;
		 	showDialog('', 600, 250, url);
		  	
			/*jQuery("#div_zjbb").load(url,parameter,function(){
			
				tipsWindown("ϵͳ��ʾ","id:div_zjbb","600","380","true","","true","id");
			});*/
		}
		
		function deleteBbsz(){
			
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			if(n>0){
				confirmInfo("�ò�������<font color='blue'>ɾ���ѹ�ѡ��֤����������</font>���Ƿ����ִ�иò�����",function(tag){
					
					if(tag=="ok"){
						
						var pkValue=new Array();
						
						var xh=new Array();
						
						jQuery("[name=div_pkValue]:checked").each(function(i){
							
							pkValue[i]=jQuery(this).val();
						
						});
						
						var parameter={}
						
						
						parameter["pkValue"]=escape(pkValue.join("!!array!!"));
						
						var url= "rcsw_zjbb_ajax.do?method=deleteBbsz";
						
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
				
				alertInfo("�빴ѡ��Ҫȡ������ļ�¼��",function(tag){
					
					if(tag=="ok"){
						return false;
					}
				
				});
			}
		}
		
		
		//��ʾ�ҵ�������ϸ
		function showLxsqDiv(){
		
			tipsWindown("ϵͳ��ʾ","id:div_lxsq","400","300","true","","true","id");
		}
		
		function saveBbsz(){
			if($("zjmc") && $("zjmc").value==""){
				alertInfo("����֤�����Ʋ���Ϊ�գ�");
				return false;
			}
			
			confirmInfo("�ò�������<font color='blue'>����֤����������</font>��Ϣ���Ƿ�ȷ����",saveBbszInfo);
			
	     }
	     
	     function modiBbsz(xmid){
			$("xmid").value=xmid;
			
			if($("zjmc") && $("zjmc").value==""){
				alertInfo("����֤�����Ʋ���Ϊ�գ�");
				return false;
			}
			confirmInfo("�ò�������<font color='blue'>�޸�֤����������</font>��Ϣ���Ƿ�ȷ����",modiBbszInfo);
			
	     }
		
		function saveBbszInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["lcid"]=escape(jQuery("[name=lcid]:checked").eq(0).val());
				
				parameter["zjmc"]=escape(jQuery("#zjmc").val());
				
				var url = "rcsw_zjbb_ajax.do?method=saveBbsz";
	          	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				closeWindown()
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						
						alertInfo(result,function(){
						
							searchRs()
						});
						
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}
		
		function modiBbszInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["lcid"]=escape(jQuery("[name=lcid]:checked").eq(0).val());
				
				parameter["zjmc"]=escape(jQuery("#zjmc").val());
				
				parameter["id"]=escape(jQuery("#xmid").val());
				
				var url = "rcsw_zjbb_ajax.do?method=modiBbsz";
	          	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				closeWindown()
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						
						alertInfo(result,function(){
						
							searchRs()
						});
						
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
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
		</div>


		<html:form action="/gyglnew_jqlx" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmid" id="xmid" />

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->

				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="showZjbb('add');return false;"
								class="btn_zj">����</a>
						</li>
						<li>
							<a href="#" onclick="zjbbModi();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="#" onclick="deleteBbsz();return false;" class="btn_sc">ɾ��</a>
						</li>
						</logic:equal>
					</ul>
				</div>

				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
							<th>
								֤������
							</th>
							<td>
								<input type="text" name="str_zjmc" id="str_zjmc" onkeypress="if(pressEnter(event)){searchRs();return false;}" 
									style="width:120px" />
							</td>
							<th>
								�Ƿ�ɲ���
							</th>
							<td>
								<select name="sfksc" id="sfksc" style="width:60px">
									<option></option>
									<option value="��">
										��
									</option>
									<option value="��">
										��
									</option>
								</select>
							</td>
							<th style="width:200px">
								
							</th>
							<th>
								
							</th>
						</tbody>
					</table>
				</div>
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jqlxForm"></jsp:include>
				<%--				<script type="text/javascript">--%>
				<%--						$('choose').className="hide";--%>
				<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->


			<div id="div_zjbb" style="display:none">

			</div>

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
