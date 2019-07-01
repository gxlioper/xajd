<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/qgzxFunction.js"></script>
	<script>
		function checkTime(){
			var result = true;
			result = checkSjTj('querygreaterequal_kssj','��ʼʱ��ο�ʼʱ��','querylessequal_kssj','����ʱ��');
			if(result){
				result = checkSjTj('querygreaterequal_jssj','����ʱ��ο�ʼʱ��','querylessequal_jssj','����ʱ��');
			}
			return result;
		}
		function del(url){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("cbv").length; i++){
		    	if(document.getElementsByName("cbv")[i].checked){
		    		RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ�������õļ�¼��");
				return false;
			}
			
			if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
				return false;
			}
			refreshForm(url);
		}
	</script>
</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/qgzxZgdzdx" method="post">
			<input type="hidden" name="realTable" value="${realTable }"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
					  		<li> <a href="#" onclick="showTopWin('/xgxt/qgzxZgdzdx.do?method=xxtsUpdate&doType=add','500','350');" class="btn_zj">�� ��</a> </li>
							<li> <a href="#" onclick="showInfo('/xgxt/qgzxZgdzdx.do?method=xxtsUpdate&doType=modi','update','500','350');" class="btn_xg">�� ��</a> </li>
							<li> <a href="#" onclick="del('/xgxt/qgzxZgdzdx.do?method=xxts&doType=del');" class="btn_sc">ɾ ��</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
					  </logic:equal>
						<li> <a href="#" onclick="expData('/xgxt/qgzxZgdzdx.do?method=xxts&doType=expData');" class="btn_dc">��������</a> </li>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="if(checkTime()){allNotEmpThenGo('/xgxt/qgzxZgdzdx.do?method=xxts&doType=query')}">
									��ѯ
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>��ʼʱ���</th>
						<td>
							<html:text property="querygreaterequal_kssj" style="width:90px"
							onclick="return showCalendar('querygreaterequal_kssj','y-mm-dd');" 
							styleId="querygreaterequal_kssj" onblur="dateFormatChg(this)" 
							
							/> -
							<html:text property="querylessequal_kssj"  style="width:90px"
							onclick="return showCalendar('querylessequal_kssj','y-mm-dd');" 
							styleId="querylessequal_kssj" onblur="dateFormatChg(this)" 
							
							/>
						</td>
						<th>����ʱ���</th>
						<td>
							<html:text property="querygreaterequal_jssj" style="width:90px"
							onclick="return showCalendar('querygreaterequal_jssj','y-mm-dd');" 
							styleId="querygreaterequal_jssj" onblur="dateFormatChg(this)" 
							readonly="true"
							/> -
							<html:text property="querylessequal_jssj"  style="width:90px"
							onclick="return showCalendar('querylessequal_jssj','y-mm-dd');" 
							styleId="querylessequal_jssj" onblur="dateFormatChg(this)" 
							readonly="true"
							/>								
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>					  
					  </tbody>
					</table>
				</div>
				</div>

				<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
<!--						<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px"/>-->
					</td>
					<logic:iterate id="tit" name="topTr" offset="1" scope="request">
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
							style="cursor:hand;">
							<td align=center>
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" id="cbv" name="primarykey_cbv" value="<bean:write name="v"/>" />
						   		</logic:iterate>
						    </td>
							<logic:iterate id="v" name="s" offset="1" >
								<td>
									${v}
								</td>
							</logic:iterate>
							
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxZgdzdxForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				
				</logic:notEmpty>
				</div>
		</html:form>
		 
		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("����ʧ�� ��");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>