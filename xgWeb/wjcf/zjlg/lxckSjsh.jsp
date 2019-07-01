<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
		<script type="text/javascript">
	function plsh() {
		var checkBoxArr = document.getElementsByName("cbv");
		var flag = false;
		var len=0;
		var str = "";
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
				len++;
				str += checkBoxArr[i].value + "!@";
			}
		}
		
		if (!flag) {
			alert("请选择要审核的记录,单选,多选即可.");
			return false;
		} else {
			showTopWin('wjcf_zjlg_plshLxckxx.do?pkString=' + str,500,400);
		}
	}
	function printLxckb() {
		var pkValue = '';
		if (curr_row != null) {
			pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
		}
		window.open('wjcf_xmlg_lxckprint.do?pkValue='+pkValue );
	}
	
	function guizhdxPrint() {
		var pkValue = '';
		if (curr_row != null) {
			pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
		}
		window.open('wjcfnblgwh.do?method=gzdxCfjcPrint&pkValue='+pkValue );
	}

</script>
	</head>
	<body onload="xyDisabled('xy');">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 解除留校察看 - 审核</a>
			</p>
		</div>

		<html:form action="/wjcfzjlgwh.do" method="post">
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="operType" value="query" />
			<input type="hidden" name="pt" id="pt" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="failInfo" id="failInfo"
				value="${failinfo }" />

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="plsh()" class="btn_sh">审核</a>
							</li>


							<logic:equal value="11062" name="xxdm">
								<li>
									<a href="#" onclick="printLxckb()" class="btn_dy">打印</a>
								</li>
							</logic:equal>
							<logic:equal value="10657" name="xxdm">
								<li>
									<a href="#" onclick="guizhdxPrint()" class="btn_dy">打印</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:80px"
										styleClass="select" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:90px"
										styleClass="select">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" styleId="nd" style="width:90px">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:150px"
										onchange="initZyList();initBjList()" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()"
										style="width:150px" styleId="zy">
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
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh" styleClass="inputtext"
										style="width:150px">
									</html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="inputtext"
										style="width:150px">
									</html:text>
								</td>
								<logic:equal value="10338" name="xxdm">

									<th>
										解除留校察看时间
									</th>
									<td>
										<html:text property="lxcksj" styleId="lxcksj" onclick="showCalendar('lxcksj','y-mm-dd');"
											onblur="dateFormatChg(this);" style="width:150px" readonly="true"></html:text>
									</td>
								</logic:equal>
								<logic:notEqual value="10338" name="xxdm">
									<th>
									</th>
									<td>
									</td>
								</logic:notEqual>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('wjcf_zjlg_lxcksjsh.do?operType=query')">
											查询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> 
							<logic:notEmpty name="rs">
								<font color="blue"> 提示：双击一行可以查看详细信息；</font>
							</logic:notEmpty>
							</span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" width="100%">
							<thead>
								<tr>
									<td>
										<input type="checkbox" id="cb" name="cb"
											onclick="selectAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
											<tr onclick="rowOnClick(this)" style="cursor:hand;"
												ondblclick="modiAndDel('wjcf_zjlg_LxckxxDgsh.do?pkValue=','modi',750,660)">
												<td>
													<input type="checkbox" id="cbv" name="cbv"
														value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
														<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
												</td>
												<logic:iterate id="v" name="s" offset="2">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
							</tbody>
						</table>
						<!--分页显示-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=wjcfZjlgActionForm"></jsp:include>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
			</div>
				<div id="tmpdiv"></div>
		</html:form>
		<logic:present name="deleted">
			<logic:equal value="yes" name="deleted">
				<script>
					alert('操作成功!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="no" name="deleted">
				<script>
					alert('操作失败!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
	</body>
	</html>