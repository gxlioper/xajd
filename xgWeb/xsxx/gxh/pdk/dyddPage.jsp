<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type="text/javascript">
		//��ʼ��
		function onShow(){ 
			jQuery('#buttonSave').focus();	
		}
			var count = 1;
		 	function trAdd(the_tab,type){
			    var len = document.getElementById(the_tab).rows.length+1;
			    var num = $("numAdd").value;
			    count=len;     
				var cellfu =[
			      function(data){
			    	var htmltext = "<input type='checkbox'  name='cbv' id='cbv" + count + "' onblur='chgPkValue(this)'/>";
			    	return htmltext;
			      },
			      function(data){
			    	var htmltext = "<input type='text' name='xqmc' id='xqmc" + count + "' maxlength='15' onblur='chgPkValue(this)'/>";
			    	return htmltext;
			      },
			      function(data){
			    	var htmltext = "<input type='text' name='pjjg' id='pjjg" + count + "' maxlength='15' onblur='chgPkValue(this)'/>";
			    	return htmltext;
			      },
			      function(data){
			    	var htmltext = "<input type='text' name='xssx' id='xssx" + count + "' maxlength='2' onkeyup='checkXssx(this)'/>";
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
			    	alertInfo("�빴ѡ��Ҫɾ������");
			       return false;
			    } else {
			    	confirmInfo("ȷ��Ҫɾ����ѡ�е�����",function(t){
			    		if (t == 'ok'){
			    			for (var i = 0 ; i < tempArr.length ;i++){
					         	tabobj.removeChild(tempArr[i]);
				    		}
			    		}
			    	})
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
			    
			    confirmInfo("ȷ��Ҫɾ�����"+num+"�У�",function(t){
			    	if (t == 'ok'){
			    		 for(i=1;i<=num;i++){           
					        length--;
					     }
					     for(i=1;i<=num;i++){
					        length--;
					        tabobj.deleteRow(tabobj.rows.length-1);
					     }
			    	}
			    })
			    $("numDel").value = "";
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
			
			function checkXssx(obj){
				obj.value=obj.value.replace(/[^\d]/g,'')
			}
			
		</script>
	</head>
	<body>
		<html:form action="/jygl" method="post">
			<input type="hidden" name="pkValue" value="${rs.xh }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<%--
										<button type="button" 
											onclick="saveUpdate('moralCard.do?method=saveDydd&doType=save','');">
											����
										</button>
										<button type="button" id="buttonSave" onclick="window.close();return false;">
											�ر�
										</button>
										--%>
										<button name="����" id="buttonSave" type="button" onclick="refreshForm('moralCard.do?method=saveDydd&doType=save')">
											�� ��
										</button>
										<button type="button"  name="�ر�" onclick="Close()" id="buttonClose">�� ��</button>
									</div>
								</td>
							</tr>
						</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����ȵ�ά��</span><br/>
							</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<th>ѧ��</th>
								<td>${rs.xh }</td>
								<th>����</th>
								<td>${rs.xm }</td>
							</tr>
							<tr>
								<th>�꼶</th>
								<td>${rs.nj }</td>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>${rs.xymc }</td>
							</tr>
							<tr>
								<th>רҵ</th>
								<td>${rs.zymc }</td>
								<th>�༶</th>
								<td>${rs.bjmc }</td>
							</tr>
							<tr>
								<td colspan="4" >
									<!-- ��ѯ�õ�����������ʾ���� -->
									<button type="button" onclick="trAdd('flag','add')">
										+
									</button>
									<button type="button" onclick="trDel('flag')">
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
								<td colspan="4">
									<table style="width:95%">
										<thead>
											<tr>
												<td style="width:20px">
													<input type="checkbox" onclick="selectAll(this);" />
												</td>
												<td>
													ѧ������
												</td>
												<td>
													����/��
												</td>
												<td>
													��ʾ˳��
												</td>
											</tr>
										</thead>
										<tbody id="flag">
											<logic:notEmpty name="dyddList">
												<logic:iterate id="d" name="dyddList" indexId="i">
													<tr>
														<td>
															<input type='checkbox' name='cbv' id="cbv${i+1 }" />
														</td>
														<td>
															<input type="text" name="xqmc"
																onblur="chgPkValue(this)"
																value="${d.xqmc }" id="xqmc${i+1 }" maxlength='15' />
														</td>
														<td>
															<input type="text" name="pjjg"
																onblur="chgPkValue(this)"
																value="${d.pjjg }" id='pjjg${i+1 }' maxlength='15' />
														</td>
														<td>
															<input type="text" name="xssx"
																onblur="chgPkValue(this)"
																value="${d.xssx }" id='xssx${i+1 }' maxlength='2' onkeyup="value=value.replace(/[^\d]/g,'')"/>
														</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script defer="defer">
				//alertInfo('${message}',function(){
				//	if (window.dialogArguments) {
				//		window.close();
				//	}
				//});
				showAlert('${message}',{},{"clkFun":function(){
						refershParent();
					}});
			</script>
		</logic:present>
	</body>
</html>
