<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�cq -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<script type='text/javascript' src="js/uicomm.js"></script>
		<%@ include file="/syscommon/head.ini"%>		
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		
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

		//ɾ��
		function del(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			var blog=true;
			if(len>=1){	
				if(blog){
					confirmInfo("�ò�������ɾ��������ѡ�����ݣ��Ƿ�ȷ������������",function(tag){
						if(tag=="ok"){
							var array = jQuery("[name=div_pkValue]:checked");
							var str = "";
							for (var i=0;i<array.length;i++) {
								var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
								str += pkValue+"!!@@";
							}
							var parameter={}
							var url="gyjl_gyjlglnew.do?method=gyjlSc";	
							parameter["str"]=str;							
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

		//��ѯ
		function searchRs(){
			var url = "gyjl_gyjlglnew_ajax.do?method=gyjlxxcl";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//����
		function gyjlcl(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			var flg = true;

			if (len > 0){
				var chbox = jQuery("[name=div_pkValue]:checked");
				for (var i = 0 ; i < chbox.length ; i++){
					var shzt = jQuery(chbox[i]).parents("tr").find("td").eq(8).text();

					if (shzt != 'δ���'&&shzt!='�˻�'){
						flg = false;
						break;
					}
				}
			} else{
				alertInfo("�빴ѡһ����Ҫ����ļ�¼��");
			}

			if (flg){
				if(len==1){	
					
					var pk_value=jQuery("[name=div_pkValue]:checked").eq(0).val();

					var xh=pk_value.split("!!shen!!")[0];

					var jlsj=pk_value.split("!!shen!!")[2];
					
					var gyjllbdm=pk_value.split("!!shen!!")[1];

					var url="gyglnew_gyjlgl_gyjlclwh.do";
					
					url+="?xh="+xh+"&jlsj="+jlsj+"&gyjllbdm="+gyjllbdm;
					 
					//showTopWin(url,800,600);
					showDialog("��Ԣ������Ϣ����", 800, 500, url);
				} else if (len > 1) {
					//tipsWindown("","id:shDiv","450","250","true","","true","id");
					tipsWindownNew("","id:shDiv",450,250);
				}
			} else {
				alertInfo("����˵ļ�¼�����ٴδ���");
			}
		}

		//˫���鿴
		function ShowView(){
			
			var pk_value=curr_row.getElementsByTagName('input')[0].value;
			
			var xh=pk_value.split("!!shen!!")[0];
			
			var jlsj=pk_value.split("!!shen!!")[2];
				
			var gyjllbdm=pk_value.split("!!shen!!")[1];
			
			var url="gyglnew_gyjlgl.do?method=gyjlcxView&act=clview";
			
			url+="&xh="+xh+"&wjsj="+jlsj+"&gyjllbdm="+gyjllbdm;

			//showTopWin(url,800,600);
			showDialog("�鿴ѧ����Ԣ������Ϣ", 800,500,url);
		}


		//��������
		function saveShzt(){
			var cljg=jQuery("#cljg").val();
			if (cljg == null || cljg == "") {
				alertInfo("��ѡ������!",function(){return false;});
				return false;
			} 
			if (jQuery("#dcqk").val()==null ||jQuery("#dcqk").val()=='') {
				alertInfo("����д���������",function(){return false;});
				return false;
			}
		//	confirmInfo("ȷ��Ҫ�����ѹ�ѡ�ļ�¼��?",function(tag){

		//		if(tag=="ok"){
					var array = document.getElementsByName("div_pkValue");
					var pk = "";
					for (var i=0;i<array.length;i++) {
						if (array[i].checked) {
							pk+= array[i].value;
							pk+="!!@@";
						}
					}
					var url="gyjl_gyjlglnew_ajax.do?method=gyjlxxPlcl";	
					var parameter={}
					parameter["pkValue"]=pk;
					parameter["cljg"]=escape(jQuery("#cljg").val());
					parameter["dcqk"]=escape(jQuery("#dcqk").val());
					parameter["ylzd1"]=jQuery("#ylzd1").val();						
					jQuery.ajaxSetup({async:false});	
					jQuery.post(url,
						parameter,
						function(result){
							alertInfo(result,function(tag){
								if(tag=="ok"){
									closeWindown();
									searchRs();
								}
							});
						}
					);
				//}else {
					return false;
				//}
			//});
		}

		function cancelCl(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len == 1){	
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var temp = jQuery(array[i]).parent().parent().find("td");
					var cljg = temp.eq(7).text();
					var shzt = temp.eq(8).text();
					if (shzt != 'δ���'&&shzt!='�˻�'){
						alertInfo("����˵ļ�¼���ܳ�����");
						return false;
					}else if (cljg == 'δ����'){
						alertInfo("δ����ļ�¼���ܳ�����");
						return false;
					}else{
						var pkValue = temp.eq(0).find("input[type='checkbox']").val();
						str += pkValue+"!!@@";
					}
				}
				confirmInfo("��ȷ��Ҫ����������",function(tag){
					if(tag=="ok"){
						var parameter={}
						var url="gyjl_gyjlglnew.do?method=gyjlCancelCl";	
						parameter["str"]=str;							
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
				alertInfo("��ѡ��һ����Ҫ���������ݣ�",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
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


		<html:form action="/gyjl_gyjlglnew" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="gyjlcl();return false;" class="btn_sh"> ���� </a></li>
						<li><a href="javascript:void(0);" onclick="cancelCl();return false;" class="btn_qxsh">����</a></li>
                        <%--��ý���Ի���ť  ֻ��zf01ӵ��ɾ��Ȩ��--%>
						<logic:equal name="xxdm" value="11647">
							<logic:equal name="userName" value="zf01">
								<li><a href="#" onclick="del();return false;" class="btn_sh"> ɾ�� </a></li>
							</logic:equal>
						</logic:equal>
							
					</ul>
				</div>
				</logic:equal>
				<!-- ��ť end-->
				
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
					<span> ��ѯ���&nbsp;&nbsp;<font color="blue">˫����¼�ɲ鿴��ϸ��Ϣ;</font></span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyjlxxglNewForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="shDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��������Ԣ������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
								<th width="30%">
									<font color="red">*</font>������
								</th>
								<td width="70%" colspan="3">
									<html:select property="cljg" style="width:150px" styleId="cljg">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="cflbList" property="gyjlcfdm" labelProperty="gyjlcfmc" />
									</html:select>
								</td>
								</tr>
							<logic:equal value="13033" name="xxdm">
								<tr>
									<th width="20%">
										�⳥���
									</th>
									<td align="left" width="30%" colspan="3">
										<html:text property="ylzd1" styleId="ylzd1" style="width:150px" maxlength="10" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')"></html:text>&nbsp;&nbsp;Ԫ&nbsp;&nbsp;
									</td>
								</tr>
								</logic:equal>
							<tr>
								<th width="30%">
									<font color="red">*</font>�������<br/>(<font color="blue">��¼��500��</font>)
								</th>
								<td width="70%" >
									<html:textarea property="dcqk" rows="4" styleId="dcqk" style="word-break:break-all;width:97%" onblur="chLeng(this,500);">
									</html:textarea>
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
										<button type="button" name="ȷ��" onclick="saveShzt();return false;">
											����
										</button>
										<button type="button" name="ȡ��" onclick="Close()" id="buttonClose">
											ȡ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
	</body>
</html>