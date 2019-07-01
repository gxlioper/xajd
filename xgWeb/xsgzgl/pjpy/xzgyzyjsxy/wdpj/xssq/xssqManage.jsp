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
			searchRs();
		}

		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_wdpj_xssq_ajax.do?method=searchWdpjXssq";
			var ie = "ie";
			var xmlx = jQuery("#xmlx").val();//��Ŀ����
			if(xmlx == ""){
				xmlx = " ";
			}
			var xmxz = jQuery("#xmxz").val();//��Ŀ����
			if(xmxz == ""){
				xmxz = " ";
			}
			var xmmc = escape(jQuery("#xmmc").val());//��Ŀ����
			if(xmmc == ""){
				xmmc = " ";
			}
			
			var otherValue = [ie,xmlx,xmxz,xmmc];
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}

		//��ʾѧ������(�޸�)div
		function showDiv(lx,xmdm){

			var url="general_wdpj_xssq_ajax.do?method=showXssqDiv";
			
			var opera=lx;
			
			//��������
		 	var parameter = {
				"opera":opera,
				"xmdm":xmdm
			};
		  	
			jQuery("#div_modi").load(url,parameter,function(){
			
				tipsWindown("ϵͳ��ʾ","id:div_modi","600","380","true","","true","id");
			});
		}
		
		//�ж�ѧ��������Ŀ�ʸ�
		function checkXssqZg(lx,xmdm){

			var url="general_wdpj_xssq_ajax.do?method=checkXssqZg";
			
			var opera=lx;
			
			//��������
		 	var parameter = {
				"xmdm":xmdm
			};
		  	
			jQuery.post(url,parameter,
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					if(result==""){
						showDiv(lx,xmdm);
					}else{
						alertInfo(result);
					}
					
				}
			);
		}
		
		function yzSqly(){
			var sqlylen = jQuery("#sqly").val();
			if(sqlylen.length<300){
				alertInfo("����������300����!");
				return false;
			}else if(sqlylen.length>500){
				alertInfo("���ܶ���500����!");
				return false;
			}else{
				return true;
			}
		}
	     
		 function save(opera,xmdm){
     		
     		var message="";
     		
     		if(opera=="add"){
     		
     			message="�Ƿ�ȷ��Ҫ<font color=\"blue\">����</font>��������Ŀ��";
     		
     		}else{
     		
     			message="�Ƿ�ȷ��Ҫ<font color=\"blue\">�޸�</font>����������Ϣ��";
     		
     		}
     		
     		confirmInfo(message,function(tag){
				
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["xmdm"]=xmdm;
					
					parameter["opera"]=opera;
					
					parameter["sqly"]=escape(jQuery("#sqly").val());
					
					var url = "general_wdpj_xssq_ajax.do?method=saveXssqInfo";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
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
				
			});
		}
		
		function saveBJLH(opera,xmdm){
			if(!yzSqly()){
				return false;
			}
     		
     		var message="";
     		
     		if(opera=="add"){
     		
     			message="�Ƿ�ȷ��Ҫ<font color=\"blue\">����</font>��������Ŀ��";
     		
     		}else{
     		
     			message="�Ƿ�ȷ��Ҫ<font color=\"blue\">�޸�</font>����������Ϣ��";
     		
     		}
     		
     		confirmInfo(message,function(tag){
				
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["xmdm"]=xmdm;
					
					parameter["opera"]=opera;
					
					parameter["sqly"]=escape(jQuery("#sqly").val());
					
					var url = "general_wdpj_xssq_ajax.do?method=saveXssqInfo";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
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
				
			});
		}
		
		function disfrockXssqInfo(pkValue){
			
			confirmInfo("�ò������᳷��������Ϣ���Ƿ�ȷ������������",function(tag){
				
				if(tag=="ok"){
					
					var xh=new Array();
					
					var parameter={}
					
					
					parameter["pkValue"]=escape(pkValue);
					
					var url= "general_wdpj_xssq_ajax.do?method=disfrockXssqInfo";
					
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
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
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
				1.�������չʾ��������Ŀ��<font color="blue">������������</font>�ڣ����뷽ʽΪ<font color="blue">ѧ������</font>��������Ŀ��<br/>
				2.��������ĳ��Ŀ��������Ļ�������ÿ����Ŀ�Դ���<font color="blue">����</font>���ӡ�<br/>
				3.�������Ϊĳ�����<font color="blue">��������</font>ĳ��Ŀ�Ļ�����<font color="blue">�������</font>��ʱ��ϵͳ��������������ʾ��<br/>
				4.������Ѿ�������ĳ��Ŀ����û����һ�û�������˵Ļ��������Զ������¼����<font color="blue">�޸�</font>��<font color="blue">����</font>������<br/>
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
					</ul>
				</div>
				<!-- ��ť end-->
				
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									��Ŀ����
								</th>
								<td>
									<select id="xmlx" style="width:150px">
										<option value=""></option>
										<option value="01">
											��ѧ��
										</option>
										<option value="02">
											�����ƺ�
										</option>
									</select>
								</td>
								<th>
									��Ŀ����
								</th>
								<td>
									<select id="xmxz" style="width:150px">
										<option value=""></option>
										<logic:iterate name="xmxzList" id="xmxz">
											<option value="${xmxz.dm }">
												${xmxz.mc }
											</option>
										</logic:iterate>
									</select>
								</td>
								<th>
									��Ŀ����
								</th>
								<td>
									<input type="text" id="xmmc" style="width:150px" />
									<input type="text" id="hidden_xmmc" style="display:none"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				
				<div style="display:none">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				</div>
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
				<div id="div_rs" style="">
				
				</div>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- �ҵ����������� -->
			<div id="div_001" style="display:none">
				<div class="open_win01" style="">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th>
									<span>����������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									1.����ʱ��δ������������<br />
									2.����ĿҪ���޲������Ŀ���������������д������Ŀ�58�֣���������<br />
									3.���Ѿ�������һ�Ƚ�ѧ���뱾��Ŀ���ɼ��<br />
									4.����Ŀ��������Ϊ3��(�༶)��Ŀǰ�Ѿ��ﵽ���ޣ�����������
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<button type="button" onclick="closeWindown();return false;">�� ��</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
				
			<!-- �ҵ����������� -->
			<div id="div_002" style="display:none">
				<div class="open_win01" style="">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��Ŀ˵��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									��Ŀ˵��
								</th>
								<td style="word-break:break-all;">
									ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��ţB�Ľ��
								</td>
							</tr>
							<tr>
								<th>
									��������
								</th>
								<td style="word-break:break-all;">
									<textarea rows="8" cols="" style="width:99%"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="closeWindown();return false;">�� ��</button>
										<button type="button" onclick="closeWindown();return false;">�� ��</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- �ҵ����������� end-->
				
			<!-- �ҵ����������� -->
			<div id="div_modi" style="display:none">
				
			</div>
			<!-- �ҵ����������� end-->
					
				
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>