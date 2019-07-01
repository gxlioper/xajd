<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="javascript" src='/xgxt/dwr/interface/getTowdays.js'></script>
	<script type="javascript" src='/xgxt/dwr/interface/getScoreinfo.js'></script>
	<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "hzjyXtyglDjxsQuery.do?act=go&doType=query";
		 	document.forms[0].submit();
    }
   
   	function viewmore(){
		var url ="/xgxt/xsjbxx_morequery.do?doType=view&pkValue=";
		var pkValue ="";

		   pkValue = curr_row.getElementsByTagName("input")[0].value;
		   url += pkValue;
		   showTopWin(url, 620, 620);
	
		}
	function refreshtheweb()
		{
			document.forms[0].action = "fdyxxQuery.do";
            document.forms[0].submit();
		}
	 function  hzjyDataExport(){
	       var realTable = $("realTable").value;
	       document.forms[0].action = "/xgxt/hzjy_xssqbDataExport.do?realTable="+realTable;
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
        
     function fdyxxtb(){
            BatAlert.showTips('正在同步，请稍候...');
            document.forms[0].action = "fdyxxQuery.do?act=tb";
            document.forms[0].submit();
     
     }
	</script>
	</head>
	<body>
		<html:form action="/fdyxxQuery" method="post">
		
			<div class="tab_cur">
				<p class="location">
						<em>您的当前位置:</em><a>系统维护 - 权限维护 - 辅导员信息同步</a>
				</p>
			</div>
			<div class="toolbox">
				<div class="searchtab">
			    <table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button onclick="fdyxxtb();">
							辅导员信息同步
						  </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
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
			    </span>
			    </h3>
			   
			  <logic:notEmpty name="rs">
			  <table summary="" class="dateline" align="" width="100%">
			    <thead>
			      <tr>
					<logic:iterate id="tit" name="topTr" offset="1"scope="request">
						<td id="${tit.en}" onclick="tableSort(this)">
							${tit.cn}
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
			      <logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" ondblclick=""
								align="center">
								<td>
								<input type="hidden"
									value="<bean:write name="s" property="rid"/>" />
									<bean:write name='s' property='行号' />
								</td>
								<td>
									<bean:write name='s' property='zgh' />
								</td>
								<td>
									<bean:write name='s' property='xm' />
								</td>
								<td>
									<bean:write name='s' property='dzyx' />
								</td>
							</tr>
					</logic:iterate>
			    </tbody>
			  </table>
			  <!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			      <script type="text/javascript">
					$('choose').className="hide";
				 </script>
			  <!--分页显示-->
			  </logic:notEmpty>
			</div>		
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none" ></button>
				<logic:notEmpty name="tb">
					<logic:equal name="tb" value="ok">
						<input type="hidden" name="howmuchsucess"
							value="<bean:write name="howmuchsucess" />" />
						<script>
						var howmuchsucess = $("howmuchsucess").value;
                      alert("同步已完成！共接收到"howmuchsucess"条新数据");
                      window.dialogArguments.document.getElementById('search_go').click();
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="tb">
					<logic:equal name="tb" value="no">
						<script>
                      alert("远程连接失败，请检查是否正确配置！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="crosstime">
					<logic:equal name="crosstime" value="no">
						<script>
                      alert("两次同步间隔时间不能小于1个小时，请稍后再试！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
