package it.wilk.pg.model;

/**
 * 状态信息
 * Created by Mr.Wilk on 2016/08/12 0012.
 */

public class StatusInfo {
    public String flagUrl;
    public String name;
    public boolean isOpen;

    public StatusInfo() {
    }

    public StatusInfo(String flagUrl, String name, boolean isOpen) {
        this.flagUrl = flagUrl;
        this.name = name;
        this.isOpen = isOpen;
    }
}
