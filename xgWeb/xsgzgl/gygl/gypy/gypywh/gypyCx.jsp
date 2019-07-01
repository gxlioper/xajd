<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>	
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
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

		function searchRs(){
			var url = "gygl_gypywh_ajax.do?method=gypyCx";
			var ie = "10.0";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		
		//����������Ϣ
		function gypyDivSave(){
			if($("xn") && $("xn").value==""){
		 		alertInfo("ѧ�겻��Ϊ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if($("xqdm") && $("xqdm").value==""){
		 		alertInfo("ѧ�ڲ���Ϊ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			if($("pylbdm") && $("pylbdm").value==""){
		 		alertInfo("���������Ϊ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}

			if($("pydx") && $("pydx").value==""){
		 		alertInfo("���Ŷ�����Ϊ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			
			if($("pysj") && $("pysj").value==""){
		 		alertInfo("����ʱ�䲻��Ϊ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			var xn=jQuery("#xn").val();
			var xqdm=jQuery("#xqdm").val();
			var pylbdm=jQuery("#pylbdm").val();
			var pydx = jQuery("#pydx").val();
			var pysj=jQuery("#pysj").val();
			var url="gygl_gypywh.do?method=pyqsCx";
			url+="&xn="+xn;
			url+="&xqdm="+xqdm;
			url+="&pylbdm="+pylbdm;
			url+="&pydx="+pydx;
			url+="&pysj="+pysj;
<%--			showTopWin(url,800,600);--%>
<%--			closeWindown();--%>
			showDialog("��Ԣ����", 800, 560, url);
		}

		function showModi(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				confirmInfo("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��",function(tag){
					if(tag=="ok"){
						var array = jQuery("[name=div_pkValue]:checked");
						var str = "";
						for (var i=0;i<array.length;i++) {
							var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
							str += pkValue+"!!@@!!";
						}
						var parameter={}
						var url="gygl_gypywh_ajax.do?method=gypySc";
						parameter["pkValue"]=str;
						jQuery.ajaxSetup({async:false});	
						jQuery.post(url,
							parameter,
							function(result){
									alertInfo(result);
									searchRs();
							}
						);
						jQuery.ajaxSetup({async:true});
					}
				});
			}else{
				alertInfo("�빴ѡ��Ҫɾ����������Ϣ��",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		var DCCLBH = "gygl_gypywh_ajax.do";//dcclbh,�������ܱ��
		//�Զ��嵼�� ����
		function exportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport(DCCLBH, exportData);
		}

		// ��������
		function exportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gygl_gypywh_ajax.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		/**
		 * ����
		 */
		function importConfig() {
			toImportData("IMPORT_N381101");
			return false;
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


		<html:form action="/gygl_gypywh" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<logic:notEqual name="userType"  value="stu">
					<logic:equal name="writeAble" value="yes">	
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" class="btn_zj" onclick="tipsWindownX('���ӹ�Ԣ����','id:tempDiv','380','250','true','','true','id');return false;">����</a>
								</li>
								<li>
									<a href="#" class="btn_sc" onclick="showModi();return false;">ɾ��</a>
								</li><%--
								 <li><a href="#" onclick="choiceFields();return false;" class="btn_qx">��������</a></li>
								<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">��������</a></li>
								
								--%>
								<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a></li>
								<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
								
							</ul>
						</div>
					</logic:equal>
				</logic:notEqual>
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
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gypywhForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="tempDiv" style="display: none">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>���ӹ�Ԣ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>ѧ��
							</th>
							<td>
								<html:select name="rs" property="xn" styleId="xn" style="width:150px">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>ѧ��
							</th>
							<td>
								<html:select name="rs" property="xqdm" styleId="xqdm" style="width:150px">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�������
							</th>
							<td>
								<html:select property="pylbdm" styleId="pylbdm" style="width:150px">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="pylbList" property="pylbdm" labelProperty="pylbmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>���Ŷ���
							</th>
							<td>
								<html:select property="pydx" styleId="pydx" style="width:150px">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="0">¥��</html:option>
									<html:option value="1">����</html:option>
									<html:option value="2">¥��</html:option>
									<html:option value="3">�㳤</html:option>
									<html:option value="4">���ҳ�</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>����ʱ��
							</th>
							<td>
								<html:text property="pysj" styleId="pysj" onclick="return showCalendar('pysj','y-mm-dd');" onblur="dateFormatChg(this)" readonly="true"></html:text>
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
									<button type="button" id="bcBtn" name="����" onclick="gypyDivSave();">
										��һ��
									</button>
									<button id="gb" type="button" name="�ر�" onclick="closeWindown();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
