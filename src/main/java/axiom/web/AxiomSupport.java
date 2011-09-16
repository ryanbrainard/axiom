package axiom.web;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public abstract class AxiomSupport extends ActionSupport
        implements
        Breadcrumbable
        , ServletRequestAware
        , ServletResponseAware
        , ServletContextAware {

    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(AxiomSupport.class);

    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;
    private ServletContext servletContext;

    public String getVersion() {
        return getServletContext().getInitParameter("version");
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    protected HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    protected HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    protected ServletContext getServletContext() {
        return servletContext;
    }

    public String noExecute() throws Exception {
        return SUCCESS;
    }

    public abstract Breadcrumbable getParentPage();

    public List<Breadcrumb> getBreadcrumbs() {
        Breadcrumbable parentPage = getParentPage();

        List<Breadcrumb> breadcrumbs;

        if (parentPage == null) {
            breadcrumbs = new ArrayList<Breadcrumb>();
        } else {
            breadcrumbs = getParentPage().getBreadcrumbs();
        }

        Breadcrumb bc = new Breadcrumb();
        bc.setTitle(getText("page.title"));
        bc.setHref(getText("page.reload"));

        breadcrumbs.add(bc);

        return breadcrumbs;
    }

    public String getAxiomEndpoint() {
        HttpServletRequest req = getServletRequest();
        // http://hostname.com:80/mywebapp/servlet/MyServlet/a/b;c=123?d=789
        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String contextPath = req.getContextPath();   // /mywebapp

        return scheme + "://" + serverName + ":" + serverPort + contextPath;
    }

}
