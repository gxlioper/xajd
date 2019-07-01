<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){
			searchRs();
		}
		
		function getShgwInfo(){
			alert(22);
			var czxm = $("czxm").value;
			var url="rcsw_qjgl.do?method=getShgwInfo";
				url+="&czxm="+czxm;
			
			jQuery.ajax({
				type:'post',
				url:url,
				dataType:'json',
				async: false,
				success:function(result){
					if(result.length==1){
						$('shgw').value=result[0].gwid;
						searchRs();
					}else{
						$("p_gwxx").innerHTML="";
						
						var html = "";
						for(var i=0;i<result.length;i++){
							var gwid = result[i].gwid;
							var gwmc = result[i].gwmc;
							
							html+="<input type=\"radio\" name=\"rad_gwid\" value=\""+gwid+"\" onclick=\"$('hid_gwid').value=this.value\"/>";
							html+=gwmc;
							html+="<br/>";
						}
						
						html+="<input type=\"hidden\" id=\"hid_gwid\" value=\"\"/>";
						$("p_gwxx").innerHTML=html;
						
						tipsWindown("ϵͳ��ʾ","id:div_gwxx","350","250","true","","true","id");
					}
				}
			});
		}
		
		//��ѯ�����
		function searchRs(){
			var url = "rcsw_qjgl.do?method=getMycxList";
			
			var ie = "ie";
			
			var otherValue = [ie];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}
		
		function showJgcxDetail(obj){
			var inputValue=obj.getElementsByTagName("input")[0].value;
			///showTopWin("rcsw_qjgl.do?method=jgcxDetail&sqid="+inputValue,800,600);

			showDialog('', 800, 500, "rcsw_qjgl.do?method=jgcxDetail&sqid="+inputValue);
		}
		
		function print(){
			var checkVal=document.getElementsByName("checkVal");
			var n=0;
			var inputValue="";
			for(var i=0;i<checkVal.length;i++){
				if(checkVal[i].checked){
					n++;
					inputValue=checkVal[i].value;
				}
			}
			if(n!=1){
				alertInfo("�빴ѡһ����¼���д�ӡ!");
				return false;
			}
			window.open("rcsw_qjgl.do?method=jgcxDetail&doType=print&sqid="+inputValue);
			//showOpenWindow("rcsw_qjgl.do?method=jgcxDetail&doType=print&sqid="+inputValue,800,600);
		}
		
		jQuery(function(){
			onShow();
		})
		
		function jgcxExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("rcsw_qjgl_jgcx.do", jgcxExportData);
		}
			
		
			
		// ��������
		function jgcxExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "rcsw_qjgl.do?method=jgcxExportData&dcclbh=" + "rcsw_qjgl_jgcx.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body  >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ճ����� - ��ٹ��� - �����ѯ</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
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
				1.��ѡһ����¼���������ӡ����ť���Դ�ӡ�����Ϣ��</br>
				2.˫��һ����¼���Բ鿴��ϸ��Ϣ��</br>
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/rcsw_qjgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �Ƿ��ʼ��  -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<!-- �Ƿ��޸� -->
			<input type="hidden" id="had_edit" value="no"/>

			
			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li id="li_js">
							<a href="#" onclick="refreshForm('rcsw_qjgl_mygz_tea.do');return false;" class="btn_fh">
								����
							</a>
						</li>
						<li id="li_js">
							<a href="#" onclick="print();return false;" class="btn_dy">
								��ӡ
							</a>	
						</li>
						<li><a href="#" class="btn_qx" onclick="jgcxExportConfig();return false;">����</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
					--%></ul>
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
						<td >
							<h3 class="datetitle_01">
								<span>
									&nbsp;
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:400px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswQjglForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
					
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			
		</html:form>
	</body>
</html>