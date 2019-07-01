<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<base target="_self"/>
	<link id="csss" />
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	//dwr获取title名
	function getTitleName(){
		if($("path")){
			var path=$("path").value;
			cqkjFunc.getTitles(path,function(data){
				document.getElementById('titleName').innerHTML=data;
			})
		}
	}
    </script>
    
</head>
	<body onload="getTitleName()">
		<html:form action="/show_xyjh_list" method="post">
			<input type="hidden" name="path" id="path" value="${path}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><span id="titleName"></span></a>
				</p>
			</div>
			
			<div class="toolbox">
			<div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		          	<input type="hidden" name="go" value="a" />
		            <div class="btn">
		              <button type="button" class="btn_cx" id="search_go" 
		              	onclick="allNotEmpThenGo('/xgxt/show_classElegant_list.do?act=bjfc')">
		              	查 询
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			
			<input type="hidden" id="writeAble" name="writeAble" value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="type" name="type" value="xy" />
			<tbody>
						<tr>
							<td align="left">
								<html:select property="year" style="width:60px" styleId="month">
									<html:option value=""></html:option>
									<html:options collection="yearList" property="en" labelProperty="cn" />
								</html:select>
								年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:select property="month" style="width:40px" styleId="month">
									<html:option value=""></html:option>
									<html:options collection="chkList" property="en" labelProperty="cn" />
								</html:select>
								月
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />名称：
									<logic:equal name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px" styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	结果查询&nbsp;&nbsp;
			    	<logic:notEqual name="isModi" value="yes" scope="request">
			    		<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
			 			 </logic:empty>
			 			 <logic:equal name="isModi" value="yes" scope="request">
							<logic:empty name="rs">
									请修改文档
							</logic:empty>
						 </logic:equal>
			 		 </logic:notEqual>
			 		 <logic:notEmpty name="rs">
			 		 	记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息</font>
					</logic:notEmpty>
			    </span>
			    </h3>
			<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:notEqual name="updataOFF" value="true"> 
								<logic:equal name="writeAble" value="yes"> 
								<td id="work">
										操作
								</td>
								</logic:equal>
								</logic:notEqual>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" ondblclick="sxDis('bjfc')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="0" length="1">
								<logic:equal name="writeAble" value="yes"> 
								<logic:notEqual name="updataOFF" value="true"> 
       							<td align=right>
       								<a href="#" onclick="location='show_classElegant_list.do?documentId=<bean:write name="v"/>&act=bjfc&part=N170303'">修改</a>/ <a href="#" onclick="if(confirm('确实要删除当前计划吗？'))location='class_elegant_del.do?type=bjfc&documentId=<bean:write name="v"/>'" >删除</a> </td>
      							</logic:notEqual>
      							</logic:equal>
      							</logic:iterate>
      							
							</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>
			</div>
			<logic:equal name="writeAble" value="yes"> 
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
				<thead>
			    	<tr>
			        	<th colspan="4"><span>添加文档</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" onclick="sxpub('<bean:write name="type"/>')">提 交</button>
			            <button type="button" type=reset name=b2 value="重填" >重 填</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    
	    		<input type="hidden" name="isModi" id="isModi" value="<bean:write name="isModi" />" /> 
	    		<input type="hidden" name="documentId" id="documentId" value="<bean:write name="documentId" />" />  	
	  			<tbody>
	    		<TR>
	      			<th align=right width="100"> 文档标题： </th> 
	      			<TD align=left colspan="3"> <input type="text" name="title" id="title" style="width:100%" value="<bean:write name="documenttit"/>"> </TD> 
	    		</TR> 
	    		<TR> 
	      		<th align=right width="100"> 文档内容： </th> 
	      		<TD align=center colspan="3"> <INPUT type="hidden" name="content1" value="<bean:write name="documentcont"/>"> 
	        		<IFRAME ID="eWebEditor1"
									src="BatEditor" frameborder="0"
									scrolling="no" width="100%" height="350"></IFRAME> </TD> 
	    		</TR>
	    		</tbody> 
	  			</TABLE> 
	  			</div>	
  			</logic:equal>
		</html:form>
	</body>
</html>
