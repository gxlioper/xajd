<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
<script language="javascript" src="js/sztzFunction.js"></script>
<script type="text/javascript">
var count = 1;
//难证必须输入数字方法
function ckinpdata(obj){
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;
		
		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 120){
			obj.value = '';
			return false;
		}
		return true;
	}
 //增加TR
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
	return count+ "<input type='hidden' style='width:80px'  name='_xuh' value='"+count+"'>";
	},
	function(data){	    
	return "<input type='text' style='width:90px'  name='_xh' id='xh" + count + "' maxlength='20' onblur='fillValue(this)'>";
    },	
	function(data){	    
	return "<input type='text' style='width:50px'  name='_xm' id='xm" + count + "' readonly='true'>";
    },
    function(data){
    return "<input type='text' style='width:180px'  name='_bj' id='bj" + count + "' readonly='true'>";
    },
    function(data){    
	return "<input type='text' style='width:170px' id='cxxm' name='_cxxm'>";	 
    },
    function(data){    
	return "<input type='text' style='width:50px' id='cxfs' name='_cxfs' maxlength='6' onkeyup='ckinpdata(this)'>";	 
    },
    function(data){	    
	return "<select name='_cxlb'><option value='1'>加分</option><option value='0'>扣分</option></select>";	 
    }
	];
	//通过DWR增加TR到TABLE
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
//删除TR
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
//批量删除TR
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
//DWR获取学生信息
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
//保存事件
function jdSave(the_tab){
   var tabobj = document.getElementById(the_tab);
    var rowLen = tabobj.rows.length;
    var xh ="";
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
    
    var nullCout = 0;

        for(j=0;j<rowLen;j++){
            var num = j+1;
            xh = document.getElementById("flag").rows[j].cells[1].getElementsByTagName('input')[0].value;
            if(xh==""){
                alert("第"+num+"行‘学号’为空！");
                return false;
            }
        }
        refreshForm('pjpy_xcxy_cxfadd.do?act=save');
}
	</script>
<body>
	<html:form action="/pjpyxcxywh" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
 			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 学生成绩维护 - 学生操行分信息维护
			</div>
		</div>
		
		<table class="tbstyle" id="rsTable" align="center" style="width:100%">
				<thead>
					<tr>
						<td colspan="7" align="center">操行分录入</td>
					</tr>
				</thead>
				<tr>
					<td>
					
							学年：
						<html:select property="xn"
							style="width:100px;background-color:#FFFFFF" styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
						&nbsp;&nbsp;学期：
						<html:select property="xq"
							style="width:90px;background-color:#FFFFFF" styleId="xq">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>	
						<br/>
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
							
								</td>
				</tr>
			</table>
									<table align="center" style="width:100%" id="rsT"
										class="tbstyle">
										<!-- 打印时第一行不显示- -->
										<thead>
											<tr>
												<td nowrap="nowrap">
													序号
												</td>
												<td nowrap="nowrap">
													学号
												</td>
												<td nowrap="nowrap">
													姓名
												</td>
												<td nowrap="nowrap">
													班级
												</td>
												<td nowrap="nowrap">
													操行项目
												</td>
												<td nowrap="nowrap">
													操行分数
												</td>
												<td nowrap="nowrap">
													操行类别
												</td>
												
											</tr>
										</thead>
										<tbody width="99%" class="tbstyle" id="flag">
										<logic:notEmpty name="rs">
										<logic:iterate name="rs" id="s">
											 <tr>
									            <td >
									            <input type="hidden"  name='_xuh' value="<bean:write name="s" property="xuh"/>">
									            <bean:write name="s" property="xuh"/>													
												</td>
												<td >
													<input type='text' style='width:90px'  name='_xh' maxlength='20' 
													value="<bean:write name="s" property="xh"/>"
													onblur='fillValue(this)'>
												</td>
												<td >
													<input type='text' style='width:50px'  name='_xm' value="<bean:write name="s" property="xm"/>" readonly='true'>
												</td>
												<td >
													<input type='text' style='width:180px'  name='_bj' value="<bean:write name="s" property="bj"/>" readonly='true'>
												</td>
												<td >
													<input type='text' style='width:170px'  name='_cxxm' value="<bean:write name="s" property="cxxm"/>" >
												</td>
												<td >
													<input type='text' style='width:50px'  name='_cxfs' maxlength="6" value="<bean:write name="s" property="cxfs"/>" >
												</td>
												<td >
													<select name="_cxlb">
														<option value="1">加分</option>
														<option value="0">扣分</option>
													</select>
												</td>
										</tr>
										</logic:iterate>
										</logic:notEmpty>
									</table>
		<div class="buttontool" align="center">
					<logic:notEqual value="view" name="flag">
						<button class="button2" id="btn_save" 
						onclick="jdSave('flag')"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<!-- 保存提示信息 -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>