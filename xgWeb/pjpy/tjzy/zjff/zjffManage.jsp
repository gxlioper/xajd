<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" defer="defer">
		function searchRs(){
			
			var url = "pjpy_tjzy_zjff.do?method=zjffManage";
			
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
		
		
		function initShjg(){

			url = "pjpy_tjzy_zjff_ajax.do?method=pjjgUpdate";
					
			jQuery.ajaxSetup({async:false});	
			
			jQuery.post(url,parameter);
			
			jQuery.ajaxSetup({async:true});
			
		}
		
		function updateZjff(fflx){
			
			var n=jQuery("[name=pkValue]:checked").length;
			
			if(n>0){
				confirmInfo("�ò��������޸Ľ�ѧ�𵽿�״̬<br/>���Ƿ�ȷ���޸ģ�",function(tag){
					
					if(tag=="ok"){
						
						var pkValue=new Array();
						
						var xh=new Array();
						
						jQuery("[name=pkValue]:checked").each(function(i){
							
							pkValue[i]=jQuery(this).val();
						
						});
						
						if(fflx=="yes"){
							fflx="��";
						}else if(fflx=="no"){
							fflx="��";
						}
						
						var parameter={}
						
						parameter["kzzd1"]=escape(fflx);
						
						parameter["pkValue"]=escape(pkValue.join("!!array!!"));
						
						var url= "pjpy_tjzy_zjff_ajax.do?method=updateZjff";
						
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
				
				alertInfo("���ȹ�ѡ��Ҫȷ�ϴ�����Ϣ��",function(){
					return false;
				});
			}
		}
		
		jQuery(function(){
		
			//��ʼ����˽��
			//initShjg();
		});
		</script>
	</head>
	<body>
		<html:form action="/pjpy_tjzy_zjff" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
			
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_sh"
								onclick="updateZjff('yes');return false;"
								class="btn_shtg">
								���ɹ�
							</a>
						</li>
						<li>
							<a href="#" id="btn_sh"
								onclick="updateZjff('no');return false;"
								class="btn_shbtg">
								���ʧ��
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
							<font color="blue">��ʾ��������ͷ��������;</font>
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
										<logic:iterate id="v" name="s" offset="1">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<%@ include file="/comm/noRows.jsp"%>

						</tbody>
					</table>
					
				</div>
				<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=pjpyZjffForm"></jsp:include>
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
