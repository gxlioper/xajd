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
		//注册
		function zc(checkboxName,url,type){
			var checkbox = jQuery('input[name='+checkboxName+']:checked');
			if(checkbox.length == 0){
				alertInfo('请选择您要操作的数据');
				return false;
			}

			var xhArr = jQuery('input[name=cbv]');
			//取消注册时验证
			if('qxzc'==type){
				var zcztArr = jQuery('input[name=zcztArr]');
				var cont = 0;
				jQuery(xhArr).each(function(i,n){
					if(n.checked){
						if(zcztArr[i].value!='已注册'){
							cont++;
						}
					}
				});
				if(cont>0){
					alertInfo('只能对已注册的学生做该操作！');
					return false;
				}
			} 

			//不可注册验证相关处理
			var sfkzcArr = jQuery('input[name=sfkzc]');
			var bkzcxh = '';//不可注册学号
			jQuery(xhArr).each(function(i,n){
				if(n.checked){
					if(sfkzcArr[i].value=='不可注册'){
						bkzcxh += n.value + ',';
					}
				}
			});
			if(bkzcxh!=''){
				var allbkzcxh = bkzcxh.substr(0,bkzcxh.length-1);
				var arr = allbkzcxh.split(',');
				var xhpz = '';
				for(var i=0;i<arr.length;i++){
					//不可注册的学生超过四个时，只列出四个...
					if(i<=3){
						xhpz += arr[i]+',';
						if(i==1)
							xhpz+='<br/>';//第二个学号开始换行，避免宽度不足
					}
				}
				alertInfo('以下学生不可注册：<br/>' + xhpz.substr(0,xhpz.length-1) + (arr.length>4 ? '等' : '') + '，<br/>请核对其交费情况');
				return false;
			}
			
			//提示确认信息
			confirmInfo('确定操作？', function(tag){
				if(tag == 'ok'){
					document.forms[0].action = url;
					document.forms[0].submit();
				}
			});
			if ($("pt")) {
				BatAlert.showTips('正在操作，请等待...');
			}
		}

		//未注册
		function wzc(checkboxName,url){
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
			showTopWin('xszc.do?method=xszcdetail&xh='+curr_row.getElementsByTagName('input')[0].value+'&xn='+document.getElementById('xn').value + '&xq=' + document.getElementById('xq').value,660,430);
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
		<html:form action="/xszc" method="post">
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
						<li> <a href="#" onclick="zc('cbv','xszc.do?method=xszc&doType=zc&zt=已注册');" class="btn_shtg">已注册</a> </li>
						<li> <a href="#" onclick="wzc('cbv','xszc.do?method=wzcyydetail');" class="btn_shbtg">未注册</a> </li>
						<li> <a href="#" onclick="zc('cbv','xszc.do?method=xszc&doType=qxzc','qxzc');" class="btn_shbtg">取消注册</a> </li>
					</logic:equal>
					<li><a href="#" class="btn_dc" onclick="expData('xszc.do?method=xszc&doType=exp');">导出</a></li>
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
								onclick="allNotEmpThenGo('xszc.do?method=xszc')">
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
					<th>注册状态</th>
					<td>
						<html:select property="zczt" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="zcztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						交费情况
					</th>
					<td colspan="5">
						<html:select property="sfqf" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="sfqfList" property="en" labelProperty="cn"/>
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
								<input type="checkbox" name="cbv" value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"/>
								<input type="hidden" name="sfkzc" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
								<input type="hidden" name="zcztArr" value="<logic:iterate id="v" name="s" offset="7" length="1">${v}</logic:iterate>"/>
							</td>
							<logic:iterate id="v" name="s" offset="1">
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxjzcForm"></jsp:include>
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
