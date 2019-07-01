<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		function searchRs(){
		
			allNotEmpThenGo('/xgxt/zhcpZczf.do?method=zcfManage');
		}
		
		function showPdbxQd(){
			var pkValue=curr_row.getElementsByTagName('input')[0].value;
			showTopWin("/xgxt/zhcpPdbx.do?method=pdbxQd&pkValue="+pkValue,800,600);
		}
		
		
		
		function lxgn(){
			showTopWin("/xgxt/zhcpZczf.do?method=kindChoose",800,500);
		}
		
		function zhszcpjs(){
			var jslxArr=document.getElementsByName("jslx");
			var pmjsArr=document.getElementsByName("pmjs");
			var blog=false;
			var flag=false;
			var bool=false;
			for(i=0;i<jslxArr.length;i++){
			
				
				if(jslxArr[i].checked){
					blog=true;
					if(jslxArr[i].value=="pmjs"){
						bool=true; 
						for(j=0;j<pmjsArr.length;j++){
							if(pmjsArr[j].checked){
								flag=true;
								break;
							}
						}
					}
				}
			}
			
			if(!blog){
				alertInfo("��ѡ����Ҫ��������ͣ�");
				return false;
			}
			
			if(!flag && bool){
				alertInfo("��ѡ����Ҫ������������ͣ�");
				return false;
			}
		
			confirmInfo("����ָ�����۲���Ϣ���м��㣬�Ƿ������",function(t){

				if(t=="ok"){
					$("prompt").innerHTML="���ڽ����۲���Ϣ���㣬���Ժ�!";
					showLoadPage();
					refreshForm("/xgxt/zhcpZczf.do?method=zcfManage&doType=zcjs");
					hiddenMessage(true,true);
				}
				
			});
		}
		
		
		//��ʾ����ҳ��
		function showLoadPage(){
			//�๦�ܲ���
			$("dgncz").style.display="none";
			//��ѯ���
			$("cxjg").style.display="none";
			//��ʾ
			$("page_loading").style.display="";
			//������ʾ��Ϣ
			
		}
		
		function showDiv(){
			viewTempDiv("���㷽ʽѡ��","zcfjsDiv",350,200);
		}
		
		function showPm(){
			//ѡ������
			if($("zcpm").value!="1" && $("zcpm").value!="2" && $("zcpm").value!="3"){
			if($("jslx_pm").checked){
				$("pmjs").style.display="";
			}else{
				$("pmjs").style.display="none";
			}
			}
		}
		
		function showZypm(){
			//ѡ������
			if($("zypm").value!="1" && $("zypm").value!="2" && $("zypm").value!="3"){
				if($("jslx_zypm").checked){
					$("zypmjs").style.display="";
				}else{
					$("zypmjs").style.display="none";
				}
			}
		}
		
		jQuery(function(){
		
			if($("zd30")){
				var zd30=$("zd30").name;
			
				jQuery("."+zd30).each(function(){
					var text=jQuery(this).text();
						
					var title=jQuery(this).attr("title");
					
					var title=text;
					if(text.length>10){
						title=text;
						text=text.substr(0,10)+"...";
					}
					jQuery(this).attr("title",title);
					jQuery(this).text(text)
				});
			}
		});
		
		function expZccj(){
			jQuery('#select_xy').attr('id','xy');
			jQuery('#select_zy').attr('id','zy');
			jQuery('#select_bj').attr('id','bj');
			viewTempDiv("ѡ���ӡ�༶","expZccj",350,200);
		}
		</script>
	</head>
	<body >
		

		

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
			<!-- ��ع��� -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;"
					onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">��ع���</a>
			</p>
			<!-- ��ع��� end-->
		</div>
		<!-- ���� end-->

		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.�����ѯ�����ʾ�����κ����ݵĻ�������ִ����
				<font color="blue">��ѡ</font>������ͺ��ˡ�
				</br>
				2.�����Ը���
				<font color="blue">��ѡ</font>������������ϣ�������Ľ������
				</br>
				3.�ۺ����ʲ������Լ����������Ҫִ��
				<font color="blue">�ּܷ���</font>��ȡ��
				</br>
				4.���������������仯�Ļ����ۺ����ʲ�������Ҫ
				<font color="blue">���¼���</font>��
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar"
				style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">

					<div class="liucheng_font floatleft">
						<a href="#" onclick="goOtherGnmk('pjpy_zhsz.do');return false;">
							<img src="<%=stylePath%>/images/blue/48-1/Function19.png" />
							<p>
								������������
							</p> </a>
					</div>

					<div class="liucheng_font floatleft">
						<a href="#"
							onclick="goOtherGnmk('zhcp_sjcl_sjdr.do');return false;"> <img
								src="<%=stylePath%>/images/blue/48-1/Function35.png" />
							<p>
								�۲��ά��
							</p> </a>
					</div>

				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->

		<html:form action="/zhcpZczf" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="zypm" id="zypm" value="${zypm }" />
			<input type="hidden" name="zcpm" id="zcpm" value="${zcpm }" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
				
		<div id="expZccj" style="display: none">
			<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span> ��ӡ�༶ѡ�� </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="select_xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="select_zy">
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
								<html:select property="bjdm" style="width:180px" styleId="select_bj">
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
								<div class="btn">
									<button type="button" onclick="expData('zhcpZczf.do?method=getZhcpPrintData&bjdm='+jQuery('#bj').val())">
										ȷ ��
									</button>
									<button type="button" onclick="hiddenMessage(true,true);">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</tfoot>
				</table>
			</div>
		</div>
		

			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showDiv();return false;" class="btn_xg">�ּܷ���</a>
						</li>
						<li>
							<a href="#" onclick="lxgn();return false;" class="btn_zt">��ѡ
							</a>
						</li>
						<logic:equal value="13108" name="xxdm">
							<li>
								<a href="#" onclick='expZccj();' class="btn_dy">��ӡ
								</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="all" value="all" onclick="chec()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
										<td id="<bean:write name="tit" property="en"/>"
											name="td_${index}" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);" ondblclick=""
										style="cursor:hand">
										<td>
											<input type="checkbox" name="primary_key" id="pkV"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"
												<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate> />
										</td>
										<logic:iterate id="v" name="s" offset="1" indexId="cols">
											<td nowrap class="td_${cols}">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=zhcpZczfForm"></jsp:include>
				</logic:notEmpty>
			</div>
		</html:form>
		
		<!-- �ּܷ���ѡ��DIV -->
		<div id="zcfjsDiv" style="display: none">
			<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span> �ۺ����ʲ����ּ��� </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��������
							</th>
							<td>
								<input type="checkbox" name='jslx' id='jslx_zf' value='zfjs'
									checked />
								�۲�ּ���
								<input type="checkbox" name='jslx' id='jslx_pm' value='pmjs'
									onclick="showPm()" />
								�۲���������
								<logic:notEqual name="zypm" value="0">
									<br />
									<input type="checkbox" name='jslx' id='jslx_zypm'
										value='zypmjs' onclick="showZypm()" />������������
								</logic:notEqual>
							</td>
						</tr>
						<tr id="pmjs" style="display:none">
							<th>
								�۲����������
							</th>
							<td>
								<logic:equal name="zcpm" value="1">
									<input type="checkbox" name='pmjs' id='pmjs_xy' value='njxy'
										checked />
								</logic:equal>
								<logic:equal name="zcpm" value="2">
									<input type="checkbox" name='pmjs' id='pmjs_zy' value='njzy'
										checked />
								</logic:equal>
								<logic:equal name="zcpm" value="3">
									<input type="checkbox" name='pmjs' id='pmjs_bj' value='bj'
										checked />
								</logic:equal>
								<logic:equal name="zcpm" value="4">
									<input type="checkbox" name='pmjs' id='pmjs_bj' value='bj'
										checked />�༶����
									<input type="checkbox" name='pmjs' id='pmjs_zy' value='njzy' />�꼶רҵ����
								</logic:equal>
								<logic:equal name="zcpm" value="5">
									<input type="checkbox" name='pmjs' id='pmjs_bj' value='bj'
										checked />�༶����
									<input type="checkbox" name='pmjs' id='pmjs_xy' value='njxy' />�꼶<bean:message key="lable.xb" />����
								</logic:equal>

								<!-- רҵ��ѧԺ����begin  -->
								<logic:equal name="zcpm" value="6">
									<input type="checkbox" name='pmjs' id='pmjs_zy' value='njzy'
										checked />�꼶רҵ����
									<input type="checkbox" name='pmjs' id='pmjs_xy' value='njxy' />�꼶<bean:message key="lable.xb" />����
								</logic:equal>
								<!-- רҵ��ѧԺ����end -->

								<!-- �༶��רҵ��ѧԺ����begin  -->
								<logic:equal name="zcpm" value="7">
									<input type="checkbox" name='pmjs' id='pmjs_bj' value='bj'
										checked />�༶����
									<input type="checkbox" name='pmjs' id='pmjs_zy' value='njzy' />�꼶רҵ����<br />
									<input type="checkbox" name='pmjs' id='pmjs_xy' value='njxy' />�꼶<bean:message key="lable.xb" />����
								</logic:equal>
								<!-- �༶��רҵ��ѧԺ����end  -->
							</td>
						</tr>
						<tr id='zypmjs' style="display:none">
							<th>
								������������
							</th>
							<td>
								<logic:equal name="zypm" value="1">
									<input type="checkbox" name='zypmjs' id='zypmjs_xy'
										value='njxy' checked />
								</logic:equal>
								<logic:equal name="zypm" value="2">
									<input type="checkbox" name='zypmjs' id='zypmjs_zy'
										value='njzy' checked />
								</logic:equal>
								<logic:equal name="zypm" value="3">
									<input type="checkbox" name='zypmjs' id='zypmjs_bj' value='bj'
										checked />
								</logic:equal>

								<logic:equal name="zypm" value="4">
									<input type="checkbox" name='zypmjs' id='zypmjs_bj' value='bj'
										checked />�༶����
									<input type="checkbox" name='zypmjs' id='zypmjs_zy'
										value='njzy' />�꼶רҵ����
								</logic:equal>
								<logic:equal name="zypm" value="5">
									<input type="checkbox" name='zypmjs' id='zypmjs_bj' value='bj'
										checked />�༶����
									<input type="checkbox" name='zypmjs' id='zypmjs_xy'
										value='njxy' />�꼶<bean:message key="lable.xb" />����
								</logic:equal>

								<!-- רҵ��ѧԺ����begin  -->
								<logic:equal name="zypm" value="6">
									<input type="checkbox" name='zypmjs' id='zypmjs_zy'
										value='njzy' checked />�꼶רҵ����
									<input type="checkbox" name='zypmjs' id='zypmjs_xy'
										value='njxy' />�꼶<bean:message key="lable.xb" />����
								</logic:equal>
								<!-- רҵ��ѧԺ����end -->

								<!-- �༶��רҵ��ѧԺ����begin  -->
								<logic:equal name="zypm" value="7">
									<input type="checkbox" name='zypmjs' id='zypmjs_bj' value='bj'
										checked />�༶����
									<input type="checkbox" name='zypmjs' id='zypmjs_zy'
										value='njzy' />�꼶רҵ����<br />
									<input type="checkbox" name='zypmjs' id='zypmjs_xy'
										value='njxy' />�꼶<bean:message key="lable.xb" />����
								</logic:equal>
								<!-- �༶��רҵ��ѧԺ����end  -->
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" onclick="zhszcpjs()">
										ȷ ��
									</button>
									<button type="button" onclick="hiddenMessage(true,true);">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		
		
		
		
	</body>
	<%@ include file="/comm/loading.jsp"%>
	<%@ include file="/comm/other/tsxx.jsp"%>
</html>
