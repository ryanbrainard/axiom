<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<a href="http://github.com/ryanbrainard/axiom" target="_blank"><img style="position: absolute; top: 0; right: 0; border: 0;" src="https://s3.amazonaws.com/github/ribbons/forkme_right_white_ffffff.png" alt="Fork me on GitHub"></a>

<div id="container"> <!-- closed footer -->

	<s:if test="%{getText('header.showLogo')}">
		<div id="appLogo">
			<s:a href="Home.action">
				<img src="<s:url value="%{getText('app.logo')}"/>" border="0"/>
			</s:a>
		</div>
	</s:if>
	<s:else>
		<div id="appLogo"/>
	</s:else>

	
	<s:if test="%{getText('header.showLangMenu')}">
		<div id="langMenu">
	        <s:url id="en" action="%{getText('page.reload')}">
	            <s:param name="request_locale">en</s:param>
	        </s:url>
	        <s:a href="%{en}"><s:text name="label.lang.en"/></s:a>
	        &nbsp;|&nbsp;
	        <s:url id="ko" action="%{getText('page.reload')}">
	            <s:param name="request_locale">ko</s:param>
	        </s:url>
	        <s:a href="%{ko}"><s:text name="label.lang.ko"/></s:a>
		</div>
	</s:if>
	<s:else>
		<div id="langMenu"></div>
	</s:else>

	<s:if test="%{getText('header.showNavBar')}">
		<div id="navBar">
			<s:iterator value="breadcrumbs" status="breadcrumbStatus">
				<a href="${href}">${title}</a>
				<s:if test="!#breadcrumbStatus.last"> | </s:if>
			</s:iterator>
		</div>
	</s:if>
	<s:else>
		<div id="navBar"></div>
	</s:else>
	
	<s:if test="%{getText('header.showPageIntro')}">
		<div id="pageIntro">
			<s:text name="page.intro"/>
		</div>
	</s:if>
	<s:else>
		<div id="pageIntro"></div>
	</s:else>
	
	<div class="mainBody"> <!-- closed in footer -->
