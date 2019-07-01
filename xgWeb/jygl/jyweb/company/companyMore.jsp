<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/jyweb.ini"%>
	</head>
<body>
<html:form action="/jyweb">
<div class="index_mainbody">
 <div class="index_topframe">
	<!-- logo 和 菜单 -->
    <jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>    
  </div>
    <div class="index_mainframe">
		<div class="type_left">
			<div class="type_UserInfo">
				<h3><span>用户信息</span></h3>
				<div class="con">
					<logic:present name="jyweb_userName">
	                	<p>欢迎您：<font color="#025BBB">${jyweb_realName }</font></p>
						<div class="btn" align="center"><button onclick="refreshForm('/xgxt/jyweb.do?method=logout')">注 销</button></div>
					</logic:present>
					<logic:notPresent name="jyweb_userName">
						<p>欢迎您：<font color="#025BBB">游客</font></p>
						<div class="btn" align="center"><button onclick="refreshForm('/xgxt/jyweb.do?method=index')">登录</button></div>
					</logic:notPresent>
                </div>
			</div>
        </div>
      <div class="type_right">
		<div class="rightcon">
       	  <h3><span>单位列表</span></h3>
          <div class="tabcon">
          	<div class="type_search">
          		<html:hidden property="queryequals_shzt" value="通过"/>
          	
            	<label>
            		单位性质
            		<html:select property="queryequals_dwxz">
	      				<html:options collection="dwxzList" property="dm" labelProperty="mc"/>
	      			</html:select>
            	</label>
            	<label>
            		行业分类
            		<html:select property="queryequals_hyfl">
	      				<html:options collection="hyflList" property="dm" labelProperty="mc"/>
	      			</html:select>
            	</label>
            	
                <div class="hdm">
                	<ul>
                    	
                    </ul>
                    <p><html:text property="querylike_dwmc" maxlength="25" value="请输入单位名称" 
                    	onfocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                    	onblur="if(!value){value=defaultValue;this.style.color='#999'}" 
                    	style="color:#999999"/>
                    <button 
                    	onclick="if(document.getElementById('querylike_dwmc').value==document.getElementById('querylike_dwmc').defaultValue){document.getElementById('querylike_dwmc').value=''} 
                    	allNotEmpThenGo('/xgxt/jyweb.do?method=companyMore&doType=query')" 
                    	id="search_go"></button></p>
                </div>
            </div>
            
            <logic:present name="rs">
            <logic:notEmpty name="rs">
            <table width="800px" border="0" class="tab_03">
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
			      <tr>
			        <td align="center">
						<a href="/xgxt/jyweb.do?method=companyInfo&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>">
							<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
						</a>
					</td>
					<logic:iterate id="v" name="s" offset="2">
						<td align="center">
							${v }
						</td>
					</logic:iterate>
			      </tr>
		      </logic:iterate>
              </tbody>
            </table>
            <jsp:include flush="true" page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
			<script type="text/javascript">
				$('choose').className="hide";
			</script>
            </logic:notEmpty>
            </logic:present>
          </div>
		</div>
      </div>
    </div>
    <div class="index_botframe">
      	<jsp:include flush="true" page="/jygl/jyweb/foot.jsp"></jsp:include>
    </div>
</div>
</html:form>
</body>
</html>
