<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
		<script type="text/javascript">
		function xjbjModiSave(){
		    var bjdm = "";
		    var bjqk = "";
		    var xn   = "";
		    if($("bjdm")){
		       bjdm = $("bjdm").value;
		    }
		    if($("xn")){
		       xn=$("xn").value;
		    }
		    if($("bjqk")){
		       bjqk = $("bjqk").value;
		    }
		    if(bjdm==""){
		       alert("班级代码不能为空！");
		       return false;
		    }
		    if(xn==""){
		       alert("学年不能为空！");
		       return false;
		    }
		    if(bjqk.length>500){
		       alert("班级情况字数过大，限500字内！");
		       return false;
		    }
		    var tem = bjdm+xn;
		     getPjpyDao.getInfoEx("zjlg_xjbjb","bjdm||xn",tem," yxsh='通过'",function(tag){
				     if(tag){
				        alert("该学年、该班级该信息已经通过审核，不能再进行修改操作！");   	         			        
				     }else{
				        if(confirm("确定要保存数据！")){
				           refreshForm("/xgxt/zjlgPjpy.do?method=xjbjModi&doType=save");
		                   if($("buttonSave")){
		                      $("buttonSave").disabled =true;
		                   }
				        }              
				     }
		    	});	    
		}
		</script>
	</head>
	<body>
	<html:form action="/zjlgPjpy" method="post">
		<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue"/>" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 荣誉称号申请 - 先进班级修改</a>
			</p>
		</div>
		
		<div class="tab">
		<table width="100%" class="formlist">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>填写申请表</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th scope="col" width="15%">
					<font color="red">*</font>班级代码
				</th>
				<td align="left" width="30%">
				<html:text name="rs" property="bjdm" readonly="true"></html:text>
				</td>
				<th>学年</th>
				<td align="left">
					<html:select name="rs" property="xn" disabled="true">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					年级
				</th>
				<td align="left">
					${rs.nj}
				</td>
				<th>
					班级名称
				</th>
				<td align="left">
					${rs.bjmc}
				</td>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc}
				</td>
				<th>
					辅导员
				</th>
				<td align="left">
					${fdy}
				</td>
			</tr>
			<tr>
				<th>
					专业
				</th>
				<td align="left">
					${rs.zymc}
				</td>
				<th>
					班级人数
				</th>
				<td align="left">
					${xhnum}
				</td>
			</tr>
			<tr>
				<th>
					班级平均成绩
				</th>
				<td align="left">
					${bjpjf}
				</td>
				<th>
					不及格率
				</th>
				<td align="left">
					${bjbjdl}
				</td>
			</tr>			
			<tr>
				<th>
					文明寝室个数
				</th>
				<td align="left">
					<html:text name="rs" property="wmqsgs" styleId="wmqsgs"
						onkeypress='return sztzNumInputValue(this,3,event)'
						onblur="onlyNumInput(this)" />
				</td>
				<th>
					A级寝室个数
				</th>
				<td align="left">
					<html:text name="rs" property="ajqsgs" styleId="ajqsgs"
						onkeypress='return sztzNumInputValue(this,3,event)'
						onblur="onlyNumInput(this)" />
				</td>
			</tr>
			<tr>
				<th>
					是否优秀班级
				</th>
				<td align="left">
					<html:select name="rs" property="sfyxbj" styleId="sfyxbj"
						style="width:120px;">
						<html:option value="否">否</html:option>
						<html:option value="是">是</html:option>
					</html:select>
				</td>
				<th></th><td></td>
			</tr>			
			<tr>
				<td width="13%" scope="row" align="right">
					班级情况
					<span style="color: red">(限500字)</span>
				</td>
				<td colspan="3" scope="row" align="left">
					<html:textarea  name="rs" rows="12" style="width:98%" property="bjqk" />
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
					  <logic:notEqual value="view" name="act">
						<button type="button" id="buttonSave" onclick="xjbjModiSave()">
							保 存
						</button>
					  </logic:notEqual>
					  <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</html:form>
</body>
<logic:equal name="done" value="true">
	<script>
	alert("添加成功！");
	Close();
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	 window.dialogArguments.document.all("search_go").click();  	 
    }			          
    </script>
</logic:equal>
<logic:equal name="done" value="false">
	<script>
	alert("添加失败！");
	</script>
</logic:equal>
<logic:equal name="pass" value="no">
	<script>
	alert("该班级不满足先进班级申请条件！");			    
   </script>
</logic:equal>
</html>
