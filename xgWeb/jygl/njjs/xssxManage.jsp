<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
			jQuery(function(){
				//��ѯ
				jQuery("#search_go").click(function(){allNotEmpThenGo("njjs_jygl.do?method=xssxManage");});
				
				//����
				jQuery("#btn_cz").click(function(){searchReset();});
				
				//��ҵ���ϱ�
				jQuery("a[class='btn_xg']").click(function(){
					modi('njjs_jygl.do?method=xssxUpdate',800,600);return false;
				});
				
					//��ҵ���ϱ�
				jQuery("a[class='btn_sc']").click(function(){
					batchData("primary_cbv",'njjs_jygl.do?method=xssxManage&doType=del',"del");return false;
				});
				
				//�꼶
				jQuery("#nj").change(function(){
					initZyList();
					initBjList();
				});
				
				//ѧԺ
				jQuery("#xy").change(function(){
					initZyList();
					initBjList();
				});
				
				//רҵ
				jQuery("#zy").change(function(){
					initBjList();
				});
			});
			
			function modi(url,h,w){
				if(curr_row != null){
					if(curr_row.getElementsByTagName('input')[1].value == "��"){
						url = url + "&sfcz=no";
					}else {
						url = url + "&sfcz=yes";
					}
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
					return true;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
			}

			function exp(){
				if(jQuery("#bj").val()!=""){					
					document.forms[0].action = "njjs_jygl.do?method=xssxExp";
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";
				}else{
					alertInfo("�������밴�༶������");
					return false;
				}
			}
			jQuery(function(){
				disXy();
			})
		</script>
		
	</head>
	<body>
		<html:form action="/njjs_jygl" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			
			<input type="hidden" id="userType" value="${user.userStatus }" />
			<input type="hidden" name="isFdy" value="${isFdy }"/>
			<input type="hidden" name="isBzr" value="${isBzr }" />
			<input type="hidden" name="userName" value="${user.userName }"/>
			
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>			
			
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox" id="dgncz">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_xg">ѧ��ʵϰ¼��</a></li>
							<li><a href="#" class="btn_sc">ѧ��ʵϰɾ��</a></li>
							<li>
								<a href="#" class="btn_dc" onclick="exp();return false;">����</a>
							</li>
						</ul>
					</div>
				</div>
			</logic:equal>

			<div class="searchtab">
				<table>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button class="btn_cx" id="search_go">
										��ѯ
									</button>
									<button class="btn_cz" id="btn_cz">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								<html:select property="nj" styleId="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:text property="xh" styleId="xh" />
							</td>
							<th>
								����
							</th>
							<td>
								<html:text property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" styleId="xy" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:select property="zydm" styleId="zy" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								�༶
							</th>
							<td>
								<html:select property="bjdm" styleId="bj" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>�Ƿ���¼��</th>
							<td>
								<html:select property="sfsb">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<td colspan="4"></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫����¼�鿴��ϸ��Ϣ</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" disabled="disabled"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit.en }" onclick="tableSort(this)">
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="pk" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="modi('njjs_jygl.do?method=xssxUpdate&doType=view',800,600);">
									<td> 
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="primary_cbv" value="${v }"/>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="6" length="1">
											<input type="hidden" value="${v }"/>
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="0">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)request.getAttribute("pageSize");
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<!--��ҳ��ʾ-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=njjsJyglForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
