<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/pjpy/ghxy/grjfsq.js"></script>
	<script type="text/javascript">
	
 var count = 1;
 var max = 0;
 function trAdd(the_tab,type,numAdd,lb){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $(numAdd).value;
    var doType = $("doType").value;
    var cellfu = getCellfu(lb);
	
    count=len;  
          	
	if(type=='add'){
		max++;
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          max++;
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
       }
       $(numAdd).value = "";
    }    
}
function time(id){
	return showCalendar(id,'y-mm-dd');
}

function scollChange(obj){
	obj.style.posHeight=obj.scrollHeight;
}

function trDel(the_tab,delRow){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    
    var checkbox = document.getElementsByName(delRow);
    var checkArray = new Array();
    var size = 0
    for(i=0;i<checkbox.length;i++){
    	if(checkbox[i].checked == true){
    		checkArray[size++] = eval(checkbox[i].value);
    	}	
    }
    
    if(checkArray.length == 0){
    	alert('您没有选中要操作的记录，请选择！');
    	return false;
    }
    	
   
    if(confirm('确定要删除选中的记录？')){
    	for(i=0;i<checkArray.length;i++){
    		tabobj.deleteRow(checkArray[i]-1);
    		for(j=0;j<checkArray.length;j++){
    			checkArray[j] += -1;
    		}
   	 }
    
   	 for(i=0;i<checkbox.length;i++){
    		checkbox[i].value=i+1;
  	  }
    }
}

function getCellfu(lb){
	var cellfu = new Array();
	if(lb == 'rzqk'){
			cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text' style='width:100%'  name='dj' id='dj" + max + "'";
				htmltext+=" />";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='bl' id='bl" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='jf' id='jf" + max + "'/>";
	    
	    	return htmltext;
	    }
		];
	}
	
	return cellfu;
}

function trDelAll(the_tab,numDel){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $(numDel).value; 
    if(length==0){
       $(numDel).value = "";
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
    $(numDel).value = "";
}	

function save(url){
	var rowLen = max;
	var nullCout = 0;
	
	var tabLen = document.getElementById('flag1').rows.length;
	
	if(tabLen == null || tabLen == 0){
		alert("请填写获奖信息");
		return false;
	}
	
	for(var i=1;i<=rowLen;i++){
		if($("dj"+i)){
			if($("dj"+i).value == ""){
				alert("表彰等级不能为空，请确定");
				return false;
			}
		}
		if($("bl"+i)){
			if($("bl"+i).value == ""){
				alert("比率不能为空，请确定");
				return false;
			}
		}
		if($("jf"+i)){
			if($("jf"+i).value == ""){
				alert("加分不能为空，请确定");
				return false;
			}
		}
	}
	showTips('处理数据中，请等待......');
	document.forms[0].action = url;
	document.forms[0].submit();
}

function onShow(){
			dwr.engine.setAsync(false);
			var tableName1="ghxy_bjbzf_csb";
			var colList1 =["dj","bl","jf"];
			var pk = "1";
			var pkValue = "1";
			var query =" ";
			getOtherData.getTableListInfo(tableName1, colList1,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;

					trAdd('flag1','madd','numAdd1','rzqk');			

					for(var i=1;i<=data.length;i++){
						if($("dj"+i)){
							var dj = data[i-1].dj;
							if(dj == null){
								dj = "数据库无信息";
							}
							$("dj"+i).value = dj;
						}
						if($("bl"+i)){
							var bl = data[i-1].bl;
							if(bl == null){
								bl = "数据库无信息";
							}
							$("bl"+i).value = bl;
						}
						if($("jf"+i)){
							var jf = data[i-1].jf;
							if(jf == null){
								jf = "数据库无信息";
							}
							$("jf"+i).value = jf;
						}			
					}
				}		
			});	
			dwr.engine.setAsync(true);
}
			
		
		function saveCssz(){
			var xxtd = eval($('xxtdbl').value);
			var xxjl = eval($('xxjlbl').value);
			var xxxg = eval($('xxxgbl').value);
			
			var zbl = xxtd + xxjl + xxxg;
			
			if(zbl>100){
				alert('所有比率和不能超过100%');
				return false;
			}else{
				document.forms[0].action='gdby_dtjs.do?method=pxxxCssz&doType=save';
				document.forms[0].submit();
			}
		}
		
		
		jQuery(function(){
			onShow();
		})
	</script>
	<body>
		<html:form action="/gdby_dtjs.do">
			<input type="hidden" name="doType" value="" />

			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：班级表彰分参数设置
				</div>
			</div>
			<fieldset>
				<legend>
					相关获奖情况
				</legend>
				<p>
					<input  value="+"
						onclick="trAdd('flag1','add','numAdd1','rzqk');"
						style="width: 20px" />
					<input  value="-" onclick="trDel('flag1','delRow1');"
						style="width: 20px" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
					<input type="text" name="numAdd" id="numAdd1" style="width: 20px"
						onblur="trAdd('flag1','madd','numAdd1','rzqk')">
					&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel1" style="width: 20px"
						onblur="trDelAll('flag1','numDel1')">
					&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- 打印时第一行不显示- -->
									<thead style="height: 10px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:15%'>
												选定删除行
											</td>
											<td align="center" nowrap="nowrap" style='width:15%'>
												表彰等级
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												所占比率
												<font color="red">(%)</font>
											</td>
											<td align="center" nowrap="nowrap" style='width:35%'>
												加分
											</td>
										</tr>
									</thead>
									<tbody width="100%" align="center" class="tbstyle" id="flag1">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>

			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" class="button2" id="buttonSave"
						onclick="save('/xgxt/ghxy_bjbz.do?method=bjbzfcssz&doType=save');"
						style="width: 80px">
						保 存
					</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width: 80px">
						关 闭
					</button> </span>
			</div>

			<logic:present name="msg">
				<hidden type="hidden" id="msg" value="${msg}" />
				<script>
					alert($("msg").value);
					Close();
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
