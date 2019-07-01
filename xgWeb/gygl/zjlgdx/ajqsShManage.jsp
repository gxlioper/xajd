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
			if(value != 'autocx'){  
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
			}else{
				if(!confirm('您确定要执行自动撤消操作吗？')){
					return false;
				}
			}
			$('pkVStr').value=pkVStr;	
			document.forms[0].action = "/xgxt/zjlg_gygl.do?method=ajqssh&doType="+value;
		   	document.forms[0].submit();
		}
		function chkView(){
			var xn = $('sqxn').value;
			var xq = $('sqxq').value;
   			var url = "/xgxt/zjlg_gygl.do?method=viewSqxx&pkValue=";
   			var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
			url += "&xn="+xn+"&xq="+xq;
    		showTopWin(url,"600","500");              		                  
 		}
 		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
		//验证数据格式是否是数字
		function ckinpdata(obj){
        	if(obj.value==""){		
				return false;
			}
			obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
			var inputStr = obj.value;		

			if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 1000){
				obj.value = '';
				return false;
			}
			return true;
		}
		function searchGo(){
			var xn=document.getElementById('xn').value;
			var xq=document.getElementById('xq').value;
			if(xn == ''||xq == ''){
				alert('学年,学期为必选！');
				return false;
			}
			refreshForm('zjlg_gygl.do?method=ajqssh&doType=query');
		}
	</script>
	</head>
	<body>
		<center>
			<html:form action="/zjlg_gygl" method="post">
				<input type="hidden" name="pkVStr" id="pkVStr" value="" />
				<input type="hidden" name="sqxn" id="sqxn" value="${myform.xn }" />
				<input type="hidden" name="sqxq" id="sqxq" value="${myform.xq }" />
				
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>公寓管理 - A级寝室管理 - 审核 - A级寝室审核</a>
					</p>
				</div>
				
				<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<li><a href="#" class="btn_shtg" onclick="operation('pass')">审核通过</a></li>
							<li><a href="#" class="btn_shbtg" onclick="operation('nopass')">审核不通过</a></li>
							<li><a href="#" class="btn_qx" onclick="operation('cx')">撤消A级寝室</a></li>
							<li><a href="#" class="btn_qxgx" onclick="operation('jccx')">解除撤消</a></li>
							<li><a href="#" class="btn_dc" onclick="operation('autocx')">自动撤消</a></li>
							<li><a href="#" class="btn_sc" onclick="operation('dele')">删 除</a></li>
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
								<th>学期</th>
								<td><html:select property="xq" styleId="xn" style="width:80px">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select></td>
								<th>楼栋</th>
								<td><html:select property="lddm" styleId="lddm"
										onchange="getLcList()">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select></td>
							</tr>
							<tr>
								<th>楼层</th>
								<td><html:select property="lc" styleId="lc"
										onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select></td>
								<th>寝室号</th>
								<td><html:select property="qsh" styleId="qsh">
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>审核状态</th>
								<td><html:select property="xxsh" styleId="xxsh">
										<html:option value="">--请选择--</html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select></td>
							</tr>
							<tr>
								<td colspan="6">申请日期从
								<html:text property="sqrq1" styleId="sqrq1" readonly="true"
										style="width:80px"
										onclick="this.value='';return showCalendar('sqrq1','y-mm-dd');"
										onblur="getRqVal('sqrq1')"></html:text>
									至
									<html:text property="sqrq2" styleId="sqrq2" readonly="true"
										style="width:80px"
										onclick="this.value='';return showCalendar('sqrq2','y-mm-dd');"
										onblur="getRqVal('sqrq2')"></html:text>
									&nbsp;&nbsp;连续
									<html:select property="zs" styleId="zs">
										<html:option value="">--请选择--</html:option>
										<html:option value="1">一周</html:option>
										<html:option value="2">二周</html:option>
										<html:option value="3">三周</html:option>
										<html:option value="4">四周</html:option>
										<html:option value="5">五周</html:option>
										<html:option value="6">六周</html:option>
										<html:option value="7">七周</html:option>
										<html:option value="8">八周</html:option>
									</html:select>
									卫生检查成绩大于等于
									<html:text property="fs" style="width:60px"
										onkeyup="ckinpdata(this)"></html:text>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<input type="hidden" name="go" value="a" />
								<button id="search_go" onclick="searchGo();">
									查询
								</button>
								 <button id="btn_cz" onclick="searchReset();return false;">
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
						<div class="con_overlfow">
							 <table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center">
										<input type="checkbox" name="all" value="all" onclick="chec()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="chkView()">
									<td align="center">
										<input type="checkbox" name="pkV"
											value="<bean:write name="s" property="pk"/>"/>
									</td>
									<td align="center">
										<bean:write name="s" property="xn" />
									</td>
									<td align="center">
										<bean:write name="s" property="xqmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="ssbh" />
									</td>
									<td align="center">
										<bean:write name="s" property="ldmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="lc" />
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
										<bean:write name="s" property="xxsh" />
									</td>
									<td align="center">
										<bean:write name="s" property="shsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="sfcx" />
									</td>
									<td align="center">
										<bean:write name="s" property="cxsj" />
									</td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
						</div>
						
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
