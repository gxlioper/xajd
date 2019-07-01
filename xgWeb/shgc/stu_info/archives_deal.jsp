<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<%@ include file="/syscommon/pagehead_xg.ini"%>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script type="text/javascript">
		var pkVals = "!@!";
		var num = 0;
		var xh = "";
		function checkOpertion(){
				pkVals = "!@!";	
				num = 0;
				xh = "";			
				var pks = document.getElementsByName('pkV');
				for(var i=0; i<pks.length; i++){
					if(pks[i].checked == true){
						num++;
						pkVals +=pks[i].value+"!@!"; 
						xh += pks[i].value;
					}
				}
				if(num == 0){
					alert("请勾选你要操作的记录！");
					return  false;
				}
				return true;
		}
		function updateZdxx(val){
			
				xh= curr_row.getElementsByTagName('input')[0].value;
				showTopWin('/xgxt/archives_deal.do?doType=update&pkStr='+xh,600,500);
			
		}
		function deleteZdxx(){
			if(checkOpertion()){
				if(confirm("确定删除所选记录？")){
					$('pkStr').value= pkVals;
					refreshForm('/xgxt/archives_deal.do?doType=delete');
				}
			}
		}
		function chec1(){
      		for(i=0;i<document.getElementsByName("pkV").length;i++){
      			document.getElementsByName("pkV")[i].checked=document.getElementsByName("all")[0].checked;
      		}
    	}
    	function viewzdxx(){
    		var array = curr_row.getElementsByTagName('input');
    		var zdlb = $('zdlb').value;
    		var xh = '';
    		if(array.length >1){
    			xh = array[1].value;
    		}else{
    			xh = array[0].value;
    		}
    		showTopWin('/xgxt/archives_deal.do?doType=view&xh='+xh+'&zdlb='+zdlb,600,500);
    	}
    	
    	function printInfo(val){
    		if(val == 'dajdd'){
    			if($('zdlb').value==''){
    				alert('转档类别不能为空！');
    				return false;
    			}
    		}
    		if(checkOpertion()){
    			$('pkStr').value= pkVals;
    			document.forms[0].action = "/xgxt/archives_print.do?doType="+val;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
    		}		
    	}
    	function viewdiv(){
    		if(checkOpertion()){
    			viewTempDiv('','div',340,180);
    		}
    	}
    	function summitdiv(){
    		if($('wh').value==''){
    			alert('文号不能为空！');
    			return  false;
    		}
    		if($('djr').value==''){
    			alert('登记人不能为空！');
    			return  false;
    		}
    		
    		$('pkStr').value= pkVals;    		
    		document.forms[0].action = "/xgxt/archives_print.do?doType=jyjdd";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			hiddenOneDiv(true,true);		
    	}
    	function init2(obj1,obj2) {
    		document.getElementById(obj2).style.pixelTop = 
			document.getElementById(obj1).style.pixelTop-260;
		}
		
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('/xgxt/archives_deal.do?doType=query');
		}
	</script>
</head>
<body>
	<html:form action="/archives_deal" method="post">
		<input type="hidden" id="pkStr" name="pkStr" value=""/>
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		
		 <!-- 高级查询 必须 -->
	     <input type="hidden" name="userName" id="userName" value="${userName }"/>
	     <input type="hidden" name="userDep" id="userDep" value="${userDep }"/>
	 	 <input type="hidden" id="path" name="searchModel.path" value="archives_deal.jsp"/>
  
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<li> <a href="#" onclick="updateZdxx('no');return false;" class="btn_xg">修 改</a> </li>
					<li> <a href="#" onclick="deleteZdxx();return false;" class="btn_sc">删 除</a> </li>
					<li> <a href="#" onclick="printInfo('dajdd');return false;" class="btn_dy">档案寄递单</a> </li>
					<li> <a href="#" onclick="printInfo('dajdxf');return false;" class="btn_dy">档案寄递信封</a> </li>
					<li> <a href="#" onclick="if(checkOpertion()){viewTempDiv('填写寄递信息','div',340,180);};return false;" class="btn_dy">机要寄递单</a> </li>
			    </ul>
			  </div>
		</div>
		
			  <!--查询条件-->
			  <!-- 内容显示区开始 -->
				<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
					<!-- new 版本 -->
				     <logic:equal name="superSearch" value="yes">
				      <%@ include file="/comm/search/superSearchArea.jsp"%>
				     </logic:equal>
				     
				     <!-- old 版本 -->
				     <logic:equal name="superSearch" value="no">
				     <logic:notEqual value="student" name="userOnLine" scope="session">
					<!-- From内容 -->
