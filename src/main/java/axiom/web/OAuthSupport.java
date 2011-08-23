package axiom.web;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * @author Ryan Brainard
 * @since 2011-08-22
 */
public abstract class OAuthSupport extends AxiomSupport implements SessionAware {

    Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
