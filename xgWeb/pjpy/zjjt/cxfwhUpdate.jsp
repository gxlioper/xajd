<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
<script type="text/javascript">
 var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    var doType = $("doType").value;
    var cellfu = new Array();
	
    count=len;  
          
		cellfu =[
		function(data){
			return "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+count+"'>"+count;
		},
		function(data){	    
			var htmltext = "<select style='' name='jb1' id='qy" + count + "'>";
		  		htmltext+= $('qy').innerHTML;
			return htmltext;
	    },
	    function(data){	    
			var htmltext = "<select style='' name='jb2' id='lb" + count + "' onchange='selectLb("+count+")'>";
		  		htmltext+= $('lb').innerHTML;
			return htmltext;
	    },
	    function(data){	    
			var htmltext = "<select style='' name='jb3' id='xx" + count + "'>";
		  		htmltext+= $('xx').innerHTML;
			return htmltext;
	    },
		function(data){	    
			var htmltext = "<select  name='jjf' id='jjf" + count + "'>";
		  		htmltext+= "<option value='减分'>减分</option>";
		  		htmltext+= "<option value='加分'>加分</option></select>";
			return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'  name='fz' id='fz" + count + "'";
	    		htmltext += "onkeypress='return sztzNumInputValue(this,7,event)' style='width: 50px;ime-mode:disabled'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' name='rq' id='rq" + count + "'";
	    		htmltext+="onblur='dateFormatChg(this)' style='cursor:hand;' readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	 
	    	return htmltext;
	    },
	    function(data){  
			var htmltext = "<textarea style='width:200px' name='cxbz' id='cxbz"+count+"'";
				htmltext+= "rows='2' onblur='chLeng(this,500);' onpropertychange='scollChange(this)'/>";	  
	    	return htmltext;
	    }
		];

	
	if(type=='add'){
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
       }
       $("numAdd").value = "";
    }    
}
function time(id){
	return showCalendar(id,'y-mm-dd');
}

function scollChange(obj){
	obj.style.posHeight=obj.scrollHeight;
}

function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    
    //var the_table = getPapaElement(the_td, "tbody");
	//alert(the_table.rows.length);
   
   if(confirm("确定要删除第"+(length)+"行？")){
    tabobj.deleteRow(tabobj.rows.length-1);      
   	}
}
function trDelAll(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $("numDel").value; 
    if(length==0){
       $("numDel").value = "";
       return false;     
    }
    if(num==""||num==null){	
       return false;
    }
    if(num>length){
      num = length;
    }
    if(confirm("确定要删除最后"+num+"行？")){ 
         for(i=1;i<=num;i++){           
            length--;
         }
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $("numDel").value = "";
}	

function saveCxf(the_tab,type){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	var xh = $("xh").value;
	
	if(rowLen == 0){
		alert("操行分加减项目不能为空");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
		if($("qy"+i)){
			if($("qy"+i).value == ""){
				alert("第"+i+"行区域为空，请确定");
				return false;
			}
		}
		if($("lb"+i)){
			if($("lb"+i).value == ""){
				alert("第"+i+"行类别为空，请确定");
				return false;
			}
		}
		if($("xx"+i)){
			if($("xx"+i).value == ""){
				alert("第"+i+"行细项为空，请确定");
				return false;
			}
		}
		if($("fz"+i)){
			if($("fz"+i).value == ""){
				alert("第"+i+"行分值为空，请确定");
				return false;
			}
		}
		if($("rq"+i)){
			if($("rq"+i).value == ""){
				alert("第"+i+"行日期为空，请确定");
				return false;
			}
		}
	}
	var url = "/xgxt/zjjtPjpy.do?method=cxfwhUpdate&doType=save";
	showTips('处理数据中，请等待......');
	saveUpdate(url,"");
}

function onShow(){
	
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
	var mklx = $("mklx").value;
	if(mklx == "lr"){
		if(xh != "" && xn != "" && xq != ""){
			dwr.engine.setAsync(false);
			var tableName="zjjt_cxflrb";
			var colList =["jjf","fz","rq","cxbz","jb1","jb2","jb3"];
			var pk = "xn||xq||pjxh";
			var pkValue = xn + xq + xh;
			var query =" ";
			getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd").value=data.length;
					trAdd('flag','madd');			
					for(var i=1;i<=data.length;i++){
						if($("jjf"+i)){
							$("jjf"+i).value = data[i-1].jjf;
						}
						if($("fz"+i)){
							var fz = data[i-1].fz;
							if(fz == null){
								fz = "";
							}
							$("fz"+i).value = fz;
						}
						if($("rq"+i)){
							var rq = data[i-1].rq;
							if(rq == null){
								rq = "";
							}
							$("rq"+i).value = rq;
						}
						if($("cxbz"+i)){
							var cxbz = data[i-1].cxbz;
							if(cxbz == null){
								cxbz = "";
							}
							$("cxbz"+i).value = cxbz;
						}
						
						if($("qy"+i)){
							var qy = data[i-1].jb1;
							if(qy == null){
								qy = "";
							}
							$("qy"+i).value = qy;		
						}
						if($("lb"+i)){
							var lb = data[i-1].jb2;
							if(lb == null){
								lb = "";
							}
							$("lb"+i).value = lb;
							selectLb(i);
						}
						if($("xx"+i)){
							var xx = data[i-1].jb3;
							if(xx == null){
								xx = "";
							}
							$("xx"+i).value = xx;
						}
					}
				}		
			});
			
			dwr.engine.setAsync(true);
		}
	}
}

