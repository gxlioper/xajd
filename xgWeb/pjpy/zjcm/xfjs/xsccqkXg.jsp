<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xfjs.js'></script>
	<script type="text/javascript">
		var mouseX;
		var mouseY;
		
		function mouseOver(obj) {
		  // 此处记录鼠标停留在组建上的时候的位置, 可以自己通过加减常量来控制离鼠标的距离.
		  mouseX = event.clientX;
		  mouseY = event.screenY;
		 }
		
		function showStuList(id){
			var count = $('tempTb').getElementsByTagName('tr').length;
		
			//显示div
			viewTempDiv('请双击选择学生','bjStu',300,400);
			setVal('curr_xh',id);
		}
		
		function changeWjlx(index){
			var value = selText('wjlxdmArr'+index);
			if(value == '旷课'){
				ele('wjcsArr'+index).readOnly = false;
			}else{
				jQuery('#wjcsArr'+index).val('');
				setVal('wjcsArr'+index,'');
				ele('wjcsArr'+index).readOnly = true;
			}	
		}
		
		function loadSelect(i){
			xfjs.selectDmList("pjpy_xfjs_wjlxdmb",['wjlxdm','wjlxmc'],function(data){
				if(data != null){
					DWRUtil.removeAllOptions("wjlxdmArr"+i);			
					DWRUtil.addOptions("wjlxdmArr"+i,[{wjlxdm:'',wjlxmc:''}],"wjlxdm","wjlxmc");
					DWRUtil.addOptions("wjlxdmArr"+i,data,"wjlxdm","wjlxmc");						
				}
			});	
			
			xfjs.selectDmList("pjpy_xfjs_qjlxdmb",['qjlxdm','qjlxmc'],function(result){
				if(result != null){
					DWRUtil.removeAllOptions("qjlxdmArr"+i);	
					DWRUtil.addOptions("qjlxdmArr"+i,[{qjlxdm:'',qjlxmc:''}],"qjlxdm","qjlxmc");
					DWRUtil.addOptions("qjlxdmArr"+i,result,"qjlxdm","qjlxmc");	
				}
			});	
		}
		
		function trAdd(the_tab,type){
		    var len = document.getElementById(the_tab).rows.length+1;
		    var num = $("numAdd").value;
		    count=len;     
			var cellfu =[
			function(data){
				return count+ "<input type='hidden' style='width:50px'  name='_xuh' value='"+count+"'>";
			},			 
			function(data){
		     var htmltext = "<div><input  name='xhArr' id='xhArr" + count + "' readonly='readonly'  style='width:100px' onclick='showStuList(this.id);' onmousedown='mouseOver(this)'/></div>";	  		
				return htmltext;
		    }, 
		    function(data){
		     var htmltext = "<input  name='xmA' id='xmA" + count + "' disabled='true' style='width:100px'/>";	  		
				return htmltext;
		    },      	
		    function(data){
		        var htmltext = "<select  name='wjlxdmArr' id='wjlxdmArr" + count + "' onchange='changeWjlx(" + count+ ")'/>";	  	      		
		   	    return htmltext;
		    },       
		    function(data){
		 	    var htmltext = "<input  name='wjcsArr' id='wjcsArr" + count + "'  maxLength='4' readonly='readonly' onkeyup='value=value.replace(/[^\\d]/g,\"\");\' style='width:40px'/>";	  		
		   	    return htmltext;
		    },
		    function(data){
		        var htmltext = "<select  name='qjlxdmArr' id='qjlxdmArr" + count + "' />";	  	      		
		   	    return htmltext;
		    },
			function(data){	
			    var htmltext =  "<textarea  name='bzArr'  rows='3' style='width:160px' id='bzArr" + count + "' onblur='chLeng(this,250)'/>";	  	  		
				return htmltext;
		    }	    
			];	
			if(type=='add'){
		      DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
		      //加载select中的数据
			  loadSelect(count);
		    }else{
		       if(num==""||num==null){	
		           return false;
		       }
		       for(i=count;i<=num;i++){          
		          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
		          //加载select中的数据
				  loadSelect(count);
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
		         }
		       for(i=1;i<=num;i++){
		          length--;
		          tabobj.deleteRow(tabobj.rows.length-1);
		       }
		    }
		    $("numDel").value = "";
		}
		
		//显示详细的学生违纪信息
		function showDataList(){
			var pk = val("pk");
			if(pk != null && pk != ""){
				dwr.engine.setAsync(false);
				xfjs.selectXsccqkbByBj(pk,function(data){
					if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');  
				  
				for(var i=1;i<=data.length;i++){
					if($("xhArr"+i)){
						var _xh = data[i-1].xh;
						_xh = _xh == null || _xh=="null" ? "" : _xh;
						$("xhArr"+i).value = _xh ;
					}
					if($("xmA"+i)){
						var _xm = data[i-1].xm;
						_xm = _xm == null || _xm=="null" ? "" : _xm;
						$("xmA"+i).value = _xm ;
					}
					if($("wjlxdmArr"+i)){
						//加载select中的数据
						xfjs.selectDmList("pjpy_xfjs_wjlxdmb",['wjlxdm','wjlxmc'],function(result){
							if(result != null){
								DWRUtil.removeAllOptions("wjlxdmArr"+i);	
								DWRUtil.addOptions("wjlxdmArr"+i,[{wjlxdm:'',wjlxmc:''}],"wjlxdm","wjlxmc");
								DWRUtil.addOptions("wjlxdmArr"+i,result,"wjlxdm","wjlxmc");			
							}
						});		
						var _wjlxdm = data[i-1].wjlxdm;
						_wjlxdm = _wjlxdm == null ? "" : _wjlxdm;
						$("wjlxdmArr"+i).value = _wjlxdm;	
						
						changeWjlx(i);		
					}
					if($("qjlxdmArr"+i)){
						//加载select中的数据
						xfjs.selectDmList("pjpy_xfjs_qjlxdmb",['qjlxdm','qjlxmc'],function(result){
							if(result != null){
								DWRUtil.removeAllOptions("qjlxdmArr"+i);	
								DWRUtil.addOptions("qjlxdmArr"+i,[{qjlxdm:'',qjlxmc:''}],"qjlxdm","qjlxmc");
								DWRUtil.addOptions("qjlxdmArr"+i,result,"qjlxdm","qjlxmc");			
												
							}
						});	
						var _qjlxdm = data[i-1].qjlxdm;
						_qjlxdm = _qjlxdm == null ? "" : _qjlxdm;							
						$("qjlxdmArr"+i).value = _qjlxdm;					
					}
					if($("wjcsArr"+i)){
						var _wjcs = data[i-1].wjcs;
						_wjcs = _wjcs == null ? "" : _wjcs;
						$("wjcsArr"+i).value = _wjcs;
					}
					if($("bzArr"+i)){
						var _bz = data[i-1].bz;
						_bz = _bz == null ? "" : _bz;
						$("bzArr"+i).value = _bz;
					}				
				}
			}
			});
			dwr.engine.setAsync(true);
			}
		}
		
		function xsccqkXg(the_tab){
			var tabobj = document.getElementById(the_tab);
			var rowLen = tabobj != null || tabobj != undefined ? tabobj.rows.length : 0;
			var pk = [];
			var sdrs = toInt(val('sdrs'));
			var wjrs = toInt(val('wjrs'));
			var qqrs = toInt(val('qqrs'));
			
			
			if(qqrs+wjrs>rowLen && sdrs!=0){
				alert('缺勤人数加违纪人数共' +(qqrs+wjrs)+ '人，您只添加了' + rowLen + '条记录！');
				return false;
			}
			
			for(var i=1;i<=rowLen;i++){
		    if($("xhArr"+i)){
				if($("xhArr"+i).value == ""){
					alert("第"+i+"行 学号不能为空！");
					return false;
				}
			}
			if($("wjlxdmArr"+i) && $("qjlxdmArr"+i)){
				if($("wjlxdmArr"+i).value == "" && $("qjlxdmArr"+i).value == ""){
					alert("第"+i+"行 违纪类型和请假类型不能同时为空！");
					return false;
				}
				if($("wjlxdmArr"+i).value != "" && $("qjlxdmArr"+i).value != ""){
					alert("第"+i+"行 违纪类型和请假类型只能选择一项！");
					return false;
				}
			}
			
			if($("wjlxdmArr"+i) && $("wjcsArr"+i)){
				if(selText("wjlxdmArr"+i) == "旷课" && $("wjcsArr"+i).value == ""){
					alert("第"+i+"行 旷课节数不能为空！");
					return false;
				}
			}
			if($("wjcsArr"+i)){
				if($("wjcsArr"+i).value != "" && toInt($("wjcsArr"+i).value)<1){
					alert("第"+i+"行 旷课次数必须大于等于1！");
					return false;
				}
			}
			if($("bzArr"+i)){
				if($("bzArr"+i).value.length > 250){
					alert("第"+i+"行 备注字数过多，限250字！");
					return false;
				}
			}				
			pk[i-1] = $("xhArr"+i).value;	
		}
		//判断记录不能重复
		if(checkArrayEleRepeat(pk)){
			alert("学生重复！");
			return false;
		}
		
		//信息保存
		showTips('处理数据中，请等待......');
	    refreshForm('/xgxt/pjpyxfjs.do?method=xsccqkXgSave');
	    $("buttonSave").disabled=true;
	   }
	   
	   function getXh(xh,xm){
	   	setVal(val('curr_xh'),xh);
	   	document.getElementById('xmA'+val('curr_xh').substr(5)).value=xm;
	   	setVal("curr_xh","");	   
	   	hiddenMessage(true,true);
	   }
	</script>
	</head>
	<body onload="showDataList();">
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
		</div>
	
	
		<input type="hidden" id="curr_xh" value=""/>
		<html:form action="/pjpyxfjs" method="post">
			<input type="hidden" name="pk" id="pk" value="${model.pk}"/>
			
			<div id="bjStu" style="display:none;">
				<div style="width:100%;height:250px;overflow-x:hidden;overflow-y:auto;">
					<table class='formlist' id="tempTb">
						<thead>
							<tr><td nowrap="nowrap">学号</td><td nowrap="nowrap">姓名</td><td nowrap="nowrap">性别&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
						</thead>
						<tbody>
						<logic:iterate name="stuList" id="s">
							<tr ondblclick="getXh(this.cells[0].getElementsByTagName('input')[0].value,this.cells[1].getElementsByTagName('input')[0].value);" style="cursor:hand">						
								<logic:iterate id="v" name="s" offset="0" >
									<td align="left" nowrap="nowrap">
										<bean:write name="v" />
										<input type="hidden" value="${v}"/>
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
						</table>
					</div>
					<div>
						<table class='formlist'>
						<tfoot>
							<tr>
								<td colspan="3">
									<div class='btn'>
										<button type="button" onclick="hiddenMessage(true,true);return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			
			
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>班级学风抽查</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<logic:notEqual value="通过" name="model" property="xxsh">
										<button type="button"  onclick="xsccqkXg('flag')" id="buttonSave">
											保 存
										</button>
									</logic:notEqual>
				
									<button type="button" onclick="window.dialogArguments.document.getElementById('search_go').click();window.close();" id="buttonClose">
										关 闭
									</button> 
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
			<tr>
				<th>
					学年
				</th>
				<td align="left">
					${model.xn}
					<input type="hidden" value="${model.xn}" name="xn"/>
				</td>
				<th>
					抽查日期
				</th>
				<td align="left">
					${model.ccrq}
					<input type="hidden" value="${model.ccrq}" name="ccrq"/>
				</td>
			</tr>
			<tr>
				<th>
					学期
				</th>
				<td align="left">
					${model.xqmc}
					<input type="hidden" value="${model.xq}" name="xq"/>
				</td>
				<th>
					抽查类型
				</th>
				<td align="left">
					${model.jclxmc}
					<input type="hidden" value="${model.jclxdm}" name="jclxdm"/>
				</td>
			</tr>
			<tr>
				<th>
					班级
				</th>
				<td align="left">
					${model.bjmc}
					<input type="hidden" value="${model.bjdm}" name="bjdm"/>
				</td>
				<th>
					应到人数
				</th>
				<td align="left">
					${model.ydrs}					
				</td>
			</tr>
			<tr>
				<th>
					实到人数
				</th>
				<td align="left">
					${model.sdrs}
					<input type="hidden" value="${model.sdrs}" name="sdrs"/>
				</td>
				<th>
					缺勤人数
				</th>
				<td align="left">
					${model.qqrs}
					<input type="hidden" value="${model.qqrs}" name="qqrs"/>					
				</td>
			</tr>		
			<tr>
				<th>
					除缺勤外的违纪人数<br/>(如:吃饭,睡觉等...)
				</th>
				<td align="left">
					${model.wjrs}
					<input type="hidden" value="${model.wjrs}" name="wjrs"/>
				</td>
				<th>
					规定处理时间
				</th>
				<td align="left">
					${model.fdyclsj}
				</td>
			</tr>
			<tr>
				<th>
					抽查用户
				</th>
				<td align="left">
					<html:hidden property="ccyhlx" name="model"/>
					<logic:equal value="xx" name="model" property="ccyhlx">
					学校
					</logic:equal>
					<logic:equal value="xy" name="model" property="ccyhlx">
					<bean:message key="lable.xsgzyxpzxy" />
					</logic:equal>
				</td>	
				<th>
					辅导员处理时间
				</th>
				<td align="left">
					${model.fdysjclsj}
				</td>				
			</tr>	
			<tr>
				<th>
					学校备注
				</th>
				<td align="left" colspan="3">
					${model.bz}
				</td>
			</tr>
			</tbody>	
		<logic:equal value="true" name="flag">
			<div align="center"><font color="red"><b>提示：在相同的检查时间已经做过反馈！</b></font></div>
			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" id="buttonClose" onclick="window.dialogArguments.document.getElementById('search_go').click();window.close();" >
						关 闭
					</button> 
				</span>
			</div>		
		</logic:equal>
		<logic:equal value="false" name="flag">
				<tbody>
					<tr>
						<td colspan="4">
							<p>
						<!-- 查询得到的数据量显示区域 -->
						<input  value="+" onclick="trAdd('flag','add')" style="width: 20px"/>
						<input  value="-" onclick="trDel('flag')" style="width: 20px"/>
						&nbsp;&nbsp;<font color="blue">整体安排</font>&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd" style="width: 20px"
							onblur="trAdd('flag','madd')"/>
						&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel" style="width: 20px"
							onblur="trDelAll('flag')"/>
						&nbsp;行
					</p>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT" class="tbstyle">
									<!-- 打印时第一行不显示- -->
									<thead style="height: 23px">
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
												违纪
											</td>
											<td nowrap="nowrap">
												旷课节数
											</td>
											<td nowrap="nowrap">
												请假
											</td>	
											<td nowrap="nowrap">
												备注<font color="red">限250字</font>
											</td>																																										
										</tr>
									</thead>
									<tbody width="100%" class="tbstyle" id="flag">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
					</tbody>
			<tbody>
				<tr>
					<th width="20%">
						备注
					</th>
					<td align="left" colspan="3">
						<html:textarea property="fdyclbz" name="model" cols="60" rows="4" onblur="chLeng(this,600)" style="width:100%"></html:textarea>
					</td>
				</tr>
			</tbody>
			</logic:equal>
		</table>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				Close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败!");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
