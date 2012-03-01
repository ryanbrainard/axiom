<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<a href="http://github.com/ryanbrainard/axiom" target="_blank"><img style="position: absolute; top: 0; right: 0; border: 0;" src="https://a248.e.akamai.net/assets.github.com/img/7afbc8b248c68eb468279e8c17986ad46549fb71/687474703a2f2f73332e616d617a6f6e6177732e636f6d2f6769746875622f726962626f6e732f666f726b6d655f72696768745f6461726b626c75655f3132313632312e706e67" alt="Fork me on GitHub"></a>

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
