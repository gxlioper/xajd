<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- ͷ�ļ� -->
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
		    	alert("�빴ѡ��Ҫɾ������");
		       return false;
		    } else {
		    	if(confirm("ȷ��Ҫɾ����ѡ�е�����")){
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
		    if(confirm("ȷ��Ҫɾ�����"+num+"�У�")){ 
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
									<span>�Զ����ֶ�ά��</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<logic:notEqual name="doType" value="view">
											<button name="�ύ"
												onclick="saveUpdate('/xgxt/bdsz.do?method=bdszUpdate&doType=save','tabname-zdid-zdmc-zdcd-zdlx-mkdm')">
												����
											</button>
										</logic:notEqual>
										<button name="�ر�" onclick="window.close();return false;">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="30%">
									<font color="red">*</font>ģ������
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
									<font color="red">*</font>��������
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
									<font color="red">*</font>�ֶ�ID
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
									<font color="red">*</font>�ֶ�����
								</th>
								<td>
									<html:text name='rs' property="zdmc" style="width:195px"
										styleId="zdmc" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>�Ƿ����
								</th>
								<td>
									<html:select property="sfbt" value="${rs.sfbt }" style="width:200px">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
							</tr>
							<tr id="zdcdTr">
								<th>
									<font color="red">*</font>�ֶγ���
								</th>
								<td>
									<html:text name='rs' property="zdcd" styleId="zdcd"
										maxlength="4" style="width:195px"
										onkeyup="value=value.replace(/[^\d]/g,'')" />
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>�ֶ�����
								</th>
								<td>
									<logic:present name="doType">
										<html:hidden property="zdlx" value="${rs.zdlx }"/>
										<html:select name="rs" property="zdlx" onchange="changeCon()"
											styleId="zdlx" style="width:200px" disabled="true">
											<html:option value="001">�ı�</html:option>
											<html:option value="006">����</html:option>
											<!-- <html:option value="007">���</html:option> -->
											<html:option value="002">����</html:option>
											<html:option value="003">������</html:option>
											<html:option value="004">���ı�(ռ��һ�е��ı�)</html:option>
											<html:option value="005">�ı���</html:option>
										</html:select>
									</logic:present>
									<logic:notPresent  name="doType">
										<html:select property="zdlx" onchange="changeCon()"
											styleId="zdlx" style="width:200px">
											<html:option value="001">�ı�</html:option>
											<html:option value="006">����</html:option>
											<!-- <html:option value="007">���</html:option> -->
											<html:option value="002">����</html:option>
											<html:option value="003">������</html:option>
											<html:option value="004">���ı�(ռ��һ�е��ı�)</html:option>
											<html:option value="005">�ı���</html:option>
										</html:select>
									</logic:notPresent>
								</td>
							</tr>
						</tbody>
						<tbody id="optr" style="display: none">
							<tr>
								<td colspan="2" >
									<!-- ��ѯ�õ�����������ʾ���� -->
									<button onclick="trAdd('flag','add')">
										+
									</button>
									<button onclick="trDel('flag')">
										-
									</button>
									&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
									<input type="text" name="numAdd" id="numAdd"
										style="width: 20px" onblur="trAdd('flag','madd')" />
									&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
									<input type="text" name="numDel" id="numDel"
										style="width: 20px" onblur="trDelAll('flag')" />
									&nbsp;��
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
													ѡ��ֵ
												</td>
												<td>
													ѡ������
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
									�ֶ�����
								</th>
								<td>
									<html:text name="rs" property="zdpx" styleId="zdpx"
										onblur="onlyNumInput(this)" style="width:195px" />
								</td>
							</tr>
							<tr>
								<th>
									��ѯ��ʾ
								</th>
								<td>
									<html:radio name="rs" property="cxxs" onclick="cxsxDis()"
										value="��ʾ" styleId="cxxs">��ʾ</html:radio>
									<html:radio name="rs" property="cxxs" onclick="cxsxDis()"
										value="����ʾ">����ʾ</html:radio>
								</td>
							</tr>
							<tr>
								<th>
									��ѯ��ʾ˳��
								</th>
								<td>
									<html:text name="rs" property="cxxspx" styleId="cxxspx"
										onblur="onlyNumInput(this)" style="width:195px" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<font color="red">ע���ֶ�ID��������ƴ����Ӣ����д��������ָ����ʦ��Ϊzdls��������¼�봿���ֻ��֣�</font>
									<font color="red">�ֶ�ID���벻���ظ����ֶ�������ʾ���������ִ�СΪ����ʽ</font>
									<font color="red">�ֶ�������¼��������ĺ��ֻ�Ӣ�ġ�</font>
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
