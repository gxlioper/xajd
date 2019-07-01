<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
		<script language="javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript">
		function gnxxsh(doType){
			var url ="/xgxt/yxjzyjs.do?act=Auditing&shenhe=auditing&pkValue=";
			var pkValue ="";
		   		if (curr_row == null) {
					alert("请选择要审核的数据！\n（单击相应的行）");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 550,400);
		    		return true;
		    	}
		}
		
	/*
	批量审核通过
	*/
	 function pass(url){
	   var RowsStr="!!#!!";
	
	   for (i=0; i<document.getElementsByName("pk").length; i++){
		 if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	 }
	   }
	   document.forms[0].pkstring.value = RowsStr;
	
	   if (RowsStr=="!!#!!"){
		  alert("请选择要批量审核的记录！");
		  return false;
	   }
	
	   if (!confirm("确定要通过所选记录？")){
		  return false;
	   }
	   document.forms[0].action=url;
       document.forms[0].submit();
    }
    
    /*
	批量审核否决
	*/
    function notpass(url){
	var RowsStr="!!#!!";
	
	  for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	  }
	  }
	  document.forms[0].pkstring.value = RowsStr;
	
	  if (RowsStr=="!!#!!"){
		alert("请选择要批量审核的记录！");
		return false;
	  }
	
	  if (!confirm("确定要否决所选记录？")){
		return false;
	  }
	document.forms[0].action=url;
    document.forms[0].submit();
   }
    
    
	function querygo(){
		 	document.forms[0].action = "/xgxt/zgdzdxXxwh.do?method=dyhdsgl_Index&xxk=zljy&go=go";
		 	document.forms[0].submit();
    }
    /*
	全部选中
	*/    
      function chec(){
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }		
      /*
	批量删除
	*/
      function del(){
        var url = "/xgxt/zgdzdxXxwh.do?method=dyhdsgl_del&xxk=zljy&czlx=del";
	    var RowsStr="!!#!!";
	
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	   }
	    }
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("请选择要批量删除的记录！");
		   return false;
    	}
	
	    if (!confirm("确定要删除所选记录？")){
		   return false;
	    }
	    document.forms[0].action=url;
        document.forms[0].submit();
     }
      
		function bysjbxxbdel(doType){
		var url = "/xgxt/yxjzyjs.do?methodact=del&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		    } else {
			if (confirm("确定要删除该行数据吗？")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		
		function viewMoreinfo(doType){
		var url = "/xgxt/zgdzdxXxwh.do?method=dyhdsgl_save&xxk=zljy&czlx=view&pkValue=";
		var pkValue ="";
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 //showOpenWindow(url, 750, 500);
		 showTopWin(url, 600, 370);
		 }
		}

		
		function dyhdsupdate(doType){
		var url ="/xgxt/zgdzdxXxwh.do?method=dyhdsgl_save&czlx=update&xxk=zljy&pkValue=";
		var pkValue ="";
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         //alert(url);
		         showTopWin(url, 600, 370);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		
		
		
		
		function jyglxsjbxxbDataExport() {
	       document.forms[0].action = "/xgxt/jyglxsjbxxbDataExport.do?tableName=jygl_xsjbxxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		function yxjzyjsadd(){
			 var url = "/xgxt/yxjzyjs.do?act=add";
			 showTopWin(url, 800, 650);
		}
		</script>
		</head>
	<body>
	
		<html:form action="/zgdzdxXxwh.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>思想教育 - 数据维护 - 党员活动室管理</a>
				</p>
			</div>
			
			<input type="hidden" id="isFdy" value=""/>
			<input type="hidden" id="zyV" name="zyV"/>
			<input type="hidden" id="bjV" name="bjV"/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="xxk" name="xxk" value="zljy" />
				<input type="hidden" name="pkstring" value="" />
			<div class="compTab">
				<div class="comp_title">
				<ul>
					<li id="001m" onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=dyhdsgl_Index&xxk=cdsbsy')">
						<a><span>场地设备使用</span></a>
					</li>
					<li id="002m" onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=dyhdsgl_Index&xxk=zcgl')">
						<a><span>资产管理</span></a>
					</li> 
					<li onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=dyhdsgl_Index&xxk=xxzl')">
						<a><span>学习资料</span></a>
					</li>
					<li id="003m" onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=dyhdsgl_Index&xxk=zljy')" class="ha">
						<a><span>资料借阅</span></a>
					</li>
				</ul>
  				</div>
  				</div>
  				
  				<div class="toolbox">
					<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_zj" onclick="showTopWin('zgdzdxXxwh.do?method=dyhdsgl_save&xxk=zljy&czlx=save',650,450)">增加</a></li>
						<li><a href="#" class="btn_xg" onclick="dyhdsupdate();">修改</a></li>
						<logic:notEqual name="userType" value="xy" scope="session">
						<li><a href="#" class="btn_sc" onclick="del()">删除</a></li>
						</logic:notEqual>
					</ul>
					</div>
				</div>
  				
  				<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th> 书、期刊名称</th>
							<td><html:text property="sqkmc"></html:text></td>
							<th>借阅人</th>
							<td><html:text property="jyr"></html:text></td>
							<th>借阅时间</th>
							<td><html:text property="jysj" onclick="return showCalendar('jysj','y-mm-dd');"></html:text></td>
						</tr>
						<tr>	
							<th>归还时间</th>
							<td><html:text property="ghsj" onclick="return showCalendar('ghsj','y-mm-dd');"></html:text></td>
							<th>联系电话</th>
							<td><html:text property="lxdh"></html:text>
							</td>
						</tr>
		
					</tbody>
					
					<tfoot>
		        		<tr>
	          			<td colspan="6">
	            		<div class="btn">
	              		<input type="hidden" name="go" value="a" />
	              		<button type="button" class="btn_cx" id="search_go" 
	              		onclick="querygo();">
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
					    		查询结果&nbsp;&nbsp;
									<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						    </span>
						    </h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')" disabled="true"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" length="7">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')">
								
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="checkbox" name="pk"
											value="<bean:write name="v"/>" />
									</logic:iterate>
								</td>
<%--								<logic:iterate id="v" name="s" offset="0" length="1">--%>
<%--									<td align="center">--%>
<%--										<bean:write name="v" />--%>
<%--									</td>--%>
<%--								</logic:iterate>--%>
								<logic:iterate id="v" name="s" offset="2" >
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<!--分页显示-->
			     	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zgdzdxDtjsForm"></jsp:include>
					<!--分页显示-->
			</logic:notEmpty>
			</div>
			   
			   
				
			<div id="tmpdiv1"></div>
			<div id="tmpdiv"></div>
			<logic:notEmpty name="delall">
				<logic:equal value="ok" name="delall">
					<script language="javascript">
						alert("删除成功");
						document.getElementById('search_go').click();
					</script>
				</logic:equal>
				<logic:equal value="no" name="delall">
					<script language="javascript">
						alert("删除失败");
						document.getElementById('search_go').click();
					</script>
				</logic:equal>
			</logic:notEmpty>
			<logic:present name="message">
				<input type="hidden" name ="message" id ="message" value = "<bean:write name="message"/>" />
				<script language="javascript">
					alert($('message').value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript">

function openWins(){
	if (document.getElementById('jxjdm').value=='') {
		alert('请选择奖学金类别!');
		return;
	} else {
		url = 'jxjrsdataexp.do?jxjdm=';
		url += document.getElementById('jxjdm').value;
		url += '&xydm=';
		url += document.getElementById('xy').value;
		url += '&zydm=';
		url += document.getElementById('zy').value;
		url += '&bjdm=';
		url += document.getElementById('bj').value;
		url += '&bmlb=';
		url += document.getElementById('dispFalg').value;
		url += '&nj=';
		url += document.getElementById('nj').value;
		window.open(url);
	} 
}
</script>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