function selectLb(count){

	var lbid = "lb"+count;
	var xxid = "xx"+count;
	
	var tableName = "zjjt_cxf_dj3"; 
	var dm = "dm"; 
	var mc = "mc";
	var msg = "";
	var pk = "ejdm";
	var pkValue = $(lbid).value;
	
	if(pkValue == ""){
		pk = "";
	}

	getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
		if(data!=null){
			DWRUtil.removeAllOptions(xxid);
			DWRUtil.addOptions(xxid,data,"dm","mc");
			$(xxid).options[0].value = "";
		}
	});
}
</script>
</head>
	<body onload="onShow()">
		<html:form action="/zjjtPjpy">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<!-- 区域列表 -->
			<html:select property="qy" styleClass="select" style="display : none" styleId="qy">
				<html:options collection="qyList" property="dm" labelProperty="mc" />
			</html:select>
			<!-- 类别列表 -->
			<html:select property="lb" styleClass="select" style="display : none" styleId="lb">
				<html:options collection="lbList" property="dm" labelProperty="mc" />
			</html:select>
			<!-- 细项列表 -->
			<html:select property="xx" styleClass="select" style="display : none" styleId="xx">
				<html:options collection="xxList" property="dm" labelProperty="mc" />
			</html:select>
			<!-- 隐藏域 end-->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table class="formlist" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="4"><span>学生信息</span></th>
						</tr>
					</thead>
					<tr style="height: 23px">
						<th width="15%">
							学号
						</th>
						<td align="left" width="35%">
							${rs.xh }
							<html:hidden name='rs' property="xh" styleId="xh"/>
						</td>
						<th width="15%">
							年级
						</th>
						<td align="left">
							${rs.nj }
						</td>
					</tr>
					<tr style="height: 23px">
						<th>
							性别
						</th>
						<td align="left">
							${rs.xb }
						</td>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							${rs.xymc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th>
							姓名
						</th>
						<td align="left">
							${rs.xm }
						</td>
						<th>
							专业
						</th>
						<td align="left">
							${rs.zymc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th>
							身份证号
						</th>
						<td align="left">
							${rs.sfzh }
						</td>
						<th>
							班级
						</th>
						<td align="left">
							${rs.bjmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th>
							校区
						</th>
						<td align="left">
							${rs.xqmc }
						</td>
						<th>
							所属楼栋
						</th>
						<td align="left">
							${rs.ldmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th>
							楼层
						</th>
						<td align="left">
							${rs.cs }
						</td>
						<th>
							寝室
						</th>
						<td align="left">
							${rs.qsh }
						</td>
					</tr>
				</table>
				</div>
				
				<div class="tab">
				<table class="formlist" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="4"><span>操行分信息</span></th>
						</tr>
					</thead>
					<tr style="height: 23px">
						<th align="right" width="15%">
							学年
						</th>
						<td align="left" width="35%">
							<html:select name="rs" property="xn" style="" styleClass="select"styleId="xn" onchange="" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
							<html:hidden name='rs' property="xn" styleId="xn"/>
						</td>
						<th align="right" width="15%">
							学期
						</th>
						<td align="left">
							<html:select name="rs" property="xq" style="" styleClass="select"styleId="xq" onchange="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
							<html:hidden name='rs' property="xq" styleId="xq"/>
						</td>
					</tr>
				</table>
				</div>
				<br/><br/>
				<!-- 录入 -->
				<logic:equal name="mklx" value="lr">
					<p>
						<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
						<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd" style="width: 20px"
							onblur="trAdd('flag','madd')">
						&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel" style="width: 20px"
							onblur="trDelAll('flag')">
						&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				<div class="tab">			
				<table class="formlist" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT" class="dateline">
									<!-- 打印时第一行不显示- -->
									<thead style="height: 23px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:5%'>
												序号
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												区域
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												类别
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												细项
											</td>
											<td align="center" nowrap="nowrap" style='width:10%'>
												加减分
											</td>
											<td align="center" nowrap="nowrap" style='width:5%'>
												分值
											</td>
											<td align="center" nowrap="nowrap" style='width:7%'>
												日期
											</td>
											<td align="center" nowrap="nowrap" style='width:28%'>
												备注
											</td>
										</tr>
									</thead>								
									<tbody width="100%" class="formlist" id="flag">
									</tbody>	
								</table>
							</div>
						</td>
					</tr>
				</table>
				</div>
				</logic:equal>
				<!-- 录入 end-->
				<!-- 查询-->
				<logic:equal name="mklx" value="cx">
				<div>
				<table class="formlist" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<table align="center" style="width: 100%" id="rsT" class="dateline">
								<!-- 打印时第一行不显示- -->
								<thead style="height: 23px">
									<tr>
										<logic:notEqual value="view" name="doType">
											<td align="left" nowrap="nowrap" style='width:5%'>
												<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
											</td>
										</logic:notEqual>
										<td align="left" nowrap="nowrap" style='width:15%'>
											区域
										</td>
										<td align="left" nowrap="nowrap" style='width:15%'>
											类别
										</td>
										<td align="left" nowrap="nowrap" style='width:15%'>
											细项
										</td>
										<td align="left" nowrap="nowrap" style='width:10%'>
											加减分
										</td>
										<td align="left" nowrap="nowrap" style='width:5%'>
											分值
										</td>
										<td align="left" nowrap="nowrap" style='width:7%'>
											日期
										</td>
										<td align="left" nowrap="nowrap" style='width:28%'>
											备注
										</td>
									</tr>
								</thead>								
								<tbody width="100%" id="flag">
									<logic:notEmpty name="rsList">
										<logic:iterate name="rsList" id="cxf">
											<tr>
											<logic:notEqual value="view" name="doType">
												<td align="left" nowrap="nowrap" style='width:5%'>
													<input type="checkbox" id="checkVal" name="checkVal" 
														value="${cxf.id }"/>
												</td>
											</logic:notEqual>
												<td align="left" nowrap="nowrap" style='width:15%'>
													${cxf.jb1 }
												</td>
												<td align="left" nowrap="nowrap" style='width:15%'>
													${cxf.jb2 }
												</td>
												<td align="left" nowrap="nowrap" style='width:15%'>
													${cxf.jb3 }
												</td>
												<td align="left" nowrap="nowrap" style='width:10%'>
													${cxf.jjf }
												</td>
												<td align="left" nowrap="nowrap" style='width:5%'>
													${cxf.fz }
												</td>
												<td align="left" nowrap="nowrap" style='width:7%'>
													${cxf.rq }
												</td>
												<td align="left" nowrap="nowrap" style='width:28%'>
													${cxf.cxbz }
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</tbody>	
							</table>
						</td>
					</tr>
				</table>
				</div>					
				</logic:equal>
				<!-- 查询 end-->
				<div align="right">
		          <div class="btn">
		          	<logic:equal name="mklx" value="lr">
						<logic:notEqual name="doType" value="view">
						<button id="buttonSave" onclick="saveCxf('flag','sh')">
							保 存
						</button> 
						</logic:notEqual>
					</logic:equal>
					<logic:equal name="mklx" value="cx">
						<logic:equal name="doType" value="update">
						<button id="buttonSave" onclick="sumitInfo('/xgxt/zjjtPjpy.do?method=cxfwhUpdate','del')">
							删除
						</button> 
						</logic:equal>
					</logic:equal>
					<button id="buttonClose" onclick="window.close();return false;">
						关 闭
					</button>
		          </div>
				</div>
		
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						window.dialogArguments.document.getElementById("search_go").click();
      					 window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
