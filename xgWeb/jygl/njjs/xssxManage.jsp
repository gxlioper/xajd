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
				//查询
				jQuery("#search_go").click(function(){allNotEmpThenGo("njjs_jygl.do?method=xssxManage");});
				
				//重置
				jQuery("#btn_cz").click(function(){searchReset();});
				
				//毕业生上报
				jQuery("a[class='btn_xg']").click(function(){
					modi('njjs_jygl.do?method=xssxUpdate',800,600);return false;
				});
				
					//毕业生上报
				jQuery("a[class='btn_sc']").click(function(){
					batchData("primary_cbv",'njjs_jygl.do?method=xssxManage&doType=del',"del");return false;
				});
				
				//年级
				jQuery("#nj").change(function(){
					initZyList();
					initBjList();
				});
				
				//学院
				jQuery("#xy").change(function(){
					initZyList();
					initBjList();
				});
				
				//专业
				jQuery("#zy").change(function(){
					initBjList();
				});
			});
			
			function modi(url,h,w){
				if(curr_row != null){
					if(curr_row.getElementsByTagName('input')[1].value == "否"){
						url = url + "&sfcz=no";
					}else {
						url = url + "&sfcz=yes";
					}
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
					return true;
				}else{
					alertInfo('请选择要操作的数据行！');
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
					alertInfo("导出必须按班级导出！");
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
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>			
			
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox" id="dgncz">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_xg">学生实习录入</a></li>
							<li><a href="#" class="btn_sc">学生实习删除</a></li>
							<li>
								<a href="#" class="btn_dc" onclick="exp();return false;">导出</a>
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
										查询
									</button>
									<button class="btn_cz" id="btn_cz">
										重置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								年级
							</th>
							<td>
								<html:select property="nj" styleId="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
							</td>
							<th>
								学号
							</th>
							<td>
								<html:text property="xh" styleId="xh" />
							</td>
							<th>
								姓名
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
								专业
							</th>
							<td>
								<html:select property="zydm" styleId="zy" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级
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
							<th>是否已录入</th>
							<td>
								<html:select property="sfsb">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
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
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;双击记录查看详细信息</font>
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
				<!--分页显示-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=njjsJyglForm"></jsp:include>
				<!--分页显示-->
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
