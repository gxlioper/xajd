<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript">
		function operation(value){
			var num = 0;
			var pkVStr = '!@!';	
				var pks = document.getElementsByName('pkV');
				for(var i=0; i<pks.length; i++){
					if(pks[i].checked == true){
						num++;
						pkVStr +=pks[i].value+'!@!'; 
					}
				}
				if(num == 0){
					alert('请选择您要操作的记录！');
		   	   		return false;
				}else{
					if(!confirm('您确定要执行该操作吗？')){
						return false;
					}
				}
			$('pkVStr').value=pkVStr;	
			document.forms[0].action = "/xgxt/zjlg_gygl.do?method=tsqssh&doType="+value;
		   	document.forms[0].submit();
		}
		function chkView(){
			var xn = $('sqxn').value;
   			var url = "/xgxt/zjlg_gygl.do?method=viewTsqsSqxx&pkValue=";
   			var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
			url += "&xn="+xn;
    		showTopWin(url,"600","600");              		                  
 		}
		function queryData(){
			var xn=document.getElementById('xn').value;
			if(xn == ''){
				alert('学年为必选！');
				return false;
			}
			refreshForm('/xgxt/zjlg_gygl.do?method=tsqssh&doType=query');
		}
	</script>
	</head>
	<body>
		<center>
			<html:form action="/zjlg_gygl" method="post">
				<input type="hidden" name="pkVStr" id="pkVStr" value="" />
				<input type="hidden" name="sqxn" id="sqxn" value="${myform.xn }" />
				
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>公寓管理 - 特色寝室管理 - 审核 - 特色寝室审核</a>
					</p>
				</div>
				<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<li><a href="#" class="btn_shtg" onclick="operation('pass')">审核通过</a></li>
							<li><a href="#" class="btn_shbtg" onclick="operation('nopass')">审核不通过</a></li>
							<li><a href="#" class="btn_sc" onclick="operation('dele')">删除</a></li>
						</ul>
					</div>
				</div>
				<div class="searchtab">
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<th>学年</th>
								<td><html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select></td>
								<th>楼栋</th>
								<td><html:select property="lddm" styleId="lddm"
										onchange="getLcList()">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select></td>
								<th>楼层</th>
								<td><html:select property="lc" styleId="lc"
										onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select></td>
							</tr>
							<tr>
								<th>寝室号</th>
								<td><html:select property="qsh" styleId="qsh">
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select></td>
									<logic:notEqual value="xy" name="userType">
										<th>学校审核</th>
										<td><html:select property="xxsh" styleId="xxsh">
											<html:option value="">--请选择--</html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />
										</html:select></td>
									</logic:notEqual>
									<logic:equal value="xy" name="userType">
										<th><bean:message key="lable.xsgzyxpzxy" />审核</th>
										<td><html:select property="xysh" styleId="xysh">
											<html:option value="">--请选择--</html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />
										</html:select></td>
									</logic:equal>
									<th></th>
									<td></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" name="go" value="a" />
								<button id="search_go" onclick="queryData();">
									查询
								</button>
								 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									重置
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
									<td align="center">
										<input type="checkbox" name="all" value="all" onclick="chec()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="chkView()">
									<td align="center">
										<input type="checkbox" name="pkV"
											value="<bean:write name="s" property="pk"/>" />
									</td>
									<td align="center">
										<bean:write name="s" property="xn" />
									</td>
									<td align="center">
										<bean:write name="s" property="ssbh" />
									</td>
									<td align="center">
										<bean:write name="s" property="ldmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="cs" />
									</td>
									<td align="center">
										<bean:write name="s" property="qsh" />
									</td>
									<td align="center">
										<bean:write name="s" property="lxdh" />
									</td>
									<td align="center">
										<bean:write name="s" property="sqsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xysh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xxsh" />
									</td>
								</tr>
							</logic:iterate>
						</table>
					
						<!--分页显示-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlg_gyglForm"></jsp:include>
						<!--分页显示-->
					<br />
				</logic:notEmpty>
				</div>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<logic:equal value="true" name="result">
		<script language="javascript">
			alert('操作成功！');
			document.getElementById('search_go').click();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('操作失败！');
		</script>
	</logic:equal>
</html>
