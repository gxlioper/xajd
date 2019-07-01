<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body onload="xyDisabled('xy');">			
			<html:form action="/data_search" method="post">
			<input type="hidden" id="modiTag" name="modiTag" value="${modiTag }"/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />	
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="zzmm" id="zzmm" value="${zzmm}" />
			<input type="hidden" name="zdmc" id="zdmc" value="${zdmc}" />
			<input type="hidden" name="tableName" id="tableName" value="${tableName}" />
			<logic:equal name="xxdm" value="11641">
				<logic:present name="jq">
				<input type="hidden" name="jq" id="jq" value="<bean:write name="jq"/>" />
				</logic:present>
			</logic:equal>
			<logic:equal name="xxdm" value="12061">
				<logic:present name="zzlb">
				<input type=hidden name=zzlb id="zzlb" value="<bean:write name="zzlb"/>" />
				</logic:present>
				<logic:present name="kns">
				<input type="hidden" name="kns" id="kns" value="<bean:write name="kns"/>" />
				</logic:present>
			</logic:equal>
			<logic:equal name="xxdm" value="13108">
				<logic:present name="jq">
				<input type="hidden" name="jq" id="jq" value="<bean:write name="jq"/>" />
				</logic:present>
			</logic:equal>
			<logic:equal name="xxdm" value="12861">
				<logic:present name="jq">
				<input type="hidden" name="jq" id="jq" value="<bean:write name="jq"/>" />
				</logic:present>
			</logic:equal>
			<logic:equal name="xxdm" value="10355">
				<logic:present name="by">
				<input type="hidden" name="by" id="by" value="<bean:write name="by"/>" />
				</logic:present>
			</logic:equal>
			<logic:equal name="xxdm" value="10491">
				<logic:present name="gfs">
				<input type="hidden" name="gfs" id="gfs" value="<bean:write name="gfs"/>" />
				</logic:present>
				<logic:present name="wlxx">
				<input type="hidden" name="wlxx" id="wlxx" value="<bean:write name="wlxx"/>" />
				</logic:present>
			</logic:equal>
			<logic:equal name="xxdm" value="10344">
				<logic:present name="xn">
				<input type="hidden" name="xn" id="xn" value="<bean:write name="xn"/>" />
				</logic:present>
				<logic:present name="xq">
				<input type="hidden" name="xq" id="xq" value="<bean:write name="xq"/>" />
				</logic:present>
				<logic:present name="sbsj">
				<input type="hidden" name="sbsj" id="sbsj" value="<bean:write name="sbsj"/>" />
				</logic:present>
				<logic:present name="zywt">
				<input type="hidden" name="zywt" id="zywt" value="<bean:write name="zywt"/>" />
				</logic:present>
				<logic:present name="brth">
				<input type="hidden" name="brth" id="brth" value="<bean:write name="brth"/>" />
				</logic:present>
				<logic:present name="lxjs">
				<input type="hidden" name="lxjs" id="lxjs" value="<bean:write name="lxjs"/>" />
				</logic:present>
				<logic:present name="bz">
				<input type="hidden" name="bz" id="bz" value="<bean:write name="bz"/>" />
				</logic:present>
			</logic:equal>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��Ϣ��ѯ - ������Ϣ</a>
				</p>
			</div>
			
			<div class="toolbox">				 
			 <!--��ѯ����-->
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="document.forms[0].go.value='go';refreshForm('/xgxt/stu_info.do');this.disabled=true;">
							��ѯ
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>
		      		<th>�꼶</th>
					<td>
						<html:select property="nj" 
							onchange="initZyList();initBjList();" styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
					<th>ѧ��</th>
					<td>
						<html:text property="xh" style="width:100" onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/stu_info.do');}"/>
					</td>
					<th>����</th>
					<td>
						<html:text property="xm" onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/stu_info.do');}"/>
					</td>
				</tr>
				<tr>
		      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" 
							onchange="initZyList();initBjList();" styleId="xy" style="width:200px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th>רҵ</th>
					<td>
						<html:select property="zydm"  styleId="zy" style="width:200px"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>�༶</th>
					<td>
						<html:select property="bjdm"  styleId="bj" style="width:200px">
							<logic:notEqual value="yes" name="isBzr">
							<html:option value=""></html:option>
							</logic:notEqual>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
			  </tbody>
			</table>				
		</div>
		<div class="formbox">
			<h3 class="datetitle_01">
		    <span>
		    	��ѯ���&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">δ�ҵ��κμ�¼��</font> 
		 		 </logic:empty>
				<logic:notEmpty name="rs">
				<logic:empty name="zzmm">
					<font color="blue">˫��һ�п���ѡ����������ͷ��������</font>
				</logic:empty>
				<logic:notEmpty name="zzmm">
					<logic:equal name="zzmm" value="ybdy">
					<font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ��������ע:��ѯ���Ϊ�ǵ�Ա</font>
					</logic:equal>
					<logic:equal name="zzmm" value="rdjjfz">
					<font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ��������ע:��ѯ���Ϊ�ǵ�Ա��Ԥ����Ա</font>
					</logic:equal>
				</logic:notEmpty>
				</logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <table summary="" class="dateline" width="100%" id="rsTable">
			    <thead>
			      <tr>
			        <logic:iterate id="tit" name="topTr" offset="0" length="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
					<logic:iterate id="tit" name="topTr" offset="2">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand"
							ondblclick="sendStuInfo()">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="left">
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="2">
								<td align="left">
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>
			   </table>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			      <script type="text/javascript">
					$('choose').className="hide";
				</script>
			    <!--��ҳ��ʾ-->
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
			</logic:notEmpty>
		</div>
	</html:form>
</body>
</html>
