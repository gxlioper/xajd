<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>

	<script type="text/javascript">
var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
	return count+ "<input type='hidden' style='width:80px'  name='_xuh' value='"+count+"'>";
	},
	function(data){	    
	return "<input type='text' style='width:200px'  name='hdzt' id='xh" + count + "' maxlength='20''>";
    },	
	function(data){	    
	return "<input type='text' style='width:80px'  name='hdrq' id='xm" + count + "' maxlength='20''>";
    },
    function(data){
    return "<input type='text' style='width:100px'  name='hddj' id='bj" + count + "' >";
    },
    function(data){    
	return "<input type='text' style='width:60px' id='hdpf' name='hdpf' maxlength='200'>";	 
    },
    function(data){    
    	return "<select style='width:60px' id='jjf' name='jjf'><option value='加分' label='加分'><option value='减分' label='减分'></select>";	 
    },
    function(data){    
    	return "<input type='text' style='width:60px' id='shfz' name='shfz' maxlength='200'>";	 
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
		
        for(j=0;j<rowLen;j++){
            var num = j+1;
            xh = document.getElementById("flag").rows[j].cells[1].getElementsByTagName('input')[0].value;
            //alert(xh);
            //alert(rowLen);
            if(xh==""){
                alert("第"+num+"行‘学号’为空！");
                return false;
            }
        }
        //alert(rowLen);
        //return false;
        refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_zznlUpdate&doType=update');
         $("buttonSave").disabled=true;
}
	</script>
	<body>
		<html:form action="/pjpyszyqwh">
			<input id="url" name="url" type="hidden" value="/pjpy/szyqxy/zhszcp/zznl_Add.jsp"/>
			<input id="getStuInfo" name="getStuInfo" type="hidden" value="xh"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：综合素质-学生综合素质养成课-组织能力查看
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
						<bean:write name="rs" property="xh"/>
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						学期：
					</td>
					<td align="left">
							<bean:write name="rs" property="xq"/>
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						专业：
					</td>
					<td align="left">
							<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
			</table>
		<fieldset>
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
											活动主题
										</td>
										<td nowrap="nowrap">
											日期
										</td>
										<td nowrap="nowrap">
											等级
										</td>
										<td nowrap="nowrap">
											加减分
										</td>
										<td nowrap="nowrap">
											评分
										</td>
									</tr>
								</thead>
								<tbody width="100%" class="tbstyle" id="flag">
									<logic:notEmpty name="rs1">
										<logic:iterate name="rs1" id="s">
											<tr>
												<td>
													<bean:write name="s" property="rnum" />
												</td>
												<td>
													<bean:write name="s" property="hdzt"/>
												</td>
												<td>
													<bean:write name="s" property="hdrq"/>
												</td>
												<td>
													<bean:write name="s" property="hddj"/>
												</td>
												<td>
													<bean:write name="s" property="jjf"/>
												</td>
												<td>
													<bean:write name="s" property="shfz"/>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</tbody>
					</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" class="button2" id="buttonClose" onclick="window.close();return false;" style="width: 80px" id="buttonClose">
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
