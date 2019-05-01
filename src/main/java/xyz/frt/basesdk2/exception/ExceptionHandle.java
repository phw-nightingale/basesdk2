package xyz.frt.basesdk2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.frt.basesdk2.common.AppConst;
import xyz.frt.basesdk2.common.JsonResult;

@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @ResponseBody
    @ExceptionHandler
    public JsonResult handle(Exception e) {
        e.printStackTrace();
        if (e instanceof SecKillException) {
            return JsonResult.error(((SecKillException) e).getStatus(), e.getMessage());
        } else {
            return JsonResult.error(AppConst.STATUS_ERROR, e.getMessage());
        }
    }

}
