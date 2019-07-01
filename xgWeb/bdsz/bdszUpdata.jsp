<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- 头文件 -->
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/bdsz.js'></script>
		<script type="text/javascript" src="js/xtwh/bdsz.js"></script>
		<script language="javascript">
		var count = 1;
	 	function trAdd(the_tab,type){
		    var len = document.getElementById(the_tab).rows.length+1;
		    var num = $("numAdd").value;
		    count=len;     
			var cellfu =[
		      function(data){
		    	var htmltext = "<input type='checkbox'  name='cbv' id='cbv" + count + "' />";
		    	return htmltext;
		      },
		      function(data){
		    	var htmltext = "<input type='text' name='opid' id='opid" + count + "' maxlength='20'/>";
		    	return htmltext;
		      },
		      function(data){
		    	var htmltext = "<input type='text' name='opmc' id='opmc" + count + "' maxlength='25'/>";
		    	return htmltext;
		      }
			];
			
			if(type=='add'){
		       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
		    }else{
		       if(num==""||num==null){	
		           return false;
		       }
		       for(var i=0;i<num;i++){          
		          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
		          count++;
		       }
		       $("numAdd").value = "";
		    }        
		}
	
		function trDel(the_tab){
			var tempArr = new Array();
		    var tabobj = document.getElementById(the_tab);
		    var trArr = tabobj.rows;
		    
		    for (var i = 0 ; i < trArr.length; i++){
		    	if (trArr[i].getElementsByTagName('td')[0].getElementsByTagName('input')[0].checked){
		    		tempArr.push(trArr[i]);
		    	}
		    }
		       
		    if(tempArr==0){
		    	alert("请勾选您要删除的行");
		       return false;
		    } else {
		    	if(confirm("确定要删除第选中的行吗？")){
		    		for (var i = 0 ; i < tempArr.length ;i++){
			         	tabobj.removeChild(tempArr[i]);
		    		}
		   	 	}
		    }
		    
		}
	
		function time(id){
			return showCalendar(id,'y-mm-dd');
		}

		function changeCon(){
			var zdlx = $("zdlx").value;
			if(zdlx=="003"){
				$("optr").style.display="";
			}else{
				$("optr").style.display="none";
			}
			if(zdlx=="002"||zdlx=="003"){
				$("zdcd").value="20";
				$("zdcdTr").style.display="none";
			}else{
				$("zdcdTr").style.display="";
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
		
		function cxsxDis(){
			if($('cxxs').checked){
				$("cxxspx").style.display="";
			}else{
				$("cxxspx").style.display="none";
			}
		
		}
		
		function selectAll(obj){
			
			var rows = document.getElementById('flag').rows;
			
			for (var i = 0 ; i < rows.length; i++){
				if (obj.checked){
					rows[i].getElementsByTagName('td')[0].getElementsByTagName('input')[0].checked = true;
				} else {
					rows[i].getElementsByTagName('td')[0].getElementsByTagName('input')[0].checked = false;
				}
			}
		}
	</script>
	</head>
	<body onload="changeCon();cxsxDis()">
		<html:form action="/bdsz" method="post">
				<input type="hidden" id="pk" name="pkValue" value="${pkValue }" />

				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>自定义字段维护</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<logic:notEqual name="doType" value="view">
											<button name="提交"
												onclick="saveUpdate('/xgxt/bdsz.do?method=bdszUpdate&doType=save','tabname-zdid-zdmc-zdcd-zdlx-mkdm')">
												保存
											</button>
										</logic:notEqual>
										<button name="关闭" onclick="window.close();return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="30%">
									<font color="red">*</font>模块名称
								</th>
								<td width="70%">
									<logic:present name="doType">
										<html:hidden property="mkdm" value="${rs.mkdm }"/>
										<html:select property="mkdm" onchange="setGnbList(this)"
											value="${rs.mkdm }" disabled="true" style="width:200px">
											<html:option value=""></html:option>
											<html:options collection="gnmkList" property="mkdm"
												labelProperty="mkmc" />
										</html:select>
									</logic:present>
									<logic:notPresent name="doType">
										<html:select property="mkdm" onchange="setGnbList(this)" style="width:200px">
											<html:option value=""></html:option>
											<html:options collection="gnmkList" property="mkdm"
												labelProperty="mkmc" />
										</html:select>
									</logic:notPresent>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>功能名称
								</th>
								<td>
									<logic:present  name="doType">
										<html:hidden property="tabname" value="${rs.tabname }"/>
										<html:select property="tabname" styleId="tabname"
											value="${rs.tabname }" disabled="true" style="width:200px">
											<html:option value=""></html:option>
											<html:options collection="gnmcList" property="gnb"
												labelProperty="gnmc" />
										</html:select>
									</logic:present>
									
									<logic:notPresent name="doType">
										<html:select property="tabname" styleId="tabname" style="width:200px">
											<html:option value=""></html:option>
											<html:options collection="gnmcList" property="gnb"
												labelProperty="gnmc" />
										</html:select>
									</logic:notPresent>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>字段ID
								</th>
								<td>
									<logic:notPresent  name="doType">
										<html:text value="${rs.zdid}" style="width:195px"
											property="zdid" styleId="zdid"
											/>
									</logic:notPresent>
									<logic:present  name="doType">
										<html:text style="width:195px"
											readonly="true" value="${rs.zdid }"
											property="zdid" styleId="zdid"
											onkeyup="value=value.replace(/[^a-z]/g,'')"
											onblur="checkKeyWord(this)" maxlength="10" />
									</logic:present>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>字段名称
								</th>
								<td>
									<html:text name='rs' property="zdmc" style="width:195px"
										styleId="zdmc" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>是否必填
								</th>
								<td>
									<html:select property="sfbt" value="${rs.sfbt }" style="width:200px">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
							</tr>
							<tr id="zdcdTr">
								<th>
									<font color="red">*</font>字段长度
								</th>
								<td>
									<html:text name='rs' property="zdcd" styleId="zdcd"
										maxlength="4" style="width:195px"
										onkeyup="value=value.replace(/[^\d]/g,'')" />
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>字段类型
								</th>
								<td>
									<logic:present name="doType">
										<html:hidden property="zdlx" value="${rs.zdlx }"/>
										<html:select name="rs" property="zdlx" onchange="changeCon()"
											styleId="zdlx" style="width:200px" disabled="true">
											<html:option value="001">文本</html:option>
											<html:option value="006">数字</html:option>
											<!-- <html:option value="007">金额</html:option> -->
											<html:option value="002">日期</html:option>
											<html:option value="003">下拉框</html:option>
											<html:option value="004">长文本(占用一行的文本)</html:option>
											<html:option value="005">文本域</html:option>
										</html:select>
									</logic:present>
									<logic:notPresent  name="doType">
										<html:select property="zdlx" onchange="changeCon()"
											styleId="zdlx" style="width:200px">
											<html:option value="001">文本</html:option>
											<html:option value="006">数字</html:option>
											<!-- <html:option value="007">金额</html:option> -->
											<html:option value="002">日期</html:option>
											<html:option value="003">下拉框</html:option>
											<html:option value="004">长文本(占用一行的文本)</html:option>
											<html:option value="005">文本域</html:option>
										</html:select>
									</logic:notPresent>
								</td>
							</tr>
						</tbody>
						<tbody id="optr" style="display: none">
							<tr>
								<td colspan="2" >
									<!-- 查询得到的数据量显示区域 -->
									<button onclick="trAdd('flag','add')">
										+
									</button>
									<button onclick="trDel('flag')">
										-
									</button>
									&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
									<input type="text" name="numAdd" id="numAdd"
										style="width: 20px" onblur="trAdd('flag','madd')" />
									&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
									<input type="text" name="numDel" id="numDel"
										style="width: 20px" onblur="trDelAll('flag')" />
									&nbsp;行
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table style="width:95%">
										<thead>
											<tr>
												<td style="width:20px">
													<input type="checkbox" onclick="selectAll(this);" />
												</td>
												<td>
													选项值
												</td>
												<td>
													选项名称
												</td>
											</tr>
										</thead>
										<tbody id="flag">
											<logic:iterate id="o" name="opList" indexId="i">
												<tr>
													<td>
														<input type='checkbox' name='cbv' id="cbv${i+1 }" />
													</td>
													<td>
														<input type="text" name="opid"
															value="${o.opid }" id="opid${i+1 }" maxlength='20' />
													</td>
													<td>
														<input type="text" name="opmc"
															value="${o.opmc }" id='opmc${i+1 }" maxlength='25' />
													</td>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
						<tbody>
							<tr>
								<th>
									字段排序
								</th>
								<td>
									<html:text name="rs" property="zdpx" styleId="zdpx"
										onblur="onlyNumInput(this)" style="width:195px" />
								</td>
							</tr>
							<tr>
								<th>
									查询显示
								</th>
								<td>
									<html:radio name="rs" property="cxxs" onclick="cxsxDis()"
										value="显示" styleId="cxxs">显示</html:radio>
									<html:radio name="rs" property="cxxs" onclick="cxsxDis()"
										value="不显示">不显示</html:radio>
								</td>
							</tr>
							<tr>
								<th>
									查询显示顺序
								</th>
								<td>
									<html:text name="rs" property="cxxspx" styleId="cxxspx"
										onblur="onlyNumInput(this)" style="width:195px" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<font color="red">注：字段ID建议输入拼音或英文缩写，例：“指导老师”为zdls，不建议录入纯数字或汉字；</font>
									<font color="red">字段ID输入不可重复；字段排序，显示排序以数字大小为排序方式</font>
									<font color="red">字段名建议录入有意义的汉字或英文。</font>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<logic:present name="result">
				<logic:equal value="false" name="result">
					<script defer="defer">
						alert("${message}");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script defer="defer">
					    alert("${message}");
					    if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					 </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
