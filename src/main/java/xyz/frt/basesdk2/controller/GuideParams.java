package xyz.frt.basesdk2.controller;

import xyz.frt.basesdk2.common.graph.Position;

public class GuideParams {

    public Position[] ps;

    public Integer maxVerNum;

    public Position[] getPs() {
        return ps;
    }

    public void setPs(Position[] ps) {
        this.ps = ps;
    }

    public Integer getMaxVerNum() {
        return maxVerNum;
    }

    public void setMaxVerNum(Integer maxVerNum) {
        this.maxVerNum = maxVerNum;
    }
}
