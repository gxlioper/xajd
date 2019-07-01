<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
		<script type="text/javascript">
		    var count = 1;
		    function trAdd(the_tab,type){
		    var len = document.getElementById(the_tab).rows.length+1;
		    var num = $("numAdd").value;
		    count=len;     
			var cellfu =[
			function(data){
				return count+ "<input type='hidden' style='width:25px'  name='_xuh' value='"+count+"'>";
			},
			function(data){	
			    var htmltext = "<textarea  name='hdnr' style='width:100px' rows='2' id='hdnr" + count + "' />";	  		
				return htmltext;
		    },   	
		    function(data){
		    	var htmltext = "<input type='text' style='width:100px'  name='hddd' id='hddd" + count + "' maxlength='50' />";   	
		    	return htmltext;
		    }, 
		    function(data){
		    	var htmltext = "<input type='text' style='width:100px'  name='hdrq' id='hdrq" + count + "' readonly='true' onblur='dateFormatChg(this)' style='cursor:hand;' onclick='time(this.id)' />";   	
		    	return htmltext;
		    },
		    function(data){
		    	var htmltext = "<input type='text' style='width:40px'  name='hdsj' id='hdsj" + count + "' maxlength='50'/>";   	
		    	return htmltext;
		    },       
			function(data){	    
				var htmltext = "<select style='width:50px'  name='jjf' id='jjf" + count + "'>";
			  		htmltext+= "<option value='加分'>加分</option>";
			  		htmltext+= "<option value='减分'>减分</option></select>";
				return htmltext;
		    },
		    function(data){
		    	var htmltext = "<input type='text' style='width:25px'  name='shfz' id='shfz" + count + "' maxlength='5'";
		    		htmltext += "onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this)'/>";
		    	return htmltext;
		    },       
			function(data){	    
				var htmltext = "<input type='text' style='width:50px'  name='shType' id='shType" + count + "' maxlength='50' value='未审核' readonly=true />";	  		
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
		       if($("shType"+length).value!="未审核"){
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
		            if($("shType"+length).value!="未审核"){
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
		function shsjSave(the_tab){
			var tabobj = document.getElementById(the_tab);
			var rowLen = tabobj.rows.length;
			var nullCout = 0;
			var xh = $("xh").value;
			var xn = $("xnV").value;
			var xq = $("xqV").value;
		
			if(xh == ""){
				alert("请确认所需操作学生");
				return false;
			}
			if(rowLen==0){
			   alert("评分项不能为空！");
			   return false;
			}
			for(var i=1;i<=rowLen;i++){
			    if($("hdnr"+i)){
					if($("hdnr"+i).value == ""){
						alert("第"+i+"活动内容不能为空，请确定");
						return false;
					}
				}
				if($("hdnr"+i)){
					if($("hdnr"+i).value.length > 500){
						alert("第"+i+"活动内容字数过大，限500字内，请确定");
						return false;
					}
				}
				if($("hddd"+i)){
					if($("hddd"+i).value == ""){
						alert("第"+i+"活动地点不能为空，请确定");
						return false;
					}
				}
				if($("hdrq"+i)){
					if($("hdrq"+i).value == ""){
						alert("第"+i+"活动日期不能为空，请确定");
						return false;
					}
				}		
				if($("shfz"+i)){
					if($("shfz"+i).value == ""){
						alert("第"+i+"行分值为空，请确定");
						return false;
					}
				}
			}
		   showTips('处理数据中，请等待......');
		   refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_shsjAdd&doType=save&xn='+xn+'&xq='+xq);
		   $("buttonSave").disabled=true;
		}
		
		function onShow(){
			var pk = $("pk").value;
			if(pk != ""){
				dwr.engine.setAsync(false);
				getSzPjpyDAO.dao_getShsj(pk,function(data){
					if(data !=null && data.length>0){
						$("numAdd").value=data.length;
						trAdd('flag','madd');    
						for(var i=1;i<=data.length;i++){
							if($("hdnr"+i)){
								$("hdnr"+i).value = data[i-1].hdnr == null ? "" : data[i-1].hdnr;
							}
							if($("hddd"+i)){
								$("hddd"+i).value = data[i-1].hddd;
							}
							if($("hdrq"+i)){
								$("hdrq"+i).value = data[i-1].hdrq;
							}
							if($("hdsj"+i)){
								$("hdsj"+i).value = data[i-1].hdsj;
							}
							if($("jjf"+i)){
								$("jjf"+i).value = data[i-1].jjf;
							}
							if($("shfz"+i)){
								$("shfz"+i).value = data[i-1].shfz;
							}
							if($("shType"+i)){
								$("shType"+i).value = data[i-1].xxsh;
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
	</head>
	<body >
		<html:form action="/pjpyszyqwh">
			<input type="hidden" id="url" name="url" value="/pjpyszyqwh.do?method=szyc_shsjAdd"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
			<input type="hidden" id="pk" name="pk" value="${pk}"/>
			<input type="hidden" id="xnV" name="xnV" value="${xnV}"/>
			<input type="hidden" id="xqV" name="xqV" value="${xqV}"/>
			
			<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>综合素质-学生综合素质养成课-社会实践分维护</a>
			</p>
		</div>
			
			<table class="formlist" border="0" id="rsTable" style="width: 100%">
				<tr style="height: 23px">
					<th>
						学号
					</th>
					<td>
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th>
						学年
					</th>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					
					<th>
						姓名
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<th>
						学期
					</th>
					<td align="left">
							<html:text name='rs' property="xq" styleId="xq" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						性别
					</th>
					<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						专业
					</th>
					<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<th>
						班级
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
				</tr>
			</table>
			<p>
				<!-- 查询得到的数据量显示区域 -->
				<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
				<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
				<input type="text" name="numAdd" id="numAdd" style="width: 20px"
					onblur="trAdd('flag','madd')"/>
				&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
				<input type="text" name="numDel" id="numDel" style="width: 20px"
					onblur="trDelAll('flag')"/>
				&nbsp;行
			</p>
			<table id="rsT" class="formlist">
				<!-- 打印时第一行不显示- -->
				<thead style="height: 23px">
					<tr>
						<th>
							序号
						</th>
						<th>
							活动内容<font color="red">(限500字内)</font>
						</th>											
						<th>
							活动地点
						</th>
						<th>
							活动日期
						</th>
						<th>
							活动时间(小时)
						</th>											
						<th>
							加减分
						</th>
						<th>
							所获分值
						</th>	
						<th>
							审核状态
						</th>																						
					</tr>
				</thead>
				<tbody class="tbstyle" id="flag">
				
				</tbody>
			</table>
							
			<div  align="right">
				<span class="openbutton">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2" id="buttonSave" onclick="shsjSave('flag');">
						保 存
					</button>
					</logic:notEqual>
					<button type="button" class="button2" id="buttonClose" 
					onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();" id="buttonClose">
						关 闭
					</button>
				</span>
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
