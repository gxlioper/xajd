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
				var url = "xszzYbbxAjax.do?method=ybbxshSearch";
				
				//ie�汾
				var ie = "ie";
	
				var parameter={}
				parameter["ie"]=ie;
	
				showWaitingDiv("1000");
				
				ybbxshSearch(url,parameter);
			
		
		}
		
		function ybbxshSearch(url,parameter){

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
		
		function ybbxsh(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){
				//�������
				showDgsh();
			
			}else if(len>1){
				//�������
				showPlsh()
			}else{
				alertInfo("�빴ѡ��Ҫ��˵ļ�¼!");
				return false;
			}
			
		}
		
		
		function showDgsh(){
		
			
			var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
			var url="xszz_ybbx_sq.do?doType=sh";
			
			url+="&pkValue="+pkValue;
			
			showTopWin(url,800,600);
			
		}
		
		function showPlsh(){
			
			tipsWindown("ϵͳ��ʾ","id:div_plsh","350","200","true","","true","id");
		}
		
		
		function plsh(shzt){
     		
     		confirmInfo("�ò��������޸����״̬���Ƿ�ȷ������������",function(tag){
     		
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
				
					
					var array=new Array();
					jQuery("input[name=div_pkValue]:checked").each(function(j){
							
							array[j] =escape(jQuery(this).val());
						
					});
					parameter["array_pkValue"]=escape(array.join('!!array!!'));
					
					parameter["str_xh"]=jQuery("#xh").val();
					
					parameter["xh"]=jQuery("#xh").val();
					
					parameter["str_xn"]=jQuery("#xn").val();
					
					parameter["xn"]=jQuery("#xn").val();
					
					parameter["xysh"]=escape(shzt);
					
					parameter["xyshyj"]=escape(jQuery("#shyj").val());
					
					parameter["xxsh"]=escape(shzt);
					
					parameter["xxshyj"]=escape(jQuery("#shyj").val());
					
					var url = "xszzYbbxAjax.do?method=plsh";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result,function(tag){
							
								closeWindown();
								
								searchRs();
							});
							
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			});
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
						<li>
							<a href="#" onclick="ybbxsh();return false;" class="btn_sh">���</a>
						</li>
						</logic:equal>
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
			<div id="div_plsh" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2" >
									<span>�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th style="width:30%">
									������
								</th>
								<td>
									<textarea  name='shyj' 
									id="shyj" style="word-break:break-all;width:85%" onblur="chLeng(this,500);"
										rows='4' ></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<logic:notEqual name="usertType" value="xy">
										<button type="button" name="�˻�" onclick="plsh('�˻�')">
											��  ��
										</button>
										</logic:notEqual>
										<button type="button" name="ͨ��" onclick="plsh('ͨ��')">
											ͨ  ��
										</button>
										<button type="button" name="��ͨ��" onclick="plsh('��ͨ��')">
											��ͨ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
