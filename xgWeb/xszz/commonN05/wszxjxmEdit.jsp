<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function ckinpdata(obj){
			obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
			var inputStr = obj.value;
			
			if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g))){
				obj.value = '';
				return false;
			} else if(inputStr.match(/\d+\.\d*\.\d*/g)){
			    obj.value = obj.value.substr(0,obj.value.length-1);
			    return false;
			}
			return true;
		}
	
		function yz(){
			var zxjmc = document.getElementsByName("_zxjmc");
			for ( var i = 0 ; i < zxjmc.length ; i ++){
				if (zxjmc[i].value.trim()==''){
					alert('助学金名称不能为空!');
					return false;
				}
			}
			
			document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxjxmEditSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function queryJkrxx(cod, obj,redirect,variable){
			if (cod == 13) {
				document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxjxmEdit";
				document.forms[0].submit();
			}
		}
		
function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
	return count;
	},
	function(){
		return "<input type='text' name='_zxjmc' maxlength='20'>";
	},
	function(data){	    
	return "<input type='text' style='width:100%'  name='_xmje' maxlength='8' onkeypress='return onlyNum(this,6)' style='width:100%;ime-mode:disabled'>";
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
	</script>
</head>

<body>
	<div class="title">
		<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>当前所在位置：学生资助 - 基础数据维护 - 外设助学金详细信息</a></p>
		</div>
	</div>
	<html:form action="n05_xszz.do?method=wszxjxmEdit" method="post">
		<input type="hidden" id="act" name="act"
			value="<bean:write name="act" />">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("保存成功！");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<logic:match value="have" name="have">
					<script language="javascript">
		         	alert("已存在此项目代码！");
		         	</script>
				</logic:match>
				<logic:notMatch value="have" name="have">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
	         	</logic:notMatch>
			</logic:match>
		</logic:present>
		<div class="tab">
			<table width="100%"  border="0" class="formlist">
			 <thead>
   				<tr>
       				<th colspan="4"><span>项目维护</span></th>
       			</tr>
  			</thead>
  			<tbody>
			<tr>
				<th style="width:30%">
					项目代码
				</th>
				<td style="width:70%" colspan="3">
					<logic:equal name="act" value="add">
						<input type="text" id="xmdm" maxlength="10" name="xmdm"
							style="width:95%" onkeyup="value=value.replace(/[^\d]/g,'')"
							value="<bean:write name="rs" property="xmdm"/>">
					</logic:equal>
					<logic:notEqual name="act" value="add">
						<input type="text" id="xmdm" maxlength="10" name="xmdm"
							style="width:95%" readonly="readonly"
							value="<bean:write name="rs" property="xmdm"/>">
					</logic:notEqual>
				</td>
			</tr>
			
			<tr>
				<th>
					项目名称
				</th>
				<td colspan="3">
					<input type="text" id="xmmc" maxlength="30" name="xmmc"
						style="width:95%;"
						value="<bean:write name="rs" property="xmmc"/>">
				</td>
			</tr>
			<thead>
			<tr>
				<th colspan="4">	
					<span>具体金额</span>
					<!-- 查询得到的数据量显示区域 -->
					<p>
					<button value="+"  onclick="trAdd('flag','add')" >+</button>
					<button value="-"  onclick="trDel('flag')" >-</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
					<input type="text" name="numAdd" id="numAdd" style="width: 50px"
						onblur="trAdd('flag','madd')">
					&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel" style="width: 50px"
						onblur="trDelAll('flag')"/>
					&nbsp;行
					</p>
				</th>
			</tr>
			</thead>
			<tr>
				<td colspan="4">
					<table align="center" style="width:100%" id="rsT" class="tbstyle">
						<thead>
							<tr>
								<th width="30%">
									行号
								</th>
								<th width="35%" >
									助学金名称
								</th>
								<th width="35%" >
									金额
								</th>
							</tr>
						</thead>
						<tbody class="tbstyle" id="flag">
							<logic:notEmpty name="xmjeList">
								<logic:iterate name="xmjeList" id="s">
									<tr>
										<td >
											<bean:write name="s" property="hh" />
										</td>
										<td>
											<bean:write name="s" property="zxjmc" />
											<input type="hidden" name="_zxjmc" value="<bean:write name="s" property="zxjmc" />">
										</td>
										<td>
											<input type='text' style='width:100%' name='_xmje' 
												onkeypress="return onlyNum(this,6)" maxlength="6" style="width:100%;ime-mode:disabled"
												value="<bean:write name="s" property="xmje"/>" maxlength="10">
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="xmjeList">
							</logic:empty>
						</tbody>
					</table>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		          	<button  id="buttonSave" onClick="if($('xmdm').value.trim() == ''|| $('xmmc').value.trim() == ''){alert('项目代码或者项目名称不能为空！');return false;}else{yz()};">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="buttonClose"
						onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						关 闭
					</button>						           
		          </div>
		          </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
</body>
</html>
