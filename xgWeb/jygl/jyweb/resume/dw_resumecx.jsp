<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript">
		
			function saveHfxx(){
				isChecked("primarykey_cbv");
			
				jyweb.saveHfxxMore(array,$('hfnr').value,function(data){
					if (data){
						alert("回复成功！");
						hiddenMessage(true,true);
						$('search_go').click();
					}
					else {
						alert("回复失败，请检查是否有已回复的记录！");
						hiddenMessage(true,true)
					}
						
				});
			}
		
			function isHf(){
				var flg = false;
				var n = 0 , tempArr = new Array();
				var checkBoxArr = document.getElementsByName("primarykey_cbv");
				
				for (var i = 0; i < checkBoxArr.length; i++) {
					if (checkBoxArr[i].checked) {
						flg = true;
						tempArr[n] = checkBoxArr[i].parentNode.parentNode.getElementsByTagName("td")[6].innerText.trim();
						n++
					}
				}
				
				if(!flg){
					alert("请选择您要回复的学生！");
					return false;
				}
				
				for (var i = 0 ; i < tempArr.length; i++){
					if ("已回复"==tempArr[i]){
						alert("您选择的学生中有已回复的记录，请勿重复回复！");
						flg = false;
						break;
					}
				}
				
				return flg;
			}
		
		
		</script>
	</head>

	<body>
		<html:form action="/jyweb" method="post">
			<input type="hidden" name="realTable" id="realTable"
				value="${realTable }" />
			<!-- 多功能操作区一 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a  onclick="if(isHf()){showUpdateWindow('primarykey_cbv','jywebUseCheckSession.do?method=revertResume',640,420,false,'请选择您要回复的学生')}"
								href="#"
								class="btn_ck"> 回复</a>
						</li>
						<li>
							<a onclick="if(isHf()){viewTempDiv('回复学生消息','dwhf',500,210)}" href="#" class="btn_ck"> 批量回复</a>
						</li>
						<li>
							<a
								href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=resumecx&doType=del','del')"
								class="btn_sc"> 删除 </a>
						</li>
						<li>
							<a
								href="javascript:expData('jywebUseCheckSession.do?method=resumecx&doType=expData')"
								class="btn_dc"> 导出 </a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=resumecx&doType=query')">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									单位名称
								</th>
								<td>
									<input type="text" disabled="disabled"
										value="${jyweb_realName }" />
								</td>
								<th>
									岗位名称
								</th>
								<td>
									<html:text property="querylike_gwmc">
									</html:text>
								</td>

							</tr>
							<tr>
								<th>
									投递日期
								</th>
								<td>
									<html:text styleId="fbsj1" property="querygreaterequal_tdrq"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);"></html:text>
									-
									<html:text styleId="fbsj2" property="querylessequal_tdrq"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- From内容 start-->
			<!---------------------表格--有复选框的数据表单------------------->
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue"> 提示：单击表头可以排序</font>
						</logic:notEmpty> 
					</span>
				</h3>

				
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="true" style="height:17.5px" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td id="${tit.en}" onclick="tableSort(this)">
										${tit.cn}
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
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<td>
											<input type="hidden" value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>" />
											<a
												onclick="window.open('jywebUseCheckSession.do?method=resumeView&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>');"
												class="pointer" style="color:#0A63BF" href="#"> 
												<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>
											</a>
										</td>
										<logic:iterate id="v" name="s" offset="2" >
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum")
										- ((List) request.getAttribute("rs")).size(); i++) {
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
					<!--分页显示-->
					<jsp:include flush="true"
						page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
					<!--分页显示-->
				
			</div>





			<div class="open_win01" style="display:none;" id="dwhf">
				<table width='80%' class='formlist'>
					<tbody>
						<tr>
							<th width='25%'>
								回复内容
							</th>
							<td>
								<textarea rows="8" style="width:95%"
									onblur="if(this.value.length>800){alert('输入字符长度超过限制');$('button_save').disabled=true}else{$('button_save').disabled=false;}"
									id="hfnr"></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class='btn'>
									<button onclick="saveHfxx()" id="button_save">
										提交
									</button>
									<button onclick="hiddenMessage(true,true);return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
