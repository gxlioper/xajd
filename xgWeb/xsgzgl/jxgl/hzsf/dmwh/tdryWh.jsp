<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.qgzx.jcdmwh.QgzxJcdmwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		function searchRs(){
			var url = "jxgl_dmwh_ajax.do?method=tdryWh";
			var ie = "10.0";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}
		//��λ����div���� ���ӡ��޸�
		var doType;
		function tdryDiv(type,topMsg){
			var len=jQuery("[name=div_pkValue]:checked").length;
			$("tdrydm").value="";
			$("tdrymc").value="";
			doType=type;
			if(type=="update"){
				if(len==1){
					var pkValue=jQuery("[name=div_pkValue]:checked").val();
					var message = checkGrry(pkValue);
					if("true"!=message){
						alertInfo(message,function(tag){
							if(tag=="ok"){
								return false;
							}
						});
						return false;
					}
					$("tdrydm").value=jQuery("[name=div_pkValue]:checked").val();
					$("tdrymc").value=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(3).text();
				}else{
					alertInfo("�빴ѡһ����¼�����޸ģ�");
					return false;
				}
			}
			tipsWindown(topMsg,"id:tempDiv","350","120","true","","true","id");
			jQuery("#tdrymc").focus();
		}
		//div����
		function DivSave(){
			if($("tdrymc").value.trim()==""){
				alertInfo("�������Ŷ��������ƣ�");
				return false;
			}
			var parameter={}
			var url="jxgl_dmwh_ajax.do?method=tdryBc&doType="+doType;
			parameter["tdrydm"]=escape(jQuery("#tdrydm").val());
			parameter["tdrymc"]=escape(jQuery("#tdrymc").val());						
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
					if("����ɹ�"==result){
						alertInfo(result,function(tag){
							if(tag=="ok"){
								closeWindown();
								onShow();
							}
						});
					}else{
						alertInfo(result);
					}
				}
			);
			jQuery.ajaxSetup({async:true});
		}

		//ɾ��
		function tdrySc(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
				var message = checkGrry(str);
				if("true"!=message){
					alertInfo(message,function(tag){
						if(tag=="ok"){
							return false;
						}
					});
					return false;
				}
				confirmInfo("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��",function(tag){
					if(tag=="ok"){
						var parameter={}
						var url="jxgl_dmwh_ajax.do?method=tdrySc";	
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
		//��֤�޸�ɾ����Ϣ
		function checkGrry(str){
			var data = "true";
			var parameter={}
			var url="jxgl_dmwh_ajax.do?method=checkTdry";	
			parameter["pkValue"]=str;							
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
					data = result;
				}
			);
			jQuery.ajaxSetup({async:true});
			return data;
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
		<html:form action="/jxgl_dmwh" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="tdryDiv('add','�����Ŷ�����');return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="tdryDiv('update','�޸��Ŷ�����');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="tdrySc();return false;" class="btn_sc">ɾ��</a></li>
						</logic:equal>
						<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">��������</a></li>
					</ul>
				</div>
			<div class="comp_title">
		      <ul>
		      	<li><a href="jxgl_dmwh.do?method=grryWh"><span>������������</span></a></li>
		        <li class="ha"><a href="#"><span>�Ŷ���������</span></a></li>
		      </ul>
		    </div>
			<div style="display: none;">
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
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
					page="/sjcz/turnpage.jsp?form=jxglDmwhForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�Ŷ���������ά��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>�Ŷ���������
								</th>
								<td>
									<input type="hidden" id="tdrydm" name="tdrydm"/>
									<input type="text" id="tdrymc" name="tdrymc" maxlength="50"/>
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
										<button type="button" name="����" onclick="DivSave()">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="closeWindown();return false;">
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