<!--					<div style="float:left;">-->
<!--						<div class="listbox" style="width:155px;float:left">-->
<!--							<div class="menulist">-->
<!--							层start-->
<!--							<div class="menutitle">-->
<!--							     <h4 style="height:30px;line-height:30px;padding-left:40px;">部门列表</h4>-->
<!--							<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height: 400px;">-->
<!--							<ul>-->
<!--							  <li class="Opened" id="xxid"><span onclick="clickBm(this,'xxid')">${xxmc}</span></li>-->
<!--							</ul>-->
<!--							</div>-->
<!--							<script type="text/javascript">-->
<!--							-->
<!--							var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu1","li");-->
<!--							//MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif","menutitle");-->
<!--	 						-->
<!--							</script>-->
<!--							</div>-->
<!--							层end-->
<!--							</div>-->
<!--						</div>-->
<!--					</div>-->
<!--					<div style="float:right;width:630px;">-->
					  <div class="searchtab">
					    <table width="100%" border="0">
					      <tfoot>
					        <tr>
					          <td colspan="6">
					            <div class="btn">
					              <input type="hidden" name="go" value="a" />
								  <button class="btn_cx" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/archives_deal.do?doType=query')">
										查询
								  </button>
					            </div>
					          </td>
					        </tr>
					      </tfoot>
						  <tbody>
					      	<tr>
					      		<th>年度</th>
								<td>
									<html:select property="nd"  styleId="nd" style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>								
								<th>学号</th>
								<td>
									<html:text property="xh" maxlength="20" style="width:100px"/>
								</td>
								<th>姓名</th>
								<td>
									<html:text property="xm" style="width:100px"></html:text>
								</td>
							</tr>
							<tr>
					      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<logic:equal name="userType" value="xy" scope="session">
										<html:select property="xydm" style="width:100px"
											 disabled="true"
											styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy" scope="session">
										<html:select property="xydm" style="width:100px"
											onchange="initZyList();initBj();" 
											styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>专业</th>
								<td>
									<html:select property="zydm" style="width:100px" styleId="zy"
										onchange="initBj();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>班级</th>
								<td>
									<html:select property="bjdm" style="width:100px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
					      		<th>转档类别</th>
								<td>
									<html:select property="zdlb" style="width:100px"
										styleId="zdlb">
										<html:option value=""></html:option>
										<html:option value="毕业">毕业</html:option>
										<html:option value="升学">升学</html:option>
										<html:option value="转学">转学</html:option>
										<html:option value="退学">退学</html:option>
									</html:select>
								</td>
								<th></th>
								<td>
								</td>
								<th></th>
								<td>
								</td>
							</tr>
						  </tbody>
						</table>
					</div>
			</logic:notEqual></logic:equal>

			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font>
			    </span>
			    </h3>
<!--			 	<div style="overflow-x:auto;width:630px;">		-->
			  <div class="con_overlfow"> 
			  <table summary="" class="dateline"  width="100%" id="rsTable">
			    <thead>
			      <tr>
					<td>
<!--						<input type="checkbox"/>-->
						<input type="checkbox" name="all" value="all" onclick="chec1()"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="0">
						<td id="${tit.en}"
							onclick="tableSort(this)">
							${tit.cn}
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
					<logic:empty name="rs">
						<%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<td align="center">
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
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;"
							ondblclick="updateZdxx('yes');">
								<td>
									<input type="checkbox" value="${s.xh}" name="pkV" id="pkV"/>
								</td>
								<td>
									${s.xh}
								</td>
								<td>
									${s.xm}
								</td>
								<td>
									${s.xymc}
								</td>
								<td>
									${s.zymc}
								</td>
								<td>
									${s.bjmc}
								</td>
								<td>
									${s.zddwmc}
								</td>
								<td>
									${s.zddwdz}
								</td>
								<td>
									${s.zdlb}
								</td>
								<td>
									${s.xxsh}
								</td>
								<td>
									${s.zcbh}
								</td>
						</tr>
					</logic:iterate>
					<logic:lessEqual value="${pageSize}" name="rsNum">
						<%
						int rsNum = ((List)request.getAttribute("rs")).size();
						int pageSize = (Integer) request.getAttribute("pageSize");
						for(int i=0; i<(pageSize-rsNum); i++){
						%>
						<tr>
							<td align="center">
								<input type="checkbox" name="pk" value="" disabled="disabled"></input>
							</td>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td>
									&nbsp;
								</td>
							</logic:iterate>
					 	</tr>
						<%}%>
					</logic:lessEqual>
					</logic:notEmpty>
			    </tbody>
			</table>
			</div>
			<!--分页显示-->
			   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=ShgcForm"></jsp:include>
			<!--分页显示-->
<!--				<script type="text/javascript">-->
<!--					$('choose').className="hide";-->
<!--				</script>-->
		</div>
		<div style="display: none;position: absolute;z-index: 1;" id="div">
			<table class="formlist" style="width:100%;" >
			 <thead>
			    <tr>
			    	<th colspan="2">
			    		<span>机要寄递单</span>
			    	</th>
			    </tr>
			  </thead>
			  <tbody>
				<tr>
					<th>文号</th><td><input type="text" name="wh" id="wh"/></td>
				</tr>
				<tr>
					<th>登记人</th><td><input type="text" name="djr" id="djr"/></td>
				</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="2">
		          <div class="btn">
		            <button onclick="$('div').style.display='none';summitdiv();">
								提 交
					</button>
					<button onclick="hiddenMessage(true,true);return false;">
								关 闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</html:form>
		<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
				<script>
				alert("操作成功");
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
				alert("操作失败");							
			</script>
			</logic:equal>
		</logic:notEmpty>
</body>
<!--<script type="text/javascript" src="js/xsxx/bmTree.js"></script>-->
<!--<script>-->
<!--	url = "/xgxt/archives_deal.do?doType=query";-->
<!--</script>-->
</html>
