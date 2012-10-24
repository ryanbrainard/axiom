package axiom.web;

import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;

public class SelfAuthenticator extends AxiomSupport {
    private static Logger logger = Logger.getLogger(SelfAuthenticator.class);

    @Override
    public String execute() throws Exception {

        String auth = getServletRequest().getParameter("auth");

        if (auth == null) {
            logger.debug("Sending to input page.");
            return INPUT;
        } else {
            logger.debug("preparing response.");

            Long wait = 0L;

            try {
                wait = Long.valueOf(getServletRequest().getParameter("wait"));
            } catch (NumberFormatException nfe) {
                logger.warn("invalid number format" + nfe);
            }

            logger.debug("sleeping for " + wait + " ms");
            Thread.sleep(wait);

            logger.debug("retutning success for response to be delivered");
            return SUCCESS;
        }

    }

    public String getServiceEndpoint() throws MalformedURLException {
        final URL self = new URL(getServletRequest().getRequestURL().toString());
        return self.getProtocol() + "://" + self.getHost() +
                (self.getPort() != 80 || self.getPort() != 443 ? ":" + self.getPort() : "") +
                getServletRequest().getAttribute("javax.servlet.forward.request_uri");
    }

    @Override
    public Breadcrumbable getParentPage() {
        return new Home();
    }

}
