<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>

	<script type="text/javascript">
       function toChk(str){                
    	   var pkValue = $("pkValue").value;   
           var url = "/xgxt/pjpyszyqwh.do?method=szyc_xthdViewAndChk&xxk=yybd&doType=save&check="+str; 
		   var RowsStr="";		  		  	   
		   xyshDone = (str=="yes")?"通过":"不通过";
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pk").length; i++){
	    	  if(document.getElementsByName("pk")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pk")[i].value+"','"
	    		 RowsStr+=document.getElementsByName("pk")[i].value+"!!";
	    	  }	    	  
		   }		   
		   if (RowsStr==""){
			   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);		   
		   if (confirm("确定要\""+xyshDone+"\"所选记录？")){
			   if(str=="yes"){
		             getSzPjpyDAO.getPointLimitXthd("yybd",pkVArray,pkValue,function(data){
		                if(data==""){
		                      refreshForm(url);
			                  if($("buttonYes")){
			                    $("buttonYes").disabled=true;
			                  }
			                  if($("buttonNo")){
			                    $("buttonNo").disabled=true;
			                  }	
		                }else{
		                    alert(data);
		                }
		            });
			  	}else{
			  		refreshForm(url);
			        if($("buttonYes")){
			           $("buttonYes").disabled=true;
			        }
			        if($("buttonNo")){
			           $("buttonNo").disabled=true;
			        }	
			  	}
		   }         		                  
       }
	</script>
	</head>
	<body>
		<html:form action="/pjpyszyqwh">
			<input type="hidden" name="xh" id="xh" value="${xh}" />
			<input type="hidden" name="xn" id="xn" value="${xn}" />
			<input type="hidden" name="xq" id="xq" value="${xq}" />
			<input type="hidden" name="pkVStr" id="pkVStr" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}" />
			<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优-学生综合素质养成课-语言表达录入-审核</a>
			</p>
		</div>
			<table class="formlist">
				<tr style="height: 23px">
					<th>
						学号
					</th>
					<td align="left">
                       <bean:write name="rsxs" property="xh" />
					</td>
					<th>
						学年
					</th>
					<td align="left">
						<html:select property="xn" disabled="true"  
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
				<th>
						姓名
					</th>
					<td align="left">
						<bean:write name="rsxs" property="xm" />
					</td>
					<th>
						学期
					</th>
					<td align="left">
						<html:select property="xq"  disabled="true"
								style="padding-left:80px" styleId="xq">								
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
					</td>					
				</tr>
				<tr style="height: 23px">
					<th>
						性别
					</th>
					<td align="left">
						<bean:write name="rsxs" property="xb" />
					</td>
					<th>
						年级
					</th>
					<td align="left">
						<bean:write name="rsxs" property="nj" />
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<bean:write name="rsxs" property="xymc" />
					</td>
					<th>
						专业
					</th>
					<td align="left">
						<bean:write name="rsxs" property="zymc" />
					</td>
				</tr>
							<tr style="height: 23px">
					<th>
						班级
					</th>
					<td align="left">
						<bean:write name="rsxs" property="bjmc" />
					</td>
					<th>
						
					</th>
					<td align="left">
						
					</td>
				</tr>				
			</table>
					记录数
					${rsNum}
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">参加活动列表</font>
						<table class="formlist">
							<thead style="height: 23px">
								<tr >
							        <td>
										<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
							     	</td>
									<th onclick="tableSort(this)">
										序号
									</th>
										<th onclick="tableSort(this)">
											语言表达内容
										</th>
										<th onclick="tableSort(this)">
											日期
										</th>
										<th onclick="tableSort(this)">
											等级
										</th>
										<th onclick="tableSort(this)">
											评分
										</th>
										<th onclick="tableSort(this)">
											审核情况
										</th>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;">										
									<td align=center><input type="checkbox" id="pk" name="pk"
										 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>									   
									</td>																									
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
				<div align="right">
					<button type="button" class="button2" id="buttonSave" onclick="toChk('yes')">
						通过
					</button>
					<button type="button" class="button2" id="buttonSave" onclick="toChk('no')">
						不通过
					</button>
					<button type="button" class="button2" id="buttonClose" onclick="window.close();return false;">
						关闭
					</button>
			</div>
			<logic:present name="done">
				<logic:equal value="true" name="done">
					<script>
						alert('操作成功！');
						Close();
					window.dialogArguments.document.getElementById('search_go').click();
					</script>
				</logic:equal>
				<logic:equal value="false" name="done">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
