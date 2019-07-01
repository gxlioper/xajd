<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- 头文件 -->
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script>
		function viewChk(){
		     var url = "/xgxt/pjpyszyqwh.do?method=szyc_zznlViewAndChk";
		     url+="&xh="+(curr_row.cells[0].getElementsByTagName("input"))[0].value;
		     url+="&xn="+$("xn").value;
		     url+="&xq="+$("xq").value;
		     showTopWin(url,"700","800");   
		} 
		
		function jsfs(){
			var xn = $("xn").value;
			var xq = $("xq").value;
			if (xn == ""||xq== ""){
				alert("计算综合素质分必须选定学年和学期！！");
				return false;
			}
			if (confirm("将要计算综合素质分，确认吗？\n点击\"确定\"，计算数据；\n点击\"取消\"，将放弃计算！")) {
				showTips('处理数据中，请等待......');
				var url = "/xgxt/pjpyszyqwh.do?method=zhszfhzAutoAccount";
				refreshForm(url);
			}
		}

		function zhszcpfhz() {
				var xn = $("xn").value;
				var xq = $("xq").value;
				var bj = $("bj").value;
				if (xn == ""||xq== ""||bj==""){
					alert("学年,学期,班级为必选项！！");
					return false;
				}
				window.open("/xgxt/pjpyszyqwh.do?method=zhszcpfbjhz&xn="+xn+"&xq="+xq+"&bj="+bj);	
		}
	</script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyszyqwh" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>综合素质-学生综合素质养成课- 综合素质分汇总</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
				<span id="div_help" style="display: none">
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc }</font>学期 展开的。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="tableName" name="tableName" value="view_szgy_zhszcp" />
		<input type="hidden" id="realTable" name="realTable" value="szgy_zhszcphzlsb" />
		<input type="hidden" id="userType" name="userType" value="${userType}"/>
		
		<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<logic:equal value="admin" name="userType" scope="session">
					<li><a href="#" class="btn_zj" onclick="showTopWin('pjpyszyqwh.do?method=jsfsUpdate',400,250);return false;">计算分值</a></li>
					<li><a href="#" class="btn_xg"  onclick="impAndChkData();return false;">导入数据</a></li>
					<li><a href="#" class="btn_sc" onclick="dataExport();return false;">导出数据</a></li>
					</logic:equal>
					<logic:equal value="xx" name="userType" scope="session">
					<li><a href="#" class="btn_zj" onclick="showTopWin('pjpyszyqwh.do?method=jsfsUpdate',400,250);return false;">计算分值</a></li>
					<li><a href="#" class="btn_xg"  onclick="impAndChkData();return false;">导入数据</a></li>
					<li><a href="#" class="btn_sc" onclick="dataExport();return false;">导出数据</a></li>
					</logic:equal>
					<li><a href="#" class="btn_dr" onclick="zhszcpfhz();return false;">导出班级汇总表</a></li>
				</ul>
			</div>
		</div>
		
		<div class="searchtab">
			<table width="100%">
				<tbody>
					<tr>
						<th>年级</th>
						<td>
							<html:select property="nj" styleId="nj" styleClass="select"
								onchange="initZyList();initBjList()" >
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>学年</th>
						<td>
							<html:select property="xn"  styleClass="select"
								styleId="xn">
								<html:options collection="xnList2" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq" styleClass="select"
								style="padding-left:80px" styleId="xq">								
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>学号</th>
						<td>
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px"></html:text>
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px"></html:text>															
						</td>
						<th>综合素质总分</th>
						<td>
							<html:text property="zhszzf" styleId="zhszzf"></html:text>
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" onchange="initZyList();initBjList()"
								style="width:180px"  styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>
							<html:select property="zydm" onchange="initBjList()" 
								style="width:180px" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>班级</th>
						<td>
							<html:select property="bjdm" 
								style="width:180px" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go" onclick="refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_zhszcphzManage&go=go');this.disabled=true;">
							查 询
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							重 置
						 </button>
						</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		
		<div class="formbox">
			<logic:empty name="rs">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
						<font color="red">未找到任何记录！</font> 
			    </span>
			    </h3>
			 </logic:empty>
			<logic:notEmpty name="rs">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
					</span>
				</h3>		
				 <div class="con_overlfow">
				<table width="99%" id="rsTable tablenowrap" class="dateline">
					<thead>
						<tr align="center" style="cursor:hand">					
							<logic:iterate id="tit" name="topTr" offset="0" scope="request">
								<td id="${tit.en}" onclick="tableSort(this)">
									${tit.cn}
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;">										
							<td align=center>
								<logic:iterate id="v" name="s" offset="0" length="1">
								  <bean:write name="v" />
								</logic:iterate>
							</td>																
							<logic:iterate id="v" name="s" offset="1">
								<td align=center>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</table>
				</div>
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzyqxyZhszcpActionForm"></jsp:include>
				<!--分页显示-->
		</logic:notEmpty>
		<div id="tmpdiv">
			
		</div>
		
		<script type="text/javascript" src="js/bottomButton.js"></script>
		</div>
	</html:form>
</body>
</html>
