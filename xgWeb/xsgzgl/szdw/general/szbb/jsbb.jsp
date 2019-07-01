<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript">
			function bc(){
				var checkboxs = jQuery("[name='grid_key']:checked");
				if(checkboxs.length < 1){
					showAlert("请至少选择一个老师！");
					return false;
				}
				 showAlert("保存成功",{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				//iFClose();
			}
		</script>
	</head>

	<body>
<%--		<div class="tab_cur">--%>
<%--			<p class="location">--%>
<%--				您当前选择了10位老师--%>
<%--			</p>--%>
<%--		</div>--%>
		<div class="prompt" id="div_help" style="">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					<font style="color: red">
						您当前选择了10个班级
					</font>				
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/xgygl_zdsq">	
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>					  
						<li><a href="javascript:void(0);" onclick="bc();" class="btn_xg">保存</a></li>
					</ul>
				</div>
				<div class="searchtab">
					<table>
						<tbody>
							<tr>
								<td width="5%">
									教师姓名
								</td>
								<td>
									<input type="text" id="xmmc" name="xmmc" maxleng="50" style="width: 100px">
								</td>
								<td width="5%">
									职工号
								</td>
								<td width="5%">
									<input type="text" id="xmmc" name="xmmc" maxleng="50" style="width: 100px">
								</td>
								<td width="5%">
									是否带班
								</td>
								<td>
									<select name="sfdb" style="width: 100px">
										<option>---请选择---</option>
										<option value="1">是</option>
										<option value="2">否</option>
									</select>
								</td>
								<td width="5%">
									所属部门
								</td>
								<td>
									<select name="xydm" id="xy" style="width:100px">
										<option value=""></option>
										<option value="509000">材料与化学工程学院</option>
										<option value="510000">电气工程学院</option>
										<option value="517000">动物科学学院</option>
										<option value="502000">法学院</option>
										<option value="511113">佛学院</option>
										<option value="523000">公共管理学院</option>
										<option value="520000">管理学院</option>
										<option value="412000">国际教育学院</option>
										<option value="514000">环境与资源学院</option>
										<option value="508000">机械与能源工程学院</option>
										<option value="521000">计算机科学与技术学院</option>
										<option value="512000">建筑工程学院</option>
										<option value="21020000">教师教育学院</option>
										<option value="31020000">教育系</option>
										<option value="503000">教育学院</option>
										<option value="31010000">经管系</option>
										<option value="501000">经济学院</option>
										<option value="31070000">理学系</option>
										<option value="21070000">理学院</option>
										<option value="506000">理学院001</option>
										<option value="610000">李学院</option>
										<option value="516000">农业与生物技术学院</option>
										<option value="504000">人文学院</option>
										<option value="522000">软件学院</option>
										<option value="21010000">商学院</option>
										<option value="31110000">社会发展系 </option>
										<option value="21110000">社会发展学院</option>
										<option value="31090000">生命科学系</option>
										<option value="507000">生命科学学院1</option>
										<option value="21090000">生命科学学院2</option>
										<option value="513000">生物系统工程与食品科学学院</option>
										<option value="515000">生物医学工程与仪器科学学院</option>
										<option value="31030000">体育系</option>
										<option value="21050000">外国语学院</option>
										<option value="5700">外国语学院1</option>
										<option value="505000">外国语言文化与国际交流学院</option>
										<option value="31050000">外语系</option>
										<option value="553000">温州医学院</option>
										<option value="21040000">文学院</option>
										<option value="692105">校共建专业</option>
										<option value="511000">信息科学与工程学院</option>
										<option value="31080000">信息与工程系</option>
										<option value="21080000">信息与工程学院</option>
										<option value="519000">药学院</option>
										<option value="31100000">医学系</option>
										<option value="518000">医学院</option>
										<option value="31060000">艺术系</option>
										<option value="21060000">艺术学院</option>
										<option value="552000">中医学院</option>
										<option value="410000">竺可桢学院</option>
									</select>
								</td>
								<td>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>教师列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" class="dateline">
					<thead>
						<tr class="nowrap">
							<td width="2%">
								<input type="checkbox" name="grid_selectAll" disabled="">
							</td>
							<th width="13%" sortname="xh">职工号</th>
							<th width="10%" sortname="xm">姓名</th>
							<th width="15%" sortname="xydm">所属部门</th>
							<th width="13%" sortname="bjdm">是否带班</th>
							<th width="15%" sortname="xn">带班数</th>
						</tr>
						<tbody>
							<tr rowindex="0">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									赫荣菊
								</td>
								<td style="word-break:break-all;">
									医学院
								</td>
								<td style="word-break:break-all;">
									是
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="1">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									赫荣菊
								</td>
								<td style="word-break:break-all;">
									医学院
								</td>
								<td style="word-break:break-all;">
									是
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="2">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									赫荣菊
								</td>
								<td style="word-break:break-all;">
									医学院
								</td>
								<td style="word-break:break-all;">
									是
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="3">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									赫荣菊
								</td>
								<td style="word-break:break-all;">
									医学院
								</td>
								<td style="word-break:break-all;">
									是
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="4">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									赫荣菊
								</td>
								<td style="word-break:break-all;">
									医学院
								</td>
								<td style="word-break:break-all;">
									是
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="5">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									赫荣菊
								</td>
								<td style="word-break:break-all;">
									医学院
								</td>
								<td style="word-break:break-all;">
									是
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="6">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									赫荣菊
								</td>
								<td style="word-break:break-all;">
									医学院
								</td>
								<td style="word-break:break-all;">
									是
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="7">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									赫荣菊
								</td>
								<td style="word-break:break-all;">
									医学院
								</td>
								<td style="word-break:break-all;">
									是
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="8">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									赫荣菊
								</td>
								<td style="word-break:break-all;">
									医学院
								</td>
								<td style="word-break:break-all;">
									是
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
							<tr rowindex="9">
								<td style="width:12px;">
									<input type="checkbox" name="grid_key">
								</td>
								<td style="word-break:break-all;">
									00423
								</td>
								<td style="word-break:break-all;">
									赫荣菊
								</td>
								<td style="word-break:break-all;">
									医学院
								</td>
								<td style="word-break:break-all;">
									是
								</td>
								<td style="word-break:break-all;">
									1
								</td>
							</tr>
						</tbody>
					</thead>
				</table>
				<div id="pager" class="pagination"><div class="pageleft"><p class="pagenum"><span style="display:none">第<input type="text" id="pageNum" value="1" style="text-align:center;width:20px;">/<span class="red" id="pageCount">1</span>页，</span>每页显示<select name="numList" id="numList"><option value="10">10</option><option value="20">20</option><option value="50">50</option><option value="100">100</option><option value="200">200</option></select>条 /  共<span class="red" id="rowConut">2</span>条记录 </p></div><div class="pageright"><div class="paging"> <a id="first" class="first pointer" title="首页">首&nbsp;页</a>&nbsp;<a id="back" class="prev pointer" title="上一页">上一页</a>&nbsp;<a id="next" class="next pointer" title="下一页">下一页</a>&nbsp;<a id="last" class="last pointer" title="末页">末&nbsp;页</a></div></div></div>
			</div>
		</div>
	</body>
</html>
