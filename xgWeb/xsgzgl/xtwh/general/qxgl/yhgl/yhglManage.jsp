<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 		
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxSearch";
			var ie = "ie";
		
			var parameter = {"ie":ie};
			
			jQuery("select,input",jQuery("#tbody_search_query")).each(function(){				
				parameter["str_"+jQuery(this).attr("name")]=escape(jQuery(this).val());
			});
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchGo(url,parameter);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
		}
		
		
		//������Ȩ
		function gnsq(){

			var url = "xtwh_qxgl_yhgl.do?method=yhglGnsq";			
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			var flag = true;
			
			if(len==1){
				jQuery.ajaxSetup({async:false});
				jQuery.post("xtwh_qxgl_yhgl_ajax.do?method=getYhxx",{yhm:jQuery("[name=primarykey_checkVal]:checked").val()},function(data){
					if(data.zdm == "" || data.zdm == null){						
						alertError("���û���δ�����û��飬���ȷ����û�����ٽ��й�����Ȩ��");
					}else{
						jQuery("#pkValue").val(jQuery("[name=primarykey_checkVal]:checked").val());
						document.forms[0].action=url;
						document.forms[0].submit();
					}		
				},'json');
				jQuery.ajaxSetup({async:true});
			}else {				
				alertInfo("�빴ѡһ����Ҫ�޸ĵ����ݣ�");
			}
		}

		//����״̬����
		function qy(zt){
			
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhty";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			var ifGly = "";
			var ifQy = "";	
			if(n>0){
				var i = 0;
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){
					if(jQuery(this).val() == "zf01"){
						ifGly = "yes";
						return false;
					}else{	
						jQuery.ajaxSetup({async:false});
						jQuery.post("xtwh_qxgl_yhgl_ajax.do?method=getYhxx",{yhm:jQuery(this).val()},function(data){
							if(data.qx == "0"){
								ifQy = "yes";
								return false;
							}
						},'json');
						jQuery.ajaxSetup({async:true});
						array[i] = escape(jQuery(this).val());
					}
				});
				if(ifGly == "yes"){
					alertError("zf01ΪϵͳĬ���û�������ͣ�ã�");
					return false; 
				}else if(ifQy == "yes"){
					alertError("��ͣ�õ��û���");
					return false; 
				}else{
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
					parameter["str_qx"]=zt;
					if(zt=='0'){
						parameter["str_zdm"]='';
					}
					confirmInfo("ȷ��Ҫͣ����ѡ�û���?",function(ok){
						if(ok=="ok"){
							jQuery.post(url,parameter,function(result){
								alertInfo(result+"��");
								hiddenMessage(true,true);
								searchRs();		
							});					
						}else{
							return false;
						}
					});
				}
			}else {				
				alertInfo("�빴ѡ��Ҫͣ�õ��û���");
				return false;
			}
		}

		//�Ƿ�����˼���ɼ�
		function szkj(zt){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=szkj";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			var ifGly = "";
			var ifQy = "";	
			if(n>0){
				var i = 0;
				var parameter = {};
				var zghs = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){
					if(jQuery(this).val() == "zf01"){
						ifGly = "yes";
						return false;
					}else{	
						zghs.push(jQuery(this).val());
					}
				});
				if(ifGly == "yes"){
					alertError("zf01ΪϵͳĬ���û�������ͣ�ã�");
					return false; 
				}else{
					confirmInfo("ȷ��Ҫ������ѡ�û���?",function(ok){
						if(ok=="ok"){
							jQuery.post(url,
							{sfbl:zt,
							 zghs:zghs.toString()
							 },function(result){
								alertInfo(result+"��");
								hiddenMessage(true,true);
								searchRs();		
							});					
						}else{
							return false;
						}
					});
				}
			}else {				
				alertInfo("���ȹ�ѡ�û���");
				return false;
			}
		}
		jQuery(function(){
			onShow();
		});

		</script>
	</head>
	<body >
		<input type="hidden" id="pksPlHidden" value="" />
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >ʹ�ð���</a>
			</p>
