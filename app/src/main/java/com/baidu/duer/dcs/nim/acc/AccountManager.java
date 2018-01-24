package com.baidu.duer.dcs.nim.acc;

/**
 * Created by kenway on 18/1/23 15:59
 * Email : xiaokai090704@126.com
 */

public class AccountManager {
    private static AccountManager manager;

    private String account;
    private String token;
    private String appkey;

    private AccountManager() {

    }

    /**
     * 获取帐号管理类单例模式
     *
     * @return
     */
    public static AccountManager getInstance() {
        AccountManager inst = manager;
        if (inst == null) {
            synchronized (AccountManager.class) {
                inst = manager;
                if (inst == null) {
                    inst = new AccountManager();
                    manager = inst;
                }
            }
        }
        return inst;
    }

    public void save(String account, String token, String appkey) {
        this.account = account;
        this.token = token;
        this.appkey = token;
    }

    public String getAccount() {
        return account;
    }

    public String getToken() {
        return token;
    }

    public String getAppkey() {
        return appkey;
    }
}
