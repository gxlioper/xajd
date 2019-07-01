<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.qgzx.jcdmwh.QgzxJcdmwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		//��ѯ
		function searchRs(){
			var url = "jxgl_tdry_ajax.do?method=tdryCx";
			var ie = "10.0";
			var xn = jQuery("#xn").val();
			var bzjbdm = jQuery("#bzjbdm").val();
 			var tdrydm = jQuery("#tdrydm").val();
 			var bzdm = jQuery("#bzdm").val();
 			if(xn==""){
 	 			alertInfo("ѧ�겻��Ϊ��",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
 			}
 	 		var query=xn+"!!splitOne!!"+bzjbdm+"!!splitOne!!"+tdrydm+"!!splitOne!!"+bzdm+"!!splitOne!!end";
			var otherValue = [ie,query];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000");
		}
		//����
		function showAdd(){
			var xn = jQuery("#xn").val();
			if(xn==""){
 	 			alertInfo("ѧ�겻��Ϊ��",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
 			}
			showTopWin('jxgl_tdry.do?method=tdryZj&xn='+xn,450,350);
		}
		//�޸Ĳ鿴
		function showModi(type){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				var url="jxgl_tdry.do?method=tdryXg";	
				url+="&doType="+type;	
				url+="&pkValue="+pkValue;
				showTopWin(url,450,350);
			}else{
				alertInfo("�빴ѡһ�����ݲ�����",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		//ɾ��
		function tdrySc(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!splitOne!!";
				}
				confirmInfo("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��",function(tag){
					if(tag=="ok"){
						var parameter={}
						var url="jxgl_tdry_ajax.do?method=tdrySc";	
						parameter["pkValue"]=str;							
						jQuery.ajaxSetup({async:false});	
						jQuery.post(url,
							parameter,
							function(result){
								alertInfo(result,function(tag){
									if(tag=="ok"){
										onShow();
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
		//�˵�����
		function getBzdmList(){
			if($("bzjbdm") && $("bzjbdm").value==""){
				jQuery('#bzdm').empty();
				jQuery('#bzdm').append("<option value=''></option>");
				jQuery("#tr_bzxx").hide();
				return false;
     		}
     		if($("bzjbdm").value=="1"){
         		jQuery("#bzxx").text("�ż�");
     		}else if($("bzjbdm").value=="2"){
         		jQuery("#bzxx").text("Ӫ��");
     		}else if($("bzjbdm").value=="3"){
         		jQuery("#bzxx").text("����");
     		}
			jQuery.ajaxSetup({async:false});
			var parameter ={};
		    parameter["xn"]=escape(jQuery("#xn").val());
		    parameter["bzjbdm"]=escape(jQuery("#bzjbdm").val());
			jQuery.getJSON('jxgl_tdry_ajax.do?method=getBzdmList',parameter,function(data){
				jQuery("#tr_bzxx").show();
				jQuery('#bzdm').empty();
				jQuery('#bzdm').append("<option value=''></option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
						jQuery('#bzdm').append(option);
					}
				}
			});
			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body >
		<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jxgl_tdry" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="showAdd();return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="showModi('update');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="tdrySc();return false;" class="btn_sc">ɾ��</a></li>
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a></li>
						</logic:equal>
						<li><a href="#" onclick="showModi('view');return false;" class="btn_ck">�鿴</a></li>
						<li><a href="#" onclick="choiceFields();return false;" class="btn_sz">��������</a></li>
						<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">��������</a></li>
					</ul>
				</div>
				<div style="display: none;">
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
							<div class="searchtab">
				<table>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" onclick="searchRs();return false;" id="search_go">
										��ѯ
									</button>
									<button type="button" class="btn_cz" onclick="reset();jQuery('#tr_bzxx').hide();return false;" id="btn_cz">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								<html:select name="rs" property="xn" style="width:180px" styleId="xn" onchange="getBzdmList()">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th>���Ƽ���</th>
							<td>
								<html:select property="bzjbdm" style="width:180px" styleId="bzjbdm" onchange="getBzdmList()">
									<html:option value=""></html:option>
									<html:options collection="zjList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>�Ŷ�����</th>
							<td>
								<html:select property="tdrydm" style="width:180px" styleId="tdrydm">
									<html:option value=""></html:option>
									<html:options collection="tdryList" property="tdrydm" labelProperty="tdrymc"/>
								</html:select>
							</td>
						</tr>
						<tr id="tr_bzxx" style="display: none;">
							<th id="bzxx">
							</th>
							<td>
								<html:select property="bzdm" style="width:180px" styleId="bzdm">
									<html:option value=""></html:option>
								</html:select>
							</td>
							<th>
							</th>
							<td>
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
					</tbody>
				</table>
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
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp;</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxglTdryForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
