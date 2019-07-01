<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "zjgyzyXxhz_ajax.do?method=fdyManage";
			var ie = 'ie';
		
			var parameter = {
				"ie":ie
			};
			
			jQuery("select,input",jQuery("#tbody_search_query")).each(function(){
				
				parameter[jQuery(this).attr("name")]=escape(jQuery(this).val());
			});
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchGo(url,parameter);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			setTimeout("setDivHeight()","1000");
		}
		
		
		function printFdy(){
	   		
	  		var url = "zjgyzyXxhz.do?method=printFdyExcel";
		   	document.forms[0].action = url;
		   	document.forms[0].target = "_blank";
		   	document.forms[0].submit();
	  	 	document.forms[0].target = "_self";
	   }
		</script>
	</head>
	<body onload="onShow()">

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/zjgyzyXxhz" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmid" id="xmid" />

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->

				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="printFdy();return false;"
								class="btn_tj">����Ա��Ϣһ��</a>
						</li>
					</ul>
				</div>

				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody id="tbody_search_query">
							<tr>
							<th>
								ְ����
							</th>
							<td>
								<input type="text" name="zgh" id="zgh" />
							</td>
							<th>
								����
							</th>
							<td>
								<input type="text" name="xm" id="xm" />
							</td>
							<th >
								�Ա�
							</th>
							<td>
								<select name="xb" id="xb" style="width:145px">
									<option value=""></option>
									<option value="��">��</option>
									<option value="Ů">Ů</option>
								</select>
							</td>
							<th >
								ְ��
							</th>
							<td>
								<input type="text" name="zwmc" id="zwmc" />
							</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjgyzyXxhzForm"></jsp:include>
				<%--				<script type="text/javascript">--%>
				<%--						$('choose').className="hide";--%>
				<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->


			<div id="div_zjbb" style="display:none">

			</div>

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
