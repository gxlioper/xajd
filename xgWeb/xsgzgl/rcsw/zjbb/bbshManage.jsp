<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" defer="defer">
		//ҳ���ʼ��
		function onShow(){
			
			var id = "left_a_0";
			
			var xmdm=$("xmdm").value;
			// �ж��Ƿ��Ǵ��ҵ����ģ����ת����
			if(xmdm!=""){
				// ����־λ�ƿ�
				$("first").value="";
				
				// �ж���Ҫѡ�е���Ŀ
				var xmdmArr=jQuery("input[name=xmdmArr]").each(function(i){
					var xmdmVal=jQuery(this).val();
					
					if(xmdmVal==xmdm){
						
						// ѡ�к�������ɫ
						setLiClick(i);
					}
				});
				// ��ѯ
				searchRs();
				
			}else{// ����ת���ˣ������ģ�����л���Ŀ��
				
				// δѡ��
				if($(id) && xmdm==""){
					$(id).click();
				}else if(xmdm!=""){// ��ѡ����Ŀ
					
					var xmdmArr=jQuery("input[name=xmdmArr]").each(function(i){
						var xmdmVal=jQuery(this).val();
						if(xmdmVal==xmdm){
							$("left_a_"+i).click();
						}
					});
					
					searchRs();
				}
			}
		}

		//��ѯ�����
		function searchRs(){
			
			var spgw=$("spgw").value;
			if(spgw){
				
				jQuery.ajaxSetup({async:false});
				
				var url = "rcsw_zjbb_ajax.do?method=bbshSearch";
				var ie = "ie";// ie�汾
				var v4Path = stylePath;//v4��ʽ·��
				var xmdm = $("xmdm").value;//��Ŀ����
				var spgw = $("spgw").value;//������λ
				
				// ��Ҫ�����̨����������
				var otherValue = [ie,v4Path,xmdm,spgw];
	
				// loding
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				// ��ѯ����
				searchRsByAjax(url,otherValue);
	
				// ���� loding
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				
				// �ж��Ƿ��ǵ�һ����˸�λ(�����˻ذ�ť��дȨ)
				if($("hid_firstSpgw")){
				
					var div_firstSpgw=$("hid_firstSpgw").value;
					// ��һ����˲������˻�
					if(div_firstSpgw=="true"){
						$("btn_th").style.display="none";
					}else if(div_firstSpgw=="false"){
						$("btn_th").style.display="";
					}
				}
				
				// ���ʱ�䡢���ؿ���
				if($("shkz")){
					
					if($("shkz").value=="true"){
						// ȡ��������˰�ť
						$("btn_sh").disabled=false;
						
					}else{
						// ���ð�ť
						$("btn_sh").disabled=true;
					
					}
				}
				
				jQuery.ajaxSetup({async:true});
			}else {
				showGwxxDiv();
			}
		}

		//������Ŀ���(������ˡ��������)
		function showShxxDiv(){
			var pk = jQuery("input[name=div_pkValue]:checked").eq(0).val();
			var len = jQuery("input[name=div_pkValue]:checked").length;
			if(len==1){// �������
				var spgw=$("spgw").value;
				var xmid=$("xmdm").value;
				var sqid=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var url = "rcsw_zjbb.do?method=bbshDetail";
				url+="&sqid="+sqid;
				url+="&spgw="+spgw;
				url+="&xmid="+xmid;
				showDialog('',800,500,url);
			
			}else{// �������
				tipsWindown("ϵͳ��ʾ","id:div_002","500","300","true","","true","id");
			}
		}

		//��ʾ��λ��ϢDiv
		function showGwxxDiv(){
			
			var xmdm=$("xmdm").value;
			// ������Ŀ���뵯��������λ
			showSpgw(xmdm);
			
		}
		
		

		//ǰ����Ŀ���
		function showSpgw(xmdm){
		
		
			if(xmdm!=""){
				$("xmdm").value=xmdm;
			}
			
			var url="rcsw_zjbb_ajax.do?method=showShgwDiv";
			
			//��������
		 	var parameter = {
				"xmdm":xmdm
			};
		  	
			
			jQuery("#div_spgw").load(url,parameter,function(){
				
				var len=jQuery("[name=spgw]").length;
				
				if(!($("spgwNum").value=="1")){
					$("btn_sx").disabled=false;
					tipsWindown("ϵͳ��ʾ","id:div_spgw","300","170","true","","true","id");
				}else{
				
					$("btn_sx").disabled=true;
					$("spgw").value=jQuery("[type=radio][id=spgw_0]").eq(0).val();
					// ��ȡѡ�е�������λID
				
					$("shjb").value=jQuery("#shjb_0").val();
					// ��˼���Ϊ��һ�� �����˻ذ�ť ������ʾ
					
					searchRs();
				}
					
			});
		}
		
		function checkSpgw(){
		
			$("xmdm").value=jQuery("#text_xmdm").val();
			$("spgw").value=jQuery("[name=spgw]:checked").val();

			closeWindown();
			
			searchRs();
			
		}
		
		function saveShzt(shzt){

			jQuery.ajaxSetup({async:false});
			
			var xmdm = $("xmdm").value;
			var spgw = $("spgw").value;
		
			confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",function(tag){
				
				if(tag=="ok"){
				
					closeWindown();
					
					//����
					var sqid=new Array();
					
					jQuery("[name=div_pkValue]:checked").each(function(i){
						sqid[i]=jQuery(this).val();
					});
					
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["str_xmid"]=xmdm;
					
					parameter["str_spgw"]=spgw;
					
					parameter["array_sqid"]=sqid.join("!!array!!");
					
					parameter["str_shzt"]=shzt;
					
					parameter["str_shyj"]=escape(jQuery("#shyj").val());
					
					var url = "rcsw_zjbb_ajax.do?method=plsh";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							
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
		
		
		//���ù�������
		function czSearch(){

		
			//����ģ����ѯ
			if($("input_mhcx")){
				$("input_mhcx").value = "";
			}
			
			//���ø߼���ѯ
			$("yxtj_div").style.display = "none";
			$("yxtj_dl").innerHTML = "";
			if($("yxtj_gxh_div")){
				$("yxtj_gxh_div").style.display  = "none";
				$("yxtj_gxh_dl").innerHTML = "";
			}
			
			var a_num = document.getElementsByTagName('a').length;
	
			for(var i=0;i<a_num;i++){
				
				var a_className = document.getElementsByTagName('a')[i].className;
				
				if(a_className == "selectedValue"){
					document.getElementsByTagName('a')[i].className = "";
				}
			}
			
			var kssj_count = document.getElementsByName("searchModel.search_tj_kssj").length;
			var jssj_count = document.getElementsByName("searchModel.search_tj_jssj").length;
			
			//��ʼʱ��
			for(var i=0;i<kssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_kssj")[i];
				obj.value = "";
			}
			
			//����ʱ��
			for(var i=0;i<jssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_jssj")[i];
				obj.value = "";
			}
		}
		
		jQuery(function(){
			onShow();
		});
		
		</script>
	</head>
	<body ondrag="return false">

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->

		<html:form action="/rcsw_zjbb" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" id="shxm" value="${xmdm}" />
			
			<html:hidden property="spgw" styleId="spgw"/>
			<html:hidden property="xmid" styleId="xmdm"/>
			<input type="hidden" name="shjb" id="shjb" value="${shjb }" />
			<input type="hidden" name="first" id="first" value="${first }" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox">

				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_sh"
								onclick="showShxxDiv();return false;"
								class="btn_sh"> <span>���</span> </a>
						</li>
						<li>
							<a href="#" id="btn_sx" onclick="showGwxxDiv();return false;"
								class="btn_sx"> <span>�л���˸�λ</span> </a>
						</li>
					</ul>
					<button type="button" class="btn_cx" id="search_go" style="display: none"
						onclick="searchRs();return false;"></button>
				</div>
				<!-- ��ť end-->

				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
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
				<!-- From���� -->
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span> </span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- ����� -->
							<div class="listbox">
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<li id="li_${index}" class="Child">

													<a href="#" name="left_a" id="left_a_${index}"
														onclick="setLiClick('${index}');showSpgw('${xmnr.xmdm}');return false;">
														<span class="ico"></span>${xmnr.xmmc} </a>
<%----%>
<%--													<logic:equal name="xmnr" property="shkz" value="no">--%>
<%--														<a href="#" name="left_a" id="left_a_${index}"--%>
<%--															onclick="setLiClick('${index}');showSpgw('${xmnr.xmdm}');return false;">--%>
<%--															<span class="ico"></span><font style="color: gray;">${xmnr.xmmc}</font>--%>
<%--														</a>--%>
<%--													</logic:equal>--%>
													<input type="hidden" name="xmdmArr" id="xmdm_${index }"
														value="${xmnr.xmdm}" />
													<input type="hidden" id="xmmc_${xmnr.xmdm}"
														value="${xmnr.xmmc}" />
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td width="85%" valign="top" style="position: relative;">
							<div id="div_rs"
								style="width:100%;height:360px;overflow-x:auto;overflow-y:hidden;">
							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=rcswZjbbForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->

			<!-- 002 begin -->
			<div id="div_002" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��Ŀ�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									������
								</th>
								<td>
									<textarea rows="5" name="shyj" id="shyj" cols=""
										style="width:400px"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveShzt('tg');return false;">
											ͨ��
										</button>
										<button type="button" onclick="saveShzt('btg');return false;">
											��ͨ��
										</button>

<%--										<button type="button" id="btn_th" onclick="saveShzt('th')">--%>
<%--											�˻�--%>
<%--										</button>--%>

										<button type="button" onclick="closeWindown();return false;">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 002 end-->

			<div id="div_spgw" style="display:none">

			</div>

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
