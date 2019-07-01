<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<jsp:directive.page import="java.util.HashMap" />
	<jsp:directive.page import="java.util.List" />
	<jsp:directive.page import="java.util.ArrayList" />
	<head>
		<!-- ͷ�ļ� -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript" src="js/studetailFunction.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type="text/javascript">
		function printOne(){
			var xh = document.getElementById('xh').value;
			var mks = document.getElementsByName('print_mk');

			var print_mk = ""
			for(var i=0; i<mks.length; i++){
				if(mks[i].checked == true){
					print_mk = print_mk + mks[i].value + "split!!";
				}
			}
			
			showOpenWindow("stu_info_details.do?xh="+xh+"&page=print&print_mk="+print_mk,800,600);
		}
	
		</script>
	</head>
	<body onload="getPageInfo($('xsxx_a'))">
		<html:form action="/xsxxJbxx" method="post">
			<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
			<input type="hidden" name="zzxh" id="zzxh" value="${xh }"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" id="titName" name="titName" value="${mrxs }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" name="pk" id="pk" value="${pk}" />
			<!-- ����ʾ�ֶ���Ϣ -->

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ���춯-ѧ���춯�޸�</a>
				</p>
			</div>
			<table width="99%" id="tabdd">
				<tbody>
					<tr>
						<td>
							<logic:iterate name="xsqList" id="xsq">
								<logic:equal name="xsq" property="sfzd" value="��">
									<table class="formlist" border="0" align="center"
										style="width: 98%" id="xsq${xsq.xsqdm }">
										<thead>
											<tr>
												<th colspan="4">
													<span>${xsq.xsqmc }</span>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr id="xsq${xsq.xsqdm }_1_tr">
												<td colspan="4">
													<div id="xsq${xsq.xsqdm }_1_div">
														<table width="100%">
															<!-- ��Ƭ��ʾ���ұ�-->
															<logic:equal name="xsq" property="zpxs" value="��">
																<logic:equal name="xsq" property="zpwz" value="��">
																	<tr>
																		<logic:iterate name="xsq" property="xshxx" id="xsh">
																			<logic:equal name="xsh" property="leftMap.dygl"
																				value="1">
																				<logic:equal name="xsh" property="leftMap.dygh"
																					value="1">
																					<th width="16%">
																						${xsh.leftMap.zdm }
																					</th>
																					<td width="34%" id="td_${xsh.leftMap.zd}">
																						<bean:write name="rs" property="${xsh.leftMap.zd}" />
																					</td>
																				</logic:equal>
																			</logic:equal>
																		</logic:iterate>
																		<!-- �� -->
																		<th width="16%" rowspan="${xsq.zpszhs }">
																			ѧ
																			</br>
																			��
																			</br>
																			��
																			</br>
																			Ƭ
																		</th>
																		<td width="34%" rowspan="${xsq.zpszhs }">
																			<img
																				src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
																				width="96" height="128" />
																		</td>
																	</tr>
																	<logic:iterate name="xsq" property="xshxx" id="xsh"
																		offset="1" length="${xsq.zpszhs-1}">
																		<logic:empty name="xsh" property="leftMap">
																			<tr>
																				<th width="16%">
																					&nbsp;
																				</th>
																				<td width="34%">
																					&nbsp;
																				</td>
																			</tr>
																		</logic:empty>
																		<logic:notEmpty name="xsh" property="leftMap">
																			<tr>
																				<th width="16%">
																					${xsh.leftMap.zdm }
																				</th>
																				<td width="34%" id="td_${xsh.leftMap.zd}">
																					<bean:write name="rs" property="${xsh.leftMap.zd}" />
																				</td>
																			</tr>
																		</logic:notEmpty>
																	</logic:iterate>
																</logic:equal>
																<!-- ��Ƭ��ʾ�����-->
																<logic:equal name="xsq" property="zpwz" value="��">
																	<tr>
																		<!-- �� -->
																		<th width="16%" rowspan="${xsq.zpszhs }">
																			ѧ
																			</br>
																			��
																			</br>
																			��
																			</br>
																			Ƭ
																		</th>
																		<td width="34%" rowspan="${xsq.zpszhs }">
																			<img
																				src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
																				width="96" height="128" />
																		</td>
																		<!-- �� -->
																		<logic:iterate name="xsq" property="xshxx" id="xsh"
																			length="1">
																			<logic:notEmpty name="xsh" property="rightMap">
																				<logic:equal name="xsh" property="rightMap.dygl"
																					value="3">
																					<logic:equal name="xsh" property="rightMap.dygh"
																						value="1">
																						<th width="16%">
																							${xsh.rightMap.zdm }
																						</th>
																						<td width="34%" id="td_${xsh.rightMap.zd}">
																							<bean:write name="rs"
																								property="${xsh.leftMap.zd}" />
																						</td>
																					</logic:equal>
																				</logic:equal>
																			</logic:notEmpty>
																			<logic:empty name="xsh" property="rightMap">
																				<th width="16%">
																					&nbsp;
																				</th>
																				<td width="34%">
																					&nbsp;
																				</td>
																			</logic:empty>
																		</logic:iterate>
																	</tr>
																	<logic:iterate name="xsq" property="xshxx" id="xsh"
																		offset="1" length="${xsq.zpszhs-1}">
																		<logic:empty name="xsh" property="rightMap">
																			<tr>
																				<th width="16%">
																					&nbsp;
																				</th>
																				<td width="34%">
																					&nbsp;
																				</td>
																			</tr>
																		</logic:empty>
																		<logic:notEmpty name="xsh" property="rightMap">
																			<tr>
																				<th width="16%">
																					${xsh.rightMap.zdm}
																				</th>
																				<td width="34%" id="td_${xsh.rightMap.zd}">
																					<bean:write name="rs" property="${xsh.rightMap.zd}" />
																				</td>
																			</tr>
																		</logic:notEmpty>
																	</logic:iterate>
																</logic:equal>

															</logic:equal>
															<logic:iterate name="xsq" property="xshxx" id="xsh"
																indexId="index">
																<logic:empty name="xsh">
																	<tr>
																		<th width="16%">
																			&nbsp;
																		</th>
																		<td width="34%">
																			&nbsp;
																		</td>
																		<th width="16%">
																			&nbsp;
																		</th>
																		<td width="34%">
																			&nbsp;
																		</td>
																	</tr>
																</logic:empty>
																<logic:notEmpty name="xsh">
																	<logic:equal name="xsh" property="sfhb" value="��">
																		<tr>
																			<logic:empty name="xsh" property="leftMap">
																				<th width="16%">
																					&nbsp;
																				</th>
																				<td colspan="3" width="84%">
																					&nbsp;
																				</td>
																			</logic:empty>
																			<logic:notEmpty name="xsh" property="leftMap">
																				<th width="16%">
																					${xsh.leftMap.zdm }
																				</th>
																				<td colspan="3" width="84%"
																					id="td_${xsh.leftMap.zd}">
																					<bean:write name="rs" property="${xsh.leftMap.zd}" />
																				</td>
																			</logic:notEmpty>
																		</tr>
																	</logic:equal>
																	<logic:equal name="xsh" property="sfhb" value="��">
																		<tr>
																			<logic:empty name="xsh" property="leftMap">
																				<th width="16%">
																					&nbsp;
																				</th>
																				<td width="34%">
																					&nbsp;
																				</td>
																			</logic:empty>
																			<logic:notEmpty name="xsh" property="leftMap">
																				<th width="16%">
																					${xsh.leftMap.zdm }
																				</th>
																				<td width="34%" id="td_${xsh.leftMap.zd}">
																					<bean:write name="rs" property="${xsh.leftMap.zd}" />
																				</td>
																			</logic:notEmpty>
																			<logic:empty name="xsh" property="rightMap">
																				<th width="16%">
																					&nbsp;
																				</th>
																				<td width="34%">
																					&nbsp;
																				</td>
																			</logic:empty>

																			<logic:notEmpty name="xsh" property="rightMap">
																				<th width="16%">
																					<logic:equal name="xsh" property="rightMap.wkxz"
																						value="����Ϊ��">
																						<font color="red">*</font>
																					</logic:equal>
																					${xsh.rightMap.zdm }
																				</th>
																				<td width="34%" id="td_${xsh.rightMap.zd}">
																					<bean:write name="rs" property="${xsh.rightMap.zd}" />
																				</td>
																			</logic:notEmpty>
																		</tr>
																	</logic:equal>
																</logic:notEmpty>
															</logic:iterate>
														</table>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</logic:equal>
							</logic:iterate>
						</td>
					</tr>
				</tbody>
			</table>
		
		<div class="menu" id="nav">
			<div class="compTab" id="card" style="position:relative">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<logic:notEmpty name="pages">
							<logic:iterate id="card" name="pages" scope="request" indexId="s">							
								<li id="${card.en}li">
									<a href="#" onclick="getPageInfo(this);return false;" id="${card.en}_a">
										<span>${card.cn}</span>
									</a>
								</li>
							</logic:iterate>						
						</logic:notEmpty>
					</ul>	
				</div>
				<div class="morediv" id="morediv" style="position:absolute;top:0px;right:3px;text-align:center"
				onmousemove="javascript:document.getElementById('morelist').style.display='block'"
				onmouseout="javascript:document.getElementById('morelist').style.display='none'">
				<p class="more"></p>
				<div class="morelist" id="morelist" style="display:none;">
					<!--[if lte IE 6]><iframe class=navbug></iframe><![endif]-->
					<ul id="dropDownUl">
					</ul>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			var sumWidth = 0;
			var splitPositionNo = 0;
			var liDropDownArr = new Array();
			var liDropDownArr2 = new Array();
			var liArr = ele('ul1').children;
			var flag = true;
			
			for(var j=0; j<liArr.length+1; j++){
				if(liArr[j] && $(liArr[j].id)){
					sumWidth += $(liArr[j].id).getWidth();
					if(sumWidth > $('card').getWidth()-300){//ȷ���ֽ�li���
						flag = false;
				         splitPositionNo = j;
				         for(var k=j;;k++){
				            if(liArr[k] && $(liArr[k].id)){//ȷ��
				                liDropDownArr.push($((liArr[k].id)));//����li����
				                
				                $((liArr[k].id)).remove();//ɾ��ԭ����li����
				             }else{
				             	if(liDropDownArr.length>0){
			  	 					for(var p=0;p<liDropDownArr.length;p++){
			     					 $('dropDownUl').insert(liDropDownArr[p]);
			     					}
		 						}
		 						break;
				             }
				         }
					}
				}
			}
			
			if(flag && $("morediv")){
				$("morediv").className="";
			}
		
		</script>
		<!--ѧ��������Ϣ-->
		<%@ include file="/xsxx/comm/qtxx/qtxx.jsp"%>
		
		<div id="tmpDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2"><span>�ɴ�ӡģ��</span></th>
							</tr>
						</thead>
						<tr>
							<logic:iterate id="card" name="pages" indexId="index">
								<td>
									${card.cn }&nbsp;&nbsp;&nbsp;
									<logic:equal name="card" property="en" value="xsxx">
										<input type="checkbox" name="print_mk" value="${card.en }" checked disabled="true"/>
										<input type="hidden" name="print_mk" value="${card.en }" />
									</logic:equal>
									<logic:notEqual name="card" property="en" value="xsxx">
										<input type="checkbox" name="print_mk" value="${card.en }"/>
									</logic:notEqual>
								</td>
								<%if(index>0 && index%2==1){%>
									</tr>
								<%} %>
							</logic:iterate>
							<%if(((List)request.getAttribute("pages")).size() == 0){ %>
								<td colspan="2" align="center"><b>�޿ɴ�ӡģ�飡</b></td>
								</tr>
							<%} %>
							<%if(((List)request.getAttribute("pages")).size()%2 != 0 && ((List)request.getAttribute("pages")).size() > 0){%>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
								</tr>
							<%} %>
						<tr>
							<td colspan='2' align='right'>
							<button type="button" onclick='printOne()'>ȷ��</button>
							</td>
						</tr>
					</table>
				</div>
		</div>
		
		<table class="formlist" border="0" align="center" style="width: 100%">
		<tfoot>
		<tr><td>
			<div class="btn">
				<logic:notEqual value="stu" name="userType">
					<button type="button" id="buttonUp" style="width:80px"
						onclick="nextOrUp()">
						��һ��
					</button>			
					<button type="button" id="buttonNext" style="width:80px"
						onclick="nextOrUp('N')">
						��һ��
					</button>	
				</logic:notEqual>
				<button type="button" onclick="viewTempDiv('ѡ���ӡģ��','tmpDiv',400,350);window.scroll(0,0);" style="width:80px"
					id="buttonSave">
					�� ӡ
				</button>
				<logic:notEqual value="zjck" name="doType">
				<button type="button" onclick="window.close();return false;" style="width:80px;"
					id="buttonClose">
					�� ��
				</button>
				</logic:notEqual>
			</div>
			</td></tr>
		</tfoot>
		</table>
		</html:form>
		<logic:notEqual value="stu" name="userType">
		<!-- ��һ������һ�����ٽ��ʱ����Ӧ��ť�ҵ� -->
		<script type="text/javascript" defer>
			if (!window.dialogArguments){
				var rowIndex = opener.curr_row.rowIndex;
				var rowsLength = opener.$("rsTable").rows.length;
				if (1==rowIndex) {
					$("buttonUp").disabled=true;
				} 
				
				if (rowsLength-1 == rowIndex 
							|| opener.$("rsTable").rows[rowIndex+1].getElementsByTagName('input')[0].value==""){
					$("buttonNext").disabled=true;
				}
			} else {
				$("buttonUp").style.display='none';
				$("buttonNext").style.display='none';
			}
		</script>
		</logic:notEqual>
		<!-- ����ҳ��رպ�Ѳ�ѯҳ���ѡ������ʽȥ�� -->
		<script type="text/javascript">
			function windowClose(){
				if (!window.dialogArguments && "stu" != $('userType').value){
					var row = opener.$("rsTable").rows[rowIndex];
					row.style.backgroundColor = obj_bgc;
					row == null;
				}
			}
		</script>
	</body>
</html>
