<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/jxjzgl/jxjzgl.js"></script>
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			//searchRs();
			loadJxjzTjb();
		}

		//���ؾ�ѵ����ͳ�Ʊ�
		function loadJxjzTjb(){
			var url="lsjzgl_cxLsjz_ajax.do?method=cxLsjzTjbAjax";
			var sjid=jQuery("#sjid").val();
			var parameter={"sjid":sjid};
			jQuery("#div_rs").load(url,parameter,function(data){
				setTimeout("setDivHeight()","200");
			});
		}

		//��ѯ��ʷ��������
		function loadLsjzxx(){
			var url="lsjzgl_cxLsjz_ajax.do?method=cxLsjxxxAjax";
			var sjid=jQuery("#sjid").val();
			var parameter={"sjid":sjid};
			jQuery("#lsjxjzxx").load(url,parameter,function(data){
			});
		}
		
		//��ѯ ��ѵ�����Ƽ��� 
		function searchJxjzTjb(){
			loadJxjzTjb();
			loadLsjzxx();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//���������Ƽ���
		function dcLsjzTjb(){
			var url = "lsjzgl_cxLsjz_ajax.do?method=dcJzTjb";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		//��ѯ��ʷ��������
		function cxLsjzmd(){
			var url = "lsjzgl_cxLsjz.do?method=cxLsjzmd";
			document.forms[0].action = url;
			document.forms[0].submit();
		}

		//��ѯ��ѵ��������
		function cxJzjzmdByJzid(jzid){
			var url="lsjzgl_cxLsjz.do?method=cxLsjzmd";
			var sjid=jQuery("#sjid").val();
			url=url+"&jzid="+jzid+"&sjid="+sjid;
			window.location.href=url;
		}

		//��ѯ��ѵ��������
		function cxJzjzmdByJzidAndBjdm(jzid,bjdm){
			var url="lsjzgl_cxLsjz.do?method=cxLsjzmd";
			var sjid=jQuery("#sjid").val();
			url=url+"&jzid="+jzid+"&sjid="+sjid+"&bjdm="+bjdm;
			window.location.href=url;
		}

		//��ѯ��ѵ��������
		function cxJzjzmdPage(){
			var url="lsjzgl_cxLsjz.do?method=cxLsjzmd";
			var sjid=jQuery("#sjid").val();
			url=url+"&sjid="+sjid;
			//url=url+"&jzid="+jzid;
			window.location.href=url;
		}

		//��ѯ��ѵ�������� �ѱ���
		function cxJzjzmdPageYbz(){
			var url="lsjzgl_cxLsjz.do?method=cxLsjzmd";
			var sjid=jQuery("#sjid").val();
			url=url+"&sjid="+sjid;
			url=url+"&sjjz=1";
			window.location.href=url;
		}

		//��ѯ��ѵ��������  δ����
		function cxJzjzmdPageWbz(){
			var url="lsjzgl_cxLsjz.do?method=cxLsjzmd";
			var sjid=jQuery("#sjid").val();
			url=url+"&sjid="+sjid;
			url=url+"&sjjz=0";
			window.location.href=url;
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.���ͳ�Ʊ��е������鿴������ϸ��Ϣ
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/jxjzgl_cxJxjz" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="cxLsjzmd();return false;" class="btn_ck">
								������ѯ
							</a>
						</li>
						<li>
							<a href="#" onclick="dcLsjzTjb();return false;" class="btn_dc">
								����ͳ���
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
				
			</div>
			
			<!-- �������� -->	
			<div style="display: none;">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �������� end-->
			
			<!-- ��ѯ����start -->
			<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th width="10%">��ѵ����</th>
								<td colspan="3">
								<logic:present name="jxxxList">
									<select name="sjid" id="sjid">
											<logic:iterate name="jxxxList" id="jxxx" >
											<logic:equal name="jxxx" property="jxid" value="${jxxxwhModel.jxid}">
											<option selected="selected" value="${jxxx.jxid}">${jxxx.jxmc}</option>
											</logic:equal>
											<logic:notEqual name="jxxx" property="jxid" value="${jxxxwhModel.jxid}">
											<option value="${jxxx.jxid}">${jxxx.jxmc}</option>
											</logic:notEqual>
											</logic:iterate>
									</select>
								</logic:present>
									<button type="button" class="btn_cx" id="search_go"
											onclick="searchJxjzTjb();return false;">
										�� ѯ
									</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<!-- ��ѯ����end -->
			
			<!--����start-->
			    <h3 class="datetitle_01">
			    <span id="lsjxjzxx">��ѵ���ƣ�${jxxxwhModel.jxmc} &nbsp;��ѵ������ <a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPage()">${rsTj.cxrs }</a>��&nbsp;�ѱ���������<a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPageYbz()">${rsTj.ybzrs }</a>��&nbsp;��δ����������<a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPageWbz()">${rsTj.wbzrs }</a>��</span>
			    </h3>
			<!--����end-->
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<DIV style="OVERFLOW-X: auto; OVERFLOW-Y: hidden; WIDTH: 100%; HEIGHT: 363px"
					id="div_rs">
					
				</DIV>


				<!--��ҳ��ʾ-->
				<div style="clear:both;" style="display: none; ">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jxjzglFrom"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>