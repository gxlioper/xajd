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
<!--
function cfxxsh(){
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
			showTopWin('wjcf_xmlg_cfplsh.do?keys=' + str,500,400);
		}
	}	
function print() {
	var pk = "";
	if (curr_row != null) {
		pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	}

	window.open('wjcf_xmlg_cfbprint.do?pk=' + pk);
}
//-->
</script>
	</head>
<body onload="xyDisabled('xy');">
	<html:form action="/wjcfxmlgwh" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="operType" value="query"/>
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		
		<div class="toolbox">
				<logic:equal value="yes" name="writeAble" scope="request">
				<div class="buttonbox" id="btn">
					<ul>
									<li>
									<a href="#" onclick="cfxxsh()"
											class="btn_sh">审核</a>
									</li>
									<li>
									<a href="#" onclick="print()"
										class="btn_dy">处分表打印</a>
									</li>
							</ul>
					</div>
				</logic:equal>
				</div>
				<div class="searchtab">
			<table width="100%" class="tbstyle">
						<tbody>
						<tr>
							<th>学年</th>
							<td>
							<html:select property="xn" styleId="xn" style="width:90px"
								styleClass="select" >
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							</td>
							<th>年度</th>
							<td>
							<html:select property="nd" styleId="nd" style="width:90px" >
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
							</td>
							<th>学号</th>
							<td>
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px">
							</html:text>
							</td>
						</tr>
						<tr>
							<th>姓名</th>
							<td>
							<html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px">
							</html:text>
							</td>
							<th>处分类别</th>
							<td>
							<html:select property="cflb" styleId="cflb" 
								styleClass="select">
								<html:option value="">--请选择--</html:option>
								<html:options collection="cflbList" property="cflbdm"
									labelProperty="cflbmc" />
							</html:select>
							</td>
							<th>处分原因</th>
							<td>
							<html:select property="cfyy" styleId="cfyy" 
								styleClass="select">
								<html:option value="">--请选择--</html:option>
								<html:options collection="cfyyList" property="cfyydm"
									labelProperty="cfyymc" />
							</html:select>
							</td>
						</tr>
						<tr>
							<th>年级</th>
							<td>
							<html:select property="nj" styleId="nj" style="width:80px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							</td>
							<th><bean:message key="lable.xsgzyxpzxy" />：</th>
							<td>
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</td>
							<th>专业</th>
							<td>
							<html:select property="zydm" style="width:170px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							</td>
						</tr>
						<tr>
							<th>班级</th>
							<td>
								<html:select property="bjdm" style="170px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							</td>
								<th></th>
							<td>
							</td>	
							<th></th>
							<td>
							</td>
						</tr>
						<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="go" />
		              		<button type="button" id="search_go" 
		              		onclick="refreshForm('wjcf_xmlg_cfsh.do')">
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
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								align="center" ondblclick="modiAndDel('wjcf_xmlg_cfsbxxDgsh.do?pkValue=','modi',700,510)">
								<td>
									<input type="checkbox" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" <logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>/>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfXmlgActionForm"></jsp:include>
				</div>
			</logic:notEmpty>
			<div id="tmpdiv"></div>
	</html:form>
		
		<logic:present name="deleted">
			<logic:equal value="true" name="deleted">
				<script>
					alert('操作成功!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="false" name="deleted">
				<script>
					alert('操作失败!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
</body>
