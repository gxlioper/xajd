<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
		
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		
		//��ѯ�����
		function searchRs(){
		
			var url = "xscfCxAjax.do";

			var ie = "ie";

			var otherValue = [ie];

			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","1000")
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function showView(obj){

				var pkValue=jQuery(obj).parent().parent().find("td").eq(0).find("input").val();
				var url="xscfCk.do?cfid=";
				url+=pkValue;
				showDialog("", 760, 420, url);
		}
		
		
		function cfsscl(obj) {
				
			var pkValue=jQuery(obj).parent().parent().find("td").eq(0).find("input").val();
//			alert(pkValue);
//			jQuery("#cfid").val(pkValue);
//			tipsWindown("��������","id:cfssDiv","460","300","true","","true","id");
			var url="wjcfCfssgl_cfsswh.do?method=cfsswhZj&cfid="+pkValue;
			showDialog("", 450,320, url);
			
			
		}
		function ssjgsave() {
			var pkValue=jQuery("#cfid").val();
			var sqly = jQuery("#sssqly").val();
			var fjmc = $("fj").value;
			jQuery("#fjmc").val(fjmc);
			if (fjmc != null && fjmc != "") {
				var hz = fjmc.substr(fjmc.lastIndexOf(".")+1,fjmc.length);
				if (hz!='doc'&&hz!='rar'&&hz!='pdf'){
					alertError("�ϴ��ļ�����ֻ��Ϊ��doc,rar,pdf");
					return false;
				}
			}
			if(fjmc.length > 50){
				alertError("�ļ�������,���޸��ļ��������ϴ�");
				return false;
			}
			if (sqly == null || sqly=="") {
				alertError("��*�����ݱ��");
				return false;
			}
			refreshForm('xsssSave.do?cfid='+pkValue);
		}
		function cfjccl(obj) {
			tipsWindown("���ֽ������","id:jcssDiv","460","300","true","","true","id");
			var pkValue=jQuery(obj).parent().parent().find("td").eq(0).find("input").val();
			jQuery("#cfid").val(pkValue);
		}
		function jcjgsave() {
			var pkValue=jQuery("#cfid").val();
			var sqly = jQuery("#jcsqly").val();
			if (sqly == null || sqly=="") {
				alertError("��*�����ݱ��");
				return false;
			}
			refreshForm('xsjcSave.do?cfid='+pkValue);
		}
		function qxss(obj) {
			confirmInfo("�ò�������ɾ��������Ϣ���Ƿ�ȷ������������",function(tag){
						
				if(tag=="ok"){
					var pkValue=jQuery(obj).parent().parent().find("td").eq(0).find("input").val();
					jQuery("#cfid").val(pkValue);
					refreshForm('xsssQx.do?cfid='+pkValue);	
				}
			});
		} 
		function qxjc(obj) {
		confirmInfo("�ò�������ɾ����������Ϣ���Ƿ�ȷ������������",function(tag){
				if(tag=="ok"){
			var pkValue=jQuery(obj).parent().parent().find("td").eq(0).find("input").val();
			jQuery("#cfid").val(pkValue);
			refreshForm('xscfjcQx.do?cfid='+pkValue);
				}
			});
		} 
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/wjcfCfshwh_xscfcx" method="post" enctype='multipart/form-data'>

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="cfid" id="cfid"/>
			<input type="hidden" name="fjmc" id="fjmc"/>
			<!-- �๦�ܲ����� --> 
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->

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
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=wjcfXscfActionForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
		
			<!-- �������� -->
			<div id="cfssDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
									������������
									</span>
									<font color="red">�ļ������ܳ���50������</font>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<td style="width:25%">
									֤�����ϻ򸽼�
									<br/>(��doc,rar,pdf��ʽ)
								</td>
								<td style="width:75%">
									<html:file  property='fj' styleId ="fj" />
								</td> 
							</tr>
							<tr id="">
								<td align="right" width="25%">
									<span class="red">*</span>��������<br/>
									<font color="red"><B>(��1000��)</B></font>
								</td>
								<td>
									<html:textarea property="sqly" styleId="sssqly" rows="8" style="width:300px" onblur="checkLen(this,1000)"></html:textarea>
								</td> 
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button"  name="����" onclick="ssjgsave();">
											�� ��
										</button>
										<button type="button"  name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ���ֽ�� -->
			<div id="jcssDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>
									���ֽ������
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<td align="right" width="25%">
									<font color="red">*</font>�������ɣ�<br/>
								<font color="red"><B>(��1000��)</B></font>
								</td>
								<td>
									<html:textarea property="jcsqly" styleId="jcsqly" rows="11" style="width:280px" onblur="checkLen(this,1000)"></html:textarea>
								</td> 
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button"  name="����" onclick="jcjgsave()">
											�� ��
										</button>
										<button type="button"  name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
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
