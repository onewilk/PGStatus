package it.wilk.pg.model;

import java.util.ArrayList;

/**
 * 解析结果
 * Created by Mr.Wilk on 2016/08/12 0012.
 */
public class ParseResult {
    public int openNum;
    public int closeNum;
    public ArrayList<StatusInfo> dataList = new ArrayList<>();

    public ParseResult(String updateTime, int openNum, int closeNum, ArrayList<StatusInfo> dataList) {
        this.openNum = openNum;
        this.closeNum = closeNum;
        this.dataList = dataList;
    }

    public ParseResult() {

    }

    public void clearData(){
        openNum=0;
        closeNum =0;
        dataList.clear();
    }
}
