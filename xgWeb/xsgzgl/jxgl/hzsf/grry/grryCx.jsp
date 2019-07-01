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
			var url = "jxgl_grry_ajax.do?method=grryCx";
			var ie = "10.0";
			var xn = jQuery("#xn").val();
 			var nj = jQuery("#nj").val();
 			var grrydm = jQuery("#grrydm").val();
 			var xydm = jQuery("#xy").val();
 			var zydm = jQuery("#zy").val();
 			var bjdm =jQuery("#bj").val();
 			var tuandm = jQuery("#tuandm").val();
 			var yingdm = jQuery("#yingdm").val();
 			var liandm = jQuery("#liandm").val();
 			var xh = escape(jQuery("#xh").val());
 			var xm = escape(jQuery("#xm").val());
 			if(xn==""){
 	 			alertInfo("ѧ�겻��Ϊ��",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
 			}
 	 		var query=xn+"!!splitOne!!"+nj+"!!splitOne!!"+grrydm+"!!splitOne!!"+xydm+"!!splitOne!!"+zydm+"!!splitOne!!";
 	 		query+=bjdm+"!!splitOne!!"+tuandm+"!!splitOne!!"+yingdm+"!!splitOne!!"+liandm+"!!splitOne!!"+xh+"!!splitOne!!"+xm+"!!splitOne!!end";
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
			
			showTopWin('jxgl_grry.do?method=grryZj&xn='+xn,600,360);
		}
		//�޸Ĳ鿴
		function showModi(type){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				var url="jxgl_grry.do?method=grryXg";	
				url+="&doType="+type;	
				url+="&pkValue="+pkValue;
				showTopWin(url,600,360);
			}else{
				alertInfo("�빴ѡһ�����ݲ�����",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		//ɾ��
		function grrySc(){
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
						var url="jxgl_grry_ajax.do?method=grrySc";	
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
		function initTuanList(){
			jQuery.ajaxSetup({async:false});
			var parameter ={};
		    parameter["xn"]=escape(jQuery("#xn").val());
		    parameter["bzdj"]=escape("1");
		    parameter["sjdm"]=escape("");
			jQuery.getJSON('jxgl_grry_ajax.do?method=getTuanYingLianList',parameter,function(data){
				jQuery('#tuandm').empty();
				jQuery('#tuandm').append("<option value=''></option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
						jQuery('#tuandm').append(option);
					}
				}
			});
			jQuery.ajaxSetup({async:true});
		}
		//��ʼ��Ӫ�˵�
		function initYingList(){
			jQuery.ajaxSetup({async:false});
			var parameter ={};
			parameter["xn"]=escape(jQuery("#xn").val());
		    parameter["bzdj"]=escape("2");
		    parameter["sjdm"]=escape(jQuery("#tuandm").val());
			jQuery.getJSON('jxgl_grry_ajax.do?method=getTuanYingLianList',parameter,function(data){
				jQuery('#yingdm').empty();
				jQuery('#yingdm').append("<option value=''></option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
						jQuery('#yingdm').append(option);
					}
				}
			});
			jQuery.ajaxSetup({async:true});
		}
		//��ʼ�����˵�
		function initLianList(){
			jQuery.ajaxSetup({async:false});
			var parameter ={};
			parameter["xn"]=escape(jQuery("#xn").val());
		    parameter["bzdj"]=escape("3");
		    parameter["sjdm"]=escape(jQuery("#yingdm").val());
			jQuery.getJSON('jxgl_grry_ajax.do?method=getTuanYingLianList',parameter,function(data){
				jQuery('#liandm').empty();
				jQuery('#liandm').append("<option value=''></option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].dm + "\">" + data[i].mc + "</option>";
						jQuery('#liandm').append(option);
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
		<html:form action="/jxgl_grry" method="post">
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
						<li><a href="#" onclick="grrySc();return false;" class="btn_sc">ɾ��</a></li>
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
									<button type="button" class="btn_cz" onclick="reset();return false;" id="btn_cz">
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
								<html:select name="rs" property="xn" style="width:150px" styleId="xn" onchange="initTuanList();">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th>�꼶</th>
							<td>
								<html:select property="nj" style="width:150px" styleId="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
							</td>
							<th>��������</th>
							<td>
								<html:select property="grrydm" style="width:150px" styleId="grrydm">
									<html:option value=""></html:option>
									<html:options collection="grryList" property="grrydm" labelProperty="grrymc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								<logic:equal name="userType" value="xy">
									<html:select property="xydm" styleId="xy" disabled="true" value="${userDep }" style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
									<html:hidden property="xydm" value="${userDep }"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
								<html:select property="xydm" styleId="xy"  style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:notEqual>
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:select property="zydm" styleId="zy"  style="width:150px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								�༶
							</th>
							<td>
								<html:select property="bjdm" styleId="bj"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>	
						<tr>
							<th>
								�ż�
							</th>
							<td>
								<html:select property="tuandm" style="width:150px" styleId="tuandm" onchange="initYingList();">
										<html:option value=""></html:option>
										<html:options collection="tuanList" property="dm"
											labelProperty="mc" />
								</html:select>
							</td>
							<th>Ӫ��</th>
							<td>
								<html:select property="yingdm" styleId="yingdm" style="width:150px" onchange="initLianList();">
									<html:option value=""></html:option>
								</html:select>
							</td>
							<th>����</th>
							<td>
								<html:select property="liandm" style="width:150px" styleId="liandm">
									<html:option value=""></html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td><html:text property="xh" styleId="xh"></html:text></td>
							<th>����</th>
							<td><html:text property="xm" styleId="xm"></html:text></td>
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
					page="/sjcz/turnpage.jsp?form=jxglGrryForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