<%--			<p class="help">--%>
<%--				<a href="#" onclick="return false;" onmouseover="showHelpDiv()"--%>
<%--					onmouseout="showHelpDiv()"> ��������</a>--%>
<%--				<img src="<%=stylePath%>/images/ico_new02.gif" />--%>
<%--			</p>--%>
		</div>
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						1������״̬Ϊ�����á����û���������¼ϵͳ����ͣ�á����û��޷���¼��
						2�����û����÷���󣬸��û��Զ�Ϊ����״̬��
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
		<!-- ��ʾ��Ϣ end-->

		<!-- ��ʾ��Ϣ end-->
<%--		<div class="prompt">--%>
<%--			<h3>--%>
<%--				<span>ϵͳ��ʾ��</span>--%>
<%--			</h3>--%>
<%--			<p>--%>
<%--				����ƶ������Ͻ�--%>
<%--				<font color="blue">��������</font>���ɲ鿴��ģ������˵����--%>
<%--				</br>--%>
<%--				<span id="div_help" style="display: none"> </span>--%>
<%--			</p>--%>
<%--		</div>--%>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/xtwh_qxgl_yhgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<%-- 
						<li><a href="#" onclick="viewTempDiv('�û���Ϣ����','addYhxx',350, 480);" class="btn_zj">����</a></li>
						<li><a href="#" onclick="updateYhxx('show');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="deleteYhxx();return false;" class="btn_sc">ɾ��</a></li>						
						--%>
						<li><a href="#" onclick="yhfz('show');return false;" class="btn_xg">����</a></li>
						<%-- <li><a href="#" onclick="qy('1');return false;" class="btn_shtg">����</a></li>--%>
						<li><a href="#" onclick="qy('0');return false;" class="btn_shbtg">ͣ��</a></li>
						<li><a href="#" onclick="gnsq();return false;" class="btn_sq">������Ȩ</a></li>					
						<li><a href="#" onclick="mmcsh('show');return false;" class="btn_csh">�����ʼ��</a></li>
						<logic:equal value="12898" name="xxdm">
						<li><a href="#" onclick="szkj('0');return false;" class="btn_shtg">˼���ɼ�</a></li>
						<li><a href="#" onclick="szkj('1');return false;" class="btn_shbtg">˼������</a></li>
						</logic:equal>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="4">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button type="button"  class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody id="tbody_search_query">
							<tr>
								<th>
									�û���
								</th>
								<td>
									<input type="text" name="yhm" id="yhm" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>								
								<th>
									����
								</th>
								<td>
									<input type="text" name="xm" id="xm" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>						
							</tr>
							<tr>
								<th>
									������
								</th>
								<td>
									<html:select property="zdm" style="width:200px" styleId="zdm">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="yhzList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>							
									<html:select property="szbm" style="width:200px" styleId="szbm">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="yjbmList" property="bmdm" labelProperty="bmqc" />
									</html:select>
								</td>	
							</tr>
							<tr>
								<th>
									�Ƿ����
								</th>
								<td>
									<html:select property="sffz" style="width:200px" styleId="sffz">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<th>
									����״̬
								</th>
								<td>
									<html:select property="qx" style="width:200px" styleId="qx">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="1">����</html:option>
										<html:option value="0">ͣ��</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�Ƿ񸨵�Ա
								</th>
								<td>
									<html:select property="sffdy" style="width:200px" styleId="sffdy">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<th>
									�Ƿ������
								</th>
								<td>
									<html:select property="sfbzr" style="width:200px" styleId="sfbzr">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
							</tr>
							<logic:equal value="12898" name="xxdm">
							<tr>
								<th>
									�Ƿ�˼���ɼ�
								</th>
								<td>
									<html:select property="sfbl" style="width:200px" styleId="sfbl">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
							</tr>
							</logic:equal>
						</tbody>
					</table>
				</div>
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
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglNewForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			<%@ include file="yhglCz.jsp"%>

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
