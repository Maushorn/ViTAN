package application;

import java.io.Serializable;

/**This class represents the information that is needed to act on behalf of a Twitter-Account
 * and ist used to serialize the information.
 *
 */
public class AuthenticationInfo implements Serializable {

    private final String CONSUMER_KEY;
    private final String CONSUMER_SECRET;
    private final String ACCESS_TOKEN;
    private final String ACCESS_TOKEN_SECRET;

    static final long serialVersionUID = 5551840;

    public AuthenticationInfo(String consumer_key, String consumer_secret, String access_token, String access_token_secret) {
        CONSUMER_KEY = consumer_key;
        CONSUMER_SECRET = consumer_secret;
        ACCESS_TOKEN = access_token;
        ACCESS_TOKEN_SECRET = access_token_secret;
    }

    public String getCONSUMER_KEY() {
        return CONSUMER_KEY;
    }

    public String getCONSUMER_SECRET() {
        return CONSUMER_SECRET;
    }

    public String getACCESS_TOKEN() {
        return ACCESS_TOKEN;
    }

    public String getACCESS_TOKEN_SECRET() {
        return ACCESS_TOKEN_SECRET;
    }
}
