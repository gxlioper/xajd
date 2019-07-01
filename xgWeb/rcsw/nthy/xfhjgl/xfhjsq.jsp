<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/xfhjglService.js"></script>
		<script type="text/javascript">
				function loadcheck() {
					var xh = document.getElementById("xh").value;
					var xn = document.getElementById("xn").value;
					xfhjglService.checkXfhjsqxx(xh,xn,function(data){
						if (data == 'shz') {
							document.getElementById("msg").style.display='';
							if (document.getElementById("btn_save")) {document.getElementById("btn_save").disabled = 'disabled';}
						} else {
							document.getElementById("msg").style.display='none';
							if (document.getElementById("btn_save")) document.getElementById("btn_save").disabled = '';
						}
					});
					
				}	
				//���ݱ���
			  function sub(){
			  		var url='rcsw_nthy_xfhjsq.do?doType=save';
				  	if (document.getElementById('xh').value==''||document.getElementById('sqyy').value=='') {
				  		alertInfo("�뽫��*�ŵ���Ϣ��д������",function(){});
				  		return false;
				  	}
				  	if($("show") && $("show").value=="cx"){
				  		url+="&show=cx";
				  	}
					refreshForm(url);
			  }
		</script>
	</head>

	<body onload="loadcheck()">
		<html:form action="/rcsw_nthy_xfhjgl.do" method="post">
			<logic:notEqual name="show" value="cx">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>
			</logic:notEqual>
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
    					alert("�������ѧ����Ч!");
    				</script>
			</logic:equal>
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xh-xm-xymc-nj-zymc-bjmc-xb" />
			<input type="hidden" id="url" name="url" value="/rcsw_nthy_xfhjgl.do?method=xfhjsq" />
			<input type="hidden" id="show" name="show" value="${show }" />	

			
			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="msg" style="display: none;" >
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					1.���������ѧ�ѻ�������ز�������У������ٽ����޸ġ�
				</p>
				<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
			</div>


			<div class="tab" >
				<table width="100%" border="0" class="formlist" >
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
		
									<button type="button" class="button2" id="btn_save" 
										onclick="sub('rcsw_nthy_xfhjsq.do?doType=save','xh-xn-sqyy');">
										�� ��
									</button>
									<logic:equal name="show" value="cx">
										<button type="button" class="button2" onclick="window.close();return false;">
											�� ��
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ�ѻ�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:text  property="xh" readonly="true" value="${rs.xh }"
									styleId="xh"  onkeypress="autoFillStuInfo(event.keyCode,this);"  />
								<logic:notEqual value="student" name="userOnLine">
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>
								
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								�꼶
							</th>
							<td>
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
						</tr>
						<tr>
							
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>����ѧ��
							</th>
							<td>
								<input type="text" id="xn" name="xn" value="${xn }" readonly="readonly"/>
							</td> 
							<th>
								����ʱ��
							</th>
							<td>
								<html:text property="sqsj" styleId="sqsj" value="${sqsj}" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
							<font color="red">*</font>����ԭ��
							</th>
							<td colspan="3">
								<html:textarea property="sqyy" styleId="sqyy" rows="5" style="width:600px"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}',function(t){
					if(t=="ok"){
						dialogArgumentsQueryChick();
			 			window.close();
					}
				});
			</script>
		</logic:present>
	</body>
</html>
