<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<%@ include file="/syscommon/pagehead_xg.ini"%>	
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>	
		<script type='text/javascript' src='/xgxt/dwr/interface/xsxxtjDWR.js'></script>			
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>		
		<script language="javascript" src="js/webPrint.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">

		//��ʼ��
		function onShow(){ 
			searchRs();
			
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_xsxx_zxxs_ajax.do?method=searchXsxxZxxs";
			var ie = "ie";
			var sfzxk = jQuery("#sfzxk").val();
			var otherValue = [ie,sfzxk];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			setTimeout("setDivHeight()","1500");
		}
		
		//��ʾѧ����Ϣ�޸�
		function showXsxxUpdate(){

			var num = jQuery("input[name=primarykey_checkVal]:checked").length;
			if(num == 0){
				alertError("�빴ѡ����Ҫ�޸ĵļ�¼");
				return false;
			}else if(num >1){
				alertError("ֻ�ܹ�ѡһ����¼����ȷ��");
				return false;
			}

			var xh = jQuery("input[name=primarykey_checkVal]:checked").eq(0).val();

			var url = "general_xsxx.do?method=xsxxModify&doType=update&xh="+xh;
			showTopWin(url,"800","600");
		}

		function showXsxxDetail(xh){
			var url = "general_xsxx.do?method=xsxxDetailed&doType=detail&xh="+xh;
			showTopWin(url,"800","600");
		}
		
		//��ʾ���ŵ�����
		function showBm(){
			tipsWindown("ϵͳ��ʾ","id:div_bm","350","300","true","","true","id");
		}
		
		// �����ҵ����
		function saveBycl(){
			var xy=jQuery("#xy option:selected").text();
			var zy=jQuery("#zy option:selected").text();
			var bj=jQuery("#bj option:selected").text();
			var nj=jQuery("#nj option:selected").text();
			var message="";
			if(jQuery("#nj").val()!=""){
				message+=nj+"��";
			}
			
			if(jQuery("#xy").val()!=""){
				message+=xy+jQuery("#xbmc").val();
			}
			
			if(jQuery("#zy").val()!=""){
				message+=zy+"רҵ";
			}
			
			if(jQuery("#bj").val()!=""){
				message+=bj+"��";
			}
			
			if(message==""){
				message="ȫУ";
			}
			confirmInfo("�ò��������<font color='red'>"+message+"����ѧ��</font>���б�ҵ�����Ƿ�ȷ������������",function(ok){
				
				if(ok=="ok"){
				
					
						
						//����һ��json����
						var parameter={};
						
						var array=new Array();
						
						//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
						jQuery("[name=xszdArr]:checked").each(function(i){
							if(jQuery(this).val()!="xh" &&jQuery(this).val()!="xm" ){
								//��ȡ���ؼ�name
								array[i]=jQuery(this).val();
							}
						});
						
						//����json����
						parameter["str_xydm"]=escape(jQuery("#xy").val());
						
						parameter["str_zydm"]=escape(jQuery("#zy").val());
						
						parameter["str_bjdm"]=escape(jQuery("#bj").val());
						
						parameter["str_nj"]=escape(jQuery("#nj").val());
						
						//����URL
						var url = "general_xsxx_zxxs_ajax.do?method=saveBycl";
						
						//------------AJAX���� begin -------------
						jQuery.ajaxSetup({async:false});
							jQuery.post(url,
							parameter,
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
						//------------AJAX���� end -------------
					
				}
			});
		}
		
		function jtxxDr(){
			$('realTable').value='xsfzxxb';
			impAndChkData();
			$('realTable').value='xsxxb';
			return false;
		}
		
		function setDivHeight(){
			
			if($("table_rs")){
				
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);
				
			}
			
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
			<logic:notEqual name="sfzxk" value="no">
			<p>
				1.������չʾ������Уѧ����Ϣ���ڸ�ģ���¿��Բ鿴��Уѧ����ϸ��Ϣ��</br>
				2.��ѡһ����¼�������޸Ŀ����޸�ѧ����Ϣ��<br/>
				3.�������ҵ������ť��������ѡ���꼶������<bean:message key="lable.xb" />������רҵ�������༶�����б�ҵ�������û��ѡ������Ĭ��Ϊ����ѧ����</br>
				4.��������������Ϣ����ť���ɽ���׼���õ�ѧ��������Ϣ���뵽ϵͳ�С�</br>
				5.����������ͥ��Ϣ����ť���ɽ���׼���õ�ѧ����ͥ��Ϣ���뵽ϵͳ�С�</br>
				6.����������ֶ����á���ť���ڵ��������ж���Ҫ�������ֶν���ѡ���϶���Ԫ��ɵ�����ʾ˳��</br>
				7.�������������ť���������ԡ������ֶ����á����������õ���Ϣ����ѧ��������Ϣ��</br>
			</p>
			</logic:notEqual>
			<logic:equal name="sfzxk" value="no">
			<p>
				1.������չʾ������ʷѧ����Ϣ���ڸ�ģ���¿��Բ鿴��ʷѧ����ϸ��Ϣ��</br>
			</p>
			</logic:equal>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_xsxx" method="post">
			<input type="hidden" id="realTable" name="realTable" value="xsxxb"/>
			<input type="hidden" id="tableName" name="tableName" value="xg_view_zjjt_xsxxdc"/>
            <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfzxk" id="sfzxk" value="${sfzxk }"/>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:notEqual name="sfzxk" value="no">
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showXsxxUpdate();return false;" class="btn_xg">
									�޸�
								</a>
							</li>
							<li>
								<a href="#" onclick="showBm();return false;" class="btn_csh">
									��ҵ����
								</a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">���������Ϣ</a>
							</li>
							<li>
								<a href="#" onclick="jtxxDr();" class="btn_dr">�����ͥ��Ϣ</a>
							</li>	
						</logic:equal>
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;">
								��������
							</a>
						</li>
						<li>
							<a href="#" onclick="configureExportData();return false;" class="btn_dc">
								����
							</a>
						</li>
						
						
					</ul>
				</div>
				</logic:notEqual>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
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
					<span> ��ѯ���&nbsp;&nbsp; 
							<font color="blue">��ʾ��˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</span>
				</h3>
				<!-- From���� -->
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
					
			<!-- ����ѡ��DIV begin-->
			<div id="div_bm" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��ҵ����</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:200px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:200px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:200px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:200px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="ȷ ��" onclick="saveBycl()">
											ȷ ��
										</button>
										<button type="button" name="ȡ ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- �����ּܷ���ѡ��DIV end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>