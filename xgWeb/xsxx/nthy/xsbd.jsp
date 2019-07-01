<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xgxt.action.Base"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/comm/commFunction.js"></script>
	<script type="text/javascript">
		//报到
		function bd(checkboxName,url,type){
			var checkbox = jQuery('input[name='+checkboxName+']:checked');
			if(checkbox.length == 0){
				alertInfo('请选择您要操作的数据');
				return false;
			} 
			//取消报到时验证
			if('qxbd'==type){
				var xhArr = jQuery('input[name=cbv]');
				var bdztArr = jQuery('input[name=bdztArr]');
				var cont = 0;
				jQuery(xhArr).each(function(i,n){
					if(n.checked){
						if(bdztArr[i].value!='已报到'){
							cont++;
						}
					}
				});
				if(cont>0){
					alertInfo('只能对已报到的学生做该操作！');
					return false;
				}
			}
			//提示确认信息
			confirmInfo('确定操作？', function(tag){
				if(tag == 'ok'){
					document.forms[0].action = url;
					document.forms[0].submit();
				}
			});
		}

		//未报到
		function wbd(checkboxName,url){
			var checkbox = jQuery('input[name='+checkboxName+']:checked');
			if(checkbox.length == 0){
				alertInfo('请选择您要操作的数据');
				return false;
			} 
			var cbs = '';
			$(checkbox).each(function(i,n){
				cbs += n.value;
				if((checkbox.length-1)!=i){
					cbs += ',';
				}
			});
			showTopWin(url + '&cb='+cbs,500,280);
		}

		function query(){
			document.getElementById('search_go').click();
		}

		//双击查看
		function view(){
			if(curr_row==null){
				alert('请选择要查看的数据！');
				return false;
			}
			showTopWin('xsbd.do?method=xsbddetail&xh='+curr_row.getElementsByTagName('input')[0].value+'&xn='+document.getElementById('xn').value + '&xq=' + document.getElementById('xq').value,660,430);
		}

		function changeDis(){
			var xn = document.getElementById('xn').value;
			var dqxn = document.getElementById('cur_dqxn').value;
			var xq = document.getElementById('xq').value;
			var dqxq = document.getElementById('cur_dqxq').value;
			if(xn!=dqxn || xq!=dqxq){
				var xhArr = jQuery('input[name=cbv]');
				jQuery(xhArr).each(function(i,n){
					n.disabled = 'disabled';
				});
			}
		}

	</script>
</head>
	<body onload="xyDisabled('xy');changeDis();">		
		<html:form action="/xsbd" method="post">
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="cb" name="cb" value="" />
			<input type="hidden" id="cur_dqxn" name="cur_dqxn" value="<%=Base.currXn %>"/>
			<input type="hidden" id="cur_dqxq" name="cur_dqxq" value="<%=Base.currXq %>"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">	
			<!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<!--读写权-->
					<logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="bd('cbv','xsbd.do?method=xsbd&doType=bd&zt=已报到');" class="btn_shtg">已报到</a> </li>
						<li> <a href="#" onclick="wbd('cbv','xsbd.do?method=wbdyydetail');" class="btn_shbtg">未报到</a> </li>
						<li> <a href="#" onclick="bd('cbv','xsbd.do?method=xsbd&doType=qxbd','qxbd');" class="btn_shbtg">取消报到</a> </li>
					</logic:equal>
					<li><a href="#" class="btn_dc" onclick="expData('xsbd.do?method=xsbd&doType=exp');">导出</a></li>
					<!--end读写权-->
			    </ul>
			  </div>			 
			 <!--查询条件-->
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="allNotEmpThenGo('xsbd.do?method=xsbd')">
							查询
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>	
		      		<th>年级</th>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList()" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
					</td>				
					<th>学号</th>
					<td>
						<html:text property="xh" maxlength="20" style="width:180px"></html:text>
					</td>	
					<th>姓名</th>
					<td>
						<html:text property="xm" maxlength="20" style="width:180px"></html:text>
					</td>	
				</tr>	
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th>专业</th>
					<td>
						<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>班级</th>
					<td>
						<html:select property="bjdm" style="width:180px" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						<html:select property="xn" style="width:180px" onchange="query();">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
					</td>
					<th>学期</th>
					<td>
						<html:select property="xq" style="width:180px" onchange="query();">
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
						</html:select>
					</td>
					<th>报到状态</th>
					<td>
						<html:select property="bdzt" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="bdztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
			  </tbody>
			</table>				
		</div>
		</div>
		
		
		<div class="formbox" id="result">
			<h3 class="datetitle_01">
		    <span>
		    	查询结果&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">未找到任何记录！</font> 
		 		 </logic:empty>
		 		 <logic:notEmpty name="rs">
					<font color="blue">提示：双击可以查看详细信息，单击表头可以排序；只能对当前学年学期数据进行操作！</font> 
		 		 </logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr align="center" style="cursor:hand">
			      		<td>
			      			<input type="checkbox" />
			      		</td>
						<logic:iterate id="tit" name="topTr" scope="request">
							<td id="${tit.en}"
								onclick="tableSort(this)">
								${tit.cn}
							</td>
						</logic:iterate>
					</tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							ondblclick="view();">
							<td>
								<input type="checkbox" name="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
								<input type="hidden" name="bdztArr" value="<logic:iterate id="v" name="s" offset="6" length="1">${v}</logic:iterate>"/>
							</td>
							<logic:iterate id="v" name="s" offset="0">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>			
			   </table>
			   </div>
			   <!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsbdForm"></jsp:include>
			    <!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>

		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	//document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("操作失败！");
				//document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
