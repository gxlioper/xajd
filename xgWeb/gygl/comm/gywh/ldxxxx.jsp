<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:directive.page import="xgxt.action.Base" />
		<jsp:directive.page import="java.util.HashMap" />
		<jsp:directive.page import="java.util.List" />
		<jsp:directive.page import="java.util.ArrayList" />
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglGywh.js"></script>
		<script>
			//显示楼层内寝室信息 
			function showTbody(obj,objTbody){
				if(obj.className=="up"){
					obj.className="down";
					obj.parentNode.parentNode.className="";
					document.getElementById(objTbody).style.display="";
					
				}else if(obj.className=="down"){
					obj.className="up";
					obj.parentNode.parentNode.className="cur-tr";
					document.getElementById(objTbody).style.display="none";
				}
			}
			
			//显示隐藏域
			function showhid(num){
				var lddm=document.getElementById("lddm_"+num).value;
				var cs=document.getElementById("cs_"+num).value;
				var qsh=document.getElementById("qsh_"+num).value;
				var strArr=[lddm,cs,qsh];
				gyglGywh.getRzryList(strArr,function(data){
					var html="";
					for(i=0;i<data.length;i++){
						var cwh=data[i].cwh;
						var xm=data[i].xm;
						html+="<li><span>"+cwh+"号床位："+xm+"</span></li>";
					}
					if(data.length==0){
						html+="<li><span>该寝室没有学生入住</span></li>"
					}
					$("ul_"+num).innerHTML=html;
				});
			}
			
			function fhldxxwh(){
				refreshForm("/xgxt/gygl_gywh_ldwh.do");
			}
			</script>
		<script src="../../js/ie6comm.js"></script>
		<script> 
			DD_belatedPNG.fix('img,.mainbody,.topframe,.mainframe,.botframe,.menu,.type_mainframe'); 
			</script>
	</head>
	<body>
		<html:form action="/gyglGywh" method="post">
			<input type="hidden" name="czxq" id="czxq" value="${czxq }" />
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}" />
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
				
				<table width="100%" border="0" class="formlist">
					<!-- 楼栋信息 -->
					<thead>
						<tr>
							<th colspan="6">
								<span><bean:message key="lable.ld" />信息</span>
								<div class="btn">
									<button onclick='fhldxxwh()'>
										返 回
									</button>
								</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="6">
								<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp; <bean:message
										key="lable.ld" />代码:${ldxx.lddm}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

									<bean:message key="lable.ld" />名称:${ldxx.ldmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name="czxq" value="是">
								所属<bean:message key="lable.xiaoqu" />:${ldxx.xqmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal> <logic:equal name="czyq" value="是">
								所属<bean:message key="lable.yuanqu" />:${ldxx.yqmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal> 层数:${ldxx.cs}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

									性别限制:${ldxx.xbxd} </font>
							</td>
						</tr>
					</tbody>
					<thead class="thead_title">
						<tr>
							<td colspan="6">
								<span><bean:message key="lable.qs" />信息</span>
							</td>
						</tr>
					</thead>
					<%
							List<HashMap<String, String>> qsxxList = (List<HashMap<String, String>>) request
							.getAttribute("qsxxList");
							List<HashMap<String, String>> ldlcxxList = (List<HashMap<String, String>>) request
							.getAttribute("ldlcxxList");
							int num = 0;
							for (int i = 0; i < ldlcxxList.size(); i++) {
								HashMap<String, String> ldlcMap = ldlcxxList.get(i);
					%>
					<!-- 楼层 标头 -->
					<thead>
						<tr>
							<th style="width:16.5%">
								<a href="#" class="up"
									onclick="showTbody(this,'lc_<%=ldlcMap.get("cs")%>');return false"><%=ldlcMap.get("cs")%>楼</a>
							</th>
							<th colspan="5">
								<em class="chamber">寝室（个）：<%=Base.isNull(ldlcMap.get("qss")) ? "0"
							: ldlcMap.get("qss")%>
								</em>
								<em class="chamber">已分配（个）：<%=Base.isNull(ldlcMap.get("yfps")) ? "0"
							: ldlcMap.get("yfps")%>
								</em>
								<em class="chamber">已入住（人）：<%=Base.isNull(ldlcMap.get("rzrs")) ? "0"
							: ldlcMap.get("rzrs")%>
								</em>
							</th>
						</tr>
					</thead>
					<!-- 楼层 标头 end-->

					<!-- 楼层 内容 -->
					<tbody id='lc_<%=ldlcMap.get("cs")%>' style="display:none;">
						<%
									//楼层寝室数
									int qss = 0;
									if (ldlcMap.get("qss") != null) {
								qss = Integer.parseInt(ldlcMap.get("qss"));
									}

									//该楼栋所有寝室信息
									int len = qsxxList.size();
									int m = 1;
									//对寝室信息进行记数
									int n = 0;
									ArrayList<HashMap<String, String>> qsxx = new ArrayList<HashMap<String, String>>();
									for (int j = 1; j <= len; j++) {
								HashMap<String, String> qsxxMap = qsxxList.get(j - 1);
								if (ldlcMap.get("cs").equalsIgnoreCase(
										qsxxMap.get("cs"))) {
									//保存每行寝室信息
									qsxx.add(qsxxMap);
									n++;
								}

								//当一行有六条记录时
								if (n == 6) {
									//将N清0重新记数
									n = 0;
						%>
						<!-- 打出该行的记录（寝室号） -->
						<tr>
							<%
											for (int k = 0; k < qsxx.size(); k++) {
											HashMap<String, String> qsMap = qsxx.get(k);
							%>
							<td style="width:16.5%">
								<span class="num"> <%=qsMap.get("qsh")%> </span>
							</td>
							<%
							}
							%>
						</tr>
						<!-- 打出该行的记录（寝室号） end-->

						<!-- 打出该行的记录（寝室详细信息） -->
						<tr>
							<%
											for (int k = 0; k < qsxx.size(); k++) {
											HashMap<String, String> qsMap = qsxx.get(k);
							%>
							<td>
								<input type="hidden" name="lddmArr" id='lddm_<%=num%>'
									value='<%=qsMap.get("lddm")%>' />
								<input type="hidden" name="csArr" id='cs_<%=num%>'
									value='<%=qsMap.get("cs")%>' />
								<input type="hidden" name="qshArr" id='qsh_<%=num%>'
									value='<%=qsMap.get("qsh")%>' />
								<span align="center" title='<%=qsMap.get("fpbm")%>'>(<%=(qsMap.get("fpbm")).length() > 6 ? qsMap
												.get("fpbm").substring(0, 6)
												+ "..."
												: qsMap.get("fpbm")%>)</span>
								<p>
									床位数：
									<%=qsMap.get("cws")%>
									<br />

									入住人数：
									<%=qsMap.get("rzrs")%>
									人
									<br />
								</p>
								<div style="position:relative;z-index:0;"
									onmouseover="javascript:document.getElementById('helpcon_<%=num%>').style.display='block'"
									onmouseout="javascript:document.getElementById('helpcon_<%=num%>').style.display='none'">
									<a href="#" class="check"
										onmouseover="showhid('<%=num%>');return false;"
										onclick="return false">查看人员 </a>
									<div class="check_people" id="helpcon_<%=num%>"
										style="display:none;">
										<ul id="ul_<%=num%>">

										</ul>
									</div>

								</div>
							</td>
							<%
										num++;
										}
							%>
						</tr>
						<!-- 打出该行的记录（寝室详细信息 end） -->
						<%
									//重新构建寝室信息LIST
									qsxx = new ArrayList<HashMap<String, String>>();

								}
									}

									//如果该楼层的寝室数不能除禁6（说明还有多余寝室信息未输出）
									if (qss % 6 != 0) {
						%>
						<tr>
							<%
										for (int k = 0; k < qsxx.size(); k++) {
										HashMap<String, String> qsMap = qsxx.get(k);
							%>
							<!-- 打出该行的记录（寝室号） -->
							<td style="width:16.5%">
								<span class="num"> <%=qsMap.get("qsh")%> </span>
							</td>
							<!-- 打出该行的记录（寝室号）end -->

							<%
									}
									//补空行
									for (int z = 1; z <= 6 - n; z++) {
							%>
							<td></td>
							<%
							}
							%>
						</tr>

						<tr>
							<%
										for (int k = 0; k < qsxx.size(); k++) {
										HashMap<String, String> qsMap = qsxx.get(k);
							%>

							<td>
								<input type="hidden" name="lddmArr" id='lddm_<%=num%>'
									value='<%=qsMap.get("lddm")%>' />
								<input type="hidden" name="csArr" id='cs_<%=num%>'
									value='<%=qsMap.get("cs")%>' />
								<input type="hidden" name="qshArr" id='qsh_<%=num%>'
									value='<%=qsMap.get("qsh")%>' />
								<span align="center" title='<%=qsMap.get("fpbm")%>'>(<%=(qsMap.get("fpbm")).length() > 6 ? qsMap
											.get("fpbm").substring(0, 6)
											+ "..."
											: qsMap.get("fpbm")%>)</span>
								<p>
									床位数：
									<%=qsMap.get("cws")%>
									<br />

									入住人数：
									<%=qsMap.get("rzrs")%>
									人
									<br />
								</p>
								<div style="position:relative;z-index:0;"
									onmouseover="javascript:document.getElementById('helpcon_<%=num%>').style.display='block'"
									onmouseout="javascript:document.getElementById('helpcon_<%=num%>').style.display='none'">
									<a href="#" class="check"
										onmouseover="showhid('<%=num%>');return false;"
										onclick="return false">查看人员
										<div class="check_people" id="helpcon_<%=num%>"
											style="display:none;">
											<ul id="ul_<%=num%>">

											</ul>
										</div> </a>
								</div>
							</td>

							<%
									num++;
									}
									//补空行
									for (int z = 1; z <= 6 - n; z++) {
							%>
							<td></td>
							<%
							}
							%>
						</tr>
						<%
						}
						%>
					</tbody>
					<!-- 楼层 内容 end-->
					<%
					}
					%>
				</table>
			</div>


			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alert("操作成功！");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
