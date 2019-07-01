<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>	
		--%>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
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

		//��ѯ
		function searchRs(){
			var url = "jxgl_jxcjgl_ajax.do?method=jxcjCx";
			var ie = "10.0";
			var otherValue = [ie,jQuery("#jxid").val()];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//����
		function jxcjBc(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len>=1){	
				var arr=jQuery("[name=div_pkValue]");
				var str = "";
				var xhs="";
				for(var i =0; i < arr.length; i++){
					var flag=false;
					if(jQuery(arr[i]).prop("checked")==true){
						var td = jQuery(arr[i]).parents("tr").find("td");
						var xh = jQuery(arr[i]).val();
						var cj = "";
						var flag = false;
						for(var j = 6; j < td.length;j++){
							var cjj = jQuery("[name=fz_"+i+"_"+j+"]").val();
							if(cjj!=null&&cjj!=""){
								flag=true;
							}
							cj+=cjj+"!!@@!!";
						}
					if(!flag){
						if(i!=0){
							xhs+=","
							}
						xhs+=xh;
						}
						
						str+=xh+"!!@@!!"+cj+"end!!splitOne!!";
					}
				}
				if(""!=xhs&&"14073"==jQuery("#xxdm").val()){
					alertInfo("ѧ��<font class='blue'>["+xhs+"]</font>"+",������¼��һ���ѵ�ɼ���");
					return false;
					}
				var parameter={}
				var url="jxgl_jxcjgl_ajax.do?method=jxcjBc";
				parameter["pkValue"]=str;
				parameter["jzid"]=jQuery("#jxid").val();
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
							alertInfo(result);
					}
				);
				jQuery.ajaxSetup({async:true});
			}else{
				alertInfo("�빴ѡ��Ҫ����ļ�¼��");
				return false;
			}
			
		}

		//������һ��
		function skipNext(obj){
			//
		}

		//�Զ���ѡ����
		function checkIn(obj){
			jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", true);
		}

		function jxcjDc(){
			var url = "jxgl_jxcjgl_ajax.do?method=jxcjDc";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		
		//ά����ʷ�ɼ�
		function whlscjDiv(){
			tipsWindown("��ѵ��Ϣ","id:tempDiv","320","120","true","","true","id");
		}
		function whlsdcjSave(){
			var url = "jxgl_jxkhgl_jxcjgl.do?jxid="+jQuery("#jxxxdm").val();
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		
		function jxcjglExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("jxgl_jxkhgl_jxcjgl.do", jxcjglExportData);
		}
			
		
			
		// ��������
		function jxcjglExportData() {
			var tid = jQuery("#jxid").val();
			setSearchTj();//���ø߼���ѯ����
			var url = "jxgl_jxcjgl_ajax.do?method=jxcjglExportData&jxid="+tid+"&dcclbh=" + "jxgl_jxkhgl_jxcjgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.��ѵ�ɼ�����Ĭ�϶�<font color="blue">�����еľ�ѵ��Ϣ</font>���гɼ�����<br/>
				2.�Ծ�ѵ�ɼ�<font color="blue">��д��ѡ����</font>����<br/>
				3.ά��Ϊ<font color="blue">������</font>��ѡ�����൱��<font color="blue">ɾ��</font>�óɼ���Ϣ�������ز���<br/>
				4.���<font color="blue">ά����ʷ�ɼ�</font>�ɶ���ʷ�ľ�ѵ�ɼ�����ά��
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/jxgl_jxcjgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" id="jxid" name="jxid" value="${rs.jxid }"/>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_ccg" onclick="jxcjBc();return false;">����</a>
							<a href="#" class="btn_dc" onclick="jxcjglExportConfig();return false;">�Զ��嵼��</a>
							<a href="#" class="btn_xg" onclick="whlscjDiv();return false;">ά����ʷ�ɼ�</a>
						</li>
					</ul>
				</div>
				</logic:equal>
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
					<span>
						��ѵ����:<font class="blue">${rs.jxmc }</font> 
					</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxglJxcjglForm"></jsp:include>
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
								<span>��ѵ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��ѵ����
							</th>
							<td>
								<html:select name="rs" property="jxid" styleId="jxxxdm">
									<html:options collection="jxxxList" property="jxid" labelProperty="jxmc"/>
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" name="�ύ" onclick="whlsdcjSave();">
										�ύ
									</button>
									<button type="button" name="�ر�" onclick="closeWindown();return false;">
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