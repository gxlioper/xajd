<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<body>
<%--		<div class="tab_cur">--%>
<%--			<p class="location">--%>
<%--				<em>您的当前位置:</em><a>相关新闻</a>--%>
<%--			</p>--%>
<%--		</div>--%>

<!--层-->
<div class="mainframe">
    <!--普通新闻页-->
    <div class="newspage">
        <div class="newspagebg">
            <div class="title">
                <h3>
                    <bean:write name="map" property="newstitle"/>
                </h3>
                <h4>

							<span>
                                发布时间：
							    <bean:write name="map" property="fbsj"/>
							</span>
                        <span>
                            发布人：
                           <bean:write name="map" property="fbr"/>
                        </span>

                        <span>
							发布部门：
							<bean:write name="map" property="fbbmmc"/>
                        </span>
                </h4>
            </div>
            <div class="newcont">
                <bean:write name="map" property="newscont" filter="false" />
            </div>

            <div class="newsbar">
                <button type="button" onclick="javascript:window.window.close();" name="关闭"
                        class="button">
                    关 闭
                </button>
            </div>
        </div>

    </div>


</div>

<%--		<div class="botframe">--%>
<%--			<div class="copy">--%>
<%--				<span>开发单位：杭州正方软件股份有限公司 版权所所有&copy;2010 联系电话：0570-89902888</span>--%>
<%--			</div>--%>
<%--		</div>--%>
</body>
</html>
