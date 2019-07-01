<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
<script language="javascript" src="js/comm/commFunction.js"></script>
<script>
function selectAllss(){
	var checkBoxArr = document.getElementsByName("cbv");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
				checkBoxArr[i].checked = true;
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
	}
function viewChk(){
     var url = "/xgxt/pjpyszyqwh.do?method=szyc_zznlViewAndChk";
     url+="&xh="+curr_row.cells[3].innerText;
     url+="&xn="+$("xn").value;
     url+="&xq="+$("xq").value;
     showTopWin(url,"700","800");   
} 
function zznlDel() {
    var RowsStr="!!#!!";
    for (i=0; i<document.getElementsByName("cbv").length; i++){
	   if(document.getElementsByName("cbv")[i].checked){
		RowsStr+=document.getElementsByName("cbv")[i].value+"!!#!!";
	   }
    }
    document.forms[0].pkstring.value = RowsStr;
       if (RowsStr=="!!#!!"){
         alert("请选择要批量删除的记录！\n(单击每条记录前复选框)");
	   return false;
	}

    if (!confirm("确定要删除所选记录？")){
	   return false;
    }
    var url = "/xgxt/pjpyszyqwh.do?method=szyc_zznlDel&go=go";
    document.forms[0].action=url;
    document.forms[0].submit();
}
function zznlHzb(){
    
    var xymc ="";
    if($("xydm").value!=""){ 
      xymc=document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;
    }
    var zymc ="";
    if($("zydm").value!=""){ 
      zymc = document.forms[0].zy.options[document.forms[0].zy.selectedIndex].text;
    }
    var bjmc = "";
    if($("bjdm").value!=""){ 
      bjmc = document.forms[0].bj.options[document.forms[0].bj.selectedIndex].text;
    }
    var xqmc = "";
    if($("xq").value!=""){
       xqmc = document.forms[0].xq.options[document.forms[0].xq.selectedIndex].text;
    }
    var url = "/xgxt/pjpyszyqwh.do?method=shsjHzb&printType=zznl";
    url+="&xqmc="+xqmc;
    url+="&xy="+xymc;
    url+="&zy="+zymc;
    url+="&bj="+bjmc;
    document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

</script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyszyqwh" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>综合素质-学生综合素质养成课- 组织能力 - 审核</a>
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
		<input type="hidden" id="userType" name="userType" value="${userType}"/>
		    	<!-- 批量删除信息提示 -->
    	<input type="hidden" id="pkstring" name="pkstring" />
    	
    	<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_shtg" onclick="sh5s('pjpyszyqwh.do?method=szyc_zznlChk&doType=save','通过');return false;">审核通过</a></li>
					<li><a href="#" class="btn_xg" onclick="sh5s('pjpyszyqwh.do?method=szyc_zznlChk&doType=save','不通过');return false;">审核不通过</a></li>
					<li><a href="#" class="btn_sc" onclick="del5s('/xgxt/pjpyszyqwh.do?method=szyc_zznlDel&go=go');return false;">删除</a></li>
					<li><a href="#" class="btn_tj" onclick="zznlHzb();return false;">汇总</a></li>
				</ul>
			</div>
		</div>
    	
		<div class="searchtab">
			<table width="100%">
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<input type="hidden" name="go" value="a" />
						<button type="button" id="search_go" onclick="refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_zznlManage&go=go');this.disabled=true">
							查询
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							重置
						 </button>
						</div>
						</td>
					</tr>
				</tfoot>
				
				<tbody>
					<tr>
						<th>
							年级
						</th>
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
								<html:option value=""></html:option>
								<html:options collection="xnList2" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>学期</th>
						<td><html:select property="xq" styleClass="select"
								style="padding-left:80px" styleId="xq">		
								<html:option value=""></html:option>					
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						</tr>
						<tr>
							<th>学号</th>
							<td><html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px"></html:text>
							</td>
							<th>姓名</th>
							<td><html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px"></html:text>															
							</td>
							<th></th>
							<td></td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
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
						<td><html:select property="bjdm" 
								style="width:180px" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
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
					<table width="99%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">					
									<td>
										<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;"
									ondblclick="viewChk()">										
									<td align=center>
										<input type = "hidden" id="pk" name="pk" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										<input type="checkbox" id="checkVal" name="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>																								
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzyqxyZhszcpActionForm"></jsp:include>
				</logic:notEmpty>
				<div id="tmpdiv"></div>
			</div>
			<script type="text/javascript" src="js/bottomButton.js"></script>
	</html:form>
</body>
