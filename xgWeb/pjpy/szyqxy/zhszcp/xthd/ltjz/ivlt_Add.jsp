<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript">
var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
	return count+ "<input type='hidden' style='width:80px'  name='xuh' value='xuh'>";
	},
	function(data){	    
	return "<input type='text' style='width:200px'  name='jztm' id='jztm" + count + "' maxlength='30''>";
    },	
	function(data){	    
	return "<input type='text' style='width:80px' name='xthdrq' id='xthdrq" + count + "' readonly='true' onblur='dateFormatChg(this)' style='cursor:hand;' onclick='time(this.id)'>";
    },
    function(data){
    return "<input type='text' style='width:100px'  name='jcdj' id='jcdj" + count + "' >";
    },
    function(data){    
	return "<input type='text' style='width:60px' id='ccdj" + count + "' name='ccdj' maxlength='200'>";	 
    },
    function(data){    
    	return "<select style='width:60px' id='jjf" + count + "' name='jjf'><option value='加分'>加分</option><option value='减分'>减分</option></select>";	 
    },
    function(data){    
    	return "<input type='text' style='width:60px' id='pf" + count + "' name='pf' onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this)' maxlength='200'>";	 
     },
     function(data){    
       	return "<input type='text' style='width:60px' id='xxsh" + count + "' value='未审核' readonly='true' name='xxsh' maxlength='10'>";	 
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
    	if($("xxsh"+length).value!="未审核"){
            alert("该记录已经过相关部门审核，不能进行删除操作！");   
            return false;
          }else{
            tabobj.deleteRow(tabobj.rows.length-1);    
          }  
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
               if($("xxsh"+length).value!="未审核"){
                 alert("有已经过相关部门审核的记录，不能进行删除操作！");   
                 return false;
               }
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
function ztbhSave(the_tab){
   var tabobj = document.getElementById(the_tab);
    var rowLen = tabobj.rows.length;
    var xh ="";
    var xm ="";
    var bj = "";
    var bhzt ="";
    var bhnr = "";
    var bhbx = "";
    var fs = "";
    var bz = "";
    var xn = "";
    var xq = "";
    if($("xh")){
        xh = $("xh").value; 
        if(xh==""){
            alert("请确认所需操作学生！");
            return false;
        }
     }
    if($("xn")){
       xn = $("xn").value; 
       if(xn==""){
           alert("学年不能为空！");
           return false;
       }
    }
    if($("xq")){
       xq = $("xq").value;
       if(xq==""){
           alert("学期不能为空！");
           return false;
       }
    }
    var pkValue = xn+xq+xh;
    var nullCout = 0;
    if(rowLen == 0){
		alert("IVT论坛评分项不能为空！！");
		return false;
	}
    for(var i=1;i<=rowLen;i++){
    	 if($("jztm"+i)){
    			if($("jztm"+i).value == ""){
    				alert("第"+i+"行,讲座题目不能为空，请确定");
    				return false;
    				}
     		}
   			if($("xthdrq"+i)){
        			if($("xthdrq"+i).value == ""){
        				alert("第"+i+"行,日期不能为空，请确定");
        				return false;
        			}
    			}
		 }
        refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_dshdAdd&xxk=ivlt&doType=save');
         $("buttonSave").disabled=true;
}
function onShow(){
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
	if(xh != ""){
		dwr.engine.setAsync(false);
		getSzPjpyDAO.dao_getXsXthdDwr(xh,xn,xq,"ivlt",function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');    
				for(var i=1;i<=data.length;i++){
					if($("jztm"+i)){
						$("jztm"+i).value = data[i-1].jztm;
					}
					if($("xthdrq"+i)){
						$("xthdrq"+i).value = data[i-1].xthdrq;
					}
					if($("jcdj"+i)){
						$("jcdj"+i).value = data[i-1].jcdj;
					}
					if($("ccdj"+i)){
						$("ccdj"+i).value = data[i-1].ccdj;
					}
					if($("jjf"+i)){
						$("jjf"+i).value = data[i-1].jjf;
					}
					if($("pf"+i)){
						$("pf"+i).value = data[i-1].pf;
					}
					if($("xxsh"+i)){
						$("xxsh"+i).value = data[i-1].xxsh;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
	}
}

		
jQuery(function(){
	onShow();
})

	</script>
	<body >
		<html:form action="/pjpyszyqwh">
			<input id="url" name="url" type="hidden" value="/pjpy/szyqxy/zhszcp/xthd/ltjz/ivlt_Add.jsp"/>
			<input id="getStuInfo" name="getStuInfo" type="hidden" value="xh"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：综合素质-学生综合素质养成课-IVT论坛录入
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
<%--				<thead>--%>
<%--					<tr style="height: 23px">--%>
<%--						<td colspan="4" align="center">--%>
<%--							思想政治与道德素质分增加--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--				</thead>--%>
				<tr style="height: 23px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<logic:notEqual name="userType" value="stu" scope="session">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />

							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:notEqual>
						<logic:equal name="userType" value="stu" scope="session">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
						</logic:equal>
					</td>
					  <td align="right">
						姓名：
					</td>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
				</tr>
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
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<td align="right">
						专业：
					</td>
					<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
					<td align="right">
					</td>
					<td align="left">
					</td>
				</tr>
			</table>
			<fieldset>
				<legend>
					<p>
						<!-- 查询得到的数据量显示区域 -->
						<input  value="+" onclick="trAdd('flag','add')" />
						<input  value="-" onclick="trDel('flag')" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd" style="width: 50px"
							onblur="trAdd('flag','madd')">
						&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel" style="width: 50px"
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
										<td nowrap="nowrap">
											序号
										</td>
										<td nowrap="nowrap">
											讲座题目
										</td>
										<td nowrap="nowrap">
											日期
										</td>
										<td nowrap="nowrap">
											进场登记
										</td>
										<td nowrap="nowrap">
											出场登记
										</td>
										<td nowrap="nowrap">
											加减分
										</td>
										<td nowrap="nowrap">
											评分
										</td>
										<td nowrap="nowrap">
											学校审核
										</td>
									</tr>
								</thead>
								<tbody class="tbstyle" id="flag">			
								</tbody>				
									</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" class="button2" id="buttonSave" onclick="ztbhSave('flag');" style="width: 80px">
						保 存
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose" onclick="window.dialogArguments.document.getElementById('search_go').click();window.close();" style="width: 80px" id="buttonClose">
						关 闭
					</button> </span>
			</div>
			<logic:present name="done">
				<logic:equal value="yes" name="done">
					<script>
	alert('操作成功！');
</script>
				</logic:equal>
				<logic:equal value="no" name="done">
					<script>
	alert('操作失败！');
</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
