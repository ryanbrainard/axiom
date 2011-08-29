package axiom.oauth;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Map;

/**
 * @author Ryan Brainard
 * @since 2011-08-28
 */
public abstract class AbstractOAuthContext {

    private String host;

    public AbstractOAuthContext(String host) {
        this.host = host;
    }

    public void setFieldsFrom(URI uri) {
        for (NameValuePair param : URLEncodedUtils.parse(uri, "UTF-8")) {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (field.getName().equals(param.getName())) {
                    try {
                        field.setAccessible(true);
                        field.set(this, param.getValue());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void setFieldsFrom(Map<String, String> map) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (map.containsKey(field.getName())) {
                try {
                    field.setAccessible(true);
                    field.set(this, map.get(field.getName()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

}
