package xyz.frt.basesdk2.exception;

public class SecKillException extends RuntimeException {

    private Integer status;

    public SecKillException(Integer status, String errMsg) {
        super(errMsg);
        this.status = status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

}
