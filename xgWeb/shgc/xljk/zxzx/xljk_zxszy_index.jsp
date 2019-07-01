<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
 <script type="text/javascript">   

 var   Rows=new   Array(); //所有选中的行对象   
 var   ShiftStartRow=""; //Shift多选时存储开始行对象   
    
  //选行主函数   
  function   onfocusit(){   
  	iRow=window.event.srcElement;   
  do{   
  	iRow=iRow.parentElement; 
  }	while(iRow.tagName!='TR')   
    
  //Ctrl多选   
  if(event.ctrlKey){   
  		var   j=-1;   
  		for(i=0;i<Rows.length;i++){   
  			if(iRow==Rows[i]){
  			//	alert("a");
 				//alert(Rows[i].getElementsByTagName("input")[0].value);   
  				j=i;   
  				break;   
  			}   
  		}   
  		if(j!=-1){    //j==0
  			for(i=j;i<Rows.length-1;i++){   
  			Rows[i]=Rows[i+1];
  			//alert("b");
  			//alert(Rows[i].getElementsByTagName("input")[0].value);
 			}   
  			Rows.length=Rows.length-1;   
 	 	}else{   
  			Rows[Rows.length]=iRow;   
  		}   
 	 		ShiftStartRow=iRow;   
  	}else{	
		if (Rows.length!=0){
			for (i=0; i<Rows.length; i++){	
				if (Rows[i].tagName.toLowerCase() == "tr") {
					Rows[i].style.backgroundColor = "#FFFFFF";
						// alert("aa");  
						// var kk=document.forms[0].xn_id.value;
						//document.
  	 					//alert(Rows[i].getElementsByTagName("input")[0].value);
	    		}
			}
		}
		Rows.length=1;
		Rows[0]=iRow;
//		ShiftStartRow=iRow;
	}
	 changeColor(iRow);
}
	
	
  function changeColor(E){   
	  for(var i=1;i<E.parentElement.rows.length;i++){   
	  	 E.parentElement.rows(i).style.backgroundColor="#FFFFFF"; 
	  }   
	  for(i=0;i<Rows.length;i++){   
	  	Rows[i].style.backgroundColor="#EEEEEE";   
	  }   
  }
  
  function pipei() {
	   var len=Rows.length;
	   var str="";
	   if(Rows.length==0){
	   		alert("请至少选择一行！");
	   		return false;
	   }
	   for(i=0;i<Rows.length;i++){
	  		str=str+Rows[i].getElementsByTagName("input")[0].value+(i==Rows.length-1?"":",");
	  	}
	  		showTopWin('/xgxt/xljk_zxsxx_view.do?doType=View_Zxszy&xn_id='+str,600,520);
  }
  
  function view() {
  	 	for(i=0;i<Rows.length;i++){
  			var xn_id=Rows[i].getElementsByTagName("input")[0].value;
  			showTopWin('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl&doType=View&xn_id='+xn_id,700,450);
  		}
  }
  
  function del(){
  		var len=Rows.length;
   		var str="";
   		if(Rows.length==0){
   			alert("请至少选择一行！");
   			return false;
   		}else {
			if (confirm("确定要删除该行数据吗？")) {
			underDealWith();
			for(i=0;i<Rows.length;i++){
  				//alert(Rows[i].getElementsByTagName("input")[0].value);
  				str=str+Rows[i].getElementsByTagName("input")[0].value+(i==Rows.length-1?"":",");
  			}
  			refreshForm('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl&doType=Del&xn_id='+str);
			return true;
			} 
			else {
				return false;
			}
		}
  }
  
	function ppzxs() {
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		var arrId = "";
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				arrId += checkBoxArr[i].value + "!!@@!!";
				flag = true;
			}
		}
		
		if(!flag){
			alert("请勾选要处理的数据");
			return false;
		}
		
		$("arrId").value = arrId;
		
		showTopWin('/xgxt/xljk_zxsxx_view.do?doType=View_Zxszy',600,520);
	}
  
	//批量提交
	function sumitInfo(url,doType){
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		
		if(flag){
			url+="&doType="+doType;
			if (confirm("确定要删除所勾选的数据?")) {
				showTips('处理数据中，请等待......');
				refreshForm(url);
			}
		}else{
			alert("请勾选要处理的数据");
			return false;
		}
	}
  </script>   
 	</head> 
	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>心理健康 - 心理咨询 - 预约资源管理</a>
			</p>
		</div>
	
		<html:form action="/xljk_zxszyAtion" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="arrId" name="arrId" value="" />
			
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="showTopWin('/xgxt/xljk_zxszy_add.do?act=xljk_zxszygl','500','410');"
									class="btn_zj">增 加 资 源</a>
							</li>
							<%-- 非广州大学 --%>
							<logic:notEqual name="xxdm" value="11078">
								<li>
									<a href="#"
										onclick="del();"
										class="btn_sc"> 删 除 </a>
								</li>
							</logic:notEqual>
							<%-- 广州大学 --%>
							<logic:equal name="xxdm" value="11078">
								<li>
									<a href="#"
										onclick="sumitInfo('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl','del');"
										class="btn_sc"> 删 除 </a>
								</li>
							</logic:equal>
							<%-- 非广州大学 --%>
							<logic:notEqual name="xxdm" value="11078">
								<li>
									<a href="#"
										onclick="pipei();"
										class="btn_xg">匹配咨询师</a>
								</li>
							</logic:notEqual>
							<%-- 广州大学 --%>
							<logic:equal name="xxdm" value="11078">
								<li>
									<a href="#"
										onclick="ppzxs();"
										class="btn_xg">匹配咨询师</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="refreshForm('/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
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
									地点
								</th>
								<td>
									<html:select property="dd_dm" style="width:100px"
										styleId="dd_dm">
										<html:option value=""></html:option>
										<html:options collection="ddList" property="DMH"
											labelProperty="DMMC" />
									</html:select>
								</td>
								<th>
									时间段
								</th>
								<td>
									<html:text styleId="dateF" property="rq"
										onclick="return showCalendar('dateF','y-mm-dd');"
										readonly="readonly" />
								</td>
								<th>
									咨询师
								</th>
								<td>
									<html:select property="zxxbh" style="width:100px"
										styleId="sjd_dm" onchange="">
										<html:option value=""></html:option>
										<html:options collection="zxxList" property="zxxbh"
											labelProperty="zxxxm" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> <logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序.</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr>
									<%-- 广州大学 --%>
									<logic:equal name="xxdm" value="11078">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" />
										</td>
									</logic:equal>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this);" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" offset="0">
									<tr style="cursor:hand" onclick="onfocusit()"
										ondblclick="view()">
										<%-- 广州大学 --%>
										<logic:equal name="xxdm" value="11078">
											<td align="center">
												<input type="checkbox" id="checkVal" name="checkVal"
													value="<bean:write name="s" property="XN_ID"/>" />
											</td>
										</logic:equal>
										<td>
											<input type="hidden" id="xn_id" name="xn_id"
												value="<bean:write name="s" property="XN_ID"/>" />
											<bean:write name="s" property="RQ" />
										</td>
										<td>
											<bean:write name="s" property="SJD" />
										</td>
										<td>
											<bean:write name="s" property="DD" />
										</td>
										<td>
											<bean:write name="s" property="ZXXXM" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<logic:notEmpty name="message">
			<logic:equal value="del_success" name="message">
				<script type="text/javascript">
						alert("删除成功!");
				</script>
			</logic:equal>
			<logic:equal value="del_fail" name="message">
				<script type="text/javascript">
						alert("删除失败!");
				</script>
			</logic:equal>
			<logic:equal value="insert_success" name="message">
				<script type="text/javascript">
						alert("保存成功!");
				</script>
			</logic:equal>
			<logic:equal value="insert_fail" name="message">
				<script type="text/javascript">
						alert("保存失败!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

