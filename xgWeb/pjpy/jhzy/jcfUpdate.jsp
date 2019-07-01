<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/jcfJhzyDAO.js'></script>
	<script type="text/javascript">
	var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
		return count+ "<input type='hidden' style='width:100%'  name='_xuh' value='"+count+"'>";
	},
	function(data){	
	    var htmltext = "<select style='width:100%'  name='jfxm' id='jfxm" + count + "'>";
	  		htmltext+= "<option value='获奖'>获奖</option>";
	  		htmltext+= "<option value='学术科技成果'>学术科技成果</option>";
	  		htmltext+= "<option value='文体获奖'>文体获奖</option>";
	  		htmltext+= "<option value='干部任职'>干部任职</option></select> ";
		return htmltext;
    },	
    function(data){
    	var htmltext = "<input type='text' style='width:100%'  name='fz' id='fz" + count + "' maxlength='5'";
    		htmltext += "onblur='onlyNumInput(this)'/>";
    	return htmltext;
    },
    function(data){
    	var htmltext = "<textarea type='text' style='width:100%' name='jfly' id='jfly"+count+"'";
			htmltext+= "rows='2' onblur='chLeng(this,250);'/>";	  
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
function fillValue(obj){
		var xm= "xm" + obj.id.replace("xh","");
		var bj= "bj" + obj.id.replace("xh","");
  		if(obj.value==""){
  		  return false;
  		}	
		getSztzData.getXsInfo(obj.value,function(data){
		if(data!=null){
			$(xm).value=data[0];
			$(bj).value=data[1];
		}else{
			alert("该学号不存在，请输入正确学号！");
			obj.value="";
			obj.focus();
			}
		});

}
function jcfSave(the_tab){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
	
	if(xh == ""){
		alert("请确认所需操作学生");
		return false;
	}
	if(xn == "" || xq == ""){
		alert("学年或者学期不能为空，请确认!!");
		return false;
	}
	if(rowLen == 0){
		alert("评分项不能为空");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
		if($("fz"+i)){
			if($("fz"+i).value == ""){
				alert("第"+i+"行分值为空，请确定");
				return false;
			}
		}
		if($("jfly"+i)){
			if($("jfly"+i).value == ""){
				alert("第"+i+"行加分理由为空，请确定");
				return false;
			}
		}
	}

	if (confirm("保存本次奖惩分后不能再修改其学年学期，确认吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
	   showTips('处理数据中，请等待......');
	   refreshForm('/xgxt/jhzy_jcf.do?method=jcfUpdate&doType=save');
	   $("buttonSave").disabled=true;
	}
}

function onShow(){
	var pk = $("pk").value;
	
	if(pk == ""){
		var xh = $("xh").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
			
		if(xh !="" && xn !="" && xq != ""){
			pk=xh+xn+xq;
		}
	}
	if(pk != ""){
		dwr.engine.setAsync(false);
		jcfJhzyDAO.getJcf(pk,function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');
				for(var i=1;i<=data.length;i++){
					if($("jfxm"+i)){
						$("jfxm"+i).value = data[i-1].jfxm;
					}
					if($("fz"+i)){
						$("fz"+i).value = data[i-1].fz;
					}
					if($("jfly"+i)){
						$("jfly"+i).value = data[i-1].jfly;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
	}
}

function getWjcf(){
	var xn = $("xn").value;
	var xh = $("xh").value;
	var xq = $("xq").value;
	dwr.engine.setAsync(false);
	DWRUtil.removeAllRows("rsTables");
	if(xn != "" && xq != "" && xh != ""){
		jcfJhzyDAO.getWjcf(xn,xq,xh,function(data){
			if(data !=null && data.length>0){
				for(var i=0;i<data.length;i++){
					var wjxx=data[i].replace("&nbsp;","");
					DWRUtil.addRows('rsTables',['dd'],[wjxx]);	
				}
			}		
		});
	}
	dwr.engine.setAsync(true);
}
	</script>
	<body onload="onShow();getWjcf();">
		<html:form action="/jhzy_jcf">
			<input type="hidden" id="url" name="url" value="/jhzy_jcf.do?method=jcfUpdate&doType=add"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
			<input type="hidden" id="pk" name="pk" value="${pk}"/>
			<input type="hidden" id="msg" name="msg" value="${msg}"/>
			<input type="hidden" id="doType" name="doType" value="${doType}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 信息维护 - 奖惩分维护
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<tr style="height: 23px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:select property="xn" style="" onchange="getWjcf();onShow();">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden property="xn" styleId="xn"/>
							<html:select property="xn" style="" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						性别：
					</td>
					<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:select property="xq" style="" onchange="getWjcf();onShow();">
							<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden property="xq" styleId="xq"/>
							<html:select property="xq" style="" disabled="true">
							<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						专业：
					</td>
					<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<td align="right">
						班级：
					</td>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
				</tr>
			</table>
			<table width="100%" class="tbstyle">
				<tbody id="rsTables">
								  
				</tbody>							
			</table>
			<fieldset>
				<legend>
					<p>
						<!-- 查询得到的数据量显示区域 -->
						<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
						<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
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
											<td align="center" nowrap="nowrap" style='width:5%'>
												序号
											</td>
											<td align="center" nowrap="nowrap" style='width:25%'>
												加分项目
											</td>
											<td align="center" nowrap="nowrap" style='width:25%'>
												分值
											</td>
											<td align="center" nowrap="nowrap" style=''>
												加分原因
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
					<button class="button2" id="buttonSave" onclick="jcfSave('flag');" style="width: 80px">
						保 存
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2" id="buttonClose"
						 onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();" 
						 style="width: 80px" id="buttonClose">
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
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
