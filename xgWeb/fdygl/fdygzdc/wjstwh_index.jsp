<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
	<body >
		<html:form action="/wjstwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>辅导员管理 - 信息维护 - 辅导员工作调查问卷维护</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- 按钮 -->
			 <logic:equal value="yes" name="writeAble" scope="request">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="MoreDo('add');" class="btn_zj"> 增加 </a> </li>
			      <li> <a href="#" onclick="MoreDo('modi');" class="btn_xg"> 修改 </a> </li>
				<li> <a href="#" onclick="MoreDo('del')" class="btn_sc"> 删除 </a> </li>
				<li> <a href="#" onclick="MoreDo('xxwh')" class="btn_sh"> 问题选项维护 </a> </li>
				<li> <a href="#" onclick="MoreDo('wjyl')" class="btn_ck"> 问卷预览 </a> </li>
			    </ul>
			 </div>
			 </logic:equal>
			
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="search_go" onclick="refreshForm('/xgxt/wjstwh.do')"/>
 			<div class="searchtab">
				<table width="100%" border="0">
				      <tfoot>
				        <tr>
				          <td colspan="6">
				            <div class="btn">
				              <button onclick="refreshForm('/xgxt/wjstwh.do?dcAct=save')">
										保存
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
				<thead>
					<tr>
						<td align="left" colspan="2">
							<bean:write name="fdyglForm" property="xn" />学年 
							(<bean:write name="fdyglForm" property="nd" />年度)， 
							第<bean:write name="fdyglForm" property="xq" />学期 辅导员工作调查问卷维护			
						</td>
					</tr>
				</thead>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
				    没有任何问题，请单击"添加"按钮添加问题！					
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
						<tbody>
							<tr>
								<th width="110" align="right">
								调查问卷是否开放:
								</th>
								<td align="left">
									<input type="radio" name="sfkf" value="1" <logic:equal name="sfkf" value="1"> checked </logic:equal>>
									开放 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="sfkf" value="0"<logic:equal name="sfkf" value="0"> checked </logic:equal>>
									关闭
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看问题选项详细信息；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
					  <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
                              <logic:iterate id="tit" name="topTr" offset="0">
                                <td id="<bean:write name="tit" property="en"/>"
                                    onclick="tableSort(this)" nowrap>
                                    <bean:write name="tit" property="cn"/>
                                </td>
                              </logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate  name="rs" id="s">
						   <tr onclick="rowOnClick(this)" style="cursor:hand"
						       ondblclick="MoreDo('view')">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<bean:write name="v" />
									</logic:iterate>
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
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script> 
	</body>
	<logic:equal value="yes" name="done">
	<script type="text/javascript" >	    
	    alert("操作成功！");
	</script> 
	</logic:equal>
	<logic:equal value="no" name="done">
	<script type="text/javascript" >	   
	    alert("操作失败！");
	</script> 
	</logic:equal>
</html>
