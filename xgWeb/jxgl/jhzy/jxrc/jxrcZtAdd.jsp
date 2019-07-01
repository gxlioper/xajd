<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<script type='text/javascript' src='/xgxt/dwr/interface/jxglJhzyDAO.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/dtjsFunction.js"></script>
	<script type="text/javascript">
	var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
		return count+ "<input type='hidden' style='width:50px'  name='_xuh' value='"+count+"'>";
	},
	function(data){
     var htmltext = "<textarea  name='nr' style='width:150px' rows='3' id='nr" + count + "' />";	  		
		return htmltext;
    },       	
    function(data){
        var htmltext = "<textarea  name='dd' style='width:150px' rows='3' id='dd" + count + "' />";	  	      		
   	    return htmltext;
    },       
    function(data){
 	    var htmltext = "<textarea  name='zzry' style='width:150px' rows='3' id='zzry" + count + "' />";	  		
   	    return htmltext;
    },
	function(data){	
	    var htmltext = "<input type='text' style='width:80px'  name='rq' id='rq" + count + "'  readonly='true' onblur='dateFormatChg(this);hdrqChk(this)' style='cursor:hand;' onclick='time(this.id)'/>";	  		
		return htmltext;
    },   	
    function(data){
    	var htmltext = "<input type='text' style='width:20px'   name='kssjH' id='kssjH" + count + "' onkeypress='return NumInputValue2(this,2);' onblur='onlyNumInput(this);hmsCheck(this);'/>"; 
    	htmltext += ":<input type='text' style='width:20px'  name='kssjM' id='kssjM" + count + "' onkeypress='return NumInputValue2(this,2);' onblur='onlyNumInput(this);hmsCheck(this);'/>"; 
    	htmltext += ":<input type='text' style='width:20px'  name='kssjS' id='kssjS" + count + "' onkeypress='return NumInputValue2(this,2);' onblur='onlyNumInput(this);hmsCheck(this);'/>";     	
    	return htmltext;
    }, 
    function(data){
    	var htmltext = "<input type='text' style='width:20px'  name='jssjH' id='jssjH" + count + "' onkeypress='return NumInputValue2(this,2);' onblur='onlyNumInput(this);hmsCheck(this);'/>"; 
    	htmltext += ":<input type='text' style='width:20px'  name='jssjM' id='jssjM" + count + "' onkeypress='return NumInputValue2(this,2);' onblur='onlyNumInput(this);hmsCheck(this);'/>"; 
    	htmltext += ":<input type='text' style='width:20px'  name='jssjS' id='jssjS" + count + "' onkeypress='return NumInputValue2(this,2);' onblur='onlyNumInput(this);hmsCheck(this);'/>";     	
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
function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
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

function dataSave(the_tab){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;	
	var nd = $("ndV").value;
	var xn = $("xnV").value;
	var xq = $("xqV").value;	
	if(rowLen==0){
	   alert("整体安排不能为空！");
	   return false;
	}
	for(var i=1;i<=rowLen;i++){
	    if($("nr"+i)){
			if($("nr"+i).value == ""){
				alert("第"+i+"行 内容不能为空！");
				return false;
			}
		}
		if($("nr"+i)){
			if($("nr"+i).value.length > 500){
				alert("第"+i+"行 内容字数过大，限500字！");
				return false;
			}
		}
		if($("dd"+i)){
			if($("dd"+i).value == ""){
				alert("第"+i+"行 活动地点不能为空！");
				return false;
			}
		}
		if($("dd"+i)){
			if($("dd"+i).value.length > 50){
				alert("第"+i+"行 地点字数过大，限50字！");
				return false;
			}
		}
		if($("zzry"+i)){
			if($("zzry"+i).value == ""){
				alert("第"+i+"行 组织者不能为空！");
				return false;
			}
		}
		if($("zzry"+i)){
			if($("zzry"+i).value.length > 300){
				alert("第"+i+"行 组织者字数过大，限300字！");
				return false;
			}
		}
		if($("rq"+i)){
			if($("rq"+i).value == ""){
				alert("第"+i+"行 日期不能为空！");
				return false;
			}
		}
	    var kssjH = $("kssjH"+i).value==""?"00":$("kssjH"+i).value;
	    if(kssjH>24){
	    	alert("第"+i+"行 时间最大为24点，请确认！");
			return false;
	    }
	    $("kssjH"+i).value = kssjH;
	    var kssjM = $("kssjM"+i).value==""?"00":$("kssjM"+i).value;
	    if(kssjM>59){
	    	alert("第"+i+"行 分钟最大为59分，请确认！");
			return false;
	    }
	    $("kssjM"+i).value=kssjM;
	    var kssjS = $("kssjS"+i).value==""?"00":$("kssjS"+i).value;
	    if(kssjS>59){
	    	alert("第"+i+"行 秒最大为59秒，请确认！");
			return false;
	    }
	    $("kssjS"+i).value=kssjS;
        var jssjH = $("jssjH"+i).value==""?"00":$("jssjH"+i).value;
        if(jssjH>24){
	    	alert("第"+i+"行 时间最大为24点，请确认！");
			return false;
	    }
        $("jssjH"+i).value=jssjH;
	    var jssjM = $("jssjM"+i).value==""?"00":$("jssjM"+i).value;
	     if(jssjM>59){
	    	alert("第"+i+"行 分钟最大为59分，请确认！");
			return false;
	    }
	    $("jssjM"+i).value = jssjM;
	    var jssjS = $("jssjS"+i).value==""?"00":$("jssjS"+i).value;
	     if(jssjS>59){
	    	alert("第"+i+"行 秒最大为59秒，请确认！");
			return false;
	    }
	    $("jssjS"+i).value = jssjS;	
	    if((kssjH+kssjM+kssjS)>(jssjH+jssjM+jssjS)){
	       alert("第"+i+"行 开始时间大于结束时间！");
		   return false;
	    }								
	}
   showTips('处理数据中，请等待......');
   refreshForm('/xgxt/jxgl_jhzy.do?method=jxrcZtAdd&doType=save&xn='+xn+'&xq='+xq+'&nd='+nd);
   $("buttonSave").disabled=true;
}

function onShow(){
	var pk = $("xnV").value+$("xqV").value+$("ndV").value;
	if(pk != ""){
		dwr.engine.setAsync(false);
		jxglJhzyDAO.dao_getJxrcZt(pk,function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');    
				for(var i=1;i<=data.length;i++){
					if($("nr"+i)){
						$("nr"+i).value = data[i-1].nr;
					}
					if($("dd"+i)){
						$("dd"+i).value = data[i-1].dd;
					}
					if($("rq"+i)){
						$("rq"+i).value = data[i-1].rq;
					}
					if($("zzry"+i)){
						$("zzry"+i).value = data[i-1].zzry;
					}
					if($("kssjH"+i)){
						$("kssjH"+i).value = data[i-1].kssjH;
					}
					if($("kssjM"+i)){
						$("kssjM"+i).value = data[i-1].kssjM;
					}
					if($("kssjS"+i)){
						$("kssjS"+i).value = data[i-1].kssjS;
					}
					if($("jssjH"+i)){
						$("jssjH"+i).value = data[i-1].jssjH;
					}
					if($("jssjM"+i)){
						$("jssjM"+i).value = data[i-1].jssjM;
					}
                    if($("jssjS"+i)){
						$("jssjS"+i).value = data[i-1].jssjS;
					}					
				}
			}		
		});
		dwr.engine.setAsync(true);
	}
}
function cheCkSjfw(){
    if($("ksrq").value==""||$("jsrq").value==""){
        $("buttonSave").disabled=true;
        alert("请先进行该年度、学年、学期军训时间范围设置！");      
    }
}
function  hdrqChk(obj){
   var ksrq = $("ksrq").value;
   var jsrq = $("jsrq").value;
   if(ksrq!=""||jsrq!=""){
       if(obj.value<ksrq||obj.value>jsrq){
         alert("活动日期应在军训时间范围内！");  
         obj.value="";
       }
   } 
}
	</script>
	<body onload="cheCkSjfw();onShow()">
		<html:form action="/jxgl_jhzy">	
		    <input type="hidden" id="pk" name="pk" value="${pk}"/>		
			<input type="hidden" id="xnV" name="xnV" value="${xnV}"/>
			<input type="hidden" id="xqV" name="xqV" value="${xqV}"/>
			<input type="hidden" id="ndV" name="ndV" value="${ndV}"/>			
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：军训管理 - 军训日程 - 整体安排信息维护
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<tr style="height: 23px">					
					<td align="right">
						学年：
					</td>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" readonly="true"/>
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
						<html:text name='rs' property="xq" styleId="xq" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					
					<td align="right">
						年度：
					</td>
					<td align="left">
						<html:text name='rs' property="nd" styleId="nd" readonly="true"/>
					</td>
					<td align="right">
						军训时间范围：
					</td>
					<td align="left" >
					   <html:text name='rsSjFw' property="ksrq" styleId="ksrq" readonly="true" style="width:80px"/>
					   --<html:text name='rsSjFw' property="jsrq" styleId="jsrq" readonly="true" style="width:80px"/>
					</td>
				</tr>								
			</table>
			<fieldset>
				<legend>
					<p>
						<!-- 查询得到的数据量显示区域 -->
						<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
						<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
						&nbsp;&nbsp;<font color="blue">整体安排</font>&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd" style="width: 20px"
							onblur="trAdd('flag','madd')">
						&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel" style="width: 20px"
							onblur="trDelAll('flag')">
						&nbsp;行
					</p>
				</legend>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT" class="tbstyle">
									<!-- 打印时第一行不显示- -->
									<thead style="height: 23px">
										<tr>
											<td nowrap="nowrap" style='width:30px'>
												序号
											</td>
											<td nowrap="nowrap" style='width:100px'>
												内容<font color="red"><限500字></font>
											</td>											
											<td nowrap="nowrap" style='width:100px'>
												地点<font color="red"><限50字></font>
											</td>
											<td nowrap="nowrap" style='width:120px'>
												组织人员<font color="red"><限300字></font>
											</td>	
											<td nowrap="nowrap" style='width:80px'>
												日期
											</td>											
											<td nowrap="nowrap" style='width:50px'>
												开始时间
											</td>
											<td nowrap="nowrap" style='width:80px'>
												结束时间
											</td>																																											
										</tr>
									</thead>
									<tbody width="100%" class="tbstyle" id="flag">
									
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2" id="buttonSave" onclick="dataSave('flag');" style="width: 80px">
						保 存
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="buttonClose" onclick="window.dialogArguments.document.getElementById('search_go').click();window.close();" style="width: 80px" id="buttonClose">
						关 闭
					</button> </span>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
