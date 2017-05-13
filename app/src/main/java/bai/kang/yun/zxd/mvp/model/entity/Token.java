package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class Token {
    boolean successed;
    String token_type;
    String access_token;
    String scope;
    int expires_in;

    public boolean isSuccessed() {
        return successed;
    }

    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "Token{" +
                "successed=" + successed +
                ", token_type='" + token_type + '\'' +
                ", access_token='" + access_token + '\'' +
                ", scope='" + scope + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
