<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script>
		
		
		//��ʾ��������Ϣ
			function showHelpDiv(){

				if($("div_help")){
					if($("div_help").style.display == "none"){
						$("div_help").style.display = "";
					}else{
						$("div_help").style.display = "none";
					}
				}
				
			}
		</script>
	</head>

	<body>
		<html:form action="/pjpy_ty_tjsz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��������-�����ۺ�����-��������</a>
				</p>
				
			<!-- ���߰��� -->
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
			<!-- ���߰��� end -->
			
			<!-- ��ع��� -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">��ع���</a>
			</p>
			<!-- ��ع��� end-->
			</div>

			<!-- ��ʾ��Ϣ-->
			<div class="prompt">
				<h3>
					<span>�������ڣ�</span>
				</h3>
				<p>
					����ѧ��(${pjxtszModel.pjxn })&nbsp;&nbsp;
					����ѧ��(${pjxtszModel.pjxqmc })&nbsp;&nbsp;
					�������(${pjxtszModel.pjnd })&nbsp;&nbsp;
				</p>
			</div>
			<!-- ��ʾ��Ϣ end-->		
	<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.��ģ�������������Ŀ�����á��޸ġ�ɾ���Ĺ��ܡ����������û�ý�ѧ��ĸ���������<br/>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_rssz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function25.png" />
							<p>������������</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_jdsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function71.png" />
							<p>��Ŀ�������</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_tzfwsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function24.png" />
							<p>��Ŀ������Χ����</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_sjsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function60.png" />
							<p>ʱ������</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->

			<div class="toolbox">
				<!-- ��ť -->

					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_sz" onclick="showTopWin('pjpy_ty_tjsz.do?method=tjszCopyUpdate',750,600);return false;" id="btn_dr">����</a>
							</li>
							<li>
								<a href="#" class="btn_sc" onclick="batchData('primarykey_cbv','pjpy_ty_tjsz.do?method=tjszManage&doType=del','del');return false;" id="btn_sc">ɾ��</a>
							</li>
							<!-- <li>
								<a href="#" class="btn_sz"
									onclick="showTopWin('pjpy_ty_tjsz.do?method=tjxz',650,500);return false;"
									id="btn_dr">����</a>
							</li> -->
						</ul>
					</div>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<%--<div class="bz">
										<label>
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>��������</strong>
										</label>
									</div>
									--%>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('pjpy_ty_tjsz.do?method=tjszManage&doType=query')">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>��Ŀ����</th>
								<td>
									<html:select property="queryequals_xmlx" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xmlxList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<th>��Ŀ����</th>
								<td>
									<html:select property="queryequals_xmxz" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xmxzList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
								<th>��Ŀ��Χ</th>
								<td>
									<html:select property="queryequals_xmfw" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>��Ŀ����</th>
								<td>
									<html:text property="querylike_xmmc" style="width:155px"></html:text>
								</td>
								<th></th>
								<td></td>
								<th></th>
								<td></td> 
							</tr>
						</tbody>
					</table>
				</div>
			</div>


			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"
												/>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum")- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
		    <!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=pjpyTjszForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<logic:present name="message">
				<script>
					alertInfo("${message}");
				</script>
			</logic:present>

		</html:form>
	</body>
</html>
