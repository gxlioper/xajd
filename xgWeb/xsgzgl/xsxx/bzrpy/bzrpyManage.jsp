<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		function searchRs(){
			
			var url = "/xgxt/xsxx_bzrpy.do?method=bzrpyManage";
			
			showTips('���ݲ�ѯ�У����Ժ�......');
			
			allNotEmpThenGo(url);
		}
		
		function showDgpy(xh){
		
			var url="xsxx_bzrpy_ajax.do?method=showDiv";
			
			//��������
		 	var parameter = {
				"xh":xh,
				"writeAble":"${writeAble}"
			};
		  	
			jQuery("#div_py").load(url,parameter,function(){
			
				var sqqx = jQuery("#sqqx").val();
				
				if(sqqx == "no"){
					$("btn_bc").style.display = "none";
				}
				
				tipsWindown("ϵͳ��ʾ","id:div_py","350","250","true","","true","id");
			});
			
		
		
			// �򿪴���ʱ��DIV�е�����ѧ��ֵ��Ϊ����ѧ��
			jQuery("#div_xh").val(xh);
			tipsWindown("ϵͳ��ʾ","id:div_py","350","200","true","","true","id");
		}
		
		function showPlpy(){
			
			var len=jQuery("[name=pkValue]:checked").length;
			
			if(!len>0){
				alertInfo("�빴ѡ��Ҫ���۵�ѧ��!")
				return false;
			}
			
			var url="xsxx_bzrpy_ajax.do?method=showDiv";
			
			var parameter = {
				"pylx":"plpy",
				"writeAble":"${writeAble}"
			};
			
			jQuery("#div_py").load(url,parameter,function(){
			
				tipsWindown("ϵͳ��ʾ","id:div_py","350","250","true","","true","id");
			});
			
			// ���������鴰��
			tipsWindown("ϵͳ��ʾ","id:div_py","350","200","true","","true","id");
		}
		
		
		function saveDgpy(pyr){
			
			confirmInfo("�ò��������޸�����ѡ��¼�İ���<br/>��������Ϣ���Ƿ�ȷ���޸ģ�",function(tag){
				
				if(tag=="ok"){
					var xh=jQuery("#div_xh").val();
					var pyyj=jQuery("#pyyj").val();
					
					var parameter={}
					
					parameter["xh"]=xh;
					
					parameter["pyyj"]=escape(pyyj);
					
					var url="";
					if(pyr==""){
						url = "xsxx_bzrpy_ajax.do?method=saveOnePy";
					}else{
						url = "xsxx_bzrpy_ajax.do?method=updateOnePy";
					}
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
		
		function savePlpy(){
			
			confirmInfo("�ò��������޸�������ѡ��¼�İ���<br/>��������Ϣ���Ƿ�ȷ���޸ģ�",function(tag){
				
				if(tag=="ok"){
					var pyyj=jQuery("#pyyj").val();
					
					var pkValue=new Array();
					
					var xh=new Array();
					
					jQuery("[name=pkValue]:checked").each(function(i){
						
						pkValue[i]=jQuery(this).val();
						
		 				xh[i]=jQuery(this).parents("tr").children().find("[name=xh]").val();
					
					});
					
					var parameter={}
					
					parameter["str_pyyj"]=escape(pyyj);
					
					parameter["array_xh"]=xh.join("!!array!!");
					
					parameter["array_pkValue"]=pkValue.join("!!array!!");
					
					var url= "xsxx_bzrpy_ajax.do?method=saveBatch";
					
					jQuery.ajaxSetup({async:false});	
					jQuery.post(url,
						parameter,
						function(result){
						
							alertInfo(result);
							
							searchRs();
							
							closeWindown();	
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			});
		}
		</script>
	</head>
	<body>
		<html:form action="/xsxx_bzrpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
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
					<logic:notEmpty name="pysjsz" property="pysj">
					��ѧ������������ֹʱ�䣺${pysjsz.pysj}
					</logic:notEmpty>
					<logic:empty  name="pysjsz" property="pysj">
						��ǰѧ����δ���ð���������ʱ��
					</logic:empty>
					<br />
				</p>
				<a class="close" title="����"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
			
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_sh"
								onclick="showPlpy();return false;"
								class="btn_sh">
								��������
							</a>
						</li>
						<li>
							<a href="#" onclick="impAndChkData();return false;" class="btn_dr">
								����
							</a>
						</li>
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
				</logic:equal>
				<!-- ��ť end-->
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end-->
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							<font color="blue">��ʾ��������ͷ��������;������������Ͽɲ鿴��ϸ</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td width="15px">
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<td 
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td id="cz" >
									����
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s">
									<tr onclick="rowMoreClick(this,'',event);" ondblclick=""
										style="cursor:hand">
										<td width="15px">
											<input type="checkBox" name="pkValue" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"
												<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate> />
											<input type="hidden" name="xh" id="xh"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"
												 />
										</td>
										<logic:iterate id="v" name="s" offset="1" length="4">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="6" length="1">
											<td nowrap title="<logic:iterate id="m" name="s" offset="5" length="1">${m }</logic:iterate>">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="7" length="2">
											<td nowrap title="<logic:iterate id="m" name="s" offset="5" length="1">${m }</logic:iterate>">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td>
											<a href="#" onclick="showDgpy('<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>');return false"><font color="blue">����</font></a>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<%@ include file="/comm/noRows.jsp"%>

						</tbody>
					</table>
					
				</div>
				<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xsxxBzrpyForm"></jsp:include>
				<!-- ������ϢDIV begin -->
				<div id="div_py" style="display:none">

				</div>
				<!-- ������ϢDIV end -->
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:present name="result">
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
	</body>
</html>
