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
		//ҳ���ʼ��
		function onShow(){
			var spgw=$("spgw").value;

			if (spgw == ""){
				showSpgw();
			} else {
				searchRs();
			}
			
		}

		//��ѯ�����
		function searchRs(){
			
			var spgw=$("spgw").value;
			if(spgw!=""){
				jQuery.ajaxSetup({async:false});
				
				var url = "gyglnew_jqlx_ajax.do?method=jqlxshSearch";
				var ie = "ie";// ie�汾
				var v4Path = stylePath;//v4��ʽ·��
				var spgw = $("spgw").value;//������λ
				
				// ��Ҫ�����̨����������
				var otherValue = [ie,v4Path,spgw];
	
				// loding
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				// ��ѯ����
				searchRsByAjax(url,otherValue);
	
				// ���� loding
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				
				
				jQuery.ajaxSetup({async:true});
			}else {
				showSpgw();
			}
		}

		//������Ŀ���(������ˡ��������)
		function showShxxDiv(){
			var pk = jQuery("input[name=div_pkValue]:checked").eq(0).val();
			var len = jQuery("input[name=div_pkValue]:checked").length;
			
			tipsWindown("ϵͳ��ʾ","id:div_002","500","300","true","","true","id");
			
		}
		
		function jqlxsh(){
			
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==0){
				
				alertInfo("�빴ѡ��Ҫ��˵ļ�¼��");
				return false;
			}else if(len==1){
				var spgw=$("spgw").value;
				var sqid=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var url = "gyglnew_jqlx.do?method=lxshDetail";
				url+="&sqid="+sqid;
				url+="&spgw="+spgw;
				showTopWin(url,"800","600");
			}else {
				showShxxDiv();
			}
		}


		//ǰ����Ŀ���
		function showSpgw(){
		
			var url="gyglnew_jqlx_ajax.do?method=showShgwDiv";
			
			var parameter={};
			
			
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
		
			$("spgw").value=jQuery("[name=spgw]:checked").val();
			// ��ȡѡ�е�������λID
			var id=jQuery("[name=spgw]:checked").attr("id");
			// ������˼����ֵ
			$("shjb").value=jQuery("#shjb_"+id.split('_')[1]).val();
			
			// ��˼���Ϊ��һ�� �����˻ذ�ť ������ʾ
<%--			if($("shjb").value=="1"){--%>
<%--				$("btn_th").style.display="none";--%>
<%--			}else{--%>
<%--				$("btn_th").style.display="";--%>
<%--			}--%>
			closeWindown();
			searchRs();
			
		}
		
		function saveShzt(shzt){

			jQuery.ajaxSetup({async:false});
			
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
					
					parameter["str_spgw"]=spgw;
					
					parameter["array_sqid"]=sqid.join("!!array!!");
					
					parameter["str_shzt"]=shzt;
					
					parameter["str_shyj"]=escape(jQuery("#shyj").val());
					
					var url = "gyglnew_jqlx_ajax.do?method=plsh";
		          	
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
		
		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
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
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			
		</div>
		<!-- ���� end-->
	
		<html:form action="/gyglnew_jqlx" method="post">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="sqkg" styleId="sqkg" />
			<html:hidden property="lcid" styleId="lcid" />
			<html:hidden property="spgw" styleId="spgw" />
			<input type="hidden" name="shjb" id="shjb" />
			<input type="hidden" name="spgwNum" id="spgwNum" value="${spgwNum}" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
			
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" id="btn_sh"
								onclick="jqlxsh();return false;" 
								class="btn_sh">
								<span>���</span>
							</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" id="btn_sx" disabled="true"
								onclick="if(checkItsDis(this)){showSpgw();};return false;"
								class="btn_sx">
								<span>�л���˸�λ</span>
							</a>
						</li>
					</ul>
					<button type="button" class="btn_cx" id="search_go" style="display: none" onclick="searchRs();return false;"></button>
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
			
				<div id="div_rs" style="width:100%;height:360px;overflow-x:auto;overflow-y:auto;">
				</div>
						
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jqlxForm"></jsp:include>
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
									<textarea rows="5" name="shyj" id="shyj" cols="" style="width:400px"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveShzt('tg');return false;">ͨ��</button>
										<button type="button" onclick="saveShzt('btg');return false;">��ͨ��</button>
<%--										<button type="button" id="btn_th" onclick="saveShzt('th')">�˻�</button>--%>
										<button type="button" onclick="closeWindown();return false;">�ر�</button>
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