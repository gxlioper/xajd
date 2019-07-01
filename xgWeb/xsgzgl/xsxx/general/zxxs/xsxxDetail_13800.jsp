<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.action.Base"%>
<%@page import="common.Globals"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 头文件 -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/studetailFunction.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
		<script>

		function deploy(id){
			document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
		}	
		
		function printOne(){
			var xh = document.getElementById('xh').value;
			var mks = document.getElementsByName('print_mk');

			var print_mk = ""
			for(var i=0; i<mks.length; i++){
				if(mks[i].checked == true){
					print_mk = print_mk + mks[i].value + "split!!";
				}
			}
			
<%--			refreshForm('stu_info_details.do?page=print');--%>
			//showOpenWindow("stu_info_details.do?xh="+xh+"&page=print&print_mk="+print_mk,800,600);
			showOpenWindow("xsxx_zxxs_hbktzy.do?method=xsxxDetail&xh="+xh+"&page=print&print_mk="+print_mk,800,600);
		}
		
		function loadchange(){
			var tab = document.getElementById("titName").value;
			if(tab != ""){
				changePage($(tab+'_a'));
			}
	    }
	    
	    function changePage(obj){
	    	var id = obj.parentNode.id;
	    	obj.parentNode.className = 'ha';
	    
	    	var nodeId = obj.id.substring(0,obj.id.length-2);
	    	
	    	var elements = ele('ul1').children;
	    	for(var i=0; i<elements.length; i++){
	    		if(elements[i].id!= id){
	    			elements[i].className = '';
	    			var trId = elements[i].id.substring(0,elements[i].id.length-2);
	    			if(document.getElementById(trId)){
	    				document.getElementById(trId).style.display = "none";	    			
	    			}
	    		}
	    	}
	    	elements = ele('dropDownUl').children;
	    	for(var i=0; i<elements.length; i++){
	    		if(elements[i].id!= id){
	    			elements[i].className = '';
	    			var trId = elements[i].id.substring(0,elements[i].id.length-2);
	    			if(document.getElementById(trId)){
	    				document.getElementById(trId).style.display = "none";	    			
	    			}
	    		}
	    	}
	    	
			if(document.getElementById(nodeId)){
				document.getElementById(nodeId).style.display = "";
				loadData(nodeId);
			}    	
	    }
	    
	    function loadData(id){
	    	var xxdm = document.getElementById("xxdm").value;
	    	if(id == "xjyd"){
	    		getXjydxx();
	    	}
	    	if(id == "jtxx"){
	    		getJtxxInfo();
	    	}
	    	if(id == "dtjs"){
	    		getSxjyInfo();
	    	}
	    	if(id == "pjpy"){
	    		getPjpyInfo();
	    	}
	    	if(id == "dwjl"){
	    		getDwjlInfo();
	    	}
	    	if(id == "xszz" && xxdm == "10628#"){
	    		//西昌学院
	    		getXcxyXszzInfo();
	    	}
	    	if(id == "qgzx"){
	    		getQgzxInfo();
	    	}
	    	if(id == "xljk"){
	    		getXljkInfo();
	    	}
	    	if(id == "xsjx"){
	    		getXsjxInfo();
	    	}
	    	if(id == "qtsj"){
	    		getQtsjInfo();
	    	}
	    	if(id == "wjcf"){
	    		getWjcfInfo();
	    	}
	    	if(id == "xsgy"){
	    		getXsgyInfo();
	    	}
	    	if(id == "sztz"){
	    		if (xxdm == '11032'){
	    			getTjzySztz();
	    		} else {
		    		getTzHd();
	    		}
	    		
	    	}
	    	if(id == "bzrfdy"){
	    		getBDinfo();
	    	}
	    	if(id == "xnmzsztz"){
	    		getTzBjJy();
	    	}
	    	if(id == "xycjd"){
	    		getXycjdInfo();
	    	}
	    	if(id == "jsxxsztz"){
	    		getTzRzXx();
	    	}
	    	if(id == "lssfxscj"){
	    		getXskccjInfo();
	    	}
	    	if(id == "zgmyxscj"){
	    		getXskccjInfo();
	    	}
	    	if(id == "xssfxx"){
	    		getChargeInfo();
	    	}
	    	if(id == "xscj"){
	    		getXscjInfo();
	    	}
	    	if(id == "jfqk"){
	    		getXsjfqkInfo();
	    	}
	    	if(id == "xxjl"){
	    		getXsxxjlInfo();
	    	}
	    	if(id == "jygl"){
	    		getJyglInfo();
		    }
		    if(id == "zcqk"){
	    		getZcqkInfo();
		    }
		    if(id == "bzrpy"){
	    		getBzrpyInfo();
		    }
	    }
	    //湖北国土资源，上一页，下一页
	    function nextOrUp2(oper){
	    	var obj = opener;

	    	var cu_R = obj.curr_row.rowIndex;
	    	if(oper == "N" && cu_R < obj.$('rsTable').rows.length){//下一页
	    		obj.rowOnClick(obj.$("rsTable").rows[cu_R+1]);//单击下一行
	    	}else if(cu_R>1){
	    		obj.rowOnClick(obj.$("rsTable").rows[cu_R-1]);//单击上一行
	    	}
	    	stuInfoWin2(obj.curr_row,true);
	    }
	  //湖北国土资源，双击查看详细页面
		var curr_row=null;
		function stuInfoWin2(objTr,isDialog) {
			var xxdm = document.getElementById("xxdm").value;
			curr_row = objTr;
			var url = "/xgxt/xsxx_zxxs_hbktzy.do?method=xsxxDetail&xh=" + curr_row.cells[1].innerText;
			var width=840;
			var height = 600;
			var left = (screen.width/2) - width/2;
			var top = (screen.height/2) - height/2;
//			width=900;
			var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;
			
			window.open(url,"msgWindow", styleStr);
			
		}
	</script>
	</head>
	<body onload="loadchange()" onunload="windowClose();">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生详细信息</a>
			</p>
		</div>
		<form action="/xsxx_zxxs_hbktzy" method="post">
			<input type="hidden" id="xh" name="xh" value="${xh }" />
			<input type="hidden" name="zzxh" id="zzxh" value="${xh }" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" id="titName" name="titName" value="${mrxs }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<%-- 学生基本信息始终显示 --%>
			<table width="97%" align="center" class="formlist breakword">
				<thead>
					<tr>
						<th colspan="6">
							<span>学生信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="15%">
							学号
						</th>
						<td width="25%">
							${rs.xh}
						</td>
						<th width="15%">
							姓名
						</th>
						<td width="25%">
							${rs.xm}
						</td>
						<td rowspan="6" class="nohover"
							style="vertical-align:top; text-align:center;width: 20%">
							<div class="open_img">
								<img
									src="<%=request.getContextPath()%>/stuPicXszp.jsp?xh=${rs.xh}"
									width="96" height="128" />
									入学前照片
								<!--						<p style="color:#0F5DC1">类型：jpg<br />-->
								<!--       						容量：小于100k<br />大小：96*128像素</p>-->
							</div>
						</td>
						<td rowspan="6" class="nohover"
							style="vertical-align:top; text-align:center;width: 20%">
							<div class="open_img">
								<img
									src="<%=request.getContextPath()%>/stuPicZp.jsp?xh=${rs.xh}"
									width="96" height="128" />
									入学后照片
								<!--						<p style="color:#0F5DC1">类型：jpg<br />-->
								<!--       						容量：小于100k<br />大小：96*128像素</p>-->
							</div>
						</td>
					</tr>
					<tr>
						<th>
							性别
						</th>
						<td>
							${rs.xb}
						</td>
						<th>
							出生日期
						</th>
						<td>
							${rs.csrq}
						</td>
					</tr>
					<tr>
						<th>
							民族
						</th>
						<td>
							${rs.mzmc}
						</td>
						<th>
							政治面貌
						</th>
						<td>
							${rs.zzmmmc}
						</td>
					</tr>
					<tr>
						<th>
							身份证号
						</th>
						<td align="left">
							${rs.sfzh}
						</td>
						<th>
							培养层次
						</th>
						<td align="left">
							<logic:equal value="new" name="edition">
								${rs.pyccmc}
							</logic:equal>
							<logic:notEqual value="new" name="edition">
								${rs.pycc}
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<th>
							籍贯
						</th>
						<td colspan="3">
							${rs.jg}
						</td>
					</tr>
					<tr>
						<th>
							来源地区(生源地)
						</th>
						<td colspan="3">
							${rs.lydq}
						</td>
					</tr>
				</tbody>
			</table>
			<br />

			<logic:present name="mkxx">
				<table width="97%" align="center" class="formlist breakword">
					<thead>
						<tr>
							<th colspan="5">
								<span>模块信息</span>
							</th>
						</tr>
					</thead>
				</table>
				<div class="menu" id="nav">
					<div class="compTab" id="card" style="position:relative">
						<div class="comp_title" id="div1">
							<ul id="ul1">
								<logic:notEmpty name="pages">
									<logic:iterate id="card" name="pages" scope="request"
										indexId="s">
										<li id="${card.en}li">
											<a href="#" onclick="changePage(this)" id="${card.en}_a">
												<span>${card.cn}</span> </a>
										</li>
									</logic:iterate>
								</logic:notEmpty>
							</ul>
						</div>
						<div class="morediv" id="morediv"
							style="position:absolute;top:0px;right:3px;text-align:center"
							onmousemove="javascript:document.getElementById('morelist').style.display='block'"
							onmouseout="javascript:document.getElementById('morelist').style.display='none'">
							<p class="more"></p>
							<div class="morelist" id="morelist" style="display:none;">
								<!--[if lte IE 6]><iframe class=navbug></iframe><![endif]-->
								<ul id="dropDownUl">
								</ul>
							</div>
						</div>
					</div>
				</div>

				<script type="text/javascript">
			var sumWidth = 0;
			var splitPositionNo = 0;
			var liDropDownArr = new Array();
			var liDropDownArr2 = new Array();
			var liArr = ele('ul1').children;
			var flag = true;
			
			for(var j=0; j<liArr.length+1; j++){
				if(liArr[j] && $(liArr[j].id)){
					sumWidth += $(liArr[j].id).getWidth();
					if(sumWidth > $('card').getWidth()-300){//确定分界li序号
						flag = false;
				         splitPositionNo = j;
				         for(var k=j;;k++){
				            if(liArr[k] && $(liArr[k].id)){//确定
				                liDropDownArr.push($((liArr[k].id)));//备份li对象
				                
				                $((liArr[k].id)).remove();//删除原来的li对象
				             }else{
				             	if(liDropDownArr.length>0){
			  	 					for(var p=0;p<liDropDownArr.length;p++){
			     					 $('dropDownUl').insert(liDropDownArr[p]);
			     					}
		 						}
		 						break;
				             }
				         }
					}
				}
			}
			
			if(flag && $("morediv")){
				$("morediv").className="";
			}
		</script>

				<div class="tab">
					<!--学生基本信息-->
					<%@ include file="/xsxx/common/xsxx_jbxx.jsp"%>
					<!--家庭信息	-->
					<%@ include file="/xsxx/common/xsxx_jtxx.jsp"%>
					<!--党团建设-->
					<%@ include file="/xsxx/common/xsxx_dtjs.jsp"%>
					<!--评奖评优-->
					<%if(Base.xxdm.equals(Globals.XXDM_SZGYYQZYJSXY)){ %>
					<!--评奖评优苏州工业园区-->
					<%@ include file="/xsxx/common/xsxx_pjpy_szgyyq.jsp"%>
					<%}else{ %>
					<%@ include file="/xsxx/common/xsxx_pjpy_zp.jsp"%>
					<%} %>
					<!--对外交流-->
					<%@ include file="/xsxx/common/xsxx_dwjl.jsp"%>
					<!--学生资助-->
					<%@ include file="/xsxx/common/xsxx_xszz.jsp"%>
					<!--勤工助学-->
					<%@ include file="/xsxx/common/xsxx_qgzx.jsp"%>
					<!--心理健康-->
					<%if(Base.xxdm.equals("10344")){ %>
					<!--浙江中医药大学-->
					<%@ include file="/xsxx/common/xsxx_xljk_zjzy.jsp"%>
					<%}else{ %>
					<%@ include file="/xsxx/common/xsxx_xljk.jsp"%>
					<%} %>
					<!--军训管理-->
					<%@ include file="/xsxx/common/xsxx_jxgl.jsp"%>
					<!--违纪处分-->
					<%@ include file="/xsxx/common/xsxx_wjcf.jsp"%>
					<!--学生成绩-->
					<%@ include file="/xsxx/common/xsxx_xscj.jsp"%>
					<!--学业成绩-->
					<%@ include file="/xsxx/common/xsxx_xscjd.jsp"%>
					<!--收费信息-->
					<%@ include file="/xsxx/common/xsxx_sfxx.jsp"%>
					<!--公寓管理-->
					<%@ include file="/xsxx/common/xsxx_gygl.jsp"%>
					<!--素质拓展-->
					<%@ include file="/xsxx/common/xsxx_sztz.jsp"%>
					<!--思政队伍-->
					<%@ include file="/xsxx/common/xsxx_szdw.jsp"%>
					<!--其它数据-->
					<%@ include file="/xsxx/common/xsxx_qtsj.jsp"%>
					<!--学生学习经历-->
					<%@ include file="/xsxx/common/xsxx_xxjl.jsp"%>
					<!--学生就业管理-->
					<%@ include file="/xsxx/common/xsxx_jygl.jsp"%>
					<!--学生注册情况-->
					<%@ include file="/xsxx/common/xsxx_zcqk.jsp"%>
					<!--班主任评语-->
					<%@ include file="/xsxx/common/xsxx_bzrpy.jsp"%>
			</logic:present>

			<div id="tmpDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>可打印模块</span>
								</th>
							</tr>
						</thead>
						<tr>
							<logic:iterate id="card" name="pages" indexId="index">
								<td>
									${card.cn }&nbsp;&nbsp;&nbsp;
									<input type="checkbox" name="print_mk" value="${card.en }" />
								</td>
								<%if(index>0 && index%2==1){%>
							
						</tr>
						<%} %>
						</logic:iterate>
						<%if(((List)request.getAttribute("pages")).size() == 0){ %>
						<td colspan="2" align="center">
							<b>无可打印模块！</b>
						</td>
						</tr>
						<%} %>
						<%if(((List)request.getAttribute("pages")).size()%2 != 0 && ((List)request.getAttribute("pages")).size() > 0){%>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						</tr>
						<%} %>
						<tr>
							<td colspan='2' align='right'>
								<button type="button" onclick='printOne()'>
									确定
								</button>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<table class="formlist" border="0" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td>
							<div class="btn">
								<logic:notEqual value="stu" name="userType">
									<button type="button" id="buttonUp" style="width:80px" onclick="nextOrUp2()">
										上一个
									</button>
									<button type="button" id="buttonNext" style="width:80px"
										onclick="nextOrUp2('N')">
										下一个
									</button>
								</logic:notEqual>
								<button type="button"
									onclick="viewTempDiv('选择打印模块','tmpDiv',400,350);window.scroll(0,0);"
									style="width:80px" id="buttonSave">
									打 印
								</button>
								<logic:notEqual value="zjck" name="doType">
									<button type="button" onclick="window.close();return false;" style="width:80px;"
										id="buttonClose">
										关 闭
									</button>
								</logic:notEqual>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
		<logic:notEqual value="stu" name="userType">
			<!-- 上一个、下一个到临界点时把相应按钮灰掉 -->
			<script type="text/javascript" defer>
			if (!window.dialogArguments){
				var rowIndex = opener.curr_row.rowIndex;
				var rowsLength = opener.$("rsTable").rows.length;
				if (1==rowIndex) {
					$("buttonUp").disabled=true;
				} 
				if (rowsLength-1 == rowIndex 
							|| opener.$("rsTable").rows[rowIndex+1].getElementsByTagName('input')[0].value==""){
					$("buttonNext").disabled=true;
				}
			} else {
				$("buttonUp").style.display='none';
				$("buttonNext").style.display='none';
			}
		</script>
		</logic:notEqual>
		<!-- 监听页面关闭后把查询页面的选中行样式去掉 -->
		<script type="text/javascript">
			function windowClose(){
				if (!window.dialogArguments && "stu" != $('userType').value){
					var row = opener.$("rsTable").rows[rowIndex];
					row.style.backgroundColor = obj_bgc;
					row == null;
				}
			}
		</script>

	</body>
</html>
